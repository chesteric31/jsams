package be.jsams.client.validator.wizard;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import be.jsams.client.i18n.I18nApplicationContext;
import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.validator.AbstractValidatorTest;
import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.transfer.TransferBean;

import com.jgoodies.validation.ValidationResult;

/**
 * Test class for {@link DocumentValidator} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class DocumentValidatorTest extends AbstractValidatorTest {

    private DocumentValidator validator;
    private TransferBean bean;

    /**
     * Setup method.
     */
    @Before
    public void setUp() {
        I18nApplicationContext.setContext(new ClassPathXmlApplicationContext(I18nApplicationContext.CONFIG));
        validator = new DocumentValidator();
        bean = MockBeanGenerator.generateMockTransfer();
    }

    /**
     * Test method for {@link be.jsams.client.validator.wizard.DocumentValidator
     * #validate(be.jsams.common.bean.model.transfer.TransferBean)}.
     */
    @Test
    public void testValidate() {
        ValidationResult result = validator.validate(bean);
        assertTrue(result.isEmpty());
    }

    /**
     * Test method for {@link be.jsams.client.validator.wizard.DocumentValidator
     * #validate(be.jsams.common.bean.model.transfer.TransferBean)}.
     */
    @Test
    public void testValidateBlankDocument() {
        bean.setDocuments(null);
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(JsamsI18nResource.ERROR_IS_MANDATORY_TO_SELECT.getTranslation()));
        assertTrue(formattedText.contains(JsamsI18nLabelResource.LABEL_DOCUMENT.getTranslation()));
    }

}
