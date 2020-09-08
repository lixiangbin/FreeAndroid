package com.lxb.freeAndroid.project.mainUI.fragment.homeFragment;

import com.lxb.freeAndroid.frame.base.BaseResponseBean;
import com.lxb.freeAndroid.frame.http.ResponseObserver;
import com.lxb.freeAndroid.frame.mvp.BasePresenter;

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
public class CaePresenter extends BasePresenter<CaeContract.CaeModel, CaeContract.CaeView>  {
    public CaePresenter(CaeContract.CaeView view) {
        super(view);
    }

    @Override
    protected CaeContract.CaeModel getModelImpl() {
        return new CaeModelImpl();
    }

    public void requestTest(){
        paramsMap.clear();
        model.requestData(paramsMap, new ResponseObserver<BaseResponseBean>() {
            @Override
            public void onSuccess(BaseResponseBean responseBean) {

            }

            @Override
            public void onFail(BaseResponseBean responseBean) {

            }
        });
    }

}
