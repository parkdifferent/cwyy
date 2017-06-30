package com.szkingdom.ssm.controller;

import com.szkingdom.ssm.entity.Admin;
import com.szkingdom.ssm.service.AdminService;
import com.szkingdom.ssm.service.impl.AdminServiceImpl;
import com.szkingdom.ssm.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * Created by tianf on 2016/4/6.
 */

@Controller
@RequestMapping("/login")
public class LoginAction{

    @Autowired
    AdminService adminService;




    @RequestMapping(value = "in",method = RequestMethod.POST)
    public ModelAndView login(HttpSession session, Admin admin) throws Exception {
        //Map session=ActionContext.getContext().getSession();
        //获取验证码
        String code=(String)session.getAttribute("code");

        //Map<String,String> msg = new HashMap<>();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("admin",admin);

        //如果是管理员登陆

            //判断验证码输入是否正确
            if(!code.equalsIgnoreCase(admin.getCode())) {
                //this.addActionError("验证码输入不正确！");
                //msg.put("codeMessage","验证码输入不正确!");
                modelAndView.addObject("codeMessage","验证码输入错误");
                modelAndView.setViewName("login");
                return modelAndView;
            }
           // user1=userService.findUserBySno(sno);
            //teacher1=teacherService.findTeacherByTno(sno);
            Admin admin1  = new Admin();
            admin1.setUserName(admin.getUserName());
            Admin admin2 =adminService.queryAdminByUserName(admin1);


            if(admin2 == null) {
                // this.addActionError("用户不存在！");
                //request.setAttribute("userMessage","用户不存在!");
                //msg.put("codeMessage","用户不存在!");
                modelAndView.addObject("userMessage","用户不存在");
                modelAndView.setViewName("login");
                return modelAndView;

            } else {
                String password1=admin1.getPassword();
                System.out.println(password1);
                /*if(!MD5Util.getMD5String(admin.getPassword()).toUpperCase().equals(admin2.getPassword())) {*/
                if(!admin.getPassword().equals(admin2.getPassword())) {
                    //this.addActionError("密码输入不正确！");
                    //request.setAttribute("passwordMessage","密码输入不正确!");
                    //msg.put("passwordMessage","密码输入不正确!");
                    modelAndView.addObject("passwordMessage","密码输入不正确");
                    modelAndView.setViewName("login");
                    return modelAndView;
                }
            }
            //session.put("userName",username);
            //request.setAttribute("userName",user1.getUserName());
            //session.put("teaId",teacher1.getTno())

        session.setAttribute("id",admin2.getId());
        modelAndView.setViewName("index");
        return modelAndView;

    }




}
