package cn.bangnongmang.admin.util;

import cn.bangnongmang.admin.data.domain.AdminAccount;
import cn.bangnongmang.admin.data.domain.AreaDict;

public class AccountUtils {
	
	public static final int LEVEL_SUPER = 999;
	public static final int LEVEL_SERVICE = 100;
	public static final int LEVEL_LEADER = 50;
	public static final int LEVEL_MARKET = 40;
	public static final int LEVEL_NONE = 0;
	
	public static boolean isSuperAdmin(AdminAccount account) {
		if (account.getLevel().equals(LEVEL_SUPER)) {
			return true;
		}
		return false;
	}

	public static boolean isService(AdminAccount account) {
		if (account.getLevel().equals(LEVEL_SERVICE)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isLeader(AdminAccount account) {
		if (account.getLevel().equals(LEVEL_LEADER)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isMarket(AdminAccount account) {
		if (account.getLevel().equals(LEVEL_MARKET)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean areaContains(AreaDict target, String province, String city, String county, String town) {
		String targetArea = target.getProvince();
		if (target.getCity() != null) {
			targetArea += target.getCity();
			if (target.getCounty() != null) {
				targetArea += target.getCounty();
				if (target.getTown() != null) {
					targetArea += target.getTown();
				}
			}
		}
		String nowArea = province + city + county + town;
		return nowArea.startsWith(targetArea);
	}
	
	public static boolean notEmpty(String str) {
		if (str == null) {
			return false;
		} else if (str.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

}
