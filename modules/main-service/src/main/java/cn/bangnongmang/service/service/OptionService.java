package cn.bangnongmang.service.service;

import cn.bangnongmang.data.domain.*;

import java.util.List;

public interface OptionService {

    boolean addOptionOrderMapping(String orderId, Long optionDetailId);

    OptionOrderMappingKey getOptionOrderMapping(String orderId, Long optionId);


    OptionDetail getOptionDetailById(Long id);

    List<OptionDetail> getOptionDetailsByClusterId(Long clusterId);

    Boolean modifyOptionDetail(OptionDetail optionDetail);

    Boolean saveOptionDetail(OptionDetail optionDetail);

    Boolean deleteOptionDetailById(Long id);


    OptionCluster getOptionClusterById(Long id);

    List<OptionCluster> getOptionCluster();

    Boolean modifyOptionCluster(OptionCluster optionCluster);

    Boolean saveOptionCluster(OptionCluster optionCluster);

    Boolean deleteOptionClusterById(Long id);

    OptionWorkingType getWorkingTypeById(Long id);

    List<OptionWorkingType> getWorkingType();
    List<OptionWorkingType> getWorkingTypeByOptionClusterId(Long optionClusterId);

    Boolean modifyOptionWorkingType(OptionWorkingType optionWorkingType);

    Boolean saveOptionWorkingType(OptionWorkingType optionWorkingType);

    Boolean deleteOptionWorkingType(Long id);

    List<OptionCluster> getOptionClusterByWorkingTypeId(Long workingTypeId);

    Boolean saveWorkingTypeOptionClusterRelation(OptionClusterWorkingTypeMappingKey optionClusterWorkingTypeMappingKey);

    Boolean deleteWorkingTypeOptionClusterRelation(Long workingtypeId, Long optionClusterId);

    Boolean optionDetailHasOrderOrMachine(Long optionDetialId);
    Boolean workingTypeHasOrderOrMachineModel(Long workingTypeId);
    Boolean isExistWorkingTypeOptionClusterRelation(OptionClusterWorkingTypeMappingKey optionWorkingTypeMachineModelMappingKey);
    Boolean isWorkingTypeOptionClusterRelationUsedOnOrderMachine(Long workingTypeId, Long optionClusterId);


    List<OptionCluster> getOptionClusterByMachineTypeId(Long machineType);

    List<OptionDetail> getOptionDetailByModelId(Long ModelId);


}
