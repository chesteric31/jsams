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
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.common.bean.model.sale.DeliveryOrderBean;
import be.jsams.common.bean.model.sale.detail.DeliveryOrderDetailBean;
import be.jsams.common.bean.model.transfer.TransferBean;
import be.jsams.server.BaseJUnitTestClass;

/**
 * Test class for {@link DeliveryOrderBillTransferServiceImpl} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class DeliveryOrderBillTransferServiceImplTest extends BaseJUnitTestClass {

    @Autowired
    private DeliveryOrderBillTransferServiceImpl service;
    
    private TransferBean model;

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
        model = new TransferBean();
    }

    /**
     * Test method for {@link be.jsams.server.service.transfer.impl.DeliveryOrderBillTransferServiceImpl
     * #createNewDocuments(be.jsams.common.bean.model.transfer.TransferBean)}.
     */
    @Test
    public void testCreateNewDocumentsFullTransferBean() {
        model.setSourceType(3);
        model.setDestinationType(4);
        model.setTransferMode(1);
        DeliveryOrderBean originalDocument = MockBeanGenerator.generateMockDeliveryOrder();
        List<DeliveryOrderBean> documents = new ArrayList<DeliveryOrderBean>();
        documents.add(originalDocument);
        model.setDocuments(documents);
        List<BillBean> newDocuments = service.createNewDocuments(model);
        assertTrue(newDocuments != null && !newDocuments.isEmpty() && newDocuments.size() == 1);
        BillBean newDocument = newDocuments.get(0);
        assertEquals(originalDocument.getCustomer(), newDocument.getCustomer());
    }

    /**
     * Test method for {@link be.jsams.server.service.transfer.impl.DeliveryOrderBillTransferServiceImpl
     * #createNewDocuments(be.jsams.common.bean.model.transfer.TransferBean)}.
     */
    @Test
    public void testCreateNewDocumentsPartialTransferBean() {
        model.setSourceType(3);
        model.setDestinationType(4);
        model.setTransferMode(2);
        DeliveryOrderBean originalDocument = MockBeanGenerator.generateMockDeliveryOrder();
        List<DeliveryOrderBean> documents = new ArrayList<DeliveryOrderBean>();
        documents.add(originalDocument);
        Map<Long, List<DeliveryOrderDetailBean>> map = new HashMap<Long, List<DeliveryOrderDetailBean>>();
        List<DeliveryOrderDetailBean> list = new ArrayList<DeliveryOrderDetailBean>();
        list.addAll(originalDocument.getDetails());
        map.put(originalDocument.getId(), list);
        model.setDeliveryOrderDetails(map);
        List<BillBean> newDocuments = service.createNewDocuments(model);
        assertTrue(newDocuments != null && !newDocuments.isEmpty() && newDocuments.size() == 1);
        BillBean newDocument = newDocuments.get(0);
        assertEquals(originalDocument.getCustomer(), newDocument.getCustomer());
        assertEquals(originalDocument.getDetails().get(0).getProduct(), newDocument.getDetails().get(0).getProduct());
    }

}
