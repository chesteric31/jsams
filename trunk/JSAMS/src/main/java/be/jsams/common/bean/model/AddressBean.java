package be.jsams.common.bean.model;

import be.jsams.common.bean.view.AddressBeanView;
import be.jsams.server.model.Address;

/**
 * Bean model for {@link Address} object.
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
    public static final String ZIP_CODE_PROPERTY = "zipCode";
    public static final String BOX_PROPERTY = "box";
    public static final String CITY_PROPERTY = "city";
    public static final String COUNTRY_PROPERTY = "country";

    /**
     * Default constructor
     */
    public AddressBean() {
        super();
        setView(buildView());
    }

    /**
     * Constructor
     * 
     * @param model the {@link Address} to use
     */
    public AddressBean(Address model) {
        super(model);
        this.box = model.getBox();
        this.city = model.getCity();
        this.country = model.getCountry();
        this.number = model.getNumber();
        this.street = model.getStreet();
        this.zipCode = model.getZipCode();
        setView(buildView());
    }

    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
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
     * @param number the number to set
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
     * @param zipCode the zipCode to set
     */
    public void setZipCode(String zipCode) {
        String oldValue = this.zipCode;
        this.zipCode = zipCode;
        firePropertyChange(ZIP_CODE_PROPERTY, oldValue, this.zipCode);
    }

    /**
     * @return the box
     */
    public String getBox() {
        return box;
    }

    /**
     * @param box the box to set
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
     * @param city the city to set
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
     * @param country the country to set
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
    public AddressBeanView buildView() {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh(AbstractIdentityBean<?, ?> bean) {
        if (bean instanceof AddressBean) {
            AddressBean other = (AddressBean) bean;
            setBox(other.getBox());
            setCity(other.getCity());
            setCountry(other.getCountry());
            setListModel(other.getListModel());
            setNumber(other.getNumber());
            setSelection(other.getSelection());
            setStreet(other.getStreet());
            setZipCode(other.getZipCode());
        } else {
            throw new RuntimeException("This bean cannot be refreshed.");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result;
        if (box == null) {
            result += 0;
        } else {
            result += box.hashCode();
        }
        if (city == null) {
            result += 0;
        } else {
            result += city.hashCode();
        }
        if (country == null) {
            result += 0;
        } else {
            result += country.hashCode();
        }
        if (number == null) {
            result += 0;
        } else {
            result += number.hashCode();
        }
        if (street == null) {
            result += 0;
        } else {
            result += street.hashCode();
        }
        if (zipCode == null) {
            result += 0;
        } else {
            result += zipCode.hashCode();
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof AddressBean)) {
            return false;
        }
        AddressBean other = (AddressBean) obj;
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
        if (zipCode == null) {
            if (other.zipCode != null) {
                return false;
            }
        } else if (!zipCode.equals(other.zipCode)) {
            return false;
        }
        return true;
    }

}
