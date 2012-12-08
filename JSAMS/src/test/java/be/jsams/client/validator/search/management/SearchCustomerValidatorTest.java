package be.jsams.client.validator.search.management;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import be.jsams.client.i18n.I18nApplicationContext;
import be.jsams.client.i18n.I18nLabelResource;
import be.jsams.client.i18n.I18nResource;
import be.jsams.client.validator.AbstractValidatorTest;
import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.management.CustomerBean;

import com.jgoodies.validation.ValidationResult;

/**
 * Test class for {@link SearchCustomerValidator} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class SearchCustomerValidatorTest extends AbstractValidatorTest {
     
    private SearchCustomerValidator validator;
    private CustomerBean bean;

    /**
     * Setup method.
     */
    @Before
    public void setUp() {
        I18nApplicationContext.setContext(new ClassPathXmlApplicationContext(I18nApplicationContext.CONFIG));
        validator = new SearchCustomerValidator();
        bean = MockBeanGenerator.generateMockCustomer();
    }

    /**
     * Test method for {@link be.jsams.client.validator.search.management.SearchCustomerValidator
     * #validate(be.jsams.common.bean.model.management.CustomerBean)}.
     */
    @Test
    public void testValidate() {
        ValidationResult result = validator.validate(bean);
        assertTrue(result.isEmpty());
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.search.management.SearchCustomerValidator
     * #validate(be.jsams.common.bean.model.management.CustomerBean)}
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
     * {@link be.jsams.client.validator.search.management.SearchCustomerValidator
     * #validate(be.jsams.common.bean.model.management.CustomerBean)}
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
     * {@link be.jsams.client.validator.search.management.SearchCustomerValidator
     * #validate(be.jsams.common.bean.model.management.CustomerBean)}
     * .
     */
    @Test
    public void testValidateWrongZipCode() {
        bean.getBillingAddress().setZipCode("!");
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(I18nResource.ERROR_IS_ALPHANUMERIC.getTranslation()));
        assertTrue(formattedText.contains(I18nLabelResource.LABEL_ZIP_CODE.getTranslation()));
    }

}
