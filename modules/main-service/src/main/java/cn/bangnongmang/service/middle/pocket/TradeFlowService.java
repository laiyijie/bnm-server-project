package cn.bangnongmang.service.middle.pocket;

import java.util.List;
import cn.bangnongmang.data.domain.Pocket;
import cn.bangnongmang.data.domain.PocketLog;
import cn.bangnongmang.service.service.exception.ServiceLayerExeption;

/**
 * 
 * 新的支付系统，修改支付的流程
 * 
 * @ClassName TradeFlowService
 * @author laiyijie
 * @date 2017年2月22日 下午6:10:32
 *
 */
public interface TradeFlowService {

	int POCKETLOG_STATE_DRAFT = 100;
	int POCKETLOG_STATE_DONE = 200;
	int POCKETLOG_STATE_CANCEL = 300;

	int POCKETLOG_TYPE_INCOME = 80;
	int POCKETLOG_TYPE_REFUND = 90;

	// 保证金退回
	int POCKETLOG_TYPE_REFUND_INSURANCE = 70;

	// 普通的付款，付給系統
	int POCKETLOG_TYPE_PAY = 300;
	// 备付金扣款和交备付金
	int POCKETLOG_TYPE_PROVISIONS_PAY = 600;
	int POCKETLOG_TYPE_PROVISIONS = 1000;
	// 机手支付保证金
	int POCKETLOG_TYPE_PAY_INSURANCE = 800;
	// 罚钱，扣除一部分保证金
	int POCKETLOG_TYPE_PUNISH_INSURANCE = 2000;
	// 机手等待入账解冻
	int POCKETLOG_TYPE_INCOME_UN_FREEZE = 1100;
	//机手等待入账扣除
	int POCKETLOG_TYPE_INCOME_PUNISH = 2100;
	// 充值提现
	int POCKETLOG_TYPE_RECHARGE = 100;
	int POCKETLOG_TYPE_WITHDRAW = 200;

	// 对应的是充值提现的不同方式
	Integer POCKETLOG_METHOD_WECHAT = 100;
	Integer POCKETLOG_METHOD_WECHAT_APP = 200;
	Integer POCKETLOG_METHOD_WECHAT_OC = 300;
	Integer POCKETLOG_METHOD_REDPACKET = 400;
	Integer POCKETLOG_METHOD_UNIONPAY = 500;
	// 其他的默认都是这个
	Integer POCKETLOG_METHOD_DEFAULT = 10;

	/**
	 *  交易流水取消（未完成超时或其他）
	 * @param logID pocketLog 的id
	 * @return 成功返回true
	 * @throws ServiceLayerExeption 操作出错抛错
	 */
	Boolean cancelTradeFlow(String logID) throws ServiceLayerExeption;

	/**
	 *  交易流水完成
	 * @param logID pocketLog id
	 * @return 成功返回true
	 * @throws ServiceLayerExeption 又不符合条件的时候抛错
	 */
	Boolean doneTradeFlow(String logID) throws ServiceLayerExeption;

	/**
	 * 创建一条流水
	 * @param uid 用户的uid
	 * @param type 交易的类型
	 * @param money 交易金额，单位分
	 * @param method 交易的方式
	 * @param detail 交易细节
	 * @param trade_order_id 外部订单id
 	 * @param connect_order_id 相关的orderFarmerId
	 * @return 返回一条记录
	 * @throws ServiceLayerExeption 检测除了问题就会
	 */
	PocketLog createTradeFlow(Long uid, Integer type, Integer money, Integer method, String
			detail,
			String trade_order_id, String connect_order_id) throws ServiceLayerExeption;

	/**
	 * 更新外部订单的id
	 * @param logID 流水的ID号
	 * @param tradeOrderId 外部订单号
	 * @return 如果成功返回true
	 */
	Boolean updateTradeOrderIdByLogId(String logID, String tradeOrderId);

	/**
	 * 根据外部订单号获取Logid
	 * @param tradeOrderId 外部订单号
 	 * @return 返回一条记录，若果没有就返回空
	 */
	PocketLog getPocketLogByTradeOrderId(String tradeOrderId);

	/**
	 * 获取当前钱包信息
	 * @param uid 用户的id
	 * @return 返回钱包的记录
	 */
	Pocket getPocketInfo(Long uid);

	/**
	 * 创建钱包
	 * @param uid 用户的ID
	 * @return 返回钱包记录
	 */
	Pocket createPocket(Long uid);

	/**
	 * 根据外部订单号，获取没有确认的记录
	 * @param outTradeOrderId 外部订单号
	 * @param method 交易方式
	 * @return 有就返回一条记录，否则返回空
	 */
	PocketLog getAllUnensuredRechargeLogByMethodAndOutTradeOrderId(String outTradeOrderId, int method);

	/**
	 * 根据交易方式返回所有没有确认的订单记录
	 * @param method 交易方式
	 * @return 如果有就返回这条log ，否则返回false
	 */
	List<PocketLog> getAllUnensuredRechargeLogByMethod(int method);

	/**
	 * 根据uid获取这个人的所有的log
	 * @param uid 用户的uid
	 * @return 返回一个log的list
	 */
	List<PocketLog> getPocketLog(Long uid);

	/**
	 * 根据orderFarmerId和
	 * @param uid 用户的id
	 * @param connectOrderId
	 * @param type
	 * @param state
	 * @return
	 */
	List<PocketLog> getPocketLogByUidAndConnectOrderIdAndTypeAndState(Long uid, String
			connectOrderId,
			int type, Integer state);

	/**
	 * 根据订单id计算用户在当前订单下还有多少钱没有解冻
	 * @param uid
	 * @param connectOrderId
	 * @return
	 */
	Integer countMyUnfreezeOrderWaitInMoneyByOrderId(Long uid,String connectOrderId);
}
