package cn.bangnongmang.admin.swagger.size;

import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.service.outerservice.SizeCounterService;
import cn.bangnongmang.size.io.swagger.api.DefaultApi;
import cn.bangnongmang.size.io.swagger.model.Error;
import cn.bangnongmang.size.io.swagger.model.Field;
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

    @Around("execution(* cn.bangnongmang.admin.swagger.size.AllSizeController.*(..))")
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
        System.out.println(" no  in  curr ");
        if (currMethod == null) return new ResponseEntity(HttpStatus.NOT_FOUND);
        Response<?> response = null;
        try {

            response = ((Call<?>) currMethod.invoke(defaultApi, pjp.getArgs())).execute();
            System.out.println(" no  in  response == null ");
            if (response == null) return new ResponseEntity(HttpStatus.NOT_FOUND);
            if (response.isSuccessful()) {
                return new ResponseEntity<>(response.body(), HttpStatus.valueOf(response.code()));
            } else {
                Error error = JSON.parseObject(response.errorBody()
                                                       .string(), Error.class);
                throw new BusinessException(error.getErrorCode(), error.getErrorMessage());
            }
        } catch (IllegalAccessException | InvocationTargetException | IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
