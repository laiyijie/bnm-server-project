package cn.bangnongmang.service.outerservice;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.bangnongmang.service.outerservice.impl.OuterPayServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OuterPayServiceTest {

	@Autowired
	private OuterPayServiceImpl outerPayServiceImpl;
	
	@Test
	public void test(){
//		outerPayServiceImpl.unionPayCharge();
	}
}
