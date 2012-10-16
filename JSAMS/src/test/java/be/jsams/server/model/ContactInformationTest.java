package be.jsams.server.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import be.jsams.common.bean.model.ContactInformationBean;
import be.jsams.common.bean.model.MockBeanGenerator;

/**
 * Test class for {@link ContactInformation} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class ContactInformationTest {
    
    private ContactInformation contactInformation;

    /**
     * Test method for
     * {@link be.jsams.server.model.ContactInformation
     * #ContactInformation(be.jsams.common.bean.model.ContactInformationBean)}
     * .
     */
    @Test
    public void testContactInformationContactInformationBean() {
        ContactInformationBean bean = MockBeanGenerator.generateMockContactInformation();
        contactInformation = new ContactInformation(bean);
        String email = contactInformation.getEmail();
        assertTrue(email.equals(bean.getEmail()));
        String fax = contactInformation.getFax();
        assertEquals(fax, bean.getFax());
        String mobile = contactInformation.getMobile();
        assertEquals(mobile, bean.getMobile());
        String phone = contactInformation.getPhone();
        assertEquals(phone, bean.getPhone());
        String website = contactInformation.getWebsite();
        assertEquals(website, bean.getWebsite());
        Long id = contactInformation.getId();
        assertEquals(id, bean.getId());
    }

}
