package ui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.NhanVien_Dao;
import dao.TamLuuMaNhanVien_Dao;

import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.GridLayout;
public class GD_BangThongTin extends JPanel implements ActionListener{

	private DefaultTableModel tableModelBTT;
	private JTable tableBTT;
	private JTextField txtTPMaSinhVien;
	private JTextField txtTPTenSinhVien;
	private JTextField txtTPMaNhaTro;
	private JTextField txtTPTenChuNhaTro;
	private JTextField txtGiaThue;
	private JTextField txtNgayCapNhat;
	private JTextField txtNgayBatDau;
	private JTextField txtNgayKetThuc;
	private JTextField txtTuKhoa;
	private JComboBox cboQuan;
	private JComboBox cboPhuong;
	private JComboBox cboDuong;
	private JComboBox cboSoNha;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnXoaTrang;
	private JComboBox cboLuaChon;
	private JButton btnTim;
	private NhanVien_Dao nv_Dao;
	private TamLuuMaNhanVien_Dao tamluu_dao;

	/**
	 * Launch the application.

	/**
	 * Create the application.
	 */
	public GD_BangThongTin() {
		
		nv_Dao = new NhanVien_Dao(); 
		tamluu_dao = new TamLuuMaNhanVien_Dao();
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		setAutoscrolls(true);
		setBorder(null);
		this.setPreferredSize(new Dimension(1600, 600));
		setLayout(new BorderLayout(0, 0));
		String loaiNV = tamluu_dao.layNhanVienTrongBangTamLuu().getLoaiNV();
		String khoa = tamluu_dao.layNhanVienTrongBangTamLuu().getTenKhoa();
		
		ImageIcon imgUser = new ImageIcon(new ImageIcon("HinhAnh/User.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		ImageIcon imgSV = new ImageIcon(new ImageIcon("HinhAnh/sinhvien.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon imgNV = new ImageIcon(new ImageIcon("HinhAnh/nhanvien.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon imgTK = new ImageIcon(new ImageIcon("HinhAnh/thongke.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon imgBTT = new ImageIcon(new ImageIcon("HinhAnh/ghichu1.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon imgTro = new ImageIcon(new ImageIcon("HinhAnh/nhatro.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon imgexit = new ImageIcon(new ImageIcon("HinhAnh/exit.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon imgHDSD = new ImageIcon(new ImageIcon("HinhAnh/User manual.jpg").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		
		JPanel pnlTrai = new JPanel();
		pnlTrai.setBackground(Color.CYAN);
		pnlTrai.setBorder(null);
		pnlTrai.setPreferredSize(new Dimension(200, 600));
		pnlTrai.setMinimumSize(new Dimension(300, 600));
		pnlTrai.setMaximumSize(new Dimension(300, 600));
		add(pnlTrai, BorderLayout.WEST);
		
		JPanel pnlUser = new JPanel();
		pnlUser.setPreferredSize(new Dimension(200, 200));
		pnlTrai.add(pnlUser);
		pnlUser.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlChucVu = new JPanel();
		pnlChucVu.setBackground(Color.CYAN);
		pnlUser.add(pnlChucVu, BorderLayout.NORTH);
		
		JLabel lblChucVu = new JLabel("Quản Lý\r\n");
		lblChucVu.setFont(new Font("Arial", Font.BOLD, 27));
		
		if(loaiNV.equals("NV")) {
			lblChucVu.setText("    Giáo Vụ Khoa: "+ khoa);
			lblChucVu.setFont(new Font("Arial", 1, 15));
		}
		
		pnlChucVu.add(lblChucVu);
		
		JLabel lblAnhUser = new JLabel("");
		lblAnhUser.setPreferredSize(new Dimension(200, 200));
		pnlUser.add(Box.createHorizontalStrut(25),BorderLayout.WEST);
		pnlUser.add(lblAnhUser, BorderLayout.CENTER);
		lblAnhUser.setIcon(imgUser);
		
		JPanel pnlTacVu = new JPanel();
		pnlTacVu.setFont(new Font("Arial", Font.BOLD, 20));
		pnlTacVu.setPreferredSize(new Dimension(200, 400));
		pnlTrai.add(pnlTacVu);
		pnlTacVu.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel pnlSinhVien = new JPanel();
		pnlSinhVien.setPreferredSize(new Dimension(200, 50));
		pnlTacVu.add(pnlSinhVien);
		
		JButton btnSinhVien = new JButton("Sinh Viên\r\n");
		btnSinhVien.setBorder(null);
		btnSinhVien.setBackground(Color.CYAN);
		btnSinhVien.setPreferredSize(new Dimension(170, 45));
		btnSinhVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(new GD_QuanLySinhVien());
				repaint();
				revalidate();
			}
		});
		btnSinhVien.setFont(new Font("Arial", Font.BOLD, 16));
		btnSinhVien.setIcon(imgSV);
		pnlSinhVien.add(btnSinhVien);
		
		JPanel pnlNhanVien = new JPanel();
		pnlNhanVien.setPreferredSize(new Dimension(200, 50));
		
		
		if(loaiNV.equals("QL")) {
			pnlTacVu.add(pnlNhanVien);
		}
		JButton btnNhanVien = new JButton("Nhân Viên\r\n");
		btnNhanVien.setBorder(null);
		btnNhanVien.setBackground(Color.CYAN);
		btnNhanVien.setPreferredSize(new Dimension(170, 45));
		btnNhanVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(new GD_QLNhanVien());
				repaint();
				revalidate();
			}
		});
		btnNhanVien.setFont(new Font("Arial", Font.BOLD, 16));
		btnNhanVien.setIcon(imgNV);
		pnlNhanVien.add(btnNhanVien);
		
		JPanel pnlThongKe = new JPanel();
		pnlThongKe.setPreferredSize(new Dimension(200, 50));
		pnlTacVu.add(pnlThongKe);
		
		JButton btnTK = new JButton("Thống Kê\r\n");
		btnTK.setBorder(null);
		btnTK.setBackground(Color.CYAN);
		btnTK.setPreferredSize(new Dimension(170, 45));
		btnTK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTK.setFont(new Font("Arial", Font.BOLD, 16));
		btnTK.setIcon(imgTK);
		pnlThongKe.add(btnTK);
		
		JPanel pnlBTT = new JPanel();
		pnlBTT.setPreferredSize(new Dimension(200, 50));
		pnlTacVu.add(pnlBTT);
		
		JButton btnBTT = new JButton("Thuê Trọ"+ "\r\n");
		btnBTT.setBorder(null);
		btnBTT.setBackground(Color.CYAN);
		btnBTT.setPreferredSize(new Dimension(170, 45));
		btnBTT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(new GD_BangThongTin());
				repaint();
				revalidate();
			}
		});
		btnBTT.setPreferredSize(btnNhanVien.getPreferredSize());
		btnBTT.setFont(new Font("Arial", Font.BOLD, 16));
		btnBTT.setIcon(imgBTT);
		pnlBTT.add(btnBTT);
		

