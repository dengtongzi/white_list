package com.locker_wx.weixin.service.impl;

import com.locker_wx.weixin.model.AmountRecord;
import com.locker_wx.weixin.model.WxAmount;
import com.locker_wx.base.AmountEnum;
import com.locker_wx.common.context.UserUtil;
import com.locker_wx.util.ReturnResult;
import com.locker_wx.util.UUIDgenerator;
import com.locker_wx.weixin.mapper.AmountRecordMapper;
import com.locker_wx.weixin.mapper.WxAmountMapper;
import com.locker_wx.weixin.service.AmountRecordService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dengtongzi
 * @since 2019-07-01
 */
@Service
public class AmountRecordServiceImpl extends ServiceImpl<AmountRecordMapper, AmountRecord> implements AmountRecordService {

	@Autowired
	private AmountRecordMapper amountRecordMapper;
	
	@Autowired
	private WxAmountMapper wxAmountMapper;
	
	@Override
	@Transactional
	public ReturnResult createOrders(String businessName,Integer businessType,BigDecimal amount,Integer status) {
		WxAmount wxAmount = wxAmountMapper.selectById(UserUtil.getUserId());
		BigDecimal newAmount = new BigDecimal("0.00");
		//先判断有没有钱包,没有则新增一个。如果有钱包，就修改金额
		if(wxAmount == null) {
			wxAmount = new WxAmount();
			wxAmount.setUserId(UserUtil.getUserId());
			wxAmount.setOpenid(UserUtil.getOpenid());
			wxAmount.setAmount(amount);
			wxAmount.setCreateTime(new Date());
			wxAmountMapper.insert(wxAmount);
		}else {
			//先查出旧的金额
			BigDecimal oldAmount = wxAmount.getAmount();
			//先判断是收入还是支出
			if(businessType.equals(AmountEnum.BUSINESS_TYPE_INCOME.getIndex())) {
				//收入相加
				newAmount = oldAmount.add(amount);
			}else {
				//支出相减，要先在入参时就判断旧的金额要大于当前金额才能相减
				newAmount = oldAmount.subtract(amount);
			}
			wxAmount.setAmount(newAmount);
			wxAmountMapper.updateById(wxAmount);
		}
		
		//添加流水记录
		AmountRecord amountRecord = new AmountRecord();
		amountRecord.setRecordId(UUIDgenerator.getOrderId());
		amountRecord.setAmountId(UserUtil.getUserId());
		amountRecord.setBusinessName(businessName);
		amountRecord.setBusinessType(businessType);
		amountRecord.setAmount(amount);
		amountRecord.setStatus(status);
		amountRecord.setSuccessBalance(newAmount);
		amountRecord.setCreateTime(new Date());
		amountRecordMapper.insert(amountRecord);
		return ReturnResult.ok("充值成功");
	}

}
