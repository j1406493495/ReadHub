package cn.com.woong.readhub.domain;

import android.text.TextUtils;

import com.blankj.utilcode.util.SPUtils;

import java.io.IOException;

import cn.com.woong.readhub.constant.Constant;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @author woong
 */

public class ResponseHeaderInterceptor implements Interceptor {
    public ResponseHeaderInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        String token = response.headers().get("token");
        if (!TextUtils.isEmpty(token)) {
            SPUtils.getInstance().put(Constant.SP_TOKEN, token);
        }

        return response;
    }
}
