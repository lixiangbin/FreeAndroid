package com.lxb.freeAndroid.project.mainUI.fragment.orderFragment;

import android.os.Bundle;
import android.view.View;

import com.lxb.freeAndroid.R;
import com.lxb.freeAndroid.frame.base.BaseFragment;
import com.lxb.freeAndroid.project.utils.ToastUtils.ToastUtil;

/**
 * 业务名: 人脉Fragment
 * 功能说明:
 * 编写日期: xxxx-09-03
 * 作者:  李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */

public class ConnectionsFragment extends BaseFragment<ConnectionsPresenter> implements ConnectionsContract.CaeView {


    public static ConnectionsFragment newInstance(Bundle bundle) {
        ConnectionsFragment fragment = new ConnectionsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected ConnectionsPresenter initPresenter() {
        return new ConnectionsPresenter(this);
    }

    @Override
    protected void initViewData(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_connections;
    }

    @Override
    public void showMsg(String msg) {
        ToastUtil.toastShow(context, msg);
    }
}
