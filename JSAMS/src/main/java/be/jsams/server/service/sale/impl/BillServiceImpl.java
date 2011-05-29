package be.jsams.server.service.sale.impl;

import java.util.ArrayList;
import java.util.List;

import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.server.dao.sale.BillDao;
import be.jsams.server.model.sale.Bill;
import be.jsams.server.service.AbstractService;
import be.jsams.server.service.sale.BillService;

/**
 * Bill service implementation
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class BillServiceImpl extends AbstractService implements BillService {

    private BillDao billDao;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public BillBean create(BillBean bean) {
        Bill bill = new Bill(bean);
        Bill addingBill = billDao.add(bill);
        return new BillBean(addingBill, bean.getSociety(), bean.getCustomer());
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
        SocietyBean currentSociety = JsamsDesktop.getInstance().getCurrentSociety();
        CustomerBean customer = getCustomerBeanBuilder().build(bill.getCustomer(), currentSociety);
        BillBean bean = new BillBean(bill, currentSociety, customer);
        return bean;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BillBean> findAll() {
        SocietyBean currentSociety = JsamsDesktop.getInstance().getCurrentSociety();
        billDao.setCurrentSociety(currentSociety);
        List<Bill> bills = billDao.findAll();
        List<BillBean> beans = new ArrayList<BillBean>();
        for (Bill bill : bills) {
            CustomerBean customer = getCustomerBeanBuilder().build(bill.getCustomer(), currentSociety);
            beans.add(new BillBean(bill, currentSociety, customer));
        }
        return beans;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BillBean> findByCriteria(BillBean criteria) {
        SocietyBean currentSociety = JsamsDesktop.getInstance().getCurrentSociety();
        billDao.setCurrentSociety(currentSociety);
        List<Bill> bills = billDao.findByCriteria(criteria);
        List<BillBean> beans = new ArrayList<BillBean>();
        for (Bill bill : bills) {
            CustomerBean customer = getCustomerBeanBuilder().build(bill.getCustomer(), currentSociety);
            beans.add(new BillBean(bill, currentSociety, customer));
        }
        return beans;
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

}
