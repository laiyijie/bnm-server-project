package cn.bangnongmang.admin.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.bangnongmang.admin.controller.api.OrderFarmerController;
import cn.bangnongmang.admin.service.OrderFarmerService;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.admin.util.PageResult;
import cn.bangnongmang.data.domain.Account;
@RestController
public class OrderFarmerControllerImpl implements OrderFarmerController {
	@Autowired
	private OrderFarmerService orderFarmerService;
	@Override
	public PageResult<Account> getAccountListByLevel(
			@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws BusinessException {
	
		return orderFarmerService.getAccountListByLevel(currentPage, pageSize);
	}

}
