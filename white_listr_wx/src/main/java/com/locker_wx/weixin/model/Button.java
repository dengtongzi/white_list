package com.locker_wx.weixin.model;

import lombok.Data;

/**
 * @describe 菜单项的基类
 * @author deng
 *
 */
@Data
public class Button {
	private String name;// 所有一级菜单、二级菜单都共有一个相同的属性，那就是name

	private String type;
	private String key;
	private String url;
	private SubButton[] sub_button;
}
