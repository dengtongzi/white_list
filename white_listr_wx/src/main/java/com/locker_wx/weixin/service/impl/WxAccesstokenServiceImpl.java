package com.locker_wx.weixin.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.locker_wx.util.DateUtils;
import com.locker_wx.weixin.mapper.WxAccesstokenMapper;
import com.locker_wx.weixin.model.WxAccesstoken;
import com.locker_wx.weixin.service.WxAccesstokenService;
import com.locker_wx.weixin.util.WeiXinUtils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dengtongzi
 * @since 2019-06-26
 */
@Service
public class WxAccesstokenServiceImpl extends ServiceImpl<WxAccesstokenMapper, WxAccesstoken> implements WxAccesstokenService {

	@Autowired
	private WxAccesstokenMapper wxAccesstokenMapper;
	
	@Override
	public String getAccesstoken() {
		WxAccesstoken wxAccesstoken = wxAccesstokenMapper.selectById(1);
		//如果初次检查表数据为0，则请求微信获取一条accesstoken
		if(wxAccesstoken == null) {
			WxAccesstoken newWxAccesstoken = WeiXinUtils.getAccessToken();
			newWxAccesstoken.setCreateTime(new Date());
			newWxAccesstoken.setId(1);
			wxAccesstokenMapper.insert(newWxAccesstoken);
			return newWxAccesstoken.getToken();
		}else {
			//如果数据库表数据不为空且不过期，则直接返回accesstoken
			Date createTime = wxAccesstoken.getCreateTime();
			Integer expiresIn = wxAccesstoken.getExpiresIn();
			Long DatePoor = (new Date().getTime() - createTime.getTime())/1000;
			//如果数据库表数据不为空且过期，则请求微信获取一条accesstoken并保存到表中
			if(DatePoor.intValue() > expiresIn) {
				wxAccesstokenMapper.deleteById(1);
				wxAccesstoken = WeiXinUtils.getAccessToken();
				wxAccesstoken.setCreateTime(new Date());
				wxAccesstoken.setId(1);
				wxAccesstokenMapper.insert(wxAccesstoken);
			}
			return wxAccesstoken.getToken();
		}
	}

}
