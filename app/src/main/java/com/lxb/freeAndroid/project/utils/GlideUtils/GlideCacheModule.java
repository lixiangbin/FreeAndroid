package com.lxb.freeAndroid.project.utils.GlideUtils;

import android.content.Context;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;

/**
 * 业务名:Glide缓存配置
 * 功能说明:
 * 编写日期: xxxx-09-12.
 * 作者: 李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */

@GlideModule
public class GlideCacheModule extends AppGlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        super.applyOptions(context, builder);
        builder.setMemoryCache(new LruResourceCache(GlideCatchConfig.GLIDE__MEMORY_CATCH_SIZE))

                //设置缓存目录为存储卡目录下(/sdcard/Android/data/<application package>/cache)
                //.setDiskCache(new DiskLruCacheFactory(context.getCacheDir().getPath() + "/" + GlideCatchConfig.GLIDE_CATCH_DIR, GlideCatchConfig.GLIDE_CATCH_SIZE));

                //设置缓存目录为data/data目录下(/data/data/<application package>/cache)
                .setDiskCache(new InternalCacheDiskCacheFactory(context, context.getCacheDir().getPath() + "/" + GlideCatchConfig.GLIDE_CATCH_DIR,
                        GlideCatchConfig.GLIDE_CATCH_SIZE));

    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        super.registerComponents(context, glide, registry);
    }

    @Override
    public boolean isManifestParsingEnabled() {
        //return super.isManifestParsingEnabled();
        return false;
    }
}
