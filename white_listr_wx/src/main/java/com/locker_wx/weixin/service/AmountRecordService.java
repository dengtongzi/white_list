package com.locker_wx.weixin.service;

import com.locker_wx.util.ReturnResult;
import com.locker_wx.weixin.model.AmountRecord;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dengtongzi
 * @since 2019-07-01
 */
public interface AmountRecordService extends IService<AmountRecord> {

	public ReturnResult createOrders(String businessName,Integer businessType,BigDecimal amount,Integer status);

}
