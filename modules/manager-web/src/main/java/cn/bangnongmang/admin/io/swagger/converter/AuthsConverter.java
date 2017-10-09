package cn.bangnongmang.admin.io.swagger.converter;

import cn.bangnongmang.admin.data.domain.AdminAccount;
import cn.bangnongmang.admin.data.domain.RescRole;
import cn.bangnongmang.admin.data.domain.UserRoleKey;
import cn.bangnongmang.admin.swagger.model.*;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2017-05-10.
 */
@Service
public class AuthsConverter {

    public ManagerAccount convertToManagerAccount(AdminAccount adminAccount) {
        if (adminAccount == null) return null;
        ManagerAccount managerAccount = new ManagerAccount();
        managerAccount.setName(adminAccount.getName());
        managerAccount.setPhone(adminAccount.getPhone());
        managerAccount.setSuperior(adminAccount.getSuperior());
        managerAccount.setUsername(adminAccount.getUsername());
        return managerAccount;
    }


    public Role convertToRole(cn.bangnongmang.admin.data.domain.Role role) {
        if (role == null) return null;
        Role newRole = new Role();
        newRole.setName(role.getName());
        newRole.setDescription(role.getDescription());
        newRole.setId(role.getId());
        return newRole;
    }

    public Resc convertToResc(cn.bangnongmang.admin.data.domain.Resc resc) {
        if (resc == null) return null;
        Resc newResc = new Resc();
        newResc.setId(resc.getId());
        newResc.setDescription(resc.getDescription());
        newResc.setResource(resc.getResource());
        newResc.setName(resc.getName());
        return newResc;
    }

    public RoleRescMap convertToRoleRescMap(RescRole rescRole) {
        if (rescRole == null) return null;
        RoleRescMap roleRescMap = new RoleRescMap();
        roleRescMap.setMethod(RoleRescMap.MethodEnum.fromValue(rescRole.getMethod()));
        roleRescMap.setRescId(rescRole.getResc_id());
        roleRescMap.setRoleId(rescRole.getRole_id());
        return roleRescMap;
    }

    public RoleAccoutMap convertToRoleAccountMap(UserRoleKey userRoleKey) {
        if (userRoleKey == null) return null;
        RoleAccoutMap roleAccoutMap = new RoleAccoutMap();
        roleAccoutMap.setRoleId(userRoleKey.getRole_id());
        roleAccoutMap.setUsername(userRoleKey.getUser_id());
        return roleAccoutMap;
    }


}
