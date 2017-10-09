package cn.bangnongmang.admin.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

import cn.bangnongmang.admin.enums.DriverFarmerEnum;
import cn.bangnongmang.admin.enums.OrderFarmerEnum;
import cn.bangnongmang.admin.service.OrderService;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.data.dao.AccountMapper;
import cn.bangnongmang.data.dao.OptionClusterMapper;
import cn.bangnongmang.data.dao.OptionClusterWorkingTypeMappingMapper;
import cn.bangnongmang.data.dao.OptionDetailMapper;
import cn.bangnongmang.data.dao.OptionOrderMappingMapper;
import cn.bangnongmang.data.dao.OptionWorkingTypeMapper;
import cn.bangnongmang.data.dao.OrderFarmerMapper;
import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.AccountCriteria;
import cn.bangnongmang.data.domain.OptionCluster;
import cn.bangnongmang.data.domain.OptionClusterWorkingTypeMappingCriteria;
import cn.bangnongmang.data.domain.OptionClusterWorkingTypeMappingKey;
import cn.bangnongmang.data.domain.OptionDetail;
import cn.bangnongmang.data.domain.OptionOrderMappingCriteria;
import cn.bangnongmang.data.domain.OptionOrderMappingKey;
import cn.bangnongmang.data.domain.OptionWorkingType;
import cn.bangnongmang.data.domain.OrderFarmer;
import cn.bangnongmang.data.domain.OrderFarmerCriteria;

/**
 * 订单service
 */
