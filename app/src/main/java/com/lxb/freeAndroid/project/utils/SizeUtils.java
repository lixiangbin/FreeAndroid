package com.lxb.freeAndroid.project.utils;

import android.content.Context;

/**
 * 业务名: 尺寸转换工具类
 * 功能说明:
 * 编写日期:
 * 作者:  李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */

public class SizeUtils {

    public static int dp2px(Context context, float dpValue) {
        if (dpValue <= 0) return 0;
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static float sp2px(Context context, float spValue) {
        if (spValue <= 0) return 0;
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5);
    }

    public static int px2sp(Context context, float pxValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }
}
