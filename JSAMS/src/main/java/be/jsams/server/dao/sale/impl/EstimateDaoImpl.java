package be.jsams.server.dao.sale.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.PeriodBean;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.server.dao.impl.DaoImpl;
import be.jsams.server.dao.sale.EstimateDao;
import be.jsams.server.model.sale.Estimate;

import com.mysql.jdbc.StringUtils;

/**
 * Estimate DAO implementation.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EstimateDaoImpl extends DaoImpl<Estimate> implements EstimateDao {

    /**
     * Constructor.
     * 
     * @param type the class type
     */
    public EstimateDaoImpl(final Class<Estimate> type) {
        super(type);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Estimate> findByCriteria(Long currentSocietyId, final EstimateBean criteria) {
        StringBuilder queryBuilder = new StringBuilder("FROM Estimate e");

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

        boolean transferred = criteria.isTransferred();

        queryBuilder.append(WHERE);
        queryBuilder.append("e.customer.society.id = " + currentSocietyId);

        if (!StringUtils.isNullOrEmpty(zipCode)) {
            queryBuilder.append(" AND e.billingAddress.zipCode = " + zipCode);
        }
        if (!StringUtils.isNullOrEmpty(city)) {
            queryBuilder.append(" AND e.billingAddress.city LIKE '%" + city + "%'");
        }
        if (startDate != null && endDate != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String formattedStartDate = format.format(startDate);
            String formattedEndDate = format.format(endDate);
            queryBuilder.append(" AND e.creationDate BETWEEN '" + formattedStartDate + "' AND '" + formattedEndDate
                    + "'");
        } else if (startDate != null && endDate == null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String formattedStartDate = format.format(startDate);
            queryBuilder.append(" AND e.creationDate >= '" + formattedStartDate + "'");
        } else if (startDate == null && endDate != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String formattedEndDate = format.format(endDate);
            queryBuilder.append(" AND e.creationDate <= '" + formattedEndDate + "'");
        }
        queryBuilder.append(" AND e.transferred = " + transferred);

        Query query = getEntityManager().createQuery(queryBuilder.toString());
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Estimate> findAll(Long currentSocietyId) {
        StringBuilder queryBuilder = new StringBuilder("FROM Estimate e");

        queryBuilder.append(WHERE);
        queryBuilder.append("e.customer.society.id = " + currentSocietyId);

        Query query = getEntityManager().createQuery(queryBuilder.toString());
        return query.getResultList();
    }

}
