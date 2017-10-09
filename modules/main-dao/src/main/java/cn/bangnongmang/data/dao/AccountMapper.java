package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.AccountCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountMapper {
    long countByExample(AccountCriteria example);

    int deleteByExample(AccountCriteria example);

    int deleteByPrimaryKey(Long uid);

    int insert(Account record);

    int insertSelective(Account record);

    List<Account> selectByExample(AccountCriteria example);

    Account selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") Account record, @Param("example") AccountCriteria example);

    int updateByExample(@Param("record") Account record, @Param("example") AccountCriteria example);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
}