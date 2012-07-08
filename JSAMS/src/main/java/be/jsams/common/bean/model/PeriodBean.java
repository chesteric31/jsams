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
     * Constructor
     */
    public PeriodBean() {
        super();
        setView(buildView());
    }

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
    public PeriodBeanView buildView() {
        return new PeriodBeanView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh(AbstractIdentityBean<?, ?> bean) {
        PeriodBean other = (PeriodBean) bean;
        if (other != null) {
            setStartDate(other.getStartDate());
            setEndDate(other.getEndDate());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result;
        if (startDate == null) {
            result += 0;
        } else {
            result += startDate.hashCode();
        }
        if (endDate == null) {
            result += 0;
        } else {
            result += endDate.hashCode();
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof PeriodBean)) {
            return false;
        }
        PeriodBean other = (PeriodBean) obj;
        if (endDate == null) {
            if (other.endDate != null) {
                return false;
            }
        } else if (!endDate.equals(other.endDate)) {
            return false;
        }
        if (startDate == null) {
            if (other.startDate != null) {
                return false;
            }
        } else if (!startDate.equals(other.startDate)) {
            return false;
        }
        return true;
    }
    
}
