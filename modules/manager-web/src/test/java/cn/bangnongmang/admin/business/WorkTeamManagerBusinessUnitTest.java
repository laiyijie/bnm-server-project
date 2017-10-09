package cn.bangnongmang.admin.business;

import cn.bangnongmang.admin.business.impl.WorkTeamManageBusinessImpl;
import cn.bangnongmang.service.middle.pocket.OrderInsuranceService;
import cn.bangnongmang.service.middle.pocket.TradeFlowService;
import cn.bangnongmang.service.service.OrderDriverService;
import cn.bangnongmang.service.service.OrderFarmerService;
import cn.bangnongmang.service.service.TeamOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by admin on 2017-05-22.
 */

@RunWith(MockitoJUnitRunner.class)
public class WorkTeamManagerBusinessUnitTest {

    @InjectMocks
    private WorkTeamManageBusinessImpl workTeamManageBusiness;
    @Mock
    private TradeFlowService tradeFlowService;
    @Mock
    private OrderFarmerService orderFarmerService;
    @Mock
    private OrderDriverService orderDriverService;
    @Mock
    private TeamOrderService teamOrderService;
    @Mock
    private OrderInsuranceService orderInsuranceService;

    @Test
    public void TC0101_addMemberTest_normal(){
        

    }

}
