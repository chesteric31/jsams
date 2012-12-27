package be.jsams.server.service.statistics.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.server.service.management.CustomerService;
import be.jsams.server.service.management.ProductService;
import be.jsams.server.service.sale.BillService;
import be.jsams.server.service.sale.CreditNoteService;
import be.jsams.server.service.sale.EstimateService;
import be.jsams.server.service.statistics.StatisticsService;

/**
 * Statistics service implementation.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class StatisticsServiceImpl implements StatisticsService {
    
    private EstimateService estimateService;
    private BillService billService;
    private CreditNoteService creditNoteService;
    private CustomerService customerService;
    private ProductService productService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Double findGlobalTurnover(SocietyBean society) {
        BigDecimal globalTurnover = billService.findGlobalTurnover(society);
        globalTurnover = globalTurnover.subtract(creditNoteService.findGlobalTurnover(society));
        return globalTurnover.doubleValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Double> findTurnoverEvolution(SocietyBean society) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Double, List<BillBean>> findNotPaidBills(SocietyBean society) {
        return billService.findNotPaidBills(society);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Double, List<BillBean>> findToThrowBackBills(SocietyBean society) {
        return billService.findToThrowBackBills(society);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Double, List<BillBean>> findExpiredBills(SocietyBean society) {
        return billService.findExpiredBills(society);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Double, List<BillBean>> findOpenedBills(SocietyBean society) {
        return billService.findOpenedBills(society);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Double, List<EstimateBean>> findNotTransferredEstimates(SocietyBean society) {
        return estimateService.findNotTransferredEstimates(society);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Double, CustomerBean> findTop5Customers(SocietyBean society) {
        return customerService.findTop5Customers(society);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CustomerBean> findCustomersWithEstimates(SocietyBean society) {
        return customerService.findWithEstimates(society);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CustomerBean> findCustomersWithBills(SocietyBean society) {
        return customerService.findWithBills(society);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Double, ProductBean> findTop5Products(SocietyBean society) {
        return productService.findTop5Products(society);
    }

    /**
     * @return the estimateService
     */
    public EstimateService getEstimateService() {
        return estimateService;
    }

    /**
     * @param estimateService the estimateService to set
     */
    public void setEstimateService(EstimateService estimateService) {
        this.estimateService = estimateService;
    }

    /**
     * @return the billService
     */
    public BillService getBillService() {
        return billService;
    }

    /**
     * @param billService the billService to set
     */
    public void setBillService(BillService billService) {
        this.billService = billService;
    }

    /**
     * @return the creditNoteService
     */
    public CreditNoteService getCreditNoteService() {
        return creditNoteService;
    }

    /**
     * @param creditNoteService the creditNoteService to set
     */
    public void setCreditNoteService(CreditNoteService creditNoteService) {
        this.creditNoteService = creditNoteService;
    }

    /**
     * @return the customerService
     */
    public CustomerService getCustomerService() {
        return customerService;
    }

    /**
     * @param customerService the customerService to set
     */
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * @return the productService
     */
    public ProductService getProductService() {
        return productService;
    }

    /**
     * @param productService the productService to set
     */
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

}
