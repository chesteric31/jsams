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

	public Address() {
		super();
	}

	@Column(name = "STREET")
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Column(name = "NUMBER")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Column(name = "ZIP_CODE")
	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	@Column(name = "BOX")
	public String getBox() {
		return box;
	}

	public void setBox(String box) {
		this.box = box;
	}

	@Column(name = "CITY")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "COUNTRY")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Address [box=" + box + ", city=" + city + ", country="
				+ country + ", number=" + number + ", street=" + street
				+ ", zipCode=" + zipCode + "]";
	}

}
