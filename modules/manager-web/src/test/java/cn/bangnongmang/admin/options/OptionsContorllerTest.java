package cn.bangnongmang.admin.options;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cn.bangnongmang.data.domain.*;
import cn.bangnongmang.server.test.MainTestDataCreator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.bangnongmang.admin.testutil.AdminTestDataCreator;
import cn.bangnongmang.admin.testutil.ControllerConfigUtil;
import cn.bangnongmang.data.dao.OptionClusterMapper;
import cn.bangnongmang.data.dao.OptionClusterWorkingTypeMappingMapper;
import cn.bangnongmang.data.dao.OptionDetailMapper;
import cn.bangnongmang.data.dao.OptionWorkingTypeMapper;

public class OptionsContorllerTest extends ControllerConfigUtil{

	@Autowired
	private OptionClusterMapper optionClusterMapper;

	@Autowired
	private OptionDetailMapper optionDetailMapper;

	@Autowired
	private OptionWorkingTypeMapper optionWorkingTypeMapper;

	@Autowired
	private OptionClusterWorkingTypeMappingMapper optionClusterWorkingTypeMappingMapper;
	
	public static final long clusterid=20;
	public static final long clusterid1=21;
	public static final long Optionid=30;
	public static final long Optionid2=31;
	public static final long OptionWorkingType=41;
	public static final long OptionWorkingType1=42;
	@Before
	public void setup() {
		
			optionClusterMapper.insert(MainTestDataCreator.createOptionCluster(clusterid,"车辆吨位", "3吨以下或3吨以上"));
			optionClusterMapper.insert(MainTestDataCreator.createOptionCluster(clusterid1,"车辆吨位", "3吨以下或3吨以上"));
			optionDetailMapper.insert(MainTestDataCreator.createOptionDetail(Optionid, "2.5吨", clusterid));
			optionDetailMapper.insert(MainTestDataCreator.createOptionDetail(Optionid2, "4吨", clusterid));
		cn.bangnongmang.data.domain.OptionWorkingType optionWorkingType = MainTestDataCreator.createOptionWorkingType();
		optionWorkingType.setId(OptionWorkingType);
			optionWorkingTypeMapper.insert(optionWorkingType);
			optionClusterWorkingTypeMappingMapper.insert(MainTestDataCreator.createOptionClusterWorkingTypeMappingKey(clusterid, OptionWorkingType));
			optionClusterWorkingTypeMappingMapper.insert(
					MainTestDataCreator.createOptionClusterWorkingTypeMappingKey(OptionWorkingType1, OptionWorkingType));
	}

	@After
	public void tearDown() {
		OptionClusterCriteria clusterCriteria = new OptionClusterCriteria();
		clusterCriteria.clear();
		clusterCriteria.or().andIdGreaterThan((long) 19);
		optionClusterMapper.deleteByExample(clusterCriteria);
		
		OptionDetailCriteria criteria1 = new OptionDetailCriteria();
		criteria1.clear();
		criteria1.or().andIdGreaterThan((long) 29);
		optionDetailMapper.deleteByExample(criteria1);
		
		OptionWorkingTypeCriteria criteria3 = new OptionWorkingTypeCriteria();
		criteria3.or().andIdGreaterThan((long) 40);
		optionWorkingTypeMapper.deleteByExample(criteria3);
		
		OptionClusterWorkingTypeMappingCriteria criteria2 = new OptionClusterWorkingTypeMappingCriteria();
		criteria2.or().andCluster_idGreaterThan((long) 19);
		optionClusterWorkingTypeMappingMapper.deleteByExample(criteria2);
	}

	@Test
	public void test001_getOrderList() throws Exception {
		getMockMvc().perform(post("/options/getOptionClusterlList")
				.session(getSession()))
				.andExpect(status().isOk())
				.andDo(print());
		
	}

