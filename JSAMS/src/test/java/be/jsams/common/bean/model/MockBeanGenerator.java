package be.jsams.common.bean.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.management.ProductCategoryBean;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.common.bean.model.sale.CreditNoteBean;
import be.jsams.common.bean.model.sale.DeliveryOrderBean;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.common.bean.model.sale.detail.BillDetailBean;
import be.jsams.common.bean.model.sale.detail.CommandDetailBean;
import be.jsams.common.bean.model.sale.detail.CreditNoteDetailBean;
import be.jsams.common.bean.model.sale.detail.DeliveryOrderDetailBean;
import be.jsams.common.bean.model.sale.detail.EstimateDetailBean;
import be.jsams.server.model.MockModelGenerator;

/**
 * Generator of bean objects.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public final class MockBeanGenerator {

    /**
     * Default private constructor for utility class.
     */
    private MockBeanGenerator() {

    }

    /**
     * Generates mock {@link AddressBean}.
     * 
     * @return the built {@link AddressBean}
     */
    public static AddressBean generateMockAddress() {
        AddressBean address = new AddressBean();
        address.setCity("Brussels");
        address.setCountry("Belgium");
        address.setNumber("1");
        address.setStreet("Rue Neuve");
        address.setZipCode("1000");
        return address;
    }

    /**
     * Generates mock {@link ContactInformationBean}.
     * 
     * @return the built {@link ContactInformationBean}
     */
    public static ContactInformationBean generateMockContactInformation() {
        ContactInformationBean contactInformation = new ContactInformationBean();
        contactInformation.setEmail("mail@mail.com");
        contactInformation.setFax("0123456789");
        contactInformation.setMobile("1234567890");
        contactInformation.setPhone("0123456788");
        contactInformation.setWebsite("www.website.com");
        return contactInformation;
    }

    /**
     * Generates mock {@link PeriodBean}.
     * 
     * @return the built {@link PeriodBean}
     * @throws ParseException a possible {@link ParseException}
     */
    public static PeriodBean generateMockPeriod() throws ParseException {
        PeriodBean period = new PeriodBean();
        String pattern = "dd/MM/yyyy";
        Date startDate = new SimpleDateFormat(pattern).parse("01/01/2000");
        Date endDate = new SimpleDateFormat(pattern).parse("31/12/2001");
        period.setStartDate(startDate);
        period.setEndDate(endDate);
        return period;
    }

    /**
     * Generates mock {@link SocietyBean}.
     * 
     * @return the built {@link SocietyBean}
     */
    public static SocietyBean generateMockSociety() {
        SocietyBean society = new SocietyBean();
        society.setActivity("Activity");
        society.setCapital(new Double("123456.78"));
        society.setName("Name");
        society.setVatNumber("BE123456789");

        AddressBean address = generateMockAddress();
        society.setAddress(address);

        ContactInformationBean contactInformation = generateMockContactInformation();
        society.setContactInformation(contactInformation);

        LegalFormBean legalForm = generateMockLegalForm();
        society.setLegalForm(legalForm);

        return society;
    }

    /**
     * Generates mock {@link LegalFormBean}.
     * 
     * @return the built {@link LegalFormBean}
     */
    public static LegalFormBean generateMockLegalForm() {
        LegalFormBean form = new LegalFormBean(MockModelGenerator.generateMockLegalForm());
        return form;
    }

    /**
     * Generates mock {@link CivilityBean}.
     * 
     * @return the built {@link CivilityBean}
     */
    public static CivilityBean generateMockCivility() {
        CivilityBean civility = new CivilityBean(MockModelGenerator.generateMockCivility());
        return civility;
    }

    /**
     * Generates mock {@link ProductBean}.
     * 
     * @return the built {@link ProductBean}
     */
    public static ProductBean generateMockProduct() {
        ProductBean bean = new ProductBean();
        bean.setCategory(generateMockProductCategory());
        bean.setName("name");
        bean.setPrice(15D);
        bean.setQuantityStock(1);
        bean.setReorderLevel(1);
        bean.setVatApplicable(21D);
        return bean;
    }

    /**
     * Generates mock {@link ProductCategoryBean}.
     * 
     * @return the built {@link ProductCategoryBean}
     */
    public static ProductCategoryBean generateMockProductCategory() {
        ProductCategoryBean bean = new ProductCategoryBean();
        bean.setLabel("label");
        bean.setLabelFr("labelFr");
        bean.setLabelNl("labelNl");
        // bean.setSociety(generateMockSociety());
        return bean;
    }

    /**
     * Generates mock {@link PaymentModeBean}.
     * 
     * @return the built {@link PaymentModeBean}
     */
    public static PaymentModeBean generateMockPaymentMode() {
        PaymentModeBean bean = new PaymentModeBean(MockModelGenerator.generateMockPaymentMode());
        return bean;
    }

    /**
     * Generates mock {@link AgentBean}.
     * 
     * @return the built {@link AgentBean}
     */
    public static AgentBean generateMockAgent() {
        AgentBean bean = new AgentBean(generateMockSociety());
        bean.setAddress(generateMockAddress());
        bean.setCivility(generateMockCivility());
        bean.setContactInformation(generateMockContactInformation());
        bean.setFunction("function");
        bean.setName("name");
        return bean;
    }

    /**
     * Generates mock {@link CustomerBean}.
     * 
     * @return the built {@link CustomerBean}
     */
    public static CustomerBean generateMockCustomer() {
        CustomerBean bean = new CustomerBean(generateMockSociety());
        bean.setAgent(generateMockAgent());
        bean.setBank1("bank1");
        bean.setBank2("bank2");
        bean.setBillingAddress(generateMockAddress());
        bean.setCivility(generateMockCivility());
        bean.setContactInformation(generateMockContactInformation());
        bean.setCreditLimit(100D);
        bean.setDefaultDiscountRate(10D);
        bean.setDeliveryAddress(generateMockAddress());
        bean.setDescription("description");
        bean.setFirstName("firstName");
        bean.setLegalForm(generateMockLegalForm());
        bean.setName("name");
        bean.setPaymentMode(generateMockPaymentMode());
        bean.setVatApplicable(6D);
        bean.setVatNumber("BE1234567890");
        return bean;
    }

    /**
     * Generates mock {@link EstimateBean}.
     * 
     * @return the built {@link EstimateBean}
     */
    public static EstimateBean generateMockEstimate() {
        EstimateBean bean = new EstimateBean(generateMockSociety(), generateMockCustomer(), generateMockAgent());
        bean.setBillingAddress(generateMockAddress());
        Date today = new Date();
        bean.setCreationDate(today);
        bean.setDetails(new ArrayList<EstimateDetailBean>());
        bean.setDiscountRate(5D);
        PeriodBean period = new PeriodBean();
        period.setStartDate(today);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        Date todayPlus30Days = calendar.getTime();
        period.setEndDate(todayPlus30Days);
        bean.setPeriod(period);
        bean.setRemark("remark");
        bean.setTransferred(false);
        return bean;
    }
    
    /**
     * Generates mock {@link EstimateDetailBean}.
     * 
     * @return the built {@link EstimateDetailBean}
     */
    public static EstimateDetailBean generateMockEstimateDetail() {
        EstimateDetailBean detailBean = new EstimateDetailBean();
        detailBean.setDiscountRate(25D);
        detailBean.setEstimate(generateMockEstimate());
        detailBean.setPrice(15D);
        detailBean.setProduct(generateMockProduct());
        detailBean.setQuantity(1);
        detailBean.setTransferred(false);
        detailBean.setVatApplicable(21D);
        return detailBean;
    }

    /**
     * Generates mock {@link CommandBean}.
     * 
     * @return the built {@link CommandBean}
     */
    public static CommandBean generateMockCommand() {
        CommandBean bean = new CommandBean(generateMockSociety(), generateMockCustomer(), generateMockAgent());
        bean.setBillingAddress(generateMockAddress());
        Date today = new Date();
        bean.setCreationDate(today);
        bean.setDetails(new ArrayList<CommandDetailBean>());
        bean.setDiscountRate(1D);
        PeriodBean period = new PeriodBean();
        period.setStartDate(today);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH, 60);
        Date todayPlus60Days = calendar.getTime();
        period.setEndDate(todayPlus60Days);
        bean.setPeriod(period);
        bean.setRemark("remark");
        bean.setTransferred(false);
        return bean;
    }

    /**
     * Generates mock {@link DeliveryOrderBean}.
     * 
     * @return the built {@link DeliveryOrderBean}
     */
    public static DeliveryOrderBean generateMockDeliveryOrder() {
        DeliveryOrderBean bean = new DeliveryOrderBean(generateMockSociety(), generateMockCustomer());
        bean.setDeliveryAddress(generateMockAddress());
        Date today = new Date();
        bean.setCreationDate(today);
        bean.setDetails(new ArrayList<DeliveryOrderDetailBean>());
        bean.setDiscountRate(1D);
        PeriodBean period = new PeriodBean();
        period.setStartDate(today);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH, 90);
        Date todayPlus90Days = calendar.getTime();
        period.setEndDate(todayPlus90Days);
        bean.setPeriod(period);
        bean.setRemark("remark");
        bean.setTransferred(false);
        return bean;
    }

    /**
     * Generates mock {@link BillBean}.
     * 
     * @return the built {@link BillBean}
     */
    public static BillBean generateMockBill() {
        BillBean bean = new BillBean(generateMockSociety(), generateMockCustomer(), generateMockPaymentMode());
        bean.setBillingAddress(generateMockAddress());
        bean.setClosed(false);
        Date today = new Date();
        bean.setCreationDate(today);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        Date dateFirstRemember = calendar.getTime();
        bean.setDateFirstRemember(dateFirstRemember);
        calendar.setTime(dateFirstRemember);
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        Date dateSecondRemember = calendar.getTime();
        bean.setDateSecondRemember(dateSecondRemember);
        calendar.setTime(dateSecondRemember);
        calendar.add(Calendar.DAY_OF_MONTH, 15);
        Date dateFormalNotice = calendar.getTime();
        bean.setDateFormalNotice(dateFormalNotice);
        bean.setDetails(new ArrayList<BillDetailBean>());
        bean.setDiscountRate(1D);
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date dueDate = calendar.getTime();
        bean.setDueDate(dueDate);
        bean.setPaid(true);
        PeriodBean period = new PeriodBean();
        period.setStartDate(today);
        Calendar periodCalendar = Calendar.getInstance();
        periodCalendar.setTime(today);
        periodCalendar.add(Calendar.DAY_OF_MONTH, 60);
        Date todayPlus60Days = periodCalendar.getTime();
        period.setEndDate(todayPlus60Days);
        bean.setPeriod(period);
        bean.setRemark("remark");
        return bean;
    }

    /**
     * Generates mock {@link CreditNoteBean}.
     * 
     * @return the built {@link CreditNoteBean}
     */
    public static CreditNoteBean generateMockCreditNote() {
        CreditNoteBean bean = new CreditNoteBean(generateMockSociety(), generateMockCustomer());
        bean.setBillingAddress(generateMockAddress());
        Date today = new Date();
        bean.setCreationDate(today);
        bean.setDetails(new ArrayList<CreditNoteDetailBean>());
        PeriodBean period = new PeriodBean();
        period.setStartDate(today);
        Calendar periodCalendar = Calendar.getInstance();
        periodCalendar.setTime(today);
        periodCalendar.add(Calendar.DAY_OF_MONTH, 60);
        Date todayPlus60Days = periodCalendar.getTime();
        period.setEndDate(todayPlus60Days);
        bean.setPeriod(period);
        bean.setRemark("remark");
        return bean;
    }

    /**
     * Generates mock {@link CommandDetailBean}.
     * 
     * @return the built {@link CommandDetailBean}
     */
    public static CommandDetailBean generateMockCommandDetail() {
        CommandDetailBean detailBean = new CommandDetailBean();
        detailBean.setDiscountRate(15D);
        detailBean.setCommand(generateMockCommand());
        detailBean.setPrice(3.5D);
        detailBean.setProduct(generateMockProduct());
        detailBean.setQuantity(2);
        detailBean.setTransferred(false);
        detailBean.setVatApplicable(15D);
        return detailBean;
    }

    /**
     * Generates mock {@link DeliveryOrderDetailBean}.
     * 
     * @return the built {@link DeliveryOrderDetailBean}
     */
    public static DeliveryOrderDetailBean generateMockDeliveryOrderDetail() {
        DeliveryOrderDetailBean detailBean = new DeliveryOrderDetailBean();
        detailBean.setBillDetail(null);
        detailBean.setCommandDetail(null);
        detailBean.setDeliveryOrder(generateMockDeliveryOrder());
        detailBean.setDiscountRate(5D);
        detailBean.setPrice(20D);
        detailBean.setProduct(generateMockProduct());
        detailBean.setQuantity(1);
        detailBean.setTransferred(true);
        detailBean.setVatApplicable(1D);
        return detailBean;
    }

}
