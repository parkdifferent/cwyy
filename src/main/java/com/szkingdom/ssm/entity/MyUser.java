package com.szkingdom.ssm.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Table(name = "tb_user")
public class MyUser implements Serializable {

    private static final long serialVersionUID = 8938700946343986195L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Field名字驼峰转下划线

    // 用户名
    @Column(name = "user_name")
    private String userName;

    // 密码
    private String password;

    private String phone;

    private String email;

    // 创建时间
    private Date created;

    // 更新时间
    private Date updated;

    /*@Transient
    private String abd;  //忽略字段，不会作为表字段使用*/

    /*@Column(name = "tb_abc")
    private String adc;   //指定不符合规则的字段名*/


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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "MyUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
