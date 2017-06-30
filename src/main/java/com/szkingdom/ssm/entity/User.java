package com.szkingdom.ssm.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by tianf on 2017/5/15.
 */

@Table(name = "t_user")
public class User extends BasePojo implements Serializable {

    private static final long serialVersionUID = -2448699216219734524L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Field名字驼峰转下划线

    // 用户名
    @Column(name = "user_name")
    private String userName;

    private String phone;

    private String email;

    public User() {
    }

    public User(String userName, String phone, String email) {
        this.userName = userName;
        this.phone = phone;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
