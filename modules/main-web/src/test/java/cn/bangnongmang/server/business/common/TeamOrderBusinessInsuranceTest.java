package cn.bangnongmang.server.business.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bangnongmang.server.test.MainTestDataCreator;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.bangnongmang.data.dao.AccountMapper;
import cn.bangnongmang.data.dao.AccountRealNameAuthMapper;
import cn.bangnongmang.data.dao.GrabSeasonMapper;
import cn.bangnongmang.data.dao.OptionWorkingTypeMachineModelMappingMapper;
import cn.bangnongmang.data.dao.OptionWorkingTypeMapper;
import cn.bangnongmang.data.dao.OrderFarmerMapper;
import cn.bangnongmang.data.dao.OrderInsuranceRecordMapper;
import cn.bangnongmang.data.dao.PocketLogMapper;
import cn.bangnongmang.data.dao.PocketMapper;
import cn.bangnongmang.data.dao.SeasonOrdersMapper;
import cn.bangnongmang.data.dao.TeamInOrderMapper;
import cn.bangnongmang.data.dao.TeamJoinRequestMapper;
import cn.bangnongmang.data.dao.UserMachineMapper;
import cn.bangnongmang.data.dao.UserMachineModelMapper;
import cn.bangnongmang.data.dao.UserMachineTypeMapper;
import cn.bangnongmang.data.dao.UserWorkCalendarMapper;
import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.AccountCriteria;
import cn.bangnongmang.data.domain.OrderFarmer;
import cn.bangnongmang.data.domain.OrderInsuranceRecord;
import cn.bangnongmang.data.domain.OrderInsuranceRecordCriteria;
import cn.bangnongmang.data.domain.Pocket;
import cn.bangnongmang.data.domain.PocketLog;
import cn.bangnongmang.data.domain.PocketLogCriteria;
import cn.bangnongmang.data.domain.SeasonOrdersCriteria;
import cn.bangnongmang.data.domain.TeamInOrderCriteria;
import cn.bangnongmang.data.domain.TeamJoinRequestCriteria;
import cn.bangnongmang.data.domain.UserMachineCriteria;
import cn.bangnongmang.data.domain.UserWorkCalendarCriteria;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.service.service.GrabSeasonService;
import cn.bangnongmang.service.middle.pocket.TradeFlowService;
import cn.bangnongmang.service.service.UserService;

