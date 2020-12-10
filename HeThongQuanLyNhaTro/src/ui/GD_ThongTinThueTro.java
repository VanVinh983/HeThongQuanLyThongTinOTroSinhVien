package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
import com.toedter.calendar.JDateChooser;

public class GD_ThongTinThueTro extends JPanel implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModelBTT;
	private JTable tableBTT;
	private JTextField txtTPMaSinhVien;
	private JTextField txtTPTenSinhVien;
	private JTextField txtTPMaNhaTro;
	private JTextField txtTPTenChuNhaTro;
	private JTextField txtTuKhoa;
	private JComboBox<String> cboQuan;
	private JComboBox<String> cboPhuong;
	private JComboBox<String> cboDuong;
	private JComboBox<String> cboSoNha;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnXoaTrang;
	private JComboBox<String> cboLuaChon;
	private JButton btnTim;
	private TamLuuMaNhanVien_Dao tamluu_dao;
	private SinhVien_Dao sinhvien_dao;
	private NhaTro_Dao NhaTro_Dao;
	private ThongTinThueTro_Dao thongtinthuetro_dao;
	private JButton btnThemTroMoi;
	private DefaultTableModel tableModelSinhVien;
	private String khoa;
	private DefaultTableModel tableModelNhaTro;
	private JTextField txtGiaThue;
	private JComboBox cboTrangThai;
	private DefaultTableModel tableModelBTT2;
	private String loaiNV;
	private JDateChooser txtNgayBatDau;
	private JDateChooser txtNgayKetThuc;
	private JDateChooser txtNgayCapNhat;
	private JButton btnThoat;

	/**
	 * Launch the application.
	 * 
	 * /** Create the application.
	 */
	public GD_ThongTinThueTro() {

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

		setAutoscrolls(true);
		setBorder(null);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height - 20;
		int screenWidth = screenSize.width + 15;
		this.setPreferredSize(new Dimension(screenWidth, screenHeight - 58));
		setLayout(new BorderLayout(0, 0));
		loaiNV = tamluu_dao.layNhanVienTrongBangTamLuu().getLoaiNV();
		khoa = tamluu_dao.layNhanVienTrongBangTamLuu().getTenKhoa();

		ImageIcon imgUser = new ImageIcon(
				new ImageIcon("HinhAnh/User.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		ImageIcon imgSV = new ImageIcon(
				new ImageIcon("HinhAnh/sinhvien.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon imgNV = new ImageIcon(
				new ImageIcon("HinhAnh/nhanvien.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon imgTK = new ImageIcon(
				new ImageIcon("HinhAnh/thongke.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon imgBTT = new ImageIcon(
				new ImageIcon("HinhAnh/ghichu1.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon imgTro = new ImageIcon(
				new ImageIcon("HinhAnh/nhatro.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon imgexit = new ImageIcon(
				new ImageIcon("HinhAnh/exit.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon imgHDSD = new ImageIcon(
				new ImageIcon("HinhAnh/User manual.jpg").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));

		JPanel pnlTrai = new JPanel();
		pnlTrai.setBackground(Color.WHITE);
		pnlTrai.setBorder(null);
		pnlTrai.setPreferredSize(new Dimension(200, 600));
		pnlTrai.setMinimumSize(new Dimension(300, 600));
		pnlTrai.setMaximumSize(new Dimension(300, 600));
		add(pnlTrai, BorderLayout.WEST);
		pnlTrai.setLayout(new BoxLayout(pnlTrai, BoxLayout.Y_AXIS));

		JPanel pnlUser = new JPanel();
		pnlUser.setPreferredSize(new Dimension(200, 200));
		pnlTrai.add(pnlUser);
		pnlUser.setLayout(new BorderLayout(0, 0));

		JPanel pnlChucVu = new JPanel();
		pnlChucVu.setBackground(Color.CYAN);
		pnlUser.add(pnlChucVu, BorderLayout.NORTH);

		JLabel lblChucVu = new JLabel("Quản Lý\r\n");
		lblChucVu.setForeground(Color.GRAY);
		lblChucVu.setFont(new Font("Arial", Font.BOLD, 27));

		if (loaiNV.equals("NV")) {
			lblChucVu.setText("    Giáo Vụ Khoa: " + khoa);
			lblChucVu.setFont(new Font("Arial", 1, 15));
		}

		pnlChucVu.add(lblChucVu);

		Component verticalStrut_16 = Box.createVerticalStrut(20);
		verticalStrut_16.setPreferredSize(new Dimension(0, 38));
		verticalStrut_16.setMinimumSize(new Dimension(0, 50));
		pnlChucVu.add(verticalStrut_16);

		JLabel lblAnhUser = new JLabel("");
		lblAnhUser.setPreferredSize(new Dimension(200, 200));
		pnlUser.add(Box.createHorizontalStrut(25), BorderLayout.WEST);
		pnlUser.add(lblAnhUser, BorderLayout.CENTER);
		lblAnhUser.setIcon(imgUser);

		JPanel pnlTacVu = new JPanel();
		pnlTacVu.setFont(new Font("Arial", Font.BOLD, 20));
		pnlTacVu.setPreferredSize(new Dimension(200, 450));
		pnlTrai.add(pnlTacVu);
		pnlTacVu.setLayout(new BoxLayout(pnlTacVu, BoxLayout.Y_AXIS));

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

		if (loaiNV.equals("QL")) {
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

		JButton btnBTT = new JButton("Thuê Trọ" + "\r\n");
		btnBTT.setBorder(null);
		btnBTT.setBackground(Color.CYAN);
		btnBTT.setPreferredSize(new Dimension(170, 45));
		btnBTT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(new GD_ThongTinThueTro());
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
		btnHDSD.setFont(new Font("Arial", Font.BOLD, 16));
		btnHDSD.setIcon(imgHDSD);
		pnlHDSD.add(btnHDSD);

		JPanel pnlThoat = new JPanel();
		pnlThoat.setPreferredSize(new Dimension(200, 50));
		pnlTacVu.add(pnlThoat);

		btnThoat = new JButton("Thoát\r\n");
		btnThoat.setBorder(null);
		btnThoat.setBackground(Color.CYAN);
		btnThoat.setPreferredSize(new Dimension(170, 45));
	
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
		pnlRight.add(pnlBang, BorderLayout.SOUTH);

		String[] header = { "Mã Sinh Viên", "Tên Sinh Viên", "Mã Nhà Trọ", "Tên Nhà Trọ", "Địa Chỉ",
				"Ngày Bắt Đầu Thuê", "Ngày Kết Thúc Thuê", "Giá", "Ngày Cập Nhật", "Trạng thái" };
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
		tableBTT.setPreferredScrollableViewportSize(new Dimension(1140, 290));
		tableBTT.setRowHeight(30);

		JPanel pnlNhapThongTin = new JPanel();
		pnlNhapThongTin.setBackground(Color.CYAN);
		pnlNhapThongTin.setPreferredSize(new Dimension(1400, 320));
		pnlRight.add(pnlNhapThongTin, BorderLayout.CENTER);
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
		Component verticalStrut_9 = Box.createVerticalStrut(10);
		verticalStrut_9.setPreferredSize(new Dimension(0, 5));
		pnlThanhPhan.add(verticalStrut_9);
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
		Component verticalStrut_10 = Box.createVerticalStrut(10);
		verticalStrut_10.setPreferredSize(new Dimension(0, 5));
		pnlThanhPhan.add(verticalStrut_10);
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
		Component verticalStrut_11 = Box.createVerticalStrut(10);
		verticalStrut_11.setPreferredSize(new Dimension(0, 5));
		pnlThanhPhan.add(verticalStrut_11);
		pnlThanhPhan.add(pnlTPDiaChiTro);
		pnlTPDiaChiTro.setLayout(new BoxLayout(pnlTPDiaChiTro, BoxLayout.X_AXIS));

		Component horizontalStrut_7 = Box.createHorizontalStrut(20);
		pnlTPDiaChiTro.add(horizontalStrut_7);

		JLabel lblQuan = new JLabel("Quận:\r\n \r\n");
		lblQuan.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTPDiaChiTro.add(lblQuan);

		cboQuan = new JComboBox<String>();
		cboQuan.setMinimumSize(new Dimension(20, 22));
		cboQuan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<DiaChi> diachi = NhaTro_Dao.layDiaChiTheoQuan(cboQuan.getSelectedItem().toString().trim());
				cboPhuong.removeAllItems();
				cboDuong.removeAllItems();
				cboSoNha.removeAllItems();
				ArrayList<String> arrTenPhuong = new ArrayList<String>();
				diachi.forEach(v -> {
					if (!arrTenPhuong.contains(v.getTenPhuong().trim())) {
						cboPhuong.addItem(v.getTenPhuong().trim());
						arrTenPhuong.add(v.getTenPhuong().trim());
					}
				});
			}
		});
		cboQuan.setFont(new Font("Arial", Font.PLAIN, 15));
		cboQuan.setEditable(true);
		AutoCompleteDecorator.decorate(cboQuan);
		pnlTPDiaChiTro.add(cboQuan);

		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		pnlTPDiaChiTro.add(horizontalStrut_4);

		JLabel lblNewLabel = new JLabel("Phường: \r\n");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTPDiaChiTro.add(lblNewLabel);

		cboPhuong = new JComboBox<String>();
		cboPhuong.setMinimumSize(new Dimension(20, 22));
		try {
			cboPhuong.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (cboPhuong.getSelectedItem() != null) {
						ArrayList<DiaChi> diachi = NhaTro_Dao.layDiaChiTheoPhuongVaQuan(
								cboPhuong.getSelectedItem().toString().trim(),
								cboQuan.getSelectedItem().toString().trim());
						cboDuong.removeAllItems();
						cboSoNha.removeAllItems();
						ArrayList<String> arrTenDuong = new ArrayList<String>();
						for (DiaChi dc : diachi) {

							if (!arrTenDuong.contains(dc.getTenDuong())) {
								cboDuong.addItem(dc.getTenDuong());
								arrTenDuong.add(dc.getTenDuong());
							}
						}
					}
				}
			});
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		cboPhuong.setFont(new Font("Arial", Font.PLAIN, 15));
		cboPhuong.setEditable(true);
		AutoCompleteDecorator.decorate(cboPhuong);
		pnlTPDiaChiTro.add(cboPhuong);

		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		pnlTPDiaChiTro.add(horizontalStrut_5);

		JLabel lblDuong = new JLabel("Đường: ");
		lblDuong.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTPDiaChiTro.add(lblDuong);

		cboDuong = new JComboBox<String>();
		cboDuong.setMinimumSize(new Dimension(20, 22));
		cboDuong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (cboDuong.getSelectedItem() != null && cboPhuong.getSelectedItem() != null) {
					ArrayList<DiaChi> diachi = NhaTro_Dao.layDiaChiTheoQuanPhuongDuong(
							cboPhuong.getSelectedItem().toString().trim(), cboQuan.getSelectedItem().toString().trim(),
							cboDuong.getSelectedItem().toString().trim());
					cboSoNha.removeAllItems();
					ArrayList<String> arrSoNha = new ArrayList<String>();
					for (DiaChi dc : diachi) {
						if (!arrSoNha.contains(dc.getSoNha().trim())) {
							cboSoNha.addItem(dc.getSoNha().toString().trim());
							arrSoNha.add(dc.getSoNha().toString().trim());
						}
					}
				}
			}
		});
		cboDuong.setFont(new Font("Arial", Font.PLAIN, 15));
		cboDuong.setEditable(true);
		AutoCompleteDecorator.decorate(cboDuong);
		pnlTPDiaChiTro.add(cboDuong);

		Component horizontalStrut_6 = Box.createHorizontalStrut(20);
		pnlTPDiaChiTro.add(horizontalStrut_6);

		JLabel lblSoNha = new JLabel("Số Nhà: ");
		lblSoNha.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTPDiaChiTro.add(lblSoNha);

		cboSoNha = new JComboBox<String>();
		cboSoNha.setMinimumSize(new Dimension(20, 22));
		cboSoNha.setFont(new Font("Arial", Font.PLAIN, 15));
		cboSoNha.setEditable(true);
		AutoCompleteDecorator.decorate(cboSoNha);
		pnlTPDiaChiTro.add(cboSoNha);

		Component horizontalStrut_8 = Box.createHorizontalStrut(20);
		pnlTPDiaChiTro.add(horizontalStrut_8);

		JPanel pnlGia_NgayCapNhat = new JPanel();
		Component verticalStrut_12 = Box.createVerticalStrut(10);
		verticalStrut_12.setPreferredSize(new Dimension(0, 5));
		pnlThanhPhan.add(verticalStrut_12);
		pnlThanhPhan.add(pnlGia_NgayCapNhat);
		pnlGia_NgayCapNhat.setLayout(new BoxLayout(pnlGia_NgayCapNhat, BoxLayout.X_AXIS));

		Component horizontalStrut_11 = Box.createHorizontalStrut(20);
		pnlGia_NgayCapNhat.add(horizontalStrut_11);

		JLabel lblTrangThai = new JLabel("Trạng Thái: ");
		lblTrangThai.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlGia_NgayCapNhat.add(lblTrangThai);

		cboTrangThai = new JComboBox();
		cboTrangThai.setModel(new DefaultComboBoxModel(new String[] { "Đang Thuê", "Không Còn Thuê" }));
		cboTrangThai.setPreferredSize(new Dimension(190, 22));
		cboTrangThai.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlGia_NgayCapNhat.add(cboTrangThai);

		Component horizontalStrut_12 = Box.createHorizontalStrut(20);
		horizontalStrut_12.setPreferredSize(new Dimension(30, 0));
		pnlGia_NgayCapNhat.add(horizontalStrut_12);

		JLabel lblNgayCapNhat = new JLabel("Ngày Cập Nhật: \r\n");
		lblNgayCapNhat.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlGia_NgayCapNhat.add(lblNgayCapNhat);

		txtNgayCapNhat = new JDateChooser();
		txtNgayCapNhat.setDate(new Date());
		txtNgayCapNhat.setDateFormatString("dd/MM/yyyy");
		txtNgayCapNhat.setFont(new Font("arial", Font.PLAIN, 15));
		pnlGia_NgayCapNhat.add(txtNgayCapNhat);

		Component horizontalGlue = Box.createHorizontalGlue();
		pnlGia_NgayCapNhat.add(horizontalGlue);

		Component horizontalStrut_13 = Box.createHorizontalStrut(20);
		pnlGia_NgayCapNhat.add(horizontalStrut_13);

		JPanel pnlNgayBatDau_KetThuc = new JPanel();
		Component verticalStrut_13 = Box.createVerticalStrut(10);
		verticalStrut_13.setPreferredSize(new Dimension(0, 5));
		pnlThanhPhan.add(verticalStrut_13);
		pnlThanhPhan.add(pnlNgayBatDau_KetThuc);
		Component verticalStrut_14 = Box.createVerticalStrut(20);
		verticalStrut_14.setPreferredSize(new Dimension(0, 5));
		pnlThanhPhan.add(verticalStrut_14);
		pnlNgayBatDau_KetThuc.setLayout(new BoxLayout(pnlNgayBatDau_KetThuc, BoxLayout.X_AXIS));

		Component horizontalStrut_14 = Box.createHorizontalStrut(20);
		pnlNgayBatDau_KetThuc.add(horizontalStrut_14);

		JLabel lblNgayBatDau = new JLabel("Ngày Bắt Đầu Thuê: ");
		lblNgayBatDau.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlNgayBatDau_KetThuc.add(lblNgayBatDau);

		txtNgayBatDau = new JDateChooser();
		txtNgayBatDau.setDateFormatString("dd/MM/yyyy");
		txtNgayBatDau.setFont(new Font("arial", Font.PLAIN, 15));
		pnlNgayBatDau_KetThuc.add(txtNgayBatDau);

		Component horizontalStrut_15 = Box.createHorizontalStrut(20);
		horizontalStrut_15.setPreferredSize(new Dimension(30, 0));
		pnlNgayBatDau_KetThuc.add(horizontalStrut_15);

		JLabel lblNgayKetThuc = new JLabel("Ngày Kết Thúc Thuê: ");
		lblNgayKetThuc.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlNgayBatDau_KetThuc.add(lblNgayKetThuc);

		txtNgayKetThuc = new JDateChooser();
		txtNgayKetThuc.setDateFormatString("dd/MM/yyyy");
		txtNgayKetThuc.setFont(new Font("arial", Font.PLAIN, 15));
		pnlNgayBatDau_KetThuc.add(txtNgayKetThuc);

		Component horizontalStrut_16 = Box.createHorizontalStrut(20);
		pnlNgayBatDau_KetThuc.add(horizontalStrut_16);

		lblTPMaNhaTro.setPreferredSize(lblNgayKetThuc.getPreferredSize());
		lblTPTenSinhVien.setPreferredSize(lblNgayKetThuc.getPreferredSize());
		lblTenChuNha.setPreferredSize(lblNgayKetThuc.getPreferredSize());
		lblTPMaSinhVien.setPreferredSize(lblNgayKetThuc.getPreferredSize());
		lblTrangThai.setPreferredSize(lblNgayKetThuc.getPreferredSize());
		lblNgayCapNhat.setPreferredSize(lblNgayKetThuc.getPreferredSize());
		lblNgayBatDau.setPreferredSize(lblNgayKetThuc.getPreferredSize());

		JPanel pnlTimKiem = new JPanel();
		pnlTimKiem.setPreferredSize(new Dimension(400, 10));
		pnlNhapThongTin.add(pnlTimKiem, BorderLayout.EAST);
		pnlTimKiem.setLayout(new BorderLayout(0, 0));

		JPanel pnlTieuDeTimKiem = new JPanel();
		pnlTieuDeTimKiem.setPreferredSize(new Dimension(10, 50));
		pnlTieuDeTimKiem.setBackground(Color.GRAY);
		pnlTimKiem.add(pnlTieuDeTimKiem, BorderLayout.NORTH);

		JLabel lblTieuDeTimKiem = new JLabel("Tác Vụ\r\n");
		lblTieuDeTimKiem.setForeground(Color.CYAN);
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
		btnThem.setPreferredSize(new Dimension(110, 30));
		pnlThem.add(btnThem);
		btnThem.setFont(new Font("Arial", Font.BOLD, 15));

		Component verticalStrut_4 = Box.createVerticalStrut(20);
		verticalStrut_4.setMinimumSize(new Dimension(0, 10));
		verticalStrut_4.setPreferredSize(new Dimension(0, 10));
		pnlTacVuBenTrai.add(verticalStrut_4);

		JPanel pnlXoa = new JPanel();
		pnlTacVuBenTrai.add(pnlXoa);

		btnXoa = new JButton("Xóa\r\n");
		btnXoa.setPreferredSize(new Dimension(110, 30));
		pnlXoa.add(btnXoa);
		btnXoa.setFont(new Font("Arial", Font.BOLD, 15));

		Component verticalStrut_5 = Box.createVerticalStrut(20);
		verticalStrut_5.setPreferredSize(new Dimension(0, 10));
		verticalStrut_5.setMinimumSize(new Dimension(0, 10));
		pnlTacVuBenTrai.add(verticalStrut_5);

		JPanel pnlSua = new JPanel();
		pnlTacVuBenTrai.add(pnlSua);

		btnSua = new JButton("Sửa\r\n");
		btnSua.setPreferredSize(new Dimension(110, 30));
		pnlSua.add(btnSua);
		btnSua.setFont(new Font("Arial", Font.BOLD, 15));

		Component verticalStrut_6 = Box.createVerticalStrut(20);
		verticalStrut_6.setPreferredSize(new Dimension(0, 10));
		pnlTacVuBenTrai.add(verticalStrut_6);

		JPanel pnlXoaTrang = new JPanel();
		pnlTacVuBenTrai.add(pnlXoaTrang);

		btnXoaTrang = new JButton("Xóa Trắng\r\n");
		btnXoaTrang.setPreferredSize(new Dimension(110, 30));
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
		verticalStrut.setMinimumSize(new Dimension(0, 10));
		verticalStrut.setPreferredSize(new Dimension(0, 10));
		pnlTacVuTimKiem.add(verticalStrut);

		cboLuaChon = new JComboBox<String>();

		String[] luaChon = { "Tìm nhà trọ theo địa chỉ", "Tìm sinh viên theo mã", "Tìm nhà trọ theo mã",
				"Tìm nhà trọ theo số điện thoại", "Tìm nhà trọ theo tên chủ trọ", "Tìm sinh viên theo tên",
				"Xem lịch sử thay đổi trọ theo mã sinh viên" };

		for (String luachon : luaChon) {
			cboLuaChon.addItem(luachon);
		}

		cboLuaChon.setPreferredSize(new Dimension(30, 40));
		pnlTacVuTimKiem.add(cboLuaChon);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalStrut_1.setMinimumSize(new Dimension(0, 10));
		verticalStrut_1.setPreferredSize(new Dimension(0, 10));
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
		verticalStrut_2.setMinimumSize(new Dimension(0, 10));
		pnlTacVuTimKiem.add(verticalStrut_2);

		JPanel pnlNutTim = new JPanel();
		pnlTacVuTimKiem.add(pnlNutTim);

		btnTim = new JButton("Tìm Kiếm\r\n");
		btnTim.setPreferredSize(new Dimension(150, 40));
		btnTim.setFont(new Font("Arial", Font.BOLD, 15));
		pnlNutTim.add(btnTim);

		Component verticalStrut_3 = Box.createVerticalStrut(20);
		verticalStrut_3.setMinimumSize(new Dimension(0, 10));
		verticalStrut_3.setPreferredSize(new Dimension(0, 10));
		pnlTacVuTimKiem.add(verticalStrut_3);

		JPanel pnlThemTroMoi = new JPanel();
		pnlTacVuTimKiem.add(pnlThemTroMoi);

		btnThemTroMoi = new JButton("Thêm Trọ Mới");
		btnThemTroMoi.setPreferredSize(new Dimension(150, 40));
		btnThemTroMoi.setFont(new Font("Arial", Font.BOLD, 15));
		pnlThemTroMoi.add(btnThemTroMoi);

		Component verticalStrut_8 = Box.createVerticalStrut(20);
		verticalStrut_8.setMinimumSize(new Dimension(0, 10));
		pnlTacVuTimKiem.add(verticalStrut_8);

		Component horizontalStrut_17 = Box.createHorizontalStrut(20);
		pnlTacVuVaTimKiem.add(horizontalStrut_17, BorderLayout.EAST);

		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnTim.addActionListener(this);
		btnThemTroMoi.addActionListener(this);
		cboDuong.addActionListener(this);
		cboLuaChon.addActionListener(this);
		cboPhuong.addActionListener(this);
		cboQuan.addActionListener(this);
		cboSoNha.addActionListener(this);
		tableBTT.addMouseListener(this);
		cboTrangThai.addActionListener(this);
		btnThoat.addActionListener(this);

		DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		txtTPMaSinhVien.setEditable(false);
		txtTPTenSinhVien.setEditable(false);
		txtTPMaNhaTro.setEditable(false);
		txtTPTenChuNhaTro.setEditable(false);

		JPanel pnlGiaThue = new JPanel();
		pnlThanhPhan.add(pnlGiaThue);
		pnlGiaThue.setLayout(new BoxLayout(pnlGiaThue, BoxLayout.X_AXIS));

		Component horizontalStrut_18 = Box.createHorizontalStrut(20);
		horizontalStrut_18.setPreferredSize(new Dimension(200, 0));
		pnlGiaThue.add(horizontalStrut_18);

		JLabel lblGiaThue = new JLabel("Giá Thuê: ");
		lblGiaThue.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlGiaThue.add(lblGiaThue);

		txtGiaThue = new JTextField();
		txtGiaThue.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlGiaThue.add(txtGiaThue);
		txtGiaThue.setColumns(10);

		Component horizontalStrut_19 = Box.createHorizontalStrut(20);
		horizontalStrut_19.setPreferredSize(new Dimension(200, 0));
		pnlGiaThue.add(horizontalStrut_19);

		Component verticalStrut_15 = Box.createVerticalStrut(20);
		pnlThanhPhan.add(verticalStrut_15);

		themDuLieuCoSan(tamluu_dao.layNhanVienTrongBangTamLuu().getMaNV(), loaiNV);
		// đổ dữ liệu vào combobox

		List<NhaTro> listNhaTro = NhaTro_Dao.layTatCaBang();

		ArrayList<String> arrTenQuan = new ArrayList<String>();
		listNhaTro.forEach(v -> {
			if (!arrTenQuan.contains(v.getDiaChiTro().getTenQuan().trim())) {
				cboQuan.addItem(v.getDiaChiTro().getTenQuan().trim());
				arrTenQuan.add(v.getDiaChiTro().getTenQuan().trim());
			}
		});
		ArrayList<String> arrTenPhuong = new ArrayList<String>();
		listNhaTro.forEach(v -> {

			if (!arrTenPhuong.contains(v.getDiaChiTro().getTenPhuong().trim())) {
				cboPhuong.addItem(v.getDiaChiTro().getTenPhuong().trim());
				arrTenPhuong.add(v.getDiaChiTro().getTenPhuong().trim());
			}
		});

		ArrayList<String> arrTenDuong = new ArrayList<String>();
		listNhaTro.forEach(v -> {

			if (!arrTenDuong.contains(v.getDiaChiTro().getTenDuong().trim())) {
				cboDuong.addItem(v.getDiaChiTro().getTenDuong().trim());
				arrTenDuong.add(v.getDiaChiTro().getTenDuong().trim());
			}
		});
		ArrayList<String> arrsoNha = new ArrayList<String>();
		listNhaTro.forEach(v -> {

			if (!arrsoNha.contains(v.getDiaChiTro().getSoNha().trim())) {
				cboSoNha.addItem(v.getDiaChiTro().getSoNha().trim());
				arrsoNha.add(v.getDiaChiTro().getSoNha().trim());
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnXoaTrang)) {
			DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			txtGiaThue.setText("");
			txtNgayBatDau.setCalendar(null);
			txtNgayKetThuc.setCalendar(null);
			txtNgayCapNhat.setDate(new Date());
			txtTPMaNhaTro.setText("");
			txtTPMaSinhVien.setText("");
			txtTPTenChuNhaTro.setText("");
			txtTPTenSinhVien.setText("");
			txtTuKhoa.setText("mời nhập vào đây");
			cboLuaChon.setSelectedIndex(0);
			cboDuong.setSelectedIndex(0);
			cboQuan.setSelectedIndex(0);
			cboPhuong.setSelectedIndex(0);
			cboSoNha.setSelectedIndex(0);
			cboTrangThai.setSelectedIndex(0);
			txtGiaThue.requestFocus();
			tableBTT.setModel(tableModelBTT);
		} else if (o.equals(btnThemTroMoi)) {
			JOptionPane.showMessageDialog(this, new GD_ThemTroMoi());
		} else if (o.equals(btnTim)) {
			if (cboLuaChon.getSelectedItem().equals("Tìm sinh viên theo mã")) {
				if (txtTuKhoa.getText().toString().trim() != null
						|| !txtTuKhoa.getText().toString().trim().equals("mời nhập vào đây")) {
					String[] headerSinhVien = "Mã sinh viên;Tên sinh viên;Ngày sinh;Quê quán;Mã lớp;Khoa;Giới tính;Chuyên nghành"
							.split(";");
					tableModelSinhVien = new DefaultTableModel(headerSinhVien, 0);
					tableBTT.setModel(tableModelSinhVien);
					SinhVien sv = sinhvien_dao.laySinhVienTheoMa(txtTuKhoa.getText().toString().trim(),
							tamluu_dao.layNhanVienTrongBangTamLuu().getMaNV().trim().toString(), loaiNV);
					if (sv != null) {
						tableModelSinhVien.addRow(new Object[] { sv.getMaSV(), sv.getTenSV(), sv.getNgaySinh(),
								sv.getQueQuanSV(), sv.getMaLop(), khoa, sv.getGioiTinh(), sv.getChuyenNghanh() });
					} else {
						JOptionPane.showMessageDialog(this, "không tìm thấy");
					}
				} else {
					JOptionPane.showMessageDialog(this, "Mời nhập vào từ khóa là mã số sinh viên");
				}
			}
			if (cboLuaChon.getSelectedItem().equals("Tìm sinh viên theo tên")) {
				if (txtTuKhoa.getText().toString().trim() != null
						|| !txtTuKhoa.getText().toString().trim().equals("mời nhập vào đây")) {
					String[] headerSinhVien = "Mã sinh viên;Tên sinh viên;Ngày sinh;Quê quán;Mã lớp;Khoa;Giới tính;Chuyên nghành"
							.split(";");
					tableModelSinhVien = new DefaultTableModel(headerSinhVien, 0);
					tableBTT.setModel(tableModelSinhVien);
					List<SinhVien> dsSinhVien = sinhvien_dao.laySinhVienBangTen(txtTuKhoa.getText().toString().trim(),
							tamluu_dao.layNhanVienTrongBangTamLuu().getMaNV(),
							tamluu_dao.layNhanVienTrongBangTamLuu().getLoaiNV());
					if (dsSinhVien.size() > 0) {
						dsSinhVien.forEach(v -> {
							String ngaySinh = v.getNgaySinh().getDayOfMonth() + "/" + v.getNgaySinh().getMonthValue()
									+ "/" + v.getNgaySinh().getYear();
							String[] row = { v.getMaSV(), v.getTenSV(), ngaySinh, v.getQueQuanSV(), v.getMaLop(), khoa,
									v.getGioiTinh(), v.getChuyenNghanh() };
							tableModelSinhVien.addRow(row);
						});
					} else {
						JOptionPane.showMessageDialog(this, "Không tìm thấy sinh viên");
					}
				} else {
					JOptionPane.showMessageDialog(this, "Mời nhập vào từ khóa là tên sinh viên");
				}
			}
			if (cboLuaChon.getSelectedItem().equals("Tìm nhà trọ theo địa chỉ")) {
				if (txtTuKhoa.getText().toString().trim() != null
						|| !txtTuKhoa.getText().toString().trim().equals("mời nhập vào đây")) {
					String[] header = "Mã nhà trọ;Tên chủ nhà;Địa chỉ;Số điện thoại".split(";");
					tableModelNhaTro = new DefaultTableModel(header, 0);
					tableBTT.setModel(tableModelNhaTro);
					NhaTro v = NhaTro_Dao.layNhaTroTheoDia(cboPhuong.getSelectedItem().toString().trim(),
							cboQuan.getSelectedItem().toString().trim(), cboDuong.getSelectedItem().toString().trim(),
							cboSoNha.getSelectedItem().toString().trim());
					String diaChi = v.getDiaChiTro().getSoNha() + " - " + v.getDiaChiTro().getTenDuong() + " - "
							+ v.getDiaChiTro().getTenPhuong() + " - " + v.getDiaChiTro().getTenQuan();
					String[] row = { v.getMaTro(), v.getTenChutro(), diaChi, v.getSDT() };
					tableModelNhaTro.addRow(row);
				}
			}
			if (cboLuaChon.getSelectedItem().equals("Tìm nhà trọ theo mã")) {
				if (txtTuKhoa.getText().toString().trim() != null
						|| txtTuKhoa.getText().toString().trim().equals("mời nhập vào đây") == false) {
					String[] header = "Mã nhà trọ;Tên chủ nhà;Địa chỉ;Số điện thoại".split(";");
					tableModelNhaTro = new DefaultTableModel(header, 0);
					tableBTT.setModel(tableModelNhaTro);
					NhaTro v = NhaTro_Dao.layTroTheoMa(txtTuKhoa.getText().toString().trim());
					String diaChi = v.getDiaChiTro().getSoNha() + " - " + v.getDiaChiTro().getTenDuong() + " - "
							+ v.getDiaChiTro().getTenPhuong() + " - " + v.getDiaChiTro().getTenQuan();
					String[] row = { v.getMaTro(), v.getTenChutro(), diaChi, v.getSDT() };
					tableModelNhaTro.addRow(row);
				} else {
					JOptionPane.showMessageDialog(this, "Mời nhập vào từ khóa là mã nhà trọ");
				}
			}
			if (cboLuaChon.getSelectedItem().equals("Tìm nhà trọ theo số điện thoại")) {
				if (txtTuKhoa.getText().toString().trim() != null
						|| !txtTuKhoa.getText().toString().trim().equals("mời nhập vào đây")) {
					String[] header = "Mã nhà trọ;Tên chủ nhà;Địa chỉ;Số điện thoại".split(";");
					tableModelNhaTro = new DefaultTableModel(header, 0);
					tableBTT.setModel(tableModelNhaTro);
					ArrayList<NhaTro> listNhaTro = NhaTro_Dao.layNhaTroTheoSDT(txtTuKhoa.getText().toString().trim());
					listNhaTro.forEach(v -> {
						String diaChi = v.getDiaChiTro().getSoNha() + " - " + v.getDiaChiTro().getTenDuong() + " - "
								+ v.getDiaChiTro().getTenPhuong() + " - " + v.getDiaChiTro().getTenQuan();
						String[] row = { v.getMaTro(), v.getTenChutro(), diaChi, v.getSDT() };
						tableModelNhaTro.addRow(row);
					});
				} else {
					JOptionPane.showMessageDialog(this, "Mời nhập vào từ khóa là số điện thoại");
				}
			}
			if (cboLuaChon.getSelectedItem().equals("Tìm nhà trọ theo tên chủ trọ")) {
				if (txtTuKhoa.getText().toString().trim() != null
						|| !txtTuKhoa.getText().toString().trim().equals("mời nhập vào đây")) {
					String[] header = "Mã nhà trọ;Tên chủ nhà;Địa chỉ;Số điện thoại".split(";");
					tableModelNhaTro = new DefaultTableModel(header, 0);
					tableBTT.setModel(tableModelNhaTro);
					ArrayList<NhaTro> listNhaTro = NhaTro_Dao
							.layNhaTroTheoTenChuTro(txtTuKhoa.getText().toString().trim());
					listNhaTro.forEach(v -> {
						String diaChi = v.getDiaChiTro().getSoNha() + " - " + v.getDiaChiTro().getTenDuong() + " - "
								+ v.getDiaChiTro().getTenPhuong() + " - " + v.getDiaChiTro().getTenQuan();
						String[] row = { v.getMaTro(), v.getTenChutro(), diaChi, v.getSDT() };
						tableModelNhaTro.addRow(row);
					});
				} else {
					JOptionPane.showMessageDialog(this, "Mời nhập vào từ khóa là tên chủ trọ");
				}
			}
			if (cboLuaChon.getSelectedItem().equals("Xem lịch sử thay đổi trọ theo mã sinh viên")) {
				if (txtTuKhoa.getText().toString().trim() != null
						|| !txtTuKhoa.getText().toString().trim().equals("mời nhập vào đây")) {
					String[] header = { "Mã Sinh Viên", "Tên Sinh Viên", "Mã Nhà Trọ", "Tên Nhà Trọ", "Địa Chỉ",
							"Ngày Bắt Đầu Thuê", "Ngày Kết Thúc Thuê", "Giá", "Ngày Cập Nhật", "Trạng thái" };
					tableModelBTT2 = new DefaultTableModel(header, 0);
					tableBTT.setModel(tableModelBTT2);
					List<ThongTinThueTro> tt = thongtinthuetro_dao.xemLichSuTheoMaSinhVien(
							txtTuKhoa.getText().toString().trim(), tamluu_dao.layNhanVienTrongBangTamLuu().getLoaiNV(),
							tamluu_dao.layNhanVienTrongBangTamLuu().getMaNV());
					if (tt == null) {
						JOptionPane.showMessageDialog(this,
								"Không tìm thấy lịch sử vinh viên có mã" + txtTuKhoa.getText().toString().trim());
					} else {
						for (ThongTinThueTro thongTinThueTro : tt) {
							themDongVaoBangThongTin(thongTinThueTro, tableModelBTT2);
						}
					}

				} else {
					JOptionPane.showMessageDialog(this, "Mời nhập vào từ khóa là mã sinh viên");
				}
			}

		} else if (o.equals(btnThem)) {
			if (rangBuocDuLieuVao()) {
				ThongTinThueTro t = revertThuocFromTextFields();
				if (t.getTrangThai().equals("Đang Thuê")) {
					thongtinthuetro_dao.suaTrangThaiThueTro(t.getSinhVien().getMaSV());
				}
				if (thongtinthuetro_dao.themThongTinThueTro(t)) {
					JOptionPane.showMessageDialog(this, "Cập nhật quá thuê trọ mới thành công");
					tableBTT.setModel(tableModelBTT);
					int rowCount = tableModelBTT.getRowCount();
					// Remove rows one by one from the end of the table
					for (int i = rowCount - 1; i >= 0; i--) {
						tableModelBTT.removeRow(i);
					}
					themDuLieuCoSan(tamluu_dao.layNhanVienTrongBangTamLuu().getMaNV().toString(), loaiNV);
				} else {
					JOptionPane.showMessageDialog(this, "Cập nhật quá thuê trọ mới không thành công");
				}
			}
		} else if (o.equals(btnXoa)) {
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
		} else if (o.equals(btnSua)) {
			if (JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa dòng này", "Xác nhận sửa",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION && rangBuocDuLieuVao()) {
				int row = tableBTT.getSelectedRow();
				String maSV = tableModelBTT.getValueAt(row, 0).toString();
				String maNT = tableModelBTT.getValueAt(row, 2).toString();

				ThongTinThueTro t = revertThuocFromTextFields();
				if (t.getTrangThai().equals("Đang Thuê")) {
					thongtinthuetro_dao.suaTrangThaiThueTro(t.getSinhVien().getMaSV());
				}
				NhaTro nt = NhaTro_Dao.layTroTheoMa(t.getNhaTro().getMaTro());
				SinhVien sv = sinhvien_dao.laySinhVienTheoMa(t.getSinhVien().getMaSV(),
						tamluu_dao.layNhanVienTrongBangTamLuu().getMaNV(), loaiNV);
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
		}else if (o.equals(btnThoat)) {
			int n = JOptionPane.showConfirmDialog(this,"Bạn có muốn thoát hay không?", "thoát", JOptionPane.YES_NO_OPTION);
			if(n==JOptionPane.YES_OPTION)
			{
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
		// TODO Auto-generated method stub
		if (tableBTT.getModel() == tableModelBTT) {
			int row = tableBTT.getSelectedRow();
			txtTPMaSinhVien.setText(tableBTT.getValueAt(row, 0).toString());
			txtTPTenSinhVien.setText(tableBTT.getValueAt(row, 1).toString());
			txtTPMaNhaTro.setText(tableBTT.getValueAt(row, 2).toString());
			txtTPTenChuNhaTro.setText(tableBTT.getValueAt(row, 3).toString());
			NhaTro nt = NhaTro_Dao.layTroTheoMaCuaVanVinh(tableBTT.getValueAt(row, 2).toString());
			cboQuan.setSelectedItem(nt.getDiaChiTro().getTenQuan());
			cboPhuong.setSelectedItem(nt.getDiaChiTro().getTenPhuong());
			cboDuong.setSelectedItem(nt.getDiaChiTro().getTenDuong());
			cboSoNha.setSelectedItem(nt.getDiaChiTro().getSoNha());
			txtGiaThue.setText(tableBTT.getValueAt(row, 7).toString());
//			txtNgayBatDau.setText(tableBTT.getValueAt(row, 5).toString());
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
				txtNgayBatDau.setDate(d);
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
				txtNgayKetThuc.setDate(dkt);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
//			txtNgayCapNhat.setText(tableBTT.getValueAt(row, 8).toString());
			String[] ngayCN = tableBTT.getValueAt(row, 8).toString().split("/");

			Calendar cacn = new GregorianCalendar();
			String daycn = ngayDB[0];
			String monthcn = ngayDB[1];
			String yearcn = ngayDB[2];

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
				txtNgayCapNhat.setDate(dcn);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			cboTrangThai.setSelectedItem(tableBTT.getValueAt(row, 9));

		} else if (tableBTT.getModel() == tableModelSinhVien) {
			int row = tableBTT.getSelectedRow();
			txtTPMaSinhVien.setText(tableBTT.getValueAt(row, 0).toString());
			txtTPTenSinhVien.setText(tableBTT.getValueAt(row, 1).toString());
		} else if (tableBTT.getModel() == tableModelNhaTro) {
			int row = tableBTT.getSelectedRow();
			txtTPMaNhaTro.setText(tableBTT.getValueAt(row, 0).toString());
			txtTPTenChuNhaTro.setText(tableBTT.getValueAt(row, 1).toString());
			NhaTro nt = NhaTro_Dao.layTroTheoMa(tableBTT.getValueAt(row, 0).toString());
			cboQuan.setSelectedItem(nt.getDiaChiTro().getTenQuan());
			cboPhuong.setSelectedItem(nt.getDiaChiTro().getTenPhuong());
			cboDuong.setSelectedItem(nt.getDiaChiTro().getTenDuong());
			cboSoNha.setSelectedItem(nt.getDiaChiTro().getSoNha());
		}
	}

	public boolean rangBuocDuLieuVao() {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		String ngayBD = dt.format(txtNgayBatDau.getDate());
		String ngayKT = dt.format(txtNgayBatDau.getDate());
		String ngayCN = dt.format(txtNgayBatDau.getDate());
		
		String gia = txtGiaThue.getText().trim().toString();

		if(!(ngayBD.length()>0)) {
			JOptionPane.showMessageDialog(this, "Ngày sinh không được bỏ trống");
			return false;
		}
		if(!(ngayBD.matches("\\d{1,2}[-|/]\\d{1,2}[-|/]\\d{4}"))) {
			JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải theo định dạng dd/MM/yyyy");
			return false;
		}
		
		if(!(ngayKT.length()>0)) {
			JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được bỏ trống");
			return false;
		}
		if(!(ngayKT.matches("\\d{1,2}[-|/]\\d{1,2}[-|/]\\d{4}"))) {
			JOptionPane.showMessageDialog(this, "Ngày kết thúc phải theo định dạng dd/MM/yyyy");
			return false;
		}
		
		if(!(ngayCN.length()>0)) {
			JOptionPane.showMessageDialog(this, "Ngày cập nhật không được bỏ trống");
			return false;
		}
		if(!(ngayCN.matches("\\d{1,2}[-|/]\\d{1,2}[-|/]\\d{4}"))) {
			JOptionPane.showMessageDialog(this, "Ngày cập nhật phải theo định dạng dd/MM/yyyy");
			return false;
		}
		if(!(gia.length()>0)) {
			JOptionPane.showMessageDialog(this, "Giá không được bỏ trống");
			txtNgayBatDau.requestFocus();
			return false;
		}
		try {
			double giathue = Double.parseDouble(gia);
			if(giathue<0) {
				JOptionPane.showMessageDialog(this, "Giá thuê phải lớn hoặc bằng 0");
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "Giá phải là số");
			return false;
		}

		return true;
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
		DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		double giaThue = Double.parseDouble(txtGiaThue.getText().trim());
		LocalDate ngayBatDau = null;
		try {
			ngayBatDau = LocalDate.of(txtNgayBatDau.getJCalendar().getYearChooser().getYear(), (txtNgayBatDau.getJCalendar().getMonthChooser().getMonth()+1), txtNgayBatDau.getJCalendar().getDayChooser().getDay());
		} catch (DateTimeParseException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		LocalDate ngayKetThuc = null;
		try {
		ngayKetThuc = LocalDate.of(txtNgayKetThuc.getJCalendar().getYearChooser().getYear(), (txtNgayKetThuc.getJCalendar().getMonthChooser().getMonth()+1), txtNgayKetThuc.getJCalendar().getDayChooser().getDay());
	} catch (DateTimeParseException e) {
		// TODO: handle exception
		e.printStackTrace();
	}
		LocalDate ngayCapNhat = null;
		try {
			ngayCapNhat = LocalDate.of(txtNgayCapNhat.getJCalendar().getYearChooser().getYear(), (txtNgayCapNhat.getJCalendar().getMonthChooser().getMonth()+1), txtNgayCapNhat.getJCalendar().getDayChooser().getDay());

		} catch (DateTimeParseException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		NhaTro nt = new NhaTro(txtTPMaNhaTro.getText().toString().trim());
		SinhVien sv = new SinhVien(txtTPMaSinhVien.getText().toString().trim());
		String trangthai = cboTrangThai.getSelectedItem().toString().trim();
		return new ThongTinThueTro(giaThue, ngayBatDau, ngayKetThuc, ngayCapNhat, trangthai, nt, sv);
	}
}
