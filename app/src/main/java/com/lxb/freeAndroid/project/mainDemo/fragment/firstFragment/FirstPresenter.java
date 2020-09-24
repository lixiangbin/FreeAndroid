package com.lxb.freeAndroid.project.mainDemo.fragment.firstFragment;

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
public class FirstPresenter extends BasePresenter<FirstContract.CaeModel, FirstContract.CaeView>  {
    public FirstPresenter(FirstContract.CaeView view) {
        super(view);
    }

    @Override
    protected FirstContract.CaeModel getModelImpl() {
        return new FirstModelImpl();
    }

    /**
     * 作者：
     * 创建时期：xxxx-09-23
     * 方法说明：
     */
    public void requestTest(){
        paramsMap.clear();
        paramsMap.put("666","888");
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
