package com.lxb.freeAndroid.frame.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.lxb.freeAndroid.frame.mvp.BasePresenter;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 业务名:BaseFragment
 * 功能说明:Fragment基类
 * 编写日期: xxxx-08-28.
 * 作者: 李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

    //Presenter
    protected P presenter;
    //View
    private View view;
    //ButterKnife注解
    private Unbinder unbinder;
    //Context
    protected Context context;
    //封装activity与fragment数据交互
    private OnFragmentInteractionListener onFragmentInteractionListener;

    private boolean tag;

    public void setTag(boolean tag) {
        this.tag = tag;
    }

    public boolean findTag() {
        return tag;
    }

    //来自宿主activity的Bundle
    protected Bundle actBundle;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            actBundle = getArguments();
        }
        //注册EventBus
        //EventBus.getDefault().register(this);
        //初始化 presenter
        presenter = initPresenter();
        //presenter绑定
        if (presenter != null) {
            presenter.bind();
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);
        //绑定 ButterKnife
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewData(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //解除EventBus
        //EventBus.getDefault().unregister(this);
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

    @Override
    public void onDetach() {
        super.onDetach();
        onFragmentInteractionListener = null;
    }

    /**
     * 作者：李相斌
     * 创建时期：xxxx-09-04
     * 方法说明：统一跳转
     */
    protected void startActivity(Class<? extends Activity> activityCls) {
        startActivity(activityCls, null);
    }

    protected void startActivity(Class<? extends Activity> activityCls, Bundle bundle) {
        Intent intent = new Intent(context, activityCls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 作者：李相斌
     * 创建时期：xxxx-08-28
     * 方法说明：初始化presenter
     */
    protected abstract P initPresenter();

    /**
     * 作者：李相斌
     * 创建时期：xxxx-08-28
     * 方法说明：初始化View
     */
    protected abstract void initViewData(View view, Bundle savedInstanceState);

    /**
     * 作者：李相斌
     * 创建时期：xxxx-08-28
     * 方法说明：获取界面布局id
     */
    protected abstract int getLayoutId();


    /**
     * 作者：李相斌
     * 创建时期：xxxx-08-28
     * 方法说明：简化findViewById
     */
    protected <T extends View> T findViewById(int resId) {
        return (T) view.findViewById(resId);
    }

    /**
     * 作者：李相斌
     * 创建时期：xxxx-09-03
     * 方法说明：封装向act传值并调用
     */
    protected void sendParamsToActivity(Bundle bundle) {
        if (context == null) {
            throw new NullPointerException(" 'context' can not be Null");
        }
        if (!(context instanceof OnFragmentInteractionListener)) {
            throw new RuntimeException(context.toString()
                    + "\n host Activity must implement OnFragmentInteractionListener (宿主Activity必须实现OnFragmentInteractionListener接口)");
        } else {
            //初始化OnFragmentInteractionListener
            onFragmentInteractionListener = (OnFragmentInteractionListener) context;
            //调用activity的onFragmentInteraction()方法并传参bundle
            onFragmentInteractionListener.onFragmentInteraction(bundle);
        }
    }

}