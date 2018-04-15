package cn.com.woong.readhub.domain;


import android.text.TextUtils;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.SPUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import cn.com.woong.readhub.App;
import cn.com.woong.readhub.BuildConfig;
import cn.com.woong.readhub.constant.Constant;
import cn.com.woong.readhub.domain.cookie.SetCookieCache;
import cn.com.woong.readhub.domain.converter.ResponseConverterFactory;
import cn.com.woong.readhub.domain.converter.StringConverterFactory;
import cn.com.woong.readhub.domain.cookie.PersistentCookieJar;
import cn.com.woong.readhub.domain.cookie.SharedPrefsCookiePersistor;
import okhttp3.Authenticator;
import okhttp3.CacheControl;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.BufferedSink;
import okio.Okio;
import okio.Sink;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ApiManager
 * Created by justin on 16/3/8.
 */
public class ApiManager {
    private static final String BASE_URL = "https://api.readhub.me/";

    private HashMap<Class, Retrofit> SERVICE_RETROFIT_BIND = new HashMap<>();

    private Retrofit retrofit_webapi;
    private ConcurrentHashMap<Class, Object> cachedApis = new ConcurrentHashMap<>();
    private ResponseHeaderInterceptor headInterceptor = new ResponseHeaderInterceptor();
    private String userAgent;
    private PersistentCookieJar cookieJar;

    public ApiManager() {
        cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(App.getAppContext()));

        // init okhttp 3 logger
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        // init cache
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logInterceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(45, TimeUnit.SECONDS)
                .writeTimeout(55, TimeUnit.SECONDS)
                .addInterceptor(headInterceptor)
                .addNetworkInterceptor(mCommonInfoInterceptor)
                .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .authenticator(mAuthenticator)
                .cookieJar(cookieJar)
                .build();

        retrofit_webapi = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(StringConverterFactory.create())
//                .addConverterFactory(ResponseConverterFactory.create())
                .build();

        SERVICE_RETROFIT_BIND.put(ApiService.class, retrofit_webapi);
    }

    public void cleanCookie() {
        SPUtils.getInstance().put(Constant.SP_TOKEN, "");
        if (cookieJar != null) {
            cookieJar.clear();
        }
    }

    public <T> void addService(Class<T> clz) {
        SERVICE_RETROFIT_BIND.put(clz, retrofit_webapi);
    }

    public <T> T getService(Class<T> clz) {
        Object obj = cachedApis.get(clz);
        if (obj != null) {
            return (T) obj;
        } else {
            Retrofit retrofit = SERVICE_RETROFIT_BIND.get(clz);
            if (retrofit != null) {
                T service = retrofit.create(clz);
                cachedApis.put(clz, service);
                return service;
            } else {
                return null;
            }
        }
    }

    Interceptor mCommonInfoInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Map<String, String> added = new HashMap<>();
            added.put("version", "v1.0.7");
            Request newRequest = interceptFormBody(request, added);

            if (newRequest.body() != null) {
                String userToken = SPUtils.getInstance().getString(Constant.SP_TOKEN);
                Request.Builder header = newRequest.newBuilder()
                        .header("Content-Length", newRequest.body().contentLength() + "")
                        .header("User-Agent", !TextUtils.isEmpty(userAgent) ? userAgent : getUserAgent());
                if (!TextUtils.isEmpty(userToken)) {
                    header.addHeader("token", userToken);
                }
                newRequest = header.build();

            } else {
            /*NO OP*/
            }
            return chain.proceed(newRequest);
        }
    };

    private String getUserAgent() {
        userAgent = DeviceUtils.getManufacturer() + "/device=" + DeviceUtils.getModel() + "/version=" + AppUtils.getAppVersionName();
        return userAgent;

    }

    Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", cacheControl())
                    .build();
        }
    };

    private String cacheControl() {
        String cacheHeaderValue;
        if (NetworkUtils.isConnected()) {
            int maxAge = 33; // read from cache for 1 minute
            cacheHeaderValue = "public, max-age=" + maxAge;
        } else {
            int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
            cacheHeaderValue = "public, only-if-cached, max-stale=" + maxStale;
        }
        return cacheHeaderValue;
    }

    Authenticator mAuthenticator = new Authenticator() {
        @Override
        public Request authenticate(Route route, Response response) throws IOException {
            return response.request().newBuilder()
                    .addHeader("Authorization", "newAccessToken")
                    .build();
        }
    };

    public static Request interceptFormBody(Request request, Map<String, String> added)
            throws IOException {

        RequestBody requestBody = request.body();

        if (requestBody instanceof FormBody) {
            FormBody formBody = (FormBody) requestBody;
            FormBody.Builder formBuilder = new FormBody.Builder();
            if (formBody.size() > 0) {
                for (int idx = 0; idx < formBody.size(); idx++) {
                    formBuilder.addEncoded(formBody.encodedName(idx),
                            formBody.encodedValue(idx));
                }
            }
            if (added != null && added.size() > 0) {
                for (Map.Entry<String, String> entry : added.entrySet()) {
                    formBuilder.add(entry.getKey(), entry.getValue());
                }
            }
            return request.newBuilder().post(formBuilder.build()).build();
        } else if (requestBody instanceof MultipartBody) {
            MultipartBody multipartBody = (MultipartBody) requestBody;
            MultipartBody.Builder multipartBuilder = new MultipartBody.Builder();
            if (multipartBody.size() > 0) {
                for (int idx = 0; idx < multipartBody.size(); idx++) {
                    multipartBuilder.addPart(multipartBody.part(idx));
                }
            }
            if (added != null && added.size() > 0) {
                for (Map.Entry<String, String> entry : added.entrySet()) {
                    multipartBuilder.addFormDataPart(
                            entry.getKey(), null,
                            RequestBody.create(
                                    MediaType.parse("text/plain; charset=UTF-8"), entry.getValue()));
                }
            }
            multipartBody = multipartBuilder.build();
            return request.newBuilder().post(multipartBody)  // need update boundary
                    .header("Content-Type", "multipart/form-data; boundary=" + multipartBody.boundary())
                    .build();
        } else if (requestBody != null) {
            //RequestBody
            if (request.method().equals("POST")) {
                FormBody.Builder formBuilder = new FormBody.Builder();
                if (added != null && added.size() > 0) {
                    for (Map.Entry<String, String> entry : added.entrySet()) {
                        formBuilder.add(entry.getKey(), entry.getValue());
                    }
                }
                return request.newBuilder().post(formBuilder.build()).header("Content-Type", "application/x-www-form-urlencoded").build();

            } else {
                StringBuilder sbParam = new StringBuilder("");
                if (added != null && added.size() > 0) {
                    for (Map.Entry<String, String> entry : added.entrySet()) {
                        sbParam.append("&")
                                .append(entry.getKey())
                                .append("=")
                                .append(URLEncoder.encode(entry.getValue()));
                    }

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    Sink sink = Okio.sink(baos);
                    BufferedSink bufferedSink = Okio.buffer(sink);

                    /**
                     * Write old params
                     * */
                    request.body().writeTo(bufferedSink);

                    /**
                     * write to buffer additional params
                     * */
                    bufferedSink.writeString(sbParam.toString(), Charset.defaultCharset());

                    RequestBody newRequestBody = RequestBody.create(
                            request.body().contentType(),
                            bufferedSink.buffer().readUtf8()
                    );
                    return request.newBuilder().post(newRequestBody).build();
                }


            }

        }

        return request;
    }

}
