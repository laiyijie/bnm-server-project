package cn.bangnongmang.admin.swagger.api;

import cn.bangnongmang.admin.swagger.model.OptionCluster;
import cn.bangnongmang.admin.swagger.model.OptionDetail;
import cn.bangnongmang.admin.swagger.model.WorkingType;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Api(value = "option", description = "the option API")
public interface OptionApi {

    @ApiOperation(value = "", notes = "", response = Void.class, tags={ "Option", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/option/optionClusters/{clusterId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> optionOptionClustersClusterIdDelete(@ApiParam(value = "选项类型的ID",required=true ) @PathVariable("clusterId") Long clusterId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获得一个选择集", notes = "", response = OptionCluster.class, tags={ "Option", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = OptionCluster.class) })
    
    @RequestMapping(value = "/option/optionClusters/{clusterId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<OptionCluster> optionOptionClustersClusterIdGet(@ApiParam(value = "选项类型的ID",required=true ) @PathVariable("clusterId") Long clusterId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "删除选项", notes = "", response = Void.class, tags={ "Option", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/option/optionClusters/{clusterId}/optionDetails/{detailId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> optionOptionClustersClusterIdOptionDetailsDetailIdDelete(@ApiParam(value = "选项类型的ID",required=true ) @PathVariable("clusterId") Long clusterId,@ApiParam(value = "选项的id",required=true ) @PathVariable("detailId") Long detailId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获得一个选择详细", notes = "", response = OptionDetail.class, tags={ "Option", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = OptionDetail.class) })
    
    @RequestMapping(value = "/option/optionClusters/{clusterId}/optionDetails/{detailId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<OptionDetail> optionOptionClustersClusterIdOptionDetailsDetailIdGet(@ApiParam(value = "选项类型的ID",required=true ) @PathVariable("clusterId") Long clusterId,@ApiParam(value = "选项的id",required=true ) @PathVariable("detailId") Long detailId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "修改选项", notes = "", response = Void.class, tags={ "Option", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/option/optionClusters/{clusterId}/optionDetails/{detailId}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Void> optionOptionClustersClusterIdOptionDetailsDetailIdPut(@ApiParam(value = "选项类型的ID",required=true ) @PathVariable("clusterId") Long clusterId,@ApiParam(value = "选项的id",required=true ) @PathVariable("detailId") Long detailId,@ApiParam(value = "optionDetail 选项详细信息，不用传id" ,required=true )  @Valid @RequestBody OptionDetail optionDetail, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取这个cluster下面的所有optionDetail", notes = "", response = OptionDetail.class, responseContainer = "List", tags={ "Option", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = OptionDetail.class) })
    
    @RequestMapping(value = "/option/optionClusters/{clusterId}/optionDetails",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<OptionDetail>> optionOptionClustersClusterIdOptionDetailsGet(@ApiParam(value = "选项类型的ID",required=true ) @PathVariable("clusterId") Long clusterId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取这个cluster下面的所有optionDetail", notes = "", response = Void.class, tags={ "Option", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/option/optionClusters/{clusterId}/optionDetails",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> optionOptionClustersClusterIdOptionDetailsPost(@ApiParam(value = "选项类型的ID",required=true ) @PathVariable("clusterId") Long clusterId,@ApiParam(value = "optionDetail 选项详细信息" ,required=true )  @Valid @RequestBody OptionDetail optionDetail, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "修改一个选项类型", notes = "", response = Void.class, tags={ "Option", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/option/optionClusters/{clusterId}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Void> optionOptionClustersClusterIdPut(@ApiParam(value = "新的option选项" ,required=true )  @Valid @RequestBody OptionCluster optionCluster,@ApiParam(value = "选项类型的ID",required=true ) @PathVariable("clusterId") Long clusterId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "删除Cluster和Worktype的关联", notes = "", response = Void.class, tags={ "Option", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/option/optionClusters/{clusterId}/workingTypes/{workingTypeId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> optionOptionClustersClusterIdWorkingTypesWorkingTypeIdDelete(@ApiParam(value = "选项类型的ID",required=true ) @PathVariable("clusterId") Long clusterId,@ApiParam(value = "选项类型的ID",required=true ) @PathVariable("workingTypeId") Long workingTypeId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取clusters对应的workingType", notes = "", response = Void.class, tags={ "Option", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/option/optionClusters/{clusterId}/workingTypes/{workingTypeId}",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> optionOptionClustersClusterIdWorkingTypesWorkingTypeIdPost(@ApiParam(value = "选项类型的ID",required=true ) @PathVariable("clusterId") Long clusterId,@ApiParam(value = "选项类型的ID",required=true ) @PathVariable("workingTypeId") Long workingTypeId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取所有的类型", notes = "", response = OptionCluster.class, responseContainer = "List", tags={ "Option", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = OptionCluster.class) })
    
    @RequestMapping(value = "/option/optionClusters",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<OptionCluster>> optionOptionClustersGet( HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "增加一个选项类型", notes = "", response = Void.class, tags={ "Option", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/option/optionClusters",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> optionOptionClustersPost(@ApiParam(value = "新的option选项" ,required=true )  @Valid @RequestBody OptionCluster optionCluster, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取所有的作业类型", notes = "", response = WorkingType.class, responseContainer = "List", tags={ "Option", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = WorkingType.class) })
    
    @RequestMapping(value = "/option/workingTypes",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<WorkingType>> optionWorkingTypesGet( HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "新增一个作业类型", notes = "", response = Void.class, tags={ "Option", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/option/workingTypes",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> optionWorkingTypesPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody WorkingType workType, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "删除一个工作类型", notes = "", response = Void.class, tags={ "Option", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/option/workingTypes/{workingTypeId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> optionWorkingTypesWorkingTypeIdDelete(@ApiParam(value = "",required=true ) @PathVariable("workingTypeId") Long workingTypeId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获得一个作业", notes = "", response = WorkingType.class, tags={ "Option", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = WorkingType.class) })
    
    @RequestMapping(value = "/option/workingTypes/{workingTypeId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<WorkingType> optionWorkingTypesWorkingTypeIdGet(@ApiParam(value = "",required=true ) @PathVariable("workingTypeId") Long workingTypeId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取workingType的所有OptionCluster", notes = "", response = OptionCluster.class, responseContainer = "List", tags={ "Option", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = OptionCluster.class) })
    
    @RequestMapping(value = "/option/workingTypes/{workingTypeId}/optionCluster",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<OptionCluster>> optionWorkingTypesWorkingTypeIdOptionClusterGet(@ApiParam(value = "工作类型Id",required=true ) @PathVariable("workingTypeId") Long workingTypeId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "新增一个作业类型", notes = "", response = Void.class, tags={ "Option", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/option/workingTypes/{workingTypeId}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Void> optionWorkingTypesWorkingTypeIdPut(@ApiParam(value = "" ,required=true )  @Valid @RequestBody WorkingType workType,@ApiParam(value = "",required=true ) @PathVariable("workingTypeId") Long workingTypeId, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
