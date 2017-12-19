package com.ysy15350.myutil.main_tabs;


import android.content.Intent;
import android.view.View;

import com.ysy15350.myutil.R;
import com.ysy15350.myutil.other.SettingActivity;
import com.ysy15350.ysyutils.base.mvp.MVPBaseFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;


@ContentView(R.layout.activity_main_tab2)
public class MainTab2Fragment extends MVPBaseFragment<MainTab2ViewInterface, MainTab2Presenter>
        implements MainTab2ViewInterface {

    private static final String TAG = "MainTab2Fragment";


    public MainTab2Fragment() {
    }

    @Override
    public MainTab2Presenter createPresenter() {
        // TODO Auto-generated method stub
        return new MainTab2Presenter(getActivity());
    }


    /**
     * 设置
     *
     * @param view
     */
    @Event(value = R.id.btn_setting)
    private void btn_settingClick(View view) {
        startActivity(new Intent(getActivity(), SettingActivity.class));
    }


}
