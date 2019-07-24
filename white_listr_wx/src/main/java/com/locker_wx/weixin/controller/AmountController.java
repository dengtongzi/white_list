package com.locker_wx.weixin.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.locker_wx.base.AmountEnum;
import com.locker_wx.util.ReturnResult;
import com.locker_wx.util.ValidateUtil;
import com.locker_wx.weixin.service.AmountRecordService;

import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  金额类控制器
 * </p>
 *
 * @author dengtongzi
 * @since 2019-07-01
 */
@Controller
@RequestMapping("/amount")
public class AmountController {
	
	@Autowired
	private AmountRecordService amountRecordService;
	
	/**
	 * 预付押金页面
	 * @param request
	 * @param session
	 * @param map
	 * @return
	 */
	@RequestMapping("/walletindexPage")
	public String walletindexPage(HttpServletRequest request , HttpSession session, Map<String, Object> map) {
		return "wallet/walletindex";
	}
	/**
	 * 预付押金页面
	 * @param request
	 * @param session
	 * @param map
	 * @return
	 */
	@RequestMapping("/advancePayPage")
	public String advancePayPage(HttpServletRequest request , HttpSession session, Map<String, Object> map) {
		return "package/advancePay";
	}
	
	/**
	 * 支付金额页面
	 * @param request
	 * @param session
	 * @param map
	 * @return
	 */
	@RequestMapping("/paymentPage")
	public String paymentPage(HttpServletRequest request , HttpSession session, Map<String, Object> map) {
		return "package/payment";
	}
	
	/**
	 * 充值操作
	 * @param money
	 * @param request
	 * @param session
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/recharge")
    public ReturnResult recharge(@RequestParam("money") String money,HttpServletRequest request, HttpSession session, Map<String, Object> map) {
		if(StringUtils.isBlank(money)) {
			return ReturnResult.fail("金额不能为空");
		}else if(!ValidateUtil.validateMoney(money)) {
			return ReturnResult.fail("请填写正确的金额");
		}else {
			BigDecimal amount = new  BigDecimal(Integer.parseInt(money));
			return amountRecordService.createOrders(AmountEnum.BUSINESS_NAME_RECHARGE.getName(), AmountEnum.BUSINESS_TYPE_INCOME.getIndex(), amount, AmountEnum.BUSINESS_STATUS_ARRIVAL.getIndex());
		}
	}
	/**
	 * test
	 * @param money
	 * @param request
	 * @param session
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/test")
	public ReturnResult test(HttpServletRequest request, HttpSession session, Map<String, Object> map) {
		return ReturnResult.ok();
	}
	
	/**
	 * 预付押金操作
	 * @param money
	 * @param request
	 * @param session
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/advancePay")
	public ReturnResult advancePay(@RequestParam("money") String money,HttpServletRequest request, HttpSession session, Map<String, Object> map) {
		if(StringUtils.isBlank(money)) {
			return ReturnResult.fail("金额不能为空");
		}else if(!ValidateUtil.validateMoney(money)) {
			return ReturnResult.fail("请填写正确的金额");
		}else {
			BigDecimal amount = new  BigDecimal(Integer.parseInt(money));
			return amountRecordService.createOrders(AmountEnum.BUSINESS_NAME_ADVANCEPAY.getName(), AmountEnum.BUSINESS_TYPE_INCOME.getIndex(), amount, AmountEnum.BUSINESS_STATUS_ARRIVAL.getIndex());
		}
	}
	
}

