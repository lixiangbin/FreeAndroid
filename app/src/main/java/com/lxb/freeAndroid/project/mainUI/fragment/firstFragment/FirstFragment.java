package com.lxb.freeAndroid.project.mainUI.fragment.firstFragment;

import android.os.Bundle;
import android.view.View;

import com.lxb.freeAndroid.R;
import com.lxb.freeAndroid.frame.base.BaseFragment;
import com.lxb.freeAndroid.project.modulesUI.mineModule.SettingsActivity;
import com.lxb.freeAndroid.project.utils.ToastUtils.ToastUtil;

import butterknife.OnClick;

/**
 * 业务名: 首页 Fragment
 * 功能说明:
 * 编写日期: xxxx-09-03
 * 作者:  李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */

public class FirstFragment extends BaseFragment<FirstPresenter> implements FirstContract.CaeView {


    public static FirstFragment newInstance(Bundle bundle) {
        FirstFragment fragment = new FirstFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected FirstPresenter initPresenter() {
        return new FirstPresenter(this);
    }

    @Override
    protected void initViewData(View view, Bundle savedInstanceState) {

        //TODO... UI  测试


    }

    @OnClick(R.id.test6)
    public void test6(){
        startActivity(SettingsActivity.class);
    }

    @OnClick(R.id.btn_login)
    public void btn_login(){
        presenter.requestTest();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cae;
    }


    @Override
    public void showMsg(String msg) {
        ToastUtil.toastShow(context, msg);
    }
}
