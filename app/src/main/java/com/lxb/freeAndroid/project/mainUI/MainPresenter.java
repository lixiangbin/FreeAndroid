package com.lxb.freeAndroid.project.mainUI;

import android.util.Log;

import com.lxb.freeAndroid.frame.base.BaseResponseBean;
import com.lxb.freeAndroid.frame.http.ResponseObserver;
import com.lxb.freeAndroid.frame.mvp.BasePresenter;
import com.lxb.freeAndroid.project.utils.GsonUtils;

/**
 * 作者：李相斌
 * 创建时期：xxxx-08-29
 * 方法说明：
 */
public class MainPresenter extends BasePresenter<MainContract.MainModel, MainContract.MainView> {

    MainPresenter(MainContract.MainView view) {
        super(view);
    }

    @Override
    protected MainContract.MainModel getModelImpl() {
        return new MainModelImpl();
    }

    //测试
    public void login(String pwd) {
        paramsMap.clear();
        paramsMap.put("pwd", "123456");
        model.login(paramsMap, new ResponseObserver<BaseResponseBean>() {
            @Override
            public void onSuccess(BaseResponseBean responseBean) {

                MainBean loginBean = GsonUtils.fromJson(responseBean.result, MainBean.class);

                Log.i("数据onSuccess", responseBean == null ? "空！" : responseBean.toString());
            }

            @Override
            public void onFail(BaseResponseBean responseBean) {
                Log.i("数据onFail", responseBean == null ? "空！" : responseBean.toString());
            }
        });
    }


}
