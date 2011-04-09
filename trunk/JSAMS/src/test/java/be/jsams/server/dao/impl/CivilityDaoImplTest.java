package be.jsams.server.dao.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import be.jsams.server.dao.AbstractJUnitTestClass;
import be.jsams.server.dao.CivilityDao;
import be.jsams.server.model.Civility;
import be.jsams.server.model.mock.MockModelGenerator;

/**
 * Test class for {@link CivilityDaoImpl}.
 *
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public class CivilityDaoImplTest extends AbstractJUnitTestClass {

    @Autowired
    private CivilityDao dao;
    
    private Civility civility;
    
    @Before
    public void setUp() throws Exception {
        civility = MockModelGenerator.generateMockCivility();
    }

    @Test
    public void testAdd() {
        dao.add(civility);
        assertNotNull(civility.getId());
    }

    @Test
    public void testFindAll() {
        int sizeBefore = dao.findAll().size();
        dao.add(civility);
        Civility anOtherCivility = new Civility();
        anOtherCivility.setLabel("anOtherLabel");
        anOtherCivility.setLabelFr("anOtherLabelFr");
        anOtherCivility.setLabelNl("anOtherLabelNl");
        dao.add(anOtherCivility);
        List<Civility> civilities = dao.findAll();
        if (sizeBefore > 0) {
            assertTrue(civilities.size() == (sizeBefore + 2));
        } else {
            assertTrue(civilities.size() == 2);
        }
    }

    @Test
    public void testFindById() {
        dao.add(civility);
        Civility foundCivility = dao.findById(civility.getId());
        assertNotNull(foundCivility);
    }

    @Test
    public void testDelete() {
        dao.add(civility);
        dao.delete(civility);
        Civility foundCivility = dao.findById(civility.getId());
        assertNull(foundCivility);
    }

    @Test
    public void testUpdate() {
        dao.add(civility);
        String fakeLabel = "fakeLabel";
        civility.setLabel(fakeLabel);
        dao.update(civility);
        Civility foundCivility = dao.findById(civility.getId());
        assertTrue(foundCivility.getLabel().equals(fakeLabel));
    }

}
