package cn.bangnongmang.server.business.common;

import static junit.framework.Assert.assertEquals;

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
import cn.bangnongmang.data.dao.OrderFarmerMapper;
import cn.bangnongmang.data.dao.StarEvaluationsMapper;
import cn.bangnongmang.data.dao.StarUserMapper;
import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.AccountCriteria;
import cn.bangnongmang.data.domain.StarEvaluationsCriteria;
import cn.bangnongmang.data.domain.StarUserCriteria;
import cn.bangnongmang.service.service.StarService;
import cn.bangnongmang.service.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StarBusinessTest {

    private final static Long LEADER = 18801902298L;
    private final static Long MEMBER = 18801902299L;
    private final static String ORDER_ID = "123654789";
    private Account leader;
    private Account member;

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private StarService starService;
    @Autowired
    private StarEvaluationsMapper starEvaluationsMapper;
    @Autowired
    private StarBusiness commonStarBuisness;
    @Autowired
    private StarUserMapper starUserMapper;
    @Autowired
    private OrderFarmerMapper orderFarmerMapper;

    @Before
    public void b() {
        clear();
        leader = MainTestDataCreator.createAccount(LEADER, UserService.AUTHENTICATED_DRIVER);
        member = MainTestDataCreator.createAccount(MEMBER, UserService.AUTHENTICATED_DRIVER);
        leader.setDriver_leader(UserService.DRIVER_LEADER_AUTHED);
        accountMapper.insert(leader);
        accountMapper.insert(member);
    }

    @After
    public void after() {
        clear();
    }

    private void clear() {

        AccountCriteria accountCriteria = new AccountCriteria();
        accountCriteria.or()
                       .andUidEqualTo(LEADER);
        accountMapper.deleteByExample(accountCriteria);

        accountCriteria = new AccountCriteria();
        accountCriteria.or()
                       .andUidEqualTo(MEMBER);
        accountMapper.deleteByExample(accountCriteria);

        StarEvaluationsCriteria starEvaluationsCriteria = new StarEvaluationsCriteria();
        starEvaluationsCriteria.or()
                               .andValutedEqualTo(LEADER)
                               .andValutorEqualTo(MEMBER);
        starEvaluationsMapper.deleteByExample(starEvaluationsCriteria);

        StarUserCriteria starUserCriteria = new StarUserCriteria();
        starUserCriteria.or()
                        .andUidEqualTo(LEADER);
        starUserMapper.deleteByExample(starUserCriteria);

        starEvaluationsCriteria = new StarEvaluationsCriteria();
        starEvaluationsCriteria.or()
                               .andValutedEqualTo(MEMBER)
                               .andValutorEqualTo(LEADER);
        starEvaluationsMapper.deleteByExample(starEvaluationsCriteria);

        starUserCriteria = new StarUserCriteria();
        starUserCriteria.or()
                        .andUidEqualTo(MEMBER);
        starUserMapper.deleteByExample(starUserCriteria);

    }

    @Test
    public void test() {

    }

    // @Test
    //
    // public void test_member_value_leader() throws BusinessException {
    // commonStarBuisness.star(MEMBER, LEADER, 5.0, ORDER_ID,
    // StarBusiness.STAR_LEADER);
    //
    // StarUser starUser = starService.getUserStar(LEADER);
    // assertEquals(starUser.getCaptain_evaluations().intValue(), 1);
    // assertEquals(starUser.getCaptain_star(), ((4 * 2.8) + 5) / 3.8);
    //
    // assertEquals(starUser.getMember_evaluations().intValue(), 0);
    // assertEquals(starUser.getMember_star(), 4.0);
    //
    // StarEvaluationsCriteria starEvaluationsCriteria = new
    // StarEvaluationsCriteria();
    // starEvaluationsCriteria.or().andValutedEqualTo(LEADER).andValutorEqualTo(MEMBER);
    //
    // List<StarEvaluations> starEvaluations =
    // starEvaluationsMapper.selectByExample(starEvaluationsCriteria);
    // assertEquals(starEvaluations.size(), 1);
    // StarEvaluations evaluation = starEvaluations.get(0);
    // assertEquals(evaluation.getType(), StarBusiness.STAR_LEADER);
    // assertEquals(evaluation.getOrder_id(), ORDER_ID);
    // assertEquals(evaluation.getStar(), 5.0);
    //
    // }

    // @Test
    // public void test_leader_value_member() throws BusinessException {
    // commonStarBuisness.star(LEADER, MEMBER, 5.0, ORDER_ID,
    // StarBusiness.STAR_MEMBER);
    //
    // StarUser starUser = starService.getUserStar(MEMBER);
    // assertEquals(starUser.getMember_evaluations().intValue(), 1);
    // assertEquals(starUser.getMember_star(), ((4 * 2.8) + 5) / 3.8);
    //
    // assertEquals(starUser.getCaptain_evaluations().intValue(), 0);
    // assertEquals(starUser.getCaptain_star(), 4.0);
    //
    // StarEvaluationsCriteria starEvaluationsCriteria = new
    // StarEvaluationsCriteria();
    // starEvaluationsCriteria.or().andValutedEqualTo(MEMBER).andValutorEqualTo(LEADER);
    //
    // List<StarEvaluations> starEvaluations =
    // starEvaluationsMapper.selectByExample(starEvaluationsCriteria);
    // assertEquals(starEvaluations.size(), 1);
    // StarEvaluations evaluation = starEvaluations.get(0);
    // assertEquals(evaluation.getType(), StarBusiness.STAR_MEMBER);
    // assertEquals(evaluation.getOrder_id(), ORDER_ID);
    // assertEquals(evaluation.getStar(), 5.0);
    //
    // }

}