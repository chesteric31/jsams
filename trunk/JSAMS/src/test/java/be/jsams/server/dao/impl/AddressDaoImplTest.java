package be.jsams.server.dao.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityExistsException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import be.jsams.server.dao.AddressDao;
import be.jsams.server.dao.BaseJUnitTestClass;
import be.jsams.server.model.Address;
import be.jsams.server.model.MockModelGenerator;

/**
 * Test class for {@link AddressDaoImpl}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class AddressDaoImplTest extends BaseJUnitTestClass {

    @Autowired
    private AddressDao dao;

    private Address address;
    
    /**
     * Setup method.
     */
    @Before
    public void setUp() {
        address = MockModelGenerator.generateMockAddress();
    }

    /**
     * Test method for add method.
     */
    @Test
    public void testAdd() {
        dao.add(address);
        assertNotNull(address.getId());
    }

    /**
     * Test method for add method with null city.
     */
    @Test(expected = EntityExistsException.class)
    public void testAddNullCity() {
        address.setCity(null);
        dao.add(address);
    }

    /**
     * Test method for add method with null country.
     */
    @Test(expected = EntityExistsException.class)
    public void testAddNullCountry() {
        address.setCountry(null);
        dao.add(address);
    }

    /**
     * Test method for add method with null number.
     */
    @Test(expected = EntityExistsException.class)
    public void testAddNullNumber() {
        address.setNumber(null);
        dao.add(address);
    }

    /**
     * Test method for add method with null street.
     */
    @Test(expected = EntityExistsException.class)
    public void testAddNullStreet() {
        address.setStreet(null);
        dao.add(address);
    }

    /**
     * Test method for findAll method.
     */
    @Test
    public void testFindAll() {
        int sizeBefore = dao.findAll().size();
        dao.add(address);
        Address anOtherAddress = new Address();
        anOtherAddress.setCity("Brussels");
        anOtherAddress.setCountry("Belgium");
        anOtherAddress.setNumber("1");
        anOtherAddress.setStreet("Rue Neuve");
        anOtherAddress.setZipCode("1000");
        dao.add(anOtherAddress);
        List<Address> addresses = dao.findAll();
        if (sizeBefore > 0) {
            assertTrue(addresses.size() == (sizeBefore + 2));
        } else {
            assertTrue(addresses.size() == 2);
        }
    }

    /**
     * Test method for findById method.
     */
    @Test
    public void testFindById() {
        dao.add(address);
        Address foundAddress = dao.findById(address.getId());
        assertNotNull(foundAddress);
    }

    /**
     * Test method for delete method.
     */
    @Test
    public void testDelete() {
        dao.add(address);
        dao.delete(address);
        Address foundAddress = dao.findById(address.getId());
        assertNull(foundAddress);
    }

    /**
     * Test method for update method.
     */
    @Test
    public void testUpdate() {
        dao.add(address);
        String fakeNumber = "101";
        address.setNumber(fakeNumber);
        dao.update(address);
        Address foundAddress = dao.findById(address.getId());
        assertTrue(foundAddress.getNumber().equals(fakeNumber));
    }

}
