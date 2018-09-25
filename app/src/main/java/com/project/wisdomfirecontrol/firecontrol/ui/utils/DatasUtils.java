package com.project.wisdomfirecontrol.firecontrol.ui.utils;

import android.content.Context;
import android.text.TextUtils;

import com.mvp_0726.common.utils.Constans;
import com.project.wisdomfirecontrol.R;
import com.project.wisdomfirecontrol.common.base.Global;
import com.project.wisdomfirecontrol.common.base.UserInfo;
import com.project.wisdomfirecontrol.common.base.UserManage;
import com.project.wisdomfirecontrol.common.util.LogUtil;
import com.project.wisdomfirecontrol.firecontrol.model.bean.video.Organ;
import com.project.wisdomfirecontrol.firecontrol.model.bean.video.VideoEquipmentDataBean;
import com.project.wisdomfirecontrol.firecontrol.model.bean.video.VideoesBean;
import com.project.wisdomfirecontrol.firecontrol.model.bean.video.VideoesXBean;
import com.project.wisdomfirecontrol.firecontrol.treesList.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/14.
 */

public class DatasUtils {

    private static int firstOsize;
    private static String secondId;
    private static String secondOrgShortName;
    private static String firstVRegistrationNO;
    private static int firstVsize;
    private static int secondVsize;
    private static String firstVehicleId;
    private static String secondVehicleId;
    private static String secondVehicleRegistrationNO;
    private static int secondOsize;
    private static String thirdOrgansId;
    private static String thirdOrgansOrgShortName;
    private static int thirdVsize;
    private static int thirdOsize;
    private static String thirdVehicleId;
    private static String thirdVehicleRegistrationNO;
    private static String thirdOrganId;
    private static String thirdOrganOrgShortName;
    private static int fourthVsize;
    private static int fourthOsize;
    private static String fourthvehicleId;
    private static String fourthvehicleRegistrationNO;
    private static String fourthOrganId;
    private static String fourthOrganOrgShortName;
    private static int fifthVsize;
    private static String fifthVehicleId;
    private static String fifthVehicleRegistrationNO;
    private static int fifthOsize;
    private static String fifthOrganId;
    private static String fifthOrganOrgShortName;
    private static int sixthVsize;
    private static int sixthOsize;
    private static String sixthVehicleId;
    private static String sixthVehicleRegistrationNO;
    private static String firstVehicleTerminalNO;
    private static String secondVehicleTerminalNO;
    private static String thirdVehicleTerminalNO;
    private static String fourthvehicleTerminalNO;
    private static String fifthVehicleTerminalNO;
    private static String sixthVehicleTerminalNO;


