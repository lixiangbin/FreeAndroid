package com.lxb.freeAndroid.project.modulesDemo.mineModule;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.lxb.freeAndroid.R;
import com.lxb.freeAndroid.frame.base.AppApplication;
import com.lxb.freeAndroid.frame.base.AppConfig;
import com.lxb.freeAndroid.frame.base.Constants;
import com.lxb.freeAndroid.frame.base.OrdinaryBaseActivity;
import com.lxb.freeAndroid.project.mainDemo.MainActivity;
import com.lxb.freeAndroid.project.utils.FileIOUtils;
import com.lxb.freeAndroid.project.utils.GlideUtils.GlideCatchUtil;
import com.lxb.freeAndroid.project.utils.LanguageUtils;
import com.lxb.freeAndroid.project.utils.SPUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 业务名: 设置
 * 功能说明:
 * 编写日期: xxxx-09-11
 * 作者:  李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */

public class SettingsActivity extends OrdinaryBaseActivity {

    @BindView(R.id.tv_catchSize)
    TextView tvCatchSize;
    @BindView(R.id.iv_test)
    ImageView ivTest;

    @BindView(R.id.tv_autoLan)
    TextView tvAutoLan;
    @BindView(R.id.tv_chinese)
    TextView tvChinese;
    @BindView(R.id.tv_english)
    TextView tvEnglish;

    //系统语言，默认中文
    private boolean isEnglish;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_settings;
    }

    @Override
    protected void initViewData(Bundle savedInstanceState) {
        tvCatchSize.setText(GlideCatchUtil.getInstance().getCacheSize());

        setAppDefaultLanguage();

    }

    Handler handler;

    @OnClick(R.id.ll_clearCatch)
    public void rl_clearCatch() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                tvCatchSize.setText(GlideCatchUtil.getInstance().getCacheSize());
                showMsg("已清除");
            }
        };

        try {
            clearCache();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 作者：李相斌
     * 创建时期：xxxx-09-22
     * 方法说明：设置默认语言
     */
    private void setAppDefaultLanguage() {
        //获取本地语言
        String spLanguage = SPUtils.getString(this, Constants.LANGUAGE, "");
        //设置语言状态  默认跟随系统
        if (TextUtils.isEmpty(spLanguage)) {
            switchLanguage_UI(tvAutoLan);
        }
        if ("zh".equals(spLanguage)) {
            switchLanguage_UI(tvChinese);
        } else if ("en".equals(spLanguage)) {
            switchLanguage_UI(tvEnglish);
        } else {
            switchLanguage_UI(tvAutoLan);
        }
    }

    //自动
    @OnClick(R.id.tv_autoLan)
    public void systemLanguage_auto() {
        switchLanguage_UI(tvAutoLan);
        LanguageUtils.setAutoLanguage(this);
        AppApplication.getInstance().finishAllActivity(true);
        startActivity(MainActivity.class);
        overridePendingTransition(R.anim.right_push_in, R.anim.left_push_out);
    }

    //中文
    @OnClick(R.id.tv_chinese)
    public void systemLanguage_zh() {
        switchLanguage_UI(tvChinese);
        LanguageUtils.changeLanguage(this, "zh", "ZH");
        AppApplication.getInstance().finishAllActivity(true);
        startActivity(MainActivity.class);
        overridePendingTransition(R.anim.right_push_in, R.anim.left_push_out);
    }

    //英文
    @OnClick(R.id.tv_english)
    public void systemLanguage_en() {
        switchLanguage_UI(tvEnglish);
        LanguageUtils.changeLanguage(this, "en", "US");
        AppApplication.getInstance().finishAllActivity(true);
        startActivity(MainActivity.class);
        overridePendingTransition(R.anim.right_push_in, R.anim.left_push_out);
    }


    /**
     * 作者：李相斌
     * 创建时期：xxxx-09-12
     * 方法说明：切换语言 ui
     */
    private void switchLanguage_UI(TextView tv) {
        //允许所有
        tvAutoLan.setTextColor(ContextCompat.getColor(this, R.color.color_1C60AC));
        tvAutoLan.setClickable(true);
        tvChinese.setTextColor(ContextCompat.getColor(this, R.color.color_1C60AC));
        tvChinese.setClickable(true);
        tvEnglish.setTextColor(ContextCompat.getColor(this, R.color.color_1C60AC));
        tvEnglish.setClickable(true);
        //置灰当前
        tv.setTextColor(ContextCompat.getColor(this, R.color.black));
        tv.setClickable(false);
    }


    /**
     * 作者：李相斌
     * 创建时期：xxxx-09-12
     * 方法说明：清除缓存
     */
    public void clearCache() {
        //清除其它缓存
        FileIOUtils.deleteFilesAll(getExternalCacheDir());//删除data缓存
        FileIOUtils.deleteFilesAll(new File(this.getFilesDir().getParent() + "/" + AppConfig.CASH_DOWNLOAD + "/"));//删除data缓存
        FileIOUtils.deleteFilesAll(new File(this.getCacheDir() + "/" + AppConfig.CASH_DOWNLOAD + "/img/"));//删除data缓存
        FileIOUtils.deleteFilesAll(new File(Environment.getExternalStorageDirectory() + "/" + AppConfig.CASH_DOWNLOAD + "/"));//删除data缓存
        //清除Glide缓存
        Glide.get(AppApplication.getInstance().getApplicationContext()).clearMemory();
        new Thread(() -> {
            GlideCatchUtil.getInstance().clearCacheDiskSelf();
            GlideCatchUtil.getInstance().cleanCatchDisk();
            GlideCatchUtil.getInstance().clearCacheMemory();
            handler.sendEmptyMessage(0);
        }).start();
    }

}
