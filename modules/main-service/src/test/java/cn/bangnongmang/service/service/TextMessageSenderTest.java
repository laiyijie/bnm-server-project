package cn.bangnongmang.service.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.junit.FixMethodOrder;


@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration({"classpath:test-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TextMessageSenderTest {

	@Autowired
	private VerifyCodeService verifyCodeService;
	
	@Test
	public void k(){
		
	}
//	
//	@Test
//	public void t1normalSendMessage() throws Exception{
//		
//		String code =  verifyCodeService.sendVerifyCode("18801902298");
//		
//		String codeInDB = verifyCodeService.getVerifyCode("18801902298");
//		
//		assertEquals(code, codeInDB);
//		
//		
//	}
//	
//	@Test
//	public void t2userAlreadySend(){
//		
//		try {
//			String code = verifyCodeService.sendVerifyCode("18801902298");
//			assertEquals(code, null);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			assertEquals(e.getClass(), BusinessException.class);
//			assertEquals( ((BusinessException)e).errorCode , 800);
//		}
//		
//		
//
//	}
//	
//	@Test
//	public void t3getVeryFy(){
//		
//	}
//	
//	@Test
//	public void t4codeoverTime() throws InterruptedException{
//		Thread.currentThread().sleep(301000);
//		
//		String code = verifyCodeService.getVerifyCode("18801902298");
//	
//		assertEquals(code, null);
//	}
}
