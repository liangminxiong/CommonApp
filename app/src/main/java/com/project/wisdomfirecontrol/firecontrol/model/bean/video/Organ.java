package com.project.wisdomfirecontrol.firecontrol.model.bean.video;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 逻辑实体类，不与数据库联系
 *
 * @author li
 */
public class Organ implements Serializable {

    public Organ() {
        organs = new ArrayList<>();
//        organsbeen = new ArrayList<>();
        vehicles = new ArrayList<>();
    }

    private String principal;
    private String principalTel;
    private String fax;
    private String address;
    private String id;
    private String pid;
    private String orgName;
    private List<Organ> organs;
    //    private List<OrgansBean> organsbeen;
    private List<VideoesBean> vehicles;

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

    public List<VideoesBean> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VideoesBean> vehicles) {
        this.vehicles = vehicles;
    }

//    public List<OrgansBean> getOrgansBeen() {
//        return organsbeen;
//    }
//
//    public void setOrgansbeen(List<OrgansBean> organs) {
//        this.organsbeen = organs;
//    }

    public List<Organ> getOrgans() {
        return organs;
    }

    public void setOrgans(List<Organ> organs) {
        this.organs = organs;

    }
}
