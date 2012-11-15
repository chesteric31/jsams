package be.jsams.common.bean.model.sale;

import java.math.BigDecimal;
import java.util.List;

import be.jsams.common.bean.model.sale.detail.DeliveryOrderDetailBean;
import be.jsams.common.bean.view.sale.DeliveryOrderBeanView;

/**
 * Mediator pattern used here to update some totals and discount rates from some sources.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class DeliveryOrderMediator {
    
    private DeliveryOrderBean deliveryOrderBean;
    
    /**
     * Constructor.
     */
    public DeliveryOrderMediator() {
    }

    /**
     * @return the deliveryOrderBean
     */
    public DeliveryOrderBean getDeliveryOrderBean() {
        return deliveryOrderBean;
    }

    /**
     * @param deliveryOrderBean the deliveryOrderBean to set
     */
    public void setDeliveryOrderBean(DeliveryOrderBean deliveryOrderBean) {
        this.deliveryOrderBean = deliveryOrderBean;
    }
    
    /**
     * Updates all totals of {@link DeliveryOrderBean} like total et, vat and total ati.
     */
    public void updateTotals() {
        Double totalEt = 0D;
        Double totalVat = 0D;
        Double totalAti = 0D;
        List<DeliveryOrderDetailBean> details = deliveryOrderBean.getDetails();
        if (details != null && !details.isEmpty()) {
            for (DeliveryOrderDetailBean detail : details) {
                Double discountRate = detail.getDiscountRate();
                Double price = detail.getPrice();
                int quantity = detail.getQuantity();
                Double vatApplicable = detail.getVatApplicable();
                BigDecimal currentTotalEt = new BigDecimal(0D);
                if (price != null) {
                    currentTotalEt = BigDecimal.valueOf(price * quantity);
                }
                if (discountRate != null) {
                    double percentage = discountRate / 100;
                    currentTotalEt = currentTotalEt.multiply(BigDecimal.valueOf(1 - percentage));
                }
                BigDecimal currentVat = new BigDecimal(0D);
                if (vatApplicable != null) {
                    currentVat = currentTotalEt.multiply(BigDecimal.valueOf(vatApplicable / 100));
                }
                totalEt += currentTotalEt.doubleValue();
                totalVat += currentVat.doubleValue();
            }
        }
        deliveryOrderBean.setTotalEt(totalEt);
        deliveryOrderBean.setTotalVat(totalVat);
        totalAti += totalEt + totalVat;
        deliveryOrderBean.setTotalAti(totalAti);
    }

    /**
     * Updates all discount rates of {@link DeliveryOrderDetailBean}.
     */
    public void updateDiscountRates() {
        List<DeliveryOrderDetailBean> details = deliveryOrderBean.getDetails();
        if (details != null && !details.isEmpty()) {
            for (DeliveryOrderDetailBean detail : details) {
                detail.setDiscountRate(deliveryOrderBean.getDiscountRate());
            }
        }
        DeliveryOrderBeanView view = deliveryOrderBean.getView();
        if (view != null && view.getDetailsTable() != null) {
            view.getDetailsTable().updateUI();
            view.updateDetailsTableRendering();
        }
    }
    
}
