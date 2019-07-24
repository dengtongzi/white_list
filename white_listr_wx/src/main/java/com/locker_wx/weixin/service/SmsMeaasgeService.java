package com.locker_wx.weixin.service;

import com.locker_wx.util.ReturnResult;
import com.locker_wx.weixin.model.SmsMeaasge;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dengtongzi
 * @since 2019-06-30
 */
public interface SmsMeaasgeService extends IService<SmsMeaasge> {
	
	public ReturnResult sendSMS(String phone);

	public ReturnResult bindPhone(String openid, String code, String phone);
}
