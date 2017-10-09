package cn.bangnongmang.admin.demo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cn.bangnongmang.server.test.MainTestDataCreator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import cn.bangnongmang.admin.enums.DriverFarmerEnum;
import cn.bangnongmang.admin.service.impl.OrderServiceImpl;
import cn.bangnongmang.admin.testutil.AdminTestDataCreator;
import cn.bangnongmang.admin.testutil.ControllerConfigUtil;
import cn.bangnongmang.data.dao.AccountMapper;
import cn.bangnongmang.data.dao.OrderFarmerMapper;
import cn.bangnongmang.data.domain.AccountCriteria;

public class OrderContorllerTest extends ControllerConfigUtil{
	
	@Autowired
	private OrderFarmerMapper orderFarmerMapper;
	@Autowired
	private AccountMapper accountMapper;
	Integer level=DriverFarmerEnum.AUTHENTICATED_FARMER.getCurrencyCode();
	String orderId2="201803011052299522562113";
	Long orderFarmerUsername2=15738397704L;
	 
	@Mock
	  private OrderServiceImpl orderServiceImpl;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);  
		 
		orderFarmerMapper.insert(MainTestDataCreator.createOrderFarmer(orderFarmerUsername2, 2, System.currentTimeMillis(),
				orderId2));
		
		accountMapper.insert(MainTestDataCreator.createAccount(orderFarmerUsername2, level));
		
}

	@After
	public void tearDown() {
		AccountCriteria accountCriteria = new AccountCriteria();
		accountCriteria.or().andUidEqualTo(orderFarmerUsername2);
		accountMapper.deleteByExample(accountCriteria);
		
		orderFarmerMapper.deleteByPrimaryKey(orderId2);
	}

	@Test
	public void test4_authOrderFarmer() throws Exception {
	 // 测试桩
		when(this.orderServiceImpl.updateDesireNum(orderId2, 6)).thenReturn("success");
		//Mockito.when(mock.updateDesireNum(orderId2, 8)).thenThrow(new RuntimeException());
	//  为连续的调用做测试桩
//		 when(mock.updateDesireNum(orderId2, 9))
//		   .thenThrow(new RuntimeException())
//		   .thenReturn("success");
		 
		 getMockMvc().perform((post("/order/updateDesireNum")
								.session(getSession())
								.param("orderId", orderId2))
								.param("desireNum", "6"))
								.andExpect(status().isOk())
								.andDo(print()).andReturn();

		 System.out.println("-----------------");
		 // 输出sccess
		//System.out.println(mock.updateDesireNum(orderId2, 6));
		 // 抛出异常
		// System.out.println(mock.updateDesireNum(orderId2, 8));
		 // 不打桩为null
		// System.out.println(mock.updateDesireNum(orderId2, 9));
		 Assert.assertEquals("success", this.orderServiceImpl.updateDesireNum(orderId2, 6));
		 // 验证被调用
		//Mockito.verify(orderServiceImpl).updateDesireNum(orderId2, 6);
		//  Mockito.verify(mock).updateDesireNum(Matchers.eq(orderId2), Matchers.eq(6));
//		  System.out.println(mock.updateDesireNum(orderId2, 9));
//		  System.out.println(mock.updateDesireNum(orderId2, 9));
//		  System.out.println(mock.updateDesireNum(orderId2, 9));
		  
		
	}

	
}