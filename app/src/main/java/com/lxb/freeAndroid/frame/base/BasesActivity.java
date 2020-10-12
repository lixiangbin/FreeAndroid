package com.lxb.freeAndroid.frame.base;

import com.lxb.freeAndroid.frame.mvp.BasePresenter;

/**
 * 业务名: BasesActivity
 * 功能说明: MVP Activity基类
 * 编写日期: xxxx-08-23
 * 作者:  李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */

public abstract class BasesActivity<P extends BasePresenter> extends OrdinaryBaseActivity {

    //Presenter实例
    protected P presenter;

    @Override
    void mvpPresenterInit() {
        super.mvpPresenterInit();
        //实例化Presenter
        presenter = initPresenter();
        //绑定presenter
        if (presenter != null) {
            presenter.bind();
        }
    }

    /**
     * 作者：李相斌
     * 创建时期：xxxx-08-23
     * 方法说明：初始化Presenter
     */
    protected abstract P initPresenter();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除presenter绑定
        if (presenter != null) {
            presenter.onDestroy();
            presenter = null;
        }
        System.gc();
    }


}
