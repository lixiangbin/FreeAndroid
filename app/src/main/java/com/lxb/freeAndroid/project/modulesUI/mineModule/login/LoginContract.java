package com.lxb.freeAndroid.project.modulesUI.mineModule.login;

import com.lxb.freeAndroid.frame.base.BaseResponseBean;
import com.lxb.freeAndroid.frame.http.ResponseObserver;
import com.lxb.freeAndroid.frame.mvp.IModel;
import com.lxb.freeAndroid.frame.mvp.IView;

import java.util.HashMap;

/**
 * 业务名:登陆
 * 功能说明:
 * 编写日期: xxxx-09-05.
 * 作者: 李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public class LoginContract {


    interface LoginView extends IView {

    }

    interface LoginModel extends IModel {
        void request(HashMap<String, Object> paramMap, ResponseObserver<BaseResponseBean> responseObserver);
    }
}
