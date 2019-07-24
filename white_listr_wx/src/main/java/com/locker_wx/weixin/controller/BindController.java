package com.locker_wx.weixin.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.locker_wx.base.Constants;
import com.locker_wx.common.context.UserUtil;
import com.locker_wx.util.ReturnResult;
import com.locker_wx.util.ValidateUtil;
import com.locker_wx.weixin.model.WxUser;
import com.locker_wx.weixin.service.SmsMeaasgeService;

/**
* @author 作者 dengtongzi
* @describe
* @version 创建时间：2019年6月27日 下午4:57:48
*/
@Controller
@RequestMapping("/bind")
public class BindController {
	
	@Autowired
	private SmsMeaasgeService smsMeaasgeService;
	
	@RequestMapping("/bindPhoneMessagePage")
    public String bindPhoneMessage(HttpServletRequest request , HttpSession session, Map<String, Object> map) {
		map.put("phone", request.getParameter("phone"));
		return "bind/bindPhoneMessage";
	}
	

	@ResponseBody
	@RequestMapping("/bindPhoneSendSms")
    public ReturnResult bindPhoneSendSms(@RequestParam("phone") String phone,HttpServletRequest request, HttpSession session, Map<String, Object> map) {
		if(StringUtils.isBlank(phone)) {
			return ReturnResult.fail("手机号码不能为空");
		}else if(!ValidateUtil.validatePhone(phone)) {
			return ReturnResult.fail("请填写正确的手机号码");
		}else {
			return smsMeaasgeService.sendSMS(phone);
		}
	}
	
	@ResponseBody
	@RequestMapping("/bindPhone")
	public ReturnResult bindPhone(@RequestParam("phone") String phone,@RequestParam("code") String code,
			HttpServletRequest request, HttpSession session, Map<String, Object> map) {
		if(StringUtils.isBlank(code)) {
			return ReturnResult.fail("验证码不能为空");
		}else {
			//WxUser weiXinUser = (WxUser) session.getAttribute("currentUser");
			ReturnResult returnResult = smsMeaasgeService.bindPhone(UserUtil.getOpenid(), code, phone);
			if(returnResult.getIntValue("state") == 1) {
				WxUser weiXinUser = (WxUser) session.getAttribute(Constants.CURRENT_USER);
				weiXinUser.setPhone(phone);
				session.setAttribute(Constants.CURRENT_USER, weiXinUser);
			}
			return returnResult;
		}
	}
	
	
}
