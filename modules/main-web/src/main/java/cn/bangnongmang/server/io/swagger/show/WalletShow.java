package cn.bangnongmang.server.io.swagger.show;

import cn.bangnongmang.data.dao.BankCardMapper;
import cn.bangnongmang.data.dao.PocketLogMapper;
import cn.bangnongmang.data.domain.BankCardCriteria;
import cn.bangnongmang.server.business.common.PaymentBusiness;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.server.io.swagger.WalletConverter;
import cn.bangnongmang.service.outerservice.AlipayService;
import cn.bangnongmang.server.swagger.model.BankCard;
import cn.bangnongmang.server.swagger.model.BankCardValidateInfo;
import cn.bangnongmang.server.swagger.model.Wallet;
import cn.bangnongmang.server.swagger.model.WalletLogItem;
import cn.bangnongmang.service.service.BankCardService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by admin on 2017-04-17.
 */
@Service
public class WalletShow {
    @Autowired
    private WalletConverter walletConverter;
    @Autowired
    private BankCardMapper bankCardMapper;
    @Autowired
    private AlipayService alipayService;
    @Autowired
    private PaymentBusiness paymentBusiness;
    @Autowired
    private PocketLogMapper pocketLogMapper;


    public List<BankCard> getMyAvailableBankCardList(Long uid) {
        BankCardCriteria bankCardCriteria = new BankCardCriteria();
        bankCardCriteria.or()
                        .andUidEqualTo(uid)
                        .andStatusEqualTo(BankCardService.CARD_AVALIABLE);
        bankCardCriteria.setOrderByClause("id");
        return bankCardMapper.selectByExample(bankCardCriteria)
                             .stream()
                             .map(b -> walletConverter
                                     .convertToBankCard(b))
                             .collect(Collectors.toList());

    }

    public Wallet getMyWallet(Long uid) {
        return walletConverter.convertToWallet(paymentBusiness.getCurrentMoney(uid));
    }

    public WalletLogItem getWalletLogItemById(String logId){
        return walletConverter.convertToWalletLogItem(pocketLogMapper.selectByPrimaryKey(logId));
    }

    public List<WalletLogItem> getMyWalletLogs(Long uid) {
        return paymentBusiness.getPocketLog(uid)
                              .stream()
                              .map(pocketLog -> walletConverter
                                      .convertToWalletLogItem(pocketLog))
                              .collect(Collectors.toList());
    }

    public BankCardValidateInfo validateCard(String cardNumber) throws BusinessException {
        JSONObject jsonObject = alipayService.validateCard(cardNumber);
        if (jsonObject.getBoolean("validated")) {
            BankCardValidateInfo bankCardValidateInfo = new BankCardValidateInfo();
            bankCardValidateInfo.setBank(
                    alipayService.convertBankNameToChinese(jsonObject.getString("bank")));
            bankCardValidateInfo.setCardNum(cardNumber);
            bankCardValidateInfo.setType(
                    alipayService.convertCardType(jsonObject.getString("cardType")));
            return bankCardValidateInfo;
        }
        throw new BusinessException("暂不支持该银行卡");

    }
}
