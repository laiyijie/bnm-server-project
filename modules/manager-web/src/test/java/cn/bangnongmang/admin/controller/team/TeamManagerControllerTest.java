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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import cn.bangnongmang.admin.testutil.ControllerConfigUtil;

public class TeamManagerControllerTest extends ControllerConfigUtil {
    String orderId="201803011052299522562112";
    @Test
    public void selectRealNameAuthListByLevel() throws Exception{
	
	
	getMockMvc().perform(post("/teamManage/getTeamInOrderByorderFarmerId")
		.session(getSession())
		.param("orderFarmerId", orderId)
		).andExpect(status().isOk())
		.andDo(print());
    }
}
  
