package com.mvp_0726.common.view.other;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.RelativeLayout;

import com.mvp_0726.common.utils.StatusBarUtil;
import com.project.wisdomfirecontrol.R;


public class BaseHomeTitleBar extends RelativeLayout {

    private View viespace;
    private RelativeLayout relativeLayout;
    private float bgAlpha;
    private Interpolator mInterpolator = new DecelerateInterpolator(3);

    public BaseHomeTitleBar(Context context) {
        super(context);
    }

    public BaseHomeTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseHomeTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }

    private void initView() {
        viespace = findViewById(R.id.view_space);
        StatusBarUtil.setFadeStatusBarHeight(getContext(), viespace);
        relativeLayout = findViewById(R.id.rly_home_title);
        setBgAlpha(0);
    }

    public void setBgAlpha(float bgAlpha) {
        this.bgAlpha = mInterpolator.getInterpolation(bgAlpha);
        viespace.setAlpha(bgAlpha);
        relativeLayout.setAlpha(bgAlpha);
    }
}
