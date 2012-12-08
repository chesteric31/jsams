package be.jsams.client.validator.wizard;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import be.jsams.client.i18n.I18nApplicationContext;
import be.jsams.client.i18n.I18nLabelResource;
import be.jsams.client.i18n.I18nResource;
import be.jsams.client.validator.AbstractValidatorTest;
import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.transfer.TransferBean;

import com.jgoodies.validation.ValidationResult;

/**
 * Test class for {@link DocumentsValidator} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class DocumentsValidatorTest extends AbstractValidatorTest {

    private DocumentsValidator validator;
    private TransferBean bean;

    /**
     * Setup method.
     */
    @Before
    public void setUp() {
        I18nApplicationContext.setContext(new ClassPathXmlApplicationContext(I18nApplicationContext.CONFIG));
        validator = new DocumentsValidator();
        bean = MockBeanGenerator.generateMockTransfer();
    }

    /**
     * Test method for {@link be.jsams.client.validator.wizard.DocumentsValidator
     * #validate(be.jsams.common.bean.model.transfer.TransferBean)}.
     */
    @Test
    public void testValidate() {
        ValidationResult result = validator.validate(bean);
        assertTrue(result.isEmpty());
    }

    /**
     * Test method for {@link be.jsams.client.validator.wizard.DocumentsValidator
     * #validate(be.jsams.common.bean.model.transfer.TransferBean)}.
     */
    @Test
    public void testValidateBlankDocuments() {
        bean.setDocuments(null);
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(I18nResource.ERROR_IS_MANDATORY_TO_SELECT.getTranslation()));
        assertTrue(formattedText.contains(I18nLabelResource.LABEL_DOCUMENTS.getTranslation()));
    }

}
