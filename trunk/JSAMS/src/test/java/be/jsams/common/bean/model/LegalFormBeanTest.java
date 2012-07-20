package be.jsams.common.bean.model;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import be.jsams.server.model.LegalForm;

/**
 * Test class for {@link LegalFormBean} class. 
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class LegalFormBeanTest {

    private LegalFormBean bean;

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
        bean = new LegalFormBean(new LegalForm());
    }

    /**
     * Test method for
     * {@link be.jsams.common.bean.model.AbstractTranslatableIdentityBean#refresh(
     * be.jsams.common.bean.model.AbstractIdentityBean)}
     * .
     */
    @Test
    public void testRefresh() {
        LegalFormBean otherBean = MockBeanGenerator.generateMockLegalForm();
        bean.refresh(otherBean);
        assertTrue(bean.equals(otherBean));
    }

}
