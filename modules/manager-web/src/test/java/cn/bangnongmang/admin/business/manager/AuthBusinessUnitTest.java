package cn.bangnongmang.admin.business.manager;

import cn.bangnongmang.admin.business.manager.impl.AuthBusinessImpl;
import cn.bangnongmang.admin.data.dao.AdminAccountMapper;
import cn.bangnongmang.admin.data.domain.AdminAccount;
import cn.bangnongmang.admin.util.BusinessException;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

/**
 * Created by admin on 2017-05-22.
 */

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthBusinessUnitTest {

    @Mock
    private AdminAccountMapper adminAccountMapper;

    @InjectMocks
    private AuthBusinessImpl authBusiness;
    @Test
    public void TC0101_addAccount_normal(){
        when(adminAccountMapper.selectByPrimaryKey("laiyijie"))
               .thenReturn(null);
        when(adminAccountMapper.insert(any())).thenReturn(1);
        authBusiness.addAccount("laiyijie","123","lalal","123","abc");

    }

    @Test
    public void TC0102_addAccount_accountAlreadyExist(){
        when(adminAccountMapper.selectByPrimaryKey(any())).thenReturn(new AdminAccount());
        try {
            authBusiness.addAccount("laiyijie","123","lailai","123","abc");
            fail();
        } catch (BusinessException ex){
            ex.printStackTrace();
        }
    }


}
