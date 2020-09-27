package com.lxb.freeAndroid.frame.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.lxb.freeAndroid.R;
import com.lxb.freeAndroid.project.utils.ToastUtils.ToastUtil;
import com.lxb.freeAndroid.project.views.CommTitleBar;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 业务名:普通BaseActivity
 * 功能说明:无mvp相关的封装，可用于无网络请求的Activity
 * 编写日期: xxxx-09-04.
 * 作者: 李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public abstract class OrdinaryBaseActivity extends AppCompatActivity {

    //ButterKnife实例
    private Unbinder unbinder;
    //公用标题栏
    protected CommTitleBar titleBar;
    //Activity / context
    protected Activity act;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        //注册EventBus
        EventBus.getDefault().register(this);
        //绑定ButterKnife
        unbinder = ButterKnife.bind(this);
        //初始化act
        act = this;
        //初始化标题栏
        titleBar = findViewById(R.id.titleBar);
        if (titleBar != null) {
            titleBar.setLeftIconClickListener(v -> finish());
            titleBar.setLeftTextClickListener(v -> finish());
        }
        //界面初始化
        initViewData(savedInstanceState);
    }


    /**
     * 作者：李相斌
     * 创建时期：xxxx-08-23
     * 方法说明：获取界面布局id
     */
    protected abstract int getLayoutId();


    /**
     * 作者：李相斌
     * 创建时期：xxxx-08-23
     * 方法说明：初始化View
     */
    protected abstract void initViewData(Bundle savedInstanceState);


    /**
     * 作者：李相斌
     * 创建时期：xxxx-08-28
     * 方法说明：统一跳转
     */
    protected void startActivity(Class<? extends Activity> activityCls) {
        startActivity(activityCls, null);
    }

    protected void startActivity(Class<? extends Activity> activityCls, Bundle bundle) {
        Intent intent = new Intent(this, activityCls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 作者：李相斌
     * 创建时期：xxxx-09-04
     * 方法说明：Toast提示
     */
    protected void showMsg(String msg) {
        ToastUtil.toastShow(this, msg);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除EventBus
        EventBus.getDefault().unregister(this);
        //解除ButterKnife注入绑定
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
        System.gc();
    }
}
