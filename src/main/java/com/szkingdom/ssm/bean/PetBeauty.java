package com.szkingdom.ssm.bean;

/**
 * Created by admin on 2017/5/18.
 */
public class PetBeauty {

    private Long id;

    private String name;  //美容项目名

    private Double price;

    private String userName;

    private String phone;

    private String nickName;

    private String veriety;     //品种

    public PetBeauty() {
    }

    public PetBeauty(Long id, String name, Double price, String userName, String phone, String nickName, String veriety) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.userName = userName;
        this.phone = phone;
        this.nickName = nickName;
        this.veriety = veriety;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    @Override
    public String toString() {
        return "PetBeauty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", nickName='" + nickName + '\'' +
                ", veriety='" + veriety + '\'' +
                '}';
    }
}
