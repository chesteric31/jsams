package be.jsams.common.bean.model;

import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.common.bean.view.CustomerBeanView;
import be.jsams.server.model.Agent;
import be.jsams.server.model.Customer;
import be.jsams.server.model.LegalForm;

/**
 * 
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CustomerBean extends AbstractNamedIdentityBean<Customer, CustomerBeanView> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -8568048729328315582L;

    private String vatNumber;
    private Double defaultDiscountRate;
    private String bank1;
    private String bank2;
    private Double creditLimit;
    private Double vatApplicable;
    private String description;

    private AddressBean deliveryAddress;
    private AddressBean billingAddress;
    private PaymentModeBean paymentMode;
    private ContactInformationBean contactInformation;
    private CivilityBean civility;
    private AgentBean agent;
    private LegalFormBean legalForm;
    private SocietyBean society;

    public static final String VATNUMBER_PROPERTY = "vatNumber";
    public static final String DEFAULT_DISCOUNT_RATE_PROPERTY = "defaultDiscountRate";
    public static final String BANK1_PROPERTY = "bank1";
    public static final String BANK2_PROPERTY = "bank2";
    public static final String CREDITLIMIT_PROPERTY = "creditLimit";
    public static final String VATAPPLICABLE_PROPERTY = "vatApplicable";
    public static final String DESCRIPTION_PROPERTY = "description";

    /**
     * Constructor
     */
    public CustomerBean() {
        super();
        setBillingAddress(new AddressBean());
        setDeliveryAddress(new AddressBean());
        setLegalForm(new LegalFormBean());
        setContactInformation(new ContactInformationBean());
        setCivility(new CivilityBean());
        setPaymentMode(new PaymentModeBean());
        setAgent(new AgentBean());
        setSociety(JsamsDesktop.getInstance().getCurrentSociety());
    }

    /**
     * Constructor
     * 
     * @param model
     *            the {@link Customer} to use
     */
    public CustomerBean(Customer model) {
        super(model);
        setBillingAddress(new AddressBean(model.getBillingAddress()));
        setDeliveryAddress(new AddressBean(model.getDeliveryAddress()));
        LegalForm form = model.getLegalForm();
        if (form != null) {
            setLegalForm(new LegalFormBean(form));
        } else {
            setLegalForm(new LegalFormBean());
        }
        setContactInformation(new ContactInformationBean(model.getContactInformation()));
        if (model.getCivility() != null) {
            setCivility(new CivilityBean(model.getCivility()));
        } else {
            setCivility(new CivilityBean());
        }
        if (model.getPaymentMode() != null) {
            setPaymentMode(new PaymentModeBean(model.getPaymentMode()));
        } else {
            setPaymentMode(new PaymentModeBean());
        }
        setBank1(model.getBank1());
        setBank2(model.getBank2());
        setCreditLimit(model.getCreditLimit());
        setDefaultDiscountRate(model.getDefaultDiscountRate());
        setVatApplicable(model.getVatApplicable());
        setVatNumber(model.getVatNumber());
        setDescription(model.getDescription());
        Agent agent = model.getAgent();
        if (agent != null) {
            setAgent(new AgentBean(agent));
        } else {
            setAgent(new AgentBean());
        }
        setSociety(new SocietyBean(model.getSociety()));
    }

    /**
     * @return the vatNumber
     */
    public String getVatNumber() {
        return vatNumber;
    }

    /**
     * @param vatNumber
     *            the vatNumber to set
     */
    public void setVatNumber(String vatNumber) {
        String oldValue = this.vatNumber;
        this.vatNumber = vatNumber;
        firePropertyChange(VATNUMBER_PROPERTY, oldValue, this.vatNumber);
    }

    /**
     * @return the defaultDiscountRate
     */
    public Double getDefaultDiscountRate() {
        return defaultDiscountRate;
    }

    /**
     * @param defaultDiscountRate
     *            the defaultDiscountRate to set
     */
    public void setDefaultDiscountRate(Double defaultDiscountRate) {
        Double oldValue = this.defaultDiscountRate;
        this.defaultDiscountRate = defaultDiscountRate;
        firePropertyChange(DEFAULT_DISCOUNT_RATE_PROPERTY, oldValue, this.defaultDiscountRate);
    }

    /**
     * @return the bank1
     */
    public String getBank1() {
        return bank1;
    }

    /**
     * @param bank1
     *            the bank1 to set
     */
    public void setBank1(String bank1) {
        String oldValue = this.bank1;
        this.bank1 = bank1;
        firePropertyChange(BANK1_PROPERTY, oldValue, this.bank1);
    }

    /**
     * @return the bank2
     */
    public String getBank2() {
        return bank2;
    }

    /**
     * @param bank2
     *            the bank2 to set
     */
    public void setBank2(String bank2) {
        String oldValue = this.bank2;
        this.bank2 = bank2;
        firePropertyChange(BANK2_PROPERTY, oldValue, this.bank2);
    }

    /**
     * @return the creditLimit
     */
    public Double getCreditLimit() {
        return creditLimit;
    }

    /**
     * @param creditLimit
     *            the creditLimit to set
     */
    public void setCreditLimit(Double creditLimit) {
        Double oldValue = this.creditLimit;
        this.creditLimit = creditLimit;
        firePropertyChange(CREDITLIMIT_PROPERTY, oldValue, this.creditLimit);
    }

    /**
     * @return the vatApplicable
     */
    public Double getVatApplicable() {
        return vatApplicable;
    }

    /**
     * @param vatApplicable
     *            the vatApplicable to set
     */
    public void setVatApplicable(Double vatApplicable) {
        Double oldValue = this.vatApplicable;
        this.vatApplicable = vatApplicable;
        firePropertyChange(VATAPPLICABLE_PROPERTY, oldValue, this.vatApplicable);
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        String oldValue = this.description;
        this.description = description;
        firePropertyChange(DESCRIPTION_PROPERTY, oldValue, this.description);
    }

    /**
     * @return the deliveryAddress
     */
    public AddressBean getDeliveryAddress() {
        return deliveryAddress;
    }

    /**
     * @param deliveryAddress
     *            the deliveryAddress to set
     */
    public void setDeliveryAddress(AddressBean deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    /**
     * @return the billingAddress
     */
    public AddressBean getBillingAddress() {
        return billingAddress;
    }

    /**
     * @param billingAddress
     *            the billingAddress to set
     */
    public void setBillingAddress(AddressBean billingAddress) {
        this.billingAddress = billingAddress;
    }

    /**
     * @return the paymentMode
     */
    public PaymentModeBean getPaymentMode() {
        return paymentMode;
    }

    /**
     * @param paymentMode
     *            the paymentMode to set
     */
    public void setPaymentMode(PaymentModeBean paymentMode) {
        this.paymentMode = paymentMode;
    }

    /**
     * @return the contactInformation
     */
    public ContactInformationBean getContactInformation() {
        return contactInformation;
    }

    /**
     * @param contactInformation
     *            the contactInformation to set
     */
    public void setContactInformation(ContactInformationBean contactInformation) {
        this.contactInformation = contactInformation;
    }

    /**
     * @return the civility
     */
    public CivilityBean getCivility() {
        return civility;
    }

    /**
     * @param civility
     *            the civility to set
     */
    public void setCivility(CivilityBean civility) {
        this.civility = civility;
    }

    /**
     * @return the agent
     */
    public AgentBean getAgent() {
        return agent;
    }

    /**
     * @param agent
     *            the agent to set
     */
    public void setAgent(AgentBean agent) {
        this.agent = agent;
    }

    /**
     * @return the legalForm
     */
    public LegalFormBean getLegalForm() {
        return legalForm;
    }

    /**
     * @param legalForm
     *            the legalForm to set
     */
    public void setLegalForm(LegalFormBean legalForm) {
        this.legalForm = legalForm;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerBeanView getView() {
        return new CustomerBeanView(this);
    }

    /**
     * {@inheritDoc}
     */
    public void clear() {
        super.clear();
        agent.clear();
        setBank1(null);
        setBank2(null);
        billingAddress.clear();
        civility.clear();
        contactInformation.clear();
        setCreditLimit(null);
        setDefaultDiscountRate(null);
        deliveryAddress.clear();
        setDescription(null);
        legalForm.clear();
        paymentMode.clear();
        setVatApplicable(null);
        setVatNumber(null);
    }

    /**
     * @return the society
     */
    public SocietyBean getSociety() {
        return society;
    }

    /**
     * @param society
     *            the society to set
     */
    public void setSociety(SocietyBean society) {
        this.society = society;
    }

}
