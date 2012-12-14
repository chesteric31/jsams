package be.jsams.server.service.transfer.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.common.bean.model.sale.DeliveryOrderBean;
import be.jsams.common.bean.model.sale.detail.CommandDetailBean;
import be.jsams.common.bean.model.transfer.TransferBean;
import be.jsams.server.BaseJUnitTestClass;

/**
 * Test class for {@link CommandDeliveryOrderTransferServiceImpl} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class CommandDeliveryOrderTransferServiceImplTest extends BaseJUnitTestClass {

    @Autowired
    private CommandDeliveryOrderTransferServiceImpl service;
    
    private TransferBean model;

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
        model = new TransferBean();
    }
    
    /**
     * Test method for {@link be.jsams.server.service.transfer.impl.CommandDeliveryOrderTransferServiceImpl
     * #createNewDocuments(be.jsams.common.bean.model.transfer.TransferBean)}.
     */
    @Test
    public void testCreateNewDocumentsFullTransferBean() {
        model.setSourceType(2);
        model.setDestinationType(2);
        model.setTransferMode(1);
        CommandBean originalDocument = MockBeanGenerator.generateMockCommand();
        List<CommandBean> documents = new ArrayList<CommandBean>();
        documents.add(originalDocument);
        model.setDocuments(documents);
        List<DeliveryOrderBean> newDocuments = service.createNewDocuments(model);
        assertTrue(newDocuments != null && !newDocuments.isEmpty() && newDocuments.size() == 1);
        DeliveryOrderBean newDocument = newDocuments.get(0);
        assertEquals(originalDocument.getDeliveryAddress(), newDocument.getDeliveryAddress());
    }

    /**
     * Test method for {@link be.jsams.server.service.transfer.impl.CommandDeliveryOrderTransferServiceImpl
     * #createNewDocuments(be.jsams.common.bean.model.transfer.TransferBean)}.
     */
    @Test
    public void testCreateNewDocumentsPartialTransferBean() {
        model.setSourceType(2);
        model.setDestinationType(2);
        model.setTransferMode(2);
        CommandBean originalDocument = MockBeanGenerator.generateMockCommand();
        List<CommandBean> documents = new ArrayList<CommandBean>();
        documents.add(originalDocument);
        Map<Long, List<CommandDetailBean>> map = new HashMap<Long, List<CommandDetailBean>>();
        List<CommandDetailBean> list = new ArrayList<CommandDetailBean>();
        list.addAll(originalDocument.getDetails());
        map.put(originalDocument.getId(), list);
        model.setCommandDetails(map);
        List<DeliveryOrderBean> newDocuments = service.createNewDocuments(model);
        assertTrue(newDocuments != null && !newDocuments.isEmpty() && newDocuments.size() == 1);
        DeliveryOrderBean newDocument = newDocuments.get(0);
        assertEquals(originalDocument.getCustomer(), newDocument.getCustomer());
        assertEquals(originalDocument.getDetails().get(0).getProduct(), newDocument.getDetails().get(0).getProduct());
    }
    
}
