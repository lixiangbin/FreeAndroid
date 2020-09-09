package com.lxb.freeAndroid.project.modulesUI.mineModule.login;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.lxb.freeAndroid.R;
import com.lxb.freeAndroid.frame.base.BasesActivity;
import com.lxb.freeAndroid.frame.base.Constants;
import com.lxb.freeAndroid.project.utils.SoftInputUtils;
import com.lxb.freeAndroid.project.utils.ToastUtils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 业务名: 登陆
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
    @BindView(R.id.tv_phone)
    TextView tv_phone;
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
        //手机号
        String phone = bundleData.getString(Constants.LOGIN_PHONE);
        //国籍
        String nationality = bundleData.getString(Constants.LOGIN_NATIONALITY);
        tv_phone.setText(nationality + " " + phone);

    }

    //获取验证码/倒计时
    @OnClick(R.id.tv_codeTime)
    public void tv_codeTime(TextView tvCodeTime) {
        //倒计时
        new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long sec = millisUntilFinished / 1000;
                tvCodeTime.setText(sec < 10 ? "0" + sec + "s后重新获取" : sec + "s后重新获取");
                tvCodeTime.setClickable(false);
                tvCodeTime.setTextColor(ContextCompat.getColor(getContext(), R.color.gray));
                tvCodeTime.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.shape_border_btn_gray_rectangle));

            }

            @Override
            public void onFinish() {
                tvCodeTime.setText("获取验证码");
                tvCodeTime.setClickable(true);
                tvCodeTime.setTextColor(ContextCompat.getColor(getContext(), R.color.color_2E79CC));
                tvCodeTime.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.shape_border_btn_blue_rectangle));
            }
        }.start();
        showMsg("发送成功");
    }

    //登录
    @OnClick(R.id.btn_login)
    public void btn_login() {

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
            SoftInputUtils.showSoftInput(etPassword);
        } else {
            rlCode.setVisibility(View.VISIBLE);
            llPwd.setVisibility(View.GONE);
            tvChangeWay.setText("用密码登录");
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
