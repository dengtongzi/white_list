package com.locker_wx.base;
/**
* @author 作者 dengtongzi
* @describe
* @version 创建时间：2019年7月2日 下午10:10:06
*/
public enum AmountEnum { 
	/**
	 * 充值
	 */
	BUSINESS_NAME_RECHARGE("充值", 1), 
	
	/**
	 * 提现
	 */
	BUSINESS_NAME_WITHDRAWAL("提现", 2), 
	
	/**
	 * 提现
	 */
	BUSINESS_NAME_ADVANCEPAY("预付押金", 3), 
	
	/**
	 * 支出
	 */
	BUSINESS_TYPE_EXPEND("支出", 0), 
	
	/**
	 * 收入
	 */
	BUSINESS_TYPE_INCOME("收入", 1), 
	
	/**
	 * 已入账
	 */
	BUSINESS_STATUS_ARRIVAL("已入账", 1),
	
	/**
	 * 未入账
	 */
	BUSINESS_STATUS_NOTARRIVAL("未入账", 0);
	
    // 成员变量  
    private String name;  
    private int index;  
    
    // 构造方法  
    private AmountEnum(String name, int index) {  
        this.name = name;  
        this.index = index;  
    }  
    
    // 普通方法  
    public static String getName(int index) {  
        for (AmountEnum c : AmountEnum.values()) {  
            if (c.getIndex() == index) {  
                return c.name;  
            }  
        }  
        return null;  
    }  
    // get set 方法  
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
    public int getIndex() {  
        return index;  
    }  
    public void setIndex(int index) {  
        this.index = index;  
    }  
}
