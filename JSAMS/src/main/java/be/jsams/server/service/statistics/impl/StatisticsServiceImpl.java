package be.jsams.server.service.statistics.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.common.bean.model.sale.EstimateBean;
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
    public List<BillBean> findToThrowBackBills(SocietyBean society) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BillBean> findExpiredBills(SocietyBean society) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
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
    public List<EstimateBean> findNotTransferredEstimates(SocietyBean society) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CustomerBean> findTop5Customers(SocietyBean society) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CustomerBean> findCustomersWithEstimates(SocietyBean society) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CustomerBean> findCustomersWithBills(SocietyBean society) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductBean> findTop5Products(SocietyBean society) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
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

}
