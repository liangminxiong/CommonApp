package com.mvp_0726.project_0726.web.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.StringCallback;
import com.mvp_0726.common.Model.FileBean;
import com.mvp_0726.common.base.codereview.BaseActivity;
import com.mvp_0726.common.event.CommonEvent;
import com.mvp_0726.common.network.ApiService;
import com.mvp_0726.common.utils.Constans;
import com.mvp_0726.common.utils.FileUtils2;
import com.mvp_0726.common.utils.Global;
import com.mvp_0726.common.utils.GsonTools;
import com.mvp_0726.common.utils.StatusBarUtil;
import com.mvp_0726.common.view.DrawableCenterTextView;
import com.mvp_0726.common.view.webview.H5Control;
import com.mvp_0726.project_0726.constant.Constant;
import com.mvp_0726.project_0726.web.utli.WebStringUtils;
import com.project.wisdomfirecontrol.R;
import com.project.wisdomfirecontrol.common.base.Const;
import com.project.wisdomfirecontrol.common.base.UserInfo;
import com.project.wisdomfirecontrol.common.base.UserManage;
import com.project.wisdomfirecontrol.firecontrol.base.MyApplication;
import com.project.wisdomfirecontrol.firecontrol.ui.activity_common.DecumentManageActivity;
import com.project.wisdomfirecontrol.firecontrol.ui.activity_common.FireInspectionActivity;
import com.project.wisdomfirecontrol.firecontrol.ui.activity_unit.RectificationDocumentActivity;
import com.project.wisdomfirecontrol.firecontrol.ui.utils.WebViewUtils;
import com.tencent.smtt.sdk.TbsReaderView;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

/**
 * Created by Administrator on 2018/8/7.
 * h5跳转
 */

public class WebH5Activity extends BaseActivity implements H5Control {

    private DrawableCenterTextView tv_item_name_1, tv_item_name_2, tv_item_name_3, tv_item_name_4;
    private String title;
    private LinearLayout ll_item_title;
    private String userid;
    private String pid;
    private WebSettings webSettings;
    private WebView webView;

    private WebViewUtils webViewUtil;
    private ProgressBar mWebProgressbar;
    private String url;
    private List<String> itemNameList = new ArrayList<>();
    private TbsReaderView readerView;


    @Override
    protected int getContentViewResId() {
        return R.layout.moudle_activity_webh5;
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void initView(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        View view = findViewById(R.id.space);

        view.setBackground(mActivity.getResources().getDrawable(R.drawable.title_toolbar_bg_blue));
        StatusBarUtil.setFadeStatusBarHeight(mActivity, view);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            title = (String) bundle.get(Constant.INTENT_KEY);
            assert title != null;
            if (!title.isEmpty()) {
                tv_title.setText(title);
            }
        }

        ll_item_title = findViewById(R.id.ll_item_title);
        tv_item_name_1 = findViewById(R.id.tv_item_name_1);
        tv_item_name_2 = findViewById(R.id.tv_item_name_2);
        tv_item_name_3 = findViewById(R.id.tv_item_name_3);
        tv_item_name_4 = findViewById(R.id.tv_item_name_4);

        webView = findViewById(R.id.webview);
        webView.requestFocusFromTouch();
        mWebProgressbar = findViewById(R.id.web_progressbar);

        if (title.equals(Constant.SAFE_PERSONAL) || title.equals(Constant.GONGGONGZIYUAN)
                || title.equals(Constant.GONGGONGZIYUAN) || title.equals(Constant.XINGZHENGGONGWEN_NEWADD)) {
            tv_item_name_3.setVisibility(View.GONE);
        } else if (title.equals(Constant.XINGZHENGGONGWEN)) {
            tv_item_name_4.setVisibility(View.VISIBLE);
        } else if (title.equals(Constant.TONGJIFENXI) || title.equals(Constant.HISTORY_RECOIDING)) {
            ll_item_title.setVisibility(View.GONE);
        }

        itemNameList.clear();
        itemNameList = WebStringUtils.getItemName(title);
        showTxt(itemNameList);
        url = "";
        url = WebStringUtils.getUrlByName(title, Constant.FISRT, "");
        initDefaultFragment(url);

    }

