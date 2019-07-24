package com.locker_wx.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
/**
* @author 作者 dengtongzi
* @describe 日期工具类
* @version 创建时间：2019年6月30日 下午1:18:07
*/
public class CalendarUtils {
	public static int YEAR = 1;
	public static int MONTH = 2;
	public static int DAY = 5;
	public static int HOUR = 11;
	public static int MINUTE = 12;
	public static int SECOND = 13;
	public static int MONDAY = 2;
	public static int SATURDAY = 7;
	public static int THURSDAY = 5;
	public static int TUESDAY = 3;
	public static int FRIDAY = 6;
	public static int SUNDAY = 1;
	public static int WEDNESDAY = 4;
	private Calendar calendar;

	private CalendarUtils(Calendar calendar) {
		this.calendar = calendar;
	}

	public static CalendarUtils newInstance() {
		return newInstance((Calendar) (new GregorianCalendar()));
	}

	public static CalendarUtils newInstance(Calendar calendar) {
		return new CalendarUtils(calendar);
	}

	public static CalendarUtils newInstance(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return newInstance((Calendar) cal);
	}

	public static CalendarUtils newInstance(String dateString, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		try {
			Date date = sdf.parse(dateString);
			return newInstance(date);
		} catch (Exception var4) {
			throw new RuntimeException(var4);
		}
	}

	public String getDateString(String separator) {
		StringBuffer buf = new StringBuffer("");
		buf.append(this.calendar.get(1));
		buf.append(separator);
		buf.append(this.calendar.get(2) + 1 > 9
				? String.valueOf(this.calendar.get(2) + 1)
				: "0" + (this.calendar.get(2) + 1));
		buf.append(separator);
		buf.append(this.calendar.get(5) > 9 ? String.valueOf(this.calendar.get(5)) : "0" + this.calendar.get(5));
		return buf.toString();
	}

	public CalendarUtils getMonthLastDay() {
		String firstDay = this.getDateString("-").substring(0, 7) + "-01 " + this.getTimeString();
		System.out.println(firstDay);
		CalendarUtils cal = newInstance(firstDay, "yyyy-MM-dd hh:mm:ss");
		cal.add(MONTH, 1);
		cal.add(DAY, -1);
		return cal;
	}

	public CalendarUtils getMonthFirstDay() {
		String firstDay = this.getDateString("-").substring(0, 7) + "-01 " + this.getTimeString();
		return newInstance(firstDay, "yyyy-MM-dd hh:mm:ss");
	}

	public int getDateInt() {
		StringBuffer buf = new StringBuffer("");
		buf.append(this.calendar.get(1));
		buf.append(this.calendar.get(2) + 1 > 9
				? String.valueOf(this.calendar.get(2) + 1)
				: "0" + (this.calendar.get(2) + 1));
		buf.append(this.calendar.get(5) > 9 ? String.valueOf(this.calendar.get(5)) : "0" + this.calendar.get(5));
		return Integer.parseInt(buf.toString());
	}

	public String getTimeString() {
		StringBuffer buf = new StringBuffer("");
		buf.append(this.calendar.get(11) > 9 ? String.valueOf(this.calendar.get(11)) : "0" + this.calendar.get(11));
		buf.append(":");
		buf.append(this.calendar.get(12) > 9 ? String.valueOf(this.calendar.get(12)) : "0" + this.calendar.get(12));
		buf.append(":");
		buf.append(this.calendar.get(13) > 9 ? String.valueOf(this.calendar.get(13)) : "0" + this.calendar.get(13));
		return buf.toString();
	}

	public int getTimeInt() {
		StringBuffer buf = new StringBuffer("");
		buf.append(this.calendar.get(11) > 9 ? String.valueOf(this.calendar.get(11)) : "0" + this.calendar.get(11));
		buf.append(this.calendar.get(12) > 9 ? String.valueOf(this.calendar.get(12)) : "0" + this.calendar.get(12));
		buf.append(this.calendar.get(13) > 9 ? String.valueOf(this.calendar.get(13)) : "0" + this.calendar.get(13));
		return Integer.parseInt(buf.toString());
	}

	public String getFullMonth() {
		StringBuffer buf = new StringBuffer("");
		buf.append(this.calendar.get(1));
		buf.append(this.calendar.get(2) + 1 > 9
				? String.valueOf(this.calendar.get(2) + 1)
				: "0" + (this.calendar.get(2) + 1));
		return buf.toString();
	}

	public int getMonth() {
		return this.calendar.get(2) + 1;
	}

	public int getYear() {
		return this.calendar.get(1);
	}

	public long getTimeInMillis() {
		return this.calendar.getTimeInMillis();
	}

	public Calendar getCalendar() {
		return this.calendar;
	}

	public void add(int type, int step) {
		this.calendar.add(type, step);
	}

	public String getDateTime(String separator) {
		return this.getDateString(separator) + " " + this.getTimeString();
	}

	public int diff(String startTime, String endTime, String format, int type) {
		CalendarUtils cal1 = newInstance(startTime, format);
		CalendarUtils cal2 = newInstance(endTime, format);
		int dateInt1 = 1;
		int dateInt2 = 1;
		if (type == MONTH) {
			dateInt1 = cal1.getMonth();
			dateInt2 = cal1.getMonth();
			return dateInt2 - dateInt1;
		} else {
			long startTimeMillis = cal1.getTimeInMillis();
			long endTimeMillis = cal2.getTimeInMillis();
			long diffMillis = endTimeMillis - startTimeMillis;
			if (type == SECOND) {
				return (int) (diffMillis / 1000L);
			} else if (type == MINUTE) {
				return (int) (diffMillis / 60000L);
			} else if (type == HOUR) {
				return (int) (diffMillis / 3600000L);
			} else if (type == DAY) {
				return (int) (diffMillis / 86400000L);
			} else {
				throw new RuntimeException("Invalid time type!");
			}
		}
	}
}