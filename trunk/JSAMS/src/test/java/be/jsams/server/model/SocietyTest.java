package be.jsams.server.model;

import org.junit.Test;

import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.SocietyBean;

/**
 * Test class for {@link Society} class.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class SocietyTest {

    private Society society;

    /**
     * Test method for
     * {@link be.jsams.server.model.Society#Society(be.jsams.common.bean.model.SocietyBean)}
     * .
     */
    @Test
    public void testSocietySocietyBean() {
        SocietyBean bean = MockBeanGenerator.generateMockSociety();
        society = new Society(bean);
        ModelTestHelper.checkSociety(bean, society);
    }

}
