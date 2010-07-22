package be.jsams.client;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;

public class Main extends JFrame {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 2821934927228486957L;
	private JMenuItem helpMenuItem;
	private JMenu helpMenu;
	private JMenuItem deleteMenuItem;
	private JSeparator jSeparator1;
	private JMenuItem pasteMenuItem;
	private JMenuItem copyMenuItem;
	private JMenuItem cutMenuItem;
	private JMenu editMenu;
	private JMenuItem exitMenuItem;
	private JSeparator jSeparator2;
	private JMenuItem closeFileMenuItem;
	private JMenuItem saveAsMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem openFileMenuItem;
	private JMenuItem newFileMenuItem;
	private JMenu fileMenu;
	private JMenuBar menuBar;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Main main = new Main();
				main.setLocationRelativeTo(null);
				main.setVisible(true);
			}
		});
	}

	public Main() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			setSize(400, 300);
			menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			fileMenu = new JMenu();
			menuBar.add(fileMenu);
			fileMenu.setText("File");
			newFileMenuItem = new JMenuItem();
			fileMenu.add(newFileMenuItem);
			newFileMenuItem.setText("New");
			openFileMenuItem = new JMenuItem();
			fileMenu.add(openFileMenuItem);
			openFileMenuItem.setText("Open");
			saveMenuItem = new JMenuItem();
			fileMenu.add(saveMenuItem);
			saveMenuItem.setText("Save");
			saveAsMenuItem = new JMenuItem();
			fileMenu.add(saveAsMenuItem);
			saveAsMenuItem.setText("Save As ...");
			closeFileMenuItem = new JMenuItem();
			fileMenu.add(closeFileMenuItem);
			closeFileMenuItem.setText("Close");
			jSeparator2 = new JSeparator();
			fileMenu.add(jSeparator2);
			exitMenuItem = new JMenuItem();
			fileMenu.add(exitMenuItem);
			exitMenuItem.setText("Exit");
			editMenu = new JMenu();
			menuBar.add(editMenu);
			editMenu.setText("Edit");
			cutMenuItem = new JMenuItem();
			editMenu.add(cutMenuItem);
			cutMenuItem.setText("Cut");
			copyMenuItem = new JMenuItem();
			editMenu.add(copyMenuItem);
			copyMenuItem.setText("Copy");
			pasteMenuItem = new JMenuItem();
			editMenu.add(pasteMenuItem);
			pasteMenuItem.setText("Paste");
			jSeparator1 = new JSeparator();
			editMenu.add(jSeparator1);
			deleteMenuItem = new JMenuItem();
			editMenu.add(deleteMenuItem);
			deleteMenuItem.setText("Delete");
			helpMenu = new JMenu();
			menuBar.add(helpMenu);
			helpMenu.setText("Help");
			helpMenuItem = new JMenuItem();
			helpMenu.add(helpMenuItem);
			helpMenuItem.setText("Help");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
