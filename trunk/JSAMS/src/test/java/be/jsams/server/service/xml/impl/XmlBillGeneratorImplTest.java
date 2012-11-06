package be.jsams.server.service.xml.impl;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.junit.Test;

import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.common.bean.model.sale.detail.BillDetailBean;
import be.jsams.server.model.xml.bill.BillXml;
import be.jsams.server.model.xml.bill.DetailXml;
import be.jsams.server.model.xml.bill.DetailsXml;

/**
 * Test class for {@link XmlBillGeneratorImpl} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class XmlBillGeneratorImplTest {

    private XmlBillGeneratorImpl generator = new XmlBillGeneratorImpl();
    
    /**
     * Test method for
     * {@link be.jsams.server.service.xml.impl.XmlBillGeneratorImpl
     * #generateXml(be.jsams.common.bean.model.sale.BillBean)}
     * .
     */
    @Test
    public void testGenerateXml() {
        BillBean bean = MockBeanGenerator.generateMockBill();
        BillXml xml = generator.generateXml(bean);
        XmlTest.checkAddress(bean.getBillingAddress(), xml.getAddress());
        assertEquals(bean.getCreationDate(), xml.getCreationDate());
        XmlTest.checkCustomer(bean.getCustomer(), xml.getCustomer());
        assertEquals(bean.getDueDate(), xml.getDueDate());
        DetailsXml detailsXml = xml.getDetails();
        List<BillDetailBean> detailsBean = bean.getDetails();
        List<DetailXml> detail = detailsXml.getDetail();
        if (detail != null && !detail.isEmpty()) {
            int i = 0;
            for (DetailXml detailXml : detail) {
                BillDetailBean detailBean = detailsBean.get(i);
                XmlTest.checkProduct(detailBean.getProduct(), detailXml.getProduct());
                assertEquals(BigDecimal.valueOf(detailBean.getPrice()), detailXml.getPrice());
                assertEquals(BigInteger.valueOf(detailBean.getQuantity()), detailXml.getQuantity());
            }
        }
        assertEquals(bean.getId(), xml.getNumber());
        XmlTest.checkSociety(bean.getSociety(), xml.getSociety());
    }

}
