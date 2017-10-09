package cn.bangnongmang.admin.controller;

import cn.bangnongmang.admin.swagger.model.OrderDetail;
import cn.bangnongmang.admin.swagger.model.OrderTag;
import cn.bangnongmang.data.dao.OptionDetailMapper;
import cn.bangnongmang.data.dao.OrderFarmerMapper;
import cn.bangnongmang.data.domain.OptionCluster;
import cn.bangnongmang.data.domain.OptionDetail;
import cn.bangnongmang.data.domain.OptionWorkingType;
import cn.bangnongmang.data.domain.OrderFarmer;
import cn.bangnongmang.server.test.MainTestDataCreator;
import cn.bangnongmang.service.service.OrderFarmerStateName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextHierarchy({
		@ContextConfiguration(name = "parent", value = "file:src/main/webapp/WEB-INF/spring/root-context.xml"),
		@ContextConfiguration(name = "child", value = "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)

@Transactional()
public class OrderControllerTest {

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;
	@Autowired
	private OptionDetailMapper optionDetailMapper;
	@Autowired
	private OrderFarmerMapper orderFarmerMapper;

	@Before
	public void before(){
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)

								 .build();

		OptionDetail optionDetail=new OptionDetail();

		optionDetail.setId(10000L);
		optionDetail.setCluster_id(10000L);
		optionDetail.setOption_name("OptionDetail1");
		optionDetail.setDescription("Test");
		optionDetailMapper.insert(optionDetail);

		optionDetail.setId(20000L);
		optionDetail.setCluster_id(10000L);
		optionDetail.setOption_name("OptionDetail2");
		optionDetail.setDescription("Test");
		optionDetailMapper.insert(optionDetail);

		OrderFarmer orderFarmer= MainTestDataCreator.createOrderFarmer(10000L,10,1111111L,"12345678");
		orderFarmerMapper.insert(orderFarmer);


	}
	@Test
	public void Test_ordersOrderIdPut() throws Exception {

		cn.bangnongmang.admin.swagger.model.OrderDetail orderDetail=new OrderDetail();

		orderDetail.setDesireTime(123456L);
		OrderTag orderTag1=new OrderTag();
		orderTag1.setTagName("Test1");
		orderDetail.addTagsItem(orderTag1);

		orderDetail.setDesireTime(123456L);
		OrderTag orderTag2=new OrderTag();
		orderTag2.setTagName("Test2");
		orderDetail.addTagsItem(orderTag2);


		ObjectMapper mapper = new ObjectMapper();

		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(put("/api/v1/orders/12345678").headers(httpHeaders)
														   .content(mapper.writeValueAsString(orderDetail))
														   .sessionAttr("username","1234456789"))
				                                           .andDo(print())
		                                                   .andExpect(status().isOk());

		OrderFarmer orderFarmer=orderFarmerMapper.selectByPrimaryKey("12345678");
        System.out.println(orderFarmer.getTags().toString());

        assert (orderFarmer.getDesire_start_time().equals(new Long(123456/1000)));


		mockMvc.perform(put("/api/v1/orders/12345678/accept").headers(httpHeaders)
													  .sessionAttr("username","1234456789"))
			   .andDo(print())
			   .andExpect(status().isOk());

		orderFarmer=orderFarmerMapper.selectByPrimaryKey("12345678");
		System.out.println(orderFarmer.getTags().toString());

		assert (orderFarmer.getState().equals(OrderFarmerStateName.FARMER_WAITTING_PAY));

	}

	@Test
	public void Test_ordersOrderIdOptionOptionIdPostOrDelete() throws Exception {

		mockMvc.perform(post("/api/v1/orders/12345678/option/20000")
				.sessionAttr("username","123445678"))
			   .andDo(print())
			   .andExpect(status().isOk());

		mockMvc.perform(post("/api/v1/orders/12345678/option/20001")
				.sessionAttr("username","123445678"))
			   .andDo(print())
			   .andExpect(status().is(417));

		mockMvc.perform(delete("/api/v1/orders/12345678/option/20000")
				.sessionAttr("username","123445678"))
			   .andDo(print())
			   .andExpect(status().isOk());

		mockMvc.perform(delete("/api/v1/orders/12345678/option/20001")
				.sessionAttr("username","123445678"))
			   .andDo(print())
			   .andExpect(status().is(417));

		mockMvc.perform(get("/api/v1/orders/201704070847130823622488/membergeo")
				.sessionAttr("username","123445678"))
			   .andDo(print());

	}
}
