package cn.bangnongmang.service.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.bangnongmang.data.domain.Account;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextHierarchy({
	@ContextConfiguration(name="parent",value="file:src/main/webapp/WEB-INF/spring/root-context.xml"),
	@ContextConfiguration(name="child",value="file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")
})
public class ToolTest {
	
	private Logger logger = LogManager.getLogger(ToolTest.class);
	
	
	@Autowired
	private ObjectMapper oj;
	
//	public static String GetImageStr()  
//    {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理  
//        String imgFile = "d://test.jpg";//待处理的图片  
//        InputStream in = null;  
//        byte[] data = null;  
//        //读取图片字节数组  
//        try   
//        {  
//            in = new FileInputStream(imgFile);          
//            data = new byte[in.available()];  
//            in.read(data);  
//            in.close();  
//        }   
//        catch (IOException e)   
//        {  
//            e.printStackTrace();  
//        }  
//        //对字节数组Base64编码  
//        BASE64Encoder encoder = new BASE64Encoder();  
//        return "data:image/jpg;base64," + encoder.encode(data);//返回Base64编码过的字节数组字符串  
//    }  

	
	@Test
	public void testJsonencode() throws JsonProcessingException{
//		logger.info(GetImageStr());
	}
	
	@Test
	public void testInsert(){
		
	}
	
	@Test
	public void testCodeMapper() throws JsonProcessingException{
		ObjectMapper objectMapper = oj;
		
		Account account = new Account();
		account.setCreate_time(System.currentTimeMillis());
		
		String string = objectMapper.writeValueAsString(account);
		
		System.out.println(string);

	}
}
