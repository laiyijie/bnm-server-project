package cn.bangnongmang.admin.util;

import cn.bangnongmang.admin.swagger.controller.base.SwaggerPathChecker;
import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


//@ControllerAdvice("cn.bangnongmang.admin")
@Service("responseAdvice")
public class OutputResponseAdvice implements ResponseBodyAdvice<Object> {

    private final static Logger logger = LogManager.getLogger(OutputResponseAdvice.class);
    @Autowired
    private JsonResultFactory factory;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {

        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        String path = request.getURI()
                             .getPath();

        if (SwaggerPathChecker.isSwaggerUri(path)) {
            return body;
        }

        boolean isString = false;

        if (selectedConverterType.equals(StringHttpMessageConverter.class)) {
            isString = true;
        }
        if (isString) {

            return factory.showResultString(body);
            //return body;
        } else {

            return factory.showResultObject(body);
        }

    }

}
