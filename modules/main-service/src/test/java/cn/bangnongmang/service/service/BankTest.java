package cn.bangnongmang.service.service;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.bangnongmang.service.outerservice.AlipayService;
import cn.bangnongmang.service.outerservice.impl.RxBankAuth;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration({"classpath:test-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BankTest {

	@Autowired
	RxBankAuth rx;

	@Autowired
	AlipayService a;
	
	
	@Test
	public void t(){
//		long currTime = System.currentTimeMillis();
//		String date = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date(currTime));
//		ClientReq req = new ClientReq();
//        req.setFuncNo("#109000");
//        req.setOrgNo("80020004");
//        req.setSeqToken( String.valueOf(currTime));
//        req.setSeqTime(Long.parseLong(date));
//        JSONObject js = new JSONObject();
//        js.put("prodNo", "00000039");
//        Map<String,String> map = new HashMap<>();
//        map.put("pan", "6214830211554612");
//        map.put("certType", "01");
//        map.put("certNo", "350821199205241219");
//        map.put("name", "赖毅杰");
//        map.put("cel", "18801902298");
//        js.put("prodParams", map);
//        req.setReqJson(js.toJSONString());
//        System.out.println(req);
//        ClientRsp rsp = rx.auth(req);
//        System.out.println(rsp.toString());
		
//		ClientRsp rsp = rx.auth("6214830211554612", "01", "350821199205241219", "赖毅杰", "18801902298");
	}
	


	@Test
	public void testValidateCard(){
		System.out.println(a.validateCard("376734776291007"));
	}

}