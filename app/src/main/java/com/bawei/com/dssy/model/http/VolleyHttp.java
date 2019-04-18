package com.bawei.com.dssy.model.http;

import android.os.Handler;
import android.os.Message;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bawei.com.dssy.application.MyApp;
import com.google.gson.Gson;

/**
 * @Auther: 樊腾亚
 * @Date: 2019/4/18 14:41:15
 * @Description:
 */
public class VolleyHttp <T>{

    private static VolleyHttp volleyHttp;

    //懒汉式
    public static synchronized VolleyHttp getInstance(){
        if (volleyHttp == null){
            volleyHttp = new VolleyHttp();
        }
        return volleyHttp;
    }

    private CallBackData mCallBack;
    public void VolleyGet(String url, final Class<T> tClass, CallBackData callBackData){
        this.mCallBack = callBackData;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Message message = handler.obtainMessage();
                message.what = 1;
                Gson gson = new Gson();
                T t = gson.fromJson(response, tClass);
                message.obj = t;
                handler.sendMessage(message);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Message message = handler.obtainMessage();
                message.what = 0;
                message.obj = error.getMessage();
                handler.sendMessage(message);
            }
        });
        stringRequest.setTag("get");
        MyApp.requestQueue().add(stringRequest);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                T t = (T) msg.obj;
                mCallBack.onSuccess(t);
            }else {
                String err = (String) msg.obj;
                mCallBack.onFail(err);
            }
        }
    };

    //创建一个接口
    public interface CallBackData<D>{
        void onSuccess(D d);
        void onFail(String err);
    }
}
