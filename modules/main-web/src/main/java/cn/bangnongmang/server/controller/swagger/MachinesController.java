package cn.bangnongmang.server.controller.swagger;

import cn.bangnongmang.server.business.common.MachineBussiness;
import cn.bangnongmang.server.controller.swagger.base.ApiBaseConfig;
import cn.bangnongmang.server.io.swagger.show.MachineShow;
import cn.bangnongmang.server.swagger.api.MachinesApi;
import cn.bangnongmang.server.swagger.model.MachineModel;
import cn.bangnongmang.server.swagger.model.MachineType;
import cn.bangnongmang.server.swagger.model.UserMachine;
import cn.bangnongmang.server.swagger.model.UserMachineDetail;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by admin on 2017-04-17.
 */
@Controller
@RequestMapping(ApiBaseConfig.BASE_URL)
public class MachinesController implements MachinesApi {
    @Autowired
    private MachineShow machineShow;
    @Autowired
    private MachineBussiness machineBussiness;

    @Override
    public ResponseEntity<MachineModel> machinesModelBrandBrandnameNumBrandnumGet(
            @ApiParam(value = "车辆品牌",required=true ) @PathVariable("brandname") String brandname,
            @ApiParam(value = "车辆品牌",required=true ) @PathVariable("brandnum") String brandnum,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        String t=URLDecoder.decode(brandnum);
        MachineModel machineModel=machineShow.getMachineModelByBrandandNum(URLDecoder.decode(brandname,"utf-8"),URLDecoder.decode(brandnum,
                "utf-8"));
        if(machineModel!=null){
            return new ResponseEntity<MachineModel>(machineModel,HttpStatus.OK);
        }
        return new ResponseEntity<MachineModel>(HttpStatus.NOT_FOUND);

    }

    @Override
    public ResponseEntity<List<String>> machinesModelBrandBrandnameNumGet(
            @ApiParam(value = "车辆品牌",required=true ) @PathVariable("brandname") String brandname,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        return new ResponseEntity<List<String>>(machineShow.getAllNumbyBrandName(URLDecoder.decode(brandname,"utf-8")),
                HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<String>> machinesModelBrandGet(
            @NotNull @ApiParam(value = "车辆品牌", required = true) @RequestParam(value = "brandname", required = true) String brandname,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ResponseEntity<List<String>>(machineShow.getAllNumbyBrandName(URLDecoder.decode(brandname,"utf-8")),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MachineModel> machinesModelBrandNumGet(
            @NotNull @ApiParam(value = "车辆品牌", required = true) @RequestParam(value = "brandname", required = true) String brandname,
            @NotNull @ApiParam(value = "车辆品牌", required = true) @RequestParam(value = "brandnum", required = true) String brandnum,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        String t=URLDecoder.decode(brandnum);
        MachineModel machineModel=machineShow.getMachineModelByBrandandNum(URLDecoder.decode(brandname,"utf-8"),URLDecoder.decode(brandnum,
                "utf-8"));
        if(machineModel!=null){
            return new ResponseEntity<MachineModel>(machineModel,HttpStatus.OK);
        }
        return new ResponseEntity<MachineModel>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<MachineType>> machinesTypeGet(HttpServletRequest request, HttpServletResponse response) throws Exception {

        return new ResponseEntity<List<MachineType>>(machineShow.getAllWorkingType(),HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<String>> machinesTypeTypeIdModelBrandGet(
            @ApiParam(value = "车辆类型Id",required=true ) @PathVariable("typeId") Long typeId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {


        return new ResponseEntity<List<String>>(machineShow.getAllBrandbyTypeId(typeId),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserMachine>> machinesUidGet(
            @ApiParam(value = "对方的用户名", required = true) @PathVariable("uid") Long uid,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ResponseEntity<List<UserMachine>>(machineShow.getUserAuthMachines(uid),
                HttpStatus.OK);
    }


}
