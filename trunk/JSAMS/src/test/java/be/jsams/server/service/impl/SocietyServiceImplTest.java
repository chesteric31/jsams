package be.jsams.server.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import be.jsams.common.bean.model.LegalFormBean;
import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.server.dao.BaseJUnitTestClass;
import be.jsams.server.dao.LegalFormDao;
import be.jsams.server.model.LegalForm;
import be.jsams.server.service.SocietyService;

/**
 * Test class for {@link SocietyServiceImpl} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class SocietyServiceImplTest extends BaseJUnitTestClass {

    @Autowired
    @Qualifier(value = "societyServiceTarget")
    private SocietyService service;
    @Autowired
    private LegalFormDao legalFormDao;
    private SocietyBean society;

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
        society = MockBeanGenerator.generateMockSociety();
        LegalForm legalForm = legalFormDao.add(new LegalForm(society.getLegalForm()));
        society.setLegalForm(new LegalFormBean(legalForm));
    }
    
    /**
     * Test method for {@link be.jsams.server.service.impl.SocietyServiceImpl#findAll()}.
     */
    @Test
    public void testFindAll() {
        SocietyBean created = service.create(society);
        List<SocietyBean> founds = service.findAll();
        assertTrue(founds.contains(created));
    }

    /**
     * Test method for {@link be.jsams.server.service.impl.SocietyServiceImpl#findById(java.lang.Long)}.
     */
    @Test
    public void testFindById() {
        SocietyBean created = service.create(society);
        SocietyBean found = service.findById(created.getId());
        assertEquals(created, found);
    }

    /**
     * Test method for {@link be.jsams.server.service.impl.SocietyServiceImpl
     * #update(be.jsams.common.bean.model.SocietyBean)}.
     */
    @Test
    public void testUpdate() {
        SocietyBean created = service.create(society);
        String newName = "newName";
        created.setName(newName);
        service.update(created);
        SocietyBean found = service.findById(created.getId());
        assertEquals(newName, found.getName());
    }

    /**
     * Test method for {@link be.jsams.server.service.impl.SocietyServiceImpl
     * #create(be.jsams.common.bean.model.SocietyBean)}.
     */
    @Test
    public void testCreate() {
        SocietyBean created = service.create(society);
        SocietyBean found = service.findById(created.getId());
        assertEquals(created, found);
    }

    /**
     * Test method for {@link be.jsams.server.service.impl.SocietyServiceImpl
     * #delete(be.jsams.common.bean.model.SocietyBean)}.
     */
    @Test
    public void testDeleteSocietyBean() {
        SocietyBean created = service.create(society);
        service.delete(created);
        SocietyBean found = service.findById(created.getId());
        assertNull(found);
    }

    /**
     * Test method for {@link be.jsams.server.service.impl.SocietyServiceImpl#delete(java.lang.Long)}.
     */
    @Test
    public void testDeleteLong() {
        SocietyBean created = service.create(society);
        // trick to avoid transient object exception
        created.setLegalForm(null);
        Long id = created.getId();
        service.delete(id);
        SocietyBean found = service.findById(id);
        assertNull(found);
    }

}
