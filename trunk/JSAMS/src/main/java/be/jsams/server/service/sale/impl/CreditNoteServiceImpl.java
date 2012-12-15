package be.jsams.server.service.sale.impl;

import java.util.ArrayList;
import java.util.List;

import be.jsams.client.desktop.Desktop;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.CreditNoteBean;
import be.jsams.server.dao.sale.CreditNoteDao;
import be.jsams.server.model.sale.CreditNote;
import be.jsams.server.service.AbstractService;
import be.jsams.server.service.sale.CreditNoteService;

/**
 * Credit note service implementation.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CreditNoteServiceImpl extends AbstractService implements CreditNoteService {

    private CreditNoteDao creditNoteDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public CreditNoteBean create(CreditNoteBean bean) {
        CreditNote creditNote = new CreditNote(bean);
        CreditNote persistedCreditNote = creditNoteDao.add(creditNote);
        SocietyBean currentSociety = Desktop.getInstance().getCurrentSociety();
        return new CreditNoteBean(persistedCreditNote, currentSociety, bean.getCustomer());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(CreditNoteBean bean) {
        CreditNote creditNote = creditNoteDao.findById(bean.getId());
        creditNoteDao.delete(creditNote);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        CreditNote creditNote = creditNoteDao.findById(id);
        creditNoteDao.delete(creditNote);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(CreditNoteBean bean) {
        CreditNote creditNote = new CreditNote(bean);
        creditNoteDao.update(creditNote);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CreditNoteBean findById(Long id) {
        SocietyBean currentSociety = Desktop.getInstance().getCurrentSociety();
        CreditNote creditNote = creditNoteDao.findById(id);
        CustomerBean customer = getCustomerBeanBuilder().build(creditNote.getCustomer(), currentSociety);
        return new CreditNoteBean(creditNote, currentSociety, customer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CreditNoteBean> findAll(SocietyBean currentSociety) {
        List<CreditNote> creditNotes = creditNoteDao.findAll(currentSociety.getId());
        List<CreditNoteBean> beans = new ArrayList<CreditNoteBean>();
        for (CreditNote creditNote : creditNotes) {
            CustomerBean customer = getCustomerBeanBuilder().build(creditNote.getCustomer(), currentSociety);
            beans.add(new CreditNoteBean(creditNote, currentSociety, customer));
        }
        return beans;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CreditNoteBean> findByCriteria(CreditNoteBean criteria) {
        SocietyBean society = criteria.getSociety();
        List<CreditNote> creditNotes = creditNoteDao.findByCriteria(society.getId(), criteria);
        List<CreditNoteBean> beans = new ArrayList<CreditNoteBean>();
        for (CreditNote creditNote : creditNotes) {
            CustomerBean customer = getCustomerBeanBuilder().build(creditNote.getCustomer(), society);
            beans.add(new CreditNoteBean(creditNote, society, customer));
        }
        return beans;
    }

    /**
     * @return the creditNoteDao
     */
    public CreditNoteDao getCreditNoteDao() {
        return creditNoteDao;
    }

    /**
     * @param creditNoteDao the creditNoteDao to set
     */
    public void setCreditNoteDao(CreditNoteDao creditNoteDao) {
        this.creditNoteDao = creditNoteDao;
    }

}
