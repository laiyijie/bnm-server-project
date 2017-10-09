package cn.bangnongmang.admin.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import cn.bangnongmang.data.dao.*;
import cn.bangnongmang.data.domain.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import cn.bangnongmang.admin.controller.api.OrderControllerApi;
import cn.bangnongmang.admin.service.OrderService;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.admin.util.PageResult;
import cn.bangnongmang.data.combo.dao.OrderFarmerInfoShowMapper;
import cn.bangnongmang.data.combo.domain.OrderFarmerInfoShow;

@RequestMapping(value = "/order")
@RestController
public class OrderController implements OrderControllerApi {
    @Autowired
    private OrderFarmerInfoShowMapper orderFarmerInfoShowMapper;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OptionClusterWorkingTypeMappingMapper optionClusterWorkingTypeMappingMapper;
    @Autowired
    private OptionClusterMapper optionClusterMapper;

    @Autowired
    private OrderFarmerMapper orderFarmerMapper;

    @Autowired
    private OrderFarmerWorkSizeMapper orderFarmerWorkSizeMapper;

    @Autowired
    private OrderWorkSizeImageMapper orderWorkSizeImageMapper;

    @Autowired
    private OptionDetailMapper optionDetailMapper;
    public static final String FARMER_WAITTING_AUTH = "FARMER_WAITTING_AUTH";

    /**
     * 查询未审核和全部订单。
     */
    @RequestMapping(value = "/getOrderList")
    @Override
    public PageResult<OrderFarmerInfoShow> getOrderList(
            @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
            @RequestParam(value = "type", defaultValue = "all") String type) throws BusinessException {
        Page<OrderFarmerInfoShow> orderFarmer = null;
        PageHelper.startPage(currentPage, pageSize);
        if ("waittingAuth".equals(type)) {
            orderFarmer = (Page<OrderFarmerInfoShow>) orderFarmerInfoShowMapper
                    .selectOrderFarmerInfoShowByState(FARMER_WAITTING_AUTH);
        } else {
            orderFarmer = (Page<OrderFarmerInfoShow>) orderFarmerInfoShowMapper.selectOrderFarmerInfoShow();
        }
        return new PageResult<OrderFarmerInfoShow>(orderFarmer);
    }

    /**
     * 更新选项，会覆盖原有的筛选项
     */
    @RequestMapping(value = "/updateOptions")
    @Override
    public String updateOptions(String orderId, String optionsJsonString) throws BusinessException {
        boolean options = orderService.updateOptions(orderId, optionsJsonString);
        if (options) {
            return "success";
        } else {
            throw new BusinessException("操作失败");
        }
    }

    @RequestMapping(value = "/updatePrePayRate")
    @Override
    public String updatePrePayRate(String orderId, Integer payRate) throws BusinessException {
        boolean prePayRate = orderService.updatePrePayRate(orderId, payRate);
        if (prePayRate) {
            return "success";
        } else {
            throw new BusinessException();
        }
    }

    @RequestMapping(value = "/updateDriverInsurance")
    public String updateDriverInsurance(String orderId, Integer money) throws BusinessException {
        boolean prePayRate = orderService.updateDriverInsurance(orderId, money);
        if (prePayRate) {
            return "success";
        } else {
            throw new BusinessException();
        }
    }

    @RequestMapping(value = "/updateCompnybonus")
    public String updateCompnybonus(String orderId, Integer rate) throws BusinessException {
        boolean prePayRate = orderService.updateCompanyBonus(orderId, rate);
        if (prePayRate) {
            return "success";
        } else {
            throw new BusinessException();
        }
    }


    @RequestMapping(value = "/authOrderFarmer")
    @Override
    public String authOrderFarmer(String orderId) {
        boolean farmer = orderService.authOrderFarmer(orderId);
        if (farmer) {
            return "success";
        }
        return null;
    }

