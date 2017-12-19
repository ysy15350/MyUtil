package com.ysy15350.myutil.main_tabs;

import android.content.Context;

import com.ysy15350.ysyutils.api.ApiCallBack;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.base.mvp.BasePresenter;

import api.UserApi;
import api.impl.UserApiImpl;


public class MainTab1Presenter extends BasePresenter<MainTab1ViewInterface> {

    public MainTab1Presenter() {
    }

    public MainTab1Presenter(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    private UserApi userApi=new UserApiImpl();

    public void login(){
        userApi.login("test", "test", new ApiCallBack() {
            @Override
            public void onSuccess(boolean isCache, Response response) {
                super.onSuccess(isCache, response);
            }
        });
    }


}
