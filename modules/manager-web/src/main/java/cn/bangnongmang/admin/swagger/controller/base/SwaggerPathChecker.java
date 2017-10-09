package cn.bangnongmang.admin.swagger.controller.base;

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
