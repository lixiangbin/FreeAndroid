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

import com.lxb.freeAndroid.R;
import com.lxb.freeAndroid.frame.base.BasesActivity;
import com.lxb.freeAndroid.frame.base.OnFragmentInteractionListener;
import com.lxb.freeAndroid.project.mainUI.fragment.homeFragment.CaeFragment;
import com.lxb.freeAndroid.project.mainUI.fragment.orderFragment.ConnectionsFragment;
import com.lxb.freeAndroid.project.mainUI.fragment.memberFragment.MemberFragment;
import com.lxb.freeAndroid.project.mainUI.fragment.settingFragment.MineFragment;
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
    private CaeFragment caeFragment;
    //会员
    private MemberFragment memberFragment;
    //人脉
    private ConnectionsFragment connectionsFragment;
    //我的
    private MineFragment mineFragment;


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
            caeFragment = (CaeFragment) fragmentManager.findFragmentByTag("caeFragment");
            memberFragment = (MemberFragment) fragmentManager.findFragmentByTag("memberFragment");
            connectionsFragment = (ConnectionsFragment) fragmentManager.findFragmentByTag("connectionsFragment");
            mineFragment = (MineFragment) fragmentManager.findFragmentByTag("mineFragment");
        }
        showCaeFragment(null);
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
     * 创建时期：xxxx-09-02
     * 方法说明：显示东盟fragment
     */
    public void showCaeFragment(Bundle bundleParams) {
        // 先隐藏掉所有Fragment
        hideFragments();
        if (caeFragment == null) {
            caeFragment = CaeFragment.newInstance(bundleParams);
        }
        if (!caeFragment.isAdded() && !caeFragment.findTag()) {
            fragmentManager.beginTransaction().add(R.id.fl_main, caeFragment, "caeFragment").commitAllowingStateLoss();
            caeFragment.setTag(true);
        }
        fragmentManager.beginTransaction().show(caeFragment).commitAllowingStateLoss();
        //导航按钮状态
        rbCae.setChecked(true);
    }

    /**
     * 作者：李相斌
     * 创建时期：xxxx-09-02
     * 方法说明：显示人脉fragment
     */
    public void showConnectionsFragment(Bundle bundleParams) {
        // 先隐藏掉所有Fragment
        hideFragments();
        if (connectionsFragment == null) {
            connectionsFragment = ConnectionsFragment.newInstance(bundleParams);
        }
        if (!connectionsFragment.isAdded() && !connectionsFragment.findTag()) {
            fragmentManager.beginTransaction().add(R.id.fl_main, connectionsFragment, "connectionsFragment").commitAllowingStateLoss();
            connectionsFragment.setTag(true);
        }
        fragmentManager.beginTransaction().show(connectionsFragment).commitAllowingStateLoss();
        //导航按钮状态
        rbConnection.setChecked(true);
    }

    /**
     * 作者：李相斌
     * 创建时期：xxxx-09-02
     * 方法说明：显示会员fragment
     */
    public void showMemberFragment(Bundle bundleParams) {
        // 先隐藏掉所有Fragment
        hideFragments();
        if (memberFragment == null) {
            memberFragment = MemberFragment.newInstance(bundleParams);
        }
        if (!memberFragment.isAdded() && !memberFragment.findTag()) {
            fragmentManager.beginTransaction().add(R.id.fl_main, memberFragment, "memberFragment").commitAllowingStateLoss();
            memberFragment.setTag(true);
        }
        fragmentManager.beginTransaction().show(memberFragment).commitAllowingStateLoss();
        //导航按钮状态
        rbMember.setChecked(true);
    }

    /**
     * 作者：李相斌
     * 创建时期：xxxx-09-02
     * 方法说明：显示我的fragment
     */
    public void showMineFragment(Bundle bundleParams) {
        // 先隐藏掉所有Fragment
        hideFragments();
        if (mineFragment == null) {
            mineFragment = MineFragment.newInstance(bundleParams);
        }
        if (!mineFragment.isAdded() && !mineFragment.findTag()) {
            fragmentManager.beginTransaction().add(R.id.fl_main, mineFragment, "mineFragment").commitAllowingStateLoss();
            mineFragment.setTag(true);
        }
        fragmentManager.beginTransaction().show(mineFragment).commitAllowingStateLoss();
        //导航按钮状态
        rbMine.setChecked(true);
    }


    /**
     * 作者：李相斌
     * 创建时期：xxxx-09-02
     * 方法说明：隐藏所有fragment
     */
    public void hideFragments() {
        if (caeFragment != null) {
            fragmentManager.beginTransaction().hide(caeFragment).commitAllowingStateLoss();
        }
        if (memberFragment != null) {
            fragmentManager.beginTransaction().hide(memberFragment).commitAllowingStateLoss();
        }
        if (connectionsFragment != null) {
            fragmentManager.beginTransaction().hide(connectionsFragment).commitAllowingStateLoss();
        }
        if (mineFragment != null) {
            fragmentManager.beginTransaction().hide(mineFragment).commitAllowingStateLoss();
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
