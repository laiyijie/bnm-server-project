package cn.bangnongmang.admin.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;

import cn.bangnongmang.admin.util.BusinessException;

@RequestMapping("pocketCredit")
public interface PocketCreditControllerApi {

	/**
	 * 
	 * 根据id更新欠款额度
	 * 
	 * @param id
	 * @param credit
	 * 
	 * @param type credit >=0
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("updateCredit")
	public boolean pocketCredit(String id ,Integer credit) throws BusinessException;

}
