package com.lxb.freeAndroid.frame.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.lxb.freeAndroid.project.mainDemo.MainActivity;
import com.lxb.freeAndroid.project.modulesDemo.mineModule.login.LoginActivity;
import com.lxb.freeAndroid.project.utils.LanguageUtils;

import java.util.Iterator;
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
    public Activity getTheActivity(Class<? extends Activity> activityCls) {
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

    /**
     * 作者：李相斌
     * 创建时期：
     * 方法说明：判断某个Activity是否在前台
     */
    public boolean isForeground(String ActivityClassName) {
        if (ActivityClassName == null || TextUtils.isEmpty(ActivityClassName))
            return false;
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
        if (list != null && list.size() > 0) {
            ComponentName cpn = list.get(0).topActivity;
            return ActivityClassName.equals(cpn.getClassName());
        }
        return false;
    }

    /**
     * 作者：李相斌
     * 创建时期：
     * 方法说明：统一跳转
     */
    public void startActivity(Class<? extends Activity> activityCls) {
        startActivity(activityCls, null);
    }

    public void startActivity(Class<? extends Activity> activityCls, Bundle bundle) {
        Intent intent = new Intent(this, activityCls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /**
     * 作者：李相斌
     * 创建时期：2019-12-13
     * 方法说明：退出到登录
     */
    public void finishAllActivityToLogin() {
        if (activityList == null) {
            return;
        }
        for (Activity activity : activityList) {
            if (activity == null) {
                continue;
            }
            if (activity.getClass() == LoginActivity.class) {
                continue;
            }
            activity.finish();
        }
        //移除已经finish的Activity
        for (Iterator<Activity> it = activityList.iterator(); it.hasNext(); ) {
            if (it.next() == null) {
                it.remove();
            }
        }
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
