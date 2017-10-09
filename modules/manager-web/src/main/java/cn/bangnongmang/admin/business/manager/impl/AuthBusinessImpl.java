package cn.bangnongmang.admin.business.manager.impl;

import cn.bangnongmang.admin.business.manager.AuthBusiness;
import cn.bangnongmang.admin.data.dao.*;
import cn.bangnongmang.admin.data.domain.*;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.server.log.BLog;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * Created by admin on 2017-05-11.
 */
@Service("managerAuth")
@Transactional
public class AuthBusinessImpl implements AuthBusiness {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private AdminAccountMapper accountMapper;
    @Autowired
    private RescMapper rescMapper;
    @Autowired
    private RescRoleMapper rescRoleMapper;

    public static String METHOD_ALL = "ALL";
    public static String METHOD_GET = "GET";

    public static String ADMIN_USERNAME = "admin";
    public static Integer DEFAULT_ROLE_ID = 1;
    public static Integer DEFAULT_RESC_ID = 1;

    @Override
    public void addAccount(String username, String name, String password, String phone, String superior) {
        if (accountMapper.selectByPrimaryKey(username) != null) throw new BusinessException("该用户已存在");
        AdminAccount adminAccount = new AdminAccount();
        adminAccount.setLevel(0);
        adminAccount.setUsername(username);
        adminAccount.setName(name);
        adminAccount.setPhone(phone);
        adminAccount.setSuperior(superior);
        adminAccount.setPassword(DigestUtils.sha1Hex(password));
        adminAccount.setLast_failed_time(0L);
        adminAccount.setLogin_failed_time(0);
        adminAccount.setVerify_code("");
        System.out.println("huangxinhe dasha bi ");
        BLog.businessJsonLogBuilder("Auth")
            .addAction("add action")
            .log();
        accountMapper.insert(adminAccount);
    }

    @Override
    public void modifyAccount(String username, String name, String password, String phone, String superior) {
        if (accountMapper.selectByPrimaryKey(username) == null) throw new BusinessException("该用户不存在");
        AdminAccount adminAccount = new AdminAccount();
        adminAccount.setUsername(username);
        adminAccount.setName(name);
        adminAccount.setPhone(phone);
        adminAccount.setSuperior(superior);
        adminAccount.setPassword(DigestUtils.sha1Hex(password));
        accountMapper.updateByPrimaryKeySelective(adminAccount);
    }

    @Override
    public void deleteAccount(String username) {
        if (ADMIN_USERNAME.equals(username)) throw new BusinessException("不能删除主账户");
        if (accountMapper.selectByPrimaryKey(username) == null) throw new BusinessException("该用户已不存在");
        accountMapper.deleteByPrimaryKey(username);
    }

    @Override
    public void addResc(String name, String resource, String description) {
        Resc resc = new Resc();
        resc.setName(name);
        resc.setResource(resource);
        resc.setDescription(description);
        if (rescMapper.insert(resc) == 0) throw new BusinessException("插入失败");
    }

    @Override
    public void deleteResc(Integer rescId) {
        if (Objects.equals(DEFAULT_RESC_ID, rescId)) throw new BusinessException("不可删除默认资源");
        if (rescMapper.selectByPrimaryKey(rescId) == null) throw new BusinessException("资源不存在");
        rescMapper.deleteByPrimaryKey(rescId);
    }

    @Override
    public void modifyResc(Integer rescId, String name, String resource, String descrpition) {
        if (Objects.equals(DEFAULT_RESC_ID, rescId)) throw new BusinessException("不可修改默认资源");
        if (rescMapper.selectByPrimaryKey(rescId) == null) throw new BusinessException("资源不存在");
        Resc resc = new Resc();
        resc.setDescription(descrpition);
        resc.setResource(resource);
        resc.setName(name);
        resc.setId(rescId);
        rescMapper.updateByPrimaryKey(resc);
    }

    @Override
    public void addRole(String name, String description) {
        Role role = new Role();
        role.setDescription(description);
        role.setName(name);
        roleMapper.insert(role);
    }

