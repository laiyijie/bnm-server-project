package cn.bangnongmang.service.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import cn.bangnongmang.service.middle.pocket.TradeFlowService;
import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.bangnongmang.data.dao.PocketLogMapper;
import cn.bangnongmang.data.dao.PocketMapper;
import cn.bangnongmang.data.domain.Pocket;
import cn.bangnongmang.data.domain.PocketCriteria;
import cn.bangnongmang.data.domain.PocketLog;
import cn.bangnongmang.data.domain.PocketLogCriteria;
import cn.bangnongmang.service.service.exception.ServiceLayerExeption;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TradeFlowServiceTest {

	private static Long belongto = 18456623365L;
	private static int POCKETLOG_TYPE_INCOME = 80;
	private static int POCKETLOG_TYPE_PAY = 300;
	private static int POCKETLOG_TYPE_REFUND = 90;
	private Integer POCKETLOG_TYPE_REFUND_INSURANCE = 70;
	private int POCKETLOG_TYPE_PROVISIONS_PAY = 600;
	private int POCKETLOG_TYPE_PROVISIONS = 1000;
	private int POCKETLOG_TYPE_PAY_INSURANCE = 800;
	private int POCKETLOG_TYPE_INCOME_UN_FREEZE = 1100;
	private int POCKETLOG_TYPE_WITHDRAW = 200;

	private static int MONEY = 1000;
	private static String DETAIL = "线下充值";
	private static String TRADEID = "11111111111111";
	private static String CONNECTORDERID = "12222222222222";
	@Autowired
	private TradeFlowService tradeFlowService;
	@Autowired
	private PocketMapper pocketMapper;
	@Autowired
	private PocketLogMapper pocketLogMapper;

	@After
	public void After() {
		PocketCriteria pocketCriteria = new PocketCriteria();
		pocketCriteria.or().andUidEqualTo(belongto);

		PocketLogCriteria pocketLogCriteria = new PocketLogCriteria();
		pocketLogCriteria.or().andUidEqualTo(belongto);

		pocketMapper.deleteByExample(pocketCriteria);
		pocketLogMapper.deleteByExample(pocketLogCriteria);

	}

	@Test
	public void TC_0101_doneTradeFlow_income_normal() throws ServiceLayerExeption {

		PocketLog pocketLog = tradeFlowService.createTradeFlow(belongto, POCKETLOG_TYPE_INCOME, MONEY, null, DETAIL,
				TRADEID, CONNECTORDERID);
		tradeFlowService.doneTradeFlow(pocketLog.getPocket_log_id());
		// 选出完成后的pocket和pocketlog
		PocketCriteria pocketCriteria = new PocketCriteria();
		pocketCriteria.or().andUidEqualTo(belongto);
		Pocket pocket = pocketMapper.selectByExample(pocketCriteria).get(0);
		PocketLogCriteria pocketLogCriteria = new PocketLogCriteria();
		pocketLogCriteria.or().andUidEqualTo(belongto);
		pocketLog = pocketLogMapper.selectByExample(pocketLogCriteria).get(0);
		// waitting in 加钱
		assertEquals((int) MONEY, (int) pocket.getWaitting_in());
		// pocketlog状态为200
		assertEquals((int) 200, (int) pocketLog.getState());

	}

	@Test
	public void TC_0102_cancelTradeFlow_income_normal() throws ServiceLayerExeption {

		PocketLog pocketLog = tradeFlowService.createTradeFlow(belongto, POCKETLOG_TYPE_INCOME, MONEY, null, DETAIL,
				TRADEID, CONNECTORDERID);
		tradeFlowService.cancelTradeFlow(pocketLog.getPocket_log_id());
		// 选出完成后的pocket和pocketlog
		PocketCriteria pocketCriteria = new PocketCriteria();
		pocketCriteria.or().andUidEqualTo(belongto);
		Pocket pocket = pocketMapper.selectByExample(pocketCriteria).get(0);
		PocketLogCriteria pocketLogCriteria = new PocketLogCriteria();
		pocketLogCriteria.or().andUidEqualTo(belongto);
		pocketLog = pocketLogMapper.selectByExample(pocketLogCriteria).get(0);
		// waitting in 加钱
		assertEquals((int) 0, (int) pocket.getWaitting_in());
		// pocketlog状态为300
		assertEquals((int) 300, (int) pocketLog.getState());

	}

	/**
	 * 待测试 pay
	 * 
	 * @throws ServiceLayerExeption
	 */

	@Test
	public void TC_0201_doneTradeFlow_pay_normal() throws ServiceLayerExeption {

		Pocket pocket = new Pocket();
		pocket.setUid(belongto);
		pocket.setCurr_money(1000);
		pocket.setArrears(0);
		pocket.setCredit(0);
		pocket.setInsurance(1500);
		pocket.setProvisions(0);
		pocket.setWaitting_in(0);
		pocketMapper.insert(pocket);
		PocketLog pocketLog = tradeFlowService.createTradeFlow(belongto, POCKETLOG_TYPE_PAY, MONEY, null, DETAIL,
				TRADEID, CONNECTORDERID);
		tradeFlowService.doneTradeFlow(pocketLog.getPocket_log_id());
		PocketCriteria pocketCriteria = new PocketCriteria();
		pocketCriteria.or().andUidEqualTo(belongto);
		pocket = pocketMapper.selectByExample(pocketCriteria).get(0);
		PocketLogCriteria pocketLogCriteria = new PocketLogCriteria();
		pocketLogCriteria.or().andUidEqualTo(belongto);
		pocketLog = pocketLogMapper.selectByExample(pocketLogCriteria).get(0);
		// currmoney 减钱
		assertEquals((int) 0, (int) pocket.getCurr_money());
		// pocketlog状态为
		assertEquals((int) 200, (int) pocketLog.getState());

	}

	@Test
	public void TC_0301_doneTradeFlow_refund_normal() throws ServiceLayerExeption {

		PocketLog pocketLog = tradeFlowService.createTradeFlow(belongto, POCKETLOG_TYPE_REFUND, MONEY, null, DETAIL,
				TRADEID, CONNECTORDERID);
		tradeFlowService.doneTradeFlow(pocketLog.getPocket_log_id());
		PocketCriteria pocketCriteria = new PocketCriteria();
		pocketCriteria.or().andUidEqualTo(belongto);
		Pocket pocket = pocketMapper.selectByExample(pocketCriteria).get(0);
		PocketLogCriteria pocketLogCriteria = new PocketLogCriteria();
		pocketLogCriteria.or().andUidEqualTo(belongto);
		pocketLog = pocketLogMapper.selectByExample(pocketLogCriteria).get(0);
		// currmoney 减钱
		assertEquals((int) 1000, (int) pocket.getCurr_money());
		// pocketlog状态为
		assertEquals((int) 200, (int) pocketLog.getState());

	}

	@Test
	public void TC_0401_doneTradeFlow_refundInsurance_normal() throws ServiceLayerExeption {

		Pocket pocket = new Pocket();
		pocket.setUid(belongto);
		pocket.setCurr_money(0);
		pocket.setArrears(0);
		pocket.setCredit(0);
		pocket.setInsurance(1500);
		pocket.setProvisions(0);
		pocket.setWaitting_in(0);
		pocketMapper.insert(pocket);
		PocketLog pocketLog = tradeFlowService.createTradeFlow(belongto, POCKETLOG_TYPE_REFUND_INSURANCE, MONEY, null,
				DETAIL, TRADEID, CONNECTORDERID);
		tradeFlowService.doneTradeFlow(pocketLog.getPocket_log_id());

		PocketCriteria pocketCriteria = new PocketCriteria();
		pocketCriteria.or().andUidEqualTo(belongto);
		Pocket pocket2 = pocketMapper.selectByExample(pocketCriteria).get(0);
		PocketLogCriteria pocketLogCriteria = new PocketLogCriteria();
		pocketLogCriteria.or().andUidEqualTo(belongto);
		pocketLog = pocketLogMapper.selectByExample(pocketLogCriteria).get(0);
		// curr_money状态为
		assertEquals((int) pocket.getCurr_money() + MONEY, (int) pocket2.getCurr_money());
		// insurance 减钱
		assertEquals((int) pocket.getInsurance() - MONEY, (int) pocket2.getInsurance());
		assertEquals((int) 200, (int) pocketLog.getState());

	}

	@Test
	public void TC_0402_doneTradeFlow_refundInsurance_notEnough() {

		try {

			Pocket pocket = new Pocket();
			pocket.setUid(belongto);
			pocket.setCurr_money(0);
			pocket.setArrears(0);
			pocket.setCredit(0);
			pocket.setInsurance(900);
			pocket.setProvisions(0);
			pocket.setWaitting_in(0);
			pocketMapper.insert(pocket);
			PocketLog pocketLog = tradeFlowService.createTradeFlow(belongto, POCKETLOG_TYPE_REFUND_INSURANCE, MONEY,
					null, DETAIL, TRADEID, CONNECTORDERID);
			tradeFlowService.doneTradeFlow(pocketLog.getPocket_log_id());
			fail();

		} catch (ServiceLayerExeption e) {

			assertEquals("保证金不足", e.getErrorMessage());
		}

	}

	@Test
	public void TC_0501_doneTradeFlow_provision_pay_provision_bigger_MONEY() throws ServiceLayerExeption {

		Pocket pocket = new Pocket();
		pocket.setUid(belongto);
		pocket.setCurr_money(0);
		pocket.setArrears(0);
		pocket.setCredit(0);
		pocket.setInsurance(0);
		pocket.setProvisions(1200);
		pocket.setWaitting_in(0);
		pocketMapper.insert(pocket);
		PocketLog pocketLog = tradeFlowService.createTradeFlow(belongto, POCKETLOG_TYPE_PROVISIONS_PAY, MONEY, null,
				DETAIL, TRADEID, CONNECTORDERID);
		tradeFlowService.doneTradeFlow(pocketLog.getPocket_log_id());
		PocketCriteria pocketCriteria = new PocketCriteria();
		pocketCriteria.or().andUidEqualTo(belongto);
		Pocket pocket2 = pocketMapper.selectByExample(pocketCriteria).get(0);
		assertEquals((int) 200, (int) pocket2.getProvisions());

	}

	// provision小于pocketlog中的money且小于信用额度
	@Test
	public void TC_0502_doneTradeFlow_provision_pay_provision_less_MONEY_less_credit() throws ServiceLayerExeption {

		Pocket pocket = new Pocket();
		pocket.setUid(belongto);
		pocket.setCurr_money(0);
		pocket.setArrears(0);
		pocket.setCredit(1000);
		pocket.setInsurance(0);
		pocket.setProvisions(1200);
		pocket.setWaitting_in(0);
		pocketMapper.insert(pocket);
		PocketLog pocketLog = tradeFlowService.createTradeFlow(belongto, POCKETLOG_TYPE_PROVISIONS_PAY, 1400, null,
				DETAIL, TRADEID, CONNECTORDERID);
		tradeFlowService.doneTradeFlow(pocketLog.getPocket_log_id());
		PocketCriteria pocketCriteria = new PocketCriteria();
		pocketCriteria.or().andUidEqualTo(belongto);
		pocket = pocketMapper.selectByExample(pocketCriteria).get(0);
		assertEquals((int) 0, (int) pocket.getProvisions());
		assertEquals((int) 200, (int) pocket.getArrears());
		assertEquals((int) 1000, (int) pocket.getCredit());

		// 又进行一次欠款，欠款数为500，总欠款700
		PocketLog pocketLog2 = tradeFlowService.createTradeFlow(belongto, POCKETLOG_TYPE_PROVISIONS_PAY, 500, null,
				DETAIL, TRADEID, CONNECTORDERID);
		tradeFlowService.doneTradeFlow(pocketLog2.getPocket_log_id());
		pocket = pocketMapper.selectByExample(pocketCriteria).get(0);
		assertEquals((int) 0, (int) pocket.getProvisions());
		assertEquals((int) 700, (int) pocket.getArrears());
		assertEquals((int) 1000, (int) pocket.getCredit());

	}

	// provision小于pocketlog中的money且大于信用额度且currMoney为0
	@Test
	public void TC_0503_doneTradeFlow_provision_pay_error() {

		try {
			Pocket pocket = new Pocket();
			pocket.setUid(belongto);
			pocket.setCurr_money(0);
			pocket.setArrears(0);
			pocket.setCredit(1000);
			pocket.setInsurance(0);
			pocket.setProvisions(1200);
			pocket.setWaitting_in(0);
			pocketMapper.insert(pocket);
			PocketLog pocketLog = tradeFlowService.createTradeFlow(belongto, POCKETLOG_TYPE_PROVISIONS_PAY, 3000, null,
					DETAIL, TRADEID, CONNECTORDERID);
			tradeFlowService.doneTradeFlow(pocketLog.getPocket_log_id());
			fail();
		} catch (ServiceLayerExeption e) {
			assertEquals("余额不足", e.getErrorMessage());
		}

	}

	@Test
	public void TC_0601_doneTradeFlow_provision() throws ServiceLayerExeption {

		Pocket pocket = new Pocket();
		pocket.setUid(belongto);
		pocket.setCurr_money(1000);
		pocket.setArrears(0);
		pocket.setCredit(1000);
		pocket.setInsurance(0);
		pocket.setProvisions(1200);
		pocket.setWaitting_in(0);
		pocketMapper.insert(pocket);
		PocketLog pocketLog = tradeFlowService.createTradeFlow(belongto, POCKETLOG_TYPE_PROVISIONS, 1000, null, DETAIL,
				TRADEID, CONNECTORDERID);
		tradeFlowService.doneTradeFlow(pocketLog.getPocket_log_id());
		PocketCriteria pocketCriteria = new PocketCriteria();
		pocketCriteria.or().andUidEqualTo(belongto);
		pocket = pocketMapper.selectByExample(pocketCriteria).get(0);

		assertEquals((int) 2200, (int) pocket.getProvisions());

	}

	@Test
	public void TC_0602_doneTradeFlow_provision_CurrMoneyNotEnough() {

		try {
			Pocket pocket = new Pocket();
			pocket.setUid(belongto);
			pocket.setCurr_money(0);
			pocket.setArrears(0);
			pocket.setCredit(1000);
			pocket.setInsurance(0);
			pocket.setProvisions(1200);
			pocket.setWaitting_in(0);
			pocketMapper.insert(pocket);
			PocketLog pocketLog;
			pocketLog = tradeFlowService.createTradeFlow(belongto, POCKETLOG_TYPE_PROVISIONS, MONEY, null, DETAIL,
					TRADEID, CONNECTORDERID);
			tradeFlowService.doneTradeFlow(pocketLog.getPocket_log_id());
			fail();
		} catch (ServiceLayerExeption e) {
			assertEquals("余额不足", e.getErrorMessage());
		}
	}

	@Test
	public void TC_0701_downTradeFlow_payInsurance_CurrMoneyNotEnough() {

		try {
			Pocket pocket = new Pocket();
			pocket.setUid(belongto);
			pocket.setCurr_money(0);
			pocket.setArrears(0);
			pocket.setCredit(1000);
			pocket.setInsurance(0);
			pocket.setProvisions(1200);
			pocket.setWaitting_in(0);
			pocketMapper.insert(pocket);
			PocketLog pocketLog;
			pocketLog = tradeFlowService.createTradeFlow(belongto, POCKETLOG_TYPE_PAY_INSURANCE, 1000, null, DETAIL,
					TRADEID, CONNECTORDERID);
			tradeFlowService.doneTradeFlow(pocketLog.getPocket_log_id());
			fail();
		} catch (ServiceLayerExeption e) {
			assertEquals("余额不足", e.getErrorMessage());
		}

	}

	@Test
	public void TC_0702_cancelTradeFlow_payInsurance() throws ServiceLayerExeption {

		Pocket pocket = new Pocket();
		pocket.setUid(belongto);
		pocket.setCurr_money(1000);
		pocket.setArrears(0);
		pocket.setCredit(1000);
		pocket.setInsurance(0);
		pocket.setProvisions(1200);
		pocket.setWaitting_in(0);
		pocketMapper.insert(pocket);
		PocketLog pocketLog = tradeFlowService.createTradeFlow(belongto, POCKETLOG_TYPE_PAY_INSURANCE, MONEY, null,
				DETAIL, TRADEID, CONNECTORDERID);
		assertTrue(tradeFlowService.cancelTradeFlow(pocketLog.getPocket_log_id()));
		PocketLogCriteria pocketLogCriteria = new PocketLogCriteria();
		pocketLogCriteria.or().andUidEqualTo(belongto);
		pocketLog = pocketLogMapper.selectByExample(pocketLogCriteria).get(0);
		assertEquals((int) 300, (int) pocketLog.getState());

	}

	// 待测

	@Test
	public void TC_0801_doneTradeFlow_unFreezeIncome() throws ServiceLayerExeption {

		Pocket pocket = new Pocket();
		pocket.setUid(belongto);
		pocket.setCurr_money(1000);
		pocket.setArrears(0);
		pocket.setCredit(1000);
		pocket.setInsurance(0);
		pocket.setProvisions(1200);
		pocket.setWaitting_in(1000);
		pocketMapper.insert(pocket);
		PocketLog pocketLog = tradeFlowService.createTradeFlow(belongto, POCKETLOG_TYPE_INCOME_UN_FREEZE, MONEY, null,
				DETAIL, TRADEID, CONNECTORDERID);
		tradeFlowService.doneTradeFlow(pocketLog.getPocket_log_id());

		PocketCriteria pocketCriteria = new PocketCriteria();
		pocketCriteria.or().andUidEqualTo(belongto);
		pocket = pocketMapper.selectByExample(pocketCriteria).get(0);
		assertEquals((int) 0, (int) pocket.getWaitting_in());
		assertEquals((int) 2000, (int) pocket.getCurr_money());
	}

	// 待测

	@Test
	public void TC_0901_doneTradeFlow_withdraw() throws ServiceLayerExeption {

		Pocket pocket = new Pocket();
		pocket.setUid(belongto);
		pocket.setCurr_money(2000);
		pocket.setArrears(0);
		pocket.setCredit(1000);
		pocket.setInsurance(0);
		pocket.setProvisions(1200);
		pocket.setWaitting_in(0);
		pocketMapper.insert(pocket);
		PocketLog pocketLog = tradeFlowService.createTradeFlow(belongto, POCKETLOG_TYPE_WITHDRAW, 1000, null, DETAIL,
				TRADEID, CONNECTORDERID);
		tradeFlowService.doneTradeFlow(pocketLog.getPocket_log_id());
		PocketCriteria pocketCriteria = new PocketCriteria();
		pocketCriteria.or().andUidEqualTo(belongto);
		pocket = pocketMapper.selectByExample(pocketCriteria).get(0);
		assertEquals((int) 1000, (int) pocket.getCurr_money());

	}

	@Test
	public void TC_0902_cancelTradeFlow_withdraw() throws ServiceLayerExeption {

		Pocket pocket = new Pocket();
		pocket.setUid(belongto);
		pocket.setCurr_money(1000);
		pocket.setArrears(0);
		pocket.setCredit(1000);
		pocket.setInsurance(0);
		pocket.setProvisions(1200);
		pocket.setWaitting_in(0);
		pocketMapper.insert(pocket);
		PocketLog pocketLog = tradeFlowService.createTradeFlow(belongto, POCKETLOG_TYPE_WITHDRAW, 1000, null, DETAIL,
				TRADEID, CONNECTORDERID);
		tradeFlowService.cancelTradeFlow(pocketLog.getPocket_log_id());
		PocketCriteria pocketCriteria = new PocketCriteria();
		pocketCriteria.or().andUidEqualTo(belongto);
		pocket = pocketMapper.selectByExample(pocketCriteria).get(0);
		assertEquals((int) 1000, (int) pocket.getCurr_money());
		PocketLogCriteria pocketLogCriteria = new PocketLogCriteria();
		pocketLogCriteria.or().andUidEqualTo(belongto);
		pocketLog = pocketLogMapper.selectByExample(pocketLogCriteria).get(0);

	}
}