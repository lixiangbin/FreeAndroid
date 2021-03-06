package com.lxb.freeAndroid.project.modulesDemo.mineModule.login;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lxb.freeAndroid.R;
import com.lxb.freeAndroid.frame.base.BasesActivity;
import com.lxb.freeAndroid.project.utils.FeatureUtils;

import com.lxb.freeAndroid.project.utils.SoftInputUtils;
import com.lxb.freeAndroid.project.utils.ToastUtils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 业务名: mvp Demo演示
 * 功能说明:
 * 编写日期: xxxx-09-09
 * 作者:  李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */

public class LoginActivity extends BasesActivity<LoginPresenter> implements LoginContract.LoginView {


    //手机号
    @BindView(R.id.et_phone)
    EditText et_phone;
    //密码
    @BindView(R.id.et_password)
    EditText etPassword;
    //密码layout
    @BindView(R.id.ll_pwd)
    LinearLayout llPwd;
    //验证码
    @BindView(R.id.et_code)
    EditText etCode;
    //获取验证码/倒计时
    @BindView(R.id.tv_codeTime)
    TextView tvCodeTime;
    //切换登录方式
    @BindView(R.id.tv_changeWay)
    TextView tvChangeWay;
    //验证码layout
    @BindView(R.id.rl_code)
    RelativeLayout rlCode;


    //登录方式
    private boolean isPwdLogin = true;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter(this);
    }


    @Override
    protected void initViewData(Bundle savedInstanceState) {
        //初始化登录方式
        changeLoginWay();



    }

    //获取验证码/倒计时
    @OnClick(R.id.tv_codeTime)
    public void tv_codeTime() {
        if (TextUtils.isEmpty(et_phone.getText().toString())) {
            showMsg("请输入手机号");
            return;
        }
        FeatureUtils.countDownTime(tvCodeTime, this);
        showMsg("发送成功");
    }

    //登录
    @OnClick(R.id.btn_login)
    public void btn_login() {
        showMsg("登录成功");
        finish();
    }

    //切换登录方式
    @OnClick(R.id.tv_changeWay)
    public void tv_changeWay() {
        isPwdLogin = !isPwdLogin;
        changeLoginWay();
    }

    /**
     * 作者：李相斌
     * 创建时期：xxxx-09-09
     * 方法说明：登录方式
     */
    public void changeLoginWay() {
        if (isPwdLogin) {
            llPwd.setVisibility(View.VISIBLE);
            rlCode.setVisibility(View.GONE);
            tvChangeWay.setText("用验证码登录");
            //获取焦点自动弹出软键盘
            SoftInputUtils.showSoftInput(et_phone);
        } else {
            rlCode.setVisibility(View.VISIBLE);
            llPwd.setVisibility(View.GONE);
            tvChangeWay.setText("用密码登录");
            //获取焦点自动弹出软键盘
            SoftInputUtils.showSoftInput(etCode);
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

}
