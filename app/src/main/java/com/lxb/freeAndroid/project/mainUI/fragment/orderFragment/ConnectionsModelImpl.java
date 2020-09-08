package com.lxb.freeAndroid.project.mainUI.fragment.orderFragment;

import com.lxb.freeAndroid.frame.base.BaseResponseBean;
import com.lxb.freeAndroid.frame.http.ApiUrl;
import com.lxb.freeAndroid.frame.http.ResponseObserver;
import com.lxb.freeAndroid.frame.mvp.BaseModel;

import java.util.HashMap;

/**
 * 业务名:
 * 功能说明:
 * 编写日期: xxxx-09-02.
 * 作者: 李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public class ConnectionsModelImpl extends BaseModel implements ConnectionsContract.CaeModel {
    @Override
    public void requestData(HashMap<String, Object> paramMap, ResponseObserver<BaseResponseBean> responseObserver) {
      requestNetworkDefault(paramMap,responseObserver,ApiUrl.API_TEST);
    }
}
