package be.jsams.common.bean.model.management;

import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.AbstractNamedIdentityBean;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.CivilityBean;
import be.jsams.common.bean.model.ContactInformationBean;
import be.jsams.common.bean.model.LegalFormBean;
import be.jsams.common.bean.model.PaymentModeBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.view.management.CustomerBeanView;
import be.jsams.server.model.management.Customer;

/**
 * Bean model for {@link Customer} object.
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
    private String firstName;

    private AddressBean deliveryAddress;
    private AddressBean billingAddress;
    private PaymentModeBean paymentMode;
    private ContactInformationBean contactInformation;
    private CivilityBean civility;
    private AgentBean agent;
    private LegalFormBean legalForm;
    private SocietyBean society;

    public static final String VAT_NUMBER_PROPERTY = "vatNumber";
    public static final String DEFAULT_DISCOUNT_RATE_PROPERTY = "defaultDiscountRate";
    public static final String BANK1_PROPERTY = "bank1";
    public static final String BANK2_PROPERTY = "bank2";
    public static final String CREDIT_LIMIT_PROPERTY = "creditLimit";
    public static final String VAT_APPLICABLE_PROPERTY = "vatApplicable";
    public static final String DESCRIPTION_PROPERTY = "description";
    public static final String FIRST_NAME_PROPERTY = "firstName";

    /**
     * Constructor
     * 
     * @param society the {@link SocietyBean} to use
     */
    public CustomerBean(SocietyBean society) {
        super();
        this.billingAddress = new AddressBean();
        this.deliveryAddress = new AddressBean();
        this.contactInformation = new ContactInformationBean();
        this.society = society;
        setView(buildView());
    }

    /**
     * Constructor
     * 
     * @param model the {@link Customer} to use
     * @param society the {@link SocietyBean} of the model
     */
    public CustomerBean(Customer model, SocietyBean society) {
        super(model);
        this.billingAddress = new AddressBean(model.getBillingAddress());
        this.deliveryAddress = new AddressBean(model.getDeliveryAddress());
        this.contactInformation = new ContactInformationBean(model.getContactInformation());
        this.bank1 = model.getBank1();
        this.bank2 = model.getBank2();
        this.creditLimit = model.getCreditLimit();
        this.defaultDiscountRate = model.getDefaultDiscountRate();
        this.vatApplicable = model.getVatApplicable();
        this.vatNumber = model.getVatNumber();
        this.description = model.getDescription();
        this.society = society;
        this.firstName = model.getFirstName();
        setView(buildView());
    }

    /**
     * @return the vatNumber
     */
    public String getVatNumber() {
        return vatNumber;
    }

    /**
     * @param vatNumber the vatNumber to set
     */
    public void setVatNumber(String vatNumber) {
        String oldValue = this.vatNumber;
        this.vatNumber = vatNumber;
        firePropertyChange(VAT_NUMBER_PROPERTY, oldValue, this.vatNumber);
    }

    /**
     * @return the defaultDiscountRate
     */
    public Double getDefaultDiscountRate() {
        return defaultDiscountRate;
    }

    /**
     * @param defaultDiscountRate the defaultDiscountRate to set
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
     * @param bank1 the bank1 to set
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
     * @param bank2 the bank2 to set
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
     * @param creditLimit the creditLimit to set
     */
    public void setCreditLimit(Double creditLimit) {
        Double oldValue = this.creditLimit;
        this.creditLimit = creditLimit;
        firePropertyChange(CREDIT_LIMIT_PROPERTY, oldValue, this.creditLimit);
    }

    /**
     * @return the vatApplicable
     */
    public Double getVatApplicable() {
        return vatApplicable;
    }

    /**
     * @param vatApplicable the vatApplicable to set
     */
    public void setVatApplicable(Double vatApplicable) {
        Double oldValue = this.vatApplicable;
        this.vatApplicable = vatApplicable;
        firePropertyChange(VAT_APPLICABLE_PROPERTY, oldValue, this.vatApplicable);
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        String oldValue = this.description;
        this.description = description;
        firePropertyChange(DESCRIPTION_PROPERTY, oldValue, this.description);
    }

    /**
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        String oldValue = this.firstName;
        this.firstName = firstName;
        firePropertyChange(FIRST_NAME_PROPERTY, oldValue, this.firstName);
    }

    /**
     * @return the deliveryAddress
     */
    public AddressBean getDeliveryAddress() {
        return deliveryAddress;
    }

    /**
     * @param deliveryAddress the deliveryAddress to set
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
     * @param billingAddress the billingAddress to set
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
     * @param paymentMode the paymentMode to set
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
     * @param contactInformation the contactInformation to set
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
     * @param civility the civility to set
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
     * @param agent the agent to set
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
     * @param legalForm the legalForm to set
     */
    public void setLegalForm(LegalFormBean legalForm) {
        this.legalForm = legalForm;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerBeanView buildView() {
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
        setFirstName(null);
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
    public void refresh(AbstractIdentityBean<?, ?> bean) {
        super.refresh(bean);
        CustomerBean other = (CustomerBean) bean;
        agent.refresh(other.getAgent());
        setBank1(other.getBank1());
        setBank2(other.getBank2());
        billingAddress.refresh(other.getBillingAddress());
        civility.refresh(other.getCivility());
        contactInformation.refresh(other.getContactInformation());
        setCreditLimit(other.getCreditLimit());
        setDefaultDiscountRate(other.getDefaultDiscountRate());
        deliveryAddress.refresh(other.getDeliveryAddress());
        setDescription(other.getDescription());
        legalForm.refresh(other.getLegalForm());
        setListModel(other.getListModel());
        paymentMode.refresh(other.getPaymentMode());
        setSelection(other.getSelection());
        society.refresh(other.getSociety());
        setVatApplicable(other.getVatApplicable());
        setVatNumber(other.getVatNumber());
        setId(other.getId());
        setFirstName(other.getFirstName());
    }

}
