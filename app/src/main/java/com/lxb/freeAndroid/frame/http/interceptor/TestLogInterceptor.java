package com.lxb.freeAndroid.frame.http.interceptor;

import android.util.Log;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 业务名:测试Logger框架
 * 功能说明:
 * 编写日期: xxxx-08-28.
 * 作者: 李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public class TestLogInterceptor  implements HttpLoggingInterceptor.Logger {
    @Override
    public void log(String message) {
        Log.d("HttpLogInfo-Logger", message);
    }
}
