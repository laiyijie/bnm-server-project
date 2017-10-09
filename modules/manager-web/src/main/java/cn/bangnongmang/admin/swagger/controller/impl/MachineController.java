package cn.bangnongmang.admin.swagger.controller.impl;

import cn.bangnongmang.admin.business.MachineBusiness;
import cn.bangnongmang.admin.business.UserMachineBusiness;
import cn.bangnongmang.admin.enums.UserMachineEnums;
import cn.bangnongmang.admin.io.swagger.show.MachineShow;
import cn.bangnongmang.admin.io.swagger.show.UserMachineShow;
import cn.bangnongmang.admin.swagger.api.MachineApi;
import cn.bangnongmang.admin.swagger.controller.base.BaseConf;
import cn.bangnongmang.admin.swagger.model.*;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.data.domain.OptionWorkingTypeMachineModelMappingKey;
import cn.bangnongmang.data.domain.UserMachine;
import cn.bangnongmang.service.service.MachineService;
import cn.bangnongmang.service.service.UserMachineService;
import io.swagger.annotations.ApiParam;
import org.json.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by lucq on 2017/5/22.
 */
@RequestMapping(BaseConf.BASE_URL)
@RestController
public class MachineController implements MachineApi {

	@Autowired
	private MachineBusiness machineBusiness;

	@Autowired
	private MachineShow machineShow;

	@Autowired
	private UserMachineShow userMachineShow;

	@Autowired
	private UserMachineBusiness userMachineBusiness;
	@Override
	public ResponseEntity<List<MachineModel>> machineMachineModelsGet(HttpServletRequest request, HttpServletResponse response) throws Exception {

		return new ResponseEntity<List<MachineModel>>(machineShow.getMachineModel(), HttpStatus.OK);

	}

	@Override
	public ResponseEntity<List<Option>> machineMachineModelsMachineModelIdOptionsGet(Long machineModelId, HttpServletRequest request,
																					 HttpServletResponse response) throws Exception {
		return null;
	}

