package cn.bangnongmang.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.bangnongmang.admin.data.dao.AccountAreaMapper;
import cn.bangnongmang.admin.data.dao.AdminAccountMapper;
import cn.bangnongmang.admin.data.dao.AreaDictMapper;
import cn.bangnongmang.admin.data.domain.AccountAreaCriteria;
import cn.bangnongmang.admin.data.domain.AccountAreaKey;
import cn.bangnongmang.admin.data.domain.AdminAccount;
import cn.bangnongmang.admin.data.domain.AdminAccountCriteria;
import cn.bangnongmang.admin.data.domain.AreaDict;
import cn.bangnongmang.admin.data.domain.AreaDictCriteria;
import cn.bangnongmang.admin.data.domain.AreaDictCriteria.Criteria;
import cn.bangnongmang.admin.model.SimpleInnerUser;
import cn.bangnongmang.admin.util.AccountUtils;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.admin.util.ClassTransUtil;
import cn.bangnongmang.admin.util.PageResult;

@Transactional
@Repository("AdminUserService")
public class UserService {

	@Autowired
	private AdminAccountMapper adminAccMapper;
	@Autowired
	private AreaDictMapper areaDictMapper;
	@Autowired
	private AccountAreaMapper accountAreaMapper;

	private static final long INTERVAL = 300000;
	private static final int MAX_TIME = 5;

	public boolean login(String username, String password) {
		AdminAccount adminAcc = adminAccMapper.selectByPrimaryKey(username);
		if (adminAcc != null) {
			long curr = System.currentTimeMillis();
			long last_failed_time = adminAcc.getLast_failed_time();
			int login_failed_time = adminAcc.getLogin_failed_time();

			if (login_failed_time > MAX_TIME && (curr - last_failed_time) < INTERVAL) {
				return false;
			}

			String encryptPassword = password;
			if (encryptPassword.equals(adminAcc.getPassword())) {
				adminAcc.setLogin_failed_time(0);
				return true;
			} else {

				if ((curr - last_failed_time) < INTERVAL) {
					adminAcc.setLogin_failed_time(adminAcc.getLogin_failed_time() + 1);
					adminAcc.setLast_failed_time(curr);
				} else {
					adminAcc.setLast_failed_time(curr);
					adminAcc.setLogin_failed_time(1);
				}
				adminAccMapper.updateByPrimaryKey(adminAcc);
				return false;
			}
		} else {
			return false;
		}
	}

	public AdminAccount createUser(String creatorUsername, String username, String clearPassword) {

		AdminAccount creator = adminAccMapper.selectByPrimaryKey(creatorUsername);

		if (AccountUtils.isSuperAdmin(creator)) {

			AdminAccount newAdminAcc = new AdminAccount();
			newAdminAcc.setUsername(username);
			newAdminAcc.setPassword(DigestUtils.sha1Hex(clearPassword));
			newAdminAcc.setLast_failed_time(0L);
			newAdminAcc.setLogin_failed_time(0);
			newAdminAcc.setVerify_code("");
			newAdminAcc.setLevel(AccountUtils.LEVEL_NONE);
			int flag = adminAccMapper.insert(newAdminAcc);
			System.out.println(flag);

			return adminAccMapper.selectByPrimaryKey(username);
		} else {
			return null;
		}
	}

	public AdminAccount changeUserPassword(String creatorUsername, String username, String clearPassword) {

		AdminAccount creator = adminAccMapper.selectByPrimaryKey(creatorUsername);

		if (AccountUtils.isSuperAdmin(creator)) {

			AdminAccount newAdminAcc = adminAccMapper.selectByPrimaryKey(username);
			newAdminAcc.setPassword(DigestUtils.sha1Hex(clearPassword));
			int flag = adminAccMapper.updateByPrimaryKey(newAdminAcc);

			return adminAccMapper.selectByPrimaryKey(username);
		} else {
			return null;
		}
	}

	public boolean changePassword(String username, String oldpassword, String newPassword) {

		AdminAccount account = adminAccMapper.selectByPrimaryKey(username);

		if (account == null) {
			return false;
		}
		if (account.getPassword().equals(DigestUtils.sha1Hex(oldpassword))) {
			account.setPassword(DigestUtils.sha1Hex(newPassword));
			adminAccMapper.updateByPrimaryKey(account);
			return true;
		}

		return false;
	}

