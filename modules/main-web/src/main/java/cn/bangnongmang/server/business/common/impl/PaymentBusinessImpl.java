package cn.bangnongmang.server.business.common.impl;

import java.util.List;
import java.util.Random;

import cn.bangnongmang.service.outerservice.AlipayService;
import cn.bangnongmang.service.outerservice.OuterBankCardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pingplusplus.model.Charge;
import com.pingplusplus.model.Transfer;

import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.AccountRealNameAuth;
import cn.bangnongmang.data.domain.BankCard;
import cn.bangnongmang.data.domain.Pocket;
import cn.bangnongmang.data.domain.PocketLog;
import cn.bangnongmang.server.business.common.PaymentBusiness;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.service.outerservice.OuterPayService;
import cn.bangnongmang.service.service.BankCardService;
import cn.bangnongmang.service.middle.pocket.TradeFlowService;
import cn.bangnongmang.service.service.UserService;
import cn.bangnongmang.service.service.exception.ServiceLayerExeption;
import cn.bangnongmang.service.outerservice.util.ServiceUtil;

@Transactional(rollbackFor = {Exception.class})
@Service("commonPayment")
public class PaymentBusinessImpl implements PaymentBusiness {

    @Autowired
    protected TradeFlowService tradeFlowService;


    @Autowired
    protected UserService userService;
    @Autowired
    protected BankCardService bankCardService;
    @Autowired
    private OuterPayService outerPayService;

    @Value("${pay.withdraw.min}")
    protected int minWithdraw;

    @Autowired
    protected ServiceUtil serviceUtil;

    @Autowired
    protected AlipayService alipayService;

    private transient final Logger logger = LogManager.getLogger(PaymentBusinessImpl.class);

    @Override
    public Pocket getCurrentMoney(Long uid) {

        Pocket pocket = tradeFlowService.getPocketInfo(uid);
        if (pocket == null) {
            return tradeFlowService.createPocket(uid);
        }
        return pocket;
    }

    @Override
    public List<PocketLog> getPocketLog(Long uid) {

        return tradeFlowService.getPocketLog(uid);
    }

    @Override
    public void doneTradeFlowByTradeOrderId(String tradeOrderId) throws BusinessException {

        PocketLog pocketLog = tradeFlowService.getPocketLogByTradeOrderId(tradeOrderId);
        if (pocketLog == null) {
            throw new BusinessException("没有这条流水");
        }

        try {
            if (!tradeFlowService.doneTradeFlow(pocketLog.getPocket_log_id())) {
                throw new BusinessException("操作失败");
            }
        } catch (ServiceLayerExeption e) {
            throw new BusinessException(e.getErrorMessage());
        }
    }

    @Override
    public void cancelTradeFlowByTradeOrderId(String tradeOrderId) throws BusinessException {

        PocketLog pocketLog = tradeFlowService.getPocketLogByTradeOrderId(tradeOrderId);
        if (pocketLog == null) {
            throw new BusinessException("没有这条流水");
        }

        try {
            if (!tradeFlowService.cancelTradeFlow(pocketLog.getPocket_log_id())) {
                throw new BusinessException("操作失败");
            }
        } catch (ServiceLayerExeption e) {
            throw new BusinessException(e.getErrorMessage());
        }
    }

    @Override
    public Charge charge(Long uid, String channelType, Integer money) throws
            BusinessException {
        Account account = userService.getUserInfo(uid);
        if (account == null) {
            throw new BusinessException("用户不存在");
        }
        PocketLog pocketLog = null;
        Charge charge = null;
        try {

            if (CHANEL_CHARGE_TYPE_UPACP.equals(channelType)) {

                pocketLog = tradeFlowService.createTradeFlow(uid, TradeFlowService.POCKETLOG_TYPE_RECHARGE, money,
                        TradeFlowService.POCKETLOG_METHOD_UNIONPAY, "银联充值", "", "");
                if (pocketLog == null) {
                    throw new BusinessException("创建充值失败");
                }
                charge = outerPayService.createUninonPayCharge(createOrderId(), money, "帮农忙钱包充值", "帮农忙钱包充值");
                if (charge != null
                        && tradeFlowService.updateTradeOrderIdByLogId(pocketLog.getPocket_log_id(), charge.getId())) {
                    return charge;
                }
                throw new BusinessException("发起充值失败");

            } else if (CHANEL_CHARGE_TYPE_WX_APP.equals(channelType)) {
                pocketLog = tradeFlowService.createTradeFlow(uid, TradeFlowService.POCKETLOG_TYPE_RECHARGE, money,
                        TradeFlowService.POCKETLOG_METHOD_WECHAT_APP, "App微信充值", "", "");

                if (pocketLog == null) {
                    throw new BusinessException("创建充值失败");
                }
                charge = outerPayService.createWxCharge(createOrderId(), money, "帮农忙钱包充值", "帮农忙钱包充值");
                if (charge != null
                        && tradeFlowService.updateTradeOrderIdByLogId(pocketLog.getPocket_log_id(), charge.getId())) {
                    return charge;
                }
                throw new BusinessException("发起充值失败");

            } else if (CHANEL_CHARGE_TYPE_WX_PUB.equals(channelType)) {

                if (account.getWechat_open_id() == null && "".equals(account.getWechat_open_id())) {
                    throw new BusinessException("没有关联微信号无法提现");
                }

                pocketLog = tradeFlowService.createTradeFlow(uid, TradeFlowService.POCKETLOG_TYPE_RECHARGE, money,
                        TradeFlowService.POCKETLOG_METHOD_WECHAT_OC, "微信公众号充值", "", "");
                if (pocketLog == null) {
                    throw new BusinessException("创建充值失败");
                }
                charge = outerPayService.createWxPubCharge(createOrderId(), money, "帮农忙钱包充值", "帮农忙钱包充值",
                        account.getWechat_open_id());
                if (charge != null
                        && tradeFlowService.updateTradeOrderIdByLogId(pocketLog.getPocket_log_id(), charge.getId())) {
                    return charge;
                }
                throw new BusinessException("发起充值失败");
            } else {
                throw new BusinessException("不支持此充值方式");
            }
        } catch (ServiceLayerExeption e) {
            throw new BusinessException(e.getErrorMessage());
        }

    }

