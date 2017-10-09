package cn.bangnongmang.server.io.swagger;

import cn.bangnongmang.data.dao.StarEvaluationsMapper;
import cn.bangnongmang.data.domain.StarEvaluations;
import cn.bangnongmang.server.swagger.model.StarInfo;
import cn.bangnongmang.service.service.StarService;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2017-05-26.
 */
@Service
public class StarConverter {

    public StarInfo convertToStarInfo(StarEvaluations starEvaluations){
        if (starEvaluations == null)
            return null;
        StarInfo starInfo = new StarInfo();
        starInfo.setId(starEvaluations.getId());
        starInfo.setStar(starEvaluations.getStar());
        starInfo.setTime(starEvaluations.getTime());
        starInfo.setValuted(starEvaluations.getValuted());
        if (StarService.STAR_LEADER.equals(starEvaluations.getType())){
            starInfo.setType(StarInfo.TypeEnum.LEADER);
        }else if (StarService.STAR_MEMBER.equals(starEvaluations.getType())){
            starInfo.setType(StarInfo.TypeEnum.MEMBER);
        }else {
            starInfo.setType(null);
        }
        return starInfo;
    }
}
