package com.ysy15350.myutil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.ysy15350.ysyutils.base.mvp.MVPBaseActivity;
import com.ysy15350.ysyutils.common.AppStatusManager;
import com.ysy15350.ysyutils.common.CommFunAndroid;
import com.ysy15350.ysyutils.common.message.MessageBox;

import org.xutils.view.annotation.ContentView;



@ContentView(R.layout.activity_guide)
public class GuideActivity extends MVPBaseActivity<GuideViewInterface, GuidePresenter>
        implements GuideViewInterface {

    private static final String TAG = "GuideActivity";

    @Override
    protected GuidePresenter createPresenter() {
        // TODO Auto-generated method stub
        return new GuidePresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppStatusManager.getInstance().setAppStatus(AppStatusManager.AppStatusConstant.APP_NORMAL);
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_guide);
    }


    @Override
    public void initView() {
        super.initView();

//        String str = null;
//        Log.d(TAG, "initView: " + str.trim());

        mPresenter.login();

        //判断根Activity代码
        if (!isTaskRoot()) {
            Log.d(TAG, "onCreate() called with: isTaskRoot = [" + isTaskRoot() + "]");
            finish();
            return;
        }

        CommFunAndroid.getCahePath(this);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //http://blog.csdn.net/wangshihui512/article/details/50768294
        //view自带的定时器：postDelayed方法
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                GuideActivity.this.startActivity(intent);
                GuideActivity.this.finish();

                MessageBox.show("跳转");

            }
        }, 3 * 1000);//3秒后执行
    }
}
