package cn.bangnongmang.admin.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.data.dao.AccountMapper;
import cn.bangnongmang.data.dao.AccountRealNameAuthMapper;
import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.AccountCriteria;
import cn.bangnongmang.data.domain.AccountRealNameAuth;
import cn.bangnongmang.data.domain.AccountRealNameAuthCriteria;

/**
 * 
 * TODO(实名认证服务)
 * 
 * @ClassName RealNameAuthService
 * @author laiyijie
 * @date 2016年12月26日 上午11:11:34
 *
 */

@Service
@Transactional(rollbackFor = { Exception.class })
public class RealNameAuthService {

	public static Integer REAL_NAME_AUTH_STATE_INIT = 200;
	public static Integer REAL_NAME_AUTH_STATE_WAITTING_AUTH = 300;
	public static Integer REAL_NAME_AUTH_STATE_PASS = 400;
	

	public static transient final Logger logger = LogManager.getLogger(RealNameAuthService.class);

	@Autowired
	private AccountRealNameAuthMapper accountRealNameAuthMapper;
	@Autowired
	private AccountMapper accountMapper;
	
	private Account getAccount(String username){
		AccountCriteria accountCriteria = new AccountCriteria();
		accountCriteria.or().andUsernameEqualTo(username);
		List<Account> accounts = accountMapper.selectByExample(accountCriteria);
		if (accounts.isEmpty()) {
			return null;
		}
		return accounts.get(0);
	}

	public boolean authRealName(String authedUsername, String operator) throws BusinessException {

		Account account = getAccount(authedUsername);
		
		if (account == null) {
			throw new BusinessException("该用户不存在");
		}
		AccountRealNameAuthCriteria accountRealNameAuthCriteria = new AccountRealNameAuthCriteria();

		accountRealNameAuthCriteria.or().andUidEqualTo(account.getUid());

		List<AccountRealNameAuth> accountRealNameAuths = accountRealNameAuthMapper
				.selectByExample(accountRealNameAuthCriteria);

		if (accountRealNameAuths.isEmpty()) {
			throw new BusinessException("此人未提交认证信息");
		}

		AccountRealNameAuth realNameAuth = accountRealNameAuths.get(0);
		if (realNameAuth.getState().equals(REAL_NAME_AUTH_STATE_PASS)) {
			throw new BusinessException("此人已经认证通过，无需再次通过");
		}

		realNameAuth.setState(REAL_NAME_AUTH_STATE_PASS);

		accountRealNameAuthMapper.updateByPrimaryKey(realNameAuth);

		if (account.getLevel()>=10 && account.getLevel()<20) {
			account.setLevel(14);
		}
		
		accountMapper.updateByPrimaryKey(account);
		return true;
	}

	public boolean denyRealName(String authedUsername, String operator, String ps) throws BusinessException {

		Account account = getAccount(authedUsername);
		
		if (account == null) {
			throw new BusinessException("该用户不存在");
		}
		
		AccountRealNameAuthCriteria accountRealNameAuthCriteria = new AccountRealNameAuthCriteria();

		accountRealNameAuthCriteria.or().andUidEqualTo(account.getUid());

		List<AccountRealNameAuth> accountRealNameAuths = accountRealNameAuthMapper
				.selectByExample(accountRealNameAuthCriteria);

		if (accountRealNameAuths.isEmpty()) {
			throw new BusinessException("此人未提交认证信息");
		}

		AccountRealNameAuth realNameAuth = accountRealNameAuths.get(0);
		if (realNameAuth.getState().equals(REAL_NAME_AUTH_STATE_INIT)) {
			throw new BusinessException("此人已经认证决绝，无需再次拒绝");
		}

		realNameAuth.setState(REAL_NAME_AUTH_STATE_INIT);
		realNameAuth.setFailed_reason(ps);

		accountRealNameAuthMapper.updateByPrimaryKey(realNameAuth);

		
		if (account.getLevel()>=10 && account.getLevel()<20) {
			account.setLevel(10);
		}
		
		accountMapper.updateByPrimaryKey(account);
		
		return true;
	}
	
}
