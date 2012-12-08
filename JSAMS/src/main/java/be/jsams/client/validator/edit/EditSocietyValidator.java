package be.jsams.client.validator.edit;

import be.jsams.client.i18n.I18nLabelResource;
import be.jsams.client.i18n.I18nResource;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.ContactInformationBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.validator.EmailValidator;
import be.jsams.common.bean.validator.StringValidator;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;

/**
 * {@link Validator} for edit society panel.
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public class EditSocietyValidator implements Validator<SocietyBean> {

    /**
     * {@inheritDoc}
     */
    public ValidationResult validate(final SocietyBean society) {
        PropertyValidationSupport support = new PropertyValidationSupport(society, "");

        String name = society.getName();
        if (ValidationUtils.isBlank(name)) {
            support.addError(I18nLabelResource.LABEL_NAME.getTranslation(), I18nResource.ERROR_IS_MANDATORY
                    .getTranslation());
        } else if (!ValidationUtils.isAlphanumericSpace(name) && !StringValidator.validate(name)) {
            support.addError(I18nLabelResource.LABEL_NAME.getTranslation(),
                    I18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }
        String activity = society.getActivity();
        if (ValidationUtils.isBlank(activity)) {
            support.addError(I18nLabelResource.LABEL_ACTIVITY.getTranslation(),
                    I18nResource.ERROR_IS_MANDATORY.getTranslation());
        } else if (!ValidationUtils.isAlphanumericSpace(activity)) {
            support.addError(I18nLabelResource.LABEL_ACTIVITY.getTranslation(),
                    I18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }
        Double capital = society.getCapital();
        if (capital == null || ValidationUtils.isBlank(capital.toString())) {
            support.addError(I18nLabelResource.LABEL_CAPITAL.getTranslation(),
                    I18nResource.ERROR_IS_MANDATORY.getTranslation());
        }
        String vatNumber = society.getVatNumber();
        if (ValidationUtils.isBlank(vatNumber)) {
            support.addError(I18nLabelResource.LABEL_VAT_NUMBER.getTranslation(),
                    I18nResource.ERROR_IS_MANDATORY.getTranslation());
        }
        ContactInformationBean contactInformation = society.getContactInformation();
        String phone = contactInformation.getPhone();
        if (ValidationUtils.isBlank(phone)) {
            support.addError(I18nLabelResource.LABEL_PHONE.getTranslation(), I18nResource.ERROR_IS_MANDATORY
                    .getTranslation());
        } else if (!ValidationUtils.isAlphanumericSpace(phone)) {
            support.addError(I18nLabelResource.LABEL_PHONE.getTranslation(),
                    I18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }
        String email = contactInformation.getEmail();
        if (!ValidationUtils.isBlank(email)) {
            if (!EmailValidator.validate(email)) {
                support.addError(I18nLabelResource.LABEL_EMAIL.getTranslation(),
                        I18nResource.ERROR_IS_INVALID.getTranslation());
            }
        }

        AddressBean address = society.getAddress();
        Validator<AddressBean> addressValidator = new EditAddressValidator();
        ValidationResult addressResult = addressValidator.validate(address);

        ValidationResult result = support.getResult();
        result.addAllFrom(addressResult);
        return support.getResult();
    }

}
