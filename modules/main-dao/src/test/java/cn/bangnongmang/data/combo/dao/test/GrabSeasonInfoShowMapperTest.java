package cn.bangnongmang.data.combo.dao.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import cn.bangnongmang.data.combo.dao.GrabSeasonInfoShowMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:test-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GrabSeasonInfoShowMapperTest {

	@Autowired
	private GrabSeasonInfoShowMapper grabSeasonInfoShowMapper;

	@Test
	public void testSelect() {

		System.out
				.println(JSON.toJSONString(grabSeasonInfoShowMapper
						.selectGrabSeasonInfoShowByMinEndTime(0L), true));
	}

}
