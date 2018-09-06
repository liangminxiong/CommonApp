package com.project.wisdomfirecontrol.firecontrol.ui.activity_video;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.project.wisdomfirecontrol.R;
import com.project.wisdomfirecontrol.common.base.BaseActivity;
import com.project.wisdomfirecontrol.common.base.Global;
import com.project.wisdomfirecontrol.common.base.UserManage;
import com.project.wisdomfirecontrol.firecontrol.model.bean.VideoEquipmentBean;
import com.project.wisdomfirecontrol.firecontrol.model.protocol.CommonProtocol;
import com.project.wisdomfirecontrol.firecontrol.treesList.Node;
import com.project.wisdomfirecontrol.firecontrol.treesList.OnTreeNodeClickListener;
import com.project.wisdomfirecontrol.firecontrol.treesList.adapter.SimpleTreeRecyclerAdapter;
import com.project.wisdomfirecontrol.firecontrol.ui.adapter_Rv.VideoListAdapter;
import com.project.wisdomfirecontrol.firecontrol.ui.utils.DatasUtils;
import com.project.wisdomfirecontrol.firecontrol.ui.utils.Unit_StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/4/28.
 */

public class VideoSytemListActivity extends BaseActivity {

    private static final String TAG = "tag";
    private TextView tv_title, tv_company_name;
    private ListView lv_videolist;
    private List<String> videoSytemList;
    private VideoListAdapter adapter;
    private String pid;
    private List<VideoEquipmentBean.DataBean.VideoesBean> videoes;

    private RecyclerView recyclerview;
    private int tempCount;
    private String type;
    private SimpleTreeRecyclerAdapter mAdapter;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_videosytemlist;
    }

    @Override
    public void initView() {
        tv_title = findView(R.id.tv_title);
        tv_company_name = findView(R.id.tv_company_name);
        lv_videolist = findView(R.id.lv_videolist);
        recyclerview = findView(R.id.recyclerview);

        tv_title.setText("视频设备列表");
        tv_company_name.setTextColor(getResources().getColor(R.color.black));
        type = UserManage.getInstance().getUserType(Global.mContext).getType();
        if (type.contains("1")) {
            lv_videolist.setVisibility(View.GONE);
            recyclerview.setVisibility(View.VISIBLE);
        } else {
            lv_videolist.setVisibility(View.VISIBLE);
            recyclerview.setVisibility(View.GONE);
        }
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

        getEquipmentCount();
    }

    @Override
    public void onClick(View v, int id) {

    }

    //    获取条数
    private void getEquipmentCount() {
        CommonProtocol commonProtocol = new CommonProtocol();
        pid = Unit_StringUtils.getUserPid(Global.mContext);
        showWaitDialog(VideoSytemListActivity.this, getResources().getString(R.string.inupdate));
        commonProtocol.getvideoequipment(this, pid, type);
    }

    @Override
    public void onHttpSuccess(int reqType, Message obj) {
        super.onHttpSuccess(reqType, obj);
        dismissWaitDialog();
        VideoEquipmentBean bean = (VideoEquipmentBean) obj.obj;
        List<VideoEquipmentBean.DataBean> beanList = bean.getData();
        if (beanList.size() > 0) {
            if (type.contains("1")){
                initRVdatas(beanList);
            }else {
                showTreesList(beanList);
            }
        } else {
            tv_company_name.setText(getResources().getString(R.string.no_datas));
        }

    }

    private void initRVdatas(List<VideoEquipmentBean.DataBean> beanList) {
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
//        //第一个参数  RecyclerView
//        //第二个参数  上下文
//        //第三个参数  数据集
//        //第四个参数  默认展开层级数 0为不展开
//        //第五个参数  展开的图标
//        //第六个参数  闭合的图标
        List<Node> mDatas = DatasUtils.ReturnTreesDatas(beanList);

        mAdapter = new SimpleTreeRecyclerAdapter(recyclerview, this,
                mDatas, 0, R.mipmap.tree_ex,R.mipmap.tree_ec);

        recyclerview.setAdapter(mAdapter);

        mAdapter.setOnTreeNodeClickListener(new OnTreeNodeClickListener() {
            @Override
            public void onClick(Node node, int position) {
                showSelectItemDatas();
            }

        });
    }

    private void showSelectItemDatas() {
        String name = "";
        final List<Node> allNodes = mAdapter.getAllNodes();
        for (int i = 0; i < allNodes.size(); i++) {
            if (allNodes.get(i).isChecked()) {
                name = allNodes.get(i).getName();
            }
        }
        if (!TextUtils.isEmpty(name)) {
            Global.showToast(name);
        }
    }


    @SuppressLint("SetTextI18n")
    private void showTreesList(List<VideoEquipmentBean.DataBean> beanList) {
        if (beanList.size() > 0) {
            for (int i = 0; i < beanList.size(); i++) {
                videoes = beanList.get(i).getVideoes();
            }
        }

        String orgName = beanList.get(0).getOrgName();

        if (videoes.size() <= 0) {
            tempCount = 0;
        } else {
            tempCount = videoes.size();

            adapter = new VideoListAdapter(VideoSytemListActivity.this, videoes);
            lv_videolist.setAdapter(adapter);
            lv_videolist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent();
                    intent.setClass(VideoSytemListActivity.this, VideoCameraActivity.class);
                    Bundle bundle = new Bundle();
                    Log.d(TAG, "onItemClick: " + videoes.get(position).getTerminalno());
                    bundle.putString("tp", videoes.get(position).getTerminalno());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }
        tv_company_name.setText(orgName + "（" + tempCount + "）");
    }

    @Override
    public void onHttpError(int reqType, String error) {
        super.onHttpError(reqType, error);
        dismissWaitDialog();
        showToast(error);
    }
}
