package com.lxb.freeAndroid.project.mainUI.fragment.secondFragment;

import android.os.Bundle;
import android.view.View;

import com.lxb.freeAndroid.R;
import com.lxb.freeAndroid.frame.base.BaseFragment;
import com.lxb.freeAndroid.project.utils.ToastUtils.ToastUtil;

/**
 * 业务名: 会员Fragment
 * 功能说明:
 * 编写日期: xxxx-09-03
 * 作者:  李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */

public class SecondFragment extends BaseFragment<SecondPresenter> implements SecondContract.CaeView {


    public static SecondFragment newInstance(Bundle bundle) {
        SecondFragment fragment = new SecondFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected SecondPresenter initPresenter() {
        return new SecondPresenter(this);
    }

    @Override
    protected void initViewData(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_member;
    }


    @Override
    public void showMsg(String msg) {
        ToastUtil.toastShow(context, msg);
    }
}
