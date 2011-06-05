package be.jsams.client.desktop;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Splash Panel to background of Jsams Main frame.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public final class JsamsSplashPanel extends JPanel {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -7495376076033558952L;

    private Image image;

    private static JsamsSplashPanel instance = null;
    
    private static Object classLock = JsamsSplashPanel.class;

    /**
     * Constructor
     */
    private JsamsSplashPanel() {
        this(new ImageIcon("src/main/resources/images/splash.png").getImage());
    }

    /**
     * Constructor
     * 
     * @param image the image to display
     */
    private JsamsSplashPanel(Image image) {
      this.image = image;
      Dimension size = JsamsMainFrame.CENTER;
//      Dimension size = new Dimension(image.getWidth(null), image.getHeight(null));
      setPreferredSize(size);
      setMinimumSize(size);
      setMaximumSize(size);
      setSize(size);
      setLayout(null);
    }
    
    /**
     * 
     * @return the instance of {@link JsamsSplashPanel}
     */
    public static JsamsSplashPanel getInstance() {
        synchronized (classLock) {
            if (instance == null) {
                instance = new JsamsSplashPanel();
            }
            return instance;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void paintComponent(Graphics g) {
        synchronized (classLock) {
            super.paintComponent(g);
            
            final float alpha = 0.3f;

            // Cast to Graphics2D so we can set composite information.
            Graphics2D g2d = (Graphics2D) g;

            // Save the original composite.
            Composite oldComp = g2d.getComposite();

            // Create an AlphaComposite with 50% translucency.
            Composite alphaComp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);

            // Set the composite on the Graphics2D object.
            g2d.setComposite(alphaComp);

            // Invoke arbitrary paint methods, which will paint
            // with 50% translucency.
//            int w = getWidth();
//            int h = getHeight();
//            int x = (w - image.getWidth(null)) / 2;
//            int y = (h - image.getHeight(null)) / 2;
//
//            g.drawImage(image, x, y, null);

            g2d.translate(this.getWidth() / 2, this.getHeight() / 2);
            g2d.translate(-image.getWidth(null) / 2, -image.getHeight(null) / 2);
            g2d.drawImage(image, 0, 0, null);

            g2d.setComposite(oldComp);
        }
    }

}
