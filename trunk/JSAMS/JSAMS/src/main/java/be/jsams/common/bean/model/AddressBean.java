package be.jsams.common.bean.model;

import be.jsams.common.bean.view.AddressBeanView;
import be.jsams.server.model.Address;

/**
 * 
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class AddressBean extends AbstractIdentityBean<Address, AddressBeanView> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 470647649997238833L;

    private String street;
    private String number;
    private String zipCode;
    private String box;
    private String city;
    private String country;

    public static final String STREET_PROPERTY = "street";
    public static final String NUMBER_PROPERTY = "number";
    public static final String ZIPCODE_PROPERTY = "zipCode";
    public static final String BOX_PROPERTY = "box";
    public static final String CITY_PROPERTY = "city";
    public static final String COUNTRY_PROPERTY = "country";

    /**
     * Default constructor
     */
    public AddressBean() {
        super();
    }

    /**
     * Constructor
     * 
     * @param model
     *            the {@link Address} to use
     */
    public AddressBean(Address model) {
        super(model);
        setBox(model.getBox());
        setCity(model.getCity());
        setCountry(model.getCountry());
        setNumber(model.getNumber());
        setStreet(model.getStreet());
        setZipCode(model.getZipCode());
    }

    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street
     *            the street to set
     */
    public void setStreet(String street) {
        String oldValue = this.street;
        this.street = street;
        firePropertyChange(STREET_PROPERTY, oldValue, this.street);
    }

    /**
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number
     *            the number to set
     */
    public void setNumber(String number) {
        String oldValue = this.number;
        this.number = number;
        firePropertyChange(NUMBER_PROPERTY, oldValue, this.number);
    }

    /**
     * @return the zipCode
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode
     *            the zipCode to set
     */
    public void setZipCode(String zipCode) {
        String oldValue = this.zipCode;
        this.zipCode = zipCode;
        firePropertyChange(ZIPCODE_PROPERTY, oldValue, this.zipCode);
    }

    /**
     * @return the box
     */
    public String getBox() {
        return box;
    }

    /**
     * @param box
     *            the box to set
     */
    public void setBox(String box) {
        String oldValue = this.box;
        this.box = box;
        firePropertyChange(BOX_PROPERTY, oldValue, this.box);
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     *            the city to set
     */
    public void setCity(String city) {
        String oldValue = this.city;
        this.city = city;
        firePropertyChange(CITY_PROPERTY, oldValue, this.city);
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country
     *            the country to set
     */
    public void setCountry(String country) {
        String oldValue = this.country;
        this.country = country;
        firePropertyChange(COUNTRY_PROPERTY, oldValue, this.country);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AddressBeanView getView() {
        return new AddressBeanView(this);
    }

    /**
     * {@inheritDoc}
     */
    public void clear() {
        setBox(null);
        setCity(null);
        setCountry(null);
        setNumber(null);
        setStreet(null);
        setZipCode(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AddressBean [box=");
        builder.append(box);
        builder.append(", city=");
        builder.append(city);
        builder.append(", country=");
        builder.append(country);
        builder.append(", number=");
        builder.append(number);
        builder.append(", street=");
        builder.append(street);
        builder.append(", zipCode=");
        builder.append(zipCode);
        builder.append("]");
        return builder.toString();
    }

}
