package be.jsams.server.service.transfer.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.AbstractDocumentBean;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.common.bean.model.sale.CreditNoteBean;
import be.jsams.common.bean.model.sale.CreditNoteMediator;
import be.jsams.common.bean.model.sale.detail.BillDetailBean;
import be.jsams.common.bean.model.sale.detail.CreditNoteDetailBean;
import be.jsams.common.bean.model.transfer.TransferBean;
import be.jsams.server.service.sale.BillService;
import be.jsams.server.service.sale.CreditNoteService;
import be.jsams.server.service.transfer.AbstractTransferService;
import be.jsams.server.service.transfer.BillCreditNoteTransferService;

/**
 * Service to transfer an {@link BillBean} to a {@link CreditNoteBean}.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class BillCreditNoteTransferServiceImpl extends AbstractTransferService<BillBean, CreditNoteBean> implements
        BillCreditNoteTransferService {

    private BillService billService;
    private CreditNoteService creditNoteService;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    protected List<CreditNoteBean> createNewDocuments(TransferBean model) {
        List<CreditNoteBean> newDocuments = new ArrayList<CreditNoteBean>();
        int transferMode = model.getTransferMode();
        List<? extends AbstractDocumentBean<?, ?>> documents = model.getDocuments();
        Map<Long, List<BillDetailBean>> details = model.getBillDetails();
        switch (transferMode) {
        case 1:
        case 3:
            List<BillBean> bills = new ArrayList<BillBean>();
            bills.addAll((List<BillBean>) documents);
            for (BillBean bean : bills) {
                newDocuments.add(fullTransfer(bean));
            }
            break;
        case 2:
        case 4:
            Set<Entry<Long, List<BillDetailBean>>> set = details.entrySet();
            for (Entry<Long, List<BillDetailBean>> item : set) {
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
    protected void persistNewDocuments(List<CreditNoteBean> newDocuments) {
        for (CreditNoteBean document : newDocuments) {
            creditNoteService.create(document);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void updateOriginalDocuments(List<BillBean> list) {
    }

    /**
     * Transfers a list of {@link BillDetailBean} to credit note in full
     * transfer.
     * 
     * @param list the list of {@link BillDetailBean} to transfer
     * @return the built new {@link CreditNoteBean}
     */
    private CreditNoteBean partialTransfer(List<BillDetailBean> list) {
        BillBean bill = list.get(0).getBill();
        CustomerBean customer = bill.getCustomer();
        CreditNoteBean newBean = new CreditNoteBean(bill.getSociety(), customer);
        CreditNoteMediator mediator = new CreditNoteMediator();
        newBean.setMediator(mediator);
        mediator.setCreditNoteBean(newBean);
        newBean.setCreationDate(new Date());
        newBean.setBillingAddress(bill.getBillingAddress());
        // to force to create a new billing address
        newBean.getBillingAddress().setId(null);
        List<CreditNoteDetailBean> details = new ArrayList<CreditNoteDetailBean>();
        for (BillDetailBean detail : list) {
            if (!detail.isTransferred()) {
                CreditNoteDetailBean bean = new CreditNoteDetailBean();
                bean.setMediator(newBean.getMediator());
                bean.setCreditNote(newBean);
                bean.setDiscountRate(detail.getDiscountRate());
                bean.setPrice(detail.getPrice());
                bean.setProduct(detail.getProduct());
                bean.setQuantity(detail.getQuantity());
                bean.setVatApplicable(detail.getVatApplicable());
                details.add(bean);
                detail.setTransferred(true);
            }
        }
        newBean.setDetails(details);
        newBean.setRemark(bill.getRemark());
        return newBean;
    }

    /**
     * Transfers a bill to credit note in full transfer.
     * 
     * @param bill the {@link BillBean} to transfer
     * @return the built new {@link CreditNoteBean}
     */
    private CreditNoteBean fullTransfer(BillBean bill) {
        CustomerBean customer = bill.getCustomer();
        CreditNoteBean newBean = new CreditNoteBean(bill.getSociety(), customer);
        CreditNoteMediator mediator = new CreditNoteMediator();
        newBean.setMediator(mediator);
        mediator.setCreditNoteBean(newBean);
        newBean.setCreationDate(new Date());
        newBean.setBillingAddress(bill.getBillingAddress());
        // to force to create a new billing address
        newBean.getBillingAddress().setId(null);
        List<CreditNoteDetailBean> details = new ArrayList<CreditNoteDetailBean>();
        for (BillDetailBean detail : bill.getDetails()) {
            CreditNoteDetailBean bean = new CreditNoteDetailBean();
            bean.setMediator(newBean.getMediator());
            bean.setCreditNote(newBean);
            bean.setDiscountRate(detail.getDiscountRate());
            bean.setPrice(detail.getPrice());
            bean.setProduct(detail.getProduct());
            bean.setQuantity(detail.getQuantity());
            bean.setVatApplicable(detail.getVatApplicable());
            detail.setTransferred(true);
            details.add(bean);
        }
        newBean.setDetails(details);
        newBean.setRemark(bill.getRemark());
        return newBean;
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
