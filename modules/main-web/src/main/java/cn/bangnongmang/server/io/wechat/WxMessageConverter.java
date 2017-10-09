package cn.bangnongmang.server.io.wechat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class WxMessageConverter {

	public static long jsDateToTimeStamp(String jsDate) {
		String[] tmp = jsDate.split(" ");

		if (tmp.length < 4) {
			return 0;
		}
		String month = tmp[1];
		String day = tmp[2];
		String year = tmp[3];

		Map<String, Integer> monthMap = new HashMap<String, Integer>() {
			{
				put("Jan", 1);
				put("Feb", 2);
				put("Mar", 3);
				put("Apr", 4);
				put("May", 5);
				put("Jun", 6);
				put("Jul", 7);
				put("Aug", 8);
				put("Sep", 9);
				put("Oct", 10);
				put("Nov", 11);
				put("Dec", 12);
			}
		};

		Date date = new Date(Integer.parseInt(year)-1900, monthMap.get(month)-1, Integer.parseInt(day));
		System.out.print(date);
		return date.getTime() / 1000;
	}
}
