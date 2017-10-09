package cn.bangnongmang.service.service;

import java.util.List;

import cn.bangnongmang.data.domain.OrderDriverWorkSize;
import cn.bangnongmang.data.domain.OrderFarmerWorkSize;
import cn.bangnongmang.data.domain.OrderWorkSizeImage;

public interface WorkSizeService {

    public static final Integer WORK_SIZE_STATE_WAITTING_ENSURE = 200;
    public static final Integer WORK_SIZE_STATE_ENSURED = 300;
    public static final Integer WORK_SIZE_STATE_DENIED = 400;
    public static final Integer WORK_SIZE_STATE_FINISH = 500;

    Boolean createOrderFarmerWorkSize(OrderFarmerWorkSize orderFarmerWorkSize);

    OrderFarmerWorkSize getOrderFarmerWorkSizeByOrderIdAndState(String orderId, Integer state);

    List<OrderDriverWorkSize> getOrderDriverWorkSizeListByOrderFarmerIdAndUsername(String
                                                                                           orderId,
                                                                                   Long uid);

    boolean addWorkSizeAuthImage(Long orderFarmerWorkSizeId, String url, String title);

    void deleteWorkSizeAuthImage(Long id);

    OrderWorkSizeImage getWorkSizeAuthImage(Long id);

    OrderDriverWorkSize createOrderDriverWorkSize(OrderDriverWorkSize orderDriverWorkSize);

    Boolean changeOrderFarmerWorkSizeStateTo(Long id, Integer workSizeStateFinish);

    boolean updateOrderFarmerWorkSize(Long id, Double totalSize);

    boolean insertOnConflictUpdateWorkEfficiency(Long uid, Long workingTypeId,
                                                 Double addingSize,
                                                 Integer addingCount);

}