	public List<String> getAdminAccountList(String opUsername) {
		AdminAccount account = adminAccMapper.selectByPrimaryKey(opUsername);

		if (account == null) {
			return null;
		}
		if (AccountUtils.isSuperAdmin(account) || AccountUtils.isService(account) || AccountUtils.isLeader(account)) {
			AdminAccountCriteria criteria = new AdminAccountCriteria();
			if (AccountUtils.isLeader(account)) {
				criteria.or().andSuperiorEqualTo(account.getUsername());
			} else {
				criteria.or();
			}
			List<AdminAccount> accounts = adminAccMapper.selectByExample(criteria);

			if (accounts.isEmpty()) {
				return null;
			}
			ArrayList<String> names = new ArrayList<String>(accounts.size());
			for (int i = 0; i < accounts.size(); i++) {
				names.add(accounts.get(i).getUsername());
			}
			return names;
		} else {
			return null;
		}
	}

	public PageResult<AdminAccount> getInnerUserList(String opUsername, Integer currentPage, Integer pageSize,
			Integer userLevel) {
		AdminAccount account = adminAccMapper.selectByPrimaryKey(opUsername);

		if (account == null) {
			return null;
		}

		if (AccountUtils.isSuperAdmin(account) || AccountUtils.isService(account) || AccountUtils.isLeader(account)) {

			AdminAccountCriteria criteria = new AdminAccountCriteria();
			cn.bangnongmang.admin.data.domain.AdminAccountCriteria.Criteria detail = criteria.or()
					.andLevelNotEqualTo(AccountUtils.LEVEL_SUPER);

			if (AccountUtils.isLeader(account)) {
				detail.andSuperiorEqualTo(account.getUsername());
			}
			if (userLevel != null) {
				detail.andLevelEqualTo(userLevel);
			}

			PageHelper.startPage(currentPage, pageSize);

			Page<AdminAccount> accounts = (Page<AdminAccount>) adminAccMapper.selectByExample(criteria);

			if (accounts.isEmpty()) {
				return new PageResult<>();
			}

			return new PageResult<>(accounts);
		} else {
			return null;
		}
	}

	public List<SimpleInnerUser> getLeaderList(String opUsername) {
		AdminAccount account = adminAccMapper.selectByPrimaryKey(opUsername);

		if (account == null) {
			return null;
		}

		if (AccountUtils.isSuperAdmin(account)) {

			AdminAccountCriteria criteria = new AdminAccountCriteria();
			criteria.or().andLevelEqualTo(AccountUtils.LEVEL_LEADER);

			List<AdminAccount> accounts = adminAccMapper.selectByExample(criteria);

			return new ClassTransUtil<SimpleInnerUser>().transToTarget(SimpleInnerUser.class, accounts);
		} else {
			return null;
		}
	}

	public SimpleInnerUser getUserDetail(String opUsername, String username) throws BusinessException {
		AdminAccount opAccount = adminAccMapper.selectByPrimaryKey(opUsername);

		if (opAccount == null) {
			return null;
		}

		if (AccountUtils.isSuperAdmin(opAccount) || AccountUtils.isService(opAccount)
				|| AccountUtils.isLeader(opAccount)) {

			if (AccountUtils.isLeader(opAccount) && !isDriectLeader(opUsername, username)) {
				return null;
			}

			ClassTransUtil<SimpleInnerUser> transUtil = new ClassTransUtil<SimpleInnerUser>();
			AdminAccount account = adminAccMapper.selectByPrimaryKey(username);
			if (account == null) {
				throw new BusinessException("用户名未找到");
			}
			SimpleInnerUser result = transUtil.transToTarget(SimpleInnerUser.class, account);
			result.setAreaList(new ArrayList<>());

			if (account.getSuperior() != null) {
				AdminAccount superior = adminAccMapper.selectByPrimaryKey(opAccount.getSuperior());
				result.setSuperiorDetail(transUtil.transToTarget(SimpleInnerUser.class, superior));
			}

			AccountAreaCriteria areaFilter = new AccountAreaCriteria();
			areaFilter.or().andUsernameEqualTo(username);
			List<AccountAreaKey> areaIds = accountAreaMapper.selectByExample(areaFilter);

			if (areaIds != null) {
				for (AccountAreaKey accountAreaKey : areaIds) {
					AreaDict areaDict = areaDictMapper.selectByPrimaryKey(accountAreaKey.getArea_id());
					if (areaDict != null) {
						result.getAreaList().add(areaDict);
					}
				}
			}

			return result;

		} else {
			return null;
		}
	}

