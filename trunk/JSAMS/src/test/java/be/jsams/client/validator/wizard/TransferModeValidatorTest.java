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
 * Test class for {@link TransferModeValidator} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class TransferModeValidatorTest extends AbstractValidatorTest {

    private TransferModeValidator validator;
    private TransferBean bean;

    /**
     * Setup method.
     */
    @Before
    public void setUp() {
        I18nApplicationContext.setContext(new ClassPathXmlApplicationContext(I18nApplicationContext.CONFIG));
        validator = new TransferModeValidator();
        bean = MockBeanGenerator.generateMockTransfer();
    }

    /**
     * Test method for {@link be.jsams.client.validator.wizard.TransferModeValidator
     * #validate(be.jsams.common.bean.model.transfer.TransferBean)}.
     */
    @Test
    public void testValidate() {
        ValidationResult result = validator.validate(bean);
        assertTrue(result.isEmpty());
    }

    /**
     * Test method for {@link be.jsams.client.validator.wizard.TransferModeValidator
     * #validate(be.jsams.common.bean.model.transfer.TransferBean)}.
     */
    @Test
    public void testValidateBlankTransferMode() {
        bean.setTransferMode(0);
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(I18nResource.ERROR_IS_MANDATORY.getTranslation()));
        assertTrue(formattedText.contains(I18nLabelResource.LABEL_TRANSFER_MODE.getTranslation()));
    }

}
