package com.locker_wx.weixin.service.impl;

import com.locker_wx.weixin.model.SmsMeaasge;
import com.locker_wx.weixin.model.WxUser;
import com.locker_wx.base.Constants;
import com.locker_wx.common.context.UserUtil;
import com.locker_wx.common.sms.MchuanMessageUtil;
import com.locker_wx.common.sms.entity.SmsTemplate;
import com.locker_wx.util.ReturnResult;
import com.locker_wx.weixin.mapper.SmsMeaasgeMapper;
import com.locker_wx.weixin.mapper.WxUserMapper;
import com.locker_wx.weixin.service.SmsMeaasgeService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dengtongzi
 * @since 2019-06-30
 */
@Service
public class SmsMeaasgeServiceImpl extends ServiceImpl<SmsMeaasgeMapper, SmsMeaasge> implements SmsMeaasgeService {
	
	@Autowired
	private SmsMeaasgeMapper smsMeaasgeMapper;
	
	@Autowired
	private WxUserMapper wxUserMapper;
	
	@Override
	@Transactional
	public ReturnResult sendSMS(String phone) {
		List<SmsMeaasge> smsMeaasgeList = smsMeaasgeMapper.selectPhoneSendSms(phone);
		Map<String, Integer> map = new HashMap<String, Integer>();
		if(smsMeaasgeList.isEmpty()) {
			return send(phone);
		}else {
			SmsMeaasge smsMeaasge = smsMeaasgeList.get(0);
			/*
			 * if((new Date().getTime() - smsMeaasge.getSendTime().getTime())/1000 >
			 * smsMeaasge.getEffectiveTime() ) { return ReturnResult.fail("验证码已过期"); }
			 */
			if((new Date().getTime() - smsMeaasge.getSendTime().getTime())/1000 > smsMeaasge.getRefreshTime()){
				return send(phone);
			}else {
				Long time = new Date().getTime() - smsMeaasge.getSendTime().getTime();
				map.put("time", time.intValue());
				return ReturnResult.ok(map);
			}
		}
	}
	
	private ReturnResult send(String phone) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		String smsContent = SmsTemplate.codeTemplate(MchuanMessageUtil.vcode());
		List<String> phoneList = new ArrayList<String>();
		phoneList.add(phone);
		JSONObject resultMap = MchuanMessageUtil.send(phoneList, smsContent);
		if(resultMap == null) {
			return ReturnResult.fail("短信发送失败");
		}else {
			if(!resultMap.get("return").equals("0")) {
				return ReturnResult.fail("短信发送失败");
			}else {
				SmsMeaasge smsMeaasge = new SmsMeaasge();
				smsMeaasge.setId(resultMap.getString("msgid"));
				smsMeaasge.setSmsContent(smsContent);
				smsMeaasge.setSendPhone(phone);
				smsMeaasge.setRefreshTime(Constants.SMS_REFRESH_TIME);
				smsMeaasge.setEffectiveTime(Constants.SMS_EFFECTIVE_TIME);
				smsMeaasge.setSendTime(new Date());
				smsMeaasge.setSmsType(SmsMeaasge.VERIFY_CODE);
				smsMeaasge.setCode(MchuanMessageUtil.vcode());
				smsMeaasgeMapper.insert(smsMeaasge);
				map.put("time", Constants.SMS_REFRESH_TIME);
				return ReturnResult.ok();
			}
		}
	}

	@Override
	public ReturnResult bindPhone(String openid, String code, String phone) {
		List<SmsMeaasge> smsMeaasgeList = smsMeaasgeMapper.selectPhoneSendSms(phone);
		if(smsMeaasgeList.isEmpty()) {
			return ReturnResult.fail("验证码错误"); 
		}else {
			SmsMeaasge smsMeaasge = smsMeaasgeList.get(0);
			
			if(smsMeaasge.getCode().equals(code)) {
				if((new Date().getTime() - smsMeaasge.getSendTime().getTime())/1000 > smsMeaasge.getEffectiveTime() ) { 
					return ReturnResult.fail("验证码已过期"); 
				}else {
					WxUser user = new WxUser();
					user.setUserId(UserUtil.getUserId());
					user.setPhone(phone);
					wxUserMapper.updateById(user);
					return ReturnResult.ok("绑定成功"); 
				}
			}else {
				return ReturnResult.fail("验证码错误"); 
			}
			
			
		}
	}
}
