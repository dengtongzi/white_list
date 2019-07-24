package com.locker_wx.common.context;

import com.locker_wx.weixin.model.WxUser;

/**
* @author 作者 dengtongzi
* @describe
* @version 创建时间：2019年7月1日 下午11:08:11
*/
public class UserUtil {
	/**
	 * 获取登录用户
	 * 
	 * @return
	 */
	public static WxUser getUser() {
		WxUser wxUser = ResourceUtil.getSessionUser();
		if (wxUser == null) {
			return null;
		}
		return wxUser;
	}

	/**
	 * 获取登录用户的id(账号)
	 * 
	 * @return
	 */
	public static String getUserId() {
		WxUser wxUser = ResourceUtil.getSessionUser();
		if (wxUser == null) {
			return null;
		}
		return wxUser.getUserId();
	}
	
	/**
	 * 获取登录用户的昵称
	 * 
	 * @return
	 */
	public static String getNickname() {
		WxUser wxUser = ResourceUtil.getSessionUser();
		if (wxUser == null) {
			return null;
		}
		return wxUser.getNickname();
	}
	
	/**
	 * 获取登录用户的Openid
	 * 
	 * @return
	 */
	public static String getOpenid() {
		WxUser wxUser = ResourceUtil.getSessionUser();
		if (wxUser == null) {
			return null;
		}
		return wxUser.getOpenid();
	}
	
	/**
	 * 获取登录用户的手机号码
	 * 
	 * @return
	 */
	public static String getPhone() {
		WxUser wxUser = ResourceUtil.getSessionUser();
		if (wxUser == null) {
			return null;
		}
		return wxUser.getPhone();
	}

}
