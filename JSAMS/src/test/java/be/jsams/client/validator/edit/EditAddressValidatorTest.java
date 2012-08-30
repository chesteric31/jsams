package be.jsams.client.validator.edit;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import be.jsams.client.i18n.I18nApplicationContext;
import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.MockBeanGenerator;

import com.jgoodies.validation.ValidationMessage;
import com.jgoodies.validation.ValidationResult;

/**
 * Test class for {@link EditAddressValidator} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class EditAddressValidatorTest extends AbstractEditValidatorTest {
    
    private EditAddressValidator validator;
    private AddressBean bean;
    
    /**
     * Setup method.
     */
    @Before
    public void setUp() {
        I18nApplicationContext.setContext(new ClassPathXmlApplicationContext(I18nApplicationContext.CONFIG));
        validator = new EditAddressValidator();
        bean = MockBeanGenerator.generateMockAddress();
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.EditAddressValidator#validate(be.jsams.common.bean.model.AddressBean)}
     * .
     */
    @Test
    public void testValidate() {
        ValidationResult result = validator.validate(bean);
        assertTrue(result.isEmpty());
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.EditAddressValidator#validate(be.jsams.common.bean.model.AddressBean)}
     * .
     */
    @Test
    public void testValidateBlankCity() {
        bean.setCity(null);
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation()));
        assertTrue(formattedText.contains(JsamsI18nLabelResource.LABEL_CITY.getTranslation()));
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.EditAddressValidator#validate(be.jsams.common.bean.model.AddressBean)}
     * .
     */
    @Test
    public void testValidateWrongCity() {
        bean.setCity("@#|");
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(JsamsI18nResource.ERROR_IS_ALPHANUMERIC.getTranslation()));
        assertTrue(formattedText.contains(JsamsI18nLabelResource.LABEL_CITY.getTranslation()));
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.EditAddressValidator#validate(be.jsams.common.bean.model.AddressBean)}
     * .
     */
    @Test
    public void testValidateBlankCountry() {
        bean.setCountry(null);
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation()));
        assertTrue(formattedText.contains(JsamsI18nLabelResource.LABEL_COUNTRY.getTranslation()));
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.EditAddressValidator#validate(be.jsams.common.bean.model.AddressBean)}
     * .
     */
    @Test
    public void testValidateWrongCountry() {
        bean.setCountry("@#|");
        ValidationResult result = validator.validate(bean);
        ValidationMessage message = result.getMessages().get(0);
        String formattedText = message.formattedText();
        assertTrue(formattedText.contains(JsamsI18nResource.ERROR_IS_ALPHANUMERIC.getTranslation()));
        assertTrue(formattedText.contains(JsamsI18nLabelResource.LABEL_COUNTRY.getTranslation()));
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.EditAddressValidator#validate(be.jsams.common.bean.model.AddressBean)}
     * .
     */
    @Test
    public void testValidateBlankNumber() {
        bean.setNumber(null);
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation()));
        assertTrue(formattedText.contains(JsamsI18nLabelResource.LABEL_NUMBER.getTranslation()));
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.EditAddressValidator#validate(be.jsams.common.bean.model.AddressBean)}
     * .
     */
    @Test
    public void testValidateWrongNumber() {
        bean.setNumber("a");
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(JsamsI18nResource.ERROR_IS_NUMERIC.getTranslation()));
        assertTrue(formattedText.contains(JsamsI18nLabelResource.LABEL_NUMBER.getTranslation()));
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.EditAddressValidator#validate(be.jsams.common.bean.model.AddressBean)}
     * .
     */
    @Test
    public void testValidateBlankStreet() {
        bean.setStreet(null);
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation()));
        assertTrue(formattedText.contains(JsamsI18nLabelResource.LABEL_STREET.getTranslation()));
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.EditAddressValidator#validate(be.jsams.common.bean.model.AddressBean)}
     * .
     */
    @Test
    public void testValidateWrongStreet() {
        bean.setStreet("#@|");
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(JsamsI18nResource.ERROR_IS_ALPHANUMERIC.getTranslation()));
        assertTrue(formattedText.contains(JsamsI18nLabelResource.LABEL_STREET.getTranslation()));
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.EditAddressValidator#validate(be.jsams.common.bean.model.AddressBean)}
     * .
     */
    @Test
    public void testValidateBlankZipCode() {
        bean.setZipCode(null);
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation()));
        assertTrue(formattedText.contains(JsamsI18nLabelResource.LABEL_ZIP_CODE.getTranslation()));
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.EditAddressValidator#validate(be.jsams.common.bean.model.AddressBean)}
     * .
     */
    @Test
    public void testValidateWrongZipCode() {
        bean.setZipCode("çà");
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(JsamsI18nResource.ERROR_IS_ALPHANUMERIC.getTranslation()));
        assertTrue(formattedText.contains(JsamsI18nLabelResource.LABEL_ZIP_CODE.getTranslation()));
    }

}
