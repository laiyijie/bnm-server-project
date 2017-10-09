package cn.bangnongmang.admin.order;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import cn.bangnongmang.data.domain.*;
import cn.bangnongmang.server.test.MainTestDataCreator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;

import cn.bangnongmang.admin.testutil.ControllerConfigUtil;
import cn.bangnongmang.data.combo.dao.OrderFarmerInfoShowMapper;
import cn.bangnongmang.data.dao.AccountMapper;
import cn.bangnongmang.data.dao.OptionClusterMapper;
import cn.bangnongmang.data.dao.OptionClusterWorkingTypeMappingMapper;
import cn.bangnongmang.data.dao.OptionDetailMapper;
import cn.bangnongmang.data.dao.OptionOrderMappingMapper;
import cn.bangnongmang.data.dao.OptionWorkingTypeMapper;
import cn.bangnongmang.data.dao.OrderFarmerMapper;

public class OrderContorllerTest extends ControllerConfigUtil{
	@Autowired
	private OrderFarmerMapper orderFarmerMapper;
	@Autowired
	private OptionOrderMappingMapper optionOrderMappingMapper;
	@Autowired
	private OptionDetailMapper optionDetailMapper;
	@Autowired
	private OptionClusterWorkingTypeMappingMapper optionClusterWorkingTypeMappingMapper;
	@Autowired
	private OptionClusterMapper optionClusterMapper;
	@Autowired
	private OptionWorkingTypeMapper optionWorkingTypeMapper;
	@Autowired
	private OrderFarmerInfoShowMapper orderFarmerInfoShowMapper;
	@Autowired
	private AccountMapper accountMapper;
	public static final long optiondetail=10;
	public static final long optiondetail1=11;
	public static final long optiondetail2=12;
	
	public static final String optionname="1W马力";
	
	public static final long optioncluster=11;
	
	public static final String clustername="马力";
	
	public static final String payRate="5";
	
	public static final String description = "2W以下或2W以上";
	
	public static final String orderId = "201803011052299522562112";
	
	public static final String optionsJsonString = "[10,11]";
	
	public static final long wrokTypeId = 12;
	
	public static final long wrokTypeId1 = 13;
	
	public static final String cropTpe = "玉米";
	
	public static final String workingType = "收割";
	
	public static final String workingType1 = "粉碎秸秆";
	
	public static final Long username=15738397703L;
	public static final int DesireNum=2;
	public static final long optionId=10;
	@Before
	public void setup() {
		
		orderFarmerMapper.insert(MainTestDataCreator.createOrderFarmer(username, DesireNum, System.currentTimeMillis(),
				orderId));
		accountMapper.insert(MainTestDataCreator.createAccount(username, 14));
		optionOrderMappingMapper.insert(MainTestDataCreator.createOptionOrderMapping(optionId, orderId));
		
		optionDetailMapper.insert(MainTestDataCreator.createOptionDetail(optiondetail, optionname, optioncluster));
		optionDetailMapper.insert(MainTestDataCreator.createOptionDetail(optiondetail1, optionname, optioncluster));
		optionDetailMapper.insert(MainTestDataCreator.createOptionDetail(optiondetail2, optionname, optioncluster));
		
		optionClusterMapper.insert(MainTestDataCreator.createOptionCluster(optioncluster, clustername, description));
		
		optionClusterWorkingTypeMappingMapper.insert(MainTestDataCreator.createOptionClusterWorkingTypeMappingKey(optioncluster, wrokTypeId));
		
		optionClusterWorkingTypeMappingMapper.insert(MainTestDataCreator.createOptionClusterWorkingTypeMappingKey(optioncluster, wrokTypeId1));
		OptionWorkingType optionWorkingType = MainTestDataCreator.createOptionWorkingType();
		optionWorkingType.setId(wrokTypeId);
		optionWorkingTypeMapper.insert(optionWorkingType);
		optionWorkingType.setId(wrokTypeId1);
		optionWorkingTypeMapper.insert(optionWorkingType);
		
		
}

