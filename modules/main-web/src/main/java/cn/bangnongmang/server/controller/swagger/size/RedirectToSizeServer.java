package cn.bangnongmang.server.controller.swagger.size;


import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.server.log.BLog;
import cn.bangnongmang.service.outerservice.SizeCounterService;
import cn.bangnongmang.size.io.swagger.api.DefaultApi;
import cn.bangnongmang.size.io.swagger.model.Error;
import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;


/**
 * Created by admin on 2017-05-16.
 */
@Aspect
@Service
public class RedirectToSizeServer {

    private DefaultApi defaultApi = null;

    @Autowired
    public RedirectToSizeServer(SizeCounterService service) {
        defaultApi = service.getDefaultApi();
    }

    @Around("execution(* cn.bangnongmang.server.controller.swagger.size.AllSizeController.*(..))")
    public ResponseEntity<?> redirectGo(ProceedingJoinPoint pjp) {
        Method[] methods = defaultApi.getClass()
                                     .getMethods();
        Method currMethod = null;

        for (Method method : methods) {
            if (method.getName()
                      .equals(pjp.getSignature()
                                 .getName())) {
                currMethod = method;
                break;
            }
        }
        if (currMethod == null) {
            BLog.errorJsonLogBuilder()
                .addErrorMessage("size function not found")
                .put("pjp_method", pjp.getSignature()
                                      .getName())
                .log();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Response<?> response = null;
        try {
            response = ((Call<?>) currMethod.invoke(defaultApi, pjp.getArgs())).execute();

            if (response == null) {
                BLog.errorJsonLogBuilder()
                    .addErrorMessage("response is null")
                    .put("pjp_method", pjp.getSignature()
                                          .getName())
                    .log();
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            BLog.businessJsonLogBuilder("sizeCounter")
                .addAction("controllerAdvice")
                .put("method", currMethod.getName())
                .put("response_body", response.body())
                .put("response_error", response.errorBody())
                .put("response_code", response.code())
                .log();
            if (response.isSuccessful()) {

                return new ResponseEntity<>(response.body(), HttpStatus.valueOf(response.code()));
            } else {
                Error error = JSON.parseObject(response.errorBody()
                                                       .string(), Error.class);
                BLog.errorJsonLogBuilder()
                    .addErrorMessage("exeption found ")
                    .put("pjp_method", pjp.getSignature()
                                          .getName())
                    .put("errorCode", error.getErrorCode())
                    .put("errorMessage", error.getErrorMessage())
                    .log();
                throw new RuntimeException();
//                throw new BusinessException(error.getErrorCode(), error.getErrorMessage());
            }
        } catch (IllegalAccessException | InvocationTargetException | IOException e) {
            BLog.errorJsonLogBuilder()
                .addErrorMessage("exeption found ")
                .put("pjp_method", pjp.getSignature()
                                      .getName())
                .put(e)
                .log();
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
