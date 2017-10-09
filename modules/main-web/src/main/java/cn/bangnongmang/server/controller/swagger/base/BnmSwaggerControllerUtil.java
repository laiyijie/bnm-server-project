package cn.bangnongmang.server.controller.swagger.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by admin on 2017-04-12.
 */
public class BnmSwaggerControllerUtil {
    public static void setSession(HttpServletRequest request, String key, Object value) {

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute(key, value);
    }

    public static String getSessionForString(HttpServletRequest request, String key) {
        HttpSession httpSession = request.getSession();
        return (String) httpSession.getAttribute(key);
    }

    public static Long getSessionForLong(HttpServletRequest request, String key) {
        HttpSession httpSession = request.getSession();
        return (Long) httpSession.getAttribute(key);
    }

    public static Object getSession(HttpServletRequest request, String key) {
        HttpSession httpSession = request.getSession();
        return httpSession.getAttribute(key);
    }

    public static String getTrace(Throwable t) {
        StringWriter stringWriter= new StringWriter();
        PrintWriter writer= new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer= stringWriter.getBuffer();
        return buffer.toString();
    }
}
