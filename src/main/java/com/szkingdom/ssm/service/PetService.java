package com.szkingdom.ssm.service;

import com.szkingdom.ssm.bean.EasyUIResult;
import com.szkingdom.ssm.bean.PetBeautyDTO;
import com.szkingdom.ssm.entity.Pet;
import com.szkingdom.ssm.entity.User;

import java.util.Date;
import java.util.List;

/**
 * Created by tianf on 2017/5/16.
 */
public interface PetService {


    public EasyUIResult queryPetList(Integer page, Integer rows);

    public Pet queryPetById(Long id);

    public Boolean savePet(Pet pet);

    public Boolean updatePet(Pet pet);

    public Boolean deletePet(Long id);

    public Boolean savePetBeauty(String petId,String hostId,String beautyId);

    EasyUIResult  queryPetBeautyInfo(Integer pageNo,Integer pageSize);


    public Boolean deletePetBeauty(Long id);

    public Boolean savePetCare(String petId,String hostId,Date beginTime,Date endTime);


    EasyUIResult  queryPetCareInfo(Integer pageNo,Integer pageSize);

    public Boolean deletePetCare(Long id);
}
