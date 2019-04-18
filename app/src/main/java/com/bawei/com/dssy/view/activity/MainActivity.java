package com.bawei.com.dssy.view.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bawei.com.dssy.R;
import com.bawei.com.dssy.model.bean.ShopBean;
import com.bawei.com.dssy.presenter.MainPresenter;
import com.bawei.com.dssy.view.adapter.ShopAdapter;
import com.bawei.com.dssy.view.adapter.ShopThreeAdapter;
import com.bawei.com.dssy.view.adapter.ShopTwoAdapter;
import com.bawei.com.dssy.view.interfaces.IMainView;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainView {

    private RecyclerView recyclerView1;
    private MainPresenter mainPresenter;
    private ShopAdapter shopAdapter;
    private RecyclerView recyclerView2;
    private ShopTwoAdapter shopTwoAdapter;
    private RecyclerView recyclerView3;
    private ShopThreeAdapter shopThreeAdapter;
    private Banner banner;
    List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        shopAdapter = new ShopAdapter(this);
        recyclerView1.setAdapter(shopAdapter);
        mainPresenter = new MainPresenter();
        mainPresenter.setView(this);
        mainPresenter.loadFormDataNet();

        recyclerView2 = findViewById(R.id.recyclerView2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        shopTwoAdapter = new ShopTwoAdapter(this);
        recyclerView2.setAdapter(shopTwoAdapter);

        recyclerView3 = findViewById(R.id.recyclerView3);
        recyclerView3.setLayoutManager(new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false));
        shopThreeAdapter = new ShopThreeAdapter(this);
        recyclerView3.setAdapter(shopThreeAdapter);

        //图片
        banner = findViewById(R.id.banner);
        list.add("http://172.17.8.100/images/small/commodity/nx/fbx/1/1.jpg");
        list.add("http://172.17.8.100/images/small/commodity/nx/bx/7/1.jpg");
        list.add("http://172.17.8.100/images/small/commodity/mzhf/cz/4/1.jpg");

        banner.setImages(list);
        banner.isAutoPlay(true);
        banner.setDelayTime(2000);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.detachView();
    }

    @Override
    public void CallBackSuccess(ShopBean shopBean) {
        List<ShopBean.ResultBean.RxxpBean.CommodityListBean> data = shopBean.getResult().getRxxp().getCommodityList();
        shopAdapter.setData(data);
        List<ShopBean.ResultBean.MlssBean.CommodityListBeanXX> data1 = shopBean.getResult().getMlss().getCommodityList();
        shopTwoAdapter.setDatae(data1);
        List<ShopBean.ResultBean.PzshBean.CommodityListBeanX> data2 = shopBean.getResult().getPzsh().getCommodityList();
        shopThreeAdapter.setDatae(data2);
    }

    @Override
    public void CallBackFail(String errMsg) {

    }
}
