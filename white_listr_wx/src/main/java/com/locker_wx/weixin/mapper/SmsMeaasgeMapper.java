package com.locker_wx.weixin.mapper;

import com.locker_wx.weixin.model.SmsMeaasge;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dengtongzi
 * @since 2019-06-30
 */
public interface SmsMeaasgeMapper extends BaseMapper<SmsMeaasge> {
	
	@Select("select ID as id,SMS_CONTENT as smsContent,SEND_PHONE as sendPhone,REFRESH_TIME as refreshTime,"
			+ " EFFECTIVE_TIME as effectiveTime,SEND_TIME as sendTime,SMS_TYPE as smsType,CODE as code"
			+ " from sms_meaasge where SEND_PHONE = #{phone} order by SEND_TIME")
	public List<SmsMeaasge> selectPhoneSendSms(@Param("phone") String phone);

}
