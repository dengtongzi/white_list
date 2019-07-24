package com.locker_wx.common.sms.entity;

import lombok.Data;

import java.util.List;

@Data
public class MchuanMessageParams {

    private String userid;

    private String password;

    private List<MchuanMessageSubmit> submit;

}
