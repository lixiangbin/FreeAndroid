package com.lxb.freeAndroid.project.mainDemo.fragment.fourthFragment;

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
public class FourthPresenter extends BasePresenter<FourthContract.CaeModel, FourthContract.CaeView>  {
    public FourthPresenter(FourthContract.CaeView view) {
        super(view);
    }

    @Override
    protected FourthContract.CaeModel getModelImpl() {
        return new FourthModelImpl();
    }


}
