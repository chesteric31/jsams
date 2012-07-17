package be.jsams.common.bean.model;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for {@link PeriodBean} class.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class PeriodBeanTest {

    private PeriodBean bean;

    /**
     * 
     * @throws Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
        bean = new PeriodBean();
    }

    /**
     * Test method for
     * {@link be.jsams.common.bean.model.PeriodBean#refresh(be.jsams.common.bean.model.AbstractIdentityBean)}
     * .
     * 
     * @throws ParseException a possible {@link ParseException}
     */
    @Test
    public void testRefreshEquals() throws ParseException {
        PeriodBean otherBean = MockBeanGenerator.generateMockPeriod();
        bean.refresh(otherBean);
        assertTrue(bean.equals(otherBean));
    }

}
