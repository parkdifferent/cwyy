package com.szkingdom.ssm.controller;

import com.szkingdom.ssm.bean.EasyUIResult;
import com.szkingdom.ssm.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by tianf on 2016/12/15.
 */

@Controller
@RequestMapping("user")
public class MyUserController {

    @Autowired
    private MyUserService myUserService;

    /**
     * 查询用户列表
     *
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public EasyUIResult queryUserList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                      @RequestParam(value = "rows", defaultValue = "30") Integer rows) {
        EasyUIResult easyUIResult = this.myUserService.queryUserList(page, rows);
        return easyUIResult;
    }
}
