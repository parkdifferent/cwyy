package com.szkingdom.ssm.controller;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.szkingdom.ssm.bean.BeautyDTO;
import com.szkingdom.ssm.bean.EasyUIResult;
import com.szkingdom.ssm.entity.Beauty;
import com.szkingdom.ssm.service.BeautyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tianf on 2017/5/15.
 */

@Controller
@RequestMapping("/beauty")
public class BeautyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeautyController.class);

    @Autowired
    BeautyService beautyService;

    @RequestMapping(value = "beautyList",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<EasyUIResult> queryBeautyList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "30") Integer rows) {
        try {
            return ResponseEntity.ok(this.beautyService.queryBeautyList(page, rows));
        } catch (Exception e) {
            LOGGER.error("查询美容项目列表出错! page = " + page + ", rows = " + rows, e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @RequestMapping(value = "beautyProject",method = RequestMethod.POST)
    @ResponseBody
    public List<BeautyDTO> queryBeautyProject() {
        List<BeautyDTO> beautyDTOList = new ArrayList<>();

        List<Beauty> beauties = beautyService.queryBeautyList();

        for (Beauty beauty : beauties) {
            BeautyDTO beautyDTO = new BeautyDTO();
            beautyDTO.setId(beauty.getId());
            beautyDTO.setName(beauty.getName());
            beautyDTOList.add(beautyDTO);
        }
        return beautyDTOList;
    }


    @RequestMapping(value = "save",method = RequestMethod.POST)
    public ResponseEntity<Void> saveBeauty(Beauty beauty) {
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("新增美容项目， beauty = {}", beauty);
            }

            // 保存商品    //处理事务，同一个服务的同一个方法
            beauty.setCreated(new Date());
            beauty.setUpdated(new Date());
            Boolean bool = this.beautyService.saveBeauty(beauty);
            if (!bool) {
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("新增美容项目失败， beauty = {}", beauty);
                }
                // 保存失败
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增美容项目成功， beautyId = {}", beauty.getId());
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            LOGGER.error("新增美容项目出错! beauty = " + beauty, e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }



    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public ResponseEntity<Void> updateBeauty(Beauty beauty) {
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("编辑美容项目， beauty = {}", beauty);
            }

            beauty.setUpdated(new Date());
            // 编辑商品
            Boolean bool = this.beautyService.updateBeauty(beauty);
            if (!bool) {
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("编辑美容项目失败， beauty = {}", beauty);
                }
                // 保存失败, 500
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("编辑美容项目成功， beautyId = {}", beauty.getId());
            }
            // 204
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            LOGGER.error("编辑美容项目出错! beauty = " + beauty, e);
        }
        // 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }



    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public ResponseEntity<Void> deleteBeauty(@RequestParam(value = "ids") String ids) {
        try {
            /*if (id.longValue() == 0) {
                // 没有传递参数，响应状态码400
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }*/
            String[] idArray = ids.split(",");
            List<Long> list = new ArrayList<>();
            for(int i = 0; i <idArray.length; i++) {
                beautyService.deleteBeauty(Long.valueOf(idArray[i]));
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
