package be.jsams.server.service.xml.impl;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.junit.Test;

import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.sale.CreditNoteBean;
import be.jsams.common.bean.model.sale.detail.CreditNoteDetailBean;
import be.jsams.server.model.xml.creditNote.CreditNoteXml;
import be.jsams.server.model.xml.creditNote.DetailXml;
import be.jsams.server.model.xml.creditNote.DetailsXml;

/**
 * Test class for {@link XmlCreditNoteGeneratorImpl} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class XmlCreditNoteGeneratorImplTest {

    private XmlCreditNoteGeneratorImpl generator = new XmlCreditNoteGeneratorImpl();
    
    /**
     * Test method for
     * {@link be.jsams.server.service.xml.impl.XmlCreditNoteGeneratorImpl
     * #generateXml(be.jsams.common.bean.model.sale.CreditNoteBean)}
     * .
     */
    @Test
    public void testGenerateXml() {
        CreditNoteBean bean = MockBeanGenerator.generateMockCreditNote();
        CreditNoteXml xml = generator.generateXml(bean);
        XmlTest.checkAddress(bean.getBillingAddress(), xml.getAddress());
        assertEquals(bean.getCreationDate(), xml.getCreationDate());
        XmlTest.checkCustomer(bean.getCustomer(), xml.getCustomer());
        DetailsXml detailsXml = xml.getDetails();
        List<CreditNoteDetailBean> detailsBean = bean.getDetails();
        List<DetailXml> detail = detailsXml.getDetail();
        if (detail != null && !detail.isEmpty()) {
            int i = 0;
            for (DetailXml detailXml : detail) {
                CreditNoteDetailBean detailBean = detailsBean.get(i);
                XmlTest.checkProduct(detailBean.getProduct(), detailXml.getProduct());
                assertEquals(BigDecimal.valueOf(detailBean.getPrice()), detailXml.getPrice());
                assertEquals(BigInteger.valueOf(detailBean.getQuantity()), detailXml.getQuantity());
            }
        }
        assertEquals(bean.getId(), xml.getNumber());
        XmlTest.checkSociety(bean.getSociety(), xml.getSociety());
    }

}
