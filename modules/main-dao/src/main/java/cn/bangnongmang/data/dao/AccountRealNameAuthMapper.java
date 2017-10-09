package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.AccountRealNameAuth;
import cn.bangnongmang.data.domain.AccountRealNameAuthCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountRealNameAuthMapper {
    long countByExample(AccountRealNameAuthCriteria example);

    int deleteByExample(AccountRealNameAuthCriteria example);

    int deleteByPrimaryKey(Long uid);

    int insert(AccountRealNameAuth record);

    int insertSelective(AccountRealNameAuth record);

    List<AccountRealNameAuth> selectByExample(AccountRealNameAuthCriteria example);

    AccountRealNameAuth selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") AccountRealNameAuth record, @Param("example") AccountRealNameAuthCriteria example);

    int updateByExample(@Param("record") AccountRealNameAuth record, @Param("example") AccountRealNameAuthCriteria example);

    int updateByPrimaryKeySelective(AccountRealNameAuth record);

    int updateByPrimaryKey(AccountRealNameAuth record);
}