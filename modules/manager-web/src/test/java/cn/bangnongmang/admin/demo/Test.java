package cn.bangnongmang.admin.demo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;

@SuppressWarnings("deprecation")
public class Test {

public static void main(String[] args) throws IOException {
//	ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//	Resource[] resources = resolver.getResources("classpath:src/main/webapp/WEB-INF/spring/root-context.xml");
//	for (Resource resource : resources) {
//		System.out.println(resource.getDescription());
//		BeanFactory beanFactory = new XmlBeanFactory(resource);
//	}
	System.out.println("----------------------------------");
	
	String sha1Hex = DigestUtils.sha1Hex("11111111111");
	System.out.println(sha1Hex);
	System.out.println("-----------------------");
	
	
}




}
