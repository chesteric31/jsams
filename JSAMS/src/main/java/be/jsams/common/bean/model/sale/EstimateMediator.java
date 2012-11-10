package be.jsams.common.bean.model.sale;

import java.math.BigDecimal;
import java.util.List;

import be.jsams.common.bean.model.sale.detail.EstimateDetailBean;
import be.jsams.common.bean.view.sale.EstimateBeanView;

/**
 * Mediator pattern used here to update some totals and discount rates from some sources.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class EstimateMediator {
    
    private EstimateBean estimateBean;
    
    /**
     * Constructor.
     */
    public EstimateMediator() {
    }

    /**
     * @return the estimateBean
     */
    public EstimateBean getEstimateBean() {
        return estimateBean;
    }

    /**
     * @param estimateBean the estimateBean to set
     */
    public void setEstimateBean(EstimateBean estimateBean) {
        this.estimateBean = estimateBean;
    }
    
    /**
     * Updates all totals of {@link EstimateBean} like total et, vat and total ati.
     */
    public void updateTotals() {
        Double totalEt = 0D;
        Double totalVat = 0D;
        Double totalAti = 0D;
        List<EstimateDetailBean> details = estimateBean.getDetails();
        if (details != null && !details.isEmpty()) {
            for (EstimateDetailBean detail : details) {
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
        estimateBean.setTotalEt(totalEt);
        estimateBean.setTotalVat(totalVat);
        totalAti += totalEt + totalVat;
        estimateBean.setTotalAti(totalAti);
    }

    /**
     * Updates all discount rates of {@link EstimateDetailBean}.
     */
    public void updateDiscountRates() {
        List<EstimateDetailBean> details = estimateBean.getDetails();
        if (details != null && !details.isEmpty()) {
            for (EstimateDetailBean detail : details) {
                detail.setDiscountRate(estimateBean.getDiscountRate());
            }
        }
        EstimateBeanView view = estimateBean.getView();
        if (view != null && view.getDetailsTable() != null) {
            view.getDetailsTable().updateUI();
            view.updateDetailsTableRendering();
        }
    }
    
}
