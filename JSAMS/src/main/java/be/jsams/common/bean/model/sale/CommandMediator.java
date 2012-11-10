package be.jsams.common.bean.model.sale;

import java.math.BigDecimal;
import java.util.List;

import be.jsams.common.bean.model.sale.detail.CommandDetailBean;
import be.jsams.common.bean.view.sale.CommandBeanView;

/**
 * Mediator pattern used here to update some totals and discount rates from some sources.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class CommandMediator {
    
    private CommandBean commandBean;
    
    /**
     * Constructor.
     */
    public CommandMediator() {
    }

    /**
     * @return the commandBean
     */
    public CommandBean getCommandBean() {
        return commandBean;
    }

    /**
     * @param commandBean the commandBean to set
     */
    public void setCommandBean(CommandBean commandBean) {
        this.commandBean = commandBean;
    }
    
    /**
     * Updates all totals of {@link CommandBean} like total et, vat and total ati.
     */
    public void updateTotals() {
        Double totalEt = 0D;
        Double totalVat = 0D;
        Double totalAti = 0D;
        List<CommandDetailBean> details = commandBean.getDetails();
        if (details != null && !details.isEmpty()) {
            for (CommandDetailBean detail : details) {
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
        commandBean.setTotalEt(totalEt);
        commandBean.setTotalVat(totalVat);
        totalAti += totalEt + totalVat;
        commandBean.setTotalAti(totalAti);
    }

    /**
     * Updates all discount rates of {@link CommandDetailBean}.
     */
    public void updateDiscountRates() {
        List<CommandDetailBean> details = commandBean.getDetails();
        if (details != null && !details.isEmpty()) {
            for (CommandDetailBean detail : details) {
                detail.setDiscountRate(commandBean.getDiscountRate());
            }
        }
        CommandBeanView view = commandBean.getView();
        if (view != null && view.getDetailsTable() != null) {
            view.getDetailsTable().updateUI();
            view.updateDetailsTableRendering();
        }
    }
    
}
