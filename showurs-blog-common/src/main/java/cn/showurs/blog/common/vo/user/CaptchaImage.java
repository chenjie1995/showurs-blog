package cn.showurs.blog.common.vo.user;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * 图片验证码
 *
 * Created by CJ on 2018/12/27 17:03.
 */
public class CaptchaImage implements Serializable {
    private static final long serialVersionUID = -5442880512983501437L;
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
