package be.jsams.server.model.sale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import be.jsams.common.bean.model.sale.CommandDetailBean;
import be.jsams.server.model.AbstractIdentity;
import be.jsams.server.model.management.Product;

/**
 * Command detail (line) entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "COMMAND_DETAIL")
public class CommandDetail extends AbstractIdentity {

    private int quantity;
    private Double price;
    private Double vatApplicable;
    private Double discountRate;
    private boolean transferred;

    private Command command;
    private Product product;

    /**
     * Constructor.
     */
    public CommandDetail() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param bean the {@link CommandDetailBean}
     * @param command the {@link Command} model
     */
    public CommandDetail(final CommandDetailBean bean, final Command command) {
        super(bean);
        setDiscountRate(bean.getDiscountRate());
        setCommand(command);
        setPrice(bean.getPrice());
        setProduct(new Product(bean.getProduct()));
        setQuantity(bean.getQuantity());
        setTransferred(bean.isTransferred());
        setVatApplicable(bean.getVatApplicable());
    }

    /**
     * 
     * @return the quantity
     */
    @Column(name = "QUANTITY")
    public int getQuantity() {
        return quantity;
    }

    /**
     * 
     * @param quantity
     *            the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * 
     * @return the price
     */
    @Column(name = "PRICE")
    public Double getPrice() {
        return price;
    }

    /**
     * 
     * @param price
     *            the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 
     * @return the VAT to apply
     */
    @Column(name = "VAT_APPLICABLE")
    public Double getVatApplicable() {
        return vatApplicable;
    }

    /**
     * 
     * @param vatApplicable
     *            the VAT applicable to set
     */
    public void setVatApplicable(Double vatApplicable) {
        this.vatApplicable = vatApplicable;
    }

    /**
     * 
     * @return a discount rate
     */
    @Column(name = "DISCOUNT_RATE")
    public Double getDiscountRate() {
        return discountRate;
    }

    /**
     * 
     * @param discountRate
     *            a discount rate to set
     */
    public void setDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
    }

    /**
     * 
     * @return true if the {@link CommandDetail} was transferred to a bill detail, false otherwise
     */
    @Column(name = "TRANSFERRED")
    public boolean isTransferred() {
        return transferred;
    }

    /**
     * 
     * @param transferred
     *            the transferred boolean to set (true, this {@link CommandDetail} is transferred to a bill detail,
     *            false otherwise
     */
    public void setTransferred(boolean transferred) {
        this.transferred = transferred;
    }

    /**
     * 
     * @return the {@link Command}
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "FK_COMMAND")
    public Command getCommand() {
        return command;
    }

    /**
     * 
     * @param command
     *            the {@link Command} to set
     */
    public void setCommand(Command command) {
        this.command = command;
    }

    /**
     * 
     * @return the {@link Product}
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "FK_PRODUCT")
    public Product getProduct() {
        return product;
    }

    /**
     * 
     * @param product
     *            the {@link Product} to set
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CommandDetail [command=");
        builder.append(command);
        builder.append(", discountRate=");
        builder.append(discountRate);
        builder.append(", price=");
        builder.append(price);
        builder.append(", product=");
        builder.append(product);
        builder.append(", quantity=");
        builder.append(quantity);
        builder.append(", transferred=");
        builder.append(transferred);
        builder.append(", vatApplicable=");
        builder.append(vatApplicable);
        builder.append("]");
        return builder.toString();
    }

}
