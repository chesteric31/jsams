package be.jsams.common.bean.model;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import be.jsams.common.bean.builder.SocietyBeanBuilder;

/**
 * Test class for {@link SocietyBean} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class SocietyBeanTest extends AbstractTransactionalJUnit4SpringContextTests {
    
    private SocietyBean bean;
    @Autowired
    private SocietyBeanBuilder builder;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        bean = builder.build(true);
    }

    /**
     * Test method for {@link be.jsams.common.bean.model.SocietyBean#refresh(be.jsams.common.bean.model.AbstractIdentityBean)}.
     */
    @Test
    public void testRefreshEquals() {
        SocietyBean otherBean = MockBeanGenerator.generateMockSociety();
        bean.refresh(otherBean);
        assertTrue(bean.equals(otherBean));
    }

}
