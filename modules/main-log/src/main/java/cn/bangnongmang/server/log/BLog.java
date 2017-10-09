package cn.bangnongmang.server.log;

import cn.bangnongmang.server.log.builder.AccessJsonLogBuilder;
import cn.bangnongmang.server.log.builder.BusinessJsonLogBuilder;
import cn.bangnongmang.server.log.builder.ErrorJsonLogBuilder;

/**
 * Created by admin on 2017-05-19.
 */
public class BLog {

    public static BusinessJsonLogBuilder businessJsonLogBuilder(String module) {
        return new BusinessJsonLogBuilder(module);
    }

    public static AccessJsonLogBuilder accessJsonLogBuilder() {
        return new AccessJsonLogBuilder();
    }

    public static ErrorJsonLogBuilder errorJsonLogBuilder() {
        return new ErrorJsonLogBuilder();
    }

}
