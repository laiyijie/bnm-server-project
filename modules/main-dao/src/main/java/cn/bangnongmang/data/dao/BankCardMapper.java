package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.BankCard;
import cn.bangnongmang.data.domain.BankCardCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BankCardMapper {
    long countByExample(BankCardCriteria example);

    int deleteByExample(BankCardCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(BankCard record);

    int insertSelective(BankCard record);

    List<BankCard> selectByExample(BankCardCriteria example);

    BankCard selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BankCard record, @Param("example") BankCardCriteria example);

    int updateByExample(@Param("record") BankCard record, @Param("example") BankCardCriteria example);

    int updateByPrimaryKeySelective(BankCard record);

    int updateByPrimaryKey(BankCard record);
}