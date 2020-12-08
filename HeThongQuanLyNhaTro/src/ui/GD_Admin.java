package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import connectDB.ConnectDB;
import dao.TamLuuMaNhanVien_Dao;

public class GD_Admin extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6505839134769737084L;
	private JLabel lblTieuDe;
	private JButton btnSinhVien;
	private JButton btnNhanVien;
	private JButton btnThongKe;
	private JButton btnTro;
	private JButton btnBangThongTin;
	private JButton btnDangXuat;
	private JLabel lblNhanVien;
	private JLabel lblSinhVien;
	private JLabel lblThongKe;
	private JLabel lblTro;
	private JLabel lblBangThongTin;
	private TamLuuMaNhanVien_Dao TamLuuMaNhanVien_Dao;
	private JButton btnHDSD;
	private JLabel lblTin;
	private JLabel lblHD;
	private JLabel lblSD;

	public GD_Admin() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height - 20;
		int screenWidth = screenSize.width + 15;
		this.setPreferredSize(new Dimension(screenWidth, screenHeight-58));
		this.setLayout(new BorderLayout());
		
		TamLuuMaNhanVien_Dao = new TamLuuMaNhanVien_Dao();
		try {
			ConnectDB.getInstance().connect();;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		ImageIcon imgNhanVien = new ImageIcon(new ImageIcon("HinhAnh/nhanvien.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
		ImageIcon imgGhiChu =  new ImageIcon(new ImageIcon("HinhAnh/ghichu1.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
		ImageIcon imgThongKe = new ImageIcon(new ImageIcon("HinhAnh/thongke.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
		ImageIcon imgNhaTro =  new ImageIcon(new ImageIcon("HinhAnh/nhatro.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
		ImageIcon imgSinhVien =  new ImageIcon(new ImageIcon("HinhAnh/sinhvien.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
		ImageIcon imgHDSD = new ImageIcon(new ImageIcon("HinhAnh/User manual.jpg").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
		
		lblTieuDe = new JLabel("Trang Chủ Quản Lý");
		lblNhanVien = new JLabel("     Nhân Viên");
		lblSinhVien = new JLabel("      Sinh Viên");
		lblThongKe = new JLabel("     Thống Kê");
		lblBangThongTin = new JLabel("    Bảng Thông");
		lblTin = new JLabel("     Tin");
		lblTro = new JLabel("           Trọ");
		lblHD = new JLabel("Hướng Dẫn\n");
		lblSD = new JLabel("Sử Dụng");
		
		lblNhanVien.setFont(new Font("arial", 1, 20));
		lblSinhVien.setFont(new Font("arial", 1, 20));
		lblTro.setFont(new Font("arial", 1, 20));
		lblBangThongTin.setFont(new Font("arial", 1, 20));
		lblThongKe.setFont(new Font("arial", 1, 20));
		lblTin.setFont(new Font("arial", 1, 20));
		lblHD.setFont(new Font("arial", 1, 20));
		lblSD.setFont(new Font("arial", 1, 20));
		
		btnSinhVien = new JButton(imgSinhVien);
		btnNhanVien = new JButton(imgNhanVien);
		btnThongKe = new JButton(imgThongKe);
		btnTro = new JButton(imgNhaTro);
		btnBangThongTin = new JButton(imgGhiChu);
		btnDangXuat = new JButton("Đăng Xuất");
		btnDangXuat.setFont(new Font("arial",1,20));
		btnDangXuat.setBackground(Color.RED);
		btnDangXuat.setForeground(Color.white);
		
		btnSinhVien.setLayout(new BorderLayout());
		btnSinhVien.add(lblSinhVien,BorderLayout.NORTH);
		
		btnNhanVien.setLayout(new BorderLayout());
		btnNhanVien.add(lblNhanVien,BorderLayout.NORTH);
		
		btnThongKe.setLayout(new BorderLayout());
		btnThongKe.add(lblThongKe,BorderLayout.NORTH);
		
		btnTro.setLayout(new BorderLayout());
		btnTro.add(lblTro,BorderLayout.NORTH);
		
		btnBangThongTin.setLayout(new BorderLayout());
		JPanel pnlTieuDeBangThongTin = new JPanel();
		pnlTieuDeBangThongTin.setLayout(new BorderLayout());
		pnlTieuDeBangThongTin.add(lblBangThongTin,BorderLayout.NORTH);
		pnlTieuDeBangThongTin.add(lblTin,BorderLayout.SOUTH);
		btnBangThongTin.add(pnlTieuDeBangThongTin,BorderLayout.NORTH);
		
		btnBangThongTin.setPreferredSize(new Dimension(190, screenHeight-300));
		btnNhanVien.setPreferredSize(new Dimension(190, screenHeight-300));
		btnSinhVien.setPreferredSize(new Dimension(190, screenHeight-300));
		btnThongKe.setPreferredSize(new Dimension(190, screenHeight-300));
		btnTro.setPreferredSize(new Dimension(190, screenHeight-300));
		btnDangXuat.setPreferredSize(new Dimension(500, 70));
		
		
		btnBangThongTin.setFocusPainted(false);
		btnNhanVien.setFocusPainted(false);
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
		pnlCenter.add(btnNhanVien);
		pnlCenter.add(btnSinhVien);
		pnlCenter.add(btnTro);
		pnlCenter.add(btnThongKe);
		
		pnlBottom.add(btnDangXuat);
		
		this.add(pnlTitle,BorderLayout.NORTH);
		this.add(pnlCenter,BorderLayout.CENTER);
		
		btnHDSD = new JButton(imgHDSD);
		btnHDSD.setLayout(new BorderLayout());
		JPanel pnlTieuDeHDSD = new JPanel();
		pnlTieuDeHDSD.setLayout(new BorderLayout());
		pnlTieuDeHDSD.add(lblHD,BorderLayout.NORTH);
		pnlTieuDeHDSD.add(lblSD,BorderLayout.SOUTH);
		btnHDSD.add(pnlTieuDeHDSD,BorderLayout.NORTH);
		btnHDSD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnHDSD.setPreferredSize(new Dimension(190, screenHeight-300));
		pnlCenter.add(btnHDSD);
		this.add(pnlBottom,BorderLayout.SOUTH);
		
		btnDangXuat.addActionListener(this);
		btnBangThongTin.addActionListener(this);
		btnNhanVien.addActionListener(this);
		btnSinhVien.addActionListener(this);
		btnThongKe.addActionListener(this);
		btnTro.addActionListener(this);
		btnHDSD.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnDangXuat)){
			TamLuuMaNhanVien_Dao.xoaMaNhanVienVaoVungNhoTam();
			removeAll();
			add(new GD_DangNhap());
			repaint();
			revalidate();
		}
		else if (o.equals(btnBangThongTin)) {
			removeAll();
			add(new GD_ThongTinThueTro());
			repaint();
			revalidate();
		}
		else if (o.equals(btnNhanVien)) {
			removeAll();
			add(new GD_QLNhanVien());
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
		else if (o.equals(btnHDSD)) {
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
	
	
	}
}
