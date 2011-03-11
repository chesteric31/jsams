package be.jsams.server.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import be.jsams.common.bean.model.CustomerBean;

/**
 * Customer entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "CUSTOMER")
public class Customer extends AbstractNamedIdentity {

    private String vatNumber;
    private Double defaultDiscountRate;
    private String bank1;
    private String bank2;
    private Double creditLimit;
    private Double vatApplicable;
    private String description;

    private Address deliveryAddress;
    private Address billingAddress;
    private PaymentMode paymentMode;
    private ContactInformation contactInformation;
    private Civility civility;

    private Agent agent;
    private Society society;

    private LegalForm legalForm;

    /**
     * Default constructor
     */
    public Customer() {
        super();
    }

    /**
     * Constructor
     * 
     * @param bean
     *            the {@link CustomerBean}
     */
    public Customer(CustomerBean bean) {
        super(bean);
        setAgent(new Agent(bean.getAgent()));
        setBank1(bean.getBank1());
        setBank2(bean.getBank2());
        setBillingAddress(new Address(bean.getBillingAddress()));
        if (bean.getCivility().getId() != null) {
            setCivility(new Civility(bean.getCivility()));
        }
        setContactInformation(new ContactInformation(bean.getContactInformation()));
        setCreditLimit(bean.getCreditLimit());
        setDefaultDiscountRate(bean.getDefaultDiscountRate());
        setDeliveryAddress(new Address(bean.getDeliveryAddress()));
        setDescription(bean.getDescription());
        if (bean.getLegalForm().getId() != null) {
            setLegalForm(new LegalForm(bean.getLegalForm()));
        }
        if (bean.getPaymentMode().getId() != null) {
            setPaymentMode(new PaymentMode(bean.getPaymentMode()));
        }
        setVatApplicable(bean.getVatApplicable());
        setVatNumber(bean.getVatNumber());
        setSociety(new Society(bean.getSociety()));
    }

    /**
     * 
     * @return the VAT number
     */
    @Column(name = "VAT_NUMBER")
    public String getVatNumber() {
        return vatNumber;
    }

    /**
     * 
     * @param vatNumber
     *            the VAT number to set
     */
    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    /**
     * 
     * @return the default discount rate
     */
    @Column(name = "DEFAULT_DISCOUNT_RATE")
    public Double getDefaultDiscountRate() {
        return defaultDiscountRate;
    }

    /**
     * 
     * @param defaultDiscountRate
     *            the default discount rate
     */
    public void setDefaultDiscountRate(Double defaultDiscountRate) {
        this.defaultDiscountRate = defaultDiscountRate;
    }

    /**
     * 
     * @return the bank 1
     */
    @Column(name = "BANK_1")
    public String getBank1() {
        return bank1;
    }

    /**
     * 
     * @param bank1
     *            the bank 1
     */
    public void setBank1(String bank1) {
        this.bank1 = bank1;
    }

    /**
     * 
     * @return the bank 2
     */
    @Column(name = "BANK_2")
    public String getBank2() {
        return bank2;
    }

    /**
     * 
     * @param bank2
     *            the bank 2
     */
    public void setBank2(String bank2) {
        this.bank2 = bank2;
    }

    /**
     * 
     * @return the credit limit
     */
    @Column(name = "CREDIT_LIMIT")
    public Double getCreditLimit() {
        return creditLimit;
    }

    /**
     * 
     * @param creditLimit
     *            the credit limit to set
     */
    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
    }

    /**
     * 
     * @return the VAT applicable
     */
    @Column(name = "VAT_APPLICABLE")
    public Double getVatApplicable() {
        return vatApplicable;
    }

    /**
     * 
     * @param vatApplicable
     *            the VAT applicable
     */
    public void setVatApplicable(Double vatApplicable) {
        this.vatApplicable = vatApplicable;
    }

    /**
     * 
     * @return the description
     */
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *            the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return the default delivery {@link Address}
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_ADDRESS_DELIVERY")
    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    /**
     * 
     * @param deliveryAddress
     *            the default delivery {@link Address}
     */
    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    /**
     * 
     * @return the default billing {@link Address}
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_ADDRESS_BILLING")
    public Address getBillingAddress() {
        return billingAddress;
    }

    /**
     * 
     * @param billingAddress
     *            the default billing {@link Address}
     */
    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    /**
     * 
     * @return the default {@link PaymentMode}
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "FK_PAYMENT_MODE")
    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    /**
     * 
     * @param paymentMode
     *            the default {@link PaymentMode} to set
     */
    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    /**
     * 
     * @return the {@link ContactInformation}
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_CONTACT_INFORMATION")
    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    /**
     * 
     * @param contactInformation
     *            the {@link ContactInformation} to set
     */
    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    /**
     * 
     * @return the {@link Civility}
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "FK_CIVILITY")
    public Civility getCivility() {
        return civility;
    }

    /**
     * 
     * @param civility
     *            the {@link Civility} to set
     */
    public void setCivility(Civility civility) {
        this.civility = civility;
    }

    /**
     * 
     * @return the {@link Agent}
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "FK_AGENT")
    public Agent getAgent() {
        return agent;
    }

    /**
     * 
     * @param agent
     *            the {@link Agent} to set
     */
    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    /**
     * 
     * @return the {@link LegalForm}
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "FK_LEGAL_FORM")
    public LegalForm getLegalForm() {
        return legalForm;
    }

    /**
     * 
     * @param legalForm
     *            the {@link LegalForm} to set
     */
    public void setLegalForm(LegalForm legalForm) {
        this.legalForm = legalForm;
    }

    /**
     * @return the society
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "FK_SOCIETY")
    public Society getSociety() {
        return society;
    }

    /**
     * @param society
     *            the society to set
     */
    public void setSociety(Society society) {
        this.society = society;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Customer [agent=");
        builder.append(agent);
        builder.append(", bank1=");
        builder.append(bank1);
        builder.append(", bank2=");
        builder.append(bank2);
        builder.append(", billingAddress=");
        builder.append(billingAddress);
        builder.append(", civility=");
        builder.append(civility);
        builder.append(", contactInformation=");
        builder.append(contactInformation);
        builder.append(", creditLimit=");
        builder.append(creditLimit);
        builder.append(", defaultDiscountRate=");
        builder.append(defaultDiscountRate);
        builder.append(", deliveryAddress=");
        builder.append(deliveryAddress);
        builder.append(", description=");
        builder.append(description);
        builder.append(", legalForm=");
        builder.append(legalForm);
        builder.append(", paymentMode=");
        builder.append(paymentMode);
        builder.append(", society=");
        builder.append(society);
        builder.append(", vatApplicable=");
        builder.append(vatApplicable);
        builder.append(", vatNumber=");
        builder.append(vatNumber);
        builder.append("]");
        return builder.toString();
    }

}
