package be.jsams.client.swing.action;

import java.awt.event.ActionEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;

import javax.swing.AbstractAction;
import javax.swing.Icon;

/**
 * Action to show the printer parameters.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class PrinterParametersAction extends AbstractAction {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 278225078264399160L;

    /**
     * Constructor
     *  
     * @param text the action text
     * @param icon the icon to display
     */
    public PrinterParametersAction(String text, Icon icon) {
        super(text, icon);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        PrinterJob pjob = PrinterJob.getPrinterJob();
        PageFormat pf = pjob.defaultPage();
        pjob.setPrintable(null, pf);
    }

}
