package com.vierbom.opensourcejun;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;

/**
 * 初始化Application
 * Created by xiangyang550 on 15/8/8.
 */
public class ApplicationManager {

    private static Context application;
    private static Handler mainHandler;

    public static void init(Context application) {
        ApplicationManager.application = application;
    }


    public static Context getApplicationContext() {
        return application;
    }

    /**
     * 获取App安装包信息
     *
     * @return
     */
    public static PackageInfo getPackageInfo() {

        PackageInfo info = null;
        try {
            info = application.getPackageManager().getPackageInfo(application.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
        if (info == null)
            info = new PackageInfo();
        return info;
    }


    /**
     * 获取主线成的handler，主线成唯一，维护消息队列的handler可以直接从这里，一般给bussiness层去使用
     * @return
     */
    public static Handler getMainHandler(){
        if (mainHandler!=null){
            return mainHandler;
        }
        synchronized (ApplicationManager.class){
            if (mainHandler==null){
                mainHandler=new Handler(Looper.getMainLooper());
            }
        }
        return  mainHandler;
    }

}
