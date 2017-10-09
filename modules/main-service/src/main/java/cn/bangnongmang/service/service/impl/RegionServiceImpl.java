package cn.bangnongmang.service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.bangnongmang.data.dao.RegionGeoMapper;
import cn.bangnongmang.data.dao.RegionMapper;
import cn.bangnongmang.data.domain.Region;
import cn.bangnongmang.data.domain.RegionCriteria;
import cn.bangnongmang.data.domain.RegionGeo;
import cn.bangnongmang.service.service.RegionService;
import cn.bangnongmang.service.outerservice.util.ServiceUtil;

@Service("S_RegionService")
public class RegionServiceImpl implements RegionService {
	@Autowired
	private RegionMapper regionMapper;

	@Autowired
	private RegionGeoMapper regionGeoMapper;

	@Autowired
	private ServiceUtil serviceUtil;

	@Override
	public Region saveRegion(Long uid, String province, String city, String county, String
			town, String village,
			String detail, String name, String tel, int level) {
		// TODO Auto-generated method stub
		Region region = new Region();
		region.setUid(uid);
		region.setProvince(province);
		region.setCity(city);
		region.setCounty(county);
		region.setTown(town);
		region.setVillage(village);
		region.setDetail(detail);
		region.setName(name);
		region.setTel(tel);
		region.setIdregion(serviceUtil.generateId(uid, 4, 5));
		region.setLevel(level);

		regionMapper.insert(region);
		return region;
	}

	@Override
	public RegionGeo saveRegionGeo(String idRegion, Double longitude, Double latitude) {
		// TODO Auto-generated method stub
		RegionGeo regionGeo = new RegionGeo();
		regionGeo.setLatitude(latitude);
		regionGeo.setLongtitude(longitude);
		regionGeo.setRegion_id(idRegion);

		regionGeoMapper.insert(regionGeo);

		return regionGeo;
	}

	@Override
	public long getRegionNumberByUid(Long uid) {
		// TODO Auto-generated method stub
		RegionCriteria regionCriteria = new RegionCriteria();

		regionCriteria.or().andUidEqualTo(uid);

		long count = regionMapper.countByExample(regionCriteria);
		return count;
	}

	@Override
	public Long getRegionUidByIdRegion(String idregion) {
		// TODO Auto-generated method stub

		Region region = regionMapper.selectByPrimaryKey(idregion);
		return region.getUid();
	}

	@Override
	public Region editRegionByRegion(Long uid, String idregion, String province, String city,
									 String county,
			String town, String village, String detail, String name, String tel) {
		// TODO Auto-generated method stub

		Region region = new Region();
		region.setUid(uid);
		region.setIdregion(idregion);
		region.setProvince(province);
		region.setCity(city);
		region.setCounty(county);
		region.setTown(town);
		region.setVillage(village);
		region.setDetail(detail);

		region.setName(name);
		region.setTel(tel);

		regionMapper.updateByPrimaryKey(region);

		return region;
	}

	@Override
	public List<Region> getRegionsByUid(Long uid) {
		// TODO Auto-generated method stub
		RegionCriteria regionCriteria = new RegionCriteria();

		regionCriteria.or().andUidEqualTo(uid);

		List<Region> regions = regionMapper.selectByExample(regionCriteria);
		return regions;
	}

	@Override
	public boolean deleteRegionByIdRegion(String idregion) {
		// TODO Auto-generated method stub
		int count = regionMapper.deleteByPrimaryKey(idregion);
		return count > 0 ? true : false;
	}

	@Override
	public RegionGeo editRegionGeo(String idRegion, Double longitude, Double latitude) {
		// TODO Auto-generated method stub
		RegionGeo regionGeo = new RegionGeo();
		regionGeo.setLatitude(latitude);
		regionGeo.setLongtitude(longitude);
		regionGeo.setRegion_id(idRegion);

		RegionGeo oldRegion = regionGeoMapper.selectByPrimaryKey(idRegion);

		if (oldRegion == null) {
			regionGeoMapper.insert(regionGeo);
		} else {
			regionGeoMapper.updateByPrimaryKey(regionGeo);
		}

		return regionGeo;
	}

	@Override
	public boolean deleteRegionGeo(String idRegion) {

		int count = regionGeoMapper.deleteByPrimaryKey(idRegion);
		return count > 0 ? true : false;
	}

	@Override
	public Region getRegionById(String id) {

		return regionMapper.selectByPrimaryKey(id);

	}

	@Override
	public RegionGeo getRegionGeo(String regionId) {
		return regionGeoMapper.selectByPrimaryKey(regionId);
	}
}
