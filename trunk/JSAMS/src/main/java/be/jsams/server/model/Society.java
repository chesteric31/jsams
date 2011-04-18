package be.jsams.server.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import be.jsams.common.bean.model.SocietyBean;

/**
 * Society entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "SOCIETY")
public class Society extends AbstractNamedIdentity {

    private Double capital;
    private String activity;
    private String responsible;
    private String vatNumber;

    private Address address;
    private LegalForm legalForm;
    private ContactInformation contactInformation;

    private List<Agent> agents;
    private List<Customer> customers;
    private List<ProductCategory> productCategories;

    /**
     * Constructor
     */
    public Society() {
        super();
    }

    /**
     * Constructor
     * 
     * @param bean
     *            the {@link SocietyBean}
     */
    public Society(SocietyBean bean) {
        super(bean);
        setActivity(bean.getActivity());
        setAddress(new Address(bean.getAddress()));
        setCapital(bean.getCapital());
        setContactInformation(new ContactInformation(bean.getContactInformation()));
        if (bean.getLegalForm().getId() != null) {
            setLegalForm(new LegalForm(bean.getLegalForm()));
        }
        setName(bean.getName());
        setResponsible(bean.getResponsible());
        setVatNumber(bean.getVatNumber());
    }

    /**
     * 
     * @return the capital
     */
    @Column(name = "CAPITAL")
    public Double getCapital() {
        return capital;
    }

    /**
     * 
     * @param capital
     *            the capital to set
     */
    public void setCapital(Double capital) {
        this.capital = capital;
    }

    /**
     * 
     * @return the activity
     */
    @Column(name = "ACTIVITY")
    public String getActivity() {
        return activity;
    }

    /**
     * 
     * @param activity
     *            the activity to set
     */
    public void setActivity(String activity) {
        this.activity = activity;
    }

    /**
     * 
     * @return the responsible
     */
    @Column(name = "RESPONSIBLE")
    public String getResponsible() {
        return responsible;
    }

    /**
     * 
     * @param responsible
     *            the responsible to set
     */
    public void setResponsible(String responsible) {
        this.responsible = responsible;
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
     * @return the {@link Address}
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_ADDRESS")
    public Address getAddress() {
        return address;
    }

    /**
     * 
     * @param address
     *            the {@link Address} to set
     */
    public void setAddress(Address address) {
        this.address = address;
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
     * @return the agents
     */
    @ManyToMany(mappedBy = "society", fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_SOCIETY")
    public List<Agent> getAgents() {
        return agents;
    }

    /**
     * @param agents
     *            the agents to set
     */
    public void setAgents(List<Agent> agents) {
        this.agents = agents;
    }

    /**
     * @return the customers
     */
    @ManyToMany(mappedBy = "society", fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_SOCIETY")
    public List<Customer> getCustomers() {
        return customers;
    }

    /**
     * @param customers
     *            the customers to set
     */
    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    /**
     * @return the productCategories
     */
    @ManyToMany(mappedBy = "society", fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_SOCIETY")
    public List<ProductCategory> getProductCategories() {
        return productCategories;
    }

    /**
     * @param productCategories
     *            the productCategories to set
     */
    public void setProductCategories(List<ProductCategory> productCategories) {
        this.productCategories = productCategories;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Society [activity=");
        builder.append(activity);
        builder.append(", address=");
        builder.append(address);
        builder.append(", agents=");
        builder.append(agents);
        builder.append(", capital=");
        builder.append(capital);
        builder.append(", contactInformation=");
        builder.append(contactInformation);
        builder.append(", customers=");
        builder.append(customers);
        builder.append(", legalForm=");
        builder.append(legalForm);
        builder.append(", productCategories=");
        builder.append(productCategories);
        builder.append(", responsible=");
        builder.append(responsible);
        builder.append(", vatNumber=");
        builder.append(vatNumber);
        builder.append("]");
        return builder.toString();
    }

}
