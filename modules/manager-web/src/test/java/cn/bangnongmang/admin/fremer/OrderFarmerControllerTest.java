package cn.bangnongmang.admin.fremer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cn.bangnongmang.admin.testutil.ControllerConfigUtil;

public class OrderFarmerControllerTest extends ControllerConfigUtil{

	@Before
	public void init(){
		
	}
	
	@After
	public void afer(){
		
	}
	
	@Test
	public void test1_getAccountListByLevel() throws Exception{
		getMockMvc()
		.perform(post("/orderFarmer/getAccountListByLevel")
		.session(getSession()))
		 .andExpect(status().isOk()).andDo(print());
		
	}
}
