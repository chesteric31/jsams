package be.jsams.server.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.MockBeanGenerator;

/**
 * Test class for {@link Address} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class AddressTest {
    
    private Address address;

    /**
     * Test method for {@link be.jsams.server.model.Address#Address(be.jsams.common.bean.model.AddressBean)}.
     */
    @Test
    public void testAddressAddressBean() {
        AddressBean bean = MockBeanGenerator.generateMockAddress();
        address = new Address(bean);
        String box = address.getBox();
        assertTrue(box.equals(bean.getBox()));
        String city = address.getCity();
        assertEquals(city, bean.getCity());
        String country = address.getCountry();
        assertEquals(country, bean.getCountry());
        String number = address.getNumber();
        assertEquals(number, bean.getNumber());
        String street = address.getStreet();
        assertEquals(street, bean.getStreet());
        String zipCode = address.getZipCode();
        assertEquals(zipCode, bean.getZipCode());
        Long id = address.getId();
        assertEquals(id, bean.getId());
    }

}
