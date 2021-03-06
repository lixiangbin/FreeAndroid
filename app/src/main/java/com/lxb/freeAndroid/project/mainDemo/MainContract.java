package com.lxb.freeAndroid.project.mainDemo;

import com.lxb.freeAndroid.frame.http.ResponseObserver;
import com.lxb.freeAndroid.frame.mvp.IModel;
import com.lxb.freeAndroid.frame.mvp.IView;

import java.util.HashMap;

/**
 * 业务名:契约 Contract   --主页
 * 功能说明:契约
 * 编写日期: xxxx/3/26 0026.
 * 作者: 李相斌
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public class MainContract {


   public interface MainView extends IView {

    }

   public interface MainModel extends IModel {
        //登录请求
        void test(HashMap<String, Object> paramMap, ResponseObserver<MainBean> responseObserver);

        //退出请求
        void quit(HashMap<String, Object> paramMap, ResponseObserver<MainBean> responseObserver);
    }


}
