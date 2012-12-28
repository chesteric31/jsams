package be.jsams.server.dao.sale.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.PeriodBean;
import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.server.dao.impl.DaoImpl;
import be.jsams.server.dao.sale.CommandDao;
import be.jsams.server.model.sale.Command;

import com.mysql.jdbc.StringUtils;

/**
 * Command DAO implementation.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CommandDaoImpl extends DaoImpl<Command> implements CommandDao {

    /**
     * Constructor.
     * 
     * @param type the class type
     */
    public CommandDaoImpl(final Class<Command> type) {
        super(type);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Command> findByCriteria(Long currentSocietyId, final CommandBean criteria) {
        StringBuilder queryBuilder = new StringBuilder("FROM Command c");

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
        queryBuilder.append(" AND c.transferred = " + transferred);

        Query query = getEntityManager().createQuery(queryBuilder.toString());
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Command> findAll(Long currentSocietyId) {
        StringBuilder queryBuilder = new StringBuilder("FROM Command c");

        queryBuilder.append(WHERE);
        queryBuilder.append("c.customer.society.id = " + currentSocietyId);

        Query query = getEntityManager().createQuery(queryBuilder.toString());
        return query.getResultList();
    }

}
