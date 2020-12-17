package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import connectDB.ConnectDB;
import dao.TamLuuMaNhanVien_Dao;
import javax.swing.border.LineBorder;
import java.awt.GridLayout;

public class GD_TrangChuNhanVienGVK extends JPanel implements ActionListener{
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
		private JButton btnDoiMK;
		private JLabel lblSinhVien;
		private JLabel lblThongKe;
		private JLabel lblTro;
		private JLabel lblBangThongTin;
		private TamLuuMaNhanVien_Dao TamLuuMaNhanVien_Dao;
		private JButton btnHDSD;
		private JLabel lblHDSD;
		private JLabel lblDoiMK;

		public GD_TrangChuNhanVienGVK() {
			
			TamLuuMaNhanVien_Dao = new TamLuuMaNhanVien_Dao();
			try {
				ConnectDB.getInstance().connect();;
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int screenHeight = screenSize.height - 20;
			int screenWidth = screenSize.width + 15;
			this.setPreferredSize(new Dimension(screenWidth, screenHeight-58));
			this.setLayout(new BorderLayout());
			
			ImageIcon imgGhiChu =  new ImageIcon(new ImageIcon("HinhAnh/ghichu1.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
			ImageIcon imgThongKe = new ImageIcon(new ImageIcon("HinhAnh/thongke.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
			ImageIcon imgNhaTro =  new ImageIcon(new ImageIcon("HinhAnh/nhatro.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
			ImageIcon imgSinhVien =  new ImageIcon(new ImageIcon("HinhAnh/sinhvien.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
			ImageIcon imgHDSD = new ImageIcon(new ImageIcon("HinhAnh/User manual.jpg").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
			ImageIcon imgDoiMK = new ImageIcon(new ImageIcon("HinhAnh/doimk.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));

			
			lblTieuDe = new JLabel("Trang Chủ Giáo Vụ Khoa");
			lblSinhVien = new JLabel("        Sinh Viên");
			lblThongKe = new JLabel("         Thống Kê");
			lblBangThongTin = new JLabel("    Bảng Thông Tin");
			lblTro = new JLabel("              Trọ");
			lblHDSD = new JLabel("     Hướng Dẫn");
			lblDoiMK = new JLabel("   Đổi mật khẩu");
			
			
			lblSinhVien.setFont(new Font("arial", 1, 20));
			lblTro.setFont(new Font("arial", 1, 20));
			lblBangThongTin.setFont(new Font("arial", 1, 20));
			lblThongKe.setFont(new Font("arial", 1, 20));
			lblHDSD.setFont(new Font("arial", 1, 20));
			lblDoiMK.setFont(new Font("arial", 1, 20));

			
			btnSinhVien = new JButton(imgSinhVien);
			btnThongKe = new JButton(imgThongKe);
			btnThongKe.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					removeAll();
					add(new GD_ThongKe());
					repaint();
					revalidate();
					
				}
			});
			btnTro = new JButton(imgNhaTro);
			btnBangThongTin = new JButton(imgGhiChu);
			btnDoiMK = new JButton(imgDoiMK);
			
			btnDangXuat = new JButton("Đăng Xuất");
			btnDangXuat.setBorder(new LineBorder(new Color(255, 102, 0), 5));
			btnDangXuat.setFont(new Font("arial",1,20));
			btnDangXuat.setBackground(Color.RED);
			btnDangXuat.setForeground(Color.white);
			
			btnDoiMK.setLayout(new BorderLayout());
			btnDoiMK.add(lblDoiMK,BorderLayout.NORTH);
			
			btnSinhVien.setLayout(new BorderLayout());
			btnSinhVien.add(lblSinhVien,BorderLayout.NORTH);
			
			
			btnThongKe.setLayout(new BorderLayout());
			btnThongKe.add(lblThongKe,BorderLayout.NORTH);
			
			btnTro.setLayout(new BorderLayout());
			btnTro.add(lblTro,BorderLayout.NORTH);
			
			btnBangThongTin.setLayout(new BorderLayout());
			btnBangThongTin.add(lblBangThongTin,BorderLayout.NORTH);
			
			btnBangThongTin.setPreferredSize(new Dimension(230, 400));
			btnSinhVien.setPreferredSize(new Dimension(230, 400));
			btnThongKe.setPreferredSize(new Dimension(230, 400));
			btnTro.setPreferredSize(new Dimension(230, 400));
			btnDoiMK.setPreferredSize(new Dimension(230, 400));
			btnDangXuat.setPreferredSize(new Dimension(500, 70));
			
			
			btnBangThongTin.setFocusPainted(false);
			btnSinhVien.setFocusPainted(false);
			btnThongKe.setFocusPainted(false);
			btnTro.setFocusPainted(false);
			btnDoiMK.setFocusPainted(false);
			
			
			lblTieuDe.setFont(new Font("arial", 1, 50));
			lblTieuDe.setForeground(Color.white);
			
			
			JPanel pnlTitle = new JPanel();
			pnlTitle.setBorder(new LineBorder(new Color(255, 160, 122), 5, true));
			JPanel pnlBottom = new JPanel();
			JPanel pnlCenter = new JPanel();
			
			
			
			pnlTitle.setPreferredSize(new Dimension(1200, 100));
			pnlTitle.setBackground(new Color(237, 125, 49));
			pnlTitle.add(lblTieuDe);
			pnlCenter.setLayout(new GridLayout(0, 6, 0, 0));
			
			
			pnlCenter.add(btnBangThongTin);
			pnlCenter.add(btnSinhVien);
			pnlCenter.add(btnTro);
			pnlCenter.add(btnThongKe);
			pnlCenter.add(btnDoiMK);
			
			pnlBottom.add(btnDangXuat);
			
			this.add(pnlTitle,BorderLayout.NORTH);
			this.add(pnlCenter,BorderLayout.CENTER);
			
			btnHDSD = new JButton(imgHDSD);
			btnHDSD.setLayout(new BorderLayout());
			btnHDSD.add(lblHDSD,BorderLayout.NORTH);
			btnHDSD.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					File file =  new File("File\\File Help.chm");
					Desktop dsDesktop = Desktop.getDesktop();
					if(file.exists()) {
						try {
							dsDesktop.open(file);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}	
			});
			btnHDSD.setPreferredSize(new Dimension(230, 400));
			pnlCenter.add(btnHDSD);
			this.add(pnlBottom,BorderLayout.SOUTH);
			
			btnDangXuat.addActionListener(this);
			btnBangThongTin.addActionListener(this);
			btnSinhVien.addActionListener(this);
			btnThongKe.addActionListener(this);
			btnTro.addActionListener(this);
			btnDoiMK.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object o = e.getSource();
			if(o.equals(btnDangXuat)) {
				if (JOptionPane.showConfirmDialog(this, "Bạn muốn đăng xuất!","Đăng xuất",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					TamLuuMaNhanVien_Dao.xoaMaNhanVienVaoVungNhoTam();
					removeAll();
					add(new GD_DangNhap());
					repaint();
					revalidate();
				}
			}
			else if (o.equals(btnBangThongTin)) {
				removeAll();
				add(new GD_ThongTinThueTro());
				repaint();
				revalidate();
			}
			else if (o.equals(btnTro)) {
				removeAll();
				add(new GD_QuanLyTro());
				repaint();
				revalidate();
			}
			else if (o.equals(btnSinhVien)) {
				
					    	removeAll();
							add(new GD_QuanLySinhVien());
							repaint();
							revalidate();

			}
			else if(o.equals(btnDoiMK)) {
				new GD_DoiMK();
			}
		}
}
