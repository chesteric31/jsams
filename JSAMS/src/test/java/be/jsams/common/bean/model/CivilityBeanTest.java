package be.jsams.common.bean.model;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import be.jsams.server.model.Civility;

/**
 * Test class for {@link CivilityBean} class. 
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class CivilityBeanTest {

    private CivilityBean bean;

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
    }
    
    /**
     * Test method for
     * {@link be.jsams.common.bean.model.AbstractTranslatableIdentityBean#refresh(
     * be.jsams.common.bean.model.AbstractIdentityBean)}
     * .
     */
    @Test
    public void testRefreshEquals() {
        bean = new CivilityBean(new Civility());
        CivilityBean otherBean = MockBeanGenerator.generateMockCivility();
        bean.refresh(otherBean);
        assertTrue(bean.equals(otherBean));
    }

    /**
     * Test method for
     * {@link be.jsams.common.bean.model.AbstractTranslatableIdentityBean#clear()}.
     */
    @Test
    public void testClear() {
        bean = MockBeanGenerator.generateMockCivility();
        bean.clear();
        assertNull(bean.getLabel());
        assertNull(bean.getLabelFr());
        assertNull(bean.getLabelNl());
    }

}
