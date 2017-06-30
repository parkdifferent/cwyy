package com.szkingdom.ssm.bean;

/**
 * Created by admin on 2017/5/18.
 */
public class BeautyDTO {

    private Long id;

    private String name;

    public BeautyDTO() {
    }

    public BeautyDTO(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "BeautyDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