	public boolean updateUserProfile(String opUsername, String username, String name, String phone, Integer level,
			String superior) throws BusinessException {
		AdminAccount opAccount = adminAccMapper.selectByPrimaryKey(opUsername);
		AdminAccount target = adminAccMapper.selectByPrimaryKey(username);

		if (opAccount == null || target == null) {
			return false;
		}
		if (AccountUtils.isSuperAdmin(opAccount) || AccountUtils.isLeader(opAccount)) {
			if (AccountUtils.isLeader(opAccount))
				if (!target.getSuperior().equals(opAccount.getUsername()))
					return false;
				else if (level != null || superior != null)
					return false;

			if (level > opAccount.getLevel()) {
				throw new BusinessException("不能设置用户等级高于自身等级");
			}

			if (name != null) {
				target.setName(name);
			}
			if (phone != null) {
				target.setPhone(phone);
			}
			if (level != null) {
				target.setLevel(level);
			}
			if (superior != null) {
				if (level != AccountUtils.LEVEL_MARKET)
					throw new BusinessException("非市场人员不能设置上级");
				target.setSuperior(superior);
			}

			adminAccMapper.updateByPrimaryKey(target);
			return true;
		} else {
			return false;
		}
	}

	public boolean addAccountArea(String opUsername, String username, String province, String city, String county,
			String town) throws BusinessException {
		AdminAccount opAccount = adminAccMapper.selectByPrimaryKey(opUsername);
		AdminAccount account = adminAccMapper.selectByPrimaryKey(username);

		if (opAccount == null || account == null) {
			return false;
		}
		if (!(AccountUtils.isSuperAdmin(opAccount) || isDriectLeader(opUsername, username))) {
			return false;
		}

		if (!AccountUtils.isSuperAdmin(opAccount) && AccountUtils.isMarket(account)) {
			SimpleInnerUser leader = getUserDetail(opUsername, account.getSuperior());
			if (leader == null) {
				return false;
			}
			boolean found = false;
			for (AreaDict areaDict : leader.getAreaList()) {
				if (AccountUtils.areaContains(areaDict, province, city, county, town)) {
					found = true;
					break;
				}
			}
			if (!found) {
				throw new BusinessException("不能修改到主管服务范围之外");
			}
		}

		AreaDict areaDict = findArea(province, city, county, town);

		AccountAreaCriteria criteria = new AccountAreaCriteria();
		criteria.or().andUsernameEqualTo(username).andArea_idEqualTo(areaDict.getId());
		List<AccountAreaKey> exists = accountAreaMapper.selectByExample(criteria);
		if (!exists.isEmpty()) {
			throw new BusinessException("已经添加过这个区域");
		}

		AccountAreaKey accountAreaKey = new AccountAreaKey();
		accountAreaKey.setUsername(username);
		accountAreaKey.setArea_id(areaDict.getId());
		accountAreaMapper.insert(accountAreaKey);

		return true;
	}

	public boolean deleteAccountArea(String opUsername, String username, Integer areaId) {
		AdminAccount account = adminAccMapper.selectByPrimaryKey(opUsername);

		if (account == null) {
			return false;
		}
		if (!(AccountUtils.isSuperAdmin(account) || isDriectLeader(opUsername, username))) {
			return false;
		}

		AccountAreaCriteria criteria = new AccountAreaCriteria();
		criteria.or().andUsernameEqualTo(username).andArea_idEqualTo(areaId);
		accountAreaMapper.deleteByExample(criteria);

		return true;
	}

	public boolean updateAccountArea(String opUsername, String username, ArrayList<AreaDict> areaList) {
		AdminAccount account = adminAccMapper.selectByPrimaryKey(opUsername);

		if (account == null) {
			return false;
		}
		if (!(AccountUtils.isSuperAdmin(account) || isDriectLeader(opUsername, username))) {
			return false;
		}

		for (AreaDict area : areaList) {
			AreaDict target = findArea(area);
			System.out.println("Target: " + target.getId());
		}

		return true;
	}

