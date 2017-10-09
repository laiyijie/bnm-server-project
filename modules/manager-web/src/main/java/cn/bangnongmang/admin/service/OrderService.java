package cn.bangnongmang.admin.service;

import java.text.ParseException;

import org.springframework.web.bind.annotation.RequestMapping;

import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.data.domain.OptionCluster;

/**
 * 
 *  Description: 订单接口
 *  @author luchangquan  DateTime 2017年4月7日 上午10:44:29 
 *  @company bangnongmang 
 *  @email luchangquan@bangnongmang.cn  
 *  @version 1.0
 */
public interface OrderService {
	public boolean updateOptions(String orderId, String optionsJsonString) throws BusinessException ;
	
	public void updateOrder(long options, String orderId) throws BusinessException;
	
	public boolean testUpdateOrderMapping(long options) throws BusinessException;
	
	public boolean update(OptionCluster optionCluster) throws BusinessException;
	
	public boolean updatePrePayRate(String orderId, Integer payRate) throws BusinessException ;

	public boolean updateDriverInsurance(String orderId, Integer rate);

	public boolean updateCompanyBonus(String orderId,Integer rate);

	public boolean authOrderFarmer(String orderId) ;

	public String  updateDesireStartTime(String orderId ,long desireStartTime)throws BusinessException,ParseException;

	public String  updateuniPrices(String orderId ,Integer uniPrices)throws BusinessException;

	public String  updateDesireNum(String orderId ,Integer desireNum)throws BusinessException;

	public String  updateSize(String orderId ,double size)throws BusinessException;
	
}
