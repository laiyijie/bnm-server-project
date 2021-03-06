package cn.bangnongmang.server.io.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.bangnongmang.data.combo.dao.UserBasicInfoShowMapper;
import cn.bangnongmang.data.combo.dao.UserMachineShowMapper;
import cn.bangnongmang.data.combo.domain.UserBasicInfoShow;
import cn.bangnongmang.data.combo.domain.UserMachineShow;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.service.service.UserMachineService;
import cn.bangnongmang.service.service.UserService;

@Order(1000)
public class AuthDriverInterceptor implements HandlerInterceptor {

    @Autowired
    private UserBasicInfoShowMapper userBasicInfoShowMapper;
    @Autowired
    private UserMachineShowMapper userMachineShowMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler)
            throws Exception {
        Long uid = (Long) request.getSession().getAttribute(ServerSessionAttr.SESSION_UID);
        if (uid == null) {
            throw new BusinessException(1003);
        }
        UserBasicInfoShow userBasicInfoShow = userBasicInfoShowMapper.selectByUid(uid);
        if (userBasicInfoShow.getAccountRealNameAuth() == null) {
            throw new BusinessException("未认证农机手无法执行这个操作");
        }
        if (!userBasicInfoShow.getAccountRealNameAuth().getState()
                .equals(UserService.REAL_NAME_AUTH_STATE_PASS)) {
            throw new BusinessException("未认证农机手无法执行这个操作");
        }
        List<UserMachineShow> userMachineShows = userMachineShowMapper.selectByUid(uid);

        if (!userMachineShows.stream()
                .anyMatch(a -> a.getUserMachine().getState()
                        .equals(UserMachineService.STATE_AUTH_PASSED))) {
            throw new BusinessException("未认证农机手无法执行这个操作");
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub

    }

}
