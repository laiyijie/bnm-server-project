package cn.bangnongmang.service.service.impl;

import cn.bangnongmang.data.dao.UserWorkCalendarMapper;
import cn.bangnongmang.data.domain.UserWorkCalendar;
import cn.bangnongmang.data.domain.UserWorkCalendarCriteria;
import cn.bangnongmang.service.service.UserWorkCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2017-04-08.
 */
@Service("S_UserWorkCalendarService")
public class UserWorkCalendarServiceImpl implements UserWorkCalendarService {
    @Autowired
    private UserWorkCalendarMapper userWorkCalendarMapper;

    @Override
    public void insertCalendar(Long uid, String relateOrderId, Long startTime, Long endTime) {
        UserWorkCalendar userWorkCalendar = new UserWorkCalendar();
        userWorkCalendar.setOrder_id(relateOrderId);
        userWorkCalendar.setTime_end(endTime);
        userWorkCalendar.setTime_start(startTime);
        userWorkCalendar.setUid(uid);
        userWorkCalendarMapper.insert(userWorkCalendar);
    }

    @Override
    public Boolean isTimeAvailable(Long uid, Long startTime, Long endTime) {

        UserWorkCalendarCriteria userWorkCalendarCriteria = new UserWorkCalendarCriteria();
        userWorkCalendarCriteria.or().andTime_startBetween(startTime, endTime).andUidEqualTo(uid);
        userWorkCalendarCriteria.or().andTime_endBetween(startTime, endTime).andUidEqualTo(uid);
        if (userWorkCalendarMapper.countByExample(userWorkCalendarCriteria) > 0)
            return false;
        return true;
    }

    @Override
    public void deleteCalendarByOrderId(String orderId) {

        UserWorkCalendarCriteria userWorkCalendarCriteria = new UserWorkCalendarCriteria();
        userWorkCalendarCriteria.or().andOrder_idEqualTo(orderId);
        userWorkCalendarMapper.deleteByExample(userWorkCalendarCriteria);
    }

    @Override
    public void deleteCalendarByOrderIdAndUid(String orderId, Long uid) {
        UserWorkCalendarCriteria userWorkCalendarCriteria = new UserWorkCalendarCriteria();
        userWorkCalendarCriteria.or().andUidEqualTo(uid).andOrder_idEqualTo(orderId);
        userWorkCalendarMapper.deleteByExample(userWorkCalendarCriteria);
    }
}
