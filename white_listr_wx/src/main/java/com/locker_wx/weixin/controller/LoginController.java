package com.locker_wx.weixin.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* @author 作者 dengtongzi
* @describe
* @version 创建时间：2019年7月20日 下午3:39:26
*/
@Controller
@RequestMapping("/login")
public class LoginController {
	/**
	 * 我的钱包页面
	 * @param request
	 * @param session
	 * @param map
	 * @return
	 */
	@RequestMapping("")
	public String advancePayPage(HttpServletRequest request , HttpSession session, Map<String, Object> map) {
		return "login";
	}
	@RequestMapping("choujiang")
	public String choujiang(HttpServletRequest request , HttpSession session, Map<String, Object> map) {
		return "choujiang";
	}
	
}
