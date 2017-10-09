package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.AccountPortrait;
import cn.bangnongmang.data.domain.AccountPortraitCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountPortraitMapper {
    long countByExample(AccountPortraitCriteria example);

    int deleteByExample(AccountPortraitCriteria example);

    int deleteByPrimaryKey(Long uid);

    int insert(AccountPortrait record);

    int insertSelective(AccountPortrait record);

    List<AccountPortrait> selectByExample(AccountPortraitCriteria example);

    AccountPortrait selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") AccountPortrait record, @Param("example") AccountPortraitCriteria example);

    int updateByExample(@Param("record") AccountPortrait record, @Param("example") AccountPortraitCriteria example);

    int updateByPrimaryKeySelective(AccountPortrait record);

    int updateByPrimaryKey(AccountPortrait record);
}