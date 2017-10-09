package cn.bangnongmang.service.service;

import cn.bangnongmang.data.domain.AreaPrice;

public interface AreaPriceService {

	Integer DEFAULT_PRICE = 9000;

	AreaPrice getTownAreaPrice(String province, String city, String county, String town);

	AreaPrice getCountyAreaPrice(String province, String city, String county);

	AreaPrice getCityAreaPrice(String province, String city);

	AreaPrice getProvinceAreaPrice(String province);

	AreaPrice getAreaPriceByPosition(String province, String city, String county, String town);

}
