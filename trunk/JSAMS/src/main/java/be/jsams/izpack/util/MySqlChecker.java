package be.jsams.izpack.util;

import javax.swing.JOptionPane;

/**
 * 
 * 
 * @author chesteric31
 * @version $Revision$ $Date:: $ $Author$
 */
public class MySqlChecker {

    public static final boolean IS_INSTALLED = getMySqlInstall();

    static boolean getMySqlInstall() {
        int n = JOptionPane.showConfirmDialog(null, "Would you like green eggs and ham?", "An Inane Question",
                JOptionPane.YES_NO_OPTION);
        return n == 0;
    }

}
