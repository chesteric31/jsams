package be.jsams.server.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 
 * 
 * @author chesteric31
 * @version $Rev$ $Date:: $ $Author$
 */
@MappedSuperclass
public class AbstractTranslatableIdentity extends AbstractIdentity {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 5943662285794644039L;

	private String label;
	private String labelFr;
	private String labelNl;

	@Column(name = "LABEL")
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Column(name = "LABEL_FR")
	public String getLabelFr() {
		return labelFr;
	}

	public void setLabelFr(String labelFr) {
		this.labelFr = labelFr;
	}

	@Column(name = "LABEL_NL")
	public String getLabelNl() {
		return labelNl;
	}

	public void setLabelNl(String labelNl) {
		this.labelNl = labelNl;
	}

}
