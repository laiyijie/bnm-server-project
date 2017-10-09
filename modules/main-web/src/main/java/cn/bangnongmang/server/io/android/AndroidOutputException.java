package cn.bangnongmang.server.io.android;

import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AndroidOutputException  extends  Exception{

	/** 
	 * @Fields serialVersionUID TODO(用一句话描述这个变量表示什么)
	 */ 
	private static final long serialVersionUID = 1454137169593881892L;
	
	public int errorCode;
	public String errorMessage;
	
	
	public AndroidOutputException(int code, String message){
		this.errorCode = code;
		this.errorMessage = message;
	}
	
	public AndroidOutputException(String message){
		this.errorCode = 800;
		this.errorMessage = message;
	}
	

	private String location;

	private static Map<Integer, String> codeToMessage;

	private static final String DEFAULT_LOCATION = "classpath:exception.xml";

	private static final String TAG_EXCEPTION_ITEM = "exception";
	private static final String TAG_MAP_KEY = "code";
	private static final String TAG_MAP_VALUE = "message";


	private Map<Integer, String> getMap() {

		if (codeToMessage != null) {
			return codeToMessage;
		}

		if (codeToMessage == null) {
			ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

			if (location == null) {
				location = DEFAULT_LOCATION;
			}

			Resource file = resolver.getResource(location);

			try {

				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document doc = builder.parse(file.getFile());
				Map<Integer, String> result = new HashMap<>();

				NodeList nl = doc.getElementsByTagName(TAG_EXCEPTION_ITEM);
				for (int i = 0; i < nl.getLength(); i++) {
					Node curr = nl.item(i);
					NodeList children = curr.getChildNodes();
					Integer key = null;
					String value = null;
					for (int j = 0; j < children.getLength(); j++) {
						Node item = children.item(j);
						if (item.getNodeName() == TAG_MAP_KEY) {
							key = Integer.decode(item.getFirstChild().getNodeValue());
						} else if (item.getNodeName() == TAG_MAP_VALUE) {
							value = item.getFirstChild().getNodeValue();
						}
					}

					if (key != null && value != null) {
						result.put(key, value);
					}
				}

				codeToMessage = result;

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return codeToMessage;
	}

	public String getReflectErrorMessage(Integer errorCode) {

		Map<Integer, String> map = getMap();

		if (map.containsKey(errorCode)) {
			return map.get(errorCode);
		} else {
			return "未定义错误";
		}

	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
