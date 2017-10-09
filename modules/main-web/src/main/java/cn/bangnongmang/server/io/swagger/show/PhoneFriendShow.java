package cn.bangnongmang.server.io.swagger.show;

import cn.bangnongmang.data.domain.Account;
import cn.bangnongmang.data.domain.Friendship;
import cn.bangnongmang.server.io.swagger.UserConverter;
import cn.bangnongmang.server.swagger.model.PhoneFriend;
import cn.bangnongmang.server.swagger.model.UserSimple;
import cn.bangnongmang.service.service.FriendshipService;
import cn.bangnongmang.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by lucq on 2017/7/4.
 */
@Component("phoneFriendShow")
public class PhoneFriendShow {

	@Autowired
	private UserService userService;
	@Autowired
	private FriendshipService friendshipService;
	@Autowired
	private UserConverter userConverter;

	public List<PhoneFriend> getPhoneFriend(Long uid, List<String> phoneList) {

		List<PhoneFriend> alllist = new ArrayList<PhoneFriend>();


		for(String tel:phoneList){
			PhoneFriend phoneFriend=new PhoneFriend();
			phoneFriend.setPhone(tel);
			alllist.add(phoneFriend);
		}


		List<PhoneFriend> userList = userService.getAccountInListPhone(phoneList)
										   .stream()
										   .map(userBasicInfoShow -> {
										   	PhoneFriend phoneFriend=new PhoneFriend();
										   	phoneFriend.setUser(userConverter.convertToUserSimple(userBasicInfoShow, UserSimple.class));
											phoneFriend.setPhone(userBasicInfoShow.getAccount().getUsername());
											   phoneFriend.setState(PhoneFriend.StateEnum.USER);
										   	return phoneFriend;})
										   .collect(Collectors.toList());

		List<PhoneFriend> friendList = friendshipService.getFriendInListPhone(uid, phoneList)
												 .stream()
												 .map(userBasicInfoShow -> {
													 PhoneFriend phoneFriend=new PhoneFriend();
													 phoneFriend.setUser(userConverter.convertToUserSimple(userBasicInfoShow, UserSimple.class));
													 phoneFriend.setPhone(userBasicInfoShow.getAccount().getUsername());
													 phoneFriend.setState(PhoneFriend.StateEnum.FRIEND);
													 return phoneFriend;
												 })
												 .collect(Collectors.toList());
		Map<String,PhoneFriend> map=new HashMap<>();

		for(PhoneFriend phoneFriend:alllist){
			phoneFriend.setState(PhoneFriend.StateEnum.NOTUSER);
			map.put(phoneFriend.getPhone(),phoneFriend);
		}

		for(PhoneFriend phoneFriend:userList){
			map.put(phoneFriend.getPhone(),phoneFriend);
		}

		for(PhoneFriend phoneFriend:friendList){
			map.put(phoneFriend.getPhone(),phoneFriend);
		}

		List list=new ArrayList(map.values());

		return list;
	}
}
