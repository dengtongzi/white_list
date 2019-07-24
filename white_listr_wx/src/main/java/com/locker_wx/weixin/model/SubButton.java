package com.locker_wx.weixin.model;

import lombok.Data;

/**
 * @describe 父菜单项 :包含有二级菜单项的一级菜单。这类菜单项包含有二个属性：name和sub_button，而sub_button以是一个子菜单项数组
 * @author deng
 *
 */
@Data
public class SubButton {
	private String name;
	private String type;
	private String key;
	private String url;
}
