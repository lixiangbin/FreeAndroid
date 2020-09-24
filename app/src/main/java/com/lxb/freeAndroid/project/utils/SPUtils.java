package com.lxb.freeAndroid.project.utils;

import android.content.Context;
import android.content.SharedPreferences;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 业务名:保存/读取对象
 * 功能说明:从SP中读取、保存对象，被保存的对象须实现序列化接口，包括内部类
 * 编写日期: 2017/11/9 0009.
 * 作者: 李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public class SPUtils {

    private static final String FILE_NAME = "freeAndroid_SP_data";

    /**
     * 作者：李相斌
     * 创建时期：xxxx-09-16
     * 方法说明：普通 存值 / 取值
     */
    public static void putString(Context context, String key, String value) {
        context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit().putString(key, value).apply();
    }

    public static String getString(Context context, String key, String defaultValue) {
        return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getString(key, defaultValue);
    }

    public static void putInt(Context context, String key, int value) {
        context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit().putInt(key, value).apply();
    }

    public static int getInt(Context context, String key, int defaultValue) {
        return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getInt(key, defaultValue);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getBoolean(key, defaultValue);
    }

    public static void putFloat(Context context, String key, float value) {
        context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit().putFloat(key, value).apply();
    }

    public static float getFloat(Context context, String key, float defaultValue) {
        return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getFloat(key, defaultValue);
    }

    public static void putLong(Context context, String key, long value) {
        context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit().putLong(key, value).apply();
    }

    public static long getLong(Context context, String key, long defaultValue) {
        return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getLong(key, defaultValue);
    }


    /**
     * 作者：李相斌
     * 创建时期：xxxx-09-16
     * 方法说明：移除某个key值已经对应的值
     */
    public static void remove(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }


    /**
     * 作者：李相斌
     * 创建时期：xxxx-09-16
     * 方法说明：查询某个key是否已经存在
     */
    public static boolean contains(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 作者：李相斌
     * 创建时期：xxxx-09-16
     * 方法说明：返回所有的键值对
     */
    public static Map<String, ?> getAll(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.getAll();
    }


    /**
     * 作者：李相斌
     * 创建时期：xxxx-09-16
     * 方法说明：清除所有数据
     */
    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }


    /**
     * 作者：李相斌
     * 创建时期：2017/11/9 0009
     * 方法说明：保存对象到 SP 文件
     */
    public static boolean setObjectToSP(Context context, String key, Object object) {
        SharedPreferences share = context.getSharedPreferences(SPUtils.FILE_NAME, Context.MODE_PRIVATE);
        if (object == null) {
            SharedPreferences.Editor editor = share.edit().remove(key);
            return editor.commit();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        // 将对象转换成byte数组，并进行base64编码
        String objectStr = new String(android.util.Base64.encode(baos.toByteArray(), android.util.Base64.DEFAULT));
        try {
            baos.close();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SharedPreferences.Editor editor = share.edit();
        // 将编码后的字符串写到base64.xml文件中
        editor.putString(key, objectStr);
        return editor.commit();
    }


    /**
     * @author 李相斌
     * created at  17:14
     * 方法说明:读取对象, kay : 名字
     */
    public static <T> T getObjectFromSP(Context context, String key) {
        SharedPreferences sharePre = context.getSharedPreferences(SPUtils.FILE_NAME, Context.MODE_PRIVATE);
        try {
            String strValue = sharePre.getString(key, "");
            // 将base64格式字符串还原成byte数组
            if (strValue == null || strValue.equals("")) { // 加此判断，否则会报java.io.StreamCorruptedException
                return null;
            }
            byte[] objBytes = android.util.Base64.decode(strValue.getBytes(),
                    android.util.Base64.DEFAULT);
            ByteArrayInputStream bais = new ByteArrayInputStream(objBytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            // 将byte数组转换成product对象
            T obj = (T) ois.readObject();
            bais.close();
            ois.close();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 作者：李相斌
     * 创建时期：2017/11/20 0020
     * 方法说明：保存 List 集合到 SP 文件
     */
    public static boolean setDataListToSP(Context ctx, String key, List<String> dataList) {
        SharedPreferences share = ctx.getSharedPreferences(SPUtils.FILE_NAME, Context.MODE_PRIVATE);
        if (null == dataList || dataList.size() <= 0) {
            SharedPreferences.Editor editor = share.edit().remove(key);
            return editor.commit();
        }
        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(dataList);
        SharedPreferences.Editor editor = share.edit();
        editor.putString(key, strJson);
        return editor.commit();

    }

    /**
     * 作者：李相斌
     * 创建时期：2017/11/20 0020
     * 方法说明：读取保存的 List 集合
     */
    public static List getDataListFromSP(Context ctx, String kay) {
        SharedPreferences sharePre = ctx.getSharedPreferences(SPUtils.FILE_NAME, Context.MODE_PRIVATE);
        List dataList = new ArrayList<String>();
        String strJson = sharePre.getString(kay, null);
        if (null == strJson) {
            return dataList;
        }
        Gson gson = new Gson();
        dataList = gson.fromJson(strJson, new TypeToken<List>() {
        }.getType());
        return dataList;

    }


}
