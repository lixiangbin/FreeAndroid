package com.lxb.freeAndroid.project.utils;

import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 业务名:软键盘工具类
 * 功能说明:
 * 编写日期: xxxx-09-10.
 * 作者: 李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public class SoftInputUtils {


    /**
     * 作者：李相斌
     * 创建时期：xxxx-09-10
     * 方法说明：弹出软键盘
     */
    public static void showSoftInput(EditText editText) {
        editText.setFocusable(true);
        editText.requestFocus();
        editText.setSelection(editText.getText().length());
        //((InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
        new Timer().schedule(new TimerTask() {
            public void run() {
                ((InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(editText, 0);
            }
        }, 500);
    }

    /**
     * 作者：李相斌
     * 创建时期：xxxx-09-10
     * 方法说明：隐藏软键盘
     */
    public static void hindSoftInput(Activity activity){
        InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    /**
     * 作者：李相斌
     * 创建时期：xxxx-09-10
     * 方法说明：屏蔽软键盘输入回车换行符
     */

    public static void shieldEnter(EditText editText) {
        editText.setOnEditorActionListener((textView, actionId, event) -> event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER));
    }
}
