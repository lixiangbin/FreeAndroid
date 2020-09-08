package com.lxb.freeAndroid.frame.mvp;

import android.content.Context;


/**
 * 业务名: View 顶层接口
 * 功能说明:
 * 编写日期: xxxx/8/25 0025.
 * 作者: 李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public interface IView {

    //获取context
    Context getContext();

    //信息提示
    void showMsg(String msg);
}
