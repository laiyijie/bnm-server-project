package cn.bangnongmang.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.bangnongmang.admin.controller.api.InfoControllerApi;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.data.domain.Account;

@RestController
public class InfoController implements InfoControllerApi {

	@Override
	public String comingString(String aa) {
		return aa + "123123123";
	}

	@RequestMapping("/testObject")
	public Object commingObject(@RequestParam("abc")String abc) {
		System.out.println(abc);
		Account account = new Account();
		account.setUsername("123123");
		return account;
	}

	@RequestMapping("/testException")
	public Object ex() throws BusinessException {
		throw new BusinessException("123123");
	}

	@RequestMapping("/testWechatModal")
	public String testWechatModal() {
		return "success";
	}
}
