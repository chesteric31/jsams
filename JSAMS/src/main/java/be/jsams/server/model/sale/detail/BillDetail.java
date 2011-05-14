package be.jsams.server.model.sale.detail;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import be.jsams.common.bean.model.sale.detail.BillDetailBean;
import be.jsams.server.model.sale.Bill;

/**
 * Bill detail (line) entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "BILL_DETAIL")
public class BillDetail extends AbstractDetail {

    private boolean transferred;

    private Bill bill;

    /**
     * Constructor.
     */
    public BillDetail() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param bean the {@link BillDetailBean}
     * @param bill the {@link Bill} model
     */
    public BillDetail(BillDetailBean bean, final Bill bill) {
        super(bean);
        setBill(bill);
        setTransferred(bean.isTransferred());
    }

    /**
     * 
     * @return true if the {@link BillDetail} was transferred to a credit note detail, false otherwise
     */
    @Column(name = "TRANSFERRED")
    public boolean isTransferred() {
        return transferred;
    }

    /**
     * 
     * @param transferred the transferred boolean to set (true, this {@link BillDetail} is transferred to a credit note
     *            detail, false otherwise
     */
    public void setTransferred(boolean transferred) {
        this.transferred = transferred;
    }

    /**
     * 
     * @return the {@link Bill}
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "FK_BILL")
    public Bill getBill() {
        return bill;
    }

    /**
     * 
     * @param bill the {@link Bill} to set
     */
    public void setBill(Bill bill) {
        this.bill = bill;
    }

}