/**
 * Created by laiyijie on 2017-04-08.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TeamOrderBusinessInsuranceTest {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountRealNameAuthMapper accountRealNameAuthMapper;
    @Autowired
    private PocketMapper pocketMapper;
    @Autowired
    private PocketLogMapper pocketLogMapper;
    @Autowired
    private OrderInsuranceRecordMapper orderInsuranceRecordMapper;
    @Autowired
    private OrderFarmerMapper orderFarmerMapper;
    @Autowired
    private GrabSeasonMapper grabSeasonMapper;
    @Autowired
    private SeasonOrdersMapper seasonOrdersMapper;
    @Autowired
    private TeamInOrderMapper teamInOrderMapper;
    @Autowired
    private TeamJoinRequestMapper teamJoinRequestMapper;
    @Autowired
    private UserMachineMapper userMachineMapper;
    @Autowired
    private UserMachineTypeMapper userMachineTypeMapper;
    @Autowired
    private UserMachineModelMapper userMachineModelMapper;
    @Autowired
    private OptionWorkingTypeMachineModelMappingMapper optionWorkingTypeMachineModelMappingMapper;
    @Autowired
    private OptionWorkingTypeMapper optionWorkingTypeMapper;

    @Autowired
    private TeamOrderBusiness teamOrderBusiness;
    @Autowired
    private UserWorkCalendarMapper userWorkCalendarMapper;

    public static final Long FARMER_UID = 18801902298L;
    public static final List<Long> DRIVER_MEMBER_LIST = Arrays.asList(18801902299L, 18801902300L, 18801902301L);
    public static final Long DRIVER_LEADER = 18801902400L;
    public static final Long DRIVER_LEADER_SECOND = 18801902500L;

    public static final Integer INSURANCE_DEFAULT_MONEY = 50000;
    public static final Integer INSURANCE_LESS_THAN_DEFAULT_MONEY = 40000;
    public static final Integer INSURANCE_GREATER_THAN_DEFAULT_MONEY = 60000;

    public static final String FARMER_ORDER_ID = "FARMER_ORDER_ID_TEST_IN_TEAM_INSURNACE";
    public static final String CURRNET_SEASON_ID = "CURRENT_SEASON_ID_XXX";

    public static final Integer NOT_ENOUGH_MONEY_BUSINESS_EXCEPTION_CODE = 1000;

    public static final Long USER_MACHINE_TYPE_ID = Long.MAX_VALUE;
    public static final Long USER_MACHINE_MODEL_ID = Long.MAX_VALUE;

    public static List<Long> allUser = new ArrayList<>();
    public static List<Long> allDriverLeader = new ArrayList<>();

    @BeforeClass
    public static void initParams() {
        allUser.addAll(DRIVER_MEMBER_LIST);
        allUser.add(DRIVER_LEADER);
        allUser.add(DRIVER_LEADER_SECOND);
        allUser.add(FARMER_UID);

        allDriverLeader.add(DRIVER_LEADER);
        allDriverLeader.add(DRIVER_LEADER_SECOND);
    }

    @After
    public void deleteAccount() {
        for (Long member : allUser) {
            AccountCriteria accountCriteria = new AccountCriteria();
            accountCriteria.or()
                           .andUidEqualTo(member);
            accountMapper.deleteByExample(accountCriteria);
            accountRealNameAuthMapper.deleteByPrimaryKey(member);
            PocketLogCriteria pocketLogCriteria = new PocketLogCriteria();
            pocketLogCriteria.or()
                             .andUidEqualTo(member);
            pocketLogMapper.deleteByExample(pocketLogCriteria);
            pocketMapper.deleteByPrimaryKey(member);
        }
    }

    @After
    public void deleteOrderRelate() {
        orderFarmerMapper.deleteByPrimaryKey(FARMER_ORDER_ID);
        grabSeasonMapper.deleteByPrimaryKey(CURRNET_SEASON_ID);
        SeasonOrdersCriteria seasonOrdersCriteria = new SeasonOrdersCriteria();
        seasonOrdersCriteria.or()
                            .andOrder_idEqualTo(FARMER_ORDER_ID);
        seasonOrdersMapper.deleteByExample(seasonOrdersCriteria);
        OrderInsuranceRecordCriteria orderInsuranceRecordCriteria = new OrderInsuranceRecordCriteria();
        orderInsuranceRecordCriteria.or()
                                    .andOrder_farmer_idEqualTo(FARMER_ORDER_ID);
        orderInsuranceRecordMapper.deleteByExample(orderInsuranceRecordCriteria);
    }

    @After
    public void deleteTeamRelate() {
        TeamJoinRequestCriteria teamJoinRequestCriteria = new TeamJoinRequestCriteria();
        teamJoinRequestCriteria.or()
                               .andUidEqualTo(0L);
        TeamInOrderCriteria teamInOrderCriteria = new TeamInOrderCriteria();
        teamInOrderCriteria.or()
                           .andUidEqualTo(0L);
        for (Long user : allUser) {
            teamJoinRequestCriteria.or()
                                   .andUidEqualTo(user);
            teamInOrderCriteria.or()
                               .andUidEqualTo(user);
        }
        teamJoinRequestMapper.deleteByExample(teamJoinRequestCriteria);
        teamInOrderMapper.deleteByExample(teamInOrderCriteria);
    }

    @Before
    public void initAccount() {

        for (Long driver : DRIVER_MEMBER_LIST) {
            accountMapper.insert(MainTestDataCreator.createAccount(driver, UserService.AUTHENTICATED_DRIVER));
            accountRealNameAuthMapper.insert(
                    MainTestDataCreator.createAccountRealNameAuth(driver, UserService.REAL_NAME_AUTH_STATE_PASS, "duiyuan"));
        }

        accountMapper.insert(MainTestDataCreator.createAccount(FARMER_UID, UserService.AUTHENTICATED_FARMER));
        accountRealNameAuthMapper.insert(MainTestDataCreator.createAccountRealNameAuth(FARMER_UID,
                UserService.REAL_NAME_AUTH_STATE_PASS, "nonghu"));

        for (Long leaderUsername : allDriverLeader) {
            Account leader = MainTestDataCreator.createAccount(leaderUsername, UserService.AUTHENTICATED_FARMER);
            leader.setDriver_leader(UserService.DRIVER_LEADER_AUTHED);
            accountMapper.insert(leader);
            accountRealNameAuthMapper.insert(MainTestDataCreator.createAccountRealNameAuth(leaderUsername,
                    UserService.REAL_NAME_AUTH_STATE_PASS, "leader"));
        }

    }

    @Before
    public void createDefaultWorkingTypeAndUserMachineAndOptionWorkingTypeMapping() {
        userMachineTypeMapper.insert(MainTestDataCreator.createUserMachineType(USER_MACHINE_TYPE_ID, "收割机"));
        userMachineModelMapper.insert(MainTestDataCreator.createUserMachineModel(USER_MACHINE_MODEL_ID, USER_MACHINE_TYPE_ID));

        optionWorkingTypeMapper.insert(MainTestDataCreator.createOptionWorkingType());
        optionWorkingTypeMachineModelMappingMapper.insert(MainTestDataCreator.createOptionWorkingTypeMachineModelMapping(
                USER_MACHINE_MODEL_ID, MainTestDataCreator.DEFAULT_WORKING_TYPE_ID));
        for (Long username : allUser) {
            userMachineMapper.insert(MainTestDataCreator.createUserMachine(username, USER_MACHINE_MODEL_ID));
        }
    }

    @After
    public void deleteDefaultWorkingTypeAndUserMachineAndOptionWorkingTypeMapping() {
        userMachineTypeMapper.deleteByPrimaryKey(USER_MACHINE_TYPE_ID);
        userMachineModelMapper.deleteByPrimaryKey(USER_MACHINE_MODEL_ID);
        optionWorkingTypeMapper.deleteByPrimaryKey(MainTestDataCreator.DEFAULT_WORKING_TYPE_ID);
        optionWorkingTypeMachineModelMappingMapper
                .deleteByPrimaryKey(MainTestDataCreator.createOptionWorkingTypeMachineModelMapping(USER_MACHINE_MODEL_ID,
                        MainTestDataCreator.DEFAULT_WORKING_TYPE_ID));
        UserMachineCriteria userMachineCriteria = new UserMachineCriteria();
        for (Long user : allUser) {
            userMachineCriteria.or()
                               .andUidEqualTo(user);
        }
        userMachineMapper.deleteByExample(userMachineCriteria);
    }

    @Before
    public void clearuserWorkCalendar() {
        for (Long user : allUser) {
            UserWorkCalendarCriteria userWorkCalendarCriteria = new UserWorkCalendarCriteria();
            userWorkCalendarCriteria.or()
                                    .andUidEqualTo(user);
            userWorkCalendarMapper.deleteByExample(userWorkCalendarCriteria);
        }
    }

    private void createPocketForAllTestUserWithMoney(Integer money) {
        for (Long member : allUser) {
            pocketMapper.insert(MainTestDataCreator.createPocket(member, money));
        }
    }

    private void createCanGrabedOrderWithInsuranceMoney(Integer money) {
        OrderFarmer order = MainTestDataCreator.createOrderFarmer(FARMER_UID, 1000,
                System.currentTimeMillis() / 1000 + 3600 * 24 * 4, FARMER_ORDER_ID);
        order.setDriver_insurance(money);
        orderFarmerMapper.insert(order);
        grabSeasonMapper.insert(MainTestDataCreator.createGrabseason(GrabSeasonService.STATE_PUBLISHED, CURRNET_SEASON_ID));
        seasonOrdersMapper.insert(MainTestDataCreator.createSeasonOrders(FARMER_ORDER_ID, CURRNET_SEASON_ID));
    }

    @Test
    public void TC00() {
        // blank for clear all the need data after failed
    }

    @Test
    public void TC0101_createTeamAndPayInsurance_normal() throws BusinessException {
        createPocketForAllTestUserWithMoney(INSURANCE_DEFAULT_MONEY);
        createCanGrabedOrderWithInsuranceMoney(INSURANCE_DEFAULT_MONEY);

        teamOrderBusiness.createOrderTeam(FARMER_ORDER_ID, DRIVER_LEADER, "super");

        checkMoneyAfterPaidInsurance(DRIVER_LEADER, FARMER_ORDER_ID, 0, INSURANCE_DEFAULT_MONEY,
                INSURANCE_DEFAULT_MONEY);

    }

    @Test
    public void TC0102_createTeamAndPayInsuranc_not_enough_money() {
        createPocketForAllTestUserWithMoney(INSURANCE_LESS_THAN_DEFAULT_MONEY);
        createCanGrabedOrderWithInsuranceMoney(INSURANCE_DEFAULT_MONEY);

        try {
            teamOrderBusiness.createOrderTeam(FARMER_ORDER_ID, DRIVER_LEADER, "super");
            fail();
        } catch (BusinessException e) {
            assertEquals(NOT_ENOUGH_MONEY_BUSINESS_EXCEPTION_CODE.intValue(), e.errorCode);
        }
    }

    @Test
    public void TC0201_sendTeamJoinRequest_with_Insurance_normal() throws BusinessException {
        createPocketForAllTestUserWithMoney(INSURANCE_DEFAULT_MONEY);
        createCanGrabedOrderWithInsuranceMoney(INSURANCE_DEFAULT_MONEY);
        Long teamId = teamOrderBusiness.createOrderTeam(FARMER_ORDER_ID, DRIVER_LEADER, "superinit");
        for (Long driver : DRIVER_MEMBER_LIST) {
            teamOrderBusiness.sendJoinOrderTeamRequest(teamId, driver, "what");
            checkMoneyAfterPaidInsurance(driver, FARMER_ORDER_ID, 0, INSURANCE_DEFAULT_MONEY, INSURANCE_DEFAULT_MONEY);
        }
    }

    @Test
    public void TC0302_sendTeamJoinRequest_to_second_team_in_the_same_order_with_Insurance_normal()
            throws BusinessException {
        createPocketForAllTestUserWithMoney(INSURANCE_DEFAULT_MONEY);
        createCanGrabedOrderWithInsuranceMoney(INSURANCE_DEFAULT_MONEY);
        Long firstTeamId = teamOrderBusiness.createOrderTeam(FARMER_ORDER_ID, DRIVER_LEADER, "superinit");
        Long sencondTeamId = teamOrderBusiness.createOrderTeam(FARMER_ORDER_ID, DRIVER_LEADER_SECOND, "superinit22");
        for (Long driver : DRIVER_MEMBER_LIST) {
            teamOrderBusiness.sendJoinOrderTeamRequest(firstTeamId, driver, "what");
            checkMoneyAfterPaidInsurance(driver, FARMER_ORDER_ID, 0, INSURANCE_DEFAULT_MONEY, INSURANCE_DEFAULT_MONEY);
            teamOrderBusiness.sendJoinOrderTeamRequest(sencondTeamId, driver, "what");
            checkMoneyAfterPaidInsurance(driver, FARMER_ORDER_ID, 0, INSURANCE_DEFAULT_MONEY, INSURANCE_DEFAULT_MONEY);
        }
    }

    @Test
    public void TC0402_cancelTeamJoinRequest_one_of_two_norml_without_refund() throws BusinessException {

        createPocketForAllTestUserWithMoney(INSURANCE_DEFAULT_MONEY);
        createCanGrabedOrderWithInsuranceMoney(INSURANCE_DEFAULT_MONEY);
        Long firstTeamId = teamOrderBusiness.createOrderTeam(FARMER_ORDER_ID, DRIVER_LEADER, "superinit");
        Long sencondTeamId = teamOrderBusiness.createOrderTeam(FARMER_ORDER_ID, DRIVER_LEADER_SECOND, "superinit22");
        for (Long driver : DRIVER_MEMBER_LIST) {
            teamOrderBusiness.sendJoinOrderTeamRequest(firstTeamId, driver, "what");
            checkMoneyAfterPaidInsurance(driver, FARMER_ORDER_ID, 0, INSURANCE_DEFAULT_MONEY, INSURANCE_DEFAULT_MONEY);
            teamOrderBusiness.sendJoinOrderTeamRequest(sencondTeamId, driver, "what");
            checkMoneyAfterPaidInsurance(driver, FARMER_ORDER_ID, 0, INSURANCE_DEFAULT_MONEY, INSURANCE_DEFAULT_MONEY);
            teamOrderBusiness.cancelTeamRequest(firstTeamId, driver);
            checkMoneyAfterPaidInsurance(driver, FARMER_ORDER_ID, 0, INSURANCE_DEFAULT_MONEY, INSURANCE_DEFAULT_MONEY);
        }

    }

    @Test
    public void TC0402_cancelTeamJoinRequest_two_of_two_norml_with_refund() throws BusinessException {
        createPocketForAllTestUserWithMoney(INSURANCE_DEFAULT_MONEY);
        createCanGrabedOrderWithInsuranceMoney(INSURANCE_DEFAULT_MONEY);
        Long firstTeamId = teamOrderBusiness.createOrderTeam(FARMER_ORDER_ID, DRIVER_LEADER, "superinit");
        Long sencondTeamId = teamOrderBusiness.createOrderTeam(FARMER_ORDER_ID, DRIVER_LEADER_SECOND, "superinit22");
        for (Long driver : DRIVER_MEMBER_LIST) {
            teamOrderBusiness.sendJoinOrderTeamRequest(firstTeamId, driver, "what");
            checkMoneyAfterPaidInsurance(driver, FARMER_ORDER_ID, 0, INSURANCE_DEFAULT_MONEY, INSURANCE_DEFAULT_MONEY);
            teamOrderBusiness.sendJoinOrderTeamRequest(sencondTeamId, driver, "what");
            checkMoneyAfterPaidInsurance(driver, FARMER_ORDER_ID, 0, INSURANCE_DEFAULT_MONEY, INSURANCE_DEFAULT_MONEY);
            teamOrderBusiness.cancelTeamRequest(firstTeamId, driver);
            checkMoneyAfterPaidInsurance(driver, FARMER_ORDER_ID, 0, INSURANCE_DEFAULT_MONEY, INSURANCE_DEFAULT_MONEY);
            teamOrderBusiness.cancelTeamRequest(sencondTeamId, driver);
            checkMoneyAfterRefundInsurance(driver, FARMER_ORDER_ID, INSURANCE_DEFAULT_MONEY, 0,
                    INSURANCE_DEFAULT_MONEY);
        }

    }

    @Test
    public void TC0501_denyTeamJoinRequest_two_of_two_norml_with_refund() throws BusinessException {
        createPocketForAllTestUserWithMoney(INSURANCE_DEFAULT_MONEY);
        createCanGrabedOrderWithInsuranceMoney(INSURANCE_DEFAULT_MONEY);
        Long firstTeamId = teamOrderBusiness.createOrderTeam(FARMER_ORDER_ID, DRIVER_LEADER, "superinit");
        Long sencondTeamId = teamOrderBusiness.createOrderTeam(FARMER_ORDER_ID, DRIVER_LEADER_SECOND, "superinit22");
        for (Long driver : DRIVER_MEMBER_LIST) {
            teamOrderBusiness.sendJoinOrderTeamRequest(firstTeamId, driver, "what");
            checkMoneyAfterPaidInsurance(driver, FARMER_ORDER_ID, 0, INSURANCE_DEFAULT_MONEY, INSURANCE_DEFAULT_MONEY);
            teamOrderBusiness.sendJoinOrderTeamRequest(sencondTeamId, driver, "what");
            checkMoneyAfterPaidInsurance(driver, FARMER_ORDER_ID, 0, INSURANCE_DEFAULT_MONEY, INSURANCE_DEFAULT_MONEY);
            teamOrderBusiness.denyTeamRequest(firstTeamId, driver, DRIVER_LEADER);
            checkMoneyAfterPaidInsurance(driver, FARMER_ORDER_ID, 0, INSURANCE_DEFAULT_MONEY, INSURANCE_DEFAULT_MONEY);
            teamOrderBusiness.denyTeamRequest(sencondTeamId, driver, DRIVER_LEADER_SECOND);
            checkMoneyAfterRefundInsurance(driver, FARMER_ORDER_ID, INSURANCE_DEFAULT_MONEY, 0,
                    INSURANCE_DEFAULT_MONEY);
        }

    }

    @Test
    public void TC0402_denyTeamJoinRequest_one_of_two_norml_without_refund() throws BusinessException {

        createPocketForAllTestUserWithMoney(INSURANCE_DEFAULT_MONEY);
        createCanGrabedOrderWithInsuranceMoney(INSURANCE_DEFAULT_MONEY);
        Long firstTeamId = teamOrderBusiness.createOrderTeam(FARMER_ORDER_ID, DRIVER_LEADER, "superinit");
        Long sencondTeamId = teamOrderBusiness.createOrderTeam(FARMER_ORDER_ID, DRIVER_LEADER_SECOND, "superinit22");
        for (Long driver : DRIVER_MEMBER_LIST) {
            teamOrderBusiness.sendJoinOrderTeamRequest(firstTeamId, driver, "what");
            checkMoneyAfterPaidInsurance(driver, FARMER_ORDER_ID, 0, INSURANCE_DEFAULT_MONEY, INSURANCE_DEFAULT_MONEY);
            teamOrderBusiness.sendJoinOrderTeamRequest(sencondTeamId, driver, "what");
            checkMoneyAfterPaidInsurance(driver, FARMER_ORDER_ID, 0, INSURANCE_DEFAULT_MONEY, INSURANCE_DEFAULT_MONEY);
            teamOrderBusiness.denyTeamRequest(firstTeamId, driver, DRIVER_LEADER);
            checkMoneyAfterPaidInsurance(driver, FARMER_ORDER_ID, 0, INSURANCE_DEFAULT_MONEY, INSURANCE_DEFAULT_MONEY);
        }

    }

    @Test
    public void TC0501_deleteTeamJoinRequest_norml_with_refund() throws BusinessException {

        createPocketForAllTestUserWithMoney(INSURANCE_DEFAULT_MONEY);
        createCanGrabedOrderWithInsuranceMoney(INSURANCE_DEFAULT_MONEY);
        Long firstTeamId = teamOrderBusiness.createOrderTeam(FARMER_ORDER_ID, DRIVER_LEADER, "superinit");
        Long sencondTeamId = teamOrderBusiness.createOrderTeam(FARMER_ORDER_ID, DRIVER_LEADER_SECOND, "superinit22");
        for (Long driver : DRIVER_MEMBER_LIST) {
            teamOrderBusiness.sendJoinOrderTeamRequest(firstTeamId, driver, "what");
            checkMoneyAfterPaidInsurance(driver, FARMER_ORDER_ID, 0, INSURANCE_DEFAULT_MONEY, INSURANCE_DEFAULT_MONEY);
            teamOrderBusiness.sendJoinOrderTeamRequest(sencondTeamId, driver, "what");
            checkMoneyAfterPaidInsurance(driver, FARMER_ORDER_ID, 0, INSURANCE_DEFAULT_MONEY, INSURANCE_DEFAULT_MONEY);
            teamOrderBusiness.acceptTeamRequest(firstTeamId, driver, DRIVER_LEADER);
            teamOrderBusiness.deleteTeamRequest(firstTeamId, driver, "fuck", DRIVER_LEADER);
            checkMoneyAfterRefundInsurance(driver, FARMER_ORDER_ID, INSURANCE_DEFAULT_MONEY, 0,
                    INSURANCE_DEFAULT_MONEY);
        }

    }

    @Test
    public void TC0501_deleteTeam_two_of_two_norml_with_refund() throws BusinessException {

        createPocketForAllTestUserWithMoney(INSURANCE_DEFAULT_MONEY);
        createCanGrabedOrderWithInsuranceMoney(INSURANCE_DEFAULT_MONEY);
        Long firstTeamId = teamOrderBusiness.createOrderTeam(FARMER_ORDER_ID, DRIVER_LEADER, "superinit");
        Long sencondTeamId = teamOrderBusiness.createOrderTeam(FARMER_ORDER_ID, DRIVER_LEADER_SECOND, "superinit22");
        for (Long driver : DRIVER_MEMBER_LIST) {
            teamOrderBusiness.sendJoinOrderTeamRequest(firstTeamId, driver, "what");
            checkMoneyAfterPaidInsurance(driver, FARMER_ORDER_ID, 0, INSURANCE_DEFAULT_MONEY, INSURANCE_DEFAULT_MONEY);
            teamOrderBusiness.sendJoinOrderTeamRequest(sencondTeamId, driver, "what");
            checkMoneyAfterPaidInsurance(driver, FARMER_ORDER_ID, 0, INSURANCE_DEFAULT_MONEY, INSURANCE_DEFAULT_MONEY);
        }
        teamOrderBusiness.deleteOrderTeam(firstTeamId, DRIVER_LEADER);
        teamOrderBusiness.deleteOrderTeam(sencondTeamId, DRIVER_LEADER_SECOND);
        for (Long driver : DRIVER_MEMBER_LIST) {
            checkMoneyAfterRefundInsurance(driver, FARMER_ORDER_ID, INSURANCE_DEFAULT_MONEY, 0,
                    INSURANCE_DEFAULT_MONEY);
        }

    }

    @Test
    public void TC0501_deleteTeam_one_of_two_without_refund() throws BusinessException {

        createPocketForAllTestUserWithMoney(INSURANCE_DEFAULT_MONEY);
        createCanGrabedOrderWithInsuranceMoney(INSURANCE_DEFAULT_MONEY);
        Long firstTeamId = teamOrderBusiness.createOrderTeam(FARMER_ORDER_ID, DRIVER_LEADER, "superinit");
        Long sencondTeamId = teamOrderBusiness.createOrderTeam(FARMER_ORDER_ID, DRIVER_LEADER_SECOND, "superinit22");
        for (Long driver : DRIVER_MEMBER_LIST) {
            teamOrderBusiness.sendJoinOrderTeamRequest(firstTeamId, driver, "what");
            checkMoneyAfterPaidInsurance(driver, FARMER_ORDER_ID, 0, INSURANCE_DEFAULT_MONEY, INSURANCE_DEFAULT_MONEY);
            teamOrderBusiness.sendJoinOrderTeamRequest(sencondTeamId, driver, "what");
            checkMoneyAfterPaidInsurance(driver, FARMER_ORDER_ID, 0, INSURANCE_DEFAULT_MONEY, INSURANCE_DEFAULT_MONEY);
        }
        teamOrderBusiness.deleteOrderTeam(firstTeamId, DRIVER_LEADER);
        for (Long driver : DRIVER_MEMBER_LIST) {
            checkMoneyAfterPaidInsurance(driver, FARMER_ORDER_ID, 0, INSURANCE_DEFAULT_MONEY, INSURANCE_DEFAULT_MONEY);
        }
    }

    private void checkMoneyAfterPaidInsurance(Long uid, String orderId, Integer pocketCurrentMoney,
                                              Integer pocketInsuranceMoney, Integer orderInsuranceMoney) {

        PocketLogCriteria pocketLogCriteria = new PocketLogCriteria();
        pocketLogCriteria.or()
                         .andUidEqualTo(uid)
                         .andConnect_order_idEqualTo(orderId)
                         .andTypeEqualTo(TradeFlowService.POCKETLOG_TYPE_PAY_INSURANCE);
        PocketLog pocketLog = pocketLogMapper.selectByExample(pocketLogCriteria)
                                             .get(0);
        assertEquals(orderInsuranceMoney, pocketLog.getMoney());
        assertEquals(TradeFlowService.POCKETLOG_STATE_DONE, (long) pocketLog.getState());
        Pocket pocket = pocketMapper.selectByPrimaryKey(uid);
        assertEquals(pocketCurrentMoney, pocket.getCurr_money());
        assertEquals(pocketInsuranceMoney, pocket.getInsurance());
        OrderInsuranceRecordCriteria orderInsuranceRecordCriteria = new OrderInsuranceRecordCriteria();
        orderInsuranceRecordCriteria.or()
                                    .andUidEqualTo(uid)
                                    .andOrder_farmer_idEqualTo(orderId);
        OrderInsuranceRecord orderInsuranceRecord = orderInsuranceRecordMapper
                .selectByExample(orderInsuranceRecordCriteria)
                .get(0);
        assertEquals(orderInsuranceMoney, orderInsuranceRecord.getMoney());
    }

    private void checkMoneyAfterRefundInsurance(Long uid, String orderId, Integer pocketCurrentMoney,
                                                Integer pocketInsuranceMoney, Integer orderInsuranceMoney) {

        PocketLogCriteria pocketLogCriteria = new PocketLogCriteria();
        pocketLogCriteria.or()
                         .andUidEqualTo(uid)
                         .andConnect_order_idEqualTo(orderId)
                         .andTypeEqualTo(TradeFlowService.POCKETLOG_TYPE_REFUND_INSURANCE);
        PocketLog pocketLog = pocketLogMapper.selectByExample(pocketLogCriteria)
                                             .get(0);
        assertEquals(orderInsuranceMoney, pocketLog.getMoney());
        assertEquals(TradeFlowService.POCKETLOG_STATE_DONE, (long) pocketLog.getState());
        Pocket pocket = pocketMapper.selectByPrimaryKey(uid);
        assertEquals(pocketCurrentMoney, pocket.getCurr_money());
        assertEquals(pocketInsuranceMoney, pocket.getInsurance());
        OrderInsuranceRecordCriteria orderInsuranceRecordCriteria = new OrderInsuranceRecordCriteria();
        orderInsuranceRecordCriteria.or()
                                    .andUidEqualTo(uid)
                                    .andOrder_farmer_idEqualTo(orderId);
        assertEquals(true, orderInsuranceRecordMapper.selectByExample(orderInsuranceRecordCriteria)
                                                     .isEmpty());
    }
}
