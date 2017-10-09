package cn.bangnongmang.server.business.common.impl;

import cn.bangnongmang.data.domain.OptionDetail;
import cn.bangnongmang.data.domain.UserMachine;
import cn.bangnongmang.server.business.common.MachineBussiness;
import cn.bangnongmang.server.io.BusinessException;
import cn.bangnongmang.server.swagger.model.MachineProperty;
import cn.bangnongmang.server.swagger.model.UserMachineDetail;
import cn.bangnongmang.service.outerservice.AliOssService;
import cn.bangnongmang.service.service.MachineService;
import cn.bangnongmang.service.service.UserMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by lucq on 2017/6/9.
 */
@Service("machineBussiness")
public class MachineBusinessImp implements MachineBussiness{
    @Autowired
	public MachineService machineService;
    @Autowired
	public UserMachineService userMachineService;
    @Autowired
	public AliOssService aliOssService;
	@Override
	public Boolean saveMachine(UserMachineDetail userMachineDetail) {

		UserMachine userMachine=new UserMachine();
		userMachine.setId(this.generateMachineId());
		userMachine.setState(UserMachineService.STATE_WAITTING_AUTH);
		userMachine.setIntegrity(1);
		userMachine.setUid(userMachineDetail.getUid());
		userMachine.setMachine_model_id(userMachineDetail.getModelId());

		Iterator it = userMachineDetail.getMachineproperties().iterator();
		List<OptionDetail> detailList= machineService.getAllDetialByMachineModelId(userMachineDetail.getModelId());

		while(it.hasNext()){
			MachineProperty machineProperty= (MachineProperty) it.next();
			if(machineProperty.getOptionCluster().getId().equals(new Long(0))){
				if(machineProperty.getOptionDetail().getName().isEmpty()){
					throw new BusinessException("割幅不能为空");
				}
				Double width=null;
				try {
					width=Double.valueOf(machineProperty.getOptionDetail().getName());
				}catch (NumberFormatException  e){
					throw new BusinessException("请正确填写割幅");
				}
				userMachine.setWidth(width);
				continue;
			}

			machineService.deleteOptionDetailByMachineId(userMachine.getId());

			Iterator iterator= detailList.iterator();
			while(iterator.hasNext()){
				cn.bangnongmang.data.domain.OptionDetail optionDetail= (cn.bangnongmang.data.domain.OptionDetail) iterator.next();
				if(optionDetail.getCluster_id().equals(machineProperty.getOptionCluster().getId())){
					if(!machineProperty.getOptionDetail().getId().equals(optionDetail.getId())){
						if(!machineService.saveMachineandOptionDetialRelation(userMachine.getId(),machineProperty.getOptionDetail().getId())){
							throw new BusinessException("添加失败");
						}
					}
				}
			}
		}

		userMachineService.saveUserMachine(userMachine);

		saveImages(userMachine.getId(),userMachineDetail.getAuthImage());

		return true;

	}

	@Override
	public Boolean modifyMachine(UserMachineDetail userMachineDetail) {

		UserMachine userMachine=new UserMachine();
		userMachine.setUid(userMachineDetail.getUid());
		userMachine.setId(userMachineDetail.getId());
		userMachine.setState(UserMachineService.STATE_WAITTING_AUTH);
		userMachine.setIntegrity(1);
		userMachine.setMachine_model_id(userMachineDetail.getModelId());

		Iterator it = userMachineDetail.getMachineproperties().iterator();

		List<cn.bangnongmang.data.domain.OptionDetail> detailList= machineService.getAllDetialByMachineModelId(userMachineDetail.getModelId());
		machineService.deleteOptionDetailByMachineId(userMachine.getId());
		while(it.hasNext()){
			MachineProperty machineProperty= (MachineProperty) it.next();
			if(machineProperty.getOptionCluster().getId().equals(new Long(0))){
				if(machineProperty.getOptionDetail().getName().isEmpty()){
					throw  new BusinessException("车宽不能为空");
				}else{
					Double width=null;
					try {
						width=Double.valueOf(machineProperty.getOptionDetail().getName());
					}catch (NumberFormatException  e){
						throw new BusinessException("请正确填写割幅");
					}
					userMachine.setWidth(width);
				}
				continue;
			}


			Iterator iterator= detailList.iterator();
			while(iterator.hasNext()){
				cn.bangnongmang.data.domain.OptionDetail optionDetail= (cn.bangnongmang.data.domain.OptionDetail) iterator.next();
				if(optionDetail.getCluster_id().equals(machineProperty.getOptionCluster().getId())){
					if(!machineProperty.getOptionDetail().getId().equals(optionDetail.getId())){
						if(!machineService.saveMachineandOptionDetialRelation(userMachine.getId(),machineProperty.getOptionDetail().getId())){
							throw new BusinessException("修改失败");
						}
					}
				}
			}
		}

		userMachineService.modifyUserMachine(userMachine);
		saveImages(userMachine.getId(),userMachineDetail.getAuthImage());
		return true;

	}

	private Long generateMachineId() {

		Integer random = new Random().nextInt(1000);
		return (System.currentTimeMillis() * 1000) + random;

	}

	private Boolean saveImages(Long machineId,List<String> imageList){


		userMachineService.deleteUserMachineAuthImage(machineId);
		Iterator it=imageList.iterator();
		while(it.hasNext()){
			if(!userMachineService.addUserMachineImage(machineId, "车照片",it.next().toString())){
				throw new BusinessException("上传图片失败，请稍候再试");
			}
		}
		return true;
	}
}
