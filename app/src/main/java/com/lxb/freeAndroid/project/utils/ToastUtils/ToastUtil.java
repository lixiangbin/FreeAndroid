package com.lxb.freeAndroid.project.utils.ToastUtils;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;

/**
 * 业务名:ToastUtils
 * 功能说明:自定义Toast
 * 编写日期: xxxx-08-29.
 * 作者:
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public class ToastUtil {

    public static void toastShow(Context context, String text) {
        if(!TextUtils.isEmpty(text)) {
            ToastView toast = new ToastView(context, text);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }


    public static void toastShow(Context context, int text) {
        ToastView toast = new ToastView(context, text);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
