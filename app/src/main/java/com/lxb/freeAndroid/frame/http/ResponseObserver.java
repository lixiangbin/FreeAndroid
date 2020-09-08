package com.lxb.freeAndroid.frame.http;

import com.lxb.freeAndroid.frame.base.AppApplication;
import com.lxb.freeAndroid.frame.base.BaseResponseBean;
import com.lxb.freeAndroid.project.utils.ToastUtils.ToastUtil;

import org.apache.http.conn.ConnectTimeoutException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * 业务名:网络响应处理
 * 功能说明:
 * 编写日期: xxxx-08-26.
 * 作者: 李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public abstract class ResponseObserver<T extends BaseResponseBean> implements Observer<T> {

    public ResponseObserver() {
    }


    @Override
    public void onNext(T responseBean) {
//        if (view != null && view.getContext() != null) {
//            Activity activity = (Activity) view.getContext();
//            if (activity.isFinishing()) {
//                return;
//            }
//        }

        //TODO...待根据接口文档具体调整
        if (null != responseBean && responseBean.succeed == 1) {
            onSuccess(responseBean);
        } else {
            onFail(responseBean);
        }
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onComplete() {
//        if (view != null && view.getContext() != null) {
//            Activity activity = (Activity) view.getContext();
//            if (activity.isFinishing()) {
//                return;
//            }
//        }
        //TODO..待添加取消加载动画等业务处理
    }

    @Override
    public void onError(Throwable e) {

        //TODO...待根据接口协议具体封装

        if (e instanceof HttpException) {
            HttpException exception = (HttpException) e;
            if (exception.code() == 401) {
                ToastUtil.toastShow(AppApplication.getInstance().getApplicationContext(), "请重新登录");
            } else {
                ToastUtil.toastShow(AppApplication.getInstance().getApplicationContext(), "操作失败，请检查您的网络");
                onNetError(e);
            }

        } else if ((e instanceof UnknownHostException) || (e instanceof ConnectTimeoutException) || (e instanceof SocketTimeoutException)) {
            ToastUtil.toastShow(AppApplication.getInstance().getApplicationContext(), "操作失败，请检查您的网络");
            onNetError(e);
        }else {
            ToastUtil.toastShow(AppApplication.getInstance().getApplicationContext(), "未知异常");
            e.printStackTrace();
            onNetError(e);
        }
        onCallError(e);
    }

    //请求成功
    public abstract void onSuccess(T responseBean);

    //请求失败
    public abstract void onFail(T responseBean);

    //网络错误
    public void onNetError(Throwable e) {
    }

    //抛出的所有错误
    public void onCallError(Throwable e) {
    }


}
