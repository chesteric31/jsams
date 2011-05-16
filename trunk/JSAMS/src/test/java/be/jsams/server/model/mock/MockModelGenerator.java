package be.jsams.server.model.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import be.jsams.server.model.Address;
import be.jsams.server.model.Civility;
import be.jsams.server.model.ContactInformation;
import be.jsams.server.model.LegalForm;
import be.jsams.server.model.Society;
import be.jsams.server.model.management.Agent;
import be.jsams.server.model.management.Customer;
import be.jsams.server.model.management.Product;
import be.jsams.server.model.management.ProductCategory;
import be.jsams.server.model.sale.Command;
import be.jsams.server.model.sale.detail.CommandDetail;

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
        
//        LegalForm legalForm = generateMockLegalForm();
//        society.setLegalForm(legalForm);

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

    public static Agent generateMockAgent() {
        Agent agent = new Agent();
        agent.setName("Name");
        agent.setFunction("Contact");

        Address address = generateMockAddress();
        agent.setAddress(address);
        
        ContactInformation contactInformation = generateMockContactInformation();
        agent.setContactInformation(contactInformation);
       
        Civility civility = generateMockCivility();
        agent.setCivility(civility);
        
        agent.setSociety(generateMockSociety());
        
        return agent;
    }

    public static Product generateMockProduct() {
        Product product = new Product();
        product.setCategory(generateMockProductCategory());
        product.setName("Product");
        product.setPrice(100D);
        product.setQuantityStock(1);
        product.setReorderLevel(2);
        product.setVatApplicable(6D);
        return product;
    }

    public static Command generateMockCommand() {
        Command command = new Command();
        command.setAgent(generateMockAgent());
        command.setBillingAddress(generateMockAddress());
        command.setCreationDate(new Date());
        command.setCustomer(generateMockCustomer());
        command.setDeliveryAddress(generateMockAddress());
        command.setDiscountRate(1D);
        command.setRemark("Remark");
        command.setTransferred(false);
        CommandDetail detail = new CommandDetail();
        detail.setCommand(command);
        detail.setDiscountRate(0D);
        detail.setPrice(15D);
        detail.setProduct(generateMockProduct());
        detail.setQuantity(1);
        detail.setTransferred(false);
        detail.setVatApplicable(21D);
        List<CommandDetail> details = new ArrayList<CommandDetail>();
        details.add(detail);
        command.setDetails(details);
        return command;
         
    }

    public static Customer generateMockCustomer() {
        Customer customer = new Customer();
        customer.setAgent(generateMockAgent());
        customer.setBank1("Bk1");
        customer.setBank2("Bk2");
        customer.setBillingAddress(generateMockAddress());
        customer.setContactInformation(generateMockContactInformation());
        customer.setCreditLimit(100D);
        customer.setDefaultDiscountRate(0D);
        customer.setDeliveryAddress(generateMockAddress());
        customer.setDescription("Description");
        customer.setName("Name");
        customer.setSociety(generateMockSociety());
        customer.setVatApplicable(6D);
        customer.setVatNumber("BE1234567890");
        return customer;
    }
    
}
