
package cn.bangnongmang.admin.swagger.controller.impl;

import cn.bangnongmang.admin.business.manager.AuthBusiness;
import cn.bangnongmang.admin.io.swagger.show.AuthsShow;
import cn.bangnongmang.admin.swagger.api.AuthsApi;
import cn.bangnongmang.admin.swagger.controller.base.BaseConf;
import cn.bangnongmang.admin.swagger.model.*;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by admin on 2017-05-10.
 */
@RequestMapping(BaseConf.BASE_URL)
@RestController
public class AuthsController implements AuthsApi {

    @Autowired
    private AuthsShow authsShow;

    @Autowired
    private AuthBusiness authBusiness;

    @Override
    public ResponseEntity<List<ManagerAccount>> authsAccountsGet(HttpServletRequest request, HttpServletResponse response) throws Exception {

        return new ResponseEntity<List<ManagerAccount>>(authsShow.getAllManagerAccount(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> authsAccountsPost(@ApiParam(value = "", required = true) @Valid @RequestBody ManagerAccount account,
                                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        authBusiness.addAccount(account.getUsername(), account.getName(), account.getPassword(), account.getPhone(), account.getSuperior());
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<Void> authsAccountsUsernameDelete(@ApiParam(value = "", required = true) @PathVariable("username") String username,
                                                            HttpServletRequest request, HttpServletResponse response) throws Exception {
        authBusiness.deleteAccount(username);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<ManagerAccount> authsAccountsUsernameGet(@ApiParam(value = "", required = true) @PathVariable("username") String username,
                                                                   HttpServletRequest request, HttpServletResponse response) throws Exception {
        ManagerAccount managerAccount = authsShow.getManagerAccountByUsername(username);
        return managerAccount == null ? new ResponseEntity<ManagerAccount>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(managerAccount);
    }

    @Override
    public ResponseEntity<Void> authsAccountsUsernamePut(@ApiParam(value = "", required = true) @PathVariable("username") String username,
                                                         @ApiParam(value = "", required = true) @Valid @RequestBody ManagerAccount manageAccount,
                                                         HttpServletRequest request, HttpServletResponse response) throws Exception {
        authBusiness.modifyAccount(username, manageAccount.getName(), manageAccount.getPassword(), manageAccount.getPhone(),
                manageAccount.getSuperior());
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<Void> authsLoginPost(
            @ApiParam(value = "用户名", required = true) @RequestParam(value = "username", required = true) String username,
            @ApiParam(value = "密码", required = true) @RequestParam(value = "password", required = true) String password, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<Resc>> authsResourcesGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return ResponseEntity.ok(authsShow.getAllResc());
    }

    @Override
    public ResponseEntity<Void> authsResourcesPost(@ApiParam(value = "不需要 传id，id 给我也没用", required = true) @Valid @RequestBody Resc resource,
                                                   HttpServletRequest request, HttpServletResponse response) throws Exception {
        authBusiness.addResc(resource.getName(), resource.getResource(), resource.getDescription());
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<Void> authsResourcesRescIdDelete(@ApiParam(value = "", required = true) @PathVariable("rescId") Integer rescId,
                                                           HttpServletRequest request, HttpServletResponse response) throws Exception {
        authBusiness.deleteResc(rescId);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<Resc> authsResourcesRescIdGet(@ApiParam(value = "", required = true) @PathVariable("rescId") Integer rescId,
                                                        HttpServletRequest request, HttpServletResponse response) throws Exception {
        Resc resc = authsShow.getRescByRescId(rescId);
        if (resc == null) return new ResponseEntity<Resc>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(resc);
    }

    @Override
    public ResponseEntity<Void> authsResourcesRescIdPut(
            @ApiParam(value = "here the id is invalid", required = true) @Valid @RequestBody Resc resource,
            @ApiParam(value = "", required = true) @PathVariable("rescId") Integer rescId, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        authBusiness.modifyResc(rescId, resource.getName(), resource.getResource(), resource.getDescription());
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<List<Role>> authsRolesGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return ResponseEntity.ok(authsShow.getAllRole());
    }

    @Override
    public ResponseEntity<Void> authsRolesPost(@ApiParam(value = "role id is not required", required = true) @Valid @RequestBody Role role,
                                               HttpServletRequest request, HttpServletResponse response) throws Exception {
        authBusiness.addRole(role.getName(), role.getDescription());
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<List<RoleAccoutMap>> authsRolesRoleIdAccountMapsGet(
            @ApiParam(value = "roleID", required = true) @PathVariable("roleId") Integer roleId, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        return ResponseEntity.ok(authsShow.getRoleAccoutMapByRoleId(roleId));

    }

    @Override
    public ResponseEntity<Void> authsRolesRoleIdAccountMapsPost(
            @ApiParam(value = "roleID", required = true) @PathVariable("roleId") Integer roleId,
            @ApiParam(value = "username", required = true) @RequestParam(value = "username", required = true) String username,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        authBusiness.addUserToRole(roleId, username);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<Void> authsRolesRoleIdAccountMapsUsernameDelete(
            @ApiParam(value = "roleID", required = true) @PathVariable("roleId") Integer roleId,
            @ApiParam(value = "username", required = true) @PathVariable("username") String username, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        authBusiness.deleteUserFromRole(roleId, username);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<RoleAccoutMap> authsRolesRoleIdAccountMapsUsernameGet(
            @ApiParam(value = "roleID", required = true) @PathVariable("roleId") Integer roleId,
            @ApiParam(value = "username", required = true) @PathVariable("username") String username, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return ResponseEntity.ok(authsShow.getRoleAccoutMapByRoleIdAndUsername(roleId, username));
    }

    @Override
    public ResponseEntity<Void> authsRolesRoleIdDelete(@ApiParam(value = "", required = true) @PathVariable("roleId") Integer roleId,
                                                       HttpServletRequest request, HttpServletResponse response) throws Exception {
        authBusiness.deleteRole(roleId);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<Role> authsRolesRoleIdGet(@ApiParam(value = "", required = true) @PathVariable("roleId") Integer roleId,
                                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        Role role = authsShow.getRoleByRoleId(roleId);
        if (role == null)
            return new ResponseEntity<Role>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(role);
    }

    @Override
    public ResponseEntity<Void> authsRolesRoleIdPut(@ApiParam(value = "role id is not required", required = true) @Valid @RequestBody Role role,
                                                    @ApiParam(value = "", required = true) @PathVariable("roleId") Integer roleId,
                                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        authBusiness.modifyRole(roleId, role.getName(), role.getDescription());
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<List<RoleRescMap>> authsRolesRoleIdResourceMapsGet(
            @ApiParam(value = "role ID", required = true) @PathVariable("roleId") Integer roleId, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return ResponseEntity.ok(authsShow.getRoleRescMapByRoleId(roleId));
    }

    @Override
    public ResponseEntity<Void> authsRolesRoleIdResourceMapsPost(@ApiParam(value = "role ID", required = true) @PathVariable("roleId") Integer roleId,
                                                                 @ApiParam(value = "map", required = true) @Valid @RequestBody RoleRescMap roleRescMap,
                                                                 HttpServletRequest request, HttpServletResponse response) throws Exception {
        authBusiness.addRescToRole(roleId, roleRescMap.getRescId(), roleRescMap.getMethod()
                                                                               .name());
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<Void> authsRolesRoleIdResourceMapsRescIdDelete(
            @ApiParam(value = "role ID", required = true) @PathVariable("roleId") Integer roleId,
            @ApiParam(value = "rescId", required = true) @PathVariable("rescId") Integer rescId, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        authBusiness.deleteRescFromRole(roleId, rescId);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<Void> authsRolesRoleIdResourceMapsRescIdPut(
            @ApiParam(value = "role ID", required = true) @PathVariable("roleId") Integer roleId,
            @ApiParam(value = "rescId", required = true) @PathVariable("rescId") Integer rescId,
            @ApiParam(value = "only method field required") @Valid @RequestBody RoleRescMap method, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        authBusiness.modifyRescRole(roleId, rescId, method.getMethod()
                                                          .name());
        return ResponseEntity.ok(null);
    }


    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
