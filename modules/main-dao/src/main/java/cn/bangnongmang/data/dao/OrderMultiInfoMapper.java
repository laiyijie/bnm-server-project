package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.OrderMultiInfo;
import cn.bangnongmang.data.domain.OrderMultiInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderMultiInfoMapper {
    long countByExample(OrderMultiInfoCriteria example);

    int deleteByExample(OrderMultiInfoCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderMultiInfo record);

    int insertSelective(OrderMultiInfo record);

    List<OrderMultiInfo> selectByExample(OrderMultiInfoCriteria example);

    OrderMultiInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderMultiInfo record, @Param("example") OrderMultiInfoCriteria example);

    int updateByExample(@Param("record") OrderMultiInfo record, @Param("example") OrderMultiInfoCriteria example);

    int updateByPrimaryKeySelective(OrderMultiInfo record);

    int updateByPrimaryKey(OrderMultiInfo record);
}