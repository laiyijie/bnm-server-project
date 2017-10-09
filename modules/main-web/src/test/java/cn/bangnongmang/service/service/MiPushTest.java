package cn.bangnongmang.service.service;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Sender;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MiPushTest {
	
	public static final String APP_SECRET_KEY = "i9Va38nSX0DMP7fPahw+Sg==";
	public static final String MY_PACKAGE_NAME = "cn.bangnongmang.driverapp";
	
	private static Sender sender;
	
	@BeforeClass
	public static void setSandbox(){
		Constants.useOfficial();
		sender = new Sender(APP_SECRET_KEY);
	}
	
	
	@Test
	public void test_userAccount_normal() throws IOException, ParseException{
	    String messagePayload = "This is a message";
	    String title = "userAccount";
	    String description = "199999";
	    String useraccount = "13262612952";    //useraccount非空白, 不能包含逗号, 长度小于128
	    Message message = new Message.Builder()
	                .title(title)
	                .description(description).payload(messagePayload)
	                .restrictedPackageName(MY_PACKAGE_NAME)
	                .notifyType(1)     // 使用默认提示音提示
	                .build();
//	    Result result =sender.sendToUserAccount(message, useraccount, 3); //根据useraccount, 发送消息到指定设备上
//	    System.out.println(result.getReason());
	}
	
	@Test
	public void test_alias_normal() throws IOException, ParseException{
	    String messagePayload = "This is a message";
	    String title = "Alias";
	    String description = "199999";
	    String alias = "13262612952";    
	    Message message = new Message.Builder()
	                .title(title)
	                .description(description).payload(messagePayload)
	                .restrictedPackageName(MY_PACKAGE_NAME)
	                .notifyType(1)     // 使用默认提示音提示
	                .build();
//	    Result result = sender.sendToAlias(message, alias, 3); //根据useraccount, 发送消息到指定设备上
//	    System.out.println(result.getErrorCode().getFullDescription());
//	    System.out.println(result.getReason());
	}
	
	
	@Test
	public void test_topicUNLOGIN_normal() throws IOException, ParseException{
	    String messagePayload = "This is a message";
	    String title = "topic UNLOGIN";
	    String description = "199999";
	    String topic = "UNLOGIN";    //useraccount非空白, 不能包含逗号, 长度小于128
	    Message message = new Message.Builder()
	                .title(title)
	                .description(description).payload(messagePayload)
	                .restrictedPackageName(MY_PACKAGE_NAME)
	                .notifyType(1)     // 使用默认提示音提示
	                .build();
//	    Result result =sender.broadcast(message, topic, 3); //根据useraccount, 发送消息到指定设备上
//	    System.out.println(result.getReason());
	}
	
	@Test
	public void test_topicLOGIN_normal() throws IOException, ParseException{
	    String messagePayload = "This is a message";
	    String title = "topic LOGIN";
	    String description = "199999";
	    String topic = "LOGIN";    //useraccount非空白, 不能包含逗号, 长度小于128
	    Message message = new Message.Builder()
	                .title(title)
	                .description(description).payload(messagePayload)
	                .restrictedPackageName(MY_PACKAGE_NAME)
	                .notifyType(1)     // 使用默认提示音提示
	                .build();
//	    Result result =sender.broadcast(message, topic, 3); //根据useraccount, 发送消息到指定设备上
//	    System.out.println(result.getReason());
	}
}
