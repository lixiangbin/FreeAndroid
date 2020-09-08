package com.lxb.freeAndroid.frame.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.lxb.freeAndroid.project.mainUI.MainActivity;
import com.lxb.freeAndroid.project.utils.LanguageUtils;

import java.util.List;
import java.util.Stack;

/**
 * 业务名: Application
 * 功能说明: 全局管理
 * 编写日期: xxxx-08-26.
 * 作者: 李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public class AppApplication extends Application {

    private static AppApplication appApplication;
    private Stack<Activity> activityList;

    @Override
    public void onCreate() {
        super.onCreate();
        appApplication = this;
        activityList = new Stack<>();
        //初始化ActivityLifecycle
        initActivityLifecycle();

    }

    /**
     * 作者：李相斌
     * 创建时期：xxxx-08-28
     * 方法说明：获取当前实例
     */
    public static synchronized AppApplication getInstance() {
        return appApplication;
    }

    /**
     * 作者：李相斌
     * 创建时期：xxxx-09-19
     * 方法说明：获取 Context
     */
    public Context getContext() {
        return getApplicationContext();
    }

    /**
     * 作者：李相斌
     * 创建时期：xxxx-08-28
     * 方法说明：获取指定名称的Activity
     * TODO...待测...
     */
    public Activity getThisActivity(Class<? extends Activity> activityCls) {
        if (activityList != null && activityList.size() > 0) {
            for (Activity activity : activityList) {
                if (activity.getClass() == activityCls) {
                    return activity;
                }
            }
        }
        return null;
    }

    /**
     * 作者：李相斌
     * 创建时期：xxxx-08-29
     * 方法说明：结束所有Activity
     */
    public void finishAllActivity() {
        //不包括MainActivity
        finishAct(false);
    }

    public void finishAllActivity(boolean isCoverMainActivity) {
        //包括MainActivity
        finishAct(isCoverMainActivity);
    }

    private void finishAct(boolean isCoverMainActivity) {
        if (activityList == null) {
            return;
        }
        for (Activity activity : activityList) {
            if (activity == null) {
                continue;
            }
            if (!isCoverMainActivity && activity.getClass() == MainActivity.class) {
                continue;
            }
            activity.finish();
        }
        activityList.clear();
    }


    /**
     * 作者：李相斌
     * 创建时期：xxxx-08-28
     * 方法说明：杀掉所有进程并退出
     */
    public void killAllProcess() {
        finishAllActivity(true);
        ActivityManager activityManager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processList = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : processList) {
            if (runningAppProcessInfo.pid != android.os.Process.myPid()) {
                android.os.Process.killProcess(runningAppProcessInfo.pid);
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    @Override
    protected void attachBaseContext(Context base) {
        //系统语言等设置发生改变时会调用此方法，需要要重置app语言
        super.attachBaseContext(LanguageUtils.attachBaseContext(base));
    }

    /**
     * 作者：李相斌
     * 创建时期：xxxx-08-28
     * 方法说明：全局Activity管理
     */
    private void initActivityLifecycle() {
        ActivityLifecycleCallbacks activityLifecycleCallbacks = new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                activityList.add(activity);
                LanguageUtils.bindOnActivityCreated(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                if (activityList == null || activityList.isEmpty()) {
                    return;
                }
                activityList.remove(activity);

            }
        };

        //注册ActivityLifecycle
        this.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

}
