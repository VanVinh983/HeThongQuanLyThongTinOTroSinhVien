package ui;

import javax.swing.JFrame;

public class GD_Chinh {
	public static JFrame frame;
	public static void main(String[] args) {
		 frame = new JFrame();
		frame.setTitle("Hệ Thống Quản Lý Ở Trọ Của Sinh Viên Trường Đại Học Công Nghiệp");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new GD_DangNhap());
		frame.pack();
		//frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}

