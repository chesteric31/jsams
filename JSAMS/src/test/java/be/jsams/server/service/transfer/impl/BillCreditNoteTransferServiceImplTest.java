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
import be.jsams.common.bean.model.sale.CreditNoteBean;
import be.jsams.common.bean.model.sale.detail.BillDetailBean;
import be.jsams.common.bean.model.transfer.TransferBean;
import be.jsams.server.BaseJUnitTestClass;

/**
 * Test class for {@link BillCreditNoteTransferServiceImpl} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class BillCreditNoteTransferServiceImplTest extends BaseJUnitTestClass {

    @Autowired
    private BillCreditNoteTransferServiceImpl service;
    
    private TransferBean model;

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
        model = new TransferBean();
    }
    
    /**
     * Test method for {@link be.jsams.server.service.transfer.impl.BillCreditNoteTransferServiceImpl
     * #createNewDocuments(be.jsams.common.bean.model.transfer.TransferBean)}.
     */
    @Test
    public void testCreateNewDocumentsFullTransferBean() {
        model.setSourceType(4);
        model.setDestinationType(5);
        model.setTransferMode(1);
        BillBean originalDocument = MockBeanGenerator.generateMockBill();
        List<BillBean> documents = new ArrayList<BillBean>();
        documents.add(originalDocument);
        model.setDocuments(documents);
        List<CreditNoteBean> newDocuments = service.createNewDocuments(model);
        assertTrue(newDocuments != null && !newDocuments.isEmpty() && newDocuments.size() == 1);
        CreditNoteBean newDocument = newDocuments.get(0);
        assertEquals(originalDocument.getCustomer(), newDocument.getCustomer());
    }

    /**
     * Test method for {@link be.jsams.server.service.transfer.impl.BillCreditNoteTransferServiceImpl
     * #createNewDocuments(be.jsams.common.bean.model.transfer.TransferBean)}.
     */
    @Test
    public void testCreateNewDocumentsPartialTransferBean() {
        model.setSourceType(4);
        model.setDestinationType(5);
        model.setTransferMode(2);
        BillBean originalDocument = MockBeanGenerator.generateMockBill();
        List<BillBean> documents = new ArrayList<BillBean>();
        documents.add(originalDocument);
        Map<Long, List<BillDetailBean>> map = new HashMap<Long, List<BillDetailBean>>();
        List<BillDetailBean> list = new ArrayList<BillDetailBean>();
        list.addAll(originalDocument.getDetails());
        map.put(originalDocument.getId(), list);
        model.setBillDetails(map);
        List<CreditNoteBean> newDocuments = service.createNewDocuments(model);
        assertTrue(newDocuments != null && !newDocuments.isEmpty() && newDocuments.size() == 1);
        CreditNoteBean newDocument = newDocuments.get(0);
        assertEquals(originalDocument.getCustomer(), newDocument.getCustomer());
        assertEquals(originalDocument.getDetails().get(0).getProduct(), newDocument.getDetails().get(0).getProduct());
    }

}
