package cn.bangnongmang.admin.io.swagger.show;

import cn.bangnongmang.admin.io.swagger.converter.OptionConverter;
import cn.bangnongmang.admin.swagger.model.Option;
import cn.bangnongmang.admin.swagger.model.OptionCluster;
import cn.bangnongmang.admin.swagger.model.OptionDetail;
import cn.bangnongmang.admin.swagger.model.WorkingType;
import cn.bangnongmang.data.domain.OptionWorkingType;
import cn.bangnongmang.service.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lucq on 2017/5/23.
 */
@Service("optionShow")
public class OptionShow {

    @Autowired
    private OptionConverter optionConverter;
    @Autowired
    private OptionService optionService;

    public OptionDetail getOptionDetailById(Long id) {

        //optionService.getOptionDetailById(id);
        return optionConverter.convertToOptionDetail(optionService.getOptionDetailById(id));

    }

    public List<OptionDetail> getOptionDetailByClusterId(Long id) {

        return optionService.getOptionDetailsByClusterId(id)
                .stream()
                .map(optionDetail -> optionConverter.convertToOptionDetail(optionDetail))
                .collect(Collectors.toList());
    }

    public OptionCluster getOptionClusterById(Long id) {

        return optionConverter.convertToOptionCluster(optionService.getOptionClusterById(id));
    }

    public List<OptionCluster> getOptionCluster() {

        return optionService.getOptionCluster()
                .stream()
                .map(optionCluster -> optionConverter.convertToOptionCluster(optionCluster))
                .collect(Collectors.toList());
    }

    public WorkingType getOptionWorkingTypeById(Long id){

        return optionConverter.convertToWorkingType(optionService.getWorkingTypeById(id));

    }

    public List<WorkingType> getOptionWorkingType(){

        return  optionService.getWorkingType()
                             .stream()
                             .map(optionWorkingType -> optionConverter.convertToWorkingType(optionWorkingType))
                             .collect(Collectors.toList());
    }

    public List<OptionCluster> getOptionClusterByWorkingTypeId(Long workingTypeId){

        return optionService.getOptionClusterByWorkingTypeId(workingTypeId)
                     .stream()
                     .map(optionCluster -> optionConverter.convertToOptionCluster(optionCluster))
                     .collect(Collectors.toList());
    }
}
