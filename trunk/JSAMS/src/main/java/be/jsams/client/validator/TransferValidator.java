package be.jsams.client.validator;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.common.bean.model.transfer.TransferBean;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;

/**
 * {@link Validator} for transfer wizard dialog.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class TransferValidator implements Validator<TransferBean> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidationResult validate(TransferBean bean) {
        PropertyValidationSupport support = new PropertyValidationSupport(bean, "");
        int sourceType = bean.getSourceType();
        int destinationType = bean.getDestinationType();
        if (sourceType != 0 && destinationType != 0) {
            switch (sourceType) {
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
        }
        ValidationResult result = support.getResult();
        return result;
    }
    
    /**
     * Check the command selecting.
     * 
     * @param destinationType the selected destination type
     * @param support the {@link PropertyValidationSupport}
     */
    private void checkCommand(int destinationType, PropertyValidationSupport support) {
        if (destinationType == 1) {
            support.addError(JsamsI18nLabelResource.LABEL_DESTINATION_TYPE.getTranslation(),
                    JsamsI18nResource.ERROR_SOURCE_DESTINATION.getTranslation());
        }
    }

    /**
     * Check the delivery order selecting.
     * 
     * @param destinationType the selected destination type
     * @param support the {@link PropertyValidationSupport}
     */
    private void checkDeliveryOrder(int destinationType, PropertyValidationSupport support) {
        if (destinationType < 3) {
            support.addError(JsamsI18nLabelResource.LABEL_DESTINATION_TYPE.getTranslation(),
                    JsamsI18nResource.ERROR_SOURCE_DESTINATION.getTranslation());
        }
    }

    /**
     * Check the bill selecting.
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
