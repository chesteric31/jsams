package be.jsams.common.bean.model.sale;

import java.util.Date;

import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.view.DateBeanView;
import be.jsams.server.model.AbstractIdentity;

/**
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class DateBean extends AbstractIdentityBean<AbstractIdentity, DateBeanView> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -5036485371415049883L;
    
    private Date date;

    public static final String DATE_PROPERTY = "date";

    /**
     * Constructor
O     */
    public DateBean() {
        super();
    }

    /**
     * Constructor
     * 
     * @param date the date
     */
    public DateBean(Date date) {
        this();
        setDate(date);
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        Date oldValue = this.date;
        this.date = date;
        firePropertyChange(DATE_PROPERTY, oldValue, this.date);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        // TODO Auto-generated method stub
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DateBeanView getView() {
        return new DateBeanView(this);
    }

    @Override
    public void refresh(AbstractIdentityBean<?, ?> bean) {
        // TODO Auto-generated method stub
        
    }

}
