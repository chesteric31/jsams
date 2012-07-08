package be.jsams.common.bean.model;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @Before
    public void setUp() throws Exception {
        bean = new PeriodBean();
    }

    /**
     * Test method for {@link be.jsams.common.bean.model.PeriodBean#refresh(be.jsams.common.bean.model.AbstractIdentityBean)}.
     * @throws ParseException a {@link ParseException}
     */
    @Test
    public void testRefreshEquals() throws ParseException {
        PeriodBean otherBean = new PeriodBean();
        String pattern = "dd/MM/yyyy";
        Date startDate = new SimpleDateFormat(pattern).parse("01/01/2000");
        Date endDate = new SimpleDateFormat(pattern).parse("31/12/2001");
        otherBean.setStartDate(startDate);
        otherBean.setEndDate(endDate);
        bean.refresh(otherBean);
        assertTrue(bean.equals(otherBean));
    }

}
