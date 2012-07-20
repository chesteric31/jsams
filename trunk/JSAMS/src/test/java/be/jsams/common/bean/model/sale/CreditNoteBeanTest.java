package be.jsams.common.bean.model.sale;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import be.jsams.common.bean.model.MockBeanGenerator;

/**
 * Test class for {@link CreditNoteBean} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class CreditNoteBeanTest {

    private CreditNoteBean bean;

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
        bean = new CreditNoteBean(MockBeanGenerator.generateMockSociety(), MockBeanGenerator.generateMockCustomer());
    }
    
    /**
     * Test method for
     * {@link be.jsams.common.bean.model.sale.CreditNoteBean#refresh(be.jsams.common.bean.model.AbstractIdentityBean)}
     * .
     */
    @Test
    public void testRefreshEquals() {
        CreditNoteBean otherBean = MockBeanGenerator.generateMockCreditNote();
        bean.refresh(otherBean);
        assertTrue(bean.equals(otherBean));
    }

}
