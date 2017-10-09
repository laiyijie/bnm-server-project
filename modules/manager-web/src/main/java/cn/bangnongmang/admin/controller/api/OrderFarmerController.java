package cn.bangnongmang.admin.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.admin.util.PageResult;
import cn.bangnongmang.data.domain.Account;

@RequestMapping("/orderFarmer")
public interface OrderFarmerController {
	/**
	 * 获取已认证的农户
	 *@param currentPage
	 *            需要查询的页码 默认 1
	 * @param pageSize
	 *            每页显示的数量 默认 20
	 *@return
	 *@throws BusinessException
	 */
	@RequestMapping("/getAccountListByLevel")
	public PageResult<Account> getAccountListByLevel(
			@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws BusinessException;
}
