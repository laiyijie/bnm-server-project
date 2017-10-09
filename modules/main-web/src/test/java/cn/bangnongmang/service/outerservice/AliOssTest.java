package cn.bangnongmang.service.outerservice;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AliOssTest {

	@Autowired
	private AliOssService ossService;
	@Autowired
	private IDCardExplainService idCardExplainService;

	@Test
	public void testGetCredentail() {
		// ossService.getGetAndPartOfPutCriDential("bnm-head", "abc/aa",
		// "android");
	}

	@Test
	public void testIDCardUtil() {
		System.out.println(JSON.toJSONString(idCardExplainService.explainIDCardNumber("350821199205241219")));
	}
}
