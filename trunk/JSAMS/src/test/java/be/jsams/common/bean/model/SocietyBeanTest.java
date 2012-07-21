package be.jsams.common.bean.model;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import be.jsams.common.bean.builder.SocietyBeanBuilder;

/**
 * Test class for {@link SocietyBean} class.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class SocietyBeanTest extends AbstractTransactionalJUnit4SpringContextTests {

    private SocietyBean bean;
    @Autowired
    private SocietyBeanBuilder builder;

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Test method for
     * {@link be.jsams.common.bean.model.SocietyBean#refresh(be.jsams.common.bean.model.AbstractIdentityBean)}
     * .
     */
    @Test
    public void testRefreshEquals() {
        bean = builder.build(true);
        SocietyBean otherBean = MockBeanGenerator.generateMockSociety();
        bean.refresh(otherBean);
        assertTrue(bean.equals(otherBean));
    }


    /**
     * Test method for
     * {@link be.jsams.common.bean.model.SocietyBean#clear()}
     * .
     */
    @Test
    public void testClear() {
        bean = builder.build(true);
        bean.clear();
        assertNull(bean.getActivity());
        AddressBean address = bean.getAddress();
        assertNull(address.getBox());
        assertNull(address.getCity());
        assertNull(address.getCountry());
        assertNull(address.getNumber());
        assertNull(address.getStreet());
        assertNull(address.getZipCode());
        assertNull(bean.getCapital());
        ContactInformationBean contactInformation = bean.getContactInformation();
        assertNull(contactInformation.getEmail());
        assertNull(contactInformation.getFax());
        assertNull(contactInformation.getMobile());
        assertNull(contactInformation.getPhone());
        assertNull(contactInformation.getWebsite());
        LegalFormBean legalForm = bean.getLegalForm();
        assertNull(legalForm.getLabel());
        assertNull(legalForm.getLabelFr());
        assertNull(legalForm.getLabelNl());
        assertNull(bean.getLogo());
        assertNull(bean.getName());
        assertNull(bean.getResponsible());
        assertNull(bean.getVatNumber());
    }

}
