package cn.bangnongmang.data.combo.dao.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import cn.bangnongmang.data.combo.dao.FriendInfoShowMapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:test-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FriendInfoShowMapperTest {

	@Autowired
	private FriendInfoShowMapper friendInfoShowMapper;

	@Test
	public void test() {
		System.out.println(JSON.toJSONString(friendInfoShowMapper.selectFriendRequestListByResponser(3578L)));
	}

	@Test
	public void test1() {
		System.out.println(JSON.toJSONString(friendInfoShowMapper.selectFriendInfoShowByUseraAndUserb(3578L,245L)));
	}

	@Test
	public void test2() {
		Map param=new HashMap();
		param.put("usera",3578L);
		param.put("phoneList",Arrays.asList("18256237321", "15312716597","123"));
		System.out.println(JSON.toJSONString(friendInfoShowMapper.selectFriendListByUseraAndUserbList(param)));
	}
}
