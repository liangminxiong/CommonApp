package com.mvp_0726.project_0726.web.utli;

import com.mvp_0726.project_0726.constant.Constant;
import com.project.wisdomfirecontrol.common.base.Const;
import com.project.wisdomfirecontrol.firecontrol.model.protocol.IHttpService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/8/8.
 */

public class WebStringUtils {

    /*h5链接url*/
    public static String getUrlByName(String name, int countNumber, String newAdd) {
        String url = "";
        if (name.equals(Constant.GONGGONGZIYUAN)) {//公共资源
            if (countNumber == Constant.FISRT) {
                url = IHttpService.FIREALARM_URL_UNIT;
            } else {
                url = IHttpService.FIREALARM_BAD_URL_UNIT;
            }
        } else if (name.equals(Constant.TONGJIFENXI)) {//统计分析
            if (countNumber == Constant.FISRT) {
//                url = IHttpService.DEVICEORDER_URL_UNIT;
//            } else if (countNumber == Constant.SECOND) {
//                url = IHttpService.ATOUMATION_ANALYZE_URL_UNIT;
//            } else {
//                url = IHttpService.FIREALARM_ANALYZE_URL_UNIT;
                url = IHttpService.TONGJIFENFX;
            }
        } else if (name.equals(Constant.XIAOFANGPINGJI)) {//消防评级
            if (countNumber == Constant.FISRT) {
                url = IHttpService.DEVICEORDER_ANALYZE_URL_UNIT;
            } else if (countNumber == Constant.SECOND) {
                url = IHttpService.NET_YEAR_SAFETY_URL_UNIT;
            } else {
                url = IHttpService.NET_MOUTH_SAFETY_URL_UNIT;
            }
        } else if (name.equals(Constant.HISTORY_RECOIDING)) {//历史记录
            if (countNumber == Constant.FISRT) {
//                url = IHttpService.ATOUMATION_ANALYZE_URL;
//            } else if (countNumber == Constant.SECOND) {
//                url = IHttpService.FIREALARM_ANALYZE_URL;
//            } else {
//                url = IHttpService.DEVICEORDER_ANALYZE_URL;
                url = IHttpService.HOSTORY_RECORD;
            }
        } else if (name.equals(Constant.SAFE_DENGJI)) {//安全等级
            if (countNumber == Constant.FISRT) {
                url = IHttpService.NET_YEAR_SAFETY_URL;
            } else if (countNumber == Constant.SECOND) {
                url = IHttpService.NET_MOUTH_SAFETY_URL;
            } else {
                url = Constant.ZIZHIGUANGLI;//跳转资质管理
            }
        } else if (name.equals(Constant.SAFE_PERSONAL)) {//安全人员
            if (countNumber == Constant.FISRT) {
                url = IHttpService.WATCH_KEEPER_URL;
            } else {
                url = Constant.XIAOFANGXUNJIAN;//跳转消防巡检
            }
        } else if (name.equals(Constant.XINGZHENGGONGWEN)) {//行政公文

            if (countNumber == Constant.FISRT) {
                url = IHttpService.FIRE_DECUMENT_MANAGE_URL_UNIT;
            } else if (countNumber == Constant.SECOND) {
                url = IHttpService.WATCH_KEEPER_URL_UNIT;
            } else if (countNumber == Constant.THIRD) {
                url = IHttpService.FIRE_INSPECTION_URL_UNIT;
            } else {
                url = IHttpService.INSPECTION_RECORD_URL_UNIT;
            }
        } else if (name.equals(Constant.XINGZHENGGONGWEN_NEWADD)) {//行政公文 新增
            if (countNumber == Constant.FISRT) {
                url = IHttpService.RECTIFICATION_DOCUMENT_URL;
            } else {
                url = Const.GO_RECTIFICATION;
            }
        }

        return url;
    }


    /*item Name*/
    public static List<String> getItemName(String name) {
        List<String> list = new ArrayList<>();
        list.clear();
        if (name.equals(Constant.GONGGONGZIYUAN)) {//公共资源
            list.add("联网资源");
            list.add("故障资源");
        } else if (name.equals(Constant.TONGJIFENXI)) {//统计分析
            list.add("统计分析");
            list.add("统计分析");
            list.add("统计分析");
            /*list.add("联网单位");
            list.add("消防报警");
            list.add("设备故障");*/
        } else if (name.equals(Constant.XIAOFANGPINGJI)) {//消防评级
            list.add("年度等级");
            list.add("月度等级");
            list.add("月度趋势");
        } else if (name.equals(Constant.HISTORY_RECOIDING)) {//历史记录
            list.add("历史记录");
            list.add("历史记录");
            list.add("历史记录");
            /*  list.add("消防监控");
            list.add("报警设备");
            list.add("故障设备");*/
        } else if (name.equals(Constant.SAFE_DENGJI)) {//安全等级
            list.add("年度等级");
            list.add("月度等级");
            list.add("资质管理");
        } else if (name.equals(Constant.SAFE_PERSONAL)) {//安全人员
            list.add("值班人员");
            list.add("安全巡检");
            list.add("资质管理");
        } else if (name.equals(Constant.XINGZHENGGONGWEN)) {//行政公文

            list.add("通知公文");
            list.add("整改公文");
            list.add("通知列表");
            list.add("整改列表");
        } else if (name.equals(Constant.XINGZHENGGONGWEN_NEWADD)) {
            list.add("消防通知");
            list.add("消防整改");
        }

        return list;
    }
}
