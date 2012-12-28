package be.jsams.server.utils;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

/**
 * Test class for {@link DateUtil} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class DateUtilTest {

    /**
     * Test method for {@link be.jsams.server.utils.DateUtil#getStartEndDate(int, int, int)}.
     */
    @Test
    public void testGetStartEndDate() {
        //25 January 2012
        Date[] startEndDate = DateUtil.getStartEndDates(25, 0, 2012);
        Calendar calendar = GregorianCalendar.getInstance();
        clear(calendar);
        calendar.set(2012, 0, 1);
        assertEquals(calendar.getTime(), startEndDate[0]);
        maximize(calendar);
        calendar.set(2012, 0, 31);
        assertEquals(calendar.getTime(), startEndDate[1]);

        // 15 February 2012
        startEndDate = DateUtil.getStartEndDates(15, 1, 2000);
        clear(calendar);
        calendar.set(2000, 1, 1);
        assertEquals(calendar.getTime(), startEndDate[0]);
        maximize(calendar);
        calendar.set(2000, 1, 29);
        assertEquals(calendar.getTime(), startEndDate[1]);
    }

    /**
     * Clears all fields.
     * 
     * @param calendar the calendar to clear
     */
    private void clear(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the
        // hour of day !
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);
    }

    /**
     * Maximize all fields.
     * 
     * @param calendar the calendar to maximize
     */
    private void maximize(Calendar calendar) {
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));
    }

}
