package com.mvp_0726.project_0726.home.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mvp_0726.common.utils.GlideUtils;
import com.mvp_0726.project_0726.constant.Constant;
import com.mvp_0726.project_0726.online_unit.ui.activity.SettingPoliceOnlineActivity;
import com.mvp_0726.project_0726.web.ui.WebH5Activity;
import com.project.wisdomfirecontrol.R;
import com.project.wisdomfirecontrol.common.base.Const;
import com.project.wisdomfirecontrol.common.base.Global;
import com.project.wisdomfirecontrol.firecontrol.model.bean.login.MenuDatasBean;
import com.project.wisdomfirecontrol.firecontrol.ui.activity_common.OnlineUnitActivity;
import com.project.wisdomfirecontrol.firecontrol.ui.activity_common.SystemSettingActivity;
import com.project.wisdomfirecontrol.firecontrol.ui.activity_setting.SettingManagerActivity;
import com.project.wisdomfirecontrol.firecontrol.ui.activity_video.VideoSytemListActivity;

import java.util.List;

/**
 * Created by Administrator on 2018/7/30.
 */

public class HomeDatasChangeAdapter extends BaseItemDraggableAdapter<MenuDatasBean, BaseViewHolder> {

    private RecyclerView recyclerview;
    private TextView tv_item_type;
    private Intent intent;
    private String INTENT_VALUE = "";

    private TextView tv_item_name;
    private LinearLayout layout_item_home_item;
    private RelativeLayout rl_home_item;
    private ImageView iv_item_images;
    private String name;

    public HomeDatasChangeAdapter(int layoutResId, @Nullable List<MenuDatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final MenuDatasBean item) {
        layout_item_home_item = helper.getView(R.id.layout_item_home_item);
        rl_home_item = helper.getView(R.id.rl_home_item);
        tv_item_name = helper.getView(R.id.tv_item_name);
        iv_item_images = helper.getView(R.id.iv_item_images);
        tv_item_name.setTextSize(13f);
        if (item != null) {
            ViewGroup.LayoutParams params = layout_item_home_item.getLayoutParams();
            params.height = (int) com.mvp_0726.common.utils.Global.mScreenWidth / 4;
            params.width = (int) com.mvp_0726.common.utils.Global.mScreenWidth / 4;
            layout_item_home_item.setLayoutParams(params);

            ViewGroup.LayoutParams params_rl = rl_home_item.getLayoutParams();
            if (com.mvp_0726.common.utils.Global.isPad()) {
                params_rl.height = (int) com.mvp_0726.common.utils.Global.mScreenWidth / 16;
                params_rl.width = (int) com.mvp_0726.common.utils.Global.mScreenWidth / 16;
            } else {
                params_rl.height = (int) com.mvp_0726.common.utils.Global.mScreenWidth / 11;
                params_rl.width = (int) com.mvp_0726.common.utils.Global.mScreenWidth / 10;
            }

            rl_home_item.setLayoutParams(params_rl);
            name = item.getName();
            helper.setText(R.id.tv_item_name, name)
                    .setTextColor(R.id.tv_item_name, mContext.getResources().getColor(R.color.mvp_home_item_txt))
                    .addOnClickListener(R.id.layout_item_home_item);
            String imagePath = item.getImagePath();
            Log.d(TAG, "convert: " + imagePath);
            GlideUtils.loadImageviewGlideAPP(mContext, iv_item_images, imagePath, R.drawable.icon_common, R.drawable.image_login);
        }
        layout_item_home_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: " + name);
                switch (name) {
                    case Constant.XIAQU_UNIT://辖区单位
                        intent = new Intent(mContext, OnlineUnitActivity.class);
                        mContext.startActivity(intent);
                        break;
                    case Constant.SYSTEMMAIN://系统维护
                        intent = new Intent(mContext, SystemSettingActivity.class);
//                        intent = new Intent(mContext, TestActivity.class);
                        mContext.startActivity(intent);
                        break;
                    case Constant.SETTINGMANAGE://设备管理
                        intent = new Intent(mContext, SettingManagerActivity.class);
                        mContext.startActivity(intent);
                        break;
                    case Constant.VEDIO_LOOKING://视频
                        intent = new Intent(mContext, VideoSytemListActivity.class);
                        mContext.startActivity(intent);
                        break;
                    case Constant.ONLINE_UNIT:////联网单位
                        INTENT_VALUE = Const.GO_SETTINGONLINE_FIRST;
                        intent = new Intent(mContext, SettingPoliceOnlineActivity.class);
                        intent.putExtra(Constant.INTENT_KEY, INTENT_VALUE);
                        mContext.startActivity(intent);
                        break;
                    case Constant.XIAOFANGBAOJING:////联网单位
                        INTENT_VALUE = Const.GO_SETTINGONLINE_SECOND;
                        intent = new Intent(mContext, SettingPoliceOnlineActivity.class);
                        intent.putExtra(Constant.INTENT_KEY, INTENT_VALUE);
                        mContext.startActivity(intent);
                        break;
                    case Constant.SHEBEIGUZHANG:////联网单位
                        INTENT_VALUE = Const.GO_SETTINGONLINE_THIRD;
                        intent = new Intent(mContext, SettingPoliceOnlineActivity.class);
                        intent.putExtra(Constant.INTENT_KEY, INTENT_VALUE);
                        mContext.startActivity(intent);
                        break;
                    default:
//                        Global.showToast("正在开发...");
                        INTENT_VALUE = getItemNameSuper(name);
                        if (INTENT_VALUE.equals("正在开发...")) {
                            Global.showToast(INTENT_VALUE);
                            return;
                        } else if (INTENT_VALUE.equals(Constant.XINGZHENGGONGWEN)) {
//                            Global.showToast("正在开发...");
                            List<?> menuDatas = item.getMenuDatas();
                            if (menuDatas.size() > 0) {//两个
                                INTENT_VALUE = Constant.XINGZHENGGONGWEN_NEWADD;
                            }
                        }
                        intent = new Intent(new Intent(mContext, WebH5Activity.class));
                        intent.putExtra(Constant.INTENT_KEY, INTENT_VALUE);
                        mContext.startActivity(intent);
//
                        break;
                }
            }
        });

    }

    /*监管*/
    private String getItemNameSuper(String name) {
        String itemName = "";
        if (name.equals(Constant.GONGGONGZIYUAN)) {//公共资源
            itemName = Constant.GONGGONGZIYUAN;
        } else if (name.equals(Constant.TONGJIFENXI)) {//统计分析
            itemName = Constant.TONGJIFENXI;
        } else if (name.equals(Constant.XIAOFANGPINGJI)) {//消防评级
            itemName = Constant.XIAOFANGPINGJI;
        } else if (name.equals(Constant.HISTORY_RECOIDING)) {//历史记录
            itemName = Constant.HISTORY_RECOIDING;
        } else if (name.equals(Constant.SAFE_DENGJI)) {//安全等级
            itemName = Constant.SAFE_DENGJI;
        } else if (name.equals(Constant.SAFE_PERSONAL)) {//安全人员
            itemName = Constant.SAFE_PERSONAL;
        } else if (name.equals(Constant.XINGZHENGGONGWEN)) {//行政公文
            itemName = Constant.XINGZHENGGONGWEN;
        } else {
            itemName = "正在开发...";
        }

        return itemName;
    }

}
