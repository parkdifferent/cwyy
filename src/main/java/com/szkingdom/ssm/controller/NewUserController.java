package com.szkingdom.ssm.controller;

import com.szkingdom.ssm.entity.MyUser;
import com.szkingdom.ssm.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RequestMapping("/user")
@Controller
public class NewUserController {

    @Autowired
    private MyUserService myUserService;

    // 根据用户id查询用户信息
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<MyUser> queryUserById(@PathVariable("id") Long id) {
        try {
            MyUser user = this.myUserService.queryUserById(id);
            if (null == user) {
                // 资源不存在，响应404
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            // 资源存在，响应200
            // return ResponseEntity.status(HttpStatus.OK).body(user);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * 新增用户
     * 
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> saveUser(MyUser user) {
        try {
            Boolean bool = this.myUserService.saveUser(user);
            if (bool) {
                // 新增成功，响应201
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 新增失败，响应500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateUser(MyUser user) {
        try {
            Boolean bool = this.myUserService.updateUser(user);
            if (bool) {
                // 更新成功，响应204
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 新增失败，响应500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * 删除用户
     * 
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@RequestParam(value = "id", defaultValue = "0") Long id) {
        try {
            if (id.longValue() == 0) {
                // 没有传递参数，响应状态码400
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            Boolean bool = this.myUserService.deleteUser(id);
            if (bool) {
                // 删除成功，响应204
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 删除失败，响应500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
