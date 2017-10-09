package cn.bangnongmang.server.controller.android.base;

import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.server.io.android.AndroidOutputException;
import cn.bangnongmang.server.io.android.AndroidServerResponseFactory;
import cn.bangnongmang.server.io.interceptor.ServerSessionAttr;
import cn.bangnongmang.server.io.wechat.WxOutputException;
import cn.bangnongmang.server.log.BLog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by admin on 2017-04-26.
 */
@ControllerAdvice({"cn.bangnongmang.server.controller.android", "cn.bangnongmang.server.controller.wechat"})
public class AndroidExceptionHandler {
    private static final Logger logger = LogManager.getLogger(AndroidExceptionHandler.class);
    @Autowired
    private AndroidServerResponseFactory factory;
    private HttpHeaders headers = new HttpHeaders();

    private static String DEFAULT_ERROR_MESSAGE = "糟糕，服务器好像有点问题";

    public AndroidExceptionHandler() {
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
    }

    @ExceptionHandler(value = {BusinessException.class})
    public ResponseEntity<String> handleBusinessException(HttpServletRequest request,
                                                          HttpServletResponse response,
                                                          BusinessException
                                                                  businessException) {
        String errString = factory
                .showError(businessException.errorCode, businessException.errorMessage);
        return new ResponseEntity<String>(errString, headers, HttpStatus.OK);
    }

    @ExceptionHandler(value = {AndroidOutputException.class})
    public ResponseEntity<String> handleAndroidOutputException(HttpServletRequest request,
                                                               HttpServletResponse response,
                                                               AndroidOutputException
                                                                       wxEx) {
        String errString = factory.showError(wxEx.errorCode, wxEx.errorMessage);
        return new ResponseEntity<String>(errString, headers, HttpStatus.OK);
    }

    @ExceptionHandler(value = {WxOutputException.class})
    public ResponseEntity<String> handleWxOutputException(HttpServletRequest request,
                                                          HttpServletResponse response,
                                                          WxOutputException
                                                                  wxEx) {
        String errString = factory.showError(wxEx.getErrorCode(), wxEx.getErrorMessage());
        return new ResponseEntity<String>(errString, headers, HttpStatus.OK);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<String> handleException(HttpServletRequest request,
                                                  HttpServletResponse response, Exception wxEx) {
        String errorString = factory.showError(800, DEFAULT_ERROR_MESSAGE);
        BLog.errorJsonLogBuilder()
            .put(request, ServerSessionAttr.SESSION_UID)
            .put(response)
            .put(wxEx)
            .log();
        return new ResponseEntity<String>(errorString, headers, HttpStatus.OK);
    }


}
