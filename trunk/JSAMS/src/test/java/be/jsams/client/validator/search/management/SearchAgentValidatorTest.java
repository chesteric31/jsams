package be.jsams.client.validator.search.management;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import be.jsams.client.i18n.I18nApplicationContext;
import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.validator.AbstractValidatorTest;
import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.management.AgentBean;

import com.jgoodies.validation.ValidationResult;

/**
 * Test class for {@link SearchAgentValidator} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class SearchAgentValidatorTest extends AbstractValidatorTest {
     
    private SearchAgentValidator validator;
    private AgentBean bean;

    /**
     * Setup method.
     */
    @Before
    public void setUp() {
        I18nApplicationContext.setContext(new ClassPathXmlApplicationContext(I18nApplicationContext.CONFIG));
        validator = new SearchAgentValidator();
        bean = MockBeanGenerator.generateMockAgent();
    }

    /**
     * Test method for {@link be.jsams.client.validator.search.management.SearchAgentValidator
     * #validate(be.jsams.common.bean.model.management.AgentBean)}.
     */
    @Test
    public void testValidate() {
        ValidationResult result = validator.validate(bean);
        assertTrue(result.isEmpty());
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.search.management.SearchAgentValidator
     * #validate(be.jsams.common.bean.model.management.AgentBean)}
     * .
     */
    @Test
    public void testValidateWrongName() {
        bean.setName("#");
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(JsamsI18nResource.ERROR_IS_ALPHANUMERIC.getTranslation()));
        assertTrue(formattedText.contains(JsamsI18nLabelResource.LABEL_NAME.getTranslation()));
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.search.management.SearchAgentValidator
     * #validate(be.jsams.common.bean.model.management.AgentBean)}
     * .
     */
    @Test
    public void testValidateWrongFunction() {
        bean.setFunction("#");
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(JsamsI18nResource.ERROR_IS_ALPHANUMERIC.getTranslation()));
        assertTrue(formattedText.contains(JsamsI18nLabelResource.LABEL_FUNCTION.getTranslation()));
    }
    
    /**
     * Test method for
     * {@link be.jsams.client.validator.search.management.SearchAgentValidator
     * #validate(be.jsams.common.bean.model.management.AgentBean)}
     * .
     */
    @Test
    public void testValidateWrongPhone() {
        bean.getContactInformation().setPhone("!");
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(JsamsI18nResource.ERROR_IS_ALPHANUMERIC.getTranslation()));
        assertTrue(formattedText.contains(JsamsI18nLabelResource.LABEL_PHONE.getTranslation()));
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.search.management.SearchAgentValidator
     * #validate(be.jsams.common.bean.model.management.AgentBean)}
     * .
     */
    @Test
    public void testValidateWrongZipCode() {
        bean.getAddress().setZipCode("!");
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(JsamsI18nResource.ERROR_IS_ALPHANUMERIC.getTranslation()));
        assertTrue(formattedText.contains(JsamsI18nLabelResource.LABEL_ZIP_CODE.getTranslation()));
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.search.management.SearchAgentValidator
     * #validate(be.jsams.common.bean.model.management.AgentBean)}
     * .
     */
    @Test
    public void testValidateWrongCity() {
        bean.getAddress().setCity("#");
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(JsamsI18nResource.ERROR_IS_ALPHANUMERIC.getTranslation()));
        assertTrue(formattedText.contains(JsamsI18nLabelResource.LABEL_CITY.getTranslation()));
    }


}
