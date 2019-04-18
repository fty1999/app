package com.bawei.com.dssy.application;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * @Auther: 樊腾亚
 * @Date: 2019/4/18 14:48:19
 * @Description:
 */
public class MyApp extends Application {

    private static RequestQueue queue;

    @Override
    public void onCreate() {
        super.onCreate();
        queue = Volley.newRequestQueue(getApplicationContext());
    }
    public static RequestQueue requestQueue(){
        return queue;
    }
}
