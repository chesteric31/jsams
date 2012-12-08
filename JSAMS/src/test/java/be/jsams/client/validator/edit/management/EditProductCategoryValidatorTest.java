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
import be.jsams.common.bean.model.management.ProductCategoryBean;

import com.jgoodies.validation.ValidationResult;

/**
 * Test class for {@link EditProductCategoryValidator} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class EditProductCategoryValidatorTest extends AbstractValidatorTest {

    private EditProductCategoryValidator validator;
    private ProductCategoryBean bean;

    /**
     * Setup method.
     */
    @Before
    public void setUp() {
        I18nApplicationContext.setContext(new ClassPathXmlApplicationContext(I18nApplicationContext.CONFIG));
        validator = new EditProductCategoryValidator();
        bean = MockBeanGenerator.generateMockProductCategory();
    }
    
    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.management.EditProductCategoryValidator
     * #validate(be.jsams.common.bean.model.management.ProductCategoryBean)}
     * .
     */
    @Test
    public void testValidate() {
        ValidationResult result = validator.validate(bean);
        assertTrue(result.isEmpty());
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.management.EditProductCategoryValidator
     * #validate(be.jsams.common.bean.model.management.ProductCategoryBean)}
     * .
     */
    @Test
    public void testValidateWrongLabel() {
        bean.setLabel("#");
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(I18nResource.ERROR_IS_ALPHANUMERIC.getTranslation()));
        assertTrue(formattedText.contains(I18nLabelResource.LABEL_PRODUCT_CATEGORY_EN.getTranslation()));
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.management.EditProductCategoryValidator
     * #validate(be.jsams.common.bean.model.management.ProductCategoryBean)}
     * .
     */
    @Test
    public void testValidateWrongLabelFr() {
        bean.setLabelFr("#");
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(I18nResource.ERROR_IS_ALPHANUMERIC.getTranslation()));
        assertTrue(formattedText.contains(I18nLabelResource.LABEL_PRODUCT_CATEGORY_FR.getTranslation()));
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.management.EditProductCategoryValidator
     * #validate(be.jsams.common.bean.model.management.ProductCategoryBean)}
     * .
     */
    @Test
    public void testValidateWrongLabelNl() {
        bean.setLabelNl("#");
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(I18nResource.ERROR_IS_ALPHANUMERIC.getTranslation()));
        assertTrue(formattedText.contains(I18nLabelResource.LABEL_PRODUCT_CATEGORY_NL.getTranslation()));
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.management.EditProductCategoryValidator
     * #validate(be.jsams.common.bean.model.management.ProductCategoryBean)}
     * .
     */
    @Test
    public void testValidateBlankLabel() {
        bean.setLabel("");
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(I18nResource.ERROR_IS_MANDATORY.getTranslation()));
        assertTrue(formattedText.contains(I18nLabelResource.LABEL_PRODUCT_CATEGORY_EN.getTranslation()));
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.management.EditProductCategoryValidator
     * #validate(be.jsams.common.bean.model.management.ProductCategoryBean)}
     * .
     */
    @Test
    public void testValidateBlankLabelFr() {
        bean.setLabelFr("");
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(I18nResource.ERROR_IS_MANDATORY.getTranslation()));
        assertTrue(formattedText.contains(I18nLabelResource.LABEL_PRODUCT_CATEGORY_FR.getTranslation()));
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.management.EditProductCategoryValidator
     * #validate(be.jsams.common.bean.model.management.ProductCategoryBean)}
     * .
     */
    @Test
    public void testValidateBlankLabelNl() {
        bean.setLabelNl("");
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(I18nResource.ERROR_IS_MANDATORY.getTranslation()));
        assertTrue(formattedText.contains(I18nLabelResource.LABEL_PRODUCT_CATEGORY_NL.getTranslation()));
    }

}
