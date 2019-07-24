package com.locker_wx.weixin.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.locker_wx.util.HttpClient;

/**
 * @author oy
 * @description 微信支付工具类
 * @date 2019/4/19
 */
public class WeiXinPayUtils {

	// 获取配置文件中的参数 返回map
	/**
	 * 读取文件中的配置
	 */
	public Map<String, String> readProperties() {
		Map<String, String> map = new HashMap<>();
		InputStream in = null;
		try {
			// 从文件中读取配置内容
			Properties properties = new Properties();
			// 读取属性文件a.properties
			in = this.getClass().getClassLoader().getResourceAsStream("weixinPay.properties");
			properties.load(in);
			Iterator<String> it = properties.stringPropertyNames().iterator();
			while (it.hasNext()) {
				String key = it.next();
				map.put(key, properties.getProperty(key));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	/**
	 * 统一下单
	 * 
	 * @author oy
	 * @date 2019/4/19
	 * @param out_trade_no：交易订单号, total_fee：金额（分）
	 * @return java.util.Map
	 */
	public static Map createNative(String out_trade_no, String total_fee) {
		// 读取配置文件
		WeiXinPayUtils utils = new WeiXinPayUtils();
		Map<String, String> properties = utils.readProperties();

		// 1.参数封装
		Map param = new HashMap();
		param.put("appid", properties.get("appid"));// 公众账号ID
		param.put("mch_id", properties.get("partner"));// 商户
		param.put("nonce_str", WXPayUtil.generateNonceStr());// 随机字符串
		param.put("body", "鸵鸟测试支付");
		param.put("out_trade_no", out_trade_no);// 交易订单号
		param.put("total_fee", total_fee);// 金额（分）
		param.put("spbill_create_ip", "127.0.0.1");
		param.put("notify_url", properties.get("url"));// 公众号的网址
		param.put("trade_type", "NATIVE");// 交易类型

		try {
			String xmlParam = WXPayUtil.generateSignedXml(param, properties.get("partnerkey"),
					WXPayConstants.SignType.MD5);// 生成签名 MD5方式 并将参数弄成xml形式
			System.out.println("请求的参数：" + xmlParam);

			// 2.发送请求
			HttpClient httpClient = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
			httpClient.setHttps(true);
			httpClient.setXmlParam(xmlParam);
			httpClient.post();

			// 3.获取结果
			String xmlResult = httpClient.getContent();

			Map<String, String> mapResult = WXPayUtil.xmlToMap(xmlResult);
			System.out.println("微信返回结果" + mapResult);
			Map map = new HashMap<>();
			map.put("code_url", mapResult.get("code_url"));// 生成支付二维码的链接
			map.put("out_trade_no", out_trade_no);
			map.put("total_fee", total_fee);

			return map;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new HashMap();
		}

	}

	/**
	 * 查询订单
	 * 
	 * @author oy
	 * @date 2019/4/19
	 * @param out_trade_no：交易订单号
	 * @return java.util.Map
	 */
	public static Map queryPayStatus(String out_trade_no) {
		// 读取配置文件
		WeiXinPayUtils utils = new WeiXinPayUtils();
		Map<String, String> properties = utils.readProperties();

		// 1.封装参数
		Map param = new HashMap();
		param.put("appid", properties.get("appid"));
		param.put("mch_id", properties.get("partner"));
		param.put("out_trade_no", out_trade_no);
		param.put("nonce_str", WXPayUtil.generateNonceStr());
		try {
			String xmlParam = WXPayUtil.generateSignedXml(param, properties.get("partnerkey"));
			// 2.发送请求
			HttpClient httpClient = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
			httpClient.setHttps(true);
			httpClient.setXmlParam(xmlParam);
			httpClient.post();

			// 3.获取结果
			String xmlResult = httpClient.getContent();
			Map<String, String> map = WXPayUtil.xmlToMap(xmlResult);
			System.out.println("调动查询API返回结果：" + xmlResult);
			return map;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

}
