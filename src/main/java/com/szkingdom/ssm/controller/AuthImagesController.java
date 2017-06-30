package com.szkingdom.ssm.controller;

import com.szkingdom.ssm.util.VerifyCodeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by admin on 2017/5/11.
 */

@Controller
public class AuthImagesController {

    private static final Logger logger = LoggerFactory.getLogger(AuthImagesController.class);

/*    @Reference(version = Const.XBIN_STORE_REDIS_VERSION)
    private JedisClient jedisClient;

    @Value("${redisKey.prefix.verifycode}")
    private String VERIFYCODE;

    @Value("${redisKey.expire_time}")
    private Integer EXPIRE_TIME;*/

    @RequestMapping(value = "/verify/image", method = RequestMethod.GET)
    public void verifyimage(/*String uid, */HttpServletResponse response, HttpSession session) {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        //生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        //存入Redis

        /*String key = VERIFYCODE + uid;
        jedisClient.set(key, verifyCode);

        jedisClient.expire(key, EXPIRE_TIME);*/

        //存入session
        session.setAttribute("code",verifyCode);

        //生成图片
        int w = 100, h = 36;
        try {
            VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
        } catch (IOException e) {
            logger.error("验证码生成失败", e);
        }

    }
}
