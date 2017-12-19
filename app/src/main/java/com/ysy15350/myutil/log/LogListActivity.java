package com.ysy15350.myutil.log;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.ysy15350.ysyutils.R;
import com.ysy15350.ysyutils.adapters.ListViewAdpater_FileInfo;
import com.ysy15350.ysyutils.base.mvp.MVPBaseListViewActivity;
import com.ysy15350.ysyutils.model.FileInfo;

import org.xutils.view.annotation.ContentView;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by yangshiyou on 2017/8/31.
 */
@ContentView(R.layout.activity_list)
public class LogListActivity extends MVPBaseListViewActivity<LogListViewInterface, LogListPresenter>
        implements LogListViewInterface {

    @Override
    protected LogListPresenter createPresenter() {

        return new LogListPresenter(LogListActivity.this);
    }


    @Override
    public void initView() {


        super.initView();
        setFormHead("接口访问日志");
        setMenuText("错误日志");
        //xListView.setDividerHeight(CommFunAndroid.dip2px(this, 0)); // 设置间距高度(此必须设置在setDivider（）之后，否则无效果)
        xListView.setPullLoadEnable(false);

    }


    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        initData(page, pageSize);


    }

    @Override
    public void initData(int page, int pageSize) {
        List<FileInfo> fileInfos = mPresenter.getFileList(getApplicationContext());

        bindData(fileInfos);
    }


    List<FileInfo> mList = new ArrayList<>();

    ListViewAdpater_FileInfo mAdapter;

    public void bindData(List<FileInfo> list) {


        if (page == 1) {
            mList.clear();
        } else {
            if (list == null) {
                showMsg("没有更多了");
                xListView.stopLoadMore();
            } else if (list.isEmpty()) {
                showMsg("没有更多了");
                xListView.stopLoadMore();
            }
        }

        if (list != null)
            mList.addAll(list);
        mAdapter = new ListViewAdpater_FileInfo(this, mList);

        bindListView(mAdapter);// 调用父类绑定数据方法

        if (list != null && !list.isEmpty()) {
            page++;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        super.onItemClick(parent, view, position, id);

        FileInfo fileInfo = (FileInfo) parent.getItemAtPosition(position);
        if (fileInfo != null) {
            Intent intent = new Intent(this, LogDetailActivity.class);
            intent.putExtra("fileInfo", fileInfo);
            startActivity(intent);
        }
    }
}
