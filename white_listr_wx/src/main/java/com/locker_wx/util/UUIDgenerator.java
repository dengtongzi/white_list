package com.locker_wx.util;
/**
* @author 作者 dengtongzi
* @describe ID生成器
* @version 创建时间：2019年6月30日 下午1:11:05
*/
public class UUIDgenerator {
	private static int counter = 0;
	private static long counter_1 = 99999L;
	private static long counter_2 = 10000L;
	
	public static String getOrderId(){
		StringBuilder str = new StringBuilder();
		str.append(DateUtils.getCurrentDateString("yyyyMMddhhmmss"));
		str.append(String.format("%05d",addFive())); //长度为5，不足补0
		return str.toString();
	}
	
	private static synchronized int addFive() {
		if (counter == 99999)
			counter = -1;
		return ++counter;
	}
	
	public static String getRandomStr() {
		return uniqueStr(true);
	}
	
	public static String uniqueStr(boolean flag) {
		return uniqueStr(5, flag);
	}

	public static synchronized String uniqueStr(int len, boolean flag) {
		len = len > 10 ? 10 : len;
		long temp = 1L;

		for (int i = 0; i < len - 1; ++i) {
			temp *= 10L;
		}

		if (flag) {
			--counter_1;
			if (counter_1 < 10000L) {
				counter_1 = 99999L;
			}

			return String.valueOf(9999999999999L - System.currentTimeMillis()) + counter_1
					+ (long) ((Math.random() * 9.0D + 1.0D) * (double) temp);
		} else {
			++counter_2;
			if (counter_2 > 99999L) {
				counter_2 = 10000L;
			}

			return String.valueOf(System.currentTimeMillis()) + counter_2
					+ (long) ((Math.random() * 9.0D + 1.0D) * (double) temp);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(getRandomStr());
	}
}
