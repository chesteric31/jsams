package be.jsams.server.service.transfer;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.prefs.Preferences;

/**
 * 
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public abstract class AbstractTransferService {

    /**
     * Calculates due date following the creation date of the bill,
     * the days number, the boolean endMonth and the additional days.
     * 
     * @param creationDate the creation date of the bill
     * @param daysNumber the days number
     * @param endMonth the boolean end month
     * @param additionalDays the additional days
     * @return the calculated due date
     */
    protected Date calculateDueDate(Date creationDate, int daysNumber, boolean endMonth, int additionalDays) {
        Date dueDate = null;
        Calendar instance = GregorianCalendar.getInstance();
        instance.setTime(creationDate);
        instance.add(Calendar.DAY_OF_MONTH, daysNumber);
        if (endMonth) {
            int maximumDaysOfMonth = instance.getActualMaximum(Calendar.DAY_OF_MONTH);
            int dayOfMonth = instance.get(Calendar.DAY_OF_MONTH);
            int daysToAdd = maximumDaysOfMonth - dayOfMonth;
            instance.add(Calendar.DAY_OF_YEAR, daysToAdd);
        }
        instance.add(Calendar.DAY_OF_YEAR, additionalDays);
        dueDate = instance.getTime();
        return dueDate;
    }

    /**
     * Calculates a date following the initial date of the bill, the
     * days number, the boolean endMonth and the additional days.
     * 
     * @param initialDate the initial date to use
     * @param daysNumber the days number
     * @return the calculated date with the days number to add
     */
    protected Date calculateDate(Date initialDate, int daysNumber) {
        Date dueDate = null;
        Calendar instance = GregorianCalendar.getInstance();
        instance.setTime(initialDate);
        instance.add(Calendar.DAY_OF_MONTH, daysNumber);
        dueDate = instance.getTime();
        return dueDate;
    }

    /**
     * 
     * @param key the key to use, firstRememberDays, secondRememberDays or
     *            formalNoticeDays
     * @return the number of days for the first remember, second remember &
     *         formal notice following the stored preferences
     */
    protected int getDays(final String key) {
        Preferences prefsRoot = Preferences.userRoot();
        Preferences jsamsPrefs = prefsRoot.node("be.jsams");
        return Integer.valueOf(jsamsPrefs.get(key, "0"));
    }

}
