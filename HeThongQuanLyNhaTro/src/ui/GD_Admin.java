package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class GD_Admin extends JPanel{

	private JLabel lblTieuDe;
	private JButton btnSinhVien;
	private JButton btnNhanVien;
	private JButton btnThongKe;
	private JButton btnTro;
	private JButton btnBangThongTin;
	private JButton btnDangXuat;

	public GD_Admin() {
		this.setPreferredSize(new Dimension(1200, 600));
		this.setLayout(new BorderLayout());
		
		lblTieuDe = new JLabel("Quản Lý");
		
		btnSinhVien = new JButton("Sinh Viên");
		btnNhanVien = new JButton("Nhân Viên");
		btnThongKe = new JButton("Thống Kê");
		btnTro = new JButton("Trọ");
		btnBangThongTin = new JButton("Bảng Thông Tin Thuê Trọ");
		btnDangXuat = new JButton("Đăng Xuất");
		
		
		btnBangThongTin.setPreferredSize(new Dimension(230, 400));
		btnNhanVien.setPreferredSize(new Dimension(230, 400));
		btnSinhVien.setPreferredSize(new Dimension(230, 400));
		btnThongKe.setPreferredSize(new Dimension(230, 400));
		btnTro.setPreferredSize(new Dimension(230, 400));
		btnDangXuat.setPreferredSize(new Dimension(500, 70));
		
		
		lblTieuDe.setFont(new Font("arial", 1, 50));
		lblTieuDe.setForeground(Color.white);
		
		
		JPanel pnlTitle = new JPanel();
		JPanel pnlBottom = new JPanel();
		JPanel pnlCenter = new JPanel();
		
		
		
		pnlTitle.setPreferredSize(new Dimension(1200, 100));
		pnlTitle.setBackground(new Color(237, 125, 49));
		pnlTitle.add(lblTieuDe);
		
		
		pnlCenter.add(btnBangThongTin);
		pnlCenter.add(btnNhanVien);
		pnlCenter.add(btnSinhVien);
		pnlCenter.add(btnTro);
		pnlCenter.add(btnThongKe);
		
		pnlBottom.add(btnDangXuat);
		
		this.add(pnlTitle,BorderLayout.NORTH);
		this.add(pnlCenter,BorderLayout.CENTER);
		this.add(pnlBottom,BorderLayout.SOUTH);
	}
	
	
}
