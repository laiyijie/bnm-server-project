package cn.bangnongmang.service.service;

import cn.bangnongmang.data.domain.AccountGeo;
import cn.bangnongmang.data.domain.OrderGeo;

public interface GeoService {

	Integer TYPE_USER_LOCATION = 100;


	boolean updateAccountGeoInfo(AccountGeo accountGeo);

	OrderGeo createOrderGeo(OrderGeo orderGeo);
}
