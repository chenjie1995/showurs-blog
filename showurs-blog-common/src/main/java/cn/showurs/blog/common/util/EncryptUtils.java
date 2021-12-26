package cn.showurs.blog.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class EncryptUtils {
    private static final String SHA_256 = "SHA-256";
    private static final String SHA_512 = "SHA-512";

    private EncryptUtils() {}

    public static String sha256(final String text) {
        return encryptText(text, SHA_256);
    }

    public static String sha512(final String text) {
        return encryptText(text, SHA_512);
    }

    private static String encryptText(final String text, final String type) {
        // 返回值
        String result = null;

        // 是否是有效字符串
        if (text != null && text.length() > 0) {
            try {
                // SHA 加密开始
                // 创建加密对象 并传入加密类型
                MessageDigest messageDigest = MessageDigest.getInstance(type);
                // 传入要加密的字符串
                messageDigest.update(text.getBytes());
                // 得到 byte 类型结果
                byte[] byteBuffer = messageDigest.digest();

                // 将 byte 转换为 string
                StringBuilder hexString = new StringBuilder();
                // 遍历 byte buffer
                for (byte temp : byteBuffer) {
                    String hex = Integer.toHexString(0xff & temp);
                    if (hex.length() == 1) {
                        hexString.append('0');
                    }
                    hexString.append(hex);
                }
                // 得到返回结果
                result = hexString.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
