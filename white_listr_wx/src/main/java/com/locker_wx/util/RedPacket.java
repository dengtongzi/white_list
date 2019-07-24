package com.locker_wx.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
* @author 作者 dengtongzi
* @describe
* @version 创建时间：2019年7月23日 上午3:58:54
*/
public class RedPacket {
	/**
	 * 1.总金额不超过200*100  单位是分
	 * 2.每个红包都要有钱，最低不能低于1分，最大金额不能超过200*100
	 */
	private static final int MINMONEY = 10;
	private static final int MAXMONEY = 200*100;
	
	/**
	 * 这里为了避免某一个红包占用大量资金，我们需要设定非最后一个红包的最大金额，
	 * 我们把他设置为红包金额平均值的N倍
	 */
	private static final double TIMES = 2;
	
	/**
	 * 红包合法性校验
	 * @param money
	 * @param count
	 * @return
	 */
	private boolean isRight(int money,int count){
		double avg = money/count;
		//小于最小金额
		if (avg<MINMONEY) {
			return false;
		}else if (avg>MAXMONEY) {
			return false;
		}
		return true;
	}
	
	/**
	 * 随机分配一个红包
	 * @param mnoney
	 * @param minS：最小金额
	 * @param maxS：最大金额
	 * @param count
	 * @return
	 */
	private int randomRedPacket(int money,int minS,int maxS,int count){
		//若只有一个，直接返回红包
		if (count==1) {
			return money;
		}
		//如果最大金额和最小金额相等，直接返回金额
		if (minS==maxS) {
			return minS;
		}
		int max=maxS>money?money:maxS;
		//分配红包正确情况，允许红包的最大值
		int maxY = money-(count-1)*minS;
		//分配红包正确情况，允许红包最小值
		int minY = money-(count-1)*maxS;
		//随机产生红包的最小值
		int min = minS>minY?minS:minY;
		//随机产生红包的最大值
		max = max>maxY?maxY:max;
		//随机产生一个红包
		return (int)Math.rint(Math.random()*(max-min) +min);
	}
	
	/**
	 * 拆分红包
	 * @param money	红包金额
	 * @param count 个数
	 * @return
	 */
	public List<Integer> splitRedPacket(int money,int count){
		//红包合法性分析
		if (!isRight(money, count)) {
			return null;
		}
		//红包列表
		List<Integer> list = new ArrayList<>();
		//每个红包的最大的金额为平均金额的TIMES倍
		int max = (int)(money*TIMES/count);
		max = max>MAXMONEY?MAXMONEY:max;
		//分配红包
		for(int i=0;i<count;i++){
			int one = randomRedPacket(money, MINMONEY, max, count-i);
			list.add(one);
			money-=one;
		}
		return list;
	}
	
	public static void main(String[] args) {
		RedPacket redPacket = new RedPacket();
		System.out.println(redPacket.splitRedPacket(1000, 13));
		List<Integer> list = redPacket.splitRedPacket(1000, 13);
		BigDecimal all = new BigDecimal("0.00");
		for(int i=0; i< list.size(); i++) {
			BigDecimal bi1 = new BigDecimal(list.get(i));
			BigDecimal bi2 = new BigDecimal("100");
			BigDecimal divide = bi1.divide(bi2);
			System.out.println(divide);
			all = all.add(divide);
		}
		System.out.println(all);
	}
}
