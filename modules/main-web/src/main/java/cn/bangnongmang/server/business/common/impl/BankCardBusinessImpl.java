package cn.bangnongmang.server.business.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bangnongmang.service.service.exception.ServiceLayerExeption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

import cn.bangnongmang.data.domain.AccountRealNameAuth;
import cn.bangnongmang.data.domain.BankCard;
import cn.bangnongmang.server.business.common.BankCardBusiness;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.service.outerservice.AlipayService;
import cn.bangnongmang.service.outerservice.OuterBankCardService;
import cn.bangnongmang.service.service.BankCardService;
import cn.bangnongmang.service.service.UserService;
import cn.bangnongmang.service.service.VerifyCodeService;
import cn.bangnongmang.service.service.impl.TextMessageSender;

@Transactional
@Service
public class BankCardBusinessImpl implements BankCardBusiness {
    private final static String SUCCESS_CODE = "0021000";
    @Autowired
    private BankCardService bankCardService;
    @Autowired
    private VerifyCodeService verifyCodeService;

    @Autowired
    private UserService userService;

    @Autowired
    private OuterBankCardService outerBankCardService;

    @Autowired
    private AlipayService alipayService;

    private Logger logger = LogManager.getLogger(BankCardBusinessImpl.class);

    @Override
    public void removeCard(Long uid, Long id) throws BusinessException {
        BankCard bankCard = bankCardService.getBankCard(id);
        if (BankCardService.CARD_AVALIABLE.equals(bankCard.getStatus()) && uid.equals(bankCard
                .getUid())) {
            if (bankCardService.deleteBankCard(id) > 0) {
                return;
            }
        }
        throw new BusinessException("解绑银行卡失败");
    }

    @Override
    public Map<String, Long> validateCardByPhone(Long uid, String cardNumber, String phone)
            throws BusinessException {
        AccountRealNameAuth accountRealNameAuth = userService.getRealNameAuth(uid);
        if (accountRealNameAuth != null
                && UserService.REAL_NAME_AUTH_STATE_PASS.equals(accountRealNameAuth.getState())) {

            if (!checkCardAvaliable(cardNumber, uid))
                throw new BusinessException("该卡已被绑定");

            boolean flag = false;
            try {
                flag = outerBankCardService
                        .auth(cardNumber, accountRealNameAuth.getId_card_number(),
                                accountRealNameAuth.getReal_name(), phone);
            } catch (ServiceLayerExeption serviceLayerExeption) {
                throw new BusinessException(serviceLayerExeption.getErrorMessage());
            }

            if (flag) {
                try {
                    verifyCodeService.sendVerifyCode(phone);
                } catch (ServiceLayerExeption serviceLayerExeption) {
                    throw new BusinessException(serviceLayerExeption.getErrorCode(), serviceLayerExeption.getErrorMessage());
                }
                Map<String, Long> re = new HashMap<>();
                re.put("nextTime", TextMessageSender.INTERVAL * 1000L);

                return re;
            }
        }
        throw new BusinessException("用户未认证");
    }

    private boolean checkCardAvaliable(String cardNumber, Long uid) {

        List<BankCard> bankCards = bankCardService.getBankCardByNumber(cardNumber);
        return bankCards.stream()
                        .noneMatch(bankCard ->
                                bankCard.getUid().equals(uid)
                                && bankCard.getCard_number().equals(cardNumber)
                                && bankCard.getStatus().equals(BankCardService.CARD_AVALIABLE));

    }

    @Override
    public void addBankCard(Long uid, String phone, String cardNumber)
            throws BusinessException {

        if (!checkCardAvaliable(cardNumber, uid))
            throw new BusinessException("该卡已被绑定");

        JSONObject jsonObject = alipayService.validateCard(cardNumber);
        if (jsonObject != null) {
            bankCardService
                    .addBankCard(uid, phone, cardNumber, jsonObject.getString("cardType"),
                            jsonObject.getString("bank"));
            return;
        }
        throw new BusinessException("添加银行卡错误");
    }

    @Override
    public void checkVerifyCode(Long uid, String phone, String cardNumber, String authCode)
            throws BusinessException {
        if (verifyCodeService.checkVerifyCode(phone, authCode)) {
            // TODO add card
            addBankCard(uid, phone, cardNumber);
            return;
        }
        throw new BusinessException("验证码错误");
    }

}
