package com.lxb.freeAndroid.frame.http;


import com.lxb.freeAndroid.frame.base.BaseResponseBean;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * 业务名: 公共Service接口
 * 功能说明:
 * 编写日期: xxxx-08-26.
 * 作者: 李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public interface Service {
    @FormUrlEncoded
    @POST
    Observable<BaseResponseBean> executePost(@Url String url, @FieldMap Map<String, Object> map);
}
