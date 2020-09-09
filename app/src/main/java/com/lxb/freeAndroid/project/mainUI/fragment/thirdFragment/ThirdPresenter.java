package com.lxb.freeAndroid.project.mainUI.fragment.thirdFragment;


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
public class ThirdPresenter extends BasePresenter<ThirdContract.CaeModel, ThirdContract.CaeView> {
    public ThirdPresenter(ThirdContract.CaeView view) {
        super(view);
    }

    @Override
    protected ThirdContract.CaeModel getModelImpl() {
        return new ThirdModelImpl();
    }


}
