package ui;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class GD_Chinh {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Hệ thống quản lý ở trọ của sinh viên trường Đại học Công nghiệp");
		frame.getContentPane().add(new GD_DangNhap());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height - 20;
		int screenWidth = screenSize.width + 15;
		frame.setSize(screenWidth,screenHeight);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}

