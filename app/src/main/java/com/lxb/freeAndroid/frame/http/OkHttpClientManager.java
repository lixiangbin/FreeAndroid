package com.lxb.freeAndroid.frame.http;


import com.lxb.freeAndroid.frame.base.AppConfig;
import com.lxb.freeAndroid.frame.http.interceptor.DefaultInterceptor;
import com.lxb.freeAndroid.frame.http.interceptor.HttpLogInterceptor;
import com.lxb.freeAndroid.frame.http.interceptor.TestLogInterceptor;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 业务名: OkHttpClient实例管理
 * 功能说明:
 * 编写日期: xxxx-08-26.
 * 作者: 李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public class OkHttpClientManager {

    private static long connectTimeout = 30;
    private static long readTimeout = 50;
    private static long writeTimeout = 2;

    private static volatile OkHttpClient okHttpClient;


    /**
     * 作者：李相斌
     * 创建时期：xxxx-08-27
     * 方法说明：获取普通OkHttpClient实例
     */
    public static OkHttpClient getOkHttpClient() {


        if (okHttpClient == null) {
            synchronized (OkHttpClientManager.class) {
                if (okHttpClient == null) {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    builder.connectTimeout(connectTimeout, TimeUnit.SECONDS)
                            .readTimeout(readTimeout, TimeUnit.SECONDS)
                            .writeTimeout(writeTimeout, TimeUnit.MINUTES);
                    builder.addInterceptor(new DefaultInterceptor());
                    if (AppConfig.isDebug) {
                        builder.addInterceptor(new HttpLogInterceptor());
                        builder.addInterceptor(new HttpLoggingInterceptor(new TestLogInterceptor()).setLevel(HttpLoggingInterceptor.Level.BODY));
                    }
                   // configSSL(builder);
                    okHttpClient = builder.build();
                }
            }
        }
        return okHttpClient;
    }

    /**
     * 作者：李相斌
     * 创建时期：xxxx-08-27
     * 方法说明：可配置超时时间的OkHttpClient实例
     * 时间必须大于0
     */
    public static OkHttpClient getOkHttpClient(long connectTimeouts, long readTimeouts, long writeTimeouts) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(connectTimeouts > 0 ? connectTimeouts : connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeouts > 0 ? readTimeouts : readTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeouts > 0 ? writeTimeouts : writeTimeout, TimeUnit.MINUTES);
        builder.addInterceptor(new DefaultInterceptor());
        if (AppConfig.isDebug) {
            builder.addInterceptor(new HttpLogInterceptor());
            builder.addInterceptor(new HttpLoggingInterceptor(new TestLogInterceptor()).setLevel(HttpLoggingInterceptor.Level.BODY));
        }
       // configSSL(builder);
        return builder.build();
    }


    /**
     * 作者：李相斌
     * 创建时期：xxxx-08-27
     * 方法说明：SSL安全校验配置
     */
    private static void configSSL(OkHttpClient.Builder builder) {
        X509TrustManager trustCert = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };

        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{trustCert}, new SecureRandom());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        builder.sslSocketFactory(sslContext.getSocketFactory(), trustCert);
        builder.hostnameVerifier((hostname, session) -> true);
    }


}
