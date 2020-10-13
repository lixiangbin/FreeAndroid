![](https://github.com/lixiangbin/FreeAndroid/blob/master/附件/titleLogo.jpg)

# FreeAndroid  

   <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;这是一个能让你快速开始新项目而不必重新搭建框架的开源mvp项目，旨在为了让更多的开发者在开发初期，或者开发周期要求较短的情况下，只需关注业务层开发，不用再去设计搭建繁琐的框架层；同时也为尝试学习设计框架的Android开发人员提供一个示范学习项目；当然，如果您发现项目中有需要改进或优化的地方，欢迎联系作者(联系方式在结尾处)指出或提交 "Pull Requests"。</p>

**【项目中用到的技术或开源库】**  
架构模式：MVP  
网络框架：Retrofit+okHttp  
注入框架：ButterKnife  
异步处理/线程切换：RxJava/RxAndroid  
图片加载：Glide  
事件总线：EventBus (这里未使用RxBus,有两点原因:1.RxBus无法发送粘性事件;2.RxBus效率会随订阅者增多而成比例下降等;若你的项
                    目中不介意这些,可在gradle文件中删除EventBus引用,并将RxBus文件添加到项目中;有关RxBus使用这里暂不讲解)  
动态权限库：RxPermissions2


**【项目目录结构说明】**

![](https://github.com/lixiangbin/FreeAndroid/blob/master/附件/目录.jpg)

**【如何使用】**

<h4>一、请参考以下Demo：</h4>
<p>1.MVP模式的Activity使用可参考 project/modulesDemo/login 目录下LoginActivity；</br>
2.MVP模式的Fragment使用可参考 project/mainDemo/fragment 目录下FirstFragment；</br>
3.相关M层、V层、P层的继承写法以及使用可参考此目录下相关类文件；</br>
4.无网络请求的Activity可继承OrdinaryBaseActivity作为基类，具体请参考SettingsActivity。
</p>

<h3>相关类文件释义:</h3>
<ul><li>xxxBean：实体类；</li>
<li>xxxContract：契约类，为了减少冗余文件，此处将Model契约和View契约写在同一个类文件中；此后将由xxxActivity具体实现View层方法，xxxModelImpl具体实现Model层方法；</li>
<li>xxxModelImpl：Model的实现类，负责进行网络数据请求；</li>
<li>xxxPresenter：主导层，在此类中处理来自View层的数据，或将Model层的数据传给View层。</li>
</ul>

<h4>二、开始前请配置以下文件(必：表示必配，选：表示选配)：</h4>
<ul><li>[必]1.ApiUrl.java类 (app/src/main/java/com/lxb/freeAndroid/frame/http/ApiUrl.java)：服务器域名、api接口地址等url可在此文件中配置、添加；</li>
<li>[必]2.BaseModel.java类 (app/src/main/java/com/lxb/freeAndroid/frame/mvp/BaseModel.java)：公共请求参数可在此类的“setParams()”方法中配置；</li>
<li>[选]3.ResponseObserver.java类 (app/src/main/java/com/lxb/freeAndroid/frame/http/ResponseObserver.java)：此文件中可根据实际需要对你请求到的网络数据进行预处理、业务预封装等；</li>
<li>[选]4.OkHttpClientManager.java类 (app/src/main/java/com/lxb/freeAndroid/frame/http/OkHttpClientManager.java)：可在此文件中配置SSL相关，默认信任所有主机；</li>
<li>[选]5.Service.java类 (app/src/main/java/com/lxb/freeAndroid/frame/http/Service.java)：api接口封装，此处只封装了一个常用POST请求，可根据需求添加GET请求、文件上传下载等api等。</li>
另：其它配置(如：签名、包名等)这里不再详述；</li>
</ul>

**三、其它：**
<p>1.项目中所提供的工具类、自定义View、属性样式、资源文件等，可自行修改或增删；
</p>

    作者QQ：1276655894
 
