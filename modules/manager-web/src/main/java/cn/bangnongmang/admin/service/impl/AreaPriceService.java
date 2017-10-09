package cn.bangnongmang.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.bangnongmang.admin.data.dao.AccountAreaMapper;
import cn.bangnongmang.admin.data.dao.AdminAccountMapper;
import cn.bangnongmang.admin.data.dao.AreaDictMapper;
import cn.bangnongmang.admin.data.domain.AccountAreaCriteria;
import cn.bangnongmang.admin.data.domain.AccountAreaKey;
import cn.bangnongmang.admin.data.domain.AdminAccount;
import cn.bangnongmang.admin.data.domain.AreaDict;
import cn.bangnongmang.admin.util.AccountUtils;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.admin.util.PageResult;
import cn.bangnongmang.data.dao.AreaPriceMapper;
import cn.bangnongmang.data.domain.AreaPrice;
import cn.bangnongmang.data.domain.AreaPriceCriteria;

@Transactional
@Service
public class AreaPriceService {

	@Autowired
	private AdminAccountMapper adminAccMapper;
	@Autowired
	private AreaDictMapper areaDictMapper;
	@Autowired
	private AccountAreaMapper accountAreaMapper;

	@Autowired
	private AreaPriceMapper areaPriceMapper;

	private boolean checkForArea(AdminAccount account, String province, String city, String county, String town)
			throws BusinessException {
		if (AccountUtils.isLeader(account) || AccountUtils.isMarket(account)) {

			AccountAreaCriteria areaFilter = new AccountAreaCriteria();
			areaFilter.or().andUsernameEqualTo(account.getUsername());
			List<AccountAreaKey> areaIds = accountAreaMapper.selectByExample(areaFilter);

			List<AreaDict> areas = new ArrayList<>();
			if (areaIds != null) {
				for (AccountAreaKey accountAreaKey : areaIds) {
					AreaDict areaDict = areaDictMapper.selectByPrimaryKey(accountAreaKey.getArea_id());
					if (areaDict != null) {
						areas.add(areaDict);
					}
				}
			} else {
				throw new BusinessException("您还不属于任何区域，因此没有权限查看任何区域价格");
			}

			boolean found = false;
			for (AreaDict areaDict : areas) {
				if (AccountUtils.areaContains(areaDict, province, city, county, town)) {
					found = true;
					break;
				}
			}
			if (!found) {
				throw new BusinessException("不能修改服务范围之外的价格");
			}

		}

		return true;
	}

	public boolean deleteAreaPrice(String opUsername, Integer id) throws BusinessException {
		AdminAccount account = adminAccMapper.selectByPrimaryKey(opUsername);

		if (account == null) {
			return false;
		}

		if (AccountUtils.isSuperAdmin(account) || AccountUtils.isMarket(account) || AccountUtils.isLeader(account)) {

			AreaPrice areaPrice = areaPriceMapper.selectByPrimaryKey(id);
			if (areaPrice == null) {
				throw new BusinessException("未找到该区域价格");
			}

			if (!checkForArea(account, areaPrice.getProvince(), areaPrice.getCity(), areaPrice.getCounty(),
					areaPrice.getTown())) {
				return false;
			}

			areaPriceMapper.deleteByPrimaryKey(id);

			return true;
		} else {
			return false;
		}
	}

	public boolean updateAreaPrice(String opUsername, Integer id, Integer uniPrice) throws BusinessException {
		AdminAccount account = adminAccMapper.selectByPrimaryKey(opUsername);

		if (account == null) {
			return false;
		}

		if (AccountUtils.isSuperAdmin(account) || AccountUtils.isMarket(account) || AccountUtils.isLeader(account)) {

			AreaPrice areaPrice = areaPriceMapper.selectByPrimaryKey(id);
			if (areaPrice == null) {
				throw new BusinessException("未找到该区域价格");
			}

			if (!checkForArea(account, areaPrice.getProvince(), areaPrice.getCity(), areaPrice.getCounty(),
					areaPrice.getTown())) {
				return false;
			}

			areaPrice.setUniprice(uniPrice);
			areaPriceMapper.updateByPrimaryKey(areaPrice);

			return true;
		} else {
			return false;
		}
	}

