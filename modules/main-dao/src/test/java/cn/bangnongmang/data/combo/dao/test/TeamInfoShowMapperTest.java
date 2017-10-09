package cn.bangnongmang.data.combo.dao.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import cn.bangnongmang.data.combo.dao.TeamInfoShowMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:test-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TeamInfoShowMapperTest {

	@Autowired
	private TeamInfoShowMapper mapper;
	@Test
	public void test(){
//		System.out.println( JSON.toJSONString(mapper.selectTeamInfoShowByOrderId("201611301825082298715502"),true));
	}
	
	@Test
	public void tt(){
		System.out.println(JSON.toJSONString(mapper.selectTeamInfoShowByRequester(25236L)));
	}
}
