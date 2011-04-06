package be.jsams.common.bean.model;

import java.util.ArrayList;
import java.util.List;

import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.common.bean.view.AgentBeanView;
import be.jsams.server.model.Agent;

/**
 * 
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class AgentBean extends AbstractNamedIdentityBean<Agent, AgentBeanView> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 5098630193729966458L;

    private String function;

    private CivilityBean civility;

    private AddressBean address;

    private ContactInformationBean contactInformation;

    private List<CustomerBean> customers;
    
    private SocietyBean society;

    public static final String FUNCTION_PROPERTY = "function";

    /**
     * Default constructor
     */
    public AgentBean() {
        super();
        setAddress(new AddressBean());
        setContactInformation(new ContactInformationBean());
        setCivility(new CivilityBean());
        setSociety(JsamsDesktop.getInstance().getCurrentSociety());
    }

    /**
     * Constructor
     * 
     * @param model
     *            the {@link Agent} to use
     */
    public AgentBean(Agent model) {
        super(model);
        setAddress(new AddressBean(model.getAddress()));
        setCivility(new CivilityBean(model.getCivility()));
        setContactInformation(new ContactInformationBean(model.getContactInformation()));
        setFunction(model.getFunction());
        setSociety(new SocietyBean(model.getSociety()));
        //TODO verify this part
//        List<CustomerBean> beans = new ArrayList<CustomerBean>();
//        List<Customer> customers = model.getCustomers();
//        if (customers != null && !customers.isEmpty()) {
//            for (Customer customer : customers) {
//                beans.add(new CustomerBean(customer));
//            }
//        }
//        setCustomers(beans);
    }

    /**
     * @return the function
     */
    public String getFunction() {
        return function;
    }

    /**
     * @param function
     *            the function to set
     */
    public void setFunction(String function) {
        String oldValue = this.function;
        this.function = function;
        firePropertyChange(FUNCTION_PROPERTY, oldValue, this.function);
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
     * @return the address
     */
    public AddressBean getAddress() {
        return address;
    }

    /**
     * @param address
     *            the address to set
     */
    public void setAddress(AddressBean address) {
        this.address = address;
    }

    /**
     * @return the contactInformationBean
     */
    public ContactInformationBean getContactInformation() {
        return contactInformation;
    }

    /**
     * @param contactInformation
     *            the contactInformationBean to set
     */
    public void setContactInformation(ContactInformationBean contactInformation) {
        this.contactInformation = contactInformation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AgentBeanView getView() {
        return new AgentBeanView(this);
    }

    /**
     * {@inheritDoc}
     */
    public void clear() {
        super.clear();
        address.clear();
        civility.clear();
        contactInformation.clear();
        setFunction(null);
        setCustomers(new ArrayList<CustomerBean>());
    }

    /**
     * @return the customers
     */
    public List<CustomerBean> getCustomers() {
        return customers;
    }

    /**
     * @param customers
     *            the customers to set
     */
    public void setCustomers(List<CustomerBean> customers) {
        this.customers = customers;
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

}
