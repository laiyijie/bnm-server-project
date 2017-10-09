package cn.bangnongmang.server.business.common;

import static org.junit.Assert.*;

import java.util.List;

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

import cn.bangnongmang.data.dao.AccountMapper;
import cn.bangnongmang.data.dao.BankCardMapper;
import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.BankCard;
import cn.bangnongmang.data.domain.BankCardCriteria;
import cn.bangnongmang.driverapp.models.BankCardInfo;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.server.io.android.show.AndroidBankCardShow;
import cn.bangnongmang.service.service.BankCardService;
import cn.bangnongmang.service.service.UserService;
import cn.bangnongmang.server.task.WxPayTask;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BankCardBusinessTest {

    private final static Long TEST_UID = 1L;
    private final String phone = "18321783963";
    private final String bank = "ICBC";
    private final String type = "DC";
    private final String cardNumber = "6222021001047017535";

    private Account testUser;

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private BankCardBusiness commonBankCardBuisness;
    @Autowired
    private BankCardMapper bankCardMapper;
    @Autowired
    private AndroidBankCardShow androidBankCardShow;
    @Autowired
    private WxPayTask wxPayTask;

    @Before
    public void b() {
        clear();
        testUser = MainTestDataCreator.createAccount(TEST_UID, UserService.AUTHENTICATED_DRIVER);
        accountMapper.insert(testUser);
    }

    @After
    public void after() {
        clear();
    }

    private void clear() {
        accountMapper.deleteByPrimaryKey(TEST_UID);

        BankCardCriteria bankCardCriteria = new BankCardCriteria();
        bankCardCriteria.or()
                        .andUidEqualTo(TEST_UID);
        bankCardMapper.deleteByExample(bankCardCriteria);

    }

    @Test
    public void test_add_bank_card() throws BusinessException {
        commonBankCardBuisness.addBankCard(TEST_UID, phone, cardNumber);

        BankCardCriteria bankCardCriteria = new BankCardCriteria();
        bankCardCriteria.or()
                        .andUidEqualTo(TEST_UID);
        List<BankCard> bankCards = bankCardMapper.selectByExample(bankCardCriteria);

        assertEquals(bankCards.size(), 1);
        BankCard bankCard = bankCards.get(0);
        assertEquals(bankCard.getBank(), bank);
        assertEquals(bankCard.getCard_number(), cardNumber);
        assertEquals(bankCard.getPhone(), phone);
        assertEquals(bankCard.getType(), type);

        assertEquals(bankCard.getUid(), TEST_UID);
        assertEquals(bankCard.getStatus(), BankCardService.CARD_AVALIABLE);

        try {
            commonBankCardBuisness.addBankCard(TEST_UID, phone, cardNumber);
            fail();
        } catch (BusinessException e) {
            assertEquals(e.errorMessage, "该卡已被绑定");
        }

    }

    @Test
    public void test_remove_bank_card() throws BusinessException {
        commonBankCardBuisness.addBankCard(TEST_UID, phone, cardNumber);

        BankCardCriteria bankCardCriteria = new BankCardCriteria();
        bankCardCriteria.or()
                        .andUidEqualTo(TEST_UID);
        List<BankCard> bankCards = bankCardMapper.selectByExample(bankCardCriteria);
        BankCard bankCard = bankCards.get(0);

        commonBankCardBuisness.removeCard(TEST_UID, bankCard.getId());
        bankCardCriteria = new BankCardCriteria();
        bankCardCriteria.or()
                        .andUidEqualTo(TEST_UID);
        bankCards = bankCardMapper.selectByExample(bankCardCriteria);

        assertEquals(bankCards.size(), 1);
        bankCard = bankCards.get(0);
        assertEquals(bankCard.getBank(), bank);
        assertEquals(bankCard.getCard_number(), cardNumber);
        assertEquals(bankCard.getPhone(), phone);
        assertEquals(bankCard.getType(), type);

        assertEquals(bankCard.getUid(), TEST_UID);
        assertEquals(bankCard.getStatus(), BankCardService.CARD_UNAVALIABLE);

    }

    @Test
    public void test_get_card_list() throws BusinessException {
        final String phone1 = "18801902298";
        final String cardNumber1 = "6225751105757086";
        commonBankCardBuisness.addBankCard(TEST_UID, phone, cardNumber);
        commonBankCardBuisness.addBankCard(TEST_UID, phone1, cardNumber1);

        List<BankCardInfo> bankCardInfos = androidBankCardShow.getBankCardList(TEST_UID);

        assertEquals(bankCardInfos.size(), 2);

        BankCardInfo bankCardInfo = bankCardInfos.get(0);
        BankCardInfo bankCardInfo1 = bankCardInfos.get(1);
        assertEquals(bankCardInfo.getBank(), "中国工商银行");
        assertEquals(bankCardInfo.getType(), "储蓄卡");
        assertEquals(bankCardInfo.getPhone(), phone);
        assertEquals(bankCardInfo.getCardNum(), cardNumber);

        assertEquals(bankCardInfo1.getBank(), "招商银行");
        assertEquals(bankCardInfo1.getType(), "信用卡");
        assertEquals(bankCardInfo1.getPhone(), phone1);
        assertEquals(bankCardInfo1.getCardNum(), cardNumber1);

        commonBankCardBuisness.removeCard(TEST_UID, bankCardInfo.getId());

        bankCardInfos = androidBankCardShow.getBankCardList(TEST_UID);

        assertEquals(bankCardInfos.size(), 1);

        bankCardInfo = bankCardInfos.get(0);

        assertEquals(bankCardInfo.getBank(), "招商银行");
        assertEquals(bankCardInfo.getType(), "信用卡");
        assertEquals(bankCardInfo.getPhone(), phone1);
        assertEquals(bankCardInfo.getCardNum(), cardNumber1);

    }

    public void tt() throws InterruptedException {
        wxPayTask.wxpayWithDrawCheck();
    }

}
