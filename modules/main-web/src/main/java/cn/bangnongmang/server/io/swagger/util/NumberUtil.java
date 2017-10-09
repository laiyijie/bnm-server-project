package cn.bangnongmang.server.io.swagger.util;

/**
 * Created by admin on 2017-04-21.
 */
public class NumberUtil {
    public static Long convertToMillisTime(Long date) {
        if (date == null)
            return null;
        return date * 1000;
    }
}
