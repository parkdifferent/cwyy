package com.szkingdom.ssm.bean;

import com.szkingdom.ssm.entity.Beauty;
import com.szkingdom.ssm.entity.Pet;
import com.szkingdom.ssm.entity.User;

import java.io.Serializable;

/**
 * Created by admin on 2017/5/18.
 */
public class PetBeautyDTO implements Serializable {

    private static final long serialVersionUID = -4544477018220633611L;
    private Long id;

    private Beauty beauty;

    private User user;

    private Pet pet;

    public PetBeautyDTO() {
    }

    public PetBeautyDTO(Long id, Beauty beauty, User user, Pet pet) {
        this.id = id;
        this.beauty = beauty;
        this.user = user;
        this.pet = pet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Beauty getBeauty() {
        return beauty;
    }

    public void setBeauty(Beauty beauty) {
        this.beauty = beauty;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public String toString() {
        return "PetBeautyDTO{" +
                "id=" + id +
                ", beauty=" + beauty +
                ", user=" + user +
                ", pet=" + pet +
                '}';
    }
}
