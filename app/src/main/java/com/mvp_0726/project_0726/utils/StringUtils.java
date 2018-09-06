package com.mvp_0726.project_0726.utils;

import android.content.Context;
import android.text.TextUtils;

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
}
