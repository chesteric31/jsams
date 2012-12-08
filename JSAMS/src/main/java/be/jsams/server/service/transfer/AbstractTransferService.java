package be.jsams.server.service.transfer;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.prefs.Preferences;

import be.jsams.common.bean.model.transfer.TransferBean;

/**
 * Abstract class to gather all common methods for the children services and for
 * the templates methods chaining.
 * 
 * @param <O> the original document to transfer
 * @param <T> the target document transferred
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public abstract class AbstractTransferService<O, T> implements TransferService {

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void transfer(TransferBean model) {
        List<T> newDocuments = createNewDocuments(model);
        persistNewDocuments(newDocuments);
        updateOriginalDocuments((List<O>) model.getDocuments());
    }

    /**
     * Creates a list of new target documents.
     * 
     * @param model the {@link TransferBean} to use
     * @return the built list of new target documents
     */
    protected abstract List<T> createNewDocuments(TransferBean model);

    /**
     * Persists in database the new target documents.
     * 
     * @param newDocuments the list of new documents to persist
     */
    protected abstract void persistNewDocuments(List<T> newDocuments);

    /**
     * Updates the status of the original documents.
     * 
     * @param list the list of the original documents to update
     */
    protected abstract void updateOriginalDocuments(List<O> list);

    /**
     * Calculates due date following the creation date of the bill, the days
     * number, the boolean endMonth and the additional days.
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
     * Calculates a date following the initial date of the bill, the days
     * number, the boolean endMonth and the additional days.
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
     * Retrieves the days number from the preferences with the key parameter.
     * 
     * @param key the key to use, that can be firstRememberDays,
     *            secondRememberDays or formalNoticeDays
     * @return the number of days for the first remember, second remember &
     *         formal notice following the stored preferences
     */
    protected int getDays(final String key) {
        Preferences prefsRoot = Preferences.userRoot();
        Preferences jsamsPrefs = prefsRoot.node("be.jsams");
        return Integer.valueOf(jsamsPrefs.get(key, "0"));
    }

}
