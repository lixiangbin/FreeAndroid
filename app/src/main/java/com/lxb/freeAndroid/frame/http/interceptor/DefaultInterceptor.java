package com.lxb.freeAndroid.frame.http.interceptor;

import org.jetbrains.annotations.NotNull;

import java.io.EOFException;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * 业务名:拦截器
 * 功能说明:
 * 编写日期: xxxx-08-27.
 * 作者: 李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public class DefaultInterceptor implements Interceptor {


    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {

        //获取请求体参数 打印日志
        System.out.println("DefaultInterceptor---解析请求头");
        RequestBody requestBody = chain.request().body();
        Charset charset = StandardCharsets.UTF_8;
        MediaType contentType = requestBody.contentType();
        if (contentType != null) {
            charset = contentType.charset(charset);
        }
        Buffer buffer = new Buffer();
        requestBody.writeTo(buffer);
        if (isPlaintext(buffer)) {//判断是否明文
            String requestBodyStr = URLDecoder.decode(buffer.readString(charset));
            System.out.println("请求体参数：" + requestBodyStr);
        } else {
            System.out.println("请求体参数(非明文 charset)：" + charset.toString());
            System.out.println("请求体参数(非明文 URLDecoder)：" + URLDecoder.decode(buffer.readString(charset)));
        }


        //获取请求头参数 打印日志
        Map<String, List<String>> map = chain.request().headers().toMultimap();
        if (map.size() > 0) {
            map.forEach((kayStr, valueStrList) -> {
                System.out.println("请求头参数-" + kayStr + ":" + valueStrList.get(0));
            });
        } else {
            System.out.println("无请求头参数");
        }
        System.out.println("-------------------------------------------");

        return chain.proceed(chain.request());
    }


    /**
     * 作者：李相斌
     * 创建时期：0001 xxx/2/1
     * 方法说明：是否明文
     */
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
            return false; // 缩减的 UTF-8 序列
        }
    }
}
