package cn.bangnongmang.admin.swagger.controller.base;

import cn.bangnongmang.admin.enums.ServerSessionAttr;
import cn.bangnongmang.admin.swagger.model.BusinessError;
import cn.bangnongmang.admin.util.BnmLogUtil;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.server.log.BLog;
import cn.bangnongmang.size.io.swagger.model.Error;
import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static cn.bangnongmang.admin.util.BnmLogUtil.getTrace;

/**
 * Created by admin on 2017-05-10.
 */
@ControllerAdvice("cn.bangnongmang.admin.swagger")
public class ExceptionAdvice {
    private static Logger logger = LogManager.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponseEntity<BusinessError> handleBusinessException(BusinessException businessException) {
        BusinessError error = new BusinessError();
        error.setCode(businessException.errorCode);
        error.setMessage(businessException.errorMessage);
        return new ResponseEntity<BusinessError>(error, HttpStatus.valueOf(417));
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<BusinessError> handleBusinessException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        String username = (String) request.getSession()
                                          .getAttribute(
                                                  "user");
        BusinessError error = new BusinessError();
        error.setCode(800);
        error.setMessage("糟糕，服务器好像有点儿问题");
        BLog.errorJsonLogBuilder()
            .addErrorMessage(ex.getMessage())
            .put(request, ServerSessionAttr.USER)
            .put(response)
            .put(ex)
            .log();
        return new ResponseEntity<BusinessError>(error, HttpStatus.valueOf(417));
    }

}
