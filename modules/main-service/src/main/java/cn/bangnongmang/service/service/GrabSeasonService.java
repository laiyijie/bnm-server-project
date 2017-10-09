package cn.bangnongmang.service.service;

import java.util.List;

import cn.bangnongmang.data.domain.GrabSeason;

public interface GrabSeasonService {

	Integer STATE_DRAFT = 100;
	Integer STATE_PUBLISHED = 200;

	List<GrabSeason> getAllSeasons();

	GrabSeason createSeason(long startTime, long endTime, String ps);

	boolean deleteSeason(String id);

	boolean publishSeason(String id);

	boolean cancelSeason(String id);

	boolean addOrdersToSeason(String seasonId, List<String> fatherOrderIds);

	boolean deleteOrderFromSeason(String seasonId, List<String> fatherOrderIds);

	boolean editSeason(String id, long sTime, long eTime, String ps);

	GrabSeason getSeasonById(String id);

	boolean editSeasonOrders(String seasonId, List<String> fatherOrderIds);

}
