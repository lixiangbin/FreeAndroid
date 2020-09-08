package com.lxb.freeAndroid.frame.http;

import com.lxb.freeAndroid.frame.http.gsonConverterFactory.CustomGsonConverterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * 业务名: Retrofit实例管理
 * 功能说明:
 * 编写日期: xxxx-08-26.
 * 作者: 李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public class RetrofitManager {

    /**
     * 作者：李相斌
     * 创建时期：xxxx-08-27
     * 方法说明：默认RetrofitService
     */
    public static <T> T getRetrofitService(Class<T> service) {
        OkHttpClient okHttpClient = OkHttpClientManager.getOkHttpClient();
        return getRetrofit(okHttpClient).create(service);
    }

    /**
     * 作者：李相斌
     * 创建时期：xxxx-08-27
     * 方法说明：可配置超时时间的RetrofitService
     */
    public static <T> T getRetrofitService(Class<T> service, long connectTimeouts, long readTimeouts, long writeTimeouts) {
        OkHttpClient okHttpClient = OkHttpClientManager.getOkHttpClient(connectTimeouts, readTimeouts, writeTimeouts);
        return getRetrofit(okHttpClient).create(service);
    }


    /**
     * 作者：李相斌
     * 创建时期：xxxx-08-27
     * 方法说明：构建Retrofit提取
     */
    private static Retrofit getRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(CustomGsonConverterFactory.create())
                .baseUrl(ApiUrl.BASE_URL)
                .build();
    }
}
