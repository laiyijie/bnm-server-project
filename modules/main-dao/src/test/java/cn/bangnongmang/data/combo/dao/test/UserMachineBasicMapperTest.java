package cn.bangnongmang.data.combo.dao.test;

import cn.bangnongmang.data.combo.dao.UserMachineBasicMapper;
import com.alibaba.fastjson.JSON;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by lucq on 2017/6/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:test-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserMachineBasicMapperTest {

	@Autowired
	private UserMachineBasicMapper userMachineBasicMapper;

	@Test
	public void selectAllUserMachineBasicTest(){
		System.out.println(JSON.toJSONString(userMachineBasicMapper.selectAllUserMachineBasic()));
	}
}
