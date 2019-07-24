package com.locker_wx.weixin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.locker_wx.base.Constants;


/**
* @author 作者 dengtongzi
* @describe
* @version 创建时间：2019年7月22日 下午8:16:21
*/
@Controller
@RequestMapping("/lottery")
public class LotteryController {
	
	@RequestMapping("lotteryPage")
	public String lotteryPage(HttpServletRequest request , HttpSession session, Map<String, Object> map) {
		if(session.getAttribute(Constants.CURRENT_USER) == null) {
			Map<String,String> user = new HashMap<String,String>();
			user.put("openid", request.getParameter("openid"));
			session.setAttribute(Constants.CURRENT_USER, user);
			//过期时间为一个月
			session.setMaxInactiveInterval(2592000);
		}
		
		return "lottery_draw/lottery_draw";
	}
	
	@RequestMapping("lotteryIndex")
	public String lotteryIndex(HttpServletRequest request , HttpSession session, Map<String, Object> map) {
		if(session.getAttribute(Constants.CURRENT_USER) != null) {
			return "lottery_draw/lottery_draw";
		}
		return "lottery_draw/lottery_index";
	}
	
}
