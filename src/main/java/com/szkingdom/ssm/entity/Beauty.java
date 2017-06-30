package com.szkingdom.ssm.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by tianf on 2017/5/15.
 */

@Table(name = "t_beauty")
public class Beauty extends BasePojo implements Serializable {
    private static final long serialVersionUID = 4002453189672637094L;
//美容

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;         //类型

    private String unit;             //单位


    public Beauty() {
    }

    public Beauty(String name, Double price, String unit) {
        this.name = name;
        this.price = price;
        this.unit = unit;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }


    @Override
    public String toString() {
        return "Beauty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
}
