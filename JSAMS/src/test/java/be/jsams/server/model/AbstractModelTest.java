package be.jsams.server.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.CivilityBean;
import be.jsams.common.bean.model.ContactInformationBean;
import be.jsams.common.bean.model.LegalFormBean;
import be.jsams.common.bean.model.PaymentModeBean;
import be.jsams.common.bean.model.SocietyBean;

/**
 * 
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public abstract class AbstractModelTest {

    /**
     * Checks if the data are equals.
     * 
     * @param societyBean the {@link SocietyBean} to check
     * @param society the {@link Society} to use
     */
    protected void checkSociety(SocietyBean societyBean, Society society) {
        String activity = society.getActivity();
        assertTrue(activity.equals(societyBean.getActivity()));
        String name = society.getName();
        assertEquals(name, societyBean.getName());
        String responsible = society.getResponsible();
        assertEquals(responsible, societyBean.getResponsible());
        Address address = society.getAddress();
        AddressBean addressBean = societyBean.getAddress();
        checkAddress(addressBean, address);
        Double capital = society.getCapital();
        assertEquals(capital, societyBean.getCapital());
        ContactInformation contactInformation = society.getContactInformation();
        ContactInformationBean contactInformationBean = societyBean.getContactInformation();
        checkContactInformation(contactInformationBean, contactInformation);
        LegalForm legalForm = society.getLegalForm();
        LegalFormBean legalFormBean = societyBean.getLegalForm();
        checkLegalForm(legalFormBean, legalForm);
        byte[] logo = society.getLogo();
        assertEquals(logo, societyBean.getLogo());
        String vatNumber = society.getVatNumber();
        assertEquals(vatNumber, societyBean.getVatNumber());
        Long id = society.getId();
        assertEquals(id, societyBean.getId());

    }
    
    /**
     * Checks if the data are equals.
     * 
     * @param addressBean the {@link AddressBean} to check
     * @param address the {@link Address} to use
     */
    protected void checkAddress(AddressBean addressBean, Address address) {
        String box = address.getBox();
        assertTrue(box.equals(addressBean.getBox()));
        String city = address.getCity();
        assertEquals(city, addressBean.getCity());
        String country = address.getCountry();
        assertEquals(country, addressBean.getCountry());
        String number = address.getNumber();
        assertEquals(number, addressBean.getNumber());
        String street = address.getStreet();
        assertEquals(street, addressBean.getStreet());
        String zipCode = address.getZipCode();
        assertEquals(zipCode, addressBean.getZipCode());
        Long id = address.getId();
        assertEquals(id, addressBean.getId());
    }

    /**
     * Checks if the data are equals.
     * 
     * @param contactInformationBean the {@link ContactInformationBean} to check
     * @param contactInformation the {@link ContactInformation} to use
     */
    protected void checkContactInformation(ContactInformationBean contactInformationBean,
            ContactInformation contactInformation) {
        String email = contactInformation.getEmail();
        assertTrue(email.equals(contactInformationBean.getEmail()));
        String fax = contactInformation.getFax();
        assertEquals(fax, contactInformationBean.getFax());
        String mobile = contactInformation.getMobile();
        assertEquals(mobile, contactInformationBean.getMobile());
        String phone = contactInformation.getPhone();
        assertEquals(phone, contactInformationBean.getPhone());
        String website = contactInformation.getWebsite();
        assertEquals(website, contactInformationBean.getWebsite());
        Long id = contactInformation.getId();
        assertEquals(id, contactInformationBean.getId());
    }

    /**
     * Checks if the data are equals.
     * 
     * @param legalFormBean the {@link LegalFormBean} to check
     * @param legalForm the {@link LegalForm} to use
     */
    protected void checkLegalForm(LegalFormBean legalFormBean, LegalForm legalForm) {
        String label = legalForm.getLabel();
        assertTrue(label.equals(legalFormBean.getLabel()));
        String labelFr = legalForm.getLabelFr();
        assertTrue(labelFr.equals(legalFormBean.getLabelFr()));
        String labelNl = legalForm.getLabelNl();
        assertTrue(labelNl.equals(legalFormBean.getLabelNl()));
        Long id = legalForm.getId();
        assertEquals(id, legalFormBean.getId());
    }

    /**
     * Checks if the data are equals.
     * 
     * @param civilityBean the {@link CivilityBean} to check
     * @param civility the {@link Civility} to use
     */
    protected void checkCivility(CivilityBean civilityBean, Civility civility) {
        String label = civility.getLabel();
        assertTrue(label.equals(civilityBean.getLabel()));
        String labelFr = civility.getLabelFr();
        assertTrue(labelFr.equals(civilityBean.getLabelFr()));
        String labelNl = civility.getLabelNl();
        assertTrue(labelNl.equals(civilityBean.getLabelNl()));
        Long id = civility.getId();
        assertEquals(id, civilityBean.getId());
    }

    /**
     * Checks if the data are equals.
     * 
     * @param paymentModeBean the {@link paymentModeBean} to check
     * @param paymentMode the {@link paymentMode} to use
     */
    protected void checkPaymentMode(PaymentModeBean paymentModeBean, PaymentMode paymentMode) {
        String label = paymentMode.getLabel();
        assertTrue(label.equals(paymentModeBean.getLabel()));
        String labelFr = paymentMode.getLabelFr();
        assertTrue(labelFr.equals(paymentModeBean.getLabelFr()));
        String labelNl = paymentMode.getLabelNl();
        assertTrue(labelNl.equals(paymentModeBean.getLabelNl()));
        Long id = paymentMode.getId();
        assertEquals(id, paymentModeBean.getId());
    }

}
