package cn.bangnongmang.data.combo.dao.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.bangnongmang.data.combo.dao.UserBasicInfoShowMapper;

import java.util.ArrayList;
import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration({"classpath:test-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserBasicInfoShowMapperTest {
	
	@Autowired
	private UserBasicInfoShowMapper userBasicInfoShowMapper;
	
	@Test
	public void user(){
		System.out.println(userBasicInfoShowMapper.selectByUserName("13131837507"));
	}
	
	@Test
	public void testGetRealNameAuthByLevel(){
		System.out.println(userBasicInfoShowMapper.selectRealNameAuthListByLevel(40, 40));
	}
	
	@Test
	public void testGetRealNameAuthByType(){
		System.out.println(userBasicInfoShowMapper.selectRealNameAuthListByType(300));
	}
	
	@Test
	public void testGetRealNameAuth(){
		System.out.println(userBasicInfoShowMapper.searchRealNameAuthListByNameOrPhone("范平江"));
	}

	@Test
	public void testsearchRealNameAuthListByPhoneList(){
		System.out.println(userBasicInfoShowMapper.searchRealNameAuthListByPhoneList(Arrays.asList("13131837507","18701917049","18033434383","15770626265")));
	}


}