	@Test
	public void test002_updateOptionCluster() throws Exception {
		getMockMvc().perform(post("/options/updateOptionCluster")
				.param("id", "20")
				.param("clusterName", "车辆宽度")
				.param("description", "2.5或3.0")
				.session(getSession())).andExpect(status().isOk()).andDo(print());
		
	}

	@Test
	public void test003_insertOptionCluster() throws Exception {
		getMockMvc().perform(post("/options/insertOptionCluster")
				.param("clusterName", "车辆吨位")
				.param("description", "2.5以上或3.0以下")
				.session(getSession())).andExpect(status().isOk()).andDo(print());
		
	}

	@Test
	public void test004_getOptionDetaillList() throws Exception {
		getMockMvc().perform(post("/options/getOptionDetaillList")
				.param("clusterId", "20").session(getSession())).andExpect(status().isOk()).andDo(print());

	}
	@Test
	public void test005_updateOptionDetail() throws Exception {
		getMockMvc().perform(post("/options/updateOptionDetail")
				.param("id", "31")
				.param("optionName", "5吨")
				.session(getSession())).andExpect(status().isOk()).andDo(print());

	}
	@Test
	public void test006_insertOptionDetail() throws Exception {
		getMockMvc().perform(post("/options/insertOptionDetail")
				.param("optionName", "23")
				.param("clusterId", "20")
				.session(getSession())).andExpect(status().isOk()).andDo(print());

	}
	@Test
	public void test007_getOptionWorkingTypeList() throws Exception {
		getMockMvc().perform(post("/options/getOptionWorkingTypeList")
				.param("clusterId", "20")
				.session(getSession()))
		.andExpect(status().isOk()).andDo(print());

	}
	@Test
	public void test008_updateOptionWorkingType() throws Exception {
		getMockMvc().perform(post("/options/updateOptionWorkingType")
				.param("id", "41")
				.param("croptype", "花生")
				.param("workingType", "播种").session(getSession())).andExpect(status().isOk()).andDo(print());

	}
	@Test
	public void test009_insertOptionWorkingType() throws Exception {
		getMockMvc().perform(post("/options/insertOptionWorkingType")
				.param("cropType", "花生")
				.param("workingType", "播种11")
				.session(getSession())).andExpect(status().isOk()).andDo(print());

	}
	@Test
	public void test010_addOptionClusterWorkingType() throws Exception {
		getMockMvc().perform(post("/options/addOptionClusterWorkingType")
				.param("clusterId", "21")
				.param("workingTypeId", "41")
				.session(getSession())).andExpect(status().isOk()).andDo(print());
	}
	@Test
	public void test011_getOptionClusterls() throws Exception {
		getMockMvc().perform(post("/options/getOptionClusterls")
				.param("workingTypeId", "41")
				.session(getSession())).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void test012_delOptionClusterWorkingType() throws Exception {
		getMockMvc().perform(post("/options/delOptionClusterWorkingType")
				.param("clusterId", "20")
				.param("workingTypeId", "41")
				.session(getSession())).andExpect(status().isOk()).andDo(print());
	}
	@Test
	public void test013_deleteOptionCluster() throws Exception {
		getMockMvc().perform(post("/options/deleteOptionCluster")
				.param("id", "20")
				.session(getSession())).andExpect(status().isOk()).andDo(print());
	}
	@Test
	public void test014_deleteOptionDetail() throws Exception {
		getMockMvc().perform(post("/options/deleteOptionDetail")
				.param("id", "30")
				.session(getSession())).andExpect(status().isOk()).andDo(print());
	}
	@Test
	public void test015_deleteOptionWorkingType() throws Exception {
		getMockMvc().perform(post("/options/deleteOptionWorkingType")
				.param("id", "41")
				.session(getSession())).andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	public void test015_getWorkingTypeList() throws Exception {
		getMockMvc().perform(post("/options/getWorkingTypeList")
				.session(getSession())).andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	public void test016_getOptionDetails() throws Exception {
		getMockMvc().perform(post("/options/getOptionDetails")
				.session(getSession())).andExpect(status().isOk()).andDo(print());
	}
}