    private void showTxt(List<String> itemNameList) {

        int size = itemNameList.size();
        if (size > 3) {
            tv_item_name_1.setText(itemNameList.get(0));
            tv_item_name_2.setText(itemNameList.get(1));
            tv_item_name_3.setText(itemNameList.get(2));
            tv_item_name_4.setText(itemNameList.get(3));
        } else if (size > 2) {
            tv_item_name_1.setText(itemNameList.get(0));
            tv_item_name_2.setText(itemNameList.get(1));
            tv_item_name_3.setText(itemNameList.get(2));
        } else {
            tv_item_name_1.setText(itemNameList.get(0));
            tv_item_name_2.setText(itemNameList.get(1));
        }
        tv_title.setText(itemNameList.get(0));
//        tv_item_name_1.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.icon_lwsb1), null, null, null);
//        tv_item_name_2.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.icon_bj), null, null, null);
//        tv_item_name_3.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.icon_gzsb), null, null, null);
//        tv_item_name_1.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.icon_gzsb), null, null, null);

        tv_item_name_1.setBackgroundColor(getResources().getColor(R.color.list_divider));
        tv_item_name_2.setBackgroundColor(getResources().getColor(R.color.white));
        tv_item_name_3.setBackgroundColor(getResources().getColor(R.color.white));
        tv_item_name_4.setBackgroundColor(getResources().getColor(R.color.white));
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void onStart() {
        if (itemNameList.size() > 0) {
            tv_title.setText(itemNameList.get(0));
        }
        tv_item_name_1.setBackgroundColor(getResources().getColor(R.color.list_divider));
        tv_item_name_2.setBackgroundColor(getResources().getColor(R.color.white));
        tv_item_name_3.setBackgroundColor(getResources().getColor(R.color.white));
        tv_item_name_4.setBackgroundColor(getResources().getColor(R.color.white));
        super.onStart();
    }

    //初始化默认fragment的加载
    private void initDefaultFragment(String url) {
        tv_item_name_1.setBackgroundColor(getResources().getColor(R.color.list_divider));
        tv_item_name_2.setBackgroundColor(getResources().getColor(R.color.white));
        tv_item_name_3.setBackgroundColor(getResources().getColor(R.color.white));
        tv_item_name_4.setBackgroundColor(getResources().getColor(R.color.white));
        loadWebH5(url);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void disposeCommonEvent(CommonEvent ecEvent) {
        switch (ecEvent.getWhat()) {
            case Constans.WEBH5SUCESS:
                url = (String) ecEvent.getData();
                loadWebH5(url);
                break;
        }
    }

    @Override
    protected void setLisenter() {
        tv_item_name_1.setOnClickListener(this);
        tv_item_name_2.setOnClickListener(this);
        tv_item_name_3.setOnClickListener(this);
        tv_item_name_4.setOnClickListener(this);
    }

    @Override
    protected void widgetClick(View v) {
        int size = itemNameList.size();
        switch (v.getId()) {
            case R.id.tv_item_name_1:
                if (size > 0) {
                    tv_title.setText(itemNameList.get(0));
                }
                tv_item_name_1.setBackgroundColor(getResources().getColor(R.color.list_divider));
                tv_item_name_2.setBackgroundColor(getResources().getColor(R.color.white));
                tv_item_name_3.setBackgroundColor(getResources().getColor(R.color.white));
                tv_item_name_4.setBackgroundColor(getResources().getColor(R.color.white));
                url = "";
                url = WebStringUtils.getUrlByName(title, Constant.FISRT, "");
                EventBus.getDefault().post(new CommonEvent(Constans.WEBH5SUCESS, url));
                break;
            case R.id.tv_item_name_2:
                if (size > 1) {
                    tv_title.setText(itemNameList.get(1));
                }
                tv_item_name_1.setBackgroundColor(getResources().getColor(R.color.white));
                tv_item_name_2.setBackgroundColor(getResources().getColor(R.color.list_divider));
                tv_item_name_3.setBackgroundColor(getResources().getColor(R.color.white));
                tv_item_name_4.setBackgroundColor(getResources().getColor(R.color.white));
                url = "";
                url = WebStringUtils.getUrlByName(title, Constant.SECOND, "");
                if (url.equals(Constant.XIAOFANGXUNJIAN)) {
                    Intent intent = new Intent(WebH5Activity.this, FireInspectionActivity.class);
                    super.startActivity(intent);
                } else if (url.equals(Const.GO_RECTIFICATION)) {
                    Intent intent = new Intent(WebH5Activity.this, RectificationDocumentActivity.class);
                    super.startActivity(intent);
                } else {
                    EventBus.getDefault().post(new CommonEvent(Constans.WEBH5SUCESS, url));
                }

                break;
            case R.id.tv_item_name_3:
                if (size > 2) {
                    tv_title.setText(itemNameList.get(2));
                }
                tv_item_name_1.setBackgroundColor(getResources().getColor(R.color.white));
                tv_item_name_2.setBackgroundColor(getResources().getColor(R.color.white));
                tv_item_name_3.setBackgroundColor(getResources().getColor(R.color.list_divider));
                tv_item_name_4.setBackgroundColor(getResources().getColor(R.color.white));
                url = "";
                url = WebStringUtils.getUrlByName(title, Constant.THIRD, "");
                if (url.equals(Constant.ZIZHIGUANGLI)) {
                    Intent intent = new Intent(WebH5Activity.this, DecumentManageActivity.class);
                    super.startActivity(intent);
                } else {
                    EventBus.getDefault().post(new CommonEvent(Constans.WEBH5SUCESS, url));
                }
                break;

            case R.id.tv_item_name_4:
                if (size > 3) {
                    tv_title.setText(itemNameList.get(3));
                }
                tv_item_name_1.setBackgroundColor(getResources().getColor(R.color.white));
                tv_item_name_2.setBackgroundColor(getResources().getColor(R.color.white));
                tv_item_name_3.setBackgroundColor(getResources().getColor(R.color.white));
                tv_item_name_4.setBackgroundColor(getResources().getColor(R.color.list_divider));
                url = "";
                url = WebStringUtils.getUrlByName(title, Constant.THIRD, "");
                EventBus.getDefault().post(new CommonEvent(Constans.WEBH5SUCESS, url));
                break;
        }
    }


    private void loadWebH5(String SERVICE_URL) {
        boolean networkConnected = MyApplication.getInstance().isNetworkConnected();
        if (!networkConnected) {
            showErrorToast("网络不可用，请检查!");
            return;
        }
        boolean hasUserInfo = UserManage.getInstance().hasUserInfo(Global.mContext);
        if (hasUserInfo) {
            UserInfo userIdInfo = UserManage.getInstance().getUserIdInfo(Global.mContext);
            userid = userIdInfo.getUserid();
            pid = userIdInfo.getPid();
            if (TextUtils.isEmpty(userid) && TextUtils.isEmpty(pid)) {
                showErrorToast(getString(R.string.login_again));
                return;
            }
        }

        SERVICE_URL = SERVICE_URL + "?id=" + userid + "&organid=" + pid;
        //自适应屏幕
        if (webSettings == null) {
            webSettings = webView.getSettings();
            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
            webSettings.setLoadWithOverviewMode(true);
            webSettings.setUseWideViewPort(true);
        }
        if (TextUtils.isEmpty(SERVICE_URL)) {
            return;
        }
        Log.d("tag", "loadWebH5: " + SERVICE_URL);

        if (webViewUtil == null) {
            webViewUtil = new WebViewUtils(WebH5Activity.this, SERVICE_URL, webView, mWebProgressbar);
            webViewUtil.initWebView();
            webViewUtil.getH5JsInterface().registerListener(this);
        } else {
            webViewUtil.initWebView(SERVICE_URL, webView, mWebProgressbar);
        }
    }


    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (webView == null) {
            return false;
        }
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack(); //goBack()表示返回WebView的上一页面
            return true;
        }
        return false;
    }

