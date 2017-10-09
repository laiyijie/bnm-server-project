package cn.bangnongmang.data.combo.domain;

import java.util.List;

import cn.bangnongmang.data.domain.GrabSeason;
import cn.bangnongmang.data.domain.SeasonOrders;

public class GrabSeasonInfoShow {
	private String id;
	private GrabSeason grabSeason;
	private List<SeasonOrders> seasonOrders;

	public GrabSeason getGrabSeason() {
		return grabSeason;
	}

	public void setGrabSeason(GrabSeason grabSeason) {
		this.grabSeason = grabSeason;
	}

	public List<SeasonOrders> getSeasonOrders() {
		return seasonOrders;
	}

	public void setSeasonOrders(List<SeasonOrders> seasonOrders) {
		this.seasonOrders = seasonOrders;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
