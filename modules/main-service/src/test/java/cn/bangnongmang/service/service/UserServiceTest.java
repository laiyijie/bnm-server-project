package cn.bangnongmang.service.service;


import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.bangnongmang.data.dao.AccountMapper;
import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.AccountCriteria;


import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration({"classpath:test-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {

	@Autowired
	private UserService userService;
	@Autowired
	private AccountMapper accountMapper;
	
	private AccountCriteria accountCriteriaWithUserNameEqualIn;
	
	private String testUsername;
	
	@Before
	public void tear(){
		
		testUsername = "18801902298";
		
		accountCriteriaWithUserNameEqualIn = new AccountCriteria();
		accountCriteriaWithUserNameEqualIn.or().andUsernameEqualTo(testUsername);
		
		List<Account> accounts = accountMapper.selectByExample(accountCriteriaWithUserNameEqualIn);
		
		if (!accounts.isEmpty()) {
			accountMapper.deleteByExample(accountCriteriaWithUserNameEqualIn);
		}
		
	}
	
	@Test
	public void t1NormalTestForCreateAccount() throws Exception{
		
		userService.createUser(testUsername, UserService.DRIVER);
		
		Account account = accountMapper.selectByExample(accountCriteriaWithUserNameEqualIn).get(0);
		
		assertEquals(account.getUsername(), testUsername);
		assertEquals(account.getNickname(), testUsername);
		assertEquals(account.getState(), new Integer(UserService.AVAILABLE));
		assertEquals(account.getLevel(), new Integer(UserService.DRIVER));
		
		accountMapper.deleteByExample(accountCriteriaWithUserNameEqualIn);
	}
	
	
	@Test
	public void t2NormalTestForGetUser() throws Exception{
		
		userService.createUser(testUsername, UserService.DRIVER);
		
		Account user = userService.getUserInfoByUsername(testUsername);
		
		
		assertEquals(user.getUsername(), testUsername);
		assertEquals(user.getLevel(), UserService.DRIVER);
		assertEquals(user.getNickname(), testUsername);
		
		accountMapper.deleteByExample(accountCriteriaWithUserNameEqualIn);
	}
	
	
	@Test
	public void t3UserAlreadyInTest(){

	}
	
	@Test
	public void t6GetNullDriver(){
		
		Account user = userService.getUserInfoByUsername(testUsername);
		
		assertEquals(user, null);
	}
	
}
