package com.lxb.freeAndroid.frame.http.interceptor;

import android.util.Log;


import com.lxb.freeAndroid.frame.base.AppConfig;

import java.io.EOFException;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * 作者：
 * 创建时期：xxxx-08-29
 * 方法说明：数据拦截日志
 */

public class HttpLogInterceptor implements Interceptor {
    public static final String TAG = HttpLogInterceptor.class.getSimpleName();


    public HttpLogInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response;
        if (AppConfig.isDebug) {


            String url = request.url().toString();
            //the request method
            String method = request.method();
            long t1 = System.nanoTime();
            Log.d(TAG, "===========================网络请求监测日志===========================");
            Log.d(TAG, String.format(Locale.getDefault(), " %s ：%s", method, url));
            //the request body
            RequestBody requestBody = request.body();
            if (requestBody != null) {
                StringBuilder sbParams = new StringBuilder("请求参数：");
                StringBuilder sb = new StringBuilder("Content-Type：");
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);
                Charset charset = Charset.forName("UTF-8");
                MediaType contentType = requestBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(charset);
                }
                if (isPlaintext(buffer)) {
                    String myrequestBody = "";
                    try {
                        myrequestBody = URLDecoder.decode(buffer.readString(charset));
                    } catch (Exception e) {
                    }
                    sbParams.append(myrequestBody);
                    sb.append(contentType.toString()).append(",")
                            .append(requestBody.contentLength()).append("-byte body");
                } else {
                    sb.append(contentType.toString())
                            .append(",binary ").append(requestBody.contentLength()).append("-byte body omitted");
                }

                Log.d(TAG, String.format(Locale.getDefault(), "%s", sbParams.toString()));
                Log.d(TAG, String.format(Locale.getDefault(), "%s", sb.toString()));
                Log.d(TAG, " - ");
                Log.d(TAG, " - ");
            }
            Response responseTemp = null;
            try {
                responseTemp = chain.proceed(request);
            } catch (SocketTimeoutException socketTimeoutException) {
                Log.d(TAG, "连接超时");
                socketTimeoutException.printStackTrace();
            } catch (Exception e) {
                Log.d(TAG, "连接异常");
                Log.d(TAG, e.toString());
            } finally {
                response = responseTemp == null ? chain.proceed(request) : responseTemp;
            }
            long t2 = System.nanoTime();

            Log.d(TAG, String.format(Locale.getDefault(), "响应时长： %.1fms", (t2 - t1) / 1e6d));

            Log.d(TAG, String.format(Locale.CHINA, "响应状态： %s ,message[%s],code[%d]", response.isSuccessful() ? "成功" : "失败", response.message(), response.code()));

            //the response data
            ResponseBody body = response.body();

            BufferedSource source = body.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();
            Charset charset = Charset.defaultCharset();
            MediaType contentType = body.contentType();
            if (contentType != null) {
                charset = contentType.charset(charset);
            }
            String bodyString = buffer.clone().readString(charset);
            Log.d(TAG, String.format("响应数据：\n  %s", bodyString));

        }

        Log.d(TAG, "-\n\n");
        Log.d(TAG, "======================================================================");
        Log.d(TAG, "-\n\n");
        return response;
    }

    static boolean isPlaintext(Buffer buffer) {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }


    private static Map<String, String> urlSplit(String URLString) {
        Map<String, String> mapRequest = new HashMap<String, String>();
        String[] arrSplit = null;
        String strUrlParam = URLString;
        if (strUrlParam == null) {
            return mapRequest;
        }
        arrSplit = strUrlParam.split("&");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual;
            arrSplitEqual = strSplit.split("=");
            //解析出键值
            if (arrSplitEqual.length > 1) {
                //正确解析
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
            } else {
                if (arrSplitEqual[0] != "") {
                    //只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        return mapRequest;
    }


}