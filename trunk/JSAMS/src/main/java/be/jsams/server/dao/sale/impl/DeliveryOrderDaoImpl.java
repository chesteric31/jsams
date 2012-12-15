package be.jsams.server.dao.sale.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.PeriodBean;
import be.jsams.common.bean.model.sale.DeliveryOrderBean;
import be.jsams.server.dao.impl.DaoImpl;
import be.jsams.server.dao.sale.DeliveryOrderDao;
import be.jsams.server.model.sale.DeliveryOrder;

import com.mysql.jdbc.StringUtils;

/**
 * Delivery Order DAO implementation.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class DeliveryOrderDaoImpl extends DaoImpl<DeliveryOrder> implements DeliveryOrderDao {

    /**
     * Constructor.
     * 
     * @param type the class type
     */
    public DeliveryOrderDaoImpl(final Class<DeliveryOrder> type) {
        super(type);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<DeliveryOrder> findByCriteria(Long currentSocietyId, DeliveryOrderBean criteria) {
        StringBuilder queryBuilder = new StringBuilder("FROM DeliveryOrder d");

        PeriodBean period = criteria.getPeriod();
        Date startDate = null;
        Date endDate = null;
        if (period != null) {
            startDate = period.getStartDate();
            endDate = period.getEndDate();
        }

        AddressBean deliveryAddress = criteria.getDeliveryAddress();
        String zipCode = deliveryAddress.getZipCode();
        String city = deliveryAddress.getCity();

        boolean transferred = criteria.isTransferred();

        queryBuilder.append(WHERE);
        queryBuilder.append("d.customer.society.id = " + currentSocietyId);

        if (!StringUtils.isNullOrEmpty(zipCode)) {
            queryBuilder.append(" AND d.deliveryAddress.zipCode = " + zipCode);
        }
        if (!StringUtils.isNullOrEmpty(city)) {
            queryBuilder.append(" AND d.deliveryAddress.city LIKE '%" + city + "%'");
        }
        if (startDate != null && endDate != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String formattedStartDate = format.format(startDate);
            String formattedEndDate = format.format(endDate);
            queryBuilder.append(" AND d.creationDate BETWEEN '" + formattedStartDate + "' AND '" + formattedEndDate
                    + "'");
        } else if (startDate != null && endDate == null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String formattedStartDate = format.format(startDate);
            queryBuilder.append(" AND d.creationDate >= '" + formattedStartDate + "'");
        } else if (startDate == null && endDate != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String formattedEndDate = format.format(endDate);
            queryBuilder.append(" AND d.creationDate <= '" + formattedEndDate + "'");
        }
        queryBuilder.append(" AND d.transferred = " + transferred);

        Query query = getEntityManager().createQuery(queryBuilder.toString());
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<DeliveryOrder> findAll(Long currentSocietyId) {
        StringBuilder queryBuilder = new StringBuilder("FROM DeliveryOrder d");

        queryBuilder.append(WHERE);
        queryBuilder.append("d.customer.society.id = " + currentSocietyId);

        Query query = getEntityManager().createQuery(queryBuilder.toString());
        return query.getResultList();
    }

}
