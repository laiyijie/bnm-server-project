package cn.bangnongmang.service.outerservice.util;

import java.io.IOException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;

public class DesHelper {
	
	private final static String DES="DES";
	

    public static String encrypt(String data, String key) throws Exception {
        byte[] bt = encrypt(data.getBytes(), key.getBytes());
        String strs = Base64.encodeBase64String(bt);
        return strs;
    }
 

    public static String decrypt(String data, String key) throws IOException,
            Exception {
        if (data == null)
            return null;
        byte[] buf = Base64.decodeBase64(data);
        byte[] bt = decrypt(buf,key.getBytes());
        return new String(bt);
    }
 

    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {

        DESKeySpec dks = new DESKeySpec(key);
 

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
 

        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
 
        IvParameterSpec iv = new IvParameterSpec(key);  
        

        cipher.init(Cipher.ENCRYPT_MODE, securekey, iv);
 
        return cipher.doFinal(data);
    }
     

    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {

        DESKeySpec dks = new DESKeySpec(key);
 

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
 

        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
 
        IvParameterSpec iv = new IvParameterSpec(key);  

        cipher.init(Cipher.DECRYPT_MODE, securekey, iv);
 
        return cipher.doFinal(data);
    }

}
