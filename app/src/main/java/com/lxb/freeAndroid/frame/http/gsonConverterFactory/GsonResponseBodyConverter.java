package com.lxb.freeAndroid.frame.http.gsonConverterFactory;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * 业务名: 自定义Gson解析工厂
 * 功能说明: 处理服务器返回的 null 字段
 * 编写日期: xxxx-09-07.
 * 作者: 李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;
    private JsonReader jsonReader;

    GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }


    @Override
    public T convert(ResponseBody value) throws IOException {
        T result = null;
        //设置io信息
        MediaType contentType = value.contentType();
        Charset charset = contentType != null ? contentType.charset(UTF_8) : UTF_8;
        String responseJsonStr = value.string();
        InputStream inputStream;
        //使用JSONObject单独解析json字符串中字段
        JSONObject parseJson;
        //重新构建的json内容
        JsonObject jsonObject = new JsonObject();
        try {

            /*解析每个字段并添加到新jsonObject中*/
            parseJson = new JSONObject(responseJsonStr);
            //succeed
            int succeed = parseJson.optInt("succeed", 0);
            jsonObject.addProperty("succeed", succeed);
            //returnMsg
            String returnMsg = parseJson.optString("returnMsg", "");
            jsonObject.addProperty("returnMsg", returnMsg);
            //returnCode
            String returnCode = parseJson.optString("returnCode", "");
            jsonObject.addProperty("returnCode", returnCode);

            /*处理 "result" 字段为 null 的情况*/
            if (parseJson.optJSONObject("result") == null || "null".equals(parseJson.optJSONObject("result") + "")) {
                jsonObject.add("result", new JsonObject());
                Log.i("GsonResponseBodyConverter", "已重置 result 字段值内容！");
            } else {
                //将 result 原始内容加入jsonObject中
                JSONObject jo = parseJson.optJSONObject("result");
                JsonObject resultJson = gson.fromJson(jo.toString(), JsonObject.class);
                jsonObject.add("result", resultJson);
            }

            //写入字节流
            inputStream = new ByteArrayInputStream(jsonObject.toString().getBytes());
            //写入JsonReader
            Reader reader = new InputStreamReader(inputStream, charset);
            jsonReader = gson.newJsonReader(reader);

            //读取
            result = adapter.read(jsonReader);
            if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
                throw new JsonIOException("JSON document was not fully consumed.");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            value.close();
        }
        return result;
    }
}
