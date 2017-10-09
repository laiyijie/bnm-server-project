package cn.bangnongmang.server.business.driver;

import static org.junit.Assert.assertEquals;

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
import cn.bangnongmang.data.dao.AccountRealNameAuthMapper;
import cn.bangnongmang.data.dao.FriendshipMapper;
import cn.bangnongmang.data.dao.FriendshipRequestMapper;
import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.AccountCriteria;
import cn.bangnongmang.data.domain.AccountRealNameAuth;
import cn.bangnongmang.data.domain.Friendship;
import cn.bangnongmang.data.domain.FriendshipCriteria;
import cn.bangnongmang.data.domain.FriendshipRequest;
import cn.bangnongmang.data.domain.FriendshipRequestCriteria;
import cn.bangnongmang.server.business.common.FriendBusiness;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.service.service.FriendshipService;
import cn.bangnongmang.service.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DriverFriendshipBusinessTest {

	@Autowired
	private FriendBusiness friendBusiness;

	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private FriendshipMapper friendshipMapper;
	@Autowired
	private FriendshipRequestMapper friendshipRequestMapper;
	@Autowired
	private AccountRealNameAuthMapper accountRealNameAuthMapper;

	public static Long testAccountMain = 18801902298L;

	public static Long testAccountSencond = 18801791729L;

	public static Long testAccountThird = 18801971505L;

	private void clear() {

		AccountCriteria accountCriteria = new AccountCriteria();

		accountCriteria.or().andUidEqualTo(testAccountMain);
		accountCriteria.or().andUidEqualTo(testAccountSencond);
		accountCriteria.or().andUidEqualTo(testAccountThird);
		accountMapper.deleteByExample(accountCriteria);

		FriendshipCriteria friendshipCriteria = new FriendshipCriteria();

		friendshipCriteria.or().andUseraEqualTo(testAccountMain).andUserbEqualTo(testAccountSencond);
		friendshipCriteria.or().andUseraEqualTo(testAccountSencond).andUserbEqualTo(testAccountMain);
		friendshipCriteria.or().andUseraEqualTo(testAccountThird).andUserbEqualTo(testAccountMain);
		friendshipCriteria.or().andUseraEqualTo(testAccountMain).andUserbEqualTo(testAccountThird);
		friendshipMapper.deleteByExample(friendshipCriteria);

		FriendshipRequestCriteria friendshipRequestCriteria = new FriendshipRequestCriteria();

		friendshipRequestCriteria.or().andRequesterEqualTo(testAccountMain);
		friendshipRequestCriteria.or().andRequesterEqualTo(testAccountSencond);
		friendshipRequestCriteria.or().andRequesterEqualTo(testAccountThird);

		friendshipRequestCriteria.or().andResponserEqualTo(testAccountMain);
		friendshipRequestCriteria.or().andResponserEqualTo(testAccountSencond);
		friendshipRequestCriteria.or().andResponserEqualTo(testAccountThird);

		friendshipRequestMapper.deleteByExample(friendshipRequestCriteria);
		accountRealNameAuthMapper.deleteByPrimaryKey(testAccountMain);
		accountRealNameAuthMapper.deleteByPrimaryKey(testAccountSencond);
		accountRealNameAuthMapper.deleteByPrimaryKey(testAccountThird);
	}

	private void create() {

		Account account = MainTestDataCreator.createAccount(testAccountMain, 20);
		accountMapper.insert(account);
		Account account2 = MainTestDataCreator.createAccount(testAccountSencond, 20);
		accountMapper.insert(account2);
		Account account3 = MainTestDataCreator.createAccount(testAccountThird, 20);
		accountMapper.insert(account3);

		AccountRealNameAuth accountRealNameAuth = MainTestDataCreator.createAccountRealNameAuth(testAccountMain,
				UserService.REAL_NAME_AUTH_STATE_PASS, "主要");
		AccountRealNameAuth accountRealNameAuth1 = MainTestDataCreator.createAccountRealNameAuth(testAccountSencond,
				UserService.REAL_NAME_AUTH_STATE_PASS, "第二");
		AccountRealNameAuth accountRealNameAuth2 = MainTestDataCreator.createAccountRealNameAuth(testAccountThird,
				UserService.REAL_NAME_AUTH_STATE_PASS, "第三");
		accountRealNameAuthMapper.insert(accountRealNameAuth);
		accountRealNameAuthMapper.insert(accountRealNameAuth1);
		accountRealNameAuthMapper.insert(accountRealNameAuth2);
	}

	@Before
	public void be() {

		clear();

		create();
	}

	@After
	public void af() {
		clear();
	}

	@Test
	public void TC0101_getFriendsList_normal() throws BusinessException {

		Friendship friendShip = new Friendship();

		friendShip.setUsera(testAccountMain);
		friendShip.setUserb(testAccountSencond);
		friendShip.setCreate_time(System.currentTimeMillis());
		friendshipMapper.insert(friendShip);

		List<Friendship> lists = friendBusiness.getFriendsList(testAccountMain);

		assertEquals(1, lists.size());
		assertEquals(testAccountMain, lists.get(0).getUsera());
		assertEquals(testAccountSencond, lists.get(0).getUserb());

	}

	@Test
	public void TC0201_getRequestList_normal() throws BusinessException {

		FriendshipRequest friendshipRequest = new FriendshipRequest();

		friendshipRequest.setCreate_time(System.currentTimeMillis());
		friendshipRequest.setPs("哦吼吼");
		friendshipRequest.setRequester(testAccountMain);
		friendshipRequest.setResponser(testAccountSencond);

		friendshipRequestMapper.insert(friendshipRequest);

		List<FriendshipRequest> lists = friendBusiness.getRequestList(testAccountSencond);

		assertEquals(1, lists.size());

		lists = friendBusiness.getRequestList(testAccountMain);

		assertEquals(0, lists.size());
	}

	@Test
	public void TC0301_sendFriendRequest_normal() throws BusinessException {

		FriendshipRequest friendshipRequest = friendBusiness.sendFriendRequest(testAccountMain, testAccountSencond,
				"我请");

		assertEquals(testAccountMain, friendshipRequest.getRequester());
		assertEquals(testAccountSencond, friendshipRequest.getResponser());

	}

	@Test
	public void TC0401_acceptFriendRequest_normal() throws BusinessException {

		FriendshipRequest friendshipRequest = new FriendshipRequest();

		friendshipRequest.setCreate_time(System.currentTimeMillis());
		friendshipRequest.setPs("星河");
		friendshipRequest.setRequester(testAccountMain);
		friendshipRequest.setResponser(testAccountSencond);
		friendshipRequest.setState(FriendshipService.STATE_INIT);

		friendshipRequestMapper.insert(friendshipRequest);

		Friendship friendship = friendBusiness.acceptFriendRequest(testAccountMain, testAccountSencond);

		assertEquals(testAccountMain, friendship.getUsera());
		assertEquals(testAccountSencond, friendship.getUserb());

		FriendshipRequestCriteria friendshipRequestCriteria = new FriendshipRequestCriteria();

		friendshipRequestCriteria.or().andRequesterEqualTo(testAccountMain);
		friendshipRequestCriteria.or().andResponserEqualTo(testAccountSencond);

		friendshipRequest = friendshipRequestMapper.selectByExample(friendshipRequestCriteria).get(0);

		assertEquals(FriendshipService.STATE_ACCEPTED, friendshipRequest.getState());
		assertEquals(testAccountMain, friendshipRequest.getRequester());
		assertEquals(testAccountSencond, friendshipRequest.getResponser());

	}

	@Test
	public void TC0501_deleteFriendshipRequest_normal() throws BusinessException {

		FriendshipRequest friendshipRequest = new FriendshipRequest();

		friendshipRequest.setCreate_time(System.currentTimeMillis());
		friendshipRequest.setPs("星河");
		friendshipRequest.setRequester(testAccountMain);
		friendshipRequest.setResponser(testAccountSencond);
		friendshipRequest.setState(FriendshipService.STATE_INIT);

		friendshipRequestMapper.insert(friendshipRequest);

		friendshipRequest.setResponser(testAccountThird);

		friendshipRequestMapper.insert(friendshipRequest);

		boolean flag = friendBusiness.deleteFrienshipRequest(testAccountMain, testAccountSencond);

		FriendshipRequestCriteria friendshipRequestCriteria = new FriendshipRequestCriteria();
		friendshipRequestCriteria.or().andRequesterEqualTo(testAccountMain).andResponserEqualTo(testAccountSencond);

		List<FriendshipRequest> requests = friendshipRequestMapper.selectByExample(friendshipRequestCriteria);

		assertEquals(true, flag);

		assertEquals(true, requests.isEmpty());

		FriendshipRequestCriteria criteria = new FriendshipRequestCriteria();
		criteria.or().andRequesterEqualTo(testAccountMain).andResponserEqualTo(testAccountThird);

		FriendshipRequest request = friendshipRequestMapper.selectByExample(criteria).get(0);

		assertEquals(testAccountMain, request.getRequester());
		assertEquals(testAccountThird, request.getResponser());
	}

	@Test
	public void TC0601_deleteFriend_normal() throws BusinessException {

		FriendshipRequest friendshipRequest = new FriendshipRequest();

		friendshipRequest.setCreate_time(System.currentTimeMillis());
		friendshipRequest.setPs("星河");
		friendshipRequest.setRequester(testAccountMain);
		friendshipRequest.setResponser(testAccountSencond);
		friendshipRequest.setState(FriendshipService.STATE_INIT);

		friendshipRequestMapper.insert(friendshipRequest);

		friendshipRequest.setResponser(testAccountThird);

		friendshipRequestMapper.insert(friendshipRequest);

		friendBusiness.acceptFriendRequest(testAccountMain, testAccountThird);
		friendBusiness.acceptFriendRequest(testAccountMain, testAccountSencond);

		boolean flag = friendBusiness.deleteFriend(testAccountMain, testAccountSencond);

		FriendshipRequestCriteria friendshipRequestCriteria = new FriendshipRequestCriteria();
		friendshipRequestCriteria.or().andRequesterEqualTo(testAccountMain).andResponserEqualTo(testAccountSencond);

		List<FriendshipRequest> requests = friendshipRequestMapper.selectByExample(friendshipRequestCriteria);

		FriendshipCriteria friendshipCriteria = new FriendshipCriteria();
		friendshipCriteria.or().andUseraEqualTo(testAccountMain).andUserbEqualTo(testAccountSencond);
		friendshipCriteria.or().andUseraEqualTo(testAccountSencond).andUserbEqualTo(testAccountMain);

		List<Friendship> lists = friendshipMapper.selectByExample(friendshipCriteria);

		assertEquals(true, flag);

		assertEquals(true, requests.isEmpty());

		assertEquals(true, lists.isEmpty());

		FriendshipCriteria friendshipCriteria1 = new FriendshipCriteria();
		friendshipCriteria1.or().andUseraEqualTo(testAccountMain).andUserbEqualTo(testAccountThird);
		friendshipCriteria1.or().andUseraEqualTo(testAccountThird).andUserbEqualTo(testAccountMain);

		Friendship friendship = friendshipMapper.selectByExample(friendshipCriteria1).get(0);

		assertEquals(testAccountMain, friendship.getUsera());
		assertEquals(testAccountThird, friendship.getUserb());

		FriendshipRequestCriteria criteria = new FriendshipRequestCriteria();
		criteria.or().andRequesterEqualTo(testAccountMain).andResponserEqualTo(testAccountThird);

		FriendshipRequest request = friendshipRequestMapper.selectByExample(criteria).get(0);

		assertEquals(testAccountMain, request.getRequester());
		assertEquals(testAccountThird, request.getResponser());

	}

}