    public static List<Node> ReturnTreesDatas(List<VideoEquipmentDataBean> msg) {

        List<Node> mDatas = new ArrayList<Node>();

        int size = msg.size();
        if (size <= 0) {
            return new ArrayList<Node>();
        }

        String treeStr = initDatasTreeStr(msg);
        VideoEquipmentDataBean msgBean = msg.get(0);

        String orgShortName = msgBean.getOrgName();
//        String fatherPid = msgBean.getPid();
        String fatherId = msgBean.getId();

        List<VideoesBean> firstVehicles = msgBean.getVideoes();
        firstVsize = firstVehicles.size();
        List<VideoEquipmentDataBean.OrgansBean> firstOrgans = msgBean.getOrgans();
        firstOsize = firstOrgans.size();
        /*第一层*/
        // id , pid , label , 其他属性
        mDatas.add(new Node(fatherId, "1000000000", orgShortName, treeStr, ""));

        if (firstVsize > 0) {
//            for (VideoEquipmentDataBean.VideoesBean firstVehicle : firstVehicles) {
            for (VideoesBean firstVehicle : firstVehicles) {
                firstVehicleId = firstVehicle.getId();
                firstVRegistrationNO = firstVehicle.getName();
                firstVehicleTerminalNO = firstVehicle.getTerminalno();
                mDatas.add(new Node(firstVehicleId + "", fatherId + "", firstVRegistrationNO, Constans.COUNT_ZERO, firstVehicleTerminalNO));
            }
        }


        /*第二层*/
        if (firstOsize > 0) {
            for (VideoEquipmentDataBean.OrgansBean firstOrgan : firstOrgans) {
                String strSecond = initDatasTreeStrSecond(firstOrgan);
                secondId = firstOrgan.getId();
                secondOrgShortName = firstOrgan.getOrgName();
                List<VideoesXBean> secondVehicles = firstOrgan.getVideoes();
                secondVsize = secondVehicles.size();
                List<Organ> organs = firstOrgan.getOrgans();
                secondOsize = organs.size();

                mDatas.add(new Node(secondId + "", fatherId + "", secondOrgShortName, strSecond, ""));

                if (secondVsize > 0) {
                    for (VideoesXBean secondVehicle : secondVehicles) {

                        secondVehicleId = secondVehicle.getId();
                        secondVehicleRegistrationNO = secondVehicle.getName();
                        secondVehicleTerminalNO = secondVehicle.getTerminalno();
                        mDatas.add(new Node(secondVehicleId + "", secondId + "",
                                secondVehicleRegistrationNO, Constans.COUNT_ZERO, secondVehicleTerminalNO));
                    }
                }
                LogUtil.d("ReturnTreesDatas22" + secondOsize + " ++ " + secondVsize);
//                第三层
                if (secondOsize > 0) {

                    for (Organ organ : organs) {
                        String strThird = initDatasTreeStrThird(organ);
                        thirdOrgansId = organ.getId();
                        thirdOrgansOrgShortName = organ.getOrgName();
                        List<VideoesBean> thirdVehicles = organ.getVehicles();
                        thirdVsize = thirdVehicles.size();
                        List<Organ> thirdOrgans = organ.getOrgans();
                        thirdOsize = thirdOrgans.size();

//                        第三层总数据
                        mDatas.add(new Node(thirdOrgansId + "", secondId + "", thirdOrgansOrgShortName, strThird, ""));

                        if (thirdVsize > 0) {
                            for (VideoesBean thirdVehicle : thirdVehicles) {
                                thirdVehicleId = thirdVehicle.getId();
                                thirdVehicleRegistrationNO = thirdVehicle.getName();
                                thirdVehicleTerminalNO = thirdVehicle.getTerminalno();
                                mDatas.add(new Node(thirdVehicleId + "", thirdOrgansId + "", thirdVehicleRegistrationNO,
                                        Constans.COUNT_ZERO, thirdVehicleTerminalNO));
                            }
                        }
                        LogUtil.d("ReturnTreesDatas33" + thirdOsize + " ++ " + thirdVsize);
//                        第四层
                        if (thirdOsize > 0) {
                            for (Organ thirdOrgan : thirdOrgans) {

                                String strFouth = initDatasTreeStrFouth(thirdOrgan);

                                thirdOrganId = thirdOrgan.getId();
                                thirdOrganOrgShortName = thirdOrgan.getOrgName();

                                List<VideoesBean> fourthvehicles = thirdOrgan.getVehicles();
                                fourthVsize = fourthvehicles.size();

                                List<Organ> fourthOrgans = thirdOrgan.getOrgans();
                                fourthOsize = fourthOrgans.size();

                                mDatas.add(new Node(thirdOrganId + "", thirdOrgansId + "", thirdOrganOrgShortName, strFouth, ""));

                                if (fourthVsize > 0) {
                                    for (VideoesBean fourthvehicle : fourthvehicles) {
                                        fourthvehicleId = fourthvehicle.getId();
                                        fourthvehicleRegistrationNO = fourthvehicle.getName();
                                        fourthvehicleTerminalNO = fourthvehicle.getTerminalno();
                                        mDatas.add(new Node(fourthvehicleId + "", thirdOrganId + "", fourthvehicleRegistrationNO,
                                                Constans.COUNT_ZERO, fourthvehicleTerminalNO));
                                    }
                                }
                                LogUtil.d("ReturnTreesDatas44" + fourthOsize + " ++ " + fourthVsize);

//                                第五层
                                if (fourthOsize > 0) {
                                    for (Organ fourthOrgan : fourthOrgans) {

                                        String strFifth = initDatasTreeStrFifth(fourthOrgan);
                                        fourthOrganId = fourthOrgan.getId();
                                        fourthOrganOrgShortName = fourthOrgan.getOrgName();

                                        List<VideoesBean> fifthVehicles = fourthOrgan.getVehicles();
                                        fifthVsize = fifthVehicles.size();

                                        List<Organ> fifthOrgans = fourthOrgan.getOrgans();
                                        fifthOsize = fifthOrgans.size();

                                        mDatas.add(new Node(fourthOrganId + "", thirdOrganId + "", fourthOrganOrgShortName, strFifth, ""));


                                        if (fifthVsize > 0) {
                                            for (VideoesBean fifthVehicle : fifthVehicles) {
                                                fifthVehicleId = fifthVehicle.getId();
                                                fifthVehicleRegistrationNO = fifthVehicle.getName();
                                                fifthVehicleTerminalNO = fifthVehicle.getTerminalno();
                                                mDatas.add(new Node(fifthVehicleId + "", thirdOrganId + "", fifthVehicleRegistrationNO,
                                                        Constans.COUNT_ZERO, fifthVehicleTerminalNO));
                                            }
                                        }
                                        LogUtil.d("ReturnTreesDatas55" + fifthOsize + " ++ " + fifthVsize);

//                                        第六层
                                        if (fifthOsize > 0) {
                                            for (Organ fifthOrgan : fifthOrgans) {

                                                String strSixth = initDatasTreeStrFouth(fifthOrgan);
                                                fifthOrganId = fifthOrgan.getId();
                                                fifthOrganOrgShortName = fifthOrgan.getOrgName();
                                                List<VideoesBean> sixthVehicles = fifthOrgan.getVehicles();
                                                sixthVsize = sixthVehicles.size();
                                                List<Organ> sixthOrganOrgans = fifthOrgan.getOrgans();
                                                sixthOsize = sixthOrganOrgans.size();

                                                mDatas.add(new Node(fifthOrganId + "", fourthOrganId + "", fifthOrganOrgShortName, strSixth, ""));


                                                if (sixthVsize > 0) {
                                                    for (VideoesBean sixthVehicle : sixthVehicles) {
                                                        sixthVehicleId = sixthVehicle.getId();
                                                        sixthVehicleRegistrationNO = sixthVehicle.getName();
                                                        sixthVehicleTerminalNO = sixthVehicle.getTerminalno();
                                                        mDatas.add(new Node(sixthVehicleId + "", fifthOrganId + "",
                                                                sixthVehicleRegistrationNO, Constans.COUNT_ZERO, sixthVehicleTerminalNO));
                                                    }
                                                }
                                                LogUtil.d("ReturnTreesDatas66" + sixthOsize + " ++ " + sixthVsize);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return mDatas;
    }


    private static String initDatasTreeStr(List<VideoEquipmentDataBean> msg) {

        int countAll = 0;

        int size = msg.size();
        if (size <= 0) {
            return "";
        }
        VideoEquipmentDataBean msgBean = msg.get(0);

//        第二层
//        List<VideoEquipmentDataBean.VideoesBean> firstVehicles = msgBean.getVideoes();
        List<VideoesBean> firstVehicles = msgBean.getVideoes();
        firstVsize = firstVehicles.size();
        List<VideoEquipmentDataBean.OrgansBean> firstOrgans = msgBean.getOrgans();
        firstOsize = firstOrgans.size();

        // id , pid , label , 其他属性

        if (firstVsize > 0) {
            countAll = firstVsize;
        }

        if (firstOsize > 0) {
            for (VideoEquipmentDataBean.OrgansBean organ : firstOrgans) {
                List<VideoesXBean> secondVehicles = organ.getVideoes();
                secondVsize = secondVehicles.size();

                List<Organ> organs = organ.getOrgans();
                secondOsize = organs.size();
                if (secondVsize > 0) {
                    for (VideoesXBean secondVehicle : secondVehicles) {
                        countAll++;
                    }
                }


//                第三层
                if (secondOsize > 0) {
                    for (Organ secondOrgans : organs) {

                        List<VideoesBean> thirdVehicles = secondOrgans.getVehicles();
                        thirdVsize = thirdVehicles.size();

                        List<Organ> thirdOrgans = secondOrgans.getOrgans();
                        thirdOsize = thirdOrgans.size();

                        if (thirdVsize > 0) {
                            for (VideoesBean thirdVehicle : thirdVehicles) {
                                countAll++;
                            }
                        }


//                        第四层
                        if (thirdOsize > 0) {
                            for (Organ thirdOrgan : thirdOrgans) {

                                List<VideoesBean> fourthvehicles = thirdOrgan.getVehicles();
                                fourthVsize = fourthvehicles.size();

                                List<Organ> fourthOrgans = thirdOrgan.getOrgans();
                                fourthOsize = fourthOrgans.size();

                                if (fourthVsize > 0) {
                                    for (VideoesBean fourthvehicle : fourthvehicles) {
                                        countAll++;
                                    }
                                }

//                                第五层
                                if (fourthOsize > 0) {
                                    for (Organ fourthOrgan : fourthOrgans) {

                                        List<VideoesBean> fifthVehicles = fourthOrgan.getVehicles();
                                        fifthVsize = fifthVehicles.size();

                                        List<Organ> fifthOrgans = fourthOrgan.getOrgans();
                                        fifthOsize = fifthOrgans.size();


                                        if (fifthVsize > 0) {
                                            for (VideoesBean fifthVehicle : fifthVehicles) {
                                                countAll++;
                                            }
                                        }

//                                        第六层
                                        if (fifthOsize > 0) {
                                            for (Organ fifthOrgan : fifthOrgans) {
                                                List<VideoesBean> sixthVehicles = fifthOrgan.getVehicles();
                                                sixthVsize = sixthVehicles.size();

                                                List<Organ> sixthOrganOrgans = fifthOrgan.getOrgans();
                                                sixthOsize = sixthOrganOrgans.size();

                                                if (sixthVsize > 0) {
                                                    for (VideoesBean sixthVehicle : sixthVehicles) {
                                                        countAll++;
                                                    }
                                                }

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return String.valueOf(countAll);
    }

    private static String initDatasTreeStrSecond(VideoEquipmentDataBean.OrgansBean firstOrgans) {

        int countAll = 0;
        List<VideoesXBean> secondVehicles = firstOrgans.getVideoes();
        secondVsize = secondVehicles.size();
        List<Organ> organs = firstOrgans.getOrgans();
        secondOsize = organs.size();
        if (secondVsize > 0) {
            for (VideoesXBean secondVehicle : secondVehicles) {
                countAll++;
            }
        }


//                第三层
        if (secondOsize > 0) {
            for (Organ secondOrgans : organs) {

                List<VideoesBean> thirdVehicles = secondOrgans.getVehicles();
                thirdVsize = thirdVehicles.size();
                List<Organ> thirdOrgans = secondOrgans.getOrgans();
                thirdOsize = thirdOrgans.size();

                if (thirdVsize > 0) {
                    for (VideoesBean thirdVehicle : thirdVehicles) {
                        countAll++;
                    }
                }


//                        第四层
                if (thirdOsize > 0) {
                    for (Organ thirdOrgan : thirdOrgans) {

                        List<VideoesBean> fourthvehicles = thirdOrgan.getVehicles();
                        fourthVsize = fourthvehicles.size();
                        List<Organ> fourthOrgans = thirdOrgan.getOrgans();
                        fourthOsize = fourthOrgans.size();

                        if (fourthVsize > 0) {
                            for (VideoesBean fourthvehicle : fourthvehicles) {
                                countAll++;
                            }
                        }

//                                第五层
                        if (fourthOsize > 0) {
                            for (Organ fourthOrgan : fourthOrgans) {

                                List<VideoesBean> fifthVehicles = fourthOrgan.getVehicles();
                                fifthVsize = fifthVehicles.size();
                                List<Organ> fifthOrgans = fourthOrgan.getOrgans();
                                fifthOsize = fifthOrgans.size();


                                if (fifthVsize > 0) {
                                    for (VideoesBean fifthVehicle : fifthVehicles) {
                                        countAll++;
                                    }
                                }

//                                        第六层
                                if (fifthOsize > 0) {
                                    for (Organ fifthOrgan : fifthOrgans) {
                                        List<VideoesBean> sixthVehicles = fifthOrgan.getVehicles();
                                        sixthVsize = sixthVehicles.size();
                                        List<Organ> sixthOrganOrgans = fifthOrgan.getOrgans();
                                        sixthOsize = sixthOrganOrgans.size();

                                        if (sixthVsize > 0) {
                                            for (VideoesBean sixthVehicle : sixthVehicles) {
                                                countAll++;
                                            }
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return countAll + "";
    }


    private static String initDatasTreeStrThird(Organ organ) {

        int countAll = 0;
        List<VideoesBean> secondVehicles = organ.getVehicles();

        secondVsize = secondVehicles.size();

        List<Organ> organs = organ.getOrgans();
        secondOsize = organs.size();
        if (secondVsize > 0) {
            for (VideoesBean secondVehicle : secondVehicles) {
                countAll++;
            }
        }


//                第三层
        if (secondOsize > 0) {
            for (Organ secondOrgans : organs) {

                List<VideoesBean> thirdVehicles = secondOrgans.getVehicles();
                thirdVsize = thirdVehicles.size();
                List<Organ> thirdOrgans = secondOrgans.getOrgans();
                thirdOsize = thirdOrgans.size();

                if (thirdVsize > 0) {
                    for (VideoesBean thirdVehicle : thirdVehicles) {
                        countAll++;
                    }
                }


//                        第四层
                if (thirdOsize > 0) {
                    for (Organ thirdOrgan : thirdOrgans) {
                        List<VideoesBean> fourthvehicles = thirdOrgan.getVehicles();
                        fourthVsize = fourthvehicles.size();
                        List<Organ> fourthOrgans = thirdOrgan.getOrgans();
                        fourthOsize = fourthOrgans.size();

                        if (fourthVsize > 0) {
                            for (VideoesBean fourthvehicle : fourthvehicles) {
                                countAll++;
                            }
                        }

//                                第五层
                        if (fourthOsize > 0) {
                            for (Organ fourthOrgan : fourthOrgans) {

                                List<VideoesBean> fifthVehicles = fourthOrgan.getVehicles();
                                fifthVsize = fifthVehicles.size();
                                List<Organ> fifthOrgans = fourthOrgan.getOrgans();
                                fifthOsize = fifthOrgans.size();


                                if (fifthVsize > 0) {
                                    for (VideoesBean fifthVehicle : fifthVehicles) {
                                        countAll++;
                                    }
                                }

//                                        第六层
                                if (fifthOsize > 0) {
                                    for (Organ fifthOrgan : fifthOrgans) {
                                        List<VideoesBean> sixthVehicles = fifthOrgan.getVehicles();
                                        sixthVsize = sixthVehicles.size();
//                                        List<OrgansBean> sixthOrganOrgans = fifthOrgan.getOrgans();
//                                        sixthOsize = sixthOrganOrgans.size();

                                        if (sixthVsize > 0) {
                                            for (VideoesBean sixthVehicle : sixthVehicles) {
                                                countAll++;
                                            }
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return countAll + "";
    }


    private static String initDatasTreeStrFouth(Organ organ) {

        int countAll = 0;
        List<VideoesBean> secondVehicles = organ.getVehicles();
        secondVsize = secondVehicles.size();

        List<Organ> organs = organ.getOrgans();
        secondOsize = organs.size();
        if (secondVsize > 0) {
            for (VideoesBean secondVehicle : secondVehicles) {
                countAll++;
            }
        }


//                第三层
        if (secondOsize > 0) {
            for (Organ secondOrgans : organs) {

                List<VideoesBean> thirdVehicles = secondOrgans.getVehicles();
                thirdVsize = thirdVehicles.size();
                List<Organ> thirdOrgans = secondOrgans.getOrgans();
                thirdOsize = thirdOrgans.size();

                if (thirdVsize > 0) {
                    for (VideoesBean thirdVehicle : thirdVehicles) {
                        countAll++;
                    }
                }


//                        第四层
                if (thirdOsize > 0) {
                    for (Organ thirdOrgan : thirdOrgans) {

                        List<VideoesBean> fourthvehicles = thirdOrgan.getVehicles();
                        fourthVsize = fourthvehicles.size();
                        List<Organ> fourthOrgans = thirdOrgan.getOrgans();
                        fourthOsize = fourthOrgans.size();

                        if (fourthVsize > 0) {
                            for (VideoesBean fourthvehicle : fourthvehicles) {
                                countAll++;
                            }
                        }

//                                第五层
                        if (fourthOsize > 0) {
                            for (Organ fourthOrgan : fourthOrgans) {

                                List<VideoesBean> fifthVehicles = fourthOrgan.getVehicles();
                                fifthVsize = fifthVehicles.size();
                                List<Organ> fifthOrgans = fourthOrgan.getOrgans();
                                fifthOsize = fifthOrgans.size();


                                if (fifthVsize > 0) {
                                    for (VideoesBean fifthVehicle : fifthVehicles) {
                                        countAll++;
                                    }
                                }

//                                        第六层
                                if (fifthOsize > 0) {
                                    for (Organ fifthOrgan : fifthOrgans) {
                                        List<VideoesBean> sixthVehicles = fifthOrgan.getVehicles();
                                        sixthVsize = sixthVehicles.size();
                                        List<Organ> sixthOrganOrgans = fifthOrgan.getOrgans();
                                        sixthOsize = sixthOrganOrgans.size();

                                        if (sixthVsize > 0) {
                                            for (VideoesBean sixthVehicle : sixthVehicles) {
                                                countAll++;
                                            }
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return countAll + "";
    }

    private static String initDatasTreeStrFifth(Organ organ) {

        int countAll = 0;
        List<VideoesBean> secondVehicles = organ.getVehicles();
        secondVsize = secondVehicles.size();

        List<Organ> organs = organ.getOrgans();
        secondOsize = organs.size();
        if (secondVsize > 0) {
            for (VideoesBean secondVehicle : secondVehicles) {
                countAll++;
            }
        }


//                第三层
        if (secondOsize > 0) {
            for (Organ secondOrgans : organs) {

                List<VideoesBean> thirdVehicles = secondOrgans.getVehicles();
                thirdVsize = thirdVehicles.size();
                List<Organ> thirdOrgans = secondOrgans.getOrgans();
                thirdOsize = thirdOrgans.size();

                if (thirdVsize > 0) {
                    for (VideoesBean thirdVehicle : thirdVehicles) {
                        countAll++;
                    }
                }


//                        第四层
                if (thirdOsize > 0) {
                    for (Organ thirdOrgan : thirdOrgans) {

                        List<VideoesBean> fourthvehicles = thirdOrgan.getVehicles();
                        fourthVsize = fourthvehicles.size();
                        List<Organ> fourthOrgans = thirdOrgan.getOrgans();
                        fourthOsize = fourthOrgans.size();

                        if (fourthVsize > 0) {
                            for (VideoesBean fourthvehicle : fourthvehicles) {
                                countAll++;
                            }
                        }

//                                第五层
                        if (fourthOsize > 0) {
                            for (Organ fourthOrgan : fourthOrgans) {

                                List<VideoesBean> fifthVehicles = fourthOrgan.getVehicles();
                                fifthVsize = fifthVehicles.size();
                                List<Organ> fifthOrgans = fourthOrgan.getOrgans();
                                fifthOsize = fifthOrgans.size();


                                if (fifthVsize > 0) {
                                    for (VideoesBean fifthVehicle : fifthVehicles) {
                                        countAll++;
                                    }
                                }

//                                        第六层
                                if (fifthOsize > 0) {
                                    for (Organ fifthOrgan : fifthOrgans) {
                                        List<VideoesBean> sixthVehicles = fifthOrgan.getVehicles();
                                        sixthVsize = sixthVehicles.size();
                                        List<Organ> sixthOrganOrgans = fifthOrgan.getOrgans();
                                        sixthOsize = sixthOrganOrgans.size();

                                        if (sixthVsize > 0) {
                                            for (VideoesBean sixthVehicle : sixthVehicles) {
                                                countAll++;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return countAll + "";
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
