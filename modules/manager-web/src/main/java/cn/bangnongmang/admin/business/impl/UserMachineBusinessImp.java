package cn.bangnongmang.admin.business.impl;

import cn.bangnongmang.admin.business.UserMachineBusiness;
import cn.bangnongmang.admin.swagger.model.MachineProperty;
import cn.bangnongmang.admin.swagger.model.UserMachineDetail;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.data.domain.UserMachine;
import cn.bangnongmang.service.outerservice.AliOssService;
import cn.bangnongmang.service.service.MachineService;
import cn.bangnongmang.service.service.UserMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by lucq on 2017/5/26.
 */
@Service("userMachineBusiness")
public class UserMachineBusinessImp implements UserMachineBusiness {

	@Autowired
	private UserMachineService userMachineService;
	@Autowired
	private MachineService machineService;

	@Autowired
	private AliOssService aliOssService;

	@Override
	public Boolean modifyUserMachine(UserMachine userMachine) {

		if(userMachine.getBuy_time()==null){
			throw  new BusinessException("购买日期不能为空");
		}
		if(!userMachineService.modifyUserMachine(userMachine)){

			throw new BusinessException("认证状态修改失败,请稍候再试");
		}

		return true;
	}

	@Override
	public Boolean modifyUserMachineDetail(UserMachineDetail userMachineDetail) {
		UserMachine userMachine=new UserMachine();
		userMachine.setId(userMachineDetail.getId());
		if(userMachineDetail.getBuytime()==null){
			throw  new BusinessException("购买日期不能为空");
		}
		userMachine.setBuy_time(userMachineDetail.getBuytime());

		userMachine.setIntegrity(1);

		machineService.deleteOptionDetailByMachineId(userMachine.getId());

		List<cn.bangnongmang.data.domain.OptionDetail> detailList= machineService.getAllDetialByMachineModelId(userMachineDetail.getModelId());
		Iterator it = userMachineDetail.getMachineproperties().iterator();
		while(it.hasNext()){
			MachineProperty machineProperty= (MachineProperty) it.next();
			if(machineProperty.getOptionCluster().getId().equals(new Long(0))){
				String width=machineProperty.getOptionDetail().getName();
				if(width.isEmpty()){
					throw  new BusinessException("车宽不能为空");
				}else{
					userMachine.setWidth(Double.parseDouble(width));
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

		return true;
	}

	@Override
	public Boolean saveUserMachineDetail(UserMachineDetail userMachineDetail) {

		UserMachine userMachine=new UserMachine();
		userMachine.setId(this.generateMachineId());
		userMachine.setBuy_time(userMachineDetail.getBuytime());
		userMachine.setState(UserMachineService.STATE_WAITTING_AUTH);
		userMachine.setIntegrity(0);
		userMachine.setUid(userMachineDetail.getUid());
		userMachine.setMachine_model_id(userMachineDetail.getModelId());

		Iterator it = userMachineDetail.getMachineproperties().iterator();
		while(it.hasNext()){
			MachineProperty machineProperty= (MachineProperty) it.next();
			if(machineProperty.getOptionCluster().getId().equals(new Long(0))){
				userMachine.setWidth(Double.parseDouble(machineProperty.getOptionDetail().getName()));
				continue;
			}

			machineService.deleteOptionDetailByMachineId(userMachine.getId());
			List<cn.bangnongmang.data.domain.OptionDetail> detailList= machineService.getAllDetialByMachineModelId(userMachineDetail.getModelId());
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

		Iterator itImage=userMachineDetail.getAuthImage().iterator();
		while(it.hasNext()){
			if(!userMachineService.addUserMachineImage(userMachine.getId(), "车照片",
					aliOssService.getAuthPutUrlFolderByUsername(userMachine.getUid()) + it.next().toString())){
				throw new BusinessException("上传图片失败，请稍候再试");
			}
		}

		userMachineService.saveUserMachine(userMachine);

		return true;
	}

	private Long generateMachineId() {

		Integer random = new Random().nextInt(1000);
		return (System.currentTimeMillis() * 1000) + random;
	}
}
