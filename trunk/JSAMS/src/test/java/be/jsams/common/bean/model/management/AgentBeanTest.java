package be.jsams.common.bean.model.management;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.CivilityBean;
import be.jsams.common.bean.model.ContactInformationBean;
import be.jsams.common.bean.model.MockBeanGenerator;

/**
 * Test class for {@link AgentBean} class. 
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class AgentBeanTest {

    private AgentBean bean;

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
    }
    
    /**
     * Test method for
     * {@link be.jsams.common.bean.model.management.AgentBean#refresh(be.jsams.common.bean.model.AbstractIdentityBean)}
     * .
     */
    @Test
    public void testRefreshEquals() {
        bean = new AgentBean(MockBeanGenerator.generateMockSociety());
        bean.setCivility(MockBeanGenerator.generateMockCivility());
        AgentBean otherBean = MockBeanGenerator.generateMockAgent();
        bean.refresh(otherBean);
        assertTrue(bean.equals(otherBean));
    }
    
    /**
     * Test method for
     * {@link be.jsams.common.bean.model.management.AgentBean#clear()}.
     */
    @Test
    public void testClear() {
        bean = MockBeanGenerator.generateMockAgent();
        bean.clear();
        AddressBean address = bean.getAddress();
        assertNull(address.getBox());
        assertNull(address.getCity());
        assertNull(address.getCountry());
        assertNull(address.getNumber());
        assertNull(address.getStreet());
        assertNull(address.getZipCode());
        CivilityBean civility = bean.getCivility();
        assertNull(civility.getLabel());
        assertNull(civility.getLabelFr());
        assertNull(civility.getLabelNl());
        assertNull(address.getCountry());
        ContactInformationBean contactInformation = bean.getContactInformation();
        assertNull(contactInformation.getEmail());
        assertNull(contactInformation.getFax());
        assertNull(contactInformation.getMobile());
        assertNull(contactInformation.getPhone());
        assertNull(contactInformation.getWebsite());
        assertNull(bean.getFunction());
        assertNull(bean.getName());
    }

}
