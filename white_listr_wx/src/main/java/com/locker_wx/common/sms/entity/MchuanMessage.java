package com.locker_wx.common.sms.entity;

import lombok.Data;

@Data
public class MchuanMessage {

    private String id;

    private String method;

    private MchuanMessageParams params;

}
