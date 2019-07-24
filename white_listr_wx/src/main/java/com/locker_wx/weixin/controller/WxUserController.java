package com.locker_wx.weixin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.locker_wx.base.Constants;
import com.locker_wx.common.context.UserUtil;
import com.locker_wx.weixin.model.WxUser;
import com.locker_wx.weixin.service.WxAccesstokenService;
import com.locker_wx.weixin.service.WxUserService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dengtongzi
 * @since 2019-06-25
 */
@Controller
@RequestMapping("/wx_user")
public class WxUserController {
	
	@Autowired
	private WxUserService wxUserService;
	
	@Autowired
	private WxAccesstokenService wxAccesstokenService;
	
	/**
	 * 进行网页授权，便于获取到用户的绑定的内容
	 * 
	 * @param request
	 * @param session
	 * @param map
	 * @return
	 */
	@RequestMapping("/userinfo")
	public String userinfo(HttpServletRequest request, HttpSession session, Map<String, Object> map) {
		// 首先判断一下session中，是否有保存着的当前用户的信息，有的话，就不需要进行重复请求信息
		WxUser weiXinUser = null;
		if (UserUtil.getUser() != null) {
			weiXinUser = wxUserService.selectById(UserUtil.getUserId());
		} else {
			/**
			 * 进行获取openId，必须的一个参数，这个是当进行了授权页面的时候，再重定向了我们自己的一个页面的时候，
			 * 会在request页面中，新增这个字段信息，要结合这个Constants.Get_WEIXINPAGE_Code这个常量思考
			 */
			String code = request.getParameter("code");
			try {
				// 得到当前用户的信息(具体信息就看weixinUser这个javabean)
				weiXinUser = getTheCode(session, code);
				// 将获取到的用户信息，放入到session中,并设置session过期时间为一个月
				session.setAttribute(Constants.CURRENT_USER, weiXinUser);
				session.setMaxInactiveInterval(2592000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(StringUtils.isBlank(weiXinUser.getPhone())) {
			return "bind/bindPhone";
		}else {
			map.put("headImgUrl", weiXinUser.getHeadimgurl());
			map.put("nickname", weiXinUser.getNickname());
			map.put("phone", weiXinUser.getPhone());
			return "user/user";
		}
	}

	/**
	 * 获取用户的openId
	 * 
	 * @param session
	 * @param code
	 * @return 返回封装的微信用户的对象
	 */
	private WxUser getTheCode(HttpSession session, String code) {
		Map<String, String> authInfo = new HashMap<>();
		String openId = "";
		if (code != null) {
			// 调用根据用户的code得到需要的授权信息
			authInfo = wxUserService.oauth2GetOpenid(code);
			// 获取到openId
			openId = authInfo.get("Openid");
			
			Map<String, Object> userMap = new HashMap<>();
			userMap.put("OPENID", openId);
			List<WxUser> list = wxUserService.selectByMap(userMap);
			if(!list.isEmpty()) {
				return list.get(0);
			}else {
				// 获取基础刷新的接口访问凭证（目前还没明白为什么用authInfo.get("AccessToken");这里面的access_token就不行）
				String accessToken = wxAccesstokenService.getAccesstoken();
				// 获取到微信用户的信息
				WxUser userinfo = wxUserService.getUserInfo(accessToken, openId);
				return userinfo;
			}
		}
		return null;
	}
	
	@RequestMapping("/bindPhoneMessagePage")
    public String putPackage(HttpServletRequest request , HttpSession session, Map<String, Object> map) {
		map.put("phone", request.getParameter("phone"));
        return "bind/bindPhoneMessage";
	}
	
}
