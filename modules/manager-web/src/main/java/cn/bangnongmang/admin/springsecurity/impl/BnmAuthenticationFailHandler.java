package cn.bangnongmang.admin.springsecurity.impl;

import cn.bangnongmang.admin.swagger.model.BusinessError;
import cn.bangnongmang.size.io.swagger.model.Error;
import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by admin on 2017-05-10.
 */

@Service("failureHandler")
public class BnmAuthenticationFailHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
        BusinessError businessError = new BusinessError();
        businessError.setCode(800);
        businessError.setMessage("账密错误，或者错误超过限制");
        response.getOutputStream()
                .write(JSON.toJSONBytes(businessError));
    }
}
