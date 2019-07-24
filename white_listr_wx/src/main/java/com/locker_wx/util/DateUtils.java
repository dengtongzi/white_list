package com.locker_wx.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
* @author 作者 dengtongzi
* @describe 日期转换工具类
* @version 创建时间：2019年6月26日 下午5:08:58
*/
public class DateUtils {
	
	/**
     * 时间相减得出秒钟数差
     * @param endDate
     * @param nowDate
     * @return
     * @throws ParseException
     */
    @SuppressWarnings("unused")
	public static int getDatePoor(String endDate, String nowDate) throws ParseException {
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    	long nd = 1000 * 24 * 60 * 60;//每天毫秒数

    	long nh = 1000 * 60 * 60;//每小时毫秒数

    	long nm = 1000 * 60;//每分钟毫秒数

    	long diff = sdf.parse(endDate).getTime() - sdf.parse(nowDate).getTime(); // 获得两个时间的毫秒时间差异

    	long min = diff / 1000;  // 计算差多少分钟
    	
    	return (int)min;
    }
    
    public Date addMinutesToTime(int minutes) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(12, minutes);
		return calendar.getTime();
	}

	public static Long getAge(Date birthDay) {
		if (birthDay == null) {
			return 0L;
		} else {
			Calendar cal = Calendar.getInstance();
			if (cal.before(birthDay)) {
				return 0L;
			} else {
				int yearNow = cal.get(1);
				int monthNow = cal.get(2) + 1;
				int dayOfMonthNow = cal.get(5);
				cal.setTime(birthDay);
				int yearBirth = cal.get(1);
				int monthBirth = cal.get(2);
				int dayOfMonthBirth = cal.get(5);
				int age = yearNow - yearBirth;
				if (monthNow <= monthBirth) {
					if (monthNow == monthBirth) {
						if (dayOfMonthNow < dayOfMonthBirth) {
							--age;
						}
					} else {
						--age;
					}
				}

				return new Long((long) age);
			}
		}
	}

	public static Date stringToDate(String dateString) {
		Date date = null;
		String sdate = "";
		if (sdate != null && !"".equals(sdate)) {
			System.out.println("stringToDate:" + dateString);
			String os = System.getProperty("os.name");
			System.out.println("system name:" + os);
			String regexYMDHMS = "[\\d]{14}";
			String regesYMD = "[\\d]{8}";
			if (Pattern.matches(regexYMDHMS, dateString)) {
				String dateSplit = "";
				if (os.toLowerCase().startsWith("win")) {
					dateSplit = "-";
				} else {
					dateSplit = "/";
				}

				sdate = dateString.substring(0, 4) + dateSplit + dateString.substring(4, 6) + dateSplit
						+ dateString.substring(6, 8) + " " + dateString.substring(8, 10) + ":"
						+ dateString.substring(10, 12) + ":" + dateString.substring(12, 14);
			} else if (Pattern.matches(regesYMD, dateString)) {
				sdate = dateString.substring(0, 4) + "-" + dateString.substring(4, 6) + "-"
						+ dateString.substring(6, 8);
			} else {
				sdate = dateString;
			}

			try {
				date = DateFormat.getDateTimeInstance().parse(sdate);
			} catch (ParseException var10) {
				try {
					date = DateFormat.getDateInstance().parse(sdate);
				} catch (ParseException var9) {
					;
				}
			} catch (Exception var11) {
				return null;
			}

			if (date == null) {
				SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDD");

				try {
					date = sdf.parse(sdate);
				} catch (ParseException var8) {
					var8.printStackTrace();
				}
			}

			return date;
		} else {
			return null;
		}
	}

	public static Date stringToDate(String pattern, String dateString) {
		Date date = null;
		if (pattern != null && !"".equals(pattern) && dateString != null && !"".equals(dateString)) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);

			try {
				date = sdf.parse(dateString);
			} catch (ParseException var5) {
				var5.printStackTrace();
			}

			return date;
		} else {
			return date;
		}
	}

	public static String getCurrentDateString(String pattern) {
		return pattern == null ? getCurrentTimeStampString() : dateToString(getCurrentDate(), pattern);
	}

	public static String getCurrentDateString() {
		return getCurrentTimeStampString();
	}

	public static String getCurrentTimeStampString() {
		String _pattern = "yyyy-MM-dd HH:mm:ss";
		return dateToString(getCurrentDate(), _pattern);
	}

	public static long stringToLong(String time) {
		String _pattern = "yyyy-MM-dd HH:mm:ss";

		try {
			return stringToLong(time, _pattern);
		} catch (Exception var3) {
			return 0L;
		}
	}

	public static long stringToLong(String date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);

		try {
			return sdf.parse(date).getTime();
		} catch (Exception var4) {
			return 0L;
		}
	}

	public static Date getCurrentDate() {
		return new Date();
	}

	public static String dateToString(Date date, String pattern) {
		String strDateTime = null;
		SimpleDateFormat formater = new SimpleDateFormat(pattern);
		strDateTime = date == null ? null : formater.format(date);
		return strDateTime;
	}

	public static String formatStringDate(String dateString, String pattern) {
		Date d = stringToDate(dateString);
		String date = dateToString(d, pattern);
		return date;
	}

	public static String createRandom(boolean isNumber, int length) {
		String retStr = "";
		String strTable = isNumber ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
		int len = strTable.length();
		boolean bDone = true;

		do {
			retStr = "";
			int count = 0;

			for (int i = 0; i < length; ++i) {
				double dblR = Math.random() * (double) len;
				int intR = (int) Math.floor(dblR);
				char c = strTable.charAt(intR);
				if ('0' <= c && c <= '9') {
					++count;
				}

				retStr = retStr + strTable.charAt(intR);
			}

			if (count >= 2) {
				bDone = false;
			}
		} while (bDone);

		return retStr;
	}

	public static String getWeekOfDate(Date dt) {
		String[] weekDays = new String[]{"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
		Calendar cal = Calendar.getInstance();
		System.out.println("dt:" + dt);
		cal.setTime(dt);
		int weekDay = cal.get(7) - 1;
		if (weekDay < 0) {
			weekDay = 0;
		}

		return weekDays[weekDay];
	}

	public static String getWeekOfDate(Date dt, String pattern) {
		String[] weekDays = new String[]{"日", "一", "二", "三", "四", "五", "六"};
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int weekDay = cal.get(7) - 1;
		if (weekDay < 0) {
			weekDay = 0;
		}

		return pattern + weekDays[weekDay];
	}

	public static long getLong(String date, String pattern) {
		if (pattern == null || "".equals(pattern)) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);

		try {
			return sdf.parse(date).getTime();
		} catch (Exception var4) {
			return 0L;
		}
	}

	public static String simplifyDateString(String dateString, String pattern) {
		if (!StringUtils.isBlank(dateString) && !StringUtils.isBlank(pattern)) {
			Date date = stringToDate(pattern, dateString);
			long delta = (new Date()).getTime() - date.getTime();
			long years;
			if (delta < 60000L) {
				years = toSeconds(delta);
				return (years <= 0L ? 1L : years) + "秒前";
			} else if (delta < 2700000L) {
				years = toMinutes(delta);
				return (years <= 0L ? 1L : years) + "分钟前";
			} else if (delta < 86400000L) {
				years = toHours(delta);
				return (years <= 0L ? 1L : years) + "小时前";
			} else if (delta < 172800000L) {
				return "昨天";
			} else if (delta < 2592000000L) {
				years = toDays(delta);
				return (years <= 0L ? 1L : years) + "天前";
			} else if (delta < 29030400000L) {
				years = toMonths(delta);
				return (years <= 0L ? 1L : years) + "月前";
			} else {
				years = toYears(delta);
				return (years <= 0L ? 1L : years) + "年前";
			}
		} else {
			return "";
		}
	}

	private static long toSeconds(long date) {
		return date / 1000L;
	}

	private static long toMinutes(long date) {
		return toSeconds(date) / 60L;
	}

	private static long toHours(long date) {
		return toMinutes(date) / 60L;
	}

	private static long toDays(long date) {
		return toHours(date) / 24L;
	}

	private static long toMonths(long date) {
		return toDays(date) / 30L;
	}

	private static long toYears(long date) {
		return toMonths(date) / 365L;
	}
}
