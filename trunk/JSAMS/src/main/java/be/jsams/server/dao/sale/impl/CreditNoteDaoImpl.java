package be.jsams.server.dao.sale.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.PeriodBean;
import be.jsams.common.bean.model.sale.CreditNoteBean;
import be.jsams.server.dao.impl.DaoImpl;
import be.jsams.server.dao.sale.CreditNoteDao;
import be.jsams.server.model.sale.CreditNote;
import be.jsams.server.utils.DateUtil;

import com.mysql.jdbc.StringUtils;

/**
 * Credit note DAO implementation.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CreditNoteDaoImpl extends DaoImpl<CreditNote> implements CreditNoteDao {

    /**
     * Constructor.
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
    public List<CreditNote> findByCriteria(Long currentSocietyId, CreditNoteBean criteria) {
        StringBuilder queryBuilder = new StringBuilder("FROM CreditNote c");

        PeriodBean period = criteria.getPeriod();
        Date startDate = null;
        Date endDate = null;
        if (period != null) {
            startDate = period.getStartDate();
            endDate = period.getEndDate();
        }

        AddressBean billingAddress = criteria.getBillingAddress();
        String zipCode = billingAddress.getZipCode();
        String city = billingAddress.getCity();

        queryBuilder.append(WHERE);
        queryBuilder.append("c.customer.society.id = " + currentSocietyId);

        if (!StringUtils.isNullOrEmpty(zipCode)) {
            queryBuilder.append(" AND c.billingAddress.zipCode = " + zipCode);
        }
        if (!StringUtils.isNullOrEmpty(city)) {
            queryBuilder.append(" AND c.billingAddress.city LIKE '%" + city + "%'");
        }
        if (startDate != null && endDate != null) {
            SimpleDateFormat format = getDateFormat();
            String formattedStartDate = format.format(startDate);
            String formattedEndDate = format.format(endDate);
            queryBuilder.append(" AND c.creationDate BETWEEN '" + formattedStartDate + "' AND '" + formattedEndDate
                    + "'");
        } else if (startDate != null && endDate == null) {
            SimpleDateFormat format = getDateFormat();
            String formattedStartDate = format.format(startDate);
            queryBuilder.append(" AND c.creationDate >= '" + formattedStartDate + "'");
        } else if (startDate == null && endDate != null) {
            SimpleDateFormat format = getDateFormat();
            String formattedEndDate = format.format(endDate);
            queryBuilder.append(" AND c.creationDate <= '" + formattedEndDate + "'");
        }

        Query query = getEntityManager().createQuery(queryBuilder.toString());
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<CreditNote> findAll(Long currentSocietyId) {
        StringBuilder queryBuilder = new StringBuilder("FROM CreditNote c");

        queryBuilder.append(WHERE);
        queryBuilder.append("c.customer.society.id = " + currentSocietyId);

        Query query = getEntityManager().createQuery(queryBuilder.toString());
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double findTurnoverByMonth(Long societyId, int month, int year) {
        SimpleDateFormat format = getDateFormat();
        Calendar instance = Calendar.getInstance();
        Date[] startEndDates = DateUtil.getStartEndDates(instance.get(Calendar.DATE), month, year);
        Date startDate = startEndDates[0];
        Date endDate = startEndDates[1];
        instance.setTime(endDate);
        instance.add(Calendar.DATE, 1);
        Date endDayPlusOneDay = instance.getTime();
        String formattedStartDate = format.format(startDate);
        String formattedEndDate = format.format(endDayPlusOneDay);
        StringBuilder queryBuilder = new StringBuilder("SELECT sum(cnd.quantity* cnd.price) "
                + "from CreditNote cn, CreditNoteDetail cnd, Customer c "
                + "WHERE cn.id = cnd.creditNote.id and cn.customer.id = c.id and c.society.id = " + societyId
                + " AND cn.creationDate BETWEEN '" + formattedStartDate + "' AND '" + formattedEndDate + "'");

        Query query = getEntityManager().createQuery(queryBuilder.toString());
        return (Double) query.getSingleResult();
    }

}
