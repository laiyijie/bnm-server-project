package cn.bangnongmang.server.testutil;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.bangnongmang.service.outerservice.util.ServiceUtil;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ServiceUtilTest {

	@Autowired
	ServiceUtil serviceutil;
	
	@Value("123123#{ systemEnvironment['BNM_CONFIG'] }")
	private Object va;
	@Value("#{ systemProperties }")
	private Object vv;
	
	@Test
	public void genIdTest() {
		String username="18321783963";
		int len=4;
		int randomLen=5;
		assertEquals("3963", username.substring(username.length()-len,username.length()));
		//String res=serviceutil.generateId(username, len, randomLen);

		
	
	}
	
	@Test
	public void printva(){
		System.out.println(vv);
	}

}
