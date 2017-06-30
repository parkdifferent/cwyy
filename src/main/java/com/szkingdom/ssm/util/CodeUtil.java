package com.szkingdom.ssm.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by tianf on 2017/5/14.
 */


public class CodeUtil {
    private static final String[] chars = { "0", "1", "2", "3", "4", "5", "6",
            "7", "8", "9", "a", "b", "c" };
    private static final int SIZE = 4;
    private static final int LINES = 8;
    private static final int WIDTH = 73;
    private static final int HEIGHT = 27;
    private static final int FONT_SIZE = 16;

    public static Map<String,BufferedImage> createImage() {
        StringBuffer sb = new StringBuffer();

        BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
                BufferedImage.TYPE_INT_RGB);

        Graphics graphic = image.getGraphics();


        // 生成随机类
        Random random = new Random();
        // 设定背景色
        graphic.setColor(getRandColor(200, 250));
        graphic.fillRect(0, 0, WIDTH, HEIGHT);

        // 设定字体
        graphic.setFont(new Font("Times New Roman", Font.PLAIN, 24));

        // 画边框
        graphic.setColor(new Color(80,80,80));
        graphic.drawRect(0,0,WIDTH-1,HEIGHT-1);

        graphic.setColor(getRandColor(160, 200));
        for (int i = 0; i < 255; i++) {
            int x = random.nextInt(WIDTH);
            int y = random.nextInt(HEIGHT);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            graphic.drawLine(x, y, x + xl, y + yl);
        }

        // 取随机产生的认证码(4位数字)
        String sRand = "";
        for (int i = 0; i < 4; i++) {
            String rand = randomInt(1).toUpperCase();
            sRand += rand;
            // 将认证码显示到图象中
            graphic.setColor(new Color(20 + random.nextInt(110), 20 + random
                    .nextInt(110), 20 + random.nextInt(110)));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            graphic.drawString(rand, 14 * i + 6, 24);
        }
        // 将认证码存入SESSION
        Map<String,BufferedImage> map = new HashMap<String,BufferedImage>();
        map.put(sRand, image);
        return map;



        /*//画随机字符
        for(int i=1;i<=SIZE;i++){
            int r = ran.nextInt(chars.length);
            graphic.setColor(getRandomColor());
            graphic.setFont(new Font(null,Font.BOLD+Font.ITALIC,FONT_SIZE));
            graphic.drawString(chars[r],(i-1)*WIDTH/SIZE , HEIGHT/2);
            sb.append(chars[r]);//将字符保存，存入Session
        }
        //画干扰线
        for(int i=2;i<=LINES;i++){
            graphic.setColor(getRandomColor());
            graphic.drawLine(ran.nextInt(WIDTH), ran.nextInt(HEIGHT), ran.nextInt(WIDTH),ran.nextInt(HEIGHT));
        }
        Map<String,BufferedImage> map = new HashMap<String,BufferedImage>();
        map.put(sb.toString(), image);
        return map;*/
    }

    /*public static Color getRandomColor(){
        Random ran = new Random();
        Color color = new Color(ran.nextInt(256),ran.nextInt(256),ran.nextInt(256));
        return color;
    }*/

    public static Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }



    public static final String randomInt(int length) {
        if (length < 1) {
            return null;
        }
        Random randGen = new Random();
        char[] numbersAndLetters = ("0123456789").toCharArray();

        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(10)];
        }
        return new String(randBuffer);
    }
}
