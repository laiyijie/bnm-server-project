package cn.bangnongmang.server.business.common;

import cn.bangnongmang.server.io.BusinessException;

public interface StarBusiness {
	void star(Long valutor, Long valuted, double star, String orderId, String type) throws
			BusinessException;


}
