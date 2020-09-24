package com.lxb.freeAndroid.project.mainDemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;

import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;
import com.lxb.freeAndroid.R;
import com.lxb.freeAndroid.project.utils.StatusBarUtil;

import butterknife.BindView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.SplashTheme_After);
        setContentView(R.layout.activity_splash);
        Activity act = this;
        ImmersionBar.with(this)
                .transparentStatusBar()
                .transparentNavigationBar()
                .hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR)
                .init();

        new CountDownTimer(3 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(act, MainActivity.class));
                finish();
            }
        }.start();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
