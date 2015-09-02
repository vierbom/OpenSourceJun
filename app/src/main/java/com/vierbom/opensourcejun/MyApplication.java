package com.vierbom.opensourcejun;

import android.app.Application;

/**
 * Created by vierbom on 15/8/31.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationManager.init(getApplicationContext());
    }
}
