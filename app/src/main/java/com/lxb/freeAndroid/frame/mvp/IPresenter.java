package com.lxb.freeAndroid.frame.mvp;

/**
 * 业务名: Presenter 顶层接口
 * 功能说明:
 * 编写日期: xxxx-08-23.
 * 作者: 李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public interface IPresenter {
    //绑定
    void bind();
    //解除绑定
    void onDestroy();
}