    @Override
    public void deleteRole(Integer roleId) {
        if (DEFAULT_ROLE_ID.equals(roleId)) throw new BusinessException("默认角色不能删除");
        if (roleMapper.selectByPrimaryKey(roleId) == null) throw new BusinessException("该角色不存在");
        roleMapper.deleteByPrimaryKey(roleId);
    }

    @Override
    public void modifyRole(Integer roleId, String name, String description) {
        if (DEFAULT_ROLE_ID.equals(roleId)) throw new BusinessException("默认角色不能修改");
        if (roleMapper.selectByPrimaryKey(roleId) == null) throw new BusinessException("该角色不存在");
        Role role = new Role();
        role.setName(name);
        role.setId(roleId);
        role.setDescription(description);
        roleMapper.updateByPrimaryKey(role);
    }

    @Override
    public void addRescToRole(Integer roleId, Integer rescId, String method) {
        if (METHOD_ALL.equals(method) || METHOD_GET.equals(method)) {
            RescRoleKey rescRoleKey = new RescRoleKey();
            rescRoleKey.setRole_id(roleId);
            rescRoleKey.setResc_id(rescId);
            if (rescRoleMapper.selectByPrimaryKey(rescRoleKey) != null) throw new BusinessException("已存在");
            RescRole rescRole = new RescRole();
            rescRole.setMethod(method);
            rescRole.setResc_id(rescId);
            rescRole.setRole_id(roleId);
            rescRoleMapper.insert(rescRole);
        } else {
            throw new BusinessException("方法不正确");
        }
    }

    @Override
    public void modifyRescRole(Integer roleId, Integer rescId, String method) {
        if (DEFAULT_RESC_ID.equals(rescId) && DEFAULT_ROLE_ID.equals(roleId)) throw new BusinessException("默认匹配不能修改");
        RescRoleKey rescRoleKey = new RescRoleKey();
        rescRoleKey.setRole_id(roleId);
        rescRoleKey.setResc_id(rescId);
        if (rescRoleMapper.selectByPrimaryKey(rescRoleKey) == null) throw new BusinessException("该记录不存在");
        RescRole rescRole = new RescRole();
        rescRole.setMethod(method);
        rescRole.setResc_id(rescId);
        rescRole.setRole_id(roleId);
        rescRoleMapper.updateByPrimaryKey(rescRole);
    }

    @Override
    public void deleteRescFromRole(Integer roleId, Integer rescId) {
        if (DEFAULT_RESC_ID.equals(rescId) && DEFAULT_ROLE_ID.equals(roleId)) throw new BusinessException("默认匹配不能删除");
        RescRoleKey rescRoleKey = new RescRoleKey();
        rescRoleKey.setRole_id(roleId);
        rescRoleKey.setResc_id(rescId);
        if (rescRoleMapper.selectByPrimaryKey(rescRoleKey) == null) throw new BusinessException("该记录已删除");
        rescRoleMapper.deleteByPrimaryKey(rescRoleKey);
    }

    @Override
    public void addUserToRole(Integer roleId, String username) {
        UserRoleCriteria userRoleCriteria = new UserRoleCriteria();
        userRoleCriteria.or()
                        .andRole_idEqualTo(roleId)
                        .andUser_idEqualTo(username);
        if (userRoleMapper.selectByExample(userRoleCriteria)
                          .size() != 0) throw new BusinessException("该记录已存在");
        UserRoleKey userRoleKey = new UserRoleKey();
        userRoleKey.setRole_id(roleId);
        userRoleKey.setUser_id(username);
        userRoleMapper.insert(userRoleKey);
    }

    @Override
    public void deleteUserFromRole(Integer roleId, String username) {
        if (DEFAULT_ROLE_ID.equals(roleId) && ADMIN_USERNAME.equals(username)) throw new BusinessException("不能修改默认的角色和用户关联");
        UserRoleCriteria userRoleCriteria = new UserRoleCriteria();
        userRoleCriteria.or()
                        .andRole_idEqualTo(roleId)
                        .andUser_idEqualTo(username);
        if (userRoleMapper.selectByExample(userRoleCriteria)
                          .size() == 0) throw new BusinessException("该记录已删除");
        userRoleMapper.deleteByExample(userRoleCriteria);
    }
}
