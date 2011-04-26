package be.jsams.server.dao.sale.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.common.bean.model.sale.PeriodBean;
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
     * Constructor
     * 
     * @param type
     *            the class type
     */
    public CommandDaoImpl(final Class<Command> type) {
        super(type);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Command> findByCriteria(final CommandBean criteria) {
        StringBuilder queryBuilder = new StringBuilder("FROM Command c");

        PeriodBean period = criteria.getPeriod();
        Date startDate = period.getStartDate();
        Date endDate = period.getEndDate();

        String zipCode = criteria.getBillingAddress().getZipCode();
        String city = criteria.getBillingAddress().getCity();

        boolean transferred = criteria.isTransferred();

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
        }
        queryBuilder.append(" AND c.transferred = " + transferred);

        Query query = getEntityManager().createQuery(queryBuilder.toString());
        List<Command> result = query.getResultList();
        return result;
    }

}
