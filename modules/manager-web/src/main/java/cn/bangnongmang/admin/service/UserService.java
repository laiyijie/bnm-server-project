package cn.bangnongmang.admin.service;

import cn.bangnongmang.data.dao.AccountMapper;
import cn.bangnongmang.data.dao.AccountRealNameAuthMapper;
import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.AccountCriteria;
import cn.bangnongmang.data.domain.AccountRealNameAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2017-05-02.
 */
@Service
public class UserService {
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountRealNameAuthMapper accountRealNameAuthMapper;

    public Account getAccountByUsername(String username) {
        AccountCriteria accountCriteria = new AccountCriteria();
        accountCriteria.or()
                       .andUsernameEqualTo(username);
        List<Account> accounts = accountMapper.selectByExample(accountCriteria);
        if (accounts.isEmpty())
            return null;
        return accounts.get(0);
    }

    public Account getAccount(Long uid) {
        return accountMapper.selectByPrimaryKey(uid);
    }


    public  AccountRealNameAuth getAccountRealNameAuth(Long uid) {
        return accountRealNameAuthMapper.selectByPrimaryKey(uid);
    }
}
