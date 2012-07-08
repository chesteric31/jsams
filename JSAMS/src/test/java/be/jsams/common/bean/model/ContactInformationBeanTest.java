package be.jsams.common.bean.model;

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
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        bean = new ContactInformationBean();
    }

    /**
     * Test method for {@link be.jsams.common.bean.model.ContactInformationBean#refresh(be.jsams.common.bean.model.AbstractIdentityBean)}.
     */
    @Test
    public void testRefreshEquals() {
        ContactInformationBean otherBean = MockBeanGenerator.generateMockContactInformation();
        bean.refresh(otherBean);
        assertTrue(bean.equals(otherBean));
    }

}
