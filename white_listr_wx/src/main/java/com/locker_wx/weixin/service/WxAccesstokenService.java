package com.locker_wx.weixin.service;

import com.baomidou.mybatisplus.service.IService;
import com.locker_wx.weixin.model.WxAccesstoken;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dengtongzi
 * @since 2019-06-26
 */
public interface WxAccesstokenService extends IService<WxAccesstoken> {
	public String getAccesstoken();
}
