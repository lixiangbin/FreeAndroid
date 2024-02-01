package com.lxb.freeAndroid.frame.http;


import com.lxb.freeAndroid.frame.base.AppConfig;
import com.lxb.freeAndroid.frame.http.interceptor.DefaultInterceptor;
import com.lxb.freeAndroid.frame.http.interceptor.HttpLogInterceptor;
import com.lxb.freeAndroid.frame.http.interceptor.TestLogInterceptor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
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

    private static final long connectTimeout = 30;
    private static final long readTimeout = 50;
    private static final long writeTimeout = 2;

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
     * 方法说明：SSL安全校验配置 1
     * 信任所有库
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

        // 实例化SSL上下文
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLS");
            // 初始化SSL上下文
            sslContext.init(null, new TrustManager[]{trustCert}, new SecureRandom());
            builder.sslSocketFactory(sslContext.getSocketFactory(), trustCert);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
        //校验服务器主机
        builder.hostnameVerifier((hostname, session) -> true);//信任所有
    }


    /**
     * 作者：李相斌
     * 创建时期：xxxx-08-27
     * 方法说明：SSL安全校验配置 2
     * 配置双向认证
     */
    private static void configSSL_KT(OkHttpClient.Builder builder, String keyStorePath, String password, String trustStorePath) {

        //双向认证管理器，在需要双向认证时使用
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

        /*-----------------------密钥配置-----------------------*/
        // 实例化密钥库
        KeyManagerFactory keyManagerFactory = null;
        try {
            keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            //keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 获得密钥库
        KeyStore keyStore = null;
        try {
            //keystore的类型，默认是jks
            keyStore = KeyStore.getInstance("JKS");
            //创建jkd密钥访问库    123456是keystore密码。
            keyStore.load(new FileInputStream(keyStorePath), "123456".toCharArray());
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException |
                 IOException e) {
            e.printStackTrace();
        }
        // 初始化密钥工厂
        try {
            keyManagerFactory.init(keyStore, password.toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            e.printStackTrace();
        }

        /*-----------------------信任配置-----------------------*/
        // 实例化信任库
        TrustManagerFactory trustManagerFactory = null;
        try {
            trustManagerFactory = TrustManagerFactory
                    .getInstance(TrustManagerFactory.getDefaultAlgorithm());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 获得信任库
        KeyStore trustStore = null;
        try {
            trustStore = KeyStore.getInstance("JKS");
            //创建jkd密钥访问库    123456是trustStore密码。
            trustStore.load(new FileInputStream(trustStorePath), "123456".toCharArray());
        } catch (CertificateException | IOException | NoSuchAlgorithmException |
                 KeyStoreException e) {
            e.printStackTrace();
        }
        // 初始化信任工厂
        try {
            trustManagerFactory.init(trustStore);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

        /*-----------------------SSL处理-----------------------*/
        // 实例化SSL上下文
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLS");
            // 初始化SSL
            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());
            builder.sslSocketFactory(sslContext.getSocketFactory(), trustCert);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }

        //校验服务器主机
        builder.hostnameVerifier((hostname, session) -> {
            if ("www.test.com".equals(hostname)) {
                return true;
            } else {
                HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
                return hv.verify(hostname, session);
            }

        });
    }


}
