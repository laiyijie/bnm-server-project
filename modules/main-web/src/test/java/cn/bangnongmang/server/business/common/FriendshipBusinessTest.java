package cn.bangnongmang.server.business.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
import org.springframework.transaction.annotation.Transactional;

import cn.bangnongmang.data.dao.AccountGeoMapper;
import cn.bangnongmang.data.dao.AccountMapper;
import cn.bangnongmang.data.dao.AccountPortraitMapper;
import cn.bangnongmang.data.dao.AccountRealNameAuthMapper;
import cn.bangnongmang.data.dao.FriendshipMapper;
import cn.bangnongmang.data.dao.FriendshipRequestMapper;
import cn.bangnongmang.data.dao.FriendshipSpecialMapper;
import cn.bangnongmang.data.dao.StarUserMapper;
import cn.bangnongmang.data.domain.AccountCriteria;
import cn.bangnongmang.data.domain.AccountGeoCriteria;
import cn.bangnongmang.data.domain.AccountPortraitCriteria;
import cn.bangnongmang.data.domain.Friendship;
import cn.bangnongmang.data.domain.FriendshipCriteria;
import cn.bangnongmang.data.domain.FriendshipRequest;
import cn.bangnongmang.data.domain.FriendshipRequestCriteria;
import cn.bangnongmang.data.domain.FriendshipSpecial;
import cn.bangnongmang.data.domain.FriendshipSpecialCriteria;
import cn.bangnongmang.data.domain.StarUserCriteria;
import cn.bangnongmang.server.io.BusinessException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FriendshipBusinessTest {

	private Long TEST_UID = 18334741666L;
	private String NAME = "wenbo";
	private Long TEST_UID2 = 15021989057L;
	private String NAME2 = "luchangquan";
	private Integer REAL_NAME_AUTH_STATE_PASS = 400;
	private Long USERNAMENOTEXSIT = 11111100L;
	@Autowired
	private FriendBusiness friendBusiness;
	@Autowired
	private AccountRealNameAuthMapper accountRealNameAuthMapper;
	@Autowired
	private FriendshipRequestMapper friendshipRequestMapper;
	@Autowired
	private AccountMapper accountmapper;
	@Autowired
	private AccountGeoMapper accountGeoMapper;
	@Autowired
	private AccountPortraitMapper accountPortraitMapper;
	@Autowired
	private StarUserMapper starUserMapper;
	@Autowired
	private FriendshipMapper friendShipMapper;
	@Autowired
	private FriendshipSpecialMapper friendshipSpecialMapper;

	@Before
	public void Init() {

		accountRealNameAuthMapper
				.insert(MainTestDataCreator.createAccountRealNameAuth(TEST_UID, REAL_NAME_AUTH_STATE_PASS, NAME));
		accountRealNameAuthMapper
				.insert(MainTestDataCreator.createAccountRealNameAuth(TEST_UID2, REAL_NAME_AUTH_STATE_PASS, NAME2));

		accountmapper.insert(MainTestDataCreator.createAccount(TEST_UID, 40));
		accountGeoMapper.insert(MainTestDataCreator.createAccountGeo(TEST_UID));
		accountPortraitMapper.insert(MainTestDataCreator.createAccountPortrait(TEST_UID));
		starUserMapper.insert(MainTestDataCreator.createStarUserMapper(TEST_UID));

		accountmapper.insert(MainTestDataCreator.createAccount(TEST_UID2, 40));
		starUserMapper.insert(MainTestDataCreator.createStarUserMapper(TEST_UID2));
	}

	@After
	public void Clean() {

		accountRealNameAuthMapper.deleteByPrimaryKey(TEST_UID);
		accountRealNameAuthMapper.deleteByPrimaryKey(TEST_UID2);

		FriendshipRequestCriteria friendshipRequestCriteria = new FriendshipRequestCriteria();
		friendshipRequestCriteria.or().andRequesterEqualTo(TEST_UID);
		friendshipRequestMapper.deleteByExample(friendshipRequestCriteria);

		FriendshipRequestCriteria friendshipRequestCriteria2 = new FriendshipRequestCriteria();
		friendshipRequestCriteria.or().andRequesterEqualTo(TEST_UID2);
		friendshipRequestMapper.deleteByExample(friendshipRequestCriteria2);

		AccountCriteria accountCriteria = new AccountCriteria();
		accountCriteria.or().andUidEqualTo(TEST_UID);
		accountmapper.deleteByExample(accountCriteria);
		AccountGeoCriteria accountGeoCriteria = new AccountGeoCriteria();
		accountCriteria.or().andUidEqualTo(TEST_UID);
		accountGeoMapper.deleteByExample(accountGeoCriteria);
		AccountPortraitCriteria accountPortraitCriteria = new AccountPortraitCriteria();
		accountPortraitCriteria.or().andUidEqualTo(TEST_UID);
		accountPortraitMapper.deleteByExample(accountPortraitCriteria);
		StarUserCriteria starUserCriteria = new StarUserCriteria();
		starUserCriteria.or().andUidEqualTo(TEST_UID);
		starUserMapper.deleteByExample(starUserCriteria);

		accountCriteria.or().andUidEqualTo(TEST_UID2);
		accountmapper.deleteByExample(accountCriteria);
		starUserCriteria.or().andUidEqualTo(TEST_UID2);
		starUserMapper.deleteByExample(starUserCriteria);

	}

	@Test
	public void test_getFriendsList_Normal() throws BusinessException {
		List<Friendship> friendships_List = friendBusiness.getFriendsList(TEST_UID);
		assertEquals(0, friendships_List.size());
	}

	@Test
	public void test_getFriendsList_WithoutUsername() {

		try {
			List<Friendship> friendships_List = friendBusiness.getFriendsList(null);
			fail();
		} catch (BusinessException e) {
			assertEquals("请先登录", e.errorMessage);
		}
	}

	@Test
	@Transactional
	public void sendFriendRequest_Normal_MultiRequest() throws BusinessException {

		friendBusiness.sendFriendRequest(TEST_UID, TEST_UID2, "wenbo");
		friendBusiness.sendFriendRequest(TEST_UID2, TEST_UID, "quanquan");

		friendBusiness.sendFriendRequest(TEST_UID, TEST_UID2, "wenbo");
		friendBusiness.sendFriendRequest(TEST_UID2, TEST_UID, "quanquan");

		friendBusiness.sendFriendRequest(TEST_UID, TEST_UID2, "wenbo");
		friendBusiness.sendFriendRequest(TEST_UID2, TEST_UID, "quanquan");

		FriendshipRequestCriteria friendshipRequestCriteria = new FriendshipRequestCriteria();
		friendshipRequestCriteria.or().andRequesterEqualTo(TEST_UID);
		List<FriendshipRequest> list = friendshipRequestMapper.selectByExample(friendshipRequestCriteria);

		assertEquals(1, list.size());
		assertEquals(TEST_UID2, list.get(0).getResponser());

		FriendshipRequestCriteria friendshipRequestCriteria2 = new FriendshipRequestCriteria();
		friendshipRequestCriteria2.or().andRequesterEqualTo(TEST_UID2);
		List<FriendshipRequest> list2 = friendshipRequestMapper.selectByExample(friendshipRequestCriteria2);

		assertEquals(1, list2.size());
		assertEquals(TEST_UID, list2.get(0).getResponser());

	}

	@Test
	@Transactional
	public void sendFriendRequest_receiverNotExist() {

		try {
			friendBusiness.sendFriendRequest(TEST_UID, USERNAMENOTEXSIT, "wenbo");
			fail();
		} catch (BusinessException e) {
			assertEquals("该用户尚未认证，无法添加好友", e.errorMessage);
		}

	}

	@Test
	@Transactional
	public void acceptFriendRequest_requestAfterAcceptted() {
		try {
			friendBusiness.sendFriendRequest(TEST_UID, TEST_UID2, "wenbo");
			friendBusiness.sendFriendRequest(TEST_UID2, TEST_UID, "quanquan");
			friendBusiness.sendFriendRequest(TEST_UID, TEST_UID2, "wenbo");
			friendBusiness.sendFriendRequest(TEST_UID2, TEST_UID, "quanquan");

			friendBusiness.acceptFriendRequest(TEST_UID, TEST_UID2);

			friendBusiness.sendFriendRequest(TEST_UID2, TEST_UID, "wenbo");
			fail();

		} catch (BusinessException e) {
			assertEquals("该用户已经是您的好友", e.errorMessage);
		}

		FriendshipCriteria friendshipCriteria = new FriendshipCriteria();
		friendshipCriteria.or().andUseraEqualTo(TEST_UID);
		friendShipMapper.deleteByExample(friendshipCriteria);
		friendshipCriteria.clear();
		friendshipCriteria.or().andUseraEqualTo(TEST_UID2);
		friendShipMapper.deleteByExample(friendshipCriteria);
	}

	@Test
	public void acceptFriendRequest_acceptedMultitimes() throws BusinessException {

		friendBusiness.sendFriendRequest(TEST_UID, TEST_UID2, "wenbo");

		friendBusiness.acceptFriendRequest(TEST_UID, TEST_UID2);
		friendBusiness.acceptFriendRequest(TEST_UID, TEST_UID2);
		friendBusiness.acceptFriendRequest(TEST_UID, TEST_UID2);

		FriendshipRequestCriteria friendshipRequestCriteria = new FriendshipRequestCriteria();
		friendshipRequestCriteria.or().andRequesterEqualTo(TEST_UID);
		friendshipRequestMapper.deleteByExample(friendshipRequestCriteria);

		FriendshipCriteria friendshipCriteria = new FriendshipCriteria();
		friendshipCriteria.or().andUseraEqualTo(TEST_UID);
		friendShipMapper.deleteByExample(friendshipCriteria);
		friendshipCriteria.clear();

		friendshipCriteria.or().andUseraEqualTo(TEST_UID2);
		friendShipMapper.deleteByExample(friendshipCriteria);

	}

	@Test
	@Transactional
	public void deleteFrienshipRequest() {

		try {
			friendBusiness.deleteFrienshipRequest(TEST_UID, TEST_UID2);
			fail();
		} catch (BusinessException e) {
			assertEquals("没有此好友请求", e.errorMessage);
		}
	}

	@Test
	public void addSpecialFriendMultitimes() throws BusinessException {

		friendBusiness.addSpecialFriend(TEST_UID, TEST_UID2);
		friendBusiness.addSpecialFriend(TEST_UID, TEST_UID2);
		friendBusiness.addSpecialFriend(TEST_UID, TEST_UID2);

		FriendshipSpecialCriteria friendshipSpecialCriteria = new FriendshipSpecialCriteria();
		friendshipSpecialCriteria.or().andBelongtoEqualTo(TEST_UID);
		List<FriendshipSpecial> list = friendshipSpecialMapper.selectByExample(friendshipSpecialCriteria);
		assertEquals(1, list.size());
		friendshipSpecialMapper.deleteByExample(friendshipSpecialCriteria);

	}

	@Test
	public void selfAddSpecialFriend() {
		try {
			friendBusiness.addSpecialFriend(TEST_UID, TEST_UID);
		} catch (BusinessException e) {
			assertEquals("不能添加自己为特殊好友", e.errorMessage);
		}

	}
}
