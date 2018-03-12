package by.client.android.railwayapp.ui.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Утилитный класс для работы с датами
 *
 * @author PRV
 */
public class DateUtils {

    /**
     * Короткий формат отобраджения времени "часы:минуты"
     */
    public static final String SHORT_TIME_FORMAT = "HH:mm";

    private Calendar calendar;

    public static DateUtils createDate() {
        return new DateUtils();
    }

    public DateUtils() {
        this(Calendar.getInstance());
    }

    public DateUtils(Calendar calendar) {
        this.calendar = calendar;
    }

    public Date toDate() {
        return calendar != null ? clone(calendar).getTime() : null;
    }

    public Calendar build() {
        return calendar != null ? clone(calendar) : null;
    }

    public DateUtils now() {
        return setTime(new Date());
    }

    public DateUtils emptyDate() {
        return setTime(new Date(0)).setToMidnight();
    }

    public DateUtils setTime(Date time) {
        calendar.setTime(time);
        return this;
    }

    public DateUtils setToMidnight() {
        setToMidnight(calendar);
        return this;
    }

    public DateUtils addMinute(int minute) {
        calendar.add(Calendar.MINUTE, minute);
        return this;
    }

    public DateUtils addMonth(int month) {
        calendar.add(Calendar.MONTH, month);
        return this;
    }

    public DateUtils addYear(int year) {
        calendar.add(Calendar.YEAR, year);
        return this;
    }

    public DateUtils addDay(int day) {
        calendar.add(Calendar.DATE, day);
        return this;
    }

    public DateUtils addDayOfYear(int day) {
        calendar.add(Calendar.DAY_OF_YEAR, day);
        return this;
    }

    public DateUtils setYear(int year) {
        calendar.set(Calendar.YEAR, year);
        return this;
    }

    public DateUtils setMonthOfYear(int monthOfYear) {
        calendar.set(Calendar.MONTH, monthOfYear);
        return this;
    }

    public DateUtils setDayOfMonth(int dayOfMonth) {
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        return this;
    }

    public DateUtils setHourOfDay(int hourOfDay) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        return this;
    }

    public DateUtils setMinute(int minute) {
        calendar.set(Calendar.MINUTE, minute);
        return this;
    }

    public DateUtils setSecond(int second) {
        calendar.set(Calendar.SECOND, second);
        return this;
    }

    public DateUtils setMilliseconds(int milliseconds) {
        calendar.set(Calendar.MILLISECOND, milliseconds);
        return this;
    }

    public DateUtils setDate(int year, int month, int day) {
        calendar.set(year, month, day);
        return this;
    }

    public int getYear() {
        return calendar.get(Calendar.YEAR);
    }

    public int getMonth() {
        return calendar.get(Calendar.MONTH);
    }

    public int getDayOfMonth() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * @return long возвращает временной интервал длиной в день (в милисекундах)
     */
    public static long dayMs() {
        return 24 * hourMs();
    }

    /**
     * @return long возвращает временной интервал длиной в час (в милисекундах)
     */
    public static long hourMs() {
        return 60 * minuteMs();
    }

    /**
     * @return long возвращает временной интервал длиной в минуту (в милисекундах)
     */
    public static long minuteMs() {
        return 60 * 1000;
    }

    public static long fromSeconds(long seconds) {
        return 1000 * seconds;
    }

    public static long toSeconds(long milliseconds) {
        return milliseconds / 1000;
    }

    /**
     * Указывают ли даты на один и тот же день
     *
     * @param date1 дата1
     * @param date2 дата2
     * @return true если содержат один и тот же день
     */
    public static boolean isSameDay(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();

        calendar1.setTime(date1);
        calendar2.setTime(date2);

        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
            && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
            && calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH);
    }

    public static Calendar clone(Calendar calendar) {
        if (calendar != null) {
            Calendar copy = Calendar.getInstance();
            copy.setTime(calendar.getTime());
            return copy;
        } else {
            return null;
        }
    }

    public static Calendar roundFloor(Calendar calendar) {
        Calendar result = clone(calendar);
        setToMidnight(result);
        return result;
    }

    public static Calendar roundCeil(Calendar calendar) {
        Calendar result = clone(calendar);
        setToMidnight(result);
        result.add(Calendar.DAY_OF_YEAR, 1);
        result.add(Calendar.SECOND, -1);
        return result;
    }

    public static void setToMidnight(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }
}
