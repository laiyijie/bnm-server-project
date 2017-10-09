package cn.bangnongmang.data.combo.dao.test;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import cn.bangnongmang.data.combo.dao.OrderFarmerInfoShowMapper;
import cn.bangnongmang.data.combo.domain.OrderFarmerInfoShow;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:test-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderFarmerInfoShowMapperTest {

	@Autowired
	private OrderFarmerInfoShowMapper mapper;

	@Test
	public void test() {
		List<OrderFarmerInfoShow> re = mapper.selectOrderFarmerInfoShowByUid(108L);

		System.out.println(JSON.toJSONString(re));

	}
	
	@Test
	public void tt(){
		
		System.out.println(JSON.toJSONString(mapper.selectOrderFarmerInfoShowByDriver(25488L)));
	}
	
	@Test
	public void tst(){
		System.out.println(JSON.toJSONString(mapper.selectOrderFarmerInfoShowByUserFavorite(25662L),true));
	}
}
