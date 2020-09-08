package com.lxb.freeAndroid.project.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 业务名:字符串过滤工具类
 * 功能说明:
 * 编写日期: xxxx-09-09.
 * 作者: 李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public class StringFilterUtils {

    //表情符过滤
    public static String specileCharFilter(String str) {
        // 只允许字母、数字和汉字  （）()——
        String regEx = "[^a-zA-Z0-9\u4E00-\u9FA5,.，@。\\s\\[\\]()（）【】*_\\-——…·]";
//        String   regEx  =  "[^a-zA-Z0-9\u4E00-\u9FA5\\p{P}\\p{L}\\p{M}\\p{Z}\\p{S}\\p{N}]";
//        String   regEx  =  "[^a-zA-Z0-9\u4E00-\u9FA5+\\p{P}\\p{Z}\\p{M}\\p{N}]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
}
