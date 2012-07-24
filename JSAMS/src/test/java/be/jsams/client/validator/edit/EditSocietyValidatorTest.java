package be.jsams.client.validator.edit;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import be.jsams.client.i18n.I18nApplicationContext;
import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.SocietyBean;

import com.jgoodies.validation.ValidationMessage;
import com.jgoodies.validation.ValidationResult;

/**
 * Test class for {@link EditSocietyValidator} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class EditSocietyValidatorTest {
    
    private EditSocietyValidator validator;

    /**
     * Setup method.
     */
    @Before
    public void setUp() {
        I18nApplicationContext.setContext(new ClassPathXmlApplicationContext(I18nApplicationContext.CONFIG));
        validator = new EditSocietyValidator();
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.EditSocietyValidator#validate(be.jsams.common.bean.model.SocietyBean)}
     * .
     */
    @Test
    public void testValidate() {
        SocietyBean bean = MockBeanGenerator.generateMockSociety();
        ValidationResult result = validator.validate(bean);
        assertTrue(result.isEmpty());
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.EditSocietyValidator#validate(be.jsams.common.bean.model.SocietyBean)}
     * .
     */
    @Test
    public void testValidateBlankName() {
        SocietyBean bean = MockBeanGenerator.generateMockSociety();
        bean.setName(null);
        ValidationResult result = validator.validate(bean);
        ValidationMessage message = result.getMessages().get(0);
        String formattedText = message.formattedText();
        assertTrue(formattedText.contains(JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation()));
        assertTrue(formattedText.contains(JsamsI18nLabelResource.LABEL_NAME.getTranslation()));
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.EditSocietyValidator#validate(be.jsams.common.bean.model.SocietyBean)}
     * .
     */
    @Test
    public void testValidateBlankActivity() {
        SocietyBean bean = MockBeanGenerator.generateMockSociety();
        bean.setActivity(null);
        ValidationResult result = validator.validate(bean);
        ValidationMessage message = result.getMessages().get(0);
        String formattedText = message.formattedText();
        assertTrue(formattedText.contains(JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation()));
        assertTrue(formattedText.contains(JsamsI18nLabelResource.LABEL_ACTIVITY.getTranslation()));
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.EditSocietyValidator#validate(be.jsams.common.bean.model.SocietyBean)}
     * .
     */
    @Test
    public void testValidateBlankCapital() {
        SocietyBean bean = MockBeanGenerator.generateMockSociety();
        bean.setCapital(null);
        ValidationResult result = validator.validate(bean);
        ValidationMessage message = result.getMessages().get(0);
        String formattedText = message.formattedText();
        assertTrue(formattedText.contains(JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation()));
        assertTrue(formattedText.contains(JsamsI18nLabelResource.LABEL_CAPITAL.getTranslation()));
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.EditSocietyValidator#validate(be.jsams.common.bean.model.SocietyBean)}
     * .
     */
    @Test
    public void testValidateBlankVatNumber() {
        SocietyBean bean = MockBeanGenerator.generateMockSociety();
        bean.setVatNumber(null);
        ValidationResult result = validator.validate(bean);
        ValidationMessage message = result.getMessages().get(0);
        String formattedText = message.formattedText();
        assertTrue(formattedText.contains(JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation()));
        assertTrue(formattedText.contains(JsamsI18nLabelResource.LABEL_VAT_NUMBER.getTranslation()));
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.EditSocietyValidator#validate(be.jsams.common.bean.model.SocietyBean)}
     * .
     */
    @Test
    public void testValidateBlankPhone() {
        SocietyBean bean = MockBeanGenerator.generateMockSociety();
        bean.getContactInformation().setPhone(null);
        ValidationResult result = validator.validate(bean);
        ValidationMessage message = result.getMessages().get(0);
        String formattedText = message.formattedText();
        assertTrue(formattedText.contains(JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation()));
        assertTrue(formattedText.contains(JsamsI18nLabelResource.LABEL_PHONE.getTranslation()));
    }

}
