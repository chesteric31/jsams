package be.jsams.server.service.xml.impl;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.List;

import org.junit.Test;

import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.sale.DeliveryOrderBean;
import be.jsams.common.bean.model.sale.detail.DeliveryOrderDetailBean;
import be.jsams.server.model.xml.deliveryOrder.DeliveryOrderXml;
import be.jsams.server.model.xml.deliveryOrder.DetailXml;
import be.jsams.server.model.xml.deliveryOrder.DetailsXml;

/**
 * Test class for {@link XmlDeliveryOrderGeneratorImpl} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class XmlDeliveryOrderGeneratorImplTest {

    private XmlDeliveryOrderGeneratorImpl generator = new XmlDeliveryOrderGeneratorImpl();
    
    /**
     * Test method for
     * {@link be.jsams.server.service.xml.impl.XmlDeliveryOrderGeneratorImpl
     * #generateXml(be.jsams.common.bean.model.sale.DeliveryOrderBean)}
     * .
     */
    @Test
    public void testGenerateXml() {
        DeliveryOrderBean bean = MockBeanGenerator.generateMockDeliveryOrder();
        DeliveryOrderXml xml = generator.generateXml(bean);
        XmlTest.checkAddress(bean.getDeliveryAddress(), xml.getAddress());
        assertEquals(bean.getCreationDate(), xml.getCreationDate());
        XmlTest.checkCustomer(bean.getCustomer(), xml.getCustomer());
        DetailsXml detailsXml = xml.getDetails();
        List<DeliveryOrderDetailBean> detailsBean = bean.getDetails();
        List<DetailXml> detail = detailsXml.getDetail();
        if (detail != null && !detail.isEmpty()) {
            int i = 0;
            for (DetailXml detailXml : detail) {
                DeliveryOrderDetailBean detailBean = detailsBean.get(i);
                XmlTest.checkProduct(detailBean.getProduct(), detailXml.getProduct());
                assertEquals(BigInteger.valueOf(detailBean.getQuantity()), detailXml.getQuantity());
            }
        }
        assertEquals(bean.getId(), xml.getNumber());
        XmlTest.checkSociety(bean.getSociety(), xml.getSociety());
    }

}
