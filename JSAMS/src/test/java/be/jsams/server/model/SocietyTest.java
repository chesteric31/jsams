package be.jsams.server.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.ContactInformationBean;
import be.jsams.common.bean.model.LegalFormBean;
import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.SocietyBean;

/**
 * Test class for {@link Society} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class SocietyTest {
    
    private Society society;

    /**
     * Test method for
     * {@link be.jsams.server.model.Society#Society(be.jsams.common.bean.model.SocietyBean)}
     * .
     */
    @Test
    public void testSocietySocietyBean() {
        SocietyBean bean = MockBeanGenerator.generateMockSociety();
        society = new Society(bean);
        String activity = society.getActivity();
        assertTrue(activity.equals(bean.getActivity()));
        String name = society.getName();
        assertEquals(name, bean.getName());
        String responsible = society.getResponsible();
        assertEquals(responsible, bean.getResponsible());
        Address address = society.getAddress();
        AddressBean addressBean = bean.getAddress();
        checkAddress(addressBean, address);
        Double capital = society.getCapital();
        assertEquals(capital, bean.getCapital());
        ContactInformation contactInformation = society.getContactInformation();
        ContactInformationBean contactInformationBean = bean.getContactInformation();
        checkContactInformation(contactInformationBean, contactInformation);
        LegalForm legalForm = society.getLegalForm();
        LegalFormBean legalFormBean = bean.getLegalForm();
        checkLegalForm(legalFormBean, legalForm);
        byte[] logo = society.getLogo();
        assertEquals(logo, bean.getLogo());
        String vatNumber = society.getVatNumber();
        assertEquals(vatNumber, bean.getVatNumber());
        Long id = society.getId();
        assertEquals(id, bean.getId());
    }

    /**
     * Checks if the data are equals.
     * 
     * @param addressBean the {@link AddressBean} to check
     * @param address the {@link Address} to use
     */
    private void checkAddress(AddressBean addressBean, Address address) {
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
    private void checkContactInformation(ContactInformationBean contactInformationBean,
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
    private void checkLegalForm(LegalFormBean legalFormBean, LegalForm legalForm) {
        String label = legalForm.getLabel();
        assertTrue(label.equals(legalFormBean.getLabel()));
        String labelFr = legalForm.getLabelFr();
        assertTrue(labelFr.equals(legalFormBean.getLabelFr()));
        String labelNl = legalForm.getLabelNl();
        assertTrue(labelNl.equals(legalFormBean.getLabelNl()));
        Long id = legalForm.getId();
        assertEquals(id, legalFormBean.getId());
    }

}
