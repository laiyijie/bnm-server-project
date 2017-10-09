package cn.bangnongmang.server.io.swagger;

import cn.bangnongmang.data.domain.Pocket;
import cn.bangnongmang.data.domain.PocketLog;
import cn.bangnongmang.server.io.swagger.util.NumberUtil;
import cn.bangnongmang.service.middle.pocket.TradeFlowService;
import cn.bangnongmang.server.swagger.model.BankCard;
import cn.bangnongmang.server.swagger.model.Wallet;
import cn.bangnongmang.server.swagger.model.WalletLogItem;
import cn.bangnongmang.service.outerservice.AlipayService;
import cn.bangnongmang.service.service.BankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2017-04-17.
 */
@Service
public class WalletConverter {

    @Autowired
    public AlipayService alipayService;

    public Wallet convertToWallet(Pocket pocket) {
        if (pocket == null) return null;
        Wallet wallet = new Wallet();
        wallet.setArrears(pocket.getArrears());
        wallet.setUid(pocket.getUid());
        wallet.setInsurance(pocket.getInsurance());
        wallet.setMoney(pocket.getCurr_money());
        wallet.setProvisions(pocket.getProvisions());
        wallet.setWattingIn(pocket.getWaitting_in());
        return wallet;
    }

    public WalletLogItem convertToWalletLogItem(PocketLog pocketLog) {
        if (pocketLog == null) return null;
        WalletLogItem walletLogItem = new WalletLogItem();
        walletLogItem.setLogId(pocketLog.getPocket_log_id());
        walletLogItem.setMoney(pocketLog.getMoney());
        walletLogItem.setUid(pocketLog.getUid());
        walletLogItem.setConnectOrderId(pocketLog.getConnect_order_id());
        walletLogItem.setDetail(pocketLog.getDetail());
        walletLogItem.setTradeType(convertToTradeType(pocketLog.getType()));
        walletLogItem.setState(convertToWalletLogState(pocketLog.getState()));
        walletLogItem.setTime(NumberUtil.convertToMillisTime(pocketLog.getTime()));
        return walletLogItem;
    }

    public BankCard convertToBankCard(cn.bangnongmang.data.domain.BankCard domainBankCard) {
        if (domainBankCard == null) return null;
        BankCard bankCard = new BankCard();
        bankCard.setBankName(alipayService.convertBankNameToChinese(domainBankCard.getBank()));
        bankCard.setUid(domainBankCard.getUid());
        bankCard.setBank(domainBankCard.getBank());
        bankCard.setCardNum(domainBankCard.getCard_number());
        bankCard.setId(domainBankCard.getId());
        bankCard.setPhone(domainBankCard.getPhone());
        bankCard.setType(domainBankCard.getType());
        return bankCard;
    }

    public WalletLogItem.TradeTypeEnum convertToTradeType(Integer tradeType) {
        switch (tradeType) {
            case TradeFlowService.POCKETLOG_TYPE_INCOME:
                return WalletLogItem.TradeTypeEnum.INCOME;
            case TradeFlowService.POCKETLOG_TYPE_INCOME_UN_FREEZE:
                return WalletLogItem.TradeTypeEnum.INCOME_UN_FREEZE;
            case TradeFlowService.POCKETLOG_TYPE_WITHDRAW:
                return WalletLogItem.TradeTypeEnum.WITHDRAW;
            case TradeFlowService.POCKETLOG_TYPE_PAY:
                return WalletLogItem.TradeTypeEnum.PAY;
            case TradeFlowService.POCKETLOG_TYPE_PAY_INSURANCE:
                return WalletLogItem.TradeTypeEnum.PAY_INSURANCE;
            case TradeFlowService.POCKETLOG_TYPE_PROVISIONS:
                return WalletLogItem.TradeTypeEnum.PROVISIONS;
            case TradeFlowService.POCKETLOG_TYPE_PROVISIONS_PAY:
                return WalletLogItem.TradeTypeEnum.PROVISIONS_PAY;
            case TradeFlowService.POCKETLOG_TYPE_RECHARGE:
                return WalletLogItem.TradeTypeEnum.RECHARGE;
            case TradeFlowService.POCKETLOG_TYPE_REFUND:
                return WalletLogItem.TradeTypeEnum.REFUND;
            case TradeFlowService.POCKETLOG_TYPE_REFUND_INSURANCE:
                return WalletLogItem.TradeTypeEnum.REFUND_INSURANCE;
        }
        return null;
    }

    public WalletLogItem.StateEnum convertToWalletLogState(Integer state) {
        if (TradeFlowService.POCKETLOG_STATE_CANCEL == state) {
            return WalletLogItem.StateEnum.CANCEL;
        }
        if (TradeFlowService.POCKETLOG_STATE_DONE == state) {
            return WalletLogItem.StateEnum.DONE;
        }
        if (TradeFlowService.POCKETLOG_STATE_DRAFT == state) {
            return WalletLogItem.StateEnum.DRAFT;
        }
        return WalletLogItem.StateEnum.DRAFT;
    }
}
