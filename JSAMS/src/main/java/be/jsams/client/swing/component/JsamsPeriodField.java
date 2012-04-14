package be.jsams.client.swing.component;

import java.util.Date;

import javax.swing.JPanel;

import be.jsams.client.i18n.JsamsI18nLabelResource;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.toedter.calendar.JDateChooser;

/**
 * {@link JPanel} to represent a period with two {@link JDateChooser}s.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsPeriodField extends JPanel {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1545435554488761050L;
    
    private Date startDate = new Date();
    
    private Date endDate = new Date();
    
    private JDateChooser startDateChooser;
    
    private JDateChooser endDateChooser;
    
    /**
     * Constructor.
     */
    public JsamsPeriodField() {
        super();
        startDateChooser = new JDateChooser(startDate);
        endDateChooser = new JDateChooser(endDate);
        FormLayout layout = new FormLayout("right:p, 3dlu, 75dlu, 3dlu, right:p, 3dlu, p:grow", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        builder.setDefaultDialogBorder();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_START_DATE.getKey(), startDateChooser);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_END_DATE.getKey(), endDateChooser);
        add(builder.getPanel());
    }

    /**
     * @return the startDateChooser
     */
    public JDateChooser getStartDateChooser() {
        return startDateChooser;
    }

    /**
     * @return the endDateChooser
     */
    public JDateChooser getEndDateChooser() {
        return endDateChooser;
    }

}
