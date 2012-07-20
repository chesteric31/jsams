package be.jsams.common.bean.model.sale;

import java.util.Date;

import be.jsams.client.swing.component.JsamsTable;
import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.PeriodBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.view.sale.AbstractDocumentBeanView;
import be.jsams.server.model.sale.AbstractDocument;

/**
 * Abstract class for all beans that have an id, a creation date, a period (for
 * criteria), a remark, a society (for criteria) and a customer
 * 
 * @param <M> an extension of {@link AbstractDocument}
 * @param <V> an extension of {@link AbstractDocumentBeanView}
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public abstract class AbstractDocumentBean<M extends AbstractDocument, V extends AbstractDocumentBeanView<?>> extends
        AbstractIdentityBean<M, V> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 2527643787963121774L;

    private Date creationDate;
    private PeriodBean period;
    private String remark;

    private CustomerBean customer;
    private SocietyBean society;

    public static final String CREATION_DATE_PROPERTY = "creationDate";
    public static final String REMARK_PROPERTY = "remark";

    /**
     * Default constructor.
     * 
     * @param society the {@link SocietyBean}
     * @param customer the {@link CustomerBean}
     */
    public AbstractDocumentBean(SocietyBean society, CustomerBean customer) {
        super();
        this.creationDate = new Date();
        this.customer = customer;
        this.period = new PeriodBean();
        this.society = society;
    }

    /**
     * Constructor
     * 
     * @param model the {@link AbstractDocument}
     * @param society the {@link SocietyBean}
     * @param customer the {@link CustomerBean}
     */
    public AbstractDocumentBean(M model, SocietyBean society, CustomerBean customer) {
        super(model);
        this.creationDate = model.getCreationDate();
        this.society = society;
        this.customer = customer;
        this.remark = model.getRemark();
    }

    /**
     * @return the creationDate
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(Date creationDate) {
        Date oldValue = this.creationDate;
        this.creationDate = creationDate;
        firePropertyChange(CREATION_DATE_PROPERTY, oldValue, this.creationDate);
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        String oldValue = this.remark;
        this.remark = remark;
        firePropertyChange(REMARK_PROPERTY, oldValue, this.remark);
    }

    /**
     * @return the customer
     */
    public CustomerBean getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(CustomerBean customer) {
        this.customer = customer;
    }

    /**
     * @return the period
     */
    public PeriodBean getPeriod() {
        return period;
    }

    /**
     * @param period the period to set
     */
    public void setPeriod(PeriodBean period) {
        this.period = period;
    }

    /**
     * @return the society
     */
    public SocietyBean getSociety() {
        return society;
    }

    /**
     * @param society the society to set
     */
    public void setSociety(SocietyBean society) {
        this.society = society;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        customer.clear();
        setCreationDate(null);
        setListModel(null);
        setRemark(null);
        setSelection(null);
        if (period != null) {
            period.clear();
        }
        if (society != null) {
            society.clear();
        }
        JsamsTable detailsTable = getView().getDetailsTable();
        if (detailsTable != null) {
            detailsTable.clear();
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void refresh(AbstractIdentityBean<?, ?> bean) {
        AbstractDocumentBean<M, V> other = (AbstractDocumentBean<M, V>) bean;
        setCreationDate(other.getCreationDate());
        customer.refresh(other.getCustomer());
        setListModel(other.getListModel());
        setRemark(other.getRemark());
        setSelection(other.getSelection());
        society.refresh(other.getSociety());
        if (period == null) {
            period = new PeriodBean();
        }
        period.refresh(other.getPeriod());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result;
        if (creationDate == null) {
            result += 0;
        } else {
            result += creationDate.hashCode();
        }
        result = prime * result;
        if (customer == null) {
            result += 0;
        } else {
            result += customer.hashCode();
        }
        result = prime * result;
        if (period == null) {
            result += 0;
        } else {
            result += period.hashCode();
        }
        result = prime * result;
        if (remark == null) {
            result += 0;
        } else {
            result += remark.hashCode();
        }
        result = prime * result;
        if (society == null) {
            result += 0;
        } else {
            result += society.hashCode();
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
        if (!(obj instanceof AbstractDocumentBean)) {
            return false;
        }
        AbstractDocumentBean<?, ?> other = (AbstractDocumentBean<?, ?>) obj;
        if (creationDate == null) {
            if (other.creationDate != null) {
                return false;
            }
        } else if (!creationDate.equals(other.creationDate)) {
            return false;
        }
        if (customer == null) {
            if (other.customer != null) {
                return false;
            }
        } else if (!customer.equals(other.customer)) {
            return false;
        }
        if (period == null) {
            if (other.period != null) {
                return false;
            }
        } else if (!period.equals(other.period)) {
            return false;
        }
        if (remark == null) {
            if (other.remark != null) {
                return false;
            }
        } else if (!remark.equals(other.remark)) {
            return false;
        }
        if (society == null) {
            if (other.society != null) {
                return false;
            }
        } else if (!society.equals(other.society)) {
            return false;
        }
        return true;
    }

}
