package cn.bangnongmang.admin.interceptor;

import cn.bangnongmang.admin.util.BnmLogUtil;
import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2017-04-25.
 */
public class LoggerInterceptor implements HandlerInterceptor {
    private Logger logger = LogManager.getLogger(LoggerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        String username = (String) request.getSession()
                                          .getAttribute(
                                                  "user");

        logger.info(
                "[REQUEST USERNAME] {}  [REQUEST URL] {} [REQUEST PARAM CHUNK] {}  " +
                        "[REQUEST USERAGENT] {}  [REQUEST ALLHEADER] {}",
                username,
                BnmLogUtil.getRequestUrl(request), BnmLogUtil.getRequestParams(request), request.getHeader
                        ("User-Agent"), BnmLogUtil.getAllHeaders(request));

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
