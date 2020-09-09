package com.lxb.freeAndroid.frame.base;

import java.io.Serializable;

/**
 * 业务名: 基础解析实体类
 * 功能说明:
 * 编写日期: xxxx-08-26
 * 作者:  李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */

public class BaseResponseBean<T> implements Serializable {
    //服务器返回码
    public String returnCode;
    //是否成功
    public int succeed;
    //服务器提示信息
    public String returnMsg;
    //服务器返回数据
    public T result;

    @Override
    public String toString() {
        return "BaseResponseBean{" +
                "returnCode='" + returnCode + '\'' +
                ", succeed=" + succeed +
                ", returnMsg='" + returnMsg + '\'' +
                ", result=" + result +
                '}';
    }
}
