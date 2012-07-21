package be.jsams.common.bean.model;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for {@link ContactInformationBean} class.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class ContactInformationBeanTest {

    private ContactInformationBean bean;

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Test method for
     * {@link be.jsams.common.bean.model.ContactInformationBean#refresh(
     * be.jsams.common.bean.model.AbstractIdentityBean)}
     * .
     */
    @Test
    public void testRefreshEquals() {
        bean = new ContactInformationBean();
        ContactInformationBean otherBean = MockBeanGenerator.generateMockContactInformation();
        bean.refresh(otherBean);
        assertTrue(bean.equals(otherBean));
    }

    /**
     * Test method for
     * {@link be.jsams.common.bean.model.ContactInformationBean#clear()}.
     */
    @Test
    public void testClear() {
        bean = MockBeanGenerator.generateMockContactInformation();
        bean.clear();
        assertNull(bean.getEmail());
        assertNull(bean.getFax());
        assertNull(bean.getMobile());
        assertNull(bean.getPhone());
        assertNull(bean.getWebsite());
    }

}
