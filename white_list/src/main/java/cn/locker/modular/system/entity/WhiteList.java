package cn.locker.modular.system.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author dengtongzi
 * @since 2019-07-20
 */
@Data
@Accessors(chain = true)
@TableName("white_list")
public class WhiteList implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 电话号码
     */
    @TableField("PHONE")
    private String phone;

    /**
     * 用户名
     */
    @TableField("USER_NAME")
    private String userName;

    /**
     * 中国银行卡号码
     */
    @TableField("BANK_CARD_NUMBER")
    private String bankCardNumber;

    /**
     * 所属分行
     */
    @TableField("SUBORDINATE_BRANCH")
    private String subordinateBranch;

    /**
     * 所属支行网点
     */
    @TableField("BRANCH_NETWORK")
    private String branchNetwork;

    /**
     * 添加人
     */
    @TableField("ADD_USER")
    private String addUser;

    /**
     * 添加时间
     */
    @TableField("ADD_TIME")
    private Date addTime;

}
