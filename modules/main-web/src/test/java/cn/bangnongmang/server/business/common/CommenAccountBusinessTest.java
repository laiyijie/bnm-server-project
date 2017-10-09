package cn.bangnongmang.server.business.common;

import static junit.framework.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.bangnongmang.data.dao.AccountGeoMapper;
import cn.bangnongmang.data.dao.AccountMapper;
import cn.bangnongmang.data.dao.AccountRealNameAuthMapper;
import cn.bangnongmang.data.dao.PhoneVerifyMapper;
import cn.bangnongmang.data.dao.PushEventMapper;
import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.AccountCriteria;
import cn.bangnongmang.data.domain.AccountGeo;
import cn.bangnongmang.data.domain.AccountRealNameAuth;
import cn.bangnongmang.data.domain.PushEvent;
import cn.bangnongmang.data.domain.PushEventCriteria;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.service.service.UserService;
import cn.bangnongmang.server.test.MainTestDataCreator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CommenAccountBusinessTest {

    @Resource
    private AccountBusiness cCommonAccountBusiness;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountRealNameAuthMapper accountRealNameAuthMapper;
    @Autowired
    private PhoneVerifyMapper phoneVerifyMapper;
    @Autowired
    private AccountGeoMapper accountGeoMapper;

    private PushEventMapper pushEventMapper;
    private static String TEST_PHONE = "18334741666";
    private static String TEST_PHONE2 = "183347416666";
    private static Long UID = 1L;
    private static Long UID_2 = 2L;
    private static int LEVEL = 50;
    private static String WECHAT_UNION_ID = "123456";
    private static String VerifyCode = "123456";
    private static String VerifyCode2 = "1234567";
    private static Integer ID_PHONEVERIFY = 12345;
    private static Integer ID_PHONEVERIFY2 = 123456;
    private static String WECHAT_OPEN_ID = "1234";

    @Before
    public void create() {
        // 创建account数据
        accountMapper.insert(MainTestDataCreator.createAccount(UID, LEVEL));

        Account account = MainTestDataCreator.createAccount(UID_2, LEVEL);
        account.setWechat_union_id(WECHAT_UNION_ID);
        accountMapper.insert(account);

        phoneVerifyMapper.insert(MainTestDataCreator.createPhoneVerify(ID_PHONEVERIFY, String.valueOf(UID), VerifyCode));

        phoneVerifyMapper.insert(MainTestDataCreator.createPhoneVerify(ID_PHONEVERIFY2, TEST_PHONE2, VerifyCode2));
    }

    @After
    public void clear() {
        // 清除account数据
        accountMapper.deleteByPrimaryKey(UID);

        accountMapper.deleteByPrimaryKey(UID_2);

        phoneVerifyMapper.deleteByPrimaryKey(ID_PHONEVERIFY);

        phoneVerifyMapper.deleteByPrimaryKey(ID_PHONEVERIFY2);

    }

    // 测试getUserinfo函数
    @Test
    public void TC0101_getUserinfo_normal() {

        Account account = cCommonAccountBusiness.getUserInfo(UID);
        assertEquals(account.getUid(), UID);
        assertEquals((int) account.getLevel(), LEVEL);

    }

    @Test
    public void TC0102_getUserinfo_abnormal_noSuchUser() {

        Account account = cCommonAccountBusiness.getUserInfoByUsername("USERNAME_NOTEXIST");// 加入引号
        assertEquals(account, null);

    }

    // 测试getUserInfoByUnionId函数
    @Test
    public void TC0201_getUserInfoByUnionId_Normal() {

        Account account = cCommonAccountBusiness.getUserInfoByUnionId(WECHAT_UNION_ID);
        assertEquals(account.getWechat_union_id(), WECHAT_UNION_ID);

    }

    @Test
    public void TC0202_getUserInfoByUnionId_Abnormal_noSuchID() {

        Account account = cCommonAccountBusiness.getUserInfoByUnionId("USERNAME_NOTEXIST");
        assertEquals(account, null);

    }

    // getCommonVerifyCode正确验证
    // 测试手机号格式不正确
    @Test
    public void TC0302_getCommonVerifyCode_abNormal_badFormat() {

        try {
            cCommonAccountBusiness.getCommonVerifyCode(String.valueOf(UID));
        } catch (BusinessException e) {
            assertEquals("手机号码错误", e.errorMessage);
        }

    }

    @Test
    public void TC0401_commonRegister_Normal() {

        try {
            // String username, String authCode, String type, String unionid,
            // String openid
            // 测试driver
            Account account = cCommonAccountBusiness.commonRegister(TEST_PHONE2, VerifyCode2, "Driver", "unionid",
                    "openid");

            assertEquals(TEST_PHONE2, account.getUsername());
            assertEquals((Integer) 20, account.getLevel());

            PushEvent pushEvent = new PushEvent();
            PushEventCriteria pushEventCriteria = null;
            pushEventCriteria.or()
                             .andUidEqualTo(account.getUid());
            pushEvent = pushEventMapper.selectByExample(pushEventCriteria)
                                       .get(0);
            assertEquals((int) 200, (int) pushEvent.getType());

            AccountCriteria accountCriteria = new AccountCriteria();
            accountCriteria.or()
                           .andUsernameEqualTo(TEST_PHONE2);
            accountMapper.deleteByExample(accountCriteria);

            pushEventCriteria.or()
                             .andUidEqualTo(account.getUid());
            pushEventMapper.deleteByExample(pushEventCriteria);

        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Test
    public void TC402_commonRegister_abNormal_resgistAlreadyExist() {

        try {

            cCommonAccountBusiness.commonRegister(TEST_PHONE2, VerifyCode2, "Driver", "unionid", "openid");
            cCommonAccountBusiness.commonRegister(TEST_PHONE2, VerifyCode2, "Driver", "unionid", "openid");

        } catch (BusinessException e) {
            // TODO: handle exception
            assertEquals(1016, e.errorCode);
        }

    }

    @Test
    public void TC0501_commonLogin_Normal() {

        try {
            Account account = cCommonAccountBusiness.commonLogin(String.valueOf(UID), VerifyCode);
            assertEquals(String.valueOf(UID), account.getUsername());

        } catch (BusinessException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void TC0502_commonLogin_abNormal_wrongVerifyCode() {

        try {
            Account account = cCommonAccountBusiness.commonLogin(String.valueOf(UID), "wrong");

        } catch (BusinessException e) {
            assertEquals(1001, e.errorCode);
        }

    }

    @Test
    public void TC0601_updateWechatInfo_Normal() {

        cCommonAccountBusiness.updateWechatInfo(UID, WECHAT_UNION_ID, WECHAT_OPEN_ID);
        AccountCriteria accountCriteria = new AccountCriteria();
        accountCriteria.or()
                       .andUsernameEqualTo(String.valueOf(UID));
        Account account = accountMapper.selectByExample(accountCriteria)
                                       .get(0);
        assertEquals(WECHAT_UNION_ID, account.getWechat_union_id());
        assertEquals(WECHAT_OPEN_ID, account.getWechat_open_id());

    }

    @Test
    public void TC0701_setPortraitUrl_Normal() {
    }

    @Test
    public void TC_0801_getAliOssCredentials() {
    }

    @Test
    public void TC0901_getImagePutUrl_Normal() {
    }

    @Test
    public void TC0801_updateAccountGeoInfo_Normal() {

        AccountGeo accountGeo = MainTestDataCreator.createAccountGeo(UID);
        cCommonAccountBusiness.updateAccountGeoInfo(accountGeo);
        AccountGeo accountGeo2 = accountGeoMapper.selectByPrimaryKey(UID);
        assertEquals(accountGeo2.getUid(), UID);

        accountGeoMapper.deleteByPrimaryKey(UID);
    }

    @Test
    public void TC01001_realNameCertificate_normal_farmer() throws BusinessException {
        Account account = MainTestDataCreator.createAccount(System.currentTimeMillis(), UserService.FARMER);
        account.setUsername(TEST_PHONE);
        Integer id = accountMapper.insert(account);

        System.out.println(id);

        cCommonAccountBusiness.realNameCertificate(account.getUid(), "赖毅杰", "350821199205241219", "123.up.jpg",
                "down.jpg");
        account = cCommonAccountBusiness.getUserInfoByUsername(TEST_PHONE);
        AccountRealNameAuth realNameAuth = accountRealNameAuthMapper.selectByPrimaryKey(account.getUid());
        assertEquals(account.getLevel(), UserService.FARMER_WAITTING_AUTH);

        assertEquals("350821199205241219", realNameAuth.getId_card_number());
        assertEquals("赖毅杰", realNameAuth.getReal_name());
        AccountCriteria accountCriteria = new AccountCriteria();
        accountCriteria.or()
                       .andUsernameEqualTo(TEST_PHONE);
        accountMapper.deleteByExample(accountCriteria);
        accountRealNameAuthMapper.deleteByPrimaryKey(account.getUid());
    }

}
