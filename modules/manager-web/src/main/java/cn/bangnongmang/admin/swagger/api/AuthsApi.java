package cn.bangnongmang.admin.swagger.api;

import cn.bangnongmang.admin.swagger.model.ManagerAccount;
import cn.bangnongmang.admin.swagger.model.Resc;
import cn.bangnongmang.admin.swagger.model.Role;
import cn.bangnongmang.admin.swagger.model.RoleAccoutMap;
import cn.bangnongmang.admin.swagger.model.RoleRescMap;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Api(value = "auths", description = "the auths API")
public interface AuthsApi {

    @ApiOperation(value = "获取所有的账户信息", notes = "", response = ManagerAccount.class, responseContainer = "List", tags={ "Auth", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "成功返回", response = ManagerAccount.class) })
    
    @RequestMapping(value = "/auths/accounts",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<ManagerAccount>> authsAccountsGet( HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "新增一个账户", notes = "", response = Void.class, tags={ "Auth", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "成功返回", response = Void.class) })
    
    @RequestMapping(value = "/auths/accounts",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> authsAccountsPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody ManagerAccount account, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "删除一个账户", notes = "", response = Void.class, tags={ "Auth", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "成功返回", response = Void.class) })
    
    @RequestMapping(value = "/auths/accounts/{username}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> authsAccountsUsernameDelete(@ApiParam(value = "",required=true ) @PathVariable("username") String username, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取一个账户信息", notes = "", response = ManagerAccount.class, tags={ "Auth", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "成功返回", response = ManagerAccount.class) })
    
    @RequestMapping(value = "/auths/accounts/{username}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<ManagerAccount> authsAccountsUsernameGet(@ApiParam(value = "",required=true ) @PathVariable("username") String username, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "修改一个用户", notes = "", response = Void.class, tags={ "Auth", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/auths/accounts/{username}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Void> authsAccountsUsernamePut(@ApiParam(value = "",required=true ) @PathVariable("username") String username,@ApiParam(value = "" ,required=true )  @Valid @RequestBody ManagerAccount manageAccount, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "登录 ", notes = "", response = Void.class, tags={ "Auth", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "成功返回", response = Void.class) })
    
    @RequestMapping(value = "/auths/login",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> authsLoginPost(@ApiParam(value = "用户名", required=true) @RequestParam(value="username", required=true)  String username,@ApiParam(value = "密码", required=true) @RequestParam(value="password", required=true)  String password, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取所有的资源", notes = "", response = Resc.class, responseContainer = "List", tags={ "Auth", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "成功返回", response = Resc.class) })
    
    @RequestMapping(value = "/auths/resources",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Resc>> authsResourcesGet( HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "新增一个资源", notes = "", response = Void.class, tags={ "Auth", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "成功返回", response = Void.class) })
    
    @RequestMapping(value = "/auths/resources",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> authsResourcesPost(@ApiParam(value = "不需要 传id，id 给我也没用" ,required=true )  @Valid @RequestBody Resc resource, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "删除一个资源", notes = "", response = Void.class, tags={ "Auth", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Void.class) })
    
    @RequestMapping(value = "/auths/resources/{rescId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> authsResourcesRescIdDelete(@ApiParam(value = "",required=true ) @PathVariable("rescId") Integer rescId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "get a resc", notes = "", response = Resc.class, tags={ "Auth", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Resc.class) })
    
    @RequestMapping(value = "/auths/resources/{rescId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Resc> authsResourcesRescIdGet(@ApiParam(value = "",required=true ) @PathVariable("rescId") Integer rescId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "修改一个资源", notes = "", response = Void.class, tags={ "Auth", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "成功返回", response = Void.class) })
    
    @RequestMapping(value = "/auths/resources/{rescId}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Void> authsResourcesRescIdPut(@ApiParam(value = "here the id is invalid" ,required=true )  @Valid @RequestBody Resc resource,@ApiParam(value = "",required=true ) @PathVariable("rescId") Integer rescId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取所有的角色", notes = "", response = Role.class, responseContainer = "List", tags={ "Auth", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "成功返回", response = Role.class) })
    
    @RequestMapping(value = "/auths/roles",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Role>> authsRolesGet( HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "add a role", notes = "", response = Void.class, tags={ "Auth", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "成功返回", response = Void.class) })
    
    @RequestMapping(value = "/auths/roles",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> authsRolesPost(@ApiParam(value = "role id is not required" ,required=true )  @Valid @RequestBody Role role, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取某个角色下的所有账户关联", notes = "", response = RoleAccoutMap.class, responseContainer = "List", tags={ "Auth", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "成功返回", response = RoleAccoutMap.class) })
    
    @RequestMapping(value = "/auths/roles/{roleId}/accountMaps",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<RoleAccoutMap>> authsRolesRoleIdAccountMapsGet(@ApiParam(value = "roleID",required=true ) @PathVariable("roleId") Integer roleId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "增加一个关联", notes = "", response = Void.class, tags={ "Auth", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "成功返回", response = Void.class) })
    
    @RequestMapping(value = "/auths/roles/{roleId}/accountMaps",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> authsRolesRoleIdAccountMapsPost(@ApiParam(value = "roleID",required=true ) @PathVariable("roleId") Integer roleId,@ApiParam(value = "username", required=true) @RequestParam(value="username", required=true)  String username, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "删除一个角色和账户的关联", notes = "", response = Void.class, tags={ "Auth", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "成功返回", response = Void.class) })
    
    @RequestMapping(value = "/auths/roles/{roleId}/accountMaps/{username}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> authsRolesRoleIdAccountMapsUsernameDelete(@ApiParam(value = "roleID",required=true ) @PathVariable("roleId") Integer roleId,@ApiParam(value = "username",required=true ) @PathVariable("username") String username, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "获取某个角色下的所有账户关联", notes = "", response = RoleAccoutMap.class, tags={ "Auth", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "成功返回", response = RoleAccoutMap.class) })
    
    @RequestMapping(value = "/auths/roles/{roleId}/accountMaps/{username}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<RoleAccoutMap> authsRolesRoleIdAccountMapsUsernameGet(@ApiParam(value = "roleID",required=true ) @PathVariable("roleId") Integer roleId,@ApiParam(value = "username",required=true ) @PathVariable("username") String username, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "delete role info", notes = "", response = Void.class, tags={ "Auth", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/auths/roles/{roleId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> authsRolesRoleIdDelete(@ApiParam(value = "",required=true ) @PathVariable("roleId") Integer roleId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "get role", notes = "", response = Role.class, tags={ "Auth", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Role.class) })
    
    @RequestMapping(value = "/auths/roles/{roleId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Role> authsRolesRoleIdGet(@ApiParam(value = "",required=true ) @PathVariable("roleId") Integer roleId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "update role info", notes = "", response = Void.class, tags={ "Auth", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "成功返回", response = Void.class) })
    
    @RequestMapping(value = "/auths/roles/{roleId}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Void> authsRolesRoleIdPut(@ApiParam(value = "role id is not required" ,required=true )  @Valid @RequestBody Role role,@ApiParam(value = "",required=true ) @PathVariable("roleId") Integer roleId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "资源和角色的对应关系", notes = "", response = RoleRescMap.class, responseContainer = "List", tags={ "Auth", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = RoleRescMap.class) })
    
    @RequestMapping(value = "/auths/roles/{roleId}/resourceMaps",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<RoleRescMap>> authsRolesRoleIdResourceMapsGet(@ApiParam(value = "role ID",required=true ) @PathVariable("roleId") Integer roleId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "在角色下增加一个resource", notes = "", response = Void.class, tags={ "Auth", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/auths/roles/{roleId}/resourceMaps",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> authsRolesRoleIdResourceMapsPost(@ApiParam(value = "role ID",required=true ) @PathVariable("roleId") Integer roleId,@ApiParam(value = "map" ,required=true )  @Valid @RequestBody RoleRescMap roleRescMap, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "在角色下删除一个resource", notes = "", response = Void.class, tags={ "Auth", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/auths/roles/{roleId}/resourceMaps/{rescId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> authsRolesRoleIdResourceMapsRescIdDelete(@ApiParam(value = "role ID",required=true ) @PathVariable("roleId") Integer roleId,@ApiParam(value = "rescId",required=true ) @PathVariable("rescId") Integer rescId, HttpServletRequest request, HttpServletResponse response) throws Exception;


    @ApiOperation(value = "修改", notes = "", response = Void.class, tags={ "Auth", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Void.class) })
    
    @RequestMapping(value = "/auths/roles/{roleId}/resourceMaps/{rescId}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Void> authsRolesRoleIdResourceMapsRescIdPut(@ApiParam(value = "role ID",required=true ) @PathVariable("roleId") Integer roleId,@ApiParam(value = "rescId",required=true ) @PathVariable("rescId") Integer rescId,@ApiParam(value = "only method field required"  )  @Valid @RequestBody RoleRescMap method, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