	@After
	public void tearDown() {
		
		orderFarmerMapper.deleteByPrimaryKey(orderId);
		
		AccountCriteria accountCriteria = new AccountCriteria();
		accountCriteria.or().andUidEqualTo(username);
		accountMapper.deleteByExample(accountCriteria);
		
		OptionOrderMappingCriteria mappingCriteria = new OptionOrderMappingCriteria();
		mappingCriteria.or().andOrder_idEqualTo(orderId).andOption_idEqualTo(optionId);
		optionOrderMappingMapper.deleteByExample(mappingCriteria);
		
		optionDetailMapper.deleteByPrimaryKey(optiondetail);
		optionDetailMapper.deleteByPrimaryKey(optiondetail1);
		optionDetailMapper.deleteByPrimaryKey(optiondetail2);
		
		optionClusterMapper.deleteByPrimaryKey(optioncluster);
		
		OptionClusterWorkingTypeMappingCriteria criteria = new OptionClusterWorkingTypeMappingCriteria();
		criteria.or().andCluster_idEqualTo(optioncluster);
		optionClusterWorkingTypeMappingMapper.deleteByExample(criteria);
		
		optionWorkingTypeMapper.deleteByPrimaryKey(wrokTypeId);
		optionWorkingTypeMapper.deleteByPrimaryKey(wrokTypeId1);
		
	}

	@Test
	public void test1_getOrderList() throws Exception {

		getMockMvc().perform((post("/order/getOrderList").session(getSession()).param("currentPage", "1").param("pageSize", "5").param("type",
				"FARMER_WAITTING_AUTH"))).andExpect(status().isOk()).andDo(print());

	}

	@Test
	public void test2_updateOptions() throws Exception {
		getMockMvc().perform((post("/order/updateOptions")
				.session(getSession())
				.param("orderId", orderId)
				.param("optionsJsonString", optionsJsonString)
				)).andExpect(status().isOk()).andDo(print());
		OptionOrderMappingCriteria optionOrderMappingCriteria = new OptionOrderMappingCriteria();
		optionOrderMappingCriteria.or().andOrder_idEqualTo(orderId);
		List<OptionOrderMappingKey> keys = optionOrderMappingMapper.selectByExample(optionOrderMappingCriteria);
		System.out.println(JSON.toJSONString(keys));
		System.out.println(JSON.toJSONString(orderFarmerInfoShowMapper.selectOrderFarmerInfoShowByOrderId(orderId),true));

	}

	@Test
	public void test3_updatePrePayRate() throws Exception {
		getMockMvc().perform(
				(post("/order/updatePrePayRate").session(getSession()).param("orderId", orderId).param("payRate", payRate)))
				.andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void test4_authOrderFarmer() throws Exception {

		getMockMvc().perform((post("/order/authOrderFarmer").session(getSession()).param("orderId", orderId)))
				.andExpect(status().isOk()).andDo(print());

	}
	@Test
	public void test5_updateDesireStartTime() throws Exception {

		getMockMvc().perform((post("/order/updateDesireStartTime")
				.session(getSession())
				.param("orderId", orderId)
				.param("desireStartTime", "1492394987")))
				.andExpect(status().isOk()).andDo(print());

	}
	@Test
	public void test6_updateuniPrices() throws Exception {

		getMockMvc().perform((post("/order/updateuniPrices")
				.session(getSession())
				.param("orderId", orderId)
				.param("uniPrices", "1111")))
				.andExpect(status().isOk()).andDo(print());

	}
	@Test
	public void test7_updateDesireNum() throws Exception {

		getMockMvc().perform((post("/order/updateDesireNum")
				.session(getSession())
				.param("orderId", orderId)
				.param("desireNum", "10")))
				.andExpect(status().isOk()).andDo(print());

	}
	@Test
	public void test8_updateSize() throws Exception {

		getMockMvc().perform((post("/order/updateSize")
				.session(getSession())
				.param("orderId", orderId)
				.param("size", "25.64")))
				.andExpect(status().isOk()).andDo(print());

	}
	
}