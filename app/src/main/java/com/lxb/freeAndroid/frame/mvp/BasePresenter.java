package com.lxb.freeAndroid.frame.mvp;

import java.util.HashMap;

/**
 * 业务名:BasePresenter
 * 功能说明:Presenter基类
 * 编写日期: xxxx/8/25 0025.
 * 作者: 李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public abstract class BasePresenter<M extends IModel, V extends IView> implements IPresenter {
    protected M model;
    protected V view;
    protected HashMap<String, Object> paramsMap = new HashMap<>();

    public BasePresenter(V view) {
        this.view = view;
    }

    /**
     * 作者：李相斌
     * 创建时期：xxxx-08-25
     * 方法说明：使Presenter层持有model(impl)并绑定
     */
    protected abstract M getModelImpl();

    @Override
    public void bind() {
        model = getModelImpl();
    }

    /**
     * 作者：李相斌
     * 创建时期：xxxx-08-25
     * 方法说明：解除model、view, 与activity生命周期绑定，解除rx订阅
     */
    @Override
    public void onDestroy() {
        if (this.model != null) {
            this.model.onDestroy();
            this.model = null;
        }
        this.view = null;
        System.gc();
    }
}
