package com.bawei.com.dssy.presenter;

import com.bawei.com.dssy.application.Contants;
import com.bawei.com.dssy.model.bean.ShopBean;
import com.bawei.com.dssy.model.http.VolleyHttp;
import com.bawei.com.dssy.view.interfaces.IMainView;

/**
 * @Auther: 樊腾亚
 * @Date: 2019/4/18 14:44:48
 * @Description:
 */
public class MainPresenter extends BasePresenter<IMainView<ShopBean>>{

    private final VolleyHttp volleyHttp;

    public MainPresenter(){
        volleyHttp = VolleyHttp.getInstance();
    }

    public void loadFormDataNet(){
        volleyHttp.VolleyGet(Contants.LIST_URL, ShopBean.class, new VolleyHttp.CallBackData<ShopBean>() {

            @Override
            public void onSuccess(ShopBean shopBean) {
                getView().CallBackSuccess(shopBean);
            }

            @Override
            public void onFail(String err) {
                getView().CallBackFail(err);
            }
        });
    }
}
