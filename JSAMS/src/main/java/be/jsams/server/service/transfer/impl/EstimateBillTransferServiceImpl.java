package be.jsams.server.service.transfer.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import be.jsams.common.bean.model.PaymentModeBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.AbstractDocumentBean;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.common.bean.model.sale.BillMediator;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.common.bean.model.sale.detail.BillDetailBean;
import be.jsams.common.bean.model.sale.detail.EstimateDetailBean;
import be.jsams.common.bean.model.transfer.TransferBean;
import be.jsams.server.service.sale.BillService;
import be.jsams.server.service.sale.EstimateService;
import be.jsams.server.service.transfer.AbstractTransferService;
import be.jsams.server.service.transfer.EstimateBillTransferService;

/**
 * Service to transfer an {@link EstimateBean} to a {@link BillBean}.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class EstimateBillTransferServiceImpl extends AbstractTransferService<EstimateBean, BillBean> implements
        EstimateBillTransferService {

    private EstimateService estimateService;
    private BillService billService;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    protected List<BillBean> createNewDocuments(TransferBean model) {
        List<BillBean> newDocuments = new ArrayList<BillBean>();
        int transferMode = model.getTransferMode();
        List<? extends AbstractDocumentBean<?, ?>> documents = model.getDocuments();
        Map<Long, List<EstimateDetailBean>> details = model.getEstimateDetails();
        switch (transferMode) {
        case 1:
        case 3:
            List<EstimateBean> estimates = new ArrayList<EstimateBean>();
            estimates.addAll((List<EstimateBean>) documents);
            for (EstimateBean bean : estimates) {
                newDocuments.add(fullTransfer(bean));
            }
            break;
        case 2:
        case 4:
            Set<Entry<Long, List<EstimateDetailBean>>> set = details.entrySet();
            for (Entry<Long, List<EstimateDetailBean>> item : set) {
                newDocuments.add(partialTransfer(details.get(item.getKey())));
            }
            break;
        default:
            break;
        }
        return newDocuments;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void persistNewDocuments(List<BillBean> newDocuments) {
        for (BillBean document : newDocuments) {
            billService.create(document);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void updateOriginalDocuments(List<EstimateBean> list) {
        for (EstimateBean document : list) {
            boolean allDetailsTransferred = true;
            for (EstimateDetailBean detail : document.getDetails()) {
                if (!detail.isTransferred()) {
                    allDetailsTransferred = false;
                    break;
                }
            }
            if (allDetailsTransferred) {
                document.setTransferred(true);
            }
            estimateService.update(document);
        }
    }

    /**
     * Transfers a list of {@link EstimateDetailBean} to bill in partial
     * transfer.
     * 
     * @param list a list of {@link EstimateDetailBean} to transfer
     * @return the built new {@link BillBean}
     */
    private BillBean partialTransfer(List<EstimateDetailBean> list) {
        EstimateBean estimate = list.get(0).getEstimate();
        CustomerBean customer = estimate.getCustomer();
        PaymentModeBean paymentMode = customer.getPaymentMode();
        BillBean newBean = new BillBean(estimate.getSociety(), customer, paymentMode);
        BillMediator mediator = new BillMediator();
        newBean.setMediator(mediator);
        mediator.setBillBean(newBean);
        newBean.setBillingAddress(estimate.getBillingAddress());
        newBean.getBillingAddress().setId(null);
        newBean.setClosed(false);
        Date creationDate = new Date();
        newBean.setCreationDate(creationDate);
        Date firstRemember = calculateDate(creationDate, getDays("firstRememberDays"));
        newBean.setFirstRememberDate(firstRemember);
        Date secondRemember = calculateDate(creationDate, getDays("firstRememberDays") + getDays("secondRememberDays"));
        newBean.setSecondRememberDate(secondRemember);
        Date formalNotice = calculateDate(creationDate, getDays("firstRememberDays") + getDays("secondRememberDays")
                + getDays("formalNoticeDays"));
        newBean.setFormalNoticeDate(formalNotice);
        List<BillDetailBean> details = new ArrayList<BillDetailBean>();
        for (EstimateDetailBean detail : list) {
            BillDetailBean bean = new BillDetailBean();
            bean.setMediator(newBean.getMediator());
            bean.setBill(newBean);
            bean.setDiscountRate(detail.getDiscountRate());
            bean.setPrice(detail.getPrice());
            bean.setProduct(detail.getProduct());
            bean.setQuantity(detail.getQuantity());
            bean.setTransferred(false);
            bean.setVatApplicable(detail.getVatApplicable());
            details.add(bean);
            detail.setTransferred(true);
        }
        newBean.setDetails(details);
        newBean.setDiscountRate(estimate.getDiscountRate());
        Date dueDate = calculateDueDate(newBean.getCreationDate(), paymentMode.getDaysNumber(),
                paymentMode.isMonthEnd(), paymentMode.getAdditionalDays());
        newBean.setDueDate(dueDate);
        newBean.setRemark(estimate.getRemark());
        return newBean;
    }

    /**
     * Transfers an estimate to bill in full transfer.
     * 
     * @param estimate the {@link EstimateBean} to transfer
     * @return the built new {@link BillBean}
     */
    private BillBean fullTransfer(EstimateBean estimate) {
        CustomerBean customer = estimate.getCustomer();
        PaymentModeBean paymentMode = customer.getPaymentMode();
        BillBean newBean = new BillBean(estimate.getSociety(), customer, paymentMode);
        BillMediator mediator = new BillMediator();
        newBean.setMediator(mediator);
        mediator.setBillBean(newBean);
        newBean.setBillingAddress(estimate.getBillingAddress());
        newBean.getBillingAddress().setId(null);
        newBean.setClosed(false);
        Date creationDate = new Date();
        newBean.setCreationDate(creationDate);
        Date firstRemember = calculateDate(creationDate, getDays("firstRememberDays"));
        newBean.setFirstRememberDate(firstRemember);
        Date secondRemember = calculateDate(creationDate, getDays("firstRememberDays") + getDays("secondRememberDays"));
        newBean.setSecondRememberDate(secondRemember);
        Date formalNotice = calculateDate(creationDate, getDays("firstRememberDays") + getDays("secondRememberDays")
                + getDays("formalNoticeDays"));
        newBean.setFormalNoticeDate(formalNotice);
        List<BillDetailBean> details = new ArrayList<BillDetailBean>();
        for (EstimateDetailBean detail : estimate.getDetails()) {
            if (!detail.isTransferred()) {
                BillDetailBean bean = new BillDetailBean();
                bean.setMediator(newBean.getMediator());
                bean.setBill(newBean);
                bean.setDiscountRate(detail.getDiscountRate());
                bean.setPrice(detail.getPrice());
                bean.setProduct(detail.getProduct());
                bean.setQuantity(detail.getQuantity());
                bean.setTransferred(false);
                bean.setVatApplicable(detail.getVatApplicable());
                details.add(bean);
                detail.setTransferred(true);
            }
        }
        newBean.setDetails(details);
        newBean.setDiscountRate(estimate.getDiscountRate());
        Date dueDate = calculateDueDate(newBean.getCreationDate(), paymentMode.getDaysNumber(),
                paymentMode.isMonthEnd(), paymentMode.getAdditionalDays());
        newBean.setDueDate(dueDate);
        newBean.setRemark(estimate.getRemark());
        return newBean;
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

}
