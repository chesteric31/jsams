package be.jsams.client.validator.edit.sale;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import be.jsams.client.i18n.I18nApplicationContext;
import be.jsams.client.i18n.I18nLabelResource;
import be.jsams.client.i18n.I18nResource;
import be.jsams.client.validator.AbstractValidatorTest;
import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.sale.BillBean;

import com.jgoodies.validation.ValidationResult;

/**
 * Test class for {@link EditBillValidator} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class EditBillValidatorTest extends AbstractValidatorTest {

    private EditBillValidator validator;
    private BillBean bean;

    /**
     * Setup method.
     */
    @Before
    public void setUp() {
        I18nApplicationContext.setContext(new ClassPathXmlApplicationContext(I18nApplicationContext.CONFIG));
        validator = new EditBillValidator();
        bean = MockBeanGenerator.generateMockBill();
    }
    
    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.sale.EditBillValidator
     * #validate(be.jsams.common.bean.model.sale.BillBean)}
     * .
     */
    @Test
    public void testValidate() {
        ValidationResult result = validator.validate(bean);
        assertTrue(result.isEmpty());
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.sale.EditBillValidator
     * #validate(be.jsams.common.bean.model.sale.BillBean)}
     * .
     */
    @Test
    public void testValidateBlankCreationDate() {
        bean.setCreationDate(null);
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(I18nResource.ERROR_IS_MANDATORY.getTranslation()));
        assertTrue(formattedText.contains(I18nLabelResource.LABEL_CREATION_DATE.getTranslation()));
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.sale.EditBillValidator
     * #validate(be.jsams.common.bean.model.sale.BillBean)}
     * .
     */
    @Test
    public void testValidateBlankCustomerName() {
        bean.getCustomer().setName("");
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(I18nResource.ERROR_IS_MANDATORY.getTranslation()));
        assertTrue(formattedText.contains(I18nLabelResource.LABEL_CUSTOMER.getTranslation()));
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.sale.EditBillValidator
     * #validate(be.jsams.common.bean.model.sale.BillBean)}
     * .
     */
    @Test
    public void testValidateBlankPaymentMode() {
        bean.getPaymentMode().setLabel(null);
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(I18nResource.ERROR_IS_MANDATORY.getTranslation()));
        assertTrue(formattedText.contains(I18nLabelResource.LABEL_PAYMENT_MODE.getTranslation()));
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.sale.EditBillValidator
     * #validate(be.jsams.common.bean.model.sale.BillBean)}
     * .
     */
    @Test
    public void testValidateBlankDueDate() {
        bean.setDueDate(null);
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(I18nResource.ERROR_IS_MANDATORY.getTranslation()));
        assertTrue(formattedText.contains(I18nLabelResource.LABEL_DUE_DATE.getTranslation()));
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.sale.EditBillValidator
     * #validate(be.jsams.common.bean.model.sale.BillBean)}
     * .
     */
    @Test
    public void testValidateBlankDateFirstRemember() {
        bean.setDateFirstRemember(null);
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(I18nResource.ERROR_IS_MANDATORY.getTranslation()));
        assertTrue(formattedText.contains(I18nLabelResource.LABEL_FIRST_REMEMBER_DATE.getTranslation()));
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.sale.EditBillValidator
     * #validate(be.jsams.common.bean.model.sale.BillBean)}
     * .
     */
    @Test
    public void testValidateBlankDateSecondRemember() {
        bean.setDateSecondRemember(null);
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(I18nResource.ERROR_IS_MANDATORY.getTranslation()));
        assertTrue(formattedText.contains(I18nLabelResource.LABEL_SECOND_REMEMBER_DATE.getTranslation()));
    }

    /**
     * Test method for
     * {@link be.jsams.client.validator.edit.sale.EditBillValidator
     * #validate(be.jsams.common.bean.model.sale.BillBean)}
     * .
     */
    @Test
    public void testValidateBlankDateFormalNotice() {
        bean.setDateFormalNotice(null);
        ValidationResult result = validator.validate(bean);
        String formattedText = retrieveFormattedText(result);
        assertTrue(formattedText.contains(I18nResource.ERROR_IS_MANDATORY.getTranslation()));
        assertTrue(formattedText.contains(I18nLabelResource.LABEL_FORMAL_NOTICE_DATE.getTranslation()));
    }

}
