package be.jsams.server.dao.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import be.jsams.server.dao.AbstractJUnitTestClass;
import be.jsams.server.dao.SocietyDao;
import be.jsams.server.model.Address;
import be.jsams.server.model.ContactInformation;
import be.jsams.server.model.Society;

/**
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SocietyDaoImplTest extends AbstractJUnitTestClass {

    @Autowired
    private SocietyDao dao;
    
    private Society society;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        society = new Society();
        society.setActivity("Activity");
        society.setCapital(new Double("123456.78"));
        society.setName("Name");
        society.setVatNumber("BE123456789");
        
        Address address = new Address();
        address.setCity("Brussels");
        address.setCountry("Belgium");
        address.setNumber("1");
        address.setStreet("Rue Neuve");
        address.setZipCode("1000");
        
        society.setAddress(address);
        
        ContactInformation contactInformation = new ContactInformation();
        contactInformation.setPhone("+3221223456");
        
        society.setContactInformation(contactInformation);
    }

    /**
     * Test method for {@link be.jsams.server.dao.impl.DaoImpl#add(java.lang.Object)}.
     */
    @Test
    public void testAdd() {
        dao.add(society);
        assertNotNull(society.getId());
    }

    /**
     * Test method for {@link be.jsams.server.dao.impl.DaoImpl#findAll()}.
     */
    @Test
    public void testFindAll() {
        dao.add(society);
        assertTrue(dao.findAll().size() > 0);
    }

    /**
     * Test method for {@link be.jsams.server.dao.impl.DaoImpl#findById(java.lang.Long)}.
     */
    @Test
    public void testFindById() {
        dao.add(society);
        assertNotNull(dao.findById(society.getId()));
    }

    /**
     * Test method for {@link be.jsams.server.dao.impl.DaoImpl#delete(java.lang.Object)}.
     */
    @Test
    public void testRemove() {
        dao.add(society);
        assertNotNull(dao.findById(society.getId()));
        dao.delete(society);
        assertNull(dao.findById(society.getId()));
    }

    /**
     * Test method for {@link be.jsams.server.dao.impl.DaoImpl#update(java.lang.Object)}.
     */
    @Test
    public void testUpdate() {
        dao.add(society);
        assertNotNull(dao.findById(society.getId()));
        society.setActivity("Management");
        dao.update(society);
        Society findById = dao.findById(society.getId());
        assertTrue(findById.getActivity().equals("Management"));
    }

    /**
     * Test method for {@link be.jsams.server.dao.impl.DaoImpl#delete(java.lang.Long)}.
     */
    @Test
    public void testRemoveWithId() {
        dao.add(society);
        Long id = society.getId();
        assertNotNull(id);
        dao.delete(id);
        assertNull(dao.findById(id));
    }

}
