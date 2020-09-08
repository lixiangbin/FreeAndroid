package com.lxb.freeAndroid.frame.http.interceptor;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

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

        //TODO...

        return chain.proceed(chain.request());
    }
}
