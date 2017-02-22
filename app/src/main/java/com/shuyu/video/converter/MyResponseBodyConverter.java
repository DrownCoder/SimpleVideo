package com.shuyu.video.converter;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.shuyu.video.utils.DataSignUtils;
import com.shuyu.video.utils.LogUtils;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by zhangleilei on 10/27/16.
 */

final class MyResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    /**
     * 构造器
     */
    MyResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    /**
     * 转换
     *
     * @param responseBody
     * @return
     * @throws IOException
     */
    @Override
    public T convert(ResponseBody responseBody) throws IOException {
        String response = responseBody.string();
        String result = DataSignUtils.decryptData(response);
        if (TextUtils.isEmpty(result)) {
            result = response;
        }
        LogUtils.d(MyResponseBodyConverter.class.getName(), result);
        responseBody = ResponseBody.create(MEDIA_TYPE, result);
        JsonReader jsonReader = gson.newJsonReader(responseBody.charStream());
        try {
            return adapter.read(jsonReader);
        } finally {
            responseBody.close();
        }
    }
}
