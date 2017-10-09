package cn.bangnongmang.admin.util;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2017-04-25.
 */
public class BnmLogUtil {


    public static String getRequestUrl(HttpServletRequest request) {

        return request.getRequestURL()
                      .toString();
    }

    public static String getRequestParams(HttpServletRequest request) {

        String re = JSON.toJSONString(request.getParameterMap());

        if (re.length() > 256) {
            return re.substring(0, 256);
        }
        return re;
    }

    public static String getAllHeaders(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, Object> headers = new HashMap<>();
        for (; headerNames.hasMoreElements(); ) {
            String headerName = headerNames.nextElement();
            headers.put(headerName, request.getHeader(headerName));
        }
        return JSON.toJSONString(headers);
    }


    public static String getTrace(Throwable t) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer = stringWriter.getBuffer();
        return buffer.toString();
    }
}
