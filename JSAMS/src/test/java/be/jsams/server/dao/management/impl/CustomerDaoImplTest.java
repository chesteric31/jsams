package be.jsams.server.dao.management.impl;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import be.jsams.common.bean.model.CivilityBean;
import be.jsams.common.bean.model.LegalFormBean;
import be.jsams.common.bean.model.PaymentModeBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.server.dao.AddressDao;
import be.jsams.server.dao.BaseJUnitTestClass;
import be.jsams.server.dao.CivilityDao;
import be.jsams.server.dao.ContactInformationDao;
import be.jsams.server.dao.LegalFormDao;
import be.jsams.server.dao.PaymentModeDao;
import be.jsams.server.dao.SocietyDao;
import be.jsams.server.dao.management.AgentDao;
import be.jsams.server.dao.management.CustomerDao;
import be.jsams.server.model.Address;
import be.jsams.server.model.Civility;
import be.jsams.server.model.ContactInformation;
import be.jsams.server.model.LegalForm;
import be.jsams.server.model.MockModelGenerator;
import be.jsams.server.model.PaymentMode;
import be.jsams.server.model.Society;
import be.jsams.server.model.management.Agent;
import be.jsams.server.model.management.Customer;

/**
 * Test class for {@link CustomerDao}.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class CustomerDaoImplTest extends BaseJUnitTestClass {

    @Autowired
    private CustomerDao dao;
    private Customer customer;

    @Autowired
    private SocietyDao societyDao;
    @Autowired
    private AgentDao agentDao;

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private CivilityDao civilityDao;
    @Autowired
    private LegalFormDao legalFormDao;
    @Autowired
    private PaymentModeDao paymentModeDao;
    
    @Autowired
    private ContactInformationDao contactInformationDao;

    private SocietyBean societyBean;
    private LegalFormBean legalFormBean;
    private PaymentModeBean paymentModeBean;
    private Customer persistedCustomer;
    private Civility persistedCivility;
    private LegalForm persistedLegalForm;
    
    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
        customer = MockModelGenerator.generateMockCustomer();
        
        Agent agent = customer.getAgent();
        // persist all necessary for agent
        civilityDao.add(agent.getCivility());
        societyDao.add(agent.getSociety());
        
        agentDao.add(agent);
        
        final Society persistedSociety = societyDao.add(customer.getSociety());
        societyBean = new SocietyBean(persistedSociety) {

            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = -3620916072968284788L;
        };
        
        final PaymentMode persistedPaymentMode = paymentModeDao.add(customer.getPaymentMode());
        paymentModeBean = new PaymentModeBean(persistedPaymentMode) {

            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = 8548843895361137546L;

        };
        
        persistedCivility = civilityDao.add(customer.getCivility());
        customer.setCivility(persistedCivility);
        
        final ContactInformation persistedContactInformation = contactInformationDao.add(customer
                .getContactInformation());
        customer.setContactInformation(persistedContactInformation);
        
        final Address persistedAddress = addressDao.add(customer.getBillingAddress());
        customer.setBillingAddress(persistedAddress);
        customer.setDeliveryAddress(persistedAddress);

        persistedLegalForm = legalFormDao.add(customer.getLegalForm());
        legalFormBean = new LegalFormBean(persistedLegalForm) {

            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = 6557303027064624675L;

        };
        customer.setLegalForm(persistedLegalForm);

        persistedCustomer = dao.add(customer);
        dao.setCurrentSociety(societyBean);
    }
    
    /**
     * Test method for
     * {@link be.jsams.server.dao.management.impl.CustomerDaoImpl#findByCriteria(
     * be.jsams.common.bean.model.management.CustomerBean)}
     * .
     */
    @Test
    public void testFindByCriteria() {
        CustomerBean criteria = new CustomerBean(societyBean) {

            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = -8384739910358786256L;

        };

        criteria.setSociety(societyBean);
        CivilityBean civilityBean = new CivilityBean(persistedCivility) {

            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = -6823226636407491323L;

        };
        criteria.setCivility(civilityBean);
        criteria.setLegalForm(legalFormBean);
        criteria.setName(persistedCustomer.getName());
        criteria.setPaymentMode(paymentModeBean);
        criteria.setLegalForm(legalFormBean);
        List<Customer> founds = dao.findByCriteria(criteria);
        assertTrue(founds.contains(persistedCustomer));
    }

    /**
     * Test method for
     * {@link be.jsams.server.dao.management.impl.CustomerDaoImpl#findAll()}.
     */
    @Test
    public void testFindAll() {
        List<Customer> founds = dao.findAll();
        assertTrue(founds.contains(persistedCustomer));
    }

}
