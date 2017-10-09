package cn.bangnongmang.admin.io.swagger.show;

import cn.bangnongmang.admin.data.dao.*;
import cn.bangnongmang.admin.data.domain.*;
import cn.bangnongmang.admin.io.swagger.converter.AuthsConverter;
import cn.bangnongmang.admin.swagger.model.*;
import cn.bangnongmang.admin.swagger.model.Resc;
import cn.bangnongmang.admin.swagger.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by admin on 2017-05-10.
 */
@Service
public class AuthsShow {

    @Autowired
    private AuthsConverter authsConverter;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RescMapper rescMapper;
    @Autowired
    private RescRoleMapper rescRoleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private AdminAccountMapper adminAccountMapper;

    public List<Role> getAllRole() {
        return roleMapper.selectByExample(new RoleCriteria())
                         .stream()
                         .map(role -> authsConverter.convertToRole(role))
                         .collect(Collectors.toList());
    }

    public List<Resc> getAllResc() {
        return rescMapper.selectByExample(new RescCriteria())
                         .stream()
                         .map(resc -> authsConverter.convertToResc(resc))
                         .collect(Collectors.toList());
    }

    public List<ManagerAccount> getAllManagerAccount() {
        return adminAccountMapper.selectByExample(new AdminAccountCriteria())
                                 .stream()
                                 .map(new Function<AdminAccount, ManagerAccount>() {
                                     @Override
                                     public ManagerAccount apply(AdminAccount adminAccount) {
                                         return authsConverter.convertToManagerAccount
                                                 (adminAccount);
                                     }
                                 })
                                 .collect(Collectors.toList());
    }

    public List<RoleRescMap> getRoleRescByRoleId(Integer roleId) {
        RescRoleCriteria rescRoleCriteria = new RescRoleCriteria();
        rescRoleCriteria.or()
                        .andRole_idEqualTo(roleId);
        return rescRoleMapper.selectByExample(rescRoleCriteria)
                             .stream()
                             .map(rescRole -> authsConverter.convertToRoleRescMap(rescRole))
                             .collect(
                                     Collectors.toList());
    }

    public List<RoleAccoutMap> getRoleAccoutMapByRoleId(Integer roleId) {
        UserRoleCriteria userRoleCriteria = new UserRoleCriteria();
        userRoleCriteria.or()
                        .andRole_idEqualTo(roleId);
        return userRoleMapper.selectByExample(userRoleCriteria)
                             .stream()
                             .map(userRoleKey -> authsConverter.convertToRoleAccountMap(userRoleKey))
                             .collect(
                                     Collectors.toList());
    }

    public Role getRoleByRoleId(Integer roleId) {
        return authsConverter.convertToRole(roleMapper.selectByPrimaryKey(roleId));
    }

    public RoleAccoutMap getRoleAccoutMapByRoleIdAndUsername(Integer roleId, String username) {
        UserRoleCriteria userRoleCriteria = new UserRoleCriteria();
        userRoleCriteria.or()
                        .andRole_idEqualTo(roleId)
                        .andUser_idEqualTo(username);
        List<RoleAccoutMap> roleAccoutMaps = userRoleMapper.selectByExample(userRoleCriteria)
                                                           .stream()
                                                           .map(userRoleKey -> authsConverter.convertToRoleAccountMap(userRoleKey))
                                                           .collect(
                                                                   Collectors.toList());
        if (roleAccoutMaps.isEmpty()) return null;
        return roleAccoutMaps.get(0);
    }

    public ManagerAccount getManagerAccountByUsername(String username) {
        return authsConverter.convertToManagerAccount(adminAccountMapper.selectByPrimaryKey(username));
    }

    public List<RoleRescMap> getRoleRescMapByRoleId(Integer roleId) {
        RescRoleCriteria rescRoleCriteria = new RescRoleCriteria();
        rescRoleCriteria.or()
                        .andRole_idEqualTo(roleId);
        return rescRoleMapper.selectByExample(rescRoleCriteria)
                             .stream()
                             .map(rescRole -> authsConverter.convertToRoleRescMap(rescRole))
                             .collect(
                                     Collectors.toList());
    }

    public Resc getRescByRescId(Integer rescId) {
        return authsConverter.convertToResc(rescMapper.selectByPrimaryKey(rescId));
    }

}
