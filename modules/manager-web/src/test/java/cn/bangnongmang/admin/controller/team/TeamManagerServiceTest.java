/**  
 * Project Name:manager  
 * File Name:TeamManager.java  
 * Package Name:cn.bangnongmang.admin.controller.team  
 * Date:2017年4月26日上午10:21:00  
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.  
 *  
*/
/**  
 * Project Name:manager  
 * File Name:TeamManager.java  
 * Package Name:cn.bangnongmang.admin.controller.team  
 * Date:2017年4月26日上午10:21:00  
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.  
 *  
 */

package cn.bangnongmang.admin.controller.team;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.bangnongmang.admin.service.TeamManageService;
import cn.bangnongmang.admin.testutil.ServiceConfigUtil;
import cn.bangnongmang.data.combo.domain.UserBasicInfoShow;

public class TeamManagerServiceTest extends ServiceConfigUtil {
	@Autowired
	private TeamManageService teamManageService;

	@Test
	public void selectRealNameAuthListByLevel() throws Exception {

		List<UserBasicInfoShow> selectRealNameAuthListByLevel = teamManageService.selectRealNameAuthListByLevel(40, 40);
		System.out.println(selectRealNameAuthListByLevel);
		assertNotNull(selectRealNameAuthListByLevel);
	}
}
