package com.lxb.freeAndroid.project.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import com.lxb.freeAndroid.frame.base.AppApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * 业务名: App工具类
 * 功能说明:
 * 编写日期: 2019-12-16.
 * 作者: 李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public class AppUtils {

    /**
     * 作者：李相斌
     * 创建时期：2019-12-16
     * 方法说明：获取设备id
     * ANDROID_ID
     */
    @SuppressLint("HardwareIds")
    public static String getDeviceId() {
        Context context = AppApplication.getInstance();
        String deviceId;
        deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return deviceId;
    }

    /**
     * 作者：李相斌
     * 创建时期：2019-12-24
     * 方法说明：跳转到应用设置
     */
    private static final String PACKAGE_URL_SCHEME = "package:";
    private static final int REQUEST_SETTING_CAMERA_CODE = 1000;

    public static void startAppSettings(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse(PACKAGE_URL_SCHEME + activity.getPackageName()));
        activity.startActivityForResult(intent, REQUEST_SETTING_CAMERA_CODE);
    }
}
