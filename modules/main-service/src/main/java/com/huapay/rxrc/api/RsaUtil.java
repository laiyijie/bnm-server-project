package com.huapay.rxrc.api;

import javax.crypto.Cipher;

import org.springframework.core.io.Resource;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.Signature;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * RSA加解密工具
 *
 * @author flasfr
 * @version 1.1
 * @date 2015年3月4日
 */
public class RsaUtil {
    private static final String TRANS_FORMATION = "RSA/ECB/PKCS1Padding";
    private static final String SIGNATURE_ALGORITHM = "MD5withRSA";
    public static final String KEY_ALGORITHM = "RSA";
    public static final int PUBLIC_KEY = 1;
    public static final int PRIVATE_KEY = 2;

    /**
     * 使用公钥加密数据
     *
     * @param publicKey 公钥串
     * @param data      原始数据
     * @return 密文
     */
    public static byte[] encrypt(String publicKey, byte[] data) {
        RSAPublicKey key = (RSAPublicKey) createKey(publicKey, PUBLIC_KEY);
        try {
            Cipher cipher = Cipher.getInstance(TRANS_FORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(data);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用公钥加密数据
     *
     * @param publicKey 公钥串
     * @param data      原始数据
     * @return base64 密文
     */
    public static String encrpyt(String publicKey, byte[] data) {
        byte[] temp = encrypt(publicKey, data);
        return Base64.getEncoder().encodeToString(temp);
    }

    /**
     * 私钥解密
     *
     * @param privateKey 私钥串
     * @param data       密文
     * @return 原文
     */
    public static byte[] decrpyt(final String privateKey, final byte[] data) {
        RSAPrivateKey key = (RSAPrivateKey) createKey(privateKey, PRIVATE_KEY);
        try {
            Cipher cipher = Cipher.getInstance(TRANS_FORMATION);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] result = cipher.doFinal(data);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 私钥做数字签名
     *
     * @param privateKey 私钥串
     * @param data       base64密文
     * @return
     */
    public static String sign(String privateKey, byte[] data) {
        RSAPrivateKey key = (RSAPrivateKey) createKey(privateKey, PRIVATE_KEY);
        try {
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(key);
            signature.update(data);
            return Base64.getEncoder().encodeToString(signature.sign());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 公钥做验签
     *
     * @param publicKey 公钥串
     * @param data
     * @param sign
     * @return
     */
    public static boolean verify(String publicKey, byte[] data, String sign) {
        RSAPublicKey key = (RSAPublicKey) createKey(publicKey, PUBLIC_KEY);
        try {
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(key);
            signature.update(data);
            // 验证签名是否正常
            return signature.verify(Base64.getDecoder().decode(sign));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 通过密钥串创建key
     *
     * @param key 密钥串
     * @return 密钥实例
     */
    public static RSAKey createKey(String key, int keyType) {
        try {
            EncodedKeySpec keySpec = null;
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            if (PUBLIC_KEY == keyType) {
                keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(key));
                return (RSAKey) keyFactory.generatePublic(keySpec);
            } else if (PRIVATE_KEY == keyType) {
                keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(key));
                return (RSAKey) keyFactory.generatePrivate(keySpec);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从文件读取密钥
     *
     * @param path 文件路径
     * @return 密钥
     * @throws Exception
     */
    public static String readKey(String path) throws Exception {
    	
        InputStream in = new FileInputStream(path);
        ObjectInputStream inStream = new ObjectInputStream(in);
        Object readObject = inStream.readObject();
        in.close();
        return Base64.getEncoder().encodeToString(((Key) readObject).getEncoded());
    }
    
    public static String readKey(Resource res) throws Exception{
        InputStream in = res.getInputStream();
        ObjectInputStream inStream = new ObjectInputStream(in);
        Object readObject = inStream.readObject();
        in.close();
        return Base64.getEncoder().encodeToString(((Key) readObject).getEncoded());
    }
}
