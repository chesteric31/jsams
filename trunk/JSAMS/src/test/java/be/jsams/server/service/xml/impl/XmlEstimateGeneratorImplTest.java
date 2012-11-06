package be.jsams.server.service.xml.impl;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.junit.Test;

import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.common.bean.model.sale.detail.EstimateDetailBean;
import be.jsams.server.model.xml.estimate.DetailXml;
import be.jsams.server.model.xml.estimate.DetailsXml;
import be.jsams.server.model.xml.estimate.EstimateXml;

/**
 * Test class for {@link XmlEstimateGeneratorImpl} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class XmlEstimateGeneratorImplTest {

    private XmlEstimateGeneratorImpl generator = new XmlEstimateGeneratorImpl();
    
    /**
     * Test method for
     * {@link be.jsams.server.service.xml.impl.XmlEstimateGeneratorImpl
     * #generateXml(be.jsams.common.bean.model.sale.EstimateBean)}
     * .
     */
    @Test
    public void testGenerateXml() {
        EstimateBean bean = MockBeanGenerator.generateMockEstimate();
        EstimateXml xml = generator.generateXml(bean);
        XmlTest.checkAddress(bean.getBillingAddress(), xml.getAddress());
        assertEquals(bean.getCreationDate(), xml.getCreationDate());
        XmlTest.checkCustomer(bean.getCustomer(), xml.getCustomer());
        DetailsXml detailsXml = xml.getDetails();
        List<EstimateDetailBean> detailsBean = bean.getDetails();
        List<DetailXml> detail = detailsXml.getDetail();
        if (detail != null && !detail.isEmpty()) {
            int i = 0;
            for (DetailXml detailXml : detail) {
                EstimateDetailBean detailBean = detailsBean.get(i);
                XmlTest.checkProduct(detailBean.getProduct(), detailXml.getProduct());
                assertEquals(BigDecimal.valueOf(detailBean.getPrice()), detailXml.getPrice());
                assertEquals(BigInteger.valueOf(detailBean.getQuantity()), detailXml.getQuantity());
            }
        }
        assertEquals(bean.getId(), xml.getNumber());
        XmlTest.checkSociety(bean.getSociety(), xml.getSociety());
    }

}
