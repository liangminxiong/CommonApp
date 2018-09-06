package com.project.wisdomfirecontrol.firecontrol.ui.adapter_Rv;

import android.content.Context;
import android.view.ViewGroup;

import com.project.wisdomfirecontrol.common.ui.BaseAdapterLV;
import com.project.wisdomfirecontrol.common.ui.BaseHolderLV;
import com.project.wisdomfirecontrol.firecontrol.model.bean.VideoEquipmentBean;
import com.project.wisdomfirecontrol.firecontrol.ui.holder_Rv.VideoListHolder;

import java.util.List;

/**
 * Created by Administrator on 2018/4/28.
 */

public class VideoListAdapter extends BaseAdapterLV<VideoEquipmentBean.DataBean.VideoesBean>{
    public VideoListAdapter(Context context, List<VideoEquipmentBean.DataBean.VideoesBean> listData) {
        super(context, listData);
    }

    @Override
    public BaseHolderLV<VideoEquipmentBean.DataBean.VideoesBean> createViewHolder
            (Context context, ViewGroup parent, VideoEquipmentBean.DataBean.VideoesBean bean, int position) {
        return new VideoListHolder(context,parent,this,position,bean);
    }
}
