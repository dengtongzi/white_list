package com.locker_wx.base;

/**
 * @author 作者 dengtongzi
 * @describe 常量类
 * @version 创建时间：2019年6月18日 下午3:53:39
 */
public class Constants {
	
	/**
	 * session名称
	 */
	public static final String CURRENT_USER  = "currentUser";
	
	public static final String WX_APPID = "wxdb528b0c080dd7ad";
	
	public static final String WX_APPSECRET = "7ce7a43ad70a9e1521b920b7407578a5";
	
	/**
	 * 用于获取当前与微信公众号交互的用户信息的接口（一般是用第一个接口地址）
	 */
	public static final String GET_WEIXIN_USER_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
	
	public final static String GetPageUsersUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

	/**
	 * 用于进行网页授权验证的接口URL，通过这个才可以得到opendID等字段信息
	 */
	public final static String GET_WEBAUTH_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	
	/**
	 * 用于进行当点击按钮的时候，能够在网页授权之后获取到code，再跳转到自己设定的一个URL路径上的接口，这个主要是为了获取之后于
	 * 获取openId的接口相结合 注意：参数：toselfURL 表示的是当授权成功后，跳转到的自己设定的页面，所以这个要根据自己的需要进行修改
	 */
	public final static String Get_WEIXINPAGE_Code = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=toselfURL&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";
	
	/**
	 * 获取access_token的URL 接口地址（GET） 限200（次/天）
	 */
	public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	/**
	 * 菜单创建（POST） 限100（次/天）
	 */
	public static String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	/**
	 * 短信刷新时间（秒）
	 */
	public static final Integer SMS_REFRESH_TIME = 60;
	
	/**
	 * 短信有效时间（秒）
	 */
	public static final Integer SMS_EFFECTIVE_TIME = 600;
}