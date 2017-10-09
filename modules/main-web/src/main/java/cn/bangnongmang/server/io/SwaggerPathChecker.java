package cn.bangnongmang.server.io;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by admin on 2017/4/11.
 */
public class SwaggerPathChecker {
    public static String reg = ".*/api/.*";

    public  static boolean isSwaggerUri(String path){
        if (path.matches(reg)){
            return true;
        }
        return false;
    }
}
