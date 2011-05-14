package be.jsams.client.validator;

import java.util.Date;
import java.util.List;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.common.bean.model.sale.detail.BillDetailBean;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;

/**
 * {@link Validator} for edit bill panel.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EditBillValidator implements Validator<BillBean> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidationResult validate(final BillBean bean) {
        PropertyValidationSupport support = new PropertyValidationSupport(bean, "");

        List<BillDetailBean> details = bean.getDetails();

        if (details == null || details.isEmpty()) {
            support.addError(JsamsI18nLabelResource.LABEL_DETAILS.getTranslation(),
                    JsamsI18nResource.ERROR_DETAILS_ARE_EMPTY.getTranslation());
        }
        Date creationDate = bean.getCreationDate();
        if (creationDate == null) {
            support.addError(JsamsI18nLabelResource.LABEL_CREATION_DATE.getTranslation(),
                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
        }
        CustomerBean customer = bean.getCustomer();
        if (ValidationUtils.isBlank(customer.getName())) {
            support.addError(JsamsI18nLabelResource.LABEL_CUSTOMER.getTranslation(),
                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
        }

        if (bean.getPaymentMode().getLabel() == null) {
            support.addError(JsamsI18nLabelResource.LABEL_PAYMENT_MODE.getTranslation(),
                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
        }
        
        Date dueDate = bean.getDueDate();
        Date firstRememberDate = bean.getDateFirstRemember();
        Date secondRememberDate = bean.getDateSecondRemember();
        Date formalNoticeDate = bean.getDateFormalNotice();
        if (dueDate == null) {
            support.addError(JsamsI18nLabelResource.LABEL_DUE_DATE.getTranslation(),
                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
        }
        if (firstRememberDate == null) {
            support.addError(JsamsI18nLabelResource.LABEL_FIRST_REMEMBER_DATE.getTranslation(),
                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
        }
        if (secondRememberDate == null) {
            support.addError(JsamsI18nLabelResource.LABEL_SECOND_REMEMBER_DATE.getTranslation(),
                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
        }
        if (formalNoticeDate == null) {
            support.addError(JsamsI18nLabelResource.LABEL_FORMAL_NOTICE_DATE.getTranslation(),
                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
        }

        Validator<AddressBean> billingAddressValidator = new EditAddressValidator();
        ValidationResult billingAddressResult = billingAddressValidator.validate(bean.getBillingAddress());

        ValidationResult result = support.getResult();
        result.addAllFrom(billingAddressResult);
        return support.getResult();
    }
    
}
