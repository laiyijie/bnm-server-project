
package cn.bangnongmang.service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bangnongmang.data.dao.AreaPriceMapper;
import cn.bangnongmang.data.domain.AreaPrice;
import cn.bangnongmang.data.domain.AreaPriceCriteria;
import cn.bangnongmang.service.service.AreaPriceService;

@Service("S_AreaPriceService")
public class AreaPriceServiceImpl implements AreaPriceService {

	@Autowired
	private AreaPriceMapper areaPriceMapper;

	@Override
	public AreaPrice getAreaPriceByPosition(String province, String city, String county, String town) {

		AreaPrice price = getTownAreaPrice(province, city, county, town);

		if (price != null) {
			return price;
		}

		price = getCountyAreaPrice(province, city, county);

		if (price != null) {
			return price;
		}

		price = getCityAreaPrice(province, city);

		if (price != null) {
			return price;
		}

		price = getProvinceAreaPrice(province);

		if (price != null) {
			return price;
		}
		
		AreaPrice areaPrice = new AreaPrice();
		areaPrice.setUniprice(DEFAULT_PRICE);

		return areaPrice;
	}
	@Override
	public AreaPrice getProvinceAreaPrice(String province) {
		AreaPriceCriteria areaPriceCriteria = new AreaPriceCriteria();
		areaPriceCriteria.or().andProvinceEqualTo(province).andCityIsNull().andCountyIsNull()
				.andTownIsNull();
		List<AreaPrice> areaPrices = areaPriceMapper.selectByExample(areaPriceCriteria);
		if (areaPrices.isEmpty()) {
			return null;
		} else {
			return areaPrices.get(0);
		}
	}
	@Override
	public AreaPrice getCityAreaPrice(String province, String city) {
		AreaPriceCriteria areaPriceCriteria = new AreaPriceCriteria();
		areaPriceCriteria.or().andProvinceEqualTo(province).andCityEqualTo(city).andCountyIsNull()
				.andTownIsNull();
		List<AreaPrice> areaPrices = areaPriceMapper.selectByExample(areaPriceCriteria);
		if (areaPrices.isEmpty()) {
			return null;
		} else {
			return areaPrices.get(0);
		}
	}
	@Override
	public AreaPrice getCountyAreaPrice(String province, String city, String county) {
		AreaPriceCriteria areaPriceCriteria = new AreaPriceCriteria();
		areaPriceCriteria.or().andProvinceEqualTo(province).andCityEqualTo(city).andCountyEqualTo(county)
				.andTownIsNull();
		List<AreaPrice> areaPrices = areaPriceMapper.selectByExample(areaPriceCriteria);
		if (areaPrices.isEmpty()) {
			return null;
		} else {
			return areaPrices.get(0);
		}
	}
	@Override
	public AreaPrice getTownAreaPrice(String province, String city, String county, String town) {
		AreaPriceCriteria areaPriceCriteria = new AreaPriceCriteria();
		areaPriceCriteria.or().andProvinceEqualTo(province).andCityEqualTo(city).andCountyEqualTo(county)
				.andTownEqualTo(town);
		List<AreaPrice> areaPrices = areaPriceMapper.selectByExample(areaPriceCriteria);
		if (areaPrices.isEmpty()) {
			return null;
		} else {
			return areaPrices.get(0);
		}
	}
}
