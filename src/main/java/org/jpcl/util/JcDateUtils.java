package org.jpcl.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author Administrator
 */
final public class JcDateUtils {
    private JcDateUtils() {}

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
        String nowText = date.format(formatter);
        return nowText;
    }

    public static String getStringByDate(LocalDateTime date) {
        String nowText = date.format(defFormatter);
        return nowText;
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
     * 匹配日期是否有效  日期格式 yyyy-MM-dd hh-mm-ss
     * @param date
     * @return
     */
    public static boolean checkDateValid(String date) {
        return JcStringUtils.isBlank(date) ? false : date.matches(
            "^\\d{4}-(0?\\d|[1][0-2])-([0-2]?\\d|[3][01]) [0-2]?\\d:[0-5]?\\d:[0-5]?\\d$");
    }

    /**
     * 匹配日期是否有效  日期格式 yyyy-MM-dd
     * @param date
     * @return
     */
    public static boolean checkDateValid2(String date) {
        return JcStringUtils.isBlank(date) ? false : date.matches(
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

    public static void main(String[] args) {
        System.out.println(checkDateValid("2018-12-31 20:09:59"));
    }
}
