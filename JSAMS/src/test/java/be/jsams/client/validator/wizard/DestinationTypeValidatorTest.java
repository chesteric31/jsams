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
 * Test class for {@link DestinationTypeValidator} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class DestinationTypeValidatorTest extends AbstractValidatorTest {

    private DestinationTypeValidator validator;
    private TransferBean bean;

    /**
     * Setup method.
     */
    @Before
    public void setUp() {
        I18nApplicationContext.setContext(new ClassPathXmlApplicationContext(I18nApplicationContext.CONFIG));
        validator = new DestinationTypeValidator();
        bean = MockBeanGenerator.generateMockTransfer();
    }

    /**
     * Test method for {@link be.jsams.client.validator.wizard.DestinationTypeValidator
     * #validate(be.jsams.common.bean.model.transfer.TransferBean)}.
     */
    @Test
    public void testValidate() {
        ValidationResult result = validator.validate(bean);
        assertTrue(result.isEmpty());
    }

    /**
     * Test method for {@link be.jsams.client.validator.wizard.DestinationTypeValidator
     * #validate(be.jsams.common.bean.model.transfer.TransferBean)}.
     */
    @Test
    public void testValidateBlankDestinationType() {
        bean.setDestinationType(0);
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation()));
        assertTrue(formattedText.contains(JsamsI18nLabelResource.LABEL_DESTINATION_TYPE.getTranslation()));
    }

    /**
     * Test method for {@link be.jsams.client.validator.wizard.DestinationTypeValidator
     * #validate(be.jsams.common.bean.model.transfer.TransferBean)}.
     */
    @Test
    public void testValidateWrongEstimateDestinationType() {
        bean.setSourceType(1);
        bean.setDestinationType(2);
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(JsamsI18nResource.ERROR_SOURCE_DESTINATION.getTranslation()));
        assertTrue(formattedText.contains(JsamsI18nLabelResource.LABEL_DESTINATION_TYPE.getTranslation()));
        bean.setDestinationType(4);
        result = validator.validate(bean);
        formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(JsamsI18nResource.ERROR_SOURCE_DESTINATION.getTranslation()));
        assertTrue(formattedText.contains(JsamsI18nLabelResource.LABEL_DESTINATION_TYPE.getTranslation()));
    }

    /**
     * Test method for {@link be.jsams.client.validator.wizard.DestinationTypeValidator
     * #validate(be.jsams.common.bean.model.transfer.TransferBean)}.
     */
    @Test
    public void testValidateWrongCommandDestinationType() {
        bean.setSourceType(2);
        bean.setDestinationType(1);
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(JsamsI18nResource.ERROR_SOURCE_DESTINATION.getTranslation()));
        assertTrue(formattedText.contains(JsamsI18nLabelResource.LABEL_DESTINATION_TYPE.getTranslation()));
        bean.setDestinationType(4);
        result = validator.validate(bean);
        formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(JsamsI18nResource.ERROR_SOURCE_DESTINATION.getTranslation()));
        assertTrue(formattedText.contains(JsamsI18nLabelResource.LABEL_DESTINATION_TYPE.getTranslation()));
    }
    
    /**
     * Test method for {@link be.jsams.client.validator.wizard.DestinationTypeValidator
     * #validate(be.jsams.common.bean.model.transfer.TransferBean)}.
     */
    @Test
    public void testValidateWrongDeliveryOrderDestinationType() {
        bean.setSourceType(3);
        bean.setDestinationType(1);
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(JsamsI18nResource.ERROR_SOURCE_DESTINATION.getTranslation()));
        assertTrue(formattedText.contains(JsamsI18nLabelResource.LABEL_DESTINATION_TYPE.getTranslation()));
        bean.setDestinationType(2);
        result = validator.validate(bean);
        formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(JsamsI18nResource.ERROR_SOURCE_DESTINATION.getTranslation()));
        assertTrue(formattedText.contains(JsamsI18nLabelResource.LABEL_DESTINATION_TYPE.getTranslation()));
        bean.setDestinationType(4);
        result = validator.validate(bean);
        formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(JsamsI18nResource.ERROR_SOURCE_DESTINATION.getTranslation()));
        assertTrue(formattedText.contains(JsamsI18nLabelResource.LABEL_DESTINATION_TYPE.getTranslation()));
    }

    /**
     * Test method for {@link be.jsams.client.validator.wizard.DestinationTypeValidator
     * #validate(be.jsams.common.bean.model.transfer.TransferBean)}.
     */
    @Test
    public void testValidateWrongBillDestinationType() {
        bean.setSourceType(4);
        bean.setDestinationType(1);
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(JsamsI18nResource.ERROR_SOURCE_DESTINATION.getTranslation()));
        assertTrue(formattedText.contains(JsamsI18nLabelResource.LABEL_DESTINATION_TYPE.getTranslation()));
        bean.setDestinationType(2);
        result = validator.validate(bean);
        formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(JsamsI18nResource.ERROR_SOURCE_DESTINATION.getTranslation()));
        assertTrue(formattedText.contains(JsamsI18nLabelResource.LABEL_DESTINATION_TYPE.getTranslation()));
        bean.setDestinationType(3);
        result = validator.validate(bean);
        formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(JsamsI18nResource.ERROR_SOURCE_DESTINATION.getTranslation()));
        assertTrue(formattedText.contains(JsamsI18nLabelResource.LABEL_DESTINATION_TYPE.getTranslation()));
    }

}
