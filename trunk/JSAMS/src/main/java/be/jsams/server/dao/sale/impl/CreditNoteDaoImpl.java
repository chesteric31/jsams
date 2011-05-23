package be.jsams.server.dao.sale.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.PeriodBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.sale.CreditNoteBean;
import be.jsams.server.dao.impl.DaoImpl;
import be.jsams.server.dao.sale.CreditNoteDao;
import be.jsams.server.model.sale.CreditNote;

import com.mysql.jdbc.StringUtils;

/**
 * Credit note DAO implementation.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CreditNoteDaoImpl extends DaoImpl<CreditNote> implements CreditNoteDao {

    private SocietyBean currentSociety;
    
    /**
     * Constructor
     * 
     * @param type the class type
     */
    public CreditNoteDaoImpl(final Class<CreditNote> type) {
        super(type);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CreditNote> findByCriteria(CreditNoteBean criteria) {
        StringBuilder queryBuilder = new StringBuilder("FROM CreditNote c");

        PeriodBean period = criteria.getPeriod();
        Date startDate = period.getStartDate();
        Date endDate = period.getEndDate();

        AddressBean billingAddress = criteria.getBillingAddress();
        String zipCode = billingAddress.getZipCode();
        String city = billingAddress.getCity();

        Long societyId = criteria.getSociety().getId();

        queryBuilder.append(" WHERE ");
        queryBuilder.append("c.customer.society.id = " + societyId);

        if (!StringUtils.isNullOrEmpty(zipCode)) {
            queryBuilder.append(" AND c.billingAddress.zipCode = " + zipCode);
        }
        if (!StringUtils.isNullOrEmpty(city)) {
            queryBuilder.append(" AND c.billingAddress.city LIKE '%" + city + "%'");
        }
        if (startDate != null && endDate != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String formattedStartDate = format.format(startDate);
            String formattedEndDate = format.format(endDate);
            queryBuilder.append(" AND c.creationDate BETWEEN '" + formattedStartDate + "' AND '" + formattedEndDate
                    + "'");
        } else if (startDate != null && endDate == null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String formattedStartDate = format.format(startDate);
            queryBuilder.append(" AND c.creationDate >= '" + formattedStartDate + "'");
        } else if (startDate == null && endDate != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String formattedEndDate = format.format(endDate);
            queryBuilder.append(" AND c.creationDate <= '" + formattedEndDate + "'");
        }

        Query query = getEntityManager().createQuery(queryBuilder.toString());
        List<CreditNote> result = query.getResultList();
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<CreditNote> findAll() {
        StringBuilder queryBuilder = new StringBuilder("FROM CreditNote c");

        queryBuilder.append(" WHERE ");
        queryBuilder.append("c.society.id = " + getCurrentSociety().getId());

        Query query = getEntityManager().createQuery(queryBuilder.toString());
        List<CreditNote> result = query.getResultList();
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public SocietyBean getCurrentSociety() {
        return currentSociety;
    }

    /**
     * {@inheritDoc}
     */
    public void setCurrentSociety(SocietyBean currentSociety) {
        this.currentSociety = currentSociety;
    }

}
