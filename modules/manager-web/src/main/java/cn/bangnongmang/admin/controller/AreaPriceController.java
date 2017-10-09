package cn.bangnongmang.admin.controller;

import static cn.bangnongmang.admin.enums.ServerSessionAttr.USER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import cn.bangnongmang.admin.service.impl.AreaPriceService;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.admin.util.PageResult;
import cn.bangnongmang.data.domain.AreaPrice;

/**
 * 用于修改和查看区域价格的接口
 * 
 * @author Yunfei
 *
 */
@RestController
@RequestMapping(value = "areaPrice")
public class AreaPriceController {

	@Autowired
	AreaPriceService areaPriceService;

	/**
	 * 添加一个新的区域价格
	 * 
	 * @param province
	 *            省
	 * @param city
	 *            市
	 * @param county
	 *            县
	 * @param town
	 *            镇
	 * @param uniPrice
	 *            具体价格，单位为分
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "createAreaPrice")
	public String createAreaPrice(@RequestParam(value = "province") String province,
			@RequestParam(value = "city") String city, @RequestParam(value = "county") String county,
			@RequestParam(value = "town") String town, @RequestParam(value = "uniPrice") Integer uniPrice,
			@SessionAttribute(USER) String opUsername) throws BusinessException {

		boolean result = areaPriceService.createAreaPrice(opUsername, province, city, county, town, uniPrice);

		if (!result) {
			throw new BusinessException("对不起，没有权限");
		}

		return "success";
	}

	/**
	 * 删除一个已有的区域价格
	 * 
	 * @param areaId
	 *            区域价格的ID号
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "deleteAreaPrice")
	public String deleteAreaPrice(@RequestParam(value = "areaId") Integer areaId,
			@SessionAttribute(USER) String opUsername) throws BusinessException {

		boolean result = areaPriceService.deleteAreaPrice(opUsername, areaId);

		if (!result) {
			throw new BusinessException("对不起，没有权限");
		}

		return "success";
	}

	/**
	 * 更新一个区域的价格
	 * 
	 * @param areaId
	 *            区域价格的ID号
	 * @param uniPrice
	 *            区域价格，单位为分
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "updateAreaPrice")
	public String updateAreaPrice(@RequestParam(value = "areaId") Integer areaId,
			@RequestParam(value = "uniPrice") Integer uniPrice, @SessionAttribute(USER) String opUsername)
			throws BusinessException {

		boolean result = areaPriceService.updateAreaPrice(opUsername, areaId, uniPrice);

		if (!result) {
			throw new BusinessException("对不起，没有权限");
		}

		return "success";
	}

	/**
	 * 获取区域价格列表，带分页和筛选
	 * 
	 * @param currentPage 当前页面
	 * @param pageSize 页面大小，默认20
	 * @param provinceFilter 省筛选，默认null
	 * @param cityFilter 市筛选，默认null
	 * @param countyFilter 县筛选，默认null
	 * @param townFilter 镇筛选，默认null
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "getAreaPriceList")
	public PageResult<AreaPrice> getAreaPriceList(
			@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
			@RequestParam(value = "province", required = false) String provinceFilter,
			@RequestParam(value = "city", required = false) String cityFilter,
			@RequestParam(value = "county", required = false) String countyFilter,
			@RequestParam(value = "town", required = false) String townFilter,
			@SessionAttribute(USER) String opUsername) throws BusinessException {

		PageResult<AreaPrice> result = areaPriceService.getAreaPriceList(opUsername, currentPage, pageSize, provinceFilter,
				cityFilter, countyFilter, townFilter);
		
		if(result == null){
			throw new BusinessException("对不起，没有权限");
		}

		return result;
	}

}
