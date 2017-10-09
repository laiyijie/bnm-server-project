package cn.bangnongmang.server.io.android;

public class AndroidOutputUtil {
	
	private static final String[] AGENT_NAMES = {"driverapp","okhttp/3.4.1"};
	
	public static boolean checkUserAgent(String userAgent){
		for (int i = 0; i < AGENT_NAMES.length; i++) {
			if (AGENT_NAMES[i].equals(userAgent)) {
				return true;
			}
		}
		return false;
	}
}
