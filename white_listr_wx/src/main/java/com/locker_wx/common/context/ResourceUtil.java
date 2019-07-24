package com.locker_wx.common.context;

import javax.servlet.http.HttpSession;

import com.locker_wx.base.Constants;
import com.locker_wx.weixin.model.WxUser;

/**
* @author 作者 dengtongzi
* @describe
* @version 创建时间：2019年7月1日 下午11:08:11
*/
public class ResourceUtil {

	/**
	 * & 获取当前在线用户
	 * 
	 * @return
	 */
	public static final WxUser getSessionUser() {
		WxUser wxUser = null;
		HttpSession session = ContextHolderUtils.getSession();
		if (session.getAttribute(Constants.CURRENT_USER) != null) {
			wxUser = (WxUser) session.getAttribute(Constants.CURRENT_USER);
		} 
		return wxUser;
	}
}
