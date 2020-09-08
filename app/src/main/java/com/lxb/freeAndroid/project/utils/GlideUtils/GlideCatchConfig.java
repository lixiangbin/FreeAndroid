package com.lxb.freeAndroid.project.utils.GlideUtils;


/**
 * 业务名: Glide缓存配置文件
 * 功能说明:
 * 编写日期: xxxx-09-11
 * 作者:  李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */

public class GlideCatchConfig {

    // 图片缓存最大容量，150M，根据自己的需求进行修改
    public static final int GLIDE_CATCH_SIZE = 1024 * 1024 * 150;
    //内存缓存大小  20MB
    public static final int GLIDE__MEMORY_CATCH_SIZE = 1024 * 1024 * 20;
    // 图片缓存子目录
    public static final String GLIDE_CATCH_DIR = "image_catch";

}
