package be.jsams.server.model.mock;

import be.jsams.server.model.Address;
import be.jsams.server.model.Civility;
import be.jsams.server.model.ContactInformation;
import be.jsams.server.model.LegalForm;
import be.jsams.server.model.ProductCategory;
import be.jsams.server.model.Society;

/**
 * Generator of model objects.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class MockModelGenerator {

    public static Society generateMockSociety() {
        Society society = new Society();
        society.setActivity("Activity");
        society.setCapital(new Double("123456.78"));
        society.setName("Name");
        society.setVatNumber("BE123456789");

        Address address = generateMockAddress();
        society.setAddress(address);
        
        ContactInformation contactInformation = generateMockContactInformation();
        society.setContactInformation(contactInformation);
        
        LegalForm legalForm = generateMockLegalForm();
        society.setLegalForm(legalForm);

        return society;
    }
    
    public static ContactInformation generateMockContactInformation() {
        ContactInformation contactInformation = new ContactInformation();
        contactInformation.setPhone("+3221223456");
        return contactInformation;
    }
    
    public static Address generateMockAddress() {
        Address address = new Address();
        address.setCity("Brussels");
        address.setCountry("Belgium");
        address.setNumber("1");
        address.setStreet("Rue Neuve");
        address.setZipCode("1000");
        return address;
    }
    
    public static Civility generateMockCivility() {
        Civility civility = new Civility();
        civility.setLabel("label");
        civility.setLabelFr("labelFr");
        civility.setLabelNl("labelNl");
        return civility;
    }

    public static ProductCategory generateMockProductCategory() {
        ProductCategory category = new ProductCategory();
        category.setLabel("category");
        category.setLabelFr("categoryFr");
        category.setLabelNl("categoryNl");
        category.setSociety(generateMockSociety());
        return category;
    }
    
    public static LegalForm generateMockLegalForm() {
        LegalForm form = new LegalForm();
        form.setLabel("label");
        form.setLabelFr("labelFr");
        form.setLabelNl("labelNl");
        return form;
    }
}
