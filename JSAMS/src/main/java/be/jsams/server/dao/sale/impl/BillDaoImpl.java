package be.jsams.server.dao.sale.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.PaymentModeBean;
import be.jsams.common.bean.model.PeriodBean;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.server.dao.impl.DaoImpl;
import be.jsams.server.dao.sale.BillDao;
import be.jsams.server.model.sale.Bill;
import be.jsams.server.utils.DateUtil;

import com.mysql.jdbc.StringUtils;

/**
 * Bill DAO implementation.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class BillDaoImpl extends DaoImpl<Bill> implements BillDao {

    /**
     * Constructor.
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
    public List<Bill> findByCriteria(Long currentSocietyId, BillBean criteria) {
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
        
        queryBuilder.append(WHERE);
        queryBuilder.append("b.customer.society.id = " + currentSocietyId);

        if (!StringUtils.isNullOrEmpty(zipCode)) {
            queryBuilder.append(" AND b.billingAddress.zipCode = " + zipCode);
        }
        if (!StringUtils.isNullOrEmpty(city)) {
            queryBuilder.append(" AND b.billingAddress.city LIKE '%" + city + "%'");
        }
        if (startDate != null && endDate != null) {
            SimpleDateFormat format = getDateFormat();
            String formattedStartDate = format.format(startDate);
            String formattedEndDate = format.format(endDate);
            queryBuilder.append(" AND b.creationDate BETWEEN '" + formattedStartDate + "' AND '" + formattedEndDate
                    + "'");
        } else if (startDate != null && endDate == null) {
            SimpleDateFormat format = getDateFormat();
            String formattedStartDate = format.format(startDate);
            queryBuilder.append(" AND b.creationDate >= '" + formattedStartDate + "'");
        } else if (startDate == null && endDate != null) {
            SimpleDateFormat format = getDateFormat();
            String formattedEndDate = format.format(endDate);
            queryBuilder.append(" AND b.creationDate <= '" + formattedEndDate + "'");
        }
        PaymentModeBean paymentModeBean = criteria.getPaymentMode();
        if (paymentModeBean != null) {
            PaymentModeBean paymentMode = (PaymentModeBean) paymentModeBean.getSelection();
            if (paymentMode != null) {
                queryBuilder.append(" AND b.paymentMode.id = " + paymentMode.getId());
            }
        }
        queryBuilder.append(" AND b.closed = " + closed);

        Date paymentDate = criteria.getPaymentDate();
        if (paymentDate != null) {
            SimpleDateFormat format = getDateFormat();
            String formattedPaymentDate = format.format(paymentDate);
            queryBuilder.append(" AND b.paymentDate = '" + formattedPaymentDate + "'");
        }
        
        Query query = getEntityManager().createQuery(queryBuilder.toString());
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Bill> findAll(Long currentSocietyId) {
        StringBuilder queryBuilder = new StringBuilder("FROM Bill b");

        queryBuilder.append(WHERE);
        queryBuilder.append("b.customer.society.id = " + currentSocietyId);

        Query query = getEntityManager().createQuery(queryBuilder.toString());
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Bill> findToThrowBack(Long societyId) {
        StringBuilder queryBuilder = new StringBuilder("FROM Bill b");

        queryBuilder.append(WHERE);
        queryBuilder.append("b.customer.society.id = " + societyId);
        queryBuilder.append(AND);
        queryBuilder.append("b.paymentDate is null");
        queryBuilder.append(AND);
        SimpleDateFormat format = getDateFormat();
        Date today = new Date();
        String formattedToday = format.format(today);
        queryBuilder.append("b.firstRememberDate <= '" + formattedToday + "'");
        
        Query query = getEntityManager().createQuery(queryBuilder.toString());
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Bill> findExpired(Long societyId) {
        StringBuilder queryBuilder = new StringBuilder("FROM Bill b");

        queryBuilder.append(WHERE);
        queryBuilder.append("b.customer.society.id = " + societyId);
        queryBuilder.append(AND);
        queryBuilder.append("b.paymentDate is null");
        queryBuilder.append(AND);
        SimpleDateFormat format = getDateFormat();
        Date today = new Date();
        String formattedToday = format.format(today);
        queryBuilder.append("b.dueDate <= '" + formattedToday + "'");
        
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
        StringBuilder queryBuilder = new StringBuilder(
                "SELECT sum(bd.quantity* bd.price*(1-(coalesce(bd.discountRate, 0)/100))) "
                        + "from Bill b, BillDetail bd, Customer c "
                        + "WHERE b.id = bd.bill.id and b.customer.id = c.id and c.society.id = " + societyId
                        + " AND b.creationDate BETWEEN '" + formattedStartDate + "' AND '" + formattedEndDate + "'");

        Query query = getEntityManager().createQuery(queryBuilder.toString());
        return (Double) query.getSingleResult();
    }

}
