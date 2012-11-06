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
public final class ModelTestHelper {

    /**
     * Private constructor.
     */
    private ModelTestHelper() {
    }

    /**
     * Checks if the data are equals.
     * 
     * @param bean the {@link ProductBean} to use
     * @param product the {@link Product} to check
     */
    public static void checkProduct(ProductBean bean, Product product) {
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
    public static void checkCustomer(CustomerBean bean, Customer customer) {
        String bank1 = customer.getBank1();
        assertEquals(bean.getBank1(), bank1);
        String bank2 = customer.getBank2();
        assertEquals(bean.getBank2(), bank2);
        String description = customer.getDescription();
        assertEquals(bean.getDescription(), description);
        String firstName = customer.getFirstName();
        assertEquals(bean.getFirstName(), firstName);
        String name = customer.getName();
        assertEquals(bean.getName(), name);
        Double creditLimit = customer.getCreditLimit();
        assertEquals(bean.getCreditLimit(), creditLimit);
        Double defaultDiscountRate = customer.getDefaultDiscountRate();
        assertEquals(bean.getDefaultDiscountRate(), defaultDiscountRate);
        Double vatApplicable = customer.getVatApplicable();
        assertEquals(bean.getVatApplicable(), vatApplicable);
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
        assertEquals(bean.getId(), id);
    }

    /**
     * Checks if the data are equals.
     * 
     * @param bean the {@link AgentBean} to use
     * @param agent the {@link Agent} to check
     */
    public static void checkAgent(AgentBean bean, Agent agent) {
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
    public static void checkSociety(SocietyBean bean, Society society) {
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
    public static void checkAddress(AddressBean bean, Address address) {
        String box = address.getBox();
        assertEquals(bean.getBox(), box);
        String city = address.getCity();
        assertEquals(bean.getCity(), city);
        String country = address.getCountry();
        assertEquals(bean.getCountry(), country);
        String number = address.getNumber();
        assertEquals(bean.getNumber(), number);
        String street = address.getStreet();
        assertEquals(bean.getStreet(), street);
        String zipCode = address.getZipCode();
        assertEquals(bean.getZipCode(), zipCode);
        Long id = address.getId();
        assertEquals(bean.getId(), id);
    }

    /**
     * Checks if the data are equals.
     * 
     * @param bean the {@link ContactInformationBean} to use
     * @param contactInformation the {@link ContactInformation} to check
     */
    public static void checkContactInformation(ContactInformationBean bean, ContactInformation contactInformation) {
        String email = contactInformation.getEmail();
        assertEquals(bean.getEmail(), email);
        String fax = contactInformation.getFax();
        assertEquals(bean.getFax(), fax);
        String mobile = contactInformation.getMobile();
        assertEquals(bean.getMobile(), mobile);
        String phone = contactInformation.getPhone();
        assertEquals(bean.getPhone(), phone);
        String website = contactInformation.getWebsite();
        assertEquals(bean.getWebsite(), website);
        Long id = contactInformation.getId();
        assertEquals(bean.getId(), id);
    }

    /**
     * Checks if the data are equals.
     * 
     * @param bean the {@link LegalFormBean} to use
     * @param legalForm the {@link LegalForm} to check
     */
    public static void checkLegalForm(LegalFormBean bean, LegalForm legalForm) {
        if (bean != null && legalForm != null) {
            String label = legalForm.getLabel();
            assertTrue(label.equals(bean.getLabel()));
            String labelFr = legalForm.getLabelFr();
            assertTrue(labelFr.equals(bean.getLabelFr()));
            String labelNl = legalForm.getLabelNl();
            assertTrue(labelNl.equals(bean.getLabelNl()));
            Long id = legalForm.getId();
            assertEquals(id, bean.getId());
        }
    }

    /**
     * Checks if the data are equals.
     * 
     * @param bean the {@link CivilityBean} to use
     * @param civility the {@link Civility} to check
     */
    public static void checkCivility(CivilityBean bean, Civility civility) {
        String label = civility.getLabel();
        assertEquals(bean.getLabel(), label);
        String labelFr = civility.getLabelFr();
        assertEquals(bean.getLabelFr(), labelFr);
        String labelNl = civility.getLabelNl();
        assertEquals(bean.getLabelNl(), labelNl);
        Long id = civility.getId();
        assertEquals(bean.getId(), id);
    }

    /**
     * Checks if the data are equals.
     * 
     * @param bean the {@link PaymentModeBean} to use
     * @param paymentMode the {@link PaymentMode} to check
     */
    public static void checkPaymentMode(PaymentModeBean bean, PaymentMode paymentMode) {
        String label = paymentMode.getLabel();
        assertEquals(bean.getLabel(), label);
        String labelFr = paymentMode.getLabelFr();
        assertEquals(bean.getLabelFr(), labelFr);
        String labelNl = paymentMode.getLabelNl();
        assertEquals(bean.getLabelNl(), labelNl);
        Long id = paymentMode.getId();
        assertEquals(bean.getId(), id);
    }

    /**
     * Checks if the data are equals.
     * 
     * @param bean the {@link ProductCategoryBean} to use
     * @param productCategory the {@link ProductCategory} to check
     */
    public static void checkProductCategory(ProductCategoryBean bean, ProductCategory productCategory) {
        String label = productCategory.getLabel();
        assertEquals(bean.getLabel(), label);
        String labelFr = productCategory.getLabelFr();
        assertEquals(bean.getLabelFr(), labelFr);
        String labelNl = productCategory.getLabelNl();
        assertEquals(bean.getLabelNl(), labelNl);
        Society society = productCategory.getSociety();
        checkSociety(bean.getSociety(), society);
        Long id = productCategory.getId();
        assertEquals(bean.getId(), id);
    }

}
