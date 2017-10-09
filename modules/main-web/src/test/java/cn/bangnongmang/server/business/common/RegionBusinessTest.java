package cn.bangnongmang.server.business.common;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.bangnongmang.data.dao.RegionGeoMapper;
import cn.bangnongmang.data.dao.RegionMapper;
import cn.bangnongmang.data.domain.Region;
import cn.bangnongmang.data.domain.RegionCriteria;
import cn.bangnongmang.data.domain.RegionGeo;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.service.outerservice.util.ServiceUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegionBusinessTest {

	@Autowired
	RegionBusiness regionBusiness;
	@Autowired
	ServiceUtil serviceUtil;
	@Autowired
	RegionMapper regionMapper;
	@Autowired
	RegionGeoMapper regionGeoMapper;

	private final Long UID = 18321783963L;

	@Before
	public void be() {

		deleteAllRegionsByUsername(UID);

	}

	@After
	public void after() {
		deleteAllRegionsByUsername(UID);
	}

	@Test
	public void testCreateRegion() throws BusinessException {
		deleteAllRegionsByUsername(UID);
		final String name = "hxh";
		final String province = "湖南";
		final String city = "上海";
		final String county = "徐汇";
		final String town = "123";
		final String village = "qq";
		final String detail = "0.21";
		final String tel = "18321783963";
		final Double longitude = 123.1;
		final Double latitude = 456.222;

		regionBusiness.createRegion(UID, name, province, city, county, town, village, detail, tel, longitude,
				latitude);
		RegionCriteria regionCriteria = new RegionCriteria();
		regionCriteria.or().andUidEqualTo(UID);

		List<Region> regions = regionMapper.selectByExample(regionCriteria);
		assertEquals(1, regions.size());

		Region region = regions.get(0);

		assertEquals(name, region.getName());
		assertEquals(province, region.getProvince());
		assertEquals(city, region.getCity());
		assertEquals(county, region.getCounty());
		assertEquals(town, region.getTown());
		assertEquals(village, region.getVillage());
		assertEquals(detail, region.getDetail());
		assertEquals(tel, region.getTel());
		assertEquals(UID, region.getUid());

		RegionGeo regionGeo = regionGeoMapper.selectByPrimaryKey(region.getIdregion());
		assertEquals(latitude, regionGeo.getLatitude());
		assertEquals(longitude, regionGeo.getLongtitude());

	}

	@Test
	public void testEditRegion() throws BusinessException {
		
		deleteAllRegionsByUsername(UID);
	   final String name="hxh";
	   final String province="湖南";
	   final String city="上海";
	   final String county="徐汇";
	   final String town="123";
	   final String village="qq";
	   final String detail="0.21";
	   final String tel="18321783963";
	   final Double longitude=123.1;
	   final Double latitude=456.222;
	   
	   final String newname="hxh1";
	   final String newprovince="湖南1";
	   final String newcity="上海1";
	   final String newcounty="徐汇1";
	   final String newtown="1231";
	   final String newvillage="qq1";
	   final String newdetail="0.211";
	   final String newtel="183217839631";
	   final Double newlongitude=123.11;
	   final Double newlatitude=456.2221;
	  
	   regionBusiness.createRegion(UID, name, province, city, county, town, village, detail, tel, longitude, latitude);
	   RegionCriteria regionCriteria = new RegionCriteria();
	   regionCriteria.or().andUidEqualTo(UID);
	   
	   
	   List<Region> regions=regionMapper.selectByExample(regionCriteria);
	   assertEquals(1,regions.size());
	   Region region=regions.get(0);
	   
	   regionBusiness.editRegion(UID, region.getIdregion(), newname, newprovince, newcity, newcounty, newtown, newvillage, newdetail, newtel, newlongitude, newlatitude);
	   
	   RegionCriteria regionCriterianew = new RegionCriteria();
	   regionCriterianew.or().andUidEqualTo(UID);
	  
	   List<Region> regionsnew=regionMapper.selectByExample(regionCriterianew);
	   assertEquals(1,regionsnew.size());
	   
	   Region regionnew=regionsnew.get(0);
	   
	   assertEquals(newname, regionnew.getName());
	   assertEquals(newprovince, regionnew.getProvince());
	   assertEquals(newcity, regionnew.getCity());
	   assertEquals(newcounty, regionnew.getCounty());
	   assertEquals(newtown, regionnew.getTown());
	   assertEquals(newvillage, regionnew.getVillage());
	   assertEquals(newdetail, regionnew.getDetail());
	   assertEquals(newtel, regionnew.getTel());
	   assertEquals(UID, regionnew.getUid());
	   assertEquals(region.getIdregion(),regionnew.getIdregion());
	   
	   RegionGeo regionGeo=regionGeoMapper.selectByPrimaryKey(regionnew.getIdregion());
	   assertEquals(newlatitude, regionGeo.getLatitude());
	   assertEquals(newlongitude, regionGeo.getLongtitude());
				
		
	}

	@Test
	public void testDeleteRegion() throws BusinessException {
		deleteAllRegionsByUsername(UID);
		final String name = "hxh";
		final String province = "湖南";
		final String city = "上海";
		final String county = "徐汇";
		final String town = "123";
		final String village = "qq";
		final String detail = "0.21";
		final String tel = "18321783963";
		final Double longitude = 123.1;
		final Double latitude = 456.222;

		regionBusiness.createRegion(UID, name, province, city, county, town, village, detail, tel, longitude,
				latitude);
		RegionCriteria regionCriteria = new RegionCriteria();
		regionCriteria.or().andUidEqualTo(UID);

		List<Region> regions = regionMapper.selectByExample(regionCriteria);
		assertEquals(1, regions.size());
		Region region = regions.get(0);

		regionBusiness.deleteRegion(UID, region.getIdregion());

		assertEquals(0, regionMapper.countByExample(regionCriteria));
		assertEquals(null, regionGeoMapper.selectByPrimaryKey(region.getIdregion()));

	}

	private void deleteAllRegionsByUsername(Long uid) {
		RegionCriteria regionCriteria = new RegionCriteria();
		regionCriteria.or().andUidEqualTo(uid);
		List<Region> regions = regionMapper.selectByExample(regionCriteria);
		regionMapper.deleteByExample(regionCriteria);
		for (Region region : regions) {
			regionGeoMapper.deleteByPrimaryKey(region.getIdregion());
		}
	}

}
