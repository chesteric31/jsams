package be.jsams.server.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Society entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "SOCIETY")
public class Society extends AbstractNamedIdentity {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -8541820243148400802L;
    private BigDecimal capital;
    private String activity;
    private String responsible;
    private String vatNumber;

    private Address address;
    private LegalForm legalForm;
    private ContactInformation contactInformation;

    /**
     * Constructor
     */
    public Society() {
        super();
    }

    /**
     * 
     * @return the capital
     */
    @Column(name = "CAPITAL")
    public BigDecimal getCapital() {
        return capital;
    }

    /**
     * 
     * @param capital
     *            the capital to set
     */
    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    /**
     * 
     * @return the activity
     */
    @Column(name = "ACTIVITY")
    public String getActivity() {
        return activity;
    }

    /**
     * 
     * @param activity
     *            the activity to set
     */
    public void setActivity(String activity) {
        this.activity = activity;
    }

    /**
     * 
     * @return the responsible
     */
    @Column(name = "RESPONSIBLE")
    public String getResponsible() {
        return responsible;
    }

    /**
     * 
     * @param responsible
     *            the responsible to set
     */
    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    /**
     * 
     * @return the VAT number
     */
    @Column(name = "VAT_NUMBER")
    public String getVatNumber() {
        return vatNumber;
    }

    /**
     * 
     * @param vatNumber
     *            the VAT number to set
     */
    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    /**
     * 
     * @return the {@link Address}
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_ADDRESS")
    public Address getAddress() {
        return address;
    }

    /**
     * 
     * @param address
     *            the {@link Address} to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * 
     * @return the {@link LegalForm}
     */
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "FK_LEGAL_FORM")
    public LegalForm getLegalForm() {
        return legalForm;
    }

    /**
     * 
     * @param legalForm
     *            the {@link LegalForm} to set
     */
    public void setLegalForm(LegalForm legalForm) {
        this.legalForm = legalForm;
    }

    /**
     * 
     * @return the {@link ContactInformation}
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_CONTACT_INFORMATION")
    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    /**
     * 
     * @param contactInformation
     *            the {@link ContactInformation} to set
     */
    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    @Override
    public String toString() {
        return "Society [activity=" + activity + ", address=" + address + ", capital=" + capital
                + ", contactInformation=" + contactInformation + ", name=" + getName() + ", legalForm=" + legalForm
                + ", responsible=" + responsible + ", vatNumber=" + vatNumber + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((activity == null) ? 0 : activity.hashCode());
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((capital == null) ? 0 : capital.hashCode());
        result = prime * result + ((contactInformation == null) ? 0 : contactInformation.hashCode());
        result = prime * result + ((legalForm == null) ? 0 : legalForm.hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((responsible == null) ? 0 : responsible.hashCode());
        result = prime * result + ((vatNumber == null) ? 0 : vatNumber.hashCode());
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
        if (!(obj instanceof Society)) {
            return false;
        }
        Society other = (Society) obj;
        if (activity == null) {
            if (other.activity != null) {
                return false;
            }
        } else if (!activity.equals(other.activity)) {
            return false;
        }
        if (address == null) {
            if (other.address != null) {
                return false;
            }
        } else if (!address.equals(other.address)) {
            return false;
        }
        if (capital == null) {
            if (other.capital != null) {
                return false;
            }
        } else if (!capital.equals(other.capital)) {
            return false;
        }
        if (contactInformation == null) {
            if (other.contactInformation != null) {
                return false;
            }
        } else if (!contactInformation.equals(other.contactInformation)) {
            return false;
        }
        if (legalForm == null) {
            if (other.legalForm != null) {
                return false;
            }
        } else if (!legalForm.equals(other.legalForm)) {
            return false;
        }
        if (getName() == null) {
            if (other.getName() != null) {
                return false;
            }
        } else if (!getName().equals(other.getName())) {
            return false;
        }
        if (responsible == null) {
            if (other.responsible != null) {
                return false;
            }
        } else if (!responsible.equals(other.responsible)) {
            return false;
        }
        if (vatNumber == null) {
            if (other.vatNumber != null) {
                return false;
            }
        } else if (!vatNumber.equals(other.vatNumber)) {
            return false;
        }
        return true;
    }

}
