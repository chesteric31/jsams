package be.jsams.client.validator;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.SocietyBean;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;

/**
 * {@link Validator} for edit society panel.
 * 
 * @author chesteric31
 * @version $$Rev: 694 $$ $$Date::                  $$ $$Author$$
 */
public class EditSocietyValidator implements Validator<SocietyBean> {

    /**
     * {@inheritDoc}
     */
    public ValidationResult validate(final SocietyBean society) {
        PropertyValidationSupport support = new PropertyValidationSupport(society, "");

        String name = society.getName();
        if (ValidationUtils.isBlank(name)) {
            support.addError(JsamsI18nLabelResource.LABEL_NAME.getTranslation(), JsamsI18nResource.ERROR_IS_MANDATORY
                    .getTranslation());
        } else if (!ValidationUtils.isAlphanumericSpace(name)) {
            support.addError(JsamsI18nLabelResource.LABEL_NAME.getTranslation(),
                    JsamsI18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }
        String activity = society.getActivity();
        if (ValidationUtils.isBlank(activity)) {
            support.addError(JsamsI18nLabelResource.LABEL_ACTIVITY.getTranslation(),
                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
        } else if (!ValidationUtils.isAlphanumericSpace(activity)) {
            support.addError(JsamsI18nLabelResource.LABEL_ACTIVITY.getTranslation(),
                    JsamsI18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }
        Double capital = society.getCapital();
        if (capital == null || ValidationUtils.isBlank(capital.toString())) {
            support.addError(JsamsI18nLabelResource.LABEL_CAPITAL.getTranslation(),
                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
        }
        String phone = society.getContactInformation().getPhone();
        if (ValidationUtils.isBlank(phone)) {
            support.addError(JsamsI18nLabelResource.LABEL_PHONE.getTranslation(), JsamsI18nResource.ERROR_IS_MANDATORY
                    .getTranslation());
        } else if (!ValidationUtils.isAlphanumericSpace(phone)) {
            support.addError(JsamsI18nLabelResource.LABEL_PHONE.getTranslation(),
                    JsamsI18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }

        AddressBean address = society.getAddress();
        Validator<AddressBean> addressValidator = new EditAddressValidator();
        ValidationResult addressResult = addressValidator.validate(address);

        ValidationResult result = support.getResult();
        result.addAllFrom(addressResult);
        return support.getResult();
    }

}
