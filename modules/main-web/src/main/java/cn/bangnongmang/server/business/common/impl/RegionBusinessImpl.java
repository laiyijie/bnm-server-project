package cn.bangnongmang.server.business.common.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bangnongmang.data.domain.Region;
import cn.bangnongmang.server.business.common.RegionBusiness;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.service.service.RegionService;

@Service
@Transactional(rollbackFor = { Exception.class })
public class RegionBusinessImpl implements RegionBusiness {

	@Autowired
	private RegionService regionService;

	private void checkEmpty(String s, String code) throws BusinessException {
		if (s == null || "".equals(s))
			throw new BusinessException("post_" + code + "_not_set");
	}

	@Override
	public Region createRegion(Long uid, String name, String province, String city, String
			county, String town,
			String village, String detail, String tel, Double longitude, Double latitude) throws BusinessException {
		// TODO Auto-generated method stub
		checkEmpty(province, "province");
		checkEmpty(city, "city");
		checkEmpty(county, "county");
		checkEmpty(town, "town");
		if (village == null || "".equals(village))
			village = "";
		checkEmpty(detail, "detail");

		checkEmpty(name, "name");
		checkEmpty(tel, "tel");

		long count = regionService.getRegionNumberByUid(uid);

		if (count >= RegionService.MAX_NUM)
			throw new BusinessException("最多只支持创建" + RegionService.MAX_NUM + "个地址");

		int level = RegionService.LEVEL_COMMON;
		if (count == 0)
			level = RegionService.LEVEL_DEFAULT;
		Region region = regionService.saveRegion(uid, province, city, county, town, village, detail, name, tel,
				level);
		regionService.saveRegionGeo(region.getIdregion(), longitude, latitude);
		return region;
	}

	@Override
	public Region editRegion(Long uid, String idregion, String name, String province, String
			city, String county,
			String town, String village, String detail, String tel, Double longitude, Double latitude)
			throws BusinessException {
		// TODO Auto-generated method stub
		checkEmpty(name, "name");
		checkEmpty(tel, "tel");
		checkEmpty(province, "province");
		checkEmpty(city, "city");
		checkEmpty(county, "county");
		checkEmpty(town, "town");
		if (village == null || "".equals(village))
			village = "";
		checkEmpty(detail, "detail");
		checkEmpty(idregion, "idregion");

		Long regionUid = regionService.getRegionUidByIdRegion(idregion);
		System.out.println(regionUid + " " + uid);
		System.out.println(regionUid.equals(uid));
		if (!regionUid.equals(uid)) {
			throw new BusinessException("not_ur_address");
		}

		Region region = regionService.editRegionByRegion(uid, idregion, province, city, county, town, village,
				detail, name, tel);
		regionService.editRegionGeo(idregion, longitude, latitude);
		return region;
	}

	@Override
	public boolean deleteRegion(Long uid, String idregion) throws BusinessException {
		// TODO Auto-generated method stub
		checkEmpty(idregion, "idregion");
		Long regionUid = regionService.getRegionUidByIdRegion(idregion);
		if (!regionUid.equals(uid))
			throw new BusinessException("not_ur_address");
		boolean flag = regionService.deleteRegionByIdRegion(idregion);
		regionService.deleteRegionGeo(idregion);
		return flag;
	}

	@Override
	public List<Region> getAllRegion(Long uid) {
		// TODO Auto-generated method stub
		List<Region> regions = regionService.getRegionsByUid(uid);
		return regions;
	}

}
