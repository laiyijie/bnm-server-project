package com.huapay.rxrc.api;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * AES 加解密 Created by flasfr on 15/09/?.
 */
public final class AesUtil {
    private static final String AES_PADDING = "AES";
    private Cipher encrpytCipher = null;
    private Cipher decryptCipher = null;

    public enum AesLevel {
        LEVEL_128(128), LEVEL_192(192), LEVEL_256(256);

        private final int level;

        AesLevel(int value) {
            this.level = value;
        }

        public int getLevel() {
            return level;
        }
    }

    private AesUtil() {
    }

    /**
     * 随机生成一个AES密钥
     */
    public static String createNewKey(AesLevel level) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(level.getLevel(), new SecureRandom());
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            return Base64.getEncoder().encodeToString(enCodeFormat);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 创建AES算法实例，实例创建后密钥不可修改
     *
     * @param keyBytes 密钥串
     * @return AES算法实例
     */
    public static AesUtil createInstance(byte[] keyBytes) {

        try {
            SecretKeySpec skspec = new SecretKeySpec(keyBytes, "AES");
            AesUtil inst = new AesUtil();
            inst.encrpytCipher = Cipher.getInstance(AES_PADDING);
            inst.encrpytCipher.init(Cipher.ENCRYPT_MODE, skspec);

            inst.decryptCipher = Cipher.getInstance(AES_PADDING);
            inst.decryptCipher.init(Cipher.DECRYPT_MODE, skspec);
            return inst;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 加密
     */
    public String encrypt(byte[] src) {
        try {
            return Base64.getEncoder().encodeToString(encrpytCipher.doFinal(src));
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解密
     */
    public byte[] decrypt(String src) {
        try {
            return decryptCipher.doFinal(Base64.getDecoder().decode(src));
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
            return null;
        }
    }
}