package cn.bangnongmang.admin.controller.api;

import java.text.ParseException;
import java.util.List;

import cn.bangnongmang.data.domain.OrderFarmerWorkSize;
import cn.bangnongmang.data.domain.OrderWorkSizeImage;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.admin.util.PageResult;
import cn.bangnongmang.data.combo.domain.OrderFarmerInfoShow;
import cn.bangnongmang.data.domain.OptionCluster;
import cn.bangnongmang.data.domain.OptionDetail;

@RequestMapping("order")
public interface OrderControllerApi {

    /**
     * 获取订单列表接口
     *
     * @param currentPage 需要查询的页码 默认 1
     * @param pageSize    每页显示的数量 默认 20
     * @param type        需要查询的类型 waittingAuth:等待认证 all:所有的
     * @return
     * @throws BusinessException
     * @Title getOrderList
     */
    @RequestMapping("getOrderList")
    public PageResult<OrderFarmerInfoShow> getOrderList(
            @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
            @RequestParam(value = "type", defaultValue = "all") String type) throws BusinessException;

    /**
     * 获取单个订单信息
     *
     * @param orderId
     * @return
     * @Title getOrderDetail
     */
    @RequestMapping("getOrderDetail")
    public OrderFarmerInfoShow getOrderDetail(@RequestParam(value = "orderId") String orderId);

    /**
     * 根据workingType 获取对应的 选项类型
     *
     * @param workingTypeId
     * @return
     * @Title getOptionClusterByWorkingType
     */
    @RequestMapping("getOptionClusterByWorkingType")
    public List<OptionCluster> getOptionClusterByWorkingType(@RequestParam("workingTypeId") Long workingTypeId);

    /**
     * 根据clusterId获取具体的选项
     *
     * @param clusterId
     * @return
     * @Title getOptionDetailByCluster
     */

    @RequestMapping("/getOptionDetailByCluster")
    public List<OptionDetail> getOptionDetailByCluster(@RequestParam("clusterId") Long clusterId);

    /**
     * 更新选项，会覆盖原有的筛选项
     *
     * @param orderId           订单编号（orderfarmer订单的订单号）
     * @param optionsJsonString 选项ID列表 转码成的 Json字符串
     * @return 成功返回"success"否则抛错
     * @throws BusinessException
     * @Title updateOptions
     */
    @RequestMapping("updateOptions")
    public String updateOptions(@RequestParam("orderId") String orderId,
                                @RequestParam("options") String optionsJsonString) throws BusinessException;

    /**
     * 更新预付比例 只有在等待认证状态才可以修改
     *
     * @param orderId orderFarmer的ID
     * @param payRate 0-100
     * @return 成功返回 "success"否则抛错
     * @throws BusinessException
     * @Title updatePrePayRate
     */
    @RequestMapping("updatePrePayRate")
    public String updatePrePayRate(@RequestParam("orderId") String orderId, @RequestParam("payRate") Integer payRate)
            throws BusinessException;

    /**
     * 认证订单，只有订单在 等待认证 状态的时候才能操作，其他情况进行这个操作进行报错，操作成功后，订单状态变更为 等待接单
     *
     * @param orderId
     * @return
     * @Title authOrderFarmer
     */
    @RequestMapping("authOrderFarmer")
    public String authOrderFarmer(@RequestParam("orderId") String orderId);

    /**
     * 暂时不写
     *
     * @param orderId
     * @return
     * @Title denyOrderFarmer
     */
    @RequestMapping("denyOrderFamer")
    public String denyOrderFarmer(@RequestParam("orderId") String orderId);

    /**
     * 增加队员
     *
     * @param orderId
     * @param username
     * @return
     * @Title addMemeber
     */
    @RequestMapping("addMember")
    public String addMemeber(@RequestParam("orderId") String orderId, @RequestParam("username") String username);

    /**
     * 删除队员
     *
     * @param orderId
     * @param username
     * @param reason
     * @return
     * @Title deleteMember
     */
    @RequestMapping("deleteMember")
    public String deleteMember(@RequestParam("orderId") String orderId, @RequestParam("username") String username,
                               @RequestParam("reason") String reason);

    /**
     * 更新訂單tag
     *
     * @param tags
     * @param orderId
     * @return
     * @throws BusinessException
     * @Title updateTags
     */
    @RequestMapping("updateTags")
    String updateTags(String tags, String orderId) throws BusinessException;

    /**
     * Description:update 作业开始时间
     *
     * @param orderId         订单id
     * @param desireStartTime long类型 时间戳;
     * @return 成 功返回"success"否则抛错
     * @throws BusinessException
     */
    @RequestMapping("updateDesireStartTime")
    public String updateDesireStartTime(String orderId, long desireStartTime) throws BusinessException, ParseException;


    /**
     * Description:update单价
     *
     * @param orderId   订单id
     * @param uniPrices int类型，不能为空
     * @return 成 功返回"success"否则抛错
     * @throws BusinessException
     */
    @RequestMapping("updateuniPrices")
    public String updateuniPrices(String orderId, Integer uniPrices) throws BusinessException;

    /**
     * Description:update订单需要车辆
     *
     * @param orderId   订单id
     * @param desireNum int类型 不能为空
     * @return 成 功返回"success"否则抛错
     * @throws BusinessException
     */

    @RequestMapping("updateDesireNum")
    public String updateDesireNum(String orderId, Integer desireNum) throws BusinessException;

    /**
     * Description:update 总面积
     *
     * @param orderId 订单id
     * @param size    double类型，不能为空，回车
     * @return 成 功返回"success"否则抛错
     * @throws BusinessException
     */


    @RequestMapping("updateSize")
    public String updateSize(String orderId, double size) throws BusinessException;


    @RequestMapping("getWorkSizeHistories")
    List<JSONObject> getWorkSizeHistoryList(String orderId);

    @RequestMapping("getWorkSizeHistoryImages")
    List<OrderWorkSizeImage> getWorkSizeHistoryImages(String workSizeId);

}
