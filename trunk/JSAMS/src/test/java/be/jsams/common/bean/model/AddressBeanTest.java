package be.jsams.common.bean.model;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for {@link AddressBean} class.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class AddressBeanTest {

    private AddressBean bean;

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Test method for
     * {@link be.jsams.common.bean.model.AddressBean#refresh(be.jsams.common.bean.model.AbstractIdentityBean)}
     * .
     */
    @Test
    public void testRefreshEquals() {
        bean = new AddressBean();
        AddressBean otherBean = MockBeanGenerator.generateMockAddress();
        bean.refresh(otherBean);
        assertTrue(bean.equals(otherBean));
    }

    /**
     * Test method for {@link be.jsams.common.bean.model.AddressBean#clear()}.
     */
    @Test
    public void testClear() {
        bean = MockBeanGenerator.generateMockAddress();
        bean.clear();
        assertNull(bean.getBox());
        assertNull(bean.getCity());
        assertNull(bean.getCountry());
        assertNull(bean.getNumber());
        assertNull(bean.getStreet());
        assertNull(bean.getZipCode());
    }

}
