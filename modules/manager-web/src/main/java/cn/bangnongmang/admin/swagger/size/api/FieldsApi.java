package cn.bangnongmang.admin.swagger.size.api;

import cn.bangnongmang.size.io.swagger.model.Field;
import cn.bangnongmang.size.io.swagger.model.FieldIdWrapper;
import cn.bangnongmang.size.io.swagger.model.Polygon;
import cn.bangnongmang.size.io.swagger.model.UpdateField;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Api(value = "fields", description = "the fields API")
public interface FieldsApi {

    @ApiOperation(value = "", notes = "根据id查看某块地 ", response = Field.class, tags={  })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "ok", response = Field.class),
        @ApiResponse(code = 403, message = "error", response = Field.class) })
    
    @RequestMapping(value = "/fields/fieldid/{fieldId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Field> fieldsFieldidFieldIdGet(@ApiParam(value = "", required = true) @PathVariable("fieldId") String fieldId) throws Exception;


    @ApiOperation(value = "删除田块", notes = "根据id删除田块", response = Void.class, tags={  })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = Void.class),
        @ApiResponse(code = 403, message = "error", response = Void.class) })
    
    @RequestMapping(value = "/fields/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> fieldsIdDelete(@ApiParam(value = "", required = true) @PathVariable("id") Integer id) throws Exception;


    @ApiOperation(value = "", notes = "根据id查看某块地 ", response = Field.class, tags={  })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "ok", response = Field.class),
        @ApiResponse(code = 403, message = "error", response = Field.class) })
    
    @RequestMapping(value = "/fields/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Field> fieldsIdGet(@ApiParam(value = "", required = true) @PathVariable("id") Integer id) throws Exception;


    @ApiOperation(value = "", notes = "修改田的信息 ", response = Void.class, tags={  })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "ok", response = Void.class),
        @ApiResponse(code = 403, message = "error", response = Void.class) })
    
    @RequestMapping(value = "/fields/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Void> fieldsIdPut(@ApiParam(value = "", required = true) @PathVariable("id") Integer id,
                                     @ApiParam(value = "田地的信息，不需要传入id ", required = true) @RequestBody Field field) throws Exception;


    @ApiOperation(value = "新增田", notes = "新增田", response = String.class, tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = String.class) })

    @RequestMapping(value = "/fields",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<FieldIdWrapper> fieldsPost(@ApiParam(value = "传入省、市和外边框信息 " ,required=true ) @RequestBody UpdateField data) throws Exception;


    @ApiOperation(value = "", notes = "获取当前视野范围内的田块 ", response = Field.class, responseContainer = "List", tags={  })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "ok", response = Field.class) })
    
    @RequestMapping(value = "/fields/view",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<List<Field>> fieldsViewPost(@ApiParam(value = "当前视野范围 ", required = true) @RequestBody Polygon viewPolygon) throws Exception;

}
