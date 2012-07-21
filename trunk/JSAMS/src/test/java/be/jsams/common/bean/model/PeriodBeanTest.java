package be.jsams.common.bean.model;

import static org.junit.Assert.assertNull;
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
        bean = new PeriodBean();
        PeriodBean otherBean = MockBeanGenerator.generateMockPeriod();
        bean.refresh(otherBean);
        assertTrue(bean.equals(otherBean));
    }

    /**
     * Test method for {@link be.jsams.common.bean.model.PeriodBean#clear()}.
     * 
     * @throws ParseException a possible {@link ParseException}
     */
    @Test
    public void testClear() throws ParseException {
        bean = MockBeanGenerator.generateMockPeriod();
        bean.clear();
        assertNull(bean.getStartDate());
        assertNull(bean.getEndDate());
    }

}
