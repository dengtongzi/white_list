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
 * 
 * </p>
 *
 * @author dengtongzi
 * @since 2019-07-01
 */
@Data
@Accessors(chain = true)
@TableName("amount_record")
public class AmountRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 交易记录ID
     */
    @TableId("RECORD_ID")
    private String recordId;
    /**
     * 钱包ID
     */
    @TableField("AMOUNT_ID")
    private String amountId;
    /**
     * 交易名称
     */
    @TableField("BUSINESS_NAME")
    private String businessName;
    /**
     * 收支类型（0支出，1收入）
     */
    @TableField("BUSINESS_TYPE")
    private Integer businessType;
    /**
     * 交易金额
     */
    @TableField("AMOUNT")
    private BigDecimal amount;
    /**
     * 交易状态（0未入账，1入账）
     */
    @TableField("STATUS")
    private Integer status;
    /**
     * 交易成功余额
     */
    @TableField("SUCCESS_BALANCE")
    private BigDecimal successBalance;
    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;


}
