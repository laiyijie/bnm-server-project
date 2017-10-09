package cn.bangnongmang.admin.order;

import java.text.ParseException;

import cn.bangnongmang.server.test.MainTestDataCreator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import cn.bangnongmang.admin.enums.DriverFarmerEnum;
import cn.bangnongmang.admin.service.OrderService;
import cn.bangnongmang.admin.testutil.ServiceConfigUtil;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.data.dao.AccountMapper;
import cn.bangnongmang.data.dao.OptionOrderMappingMapper;
import cn.bangnongmang.data.dao.OrderFarmerMapper;
import cn.bangnongmang.data.domain.AccountCriteria;
import cn.bangnongmang.data.domain.OptionOrderMappingCriteria;
//@Transactional
public class OrderServiceTest extends ServiceConfigUtil{

	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderFarmerMapper orderFarmerMapper;
	@Autowired
	private OptionOrderMappingMapper optionOrderMappingMapper;
	@Autowired
	private AccountMapper accountMapper;
	
	String orderId="201803011052299522562112";
	String orderId2="201803011052299522562113";
	String optionsJsonString="[2,4]";
	Integer payRate=100;
	Long orderFarmerUsername=15738397703L;
	Long orderFarmerUsername2=15738397704L;
	Integer level=DriverFarmerEnum.AUTHENTICATED_FARMER.getCurrencyCode();
	long desireStartTime=1492394987L;
	Integer uniPrices=1235;
	Integer desireNum=6;
	double size=63.9;
	@Before
	public void setUp() throws InstantiationException, IllegalAccessException {
		orderFarmerMapper.insert(MainTestDataCreator.createOrderFarmer(orderFarmerUsername, 2, System.currentTimeMillis(),
				orderId));
		orderFarmerMapper.insert(MainTestDataCreator.createOrderFarmer(orderFarmerUsername2, 2, System.currentTimeMillis(),
				orderId2));
		
		accountMapper.insert(MainTestDataCreator.createAccount(orderFarmerUsername2, level));
		optionOrderMappingMapper.insert(MainTestDataCreator.createOptionOrderMapping(1, orderId));
	}

	@After
	public void tearDown() {
		AccountCriteria accountCriteria = new AccountCriteria();
		accountCriteria.or().andUidEqualTo(orderFarmerUsername2);
		accountMapper.deleteByExample(accountCriteria);
		
		orderFarmerMapper.deleteByPrimaryKey(orderId);
		orderFarmerMapper.deleteByPrimaryKey(orderId2);
		
		OptionOrderMappingCriteria mappingCriteria = new OptionOrderMappingCriteria();
		mappingCriteria.or().andOrder_idEqualTo(orderId);
		optionOrderMappingMapper.deleteByExample(mappingCriteria);
	}


	@Test
	//@Rollback
	public void test1_updateOptions() throws BusinessException {
		
		boolean options = orderService.updateOptions(orderId, optionsJsonString);
		Assert.assertTrue("筛选项fail",options);
	
	}




	@Test
	@Rollback
	public void test5_updateDesireStartTime() throws BusinessException, ParseException {
		String updateDesireStartTime = orderService.updateDesireStartTime(orderId2, desireStartTime);
		Assert.assertEquals("success", updateDesireStartTime);
	}
	@Test
	//@Rollback
	public void test6_updateuniPrices() throws BusinessException {
		String updateuniPrices = orderService.updateuniPrices(orderId2, uniPrices);
		Assert.assertEquals("success", updateuniPrices);
	}
	@Test
	//@Rollback
	public void test7_updateDesireNum() throws BusinessException {
		String updateDesireNum = orderService.updateDesireNum(orderId2, desireNum);
		Assert.assertEquals("success", updateDesireNum);
	}
	@Test
	//@Rollback
	public void test8_updateSize() throws BusinessException {
		String updateSize = orderService.updateSize(orderId2, size);
		Assert.assertEquals("success", updateSize);
	}
}
