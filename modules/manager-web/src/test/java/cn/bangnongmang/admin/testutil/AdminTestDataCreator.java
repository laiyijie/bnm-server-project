package cn.bangnongmang.admin.testutil;

import java.util.Date;
import java.util.Random;

import cn.bangnongmang.admin.data.domain.AccountAreaKey;
import cn.bangnongmang.admin.data.domain.AdminAccount;
import cn.bangnongmang.admin.data.domain.AreaDict;
import cn.bangnongmang.admin.enums.OrderDriverEnum;
import cn.bangnongmang.data.domain.*;

public class AdminTestDataCreator {


    public static AdminAccount createAdminAccount(String username) {
        AdminAccount adminAccount = new AdminAccount();

        adminAccount.setUsername(username);
        adminAccount.setPassword("xxx");
        adminAccount.setLogin_failed_time(1111);
        adminAccount.setLast_failed_time(1111555L);
        adminAccount.setVerify_code("dksl");
        adminAccount.setSuperior("xx");
        adminAccount.setName("xx");
        adminAccount.setPhone("22");
        adminAccount.setLevel(30);
        return adminAccount;
    }

    public static AccountAreaKey createAccountAreaKey(Integer area_id, String username) {
        AccountAreaKey accountAreaKey = new AccountAreaKey();
        accountAreaKey.setArea_id(area_id);
        accountAreaKey.setUsername(username);
        return accountAreaKey;
    }

    public static AreaDict createAreaDict(Integer id) {
        AreaDict areaDict = new AreaDict();
        areaDict.setId(id);
        areaDict.setProvince("上海");
        areaDict.setCity("上海");
        areaDict.setCounty("xxx");
        return areaDict;
    }


}
