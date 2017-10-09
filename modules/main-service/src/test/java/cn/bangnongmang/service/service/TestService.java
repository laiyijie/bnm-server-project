package cn.bangnongmang.service.service;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.bangnongmang.data.combo.dao.UserMachineShowMapper;


@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration({"classpath:test-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestService {

	@Autowired
	private UserMachineShowMapper userMachineShowMapper;
	
	@Test
	public void test(){
		System.out.println(userMachineShowMapper.selectByUid(108L));
	}
}
