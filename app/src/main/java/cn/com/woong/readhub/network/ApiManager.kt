package cn.com.woong.readhub.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit


/**
 * ApiManager
 * Created by woong on 18/4/23.
 */
class ApiManager {
    private val BASE_URL = "https://api.readhub.me/"
    private val mApiRetrofit: Retrofit
    private val mRetrofitServiceHashMap = mutableMapOf<Class<*>, Retrofit>()
    private val cachedApis = ConcurrentHashMap<Class<*>, Any?>()

    init {
        // init okhttp 3 logger
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.NONE
        // init cache
        val client = OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(45, TimeUnit.SECONDS)
                .writeTimeout(55, TimeUnit.SECONDS)
                .addInterceptor(logInterceptor)
                .build()

        mApiRetrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        mRetrofitServiceHashMap[ReadhubApiService::class.java] = mApiRetrofit
    }

    fun <T> addService(clz: Class<T>) {
        mRetrofitServiceHashMap[clz] = mApiRetrofit
    }

    fun <T> getService(clz: Class<T>): T? {
        val obj = cachedApis[clz]

        if (obj != null) {
            return obj as T?
        } else {
            val retrofit = mRetrofitServiceHashMap[clz]

            retrofit.let {
                val service = retrofit?.create(clz)
                cachedApis[clz] = service
                return service
            }
        }
    }
}