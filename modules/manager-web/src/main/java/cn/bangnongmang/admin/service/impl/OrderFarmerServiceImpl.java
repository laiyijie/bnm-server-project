package cn.bangnongmang.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.bangnongmang.admin.enums.DriverFarmerEnum;
import cn.bangnongmang.admin.service.OrderFarmerService;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.admin.util.PageResult;
import cn.bangnongmang.data.dao.AccountMapper;
import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.AccountCriteria;
@Transactional(rollbackFor = { Exception.class })
@Service
public class OrderFarmerServiceImpl implements OrderFarmerService {
	@Autowired
	private AccountMapper accountMapper;
	@Override
	public PageResult<Account> getAccountListByLevel(Integer currentPage, Integer pageSize) throws BusinessException {
		AccountCriteria accountCriteria = new AccountCriteria();
		accountCriteria.or().andLevelEqualTo(DriverFarmerEnum.AUTHENTICATED_FARMER.getCurrencyCode());
		
		PageHelper.startPage(currentPage, pageSize);
		Page<Account> account = (Page<Account>) accountMapper.selectByExample(accountCriteria);
		return new PageResult<Account>(account);
	}

}
