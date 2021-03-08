package org.jpcl.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author Administrator
 */
final public class DateUtils {
    private DateUtils() {}

    private static DateTimeFormatter defFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 取得当前的时间
     * @return
     */
    public static String getToDay() {
         return getStringByDate(LocalDateTime.now());
    }

    /**
     * 根据字符串的日期格式 取得LocalDateTime
     * @param date
     * @param format
     * @return
     */
    public static LocalDateTime getDayByString(String date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime datetime = LocalDateTime.parse(date, formatter);
        return datetime;
    }

    /**
     * 根据固定格式的字符串取得LocalDateTime
     * @param date
     * @return
     */
    public static LocalDateTime getDayByString(String date) {
        LocalDateTime datetime = LocalDateTime.parse(date, defFormatter);
        return datetime;
    }

    /**
     * 根据LocalDateTime 取得字符串的描述
     * @param date
     * @param format
     * @return
     */
    public static String getStringByDate(LocalDateTime date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.format(formatter);
    }

    public static String getStringByDate(LocalDateTime date) {
        return date.format(defFormatter);
    }

    /**
     * date 转换成LocalDateTime
     * @param date
     * @return
     */
    public static LocalDateTime convertDateToLDT(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * LocalDateTime转换成Date
     * @param time
     * @return
     */
    public static Date convertLDTToDate(LocalDateTime time) {
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 取指定日期的 前几天 或者 后几天 根据num来定位
     * @param now
     * @param day
     * @return
     */
    public static String getDateBySpDay(String now, long day){
        LocalDateTime ldt = getDayByString(now);
        return getStringByDate(ldt.plusDays(day));
    }

    /**
     * 取得指定日期的年月日
     * @param now
     * @return
     */
    public static String getOnlyDay(String now){
        LocalDateTime ldt = getDayByString(now);
        return ldt.toLocalDate().toString();
    }

    /**
     * 取得指定日期的毫秒
     * @param now
     * @return
     */
    public static long getTimeMillis(String now){
        return getDayByString(now).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 匹配日期是否有效  日期格式 yyyy-MM-dd hh-mm-ss
     * @param date
     * @return
     */
    public static boolean checkDateValid(String date) {
        return !StringUtils.isBlank(date) && date.matches(
                "^\\d{4}-(0?\\d|[1][0-2])-([0-2]?\\d|[3][01]) [0-2]?\\d:[0-5]?\\d:[0-5]?\\d$");
    }

    /**
     * 匹配日期是否有效  日期格式 yyyy-MM-dd
     * @param date
     * @return
     */
    public static boolean checkDateValid2(String date) {
        return !StringUtils.isBlank(date) && date.matches(
                "^\\d{4}-(0?\\d|[1][0-2])-([0-2]?\\d|[3][01])$");
    }

    /**
     * 根据毫秒取得指定的日期
     * @param time
     * @return
     */
    public static String getDateByMillSeconds(Long time) {
        return getStringByDate(LocalDateTime.ofEpochSecond(
            time/1000, 0, ZoneOffset.ofHours(8)
        ));
    }

    /**
     * 取得时间段内的每一天
     * @param beginTime
     * @param endTime
     * @return
     */
    public static List<String> findDaysStr(String beginTime, String endTime) {
        final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        List<String> daysStrList = new ArrayList<>();
        try {
            Date dBegin = fmt.parse(beginTime);
            Date dEnd = fmt.parse(endTime);
            daysStrList.add(fmt.format(dBegin));
            Calendar calBegin = Calendar.getInstance();
            calBegin.setTime(dBegin);
            Calendar calEnd = Calendar.getInstance();
            calEnd.setTime(dEnd);
            while (dEnd.after(calBegin.getTime())) {
                calBegin.add(Calendar.DAY_OF_MONTH, 1);
                String dayStr = fmt.format(calBegin.getTime());
                daysStrList.add(dayStr);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Collections.unmodifiableList(daysStrList);
    }

    public static String getDateByMilli(long milli) {
        return getStringByDate(LocalDateTime.ofEpochSecond(milli/1000,0, ZoneOffset.ofHours(8)));
    }

    public static void main(String[] args) {
        findDaysStr("2020-01-01", "2020-07-13").forEach(System.out::println);
    }
}
