package be.jsams.common.bean.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.management.ProductCategoryBean;
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

}
