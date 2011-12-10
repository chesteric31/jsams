import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main extends JFrame {
    Main() {
        setTitle("IMAGES");
        setSize(800, 500);
        pan = new Panel();
        getContentPane().add(pan);
    }

    private JPanel pan;
}

class Panel extends JPanel {

    private JLabel photo;
    private JButton browse;

    public Panel() {
        super();
        photo = new JLabel();
        browse = new JButton("Browse");
        browse.addActionListener(new ActionListener() {
            
            /**
             * {@inheritDoc}
             */
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser chooser = new JFileChooser();
                int returnValue = chooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    System.out.println("Opening: " + file.getName());
                    photo.setIcon(new ImageIcon(file.getAbsolutePath()));
                }
            }
        });
        this.add(photo);
        this.add(browse);
        // photo = getToolkit().getImage("/home/chesteric31/Images/BTS.png");
    }

    /**
     * 
     * @return
     */
    public JLabel getPhoto() {
        return this.photo;
    }

    /**
     * 
     * @param photo
     */
    public void setPhoto(JLabel photo) {
        this.photo = photo;
    }

    //
    // public void paintComponent(Graphics g) {
    // super.paintComponent(g);
    // int x = 10, y = 10;
    // if (photo != null) {
    // g.drawImage(photo, x, y, this);
    // System.out.println("photo");
    // }
    // }
    //
    // private Image photo = null;

    /**
     * @return the browse
     */
    public JButton getBrowse() {
        return browse;
    }

    /**
     * @param browse the browse to set
     */
    public void setBrowse(JButton browse) {
        this.browse = browse;
    }
}

class TestMenu {
    public static void main(String args[]) {
        Main fen = new Main();
        fen.setVisible(true);
    }
}
