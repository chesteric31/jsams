package be.jsams.common.bean.model;

import static org.junit.Assert.*;

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
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        bean = new AddressBean();
    }

    /**
     * Test method for {@link be.jsams.common.bean.model.AddressBean#refresh(be.jsams.common.bean.model.AbstractIdentityBean)}.
     */
    @Test
    public void testRefreshEquals() {
        AddressBean otherBean = new AddressBean();
        otherBean.setBox("B");
        otherBean.setCity("Brussels");
        otherBean.setCountry("Belgium");
        otherBean.setNumber("2");
        otherBean.setStreet("Rue Neuve");
        otherBean.setZipCode("1000");
        bean.refresh(otherBean);
        assertTrue(bean.equals(otherBean));
    }

}
