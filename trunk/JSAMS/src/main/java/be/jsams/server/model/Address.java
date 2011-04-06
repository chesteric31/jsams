package be.jsams.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Address information entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "ADDRESS")
public class Address extends AbstractIdentity {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -2640100748737181266L;
    private String street;
    private String number;
    private int zipCode;
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
    public int getZipCode() {
        return zipCode;
    }

    /**
     * 
     * @param zipCode
     *            the zip code to set
     */
    public void setZipCode(int zipCode) {
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

    @Override
    public String toString() {
        return "Address [box=" + box + ", city=" + city + ", country=" + country + ", number=" + number + ", street="
                + street + ", zipCode=" + zipCode + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((box == null) ? 0 : box.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((number == null) ? 0 : number.hashCode());
        result = prime * result + ((street == null) ? 0 : street.hashCode());
        result = prime * result + zipCode;
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        // if (!super.equals(obj)) {
        // return false;
        // }
        if (!(obj instanceof Address)) {
            return false;
        }
        Address other = (Address) obj;
        if (box == null) {
            if (other.box != null) {
                return false;
            }
        } else if (!box.equals(other.box)) {
            return false;
        }
        if (city == null) {
            if (other.city != null) {
                return false;
            }
        } else if (!city.equals(other.city)) {
            return false;
        }
        if (country == null) {
            if (other.country != null) {
                return false;
            }
        } else if (!country.equals(other.country)) {
            return false;
        }
        if (number == null) {
            if (other.number != null) {
                return false;
            }
        } else if (!number.equals(other.number)) {
            return false;
        }
        if (street == null) {
            if (other.street != null) {
                return false;
            }
        } else if (!street.equals(other.street)) {
            return false;
        }
        if (zipCode != other.zipCode) {
            return false;
        }
        return true;
    }

}
