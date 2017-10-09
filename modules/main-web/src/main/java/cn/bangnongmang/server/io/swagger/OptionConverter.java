package cn.bangnongmang.server.io.swagger;

import cn.bangnongmang.server.swagger.model.OptionCluster;
import cn.bangnongmang.server.swagger.model.OptionDetail;
import cn.bangnongmang.server.swagger.model.WorkingType;
import org.springframework.stereotype.Component;

/**
 * Created by lucq on 2017/5/23.
 */
@Component("optionConverter")
public class OptionConverter {


    public OptionDetail convertToOptionDetail(cn.bangnongmang.data.domain.OptionDetail optionDetail) {

        if (optionDetail == null)
            return null;

        OptionDetail od = new OptionDetail();
        od.setId(optionDetail.getId());
        od.setName(optionDetail.getOption_name());
        od.setDescprition(optionDetail.getDescription());

        return od;

    }

    public OptionCluster convertToOptionCluster(cn.bangnongmang.data.domain.OptionCluster optionCluster) {

        if (optionCluster == null)
            return null;

        OptionCluster oc = new OptionCluster();
        oc.setId(optionCluster.getId());
        oc.setName(optionCluster.getCluster_name());
        oc.setDescription(optionCluster.getDescription());

        return oc;
    }

    public WorkingType convertToWorkingType(cn.bangnongmang.data.domain.OptionWorkingType optionWorkingType){

        if(optionWorkingType==null)
            return null;

        WorkingType wt=new WorkingType();
        wt.setId(optionWorkingType.getId());
        wt.setCropType(optionWorkingType.getCrop_type());
        wt.setWorkingType(optionWorkingType.getWorking_type());

        return  wt;
    }
}
