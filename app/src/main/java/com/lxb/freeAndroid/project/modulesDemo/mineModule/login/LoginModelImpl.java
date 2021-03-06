package com.lxb.freeAndroid.project.modulesDemo.mineModule.login;

import com.lxb.freeAndroid.frame.http.ApiUrl;
import com.lxb.freeAndroid.frame.http.ResponseObserver;
import com.lxb.freeAndroid.frame.mvp.BaseModel;

import java.util.HashMap;

/**
 * 业务名:登录
 * 功能说明:
 * 编写日期: xxxx-09-05.
 * 作者: 李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public class LoginModelImpl extends BaseModel implements LoginContract.LoginModel {
    @Override
    public void requestLogin(HashMap<String, Object> paramMap, ResponseObserver<LoginBean> responseObserver) {
        requestNetworkDefault(paramMap, responseObserver, ApiUrl.API_TEST);
    }
}
