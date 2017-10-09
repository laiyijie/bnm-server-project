package cn.bangnongmang.service.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bangnongmang.data.dao.AccountGeoMapper;
import cn.bangnongmang.data.dao.OrderGeoMapper;
import cn.bangnongmang.data.domain.AccountGeo;
import cn.bangnongmang.data.domain.OrderGeo;
import cn.bangnongmang.service.service.GeoService;

@Service("S_GeoService")
public class GeoServiceImpl implements GeoService {

	private final transient Logger logger = LogManager.getLogger(GeoServiceImpl.class);

	@Autowired
	private OrderGeoMapper orderLatitudeMapper;
	@Autowired
	private AccountGeoMapper accountGeoMapper;


	@Override
	public boolean updateAccountGeoInfo(AccountGeo accountGeo) {

		if (accountGeo == null) {
			return false;
		}
		if (accountGeo.getUid() == null) {
			return false;
		}
		accountGeo.setUpdate_time(System.currentTimeMillis() / 1000);
		if (accountGeoMapper.updateByPrimaryKey(accountGeo) > 0) {
			return true;
		}
		accountGeoMapper.insert(accountGeo);
		return true;
	}


	@Override
	public OrderGeo createOrderGeo(OrderGeo orderGeo) {
		if (orderGeo == null) {
			return null;
		}
		orderLatitudeMapper.insert(orderGeo);
		return orderLatitudeMapper.selectByPrimaryKey(orderGeo.getOrder_id());
	}
}