	public List<SimpleInnerUser> searchAccount(String opUsername, String key) {
		AdminAccount account = adminAccMapper.selectByPrimaryKey(opUsername);

		if (account == null) {
			return null;
		}

		AdminAccountCriteria criteria = new AdminAccountCriteria();
		criteria.or().andUsernameEqualTo(key);
		criteria.or().andNameEqualTo(key);
		criteria.or().andPhoneEqualTo(key);

		List<AdminAccount> result = adminAccMapper.selectByExample(criteria);
		if (AccountUtils.isLeader(account)) {
			List<AdminAccount> filter = new ArrayList<>();
			for (AdminAccount adminAccount : result) {
				if (isDriectLeader(opUsername, adminAccount.getUsername())) {
					filter.add(adminAccount);
				}
			}
			result = filter;
		}

		return new ClassTransUtil<SimpleInnerUser>().transToTarget(SimpleInnerUser.class, result);
	}

	private boolean isDriectLeader(String opUsername, String username) {
		AdminAccount leaderAccount = adminAccMapper.selectByPrimaryKey(opUsername);
		if (!AccountUtils.isLeader(leaderAccount)) {
			return false;
		}
		AdminAccount marketAccount = adminAccMapper.selectByPrimaryKey(username);
		if (!opUsername.equals(username) && !marketAccount.getSuperior().equals(opUsername)) {
			return false;
		} else {
			return true;
		}
	}

	public AreaDict findArea(String province, String city, String county, String town) {
		AreaDict target = new AreaDict();
		target.setProvince(province);
		target.setCity(city);
		target.setCounty(county);
		target.setTown(town);
		return findArea(target);
	}

	public AreaDict findArea(AreaDict area) {
		AreaDictCriteria areaDictCriteria = new AreaDictCriteria();
		Criteria criteria = areaDictCriteria.or();
		AreaDict fatherFinder = new AreaDict();
		fatherFinder.setProvince(area.getProvince());
		fatherFinder.setCity(area.getCity());
		fatherFinder.setCounty(area.getCounty());

		if (AccountUtils.notEmpty(area.getProvince())) {
			criteria.andProvinceEqualTo(area.getProvince());
			if (AccountUtils.notEmpty(area.getCity())) {
				criteria.andCityEqualTo(area.getCity());
				if (AccountUtils.notEmpty(area.getCounty())) {
					criteria.andCountyEqualTo(area.getCounty());
					if (AccountUtils.notEmpty(area.getTown())) {
						criteria.andTownEqualTo(area.getTown());
					} else {
						criteria.andTownIsNull();
						fatherFinder.setCounty(null);
					}
				} else {
					criteria.andCountyIsNull();
					fatherFinder.setCity(null);
				}
			} else {
				criteria.andCityIsNull();
				fatherFinder.setProvince(null);
			}
		}

		List<AreaDict> target = areaDictMapper.selectByExample(areaDictCriteria);
		if (target.isEmpty()) {
			if (AccountUtils.notEmpty(fatherFinder.getProvince())) {
				AreaDict father = findArea(fatherFinder);
				area.setFather_id(father.getId());
			}
			areaDictMapper.insert(area);

			target = areaDictMapper.selectByExample(areaDictCriteria);
		}

		return target.get(0);
	}
	/**
	 * 
	 *删除管理员用户
	 * 
	 * @Title deleteUser
	 * 
	 * @param username
	 * @return
	 * @throws BusinessException 
	 */
	public boolean deleteUser(String username) throws BusinessException {
		
		if (adminAccMapper.deleteByPrimaryKey(username) <0) {
			return false;
		}
		 AccountAreaCriteria accountAreaCriteria = new AccountAreaCriteria();
		 accountAreaCriteria.or().andUsernameEqualTo(username);
		
		List<AccountAreaKey> example = accountAreaMapper.selectByExample(accountAreaCriteria);
		
		for (AccountAreaKey accountAreaKey : example) {
			Integer area_id = accountAreaKey.getArea_id();
			if (areaDictMapper.deleteByPrimaryKey(area_id) <0) {
	
			}
		}
		accountAreaMapper.deleteByExample(accountAreaCriteria);
		
		return true;

		
	}
	
}
