package com.locker_wx.weixin.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.locker_wx.weixin.model.WxUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dengtongzi
 * @since 2019-06-25
 */
public interface WxUserService extends IService<WxUser> {
	/**
     * 获取到微信个人用户的信息
     * @param accessToken
     * @param openId
     * @return
     */
	WxUser getUserInfo(String accessToken, String openId);

    /**
     *用于获取网页授权后的信息字段，其中主要是获取openId
     * @param code  授权码
     * @return
     */
    //Map<String , String > getAuthInfo(String code);

    /**
     * 进行网页授权的认证
     * @param code 授权码
     * @return
     */
    Map<String,String> oauth2GetOpenid(String code);
}
