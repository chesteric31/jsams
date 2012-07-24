package be.jsams.server.dao.sale.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.PaymentModeBean;
import be.jsams.common.bean.model.PeriodBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.server.dao.impl.DaoImpl;
import be.jsams.server.dao.sale.BillDao;
import be.jsams.server.model.sale.Bill;

import com.mysql.jdbc.StringUtils;

/**
 * Bill DAO implementation.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class BillDaoImpl extends DaoImpl<Bill> implements BillDao {

    private SocietyBean currentSociety;
    
    /**
     * Constructor
     * 
     * @param type the class type
     */
    public BillDaoImpl(final Class<Bill> type) {
        super(type);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Bill> findByCriteria(BillBean criteria) {
        StringBuilder queryBuilder = new StringBuilder("FROM Bill b");

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

        boolean closed = criteria.isClosed();
        boolean paid = criteria.isPaid();

        Long societyId = getCurrentSociety().getId();

        queryBuilder.append(" WHERE ");
        queryBuilder.append("b.customer.society.id = " + societyId);

        if (!StringUtils.isNullOrEmpty(zipCode)) {
            queryBuilder.append(" AND b.billingAddress.zipCode = " + zipCode);
        }
        if (!StringUtils.isNullOrEmpty(city)) {
            queryBuilder.append(" AND b.billingAddress.city LIKE '%" + city + "%'");
        }
        if (startDate != null && endDate != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String formattedStartDate = format.format(startDate);
            String formattedEndDate = format.format(endDate);
            queryBuilder.append(" AND b.creationDate BETWEEN '" + formattedStartDate + "' AND '" + formattedEndDate
                    + "'");
        } else if (startDate != null && endDate == null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String formattedStartDate = format.format(startDate);
            queryBuilder.append(" AND b.creationDate >= '" + formattedStartDate + "'");
        } else if (startDate == null && endDate != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String formattedEndDate = format.format(endDate);
            queryBuilder.append(" AND b.creationDate <= '" + formattedEndDate + "'");
        }
        PaymentModeBean paymentMode = (PaymentModeBean) criteria.getPaymentMode().getSelection();
        if (paymentMode != null) {
            queryBuilder.append(" AND b.paymentMode.id = " + paymentMode.getId());
        }
        queryBuilder.append(" AND b.closed = " + closed);
        queryBuilder.append(" AND b.paid = " + paid);

        Query query = getEntityManager().createQuery(queryBuilder.toString());
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Bill> findAll() {
        StringBuilder queryBuilder = new StringBuilder("FROM Bill b");

        queryBuilder.append(" WHERE ");
        queryBuilder.append("b.customer.society.id = " + getCurrentSociety().getId());

        Query query = getEntityManager().createQuery(queryBuilder.toString());
        return query.getResultList();
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