		JPanel pnlTro = new JPanel();
		pnlTro.setPreferredSize(new Dimension(200, 50));
		pnlTacVu.add(pnlTro);
		
		JButton btnTro = new JButton("Nhà Trọ\r\n");
		btnTro.setBorder(null);
		btnTro.setBackground(Color.CYAN);
		btnTro.setPreferredSize(new Dimension(170, 45));
		btnTro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(new GD_QuanLyTro());
				repaint();
				revalidate();
			}
		});
		btnTro.setFont(new Font("Arial", Font.BOLD, 16));
		btnTro.setIcon(imgTro);
		pnlTro.add(btnTro);
		
		JPanel pnlHDSD = new JPanel();
		pnlHDSD.setPreferredSize(new Dimension(200, 50));
		pnlTacVu.add(pnlHDSD);
		
		JButton btnHDSD = new JButton("Hướng dẫn\r\n");
		btnHDSD.setBorder(null);
		btnHDSD.setBackground(Color.CYAN);
		btnHDSD.setPreferredSize(new Dimension(170, 45));
		btnHDSD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(new GD_HDSD());
				repaint();
				revalidate();
			}
		});
		btnHDSD.setFont(new Font("Arial", Font.BOLD, 16));
		btnHDSD.setIcon(imgHDSD);
		pnlHDSD.add(btnHDSD);
		
		JPanel pnlThoat = new JPanel();
		pnlThoat.setPreferredSize(new Dimension(200, 50));
		pnlTacVu.add(pnlThoat);
		
		JButton btnThoat = new JButton("Thoát\r\n");
		btnThoat.setBorder(null);
		btnThoat.setBackground(Color.CYAN);
		btnThoat.setPreferredSize(new Dimension(170, 45));
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String loaiNV = tamluu_dao.layNhanVienTrongBangTamLuu().getLoaiNV();
				if(loaiNV.equals("QL")) {
					removeAll();
					add(new GD_Admin());
					repaint();
					revalidate();
				}
				else if (loaiNV.equals("NV")) {
					removeAll();
					add(new GD_TrangChuNhanVienGVK());
					repaint();
					revalidate();
				}
			}
		});
		btnThoat.setFont(new Font("Arial", Font.BOLD, 16));
		btnThoat.setIcon(imgexit);
		pnlThoat.add(btnThoat);
		
		JPanel pnlRight = new JPanel();
		pnlRight.setMinimumSize(new Dimension(1000, 10));
		pnlRight.setPreferredSize(new Dimension(100, 10));
		add(pnlRight, BorderLayout.CENTER);
		pnlRight.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlTieuDe = new JPanel();
		pnlTieuDe.setBackground(Color.CYAN);
		pnlRight.add(pnlTieuDe, BorderLayout.NORTH);
		
		JLabel lblTieuDe = new JLabel("Thuê Trọ\r\n");
		lblTieuDe.setBackground(Color.CYAN);
		lblTieuDe.setForeground(Color.BLUE);
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 30));
		pnlTieuDe.add(lblTieuDe);
		
		JPanel pnlBang = new JPanel();
		pnlBang.setBorder(new LineBorder(Color.CYAN, 2));
		pnlRight.add(pnlBang, BorderLayout.CENTER);
		
		String[] header = { "Mã Sinh Viên", "Tên Sinh Viên", "Mã Nhà Trọ", "Tên Nhà Trọ", "Địa Chỉ", "Ngày Bắt Đầu Thuê", "Ngày Kết Thúc Thuê", "Giá",
				"Ngày Cập Nhật"};
		tableModelBTT = new DefaultTableModel(header, 0);
		tableBTT = new JTable(tableModelBTT) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

						// Không cho sửa trên table
						@Override
						public boolean isCellEditable(int row, int column) {
							return false;
						}
		};
		pnlBang.add(new JScrollPane(tableBTT, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		tableBTT.setPreferredScrollableViewportSize(new Dimension(1150, 240));
		tableBTT.setRowHeight(30);
		
		JPanel pnlNhapThongTin = new JPanel();
		pnlNhapThongTin.setBackground(Color.CYAN);
		pnlNhapThongTin.setPreferredSize(new Dimension(1400, 280));
		pnlRight.add(pnlNhapThongTin, BorderLayout.SOUTH);
		pnlNhapThongTin.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlNhapThongTinCoBan = new JPanel();
		pnlNhapThongTinCoBan.setPreferredSize(new Dimension(700, 10));
		pnlNhapThongTin.add(pnlNhapThongTinCoBan, BorderLayout.CENTER);
		pnlNhapThongTinCoBan.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlKhoangTrongTrai = new JPanel();
		pnlKhoangTrongTrai.setBackground(Color.GRAY);
		pnlKhoangTrongTrai.setPreferredSize(new Dimension(30, 10));
		pnlNhapThongTinCoBan.add(pnlKhoangTrongTrai, BorderLayout.WEST);
		
		JPanel pnlTieuDeNhapThongTin = new JPanel();
		pnlTieuDeNhapThongTin.setForeground(Color.YELLOW);
		pnlTieuDeNhapThongTin.setPreferredSize(new Dimension(10, 50));
		pnlTieuDeNhapThongTin.setBackground(Color.GRAY);
		pnlNhapThongTinCoBan.add(pnlTieuDeNhapThongTin, BorderLayout.NORTH);
		
		JLabel lblNhapThongTin = new JLabel("Nhập Thông Tin");
		lblNhapThongTin.setForeground(Color.CYAN);
		lblNhapThongTin.setFont(new Font("Arial", Font.BOLD, 27));
		pnlTieuDeNhapThongTin.add(lblNhapThongTin);
		
		JPanel pnlThanhPhan = new JPanel();
		pnlNhapThongTinCoBan.add(pnlThanhPhan, BorderLayout.CENTER);
		pnlThanhPhan.setLayout(new BoxLayout(pnlThanhPhan, BoxLayout.Y_AXIS));
		
		JPanel pnlTPNhaTro = new JPanel();
		pnlThanhPhan.add(Box.createVerticalStrut(10));
		pnlThanhPhan.add(pnlTPNhaTro);
		pnlTPNhaTro.setLayout(new BoxLayout(pnlTPNhaTro, BoxLayout.X_AXIS));
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		pnlTPNhaTro.add(horizontalStrut_2);
		
		JLabel lblTPMaNhaTro = new JLabel("Mã Nhà Trọ: ");
		lblTPMaNhaTro.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTPNhaTro.add(lblTPMaNhaTro);
		
		txtTPMaNhaTro = new JTextField();
		txtTPMaNhaTro.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTPNhaTro.add(txtTPMaNhaTro);
		txtTPMaNhaTro.setColumns(10);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalStrut_3.setPreferredSize(new Dimension(30, 0));
		pnlTPNhaTro.add(horizontalStrut_3);
		
		JLabel lblTenChuNha = new JLabel("Tên Chủ Nhà: ");
		lblTenChuNha.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTPNhaTro.add(lblTenChuNha);
		
		txtTPTenChuNhaTro = new JTextField();
		txtTPTenChuNhaTro.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTPNhaTro.add(txtTPTenChuNhaTro);
		txtTPTenChuNhaTro.setColumns(10);
		
		Component horizontalStrut_9 = Box.createHorizontalStrut(20);
		pnlTPNhaTro.add(horizontalStrut_9);
		
		JPanel pnlTPSinhVien = new JPanel();
		pnlThanhPhan.add(Box.createVerticalStrut(10));
		pnlThanhPhan.add(pnlTPSinhVien);
		pnlTPSinhVien.setLayout(new BoxLayout(pnlTPSinhVien, BoxLayout.X_AXIS));
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		pnlTPSinhVien.add(horizontalStrut_1);
		
		JLabel lblTPMaSinhVien = new JLabel("Mã Sinh Viên: ");
		lblTPMaSinhVien.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTPSinhVien.add(lblTPMaSinhVien);
		
		txtTPMaSinhVien = new JTextField();
		txtTPMaSinhVien.setPreferredSize(new Dimension(20, 20));
		txtTPMaSinhVien.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTPSinhVien.add(txtTPMaSinhVien);
		txtTPMaSinhVien.setColumns(10);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setPreferredSize(new Dimension(30, 0));
		pnlTPSinhVien.add(horizontalStrut);
		
		JLabel lblTPTenSinhVien = new JLabel("Tên Sinh Viên: ");
		lblTPTenSinhVien.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTPSinhVien.add(lblTPTenSinhVien);
		
		txtTPTenSinhVien = new JTextField();
		txtTPTenSinhVien.setFont(new Font("Arial", Font.PLAIN, 15));
		txtTPTenSinhVien.setColumns(10);
		pnlTPSinhVien.add(txtTPTenSinhVien);
		
		Component horizontalStrut_10 = Box.createHorizontalStrut(20);
		pnlTPSinhVien.add(horizontalStrut_10);
		
		JPanel pnlTPDiaChiTro = new JPanel();
		pnlThanhPhan.add(Box.createVerticalStrut(10));
		pnlThanhPhan.add(pnlTPDiaChiTro);
		pnlTPDiaChiTro.setLayout(new BoxLayout(pnlTPDiaChiTro, BoxLayout.X_AXIS));
		
		Component horizontalStrut_7 = Box.createHorizontalStrut(20);
		pnlTPDiaChiTro.add(horizontalStrut_7);
		
		JLabel lblTPDiaChiTro = new JLabel("Địa Chỉ: ");
		lblTPDiaChiTro.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTPDiaChiTro.add(lblTPDiaChiTro);
		
		JLabel lblQuan = new JLabel("Quận:\r\n \r\n");
		lblQuan.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTPDiaChiTro.add(lblQuan);
		
		cboQuan = new JComboBox();
		cboQuan.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTPDiaChiTro.add(cboQuan);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		pnlTPDiaChiTro.add(horizontalStrut_4);
		
		JLabel lblNewLabel = new JLabel("Phường: \r\n");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTPDiaChiTro.add(lblNewLabel);
		
		cboPhuong = new JComboBox();
		cboPhuong.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTPDiaChiTro.add(cboPhuong);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		pnlTPDiaChiTro.add(horizontalStrut_5);
		
		JLabel lblDuong = new JLabel("Đường: ");
		lblDuong.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTPDiaChiTro.add(lblDuong);
		
		cboDuong = new JComboBox();
		cboDuong.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTPDiaChiTro.add(cboDuong);
		
		Component horizontalStrut_6 = Box.createHorizontalStrut(20);
		pnlTPDiaChiTro.add(horizontalStrut_6);
		
		JLabel lblSoNha = new JLabel("Số Nhà: ");
		lblSoNha.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTPDiaChiTro.add(lblSoNha);
		
		cboSoNha = new JComboBox();
		cboSoNha.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTPDiaChiTro.add(cboSoNha);
		
		Component horizontalStrut_8 = Box.createHorizontalStrut(20);
		pnlTPDiaChiTro.add(horizontalStrut_8);
		
		JPanel pnlGia_NgayCapNhat = new JPanel();
		pnlThanhPhan.add(Box.createVerticalStrut(10));
		pnlThanhPhan.add(pnlGia_NgayCapNhat);
		pnlGia_NgayCapNhat.setLayout(new BoxLayout(pnlGia_NgayCapNhat, BoxLayout.X_AXIS));
		
		Component horizontalStrut_11 = Box.createHorizontalStrut(20);
		pnlGia_NgayCapNhat.add(horizontalStrut_11);
		
		JLabel lblGia = new JLabel("Giá Thuê: ");
		lblGia.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlGia_NgayCapNhat.add(lblGia);
		
		txtGiaThue = new JTextField();
		txtGiaThue.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlGia_NgayCapNhat.add(txtGiaThue);
		txtGiaThue.setColumns(10);
		
		Component horizontalStrut_12 = Box.createHorizontalStrut(20);
		horizontalStrut_12.setPreferredSize(new Dimension(30, 0));
		pnlGia_NgayCapNhat.add(horizontalStrut_12);
		
		JLabel lblNgayCapNhat = new JLabel("Ngày Cập Nhật: \r\n");
		lblNgayCapNhat.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlGia_NgayCapNhat.add(lblNgayCapNhat);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		pnlGia_NgayCapNhat.add(horizontalGlue);
		
		
		txtNgayCapNhat = new JTextField();
		txtNgayCapNhat.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlGia_NgayCapNhat.add(txtNgayCapNhat);
		txtNgayCapNhat.setColumns(10);
		
		Component horizontalStrut_13 = Box.createHorizontalStrut(20);
		pnlGia_NgayCapNhat.add(horizontalStrut_13);
		
		JPanel pnlNgayBatDau_KetThuc = new JPanel();
		pnlThanhPhan.add(Box.createVerticalStrut(10));
		pnlThanhPhan.add(pnlNgayBatDau_KetThuc);
		pnlThanhPhan.add(Box.createVerticalStrut(20));
		pnlNgayBatDau_KetThuc.setLayout(new BoxLayout(pnlNgayBatDau_KetThuc, BoxLayout.X_AXIS));
		
		Component horizontalStrut_14 = Box.createHorizontalStrut(20);
		pnlNgayBatDau_KetThuc.add(horizontalStrut_14);
		
		JLabel lblNgayBatDau = new JLabel("Ngày Bắt Đầu Thuê: ");
		lblNgayBatDau.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlNgayBatDau_KetThuc.add(lblNgayBatDau);
		
		txtNgayBatDau = new JTextField();
		txtNgayBatDau.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlNgayBatDau_KetThuc.add(txtNgayBatDau);
		txtNgayBatDau.setColumns(10);
		
		Component horizontalStrut_15 = Box.createHorizontalStrut(20);
		horizontalStrut_15.setPreferredSize(new Dimension(30, 0));
		pnlNgayBatDau_KetThuc.add(horizontalStrut_15);
		
		JLabel lblNgayKetThuc = new JLabel("Ngày Kết Thúc Thuê: ");
		lblNgayKetThuc.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlNgayBatDau_KetThuc.add(lblNgayKetThuc);
		
		txtNgayKetThuc = new JTextField();
		txtNgayKetThuc.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlNgayBatDau_KetThuc.add(txtNgayKetThuc);
		txtNgayKetThuc.setColumns(10);
		
		Component horizontalStrut_16 = Box.createHorizontalStrut(20);
		pnlNgayBatDau_KetThuc.add(horizontalStrut_16);
		
		lblTPMaNhaTro.setPreferredSize(lblNgayKetThuc.getPreferredSize());
		lblTPTenSinhVien.setPreferredSize(lblNgayKetThuc.getPreferredSize());
		lblTenChuNha.setPreferredSize(lblNgayKetThuc.getPreferredSize());
		lblTPMaSinhVien.setPreferredSize(lblNgayKetThuc.getPreferredSize());
		lblGia.setPreferredSize(lblNgayKetThuc.getPreferredSize());
		lblNgayCapNhat.setPreferredSize(lblNgayKetThuc.getPreferredSize());
		lblNgayBatDau.setPreferredSize(lblNgayKetThuc.getPreferredSize());
		
		JPanel pnlTimKiem = new JPanel();
		pnlTimKiem.setPreferredSize(new Dimension(400, 10));
		pnlNhapThongTin.add(pnlTimKiem, BorderLayout.EAST);
		pnlTimKiem.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlTieuDeTimKiem = new JPanel();
		pnlTieuDeTimKiem.setPreferredSize(new Dimension(10, 50));
		pnlTieuDeTimKiem.setBackground(Color.CYAN);
		pnlTimKiem.add(pnlTieuDeTimKiem, BorderLayout.NORTH);
		
		JLabel lblTieuDeTimKiem = new JLabel("Tác Vụ\r\n");
		lblTieuDeTimKiem.setFont(new Font("Arial", Font.BOLD, 25));
		pnlTieuDeTimKiem.add(lblTieuDeTimKiem);
		
		JPanel pnlTacVuVaTimKiem = new JPanel();
		pnlTimKiem.add(pnlTacVuVaTimKiem, BorderLayout.CENTER);
		pnlTacVuVaTimKiem.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlTacVuBenTrai = new JPanel();
		pnlTacVuBenTrai.setBorder(new LineBorder(Color.CYAN));
		pnlTacVuVaTimKiem.add(pnlTacVuBenTrai, BorderLayout.WEST);
		pnlTacVuBenTrai.setLayout(new BoxLayout(pnlTacVuBenTrai, BoxLayout.Y_AXIS));
		
		JPanel pnlThem = new JPanel();
		pnlTacVuBenTrai.add(pnlThem);
		
		btnThem = new JButton("Thêm \r\n");
		btnThem.setPreferredSize(new Dimension(200, 30));
		pnlThem.add(btnThem);
		btnThem.setFont(new Font("Arial", Font.BOLD, 15));
		
		Component verticalStrut_4 = Box.createVerticalStrut(20);
		verticalStrut_4.setMinimumSize(new Dimension(0, 10));
		verticalStrut_4.setPreferredSize(new Dimension(0, 10));
		pnlTacVuBenTrai.add(verticalStrut_4);
		
		JPanel pnlXoa = new JPanel();
		pnlTacVuBenTrai.add(pnlXoa);
		
		btnXoa = new JButton("Xóa\r\n");
		btnXoa.setPreferredSize(new Dimension(200, 30));
		pnlXoa.add(btnXoa);
		btnXoa.setFont(new Font("Arial", Font.BOLD, 15));
		
		Component verticalStrut_5 = Box.createVerticalStrut(20);
		verticalStrut_5.setPreferredSize(new Dimension(0, 10));
		verticalStrut_5.setMinimumSize(new Dimension(0, 10));
		pnlTacVuBenTrai.add(verticalStrut_5);
		
		JPanel pnlSua = new JPanel();
		pnlTacVuBenTrai.add(pnlSua);
		
		btnSua = new JButton("Sửa\r\n");
		btnSua.setPreferredSize(new Dimension(200, 30));
		pnlSua.add(btnSua);
		btnSua.setFont(new Font("Arial", Font.BOLD, 15));
		
		Component verticalStrut_6 = Box.createVerticalStrut(20);
		verticalStrut_6.setPreferredSize(new Dimension(0, 10));
		pnlTacVuBenTrai.add(verticalStrut_6);
		
		JPanel pnlXoaTrang = new JPanel();
		pnlTacVuBenTrai.add(pnlXoaTrang);
		
		btnXoaTrang = new JButton("Xóa Trắng\r\n");
		btnXoaTrang.setPreferredSize(new Dimension(200, 30));
		pnlXoaTrang.add(btnXoaTrang);
		btnXoaTrang.setFont(new Font("Arial", Font.BOLD, 15));
		
		Component verticalStrut_7 = Box.createVerticalStrut(20);
		verticalStrut_7.setPreferredSize(new Dimension(0, 10));
		pnlTacVuBenTrai.add(verticalStrut_7);
		
		JPanel pnlTacVuBenPhai = new JPanel();
		pnlTacVuBenPhai.setPreferredSize(new Dimension(190, 10));
		pnlTacVuVaTimKiem.add(pnlTacVuBenPhai, BorderLayout.CENTER);
		pnlTacVuBenPhai.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlTieuDeTacVuBenPhai = new JPanel();
		pnlTieuDeTacVuBenPhai.setBackground(Color.LIGHT_GRAY);
		pnlTacVuBenPhai.add(pnlTieuDeTacVuBenPhai, BorderLayout.NORTH);
		
		JLabel lblTacVuTimKiem = new JLabel("Tìm Kiếm\r\n");
		lblTacVuTimKiem.setForeground(Color.BLACK);
		lblTacVuTimKiem.setFont(new Font("Arial", Font.BOLD, 15));
		pnlTieuDeTacVuBenPhai.add(lblTacVuTimKiem);
		
		JPanel pnlTacVuTimKiem = new JPanel();
		pnlTacVuBenPhai.add(pnlTacVuTimKiem, BorderLayout.CENTER);
		pnlTacVuTimKiem.setLayout(new BoxLayout(pnlTacVuTimKiem, BoxLayout.Y_AXIS));
		
		Component verticalStrut = Box.createVerticalStrut(20);
		pnlTacVuTimKiem.add(verticalStrut);
		
		cboLuaChon = new JComboBox();
		cboLuaChon.setPreferredSize(new Dimension(30, 40));
		pnlTacVuTimKiem.add(cboLuaChon);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		pnlTacVuTimKiem.add(verticalStrut_1);
		
		txtTuKhoa = new JTextField();
		txtTuKhoa.setPreferredSize(new Dimension(7, 40));
		txtTuKhoa.setForeground(Color.LIGHT_GRAY);
		txtTuKhoa.setText("mời nhập vào đây");
		txtTuKhoa.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTacVuTimKiem.add(txtTuKhoa);
		txtTuKhoa.setColumns(10);
		txtTuKhoa.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtTuKhoa.getText().equals("mời nhập vào đây")) {
					txtTuKhoa.setForeground(Color.black);
					txtTuKhoa.setText("");
				} else {
					txtTuKhoa.selectAll();
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtTuKhoa.getText().equals("")) {
					txtTuKhoa.setForeground(new Color(153, 153, 153));
					txtTuKhoa.setText("mời nhập vào đây");
				}
			}
		});
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		pnlTacVuTimKiem.add(verticalStrut_2);
		
		JPanel pnlNutTim = new JPanel();
		pnlTacVuTimKiem.add(pnlNutTim);
		
		btnTim = new JButton("Tìm Kiếm\r\n");
		btnTim.setPreferredSize(new Dimension(190, 40));
		btnTim.setFont(new Font("Arial", Font.BOLD, 15));
		pnlNutTim.add(btnTim);
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		pnlTacVuBenPhai.add(verticalStrut_3, BorderLayout.SOUTH);
		
		Component horizontalStrut_17 = Box.createHorizontalStrut(20);
		pnlTacVuVaTimKiem.add(horizontalStrut_17, BorderLayout.EAST);
		
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnTim.addActionListener(this);
		cboDuong.addActionListener(this);
		cboLuaChon.addActionListener(this);
		cboPhuong.addActionListener(this);
		cboQuan.addActionListener(this);
		cboSoNha.addActionListener(this);
		
		DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		txtTPMaSinhVien.setEnabled(false);
		txtTPTenSinhVien.setEnabled(false);
		txtTPMaNhaTro.setEnabled(false);
		txtTPTenChuNhaTro.setEnabled(false);
		txtNgayCapNhat.setText(dt.format(LocalDate.now()));
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnXoaTrang)) {
			DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			txtGiaThue.setText("");
			txtNgayBatDau.setText("");
			txtNgayKetThuc.setText("");
			txtNgayCapNhat.setText(dt.format(LocalDate.now()));
			txtTPMaNhaTro.setText("");
			txtTPMaSinhVien.setText("");
			txtTPTenChuNhaTro.setText("");
			txtTPTenSinhVien.setText("");
			txtTuKhoa.setText("mời nhập vào đây");
//			cboLuaChon.setSelectedIndex(0);
//			cboDuong.setSelectedIndex(0);
//			cboQuan.setSelectedIndex(0);
//			cboPhuong.setSelectedIndex(0);
//			cboSoNha.setSelectedIndex(0);
			txtGiaThue.isFocusable();
		}
		
	}
	

}
