package cn.bangnongmang.service.service;

import java.io.IOException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.bangnongmang.service.outerservice.util.ServiceUtil;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GeTuiTest {
	
	
	 private static String appId = "mEuXX6m1eh7si7NzLCvdq5";
	    private static String appKey = "qvy2ftmT4VAvxxkuRRV7T2";
	    private static String masterSecret = "lrPFqlCi0i7rbiPRnnjXw4";
	    private static String url = "http://sdk.open.api.igexin.com/apiex.htm";
	    
	    @Autowired
	    private ServiceUtil serviceutil;
	@Test
	public void k(){
		
//		 IGtPush push = new IGtPush(url, appKey, masterSecret);
//
//	        // 定义"点击链接打开通知模板"，并设置标题、内容、链接
//	        LinkTemplate template = new LinkTemplate();
//	        template.setAppId(appId);
//	        template.setAppkey(appKey);
//	        template.setTitle("欢迎使用个推!");
//	        template.setText("这是一条推送消息~");
//	        template.setUrl("http://getui.com");
//
//	        List<String> appIds = new ArrayList<String>();
//	        appIds.add(appId);
//
//	        // 定义"AppMessage"类型消息对象，设置消息内容模板、发送的目标App列表、是否支持离线发送、以及离线消息有效期(单位毫秒)
//	        AppMessage message = new AppMessage();
//	        message.setData(template);
//	        message.setAppIdList(appIds);
//	        message.setOffline(true);
//	        message.setOfflineExpireTime(1000 * 600);
//
//	        IPushResult ret = push.pushMessageToApp(message);
//	        System.out.println(ret.getResponse().toString());
	}
	
	@Test
	public void t1() throws IOException{

	}
}
