package com.example.demo.util.other;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils {
    /**
     * string date 转 Date 类型
     *
     * @param date
     * @return
     */
    public static Date fmtStringToDateYMD(String date) {
        SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy-MM-dd");
        myFmt1.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Date stringTodate = new Date();
        try {
            stringTodate = myFmt1.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringTodate;
    }

    /**
     * Date  转 string date类型
     *
     * @param date
     * @return
     */
    public static String fmtDateToStringYMD(Date date) {
        SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy-MM-dd");
        myFmt1.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String dateString = "";
        try {
            dateString = myFmt1.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateString;
    }

    /**
     * Date  转 string date类型
     *
     * @param date
     * @return
     */
    public static String fmtDateToStringYMD2(Date date) {
        SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy年MM月dd日");
        myFmt1.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String dateString = "";
        try {
            dateString = myFmt1.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateString;
    }

    /**
     * Date  转 string date类型
     *
     * @param date
     * @return
     */
    public static String fmtDateToStringYMDHMS(Date date) {
        SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        myFmt1.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String dateString = "";
        try {
            dateString = myFmt1.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateString;
    }

    public static Date fmtDateToStringYMDHMS(String date) {
        SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        myFmt1.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Date dateString = null;
        try {
            dateString = myFmt1.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateString;
    }

    /**
     * 获取两个日期之间的日期
     *
     * @return 日期集合
     */
    public static List<Date> getBetweenDates(Date dBegin, Date dEnd) {
        List lDate = new ArrayList();
        Calendar calBegin = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        if (dBegin == dEnd) {
            lDate.add(calBegin.getTime());
            calBegin.add(Calendar.DAY_OF_YEAR, 1);
        } else {
            while (!calBegin.after(calEnd)) {

                lDate.add(calBegin.getTime());
                calBegin.add(Calendar.DAY_OF_YEAR, 1);
            }
        }
        return lDate;
    }

    /**
     * 计算第i天
     *
     * @param date
     * @param i
     * @return
     */
    public static Date getForIDay(Date date, int i) {
        SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy-MM-dd");
        myFmt1.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        cal.setTime(date);
        cal.add(Calendar.DATE, i);
        Date iday = null;
        try {
            iday = myFmt1.parse(fmtDateToStringYMD(cal.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return iday;
    }

    /**
     * 年月日时分秒
     *
     * @param date
     * @param i
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static Date getForIDayHMS(Date date, int i, int hour) {
        SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        myFmt1.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        cal.setTime(date);
        cal.add(Calendar.DATE, i);
        Date iday = null;
        try {
            cal.add(Calendar.HOUR, hour);// 24小时制
            iday = cal.getTime();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return iday;
    }

    public static String getMACAddress2(String ip) {
        String str = "";
        String macAddress = "";
        try {
            Process p = Runtime.getRuntime().exec("nbtstat -a " + ip);
            InputStreamReader ir = new InputStreamReader(p.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            for (int i = 1; i < 100; i++) {
                str = input.readLine();
                if (str != null) {
                    //if (str.indexOf("MAC Address") > 1) {
                    if (str.indexOf("MAC") > 1) {
                        macAddress = str.substring(
                                str.indexOf("=") + 2, str.length());
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return macAddress;
    }



    /**
     * 输入日期所在周的星期一和星期日  两个日期
     *
     * @param date
     * @param weekDay
     * @return
     */
    public Map<String, Date> mondayAndSunday(Date date, String weekDay) {
        Date time = new Date();
        if (weekDay.equals("-1")) {
            time = new Date(date.getYear(), date.getMonth(), date.getDate() + 1);
        } else if (weekDay.equals("1")) {
            time = new Date(date.getYear(), date.getMonth(), date.getDate() - 1);
        } else if (weekDay.equals("0")) {
            time = new Date(date.getYear(), date.getMonth(), date.getDate());
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        cal.setTime(time);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        String imptimeBegin = sdf.format(cal.getTime());
        Date mondayDate = cal.getTime();
        cal.add(Calendar.DATE, 6);
        cal.set(Calendar.HOUR, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        String imptimeEnd = sdf.format(cal.getTime());
        Date sundayDate = cal.getTime();
        SimpleDateFormat datetimeDf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        datetimeDf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Map<String, Date> map = new HashMap<String, Date>();
        map.put("mondayDate", mondayDate);
        map.put("sundayDate", sundayDate);
        return map;
    }


    /**
     * 返回 HH:mm
     *
     * @param d
     * @return
     */
    public String getHHmm(Date d) {
        SimpleDateFormat datetimeDf = new SimpleDateFormat("HH:mm");
        return datetimeDf.format(d);
    }

    /**
     * 计算两个日期相差天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long getDaysBetween(String startDate, String endDate) {
        Date start = fmtStringToDateYMD(startDate);
        Date end = fmtStringToDateYMD(endDate);
        long betweenDate = (end.getTime() - start.getTime()) / (60 * 60 * 24 * 1000);
        return betweenDate;
    }

    /**
     * 计算两个日期相差月数
     *
     * @param date1
     * @param date2
     * @return
     * @throws ParseException
     */
    public static int getMonthBetween(String date1, String date2)
            throws ParseException {

        int result = 0;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(sdf.parse(date1));
        c2.setTime(sdf.parse(date2));

        result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);

        return result == 0 ? 1 : Math.abs(result);

    }
    public static void main(String[] args) throws Exception {

        //设置转换的日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        //开始时间
        Date startDate = sdf.parse("2018-05-10");
        //结束时间
        Date endDate = sdf.parse("2018-05-09");

        //得到相差的天数 betweenDate
        long betweenDate = (endDate.getTime() - startDate.getTime())/(60*60*24*1000);

        //打印控制台相差的天数
        System.out.println(betweenDate);
    }

}
