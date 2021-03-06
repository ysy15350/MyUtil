package com.ysy15350.ysyutils.base;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.ysy15350.ysyutils.R;
import com.ysy15350.ysyutils.common.CommFunAndroid;
import com.ysy15350.ysyutils.custom_view.x_view.XListView;


public abstract class BaseListViewFragment extends BaseFragment
        implements XListView.IXListViewListener, OnItemClickListener {

    /**
     * 下拉刷新列表
     */
//    @ViewInject(R.id.xListView)
    protected XListView xListView;


    /**
     * 无数据时显示
     */
//    @ViewInject(R.id.ll_nodata)
    protected LinearLayout ll_nodata;

    @Override
    public void initView() {
        // TODO Auto-generated method stub
        super.initView();

        if (xListView != null) {

            xListView.setDivider(new ColorDrawable(getResources().getColor(R.color.devider_color))); // 设置间距颜色
            xListView.setDividerHeight(10); // 设置间距高度(此必须设置在setDivider（）之后，否则无效果)

            xListView.setPullLoadEnable(true);

            xListView.setXListViewListener(this);

            xListView.setOnItemClickListener(this);
        }
    }

    protected int page = 1, pageSize = 10;

    public abstract void initData(int page, int pageSize);

    @Override
    public void onRefresh() {
        // TODO Auto-generated method stub
        page = 1;
        initData(page, pageSize);
    }

    @Override
    public void onLoadMore() {
        initData(page, pageSize);
    }

    /**
     * 绑定下拉列表
     */
    protected void bindListView(BaseAdapter mAdapter) {
        // TODO Auto-generated method stub

        if (mAdapter != null) {

            int count = mAdapter.getCount();
            if (count == 0) {
                mHolder.setVisibility_GONE(R.id.xListView)
                        .setVisibility_VISIBLE(R.id.ll_nodata)
                //.setText(R.id.tv_nodata, "暂无数据，点击刷新")
                ;
            } else {
                mHolder.setVisibility_GONE(R.id.ll_nodata)
                        .setVisibility_VISIBLE(R.id.xListView)
                ;
            }

            if (page == 1) {

                String timeStr = CommFunAndroid.getDateString("yyyy-MM-dd HH:mm:ss");
                xListView.setRefreshTime(timeStr);

                xListView.setAdapter(mAdapter);

            } else {
                mAdapter.notifyDataSetChanged();


            }

            xListView.stopRefresh();
            xListView.stopLoadMore();


            // // listview布局动画
            // if (mContext != null) {
            // LayoutAnimationController lac = new LayoutAnimationController(
            // AnimationUtils.loadAnimation(mContext, R.anim.zoom_in));
            // lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
            // xListView.setLayoutAnimation(lac);
            // xListView.startLayoutAnimation();
            // }
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // TODO Auto-generated method stub

    }


}
