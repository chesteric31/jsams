package be.jsams.client.validator.edit.sale;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import be.jsams.client.i18n.I18nApplicationContext;
import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.validator.AbstractValidatorTest;
import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.sale.CommandBean;

import com.jgoodies.validation.ValidationResult;

/**
 * Test class for {@link EditCommandValidator} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class EditCommandValidatorTest extends AbstractValidatorTest {

    private EditCommandValidator validator;
    private CommandBean bean;

    /**
     * Setup method.
     */
    @Before
    public void setUp() {
        I18nApplicationContext.setContext(new ClassPathXmlApplicationContext(I18nApplicationContext.CONFIG));
        validator = new EditCommandValidator();
        bean = MockBeanGenerator.generateMockCommand();
    }
    
    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.sale.EditCommandValidator
     * #validate(be.jsams.common.bean.model.sale.CommandBean)}
     * .
     */
    @Test
    public void testValidate() {
        ValidationResult result = validator.validate(bean);
        assertTrue(result.isEmpty());
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.sale.EditCommandValidator
     * #validate(be.jsams.common.bean.model.sale.CommandBean)}
     * .
     */
    @Test
    public void testValidateBlankCreationDate() {
        bean.setCreationDate(null);
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation()));
        assertTrue(formattedText.contains(JsamsI18nLabelResource.LABEL_CREATION_DATE.getTranslation()));
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.sale.EditCommandValidator
     * #validate(be.jsams.common.bean.model.sale.CommandBean)}
     * .
     */
    @Test
    public void testValidateBlankCustomerName() {
        bean.getCustomer().setName("");
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation()));
        assertTrue(formattedText.contains(JsamsI18nLabelResource.LABEL_CUSTOMER.getTranslation()));
    }

}
