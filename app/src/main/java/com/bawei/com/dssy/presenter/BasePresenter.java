package com.bawei.com.dssy.presenter;

/**
 * @Auther: 樊腾亚
 * @Date: 2019/4/18 14:44:38
 * @Description:
 */
public class BasePresenter <V>{
    public V view;

    public V getView() {
        return view;
    }

    public void setView(V view) {
        this.view = view;
    }
    public void detachView(){
        this.view = null;
    }
}
