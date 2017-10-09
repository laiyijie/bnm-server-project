package cn.bangnongmang.admin.business.manager;

import java.util.List;

/**
 * Created by wenxx on 2017/6/2.
 */
public interface GrabSeasonBusiness {
    void createGrabSeason(Long startTime, Long endTime, String ps);
    void deleteGrabSeason(String seasonId);
    void changeGrabSeasonBasicInfo(String seasonId, Long startTime, Long endTime, String ps);
    void changeGrabSeasonOrders(String seasonId, List<String> orders);
    void setGrabSeasonStateAsDraft (String seasonId);
    void setGrabSeasonStateAsPublished (String seasonId);
}
