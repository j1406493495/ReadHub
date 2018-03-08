package cn.com.woong.readhub.domain.converter;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import cn.com.woong.readhub.domain.exception.ResultException;
import cn.com.woong.readhub.resp.BaseResponse;
import okhttp3.ResponseBody;
import retrofit2.Converter;

public class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    public JsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        T t = gson.fromJson(response, type);
        try {
            if (t instanceof BaseResponse) {
                LogUtils.d("Network", "response>>" + response);
                BaseResponse resultResponse = gson.fromJson(response, BaseResponse.class);
                if (resultResponse.success || resultResponse.code == 0) {
                    return gson.fromJson(response, type);
                } else {
                    throw new ResultException(resultResponse.errorCode, resultResponse.errorMsg);
                }
            } else {
                return t;
            }

        } finally {

        }
    }
}