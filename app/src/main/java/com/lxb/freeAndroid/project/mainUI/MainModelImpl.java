package com.lxb.freeAndroid.project.mainUI;


import com.lxb.freeAndroid.frame.http.ApiUrl;
import com.lxb.freeAndroid.frame.http.ResponseObserver;
import com.lxb.freeAndroid.frame.mvp.BaseModel;

import java.util.HashMap;

/**
 * 业务名:MainModel的实现类
 * 功能说明:
 * 编写日期: xxxx/8/26 0026.
 * 作者: 李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public class MainModelImpl extends BaseModel implements MainContract.MainModel {


    @Override
    public void login(HashMap<String, Object> paramMap, ResponseObserver<MainBean> responseObserver) {
        requestNetworkDefault(paramMap, responseObserver, ApiUrl.API_TEST);
    }

    @Override
    public void quit(HashMap<String, Object> paramMap, ResponseObserver<MainBean> responseObserver) {
        requestNetworkDefault(paramMap, responseObserver, ApiUrl.API_TEST);
    }
}
