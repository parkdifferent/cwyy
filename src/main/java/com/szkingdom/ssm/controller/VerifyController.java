package com.szkingdom.ssm.controller;

/**
 * Created by tianf on 2017/5/14.
 */


import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.szkingdom.ssm.util.CodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by tianf on 2017/5/14.
 */


@Controller
@RequestMapping("/vefify")
public class VerifyController {

    private static final long serialVersionUID = -712962347758638769L;
    private InputStream imageStream;

    public InputStream getImageStream() {
        return imageStream;
    }

    public void setImageStream(InputStream imageStream) {
        this.imageStream = imageStream;
    }


    @RequestMapping(value = "vf",method = RequestMethod.GET)
    public String execute(HttpSession session) {

        Map<String, BufferedImage> map = CodeUtil.createImage();
        String code = map.keySet().iterator().next();//获取图片字符
        //Map<String, Object> session = ActionContext.getContext().getSession();//ActionContext 工具类
        //session.put("code", code);
        session.setAttribute("code",code);
        System.out.println(code);
        BufferedImage image = map.get(code);//获取图片
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
        try {
            encoder.encode(image);
            byte[] bits = bos.toByteArray();
            imageStream = new ByteArrayInputStream(bits);
            return "login";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "login";
    }
}
