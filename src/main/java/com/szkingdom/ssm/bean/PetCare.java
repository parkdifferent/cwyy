package com.szkingdom.ssm.bean;

import java.util.Date;

/**
 * Created by admin on 2017/5/18.
 */
public class PetCare {

    private Long id;

    private String userName;

    private String phone;

    private String nickName;

    private String veriety;     //品种

    private Date beginTime;

    private Date endTime;

    public PetCare() {
    }

    public PetCare(Long id, String userName, String phone, String nickName, String veriety, Date beginTime, Date endTime) {
        this.id = id;
        this.userName = userName;
        this.phone = phone;
        this.nickName = nickName;
        this.veriety = veriety;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getVeriety() {
        return veriety;
    }

    public void setVeriety(String veriety) {
        this.veriety = veriety;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "PetCare{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", nickName='" + nickName + '\'' +
                ", veriety='" + veriety + '\'' +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                '}';
    }
}
