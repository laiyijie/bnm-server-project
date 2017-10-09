package cn.bangnongmang.server.io.android.show;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import cn.bangnongmang.data.combo.dao.OrderFarmerInfoShowMapper;
import cn.bangnongmang.data.combo.dao.UserMachineShowMapper;
import cn.bangnongmang.data.combo.domain.OptionClusterDetailInfo;
import cn.bangnongmang.data.combo.domain.OrderFarmerInfoShow;
import cn.bangnongmang.data.combo.domain.UserMachineShow;
import cn.bangnongmang.driverapp.models.OptionInfo;
import cn.bangnongmang.driverapp.models.UserMachineInfo;

@Service
public class AndroidMachineShow {

	@Autowired
	private UserMachineShowMapper userMachineShowMapper;

	@Autowired
	private OrderFarmerInfoShowMapper orderFarmerInfoShowMapper;

	public List<UserMachineInfo> getUserMachineList(Long uid) {

		List<UserMachineShow> userMachineShows = userMachineShowMapper.selectByUid(uid);
		return userMachineShows.stream().map(a -> convertUserMachineShowToUserMachineInfo(a))
				.collect(Collectors.toList());
	}

	public UserMachineInfo convertUserMachineShowToUserMachineInfo(UserMachineShow userMachineShow) {

		UserMachineInfo userMachineInfo = new UserMachineInfo();
		userMachineInfo.setBrand(userMachineShow.getUserMachineModel() != null
				? userMachineShow.getUserMachineModel().getModel_brand() : "未知");
		userMachineInfo.setBuyTime(userMachineShow.getUserMachine().getBuy_time());
		userMachineInfo.setFailReason(userMachineShow.getUserMachine().getFailed_reason());
		userMachineInfo.setId(userMachineShow.getUserMachine().getId());
		userMachineInfo.setNumber(userMachineShow.getUserMachineModel().getModel_number());
		userMachineInfo.setState(userMachineShow.getUserMachine().getState());
		userMachineInfo.setType(userMachineShow.getUserMachineType().getType_name());

		userMachineInfo.setSupportWorkingTypes(
				userMachineShow.getOptionWorkingTypes().stream().map(a -> a.getId()).collect(Collectors.toList()));
		userMachineInfo.setOptionInfos(userMachineShow.getOptions().stream()
				.map(this::convertOptionClusterDetailInfoToOptionInfo).collect(Collectors.toList()));
		return userMachineInfo;
	}

	public OptionInfo convertOptionClusterDetailInfoToOptionInfo(OptionClusterDetailInfo oClusterDetailInfo) {
		OptionInfo optionInfo = new OptionInfo();
		optionInfo.setClusterId(oClusterDetailInfo.getOptionCluster().getId());
		optionInfo.setClusterName(oClusterDetailInfo.getOptionCluster().getCluster_name());
		optionInfo.setId(oClusterDetailInfo.getOptionDetail().getId());
		optionInfo.setName(oClusterDetailInfo.getOptionDetail().getOption_name());

		return optionInfo;
	}

	public Double getUserAvailableMachineWidth(String orderId, Long uid) {
		final Double DEFAULT_WIDTH = 2.0;
		List<UserMachineShow> userMachineShows = userMachineShowMapper.selectByUid(uid);
		OrderFarmerInfoShow orderFarmerInfoShow = orderFarmerInfoShowMapper.selectOrderFarmerInfoShowByOrderId(orderId);

		if (orderFarmerInfoShow == null) {
			return DEFAULT_WIDTH;
		}

		if (userMachineShows.isEmpty()) {
			return DEFAULT_WIDTH;
		}

		if (orderFarmerInfoShow.getOptionWorkingType() == null) {
			return DEFAULT_WIDTH;
		}

		Long workingTypeId = orderFarmerInfoShow.getOptionWorkingType().getId();

		Set<Long> orderOptions = orderFarmerInfoShow.getOptions().stream().map(a -> a.getId())
				.collect(Collectors.toSet());

		for (UserMachineShow userMachineShow : userMachineShows) {
			Set<Long> machineOptions = userMachineShow.getOptions().stream().map(a -> a.getId())
					.collect(Collectors.toSet());
			Set<Long> machineWorkingType = userMachineShow.getOptionWorkingTypes().stream().map(a -> a.getId())
					.collect(Collectors.toSet());
			if (machineOptions.containsAll(orderOptions) && machineWorkingType.contains(workingTypeId)) {

				if (userMachineShow.getUserMachineModel() != null
						&& userMachineShow.getUserMachineModel().getModel_width() != null) {
					return userMachineShow.getUserMachineModel().getModel_width();
				}
			}
		}

		return DEFAULT_WIDTH;
	}

}
