package cn.bangnongmang.admin.business.manager.impl;

import cn.bangnongmang.admin.business.manager.RealNameAuthBusiness;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.data.dao.AccountMapper;
import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.service.service.UserService;
import cn.bangnongmang.service.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wenxx on 2017/5/23.
 */
@Service
public class RealNameAuthBusinessImpl implements RealNameAuthBusiness{
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public void acceptRealNameAuth(Long uid) {
        Account account = accountMapper.selectByPrimaryKey(uid);
        if (account == null) {
            throw new BusinessException("用户不存在");
        }
        if(!userService.changeRealNameAuthState(uid,null, UserService.REAL_NAME_AUTH_STATE_PASS)){
            throw new BusinessException("操作失败");
        }
    }

    @Override
    public void denyRealNameAuth(Long uid,String reason) {
        Account account = accountMapper.selectByPrimaryKey(uid);
        if (account == null) {
            throw new BusinessException("用户不存在");
        }
        if(!userService.changeRealNameAuthState(uid,reason, UserService.REAL_NAME_AUTH_STATE_INIT)){
            throw new BusinessException("操作失败");
        }
    }
}
