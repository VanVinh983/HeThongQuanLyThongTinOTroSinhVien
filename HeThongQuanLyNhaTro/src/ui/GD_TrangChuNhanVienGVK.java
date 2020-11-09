package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GD_TrangChuNhanVienGVK extends JPanel{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private JLabel lblTieuDe;
		private JButton btnSinhVien;
		private JButton btnThongKe;
		private JButton btnTro;
		private JButton btnBangThongTin;
		private JButton btnDangXuat;
		private JLabel lblSinhVien;
		private JLabel lblThongKe;
		private JLabel lblTro;
		private JLabel lblBangThongTin;

		public GD_TrangChuNhanVienGVK() {
			this.setPreferredSize(new Dimension(1200, 600));
			this.setLayout(new BorderLayout());
			
			ImageIcon imgGhiChu =  new ImageIcon(new ImageIcon("HinhAnh/ghichu1.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
			ImageIcon imgThongKe = new ImageIcon(new ImageIcon("HinhAnh/thongke.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
			ImageIcon imgNhaTro =  new ImageIcon(new ImageIcon("HinhAnh/nhatro.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
			ImageIcon imgSinhVien =  new ImageIcon(new ImageIcon("HinhAnh/sinhvien.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
			
			lblTieuDe = new JLabel("Quản Lý");
			lblSinhVien = new JLabel("        Sinh Viên");
			lblThongKe = new JLabel("         Thống Kê");
			lblBangThongTin = new JLabel("    Bảng Thông Tin");
			lblTro = new JLabel("              Trọ");
			
			lblSinhVien.setFont(new Font("arial", 1, 20));
			lblTro.setFont(new Font("arial", 1, 20));
			lblBangThongTin.setFont(new Font("arial", 1, 20));
			lblThongKe.setFont(new Font("arial", 1, 20));
			
			btnSinhVien = new JButton(imgSinhVien);
			btnThongKe = new JButton(imgThongKe);
			btnTro = new JButton(imgNhaTro);
			btnBangThongTin = new JButton(imgGhiChu);
			btnDangXuat = new JButton("Đăng Xuất");
			btnDangXuat.setFont(new Font("arial",1,20));
			btnDangXuat.setBackground(Color.RED);
			btnDangXuat.setForeground(Color.white);
			
			btnSinhVien.setLayout(new BorderLayout());
			btnSinhVien.add(lblSinhVien,BorderLayout.NORTH);
			
			
			btnThongKe.setLayout(new BorderLayout());
			btnThongKe.add(lblThongKe,BorderLayout.NORTH);
			
			btnTro.setLayout(new BorderLayout());
			btnTro.add(lblTro,BorderLayout.NORTH);
			
			btnBangThongTin.setLayout(new BorderLayout());
			btnBangThongTin.add(lblBangThongTin,BorderLayout.NORTH);
			
			btnBangThongTin.setPreferredSize(new Dimension(280, 400));
			btnSinhVien.setPreferredSize(new Dimension(280, 400));
			btnThongKe.setPreferredSize(new Dimension(280, 400));
			btnTro.setPreferredSize(new Dimension(280, 400));
			btnDangXuat.setPreferredSize(new Dimension(500, 70));
			
			
			btnBangThongTin.setFocusPainted(false);
			btnSinhVien.setFocusPainted(false);
			btnThongKe.setFocusPainted(false);
			btnTro.setFocusPainted(false);
			
			
			
			lblTieuDe.setFont(new Font("arial", 1, 50));
			lblTieuDe.setForeground(Color.white);
			
			
			JPanel pnlTitle = new JPanel();
			JPanel pnlBottom = new JPanel();
			JPanel pnlCenter = new JPanel();
			
			
			
			pnlTitle.setPreferredSize(new Dimension(1200, 100));
			pnlTitle.setBackground(new Color(237, 125, 49));
			pnlTitle.add(lblTieuDe);
			
			
			pnlCenter.add(btnBangThongTin);
			pnlCenter.add(btnSinhVien);
			pnlCenter.add(btnTro);
			pnlCenter.add(btnThongKe);
			
			pnlBottom.add(btnDangXuat);
			
			this.add(pnlTitle,BorderLayout.NORTH);
			this.add(pnlCenter,BorderLayout.CENTER);
			this.add(pnlBottom,BorderLayout.SOUTH);
		}
}
