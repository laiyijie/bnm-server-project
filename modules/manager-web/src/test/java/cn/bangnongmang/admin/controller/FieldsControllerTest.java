package cn.bangnongmang.admin.controller;

import cn.bangnongmang.admin.testutil.ControllerConfigUtil;
import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by admin on 2017/4/17.
 */
public class FieldsControllerTest extends ControllerConfigUtil {

    @Test
    public void testFieldsGet() throws Exception {
        getMockMvc().perform(get("/fields/123/fieldsIdGet").session(getSession())).andDo(print());
    }
}
