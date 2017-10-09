package cn.bangnongmang.server.controller.swagger.base;

import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.server.io.interceptor.ServerSessionAttr;
import cn.bangnongmang.server.log.BLog;
import cn.bangnongmang.server.swagger.model.BusinessError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by admin on 2017/4/11.
 */
@ControllerAdvice("cn.bangnongmang.server.controller.swagger")
public class SwaggerExceptionHandler {
    private final Logger logger = LogManager.getLogger(SwaggerExceptionHandler.class);
    public static final Integer DEFAULT_ERROR_CODE = 800;
    public static final String DEFAULT_ERROR_MESSAGE = "糟糕，服务器有点问题";

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<BusinessError> handleSwaggerException(BusinessException
                                                                        businessException,
                                                                HttpServletRequest request,
                                                                HttpServletResponse response) {
        if (businessException.errorCode == 1003) {
            return new ResponseEntity<BusinessError>(HttpStatus.valueOf(420));
        }
        BusinessError businessError = new BusinessError();
        businessError.setCode(businessException.errorCode);
        businessError.setMessage(businessException.errorMessage);
        return new ResponseEntity<BusinessError>(businessError, HttpStatus.valueOf(417));
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<BusinessError> handleSwaggerException(Exception
                                                                        exception,
                                                                HttpServletRequest request,
                                                                HttpServletResponse response) {
        BusinessError businessError = new BusinessError();
        businessError.setCode(DEFAULT_ERROR_CODE);
        businessError.setMessage(DEFAULT_ERROR_MESSAGE);
        BLog.errorJsonLogBuilder()
            .put(request, ServerSessionAttr.SESSION_UID)
            .put(response)
            .put(exception)
            .log();
        return new ResponseEntity<BusinessError>(businessError, HttpStatus.valueOf(417));
    }
}
