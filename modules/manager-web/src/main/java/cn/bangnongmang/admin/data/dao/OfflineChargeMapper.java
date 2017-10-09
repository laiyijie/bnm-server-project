package cn.bangnongmang.admin.data.dao;

import cn.bangnongmang.admin.data.domain.OfflineCharge;
import cn.bangnongmang.admin.data.domain.OfflineChargeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OfflineChargeMapper {
    long countByExample(OfflineChargeCriteria example);

    int deleteByExample(OfflineChargeCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(OfflineCharge record);

    int insertSelective(OfflineCharge record);

    List<OfflineCharge> selectByExample(OfflineChargeCriteria example);

    OfflineCharge selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") OfflineCharge record, @Param("example") OfflineChargeCriteria example);

    int updateByExample(@Param("record") OfflineCharge record, @Param("example") OfflineChargeCriteria example);

    int updateByPrimaryKeySelective(OfflineCharge record);

    int updateByPrimaryKey(OfflineCharge record);
}