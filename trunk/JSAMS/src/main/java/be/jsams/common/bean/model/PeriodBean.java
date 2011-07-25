package be.jsams.common.bean.model;

import java.util.Date;

import be.jsams.common.bean.view.PeriodBeanView;
import be.jsams.server.model.AbstractIdentity;

/**
 * {@link AbstractIdentityBean} for a period object: start/end dates.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class PeriodBean extends AbstractIdentityBean<AbstractIdentity, PeriodBeanView> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -4056201513294539437L;

    private Date startDate;
    private Date endDate;

    public static final String START_DATE_PROPERTY = "startDate";
    public static final String END_DATE_PROPERTY = "endDate";

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        Date oldValue = this.startDate;
        this.startDate = startDate;
        firePropertyChange(START_DATE_PROPERTY, oldValue, this.startDate);
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        Date oldValue = this.endDate;
        this.endDate = endDate;
        firePropertyChange(END_DATE_PROPERTY, oldValue, this.endDate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        setStartDate(null);
        setEndDate(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PeriodBeanView getView() {
        return new PeriodBeanView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh(AbstractIdentityBean<?, ?> bean) {
        PeriodBean other = (PeriodBean) bean;
        setStartDate(other.getStartDate());
        setEndDate(other.getEndDate());
    }

}
