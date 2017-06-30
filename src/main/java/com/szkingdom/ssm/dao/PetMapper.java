package com.szkingdom.ssm.dao;

import com.github.abel533.mapper.Mapper;
import com.szkingdom.ssm.bean.PetBeautyDTO;
import com.szkingdom.ssm.bean.PetCare;
import com.szkingdom.ssm.entity.Pet;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by tianf on 2017/5/16.
 */
public interface PetMapper extends Mapper<Pet> {

    int savePetBeauty(Map<String,String> map);
    List<PetBeautyDTO> queryPetBeautyInfo(@Param("pageNo") Integer pageNo,@Param("pageSize") Integer pageSize);

    int deletePetBeauty(@Param("id") Long id);

    int savePetCare(Map<String,Object> map);

    List<PetCare> queryPetCareInfo(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

    int deletePetCare(@Param("id") Long id);

}
