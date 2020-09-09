package com.lxb.freeAndroid.project.mainUI.presenter;

import com.lxb.freeAndroid.frame.base.BaseResponseBean;
import com.lxb.freeAndroid.frame.http.ResponseObserver;
import com.lxb.freeAndroid.frame.mvp.BasePresenter;
import com.lxb.freeAndroid.project.mainUI.MainBean;
import com.lxb.freeAndroid.project.mainUI.MainContract;
import com.lxb.freeAndroid.project.mainUI.MainModelImpl;

/**
 * 作者：李相斌
 * 创建时期：xxxx-08-29
 * 方法说明：
 */
public class MainPresenter extends BasePresenter<MainContract.MainModel, MainContract.MainView> {

    public MainPresenter(MainContract.MainView view) {
        super(view);
    }

    @Override
    protected MainContract.MainModel getModelImpl() {
        return new MainModelImpl();
    }

    /**
     * 作者：李相斌
     * 创建时期：
     * 方法说明：登录
     */
    public void login(String pwd) {
        paramsMap.clear();
        paramsMap.put("pwd", pwd);
       model.login(paramsMap, new ResponseObserver<MainBean>() {
           @Override
           public void onSuccess(MainBean responseBean) {

           }

           @Override
           public void onFail(BaseResponseBean responseBean) {

           }
       });
    }


}