    @Override
    public void onPause() {
        if (webView != null) {
            webView.reload();
        }
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (webView != null) {
            webView.reload();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        // 一定要调用此方法，才能选择下一个文件预览
        // 否则显示loading而不展示
        // 适当的位置调用此方法
        if (readerView != null) {
            readerView.onStop();
        }
        if (webViewUtil != null) {
            webViewUtil.getH5JsInterface().unRegisterListener();
        }
    }

    @Override
    public void H5ControlAndroidEvent(String url, Bundle bundle) {

    }

    @Override
    public void H5ControlAndroidEvent() {
//        webView.loadUrl("javascript:" + "echo('aaaaaaaaaaaa')");
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        WebH5Activity.this.startActivityForResult(intent, 1);
    }

    //文件路径
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {//是否选择，没选择就不会继续
            try {
                Uri uri = data.getData();//得到uri，后面就是将uri转化成file的过程。
                String url = FileUtils2.getPath(WebH5Activity.this, uri);
                String url2 = url.trim();
                Log.d("文件路径--", url2 + "");
                UploadFile(url2);
            } catch (Exception e) {
                e.printStackTrace();
            }
//            String[] proj = {MediaStore.Images.Media.DATA};
//            Cursor actualimagecursor = managedQuery(uri, proj, null, null, null);
//            int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//            actualimagecursor.moveToFirst();
//            String img_path = actualimagecursor.getString(actual_image_column_index);
//            File file = new File(img_path);
//            Toast.makeText(Activity_Deatail.this, file.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void UploadFile(String url) {
        File file = new File(url);
        OkHttpUtils.post(ApiService.uploadfile)
                .params("file", file)
                .execute(new StringCallback() {

                    private FileBean fileBean;
                    @Override
                    public void onSuccess(String s, okhttp3.Call call, Response response) {
                        Log.d("tag", "onSuccess: " + s);
                        if (!TextUtils.isEmpty(s)) {
                            fileBean = GsonTools.changeGsonToBean(s, FileBean.class);
                        }
                        if (webView != null) {
                            webView.loadUrl("javascript:" + "echo('" + fileBean.getMessage() + "')");
                        }
                    }

                    @Override
                    public void onError(okhttp3.Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        showErrorToast("文件选择失败，请重新选择文件");
                    }
                });
    }
}
