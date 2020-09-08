package com.lxb.freeAndroid.project.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import androidx.core.os.ConfigurationCompat;
import androidx.core.os.LocaleListCompat;

import com.lxb.freeAndroid.frame.base.Constants;

import java.util.Locale;

/**
 * 业务名: APP内部多语言切换工具类
 * 功能说明:
 * 编写日期: xxxx-09-23
 * 作者:  李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public class LanguageUtils {


    /**
     * 作者：李相斌
     * 创建时期：xxxx-09-23
     * 方法说明：设置语言为：自动/跟随系统
     */
    public static void setAutoLanguage(Context context) {
        //获取手机系统语言
        Locale locale = getSystemLanguage().get(0);
        String language = locale.getLanguage();
        String country = locale.getCountry();
        //切换成手机系统语言  例：手机系统是中文则换成中文
        LanguageUtils.changeLanguage(context, language, country);
        //清空SP数据 ，用于当系统切换语言时 应用可以同步保持切换 例：系统转换成英文 则应用语言也会变成英文
        LanguageUtils.changeLanguage(context, null, null);

        /*
         * //关闭应用所有Activity
         * ...
         * //启动 MainActivity
         * ...
         * finish();
         * */
    }


    /**
     * 作者：李相斌
     * 创建时期：xxxx-09-23
     * 方法说明：修改应用内语言设置
     */
    public static void changeLanguage(Context context, String language, String area) {
        if (TextUtils.isEmpty(language) && TextUtils.isEmpty(area)) {
            //如果语言和地区都是空，则跟随系统语言
            SPUtils.putString(context, Constants.LANGUAGE, "");
            SPUtils.putString(context, Constants.COUNTRY, "");
        } else {
            //设置APP语言
            Locale newLocale = new Locale(language, area);
            changeAppLanguage(context, newLocale, true);
        }

    }

    /**
     * 作者：李相斌
     * 创建时期：xxxx-09-23
     * 方法说明：更改应用语言
     * locale  语言地区
     * persistence 是否持久化
     */
    public static void changeAppLanguage(Context context, Locale locale, boolean persistence) {
        //获取语言信息
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        Configuration configuration = context.getResources().getConfiguration();
        setLanguage(context, locale, configuration);
        context.getResources().updateConfiguration(configuration, metrics);
        if (persistence) {
            //保存多语言信息到sp中
            SPUtils.putString(context, Constants.LANGUAGE, locale.getLanguage());
            SPUtils.putString(context, Constants.COUNTRY, locale.getCountry());
        }
    }


    /**
     * 作者：李相斌
     * 创建时期：xxxx-09-23
     * 方法说明：设置语言
     */
    private static void setLanguage(Context context, Locale locale, Configuration configuration) {
        //根据不同版本设置语言
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            configuration.setLocale(locale);
            configuration.setLocales(new LocaleList(locale));
            context.createConfigurationContext(configuration);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
        } else {
            configuration.locale = locale;
        }
    }

    /**
     * 作者：李相斌
     * 创建时期：xxxx-09-23
     * 方法说明： 跟随系统语言
     */
    public static Context attachBaseContext(Context context) {
        String spLanguage = SPUtils.getString(context, Constants.LANGUAGE, "");
        String spCountry = SPUtils.getString(context, Constants.COUNTRY, "");
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        Locale appLocale = getAppLocale(context);
        //如果本地有语言信息，以本地为主，如果本地没有使用默认Locale
        Locale locale;
        if (!TextUtils.isEmpty(spLanguage) && !TextUtils.isEmpty(spCountry)) {
            if (isSameLocal(appLocale, spLanguage, spCountry)) {
                locale = appLocale;
            } else {
                locale = new Locale(spLanguage, spCountry);
            }
        } else {
            locale = appLocale;
        }
        setLanguage(context, locale, configuration);
        resources.updateConfiguration(configuration, dm);
        return context;
    }

    /**
     * 作者：李相斌
     * 创建时期：xxxx-09-23
     * 方法说明：判断sp中的语言和设置的多语言信息是否相同
     */
    public static boolean isSameSpWithSetting(Context context) {
        Locale locale = getAppLocale(context);
        String language = locale.getLanguage();
        String country = locale.getCountry();
        String sp_language = SPUtils.getString(context, Constants.LANGUAGE, "");
        String sp_country = SPUtils.getString(context, Constants.COUNTRY, "");
        if (language.equals(sp_language) && country.equals(sp_country)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 作者：李相斌
     * 创建时期：xxxx-09-23
     * 方法说明：判断app与系统语言是否相同
     */
    public static boolean isSameLocal(Locale appLocale, String sp_language, String sp_country) {
        String appLanguage = appLocale.getLanguage();
        String appCountry = appLocale.getCountry();
        if (appLanguage.equals(sp_language) && appCountry.equals(sp_country)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 作者：李相斌
     * 创建时期：xxxx-09-23
     * 方法说明：获取应用地区Locale
     */
    public static Locale getAppLocale(Context context) {
        Locale local;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            local = context.getResources().getConfiguration().getLocales().get(0);
        } else {
            local = context.getResources().getConfiguration().locale;
        }
        return local;
    }

    /**
     * 作者：李相斌
     * 创建时期：xxxx-09-23
     * 方法说明：获取系统语言
     */
    public static LocaleListCompat getSystemLanguage() {
        Configuration configuration = Resources.getSystem().getConfiguration();
        LocaleListCompat locales = ConfigurationCompat.getLocales(configuration);
        return locales;
    }


    /**
     * 作者：李相斌
     * 创建时期：xxxx-09-23
     * 方法说明：在Application中注册Activity生命周期监听回调
     * 因为有些版本可能多语言切换不回来
     */
    public static void bindOnActivityCreated(Activity activity) {
        String language = SPUtils.getString(activity, Constants.LANGUAGE, "");
        String country = SPUtils.getString(activity, Constants.COUNTRY, "");
        if (!TextUtils.isEmpty(language) && !TextUtils.isEmpty(country)) {
            //强制修改app语言
            if (!isSameSpWithSetting(activity)) {
                Locale locale = new Locale(language, country);
                changeAppLanguage(activity, locale, false);
            }
        }
    }
}
