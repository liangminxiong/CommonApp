package com.mvp_0726.common.view.dialog;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.project.wisdomfirecontrol.R;

/**
 加载
 */

public class LoadingDialog extends Dialog {

    private ProgressBar progressbar;
    private TextView tv_txt;

    public LoadingDialog(@NonNull Context context) {

        super(context, R.style.DialogStyle_white);
        setContentView(R.layout.dialog_loading);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setCancelable(true);
        initView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        this.getWindow().setDimAmount(0f);
    }

    public void setMessage(String txt){
        this.tv_txt.setText(txt);
    }

    private void initView() {
        progressbar = findViewById(R.id.progressbar);
        tv_txt = findViewById(R.id.tv_txt);
       /* cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deletaCacheListener != null) {
                    deletaCacheListener.cancle();
                }
                dismiss();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deletaCacheListener != null) {
                    deletaCacheListener.sure();
                }
                dismiss();
            }
        });*/
    }

    /*private DeletaCacheListener deletaCacheListener;

    public void setDeletaCacheListener(DeletaCacheListener listener) {
        this.deletaCacheListener = listener;
    }

    public interface DeletaCacheListener {
        void sure();

        void cancle();
    }*/
}
