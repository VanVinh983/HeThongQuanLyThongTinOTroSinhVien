package ui;

import javax.swing.JFrame;

public class GD_Chinh {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new GD_BangThongTin());
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}
