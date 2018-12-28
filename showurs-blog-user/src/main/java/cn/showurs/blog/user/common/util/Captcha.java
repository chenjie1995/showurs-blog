package cn.showurs.blog.user.common.util;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by CJ on 2018/12/27 11:00.
 */
public class Captcha {
    private static final String[] FONT_FAMILY = {"Microsoft YaHei", "console"};
    private static final int LINE_COUNT = 20;

    public static BufferedImage createCaptchaImage(String text, Integer width, Integer height) {
        Random random = new Random();

        // 验证码字体大小
        int fontSize = height * 2 / 3;

        // 创建BufferedImage对象,其作用相当于一图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 创建Graphics2D对象，Graphics对象的扩展,其作用相当于画笔
        Graphics2D g2d = (Graphics2D) image.getGraphics();

        Font font = new Font(FONT_FAMILY[random.nextInt(FONT_FAMILY.length)], Font.PLAIN, fontSize); // 定义字体样式
        g2d.setFont(font); // 设置字体

        // 绘制背景
        g2d.setColor(getRandColor(200, 250));
        g2d.fillRect(0, 0, width, height);

        // 绘制颜色和位置全部为随机产生的线条
        g2d.setColor(getRandColor(180, 200));
        for (int i = 0; i < LINE_COUNT; i++) {
            int x = random.nextInt(width - 1);
            int y = random.nextInt(height - 1);
            int x1 = random.nextInt(width / 3);
            int y1 = random.nextInt(height / 3);

            Line2D line = new Line2D.Double(x, y, x + x1, y + y1);
            g2d.setStroke(new BasicStroke(3.0f));

            //消除直线锯齿
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // 绘制直线
            g2d.draw(line);
        }


        // 消除文字锯齿
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // 绘制验证码
        g2d.setColor(getRandColor(80, 200));
        g2d.rotate(random.nextInt(6) * 3.14 / 180, 0, 0);
        g2d.drawString(text, fontSize + random.nextInt(width - fontSize * 4), fontSize);


        // 释放g所占用的系统资源
        g2d.dispose();

        return image;
    }

    /* 该方法主要作用是获得随机生成的颜色 */
    private static Color getRandColor(int s, int e) {
        Random random = new Random();
        if (s > 255)
            s = 255;
        if (e > 255)
            e = 255;
        int r, g, b;
        r = s + random.nextInt(e - s); // 随机生成RGB颜色中的r值
        g = s + random.nextInt(e - s); // 随机生成RGB颜色中的g值
        b = s + random.nextInt(e - s); // 随机生成RGB颜色中的b值
        return new Color(r, g, b);
    }
}
