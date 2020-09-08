package com.lxb.freeAndroid.frame.base;

import android.view.View;

import butterknife.ButterKnife;

/**
 * 业务名:基础ViewHolder封装
 * 功能说明:
 * 编写日期: xxxx-08-28.
 * 作者: 李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public class BaseViewHolder {
    public BaseViewHolder(View item) {
        ButterKnife.bind(this, item);
    }
}
