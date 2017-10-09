package cn.bangnongmang.server.business.common;

import java.util.List;

import com.pingplusplus.model.Charge;
import com.pingplusplus.model.Transfer;

import cn.bangnongmang.data.domain.Pocket;
import cn.bangnongmang.data.domain.PocketLog;
import cn.bangnongmang.server.io.BusinessException;

public interface PaymentBusiness {

	String CHANEL_CHARGE_TYPE_UPACP = "upacp";
	String CHANEL_CHARGE_TYPE_WX_APP = "wx_app";
	String CHANEL_CHARGE_TYPE_WX_PUB = "wx_pub";

	/**
	 * 
	 * 获取当前钱包金额，如果数据库中有就直接获取 pocket的信息，如果没有则创建一条金额为0的pocket记录
	 * 
	 * @Title getCurrentMoney
	 * 
	 * @param uid
	 * @return
	 */
	Pocket getCurrentMoney(Long uid);

	/**
	 * 
	 * 获取某个用户的钱包日志，如果没有应该返回一个size为0 的pocketLog
	 * 
	 * @Title getPocketLog
	 * 
	 * @param uid
	 * @return
	 */
	List<PocketLog> getPocketLog(Long uid);

	/**
	 * 
	 * 发起一条充值，并且得到充值所需要的参数，未完成充值之前钱包余额不会变动（未调用done）
	 * 
	 * @Title charge
	 * 
	 * @param uid
	 * @param channelType
	 * @param money
	 * @return
	 * @throws BusinessException
	 * 
	 */
	Charge charge(Long uid, String channelType, Integer money) throws BusinessException;

	/**
	 * 
	 * 通过银联渠道提现，提现会先进行扣款，如果提现最后失败，钱会退回钱包
	 * 
	 * @Title withdrawByUnionPay
	 * 
	 * @param uid
	 * @param money
	 * @param cardNumber
	 * @param openBank
	 * @return
	 * @throws BusinessException
	 */
	Transfer withdrawByUnionPay(Long uid, int money, String cardNumber, String openBank) throws
			BusinessException;

	/**
	 * 
	 * 通过公众号提现，提现会先进行扣款，如果提现最后失败，钱会退回钱包
	 * 
	 * @Title withdrawByOc
	 * 
	 * @param uid
	 * @param money
	 * @return
	 * @throws BusinessException
	 */
	Transfer withdrawByOc(Long uid, int money) throws BusinessException;

	/**
	 * 
	 * 通过TradeOrderId来完成某条pocketLog
	 * 
	 * @Title doneTradeFlowByTradeOrderId
	 * 
	 * @param tradeOrderId
	 * @throws BusinessException
	 *             没有找到对应的pocket抛错 没有pocketLog状态错误会抛错
	 */
	void doneTradeFlowByTradeOrderId(String tradeOrderId) throws BusinessException;

	/**
	 * 
	 * 通过TradeOrderId来取消某条pocketLog
	 * 
	 * @Title cancelTradeFlowByTradeOrderId
	 * 
	 * @param tradeOrderId
	 * @throws BusinessException
	 *             没有找到对应的pocket抛错 没有pocketLog状态错误会抛错
	 */
	void cancelTradeFlowByTradeOrderId(String tradeOrderId) throws BusinessException;

}
