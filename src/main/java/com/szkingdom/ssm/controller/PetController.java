package com.szkingdom.ssm.controller;

import com.szkingdom.ssm.bean.EasyUIResult;
import com.szkingdom.ssm.entity.Pet;
import com.szkingdom.ssm.service.PetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tianf on 2017/5/16.
 */

@Controller
@RequestMapping("/pet")
public class PetController {


 /*   @RequestMapping(value = "toAddPage", method = RequestMethod.POST)
    public ModelAndView toPage(@RequestParam("ids") String ids ) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ids",ids);
        modelAndView.setViewName("pet-add");
        return modelAndView;
    }*/


    private static final Logger LOGGER = LoggerFactory.getLogger(PetController.class);

    @Autowired
    PetService petService;

    @RequestMapping(value = "petList",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<EasyUIResult> queryPetList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "30") Integer rows) {
        try {
            return ResponseEntity.ok(this.petService.queryPetList(page, rows));
        } catch (Exception e) {
            LOGGER.error("查询宠物列表出错! page = " + page + ", rows = " + rows, e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @RequestMapping(value = "petBeautyList",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<EasyUIResult> queryPetBeautyList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "30") Integer rows) {
        try {
            Integer pageNo = (page -1) * rows;
            Integer pageSize = rows;
            EasyUIResult easyUIResult = this.petService.queryPetBeautyInfo(pageNo,pageSize);
            System.out.println(easyUIResult);
            return ResponseEntity.ok(easyUIResult);
        } catch (Exception e) {
            LOGGER.error("查询宠物美容列表出错! page = " + page + ", rows = " + rows, e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }


    @RequestMapping(value = "petCareList",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<EasyUIResult> queryPetCareList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "30") Integer rows) {
        try {
            Integer pageNo = (page -1) * rows;
            Integer pageSize = rows;
            EasyUIResult easyUIResult = this.petService.queryPetCareInfo(pageNo,pageSize);
            System.out.println(easyUIResult);
            return ResponseEntity.ok(easyUIResult);
        } catch (Exception e) {
            LOGGER.error("查询宠物寄养列表出错! page = " + page + ", rows = " + rows, e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }





    @RequestMapping(value = "save",method = RequestMethod.POST)
    public ResponseEntity<Void> savePet(Pet pet) {
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("新增宠物， pet = {}", pet);
            }

            // 保存商品    //处理事务，同一个服务的同一个方法
            pet.setCreated(new Date());
            pet.setUpdated(new Date());
            Boolean bool = this.petService.savePet(pet);
            if (!bool) {
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("新增宠物失败， pet = {}", pet);
                }
                // 保存失败
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增宠物成功， petId = {}", pet.getId());
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            LOGGER.error("新增宠物出错! pet = " + pet, e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


    @RequestMapping(value = "savePetBeauty",method = RequestMethod.POST)
    public ResponseEntity<Void> savePetBeauty(@RequestParam("hostId") String hostId,@RequestParam("beautyId") String beautyId,
                                              @RequestParam("petId") String petId) {
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("新增宠物美容， hostId = {}，beautyId = {}，petId = {}", hostId,beautyId,petId);
            }

            // 保存商品    //处理事务，同一个服务的同一个方法

            Boolean bool = this.petService.savePetBeauty(petId,hostId,beautyId);
            if (!bool) {
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("新增宠物美容失败， hostId = {}，beautyId = {}，petId = {}", hostId,beautyId,petId);
                }
                // 保存失败
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增宠物美容成功， hostId = {}，beautyId = {}，petId = {}", hostId,beautyId,petId);
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            LOGGER.error("新增宠物美容出错! hostId = {}，beautyId = {}，petId = {}", hostId,beautyId,petId, e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @RequestMapping(value = "savePetCare",method = RequestMethod.POST)
    public ResponseEntity<Void> savePetCare(@RequestParam("hostId") String hostId, @RequestParam("petId") String petId,
                                            @RequestParam("beginTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date beginTime,
                                            @RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime ) {
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("新增宠物寄养， hostId = {}，petId = {}", hostId,petId);
            }

            // 保存商品    //处理事务，同一个服务的同一个方法

            Boolean bool = this.petService.savePetCare(petId,hostId,beginTime,endTime);
            if (!bool) {
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("新增宠物寄养失败， hostId = {}，petId = {}", hostId,petId);
                }
                // 保存失败
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增宠物寄养成功， hostId = {}，beautyId = {}，petId = {}", hostId,petId);
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            LOGGER.error("新增宠物寄养出错! hostId = {}，petId = {}", hostId,petId, e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }





    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public ResponseEntity<Void> updatePet(Pet pet) {
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("编辑宠物， pet = {}", pet);
            }

            pet.setUpdated(new Date());
            // 编辑商品
            Boolean bool = this.petService.updatePet(pet);
            if (!bool) {
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("编辑宠物失败， pet = {}", pet);
                }
                // 保存失败, 500
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("编辑宠物成功， petId = {}", pet.getId());
            }
            // 204
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            LOGGER.error("编辑宠物出错! pet = " + pet, e);
        }
        // 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }



    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public ResponseEntity<Void> deletePet(@RequestParam(value = "ids") String ids) {
        try {
            /*if (id.longValue() == 0) {
                // 没有传递参数，响应状态码400
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }*/
            String[] idArray = ids.split(",");
            List<Long> list = new ArrayList<>();
            for(int i = 0; i <idArray.length; i++) {
                petService.deletePet(Long.valueOf(idArray[i]));
            }

            // 删除成功，响应204
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 删除失败，响应500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


    @RequestMapping(value = "petBeautyDel",method = RequestMethod.POST)
    public ResponseEntity<Void> petBeautyDel(@RequestParam(value = "ids") String ids) {
        try {
            /*if (id.longValue() == 0) {
                // 没有传递参数，响应状态码400
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }*/
            String[] idArray = ids.split(",");
            List<Long> list = new ArrayList<>();
            for(int i = 0; i <idArray.length; i++) {
                petService.deletePetBeauty(Long.valueOf(idArray[i]));
            }

            // 删除成功，响应204
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 删除失败，响应500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


    @RequestMapping(value = "petCareDel",method = RequestMethod.POST)
    public ResponseEntity<Void> petCareDel(@RequestParam(value = "ids") String ids) {
        try {
            /*if (id.longValue() == 0) {
                // 没有传递参数，响应状态码400
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }*/
            String[] idArray = ids.split(",");
            List<Long> list = new ArrayList<>();
            for(int i = 0; i <idArray.length; i++) {
                petService.deletePetCare(Long.valueOf(idArray[i]));
            }

            // 删除成功，响应204
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 删除失败，响应500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }



}
