package com.locker_wx.weixin.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
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
@TableName("wx_user")
public class WxUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户Id
     */
    @TableId("USER_ID")
    private String userId;
    /**
     * 微信openid
     */
    @TableField("OPENID")
    private String openid;
    /**
     * 用户的性别（1是男性，2是女性，0是未知）
     */
    @TableField("SEX")
    private Integer sex;
    /**
     * 昵称
     */
    @TableField("NICKNAME")
    private String nickname;
    /**
     * 头像
     */
    @TableField("HEADIMGURL")
    private String headimgurl;
    /**
     * 电话
     */
    @TableField("PHONE")
    private String phone;
    /**
     * 关注状态（1是关注，0是未关注），未关注时获取不到其余信息
     */
    @TableField("SUBSCRIBE")
    private Integer subscribe;
    /**
     * 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
     */
    @TableField("SUBSCRIBETIME")
    private String subscribetime;
    /**
     * 用户所在城市
     */
    @TableField("CITY")
    private String city;
    /**
     * 用户所在省份
     */
    @TableField("PROVINCE")
    private String province;
    /**
     * 用户所在国家
     */
    @TableField("COUNTRY")
    private String country;
    /**
     * 用户的语言，简体中文为zh_CN
     */
    @TableField("LANGUAGE")
    private String language;


}
