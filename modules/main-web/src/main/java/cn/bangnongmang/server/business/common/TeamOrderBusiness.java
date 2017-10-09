package cn.bangnongmang.server.business.common;

import cn.bangnongmang.server.io.BusinessException;

public interface TeamOrderBusiness {

    /**
     * 关注一个订单，如果订单的状态处于等待抢单，则可以关注，否则关注失败
     *
     * @param uid
     * @param orderid
     * @return
     * @throws BusinessException
     * @Title followOrder
     */
    boolean followOrder(Long uid, String orderid) throws BusinessException;

    /**
     * 取消关注一个订单
     *
     * @param uid
     * @param orderid
     * @return
     * @throws BusinessException
     * @Title unfollowOrder
     */
    boolean unfollowOrder(Long uid, String orderid) throws BusinessException;

    /**
     * 这个用户是否关注了某个
     *
     * @param uid
     * @param orderid
     * @return
     * @Title isUserFollowOrder
     */
    boolean isUserFollowOrder(Long uid, String orderid);

    /**
     * 创建一个队伍，需要时队长才能创建
     *
     * @param orderid
     * @param uid
     * @param msg
     * @return
     * @throws BusinessException
     * @Title createOrderTeam
     */
    Long createOrderTeam(String orderid, Long uid, String msg) throws BusinessException;

    /**
     * 发送一个入队请求
     *
     * @param teamid
     * @param uid
     * @param msg
     * @return
     * @throws BusinessException
     * @Title sendJoinOrderTeamRequest
     */
    Long sendJoinOrderTeamRequest(Long teamid, Long uid, String msg) throws BusinessException;

    /**
     * 接受一个入队请求
     *
     * @param teamid
     * @param uid
     * @param operator
     * @return
     * @throws BusinessException
     * @Title acceptTeamRequest
     */
    boolean acceptTeamRequest(Long teamid, Long uid, Long operator) throws BusinessException;

    /**
     * 拒绝一个入队请求
     *
     * @param teamid
     * @param uid
     * @param operator
     * @return
     * @throws BusinessException
     * @Title denyTeamRequest
     */
    boolean denyTeamRequest(Long teamid, Long uid, Long operator) throws BusinessException;

    /**
     * 取消自己的入队请求
     *
     * @param teamid
     * @param uid
     * @return
     * @throws BusinessException
     * @Title cancelTeamRequest
     */
    boolean cancelTeamRequest(Long teamid, Long uid) throws BusinessException;

    /**
     * 删除一个队友
     *
     * @param teamid
     * @param uid
     * @param ps
     * @param operator
     * @return
     * @throws BusinessException
     * @Title deleteTeamRequest
     */
    boolean deleteTeamRequest(Long teamid, Long uid, String ps, Long operator) throws
            BusinessException;

    /**
     * 解散队伍
     *
     * @param teamId
     * @param operator
     * @return
     * @throws BusinessException
     * @Title deleteOrderTeam
     */

    boolean deleteOrderTeam(Long teamId, Long operator) throws BusinessException;


}
