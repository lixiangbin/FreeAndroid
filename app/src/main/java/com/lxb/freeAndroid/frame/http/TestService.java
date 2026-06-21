package com.lxb.freeAndroid.frame.http;

import com.lxb.freeAndroid.frame.base.BaseResponseBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.Request;
import okio.Timeout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 业务名:
 * 功能说明:
 * 编写日期: 0010 2025/1/10.
 * 作者: 李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
class TestService implements Service{
    @Override
    public Observable<BaseResponseBean> executePost(String url, Map<String, Object> map) {
        return null;
    }

    @Override
    public Call<BaseResponseBean> executeCall(String url, Map<String, Object> map) {
        return new Call<BaseResponseBean>() {
            @Override
            public Response<BaseResponseBean> execute() throws IOException {
                return null;
            }

            @Override
            public void enqueue(Callback<BaseResponseBean> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<BaseResponseBean> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }

            @Override
            public Timeout timeout() {
                return null;
            }
        };
    }

    @Override
    public ArrayList<String> executeCallArr(String url, Map<String, Object> map) {
        return null;
    }
}
