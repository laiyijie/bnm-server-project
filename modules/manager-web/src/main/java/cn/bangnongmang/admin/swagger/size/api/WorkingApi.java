package cn.bangnongmang.admin.swagger.size.api;

import cn.bangnongmang.size.io.swagger.model.SizeCounterSetting;
import cn.bangnongmang.size.io.swagger.model.UserPoint;
import cn.bangnongmang.size.io.swagger.model.WorkingOrderStatus;
import cn.bangnongmang.size.io.swagger.model.WorkingSizeHistory;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Api(value = "working", description = "the working API")
public interface WorkingApi {

    @ApiOperation(value = "", notes = "获取从上次开始作业到当前的作业历史 ", response = WorkingSizeHistory.class, responseContainer = "List", tags={  })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "ok", response = WorkingSizeHistory.class) })
    
    @RequestMapping(value = "/working/history/current/{orderid}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<WorkingSizeHistory>> workingHistoryCurrentOrderidGet(
            @ApiParam(value = "", required = true) @PathVariable("orderid") String orderid) throws Exception;


    @ApiOperation(value = "", notes = "获取测亩历史 ", response = WorkingSizeHistory.class, responseContainer = "List", tags={  })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "ok", response = WorkingSizeHistory.class) })
    
    @RequestMapping(value = "/working/history/orderid/{orderid}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<WorkingSizeHistory>> workingHistoryOrderidOrderidGet(
            @ApiParam(value = "", required = true) @PathVariable("orderid") String orderid) throws Exception;


    @ApiOperation(value = "", notes = "获取测亩历史 ", response = WorkingSizeHistory.class, responseContainer = "List", tags={  })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "ok", response = WorkingSizeHistory.class) })
    
    @RequestMapping(value = "/working/history/outerindex/{outerindex}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<WorkingSizeHistory>> workingHistoryOuterindexOuterindexGet(
            @ApiParam(value = "", required = true) @PathVariable("outerindex") String outerindex) throws Exception;


    @ApiOperation(value = "", notes = "订单状态修改 ", response = WorkingSizeHistory.class, responseContainer = "List", tags={  })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = WorkingSizeHistory.class) })
    
    @RequestMapping(value = "/working/{orderid}/{outerindex}/status",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<List<WorkingSizeHistory>> workingOrderidOuterindexStatusPut(
            @ApiParam(value = "", required = true) @PathVariable("orderid") String orderid,
            @ApiParam(value = "", required = true) @PathVariable("outerindex") String outerindex,
            @ApiParam(value = "修改订单状态，当完成单日作业时，为stop，完成全部作业之后为finish ", required = true) @RequestBody WorkingOrderStatus status) throws Exception;


    @ApiOperation(value = "工作的时候新增点", notes = "工作的时候新增点", response = WorkingSizeHistory.class, responseContainer = "List", tags={  })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = WorkingSizeHistory.class),
        @ApiResponse(code = 403, message = "not login", response = WorkingSizeHistory.class) })
    
    @RequestMapping(value = "/working/{orderid}/points",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<List<WorkingSizeHistory>> workingOrderidPointsPost(@ApiParam(value = "", required = true) @PathVariable("orderid") String orderid,
                                                                      @ApiParam(value = "") @RequestBody List<UserPoint> point) throws Exception;


    @ApiOperation(value = "", notes = "订单设置 ", response = Void.class, tags={  })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/working/{orderid}/settings",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> workingOrderidSettingsPost(@ApiParam(value = "", required = true) @PathVariable("orderid") String orderid,
                                                    @ApiParam(value = "", required = true) @RequestBody SizeCounterSetting settings) throws Exception;

}
