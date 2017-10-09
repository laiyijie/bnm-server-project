package cn.bangnongmang.admin.io.swagger.converter;

import cn.bangnongmang.admin.swagger.model.GrabSeason;
import cn.bangnongmang.service.service.GrabSeasonService;
import org.springframework.stereotype.Service;

/**
 * Created by wenxx on 2017/6/2.
 */
@Service
public class GrabSeasonsConverter {
    public GrabSeason convertToGrabSeason(cn.bangnongmang.data.domain.GrabSeason grabSeason){
        if(grabSeason==null){
            return null;
        }
        GrabSeason newGrabSeason=new GrabSeason();
        newGrabSeason.setStartTime(grabSeason.getStart_time());
        newGrabSeason.setEndTime(grabSeason.getEnd_time());
        newGrabSeason.setPs(grabSeason.getPs());
        newGrabSeason.setSeasonId(grabSeason.getId());
        Integer seasonState=grabSeason.getState();
        if(seasonState!=null){
            if(seasonState.equals(GrabSeasonService.STATE_DRAFT)){
                newGrabSeason.setState(GrabSeason.StateEnum.DRAFT);
            }else if(seasonState.equals(GrabSeasonService.STATE_PUBLISHED)){
                newGrabSeason.setState(GrabSeason.StateEnum.PUBLISHED);
            }
        }
        return newGrabSeason;
    }

}
