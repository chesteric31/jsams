package be.jsams.server.service.transfer.impl;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import be.jsams.server.dao.BaseJUnitTestClass;

/**
 * 
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class EstimateCommandTransferServiceImplTest extends BaseJUnitTestClass {
    
    @Autowired
    private EstimateCommandTransferServiceImpl service;

    /**
     * Test method for {@link be.jsams.server.service.transfer.impl.EstimateCommandTransferServiceImpl#createNewDocuments(be.jsams.common.bean.model.transfer.TransferBean)}.
     */
    @Test
    public void testCreateNewDocumentsTransferBean() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link be.jsams.server.service.transfer.impl.EstimateCommandTransferServiceImpl#persistNewDocuments(java.util.List)}.
     */
    @Test
    public void testPersistNewDocumentsListOfCommandBean() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link be.jsams.server.service.transfer.impl.EstimateCommandTransferServiceImpl#updateOriginalDocuments(java.util.List)}.
     */
    @Test
    public void testUpdateOriginalDocumentsListOfEstimateBean() {
        fail("Not yet implemented");
    }

}
