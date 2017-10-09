package cn.bangnongmang.service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bangnongmang.data.dao.GrabSeasonMapper;
import cn.bangnongmang.data.dao.SeasonOrdersMapper;
import cn.bangnongmang.data.domain.GrabSeason;
import cn.bangnongmang.data.domain.GrabSeasonCriteria;
import cn.bangnongmang.data.domain.SeasonOrders;
import cn.bangnongmang.data.domain.SeasonOrdersCriteria;
import cn.bangnongmang.service.service.GrabSeasonService;

@Service("S_GrabSeasonService")
public class GrabSeasonServiceImpl implements GrabSeasonService {

	@Autowired
	private GrabSeasonMapper grabSeasonMapper;
	@Autowired
	private SeasonOrdersMapper seasonOrdersMapper;



	@Override
	public List<GrabSeason> getAllSeasons() {

		GrabSeasonCriteria grabSeasonCriteria = new GrabSeasonCriteria();

		grabSeasonCriteria.or();

		return grabSeasonMapper.selectByExample(grabSeasonCriteria);

	}

	@Override
	public GrabSeason createSeason(long startTime, long endTime, String ps) {

		GrabSeason grabSeason = new GrabSeason();

		grabSeason.setEnd_time(endTime);
		grabSeason.setStart_time(startTime);
		grabSeason.setId(String.valueOf(System.currentTimeMillis()));
		grabSeason.setPs(ps);
		grabSeason.setState(STATE_DRAFT);
		grabSeason.setToatalsize(0.0);

		int flag = grabSeasonMapper.insert(grabSeason);

		if (flag > 0) {

			return grabSeason;
		}

		return null;
	}

	@Override
	public boolean deleteSeason(String id) {

		GrabSeason grabSeason = grabSeasonMapper.selectByPrimaryKey(id);

		SeasonOrdersCriteria seasonOrdersCriteria = new SeasonOrdersCriteria();

		seasonOrdersCriteria.or().andSeason_idEqualTo(id);

		seasonOrdersMapper.deleteByExample(seasonOrdersCriteria);

		int flag = grabSeasonMapper.deleteByPrimaryKey(id);

		if (flag > 0) {
			return true;
		}

		return false;
	}

	@Override
	public boolean publishSeason(String id) {

		GrabSeason grabSeason = grabSeasonMapper.selectByPrimaryKey(id);

		grabSeason.setState(STATE_PUBLISHED);

		int flag = grabSeasonMapper.updateByPrimaryKey(grabSeason);

		if (flag > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean cancelSeason(String id) {

		GrabSeason grabSeason = grabSeasonMapper.selectByPrimaryKey(id);

		grabSeason.setState(STATE_DRAFT);

		int flag = grabSeasonMapper.updateByPrimaryKey(grabSeason);

		if (flag > 0) {
			return true;
		}

		return false;
	}

	@Override
	public boolean editSeason(String id, long sTime, long eTime, String ps) {

		GrabSeason grabSeason = grabSeasonMapper.selectByPrimaryKey(id);

		grabSeason.setEnd_time(eTime);
		grabSeason.setStart_time(sTime);
		grabSeason.setPs(ps);

		int flag = grabSeasonMapper.updateByPrimaryKey(grabSeason);

		if (flag > 0) {
			return true;
		}

		return false;
	}

	@Override
	public GrabSeason getSeasonById(String id) {

		return grabSeasonMapper.selectByPrimaryKey(id);

	}

	@Override
	public boolean editSeasonOrders(String seasonId, List<String> fatherOrderIds) {

		GrabSeason grabSeason = grabSeasonMapper.selectByPrimaryKey(seasonId);

		if (grabSeason == null) {

			return false;
		}

		SeasonOrdersCriteria seasonOrdersCriteria = new SeasonOrdersCriteria();
		seasonOrdersCriteria.or().andSeason_idEqualTo(seasonId);

		seasonOrdersMapper.deleteByExample(seasonOrdersCriteria);

		grabSeason.setToatalsize(0.0);

		grabSeasonMapper.updateByPrimaryKey(grabSeason);

		return addOrdersToSeason(seasonId, fatherOrderIds);

	}

	@Override
	public boolean addOrdersToSeason(String seasonId, List<String> fatherOrderIds) {
		for (String fatherOrderId : fatherOrderIds) {
			// grabSeasonMapper
			SeasonOrders seasonOrders = new SeasonOrders();
			seasonOrders.setOrder_id(fatherOrderId);
			seasonOrders.setSeason_id(seasonId);
			seasonOrdersMapper.insert(seasonOrders);
		}
		return true;
	}

	@Override
	public boolean deleteOrderFromSeason(String seasonId, List<String> fatherOrderIds) {
		if (fatherOrderIds.isEmpty()) {
			return true;
		}
		SeasonOrdersCriteria seasonOrdersCriteria = new SeasonOrdersCriteria();
		fatherOrderIds.stream().forEach(a -> seasonOrdersCriteria.or().andOrder_idEqualTo(a));
		seasonOrdersMapper.deleteByExample(seasonOrdersCriteria);
		return true;
	}

}
