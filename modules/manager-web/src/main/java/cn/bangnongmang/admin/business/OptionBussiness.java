package cn.bangnongmang.admin.business;


import cn.bangnongmang.data.domain.OptionCluster;
import cn.bangnongmang.data.domain.OptionClusterWorkingTypeMappingKey;
import cn.bangnongmang.data.domain.OptionDetail;
import cn.bangnongmang.data.domain.OptionWorkingType;
import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * Created by lucq on 2017/5/22.
 */
public interface OptionBussiness {

    public Boolean deleteOtpionDetailById(Long id);

    public Boolean modifyOptionDetail(OptionDetail optionDetail);

    public Boolean saveOptionDetail(OptionDetail optionDetail);

    public  Boolean deleteOptionCkusterById(Long id);

    public Boolean modifyOptionCluster(OptionCluster optionCluster);

    public Boolean saveOptionCluster(OptionCluster optionCluster);

    public Boolean deleteOptionWorkingTypeById(Long id);

    public Boolean modifyOptionWorkingType(OptionWorkingType optionWorkingType);

    public Boolean saveOptionWorkingType(OptionWorkingType optionWorkingType);

    public Boolean deleteWorkingTypeOptionClusterRelation(Long workingTypeId,Long optionClusterId);

    public Boolean saveWorkingTypeOptionClusterRelation(OptionClusterWorkingTypeMappingKey optionClusterWorkingTypeMappingKey);


}
