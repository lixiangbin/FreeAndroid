package com.lxb.freeAndroid.frame.http;

import com.lxb.freeAndroid.frame.base.AppApplication;
import com.lxb.freeAndroid.frame.base.BaseResponseBean;
import com.lxb.freeAndroid.project.utils.ToastUtils.ToastUtil;

import org.apache.http.conn.ConnectTimeoutException;

import java.net.ConnectException;
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
public abstract class ResponseObserver<T> implements Observer<BaseResponseBean<T>> {

    //private Context context;

    public ResponseObserver() {
    }

    //public ResponseObserver(@NonNull Context context) {
    //this.context = context;
    //初始化Loading等
    //}


    @Override
    public void onNext(BaseResponseBean<T> responseBean) {
        //TODO...待根据接口文档具体调整
        if (null != responseBean && responseBean.succeed == 1) {
            onSuccess(responseBean.result);
            onSuccessAllData(responseBean);
        } else {
            onFail(responseBean);
            onFailAllData(responseBean);
        }
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onComplete() {
        //TODO..待添加取消加载动画等业务处理
    }

    @Override
    public void onError(Throwable e) {

        //TODO...待根据接口协议具体封装

        if (e instanceof HttpException) {
            HttpException exception = (HttpException) e;
            if (exception.code() == 401) {//例 401:被挤下线
                /*if (!AppApplication.getInstance().isForeground(LoginActivity.class.getName())) {
                    //退到登录界面
                    AppApplication.getInstance().startActivity(LoginActivity.class);
                    AppApplication.getInstance().finishAllActivityToLogin();
                    //清除token
                    SPUtils.remove(AppApplication.getInstance().getApplicationContext(), SPKeyConstant.TOKEN);
                    SPUtils.remove(AppApplication.getInstance().getApplicationContext(), SPKeyConstant.USER_ID);
                }*/
                ToastUtil.toastShow(AppApplication.getInstance().getApplicationContext(), "请重新登录");
            } else {
                ToastUtil.toastShow(AppApplication.getInstance().getApplicationContext(), "操作失败，请检查您的网络");
                e.printStackTrace();
                onNetError(e);
            }

        } else if ((e instanceof UnknownHostException) || (e instanceof ConnectTimeoutException) || (e instanceof SocketTimeoutException) || e instanceof ConnectException) {
            ToastUtil.toastShow(AppApplication.getInstance().getApplicationContext(), "操作失败，请检查您的网络");
            e.printStackTrace();
            onNetError(e);
        } else {
            ToastUtil.toastShow(AppApplication.getInstance().getApplicationContext(), "未知异常");
            e.printStackTrace();
            onNetError(e);
        }
        onCallError(e);
        //取消loading动画
        //TODO...
    }

    //请求成功
    public abstract void onSuccess(T responseBean);

    //请求成功 - 返回所有原始数据
    public void onSuccessAllData(BaseResponseBean<T> baseResponseBean) {
    }

    //请求失败
    public abstract void onFail(BaseResponseBean<T> responseBean);

    //请求失败 - 返回所有原始数据
    public void onFailAllData(BaseResponseBean<T> baseResponseBean) {
    }

    //网络错误
    public void onNetError(Throwable e) {
    }

    //抛出的所有错误
    public void onCallError(Throwable e) {
    }


}
