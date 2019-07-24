package com.locker_wx.weixin.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* @author 作者 dengtongzi
* @describe 钱包控制器
* @version 创建时间：2019年7月10日 下午10:30:12
*/
@Controller
@RequestMapping("/wallet")
public class WalletController {
	/**
	 * 我的钱包页面
	 * @param request
	 * @param session
	 * @param map
	 * @return
	 */
	@RequestMapping("/walletindexPage")
	public String advancePayPage(HttpServletRequest request , HttpSession session, Map<String, Object> map) {
		return "wallet/walletindex";
	}
	
	/**
	 * 充值页面
	 * @param request
	 * @param session
	 * @param map
	 * @return
	 */
	@RequestMapping("/rechargePage")
	public String rechargePage(HttpServletRequest request , HttpSession session, Map<String, Object> map) {
		return "wallet/recharge";
	}
	
	/**
	 * 账单明细页面
	 * @param request
	 * @param session
	 * @param map
	 * @return
	 */
	@RequestMapping("/billPage")
	public String billPage(HttpServletRequest request , HttpSession session, Map<String, Object> map) {
		return "wallet/bill";
	}
	
	/**
	 * 退款页面
	 * @param request
	 * @param session
	 * @param map
	 * @return
	 */
	@RequestMapping("/refundPage")
	public String refundPage(HttpServletRequest request , HttpSession session, Map<String, Object> map) {
		return "wallet/refund";
	}
	
	/**
	 * 提现页面
	 * @param request
	 * @param session
	 * @param map
	 * @return
	 */
	@RequestMapping("/withdrawalPage")
	public String withdrawalPage(HttpServletRequest request , HttpSession session, Map<String, Object> map) {
		return "wallet/withdrawal";
	}
}
