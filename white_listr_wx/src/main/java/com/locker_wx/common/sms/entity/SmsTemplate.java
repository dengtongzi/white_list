package com.locker_wx.common.sms.entity;

/**
* @author 作者 dengtongzi
* @describe
* @version 创建时间：2019年6月30日 上午10:41:15
*/
public class SmsTemplate {
	/**
	 * 短信验证码发送模板
	 * @return
	 */
	public static String codeTemplate(String code) {
		String codeTemplateStr = "欢迎注册易宝保，您的注册验证码是 %s，请在十分钟内完成注册。【易宝保】";
		return String.format(codeTemplateStr, code);
	}
}
