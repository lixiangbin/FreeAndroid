package com.lxb.freeAndroid.project.utils;

import android.util.Log;

import java.io.File;

/**
 * 业务名:文件操作/IO流操作工具类
 * 功能说明:
 * 编写日期: xxxx-09-12.
 * 作者: 李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public class FileIOUtils {

    /**
     * 作者：李相斌
     * 创建时期：2018/4/17 0017
     * 方法说明：递归方式删除目录下所有文件
     */
    public static void deleteFilesAll(File file) {
        if (file != null) {
            File[] fileRoot = file.listFiles();
            if (fileRoot == null) {//解决部分子目录无权限访问 报空指针问题
                return;
            }
            for (File files : fileRoot) {
                Log.i("目录文件数", files.getPath() + "—→" + fileRoot.length + "");
                if (!files.isDirectory()) {
                    if (files.delete()) {
                        Log.i("清除目录", files.getAbsolutePath() + "-" + "删除成功");
                    } else {
                        Log.i("清除目录", files.getAbsolutePath() + "-" + "删除失败");
                    }
                } else {
                    deleteFilesAll(files);
                }
            }
        }
    }
}
