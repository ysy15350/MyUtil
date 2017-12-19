package api;

import com.ysy15350.ysyutils.api.ApiCallBack;

/**
 * Created by yangshiyou on 2017/12/15.
 */

public interface UserApi {

    public void login(String userName, String password, ApiCallBack callBack);

}
