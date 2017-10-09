package cn.bangnongmang.admin.business.manager.impl;

import cn.bangnongmang.admin.business.manager.GrabSeasonBusiness;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.data.dao.GrabSeasonMapper;
import cn.bangnongmang.service.service.impl.GrabSeasonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wenxx on 2017/6/2.
 */
@Service
public class GrabSeasonBusinessImpl implements GrabSeasonBusiness{
    @Autowired
    private GrabSeasonServiceImpl grabSeasonService;
    @Autowired
    private GrabSeasonMapper grabSeasonMapper;

    @Override
    public void createGrabSeason(Long startTime, Long endTime, String ps) {
        if(grabSeasonService.createSeason(startTime,endTime,ps)==null){
            throw new BusinessException("场次创建失败");
        }
    }
    @Override
    public void deleteGrabSeason(String seasonId) {
        if(grabSeasonMapper.selectByPrimaryKey(seasonId)==null){
            throw new BusinessException("场次ID无效");
        }
        if(!grabSeasonService.deleteSeason(seasonId)){
            throw new BusinessException("场次删除失败");
        }
    }

    @Override
    public void changeGrabSeasonBasicInfo(String seasonId, Long startTime, Long endTime, String ps) {
        if(grabSeasonMapper.selectByPrimaryKey(seasonId)==null){
            throw new BusinessException("场次ID无效");
        }
        if(!grabSeasonService.editSeason(seasonId,startTime,endTime,ps)){
            throw new BusinessException("场次信息修改失败");
        }
    }

    @Override
    public void changeGrabSeasonOrders(String seasonId, List<String> orders) {
        if(grabSeasonMapper.selectByPrimaryKey(seasonId)==null){
            throw new BusinessException("场次ID无效");
        }
        if (grabSeasonService.editSeasonOrders(seasonId,orders)) {
            return;
        }
        throw new BusinessException("场次对应订单更改失败");
    }

    @Override
    public void setGrabSeasonStateAsDraft(String seasonId) {
        if(grabSeasonMapper.selectByPrimaryKey(seasonId)==null){
            throw new BusinessException("场次ID无效");
        }
        if(!grabSeasonService.cancelSeason(seasonId)){
            throw new BusinessException("场次状态更改失败");
        }
    }

    @Override
    public void setGrabSeasonStateAsPublished(String seasonId) {
        if(grabSeasonMapper.selectByPrimaryKey(seasonId)==null){
            throw new BusinessException("场次ID无效");
        }
        if(!grabSeasonService.publishSeason(seasonId)){
            throw new BusinessException("场次状态更改失败");
        }
    }
}