    @Override
    public String denyOrderFarmer(String orderId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String updateTags(String tags, String orderId) throws BusinessException {
        OrderFarmer orderFarmer = orderFarmerMapper.selectByPrimaryKey(orderId);
        if (orderFarmer == null) {
            throw new BusinessException("订单不存在");
        }
        List<String> tagList = JSON.parseArray(tags, String.class);


        orderFarmer = new OrderFarmer();
        orderFarmer.setOrder_id(orderId);
        orderFarmer.setTags(tags);

        if (orderFarmerMapper.updateByPrimaryKeySelective(orderFarmer) > 0) {
            return "success";
        }

        throw new BusinessException("操作失败");

    }

    @Override
    public OrderFarmerInfoShow getOrderDetail(String orderId) {

        return orderFarmerInfoShowMapper.selectOrderFarmerInfoShowByOrderId(orderId);
    }

    @Override
    public List<OptionCluster> getOptionClusterByWorkingType(Long workingTypeId) {
        OptionClusterWorkingTypeMappingCriteria criteria = new OptionClusterWorkingTypeMappingCriteria();
        criteria.or()
                .andWorking_type_idEqualTo(workingTypeId);

        List<OptionClusterWorkingTypeMappingKey> keys = optionClusterWorkingTypeMappingMapper.selectByExample(criteria);

        if (keys.isEmpty()) {
            return new ArrayList<>();
        }

        OptionClusterCriteria optionClusterCriteria = new OptionClusterCriteria();
        for (OptionClusterWorkingTypeMappingKey optionClusterWorkingTypeMappingKey : keys) {
            optionClusterCriteria.or()
                                 .andIdEqualTo(optionClusterWorkingTypeMappingKey.getCluster_id());
        }

        return optionClusterMapper.selectByExample(optionClusterCriteria);
    }

    @Override
    public List<OptionDetail> getOptionDetailByCluster(Long clusterId) {
        OptionDetailCriteria optionDetailCriteria = new OptionDetailCriteria();
        optionDetailCriteria.or()
                            .andCluster_idEqualTo(clusterId);

        return optionDetailMapper.selectByExample(optionDetailCriteria);
    }

    @Override
    public String addMemeber(String orderId, String username) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String deleteMember(String orderId, String username, String reason) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String updateDesireStartTime(String orderId, long desireStartTime) throws BusinessException, ParseException {

        if (orderId == null && "".equals(desireStartTime)) {
            throw new BusinessException("时间不正确");
        }
        return orderService.updateDesireStartTime(orderId, desireStartTime);
    }

    @Override
    public String updateuniPrices(String orderId, Integer uniPrices) throws BusinessException {

        if (orderId == null && uniPrices == null) {
            throw new BusinessException("价钱为空");
        }

        return orderService.updateuniPrices(orderId, uniPrices);
    }

    @Override
    public String updateDesireNum(String orderId, Integer desireNum) throws BusinessException {

        if (orderId == null && desireNum == null) {
            throw new BusinessException("车辆为空");
        }

        return orderService.updateDesireNum(orderId, desireNum);
    }

    @Override
    public String updateSize(String orderId, double size) throws BusinessException {

        if (orderId == null && "".equals(size)) {
            throw new BusinessException("面积为空");
        }

        return orderService.updateSize(orderId, size);
    }

    @Override
    public List<JSONObject> getWorkSizeHistoryList(String orderId) {
        if (orderId == null) return new ArrayList<>();
        OrderFarmerWorkSizeCriteria orderFarmerWorkSizeCriteria = new OrderFarmerWorkSizeCriteria();
        orderFarmerWorkSizeCriteria.or()
                                   .andOrder_farmer_idEqualTo(orderId);
        List<OrderFarmerWorkSize> workSizes = orderFarmerWorkSizeMapper.selectByExample(orderFarmerWorkSizeCriteria);
        List<JSONObject> jsonObjects = new ArrayList<>();
        for (OrderFarmerWorkSize orderFarmerWorkSize : workSizes) {
            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(orderFarmerWorkSize));
            jsonObject.put("id", String.valueOf(jsonObject.getLong("id")));
            jsonObjects.add(jsonObject);
        }
        return jsonObjects;
    }

    @Override
    public List<OrderWorkSizeImage> getWorkSizeHistoryImages(String workSizeId) {
        if (workSizeId == null) return new ArrayList<>();

        OrderWorkSizeImageCriteria orderWorkSizeImageCriteria = new OrderWorkSizeImageCriteria();
        orderWorkSizeImageCriteria.or()
                                  .andOrder_farmer_work_size_idEqualTo(Long.valueOf(workSizeId));


        return orderWorkSizeImageMapper.selectByExample(orderWorkSizeImageCriteria);
    }


}
