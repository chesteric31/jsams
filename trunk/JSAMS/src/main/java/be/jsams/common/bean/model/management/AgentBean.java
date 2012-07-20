package be.jsams.common.bean.model.management;

import java.util.ArrayList;
import java.util.List;

import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.AbstractNamedIdentityBean;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.CivilityBean;
import be.jsams.common.bean.model.ContactInformationBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.view.management.AgentBeanView;
import be.jsams.server.model.management.Agent;

/**
 * Bean model for {@link Agent} object.
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
     * 
     * @param society the {@link SocietyBean} to use
     */
    public AgentBean(SocietyBean society) {
        super();
        this.address = new AddressBean();
        this.contactInformation = new ContactInformationBean();
        this.society = society;
        setView(buildView());
    }

    /**
     * Constructor
     * 
     * @param model the {@link Agent} to use
     * @param society the current {@link SocietyBean}
     */
    public AgentBean(Agent model, SocietyBean society) {
        super(model);
        this.address = new AddressBean(model.getAddress());
        this.contactInformation = new ContactInformationBean(model.getContactInformation());
        this.function = model.getFunction();
        this.society = society;
        setView(buildView());
    }

    /**
     * @return the function
     */
    public String getFunction() {
        return function;
    }

    /**
     * @param function the function to set
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
     * @param civility the civility to set
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
     * @param address the address to set
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
     * @param contactInformation the contactInformationBean to set
     */
    public void setContactInformation(ContactInformationBean contactInformation) {
        this.contactInformation = contactInformation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AgentBeanView buildView() {
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
     * @param customers the customers to set
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh(AbstractIdentityBean<?, ?> bean) {
        super.refresh(bean);
        AgentBean other = (AgentBean) bean;
        address.refresh(other.getAddress());
        civility.refresh(other.getCivility());
        contactInformation.refresh(other.getContactInformation());
        setFunction(other.getFunction());
        setId(other.getId());
        setListModel(other.getListModel());
        setSelection(other.getSelection());
        society.refresh(other.getSociety());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result;
        if (address == null) {
            result += 0;
        } else {
            result += address.hashCode();
        }
        result = prime * result;
        if (civility == null) {
            result += 0;
        } else {
            result += civility.hashCode();
        }
        result = prime * result;
        if (contactInformation == null) {
            result += 0;
        } else {
            result += contactInformation.hashCode();
        }
        result = prime * result;
        if (customers == null) {
            result += 0;
        } else {
            result += customers.hashCode();
        }
        result = prime * result;
        if (function == null) {
            result += 0;
        } else {
            result += function.hashCode();
        }
        result = prime * result;
        if (society == null) {
            result += 0;
        } else {
            result += society.hashCode();
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof AgentBean)) {
            return false;
        }
        AgentBean other = (AgentBean) obj;
        if (address == null) {
            if (other.address != null) {
                return false;
            }
        } else if (!address.equals(other.address)) {
            return false;
        }
        if (civility == null) {
            if (other.civility != null) {
                return false;
            }
        } else if (!civility.equals(other.civility)) {
            return false;
        }
        if (contactInformation == null) {
            if (other.contactInformation != null) {
                return false;
            }
        } else if (!contactInformation.equals(other.contactInformation)) {
            return false;
        }
        if (customers == null) {
            if (other.customers != null) {
                return false;
            }
        } else if (!customers.equals(other.customers)) {
            return false;
        }
        if (function == null) {
            if (other.function != null) {
                return false;
            }
        } else if (!function.equals(other.function)) {
            return false;
        }
        if (society == null) {
            if (other.society != null) {
                return false;
            }
        } else if (!society.equals(other.society)) {
            return false;
        }
        return true;
    }

}
