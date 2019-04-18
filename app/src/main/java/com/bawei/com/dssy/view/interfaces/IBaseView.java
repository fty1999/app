package com.bawei.com.dssy.view.interfaces;

import com.bawei.com.dssy.model.bean.ShopBean;

/**
 * @Auther: 樊腾亚
 * @Date: 2019/4/18 14:38:52
 * @Description:
 */
public interface IBaseView {
    void CallBackSuccess(ShopBean shopBean);
    void CallBackFail(String errMsg);
}
