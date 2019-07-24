package com.locker_wx.weixin.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.locker_wx.base.Constants;
import com.locker_wx.util.UUIDgenerator;
import com.locker_wx.weixin.mapper.WxAmountMapper;
import com.locker_wx.weixin.mapper.WxUserMapper;
import com.locker_wx.weixin.model.WxAmount;
import com.locker_wx.weixin.model.WxUser;
import com.locker_wx.weixin.service.WxUserService;
import com.locker_wx.weixin.util.WeiXinUtils;

import net.sf.json.JSONObject;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dengtongzi
 * @since 2019-06-25
 */
@Service
public class WxUserServiceImpl extends ServiceImpl<WxUserMapper, WxUser> implements WxUserService {

	@Autowired
	private WxUserMapper wxUserMapper;
	
	@Autowired
	private WxAmountMapper wxAmountMapper;

	/**
	 * 获取微信用户的信息
	 * 
	 * @param accessToken
	 * @param openId
	 * @return
	 */
	@Override
	public WxUser getUserInfo(String accessToken, String openId) {
		// 拼接获取用户信息接口的请求地址
		String requestUrl = Constants.GET_WEIXIN_USER_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID",
				openId);
		// 获取用户信息(返回的是Json格式内容)
		JSONObject jsonObject = WeiXinUtils.doGetStr(requestUrl);

		if (null != jsonObject) {
			try {
				// 封装获取到的用户信息
				WxUser weixinUserInfo = new WxUser();
				String userId = UUIDgenerator.getRandomStr();
				String openid = jsonObject.getString("openid");
				weixinUserInfo.setUserId(userId);
				weixinUserInfo.setOpenid(openid);
				weixinUserInfo.setNickname(jsonObject.getString("nickname"));
				weixinUserInfo.setHeadimgurl(jsonObject.getString("headimgurl"));
				weixinUserInfo.setSex(jsonObject.getInt("sex"));
				weixinUserInfo.setSubscribe(jsonObject.getInt("subscribe"));
				weixinUserInfo.setCity(jsonObject.getString("city"));
				weixinUserInfo.setProvince(jsonObject.getString("province"));
				weixinUserInfo.setCountry(jsonObject.getString("country"));
				weixinUserInfo.setSubscribetime(jsonObject.getString("subscribe_time"));
				weixinUserInfo.setLanguage(jsonObject.getString("language"));
				wxUserMapper.insert(weixinUserInfo);
				
				WxAmount wxAmount = new WxAmount();
				wxAmount.setUserId(userId);
				wxAmount.setOpenid(openid);
				wxAmount.setCreateTime(new Date());
				wxAmountMapper.insert(wxAmount);
				return weixinUserInfo;
			} catch (Exception e) {
				if (0 == jsonObject.getInt("subscribe")) {
					System.out.println("用户并没有关注本公众号");
				} else {
					String errorCode = jsonObject.getString("errcode");
					String errorMsg = jsonObject.getString("errmsg");
					System.out.println("由于" + errorCode + "错误码；错误信息为：" + errorMsg + "；导致获取用户信息失败");
				}
			}
		}
		return null;

	}

	/**
	 * 进行用户授权，获取到需要的授权字段，比如openId
	 * 
	 * @param code 识别得到用户id必须的一个值 得到网页授权凭证和用户id
	 * @return
	 */
	@Override
	public Map<String, String> oauth2GetOpenid(String code) {
		// 自己的配置appid（公众号进行查阅）
		String appid = Constants.WX_APPID;
		// 自己的配置APPSECRET;（公众号进行查阅）
		String appsecret = Constants.WX_APPSECRET;
		// 拼接用户授权接口信息
		String requestUrl = Constants.GET_WEBAUTH_URL.replace("APPID", appid).replace("SECRET", appsecret)
				.replace("CODE", code);
		// 存储获取到的授权字段信息
		Map<String, String> result = new HashMap<String, String>();
		try {
			JSONObject OpenidJSONO = WeiXinUtils.doGetStr(requestUrl);
			// OpenidJSONO可以得到的内容：access_token expires_in refresh_token openid scope
			String Openid = String.valueOf(OpenidJSONO.get("openid"));
			String AccessToken = String.valueOf(OpenidJSONO.get("access_token"));
			// 用户保存的作用域
			String Scope = String.valueOf(OpenidJSONO.get("scope"));
			String refresh_token = String.valueOf(OpenidJSONO.get("refresh_token"));
			result.put("Openid", Openid);
			result.put("AccessToken", AccessToken);
			result.put("scope", Scope);
			result.put("refresh_token", refresh_token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(UUIDgenerator.getRandomStr());
	}

}
