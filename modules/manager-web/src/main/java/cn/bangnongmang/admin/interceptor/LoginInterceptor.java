package cn.bangnongmang.admin.interceptor;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.bangnongmang.admin.util.BusinessException;

public class LoginInterceptor implements HandlerInterceptor {

	public static final Set<String> AUTH_COMMON = new HashSet<>();
	public static final Set<String> AUTH_USER_LIST = new HashSet<>();
	
	private final transient static Logger logger = LogManager.getLogger(LoginInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub

		Object username = request.getSession().getAttribute("user");

		System.out.println(request.getRequestURL());
		
		if (username != null) {
			return true;
		}
		
		throw new BusinessException("not_login");
	}

}
