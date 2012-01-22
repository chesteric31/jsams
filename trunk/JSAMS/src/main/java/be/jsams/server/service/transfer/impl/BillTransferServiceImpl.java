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
import be.jsams.common.bean.model.sale.detail.BillDetailBean;
import be.jsams.common.bean.model.sale.detail.CreditNoteDetailBean;
import be.jsams.common.bean.model.transfer.TransferBean;
import be.jsams.server.service.sale.BillService;
import be.jsams.server.service.sale.CreditNoteService;
import be.jsams.server.service.transfer.AbstractTransferService;
import be.jsams.server.service.transfer.BillTransferService;

/**
 * 
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class BillTransferServiceImpl extends AbstractTransferService implements BillTransferService {

    private BillService billService;
    private CreditNoteService creditNoteService;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void transfer(TransferBean model) {
        int destinationType = model.getDestinationType();
        switch (destinationType) {
        case 4:
            toCreditNoteTransfer(model);
            break;
        default:
            break;
        }
    }

    /**
     * @param model the wrapper contains all the beans to be transferred
     */
    @SuppressWarnings("unchecked")
    private void toCreditNoteTransfer(TransferBean model) {
        int transferMode = model.getTransferMode();
        List<? extends AbstractDocumentBean<?, ?>> documents = model.getDocuments();
        Map<Long, List<BillDetailBean>> details = model.getBillDetails();
        switch (transferMode) {
        case 1:
            BillBean bill = (BillBean) documents.get(0);
            toCreditNoteFullTransfer(bill);
            break;
        case 2:
        case 4:
            Set<Entry<Long, List<BillDetailBean>>> set = details.entrySet();
            for (Entry<Long, List<BillDetailBean>> item : set) {
                toCreditNotePartialTransfer(details.get(item.getKey()));
            }
            break;
        case 3:
            List<BillBean> bills = new ArrayList<BillBean>();
            bills.addAll((List<BillBean>) documents);
            for (BillBean bean : bills) {
                toCreditNoteFullTransfer(bean);
            }
            break;
        default:
            break;
        }
    }

    /**
     * Transfers a list of {@link BillDetailBean} to credit note in full
     * transfer.
     * 
     * @param list the list of {@link BillDetailBean} to transfer
     */
    private void toCreditNotePartialTransfer(List<BillDetailBean> list) {
        BillBean bill = list.get(0).getBill();
        CustomerBean customer = bill.getCustomer();
        CreditNoteBean newBean = new CreditNoteBean(bill.getSociety(), customer);
        newBean.setCreationDate(new Date());
        newBean.setBillingAddress(bill.getBillingAddress());
        // to force to create a new billing address
        newBean.getBillingAddress().setId(null);
        List<CreditNoteDetailBean> details = new ArrayList<CreditNoteDetailBean>();
        for (BillDetailBean detail : list) {
            CreditNoteDetailBean bean = new CreditNoteDetailBean();
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
        creditNoteService.create(newBean);
        // bill.setClosed(true);
        billService.update(bill);
    }

    /**
     * Transfers a bill to credit note in full transfer.
     * 
     * @param bill the {@link BillBean} to transfer
     */
    private void toCreditNoteFullTransfer(BillBean bill) {
        CustomerBean customer = bill.getCustomer();
        CreditNoteBean newBean = new CreditNoteBean(bill.getSociety(), customer);
        newBean.setCreationDate(new Date());
        newBean.setBillingAddress(bill.getBillingAddress());
        // to force to create a new billing address
        newBean.getBillingAddress().setId(null);
        List<CreditNoteDetailBean> details = new ArrayList<CreditNoteDetailBean>();
        for (BillDetailBean detail : bill.getDetails()) {
            CreditNoteDetailBean bean = new CreditNoteDetailBean();
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
        creditNoteService.create(newBean);
        // bill.setClosed(true);
        billService.update(bill);
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
