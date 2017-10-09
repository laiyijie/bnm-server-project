package cn.bangnongmang.data.combo.dao;

import java.util.List;

import cn.bangnongmang.data.combo.domain.GrabSeasonInfoShow;

public interface GrabSeasonInfoShowMapper {
	
	public List<GrabSeasonInfoShow> selectGrabSeasonInfoShowByMinEndTime(Long time);
}
