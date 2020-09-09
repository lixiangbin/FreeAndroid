package com.lxb.freeAndroid.project.mainUI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.lxb.freeAndroid.R;
import com.lxb.freeAndroid.frame.base.BasesActivity;
import com.lxb.freeAndroid.frame.base.OnFragmentInteractionListener;
import com.lxb.freeAndroid.project.mainUI.fragment.firstFragment.FirstFragment;
import com.lxb.freeAndroid.project.mainUI.fragment.fourthFragment.FourthFragment;
import com.lxb.freeAndroid.project.mainUI.fragment.secondFragment.SecondFragment;
import com.lxb.freeAndroid.project.mainUI.fragment.thirdFragment.ThirdFragment;
import com.lxb.freeAndroid.project.mainUI.presenter.MainPresenter;
import com.lxb.freeAndroid.project.utils.GlideUtils.GlideApp;
import com.lxb.freeAndroid.project.utils.ToastUtils.ToastUtil;

import butterknife.BindView;

/**
 * 业务名: 主页界面
 * 功能说明:
 * 编写日期: xxxx/3/26 0026
 * 作者:  李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */

public class MainActivity extends BasesActivity<MainPresenter> implements MainContract.MainView, RadioGroup.OnCheckedChangeListener, OnFragmentInteractionListener {

    @BindView(R.id.rb_cae)
    RadioButton rbCae;
    @BindView(R.id.fl_main)
    FrameLayout flMain;
    @BindView(R.id.rb_member)
    RadioButton rbMember;
    @BindView(R.id.rb_connection)
    RadioButton rbConnection;
    @BindView(R.id.rb_mine)
    RadioButton rbMine;
    @BindView(R.id.rg_bottomNavigation)
    RadioGroup rgBottomNavigation;

    //东盟
    private FirstFragment firstFragment;
    private final String TAG_FirstFragment = "TAG_FirstFragment";
    //会员
    private SecondFragment secondFragment;
    private final String TAG_SecondFragment = "TAG_SecondFragment";
    //人脉
    private ThirdFragment thirdFragment;
    private final String TAG_ThirdFragment = "TAG_ThirdFragment";
    //我的
    private FourthFragment fourthFragment;
    private final String TAG_FourthFragment = "TAG_FourthFragment";


    //获取FragmentManager
    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void initViewData(Bundle savedInstanceState) {
        rgBottomNavigation.setOnCheckedChangeListener(this);
        //防止app重启或所依附的Activity被GC后Fragment穿透叠加
        if (savedInstanceState != null) {
            firstFragment = (FirstFragment) fragmentManager.findFragmentByTag(TAG_FirstFragment);
            secondFragment = (SecondFragment) fragmentManager.findFragmentByTag(TAG_SecondFragment);
            thirdFragment = (ThirdFragment) fragmentManager.findFragmentByTag(TAG_ThirdFragment);
            fourthFragment = (FourthFragment) fragmentManager.findFragmentByTag(TAG_FourthFragment);
        }
        showCaeFragment(null);
        presenter.login("123456");
    }


    @Override
    public void setLoginResult(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFragmentInteraction(Bundle bundle) {
        String msg = bundle.getString("T");
        showMsg(msg);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        //...
        super.onNewIntent(intent);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_cae://东盟
                showCaeFragment(null);
                break;
            case R.id.rb_member://会员
                showMemberFragment(null);
                break;
            case R.id.rb_connection://人脉
                showConnectionsFragment(null);
                break;
            case R.id.rb_mine://我的
                showMineFragment(null);
                break;
        }
    }


    /**
     * 作者：李相斌
     * 创建时期：2019-09-02
     * 方法说明：显示第一个fragment
     */
    public void showCaeFragment(Bundle bundleParams) {
        // 先隐藏掉所有Fragment
        hideFragments();
        if (firstFragment == null) {
            firstFragment = FirstFragment.newInstance(bundleParams);
        }
        if (!firstFragment.isAdded() && !firstFragment.findTag()) {
            fragmentManager.beginTransaction().add(R.id.fl_main, firstFragment, TAG_FirstFragment).commitAllowingStateLoss();
            firstFragment.setTag(true);
        }
        fragmentManager.beginTransaction().show(firstFragment).commitAllowingStateLoss();
        //导航按钮状态
        rbCae.setChecked(true);
    }

    /**
     * 作者：李相斌
     * 创建时期：2019-09-02
     * 方法说明：显示第二个fragment
     */
    public void showMemberFragment(Bundle bundleParams) {
        // 先隐藏掉所有Fragment
        hideFragments();
        if (secondFragment == null) {
            secondFragment = SecondFragment.newInstance(bundleParams);
        }
        if (!secondFragment.isAdded() && !secondFragment.findTag()) {
            fragmentManager.beginTransaction().add(R.id.fl_main, secondFragment, TAG_SecondFragment).commitAllowingStateLoss();
            secondFragment.setTag(true);
        }
        fragmentManager.beginTransaction().show(secondFragment).commitAllowingStateLoss();
        //导航按钮状态
        rbMember.setChecked(true);
    }

    /**
     * 作者：李相斌
     * 创建时期：2019-09-02
     * 方法说明：显示第三个fragment
     */
    public void showConnectionsFragment(Bundle bundleParams) {
        // 先隐藏掉所有Fragment
        hideFragments();
        if (thirdFragment == null) {
            thirdFragment = ThirdFragment.newInstance(bundleParams);
        }
        if (!thirdFragment.isAdded() && !thirdFragment.findTag()) {
            fragmentManager.beginTransaction().add(R.id.fl_main, thirdFragment, TAG_ThirdFragment).commitAllowingStateLoss();
            thirdFragment.setTag(true);
        }
        fragmentManager.beginTransaction().show(thirdFragment).commitAllowingStateLoss();
        //导航按钮状态
        rbConnection.setChecked(true);
    }

    /**
     * 作者：李相斌
     * 创建时期：2019-09-02
     * 方法说明：显示第四个fragment
     */
    public void showMineFragment(Bundle bundleParams) {
        // 先隐藏掉所有Fragment
        hideFragments();
        if (fourthFragment == null) {
            fourthFragment = FourthFragment.newInstance(bundleParams);
        }
        if (!fourthFragment.isAdded() && !fourthFragment.findTag()) {
            fragmentManager.beginTransaction().add(R.id.fl_main, fourthFragment, TAG_FourthFragment).commitAllowingStateLoss();
            fourthFragment.setTag(true);
        }
        fragmentManager.beginTransaction().show(fourthFragment).commitAllowingStateLoss();
        //导航按钮状态
        rbMine.setChecked(true);
    }


    /**
     * 作者：李相斌
     * 创建时期：2019-09-02
     * 方法说明：隐藏所有fragment
     */
    public void hideFragments() {
        if (firstFragment != null) {
            fragmentManager.beginTransaction().hide(firstFragment).commitAllowingStateLoss();
        }
        if (secondFragment != null) {
            fragmentManager.beginTransaction().hide(secondFragment).commitAllowingStateLoss();
        }
        if (thirdFragment != null) {
            fragmentManager.beginTransaction().hide(thirdFragment).commitAllowingStateLoss();
        }
        if (fourthFragment != null) {
            fragmentManager.beginTransaction().hide(fourthFragment).commitAllowingStateLoss();
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showMsg(String msg) {
        ToastUtil.toastShow(this, msg);
    }

    //退出
    private static boolean isExit = false;
    private static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isExit) {
                isExit = true;
                ToastUtil.toastShow(getApplicationContext(), "再按一次退出");
                handler.sendEmptyMessageDelayed(0, 2000);
                return true;
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
