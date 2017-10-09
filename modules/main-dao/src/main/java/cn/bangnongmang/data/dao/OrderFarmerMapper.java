package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.OrderFarmer;
import cn.bangnongmang.data.domain.OrderFarmerCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderFarmerMapper {
    long countByExample(OrderFarmerCriteria example);

    int deleteByExample(OrderFarmerCriteria example);

    int deleteByPrimaryKey(String order_id);

    int insert(OrderFarmer record);

    int insertSelective(OrderFarmer record);

    List<OrderFarmer> selectByExample(OrderFarmerCriteria example);

    OrderFarmer selectByPrimaryKey(String order_id);

    int updateByExampleSelective(@Param("record") OrderFarmer record, @Param("example") OrderFarmerCriteria example);

    int updateByExample(@Param("record") OrderFarmer record, @Param("example") OrderFarmerCriteria example);

    int updateByPrimaryKeySelective(OrderFarmer record);

    int updateByPrimaryKey(OrderFarmer record);
}