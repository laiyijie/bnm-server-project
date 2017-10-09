package cn.bangnongmang.admin.service;

import org.springframework.web.bind.annotation.RequestParam;

import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.admin.util.PageResult;
import cn.bangnongmang.data.domain.Account;
/**
 * 
 * 农主接口
 *
 */
public interface OrderFarmerService {
	/**
	 * 获取所有已认证用户
	 */
	public PageResult<Account> getAccountListByLevel(
			@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws BusinessException;
}
