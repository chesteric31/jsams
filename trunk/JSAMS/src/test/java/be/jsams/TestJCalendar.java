package be.jsams;

import javax.swing.JFrame;

import com.toedter.calendar.JDateChooser;

/**
 * 
 * 
 * @author chesteric31
 * @version $Rev$ $Date::   $Author$
 */
public class TestJCalendar {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JDateChooser chooser = new JDateChooser();
        frame.add(chooser);
//        JsamsPeriodField periodField = new JsamsPeriodField();
//        frame.add(periodField);
        frame.pack();
        frame.setVisible(true);
    }
}
