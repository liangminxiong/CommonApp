package com.mvp_0726.project_0726.utils;

import android.content.Context;
import android.text.TextUtils;

import com.mvp_0726.project_0726.constant.Constant;
import com.project.wisdomfirecontrol.R;
import com.project.wisdomfirecontrol.common.base.Global;
import com.project.wisdomfirecontrol.common.base.UserInfo;
import com.project.wisdomfirecontrol.common.base.UserManage;

/**
 * Created by Administrator on 2018/8/10.
 */

public class StringUtils {

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

    /*监管*/
    public static String getItemNameSuper(String name) {
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
