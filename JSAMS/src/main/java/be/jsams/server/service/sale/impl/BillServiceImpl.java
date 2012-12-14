package be.jsams.server.service.sale.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import be.jsams.client.desktop.Desktop;
import be.jsams.common.bean.builder.PaymentModeBeanBuilder;
import be.jsams.common.bean.model.PaymentModeBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.server.dao.sale.BillDao;
import be.jsams.server.model.sale.Bill;
import be.jsams.server.model.sale.detail.BillDetail;
import be.jsams.server.service.AbstractService;
import be.jsams.server.service.sale.BillService;

/**
 * Bill service implementation.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class BillServiceImpl extends AbstractService implements BillService {

    private BillDao billDao;
    
    private PaymentModeBeanBuilder paymentModeBeanBuilder;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public BillBean create(BillBean bean) {
        Bill bill = new Bill(bean);
        Bill persistedBill = billDao.add(bill);
        return new BillBean(persistedBill, bean.getSociety(), bean.getCustomer(), bean.getPaymentMode());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(BillBean bean) {
        Bill bill = billDao.findById(bean.getId());
        billDao.delete(bill);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        Bill bill = billDao.findById(id);
        billDao.delete(bill);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(BillBean bean) {
        Bill bill = new Bill(bean);
        billDao.update(bill);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BillBean findById(Long id) {
        Bill bill = billDao.findById(id);
        SocietyBean currentSociety = Desktop.getInstance().getCurrentSociety();
        CustomerBean customer = getCustomerBeanBuilder().build(bill.getCustomer(), currentSociety);
        paymentModeBeanBuilder.setModel(bill.getPaymentMode());
        PaymentModeBean mode = paymentModeBeanBuilder.build();
        return new BillBean(bill, currentSociety, customer, mode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BillBean> findAll(SocietyBean currentSociety) {
        billDao.setCurrentSociety(currentSociety);
        List<Bill> bills = billDao.findAll();
        List<BillBean> beans = new ArrayList<BillBean>();
        for (Bill bill : bills) {
            CustomerBean customer = getCustomerBeanBuilder().build(bill.getCustomer(), currentSociety);
            paymentModeBeanBuilder.setModel(bill.getPaymentMode());
            PaymentModeBean mode = paymentModeBeanBuilder.build();
            beans.add(new BillBean(bill, currentSociety, customer, mode));
        }
        return beans;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BillBean> findByCriteria(BillBean criteria) {
        SocietyBean currentSociety = Desktop.getInstance().getCurrentSociety();
        billDao.setCurrentSociety(currentSociety);
        List<Bill> bills = billDao.findByCriteria(criteria);
        List<BillBean> beans = new ArrayList<BillBean>();
        for (Bill bill : bills) {
            CustomerBean customer = getCustomerBeanBuilder().build(bill.getCustomer(), currentSociety);
            paymentModeBeanBuilder.setModel(bill.getPaymentMode());
            PaymentModeBean mode = paymentModeBeanBuilder.build();
            beans.add(new BillBean(bill, currentSociety, customer, mode));
        }
        return beans;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal findGlobalTurnover(SocietyBean society) {
        BigDecimal globalTurnover = new BigDecimal(0D);
        billDao.setCurrentSociety(society);
        List<Bill> bills = billDao.findAll();
        if (bills != null && !bills.isEmpty()) {
            for (Bill bill : bills) {
                List<BillDetail> details = bill.getDetails();
                if (details != null && !details.isEmpty()) {
                    for (BillDetail detail : details) {
                        Double discountRate = detail.getDiscountRate();
                        Double price = detail.getPrice();
                        int quantity = detail.getQuantity();
                        BigDecimal totalEt = BigDecimal.valueOf(price * quantity);
                        if (discountRate != null) {
                            double percentage = discountRate / 100;
                            totalEt = totalEt.multiply(BigDecimal.valueOf(1 - percentage));
                        }
                        globalTurnover = globalTurnover.add(totalEt);
                    }
                }
            }
        }
        return globalTurnover;
    }

    /**
     * @return the billDao
     */
    public BillDao getBillDao() {
        return billDao;
    }

    /**
     * @param billDao the billDao to set
     */
    public void setBillDao(BillDao billDao) {
        this.billDao = billDao;
    }

    /**
     * @return the paymentModeBeanBuilder
     */
    public PaymentModeBeanBuilder getPaymentModeBeanBuilder() {
        return paymentModeBeanBuilder;
    }

    /**
     * @param paymentModeBeanBuilder the paymentModeBeanBuilder to set
     */
    public void setPaymentModeBeanBuilder(PaymentModeBeanBuilder paymentModeBeanBuilder) {
        this.paymentModeBeanBuilder = paymentModeBeanBuilder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BillBean> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

}
