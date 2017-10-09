package cn.bangnongmang.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

import cn.bangnongmang.admin.util.BnmLogUtil;
import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.admin.util.JsonResult;
import cn.bangnongmang.admin.util.JsonResultFactory;

import javax.servlet.http.HttpServletRequest;

import static cn.bangnongmang.admin.util.BnmLogUtil.*;

@ControllerAdvice("cn.bangnongmang.admin.controller" )
public class AdminExceptionAdvice {

    @Autowired
    private JsonResultFactory jasonResultFactory;

    private static Logger logger = LogManager.getLogger(AdminExceptionAdvice.class);

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<JsonResult> handleBusinessException(BusinessException businessException) {
        JsonResult jsonResult = jasonResultFactory.makeJsonResult("error", businessException.errorMessage);
        logger.info("[RESPONSE] {}", JSON.toJSONString(jsonResult));
        return new ResponseEntity<>(jsonResult, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<JsonResult> handleBusinessException6(Exception exception, HttpServletRequest request) {
        String username = (String) request.getSession()
                                          .getAttribute(
                                                  "user");
        JsonResult jsonResult = jasonResultFactory.makeJsonResult("error", "糟糕，服务器好像有点儿问题");
        logger.info("[RESPONSE] {}", JSON.toJSONString(jsonResult));
        logger.error("[ERROR] [REQUEST USERNAME] {}  [REQUEST URL] {} [REQUEST PARAM CHUNK] {}  " +
                        "[REQUEST USERAGENT] {}  [REQUEST ALLHEADER] {} [TRACE] {}",
                username,
                BnmLogUtil.getRequestUrl(request), BnmLogUtil.getRequestParams(request), request.getHeader
                        ("User-Agent"), BnmLogUtil.getAllHeaders(request), getTrace(exception));
        return new ResponseEntity<>(jsonResult, HttpStatus.OK);
    }


}
