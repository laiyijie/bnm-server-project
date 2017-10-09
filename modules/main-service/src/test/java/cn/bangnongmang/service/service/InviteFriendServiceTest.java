package cn.bangnongmang.service.service;

import cn.bangnongmang.data.dao.InviteFriendMapper;
import cn.bangnongmang.data.domain.InviteFriend;
import cn.bangnongmang.data.domain.InviteFriendCriteria;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lucq on 2017/7/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:test-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class InviteFriendServiceTest {

	@Autowired
	private InviteFriendMapper inviteFriendMapper;
	@Autowired
	private InviteFriendService inviteFriendService;

	@Before
	public  void beforeClass(){

		InviteFriend inviteFriend=new InviteFriend();
		inviteFriend.setUid(1L);
		inviteFriend.setFriend_phone("18888888888");
		inviteFriend.setCreate_time(2345L);
		inviteFriendMapper.insert(inviteFriend);
	}
	@Test
	public void Test(){
		inviteFriendService.insertInvite(1L,"13323455432");
		InviteFriendCriteria inviteFriendCriteria=new InviteFriendCriteria();
		inviteFriendCriteria.or().andUidEqualTo(1L).andFriend_phoneEqualTo("13323455432");
		assert (inviteFriendMapper.selectByExample(inviteFriendCriteria).size()==1);

		assert (inviteFriendService.SendTooFast(1L,"13323455432",100000000L)==true);

		assert (inviteFriendService.SendTooFast(1L,"18888888888",100000000L)==false);

	}

	//@Test
	public void Test_sendMessage(){
		inviteFriendService.sendMessage("13664401628","test");
	}
}
