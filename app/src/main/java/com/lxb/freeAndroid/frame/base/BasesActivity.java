package com.lxb.freeAndroid.frame.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.lxb.freeAndroid.R;
import com.lxb.freeAndroid.frame.mvp.BasePresenter;
import com.lxb.freeAndroid.project.views.CommTitleBar;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 业务名: BasesActivity
 * 功能说明: Activity基类
 * 编写日期: xxxx-08-23
 * 作者:  李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */

public abstract class BasesActivity<P extends BasePresenter> extends AppCompatActivity {

    //Presenter实例
    protected P presenter;
    //ButterKnife实例
    private Unbinder unbinder;
    //公用标题栏
    protected CommTitleBar titleBar;
    //统一获取上个界面传来的Bundle实例
    protected Bundle bundleData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentViewBefore();
        setContentView(getLayoutId());
        //注册EventBus
        EventBus.getDefault().register(this);
        //绑定ButterKnife
        unbinder = ButterKnife.bind(this);
        //实例化Presenter
        presenter = initPresenter();
        //绑定presenter
        if (presenter != null) {
            presenter.bind();
        }
        //获取从上个界面传来的数据
        bundleData = getIntent().getExtras();
        //初始化标题栏
        titleBar = findViewById(R.id.titleBar);
        if (titleBar != null) {
            titleBar.setLeftIconClickListener(v -> finish());
            titleBar.setLeftTextClickListener(v -> finish());
        }
        //界面数据初始化
        initViewData(savedInstanceState);
    }


    /**
     * 作者：李相斌
     * 创建时期：xxxx-09-23
     * 方法说明：在setContentView前执行
     */
    protected void setContentViewBefore() {
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
     * 方法说明：初始化Presenter
     */
    protected abstract P initPresenter();

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

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除EventBus
        EventBus.getDefault().unregister(this);
        //解除presenter绑定
        if (presenter != null) {
            presenter.onDestroy();
            presenter = null;
        }
        //解除ButterKnife注入绑定
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
        System.gc();
    }


}
