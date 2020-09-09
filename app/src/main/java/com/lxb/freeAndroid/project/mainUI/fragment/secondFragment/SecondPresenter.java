package com.lxb.freeAndroid.project.mainUI.fragment.secondFragment;

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
public class SecondPresenter extends BasePresenter<SecondContract.CaeModel, SecondContract.CaeView>  {
    public SecondPresenter(SecondContract.CaeView view) {
        super(view);
    }

    @Override
    protected SecondContract.CaeModel getModelImpl() {
        return new SecondModelImpl();
    }


}
