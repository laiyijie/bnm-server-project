package cn.bangnongmang.admin.springsecurity.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import cn.bangnongmang.admin.data.combo.dao.RescShowMapper;
import cn.bangnongmang.admin.data.combo.domain.RescAndMethod;
import cn.bangnongmang.admin.data.domain.*;
import cn.bangnongmang.admin.service.UserService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.functors.TruePredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bangnongmang.admin.data.dao.AdminAccountMapper;
import cn.bangnongmang.admin.data.dao.RoleMapper;
import cn.bangnongmang.admin.data.dao.UserRoleMapper;

@Transactional(readOnly = true)
@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AdminAccountMapper adminAccountMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RescShowMapper rescShowMapper;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        AdminAccount adminAccount = adminAccountMapper.selectByPrimaryKey(username);
        if (adminAccount == null) {
            throw new UsernameNotFoundException("");
        }
        Collection<GrantedAuthority> authorities = new HashSet<>();
        return new User(username, adminAccount.getPassword(), authorities);

    }

}
