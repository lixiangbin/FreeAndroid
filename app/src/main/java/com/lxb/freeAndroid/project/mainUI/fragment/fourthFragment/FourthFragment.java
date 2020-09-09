package com.lxb.freeAndroid.project.mainUI.fragment.fourthFragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lxb.freeAndroid.R;
import com.lxb.freeAndroid.frame.base.BaseFragment;
import com.lxb.freeAndroid.frame.base.Constants;
import com.lxb.freeAndroid.project.modulesUI.mineModule.login.LoginActivity;
import com.lxb.freeAndroid.project.utils.ToastUtils.ToastUtil;

/**
 * 业务名: 我的Fragment
 * 功能说明:
 * 编写日期: xxxx-09-03
 * 作者:  李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */

public class FourthFragment extends BaseFragment<FourthPresenter> implements FourthContract.CaeView {


    private TextView tv_nationality;//国籍
    private EditText et_phone;//手机号

    public static FourthFragment newInstance(Bundle bundle) {
        FourthFragment fragment = new FourthFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected FourthPresenter initPresenter() {
        return new FourthPresenter(this);
    }

    @Override
    protected void initViewData(View view, Bundle savedInstanceState) {
        tv_nationality = findViewById(R.id.tv_nationality);
        et_phone = findViewById(R.id.et_phone);

        et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s == null || s.length() == 0) return;
                String str = s.toString().replaceAll(" ", "");
                //处理手机号，前三位分割，后面每四位分割
                if (str.length() > 3) {
                    String strStart = str.substring(0, 3);
                    String strEnd = str.substring(3);
                    StringBuffer sbOldPhone = new StringBuffer(strEnd);
                    StringBuffer sbNewPhone = new StringBuffer();
                    int len = sbOldPhone.length();
                    for (int i = 0; i < len; i++) {
                        if (sbOldPhone.length() > 4) {
                            sbNewPhone.append(" ");
                            sbNewPhone.append(sbOldPhone.substring(0, 4));
                            sbOldPhone = sbOldPhone.delete(0, 4);
                        } else {
                            if (sbOldPhone.length() > 0) {
                                sbNewPhone.append(" ");
                                sbNewPhone.append(sbOldPhone.substring(0, sbOldPhone.length()));
                                sbOldPhone = sbOldPhone.delete(0, sbOldPhone.length());
                            }
                        }
                    }

                    sbNewPhone.insert(0, strStart);
                    et_phone.removeTextChangedListener(this);
                    et_phone.setText(sbNewPhone.toString());
                    et_phone.setSelection(sbNewPhone.length());
                    et_phone.addTextChangedListener(this);
                }


            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        findViewById(R.id.btn_next).setOnClickListener(v -> {
            if (!checkPhone()) return;
            //国籍编码截取
            String nationality = tv_nationality.getText().toString();
            String nationalitySub = nationality.substring(nationality.lastIndexOf("+"));
            Bundle bundle = new Bundle();
            bundle.putString(Constants.LOGIN_PHONE, et_phone.getText().toString());
            bundle.putString(Constants.LOGIN_NATIONALITY, nationalitySub);
            startActivity(LoginActivity.class, bundle);
        });

    }

    /**
     * 作者：李相斌
     * 创建时期：xxxx-09-09
     * 方法说明：检查手机号
     */
    private boolean checkPhone() {
        if (TextUtils.isEmpty(et_phone.getText().toString())) {
            showMsg("请输入手机号");
            return false;
        }
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }


    @Override
    public void showMsg(String msg) {
        ToastUtil.toastShow(context, msg);
    }
}
