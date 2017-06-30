package com.szkingdom.ssm.controller;

import com.szkingdom.ssm.bean.EasyUIResult;
import com.szkingdom.ssm.entity.User;
import com.szkingdom.ssm.service.UserService;
import com.szkingdom.ssm.service.impl.UserServiceImpl;
import org.apache.commons.lang3.StringUtils;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tianf on 2017/5/15.
 */

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = "userList",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<EasyUIResult> queryUserList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "30") Integer rows) {
        try {
            return ResponseEntity.ok(this.userService.queryUserList(page, rows));
        } catch (Exception e) {
            LOGGER.error("查询用户列表出错! page = " + page + ", rows = " + rows, e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }


    @RequestMapping(value = "save",method = RequestMethod.POST)
    public ResponseEntity<Void> saveUser(User user) {
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("新增用户， user = {}", user);
            }

            // 保存商品    //处理事务，同一个服务的同一个方法
            user.setCreated(new Date());
            user.setUpdated(new Date());
            Boolean bool = this.userService.saveUser(user);
            if (!bool) {
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("新增用户失败， user = {}", user);
                }
                // 保存失败
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增用户成功， userId = {}", user.getId());
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            LOGGER.error("新增用户出错! user = " + user, e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }



    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public ResponseEntity<Void> updateUser(User user) {
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("编辑用户， user = {}", user);
            }

            user.setUpdated(new Date());
            // 编辑商品
            Boolean bool = this.userService.updateUser(user);
            if (!bool) {
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("编辑用户失败， user = {}", user);
                }
                // 保存失败, 500
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("编辑用户成功， userId = {}", user.getId());
            }
            // 204
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            LOGGER.error("编辑用户出错! user = " + user, e);
        }
        // 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }



    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public ResponseEntity<Void> deleteUser(@RequestParam(value = "ids") String ids) {
        try {
            /*if (id.longValue() == 0) {
                // 没有传递参数，响应状态码400
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }*/
            String[] idArray = ids.split(",");
            List<Long> list = new ArrayList<>();
            for(int i = 0; i <idArray.length; i++) {
                userService.deleteUser(Long.valueOf(idArray[i]));
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