    @Override
    public Transfer withdrawByUnionPay(Long uid, int money, String cardNumber, String openBank)
            throws BusinessException {

        AccountRealNameAuth accountRealNameAuth = userService.getRealNameAuth(uid);

        if (accountRealNameAuth == null
                || !accountRealNameAuth.getState()
                                       .equals(UserService.REAL_NAME_AUTH_STATE_PASS)) {
            throw new BusinessException("未实名认证无法提现");
        }
        if (money < minWithdraw) {
            throw new BusinessException("提现金额不能少于" + minWithdraw / 100 + "元");
        }
        BankCard bankCard = bankCardService.getBankCardByUidAndCardNumber(uid, cardNumber);

        if (bankCard == null) {
            throw new BusinessException("此卡未绑定");
        }
        // TODO 提现金额，提现的到账是否要有周期，等等问题
        try {
            PocketLog pocketLog = tradeFlowService.createTradeFlow(uid, TradeFlowService.POCKETLOG_TYPE_WITHDRAW,
                    money, TradeFlowService.POCKETLOG_METHOD_UNIONPAY, "用户提现", "", "");

            if (pocketLog == null) {
                throw new BusinessException("提现失败");
            }
            Transfer transfer = outerPayService.unionPayTransfer(createOrderId(), money, cardNumber,
                    accountRealNameAuth.getReal_name(), alipayService.convertBankNameToChinese(bankCard.getBank()), "用户提现");
            if (transfer == null) {
                throw new BusinessException("提现失败");
            }
            if (tradeFlowService.updateTradeOrderIdByLogId(pocketLog.getPocket_log_id(), transfer.getId())) {
                return transfer;
            }
        } catch (ServiceLayerExeption e) {
            throw new BusinessException(e.getErrorMessage());
        }
        return null;
    }

    @Override
    public Transfer withdrawByOc(Long uid, int money) throws BusinessException {
        AccountRealNameAuth accountRealNameAuth = userService.getRealNameAuth(uid);
        if (accountRealNameAuth == null
                || !accountRealNameAuth.getState()
                                       .equals(UserService.REAL_NAME_AUTH_STATE_PASS)) {
            throw new BusinessException("未实名认证无法提现");
        }
        if (money < minWithdraw) {
            throw new BusinessException("提现金额不能少于" + minWithdraw / 100 + "元");
        }
        Account user = userService.getUserInfo(uid);
        if (user.getWechat_open_id() == null || user.getWechat_open_id()
                                                    .equals("")) {
            throw new BusinessException(82003);
        }
        PocketLog pocketLog = null;
        try {
            pocketLog = tradeFlowService.createTradeFlow(uid, TradeFlowService.POCKETLOG_TYPE_WITHDRAW, money,
                    TradeFlowService.POCKETLOG_METHOD_WECHAT_OC, "微信公众号提现", "", "");
        } catch (ServiceLayerExeption e1) {
            throw new BusinessException(e1.getErrorMessage());
        }
        if (pocketLog == null) {
            throw new BusinessException(82004);
        }
        Transfer transfer = null;
        try {
            transfer = outerPayService.wxPubTransfer(createOrderId(), money, user.getWechat_open_id(), "用户提现");
        } catch (ServiceLayerExeption e) {
            // TODO Auto-generated catch block
            throw new BusinessException(e.getErrorMessage());
        }
        if (tradeFlowService.updateTradeOrderIdByLogId(pocketLog.getPocket_log_id(), transfer.getId())) {
            return transfer;
        }
        throw new BusinessException("提现失败");
    }

    private String createOrderId() {
        Random random = new Random();
        int x = random.nextInt(899);
        x = x + 100;
        String result = String.valueOf(System.currentTimeMillis()) + String.valueOf(x);
        if (result.length() > 16) {
            return result.substring(0, 16);
        }
        return result;
    }

}
