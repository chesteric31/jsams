package be.jsams.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import be.jsams.common.bean.model.AddressBean;

/**
 * Address information entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "ADDRESS")
public class Address extends AbstractIdentity {

    private String street;
    private String number;
    private String zipCode;
    private String box;
    private String city;
    private String country;

    /**
     * Constructor.
     */
    public Address() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param bean
     *            the {@link AddressBean}
     */
    public Address(final AddressBean bean) {
        super(bean);
        setBox(bean.getBox());
        setCity(bean.getCity());
        setCountry(bean.getCountry());
        setNumber(bean.getNumber());
        setStreet(bean.getStreet());
        setZipCode(bean.getZipCode());
    }

    /**
     * 
     * @return the street
     */
    @Column(name = "STREET")
    public String getStreet() {
        return street;
    }

    /**
     * 
     * @param street
     *            the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * 
     * @return the house number
     */
    @Column(name = "NUMBER")
    public String getNumber() {
        return number;
    }

    /**
     * 
     * @param number
     *            the house number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * 
     * @return the zip code
     */
    @Column(name = "ZIP_CODE")
    public String getZipCode() {
        return zipCode;
    }

    /**
     * 
     * @param zipCode
     *            the zip code to set
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * 
     * @return the box
     */
    @Column(name = "BOX")
    public String getBox() {
        return box;
    }

    /**
     * 
     * @param box
     *            the box to set
     */
    public void setBox(String box) {
        this.box = box;
    }

    /**
     * 
     * @return the city
     */
    @Column(name = "CITY")
    public String getCity() {
        return city;
    }

    /**
     * 
     * @param city
     *            the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 
     * @return the country
     */
    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    /**
     * 
     * @param country
     *            the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Address [box=");
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
