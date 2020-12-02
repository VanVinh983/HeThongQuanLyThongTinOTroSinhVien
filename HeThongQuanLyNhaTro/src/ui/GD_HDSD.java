package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.io.File;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class GD_HDSD extends JPanel {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	public GD_HDSD() {
		File file = new File("File\\File Help.chm");
		Desktop dsDesktop = Desktop.getDesktop();
		if (file.exists()) {
			try {
				dsDesktop.open(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
