package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Label;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class GD_DangNhap extends JPanel{


	private JLabel lblmaNV;
	private JLabel lbltitle;
	private JTextField txtmaNV;
	private JLabel lblmatkhau;
	private JTextField txtmatkhau;
	private JButton btnDangNhap;

	public GD_DangNhap() {
		this.setLayout(new BorderLayout());
		
		this.setPreferredSize(new Dimension(1200,700));
		JPanel pnltop = new JPanel();
		
		pnltop.add(Box.createVerticalStrut(100));
		JPanel pnlbottom = new JPanel();
		pnlbottom.add(Box.createVerticalStrut(100));
		
		JPanel pnlleft = new JPanel();
		pnlleft.add(Box.createHorizontalStrut(300));
		
		JPanel pnlright = new JPanel();
		pnlright.add(Box.createHorizontalStrut(300));
		
		JPanel pnlcenter = new JPanel();
		pnlcenter.setLayout(new BoxLayout(pnlcenter, BoxLayout.Y_AXIS));
		JPanel pnltitle = new JPanel();
		pnltitle.add(lbltitle = new JLabel("ĐĂNG NHẬP"));
		JPanel pnlmaNV = new JPanel();
		pnlmaNV.add(lblmaNV = new JLabel("Mã Nhân Viên: "));
		pnlmaNV.add(txtmaNV = new JTextField(20));
		JPanel pnlmatKhau = new JPanel();
		pnlmatKhau.add(lblmatkhau = new JLabel("Mật Khẩu: "));
		pnlmatKhau.add(txtmatkhau = new JTextField(20));
		JPanel pnlDangNhap= new JPanel();
		pnlDangNhap.add(btnDangNhap = new JButton("Đăng Nhập"));
		
		pnlcenter.add(pnltitle);
		pnlcenter.add(Box.createVerticalStrut(20));
		pnlcenter.add(pnlmaNV);
		pnlcenter.add(Box.createVerticalStrut(20));
		pnlcenter.add(pnlmatKhau);
		pnlcenter.add(Box.createVerticalStrut(20));
		pnlcenter.add(pnlDangNhap);
		Border border = BorderFactory.createLineBorder(Color.BLUE);
		pnlcenter.setBorder(border);
		
		
		
		this.add(pnlleft,BorderLayout.WEST);
		this.add(pnlright,BorderLayout.EAST);
		this.add(pnlbottom,BorderLayout.SOUTH);
		this.add(pnltop,BorderLayout.NORTH);
		this.add(pnlcenter,BorderLayout.CENTER);
		
	}
}
