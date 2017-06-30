package com.szkingdom.ssm.service.impl;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szkingdom.ssm.bean.EasyUIResult;
import com.szkingdom.ssm.bean.PetBeauty;
import com.szkingdom.ssm.bean.PetBeautyDTO;
import com.szkingdom.ssm.bean.PetCare;
import com.szkingdom.ssm.dao.PetMapper;
import com.szkingdom.ssm.entity.Pet;
import com.szkingdom.ssm.service.BaseService;
import com.szkingdom.ssm.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by tianf on 2017/5/16.
 */

@Service
public class PetServiceImpl extends BaseService<Pet> implements PetService {

    @Autowired
    PetMapper petMapper;


    @Override
    public EasyUIResult queryPetList(Integer page, Integer rows) {
        // 设置分页参数
        PageHelper.startPage(page, rows);

        // 查询User数据
        Example example = new Example(Pet.class);
        example.setOrderByClause("updated DESC"); // 设置排序条件
        List<Pet> pets = this.petMapper.selectByExample(example);

        // 获取分页后的信息
        PageInfo<Pet> pageInfo = new PageInfo<Pet>(pets);

        return new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());

    }

    @Override
    public Pet queryPetById(Long id) {
        return this.petMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean savePet(Pet pet) {
        return this.petMapper.insert(pet) == 1;
    }

    @Override
    public Boolean updatePet(Pet pet) {
        return this.petMapper.updateByPrimaryKeySelective(pet) == 1 ;
    }

    @Override
    public Boolean deletePet(Long id) {
        return this.petMapper.deleteByPrimaryKey(id) == 1;
    }

    @Override
    public Boolean savePetBeauty(String petId, String hostId, String beautyId) {
        Map<String,String> map = new HashMap<>(4);
        //String hostId,String petId,String beautyId
        map.put("hostId",hostId);
        map.put("petId",petId);
        map.put("beautyId",beautyId);
        return this.petMapper.savePetBeauty(map) == 1;
    }

    @Override
    public EasyUIResult queryPetBeautyInfo(Integer pageNo, Integer pageSize) {
        List<PetBeautyDTO> petBeautyDTOs = petMapper.queryPetBeautyInfo(pageNo,pageSize);

        for(PetBeautyDTO petBeautyDTO : petBeautyDTOs) {
            System.out.println(petBeautyDTO);
        }

        List<PetBeauty> petBeauties = new ArrayList<>();
        for(PetBeautyDTO petBeautyDTO : petBeautyDTOs) {
            PetBeauty petBeauty = new PetBeauty();
            petBeauty.setId(petBeautyDTO.getId());
            petBeauty.setName(petBeautyDTO.getBeauty().getName());
            petBeauty.setPrice(petBeautyDTO.getBeauty().getPrice());

            petBeauty.setUserName(petBeautyDTO.getUser().getUserName());
            petBeauty.setPhone(petBeautyDTO.getUser().getUserName());

            petBeauty.setNickName(petBeautyDTO.getPet().getNickName());
            petBeauty.setVeriety(petBeautyDTO.getPet().getVeriety());

            petBeauties.add(petBeauty);

        }

        //PageInfo<PetBeauty> pageInfo = new PageInfo<>(petBeauties);

        return new EasyUIResult((long) petBeauties.size(), petBeauties);
    }

    @Override
    public Boolean deletePetBeauty(Long id) {
        return petMapper.deletePetBeauty(id) == 1;
    }

    @Override
    public Boolean savePetCare(String petId,String hostId,Date beginTime,Date endTime) {
        Map<String,Object> hashMap = new HashMap<>();
        hashMap.put("petId",petId);
        hashMap.put("hostId",hostId);
        hashMap.put("beginTime",beginTime);
        hashMap.put("endTime",endTime);
        return petMapper.savePetCare(hashMap) == 1;
    }

    @Override
    public EasyUIResult queryPetCareInfo(Integer pageNo, Integer pageSize) {
        List<PetCare> petCareList = petMapper.queryPetCareInfo(pageNo,pageSize);
        return new EasyUIResult((long) petCareList.size(),petCareList);
    }

    @Override
    public Boolean deletePetCare(Long id) {
        return petMapper.deletePetCare(id) == 1;
    }


}
