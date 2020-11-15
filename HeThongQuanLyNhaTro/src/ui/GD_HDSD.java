package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class GD_HDSD extends JPanel{


	
	
	public GD_HDSD() {
		setAutoscrolls(true);
		setBorder(null);
		this.setPreferredSize(new Dimension(1400, 350));
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnlRight = new JPanel();
		pnlRight.setMinimumSize(new Dimension(1000, 10));
		pnlRight.setPreferredSize(new Dimension(100, 10));
		add(pnlRight, BorderLayout.CENTER);
		pnlRight.setLayout(new BorderLayout(0, 0));
	
		
		JTextArea txaHDSD = new JTextArea("Phần mềm quản lý sinh viên ở trọ nhằm đáp ứng yêu cầu quản ý tất cả các thông tin của sinh viên về nơi tạm trú trong quá trình học tập tại trường Đại học Công Nghiệp Thành Phố Hồ Chí Minh và cung cấp cho"
				+ "\n" +"bộ phận giáo vụ khoa nhà trường để sử dụng quản lý việc ở trọ của sinh viên.");
		txaHDSD.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlRight.add(txaHDSD);
		
		
	}
}
