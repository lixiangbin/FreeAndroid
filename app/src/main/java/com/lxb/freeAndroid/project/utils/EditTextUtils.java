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
    private void filterEmoji(final EditText et) {
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
}
