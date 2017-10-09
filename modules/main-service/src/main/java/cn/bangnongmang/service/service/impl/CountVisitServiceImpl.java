package cn.bangnongmang.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bangnongmang.data.dao.VisitLogItemMapper;
import cn.bangnongmang.data.domain.VisitLogItem;
import cn.bangnongmang.service.service.CountVisitService;

@Service("S_CountVisitService")
public class CountVisitServiceImpl implements CountVisitService {
	
	@Autowired
	private VisitLogItemMapper visitLogItemMapper;
	
	@Override
	public void count(String name) {
		VisitLogItem item = new VisitLogItem();
		item.setButton_name(name);
		item.setCreate_time(System.currentTimeMillis()/1000);
		visitLogItemMapper.insert(item);
	}

}
