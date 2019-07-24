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
 * 
 * </p>
 *
 * @author dengtongzi
 * @since 2019-06-26
 */
@Data
@Accessors(chain = true)
@TableName("wx_accessToken")
public class WxAccesstoken implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private Integer id;
    @TableField("TOKEN")
    private String token;
    @TableField("EXPIRES_IN")
    private Integer expiresIn;
    @TableField("CREATE_TIME")
    private Date createTime;


}
