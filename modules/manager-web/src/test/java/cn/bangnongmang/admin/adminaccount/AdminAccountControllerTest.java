package cn.bangnongmang.admin.adminaccount;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.bangnongmang.admin.data.dao.AccountAreaMapper;
import cn.bangnongmang.admin.data.dao.AdminAccountMapper;
import cn.bangnongmang.admin.data.dao.AreaDictMapper;
import cn.bangnongmang.admin.data.domain.AccountAreaCriteria;
import cn.bangnongmang.admin.testutil.AdminTestDataCreator;
import cn.bangnongmang.admin.testutil.ControllerConfigUtil;
@Transactional
public class AdminAccountControllerTest extends ControllerConfigUtil{

	@Autowired
	private AdminAccountMapper adminAccMapper;
	@Autowired
	private AreaDictMapper areaDictMapper;
	@Autowired
	private AccountAreaMapper accountAreaMapper;
	String username="quanquan";
	Integer area_id=5;
	Integer id1=123456;
	Integer id2=123457;
	@Before
	public void setup() {
		adminAccMapper.insert(AdminTestDataCreator.createAdminAccount(username));
	    accountAreaMapper.insert(AdminTestDataCreator.createAccountAreaKey(area_id, username));
	    areaDictMapper.insert(AdminTestDataCreator.createAreaDict(id1));
	    areaDictMapper.insert(AdminTestDataCreator.createAreaDict(id2));
	}

	@After
	public void tearDown() {
		adminAccMapper.deleteByPrimaryKey(username);
		AccountAreaCriteria accountAreaCriteria = new AccountAreaCriteria();
		accountAreaCriteria.or().andUsernameEqualTo(username);
		accountAreaMapper.deleteByExample(accountAreaCriteria);
		areaDictMapper.deleteByPrimaryKey(id1);
		areaDictMapper.deleteByPrimaryKey(id2);
	}

	@Test
	public void test001_getOrderList() throws Exception {
		getMockMvc().perform(post("/deleteAccount")
				.param("username", username)
				.session(getSession()))
				.andExpect(status().isOk())
				.andDo(print());
		
	}

	
}