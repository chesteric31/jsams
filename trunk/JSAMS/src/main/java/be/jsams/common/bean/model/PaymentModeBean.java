package be.jsams.common.bean.model;

import be.jsams.common.bean.view.PaymentModeBeanView;
import be.jsams.server.model.PaymentMode;

import com.jgoodies.common.collect.ObservableList;

/**
 * Bean model for {@link PaymentMode} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class PaymentModeBean extends AbstractTranslatableIdentityBean<PaymentMode, PaymentModeBeanView> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 3641238456200690731L;
    
    private Integer daysNumber;
    private boolean monthEnd;
    private Integer additionalDays;

    private static final String DAYS_NUMBER_PROPERTY = "daysNumber";
    private static final String MONTH_END_PROPERTY = "monthEnd";
    private static final String ADDITIONAL_DAYS_PROPERTY = "additionalDays";

    /**
     * Constructor.
     * 
     * @param model the {@link PaymentMode}
     */
    public PaymentModeBean(PaymentMode model) {
        super(model);
        this.daysNumber = model.getAdditionalDays();
        this.monthEnd = model.isMonthEnd();
        this.additionalDays = model.getAdditionalDays();
    }

    /**
     * Constructor.
     * 
     * @param list the {@link ObservableList}
     */
    public PaymentModeBean(ObservableList<PaymentModeBean> list) {
        super();
        setListModel(list);
        setSelection(list.get(0));
    }

    /**
     * Constructor
     * 
     * @param list the {@link ObservableList}
     * @param model the {@link PaymentMode} object
     */
    public PaymentModeBean(ObservableList<PaymentModeBean> list, PaymentMode model) {
        this(model);
        setListModel(list);
        setSelection(this);
    }

    /**
     * @return the daysNumber
     */
    public Integer getDaysNumber() {
        return daysNumber;
    }

    /**
     * @param daysNumber the daysNumber to set
     */
    public void setDaysNumber(Integer daysNumber) {
        Integer oldValue = this.daysNumber;
        this.daysNumber = daysNumber;
        firePropertyChange(DAYS_NUMBER_PROPERTY, oldValue, this.daysNumber);
    }

    /**
     * @return the monthEnd
     */
    public boolean isMonthEnd() {
        return monthEnd;
    }

    /**
     * @param monthEnd the monthEnd to set
     */
    public void setMonthEnd(boolean monthEnd) {
        boolean oldValue = this.monthEnd;
        this.monthEnd = monthEnd;
        firePropertyChange(MONTH_END_PROPERTY, oldValue, this.monthEnd);
    }
    /**
     * @return the additionalDays
     */
    public Integer getAdditionalDays() {
        return additionalDays;
    }

    /**
     * @param additionalDays the additionalDays to set
     */
    public void setAdditionalDays(Integer additionalDays) {
        Integer oldValue = this.additionalDays;
        this.additionalDays = additionalDays;
        firePropertyChange(ADDITIONAL_DAYS_PROPERTY, oldValue, this.additionalDays);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public PaymentModeBeanView getView() {
        return new PaymentModeBeanView(this);
    }
    
    /**
     * {@inheritDoc}
     */
    public void clear() {
        super.clear();
        setDaysNumber(null);
        setMonthEnd(false);
        setAdditionalDays(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh(AbstractIdentityBean<?, ?> bean) {
        PaymentModeBean other = (PaymentModeBean) bean;
        super.refresh(bean);
        setDaysNumber(other.getDaysNumber());
        setMonthEnd(other.isMonthEnd());
        setAdditionalDays(other.getAdditionalDays());
    }

}
