package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class GD_DangNhap extends JPanel implements ActionListener{


	private JLabel lblmaNV;
	private JLabel lbltitle;
	private JTextField txtmaNV;
	private JLabel lblmatkhau;
	private JTextField txtmatkhau;
	private JButton btnDangNhap;
	private JButton btnXemMatKhau;

	public GD_DangNhap() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(800,600));
		JPanel pnltop = new JPanel();
		pnltop.setBackground(new Color(0, 102, 255));
		
		
		pnltop.add(Box.createVerticalStrut(100));
		JPanel pnlbottom = new JPanel();
		pnlbottom.add(Box.createVerticalStrut(100));
		pnlbottom.setBackground(new Color(0, 102, 255));
		
		JPanel pnlleft = new JPanel();
		pnlleft.add(Box.createHorizontalStrut(10));
		pnlleft.setBackground(new Color(0, 102, 255));
		
		JPanel pnlright = new JPanel();
		pnlright.setBackground(new Color(0, 102, 255));
		pnlright.add(Box.createHorizontalStrut(10));
		
		JPanel pnlcenter = new JPanel();
		pnlcenter.setLayout(new BoxLayout(pnlcenter, BoxLayout.Y_AXIS));
		JPanel pnltitle = new JPanel();
		pnltitle.setBackground(Color.WHITE);
		lbltitle = new JLabel("ĐĂNG NHẬP");
		lbltitle.setFont(new Font("arial", 1, 40));
		lbltitle.setForeground(new Color(0, 102, 255));
		pnltitle.add(lbltitle);
		JPanel pnlmaNV = new JPanel();
		pnlmaNV.setBackground(Color.WHITE);
		lblmaNV = new JLabel("Mã Nhân Viên: ");
		lblmaNV.setFont(new Font("arial", 1, 25));
		lblmaNV.setForeground(new Color(0, 102, 255));
		pnlmaNV.add(lblmaNV);
		txtmaNV = new JTextField(20);
		txtmaNV.setPreferredSize(new Dimension(20, 50));
		txtmaNV.setFont(new Font("arial", 1	, 25));
		pnlmaNV.add(txtmaNV);
		pnlmaNV.add(Box.createHorizontalStrut(70));
		
		JPanel pnlmatKhau = new JPanel();
		pnlmatKhau.setBackground(Color.WHITE);
		lblmatkhau = new JLabel("Mật Khẩu: ");
		lblmatkhau.setFont(new Font("arial", 1, 25));
		lblmatkhau.setForeground(new Color(0, 102, 255));
		lblmatkhau.setPreferredSize(lblmaNV.getPreferredSize());
		pnlmatKhau.add(lblmatkhau);
		txtmatkhau = new JTextField(20);
		txtmatkhau.setPreferredSize(new Dimension(20, 50));
		txtmatkhau.setFont(new Font("arial", 1, 25));
		pnlmatKhau.add(txtmatkhau);
		
		btnXemMatKhau = new JButton("xem");
		btnXemMatKhau.setPreferredSize(new Dimension(70, 50));
		pnlmatKhau.add(btnXemMatKhau);
		
		
		JPanel pnlDangNhap= new JPanel();
		pnlDangNhap.setBackground(Color.WHITE);
		btnDangNhap = new JButton("Đăng Nhập");
		btnDangNhap.setPreferredSize(new Dimension(350, 50));
		btnDangNhap.setFont(new Font("arial", 1, 25));
		btnDangNhap.setBackground(new Color(255, 102, 0));
		
		pnlDangNhap.add(btnDangNhap);
		
		pnlcenter.add(pnltitle);
		pnlcenter.add(Box.createVerticalStrut(20));
		pnlcenter.add(pnlmaNV);
		pnlcenter.add(Box.createVerticalStrut(20));
		pnlcenter.add(pnlmatKhau);
		pnlcenter.add(Box.createVerticalStrut(20));
		pnlcenter.add(pnlDangNhap);
		Border border = BorderFactory.createLineBorder(new Color(0, 102, 255));
		pnlcenter.setBorder(border);
		
		
		
		this.add(pnlleft,BorderLayout.WEST);
		this.add(pnlright,BorderLayout.EAST);
		this.add(pnlbottom,BorderLayout.SOUTH);
		this.add(pnltop,BorderLayout.NORTH);
		pnlcenter.setBackground(Color.WHITE);
		this.add(pnlcenter,BorderLayout.CENTER);
		
		txtmaNV.addActionListener(this);
		txtmatkhau.addActionListener(this);
		btnDangNhap.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		
	}
}
