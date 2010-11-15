package be.jsams.client.swing.utils;

import java.awt.Image;
import java.awt.Toolkit;

public class IconUtil {
	
	public static Image buildIcon(final String fileName) {
		return Toolkit.getDefaultToolkit().getImage(
				IconUtil.class.getClassLoader().getResource(fileName));
	}

}
