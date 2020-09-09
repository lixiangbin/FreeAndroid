package com.lxb.freeAndroid.frame.mvp;


import com.lxb.freeAndroid.frame.base.AppConfig;
import com.lxb.freeAndroid.frame.base.BaseResponseBean;
import com.lxb.freeAndroid.frame.http.ResponseObserver;
import com.lxb.freeAndroid.frame.http.RetrofitManager;
import com.lxb.freeAndroid.frame.http.Service;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


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
    protected  <T> void requestNetworkResetTimeOut(Map<String, Object> map, ResponseObserver<T> responseObserver, String url, long connectTimeouts, long readTimeouts, long writeTimeouts) {
        Service service = RetrofitManager.getRetrofitService(Service.class, connectTimeouts, readTimeouts, writeTimeouts);
        configRetrofitAndRx(service, map, responseObserver, url);
    }


    /**
     * 作者：李相斌
     * 创建时期：xxxx-08-27
     * 方法说明：提取封装 配置Retrofit与Rx
     */
    private <T> void configRetrofitAndRx(Service service, Map<String, Object> map, ResponseObserver<T> responseObserver, String url) {
        service.executePost(url, setParams(map))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //.subscribe(responseObserver);
                //此处不直接使用形参-responseObserver，以将订阅事件在Model层加入管理容器中
                .subscribe(new Observer<BaseResponseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        responseObserver.onSubscribe(d);
                        addCompositeDisposable(d);
                    }

                    @Override
                    public void onNext(BaseResponseBean baseResponseBean) {
                        responseObserver.onNext(baseResponseBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        responseObserver.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        responseObserver.onComplete();
                    }
                });
    }


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
        map.put("ver", AppConfig.VER);
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
