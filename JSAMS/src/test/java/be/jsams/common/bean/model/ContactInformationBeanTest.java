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
        ContactInformationBean otherBean = new ContactInformationBean();
        otherBean.setEmail("mail@mail.com");
        otherBean.setFax("0123456789");
        otherBean.setMobile("1234567890");
        otherBean.setPhone("0123456788");
        otherBean.setWebsite("www.website.com");
        bean.refresh(otherBean);
        assertTrue(bean.equals(otherBean));
    }

}
