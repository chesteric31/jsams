package be.jsams.server.service.sale.impl;

import java.util.ArrayList;
import java.util.List;

import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.sale.CreditNoteBean;
import be.jsams.server.dao.sale.CreditNoteDao;
import be.jsams.server.model.sale.CreditNote;
import be.jsams.server.service.sale.CreditNoteService;

/**
 * Credit note service implementation
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CreditNoteServiceImpl implements CreditNoteService {

    private CreditNoteDao creditNoteDao;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public CreditNoteBean create(CreditNoteBean bean) {
        CreditNote creditNote = new CreditNote(bean);
        CreditNote addingCreditNote = creditNoteDao.add(creditNote);
        return new CreditNoteBean(addingCreditNote, JsamsDesktop.getInstance().getCurrentSociety());
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
        CreditNote creditNote = creditNoteDao.findById(id);
        CreditNoteBean bean = new CreditNoteBean(creditNote, JsamsDesktop.getInstance().getCurrentSociety());
        return bean;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CreditNoteBean> findAll() {
        SocietyBean currentSociety = JsamsDesktop.getInstance().getCurrentSociety();
        creditNoteDao.setCurrentSociety(currentSociety);
        List<CreditNote> creditNotes = creditNoteDao.findAll();
        List<CreditNoteBean> beans = new ArrayList<CreditNoteBean>();
        for (CreditNote creditNote : creditNotes) {
            beans.add(new CreditNoteBean(creditNote, currentSociety));
        }
        return beans;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CreditNoteBean> findByCriteria(CreditNoteBean criteria) {
        SocietyBean currentSociety = JsamsDesktop.getInstance().getCurrentSociety();
        creditNoteDao.setCurrentSociety(currentSociety);
        List<CreditNote> creditNotes = creditNoteDao.findByCriteria(criteria);
        List<CreditNoteBean> beans = new ArrayList<CreditNoteBean>();
        for (CreditNote creditNote : creditNotes) {
            beans.add(new CreditNoteBean(creditNote, currentSociety));
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
