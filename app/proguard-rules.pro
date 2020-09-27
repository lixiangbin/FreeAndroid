#-------------公共配置----------------#
#打印混淆的详细信息
-verbose
#预校验
-dontpreverify
#忽略警告
-ignorewarnings
#指定压缩级别
-optimizationpasses 5
#不优化输入的类文件
-dontoptimize
#优化时允许访问并修改有修饰符的类和类的成员
-allowaccessmodification
#包名不混合大小写
-dontusemixedcaseclassnames
#不忽略非公共的库类
-dontskipnonpubliclibraryclasses
#保留行号
-keepattributes SourceFile,LineNumberTable
#避免混淆泛型
-keepattributes Signature
#抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable
#保护注解
-keepattributes *Annotation*
#排除JavascriptInterface
-keepattributes *JavascriptInterface*
#混淆采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
#保留native方法的类名和方法名
-keepclasseswithmembernames class * { native <methods>; }


#-------------引用开源库配置----------------#
#Glide混淆
 -dontwarn com.bumptech.glide.**
 -keep class com.bumptech.glide.**{*;}
 -keep public class * implements com.bumptech.glide.module.GlideModule
 -keep public class * extends com.bumptech.glide.module.AppGlideModule
 -keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
   **[] $VALUES;
   public *;
 }
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule
#Glide如果API级别<=Android API 27 则需要添加
 -dontwarn com.bumptech.glide.load.resource.bitmap.VideoDecoder
#OkHttp3
 -dontwarn okhttp3.logging.**
 -keep class okhttp3.**{*;}
 -keep class okhttp3.internal.**{*;}
 -dontwarn okio.**
 -keep class okio.**{*;}
 -dontwarn okhttp3.**
 -keep class org.apache.** {*;}
#Retrofit
 -dontwarn retrofit2.**
 -keep class retrofit2.** { *; }
 -keepattributes Signature
 -keepattributes Exceptions
#RxJava RxAndroid
 -dontwarn sun.misc.**
 -keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
     long producerIndex;
     long consumerIndex;
 }
 -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef { rx.internal.util.atomic.LinkedQueueNode producerNode; }
 -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef { rx.internal.util.atomic.LinkedQueueNode consumerNode; }
#Gson
 -keep class sun.misc.Unsafe { *; }
 -keep class com.google.gson.stream.** { *; }
 -keep class com.google.gson.** {*;}
 -keep class com.google.gson.examples.android.model.** { *; }
 -dontwarn com.google.gson.**

#扫码
 #//-keep class com.google.zxing.** { *; }
#ImmersionBar库
 #//-keep class com.gyf.immersionbar.* {*;}
 #//-dontwarn com.gyf.immersionbar.**
#XRecyclerview
 -keep class com.jcodecraeer.xrecyclerview.** { *; }
#WebView
 #-keepclassmembers class fqcn.of.javascript.interface.for.webview { public *;}
 #-keepclassmembers class * extends android.webkit.WebViewClient {
 #    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
 #    public boolean *(android.webkit.WebView, java.lang.String);
 #}
 #-keepclassmembers class * extends android.webkit.WebViewClient {
 #    public void *(android.webkit.WebView, jav.lang.String);
 #}
#EventBus 事件/消息总线
 -keepattributes *Annotation*
 -keepclassmembers class * {@org.greenrobot.eventbus.Subscribe <methods>;}
 -keep enum org.greenrobot.eventbus.ThreadMode { *; }
 -keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {<init>(java.lang.Throwable);}
  # And if you use AsyncExecutor:
 -keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {<init>(java.lang.Throwable);}

#-------------项目配置----------------#
#自定义控件
 -keep public class * extends android.view.View{
     *** get*();
     void set*(***);
     public <init>(android.content.Context);
     public <init>(android.content.Context, android.util.AttributeSet);
     public <init>(android.content.Context, android.util.AttributeSet, int);
 }
#保留所有组件
 -keep public class * extends android.app.Activity
 -keep public class * extends android.app.Appliction
 -keep public class * extends android.app.Service
 -keep public class * extends android.content.BroadcastReceiver
 -keep public class * extends android.content.ContentProvider
 -keep public class * extends android.app.backup.BackupAgentHelper
 -keep public class * extends android.preference.Preference
 -keep public class * extends android.view.View
 -keep public class com.android.vending.licensing.ILicensingService
#保留support下的所有类及其内部类
 -keep class android.support.** {*;}
 -keep public class * extends android.support.v4.**
 -keep public class * extends android.support.v7.**
 -keep public class * extends android.support.annotation.**
#保留R资源
 -keep class **.R$* {*;}
#保留在Activity中的方法参数是view的方法，
 -keepclassmembers class * extends android.app.Activity{ public void *(android.view.View);}
#保留枚举类不被混淆
#-keepclassmembers enum * {
#     public static **[] values();
#     public static ** valueOf(java.lang.String);
# }
#保留带有回调函数的Event、*Listener
-keepclassmembers class * {
    void *(**Event);
    void *(**Listener);
}
#保留Parcelable序列化类不被混淆
 -keep class * implements android.os.Parcelable {
     public static final android.os.Parcelable$Creator *;
 }
#保留Serializable序列化的类不被混淆
 -keepclassmembers class * implements java.io.Serializable {
     static final long serialVersionUID;
     private static final java.io.ObjectStreamField[] serialPersistentFields;
     !static !transient <fields>;
     !private <fields>;
     !private <methods>;
     private void writeObject(java.io.ObjectOutputStream);
     private void readObject(java.io.ObjectInputStream);
     java.lang.Object writeReplace();
     java.lang.Object readResolve();
 }

#保留业务模块中相关目录、文件
 -keep class com.lxb.freeAndroid.frame.base.**{*;}
 -keep class com.lxb.freeAndroid.frame.http.**{*;}
 -keep class com.lxb.freeAndroid.frame.mvp.**{*;}
 -keep class com.lxb.freeAndroid.project.views.**{*;}
 -keep class com.lxb.freeAndroid.project.modulesUI.**{*;}

# -keep class com.lxb.freeAndroid.project.modulesUI.login.**{*;}
# -keep class com.lxb.freeAndroid.project.modulesUI.main.**{*;}
# -keep class com.lxb.freeAndroid.project.modulesUI.result.**{*;}
# -keep class com.lxb.freeAndroid.project.modulesUI.scan.**{*;}
# -keep class com.lxb.freeAndroid.project.modulesUI.settings.**{*;}
# -keep class com.lxb.freeAndroid.project.modulesUI.settings.modifyPassword.**{*;}






