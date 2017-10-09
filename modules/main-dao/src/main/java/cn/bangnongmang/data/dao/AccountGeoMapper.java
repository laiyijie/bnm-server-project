package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.AccountGeo;
import cn.bangnongmang.data.domain.AccountGeoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountGeoMapper {
    long countByExample(AccountGeoCriteria example);

    int deleteByExample(AccountGeoCriteria example);

    int deleteByPrimaryKey(Long uid);

    int insert(AccountGeo record);

    int insertSelective(AccountGeo record);

    List<AccountGeo> selectByExample(AccountGeoCriteria example);

    AccountGeo selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") AccountGeo record, @Param("example") AccountGeoCriteria example);

    int updateByExample(@Param("record") AccountGeo record, @Param("example") AccountGeoCriteria example);

    int updateByPrimaryKeySelective(AccountGeo record);

    int updateByPrimaryKey(AccountGeo record);
}