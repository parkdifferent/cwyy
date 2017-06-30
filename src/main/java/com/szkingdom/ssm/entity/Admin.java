package com.szkingdom.ssm.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by tianf on 2017/5/14.
 */

@Table(name = "tb_admin")
public class Admin extends BasePojo implements Serializable {
    private static final long serialVersionUID = 3610633970156116226L;
//员工表

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    private String password;


    @Column(name = "true_name")
    private String trueName;

    private Integer gender;             //  0: 男   1：女

    private String position;

    private Double salary;

    @Column(name = "entry_time")
    private Date entryTime;

    @Column(name = "phone_number")
    private String phoneNumber;


    @Transient
    private String code;

    public Admin() {
    }

    public Admin(String userName, String password, String trueName, Integer gender, String position, Double salary, Date entryTime, String phoneNumber, String code) {
        this.userName = userName;
        this.password = password;
        this.trueName = trueName;
        this.gender = gender;
        this.position = position;
        this.salary = salary;
        this.entryTime = entryTime;
        this.phoneNumber = phoneNumber;
        this.code = code;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", trueName='" + trueName + '\'' +
                ", gender=" + gender +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", entryTime=" + entryTime +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
