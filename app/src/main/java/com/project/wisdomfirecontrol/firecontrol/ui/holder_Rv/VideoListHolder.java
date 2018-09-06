package com.project.wisdomfirecontrol.firecontrol.ui.holder_Rv;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.wisdomfirecontrol.R;
import com.project.wisdomfirecontrol.common.base.Global;
import com.project.wisdomfirecontrol.common.ui.BaseAdapterLV;
import com.project.wisdomfirecontrol.common.ui.BaseHolderLV;
import com.project.wisdomfirecontrol.firecontrol.model.bean.VideoEquipmentBean;

/**
 * Created by Administrator on 2018/4/28.
 */

public class VideoListHolder extends BaseHolderLV<VideoEquipmentBean.DataBean.VideoesBean> {

    private TextView tv_item_sytem_name;

    public VideoListHolder(Context context, ViewGroup parent,
                           BaseAdapterLV<VideoEquipmentBean.DataBean.VideoesBean> adapter,
                           int position, VideoEquipmentBean.DataBean.VideoesBean bean) {
        super(context, parent, adapter, position, bean);
    }

    @Override
    public View onCreateView(Context context, ViewGroup parent) {
        View view = Global.inflate(R.layout.item_lv_sytemlist, parent);
        tv_item_sytem_name = view.findViewById(R.id.tv_item_sytem_name);
        tv_item_sytem_name.setTextColor(context.getResources().getColor(R.color.black));
        return view;
    }

    @Override
    protected void onRefreshView(VideoEquipmentBean.DataBean.VideoesBean bean, int position) {
        if (!TextUtils.isEmpty(bean.getName())) {
            tv_item_sytem_name.setText(bean.getName());
        }
    }
}