	@Override
	public ResponseEntity<Void> machineMachineModelsMachineModelIdOptionsOptionIdDelete(
			@ApiParam(value = "选项类型的ID", required = true) @PathVariable("machineModelId") Long machineModelId, Long optionId,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		machineBusiness.deleteMachineModelById(machineModelId);

		return new ResponseEntity(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> machineMachineModelsMachineModelIdOptionsOptionIdPost(
			@ApiParam(value = "机器型号的id", required = true) @PathVariable("machineModelId") Long machineModelId,
			@ApiParam(value = "选项类型的ID", required = true) @PathVariable("optionId") Long optionId,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<WorkingType>> machineMachineModelsMachineModelIdWorkingTypesGet(
			@ApiParam(value = "机器型号的id", required = true) @PathVariable("machineModelId") Long machineModelId,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ResponseEntity<List<WorkingType>>(machineShow.getWorkingTypeByMachineModelId(machineModelId), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> machineMachineModelsMachineModelIdWorkingTypesWorkingTypeIdDelete(
			@ApiParam(value = "机器型号的id", required = true) @PathVariable("machineModelId") Long machineModelId,
			@ApiParam(value = "选项类型的ID", required = true) @PathVariable("workingTypeId") Long workingTypeId,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		machineBusiness.deleteMachineModelWorkingTypeRelation(machineModelId, workingTypeId);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@Override
	public ResponseEntity<Void> machineMachineModelsMachineModelIdWorkingTypesWorkingTypeIdPost(
			@ApiParam(value = "机器型号的id", required = true) @PathVariable("machineModelId") Long machineModelId,
			@ApiParam(value = "选项类型的ID", required = true) @PathVariable("workingTypeId") Long workingTypeId,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		OptionWorkingTypeMachineModelMappingKey owtmmmk = new OptionWorkingTypeMachineModelMappingKey();
		owtmmmk.setMachine_model_id(machineModelId);
		owtmmmk.setOption_working_type_id(workingTypeId);
		machineBusiness.saveMachineModelWorkingTypeRelation(owtmmmk);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> machineMachineModelsModelIdDelete(
			@ApiParam(value = "", required = true) @PathVariable("modelId") Long modelId,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		machineBusiness.deleteMachineModelById(modelId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<MachineModel> machineMachineModelsModelIdGet(
			@ApiParam(value = "", required = true) @PathVariable("modelId") Long modelId,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		MachineModel machineModel = machineShow.getMachineModelById(modelId);
		if (machineModel == null)
			return new ResponseEntity<MachineModel>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<MachineModel>(machineModel, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> machineMachineModelsModelIdPut(
			@ApiParam(value = "", required = true) @PathVariable("modelId") Long modelId,
			@ApiParam(value = "", required = true) @Valid @RequestBody MachineModel machineModel,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		cn.bangnongmang.data.domain.UserMachineModel userMachineModel = new cn.bangnongmang.data.domain.UserMachineModel();
		userMachineModel.setId(modelId);
		userMachineModel.setModel_brand(machineModel.getBrand());
		userMachineModel.setModel_power(machineModel.getPower());
		//userMachineModel.setModel_width(machineModel.getWidth());
		//userMachineModel.setModel_number(machineModel.getModel());
		//userMachineModel.setState(machineModel.getState());
		userMachineModel.setUser_machine_type_id(machineModel.getMachineType()
															 .getId());
		machineBusiness.modifyMachineModel(userMachineModel);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> machineMachineModelsPost(
			@ApiParam(value = "", required = true) @Valid @RequestBody MachineModel machineModel,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		cn.bangnongmang.data.domain.UserMachineModel userMachineModel = new cn.bangnongmang.data.domain.UserMachineModel();

		userMachineModel.setModel_brand(machineModel.getBrand());
		userMachineModel.setModel_power(machineModel.getPower());
		//userMachineModel.setModel_width(machineModel.getWidth());
		//userMachineModel.setModel_number(machineModel.getModel());
		//userMachineModel.setState(machineModel.getState());
		userMachineModel.setUser_machine_type_id(machineModel.getMachineType()
															 .getId());
		machineBusiness.saveMachineModel(userMachineModel);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<MachineType>> machineMachineTypesGet(HttpServletRequest request, HttpServletResponse response) throws Exception {

		return new ResponseEntity<List<MachineType>>(machineShow.getMachineType(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> machineMachineTypesMachineTypeIdDelete(
			@ApiParam(value = "机器类型的ID", required = true) @PathVariable("machineTypeId") Long machineTypeId,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		machineBusiness.deleteMachineTypeById(machineTypeId);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@Override
	public ResponseEntity<MachineType> machineMachineTypesMachineTypeIdGet(
			@ApiParam(value = "机器类型的ID", required = true) @PathVariable("machineTypeId") Long machineTypeId,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		MachineType machineType = machineShow.getMachineTypeById(machineTypeId);

		if (machineType == null) {
			return new ResponseEntity<MachineType>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<MachineType>(machineType, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> machineMachineTypesMachineTypeIdPut(
			@ApiParam(value = "机器类型的ID", required = true) @PathVariable("machineTypeId") Long machineTypeId,
			@ApiParam(value = "here the id is invalid", required = true) @Valid @RequestBody MachineType machineType,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		cn.bangnongmang.data.domain.UserMachineType userMachineType = new cn.bangnongmang.data.domain.UserMachineType();
		userMachineType.setId(machineTypeId);
		userMachineType.setType_name(machineType.getName());
		userMachineType.setDescripetion(machineType.getDescription());
		machineBusiness.modifyMachineType(userMachineType);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> machineMachineTypesPost(
			@ApiParam(value = "here the id is invalid", required = true) @Valid @RequestBody MachineType machineType,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		cn.bangnongmang.data.domain.UserMachineType userMachineType = new cn.bangnongmang.data.domain.UserMachineType();

		userMachineType.setType_name(machineType.getName());
		userMachineType.setDescripetion(machineType.getDescription());
		machineBusiness.saveMachineType(userMachineType);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> machineUserMachinesAuthsUserMachineIdStatusAcceptPost(
			@ApiParam(value = "车辆id",required=true ) @PathVariable("userMachineId") Long userMachineId,
			@ApiParam(value = "购买日期", required=true) @RequestParam(value="buyDate", required=true)  Long buyDate,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserMachine userMachine=new UserMachine();
		userMachine.setId(userMachineId);
		userMachine.setState(UserMachineEnums.STATE_AUTH_PASSED.getCurrencyCode());
		userMachine.setBuy_time(buyDate.longValue()/1000);
		userMachineBusiness.modifyUserMachine(userMachine);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> machineUserMachinesAuthsUserMachineIdStatusDenyPost(
			@ApiParam(value = "车辆id",required=true ) @PathVariable("userMachineId")  Long userMachineId,
			@ApiParam(value = "失败理由", required=true) @RequestParam(value="reason", required=true)   String reason,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserMachine userMachine=new UserMachine();
		userMachine.setId(userMachineId);
		userMachine.setState(UserMachineEnums.STATE_AUTH_FAILED.getCurrencyCode());
		userMachine.setFailed_reason(reason);
		userMachineBusiness.modifyUserMachine(userMachine);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<UserMachineBasic>> machineUserMachinesGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ResponseEntity<List<UserMachineBasic>>(userMachineShow.getAllUserMachine(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<UserMachineDetail>> machineUserMachinesGetByuidUidGet(
			@ApiParam(value = "用户id",required=true ) @PathVariable("uid") Long uid,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		return new ResponseEntity<List<UserMachineDetail>>(userMachineShow.getAllUserMachineByUid(uid), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> machineUserMachinesUserMachineIdPut(
			@ApiParam(value = "车辆的id",required=true ) @PathVariable("userMachineId") Long userMachineId,
			@ApiParam(value = "" ,required=true )  @Valid @RequestBody UserMachineDetail userMachine,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		userMachine.setId(userMachineId);
		userMachineBusiness.modifyUserMachineDetail(userMachine);


		return new ResponseEntity<Void>(HttpStatus.OK);
	}


	@Override
	public ResponseEntity<UserMachineDetail> machineUserMachinesUserMachineIdGet(
			@ApiParam(value = "车辆的id", required = true) @PathVariable("userMachineId") Long userMachineId,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		UserMachineDetail umb = userMachineShow.getUserMachineById(userMachineId);
		if (umb == null) {
			return new ResponseEntity<UserMachineDetail>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<UserMachineDetail>(umb, HttpStatus.OK);
	}

}
