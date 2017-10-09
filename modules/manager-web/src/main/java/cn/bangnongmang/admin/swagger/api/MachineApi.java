package cn.bangnongmang.admin.swagger.api;

import cn.bangnongmang.admin.swagger.model.MachineModel;
import cn.bangnongmang.admin.swagger.model.MachineType;
import cn.bangnongmang.admin.swagger.model.Option;
import cn.bangnongmang.admin.swagger.model.UserMachineBasic;
import cn.bangnongmang.admin.swagger.model.UserMachineDetail;
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

@Api(value = "machine", description = "the machine API")
public interface MachineApi {

    @ApiOperation(value = "获取所有的车辆模型", notes = "", response = MachineModel.class, responseContainer = "List", tags={ "MachineManage", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = MachineModel.class) })
    
    @RequestMapping(value = "/machine/machineModels",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<MachineModel>> machineMachineModelsGet( HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取机器型号对应的选项", notes = "", response = Option.class, responseContainer = "List", tags={ "MachineManage", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Option.class) })
    
    @RequestMapping(value = "/machine/machineModels/{machineModelId}/options",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Option>> machineMachineModelsMachineModelIdOptionsGet(@ApiParam(value = "机器型号的id",required=true ) @PathVariable("machineModelId") Long machineModelId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "删除机器的选项", notes = "", response = Void.class, tags={ "MachineManage", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/machine/machineModels/{machineModelId}/options/{optionId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> machineMachineModelsMachineModelIdOptionsOptionIdDelete(@ApiParam(value = "选项类型的ID",required=true ) @PathVariable("machineModelId") Long machineModelId,@ApiParam(value = "选项类型的ID",required=true ) @PathVariable("optionId") Long optionId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "增加机器的选项", notes = "", response = Void.class, tags={ "MachineManage", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/machine/machineModels/{machineModelId}/options/{optionId}",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> machineMachineModelsMachineModelIdOptionsOptionIdPost(@ApiParam(value = "机器型号的id",required=true ) @PathVariable("machineModelId") Long machineModelId,@ApiParam(value = "选项类型的ID",required=true ) @PathVariable("optionId") Long optionId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取车辆型号对应的可以作业的种类", notes = "", response = WorkingType.class, responseContainer = "List", tags={ "MachineManage", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = WorkingType.class) })
    
    @RequestMapping(value = "/machine/machineModels/{machineModelId}/workingTypes",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<WorkingType>> machineMachineModelsMachineModelIdWorkingTypesGet(@ApiParam(value = "机器型号的id",required=true ) @PathVariable("machineModelId") Long machineModelId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "删除车辆型号对应的可以作业的种类", notes = "", response = Void.class, tags={ "MachineManage", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/machine/machineModels/{machineModelId}/workingTypes/{workingTypeId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> machineMachineModelsMachineModelIdWorkingTypesWorkingTypeIdDelete(@ApiParam(value = "机器型号的id",required=true ) @PathVariable("machineModelId") Long machineModelId,@ApiParam(value = "选项类型的ID",required=true ) @PathVariable("workingTypeId") Long workingTypeId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "增加车辆型号对应的可以作业的种类", notes = "", response = Void.class, tags={ "MachineManage", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/machine/machineModels/{machineModelId}/workingTypes/{workingTypeId}",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> machineMachineModelsMachineModelIdWorkingTypesWorkingTypeIdPost(@ApiParam(value = "机器型号的id",required=true ) @PathVariable("machineModelId") Long machineModelId,@ApiParam(value = "选项类型的ID",required=true ) @PathVariable("workingTypeId") Long workingTypeId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "删除一个车辆模型", notes = "", response = Void.class, tags={ "MachineManage", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/machine/machineModels/{modelId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> machineMachineModelsModelIdDelete(@ApiParam(value = "",required=true ) @PathVariable("modelId") Long modelId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取某车型的信息", notes = "", response = MachineModel.class, tags={ "MachineManage", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = MachineModel.class) })
    
    @RequestMapping(value = "/machine/machineModels/{modelId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<MachineModel> machineMachineModelsModelIdGet(@ApiParam(value = "",required=true ) @PathVariable("modelId") Long modelId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "modify the car's info", notes = "", response = Void.class, tags={ "MachineManage", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/machine/machineModels/{modelId}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Void> machineMachineModelsModelIdPut(@ApiParam(value = "",required=true ) @PathVariable("modelId") Long modelId,@ApiParam(value = "here the id is invalid" ,required=true )  @Valid @RequestBody MachineModel machineType, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "增加一个车辆模型", notes = "", response = Void.class, tags={ "MachineManage", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/machine/machineModels",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> machineMachineModelsPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody MachineModel machineModel, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取所有的车辆类型", notes = "", response = MachineType.class, responseContainer = "List", tags={ "MachineManage", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = MachineType.class) })
    
    @RequestMapping(value = "/machine/machineTypes",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<MachineType>> machineMachineTypesGet( HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "删除一个车辆模型", notes = "", response = Void.class, tags={ "MachineManage", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/machine/machineTypes/{machineTypeId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> machineMachineTypesMachineTypeIdDelete(@ApiParam(value = "机器类型的ID",required=true ) @PathVariable("machineTypeId") Long machineTypeId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获得一个车辆模型", notes = "", response = MachineType.class, tags={ "MachineManage", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = MachineType.class) })
    
    @RequestMapping(value = "/machine/machineTypes/{machineTypeId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<MachineType> machineMachineTypesMachineTypeIdGet(@ApiParam(value = "机器类型的ID",required=true ) @PathVariable("machineTypeId") Long machineTypeId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "修改车辆类型", notes = "", response = Void.class, tags={ "MachineManage", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/machine/machineTypes/{machineTypeId}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Void> machineMachineTypesMachineTypeIdPut(@ApiParam(value = "机器类型的ID",required=true ) @PathVariable("machineTypeId") Long machineTypeId,@ApiParam(value = "here the id is invalid" ,required=true )  @Valid @RequestBody MachineType machineModel, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "增加车辆类型", notes = "", response = Void.class, tags={ "MachineManage", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/machine/machineTypes",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> machineMachineTypesPost(@ApiParam(value = "here the id is invalid" ,required=true )  @Valid @RequestBody MachineType machineType, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "更改认证状态", notes = "", response = Void.class, tags={ "UserMachine", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/machine/userMachines/auths/{userMachineId}/status/accept",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> machineUserMachinesAuthsUserMachineIdStatusAcceptPost(@ApiParam(value = "车辆的id",required=true ) @PathVariable("userMachineId") Long userMachineId,@ApiParam(value = "购买日期", required=true) @RequestParam(value="buyDate", required=true)  Long buyDate, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "拒绝认证", notes = "", response = Void.class, tags={ "UserMachine", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/machine/userMachines/auths/{userMachineId}/status/deny",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> machineUserMachinesAuthsUserMachineIdStatusDenyPost(@ApiParam(value = "车辆id",required=true ) @PathVariable("userMachineId") Long userMachineId,@ApiParam(value = "失败理由", required=true) @RequestParam(value="reason", required=true)  String reason, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "用户车辆认证列表", notes = "", response = UserMachineBasic.class, responseContainer = "List", tags={ "UserMachine", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = UserMachineBasic.class) })
    
    @RequestMapping(value = "/machine/userMachines",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<UserMachineBasic>> machineUserMachinesGet( HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取该用户下的所有车辆的详细信息", notes = "", response = UserMachineDetail.class, responseContainer = "List", tags={ "UserMachine", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = UserMachineDetail.class) })
    
    @RequestMapping(value = "/machine/userMachines/getByuid/{uid}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<UserMachineDetail>> machineUserMachinesGetByuidUidGet(@ApiParam(value = "用户id",required=true ) @PathVariable("uid") Long uid, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取车辆认证的详细信息", notes = "", response = UserMachineDetail.class, tags={ "UserMachine", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = UserMachineDetail.class) })
    
    @RequestMapping(value = "/machine/userMachines/{userMachineId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<UserMachineDetail> machineUserMachinesUserMachineIdGet(@ApiParam(value = "车辆的id",required=true ) @PathVariable("userMachineId") Long userMachineId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "修改认证", notes = "", response = Void.class, tags={ "UserMachine", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/machine/userMachines/{userMachineId}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Void> machineUserMachinesUserMachineIdPut(@ApiParam(value = "车辆的id",required=true ) @PathVariable("userMachineId") Long userMachineId,@ApiParam(value = "" ,required=true )  @Valid @RequestBody UserMachineDetail userMachine, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
