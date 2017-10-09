package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.PhoneVerify;
import cn.bangnongmang.data.domain.PhoneVerifyCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PhoneVerifyMapper {
    long countByExample(PhoneVerifyCriteria example);

    int deleteByExample(PhoneVerifyCriteria example);

    int deleteByPrimaryKey(Integer id_phoneverify);

    int insert(PhoneVerify record);

    int insertSelective(PhoneVerify record);

    List<PhoneVerify> selectByExample(PhoneVerifyCriteria example);

    PhoneVerify selectByPrimaryKey(Integer id_phoneverify);

    int updateByExampleSelective(@Param("record") PhoneVerify record, @Param("example") PhoneVerifyCriteria example);

    int updateByExample(@Param("record") PhoneVerify record, @Param("example") PhoneVerifyCriteria example);

    int updateByPrimaryKeySelective(PhoneVerify record);

    int updateByPrimaryKey(PhoneVerify record);
}