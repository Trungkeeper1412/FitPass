package com.ks.fitpass.web.util;

import java.text.SimpleDateFormat;
import java.util.*;

public class Calendars {

    public static double getDurationTime(long checkInDateTime, long currentTime) {
        double number = (currentTime - checkInDateTime) / (double) 3600;
        return (double) Math.round(number * 100) / 100;
    }

    public static long getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTimeInMillis() / 1000;
    }

    public static String formatTime(long millisecond) {
        SimpleDateFormat isoFormat = new SimpleDateFormat("HH:mm:ss");
        isoFormat.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        return isoFormat.format(millisecond * 1000);
    }

    public static String formatShortTime(long millisecond) {
        SimpleDateFormat isoFormat = new SimpleDateFormat("HH:mm");
        isoFormat.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        return isoFormat.format(millisecond * 1000);
    }

    public static String formatDate(long millisecond) {
        SimpleDateFormat isoFormat = new SimpleDateFormat("dd-MM-yyyy");
        isoFormat.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        return isoFormat.format(millisecond * 1000);
    }

    /**
     * Returns current date.
     *
     * @return the integer value
     */
    public static int getCurrentDate() {
        return Calendar.getInstance().get(Calendar.DATE);
    }

    /**
     * Returns current month.
     *
     * @return the integer value
     */
    public static int getCurrentMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    /**
     * Returns current year.
     *
     * @return the integer value
     */
    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * Returns number of days in a month.
     *
     * @return the integer value
     */
    public static int getNumberOfDatesInMonth(int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * Returns a list of dates of the month.
     *
     * @return the list integer value
     */
    public static List<Integer> getListOfDatesOfTheMonth(int days) {
        List<Integer> ls = new ArrayList<>();
        for (int i = 1; i <= days; ++i) {
            ls.add(i);
        }
        return ls;
    }

    /**
     * Returns a list of months of the year.
     *
     * @return the list integer value (1 => 12)
     */
    public static List<Integer> getListMonth() {
        List<Integer> ls = new ArrayList<>();
        for (int i = 1; i <= 12; ++i) {
            ls.add(i);
        }
        return ls;
    }

    /**
     * Returns the first date of a month.
     *
     * @return the long value - timestamp
     */
    public static long getFirstDateOfMonth(int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis() / 1000 + 86400;
    }

    /**
     * Returns the first date time of a month.
     *
     * @return the long value - timestamp
     */
    public static Date getFirstDateTimeOfMonth(int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }
}
