package cn.bangnongmang.service.outerservice;

import cn.bangnongmang.service.service.exception.ServiceLayerExeption;

public interface OuterBankCardService {
	boolean auth(String pan,String certNo,String name,String tel ) throws ServiceLayerExeption;
}
