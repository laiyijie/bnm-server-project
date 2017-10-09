package cn.bangnongmang.admin.controller;

import cn.bangnongmang.admin.enums.UserMachineEnums;
import cn.bangnongmang.admin.swagger.model.*;
import cn.bangnongmang.admin.swagger.model.OptionCluster;
import cn.bangnongmang.admin.swagger.model.OptionDetail;
import cn.bangnongmang.data.combo.dao.UserMachineShowMapper;
import cn.bangnongmang.data.dao.*;
import cn.bangnongmang.data.domain.*;
import cn.bangnongmang.server.test.MainTestDataCreator;
import com.alibaba.fastjson.JSON;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.jws.soap.SOAPBinding;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by lucq on 2017/5/26.
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextHierarchy({
		@ContextConfiguration(name = "parent", value = "file:src/main/webapp/WEB-INF/spring/root-context.xml"),
		@ContextConfiguration(name = "child", value = "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)

@Transactional()
public class UserMachineControllerTest {

	@Autowired
	private UserMachineMapper userMachineMapper;
	@Autowired
	private UserMachineModelMapper userMachineModelMapper;
	@Autowired
	private UserMachineTypeMapper userMachineTypeMapper;
	@Autowired
	private OptionWorkingTypeMachineModelMappingMapper optionWorkingTypeMachineModelMappingMapper;
	@Autowired
	private OptionWorkingTypeMapper optionWorkingTypeMapper;
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private AccountRealNameAuthMapper accountRealNameAuthMapper;
	@Autowired
	private UserMachineOptionDetailMappingMapper userMachineOptionDetailMappingMapper;
	@Autowired OptionDetailMapper optionDetailMapper;

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	private ObjectMapper mapper;



	@Before
	public void Before(){


		Account account=MainTestDataCreator.createAccount(1L,1);
		AccountRealNameAuth accountRealNameAuth=MainTestDataCreator.createAccountRealNameAuth(1L,200,"Hantest");
//		Account account=new Account();
//        account.setUid(1L);
//        account.setName("HANtest");
//        account.setTel("1234567");
//        accountMapper.insert(account);
//		accountRealNameAuthMapper.insert(accountRealNameAuth);
//
//
//		UserMachine userMachine=new UserMachine();
//
//		userMachine.setId(100L);
//		userMachine.setBuy_time(10000L);
//		userMachine.setState(200);
//		userMachine.setMachine_model_id(1482804954324710L);
//		userMachine.setFailed_reason("不知道");
//		userMachine.setUid(1L);
//		userMachine.setIntegrity(0);
//		userMachine.setWidth(1000D);
//		System.out.println(userMachineMapper);
//		userMachineMapper.insert(userMachine);
//
//		UserMachineOptionDetailMappingKey userMachineOptionDetailMappingKey=new UserMachineOptionDetailMappingKey();
//
//		userMachineOptionDetailMappingKey.setUser_machine_id(100L);
//
//		OptionDetailCriteria optionDetailCriteria=new OptionDetailCriteria();
//		optionDetailCriteria.or().andOption_nameEqualTo("是");
//		userMachineOptionDetailMappingKey.setOption_detail_id(optionDetailMapper.selectByExample(optionDetailCriteria).get(0).getId());
//		userMachineOptionDetailMappingMapper.insert(userMachineOptionDetailMappingKey);
//		UserMachineOptionDetailMappingKey t=userMachineOptionDetailMappingMapper.selectByExample(null).get(0);
//
//		userMachine.setId(200000L);
//		userMachineMapper.insert(userMachine);
//		UserMachineModel userMachineModel = new UserMachineModel();
//		userMachineModel.setId(10000L);
//		userMachineModel.setUser_machine_type_id(10000L);
//		userMachineModel.setModel_number("No123456");
//		userMachineModel.setModel_brand("Test");
//		userMachineModel.setState(400);
//		userMachineModel.setUser_machine_type_id(10000L);
//		userMachineModel.setModel_width(1000D);
//		userMachineModel.setModel_power(2000D);
//		userMachineModelMapper.insert(userMachineModel);
//		userMachineModel.setModel_brand("Test2");
//		userMachineModel.setId(20000L);
//		userMachineModelMapper.insert(userMachineModel);
//		userMachineModel.setModel_brand("Test3");
//		userMachineModel.setId(30000L);
//		userMachineModelMapper.insert(userMachineModel);
//
//
//		UserMachineType userMachineType = new UserMachineType();
//		userMachineType.setId(10000L);
//		userMachineType.setType_name("Test1");
//		userMachineType.setDescripetion("TEST");
//		userMachineTypeMapper.insert(userMachineType);
//
//		userMachineType.setId(20000L);
//		userMachineType.setType_name("Test2");
//		userMachineTypeMapper.insert(userMachineType);
//
//		userMachineType.setId(30000L);
//		userMachineType.setType_name("Test3");
//		userMachineTypeMapper.insert(userMachineType);
//
//		OptionWorkingTypeMachineModelMappingKey optionWorkingTypeMachineModelMappingKey=new OptionWorkingTypeMachineModelMappingKey();
//		optionWorkingTypeMachineModelMappingKey.setMachine_model_id(10000L);
//		optionWorkingTypeMachineModelMappingKey.setOption_working_type_id(20L);
//		optionWorkingTypeMachineModelMappingMapper.insert(optionWorkingTypeMachineModelMappingKey);
//		optionWorkingTypeMachineModelMappingKey.setMachine_model_id(10000L);
//		optionWorkingTypeMachineModelMappingKey.setOption_working_type_id(10000L);
//		optionWorkingTypeMachineModelMappingMapper.insert(optionWorkingTypeMachineModelMappingKey);
//
//
//		OptionWorkingType optionWorkingType=new OptionWorkingType();
//
//		optionWorkingType.setId(10000L);
//		optionWorkingType.setWorking_type("Test1");
//		optionWorkingType.setCrop_type("Test1");
//		optionWorkingTypeMapper.insert(optionWorkingType);
//
//		optionWorkingType.setId(20L);
//		optionWorkingType.setWorking_type("Test2");
//		optionWorkingType.setCrop_type("Test2");
//		optionWorkingTypeMapper.insert(optionWorkingType);
//
//
//
//		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
//								 .build();
//		mapper = new ObjectMapper();
//


	}

	@Test
	public void Test(){

	}

//	@Test
	public void Test_machineUserMachinesAuthsGet() throws Exception {

		MvcResult  result=mockMvc.perform(get("/api/v1/machine/userMachines").sessionAttr("username", "1234456789"))
			   .andDo(print())
			   .andExpect(status().isOk())
		       .andReturn();

		List list=JSON.parseArray(result.getResponse().getContentAsString());
		assert (list.size() ==  userMachineMapper.countByExample(null));

	}
	//@Test
	public void   Test_machineUserMachinesAuthsUserMachineIdGet() throws Exception{
		mockMvc.perform(get("/api/v1/machine/userMachines/10000").sessionAttr("username", "1234456789"))
			   .andDo(print())
			   .andExpect(status().is(404));
		mockMvc.perform(get("/api/v1/machine/userMachines/549").sessionAttr("username", "1234456789"))
			   .andDo(print())
			   .andExpect(status().isOk());

	}

	//@Test
	public void Test_getUserMachineByUid() throws Exception {

		MvcResult  result=mockMvc.perform(get("/api/v1/machine/userMachines/getByuid/3073").sessionAttr("username", "1234456789"))
								 .andDo(print())
								 .andExpect(status().isOk())
								 .andReturn();


	}


//	@Test
	public void Test_machineUserMachinesAuthsUserMachineIdPut() throws Exception{

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);


		UserMachineDetail userMachineDetail= new UserMachineDetail();
		userMachineDetail.setId(100L);
		userMachineDetail.setModelId(1482804954324515L);
		userMachineDetail.setUid(1L);

		List<MachineProperty> list = new ArrayList<>();

		MachineProperty machineProperty=new MachineProperty();

		OptionCluster optionCluster=new OptionCluster();
		optionCluster.setId(0L);

		OptionDetail optionDetail=new OptionDetail();
		optionDetail.setName("20");

		machineProperty.setOptionCluster(optionCluster);
		machineProperty.setOptionDetail(optionDetail);

		list.add(machineProperty);


		machineProperty=new MachineProperty();
		optionCluster=new OptionCluster();
		optionCluster.setId(28L);
		optionDetail=new OptionDetail();
		optionDetail.setId(1125L);

		machineProperty.setOptionCluster(optionCluster);
		machineProperty.setOptionDetail(optionDetail);

		list.add(machineProperty);

		userMachineDetail.setMachineproperties(list);

		mockMvc.perform(put("/api/v1/machine/userMachines/100").sessionAttr("username", "1234456789").headers(httpHeaders).content(mapper
				.writeValueAsString(
		userMachineDetail)))
			   .andDo(print())
			   .andExpect(status().isOk());

		assert (userMachineMapper.selectByPrimaryKey(100L).getWidth().equals(20D));
	}


//	@Test
	public void Test_machineDenyOrPass() throws Exception {

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
		mockMvc.perform(post("/api/v1/machine/userMachines/auths/100/status/accept").sessionAttr("username", "1234456789").headers(httpHeaders)
																					.param("buyDate","123456789"))
			   .andDo(print())
			   .andExpect(status().isOk());

		UserMachine userMachine=userMachineMapper.selectByPrimaryKey(100L);
		assert(userMachine.getState().equals(UserMachineEnums.STATE_AUTH_PASSED.getCurrencyCode()));
		assert(userMachine.getBuy_time().equals(123456L));

		mockMvc.perform(post("/api/v1/machine/userMachines/auths/100/status/deny").sessionAttr("username", "1234456789").headers(httpHeaders)
																				  .param("reason","Test"))
			   .andDo(print())
			   .andExpect(status().isOk());

		userMachine=userMachineMapper.selectByPrimaryKey(100L);
		assert(userMachine.getState().equals(UserMachineEnums.STATE_AUTH_FAILED.getCurrencyCode()));
        assert(userMachine.getIntegrity().equals(0));
        assert(userMachine.getWidth().equals(1000D));
	}
}
