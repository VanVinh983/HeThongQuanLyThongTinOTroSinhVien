package ui;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import resourse.SetSizeByPercent;
import java.awt.Color;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import connectDB.ConnectDB;
import dao.NhaTro_Dao;
import dao.SinhVien_Dao;
import dao.TamLuuMaNhanVien_Dao;
import dao.ThongTinThueTro_Dao;
import entity.DiaChi;
import entity.NhaTro;
import entity.SinhVien;
import entity.ThongTinThueTro;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Desktop;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

public class GD_ThongTinThueTro extends JPanel implements ActionListener, MouseListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7859858909817198764L;
	private TamLuuMaNhanVien_Dao tamluu_dao;
	private SinhVien_Dao sinhvien_dao;
	private NhaTro_Dao NhaTro_Dao;
	private ThongTinThueTro_Dao thongtinthuetro_dao;
	private String loaiNV;
	private String khoa;
	private JButton btnThoat;
	private JTextField txtMaSinhVien;
	private JTextField txtTenSinhVien;
	private JTextField txtMaNhaTro;
	private JTextField txtTenChuTro;
	private JTextField txtDiaChi;
	private JTextField txtGiaThue;
	private DefaultTableModel tableModelBTT;
	private JTable tableBTT;
	private JDateChooser dateNgayBatDau;
	private JDateChooser dateNgayCapNhat;
	private JDateChooser dateNgayKetThuc;
	private JComboBox<String> cboTrangThai;
	private JButton btnXoaTrang;
	private JButton btnCapNhatThongTin;
	private JButton btnXoaThongTin;
	private JButton btnSuaThongTin;
	private JButton btnTimKiemSinhVien;
	private JButton btnTimKiemTro;
	private JButton btnThemTro;
	private JButton btnXemLichSu;
	private List<SinhVien> listSV;
	private JComboBox<String> cboTimNhaTro;
	private JComboBox<String> cboTimSinhVien;
	private DefaultTableModel tableModelNhaTro;
	private DefaultTableModel tableModelSinhVien;
	private DefaultTableModel tableModelBTT2;

	/**
	 * Create the panel.
	 */
	public GD_ThongTinThueTro() {
		setBorder(null);
		setBackground(Color.WHITE);

		tamluu_dao = new TamLuuMaNhanVien_Dao();
		sinhvien_dao = new SinhVien_Dao();
		NhaTro_Dao = new NhaTro_Dao();
		thongtinthuetro_dao = new ThongTinThueTro_Dao();
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		loaiNV = tamluu_dao.layNhanVienTrongBangTamLuu().getLoaiNV();
		khoa = tamluu_dao.layNhanVienTrongBangTamLuu().getTenKhoa();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		SetSizeByPercent setSizeByPercent = new SetSizeByPercent(screenSize);
		int screenHeight = screenSize.height - setSizeByPercent.getHeightByPercent(3);
		int screenWidth = screenSize.width;
		this.setPreferredSize(new Dimension(screenSize));

		ImageIcon imgUser = new ImageIcon(
				new ImageIcon("HinhAnh/User.png").getImage().getScaledInstance(setSizeByPercent.getWidthByPercent(10),
						setSizeByPercent.getHeightByPercent(17.7), Image.SCALE_DEFAULT));
		ImageIcon imgSV = new ImageIcon(new ImageIcon("HinhAnh/sinhvien.png").getImage().getScaledInstance(
				setSizeByPercent.getWidthByPercent(3), setSizeByPercent.getHeightByPercent(6), Image.SCALE_DEFAULT));
		ImageIcon imgNV = new ImageIcon(new ImageIcon("HinhAnh/nhanvien.png").getImage().getScaledInstance(
				setSizeByPercent.getWidthByPercent(3), setSizeByPercent.getHeightByPercent(6), Image.SCALE_DEFAULT));
		ImageIcon imgTK = new ImageIcon(new ImageIcon("HinhAnh/thongke.png").getImage().getScaledInstance(
				setSizeByPercent.getWidthByPercent(3), setSizeByPercent.getHeightByPercent(6), Image.SCALE_DEFAULT));
		ImageIcon imgBTT = new ImageIcon(new ImageIcon("HinhAnh/ghichu1.png").getImage().getScaledInstance(
				setSizeByPercent.getWidthByPercent(3), setSizeByPercent.getHeightByPercent(6), Image.SCALE_DEFAULT));
		ImageIcon imgTro = new ImageIcon(new ImageIcon("HinhAnh/nhatro.png").getImage().getScaledInstance(
				setSizeByPercent.getWidthByPercent(3), setSizeByPercent.getHeightByPercent(6), Image.SCALE_DEFAULT));
		ImageIcon imgexit = new ImageIcon(new ImageIcon("HinhAnh/exit.png").getImage().getScaledInstance(
				setSizeByPercent.getWidthByPercent(3), setSizeByPercent.getHeightByPercent(6), Image.SCALE_DEFAULT));
		ImageIcon imgHDSD = new ImageIcon(new ImageIcon("HinhAnh/User manual.jpg").getImage().getScaledInstance(
				setSizeByPercent.getWidthByPercent(3), setSizeByPercent.getHeightByPercent(6), Image.SCALE_DEFAULT));
		ImageIcon imgdoimk = new ImageIcon(new ImageIcon("HinhAnh/doimk.png").getImage().getScaledInstance(
				setSizeByPercent.getWidthByPercent(3), setSizeByPercent.getHeightByPercent(6), Image.SCALE_DEFAULT));
		setLayout(new BorderLayout(0, 0));

		JPanel pnlChinh = new JPanel();
		pnlChinh.setBackground(Color.WHITE);
		add(pnlChinh);
		pnlChinh.setPreferredSize(new Dimension(screenWidth, screenHeight));
		pnlChinh.setLayout(new BorderLayout());

		JPanel pnlMenu = new JPanel();
		pnlMenu.setBackground(Color.WHITE);
		pnlChinh.add(pnlMenu, BorderLayout.WEST);
		pnlMenu.setPreferredSize(new Dimension(setSizeByPercent.getWidthByPercent(15), screenHeight));
		pnlMenu.setLayout(new BorderLayout(0, 0));

		JPanel pnlNguoiDung = new JPanel();
		pnlMenu.add(pnlNguoiDung, BorderLayout.NORTH);
		pnlNguoiDung.setPreferredSize(
				new Dimension(setSizeByPercent.getWidthByPercent(15), setSizeByPercent.getHeightByPercent(26.5)));
		pnlNguoiDung.setLayout(new BorderLayout(0, 0));

		JPanel pnlChucVu = new JPanel();
		pnlChucVu.setBorder(new LineBorder(new Color(135, 206, 250), 3, true));
		pnlChucVu.setBackground(new Color(0, 191, 255));
		pnlNguoiDung.add(pnlChucVu, BorderLayout.NORTH);
		pnlChucVu.setPreferredSize(
				new Dimension(setSizeByPercent.getWidthByPercent(15), setSizeByPercent.getHeightByPercent(5)));
		JLabel lblNewLabel = new JLabel("Quản Lý\r\n");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		if (loaiNV.equals("NV")) {
			lblNewLabel.setFont(new Font("arial",Font.BOLD, 15));
			lblNewLabel.setText(tamluu_dao.layNhanVienTrongBangTamLuu().getTenKhoa());
		}
		
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlChucVu.add(lblNewLabel);

		JPanel pnlAnhNguoiDung = new JPanel();
		pnlAnhNguoiDung.setBorder(new LineBorder(new Color(135, 206, 250), 4, true));
		pnlAnhNguoiDung.setBackground(new Color(0, 191, 255));
		pnlNguoiDung.add(pnlAnhNguoiDung, BorderLayout.CENTER);

		JLabel lblAnhNguoiDung = new JLabel(imgUser);
		lblAnhNguoiDung.setBorder(new LineBorder(new Color(135, 206, 250), 1, true));
		lblAnhNguoiDung.setPreferredSize(
				new Dimension(setSizeByPercent.getWidthByPercent(10), setSizeByPercent.getHeightByPercent(17.7)));
		pnlAnhNguoiDung.add(lblAnhNguoiDung);

		JPanel pnlMenuCon = new JPanel();
		pnlMenuCon.setBorder(new LineBorder(new Color(135, 206, 250), 3));
		pnlMenuCon.setBackground(new Color(135, 206, 250));
		pnlMenu.add(pnlMenuCon, BorderLayout.CENTER);
		pnlMenuCon.setLayout(new GridLayout(8, 1));

		JPanel pnlNhaTro = new JPanel();
		pnlNhaTro.setBackground(new Color(135, 206, 250));
		pnlMenuCon.add(pnlNhaTro);

		JButton btnNhaTro = new JButton("Nhà Trọ\r\n");
		btnNhaTro.setIcon(imgTro);
		btnNhaTro.setBorder(null);
		btnNhaTro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(new GD_QuanLyTro());
				repaint();
				revalidate();
			}
		});
		pnlNhaTro.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnNhaTro.setBackground(new Color(0, 191, 255));
		btnNhaTro.setFont(new Font("Arial", Font.BOLD, 20));
		pnlNhaTro.add(btnNhaTro);

		JPanel pnlSinhVien = new JPanel();
		pnlSinhVien.setBackground(new Color(135, 206, 250));
		pnlMenuCon.add(pnlSinhVien);
		pnlSinhVien.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnSinhVien = new JButton("Sinh Viên\r\n");
		btnSinhVien.setBackground(new Color(0, 191, 255));
		btnSinhVien.setBorder(null);
		btnSinhVien.setFont(new Font("Arial", Font.BOLD, 20));
		pnlSinhVien.add(btnSinhVien);
		btnSinhVien.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(new GD_QuanLySinhVien());
				repaint();
				revalidate();
			}
		});

		JPanel pnlThueTro = new JPanel();
		pnlThueTro.setBackground(new Color(135, 206, 250));
		pnlMenuCon.add(pnlThueTro);
		pnlThueTro.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnThueTro = new JButton("Thuê Trọ");
		btnThueTro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(new GD_ThongTinThueTro());
				repaint();
				revalidate();
			}
		});
		btnThueTro.setBorder(null);
		btnThueTro.setBackground(new Color(0, 191, 255));
		btnThueTro.setFont(new Font("Arial", Font.BOLD, 20));
		pnlThueTro.add(btnThueTro);

		JPanel pnlThongKe = new JPanel();
		pnlThongKe.setBackground(new Color(135, 206, 250));
		pnlMenuCon.add(pnlThongKe);
		pnlThongKe.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnNhanVien = new JButton("Nhân Viên\r\n");
		btnNhanVien.setBorder(null);
		btnNhanVien.setBackground(new Color(0, 191, 255));
		btnNhanVien.setFont(new Font("Arial", Font.BOLD, 20));
		pnlThongKe.add(btnNhanVien);
		btnNhanVien.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(new GD_QLNhanVien());
				repaint();
				revalidate();
			}
		});

		JPanel pnlNhanVien = new JPanel();
		pnlNhanVien.setBackground(new Color(135, 206, 250));
		pnlMenuCon.add(pnlNhanVien);
		pnlNhanVien.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnThongKe = new JButton("Thống Kê\r\n");
		btnThongKe.setBorder(null);
		btnThongKe.setBackground(new Color(0, 191, 255));
		btnThongKe.setFont(new Font("Arial", Font.BOLD, 20));
		pnlNhanVien.add(btnThongKe);
		
		JPanel pnlDoiMatKhau = new JPanel();
		pnlDoiMatKhau.setBackground(new Color(135, 206, 250));
		pnlMenuCon.add(pnlDoiMatKhau);
		
		JButton btnDoiMatKhau = new JButton("Đổi mật khẩu");
		btnDoiMatKhau.setFont(new Font("Arial", Font.BOLD, 20));
		btnDoiMatKhau.setPreferredSize(btnNhanVien.getPreferredSize());
		btnDoiMatKhau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GD_DoiMK();
			}
		});
		btnDoiMatKhau.setBorder(null);
		btnDoiMatKhau.setIcon(imgdoimk);
		btnDoiMatKhau.setBackground(new Color(0, 191, 255));
		pnlDoiMatKhau.add(btnDoiMatKhau);

		JPanel pnlTroGiup = new JPanel();
		pnlTroGiup.setBackground(new Color(135, 206, 250));
		pnlMenuCon.add(pnlTroGiup);
		pnlTroGiup.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnTroGiup = new JButton("Trợ Giúp\r\n");
		btnTroGiup.setBorder(null);
		btnTroGiup.setBackground(new Color(0, 191, 255));
		btnTroGiup.setFont(new Font("Arial", Font.BOLD, 20));
		pnlTroGiup.add(btnTroGiup);
		btnTroGiup.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				File file = new File("File\\File Help.chm");
				Desktop dsDesktop = Desktop.getDesktop();
				if (file.exists()) {
					try {
						dsDesktop.open(file);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		JPanel pnlThoat = new JPanel();
		pnlThoat.setBackground(new Color(135, 206, 250));
		pnlMenuCon.add(pnlThoat);
		pnlThoat.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnThoat = new JButton("Thoát");
		btnThoat.setBorder(null);
		btnThoat.setBackground(new Color(0, 191, 255));
		btnThoat.setFont(new Font("Arial", Font.BOLD, 20));
		pnlThoat.add(btnThoat);
		btnThoat.addActionListener(this);

		btnNhaTro.setPreferredSize(
				new Dimension(setSizeByPercent.getWidthByPercent(15), setSizeByPercent.getHeightByPercent(7)));
		btnSinhVien.setPreferredSize(
				new Dimension(setSizeByPercent.getWidthByPercent(15), setSizeByPercent.getHeightByPercent(7)));
		btnThueTro.setPreferredSize(
				new Dimension(setSizeByPercent.getWidthByPercent(15), setSizeByPercent.getHeightByPercent(7)));
		btnNhanVien.setPreferredSize(
				new Dimension(setSizeByPercent.getWidthByPercent(15), setSizeByPercent.getHeightByPercent(7)));
		btnThongKe.setPreferredSize(
				new Dimension(setSizeByPercent.getWidthByPercent(15), setSizeByPercent.getHeightByPercent(7)));
		btnTroGiup.setPreferredSize(
				new Dimension(setSizeByPercent.getWidthByPercent(15), setSizeByPercent.getHeightByPercent(7)));
		btnThoat.setPreferredSize(
				new Dimension(setSizeByPercent.getWidthByPercent(15), setSizeByPercent.getHeightByPercent(7)));
		btnDoiMatKhau.setPreferredSize(
				new Dimension(setSizeByPercent.getWidthByPercent(15), setSizeByPercent.getHeightByPercent(7)));

		btnNhanVien.setIcon(imgNV);
		btnNhaTro.setIcon(imgTro);
		btnSinhVien.setIcon(imgSV);
		btnThoat.setIcon(imgexit);
		btnThongKe.setIcon(imgTK);
		btnThueTro.setIcon(imgBTT);
		btnTroGiup.setIcon(imgHDSD);

		btnNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnNhanVien.setBackground(new Color(30, 144, 255));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnNhanVien.setBackground(new Color(0, 191, 255));
			}
		});
		btnSinhVien.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnSinhVien.setBackground(new Color(30, 144, 255));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnSinhVien.setBackground(new Color(0, 191, 255));
			}
		});
		btnNhaTro.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnNhaTro.setBackground(new Color(30, 144, 255));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnNhaTro.setBackground(new Color(0, 191, 255));
			}
		});
		btnThueTro.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnThueTro.setBackground(new Color(30, 144, 255));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnThueTro.setBackground(new Color(0, 191, 255));
			}
		});
		btnThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnThongKe.setBackground(new Color(30, 144, 255));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnThongKe.setBackground(new Color(0, 191, 255));
			}
		});
		btnTroGiup.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnTroGiup.setBackground(new Color(30, 144, 255));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnTroGiup.setBackground(new Color(0, 191, 255));
			}
		});
		btnThoat.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnThoat.setBackground(new Color(30, 144, 255));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnThoat.setBackground(new Color(0, 191, 255));
			}
		});

		JPanel pnlTrongTam = new JPanel();
		pnlChinh.add(pnlTrongTam, BorderLayout.CENTER);
		pnlTrongTam.setLayout(new BorderLayout(0, 0));

		JPanel pnlTieuDe = new JPanel();
		pnlTieuDe.setBorder(new LineBorder(new Color(135, 206, 250), 3));
		pnlTieuDe.setBackground(new Color(0, 191, 255));
		pnlTieuDe.setPreferredSize(
				new Dimension(setSizeByPercent.getWidthByPercent(75), setSizeByPercent.getHeightByPercent(5)));
		pnlTrongTam.add(pnlTieuDe, BorderLayout.NORTH);

		JLabel lblNewLabel_1 = new JLabel("Quản lý thông tin ở trọ của Sinh viên");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 25));
		pnlTieuDe.add(lblNewLabel_1);

		JPanel pnlTrongTamChinh = new JPanel();
		pnlTrongTam.add(pnlTrongTamChinh, BorderLayout.CENTER);
		pnlTrongTamChinh.setLayout(new BorderLayout(0, 0));

		JPanel pnlNhapLieu = new JPanel();
		pnlNhapLieu.setBorder(new LineBorder(new Color(135, 206, 250), 2, true));
		pnlNhapLieu.setLayout(new GridLayout(5, 2));

		JPanel pnlMaSinhVien = new JPanel();
		pnlMaSinhVien.setBorder(new EmptyBorder(3, 3, 3, 3));
		pnlNhapLieu.add(pnlMaSinhVien);
		pnlMaSinhVien.setLayout(new BorderLayout(0, 0));

		JLabel lblMaSinhVien = new JLabel("Mã Sinh viên: ");
		lblMaSinhVien.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlMaSinhVien.add(lblMaSinhVien, BorderLayout.WEST);

		txtMaSinhVien = new JTextField();
		txtMaSinhVien.setEditable(false);
		txtMaSinhVien.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlMaSinhVien.add(txtMaSinhVien, BorderLayout.CENTER);
		txtMaSinhVien.setColumns(10);

		JPanel pnlTenSinhVien = new JPanel();
		pnlTenSinhVien.setBorder(new EmptyBorder(3, 3, 3, 3));
		pnlNhapLieu.add(pnlTenSinhVien);
		pnlTenSinhVien.setLayout(new BorderLayout(0, 0));

		JLabel lblTenSinhVien = new JLabel("Tên Sinh viên: ");
		lblTenSinhVien.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTenSinhVien.add(lblTenSinhVien, BorderLayout.WEST);

		txtTenSinhVien = new JTextField();
		txtTenSinhVien.setEditable(false);
		txtTenSinhVien.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTenSinhVien.add(txtTenSinhVien);
		txtTenSinhVien.setColumns(10);

		JPanel pnlMaNhaTro = new JPanel();
		pnlMaNhaTro.setBorder(new EmptyBorder(3, 3, 3, 3));
		pnlNhapLieu.add(pnlMaNhaTro);
		pnlMaNhaTro.setLayout(new BorderLayout(0, 0));

		JLabel lblMaNhaTro = new JLabel("Mã Nhà trọ: ");
		lblMaNhaTro.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlMaNhaTro.add(lblMaNhaTro, BorderLayout.WEST);

		txtMaNhaTro = new JTextField();
		txtMaNhaTro.setEditable(false);
		txtMaNhaTro.setFont(new Font("Arial", Font.PLAIN, 15));
		txtMaNhaTro.setText("");
		pnlMaNhaTro.add(txtMaNhaTro);
		txtMaNhaTro.setColumns(10);

		JPanel pnlTenChuTro = new JPanel();
		pnlTenChuTro.setBorder(new EmptyBorder(3, 3, 3, 3));
		pnlNhapLieu.add(pnlTenChuTro);
		pnlTenChuTro.setLayout(new BorderLayout(0, 0));

		JLabel lblTenChuNhaTro = new JLabel("Tên chủ Nhà trọ: ");
		lblTenChuNhaTro.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTenChuTro.add(lblTenChuNhaTro, BorderLayout.WEST);

		txtTenChuTro = new JTextField();
		txtTenChuTro.setEditable(false);
		txtTenChuTro.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTenChuTro.add(txtTenChuTro);
		txtTenChuTro.setColumns(10);

		JPanel pnlNgayBatDau = new JPanel();
		pnlNgayBatDau.setBorder(new EmptyBorder(3, 3, 3, 3));
		pnlNhapLieu.add(pnlNgayBatDau);
		pnlNgayBatDau.setLayout(new BorderLayout(0, 0));

		JLabel lblNgayBatDau = new JLabel("Ngày bắt đầu: ");
		lblNgayBatDau.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlNgayBatDau.add(lblNgayBatDau, BorderLayout.WEST);

		dateNgayBatDau = new JDateChooser();
		pnlNgayBatDau.add(dateNgayBatDau);

		JPanel pnlNgayCapNhat = new JPanel();
		pnlNgayCapNhat.setBorder(new EmptyBorder(3, 3, 3, 3));
		pnlNhapLieu.add(pnlNgayCapNhat);
		pnlNgayCapNhat.setLayout(new BorderLayout(0, 0));

		JLabel lblNgayCapNhat = new JLabel("Ngày cập nhật: ");
		lblNgayCapNhat.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlNgayCapNhat.add(lblNgayCapNhat, BorderLayout.WEST);

		dateNgayCapNhat = new JDateChooser();
		pnlNgayCapNhat.add(dateNgayCapNhat);
		dateNgayCapNhat.setDate(new Date());

		JPanel pnlNgayKetThuc = new JPanel();
		pnlNgayKetThuc.setBorder(new EmptyBorder(3, 3, 3, 3));
		pnlNhapLieu.add(pnlNgayKetThuc);
		pnlNgayKetThuc.setLayout(new BorderLayout(0, 0));

		JLabel lblNgayKetThuc = new JLabel("Ngày kết thúc: ");
		lblNgayKetThuc.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlNgayKetThuc.add(lblNgayKetThuc, BorderLayout.WEST);

		dateNgayKetThuc = new JDateChooser();
		pnlNgayKetThuc.add(dateNgayKetThuc);

		JPanel pnlGiaThue = new JPanel();
		pnlGiaThue.setBorder(new EmptyBorder(3, 3, 3, 3));
		pnlNhapLieu.add(pnlGiaThue);
		pnlGiaThue.setLayout(new BorderLayout(0, 0));

		JLabel lblGiaThue = new JLabel("Giá thuê: ");
		lblGiaThue.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlGiaThue.add(lblGiaThue, BorderLayout.WEST);

		txtGiaThue = new JTextField();
		txtGiaThue.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlGiaThue.add(txtGiaThue);
		txtGiaThue.setColumns(10);

		JPanel pnlTrangThai = new JPanel();
		pnlTrangThai.setBorder(new EmptyBorder(3, 3, 3, 3));
		pnlNhapLieu.add(pnlTrangThai);
		pnlTrangThai.setLayout(new BorderLayout(0, 0));

		JLabel lblTrangThai = new JLabel("Trạng Thái: ");
		lblTrangThai.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTrangThai.add(lblTrangThai, BorderLayout.WEST);

		cboTrangThai = new JComboBox<String>();
		cboTrangThai.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTrangThai.add(cboTrangThai);
		cboTrangThai.addItem("Đang Thuê");
		cboTrangThai.addItem("Không Còn Thuê");

		JPanel pnlDiaChi = new JPanel();
		pnlDiaChi.setBorder(new EmptyBorder(3, 3, 3, 3));
		pnlNhapLieu.add(pnlDiaChi);
		pnlDiaChi.setLayout(new BorderLayout(0, 0));

		JLabel lblDiaChi = new JLabel("Địa chỉ: ");
		lblDiaChi.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlDiaChi.add(lblDiaChi, BorderLayout.WEST);

		txtDiaChi = new JTextField();
		txtDiaChi.setEditable(false);
		txtDiaChi.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlDiaChi.add(txtDiaChi);
		txtDiaChi.setColumns(10);

		lblDiaChi.setPreferredSize(lblTenChuNhaTro.getPreferredSize());
		lblGiaThue.setPreferredSize(lblTenChuNhaTro.getPreferredSize());
		lblMaNhaTro.setPreferredSize(lblTenChuNhaTro.getPreferredSize());
		lblMaSinhVien.setPreferredSize(lblTenChuNhaTro.getPreferredSize());
		lblNgayBatDau.setPreferredSize(lblTenChuNhaTro.getPreferredSize());
		lblNgayCapNhat.setPreferredSize(lblTenChuNhaTro.getPreferredSize());
		lblNgayKetThuc.setPreferredSize(lblTenChuNhaTro.getPreferredSize());
		lblTrangThai.setPreferredSize(lblTenChuNhaTro.getPreferredSize());
		lblTenSinhVien.setPreferredSize(lblTenChuNhaTro.getPreferredSize());

		JPanel pnlNhaplieuVaTacVuVaTimKiem = new JPanel();
		pnlTrongTamChinh.add(pnlNhaplieuVaTacVuVaTimKiem, BorderLayout.NORTH);
		pnlNhaplieuVaTacVuVaTimKiem.setLayout(new BorderLayout(0, 0));
		pnlNhaplieuVaTacVuVaTimKiem.add(pnlNhapLieu, BorderLayout.CENTER);

		JPanel pnlTacVu = new JPanel();
		pnlTacVu.setBorder(new LineBorder(new Color(135, 206, 235), 3));
		pnlNhaplieuVaTacVuVaTimKiem.add(pnlTacVu, BorderLayout.EAST);
		pnlTacVu.setLayout(new GridLayout(5, 1));

		JPanel pnlTieuDeTacVu = new JPanel();
		pnlTieuDeTacVu.setBorder(new LineBorder(new Color(135, 206, 235), 3));
		pnlTieuDeTacVu.setBackground(new Color(0, 191, 255));
		pnlTacVu.add(pnlTieuDeTacVu);

		JLabel lblTacVu = new JLabel("Tác vụ");
		lblTacVu.setFont(new Font("Arial", Font.BOLD, 20));
		pnlTieuDeTacVu.add(lblTacVu);

		JPanel pnlthemThongTinOTro = new JPanel();
		pnlthemThongTinOTro.setBorder(new EmptyBorder(3, 3, 3, 3));
		pnlTacVu.add(pnlthemThongTinOTro);
		pnlthemThongTinOTro.setLayout(new BorderLayout(0, 0));

		btnCapNhatThongTin = new JButton("Cập nhật thông tin\r\n");
		btnCapNhatThongTin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCapNhatThongTin.setBackground(new Color(50, 205, 50));
		btnCapNhatThongTin.setFont(new Font("Arial", Font.BOLD, 20));
		pnlthemThongTinOTro.add(btnCapNhatThongTin);

		JPanel pnlXoaThongTinOTro = new JPanel();
		pnlXoaThongTinOTro.setBorder(new EmptyBorder(3, 3, 3, 3));
		pnlTacVu.add(pnlXoaThongTinOTro);
		pnlXoaThongTinOTro.setLayout(new BorderLayout(0, 0));

		btnXoaThongTin = new JButton("Xóa thông tin\r\n");
		btnXoaThongTin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnXoaThongTin.setBackground(new Color(240, 128, 128));
		btnXoaThongTin.setFont(new Font("Arial", Font.BOLD, 20));
		pnlXoaThongTinOTro.add(btnXoaThongTin);

		JPanel pnlSuaThongTinOTro = new JPanel();
		pnlSuaThongTinOTro.setBorder(new EmptyBorder(3, 3, 3, 3));
		pnlTacVu.add(pnlSuaThongTinOTro);
		pnlSuaThongTinOTro.setLayout(new BorderLayout(0, 0));

		btnSuaThongTin = new JButton("Sửa thông tin\r\n");
		btnSuaThongTin.setBackground(new Color(255, 215, 0));
		btnSuaThongTin.setFont(new Font("Arial", Font.BOLD, 20));
		pnlSuaThongTinOTro.add(btnSuaThongTin);

		JPanel pnlXoaTrang = new JPanel();
		pnlXoaTrang.setBorder(new EmptyBorder(3, 3, 3, 3));
		pnlTacVu.add(pnlXoaTrang);
		pnlXoaTrang.setLayout(new BorderLayout(0, 0));

		btnXoaTrang = new JButton("Xóa trắng\r\n");
		btnXoaTrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnXoaTrang.setBackground(new Color(0, 191, 255));
		btnXoaTrang.setFont(new Font("Arial", Font.BOLD, 20));
		pnlXoaTrang.add(btnXoaTrang);

		JPanel pnlTimKiem = new JPanel();
		pnlTimKiem.setBorder(new LineBorder(new Color(135, 206, 235), 3));
		pnlNhaplieuVaTacVuVaTimKiem.add(pnlTimKiem, BorderLayout.SOUTH);
		pnlTimKiem.setLayout(new GridLayout(1, 3));

		JPanel pnlTimKiemSinhVien = new JPanel();
		pnlTimKiemSinhVien.setBorder(new EmptyBorder(3, 3, 3, 3));
		pnlTimKiem.add(pnlTimKiemSinhVien);
		pnlTimKiemSinhVien.setLayout(new BorderLayout(0, 0));

		JLabel lblTimKiemSinhVien = new JLabel("Tìm Sinh viên: ");
		lblTimKiemSinhVien.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTimKiemSinhVien.add(lblTimKiemSinhVien, BorderLayout.WEST);

		btnTimKiemSinhVien = new JButton("Tìm kiếm");
		btnTimKiemSinhVien.setBackground(new Color(30, 144, 255));
		btnTimKiemSinhVien.setFont(new Font("Arial", Font.BOLD, 20));
		pnlTimKiemSinhVien.add(btnTimKiemSinhVien, BorderLayout.EAST);

		cboTimSinhVien = new JComboBox<String>();
		cboTimSinhVien.setEditable(true);
		pnlTimKiemSinhVien.add(cboTimSinhVien, BorderLayout.CENTER);

		JPanel pnlTimKiemNhaTro = new JPanel();
		pnlTimKiemNhaTro.setBorder(new EmptyBorder(3, 3, 3, 3));
		pnlTimKiemNhaTro.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTimKiem.add(pnlTimKiemNhaTro);
		pnlTimKiemNhaTro.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_2 = new JLabel("Tìm nhà trọ: ");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTimKiemNhaTro.add(lblNewLabel_2, BorderLayout.WEST);

		btnTimKiemTro = new JButton("Tìm kiếm");
		btnTimKiemTro.setBackground(new Color(30, 144, 255));
		btnTimKiemTro.setFont(new Font("Arial", Font.BOLD, 20));
		pnlTimKiemNhaTro.add(btnTimKiemTro, BorderLayout.EAST);

		cboTimNhaTro = new JComboBox<String>();
		cboTimNhaTro.setEditable(true);
		pnlTimKiemNhaTro.add(cboTimNhaTro, BorderLayout.CENTER);

		JPanel pnlThemTroMoi = new JPanel();
		pnlTimKiem.add(pnlThemTroMoi);
		pnlThemTroMoi.setLayout(new GridLayout(1, 2));

		JPanel pnlThemTro = new JPanel();
		pnlThemTro.setBorder(new EmptyBorder(3, 3, 3, 3));
		pnlThemTroMoi.add(pnlThemTro);
		pnlThemTro.setLayout(new BorderLayout(0, 0));

		btnThemTro = new JButton("Thêm trọ mới");
		btnThemTro.setFont(new Font("Arial", Font.BOLD, 20));
		btnThemTro.setBackground(new Color(50, 205, 50));
		pnlThemTro.add(btnThemTro);

		JPanel pnlxemlichsu = new JPanel();
		pnlxemlichsu.setBorder(new EmptyBorder(3, 3, 3, 3));
		pnlThemTroMoi.add(pnlxemlichsu);
		pnlxemlichsu.setLayout(new BorderLayout(0, 0));

		btnXemLichSu = new JButton("Xem lịch sử ");
		btnXemLichSu.setFont(new Font("Arial", Font.BOLD, 20));
		btnXemLichSu.setBackground(new Color(30, 144, 255));
		pnlxemlichsu.add(btnXemLichSu, BorderLayout.CENTER);

		JPanel pnlBang = new JPanel();
		pnlTrongTamChinh.add(pnlBang, BorderLayout.CENTER);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setPreferredSize(new Dimension(10, 0));
		pnlChinh.add(horizontalStrut, BorderLayout.EAST);
		String[] header = { "Mã Sinh Viên", "Tên Sinh Viên", "Mã Nhà Trọ", "Tên Nhà Trọ", "Địa Chỉ",
				"Ngày Bắt Đầu Thuê", "Ngày Kết Thúc Thuê", "Giá", "Ngày Cập Nhật", "Trạng thái" };
		tableModelBTT = new DefaultTableModel(header, 0);
		pnlBang.setLayout(new BorderLayout(0, 0));
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
		tableBTT.setRowHeight(30);
		dateNgayBatDau.setDateFormatString("dd/MM/yyyy");
		dateNgayCapNhat.setDateFormatString("dd/MM/yyyy");
		dateNgayKetThuc.setDateFormatString("dd/MM/yyyy");
		dateNgayBatDau.setFont(new Font("arial", Font.PLAIN, 15));
		dateNgayCapNhat.setFont(new Font("arial", Font.PLAIN, 15));
		dateNgayKetThuc.setFont(new Font("arial", Font.PLAIN, 15));
		themDuLieuCoSan(tamluu_dao.layNhanVienTrongBangTamLuu().getMaNV(), loaiNV);

		listSV = new ArrayList<SinhVien>();
		if (sinhvien_dao.layLoaiNV().equals("QL")) {
			listSV = sinhvien_dao.layTatCaBangQL();
		} else if (sinhvien_dao.layLoaiNV().equals("NV")) {
			listSV = sinhvien_dao.layTatCaBangNV();
		} else {
			JOptionPane.showMessageDialog(null, "Lỗi loại NV!!");
		}
		DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		ArrayList<String> list = new ArrayList<String>();
		for (SinhVien v : listSV) {
			list.add(v.getChuyenNghanh().toString().trim());
			list.add(v.getGioiTinh().toString().trim());
			list.add(v.getMaLop().toString().trim());
			list.add(v.getMaSV().toString().trim());
			list.add(dt.format(v.getNgaySinh()));
			list.add(v.getQueQuanSV().toString().trim());
			list.add(v.getTenSV().toString().trim());
		}
		Set<String> set = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
		set.addAll(list);
		list = new ArrayList<String>(set);
		cboTimSinhVien.addItem("");
		list.forEach(v -> cboTimSinhVien.addItem(v));
		AutoCompleteDecorator.decorate(cboTimSinhVien);

		List<NhaTro> listnt = new ArrayList<NhaTro>();
		listnt = NhaTro_Dao.layTatCaBang();
		ArrayList<String> list1 = new ArrayList<String>();
		for (NhaTro nhaTro : listnt) {
			list1.add(nhaTro.getMaTro());
			list1.add(nhaTro.getSDT());
			list1.add(nhaTro.getTenChutro());
			DiaChi d = nhaTro.getDiaChiTro();
			String dc1 = d.getTenQuan()+", "+d.getTenPhuong()+", "+d.getTenDuong()+", "+d.getSoNha();
			list1.add(dc1);
			String dc2 = d.getSoNha()+", "+d.getTenDuong()+", "+d.getTenPhuong()+", "+d.getTenQuan();
			String dc3 = d.getTenQuan()+", "+d.getTenPhuong()+", "+d.getTenDuong();
			String dc4 = d.getTenQuan()+", "+d.getTenPhuong();
			String dc5 =  d.getTenQuan();
			String dc6 = d.getTenDuong()+", "+d.getTenPhuong()+", "+d.getTenQuan();
			String dc7 = d.getTenPhuong()+", "+d.getTenQuan();
			String dc8 = d.getTenPhuong();
			String dc9 = d.getTenDuong();
			list1.add(dc9);
			list1.add(dc8);
			list1.add(dc7);
			list1.add(dc6);
			list1.add(dc5);
			list1.add(dc4);
			list1.add(dc3);
			list1.add(dc2);
		}
		Set<String> set1 = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
		set1.addAll(list);
		list = new ArrayList<String>(set1);
		cboTimNhaTro.addItem("");
		list1.forEach(v->cboTimNhaTro.addItem(v));
		AutoCompleteDecorator.decorate(cboTimNhaTro);
		cboTimNhaTro.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					btnTimKiemTro.doClick();
				}
			}
		});
		
		
		
		cboTimSinhVien.getEditor().getEditorComponent().addKeyListener(this);
		tableBTT.addMouseListener(this);
		btnXoaTrang.addActionListener(this);
		btnCapNhatThongTin.addActionListener(this);
		btnXoaThongTin.addActionListener(this);
		btnSuaThongTin.addActionListener(this);
		txtDiaChi.addActionListener(this);
		txtGiaThue.addActionListener(this);
		txtMaNhaTro.addActionListener(this);
		txtMaSinhVien.addActionListener(this);
		txtTenChuTro.addActionListener(this);
		txtTenSinhVien.addActionListener(this);
		cboTrangThai.addActionListener(this);
		cboTimNhaTro.addActionListener(this);
		cboTimSinhVien.addActionListener(this);
		btnTimKiemSinhVien.addActionListener(this);
		btnTimKiemTro.addActionListener(this);
		btnThemTro.addActionListener(this);
		btnXemLichSu.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThoat)) {
			int n = JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát hay không?", "thoát",
					JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				String loaiNV = tamluu_dao.layNhanVienTrongBangTamLuu().getLoaiNV();
				if (loaiNV.equals("QL")) {
					removeAll();
					add(new GD_Admin());
					repaint();
					revalidate();
				} else if (loaiNV.equals("NV")) {
					removeAll();
					add(new GD_TrangChuNhanVienGVK());
					repaint();
					revalidate();
				}
			}
		} else if (o.equals(btnXoaTrang)) {
			txtDiaChi.setText("");
			txtGiaThue.setText("");
			txtMaNhaTro.setText("");
			txtMaSinhVien.setText("");
			txtTenChuTro.setText("");
			txtTenSinhVien.setText("");
			dateNgayBatDau.setCalendar(null);
			dateNgayCapNhat.setDate(new Date());
			dateNgayKetThuc.setCalendar(null);
			cboTrangThai.setSelectedIndex(0);
			cboTimNhaTro.setSelectedIndex(0);
			cboTimSinhVien.setSelectedIndex(0);
		} else if (o.equals(btnCapNhatThongTin)) {
			if (rangBuocDuLieuVao()) {
				ThongTinThueTro t = revertThuocFromTextFields();
				if (t.getTrangThai().equals("Đang Thuê")) {
					thongtinthuetro_dao.suaTrangThaiThueTro(t.getSinhVien().getMaSV());
				}
				if (thongtinthuetro_dao.themThongTinThueTro(t)) {
					JOptionPane.showMessageDialog(this, "Cập nhật quá thuê trọ mới thành công");
					tableBTT.setModel(tableModelBTT);
					int rowCount = tableModelBTT.getRowCount();
					for (int i = rowCount - 1; i >= 0; i--) {
						tableModelBTT.removeRow(i);
					}
					themDuLieuCoSan(tamluu_dao.layNhanVienTrongBangTamLuu().getMaNV().toString(), loaiNV);
				} else {
					JOptionPane.showMessageDialog(this, "Cập nhật quá thuê trọ mới không thành công");
				}
			} else if (o.equals(btnXoaThongTin)) {
				if (JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa dòng này", "Xác nhận xóa",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

					int n = tableBTT.getSelectedRowCount();
					for (int i = 0; i < n; i++) {
						int row = tableBTT.getSelectedRow();
						String maNhaTro = tableModelBTT.getValueAt(row, 2).toString();
						String maSinhVien = tableModelBTT.getValueAt(row, 0).toString();
						if (thongtinthuetro_dao.xoaThong(maSinhVien, maNhaTro)) {
							JOptionPane.showMessageDialog(this, "Đã xóa thành công");
							tableModelBTT.removeRow(row);
						} else {
							JOptionPane.showMessageDialog(this, "Xóa không thành công");
						}
					}
				}
			}
		} else if (o.equals(btnSuaThongTin)) {
			if (JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa dòng này", "Xác nhận sửa",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION && rangBuocDuLieuVao()) {
				int row = tableBTT.getSelectedRow();
				String maSV = tableModelBTT.getValueAt(row, 0).toString();
				String maNT = tableModelBTT.getValueAt(row, 2).toString();

				ThongTinThueTro t = revertThuocFromTextFields();
				if (t.getTrangThai().equals("Đang Thuê")) {
					thongtinthuetro_dao.suaTrangThaiThueTro(t.getSinhVien().getMaSV());
				}
				if (t != null && thongtinthuetro_dao.suaThongTinThueTro(maNT, maSV, t)) {
					tableBTT.setModel(tableModelBTT);
					int rowCount = tableModelBTT.getRowCount();
					// Remove rows one by one from the end of the table
					for (int i = rowCount - 1; i >= 0; i--) {
						tableModelBTT.removeRow(i);
					}
					themDuLieuCoSan(tamluu_dao.layNhanVienTrongBangTamLuu().getMaNV().toString(), loaiNV);
					JOptionPane.showMessageDialog(this, "Đã sửa thành công");
				} else
					JOptionPane.showMessageDialog(this, "Sửa không thành công");
			}
		} else if (o.equals(btnTimKiemSinhVien)) {
			String[] headerSinhVien = "Mã sinh viên;Tên sinh viên;Ngày sinh;Quê quán;Mã lớp;Khoa;Giới tính;Chuyên nghành"
					.split(";");
			tableModelSinhVien = new DefaultTableModel(headerSinhVien, 0);
			tableBTT.setModel(tableModelSinhVien);
			if (!cboTimSinhVien.getSelectedItem().equals("")) {
				String[] headerSinhVien1 = "Mã sinh viên;Tên sinh viên;Ngày sinh;Quê quán;Mã lớp;Khoa;Giới tính;Chuyên nghành"
						.split(";");
				tableModelSinhVien = new DefaultTableModel(headerSinhVien1, 0);
				tableBTT.setModel(tableModelSinhVien);
				ArrayList<SinhVien> dssv = thongtinthuetro_dao.timKiemSinhVienTrongBangThongTin(
						cboTimSinhVien.getSelectedItem().toString().trim(),
						tamluu_dao.layNhanVienTrongBangTamLuu().getMaNV(), loaiNV);
				if (dssv != null) {
					for (SinhVien sv : dssv) {
						tableModelSinhVien.addRow(new Object[] { sv.getMaSV(), sv.getTenSV(), sv.getNgaySinh(),
								sv.getQueQuanSV(), sv.getMaLop(), khoa, sv.getGioiTinh(), sv.getChuyenNghanh() });
					}
				} else {
					JOptionPane.showMessageDialog(this, "không tìm thấy");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Mời nhập vào thông tin cần tìm");
				cboTimSinhVien.setFocusable(true);
			}
		}else if (o.equals(btnTimKiemTro)) {
			if (!cboTimNhaTro.getSelectedItem().equals("")) {
				String[] header = "Mã nhà trọ;Tên chủ nhà;Địa chỉ;Số điện thoại".split(";");
				tableModelNhaTro = new DefaultTableModel(header, 0);
				tableBTT.setModel(tableModelNhaTro);
				ArrayList<NhaTro> dsnt = thongtinthuetro_dao.TimKiemTroTrenBangThongTin(cboTimNhaTro.getSelectedItem().toString().trim());
				for (NhaTro v : dsnt) {
					String diaChi = v.getDiaChiTro().getSoNha() + ", " + v.getDiaChiTro().getTenDuong() + ", "
							+ v.getDiaChiTro().getTenPhuong() + ", " + v.getDiaChiTro().getTenQuan();
					String[] row = { v.getMaTro(), v.getTenChutro(), diaChi, v.getSDT() };
					tableModelNhaTro.addRow(row);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Mời nhập vào từ khóa là mã nhà trọ");
			}
		}else if (o.equals(btnThemTro)) {
			JFrame frame = new JFrame();
			frame.getContentPane().add(new GD_ThemTroMoi());
			frame.pack();
			frame.setExtendedState(Frame.MAXIMIZED_BOTH);
			frame.setVisible(true);
		}else if (o.equals(btnXemLichSu)) {
			String maSV = JOptionPane.showInputDialog("Mời nhập vào mã sinh viên cần xem lịch sử thay đổi trọ");
			String[] header = { "Mã Sinh Viên", "Tên Sinh Viên", "Mã Nhà Trọ", "Tên Nhà Trọ", "Địa Chỉ",
					"Ngày Bắt Đầu Thuê", "Ngày Kết Thúc Thuê", "Giá", "Ngày Cập Nhật", "Trạng thái" };
			tableModelBTT2 = new DefaultTableModel(header, 0);
			tableBTT.setModel(tableModelBTT2);
			List<ThongTinThueTro> tt = thongtinthuetro_dao.xemLichSuTheoMaSinhVien(
					maSV, tamluu_dao.layNhanVienTrongBangTamLuu().getLoaiNV(),
					tamluu_dao.layNhanVienTrongBangTamLuu().getMaNV());
			if (tt == null) {
				JOptionPane.showMessageDialog(this,
						"Không tìm thấy lịch sử vinh viên có mã" + maSV);
			} else {
				for (ThongTinThueTro thongTinThueTro : tt) {
					themDongVaoBangThongTin(thongTinThueTro, tableModelBTT2);
				}
			}
		}
	}

	public void themDuLieuCoSan(String maNV, String loaiNV) {
		ArrayList<ThongTinThueTro> t = null;
		if (loaiNV.equals("QL")) {
			t = thongtinthuetro_dao.layTatCaDsThongTinThueTro();
		} else if (loaiNV.equals("NV")) {
			t = thongtinthuetro_dao.layTatCaDsThongTinThueTroTheoMaNhanVien(maNV);
		}
		for (ThongTinThueTro thongTinThueTro : t) {
			themDongVaoBangThongTin(thongTinThueTro, tableModelBTT);
		}
	}

	public void themDongVaoBangThongTin(ThongTinThueTro t, DefaultTableModel model) {
		SinhVien sv = sinhvien_dao.laySinhVienTheoMa(t.getSinhVien().getMaSV(),
				tamluu_dao.layNhanVienTrongBangTamLuu().getMaNV(), loaiNV);
		NhaTro nt = NhaTro_Dao.layTroTheoMaCuaVanVinh(t.getNhaTro().getMaTro());
		String diachi = nt.getDiaChiTro().getSoNha() + ", " + nt.getDiaChiTro().getTenDuong() + ", "
				+ nt.getDiaChiTro().getTenPhuong() + ", " + nt.getDiaChiTro().getTenQuan();
		DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DecimalFormat df = new DecimalFormat("###.0");
		model.addRow(new Object[] { t.getSinhVien().getMaSV(), sv.getTenSV(), t.getNhaTro().getMaTro(),
				nt.getTenChutro(), diachi, dt.format(t.getNgayBatDau()), dt.format(t.getNgayKetThuc()),
				df.format(t.getGiaThue()), dt.format(t.getNgayCapNhat()), t.getTrangThai() });
	}

	@Override
	public void mouseClicked(MouseEvent e) {
			int row = tableBTT.getSelectedRow();
		if (tableBTT.getModel() == tableModelBTT || tableBTT.getModel() == tableModelBTT2) {
			
			txtMaSinhVien.setText(tableBTT.getValueAt(row, 0).toString());
			txtTenSinhVien.setText(tableBTT.getValueAt(row, 1).toString());
			txtMaNhaTro.setText(tableBTT.getValueAt(row, 2).toString());
			txtTenChuTro.setText(tableBTT.getValueAt(row, 3).toString());
			txtDiaChi.setText(tableBTT.getValueAt(row, 4).toString());
			txtGiaThue.setText(tableBTT.getValueAt(row, 7).toString());
			String[] ngayDB = tableBTT.getValueAt(row, 5).toString().split("/");

			Calendar ca = new GregorianCalendar();
			String day = ngayDB[0];
			String month = ngayDB[1];
			String year = ngayDB[2];

			if (day.length() == 1) {
				day = "0" + day;
			}
			if (month.length() == 1) {
				month = "0" + month;
			}

			String dd = year + "-" + month + "-" + day;

			Date d;
			try {
				d = new SimpleDateFormat("yyyy-MM-dd").parse(dd);
				dateNgayBatDau.setDate(d);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
//			txtNgayKetThuc.setText(tableBTT.getValueAt(row, 6).toString());
			String[] ngayKT = tableBTT.getValueAt(row, 6).toString().split("/");

			Calendar cakt = new GregorianCalendar();
			String daykt = ngayKT[0];
			String monthkt = ngayKT[1];
			String yearkt = ngayKT[2];

			if (daykt.length() == 1) {
				daykt = "0" + daykt;
			}
			if (monthkt.length() == 1) {
				monthkt = "0" + monthkt;
			}

			String ddkt = yearkt + "-" + monthkt + "-" + daykt;

			Date dkt;
			try {
				dkt = new SimpleDateFormat("yyyy-MM-dd").parse(ddkt);
				dateNgayKetThuc.setDate(dkt);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
//			txtNgayCapNhat.setText(tableBTT.getValueAt(row, 8).toString());
			String[] ngayCN = tableBTT.getValueAt(row, 8).toString().split("/");

			Calendar cacn = new GregorianCalendar();
			String daycn = ngayCN[0];
			String monthcn = ngayCN[1];
			String yearcn = ngayCN[2];

			if (daycn.length() == 1) {
				daycn = "0" + daycn;
			}
			if (monthcn.length() == 1) {
				monthcn = "0" + monthcn;
			}

			String ddcn = yearcn + "-" + monthcn + "-" + daycn;

			Date dcn;
			try {
				dcn = new SimpleDateFormat("yyyy-MM-dd").parse(ddcn);
				dateNgayCapNhat.setDate(dcn);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			cboTrangThai.setSelectedItem(tableBTT.getValueAt(row, 9));
		}else if (tableBTT.getModel()== tableModelSinhVien) {
			txtMaSinhVien.setText(tableBTT.getValueAt(row, 0).toString().trim());
			txtTenSinhVien.setText(tableBTT.getValueAt(row, 1).toString().trim());
		}else if (tableBTT.getModel()==tableModelNhaTro) {
			txtMaNhaTro.setText(tableBTT.getValueAt(row, 0).toString().trim());
			txtTenChuTro.setText(tableBTT.getValueAt(row, 1).toString().trim());
			txtDiaChi.setText(tableBTT.getValueAt(row, 2).toString().trim());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public ThongTinThueTro revertThuocFromTextFields() {
		double giaThue = Double.parseDouble(txtGiaThue.getText().trim());
		LocalDate ngayBatDau = null;
		try {
			ngayBatDau = LocalDate.of(dateNgayBatDau.getJCalendar().getYearChooser().getYear(),
					(dateNgayBatDau.getJCalendar().getMonthChooser().getMonth() + 1),
					dateNgayBatDau.getJCalendar().getDayChooser().getDay());
		} catch (DateTimeParseException e) {
			JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải theo dạng dd/MM/yyyy");
			dateNgayBatDau.setDate(null);
			dateNgayBatDau.setFocusable(true);
		}
		LocalDate ngayKetThuc = null;
		try {
			ngayKetThuc = LocalDate.of(dateNgayKetThuc.getJCalendar().getYearChooser().getYear(),
					(dateNgayKetThuc.getJCalendar().getMonthChooser().getMonth() + 1),
					dateNgayKetThuc.getJCalendar().getDayChooser().getDay());
		} catch (DateTimeParseException e) {
			JOptionPane.showMessageDialog(this, "Ngày kết thúc phải theo dạng dd/MM/yyyy");
			dateNgayBatDau.setDate(null);
			dateNgayBatDau.setFocusable(true);
		}
		
		LocalDate ngayCapNhat = null;
		try {
			ngayCapNhat = LocalDate.of(dateNgayCapNhat.getJCalendar().getYearChooser().getYear(),
					(dateNgayCapNhat.getJCalendar().getMonthChooser().getMonth() + 1),
					dateNgayCapNhat.getJCalendar().getDayChooser().getDay());

		} catch (DateTimeParseException e) {
			JOptionPane.showMessageDialog(this, "Ngày cập nhật phải theo dạng dd/MM/yyyy");
			dateNgayBatDau.setDate(null);
			dateNgayBatDau.setFocusable(true);
		}

		NhaTro nt = new NhaTro(txtMaNhaTro.getText().toString().trim());
		SinhVien sv = new SinhVien(txtMaSinhVien.getText().toString().trim());
		String trangthai = cboTrangThai.getSelectedItem().toString().trim();
		return new ThongTinThueTro(giaThue, ngayBatDau, ngayKetThuc, ngayCapNhat, trangthai, nt, sv);
	}

	public boolean rangBuocDuLieuVao() {
		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
		String ngayBD = dt.format(dateNgayBatDau.getDate());
		String ngayKT = dt.format(dateNgayKetThuc.getDate());
		String ngayCN = dt.format(dateNgayCapNhat.getDate());

		String gia = txtGiaThue.getText().trim().toString();
		String maSV = txtMaNhaTro.getText().trim().toString();
		String maNT = txtMaNhaTro.getText().trim().toString();
		
		if (maSV.equals(null)) {
			JOptionPane.showMessageDialog(this, "Bạn phải chọn sinh viên trước đã");
		}
		if (maNT.equals(null)) {
			JOptionPane.showMessageDialog(this, "Bạn phải chọn nhà trọ trước đã");
		}
		if (!(ngayBD.length() > 0)) {
			JOptionPane.showMessageDialog(this, "Ngày sinh không được bỏ trống");
			return false;
		}
		if (!(ngayKT.length() > 0)) {
			JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được bỏ trống");
			return false;
		}
		if (!(ngayCN.length() > 0)) {
			JOptionPane.showMessageDialog(this, "Ngày cập nhật không được bỏ trống");
			return false;
		}
		if (!(gia.length() > 0)) {
			JOptionPane.showMessageDialog(this, "Giá không được bỏ trống");
			dateNgayBatDau.requestFocus();
			return false;
		}
		LocalDate ngayBatDau = null;
		try {
			ngayBatDau = LocalDate.of(dateNgayBatDau.getJCalendar().getYearChooser().getYear(),
					(dateNgayBatDau.getJCalendar().getMonthChooser().getMonth() + 1),
					dateNgayBatDau.getJCalendar().getDayChooser().getDay());
		} catch (DateTimeParseException e) {
			JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải theo dạng dd/MM/yyyy");
			dateNgayBatDau.setDate(null);
			dateNgayBatDau.setFocusable(true);
			return false;
		}
		LocalDate ngayKetThuc = null;
		try {
			ngayKetThuc = LocalDate.of(dateNgayKetThuc.getJCalendar().getYearChooser().getYear(),
					(dateNgayKetThuc.getJCalendar().getMonthChooser().getMonth() + 1),
					dateNgayKetThuc.getJCalendar().getDayChooser().getDay());
		} catch (DateTimeParseException e) {
			JOptionPane.showMessageDialog(this, "Ngày kết thúc phải theo dạng dd/MM/yyyy");
			dateNgayBatDau.setDate(null);
			dateNgayBatDau.setFocusable(true);
			return false;
		}
		
		if (ngayBatDau.isAfter(ngayKetThuc)) {
			JOptionPane.showMessageDialog(this, "Ngày bắt đầu trước ngày kết thúc!");
			dateNgayKetThuc.setFocusable(true);
			return false;
		}
		try {
			double giathue = Double.parseDouble(gia);
			if (giathue < 0) {
				JOptionPane.showMessageDialog(this, "Giá thuê phải lớn hoặc bằng 0");
				txtGiaThue.selectAll();
				txtGiaThue.setFocusable(true);
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "Giá phải là số");
			txtGiaThue.selectAll();
			txtGiaThue.setFocusable(true);
			return false;
		}

		return true;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		Object o = e.getSource();
		if (cboTimSinhVien.isFocusable()) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				btnTimKiemSinhVien.doClick();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
