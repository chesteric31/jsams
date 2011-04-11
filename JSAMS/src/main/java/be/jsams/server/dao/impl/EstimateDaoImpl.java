package be.jsams.server.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.common.bean.model.sale.PeriodBean;
import be.jsams.server.dao.EstimateDao;
import be.jsams.server.model.Estimate;

import com.mysql.jdbc.StringUtils;

/**
 * Estimate DAO implementation.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EstimateDaoImpl extends DaoImpl<Estimate> implements EstimateDao {

    /**
     * Constructor
     * 
     * @param type
     *            the class type
     */
    public EstimateDaoImpl(final Class<Estimate> type) {
        super(type);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Estimate> findByCriteria(final EstimateBean criteria) {
        StringBuilder queryBuilder = new StringBuilder("FROM Estimate e");

        PeriodBean period = criteria.getPeriod();
        Date startDate = period.getStartDate();
        Date endDate = period.getEndDate();

        String zipCode = criteria.getBillingAddress().getZipCode();
        String city = criteria.getBillingAddress().getCity();

        boolean transferred = criteria.isTransferred();

        Long societyId = JsamsDesktop.getInstance().getCurrentSociety().getId();

        queryBuilder.append(" WHERE ");
        queryBuilder.append("e.customer.society.id = " + societyId);

        if (!StringUtils.isNullOrEmpty(zipCode)) {
            queryBuilder.append(" AND e.billingAddress.zipCode = " + zipCode);
        }
        if (!StringUtils.isNullOrEmpty(city)) {
            queryBuilder.append(" AND e.billingAddress.city = " + city);
        }
        if (startDate != null && endDate != null) {
            queryBuilder.append(" AND e.creationDate BETWEEN STR_TO_DATE('" + startDate
                    + "', 'yyyy/mm/dd') AND STR_TO_DATE('" + endDate + "', 'yyyy/mm/dd')");
        }
        int bit = 0;
        if (transferred) {
            bit = 1;
        }
        queryBuilder.append(" AND e.transferred = " + bit);

        Query query = getEntityManager().createQuery(queryBuilder.toString());
        List<Estimate> result = query.getResultList();
        return result;
    }

}