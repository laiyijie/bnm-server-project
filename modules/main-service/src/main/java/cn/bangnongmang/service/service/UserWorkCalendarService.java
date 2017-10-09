package cn.bangnongmang.service.service;

/**
 * Created by admin on 2017-04-08.
 */
public interface UserWorkCalendarService {
    Long DEFAULT_WORKING_TIME = 3600*24*7L;

    void insertCalendar(Long uid, String relateOrderId, Long startTime, Long endTime);

    Boolean isTimeAvailable(Long uid, Long startTime, Long endTime);

    void deleteCalendarByOrderId(String orderId);

    void deleteCalendarByOrderIdAndUid(String orderId,Long uid);
}
