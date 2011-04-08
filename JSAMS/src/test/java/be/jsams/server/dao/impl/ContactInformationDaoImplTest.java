package be.jsams.server.dao.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityExistsException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import be.jsams.server.dao.AbstractJUnitTestClass;
import be.jsams.server.dao.ContactInformationDao;
import be.jsams.server.model.ContactInformation;

/**
 * Test class for {@link ContactInformationDaoImpl}.
 *
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public class ContactInformationDaoImplTest extends AbstractJUnitTestClass {

    @Autowired
    private ContactInformationDao dao;
    
    private ContactInformation contactInformation;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        contactInformation = new ContactInformation();
        contactInformation.setPhone("08161553322");
    }

    /**
     * Test method for {@link be.jsams.server.dao.impl.DaoImpl#add(java.lang.Object)}.
     */
    @Test
    public void testAdd() {
        dao.add(contactInformation);
        assertNotNull(contactInformation.getId());
    }

    /**
     * Test method for {@link be.jsams.server.dao.impl.DaoImpl#findAll()}.
     */
    @Test
    public void testFindAll() {
        int sizeBefore = dao.findAll().size();
        dao.add(contactInformation);
        ContactInformation anOtherContactInformation = new ContactInformation();
        anOtherContactInformation.setPhone("0212345678");
        dao.add(anOtherContactInformation);
        List<ContactInformation> informations = dao.findAll();
        if (sizeBefore > 0) {
            assertTrue(informations.size() == (sizeBefore + 2));
        } else {
            assertTrue(informations.size() == 2);
        }
    }

    /**
     * Test method for {@link be.jsams.server.dao.impl.DaoImpl#findById(java.lang.Long)}.
     */
    @Test
    public void testFindById() {
        dao.add(contactInformation);
        ContactInformation foundInformation = dao.findById(contactInformation.getId());
        assertNotNull(foundInformation);
    }

    /**
     * Test method for {@link be.jsams.server.dao.impl.DaoImpl#delete(java.lang.Object)}.
     */
    @Test
    public void testDelete() {
        dao.add(contactInformation);
        dao.delete(contactInformation);
        ContactInformation foundInformation = dao.findById(contactInformation.getId());
        assertNull(foundInformation);
    }

    /**
     * Test method for {@link be.jsams.server.dao.impl.DaoImpl#update(java.lang.Object)}.
     */
    @Test
    public void testUpdate() {
        dao.add(contactInformation);
        String fakePhone = "0211334454";
        contactInformation.setPhone(fakePhone);
        dao.update(contactInformation);
        ContactInformation foundInformation = dao.findById(contactInformation.getId());
        assertTrue(foundInformation.getPhone().equals(fakePhone));
    }

    @Test(expected = EntityExistsException.class)
    public void testAddNullPhone() {
        contactInformation.setPhone(null);
        dao.add(contactInformation);
    }

}
