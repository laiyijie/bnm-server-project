package cn.bangnongmang.server.swagger.api;

import cn.bangnongmang.server.swagger.model.GrabSeason;

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

@Api(value = "grabseasons", description = "the grabseasons API")
public interface GrabseasonsApi {

    @ApiOperation(value = "获取后续所有有效的抢单场次", notes = "", response = GrabSeason.class, responseContainer = "List", tags={ "GrabSeason", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = GrabSeason.class) })
    
    @RequestMapping(value = "/grabseasons/status/available",
        produces = { "application/json" }, 
        consumes = { "application/json", "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<List<GrabSeason>> grabseasonsStatusAvailableGet( HttpServletRequest request, HttpServletResponse response) throws Exception;

}
