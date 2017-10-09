package cn.bangnongmang.admin.pockage;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cn.bangnongmang.server.test.MainTestDataCreator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.bangnongmang.admin.testutil.AdminTestDataCreator;
import cn.bangnongmang.admin.testutil.ControllerConfigUtil;
import cn.bangnongmang.data.dao.PocketMapper;

public class PockageControllerTest extends ControllerConfigUtil{
	@Autowired
	private PocketMapper pocketMapper;

	@Before
	public void setup() {
		pocketMapper.insert(MainTestDataCreator.createPocket(15738397703L, 1111));
			
}

	@After
	public void tearDown() throws Exception {
		
	pocketMapper.deleteByPrimaryKey(15738397703L);
		
	}



	@Test
	public void test001_updatePockage() throws Exception {
		
		getMockMvc().perform(
				post("/pocketCredit/updateCredit")
				.param("id", "15738397703")
				.param("credit", "3")
				.session(getSession())).andExpect(status().isOk()).andDo(print());

	
	}

}