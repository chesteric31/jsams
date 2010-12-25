package be.jsams.client.model.panel;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Search {@link JPanel} for Customer objects.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SearchCustomerPanel extends JPanel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 2222078506888522042L;
	
	public SearchCustomerPanel() {
		super();
		initComponents();
	}
	
	private void initComponents() {
		JLabel label = new JLabel("customer");
		add(label);
	}

}
