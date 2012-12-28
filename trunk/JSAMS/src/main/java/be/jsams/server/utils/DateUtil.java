package be.jsams.server.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Utility class for date management.
 * 
 * @author chesteric31
 * @version $Revision$ $Date:: $ $Author$
 */
public final class DateUtil {

    /**
     * Constructor to avoid to instance this utility class.
     */
    private DateUtil() {
    }

    /**
     * Gets an array of start/end dates from a month and a year, based onto current day.
     * 
     * @param day the day to use
     * @param month the month to use
     * @param year the year to use
     * @return the built array
     */
    public static Date[] getStartEndDates(int day, int month, int year) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(year, month, day);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
        calendar.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the
                                               // hour of day !
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);
        Date startDate = calendar.getTime();
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));
        Date endDate = calendar.getTime();
        return new Date[] {startDate, endDate};
    }

}
