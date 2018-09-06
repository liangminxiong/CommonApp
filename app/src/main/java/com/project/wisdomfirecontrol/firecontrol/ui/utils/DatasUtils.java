package com.project.wisdomfirecontrol.firecontrol.ui.utils;

import android.content.Context;
import android.text.TextUtils;

import com.project.wisdomfirecontrol.R;
import com.project.wisdomfirecontrol.common.base.Global;
import com.project.wisdomfirecontrol.common.base.UserInfo;
import com.project.wisdomfirecontrol.common.base.UserManage;
import com.project.wisdomfirecontrol.firecontrol.model.bean.VideoEquipmentBean;
import com.project.wisdomfirecontrol.firecontrol.treesList.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/14.
 */

public class DatasUtils {

    public static List<Node> ReturnTreesDatas(List<VideoEquipmentBean.DataBean> beanList) {

        List<Node> mDatas = new ArrayList<Node>();

        String orgName = beanList.get(0).getOrgName();

        // id , pid , label , 其他属性
        mDatas.add(new Node("1", "10000", orgName, -1, 14));

        mDatas.add(new Node(2 + "", 1 + "", "游戏", -1, 12));
        mDatas.add(new Node(3 + "", 1 + "", "文档", -1, 0));
        mDatas.add(new Node(4 + "", 1 + "", "程序", -1, 0));
        mDatas.add(new Node(5 + "", 2 + "", "war3", 2, 0));
        mDatas.add(new Node(6 + "", 2 + "", "刀塔传奇", -1, 11));

        mDatas.add(new Node(17 + "", 6 + "", "adfadsfas", 1, 1));

        mDatas.add(new Node(110 + "", 17 + "", "aaaa", 2, 0));

        mDatas.add(new Node(18 + "", 6 + "", "投资", -1, 10));
        mDatas.add(new Node(19 + "", 18 + "", "阿发达是否", -1, 10));
        for (int i = 0; i < 10; i++) {
            Node node = new Node("999" + i, 19 + "", "afffffffff ++= " + i, 2, 0);
            mDatas.add(node);
        }

        return mDatas;
    }

    public static String getUserId(Context context) {
        String userid = "";
        boolean hasUserInfo = UserManage.getInstance().hasUserInfo(Global.mContext);
        if (hasUserInfo) {
            UserInfo userIdInfo = UserManage.getInstance().getUserIdInfo(Global.mContext);
            userid = userIdInfo.getUserid();
            if (TextUtils.isEmpty(userid)) {
                Global.showToast(context.getResources().getString(R.string.login_again));
            } else {
                return userid;
            }
        }
        return userid;
    }

    public static String getUserPid(Context context) {
        String pid = "";
        boolean hasUserInfo = UserManage.getInstance().hasUserInfo(Global.mContext);
        if (hasUserInfo) {
            UserInfo userIdInfo = UserManage.getInstance().getUserIdInfo(Global.mContext);
            pid = userIdInfo.getPid();
            if (TextUtils.isEmpty(pid)) {
                Global.showToast(context.getResources().getString(R.string.login_again));
            } else {
                return pid;
            }
        }
        return pid;
    }

    public static UserInfo getUserInfos(Context context) {
        UserInfo userIdInfo = null;
        boolean hasUserInfo = UserManage.getInstance().hasUserInfo(Global.mContext);
        if (hasUserInfo) {
            userIdInfo = UserManage.getInstance().getUserInfo(Global.mContext);
            return userIdInfo;
        } else {
            Global.showToast(context.getResources().getString(R.string.login_again));
        }
        return userIdInfo;
    }
}
