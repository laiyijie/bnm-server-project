package cn.bangnongmang.service.outerservice;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.bangnongmang.service.service.VerifyCodeService;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TextMessageSender {
	
	@Autowired
	private VerifyCodeService verifyCodeService;
	
	@Test
	public void test(){
//		try {
//			verifyCodeService.sendVerifyCode("18801902298");
//		} catch (BusinessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
