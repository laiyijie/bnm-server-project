package cn.bangnongmang.admin.business;

import cn.bangnongmang.admin.util.BusinessException;

/**
 * 队伍管理的业务逻辑层
 */
public interface WorkTeamManageBusiness {

    /**
     * 修改队长
     * a) 开始作业之后不能更换队长，未抢单之前不能更换
     * b) 新队长必须具备队长资格
     * c) 新队长必须在队伍中
     *
     * @param newLeaderUid 新队长的uid
     * @param orderId      需要操作订单的id
     * @throws BusinessException 当不符合条件的时候会返回错误
     */
    void changeLeader( Long newLeaderUid, String orderId) throws BusinessException;

    /**
     * 移除队员
     *
     * @param uid     队员的uid
     * @param orderId 订单id
     * @param punishMoney   违约金
     * @throws BusinessException 抛出的是不符合条件的操作
     */
    void removeMember(Long uid, String orderId, Integer punishMoney,Boolean isPunishWaitInMoney) throws BusinessException;

    /**
     * 增加队员
     *
     * @param uid      新队员的ID
     * @param orderId  订单的ID
     * @param needInsurance 是否需要检查车和订单筛选项是否匹配
     * @throws BusinessException 在不符合条件的时候抛出错误
     */
    void addMember(Long uid, String orderId, Boolean needInsurance) throws BusinessException;


}
