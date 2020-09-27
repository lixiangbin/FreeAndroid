![](https://github.com/lixiangbin/FreeAndroid/blob/master/附件/titleLogo.jpg)

# FreeAndroid  

   <p>\t这是一个能让你快速开始新项目而不必重新搭建框架的开源mvp项目，旨在为了让更多的开发者在开发初期，或者开发周期要求较短的情况下，只需关注业务层开发，不用再去设计搭建繁琐的框架层；同时也为尝试学习设计框架的Android开发人员提供一个示范学习项目；当然，如果您发现项目中有需要改进或优化的地方，欢迎联系作者(联系方式在结尾处)指出或提交 "Pull Requests"。</p>

**【项目中用到的技术或开源库】**  
架构模式：MVP  
网络框架：Retrofit+okHttp  
注入框架：ButterKnife  
异步处理/线程切换：RxJava/RxAndroid  
图片加载：Glide  
事件总线：EventBus (这里未使用RxBus,有两点原因:1.RxBus无法发送粘性事件;2.RxBus效率会随订阅者增多而成比例下降等;若你的项
                    目中不介意这些,可在gradle文件中删除EventBus引用,并将RxBus文件添加到项目中;有关RxBus使用这里暂不讲解)  
动态权限库：RxPermissions2




**【·项目框架目录结构说明·】**

![](https://github.com/lixiangbin/FreeAndroid/blob/master/附件/目录.jpg)

    作者QQ：1276655894
 
