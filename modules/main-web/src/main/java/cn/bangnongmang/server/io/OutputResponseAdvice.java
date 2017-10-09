package cn.bangnongmang.server.io;

import java.util.List;

import cn.bangnongmang.server.io.android.AndroidServerResponse;
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
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import cn.bangnongmang.server.io.android.AndroidOutputUtil;
import cn.bangnongmang.server.io.android.ServerResponseFactory;
import cn.bangnongmang.server.io.wechat.WxOutputResponsFactory;

@Service("responseAdvice")
public class OutputResponseAdvice implements ResponseBodyAdvice<Object> {

    private final static Logger logger = LogManager.getLogger(OutputResponseAdvice.class);
    @Autowired
    private ServerResponseFactory factory;
    @Autowired
    private WxOutputResponsFactory wxOutputFactory;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {

        if (body instanceof AndroidServerResponse) {
            return body;
        }
        String path = request.getURI()
                             .getPath();

        if (SwaggerPathChecker.isSwaggerUri(path)) {
            return body;
        }

        boolean isString = false;
        List<String> strings = request.getHeaders()
                                      .get("User-Agent");

        if (strings == null || strings.isEmpty()) {
            return body;
        }

        if (selectedConverterType.equals(StringHttpMessageConverter.class)) {
            isString = true;
        }

        logger.debug("selectedConverterType:{}", selectedConverterType.getName());

        String agent = strings.get(0);

        if (AndroidOutputUtil.checkUserAgent(agent)) {

            if (isString) {
                return factory.showWithoutError(body);
            }
            return factory.showWithoutErrorObject(body);
        } else {
            if (isString) {
                return wxOutputFactory.showJsonStringResult(body);
            }
            return wxOutputFactory.showResult(body);
        }

    }

}
