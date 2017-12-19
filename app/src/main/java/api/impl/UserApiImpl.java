package api.impl;

import com.ysy15350.ysyutils.Ysy;
import com.ysy15350.ysyutils.api.ApiCallBack;
import com.ysy15350.ysyutils.api.model.RequestOptions;

import api.UserApi;

/**
 * Created by yangshiyou on 2017/12/15.
 */

public class UserApiImpl implements UserApi {

    private static final String moduleName = "user/";

    @Override
    public void login(String userName, String password, ApiCallBack callBack) {


        //RequestOptions.Builder builder = new RequestOptions.Builder();

        RequestOptions requestOptions = new RequestOptions.Builder()
                .setRequestMapping(moduleName+"login")
                .addBodyParameter("mobile", userName)
                .addBodyParameter("password", password)
                .build();


        Ysy.http().requestPost(requestOptions,callBack);

    }
}
