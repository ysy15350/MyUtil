package com.ysy15350.myutil.main_tabs;

import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.view.View;

import com.ysy15350.myutil.R;
import com.ysy15350.ysyutils.base.mvp.MVPBaseFragment;
import com.ysy15350.ysyutils.common.RequestPermissionType;
import com.ysy15350.ysyutils.custom_view.dialog.ConfirmDialog;
import com.ysy15350.ysyutils.custom_view.dialog.QrCodeDialog;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;


@ContentView(R.layout.activity_main_tab1)
public class MainTab1Fragment extends MVPBaseFragment<MainTab1ViewInterface, MainTab1Presenter>
        implements MainTab1ViewInterface {

    private static final String TAG = "MainTab1Fragment";

    private DataTask dataTask;


    public MainTab1Fragment() {
    }

    @Override
    public MainTab1Presenter createPresenter() {
        // TODO Auto-generated method stub
        return new MainTab1Presenter(getActivity());
    }

    @Event(R.id.qr_code)
    private void qr_codeClick(View view) {


        boolean isGranted = checkPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, RequestPermissionType.REQUEST_CODE_ASK_READ_EXTERNAL_STORAGE, new PermissionsResultListener() {
            @Override
            public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
                boolean isGranted = false;
                if (grantResults != null && grantResults != null) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        isGranted = true;

                    }
                }

                if (isGranted) {
                    showQrCodeDialog();
                } else {
                    ConfirmDialog confirmDialog = new ConfirmDialog(mContext, "你已拒绝读写手机存储，去权限设置页面打开？");
                    confirmDialog.setDialogListener(new ConfirmDialog.DialogListener() {
                        @Override
                        public void onCancelClick() {

                        }

                        @Override
                        public void onOkClick() {
                            gotoMiuiPermission();
                        }
                    });
                    confirmDialog.show();

                }

            }
        });


    }

    @Event(R.id.btn_ok)
    private void btn_okClick(View view) {
        mPresenter.login();
    }



    /**
     * 二维码弹窗
     */
    private void showQrCodeDialog() {
        QrCodeDialog dialog = new QrCodeDialog(getActivity());
        dialog.show();
    }

//    @ViewInject(R.id.swipeRefreshLayout)
//    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    public void initView() {
        super.initView();

//        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
//                android.R.color.holo_orange_light, android.R.color.holo_red_light);
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                showWaitDialog("数据加载中...");
//                getData();
//            }
//        });

        //swipeRefreshLayout.setRefreshing(false);//通知停止刷新


    }

    private void getData() {

        mStatus = Status.OPENING;
        if (dataTask != null && dataTask.getStatus() != AsyncTask.Status.FINISHED) {
            dataTask.cancel(true);
        }

        //Log.d(TAG + 12345678, "openBook() called with: bookList = [" + bookList + "]");

        dataTask = new DataTask();
        dataTask.execute(0l);
    }

    public static Status mStatus;

    public enum Status {
        OPENING,
        FINISH,
        FAIL,
    }

    private class DataTask extends AsyncTask<Long, Void, Boolean> {
        private long begin = 0;


        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);


            if (isCancelled()) {
                return;
            }
            if (result) {
                showMsg("success");

            } else {
                showMsg("fail");
            }


            mStatus = MainTab1Fragment.Status.FINISH;

            //swipeRefreshLayout.setRefreshing(false);//通知停止刷新
            hideWaitDialog();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Boolean doInBackground(Long... params) {
            begin = params[0];
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

    }


}
