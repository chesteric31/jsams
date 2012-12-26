package be.jsams.server.service.sale.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import be.jsams.common.bean.builder.PaymentModeBeanBuilder;
import be.jsams.common.bean.model.LegalFormBean;
import be.jsams.common.bean.model.PaymentModeBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.server.dao.sale.BillDao;
import be.jsams.server.model.Society;
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
        BillBean billBean = new BillBean(persistedBill, bean.getSociety(), bean.getCustomer(), bean.getPaymentMode(),
                getProductBeanBuilder());
        billBean.setProductBeanBuilder(getProductBeanBuilder());
        return billBean;
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
        if (bill != null) {
            Society model = bill.getCustomer().getSociety();
            SocietyBean society = new SocietyBean(model);
            society.setLegalForm(new LegalFormBean(model.getLegalForm()));
            CustomerBean customer = getCustomerBeanBuilder().build(bill.getCustomer(), society);
            paymentModeBeanBuilder.setModel(bill.getPaymentMode());
            PaymentModeBean mode = paymentModeBeanBuilder.build();
            return new BillBean(bill, society, customer, mode, getProductBeanBuilder());
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BillBean> findAll(SocietyBean currentSociety) {
        List<Bill> bills = billDao.findAll(currentSociety.getId());
        List<BillBean> beans = mapModelToBean(currentSociety, bills);
        return beans;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BillBean> findByCriteria(BillBean criteria) {
        SocietyBean society = criteria.getSociety();
        List<Bill> bills = billDao.findByCriteria(society.getId(), criteria);
        List<BillBean> beans = mapModelToBean(society, bills);
        return beans;
    }

    /**
     * Maps the bills to the billBeans.
     * 
     * @param society the {@link SocietyBean} to use
     * @param bills the bills to map
     * @return the mapped billBeans.
     */
    private List<BillBean> mapModelToBean(SocietyBean society, List<Bill> bills) {
        List<BillBean> beans = new ArrayList<BillBean>();
        for (Bill bill : bills) {
            CustomerBean customer = getCustomerBeanBuilder().build(bill.getCustomer(), society);
            paymentModeBeanBuilder.setModel(bill.getPaymentMode());
            PaymentModeBean mode = paymentModeBeanBuilder.build();
            beans.add(new BillBean(bill, society, customer, mode, getProductBeanBuilder()));
        }
        return beans;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal findGlobalTurnover(SocietyBean society) {
        List<Bill> bills = billDao.findAll(society.getId());
        BigDecimal globalTurnover = calculateSum(bills);
        return globalTurnover;
    }

    /**
     * Calculates sum of the bills specified.
     * 
     * @param bills the list of bills to use
     * @return the {@link BigDecimal} for the sum of all the bills
     */
    public BigDecimal calculateSum(List<Bill> bills) {
        BigDecimal globalTurnover = new BigDecimal(0D);
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
     * {@inheritDoc}
     */
    @Override
    public Map<Double, List<BillBean>> findOpenedBills(SocietyBean society) {
        BillBean criteria = new BillBean(society, null, null);
        criteria.setClosed(false);
        Map<Double, List<BillBean>> map = findByCriteriaWithSum(society, criteria);
        return map;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Double, List<BillBean>> findNotPaidBills(SocietyBean society) {
        BillBean criteria = new BillBean(society, null, null);
        criteria.setPaymentDate(null);
        Map<Double, List<BillBean>> map = findByCriteriaWithSum(society, criteria);
        return map;
    }

    /**
     * Builds the map with sum and result of criteria query.
     * 
     * @param society the {@link SocietyBean} to use
     * @param criteria the criteria to use
     * @return the built map with sum and result of criteria query
     */
    private Map<Double, List<BillBean>> findByCriteriaWithSum(SocietyBean society, BillBean criteria) {
        List<Bill> bills = billDao.findByCriteria(society.getId(), criteria);
        BigDecimal sum = calculateSum(bills);
        List<BillBean> list = mapModelToBean(society, bills);
        Map<Double, List<BillBean>> map = new HashMap<Double, List<BillBean>>();
        map.put(sum.doubleValue(), list);
        return map;
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

}
