package cn.bangnongmang.admin.business.manager;

/**
 * Created by admin on 2017-05-10.
 */
public interface AuthBusiness {

    void addAccount(String username, String name, String password, String phone, String superior);
    void modifyAccount(String username, String name, String password, String phone, String superior);
    void deleteAccount(String username);

    void addResc(String name, String resource, String description);

    void deleteResc(Integer rescId);

    void modifyResc(Integer rescId, String name, String resource, String descrpition);

    void addRole(String name, String description);

    void deleteRole(Integer roleId);

    void modifyRole(Integer roleId, String name, String description);

    void addRescToRole(Integer roleId, Integer rescId, String method);

    void modifyRescRole(Integer roleId, Integer rescId, String method);

    void deleteRescFromRole(Integer roleId, Integer rescId);

    void addUserToRole(Integer roleId, String username);

    void deleteUserFromRole(Integer roleId, String username);


}
