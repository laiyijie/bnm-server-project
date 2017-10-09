package cn.bangnongmang.service.service;

/**
 * Created by lucq on 2017/5/18.
 */
import java.util.List;

import cn.bangnongmang.data.dao.UserWorkCalendarMapper;
import cn.bangnongmang.data.domain.UserWorkCalendar;
import cn.bangnongmang.data.domain.UserWorkCalendarCriteria;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.bangnongmang.data.dao.AccountMapper;
import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.AccountCriteria;


import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:test-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class UserWorkCalendarTest{
    @Autowired
    private UserWorkCalendarMapper userWorkCalendarMapper;
    @Autowired
    private UserWorkCalendarService userWorkCalendarService;

    @Before
    public void tear(){
        UserWorkCalendar userWorkCalendar=new UserWorkCalendar();

        for(int i=0;i<5;i++){
            userWorkCalendar.setOrder_id("11111");
            userWorkCalendar.setTime_start(1L);
            userWorkCalendar.setTime_end(1L);
            userWorkCalendar.setUid(1L);
            userWorkCalendarMapper.insert(userWorkCalendar);
        };

        userWorkCalendar.setOrder_id("11112");
        userWorkCalendar.setTime_start(1L);
        userWorkCalendar.setTime_end(1L);
        userWorkCalendar.setUid(1L);
        userWorkCalendarMapper.insert(userWorkCalendar);

    }
    @Test
    public void testDelete(){
        userWorkCalendarService.deleteCalendarByOrderId("11111");

        List test=userWorkCalendarMapper.selectByExample(new UserWorkCalendarCriteria());

        assertEquals(1,test.size());
    }

    @After
    public void end(){

        userWorkCalendarMapper.deleteByExample(new UserWorkCalendarCriteria());

    }
}
