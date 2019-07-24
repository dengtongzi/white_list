package com.locker_wx.util;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;
/**
* @author 作者 dengtongzi
* @describe StringUtils工具类
* @version 创建时间：2019年6月25日 上午11:46:52
*/
public class StringUtils extends org.apache.commons.lang3.StringUtils {
	
	public static String firstCharToLowerCase(String str) {
		char firstChar = str.charAt(0);
		if (firstChar >= 'A' && firstChar <= 'Z') {
			char[] arr = str.toCharArray();
			arr[0] = (char) (arr[0] + 32);
			return new String(arr);
		} else {
			return str;
		}
	}

	public static String firstCharToUpperCase(String str) {
		char firstChar = str.charAt(0);
		if (firstChar >= 'a' && firstChar <= 'z') {
			char[] arr = str.toCharArray();
			arr[0] = (char) (arr[0] - 32);
			return new String(arr);
		} else {
			return str;
		}
	}

	public static boolean isBlank(String str) {
		if (str == null) {
			return true;
		} else {
			int len = str.length();
			if (len == 0) {
				return true;
			} else {
				int i = 0;

				while (i < len) {
					switch (str.charAt(i)) {
						case '\t' :
						case '\n' :
						case '\r' :
						case ' ' :
							++i;
							break;
						default :
							return false;
					}
				}

				return true;
			}
		}
	}

	public static boolean notBlank(String str) {
		return !isBlank(str);
	}

	public static boolean notBlank(String... strings) {
		if (strings == null) {
			return false;
		} else {
			String[] var4 = strings;
			int var3 = strings.length;

			for (int var2 = 0; var2 < var3; ++var2) {
				String str = var4[var2];
				if (isBlank(str)) {
					return false;
				}
			}

			return true;
		}
	}

	public static boolean notNull(Object... paras) {
		if (paras == null) {
			return false;
		} else {
			Object[] var4 = paras;
			int var3 = paras.length;

			for (int var2 = 0; var2 < var3; ++var2) {
				Object obj = var4[var2];
				if (obj == null) {
					return false;
				}
			}

			return true;
		}
	}

	public static String toCamelCase(String stringWithUnderline) {
		if (stringWithUnderline.indexOf(95) == -1) {
			return stringWithUnderline;
		} else {
			stringWithUnderline = stringWithUnderline.toLowerCase();
			char[] fromArray = stringWithUnderline.toCharArray();
			char[] toArray = new char[fromArray.length];
			int j = 0;

			for (int i = 0; i < fromArray.length; ++i) {
				if (fromArray[i] == '_') {
					++i;
					if (i < fromArray.length) {
						toArray[j++] = Character.toUpperCase(fromArray[i]);
					}
				} else {
					toArray[j++] = fromArray[i];
				}
			}

			return new String(toArray, 0, j);
		}
	}

	public static String join(String[] stringArray) {
		StringBuilder sb = new StringBuilder();
		String[] var5 = stringArray;
		int var4 = stringArray.length;

		for (int var3 = 0; var3 < var4; ++var3) {
			String s = var5[var3];
			sb.append(s);
		}

		return sb.toString();
	}

	public static String joinSql(String[] stringArray) {
		StringBuilder sb = new StringBuilder();
		if (stringArray != null && stringArray.length > 0) {
			if (stringArray.length == 1) {
				sb.append(" = ").append("'" + stringArray[0] + "'");
				return sb.toString();
			}

			sb.append(" IN (");

			for (int i = 0; i < stringArray.length; ++i) {
				if (i > 0) {
					sb.append(",");
				}

				sb.append("'" + stringArray[i] + "'");
			}

			sb.append(")");
		} else {
			sb.append(" = ''");
		}

		return sb.toString();
	}

	public static String joinSql(int[] intArray) {
		StringBuilder sb = new StringBuilder();
		if (intArray != null && intArray.length > 0) {
			if (intArray.length == 1) {
				sb.append(" = ").append("" + intArray[0]);
				return sb.toString();
			}

			sb.append(" IN (");

			for (int i = 0; i < intArray.length; ++i) {
				if (i > 0) {
					sb.append(",");
				}

				sb.append("" + intArray[i]);
			}

			sb.append(")");
		} else {
			sb.append(" = ''");
		}

		return sb.toString();
	}

	public static String join(String[] stringArray, String separator) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < stringArray.length; ++i) {
			if (i > 0) {
				sb.append(separator);
			}

			sb.append(stringArray[i]);
		}

		return sb.toString();
	}

	public static boolean equals(String a, String b) {
		return a == null ? b == null : a.equals(b);
	}

	public static String getRandomUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static List<String> strSplitToStringList(String str) {
		if (notBlank(str)) {
			List<String> list = new ArrayList<String>();
			String[] strList = str.split(",");

			try {
				for (int i = 0; i < strList.length; ++i) {
					list.add(strList[i]);
				}

				return list;
			} catch (RuntimeException var4) {
				return null;
			}
		} else {
			return null;
		}
	}

	public static List<Integer> strSplitToIntList(String str) {
		if (notBlank(str)) {
			List<Integer> list = new ArrayList<Integer>();
			String[] strList = str.split(",");

			try {
				for (int i = 0; i < strList.length; ++i) {
					list.add(Integer.valueOf(strList[i]));
				}

				return list;
			} catch (NumberFormatException var4) {
				return null;
			}
		} else {
			return null;
		}
	}

	public static String toUtf8String(String fn) {
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < fn.length(); ++i) {
			char c = fn.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception var7) {
					b = new byte[0];
				}

				for (int j = 0; j < b.length; ++j) {
					int k = b[j];
					if (k < 0) {
						k += 256;
					}

					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}

		return sb.toString();
	}
	
	
	
	/**
	 * @param args
	 */
	/*
	 * 100元
	 * 1个人	100
	 * 2个人	第一个人x元	第二个人100-x
	 * 3个人	第一个人x元	第二个人y=100-x-0.01	第三个人100-x-y
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner( System.in );
		System.out.println( "请输入总金额：" );
		double totalMoney = sc.nextDouble();
		System.out.println( "请输入获取红包人数：" );
		int personNum = sc.nextInt();
		double[] allocationMoney = new double[personNum]; 
		for ( int i = personNum; i > 1; i-- ) {
			//double surplusMoney = totalMoney - ( i - 1 ) * 0.01;
			//安全线
			double surplusMoney = ( totalMoney - ( i - 1 ) * 0.01 ) / 
 
( i / 2 );
			//设surplusMoney为15，1、获取[0,1500)随机数，2、获取
 
//[1,1500]随机数，3、获取[0.01,15.00]随机数
			double randomlyAssignedMoney = ( (int)( Math.random() * 
 
surplusMoney * 100 ) + 1 ) * 0.01;
			//截取小数点后两位输出
			BigDecimal aBigDecimal = new BigDecimal( 
 
randomlyAssignedMoney );
			double moneyOfOnePerson = aBigDecimal.setScale(2, 
 
BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out.println( moneyOfOnePerson );
			totalMoney -= randomlyAssignedMoney;
			allocationMoney[i-1] = randomlyAssignedMoney; 
		}
		BigDecimal bg = new BigDecimal( totalMoney );
		double remainingMoney = bg.setScale(2, 
 
BigDecimal.ROUND_HALF_UP).doubleValue();
		System.out.println( remainingMoney );
		allocationMoney[0] = remainingMoney;
		double checkTotalMoney = 0; 
		for( int i = 0; i < personNum; i++ ) {
			checkTotalMoney += allocationMoney[i];
		}
		System.out.println( checkTotalMoney );
	}
}
