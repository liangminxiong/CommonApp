package com.mvp_0726.project_0726.home.adapter;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mvp_0726.common.utils.GlideUtils;
import com.project.wisdomfirecontrol.R;
import com.project.wisdomfirecontrol.firecontrol.model.bean.login.MenuDatasBean;

import java.util.List;

/**
 * Created by Administrator on 2018/7/30.
 */

public class HomeDatasChangeAdapter extends BaseItemDraggableAdapter<MenuDatasBean, BaseViewHolder> {

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
    }

}
