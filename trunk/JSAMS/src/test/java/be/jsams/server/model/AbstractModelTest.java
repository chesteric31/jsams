package be.jsams.server.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.CivilityBean;
import be.jsams.common.bean.model.ContactInformationBean;
import be.jsams.common.bean.model.LegalFormBean;
import be.jsams.common.bean.model.PaymentModeBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.management.ProductCategoryBean;
import be.jsams.server.model.management.Agent;
import be.jsams.server.model.management.Customer;
import be.jsams.server.model.management.Product;
import be.jsams.server.model.management.ProductCategory;

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
     * @param bean the {@link ProductBean} to use
     * @param product the {@link Product} to check
     */
    protected void checkProduct(ProductBean bean, Product product) {
        Double price = product.getPrice();
        assertTrue(price.equals(bean.getPrice()));
        String name = product.getName();
        assertEquals(name, bean.getName());
        ProductCategory category = product.getCategory();
        ProductCategoryBean categoryBean = bean.getCategory();
        checkProductCategory(categoryBean, category);
        int quantityStock = product.getQuantityStock();
        assertEquals(quantityStock, bean.getQuantityStock());
        int reorderLevel = product.getReorderLevel();
        assertEquals(reorderLevel, bean.getReorderLevel());
        Double vatApplicable = product.getVatApplicable();
        assertEquals(vatApplicable, bean.getVatApplicable());
        Long id = product.getId();
        assertEquals(id, bean.getId());
    }
    
    /**
     * Checks if the data are equals.
     * 
     * @param bean the {@link CustomerBean} to use
     * @param customer the {@link Customer} to check
     */
    protected void checkCustomer(CustomerBean bean, Customer customer) {
        String bank1 = customer.getBank1();
        assertTrue(bank1.equals(bean.getBank1()));
        String bank2 = customer.getBank2();
        assertTrue(bank2.equals(bean.getBank2()));
        String description = customer.getDescription();
        assertTrue(description.equals(bean.getDescription()));
        String firstName = customer.getFirstName();
        assertTrue(firstName.equals(bean.getFirstName()));
        String name = customer.getName();
        assertEquals(name, bean.getName());
        Double creditLimit = customer.getCreditLimit();
        assertEquals(creditLimit, bean.getCreditLimit());
        Double defaultDiscountRate = customer.getDefaultDiscountRate();
        assertEquals(defaultDiscountRate, bean.getDefaultDiscountRate());
        Double vatApplicable = customer.getVatApplicable();
        assertEquals(vatApplicable, bean.getVatApplicable());
        String vatNumber = customer.getVatNumber();
        assertEquals(vatNumber, bean.getVatNumber());
        Address billingAddress = customer.getBillingAddress();
        AddressBean billingAddressBean = bean.getBillingAddress();
        checkAddress(billingAddressBean, billingAddress);
        Address deliveryAddress = customer.getDeliveryAddress();
        AddressBean deliveryAddressBean = bean.getDeliveryAddress();
        checkAddress(deliveryAddressBean, deliveryAddress);
        ContactInformation contactInformation = customer.getContactInformation();
        ContactInformationBean contactInformationBean = bean.getContactInformation();
        checkContactInformation(contactInformationBean, contactInformation);
        LegalForm legalForm = customer.getLegalForm();
        checkLegalForm(bean.getLegalForm(), legalForm);
        PaymentMode paymentMode = customer.getPaymentMode();
        checkPaymentMode(bean.getPaymentMode(), paymentMode);
        Civility civility = customer.getCivility();
        CivilityBean civilityBean = bean.getCivility();
        checkCivility(civilityBean, civility);
        Society society = customer.getSociety();
        SocietyBean societyBean = bean.getSociety();
        checkSociety(societyBean, society);
        Long id = customer.getId();
        assertEquals(id, bean.getId());
    }

    /**
     * Checks if the data are equals.
     * 
     * @param bean the {@link AgentBean} to use
     * @param agent the {@link Agent} to check
     */
    protected void checkAgent(AgentBean bean, Agent agent) {
        String function = agent.getFunction();
        assertTrue(function.equals(bean.getFunction()));
        String name = agent.getName();
        assertEquals(name, bean.getName());
        Address address = agent.getAddress();
        AddressBean addressBean = bean.getAddress();
        checkAddress(addressBean, address);
        ContactInformation contactInformation = agent.getContactInformation();
        ContactInformationBean contactInformationBean = bean.getContactInformation();
        checkContactInformation(contactInformationBean, contactInformation);
        Civility civility = agent.getCivility();
        CivilityBean civilityBean = bean.getCivility();
        checkCivility(civilityBean, civility);
        Society society = agent.getSociety();
        SocietyBean societyBean = bean.getSociety();
        checkSociety(societyBean, society);
        Long id = agent.getId();
        assertEquals(id, bean.getId());
    }

    /**
     * Checks if the data are equals.
     * 
     * @param bean the {@link SocietyBean} to use
     * @param society the {@link Society} to check
     */
    protected void checkSociety(SocietyBean bean, Society society) {
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
     * @param bean the {@link AddressBean} to use
     * @param address the {@link Address} to check
     */
    protected void checkAddress(AddressBean bean, Address address) {
        String box = address.getBox();
        assertTrue(box.equals(bean.getBox()));
        String city = address.getCity();
        assertEquals(city, bean.getCity());
        String country = address.getCountry();
        assertEquals(country, bean.getCountry());
        String number = address.getNumber();
        assertEquals(number, bean.getNumber());
        String street = address.getStreet();
        assertEquals(street, bean.getStreet());
        String zipCode = address.getZipCode();
        assertEquals(zipCode, bean.getZipCode());
        Long id = address.getId();
        assertEquals(id, bean.getId());
    }

    /**
     * Checks if the data are equals.
     * 
     * @param bean the {@link ContactInformationBean} to use
     * @param contactInformation the {@link ContactInformation} to check
     */
    protected void checkContactInformation(ContactInformationBean bean, ContactInformation contactInformation) {
        String email = contactInformation.getEmail();
        assertTrue(email.equals(bean.getEmail()));
        String fax = contactInformation.getFax();
        assertEquals(fax, bean.getFax());
        String mobile = contactInformation.getMobile();
        assertEquals(mobile, bean.getMobile());
        String phone = contactInformation.getPhone();
        assertEquals(phone, bean.getPhone());
        String website = contactInformation.getWebsite();
        assertEquals(website, bean.getWebsite());
        Long id = contactInformation.getId();
        assertEquals(id, bean.getId());
    }

    /**
     * Checks if the data are equals.
     * 
     * @param bean the {@link LegalFormBean} to use
     * @param legalForm the {@link LegalForm} to check
     */
    protected void checkLegalForm(LegalFormBean bean, LegalForm legalForm) {
        String label = legalForm.getLabel();
        assertTrue(label.equals(bean.getLabel()));
        String labelFr = legalForm.getLabelFr();
        assertTrue(labelFr.equals(bean.getLabelFr()));
        String labelNl = legalForm.getLabelNl();
        assertTrue(labelNl.equals(bean.getLabelNl()));
        Long id = legalForm.getId();
        assertEquals(id, bean.getId());
    }

    /**
     * Checks if the data are equals.
     * 
     * @param bean the {@link CivilityBean} to use
     * @param civility the {@link Civility} to check
     */
    protected void checkCivility(CivilityBean bean, Civility civility) {
        String label = civility.getLabel();
        assertTrue(label.equals(bean.getLabel()));
        String labelFr = civility.getLabelFr();
        assertTrue(labelFr.equals(bean.getLabelFr()));
        String labelNl = civility.getLabelNl();
        assertTrue(labelNl.equals(bean.getLabelNl()));
        Long id = civility.getId();
        assertEquals(id, bean.getId());
    }

    /**
     * Checks if the data are equals.
     * 
     * @param bean the {@link PaymentModeBean} to use
     * @param paymentMode the {@link PaymentMode} to check
     */
    protected void checkPaymentMode(PaymentModeBean bean, PaymentMode paymentMode) {
        String label = paymentMode.getLabel();
        assertTrue(label.equals(bean.getLabel()));
        String labelFr = paymentMode.getLabelFr();
        assertTrue(labelFr.equals(bean.getLabelFr()));
        String labelNl = paymentMode.getLabelNl();
        assertTrue(labelNl.equals(bean.getLabelNl()));
        Long id = paymentMode.getId();
        assertEquals(id, bean.getId());
    }

    /**
     * Checks if the data are equals.
     * 
     * @param bean the {@link ProductCategoryBean} to use
     * @param productCategory the {@link ProductCategory} to check
     */
    protected void checkProductCategory(ProductCategoryBean bean, ProductCategory productCategory) {
        String label = productCategory.getLabel();
        assertTrue(label.equals(bean.getLabel()));
        String labelFr = productCategory.getLabelFr();
        assertTrue(labelFr.equals(bean.getLabelFr()));
        String labelNl = productCategory.getLabelNl();
        assertTrue(labelNl.equals(bean.getLabelNl()));
        Society society = productCategory.getSociety();
        checkSociety(bean.getSociety(), society);
        Long id = productCategory.getId();
        assertEquals(id, bean.getId());
    }

}
