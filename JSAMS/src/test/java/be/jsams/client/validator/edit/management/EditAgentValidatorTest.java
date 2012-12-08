package be.jsams.client.validator.edit.management;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import be.jsams.client.i18n.I18nApplicationContext;
import be.jsams.client.i18n.I18nLabelResource;
import be.jsams.client.i18n.I18nResource;
import be.jsams.client.validator.AbstractValidatorTest;
import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.management.AgentBean;

import com.jgoodies.validation.ValidationResult;

/**
 * Test class for {@link EditAgentValidator} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class EditAgentValidatorTest extends AbstractValidatorTest {

    private EditAgentValidator validator;
    private AgentBean bean;

    /**
     * Setup method.
     */
    @Before
    public void setUp() {
        I18nApplicationContext.setContext(new ClassPathXmlApplicationContext(I18nApplicationContext.CONFIG));
        validator = new EditAgentValidator();
        bean = MockBeanGenerator.generateMockAgent();
    }
    
    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.management.EditAgentValidator
     * #validate(be.jsams.common.bean.model.management.AgentBean)}
     * .
     */
    @Test
    public void testValidate() {
        ValidationResult result = validator.validate(bean);
        assertTrue(result.isEmpty());
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.management.EditAgentValidator
     * #validate(be.jsams.common.bean.model.management.AgentBean)}
     * .
     */
    @Test
    public void testValidateWrongName() {
        bean.setName("#");
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(I18nResource.ERROR_IS_ALPHANUMERIC.getTranslation()));
        assertTrue(formattedText.contains(I18nLabelResource.LABEL_NAME.getTranslation()));
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.management.EditAgentValidator
     * #validate(be.jsams.common.bean.model.management.AgentBean)}
     * .
     */
    @Test
    public void testValidateBlankFunction() {
        bean.setFunction("");
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(I18nResource.ERROR_IS_MANDATORY.getTranslation()));
        assertTrue(formattedText.contains(I18nLabelResource.LABEL_FUNCTION.getTranslation()));
    }
    
    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.management.EditAgentValidator
     * #validate(be.jsams.common.bean.model.management.AgentBean)}
     * .
     */
    @Test
    public void testValidateWrongFunction() {
        bean.setFunction("#");
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(I18nResource.ERROR_IS_ALPHANUMERIC.getTranslation()));
        assertTrue(formattedText.contains(I18nLabelResource.LABEL_FUNCTION.getTranslation()));
    }
    
    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.management.EditAgentValidator
     * #validate(be.jsams.common.bean.model.management.AgentBean)}
     * .
     */
    @Test
    public void testValidateBlankName() {
        bean.setName(null);
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(I18nResource.ERROR_IS_MANDATORY.getTranslation()));
        assertTrue(formattedText.contains(I18nLabelResource.LABEL_NAME.getTranslation()));
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.management.EditAgentValidator
     * #validate(be.jsams.common.bean.model.management.AgentBean)}
     * .
     */
    @Test
    public void testValidateBlankPhone() {
        bean.getContactInformation().setPhone(null);
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(I18nResource.ERROR_IS_MANDATORY.getTranslation()));
        assertTrue(formattedText.contains(I18nLabelResource.LABEL_PHONE.getTranslation()));
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.management.EditAgentValidator
     * #validate(be.jsams.common.bean.model.management.AgentBean)}
     * .
     */
    @Test
    public void testValidateWrongPhone() {
        bean.getContactInformation().setPhone("!");
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(I18nResource.ERROR_IS_ALPHANUMERIC.getTranslation()));
        assertTrue(formattedText.contains(I18nLabelResource.LABEL_PHONE.getTranslation()));
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.management.EditAgentValidator
     * #validate(be.jsams.common.bean.model.management.AgentBean)}
     * .
     */
    @Test
    public void testValidateWrongEmail() {
        bean.getContactInformation().setEmail("not a good email!!!");
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(I18nResource.ERROR_IS_INVALID.getTranslation()));
        assertTrue(formattedText.contains(I18nLabelResource.LABEL_EMAIL.getTranslation()));
    }

}
