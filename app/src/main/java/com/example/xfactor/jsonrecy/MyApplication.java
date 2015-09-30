package com.example.xfactor.jsonrecy;

import android.app.Application;
import android.content.Context;

/**
 * Created by Sarthak on 13-07-2015.
 */
public class MyApplication extends Application {
    //Donot forget to add in manifest
  static  MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
    }
    public static MyApplication getInstance()
    {
        return application;
    }
    public static Context getContext()
    {
        return application.getApplicationContext();
    }
}
