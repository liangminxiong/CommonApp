package com.project.wisdomfirecontrol.firecontrol.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/4/28.
 */

public class VideoEquipmentBean implements Serializable {

    /**
     * success : true
     * msgTitle : 成功提示
     * msg : 操作成功!
     * data : [{"id":"c5073eab0a00000518c6c9b9c959a415","pid":"07165dd10a00006300983d9592e50c94","orgName":"东莞金波罗","principal":"dpc","principalTel":"13066165530","fax":"0755-89899898","address":"广东省肇庆市鼎湖区广利街道167乡道","organs":[],"videoes":[{"id":"06064845ffffffc95ae293ce0a544617","name":"视频3","terminalno":"211999","txh":"211999","pid":"c5073eab0a00000518c6c9b9c959a415","createTime":"2018-04-27 15:34:42.0"},{"id":"751686b10a000063724451cbcaf3586f","name":"视频1","terminalno":"112","txh":"113","pid":"c5073eab0a00000518c6c9b9c959a415","createTime":"2018-03-30 12:06:13.0"},{"id":"751a583e0a000063724451cbd793c18a","name":"视频2","terminalno":"115","txh":"116","pid":"c5073eab0a00000518c6c9b9c959a415","createTime":"2018-03-30 12:11:13.0"}]}]
     */

    private boolean success;
    private String msgTitle;
    private String msg;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : c5073eab0a00000518c6c9b9c959a415
         * pid : 07165dd10a00006300983d9592e50c94
         * orgName : 东莞金波罗
         * principal : dpc
         * principalTel : 13066165530
         * fax : 0755-89899898
         * address : 广东省肇庆市鼎湖区广利街道167乡道
         * organs : []
         * videoes : [{"id":"06064845ffffffc95ae293ce0a544617","name":"视频3","terminalno":"211999","txh":"211999","pid":"c5073eab0a00000518c6c9b9c959a415","createTime":"2018-04-27 15:34:42.0"},
         * {"id":"751686b10a000063724451cbcaf3586f","name":"视频1","terminalno":"112","txh":"113","pid":"c5073eab0a00000518c6c9b9c959a415","createTime":"2018-03-30 12:06:13.0"},
         * {"id":"751a583e0a000063724451cbd793c18a","name":"视频2","terminalno":"115","txh":"116","pid":"c5073eab0a00000518c6c9b9c959a415","createTime":"2018-03-30 12:11:13.0"}]
         */

        private String id;
        private String pid;
        private String orgName;
        private String principal;
        private String principalTel;
        private String fax;
        private String address;
        private List<?> organs;
        private List<VideoesBean> videoes;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public String getPrincipal() {
            return principal;
        }

        public void setPrincipal(String principal) {
            this.principal = principal;
        }

        public String getPrincipalTel() {
            return principalTel;
        }

        public void setPrincipalTel(String principalTel) {
            this.principalTel = principalTel;
        }

        public String getFax() {
            return fax;
        }

        public void setFax(String fax) {
            this.fax = fax;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public List<?> getOrgans() {
            return organs;
        }

        public void setOrgans(List<?> organs) {
            this.organs = organs;
        }

        public List<VideoesBean> getVideoes() {
            return videoes;
        }

        public void setVideoes(List<VideoesBean> videoes) {
            this.videoes = videoes;
        }

        public static class VideoesBean {
            /**
             * id : 06064845ffffffc95ae293ce0a544617
             * name : 视频3
             * terminalno : 211999
             * txh : 211999
             * pid : c5073eab0a00000518c6c9b9c959a415
             * createTime : 2018-04-27 15:34:42.0
             */

            private String id;
            private String name;
            private String terminalno;
            private String txh;
            private String pid;
            private String createTime;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTerminalno() {
                return terminalno;
            }

            public void setTerminalno(String terminalno) {
                this.terminalno = terminalno;
            }

            public String getTxh() {
                return txh;
            }

            public void setTxh(String txh) {
                this.txh = txh;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }
        }
    }
}
