package cn.bangnongmang.admin.io.swagger.show;

import cn.bangnongmang.admin.io.swagger.converter.GrabSeasonsConverter;
import cn.bangnongmang.admin.swagger.model.GrabSeason;
import cn.bangnongmang.admin.swagger.model.OrderIdWrapper;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.data.dao.GrabSeasonMapper;
import cn.bangnongmang.data.dao.SeasonOrdersMapper;
import cn.bangnongmang.data.domain.SeasonOrdersCriteria;
import cn.bangnongmang.service.service.impl.GrabSeasonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wenxx on 2017/5/27.
 */
@Service
public class GrabSeasonsShow {
    @Autowired
    private GrabSeasonsConverter grabSeasonsConverter;
    @Autowired
    private GrabSeasonServiceImpl grabSeasonService;
    @Autowired
    private SeasonOrdersMapper seasonOrdersMapper;

    public List<GrabSeason> getGrabSeasons() {
        return grabSeasonService.getAllSeasons()
                                .stream()
                                .map(grabSeason -> grabSeasonsConverter.convertToGrabSeason(grabSeason))
                                .collect(Collectors.toList());
    }

    public GrabSeason getGrabSeason(String seasonId) {
        GrabSeason grabSeason = grabSeasonsConverter.convertToGrabSeason(grabSeasonService.getSeasonById(seasonId));
        if (grabSeason == null) {
            throw new BusinessException("场次ID无效");
        }
        return grabSeason;
    }

    public List<OrderIdWrapper> getGrabSeasonOrders(String seasonId) {
        if (grabSeasonService.getSeasonById(seasonId) == null) {
            throw new BusinessException("场次ID无效");
        }
        SeasonOrdersCriteria seasonOrdersCriteria = new SeasonOrdersCriteria();
        seasonOrdersCriteria.or()
                            .andSeason_idEqualTo(seasonId);
        return seasonOrdersMapper.selectByExample(seasonOrdersCriteria)
                                 .stream()
                                 .map(seasonOrders -> {
                                     OrderIdWrapper oiw = new OrderIdWrapper();
                                     oiw.setOrderId(seasonOrders.getOrder_id());
                                     return oiw;
                                 })
                                 .collect(Collectors.toList());
    }

}
