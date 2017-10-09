package cn.bangnongmang.server.business.common;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Random;

import cn.bangnongmang.server.test.MainTestDataCreator;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pingplusplus.model.Charge;
import com.pingplusplus.model.Transfer;

import cn.bangnongmang.data.dao.AccountMapper;
import cn.bangnongmang.data.dao.AccountRealNameAuthMapper;
import cn.bangnongmang.data.dao.BankCardMapper;
import cn.bangnongmang.data.dao.PocketLogMapper;
import cn.bangnongmang.data.dao.PocketMapper;
import cn.bangnongmang.data.domain.AccountCriteria;
import cn.bangnongmang.data.domain.AccountRealNameAuthCriteria;
import cn.bangnongmang.data.domain.BankCardCriteria;
import cn.bangnongmang.data.domain.Pocket;
import cn.bangnongmang.data.domain.PocketLog;
import cn.bangnongmang.data.domain.PocketLogCriteria;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.service.middle.pocket.TradeFlowService;
import cn.bangnongmang.service.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PaymentBusinessTest {

    @Autowired
    private PaymentBusiness paymentBusiness;
    @Autowired
    TradeFlowService doneTradeFlow;
    @Autowired
    private PocketLogMapper pocketLogMapper;
    @Autowired
    private PocketMapper pocketMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private BankCardMapper bankCardMapper;
    @Autowired
    private AccountRealNameAuthMapper accountRealNameAuthMapper;
    private PocketLogCriteria pocketLogCriteria = new PocketLogCriteria();
    private AccountCriteria accountCriteria = new AccountCriteria();
    private BankCardCriteria bankCardCriteria = new BankCardCriteria();
    private AccountRealNameAuthCriteria accountRealNameAuthCriteria = new AccountRealNameAuthCriteria();
    private Long FARMER_UID = 15738397706L;
    private String care_number = "6217002430009414500";
    private String openBank = "CMB";
    private String status = "CARD_AVALIABLE";
    private String type = "DC";
    private Integer Number = 100000;
    private String realNamestatus = "张三";
    private Random random = new Random();

    @Before
    public void create() {
        FARMER_UID = 13000000000L + random.nextInt(1000000000);
        // 创建账户
        accountMapper.insert(MainTestDataCreator.createAccount(FARMER_UID, UserService.FARMER));
        // 创建钱包
        pocketMapper.insert(MainTestDataCreator.createPocket(FARMER_UID, Number));
        // 创建卡号
        bankCardMapper
                .insert(MainTestDataCreator.createBankCard(care_number, FARMER_UID, type, openBank, status, String.valueOf(FARMER_UID)));
        // 创建认证账户
        accountRealNameAuthMapper.insert(MainTestDataCreator.createAccountRealNameAuth(FARMER_UID,
                UserService.REAL_NAME_AUTH_STATE_PASS, realNamestatus));
    }

    @After
    public void destroy() throws BusinessException {
        // 清账号
        accountCriteria.or()
                       .andUidEqualTo(FARMER_UID);
        accountMapper.deleteByExample(accountCriteria);
        // 清钱包
        pocketMapper.deleteByPrimaryKey(FARMER_UID);
        // 清卡号
        bankCardCriteria.or()
                        .andCard_numberEqualTo(care_number);
        bankCardMapper.deleteByExample(bankCardCriteria);
        // 清认证账户
        accountRealNameAuthCriteria.or()
                                   .andUidEqualTo(FARMER_UID);
        accountRealNameAuthMapper.deleteByExample(accountRealNameAuthCriteria);
        // 清记录
        pocketLogCriteria.or()
                         .andUidEqualTo(FARMER_UID);
        pocketLogMapper.deleteByExample(pocketLogCriteria);
    }

//    @Test
    public void TC0101_withDrawByUnionPay_normal() throws BusinessException {
        Pocket money = paymentBusiness.getCurrentMoney(FARMER_UID);
        assertEquals(true, money != null);
    }

//    @Test
    public void TC0102_getPocketLog() throws BusinessException {
        List<PocketLog> pocketLog = paymentBusiness.getPocketLog(FARMER_UID);
        assertEquals(true, pocketLog != null);
    }

    // 充值
//    @Test
    public void TC0103_charge() throws Exception {
        String[] str = {"upacp", "wx_app", "wx_pub"};
        Charge charge;
        for (String element : str) {
            if (!String.valueOf(FARMER_UID)
                       .equals("") && String.valueOf(FARMER_UID)
                                            .length() == 11 && Number > 0) {
                // 判断账户是否存在,根据farmerPhone查询账户信息
                // 发起充值
                charge = paymentBusiness.charge(FARMER_UID, element, Number);
                pocketLogCriteria.clear();
                pocketLogCriteria.or()
                                 .andTrade_order_idEqualTo(charge.getId());
                List<PocketLog> selectByExample = pocketLogMapper.selectByExample(pocketLogCriteria);
                assertEquals(true, selectByExample.size() > 0);
                for (PocketLog pocketLog : selectByExample) {
                    doneTradeFlow.doneTradeFlow(pocketLog.getPocket_log_id());
                }
            }
        }
    }

    // 银联提现
    @Test
    public void TC0104_withdrawByUnionPay() {

    }

    // 微信提现
//    @Test
    public void TC0105_withdrawByOc() throws Exception {
        Integer number = 100000;
        // 账户是否认证(账户状态)
        // 此账户open_id是否存在

        Transfer withdrawByOc = paymentBusiness.withdrawByOc(FARMER_UID, number);
        assertEquals(true, null != withdrawByOc);
        pocketLogCriteria.or()
                         .andTrade_order_idEqualTo(withdrawByOc.getId());
        List<PocketLog> pocketLogs = pocketLogMapper.selectByExample(pocketLogCriteria);
        for (PocketLog pocketLog : pocketLogs) {
            // true扣钱成功，否则钱退回原账户。
            Boolean boolean1 = doneTradeFlow.doneTradeFlow(pocketLog.getPocket_log_id());
            if (!boolean1) {
                doneTradeFlow.cancelTradeFlow(pocketLog.getPocket_log_id());
            }
        }

    }

    /***
     * 通过TradeOrderId来完成某条pocketLog
     *
     */
//    @Test
    public void TC0106_oneTradeFlowByTradeOrderId() throws Exception {
        Integer number = 100000;

        String[] str = {"upacp", "wx_app", "wx_pub"};
        // 充值
        for (String string : str) {
            Charge charge = paymentBusiness.charge(FARMER_UID, string, Number);
            paymentBusiness.doneTradeFlowByTradeOrderId(charge.getId());
        }
        // 银联提现 2017-04-06 由于次数限制删除掉，后续再起来
//			Transfer transfer = paymentBusiness.withdrawByUnionPay(farmerPhone, number, care_number, openBank);
//			paymentBusiness.doneTradeFlowByTradeOrderId(transfer.getId());
            /*
             * 微信提现：同一账户在极短时间内不能进行重复微信提现和取消。 Error message: 对支付渠道的请求未能成功。来自
			 * wx_pub 渠道的错误信息：对同一用户转账操作过于频繁,请稍候重试. Error code:
			 * channel_request_error
			 */
        // Transfer withdrawByOc = paymentBusiness.withdrawByOc(farmerPhone,
        // number);
        // paymentBusiness.doneTradeFlowByTradeOrderId(withdrawByOc.getId());


    }

    /**
     * 通过TradeOrderId来取消某条pocketLog
     */
//    @Test
    public void TC0107_cancelTradeFlowByTradeOrderId() throws Exception {
        Integer number = 100000;
        String[] str = {"upacp", "wx_app", "wx_pub"};
        // 取消充值
        for (String string : str) {
            Charge charge = paymentBusiness.charge(FARMER_UID, string, Number);
            paymentBusiness.cancelTradeFlowByTradeOrderId(charge.getId());
        }
        // 取消银联提现		2017-04-06 由于次数限制删除掉
//			Transfer transfer = paymentBusiness.withdrawByUnionPay(farmerPhone, number, care_number, openBank);
//			paymentBusiness.cancelTradeFlowByTradeOrderId(transfer.getId());
            /*
             * 微信提现：同一账户在极短时间内不能进行重复微信提现和取消。 Error message: 对支付渠道的请求未能成功。来自
			 * wx_pub 渠道的错误信息：对同一用户转账操作过于频繁,请稍候重试. Error code:
			 * channel_request_error
			 */
        // Transfer withdrawByOc = paymentBusiness.withdrawByOc(farmerPhone,
        // number);
        // paymentBusiness.cancelTradeFlowByTradeOrderId(withdrawByOc.getId());

    }
}
