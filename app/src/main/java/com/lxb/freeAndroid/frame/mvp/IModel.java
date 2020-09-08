package com.lxb.freeAndroid.frame.mvp;

/**
 * 业务名: Model 顶层接口
 * 功能说明:
 * 编写日期: xxxx/8/25 0025.
 * 作者: 李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public interface IModel {

    //解除相关绑定或rx异步任务
    void onDestroy();
}
