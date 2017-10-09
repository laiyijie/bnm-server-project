package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.OrderFarmerWorkSize;
import cn.bangnongmang.data.domain.OrderFarmerWorkSizeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderFarmerWorkSizeMapper {
    long countByExample(OrderFarmerWorkSizeCriteria example);

    int deleteByExample(OrderFarmerWorkSizeCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderFarmerWorkSize record);

    int insertSelective(OrderFarmerWorkSize record);

    List<OrderFarmerWorkSize> selectByExample(OrderFarmerWorkSizeCriteria example);

    OrderFarmerWorkSize selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderFarmerWorkSize record, @Param("example") OrderFarmerWorkSizeCriteria example);

    int updateByExample(@Param("record") OrderFarmerWorkSize record, @Param("example") OrderFarmerWorkSizeCriteria example);

    int updateByPrimaryKeySelective(OrderFarmerWorkSize record);

    int updateByPrimaryKey(OrderFarmerWorkSize record);
}