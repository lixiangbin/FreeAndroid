package com.lxb.freeAndroid.frame.mvp;


import android.util.Log;

import com.lxb.freeAndroid.frame.base.AppConfig;
import com.lxb.freeAndroid.frame.base.BaseResponseBean;
import com.lxb.freeAndroid.frame.http.ResponseObserver;
import com.lxb.freeAndroid.frame.http.RetrofitManager;
import com.lxb.freeAndroid.frame.http.Service;
import com.lxb.freeAndroid.project.utils.AppUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


/**
 * 业务名:网络请求
 * 功能说明:数据源
 * 处理http网络层与mvp的model层进行衔接
 * 编写日期: xxxx/8/26 0026.
 * 作者: 李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public class BaseModel implements IModel {

    //订阅管理
    private CompositeDisposable mCompositeDisposable;

    /**
     * 作者：李相斌
     * 创建时期：xxxx-08-26
     * 方法说明：默认请求
     */
    protected <T> void requestNetworkDefault(Map<String, Object> map, ResponseObserver<T> responseObserver, String url) {
        Service apiService = RetrofitManager.getRetrofitService(Service.class);
        configRetrofitAndRx(apiService, map, responseObserver, url);
    }

    /**
     * 作者：李相斌
     * 创建时期：xxxx-08-27
     * 方法说明：可重置超时时间的请求
     */
    protected <T> void requestNetworkResetTimeOut(Map<String, Object> map, ResponseObserver<T> responseObserver, String url, long connectTimeouts, long readTimeouts, long writeTimeouts) {
        Service service = RetrofitManager.getRetrofitService(Service.class, connectTimeouts, readTimeouts, writeTimeouts);
        configRetrofitAndRx(service, map, responseObserver, url);
    }


    /**
     * 作者：李相斌
     * 创建时期：xxxx-08-27
     * 方法说明：提取封装 配置Retrofit与Rx
     */
    private <T> void configRetrofitAndRx(Service service, Map<String, Object> map, ResponseObserver<T> responseObserver, String url) {
        Disposable disposable = service.executePost(url, setParams(map))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //采用方案2：手动转发ResponseObserver事件。
                //subscribe返回的Disposable会在订阅后加入Model层管理容器，避免忽略订阅结果。
                .doOnSubscribe(responseObserver::onSubscribe)
                .subscribe(
                        responseObserver::onNext,
                        responseObserver::onError,
                        responseObserver::onComplete
                );
        addCompositeDisposable(disposable);

        /*
         * 备用方案1：让Service返回Observable<BaseResponseBean<Object>>，再在Model层统一转换为Observable<BaseResponseBean<T>>。
         * 这个方案可以让主链路直接.subscribe(responseObserver)，但内部仍需要一次unchecked转换，所以暂时保留不启用。
         *
         * this.<T>executePost(service, url, setParams(map))
         *         .subscribeOn(Schedulers.io())
         *         .unsubscribeOn(Schedulers.io())
         *         .observeOn(AndroidSchedulers.mainThread())
         *         .doOnSubscribe(this::addCompositeDisposable)
         *         .subscribe(responseObserver);
         */
    }

//    @SuppressWarnings("unchecked")
//    private <T> Observable<BaseResponseBean<T>> executePost(Service service, String url, Map<String, Object> map) {
//        return (Observable<BaseResponseBean<T>>) (Observable<?>) service.executePost(url, map);
//    }


    /**
     * 作者：李相斌
     * 创建时期：xxxx-08-27
     * 方法说明：加入rx订阅管理
     */
    private void addCompositeDisposable(Disposable d) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(d);
    }

    /**
     * 作者：李相斌
     * 创建时期：xxxx-08-27
     * 方法说明：配置公共参数
     */
    private Map<String, Object> setParams(Map<String, Object> map) {
        map = map != null ? map : new LinkedHashMap<>();
        map.put("ver", AppConfig.VER);
        map.put("deviceId", AppUtils.getDeviceId());
        //TODO...
        return map;
    }

    @Override
    public void onDestroy() {
        //解除rx订阅
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
            mCompositeDisposable = null;
        }
    }
}
