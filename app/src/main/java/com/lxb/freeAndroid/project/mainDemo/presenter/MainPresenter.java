package com.lxb.freeAndroid.project.mainDemo.presenter;

import com.lxb.freeAndroid.frame.mvp.BasePresenter;
import com.lxb.freeAndroid.project.mainDemo.MainContract;
import com.lxb.freeAndroid.project.mainDemo.MainModelImpl;

/**
 * 作者：李相斌
 * 创建时期：xxxx-08-29
 * 方法说明：
 */
public class MainPresenter extends BasePresenter<MainContract.MainModel, MainContract.MainView> {

    public MainPresenter(MainContract.MainView view) {
        super(view);
    }

    @Override
    protected MainContract.MainModel getModelImpl() {
        return new MainModelImpl();
    }


}
