package cn.bangnongmang.admin.swagger.controller.impl;

import cn.bangnongmang.admin.business.OptionBussiness;
import cn.bangnongmang.admin.io.swagger.show.OptionShow;
import cn.bangnongmang.admin.swagger.api.OptionApi;
import cn.bangnongmang.admin.swagger.controller.base.BaseConf;
import cn.bangnongmang.admin.swagger.model.OptionCluster;
import cn.bangnongmang.admin.swagger.model.OptionDetail;
import cn.bangnongmang.admin.swagger.model.WorkingType;
import cn.bangnongmang.data.domain.OptionClusterWorkingTypeMappingKey;
import cn.bangnongmang.data.domain.OptionWorkingType;
import cn.bangnongmang.server.log.BLog;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by lucq on 2017/5/22.
 */
@RequestMapping(BaseConf.BASE_URL)
@RestController
public class OptionConctroller implements OptionApi {
    @Autowired
    private OptionBussiness optionBussiness;
    @Autowired
    private OptionShow optionShow;


    @Override
    public ResponseEntity<Void> optionOptionClustersClusterIdDelete( @ApiParam(value = "选项类型的ID",required=true ) @PathVariable("clusterId") Long
            clusterId, HttpServletRequest request, HttpServletResponse response) throws Exception {

        optionBussiness.deleteOptionCkusterById(clusterId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OptionCluster> optionOptionClustersClusterIdGet(
            @ApiParam(value = "选项类型的ID", required = true) @PathVariable("clusterId") Long clusterId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        OptionCluster oc = optionShow.getOptionClusterById(clusterId);
        if (oc == null) {
            return new ResponseEntity<OptionCluster>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(oc, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<List<OptionCluster>> optionOptionClustersGet(
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        return new ResponseEntity<List<OptionCluster>>(optionShow.getOptionCluster(), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Void> optionOptionClustersClusterIdOptionDetailsDetailIdDelete(
            @ApiParam(value = "选项类型的ID", required = true) @PathVariable("clusterId") Long clusterId,
            @ApiParam(value = "选项的id", required = true) @PathVariable("detailId") Long detailId, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        optionBussiness.deleteOtpionDetailById(detailId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OptionDetail> optionOptionClustersClusterIdOptionDetailsDetailIdGet(
            @ApiParam(value = "选项类型的ID", required = true) @PathVariable("clusterId") Long clusterId,
            @ApiParam(value = "选项的id", required = true) @PathVariable("detailId") Long detailId, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        OptionDetail od = optionShow.getOptionDetailById(detailId);
        if (od == null) {
            return new ResponseEntity<OptionDetail>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<OptionDetail>(od, HttpStatus.OK);
        }
    }


    @Override
    public ResponseEntity<Void> optionOptionClustersClusterIdOptionDetailsDetailIdPut(
            @ApiParam(value = "选项类型的ID", required = true) @PathVariable("clusterId") Long clusterId,
            @ApiParam(value = "选项的id", required = true) @PathVariable("detailId") Long detailId,
            @ApiParam(value = "optionDetail 选项详细信息，不用传id", required = true) @Valid @RequestBody OptionDetail optionDetail,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        cn.bangnongmang.data.domain.OptionDetail op = new cn.bangnongmang.data.domain.OptionDetail();
        op.setId(detailId);
        op.setCluster_id(clusterId);
        op.setOption_name(optionDetail.getName());
        op.setDescription(optionDetail.getDescprition());
        BLog.businessJsonLogBuilder("Option:Modify")
            .put("op:", op.toString())
            .log();

        optionBussiness.modifyOptionDetail(op);
        return new ResponseEntity<Void>(HttpStatus.OK);


    }

    @Override
    public ResponseEntity<List<OptionDetail>> optionOptionClustersClusterIdOptionDetailsGet(
            @ApiParam(value = "选项类型的ID", required = true) @PathVariable("clusterId") Long clusterId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<OptionDetail> list = optionShow.getOptionDetailByClusterId(clusterId);

        return new ResponseEntity<List<OptionDetail>>(list, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Void> optionOptionClustersClusterIdOptionDetailsPost(
            @ApiParam(value = "选项类型的ID", required = true) @PathVariable("clusterId") Long clusterId,
            @ApiParam(value = "optionDetail 选项详细信息", required = true) @Valid @RequestBody OptionDetail optionDetail,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        cn.bangnongmang.data.domain.OptionDetail op = new cn.bangnongmang.data.domain.OptionDetail();
        op.setCluster_id(clusterId);
        op.setOption_name(optionDetail.getName());
        op.setDescription(optionDetail.getDescprition());
        BLog.businessJsonLogBuilder("Option:ADD")
            .put("op:", op.getOption_name())
            .log();
        optionBussiness.saveOptionDetail(op);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> optionOptionClustersClusterIdPut(
            @ApiParam(value = "新的option选项", required = true) @Valid @RequestBody OptionCluster optionCluster,
            @ApiParam(value = "选项类型的ID", required = true) @PathVariable("clusterId") Long clusterId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {


        cn.bangnongmang.data.domain.OptionCluster oc = new cn.bangnongmang.data.domain.OptionCluster();
        oc.setId(clusterId);
        oc.setCluster_name(optionCluster.getName());
        oc.setDescription(optionCluster.getDescription());
        optionBussiness.modifyOptionCluster(oc);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> optionOptionClustersClusterIdWorkingTypesWorkingTypeIdDelete(
            @ApiParam(value = "选项类型的ID",required=true ) @PathVariable("clusterId") Long clusterId,
            @ApiParam(value = "选项类型的ID",required=true ) @PathVariable("workingTypeId") Long workingTypeId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        return null;
    }

    @Override
    public ResponseEntity<Void> optionOptionClustersClusterIdWorkingTypesWorkingTypeIdPost(
            @ApiParam(value = "选项类型的ID",required=true ) @PathVariable("clusterId") Long clusterId,
            @ApiParam(value = "选项类型的ID",required=true ) @PathVariable("workingTypeId") Long workingTypeId,
            HttpServletRequest request, HttpServletResponse response) throws Exception{

        OptionClusterWorkingTypeMappingKey ocwtmk=new OptionClusterWorkingTypeMappingKey();
        ocwtmk.setWorking_type_id(workingTypeId);
        ocwtmk.setCluster_id(clusterId);

        optionBussiness.saveWorkingTypeOptionClusterRelation(ocwtmk);
        return null;
    }

    @Override
    public ResponseEntity<Void> optionOptionClustersPost(
            @ApiParam(value = "新的option选项", required = true) @Valid @RequestBody OptionCluster optionCluster,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        cn.bangnongmang.data.domain.OptionCluster oc = new cn.bangnongmang.data.domain.OptionCluster();
        oc.setCluster_name(optionCluster.getName());
        oc.setDescription(optionCluster.getDescription());
        optionBussiness.saveOptionCluster(oc);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<WorkingType>> optionWorkingTypesGet(HttpServletRequest request, HttpServletResponse response) throws Exception {

        return new ResponseEntity<List<WorkingType>>(optionShow.getOptionWorkingType(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> optionWorkingTypesPost(@ApiParam(value = "", required = true) @Valid @RequestBody WorkingType workType,
                                                       HttpServletRequest request, HttpServletResponse response) throws Exception {
        cn.bangnongmang.data.domain.OptionWorkingType owt = new cn.bangnongmang.data.domain.OptionWorkingType();

        owt.setCrop_type(workType.getCropType());
        owt.setWorking_type(workType.getCropType());

        optionBussiness.saveOptionWorkingType(owt);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> optionWorkingTypesWorkingTypeIdDelete(@ApiParam(value = "",required=true ) @PathVariable("workingTypeId")Long workingTypeId,
                                                                      HttpServletRequest request, HttpServletResponse response) throws Exception {

        optionBussiness.deleteOptionWorkingTypeById(workingTypeId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<WorkingType> optionWorkingTypesWorkingTypeIdGet(
            @ApiParam(value = "", required = true) @PathVariable("workingTypeId") Long workingTypeId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        WorkingType wt = optionShow.getOptionWorkingTypeById(workingTypeId);

        if (wt == null) {
            return new ResponseEntity<WorkingType>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<WorkingType>(wt, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<List<OptionCluster>> optionWorkingTypesWorkingTypeIdOptionClusterGet(
            @ApiParam(value = "工作类型Id",required=true ) @PathVariable("workingTypeId") Long workingTypeId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        return new ResponseEntity<List<OptionCluster>>(optionShow.getOptionClusterByWorkingTypeId(workingTypeId),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> optionWorkingTypesWorkingTypeIdPut(@ApiParam(value = "", required = true) @Valid @RequestBody WorkingType workType,
                                                                   @ApiParam(value = "", required = true) @PathVariable("workingTypeId") Long workingTypeId,
                                                                   HttpServletRequest request, HttpServletResponse response) throws Exception {
        cn.bangnongmang.data.domain.OptionWorkingType owt = new cn.bangnongmang.data.domain.OptionWorkingType();
        owt.setId(workingTypeId);
        owt.setCrop_type(workType.getCropType());
        owt.setWorking_type(workType.getCropType());

        optionBussiness.modifyOptionWorkingType(owt);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }




}
