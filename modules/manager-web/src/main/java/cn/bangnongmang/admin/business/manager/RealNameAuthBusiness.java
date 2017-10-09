package cn.bangnongmang.admin.business.manager;

/**
 * Created by wenxx on 2017/5/23.
 */
public interface RealNameAuthBusiness {
    void acceptRealNameAuth(Long uid);
    void denyRealNameAuth(Long uid,String reason);
}