@Transactional(rollbackFor = {Exception.class})
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderFarmerMapper orderFarmerMapper;
    @Autowired
    private OptionOrderMappingMapper optionOrderMappingMapper;
    @Autowired
    private OptionDetailMapper optionDetailMapper;
    @Autowired
    private OptionClusterMapper optionClusterMapper;
    @Autowired
    private OptionClusterWorkingTypeMappingMapper optionClusterWorkingTypeMappingMapper;
    @Autowired
    private OptionWorkingTypeMapper optionWorkingTypeMapper;
    @Autowired
    private AccountMapper accountMapper;

    public boolean updateOptions(String orderId, String optionsJsonString) throws BusinessException {
        // 判断订单是否存在
        OrderFarmer orderFarmer = orderFarmerMapper.selectByPrimaryKey(orderId);
        if (null == orderFarmer) {
            throw new BusinessException("订单不存在");
        }
        OptionOrderMappingCriteria orderMappingCriteria = new OptionOrderMappingCriteria();
        orderMappingCriteria.or()
                            .andOrder_idEqualTo(orderId);
        optionOrderMappingMapper.deleteByExample(orderMappingCriteria);

        List<Long> optionsJsonStrings = JSON.parseArray(optionsJsonString, Long.class);
        // 判断订单是否存在OptionOrderMapping表中
        for (Long options : optionsJsonStrings) {
            OptionOrderMappingKey optionOrderMapping = new OptionOrderMappingKey();
            optionOrderMapping.setOption_id(options);
            optionOrderMapping.setOrder_id(orderId);
            optionOrderMappingMapper.insert(optionOrderMapping);
        }
        return true;

    }

    public void updateOrder(long options, String orderId) throws BusinessException {
        if (testUpdateOrderMapping(options)) {
            // 插入数据
            OptionOrderMappingKey optionOrderMapping = new OptionOrderMappingKey();
            optionOrderMapping.setOption_id(options);
            optionOrderMapping.setOrder_id(orderId);
            optionOrderMappingMapper.insert(optionOrderMapping);

        }

    }

    public boolean testUpdateOrderMapping(long options) throws BusinessException {
        OptionDetail optionDetail = optionDetailMapper.selectByPrimaryKey(options);
        if (optionDetail != null) {
            OptionCluster optionCluster = optionClusterMapper.selectByPrimaryKey(optionDetail.getCluster_id());
            if (optionCluster != null) {
                update(optionCluster);
            } else {
                throw new BusinessException("option_id填写不正确");
            }

        } else {
            throw new BusinessException("option_id填写不正确");
        }
        return true;
    }

    public boolean update(OptionCluster optionCluster) throws BusinessException {

        OptionClusterWorkingTypeMappingCriteria optionClusterWorkingTypeMappingCriteria = new OptionClusterWorkingTypeMappingCriteria();
        optionClusterWorkingTypeMappingCriteria.clear();
        optionClusterWorkingTypeMappingCriteria.or()
                                               .andCluster_idEqualTo(optionCluster.getId());
        List<OptionClusterWorkingTypeMappingKey> Options = optionClusterWorkingTypeMappingMapper
                .selectByExample(optionClusterWorkingTypeMappingCriteria);

        for (OptionClusterWorkingTypeMappingKey optionClusterWorkingTypeMappingKey : Options) {
            OptionWorkingType workingType = optionWorkingTypeMapper
                    .selectByPrimaryKey(optionClusterWorkingTypeMappingKey.getWorking_type_id());
            if (workingType != null) {
                return true;
            } else {
                throw new BusinessException("option_id填写不正确");
            }
        }
        return false;

    }

    public boolean updatePrePayRate(String orderId, Integer payRate) throws BusinessException {
        OrderFarmerCriteria farmerCriteria = new OrderFarmerCriteria();
        farmerCriteria.or()
                      .andStateEqualTo(OrderFarmerEnum.FARMER_WAITTING_AUTH.getCurrencyCode())
                      .andOrder_idEqualTo(orderId);
        List<OrderFarmer> selectByExample = orderFarmerMapper.selectByExample(farmerCriteria);
        if (!selectByExample.isEmpty()) {
            if (payRate >= 0 && payRate <= 100) {
                OrderFarmer farmer = selectByExample.get(0);
                farmer.setPre_pay_rate(payRate);
                if (orderFarmerMapper.updateByExample(farmer, farmerCriteria) > 0) {
                    return true;
                } else {
                    throw new BusinessException("预付比例更新失败");
                }
            } else {
                throw new BusinessException("比例填写不正确");
            }
        } else {
            throw new BusinessException("预付比例无法更改");
        }
    }

    @Override
    public boolean updateDriverInsurance(String orderId, Integer rate) {
        OrderFarmer orderFarmer = orderFarmerMapper.selectByPrimaryKey(orderId);
        if (orderFarmer == null)
            throw new BusinessException("订单不存在");
        orderFarmer.setDriver_insurance(rate);
        orderFarmerMapper.updateByPrimaryKey(orderFarmer);
        return true;
    }

    @Override
    public boolean updateCompanyBonus(String orderId, Integer rate) {
        OrderFarmer orderFarmer = orderFarmerMapper.selectByPrimaryKey(orderId);
        if (orderFarmer == null)
            throw new BusinessException("订单不存在");
        orderFarmer.setCompany_bonus(rate);
        orderFarmerMapper.updateByPrimaryKey(orderFarmer);
        return true;
    }

    public boolean authOrderFarmer(String orderId) {
        OrderFarmerCriteria farmerCriteria = new OrderFarmerCriteria();
        farmerCriteria.or()
                      .andStateEqualTo(OrderFarmerEnum.FARMER_WAITTING_AUTH.getCurrencyCode())
                      .andOrder_idEqualTo(orderId);
        List<OrderFarmer> selectByExample = orderFarmerMapper.selectByExample(farmerCriteria);
        if (!selectByExample.isEmpty()) {
            OrderFarmer farmer = selectByExample.get(0);
            farmer.setState(OrderFarmerEnum.FARMER_WAITTING_PAY.getCurrencyCode());
            if (orderFarmerMapper.updateByExample(farmer, farmerCriteria) > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String updateDesireStartTime(String orderId, long desireStartTime) throws ParseException, BusinessException {

        OrderFarmer orderFarmer = getOrderFarmerState(orderId);

        orderFarmer.setDesire_start_time(desireStartTime);
        if (orderFarmerMapper.updateByPrimaryKey(orderFarmer) < 0) {
            throw new BusinessException("时间更新失败");
        }

        return "success";
    }

    @Override
    public String updateuniPrices(String orderId, Integer uniPrices) throws BusinessException {

        OrderFarmer orderFarmer = getOrderFarmerState(orderId);
        orderFarmer.setUni_price(uniPrices);

        if (orderFarmerMapper.updateByPrimaryKey(orderFarmer) < 0) {
            throw new BusinessException("单价更新失败");
        }

        return "success";
    }

    @Override
    public String updateDesireNum(String orderId, Integer desireNum) throws BusinessException {

        OrderFarmer orderFarmer = getOrderFarmerState(orderId);
        orderFarmer.setDesire_num(desireNum);

        if (orderFarmerMapper.updateByPrimaryKey(orderFarmer) < 0) {
            throw new BusinessException("车辆更新失败");
        }

        return "success";
    }

    @Override
    public String updateSize(String orderId, double size) throws BusinessException {

        OrderFarmer orderFarmer = getOrderFarmerState(orderId);
        orderFarmer.setSize(size);

        if (orderFarmerMapper.updateByPrimaryKey(orderFarmer) < 0) {
            throw new BusinessException("面积更新失败");
        }

        return "success";
    }

    public OrderFarmer getOrderFarmerState(String orderId) throws BusinessException {

        OrderFarmer orderFarmer = orderFarmerMapper.selectByPrimaryKey(orderId);

        AccountCriteria accountCriteria = new AccountCriteria();
        accountCriteria.or()
                       .andUidEqualTo(orderFarmer.getUid());
        List<Account> example = accountMapper.selectByExample(accountCriteria);

        if (example.isEmpty()) {
            throw new BusinessException("农主不存在");
        }

        Account account = example.get(0);
        if (!account.getLevel()
                    .equals(DriverFarmerEnum.AUTHENTICATED_FARMER.getCurrencyCode())) {
            throw new BusinessException("农主未认证");
        }

        return orderFarmer;

    }
}
