package cn.bangnongmang.service.service.impl;

import java.util.List;

import cn.bangnongmang.data.combo.dao.UserBasicInfoShowMapper;
import cn.bangnongmang.data.combo.domain.UserBasicInfoShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bangnongmang.data.dao.AccountMapper;
import cn.bangnongmang.data.dao.AccountPortraitMapper;
import cn.bangnongmang.data.dao.AccountRealNameAuthMapper;
import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.AccountCriteria;
import cn.bangnongmang.data.domain.AccountPortrait;
import cn.bangnongmang.data.domain.AccountRealNameAuth;
import cn.bangnongmang.service.service.UserService;

@Service("S_UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private AccountMapper accountMapper;// 加载dao层的accountMapper
    @Autowired
    private AccountPortraitMapper portraitMapper;
    @Autowired
    private AccountRealNameAuthMapper accountRealNameAuthMapper;
    @Autowired
    private UserBasicInfoShowMapper userBasicInfoShowMapper;

    public boolean createUser(String username, int level) {

        AccountCriteria accountCriteria = new AccountCriteria();

        accountCriteria.or().andUsernameEqualTo(username);// 查询数据库中是否存在这个帐户

        List<Account> accounts = accountMapper.selectByExample(accountCriteria);

        if (!accounts.isEmpty()) {

            return false;//应该抛出用户已经存在，注册失败
        }

        Account acc = new Account();

        acc.setUsername(username);

        acc.setCreate_time(System.currentTimeMillis() / 1000);

        acc.setIdcard_id("");

        acc.setLevel(level);

        acc.setName(username);

        acc.setNickname(username);

        acc.setOnetime_available_time(0L);

        acc.setOnetime_temp_password("");

        acc.setPassword("");

        acc.setSalt_string("");

        acc.setState(UserService.AVAILABLE);

        acc.setTel(username);

        acc.setTemp_available_time(0L);

        acc.setTemp_password("");

        acc.setWechat_info("");

        acc.setWechat_open_id("");

        acc.setWechat_union_id("");

        int flag = accountMapper.insert(acc);

        if (flag > 0) {
            return true;
        }
        return false;

    }

    @Override
    public Account getUserInfoByUsername(String username) {

        AccountCriteria accountCriteria = new AccountCriteria();

        accountCriteria.or().andUsernameEqualTo(username);// 组合example语句

        List<Account> accounts = accountMapper.selectByExample(accountCriteria);

        if (accounts.isEmpty()) {

            return null;
        } else {

            return accounts.get(0);
        }

    }

    @Override
    public Account getUserInfo(Long uid) {
        return accountMapper.selectByPrimaryKey(uid);
    }


    @Override
    public boolean setAutoLoginPasswordAndMachineId(String username, String authCode,
                                                    String machineId) {

        Account account = getUserInfoByUsername(username);

        if (account == null) {
            return false;
        }
        AccountCriteria accountCriteria=new AccountCriteria();
        account.setSalt_string(machineId);
        account.setPassword(authCode);

        int flag = accountMapper.updateByPrimaryKey(account);

        if (flag > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isDriver(Long uid) {
        Account account = getUserInfo(uid);

        if (account == null) {
            return false;
        }

        if (account.getLevel() >= DRIVER) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isFarmer(Long uid) {
        Account account = getUserInfo(uid);

        if (account == null) {
            return false;
        }

        if (account.getLevel() >= FARMER && account.getLevel() < DRIVER) {
            return true;
        }

        return false;
    }

    @Override
    public Account getUserInfoByUnionId(String unionid) {
        AccountCriteria accountCriteria = new AccountCriteria();

        accountCriteria.or().andWechat_union_idEqualTo(unionid);

        List<Account> accounts = accountMapper.selectByExample(accountCriteria);

        if (accounts.isEmpty()) {

            return null;
        } else {

            return accounts.get(0);
        }
    }

    @Override
    public boolean updateWechatInfo(Long uid, String unionid, String openid) {
        Account account = getUserInfo(uid);
        if (account == null) {
            return false;
        }
        account.setWechat_union_id(unionid);
        account.setWechat_open_id(openid);
        accountMapper.updateByPrimaryKey(account);
        return true;
    }

    @Override
    public List<Account> getUserInfoByName(String key) {

        AccountCriteria accountCriteria = new AccountCriteria();

        accountCriteria.or().andNameEqualTo(key);

        return accountMapper.selectByExample(accountCriteria);
    }

    @Override
    public AccountPortrait getPortraitUrl(Long uid) {

        return portraitMapper.selectByPrimaryKey(uid);

    }

    @Override
    public boolean setPortraitUrl(Long uid, String url) {

        AccountPortrait portrait = portraitMapper.selectByPrimaryKey(uid);

        if (portrait == null) {
            AccountPortrait accountPortrait = new AccountPortrait();

            accountPortrait.setPortrait_url(url);
            accountPortrait.setUid(uid);

            portraitMapper.insert(accountPortrait);

        } else {
            portrait.setPortrait_url(url);

            portraitMapper.updateByPrimaryKey(portrait);
        }

        return true;
    }

    @Override
    public boolean addRealNameAuth(Long uid, String name, String idNumber, String upSide,
                                   String downSide) {

        Account account = accountMapper.selectByPrimaryKey(uid);

        if (account == null) {
            return false;
        }

        AccountRealNameAuth accountRealNameAuth = accountRealNameAuthMapper.selectByPrimaryKey(uid);

        if (accountRealNameAuth != null) {
            accountRealNameAuthMapper.deleteByPrimaryKey(uid);
        }
        accountRealNameAuth = new AccountRealNameAuth();
        accountRealNameAuth.setDown_side(downSide);
        accountRealNameAuth.setUp_side(upSide);
        accountRealNameAuth.setId_card_number(idNumber);
        accountRealNameAuth.setState(REAL_NAME_AUTH_STATE_WAITTING_AUTH);
        accountRealNameAuth.setUpdate_time(System.currentTimeMillis() / 1000);
        accountRealNameAuth.setUid(uid);
        accountRealNameAuth.setReal_name(name);
        accountRealNameAuthMapper.insert(accountRealNameAuth);
        return true;
    }

    @Override
    public boolean changeRealNameAuthState(Long uid,String reason, Integer state) {
        AccountRealNameAuth accountRealNameAuth=accountRealNameAuthMapper.selectByPrimaryKey(uid);
        if(accountRealNameAuth==null){
            return false;
        }
        if (REAL_NAME_AUTH_STATE_INIT.equals(state)||REAL_NAME_AUTH_STATE_WAITTING_AUTH.equals(state)||REAL_NAME_AUTH_STATE_PASS.equals(state)){
            accountRealNameAuth.setState(state);
            accountRealNameAuth.setUpdate_time(System.currentTimeMillis()/1000);
            if(REAL_NAME_AUTH_STATE_INIT.equals(state)){
                if(reason!=null&&!"".equals(reason)){
                    accountRealNameAuth.setFailed_reason(reason);
                }else{
                    accountRealNameAuth.setFailed_reason("无");
                }
            }
            accountRealNameAuthMapper.updateByPrimaryKeySelective(accountRealNameAuth);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public AccountRealNameAuth getRealNameAuth(Long uid) {

        return accountRealNameAuthMapper.selectByPrimaryKey(uid);
    }

    @Override
    public boolean changeAccountState(Long uid, Integer state) {

        Account account = getUserInfo(uid);
        if (account == null) {
            return false;
        }
        account.setLevel(state);

        accountMapper.updateByPrimaryKey(account);
        return true;
    }

    @Override
    public boolean changeAccountName(Long uid, String name) {

        Account account = getUserInfo(uid);

        if (account == null) {
            return false;
        }
        account.setName(name);
        accountMapper.updateByPrimaryKey(account);
        return true;
    }

    @Override
    public List<UserBasicInfoShow> getAccountInListPhone(List<String> phoneList) {

        return userBasicInfoShowMapper.searchRealNameAuthListByPhoneList(phoneList);
    }


}
