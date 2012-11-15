package be.jsams.common.bean.model.sale;

import java.math.BigDecimal;
import java.util.List;

import be.jsams.common.bean.model.sale.detail.BillDetailBean;
import be.jsams.common.bean.view.sale.BillBeanView;

/**
 * Mediator pattern used here to update some totals and discount rates from some sources.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class BillMediator {
    
    private BillBean billBean;
    
    /**
     * Constructor.
     */
    public BillMediator() {
    }

    /**
     * @return the billBean
     */
    public BillBean getBillBean() {
        return billBean;
    }

    /**
     * @param billBean the billBean to set
     */
    public void setBillBean(BillBean billBean) {
        this.billBean = billBean;
    }
    
    /**
     * Updates all totals of {@link BillBean} like total et, vat and total ati.
     */
    public void updateTotals() {
        Double totalEt = 0D;
        Double totalVat = 0D;
        Double totalAti = 0D;
        List<BillDetailBean> details = billBean.getDetails();
        if (details != null && !details.isEmpty()) {
            for (BillDetailBean detail : details) {
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
        billBean.setTotalEt(totalEt);
        billBean.setTotalVat(totalVat);
        totalAti += totalEt + totalVat;
        billBean.setTotalAti(totalAti);
    }

    /**
     * Updates all discount rates of {@link BillDetailBean}.
     */
    public void updateDiscountRates() {
        List<BillDetailBean> details = billBean.getDetails();
        if (details != null && !details.isEmpty()) {
            for (BillDetailBean detail : details) {
                detail.setDiscountRate(billBean.getDiscountRate());
            }
        }
        BillBeanView view = billBean.getView();
        if (view != null && view.getDetailsTable() != null) {
            view.getDetailsTable().updateUI();
            view.updateDetailsTableRendering();
        }
    }
    
}
