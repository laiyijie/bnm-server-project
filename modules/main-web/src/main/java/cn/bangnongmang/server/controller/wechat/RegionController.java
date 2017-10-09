package cn.bangnongmang.server.controller.wechat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import cn.bangnongmang.data.domain.Region;
import cn.bangnongmang.server.business.common.RegionBusiness;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.server.io.wechat.WxOutputException;

import static cn.bangnongmang.server.io.interceptor.ServerSessionAttr.*;

import java.util.List;

@Controller("wxRegionController")
@RequestMapping("/wx/region")
public class RegionController {

	@Autowired
	private RegionBusiness commonRegionBusiness;

	@RequestMapping("/createRegion")
	@ResponseBody
	public Object createRegion(@SessionAttribute(SESSION_UID) Long uid, @RequestParam("name") String name,
							   @RequestParam("province") String province, @RequestParam("city") String city,
							   @RequestParam("county") String county, @RequestParam("town") String town,
							   @RequestParam(value = "village", required = false) String village, @RequestParam("detail") String detail,
							   @RequestParam("tel") String tel, @RequestParam("longitude") Double longitude,
							   @RequestParam("latitude") Double latitude) throws BusinessException {

		Region region = commonRegionBusiness.createRegion(uid, name, province, city, county, town, village, detail,
				tel, longitude, latitude);

		return region;

	}

	@RequestMapping("/editRegion")
	@ResponseBody
	public Object editRegion(@SessionAttribute(SESSION_UID) Long uid, @RequestParam("name") String name,
							 @RequestParam("province") String province, @RequestParam("city") String city,
							 @RequestParam("county") String county, @RequestParam("town") String town,
							 @RequestParam(value = "village", required = false) String village, @RequestParam("detail") String detail,
							 @RequestParam("tel") String tel, @RequestParam("idregion") String idregion,
							 @RequestParam("longitude") Double longitude, @RequestParam("latitude") Double latitude) throws BusinessException {

		Region region = commonRegionBusiness.editRegion(uid, idregion, name, province, city, county, town, village,
				detail, tel, longitude, latitude);
		return region;

	}

	@RequestMapping("/delRegion")
	@ResponseBody
	public Object delRegion(@SessionAttribute(SESSION_UID) Long uid,
							@RequestParam("idregion") String idRegion) throws WxOutputException, BusinessException {

		boolean flag = commonRegionBusiness.deleteRegion(uid, idRegion);

		if (flag) {
			return "done";
		}
		throw new WxOutputException("删除失败");
	}

	@RequestMapping("/getAllRegion")
	@ResponseBody
	public Object getAllRegion(@SessionAttribute(SESSION_UID) Long uid) throws WxOutputException {

		List<Region> regions = commonRegionBusiness.getAllRegion(uid);
		;
		return regions;
	}

}
