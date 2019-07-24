package com.locker_wx.weixin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.locker_wx.weixin.model.Button;
import com.locker_wx.weixin.model.Menu;
import com.locker_wx.weixin.model.SubButton;
import com.locker_wx.weixin.model.WxAccesstoken;
import com.locker_wx.weixin.util.WeiXinUtils;

/**
 * @author 作者 dengtongzi
 * @describe
 * @version 创建时间：2019年6月17日 下午5:33:12
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);

	public static void main(String[] args) {
		// 调用接口获取access_token
		WxAccesstoken at = WeiXinUtils.getAccessToken();

		if (null != at) {
			// 调用接口创建菜单
			int result = WeiXinUtils.createMenu(getMenu(), at.getToken());

			// 判断菜单创建结果
			if (0 == result)
				log.info("菜单创建成功！");
			else
				log.info("菜单创建失败，错误码：" + result);
		}
	}

	/**
	 * @describe 自定义微信菜单页面
	 * @return
	 */
	private static Menu getMenu() {
		Button but_left = new Button(); 
		but_left.setName("存包"); 
		but_left.setType("view");
		but_left.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxdb528b0c080dd7ad&redirect_uri=http://deng.natapp1.cc/locker_wx/package/putPackage?response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
		
		Button but_center = new Button(); 
		but_center.setName("取包"); 
		but_center.setType("view");
		but_center.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxdb528b0c080dd7ad&redirect_uri=http://deng.natapp1.cc/locker_wx/package/takePackage?response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
		
		SubButton subButton_one = new SubButton();
		subButton_one.setName("个人中心");
		subButton_one.setType("view");
		subButton_one.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxdb528b0c080dd7ad&redirect_uri=http://deng.natapp1.cc/locker_wx/wx_user/userinfo?response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
		
		SubButton subButton_two = new SubButton();
		subButton_two.setName("使用帮助");
		subButton_two.setType("view");
		subButton_two.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxdb528b0c080dd7ad&redirect_uri=http://deng.natapp1.cc/locker_wx/wx_user/userinfo?response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
		
		Button rig_right = new Button(); 
		rig_right.setName("个人中心+"); 
		rig_right.setSub_button(new SubButton[]{subButton_one,subButton_two});
		
		Menu menu = new Menu(); 
		menu.setButton(new Button[] { but_left, but_center,rig_right});
		return menu;
	}
	
}
