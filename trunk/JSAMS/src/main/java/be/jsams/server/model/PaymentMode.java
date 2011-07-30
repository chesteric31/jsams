package be.jsams.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import be.jsams.common.bean.model.PaymentModeBean;

/**
 * Payment mode entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "PAYMENT_MODE")
public class PaymentMode extends AbstractTranslatableIdentity {

    private Integer daysNumber;
    private boolean monthEnd;
    private Integer additionalDays;
    
    /**
     * Constructor.
     */
    public PaymentMode() {
        super();
    }

    /**
     * Constructor
     * 
     * @param bean the {@link PaymentModeBean}
     */
    public PaymentMode(PaymentModeBean bean) {
        super(bean);
        this.daysNumber = bean.getDaysNumber();
        this.monthEnd = bean.isMonthEnd();
        this.additionalDays = bean.getAdditionalDays();
    }

    /**
     * @return the daysNumber
     */
    @Column(name = "DAYS_NUMBER")
    public Integer getDaysNumber() {
        return daysNumber;
    }

    /**
     * @param daysNumber the daysNumber to set
     */
    public void setDaysNumber(Integer daysNumber) {
        this.daysNumber = daysNumber;
    }

    /**
     * @return the monthEnd
     */
    @Column(name = "MONTH_END")
    public boolean isMonthEnd() {
        return monthEnd;
    }

    /**
     * @param monthEnd the monthEnd to set
     */
    public void setMonthEnd(boolean monthEnd) {
        this.monthEnd = monthEnd;
    }
    /**
     * @return the additionalDays
     */
    @Column(name = "ADDITIONAL_DAYS")
    public Integer getAdditionalDays() {
        return additionalDays;
    }

    /**
     * @param additionalDays the additionalDays to set
     */
    public void setAdditionalDays(Integer additionalDays) {
        this.additionalDays = additionalDays;
    }
    
}
