package cn.showurs.blog.common.vo.user;

import java.awt.image.BufferedImage;

public class CaptchaImage {
    private String key;
    private BufferedImage image;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
