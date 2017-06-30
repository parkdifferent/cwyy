package com.szkingdom.ssm.controller;

import com.szkingdom.ssm.bean.EasyUIResult;
import com.szkingdom.ssm.entity.Admin;
import com.szkingdom.ssm.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tianf on 2017/5/15.
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    AdminService adminService;

    @RequestMapping(value = "adminList",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<EasyUIResult> queryAdminList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "30") Integer rows,HttpSession session) {
        try {
            return ResponseEntity.ok(this.adminService.queryAdminList(page, rows, session));
        } catch (Exception e) {
            LOGGER.error("查询员工列表出错! page = " + page + ", rows = " + rows, e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }


/*    @RequestMapping(value = "save",method = RequestMethod.POST)
    public ResponseEntity<Void> saveAdmin(Admin admin) {
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("新增员工， admin = {}", admin);
            }

            // 保存商品    //处理事务，同一个服务的同一个方法
            admin.setCreated(new Date());
            admin.setUpdated(new Date());
            Boolean bool = this.adminService.saveAdmin(admin);
            if (!bool) {
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("新增员工失败， admin = {}", admin);
                }
                // 保存失败
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增员工成功， adminId = {}", admin.getId());
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            LOGGER.error("新增员工出错! admin = " + admin, e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }*/


    @RequestMapping(value = "save",method = RequestMethod.POST)
    public ModelAndView saveAdmin(Admin admin) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("新增员工， admin = {}", admin);
            }

            // 保存商品    //处理事务，同一个服务的同一个方法
            admin.setCreated(new Date());
            admin.setUpdated(new Date());
            Boolean bool = this.adminService.saveAdmin(admin);
            if (!bool) {
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("新增员工失败， admin = {}", admin);
                }
                // 保存失败
                return modelAndView;
            }
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增员工成功， adminId = {}", admin.getId());
            }
            modelAndView.setViewName("login");
            return modelAndView;
        } catch (Exception e) {
            LOGGER.error("新增员工出错! admin = " + admin, e);
        }
        return modelAndView;
    }



    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public ResponseEntity<Void> updateAdmin(Admin admin) {
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("编辑员工， admin = {}", admin);
            }

            admin.setUpdated(new Date());
            // 编辑商品
            Boolean bool = this.adminService.updateAdmin(admin);
            if (!bool) {
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("编辑员工失败， admin = {}", admin);
                }
                // 保存失败, 500
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("编辑员工成功， adminId = {}", admin.getId());
            }
            // 204
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            LOGGER.error("编辑员工出错! admin = " + admin, e);
        }
        // 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }



    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public ResponseEntity<Void> deleteAdmin(@RequestParam(value = "ids") String ids) {
        try {
            /*if (id.longValue() == 0) {
                // 没有传递参数，响应状态码400
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }*/
            String[] idArray = ids.split(",");
            List<Long> list = new ArrayList<>();
            for(int i = 0; i <idArray.length; i++) {
                adminService.deleteAdmin(Long.valueOf(idArray[i]));
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
