package cn.bangnongmang.service.outerservice.util;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

@Service
public class ServiceUtil {

    private final static String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final static String allNumber = "0123456789";


    public long getCurrTimeStampInSecond() {

        return System.currentTimeMillis() / 1000;
    }

    public long getLong(Object version) {

        long keyVersion = 0L;

        if (version instanceof Integer) {

            keyVersion = Integer.toUnsignedLong((int) version);

        } else if (version instanceof Long) {

            keyVersion = (long) version;
        }

        return keyVersion;
    }

    public String generateString(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int index = ThreadLocalRandom.current()
                                         .nextInt(allChar.length());
            sb.append(allChar.charAt(index));
        }
        return sb.toString();
    }

    public String generateRandomNumber(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int index = ThreadLocalRandom.current()
                                         .nextInt(allNumber.length());
            sb.append(allNumber.charAt(index));
        }
        return sb.toString();
    }

    public String generateId(String addone, int len, int randomLen) {
        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
        String id = time + addone.substring(addone.length() - len, addone.length()) + generateRandomNumber(randomLen);
        return id;
    }

    public String generateId(Long a, int len, int randomLen) {
        String addone = String.valueOf(a);
        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
        if (addone.length() < len) {
            return time + addone + generateRandomNumber(randomLen);
        }
        String id = time + addone.substring(addone.length() - len, addone.length()) + generateRandomNumber(randomLen);
        return id;
    }
}
