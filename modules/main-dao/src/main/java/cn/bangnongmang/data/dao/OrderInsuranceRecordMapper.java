package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.OrderInsuranceRecord;
import cn.bangnongmang.data.domain.OrderInsuranceRecordCriteria;
import cn.bangnongmang.data.domain.OrderInsuranceRecordKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderInsuranceRecordMapper {
    long countByExample(OrderInsuranceRecordCriteria example);

    int deleteByExample(OrderInsuranceRecordCriteria example);

    int deleteByPrimaryKey(OrderInsuranceRecordKey key);

    int insert(OrderInsuranceRecord record);

    int insertSelective(OrderInsuranceRecord record);

    List<OrderInsuranceRecord> selectByExample(OrderInsuranceRecordCriteria example);

    OrderInsuranceRecord selectByPrimaryKey(OrderInsuranceRecordKey key);

    int updateByExampleSelective(@Param("record") OrderInsuranceRecord record, @Param("example") OrderInsuranceRecordCriteria example);

    int updateByExample(@Param("record") OrderInsuranceRecord record, @Param("example") OrderInsuranceRecordCriteria example);

    int updateByPrimaryKeySelective(OrderInsuranceRecord record);

    int updateByPrimaryKey(OrderInsuranceRecord record);
}