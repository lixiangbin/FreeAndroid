package com.lxb.freeAndroid.project.modulesDemo.mineModule.login;

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

    /**
     * 作者：lxb
     * 创建时期：xxxx-09-23
     * 方法说明：请求登录接口demo
     */
    public void requestLogin(){
        paramsMap.clear();
        model.requestLogin(paramsMap, new ResponseObserver<LoginBean>() {
            @Override
            public void onSuccess(LoginBean responseBean) {

            }

            @Override
            public void onFail(BaseResponseBean<LoginBean> responseBean) {

            }
        });


    }
}
