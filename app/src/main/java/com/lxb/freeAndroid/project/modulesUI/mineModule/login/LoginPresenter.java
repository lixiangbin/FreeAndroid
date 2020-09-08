package com.lxb.freeAndroid.project.modulesUI.mineModule.login;

import com.lxb.freeAndroid.frame.base.BaseResponseBean;
import com.lxb.freeAndroid.frame.http.ResponseObserver;
import com.lxb.freeAndroid.frame.mvp.BasePresenter;

/**
 * 业务名:登陆
 * 功能说明:
 * 编写日期: xxxx-09-09.
 * 作者: 李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public class LoginPresenter extends BasePresenter<LoginContract.LoginModel,LoginContract.LoginView> {

    public LoginPresenter(LoginContract.LoginView view) {
        super(view);
    }

    @Override
    protected LoginContract.LoginModel getModelImpl() {
        return new LoginModelImpl();
    }

    public void request(){
        paramsMap.clear();
        model.request(paramsMap, new ResponseObserver<BaseResponseBean>() {
            @Override
            public void onSuccess(BaseResponseBean responseBean) {

            }

            @Override
            public void onFail(BaseResponseBean responseBean) {

            }
        });


    }
}
