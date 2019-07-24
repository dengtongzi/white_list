package com.locker_wx.weixin.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 短信表
 * </p>
 * @author dengtongzi
 * @since 2019-06-30
 */
@Data
@Accessors(chain = true)
@TableName("sms_meaasge")
public class SmsMeaasge implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * SMS_TYPE类型
     */
    public static final String VERIFY_CODE = "验证码";

    /**
     * 主键ID
     */
    @TableId("ID")
    private String id;
    /**
     * 短信内容
     */
    @TableField("SMS_CONTENT")
    private String smsContent;
    
    /**
     * 验证码
     */
    @TableField("CODE")
    private String code;
    /**
     * 发送电话
     */
    @TableField("SEND_PHONE")
    private String sendPhone;
    /**
     * 刷新时间（秒）
     */
    @TableField("REFRESH_TIME")
    private Integer refreshTime;
    /**
     * 有效时间（秒）
     */
    @TableField("EFFECTIVE_TIME")
    private Integer effectiveTime;
    /**
     * 发送时间
     */
    @TableField("SEND_TIME")
    private Date sendTime;
    /**
     * 短信类型
     */
    @TableField("SMS_TYPE")
    private String smsType;

}
