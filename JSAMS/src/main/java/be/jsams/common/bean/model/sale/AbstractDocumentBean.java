package be.jsams.common.bean.model.sale;

import java.util.Date;

import be.jsams.client.desktop.JsamsDesktop;
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
public abstract class AbstractDocumentBean<M extends AbstractDocument, V extends AbstractDocumentBeanView<?, ?, ?>>
        extends AbstractIdentityBean<M, V> {

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

    private V view;

    /**
     * Default constructor.
     */
    public AbstractDocumentBean() {
        super();
        SocietyBean society = JsamsDesktop.getInstance().getCurrentSociety();
        setCreationDate(new Date());
        setCustomer(new CustomerBean(society));
        setPeriod(new PeriodBean());
        setSociety(society);
    }

    /**
     * Constructor
     * 
     * @param model the {@link AbstractDocument}
     */
    public AbstractDocumentBean(M model) {
        super(model);
        SocietyBean society = JsamsDesktop.getInstance().getCurrentSociety();
        setCreationDate(model.getCreationDate());
        setSociety(society);
        setCustomer(new CustomerBean(model.getCustomer(), society));
        setRemark(model.getRemark());
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
     * @return the view
     */
    public V getView() {
        return view;
    }

    /**
     * @param view the view to set
     */
    public void setView(V view) {
        this.view = view;
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
        JsamsTable detailsTable = view.getDetailsTable();
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
        period.refresh(other.getPeriod());
    }

}
