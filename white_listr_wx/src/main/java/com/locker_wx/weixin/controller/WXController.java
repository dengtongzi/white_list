package com.locker_wx.weixin.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.locker_wx.weixin.service.WxAccesstokenService;
import com.locker_wx.weixin.util.WeiXinUtils;

/**
 * @author 作者 dengtongzi
 * @describe
 * @version 创建时间：2019年6月20日 下午5:09:56
 */
@Controller
@RequestMapping("/")
public class WXController {
	
	@Autowired
	private WxAccesstokenService wxAccesstokenService;

	@RequestMapping("/userPage")
	public String toUser(HttpServletRequest request) {
		return "user/user";
	}

	@RequestMapping("index")
	public String index(Map<String, Object> map) {
		map.put("user", "Tyrone");
		return "index";
	}

	@ResponseBody
	@RequestMapping("getSign")
	public JSONObject getSign(HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		String url = request.getParameter("putPackageUrl");
		Map<String, String> ret = WeiXinUtils.sign(url,wxAccesstokenService.getAccesstoken());
		System.out.println("map=" + ret.toString());
		jsonObject.put("weixin", ret);
		return jsonObject;
	}
}
