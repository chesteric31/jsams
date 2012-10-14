package be.jsams.client.validator.wizard;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.common.bean.model.transfer.TransferBean;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;

/**
 * {@link Validator} for the step: destination chooser in the transfer wizard
 * dialog.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class DestinationTypeValidator implements Validator<TransferBean> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidationResult validate(TransferBean bean) {
        PropertyValidationSupport support = new PropertyValidationSupport(bean, "");
        int sourceType = bean.getSourceType();
        int destinationType = bean.getDestinationType();
        if (destinationType != 0) {
            switch (sourceType) {
            case 1:
                checkEstimate(destinationType, support);
                break;
            case 2:
                checkCommand(destinationType, support);
                break;
            case 3:
                checkDeliveryOrder(destinationType, support);
                break;
            case 4:
                checkBill(destinationType, support);
                break;
            default:
                break;
            }
        } else {
            support.addError(JsamsI18nLabelResource.LABEL_DESTINATION_TYPE.getTranslation(),
                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
        }
        return support.getResult();
    }

    /**
     * Checks the estimate selecting.
     * 
     * @param destinationType the selected destination type
     * @param support the {@link PropertyValidationSupport}
     */
    private void checkEstimate(int destinationType, PropertyValidationSupport support) {
        if (destinationType == 2 || destinationType == 4) {
            support.addError(JsamsI18nLabelResource.LABEL_DESTINATION_TYPE.getTranslation(),
                    JsamsI18nResource.ERROR_SOURCE_DESTINATION.getTranslation());
        }
    }
    
    /**
     * Checks the command selecting.
     * 
     * @param destinationType the selected destination type
     * @param support the {@link PropertyValidationSupport}
     */
    private void checkCommand(int destinationType, PropertyValidationSupport support) {
        if (destinationType == 1 || destinationType == 4) {
            support.addError(JsamsI18nLabelResource.LABEL_DESTINATION_TYPE.getTranslation(),
                    JsamsI18nResource.ERROR_SOURCE_DESTINATION.getTranslation());
        }
    }

    /**
     * Checks the delivery order selecting.
     * 
     * @param destinationType the selected destination type
     * @param support the {@link PropertyValidationSupport}
     */
    private void checkDeliveryOrder(int destinationType, PropertyValidationSupport support) {
        if (destinationType != 3) {
            support.addError(JsamsI18nLabelResource.LABEL_DESTINATION_TYPE.getTranslation(),
                    JsamsI18nResource.ERROR_SOURCE_DESTINATION.getTranslation());
        }
    }

    /**
     * Checks the bill selecting.
     * 
     * @param destinationType the selected destination type
     * @param support the {@link PropertyValidationSupport}
     */
    private void checkBill(int destinationType, PropertyValidationSupport support) {
        if (destinationType != 4) {
            support.addError(JsamsI18nLabelResource.LABEL_DESTINATION_TYPE.getTranslation(),
                    JsamsI18nResource.ERROR_SOURCE_DESTINATION.getTranslation());
        }
    }

}
