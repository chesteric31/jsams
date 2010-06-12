package be.jsams.client.gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainFrame extends JFrame {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -5033009067939626719L;

	private static final String title = "BTS - Concept";

	private String text;

	public MainFrame() {
		super(title);
		initForm();
	}

	private void initForm() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Center
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void setText(String text) {
		this.text = text;
	}

	public void paintText() {
		final JTextArea textArea = new JTextArea(text);
		this.getContentPane().add(textArea, BorderLayout.NORTH);
		this.getContentPane().add(new JButton("button"), BorderLayout.CENTER);
		this.getContentPane().add(new JLabel("toto"), BorderLayout.SOUTH);
		this.getContentPane().repaint();
		this.pack();
	}

}
