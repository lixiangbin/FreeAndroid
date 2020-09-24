package com.lxb.freeAndroid.project.utils;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.lxb.freeAndroid.R;

/**
 * 业务名:功能工具类
 * 功能说明:
 * 编写日期: 2020-09-11.
 * 作者: 李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public class FeatureUtils {

    /**
     * 作者：李相斌
     * 创建时期：2020-09-11
     * 方法说明：验证码倒计时
     */
    public static void countDownTime(TextView tvCodeTime, Context ctx){
        //倒计时
        new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long sec = millisUntilFinished / 1000;
                tvCodeTime.setText(sec < 10 ? "0" + sec + "s后重新获取" : sec + "s后重新获取");
                tvCodeTime.setClickable(false);
                tvCodeTime.setTextColor(ContextCompat.getColor(ctx, R.color.gray));
                tvCodeTime.setBackground(ContextCompat.getDrawable(ctx, R.drawable.shape_border_btn_gray_rectangle));

            }

            @Override
            public void onFinish() {
                tvCodeTime.setText("获取验证码");
                tvCodeTime.setClickable(true);
                tvCodeTime.setTextColor(ContextCompat.getColor(ctx, R.color.color_2E79CC));
                tvCodeTime.setBackground(ContextCompat.getDrawable(ctx, R.drawable.shape_border_btn_blue_rectangle));
            }
        }.start();
    }

}
