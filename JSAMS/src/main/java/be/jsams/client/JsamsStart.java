package be.jsams.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import be.jsams.client.desktop.JsamsDesktop;

public class JsamsStart {

	/**
	 * Start the JSAMS application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		JsamsDesktop jsamsDesktop = new JsamsDesktop();
		jsamsDesktop.start();
	}

}
