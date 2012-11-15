package be.jsams.common.bean.model.sale;

import java.math.BigDecimal;
import java.util.List;

import be.jsams.common.bean.model.sale.detail.CreditNoteDetailBean;

/**
 * Mediator pattern used here to update some totals and discount rates from some sources.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class CreditNoteMediator {
    
    private CreditNoteBean creditNoteBean;
    
    /**
     * Constructor.
     */
    public CreditNoteMediator() {
    }

    /**
     * @return the creditNoteBean
     */
    public CreditNoteBean getCreditNoteBean() {
        return creditNoteBean;
    }

    /**
     * @param creditNoteBean the creditNoteBean to set
     */
    public void setCreditNoteBean(CreditNoteBean creditNoteBean) {
        this.creditNoteBean = creditNoteBean;
    }
    
    /**
     * Updates all totals of {@link CreditNoteBean} like total et, vat and total ati.
     */
    public void updateTotals() {
        Double totalEt = 0D;
        Double totalVat = 0D;
        Double totalAti = 0D;
        List<CreditNoteDetailBean> details = creditNoteBean.getDetails();
        if (details != null && !details.isEmpty()) {
            for (CreditNoteDetailBean detail : details) {
//                Double discountRate = detail.getDiscountRate();
                Double price = detail.getPrice();
                int quantity = detail.getQuantity();
                Double vatApplicable = detail.getVatApplicable();
                BigDecimal currentTotalEt = new BigDecimal(0D);
                if (price != null) {
                    currentTotalEt = BigDecimal.valueOf(price * quantity);
                }
//                if (discountRate != null) {
//                    double percentage = discountRate / 100;
//                    currentTotalEt = currentTotalEt.multiply(BigDecimal.valueOf(1 - percentage));
//                }
                BigDecimal currentVat = new BigDecimal(0D);
                if (vatApplicable != null) {
                    currentVat = currentTotalEt.multiply(BigDecimal.valueOf(vatApplicable / 100));
                }
                totalEt += currentTotalEt.doubleValue();
                totalVat += currentVat.doubleValue();
            }
        }
        creditNoteBean.setTotalEt(totalEt);
        creditNoteBean.setTotalVat(totalVat);
        totalAti += totalEt + totalVat;
        creditNoteBean.setTotalAti(totalAti);
    }

}
