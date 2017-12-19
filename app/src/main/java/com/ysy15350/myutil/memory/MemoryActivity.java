package com.ysy15350.myutil.memory;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ysy15350.myutil.R;
import com.ysy15350.ysyutils.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.Random;



/**
 * Created by yangshiyou on 2017/11/2.
 */

@ContentView(R.layout.activity_memory)
public class MemoryActivity extends BaseActivity {

    private static final String TAG = "MemoryActivity";

    @ViewInject(R.id.tv_test)
    private TextView tv_test;

    @Override
    public void initView() {
        super.initView();

        //tv_test.setText(commFunAndroid.getMemoryInfo());

    }

    @Event(R.id.tv_test)
    private void tv_testClick(View view) {
        //if (tv_test != null)
        //   tv_test.setText(commFunAndroid.getMemoryInfo());
        //doChurn();
    }

    private int rowlength = 10;
    private int length = 420000;
    private Random random = new Random();

    /**
     * 内存抖动
     */
    private void doChurn() {
        Log.d(TAG, "doChurn: start");
        for (int i = 0; i < rowlength; i++) {
            String[] strMatrix = new String[length];
            for (int j = 0; j < length; j++) {
                strMatrix[j] = String.valueOf(random.nextDouble());
            }
            Log.d(TAG, "doChurn: rowStr" + i);
        }
        Log.d(TAG, "doChurn: end");
    }

}
