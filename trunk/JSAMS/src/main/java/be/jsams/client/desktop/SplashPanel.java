package be.jsams.client.desktop;

import java.awt.AlphaComposite;
import java.awt.Composite;
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
public final class SplashPanel extends JPanel {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -7495376076033558952L;

    private Image image;

    private static SplashPanel instance = null;

    private static Object classLock = SplashPanel.class;

    /**
     * Constructor.
     */
    private SplashPanel() {
        this(new ImageIcon(SplashPanel.class.getResource("/images/splash.png")).getImage());
    }

    /**
     * Constructor.
     * 
     * @param image the image to display
     */
    private SplashPanel(Image image) {
        super();
        this.image = image;
        setLayout(null);
    }

    /**
     * @return the instance of {@link SplashPanel}
     */
    public static SplashPanel getInstance() {
        synchronized (classLock) {
            if (instance == null) {
                instance = new SplashPanel();
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

            g2d.translate(this.getWidth() / 2, this.getHeight() / 2);
            g2d.translate(-image.getWidth(null) / 2, -image.getHeight(null) / 2);
            g2d.drawImage(image, 0, 0, null);

            g2d.setComposite(oldComp);
        }
    }

    /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }

}
