package be.jsams.common.bean.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import be.jsams.server.model.mock.MockModelGenerator;

/**
 * Generator of bean objects.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public final class MockBeanGenerator {

    public static AddressBean generateMockAddress() {
        AddressBean address = new AddressBean();
        address.setCity("Brussels");
        address.setCountry("Belgium");
        address.setNumber("1");
        address.setStreet("Rue Neuve");
        address.setZipCode("1000");
        return address;
    }

    public static ContactInformationBean generateMockContactInformation() {
        ContactInformationBean contactInformation = new ContactInformationBean();
        contactInformation.setEmail("mail@mail.com");
        contactInformation.setFax("0123456789");
        contactInformation.setMobile("1234567890");
        contactInformation.setPhone("0123456788");
        contactInformation.setWebsite("www.website.com");
        return contactInformation;
    }

    public static PeriodBean generateMockPeriod() throws ParseException {
        PeriodBean period = new PeriodBean();
        String pattern = "dd/MM/yyyy";
        Date startDate = new SimpleDateFormat(pattern).parse("01/01/2000");
        Date endDate = new SimpleDateFormat(pattern).parse("31/12/2001");
        period.setStartDate(startDate);
        period.setEndDate(endDate);
        return period;
    }
    
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

    public static LegalFormBean generateMockLegalForm() {
        LegalFormBean form = new LegalFormBean(MockModelGenerator.generateMockLegalForm());
        return form;
    }

}