	public boolean createAreaPrice(String opUsername, String province, String city, String county, String town,
			Integer uniPrice) throws BusinessException {
		AdminAccount account = adminAccMapper.selectByPrimaryKey(opUsername);

		if (account == null) {
			return false;
		}

		if (!AccountUtils.notEmpty(province)) {
			throw new BusinessException("省级范围不能为空");
		}

		if (AccountUtils.isSuperAdmin(account) || AccountUtils.isMarket(account) || AccountUtils.isLeader(account)) {

			if (!checkForArea(account, province, city, county, town)) {
				return false;
			}

			AreaPriceCriteria areaPriceCriteria = new AreaPriceCriteria();
			cn.bangnongmang.data.domain.AreaPriceCriteria.Criteria criteria = areaPriceCriteria.or()
					.andProvinceEqualTo(province);

			if (AccountUtils.notEmpty(city)) {
				criteria.andCityEqualTo(city);
				if (AccountUtils.notEmpty(county)) {
					criteria.andCountyEqualTo(county);
					if (AccountUtils.notEmpty(town)) {
						criteria.andTownEqualTo(town);
					} else {
						criteria.andTownIsNull();
					}
				} else {
					criteria.andCountyIsNull();
				}
			} else {
				criteria.andCityIsNull();
			}

			List<AreaPrice> prices = areaPriceMapper.selectByExample(areaPriceCriteria);

			if (prices.isEmpty()) {
				AreaPrice areaPrice = new AreaPrice();
				areaPrice.setProvince(province);
				areaPrice.setCity(city);
				areaPrice.setCounty(county);
				areaPrice.setTown(town);
				areaPrice.setUniprice(uniPrice);
				areaPriceMapper.insert(areaPrice);
			} else {
				AreaPrice areaPrice = prices.get(0);
				areaPrice.setUniprice(uniPrice);
				areaPriceMapper.updateByPrimaryKey(areaPrice);
			}

			return true;
		} else {
			return false;
		}

	}

	private void addFilter(cn.bangnongmang.data.domain.AreaPriceCriteria.Criteria filter, String provinceFilter,
			String cityFilter, String countyFilter, String townFilter) {
		if (AccountUtils.notEmpty(provinceFilter)) {
			filter.andProvinceEqualTo(provinceFilter);
			if (AccountUtils.notEmpty(cityFilter)) {
				filter.andCityEqualTo(cityFilter);
				if (AccountUtils.notEmpty(countyFilter)) {
					filter.andCountyEqualTo(countyFilter);
					if (AccountUtils.notEmpty(townFilter)) {
						filter.andTownEqualTo(townFilter);
					}
				}
			}
		}
	}

	public PageResult<AreaPrice> getAreaPriceList(String opUsername, Integer currentPage, Integer pageSize,
			String provinceFilter, String cityFilter, String countyFilter, String townFilter) throws BusinessException {
		AdminAccount account = adminAccMapper.selectByPrimaryKey(opUsername);

		if (account == null) {
			return null;
		}

		if (AccountUtils.isLeader(account) || AccountUtils.isMarket(account)) {
			AccountAreaCriteria areaFilter = new AccountAreaCriteria();
			areaFilter.or().andUsernameEqualTo(opUsername);
			List<AccountAreaKey> areaIds = accountAreaMapper.selectByExample(areaFilter);

			List<AreaDict> areas = new ArrayList<>();
			if (areaIds != null) {
				for (AccountAreaKey accountAreaKey : areaIds) {
					AreaDict areaDict = areaDictMapper.selectByPrimaryKey(accountAreaKey.getArea_id());
					if (areaDict != null) {
						areas.add(areaDict);
					}
				}
			} else {
				throw new BusinessException("您还不属于任何区域，因此没有权限查看任何区域价格");
			}

			AreaPriceCriteria criteria = new AreaPriceCriteria();
			for (AreaDict areaDict : areas) {
				cn.bangnongmang.data.domain.AreaPriceCriteria.Criteria filter = criteria.or()
						.andProvinceEqualTo(areaDict.getProvince());
				addFilter(filter, provinceFilter, cityFilter, countyFilter, townFilter);
				if (AccountUtils.notEmpty(areaDict.getCity())) {
					filter.andCityEqualTo(areaDict.getCity());
					if (AccountUtils.notEmpty(areaDict.getCounty())) {
						filter.andCountyEqualTo(areaDict.getCounty());
						if (AccountUtils.notEmpty(areaDict.getTown())) {
							filter.andTownEqualTo(areaDict.getTown());
						}
					}
				}
			}
			criteria.setOrderByClause("province, city, county, town");
			
			PageHelper.startPage(currentPage, pageSize);
			Page<AreaPrice> areaPrices = (Page<AreaPrice>) areaPriceMapper.selectByExample(criteria);

			if (areaPrices == null) {
				return new PageResult<AreaPrice>();
			}
			return new PageResult<AreaPrice>(areaPrices);
		} else {
			AreaPriceCriteria criteria = new AreaPriceCriteria();
			cn.bangnongmang.data.domain.AreaPriceCriteria.Criteria filter = criteria.or();
			addFilter(filter, provinceFilter, cityFilter, countyFilter, townFilter);
			criteria.setOrderByClause("province, city, county, town");
			
			PageHelper.startPage(currentPage, pageSize);
			Page<AreaPrice> areaPrices = (Page<AreaPrice>) areaPriceMapper.selectByExample(criteria);

			if (areaPrices == null) {
				return new PageResult<AreaPrice>();
			}
			return new PageResult<AreaPrice>(areaPrices);
		}
	}
}
