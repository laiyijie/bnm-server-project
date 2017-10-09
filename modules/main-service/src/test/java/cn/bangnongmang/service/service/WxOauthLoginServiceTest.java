package cn.bangnongmang.service.service;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.bangnongmang.service.outerservice.WxOcService;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration({"classpath:test-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WxOauthLoginServiceTest {

	@Autowired
	private WxOcService oauthLoginService;
	
	@Test
	public void testOnePhone(){
//		String code = "031ox9Tt15HJ9a0b6ZRt1EP8Tt1ox9TC";
//
//		oauthLoginService.getUserWechatInfo(code);
	}
}
