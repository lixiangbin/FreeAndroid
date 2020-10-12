package com.lxb.freeAndroid.project.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * 业务名:EditText工具类
 * 功能说明:
 * 编写日期: xxxx-09-09.
 * 作者: 李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public class EditTextUtils {

    /**
     * 作者：李相斌
     * 创建时期：xxxx-09-09
     * 方法说明：表情符过滤
     */
    public static void filterEmoji(final EditText et) {
        TextWatcher emojiTextWatcher = new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                String editable = et.getText().toString();
                String str = StringFilterUtils.specileCharFilter(editable); //过滤特殊字符
                if (!editable.equals(str)) {
                    et.setText(str);
                    et.setSelection(str.length());
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        et.addTextChangedListener(emojiTextWatcher);
    }


    /**
     * 作者：李相斌
     * 创建时期：2020-10-12
     * 方法说明：处理手机号，每四位分割
     */
    public static void phoneEditTextSplit(EditText et_phone) {
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
    }

}
