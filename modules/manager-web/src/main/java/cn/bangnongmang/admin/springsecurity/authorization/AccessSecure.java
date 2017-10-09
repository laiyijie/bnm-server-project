package cn.bangnongmang.admin.springsecurity.authorization;

import cn.bangnongmang.admin.data.combo.dao.RescShowMapper;
import cn.bangnongmang.admin.data.combo.domain.RescAndMethod;
import cn.bangnongmang.admin.data.dao.AdminAccountMapper;
import cn.bangnongmang.admin.data.dao.RescMapper;
import cn.bangnongmang.admin.data.dao.RescRoleMapper;
import cn.bangnongmang.admin.data.dao.RoleMapper;
import cn.bangnongmang.admin.data.domain.AdminAccount;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * Created by admin on 2017-05-10.
 */
@Component("accessSecure")
public class AccessSecure {
    @Autowired
    private AdminAccountMapper adminAccountMapper;
    @Autowired
    private RescShowMapper rescShowMapper;

    public boolean checkResource(Authentication authentication, HttpServletRequest request) {

        String username = authentication.getName();
        AdminAccount adminAccount = adminAccountMapper.selectByPrimaryKey(username);
        if (adminAccount == null) {
            throw new UsernameNotFoundException("");
        }
        List<RescAndMethod> rescs = rescShowMapper.selectRescAndMethodByUsername(username);
        return rescs.stream()
                    .anyMatch(rescAndMethod -> RescAndMethod.ALL_METHOD.equals(rescAndMethod.getMethod()) ?
                            new AntPathRequestMatcher(rescAndMethod.getResource()).matches(request) : new AntPathRequestMatcher
                            (rescAndMethod.getResource(), "GET").matches(request));
    }
}
