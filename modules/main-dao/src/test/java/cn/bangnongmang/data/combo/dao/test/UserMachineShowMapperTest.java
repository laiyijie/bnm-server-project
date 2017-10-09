package cn.bangnongmang.data.combo.dao.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import cn.bangnongmang.data.combo.dao.UserMachineShowMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:test-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserMachineShowMapperTest {

	@Autowired
	private UserMachineShowMapper userMachineShowMapper;


	// @Test
	// public void tt() {
	// System.out.println(userMachineShowMapper.selectByUserName("13021929522"));
	// }
	//
	// @Test
	// public void selectOne() {
	// System.out.println(userMachineShowMapper.selectByUserMachineId(1483509469452695L));
	// }
	//
	// @Test
	// public void selectIt() {
	// GeoWorkHistory history = geoWorkHistoryMapper.selectByPrimaryKey(1);
	// System.out.println(JSON.toJSONString(history));
	// }

	@Test
	public void tt() {
		System.out.println(JSON.toJSONString(userMachineShowMapper.selectByUid(121L),true) );
	}

}
