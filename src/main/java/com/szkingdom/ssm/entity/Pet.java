package com.szkingdom.ssm.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by tianf on 2017/5/15.
 */

@Table(name = "t_pet")
public class Pet extends BasePojo implements Serializable {


    private static final long serialVersionUID = -6679740820075254044L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nick_name")
    private String nickName;

    private String type;         //类型

    private String veriety;   //品种

    private Integer age;

    private Integer gender;            // 0 公  1 母

    @Column(name = "host_id")
    private Long hostId;

    public Pet() {
    }

    public Pet(String nickName, String type, String veriety, Integer age, Integer gender, Long hostId) {
        this.nickName = nickName;
        this.type = type;
        this.veriety = veriety;
        this.age = age;
        this.gender = gender;
        this.hostId = hostId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVeriety() {
        return veriety;
    }

    public void setVeriety(String veriety) {
        this.veriety = veriety;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Long getHostId() {
        return hostId;
    }

    public void setHostId(Long hostId) {
        this.hostId = hostId;
    }


    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", type='" + type + '\'' +
                ", veriety='" + veriety + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", hostId=" + hostId +
                '}';
    }
}
