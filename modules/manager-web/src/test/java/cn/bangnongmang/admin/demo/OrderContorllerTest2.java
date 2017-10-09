package cn.bangnongmang.admin.demo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cn.bangnongmang.server.test.MainTestDataCreator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import cn.bangnongmang.admin.controller.OrderController;
import cn.bangnongmang.admin.enums.DriverFarmerEnum;
import cn.bangnongmang.admin.service.impl.OrderServiceImpl;
import cn.bangnongmang.admin.testutil.AdminTestDataCreator;
import cn.bangnongmang.data.dao.AccountMapper;
import cn.bangnongmang.data.dao.OrderFarmerMapper;
import cn.bangnongmang.data.domain.AccountCriteria;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({ @ContextConfiguration(name = "parent", value = "file:src/main/webapp/WEB-INF/spring/root-context.xml"),
		@ContextConfiguration(name = "child", value = "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml") })

public class OrderContorllerTest2 {
	@Autowired
	private MockHttpSession session;
	private MockMvc mockMvc;
	
	@Autowired
	private OrderFarmerMapper orderFarmerMapper;
	@Autowired
	private AccountMapper accountMapper;
	Integer level=DriverFarmerEnum.AUTHENTICATED_FARMER.getCurrencyCode();
	String orderId2="201803011052299522562113";
	Long orderFarmerUsername2=15738397704L;
	
	 @InjectMocks
	 @Autowired
	//@Autowired
	private OrderController orderController;
	@Mock
	//@Autowired
	  private OrderServiceImpl orderServiceImpl;
	
	@Before
	public void setup() throws Exception {
		
		this.mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
		MockitoAnnotations.initMocks(this);  
		
//		mockMvc.perform(post("/login.do").session(session).param("username", "shixiaoxue")
//				.param("password", "1076f90dbb9f4e9dbe30c541710d1f1f1eee5e24")).andExpect(status().isOk())
//				.andDo(print());
		 
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
		 
		 mockMvc.perform((post("/order/updateDesireNum")
								.session(session)
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
		// Assert.assertEquals("success", this.orderServiceImpl.updateDesireNum(orderId2, 6));
		 // 验证被调用
		//Mockito.verify(orderServiceImpl).updateDesireNum(orderId2, 6);
		  Mockito.verify(orderServiceImpl).updateDesireNum(Matchers.eq(orderId2), Matchers.eq(6));
//		  System.out.println(mock.updateDesireNum(orderId2, 9));
//		  System.out.println(mock.updateDesireNum(orderId2, 9));
//		  System.out.println(mock.updateDesireNum(orderId2, 9));
		  
		
	}

	
}