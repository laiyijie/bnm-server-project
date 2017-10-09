package cn.bangnongmang.admin.swagger.size.api;



import cn.bangnongmang.size.io.swagger.model.OrderArea;
import cn.bangnongmang.size.io.swagger.model.OrderField;
import cn.bangnongmang.size.io.swagger.model.OrderMarker;
import cn.bangnongmang.size.io.swagger.model.Polygon;
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

import java.util.List;
import javax.validation.constraints.*;

@Api(value = "market", description = "the market API")
public interface MarketApi {

    @ApiOperation(value = "", notes = "获取订单区域所对应的田块 ", response = OrderMarker.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = OrderMarker.class) })
    
    @RequestMapping(value = "/market/{orderid}/{areaid}/marks",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<OrderMarker>> marketOrderidAreaidMarksGet(@ApiParam(value = "",required=true ) @PathVariable("orderid") String orderid,@ApiParam(value = "",required=true ) @PathVariable("areaid") Integer areaid) throws Exception;


    @ApiOperation(value = "", notes = "将一个新的田块添加到订单区域内 ", response = Void.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/market/{orderid}/{areaid}/marks",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> marketOrderidAreaidMarksPost(@ApiParam(value = "",required=true ) @PathVariable("orderid") String orderid,@ApiParam(value = "",required=true ) @PathVariable("areaid") Integer areaid,@ApiParam(value = "" ,required=true ) @RequestBody String fieldId) throws Exception;


    @ApiOperation(value = "", notes = "更新一个区域的田块标记信息 ", response = Void.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/market/{orderid}/{areaid}/marks",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Void> marketOrderidAreaidMarksPut(@ApiParam(value = "",required=true ) @PathVariable("orderid") String orderid,@ApiParam(value = "",required=true ) @PathVariable("areaid") Integer areaid,@ApiParam(value = "" ,required=true ) @RequestBody OrderMarker orderMarker) throws Exception;


    @ApiOperation(value = "", notes = "获取市场标记的订单区域 ", response = OrderArea.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = OrderArea.class) })
    
    @RequestMapping(value = "/market/{orderid}/areas",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<OrderArea>> marketOrderidAreasGet(@ApiParam(value = "",required=true ) @PathVariable("orderid") String orderid) throws Exception;


    @ApiOperation(value = "", notes = "添加一个市场标记区域 ", response = Void.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/market/{orderid}/areas",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> marketOrderidAreasPost(@ApiParam(value = "",required=true ) @PathVariable("orderid") String orderid,@ApiParam(value = "" ,required=true ) @RequestBody Polygon data) throws Exception;


    @ApiOperation(value = "", notes = "修改一个市场标记区域 ", response = Void.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/market/{orderid}/areas",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Void> marketOrderidAreasPut(@ApiParam(value = "",required=true ) @PathVariable("orderid") String orderid,@ApiParam(value = "" ,required=true ) @RequestBody OrderArea data) throws Exception;


    @ApiOperation(value = "", notes = "获取订单所对应的田块 ", response = OrderMarker.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = OrderMarker.class) })
    
    @RequestMapping(value = "/market/{orderid}/marks",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<OrderMarker>> marketOrderidMarksGet(@ApiParam(value = "",required=true ) @PathVariable("orderid") String orderid) throws Exception;


    @ApiOperation(value = "", notes = "删除一个田块和订单的关联 ", response = Void.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/market/{orderid}/orderField/{fieldId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> marketOrderidOrderFieldFieldIdDelete(@ApiParam(value = "",required=true ) @PathVariable("orderid") String orderid,@ApiParam(value = "",required=true ) @PathVariable("fieldId") String fieldId) throws Exception;


    @ApiOperation(value = "", notes = "获取订单所对应的田块列表 ", response = OrderField.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = OrderField.class) })
    
    @RequestMapping(value = "/market/{orderid}/orderField",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<OrderField>> marketOrderidOrderFieldGet(@ApiParam(value = "",required=true ) @PathVariable("orderid") String orderid) throws Exception;


    @ApiOperation(value = "", notes = "将一个田块关联到订单 ", response = Void.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/market/{orderid}/orderField",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> marketOrderidOrderFieldPost(@ApiParam(value = "",required=true ) @PathVariable("orderid") String orderid,@ApiParam(value = "" ,required=true ) @RequestBody String fieldId) throws Exception;

}
