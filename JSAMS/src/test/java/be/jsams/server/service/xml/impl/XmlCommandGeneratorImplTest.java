package be.jsams.server.service.xml.impl;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.junit.Test;

import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.common.bean.model.sale.detail.CommandDetailBean;
import be.jsams.server.model.xml.command.CommandXml;
import be.jsams.server.model.xml.command.DetailXml;
import be.jsams.server.model.xml.command.DetailsXml;

/**
 * 
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class XmlCommandGeneratorImplTest extends AbstractXmlTest {

    private XmlCommandGeneratorImpl generator = new XmlCommandGeneratorImpl();
    
    /**
     * Test method for
     * {@link be.jsams.server.service.xml.impl.XmlCommandGeneratorImpl
     * #generateXml(be.jsams.common.bean.model.sale.CommandBean)}
     * .
     */
    @Test
    public void testGenerateXml() {
        CommandBean bean = MockBeanGenerator.generateMockCommand();
        CommandXml xml = generator.generateXml(bean);
        checkAddress(bean.getBillingAddress(), xml.getAddress());
        assertEquals(bean.getCreationDate(), xml.getCreationDate());
        checkCustomer(bean.getCustomer(), xml.getCustomer());
        DetailsXml detailsXml = xml.getDetails();
        List<CommandDetailBean> detailsBean = bean.getDetails();
        List<DetailXml> detail = detailsXml.getDetail();
        if (detail != null && !detail.isEmpty()) {
            int i = 0;
            for (DetailXml detailXml : detail) {
                CommandDetailBean detailBean = detailsBean.get(i);
                checkProduct(detailBean.getProduct(), detailXml.getProduct());
                assertEquals(BigDecimal.valueOf(detailBean.getPrice()), detailXml.getPrice());
                assertEquals(BigInteger.valueOf(detailBean.getQuantity()), detailXml.getQuantity());
            }
        }
        assertEquals(bean.getId(), xml.getNumber());
        checkSociety(bean.getSociety(), xml.getSociety());
    }

}
