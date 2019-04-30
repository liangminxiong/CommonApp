package com.mvp_0726.common.network;

import com.mvp_0726.project_0726.bean.settingpolice.GetsensorObdSuccessBean;
import com.mvp_0726.project_0726.home.model.GridCountBean;
import com.mvp_0726.project_0726.home.model.MarqueeBean;
import com.mvp_0726.project_0726.home.model.OrgandetailBean;
import com.mvp_0726.project_0726.login.modle.ChangePwdBean;
import com.mvp_0726.project_0726.web.model.GetSumTypeBean;
import com.project.wisdomfirecontrol.firecontrol.model.bean.login.LoginChangeBean;
import com.project.wisdomfirecontrol.firecontrol.model.bean.other.EquipmentCount;
import com.project.wisdomfirecontrol.firecontrol.model.bean.other.GetSenorcountBean;
import com.project.wisdomfirecontrol.firecontrol.model.bean.other.SubmitBean;
import com.project.wisdomfirecontrol.firecontrol.model.bean.setting.SettingManagerBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created  on 2018-03-19.
 * author:seven
 * email:seven2016s@163.com
 */

public interface ApiService {

    String uploadfile = "http://www.zgjiuan.cn/zgbd/file/fileUploadStatus";
    String HOST_IP = "zgbd_fireControl/h5/";
    String HOST_FIRECONTROL = "zgbd_fireControl/application/message/";

    /*登录*/
    @Headers({"urlname:zgjiuan"})
    @FormUrlEncoded
    @POST("h5/login.action")
    Observable<LoginChangeBean> login(@Field("username") String username, @Field("password") String password);

    /*获取公司注册信息*/
    @Headers({"urlname:zgjiuan"})
    @FormUrlEncoded
    @POST("h5/getorgandetailbyid.action")
    Observable<OrgandetailBean> getorgandetailbyid(@Field("id") String id);

    /*设备报警，联网设备，设备故障数量*/
    @Headers({"urlname:zgjiuan"})
    @FormUrlEncoded
    @POST("h5/getequipmentcount.action")
    Observable<EquipmentCount> getequipmentcount(@Field("pid") String pid);

    /*轮播设备报警数量*/
    @Headers({"urlname:zgjiuan"})
    @FormUrlEncoded
    @POST("h5/getSensorsAlarm.action")
    Observable<MarqueeBean> getMarqueeDatas(@Field("pid") String pid);

    /*设备报警，联网设备，设备故障数量*/
    @Headers({"urlname:zgjiuan"})
    @FormUrlEncoded
    @POST(HOST_IP + "getsenorcount.action")
    Observable<GetSenorcountBean> getSenorcount(
            @Field("pid") String pid, @Field("state") String state);

    @Headers({"urlname:zgjiuan"})
    @FormUrlEncoded
    @POST(HOST_IP + "getsensor.action")
    Observable<SettingManagerBean> getSensor(
            @Field("pid") String pid, @Field("type") String type,
            @Field("state") String state, @Field("ishave") String ishave);

    /*changePwd*/
    @Headers({"urlname:zgjiuan"})
    @FormUrlEncoded
    @POST("h5/updatePwd.action")
    Observable<ChangePwdBean> updatePwd(
            @Field("username") String username,
            @Field("oldPwd") String oldPwd,
            @Field("newPwd") String newPwd);

    /*获取角标数量*/
//    @Headers({"urlname:cheshi50"})
    @Headers({"urlname:zgjiuan"})
    @FormUrlEncoded
    @POST(HOST_FIRECONTROL + "getAppNum.action")
    Observable<GridCountBean> getAppNum(
            @Field("pid") String pid,
            @Field("id") String id);

    /*获取安全档案角标数量*/
//    @Headers({"urlname:cheshi50"})
    @Headers({"urlname:zgjiuan"})
    @FormUrlEncoded
    @POST(HOST_FIRECONTROL + "getSumType.action")
    Observable<GetSumTypeBean> getSumType(
            @Field("pid") String pid,
            @Field("id") String id);


    /*打卡*/
    @Headers({"urlname:zgjiuan"})
    @FormUrlEncoded
    @POST("h5/daka.action")
    Observable<SubmitBean> daka(
            @Query("userid") String userid,
            @Query("latitude") String Latitude,
            @Query("longitude") String Longitude,
            @Query("address") String address,
            @Query("memo") String memo,
            @Query("pid") String pid,
            @Query("imageurl") String imageurl);


    /*获取obd数据*/
    @Headers({"urlname:zgjiuan"})
    @FormUrlEncoded
    @POST("h5/getsensorobd.action")
    Observable<GetsensorObdSuccessBean> getsensorObd(
            @Field("terminalNo") String terminalNo);

}
