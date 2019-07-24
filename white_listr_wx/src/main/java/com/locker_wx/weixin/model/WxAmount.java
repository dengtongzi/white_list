package com.locker_wx.weixin.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 我的钱包余额
 * </p>
 *
 * @author dengtongzi
 * @since 2019-07-01
 */
@Data
@Accessors(chain = true)
@TableName("wx_amount")
public class WxAmount implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("USER_ID")
    private String userId;
    
    /**
     * 微信openId
     */
    @TableField("OPENID")
    private String openid;
    
    /**
     * 余额
     */
    @TableField("AMOUNT")
    private BigDecimal amount = new BigDecimal("0.00");
    
    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;

}
