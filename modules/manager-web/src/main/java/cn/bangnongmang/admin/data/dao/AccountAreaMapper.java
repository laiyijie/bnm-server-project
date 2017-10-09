package cn.bangnongmang.admin.data.dao;

import cn.bangnongmang.admin.data.domain.AccountAreaCriteria;
import cn.bangnongmang.admin.data.domain.AccountAreaKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountAreaMapper {
    long countByExample(AccountAreaCriteria example);

    int deleteByExample(AccountAreaCriteria example);

    int deleteByPrimaryKey(AccountAreaKey key);

    int insert(AccountAreaKey record);

    int insertSelective(AccountAreaKey record);

    List<AccountAreaKey> selectByExample(AccountAreaCriteria example);

    int updateByExampleSelective(@Param("record") AccountAreaKey record, @Param("example") AccountAreaCriteria example);

    int updateByExample(@Param("record") AccountAreaKey record, @Param("example") AccountAreaCriteria example);
}