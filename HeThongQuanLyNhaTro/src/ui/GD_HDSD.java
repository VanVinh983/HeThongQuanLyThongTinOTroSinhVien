package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GD_HDSD  extends JFrame{
	private JLabel lblTitle;
	
	
	public GD_HDSD() {
		
		setBackground(Color.MAGENTA);
		JPanel pNorth = new JPanel();
		pNorth.add(lblTitle = new JLabel("HƯỚNG DẪN SỬ DỤNG"));
		lblTitle.setForeground(Color.black);
		Font font = new Font("Arias", Font.BOLD, 30);
		lblTitle.setFont(font);
		add(pNorth, BorderLayout.NORTH);
		
		Box b= Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		b.add(b1);
		add(b,BorderLayout.CENTER);
		
		JLabel lblMaTro;
		JTextField txtMaTro;
		b1.add(txtMaTro = new  JTextField("oseph Robinette Biden, Jr. (còn gọi"
				+ " là Joe Biden; /ˌrɒbɪˈnɛt ˈbaɪdən/[2]; sinh ngày 20 tháng "
				+ "11 năm 1942) là Tổng thống tân cử của Hoa Kỳ. Sau khi đán"
				+ "h bại tổng thống đương nhiệm Donald Trump trong cuộc bầu cử tổ"
				+ "ng thống năm 2020, ông sẽ nhậm chức trở thành tổng thống thứ 46"
				+ " vào tháng 1 năm 2021. Là thành viên của Đảng Dân chủ, ông từng"
				+ " là Phó Tổng thống thứ 47 của Hoa Kỳ từ năm 2009 đến năm 2017. Ô"
				+ "ng cũng từng là Thượng nghị sĩ Hoa Kỳ đại diện tiểu bang Dela"
				+ "ware từ năm 1973 đến năm 2009."));
		
		
		
		
		
		setSize(1000, 6000);
		setVisible(true);
		
	
	
		
		setLocationRelativeTo(null);
		
}
}
