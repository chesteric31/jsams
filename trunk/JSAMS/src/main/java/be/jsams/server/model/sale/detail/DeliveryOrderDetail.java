package be.jsams.server.model.sale.detail;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import be.jsams.common.bean.model.sale.detail.BillDetailBean;
import be.jsams.common.bean.model.sale.detail.CommandDetailBean;
import be.jsams.common.bean.model.sale.detail.DeliveryOrderDetailBean;
import be.jsams.server.model.sale.Bill;
import be.jsams.server.model.sale.Command;
import be.jsams.server.model.sale.DeliveryOrder;

/**
 * Delivery order detail (line) entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "DELIVERY_ORDER_DETAIL")
public class DeliveryOrderDetail extends AbstractDetail {

    private boolean transferred;

    private DeliveryOrder deliveryOrder;
    private CommandDetail commandDetail;
    private BillDetail billDetail;

    /**
     * Constructor.
     */
    public DeliveryOrderDetail() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param bean the {@link DeliveryOrderDetailBean}
     * @param deliveryOrder the {@link DeliveryOrder} model
     */
    public DeliveryOrderDetail(final DeliveryOrderDetailBean bean, final DeliveryOrder deliveryOrder) {
        super(bean);
        this.deliveryOrder = deliveryOrder;
        this.transferred = bean.isTransferred();
        CommandDetailBean commandDetail = bean.getCommandDetail();
        if (commandDetail != null) {
            this.commandDetail = new CommandDetail(commandDetail, new Command(commandDetail.getCommand()));
        }
        BillDetailBean billDetail = bean.getBillDetail();
        if (billDetail != null) {
            this.billDetail = new BillDetail(billDetail, new Bill(billDetail.getBill()));
        }
    }

    /**
     * 
     * @return a boolean to indicate is the {@link DeliveryOrderDetail} is
     *         transferred to bill detail
     */
    @Column(name = "TRANSFERRED")
    public boolean isTransferred() {
        return transferred;
    }

    /**
     * 
     * @param transferred a boolean to indicate is the
     *            {@link DeliveryOrderDetail} is transferred to bill detail
     */
    public void setTransferred(boolean transferred) {
        this.transferred = transferred;
    }

    /**
     * 
     * @return the {@link DeliveryOrder}
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "FK_DELIVERY_ORDER")
    public DeliveryOrder getDeliveryOrder() {
        return deliveryOrder;
    }

    /**
     * 
     * @param deliveryOrder the {@link DeliveryOrder} to set
     */
    public void setDeliveryOrder(DeliveryOrder deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
    }

    /**
     * 
     * @return the {@link CommandDetail}
     */
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "FK_COMMAND_DETAIL")
    public CommandDetail getCommandDetail() {
        return commandDetail;
    }

    /**
     * 
     * @param commandDetail the {@link CommandDetail} to set
     */
    public void setCommandDetail(CommandDetail commandDetail) {
        this.commandDetail = commandDetail;
    }

    /**
     * 
     * @return the {@link BillDetail}
     */
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "FK_BILL_DETAIL")
    public BillDetail getBillDetail() {
        return billDetail;
    }

    /**
     * 
     * @param billDetail the {@link BillDetail} to set
     */
    public void setBillDetail(BillDetail billDetail) {
        this.billDetail = billDetail;
    }

}
