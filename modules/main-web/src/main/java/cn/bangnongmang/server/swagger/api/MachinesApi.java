package cn.bangnongmang.server.swagger.api;

import cn.bangnongmang.server.swagger.model.MachineModel;
import cn.bangnongmang.server.swagger.model.MachineType;
import cn.bangnongmang.server.swagger.model.UserMachine;

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

@Api(value = "machines", description = "the machines API")
public interface MachinesApi {

    @ApiOperation(value = "获取所有的车辆型号", notes = "", response = MachineModel.class, tags={ "Machine", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = MachineModel.class) })
    
    @RequestMapping(value = "/machines/model/brand/{brandname}/num/{brandnum:.+}",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<MachineModel> machinesModelBrandBrandnameNumBrandnumGet(@ApiParam(value = "车辆品牌",required=true ) @PathVariable("brandname") String brandname,@ApiParam(value = "车辆品牌",required=true ) @PathVariable("brandnum") String brandnum, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取所有的车辆型号", notes = "", response = String.class, responseContainer = "List", tags={ "Machine", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = String.class) })
    
    @RequestMapping(value = "/machines/model/brand/{brandname}/num",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<List<String>> machinesModelBrandBrandnameNumGet(@ApiParam(value = "车辆品牌",required=true ) @PathVariable("brandname") String brandname, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取所有的车辆型号", notes = "", response = String.class, responseContainer = "List", tags={ "Machine", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = String.class) })
    
    @RequestMapping(value = "/machines/model/brand",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<List<String>> machinesModelBrandGet( @NotNull @ApiParam(value = "车辆品牌", required = true) @RequestParam(value = "brandname", required = true) String brandname, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取所有的车辆型号", notes = "", response = MachineModel.class, tags={ "Machine", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = MachineModel.class) })
    
    @RequestMapping(value = "/machines/model/brand/num",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<MachineModel> machinesModelBrandNumGet( @NotNull @ApiParam(value = "车辆品牌", required = true) @RequestParam(value = "brandname", required = true) String brandname, @NotNull @ApiParam(value = "车辆品牌", required = true) @RequestParam(value = "brandnum", required = true) String brandnum, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取所有车辆信息", notes = "", response = MachineType.class, responseContainer = "List", tags={ "Machine", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = MachineType.class) })
    
    @RequestMapping(value = "/machines/type",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<List<MachineType>> machinesTypeGet( HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取所有的车辆型号", notes = "", response = String.class, responseContainer = "List", tags={ "Machine", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = String.class) })
    
    @RequestMapping(value = "/machines/type/{typeId}/model/brand",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<List<String>> machinesTypeTypeIdModelBrandGet(@ApiParam(value = "车辆类型Id",required=true ) @PathVariable("typeId") Long typeId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取某个用户的机器列表", notes = "", response = UserMachine.class, responseContainer = "List", tags={ "Machine", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = UserMachine.class) })
    
    @RequestMapping(value = "/machines/{uid}",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<List<UserMachine>> machinesUidGet(@ApiParam(value = "用户的id",required=true ) @PathVariable("uid") Long uid, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
