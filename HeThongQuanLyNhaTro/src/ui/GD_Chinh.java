package ui;

import javax.swing.JFrame;

public class GD_Chinh {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("Hệ Thống Quản Lý Ở Trọ Của Sinh Viên Trường Đại Học Công Nghiệp");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new GD_ThemTroMoiReal());
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}
