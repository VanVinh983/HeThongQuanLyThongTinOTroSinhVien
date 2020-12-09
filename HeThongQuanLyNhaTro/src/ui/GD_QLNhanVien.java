package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;

import connectDB.ConnectDB;
import dao.NhaTro_Dao;
import dao.NhanVien_Dao;
import dao.TamLuuMaNhanVien_Dao;
import entity.NhanVien;

public class GD_QLNhanVien extends JPanel implements ActionListener, MouseListener {

	// thông tin tk nhan vien
	private JLabel lblthongTinNV;
	private JLabel lblTKMaNV;
	private JLabel lblTKTenNV;
	private JLabel lblTKNgaySinh;
	private JLabel lblTKLoaiNV;
	private JLabel lblTKKhoa;
	private JLabel lblTKMaNV_data;
	private JLabel lblTKTenNV_data;
	private JLabel lblTKNgaySinh_data;
	private JLabel lblTKLoaiNV_data;
	private JLabel lblTKKhoa_data;
	private TamLuuMaNhanVien_Dao tamluu_dao;

	// left
	private JButton btnThueTro;
	private JButton btnNhanVien;
	private JButton btnSinhVien;
	private JButton btnTro;
	private JButton btnHuongDanSD;

	private JLabel lblNameUser;
	// texfield nhanvien
	private JLabel lblMaNV;
	private JLabel lblTenNV;
	private JLabel lblNgaySinh;
	private JLabel lblLoaiNV;
	private JLabel lblKhoa;
	private JTextField txtMaNV;
	private JTextField txtTenNV;
	private JTextField txtNgaySinh;
	private JTextField txtLoaiNV;
	private JTextField txtKhoa;
	private int row;

	private JTextField txtUser;
	private JTextField txtTim;
	private JLabel lblTim;
	private JButton btnTim;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnXoaTrang;
	private JButton btnQuanLy;
	private JLabel lblUser;
	private JLabel lblcen1;
	private DefaultTableModel tableModel;
	private JTable table;
	private JLabel lblThongTin;
	private JButton btnThongKe;
	private JButton btnCapNhatTK;
	private JButton btnThoat;
	private JComboBox<String> cbKhoa;
	private JComboBox<String> cbCapBac;
	private JComboBox<String> cmp;
	private JButton btnDSNV;
	private JButton btnLuu;

	private NhanVien_Dao nv_dao;

	public void setDataFont(JLabel lbl) {
		lbl.setForeground(Color.BLUE);
		lbl.setFont(new Font("Monospaced", Font.BOLD, 15));
	}

	public GD_QLNhanVien() {
		
		tamluu_dao = new TamLuuMaNhanVien_Dao();
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		// TODO Auto-generated constructor stub
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(1200, 600));

		JPanel pnl = new JPanel();

		pnl.setLayout(new BorderLayout());
		Box box = Box.createVerticalBox();

		// Tạo ảnh và txt hiển thị user
		JPanel pnlUser = new JPanel();
		pnlUser.setBackground(Color.GREEN);
		Box buser = Box.createVerticalBox();
		Box bUser, buserImg;
		buser.add(bUser = Box.createHorizontalBox());
		// NhaTro_Dao dao = new NhaTro_Dao();

		/*
		 * String tenKhoaNV = dao.layTenNhanVien().trim().toString();
		 * if(dao.layTenNhanVien().trim().toString().equals("NV")) {
		 * bUser.add(lblNameUser = new JLabel("Giáo vụ khoa: " + tenKhoaNV)); } else
		 * if(dao.layTenNhanVien().trim().toString().equals("QL")){
		 * bUser.add(lblNameUser = new JLabel("Người quản lý: " + tenKhoaNV)); }
		 */

		// bUser.add(lblNameUser = new JLabel(dao.layTenNhanVien()));
		buser.add(buserImg = Box.createVerticalBox());
		// Hinh Anh user
		JPanel pnlImg = new JPanel();
		JLabel lblBanner = new JLabel();
		pnlImg.add(lblBanner);
		lblBanner.setSize(250, 250);
		add(pnlImg);
		// đường dẫn
		setPicture(lblBanner, "HinhAnh/User.png");

		buserImg.add(pnlImg);
		pnlUser.add(buser);

		pnlUser.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		box.add(pnlUser);
		pnl.add(box, BorderLayout.WEST);
		// thêm vào màn hình chính
		this.add(pnl, BorderLayout.CENTER);

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		JPanel pnlMenu = new JPanel();
		pnlMenu.setLayout(new BorderLayout());
		JPanel pnlMenubtn = new JPanel();
		pnlMenubtn.setBackground(Color.MAGENTA);
		pnlMenu.add(pnlMenubtn, BorderLayout.CENTER);
		pnlMenubtn.setLayout(new BoxLayout(pnlMenubtn, BoxLayout.Y_AXIS));
		box.add(pnlMenu);
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Thanh điều hướng
		pnlMenubtn.add(Box.createVerticalStrut(20));
		pnlMenubtn.add(btnTro = new JButton("Quản lý trọ"));
		btnTro.add(Box.createHorizontalStrut(30));
		btnTro.add(Box.createVerticalStrut(20));

		pnlMenubtn.add(Box.createVerticalStrut(10));
		pnlMenubtn.add(btnSinhVien = new JButton("Quản lý sinh viên"));
		btnSinhVien.add(Box.createHorizontalStrut(30));
		btnSinhVien.add(Box.createVerticalStrut(20));

		pnlMenubtn.add(Box.createVerticalStrut(10));
		pnlMenubtn.add(btnNhanVien = new JButton("Quản lý nhân viên"));
		btnNhanVien.add(Box.createHorizontalStrut(30));
		btnNhanVien.add(Box.createVerticalStrut(20));
		btnNhanVien.addActionListener(this);

		pnlMenubtn.add(Box.createVerticalStrut(10));
		pnlMenubtn.add(btnThueTro = new JButton("Quản lý thuê trọ"));
		btnThueTro.add(Box.createHorizontalStrut(30));
		btnThueTro.add(Box.createVerticalStrut(20));

		pnlMenubtn.add(Box.createVerticalStrut(10));
		pnlMenubtn.add(btnThongKe = new JButton("Thống kê"));
		btnThongKe.add(Box.createHorizontalStrut(30));
		btnThongKe.add(Box.createVerticalStrut(20));

		pnlMenubtn.add(Box.createVerticalStrut(10));
		pnlMenubtn.add(btnHuongDanSD = new JButton("Hướng dẫn sử dụng"));
		btnHuongDanSD.add(Box.createHorizontalStrut(30));
		btnHuongDanSD.add(Box.createVerticalStrut(20));

		pnlMenubtn.add(Box.createVerticalStrut(10));
		pnlMenubtn.add(btnThoat = new JButton("Thoát"));
		btnThoat.add(Box.createHorizontalStrut(30));
		btnThoat.add(Box.createVerticalStrut(20));

		/*
		 * JPanel pnl = new JPanel(); pnl.setLayout(new BorderLayout()); Box box =
		 * Box.createVerticalBox(); Box b1, b2; box.add(b1 = Box.createHorizontalBox());
		 * 
		 * JPanel pnluser1 = new JPanel(); pnluser1.setBackground(Color.CYAN); Box buser
		 * = Box.createVerticalBox(); Box buser1, buser2, buser3; buser.add(buser1 =
		 * Box.createHorizontalBox()); buser1.add(lblUser = new JLabel("User: "));
		 * lblUser.setFont(new Font("Arial", Font.BOLD, 35)); buser1.add(lblTKMaNV_data
		 * = new JLabel()); lblTKMaNV_data.setFont(new Font("Monospace", Font.BOLD,
		 * 35)); lblTKMaNV_data.setText("ÀEQEEV");
		 * lblTKMaNV_data.setForeground(Color.BLUE);
		 * buser1.add(Box.createVerticalStrut(20));
		 * 
		 * buser.add(buser2 = Box.createVerticalBox());
		 * 
		 * //Hinh Anh JPanel pnlcontent=new JPanel(); JLabel lblBanner = new JLabel();
		 * pnlcontent.add(lblBanner); lblBanner.setSize(250,250); add(pnlcontent);
		 * setPicture(lblBanner, "HinhAnh/User.png");
		 * 
		 * buser2.add(pnlcontent); pnluser1.add(buser);
		 * 
		 * pnluser1.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		 * b1.add(pnluser1); pnl.add(box, BorderLayout.WEST); this.add(pnl,
		 * BorderLayout.CENTER);
		 * 
		 * /////////////////////////////////////////////////////////////
		 * 
		 * JPanel pnluser2 = new JPanel(); pnluser2.setLayout(new BorderLayout());
		 * JPanel pnluser22 = new JPanel(); pnluser22.setBackground(Color.CYAN);
		 * pnl.add(pnluser2); pnluser2.add(pnluser22, BorderLayout.CENTER);
		 * pnluser22.setLayout(new BoxLayout(pnluser22, BoxLayout.Y_AXIS));
		 * box.add(pnluser2);
		 * 
		 * //thong tin TK nhan vien pnluser22.add(Box.createVerticalStrut(20));
		 * pnluser22.add(lblthongTinNV= new JLabel("Thông Tin Nhân Viên"));
		 * lblthongTinNV.setForeground(Color.RED);
		 * 
		 * pnluser22.add(Box.createVerticalStrut(20)); Box boxTKTenNV =
		 * Box.createHorizontalBox(); pnluser22.add(boxTKTenNV);
		 * boxTKTenNV.add(lblTKTenNV = new JLabel("Họ tên   :   "));
		 * boxTKTenNV.add(lblTKTenNV_data = new JLabel()); setDataFont(lblTKTenNV_data);
		 * lblTKTenNV_data.setText("Nguyễn Văn A");
		 * 
		 * 
		 * pnluser22.add(Box.createVerticalStrut(20)); Box boxTKNgaySinh =
		 * Box.createHorizontalBox(); pnluser22.add(boxTKNgaySinh);
		 * boxTKNgaySinh.add(lblTKNgaySinh = new JLabel("Ngày sinh   :   "));
		 * boxTKNgaySinh.add(lblTKNgaySinh_data = new JLabel());
		 * setDataFont(lblTKNgaySinh_data); lblTKNgaySinh_data.setText("11-10-1998");
		 * 
		 * 
		 * pnluser22.add(Box.createVerticalStrut(20)); Box boxTKKhoa =
		 * Box.createHorizontalBox(); pnluser22.add(boxTKKhoa); boxTKKhoa.add(lblTKKhoa
		 * = new JLabel("Khoa   :   ")); boxTKKhoa.add(lblTKKhoa_data = new JLabel());
		 * setDataFont(lblTKKhoa_data); lblTKKhoa_data.setText("CNTT");
		 * 
		 * pnluser22.add(Box.createVerticalStrut(20)); Box boxTKCapBac =
		 * Box.createHorizontalBox(); pnluser22.add(boxTKCapBac);
		 * boxTKCapBac.add(lblTKLoaiNV = new JLabel("Chức vụ   :   "));
		 * boxTKCapBac.add(lblTKLoaiNV_data = new JLabel());
		 * setDataFont(lblTKLoaiNV_data); lblTKLoaiNV_data.setText("Người quản lý");
		 * 
		 * //lblTKTenNV.setPreferredSize(lblTKNgaySinh.getPreferredSize());
		 * //lblTKKhoa.setPreferredSize(lblTKNgaySinh.getPreferredSize());
		 * //lblTKLoaiNV.setPreferredSize(lblTKNgaySinh.getPreferredSize());
		 * 
		 * ------------------ lblTKTenNV.add(Box.createHorizontalStrut(30));
		 * lblTKTenNV.add(Box.createVerticalStrut(20));
		 * pnluser22.add(Box.createVerticalStrut(10)); pnluser22.add(lblTKTenNV= new
		 * JLabel("Tên Nhân Viên:"));
		 * 
		 * /*btnSinhVien.add(Box.createHorizontalStrut(30));
		 * btnSinhVien.add(Box.createVerticalStrut(20));
		 * pnluser22.add(Box.createVerticalStrut(10)); pnluser22.add(btnNhanVien = new
		 * JButton("Quản Lý Nhân Viên"));
		 * 
		 * btnNhanVien.add(Box.createHorizontalStrut(30));
		 * btnNhanVien.add(Box.createVerticalStrut(20));
		 * pnluser22.add(Box.createVerticalStrut(10)); pnluser22.add(btnThueTro = new
		 * JButton("Quản Lý Thuê Trọ"));
		 * 
		 * btnThueTro.add(Box.createHorizontalStrut(30));
		 * btnThueTro.add(Box.createVerticalStrut(20));
		 * pnluser22.add(Box.createVerticalStrut(10)); pnluser22.add(btnThongKe = new
		 * JButton("Thống Kê"));
		 * 
		 * btnThongKe.add(Box.createHorizontalStrut(30));
		 * btnThongKe.add(Box.createVerticalStrut(20));
		 * pnluser22.add(Box.createVerticalStrut(10)); pnluser22.add(btnHuongDanSD = new
		 * JButton("Hướng Dẫn Sử Dụng"));
		 * 
		 * btnHuongDanSD.add(Box.createHorizontalStrut(30));
		 * btnHuongDanSD.add(Box.createVerticalStrut(20)); ------------------
		 * 
		 * 
		 * pnluser22.add(Box.createVerticalStrut(50));
		 * 
		 * pnluser22.add(btnQuanLy = new JButton("Quản Lý Sinh Viên"));
		 * btnQuanLy.setBackground(Color.ORANGE);
		 * btnQuanLy.add(Box.createHorizontalStrut(50));
		 * btnQuanLy.add(Box.createVerticalStrut(20));
		 * pnluser22.add(Box.createVerticalStrut(10));
		 * 
		 * 
		 * pnluser22.add(btnThongKe = new JButton("Thống kê"));
		 * btnThongKe.setBackground(Color.ORANGE);
		 * btnThongKe.add(Box.createHorizontalStrut(50));
		 * btnThongKe.add(Box.createVerticalStrut(20));
		 * 
		 * pnluser22.add(Box.createVerticalStrut(10)); pnluser22.add(btnCapNhatTK = new
		 * JButton("Cập Nhật Thông Tin")); btnCapNhatTK.setBackground(Color.ORANGE);
		 * btnCapNhatTK.add(Box.createHorizontalStrut(30));
		 * btnCapNhatTK.add(Box.createVerticalStrut(20));
		 * 
		 * pnluser22.add(Box.createVerticalStrut(10)); pnluser22.add(btnThoat = new
		 * JButton("Thoát")); btnThoat.setBackground(Color.RED);
		 * btnThoat.setForeground(Color.WHITE);
		 * btnThoat.add(Box.createHorizontalStrut(30));
		 * btnThoat.add(Box.createVerticalStrut(20));
		 */

		// Center
		JPanel pnlCen = new JPanel();
		Box boxCen = Box.createVerticalBox();

		Box bcen1, bcen2, bcen3;
		boxCen.add(bcen1 = Box.createHorizontalBox());

		JPanel pnlQuanLyTro = new JPanel();
		pnlQuanLyTro.add(lblcen1 = new JLabel("QUẢN LÝ NHÂN VIÊN"));
		bcen1.add(pnlQuanLyTro);
		pnlQuanLyTro.setBackground(Color.BLUE);
		lblcen1.setFont(new Font("Arial", Font.BOLD, 40));
		lblcen1.setForeground(Color.PINK);
		// Table
		boxCen.add(bcen2 = Box.createHorizontalBox());

		JPanel pnlcen2 = new JPanel();
		JScrollPane scroll;
		String[] header = "Mã Nhân Viên;Tên Nhân Viên;Ngày Sinh;Khoa;Cấp Bậc".split(";");
		tableModel = new DefaultTableModel(header, 20);
		table = new JTable(tableModel) {
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
		pnlcen2.add(scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.SOUTH);
		scroll.setBorder(BorderFactory.createTitledBorder("Danh Sách"));

		table.setRowHeight(50);
		scroll.setPreferredSize(new Dimension(0, 500));
		bcen2.add(scroll);
		///////////////////
		pnl.add(boxCen, BorderLayout.CENTER);

		// South
		boxCen.add(bcen3 = Box.createVerticalBox());
		JPanel pnlcen3 = new JPanel();
		pnlcen3.setLayout(new BorderLayout());

		Box boxcen31, boxcen32;
		pnlcen3.add(boxcen31 = box.createVerticalBox(), BorderLayout.NORTH);

		bcen3.add(pnlcen3);
		JPanel pnlThongTin = new JPanel();
		pnlThongTin.add(lblThongTin = new JLabel("Thông tin"));
		boxcen31.add(pnlThongTin);
		lblThongTin.setFont(new Font("Arial", Font.BOLD, 30));
		pnlThongTin.setBackground(Color.LIGHT_GRAY);

		///////////////////////////////////////////

		bcen3.add(boxcen32 = Box.createVerticalBox());
		JPanel pnlSouth = new JPanel();
		pnlSouth.setLayout(new BorderLayout());
		pnlSouth.setBackground(Color.LIGHT_GRAY);

		pnlSouth.add(Box.createHorizontalStrut(100), BorderLayout.WEST);
		JPanel pnlForm = new JPanel();
		pnlSouth.add(pnlForm);
		boxcen31.add(pnlSouth);
		pnlForm.setBounds(0, 80, 1055, 450);
		pnlForm.setLayout(new BoxLayout(pnlForm, BoxLayout.Y_AXIS));

		pnlForm.add(Box.createVerticalStrut(30));
		Box boxMaNV = Box.createHorizontalBox();
		boxMaNV.add(lblMaNV = new JLabel("Mã nhân viên: "));
		boxMaNV.add(txtMaNV = new JTextField());
		pnlForm.add(boxMaNV);

		pnlForm.add(Box.createVerticalStrut(10));
		Box boxTenNV = Box.createHorizontalBox();
		boxTenNV.add(lblTenNV = new JLabel("Tên nhân viên: "));
		boxTenNV.add(txtTenNV = new JTextField());
		pnlForm.add(boxTenNV);

		pnlForm.add(Box.createVerticalStrut(10));
		Box boxNgaySinh = Box.createHorizontalBox();
		boxNgaySinh.add(lblNgaySinh = new JLabel("Ngày sinh: "));
		boxNgaySinh.add(txtNgaySinh = new JTextField());
		pnlForm.add(boxNgaySinh);

		pnlForm.add(Box.createVerticalStrut(10));
		Box boxLoaiNV = Box.createHorizontalBox();
		boxLoaiNV.add(lblLoaiNV = new JLabel("Cấp bậc: "));
		boxLoaiNV.add(cbCapBac = new JComboBox<>());
		cbCapBac.addItem("QL");
		cbCapBac.addItem("NV");
		pnlForm.add(boxLoaiNV);

		pnlForm.add(Box.createVerticalStrut(10));
		Box boxKhoa = Box.createHorizontalBox();
		boxKhoa.add(lblKhoa = new JLabel("Khoa: "));
		boxKhoa.add(cbKhoa = new JComboBox<>());
		String[] khoa = "Công Nghệ Cơ Khí;Công Nghệ Thông Tin;Công Nghệ Điện;Công Nghệ Điện Tử;Công Nghệ Động Lực;Công Nghệ Nhiệt - Lạnh;Công Nghệ May - Thời Trang;Công Nghệ Hóa Học;Kế toán - Kiểm toán;Lý Luận Chính Trị;Ngoại Ngữ;Quản Trị Kinh Doanh;Tài Chính - Ngân Hàng;Thương Mại - Du Lịch;Kỹ Thuật Xây Dựng;Luật;Khoa Học Cơ Bản"
				.split(";");
		for (String k : khoa) {
			cbKhoa.addItem(k);
		}
		pnlForm.add(boxKhoa);

		lblMaNV.setPreferredSize(lblTenNV.getPreferredSize());
		lblNgaySinh.setPreferredSize(lblTenNV.getPreferredSize());
		lblKhoa.setPreferredSize(lblTenNV.getPreferredSize());
		lblLoaiNV.setPreferredSize(lblTenNV.getPreferredSize());

		pnlForm.add(Box.createVerticalStrut(10));
		Box boxButton = Box.createHorizontalBox();
		boxButton.add(btnThem = new JButton("Thêm"));
		boxButton.add(Box.createHorizontalStrut(20));
		boxButton.add(btnXoa = new JButton("Xoá"));
		boxButton.add(Box.createHorizontalStrut(20));
		boxButton.add(btnSua = new JButton("Sửa"));
		boxButton.add(Box.createHorizontalStrut(20));
		boxButton.add(btnLuu = new JButton("Lưu"));
		boxButton.add(Box.createHorizontalStrut(20));
		boxButton.add(btnXoaTrang = new JButton("Xoá Trắng"));
		pnlForm.add(boxButton);
		pnlForm.add(Box.createVerticalStrut(20));

		pnlForm.setBorder(BorderFactory.createRaisedBevelBorder());

		JPanel pnlSouthRight = new JPanel();
		pnlSouthRight.setLayout(new BoxLayout(pnlSouthRight, BoxLayout.Y_AXIS));
		pnlSouth.add(pnlSouthRight, BorderLayout.EAST);
		pnlSouthRight.setBorder(BorderFactory.createLoweredSoftBevelBorder());

		pnlSouthRight.add(Box.createVerticalStrut(30));
		Box boxtxtTim = Box.createHorizontalBox();
		boxtxtTim.add(lblTim = new JLabel("Tìm"));
		boxtxtTim.add(txtTim = new JTextField(20));
		pnlSouthRight.add(boxtxtTim);

		pnlSouthRight.add(Box.createVerticalStrut(30));
		Box boxcmpTim = Box.createHorizontalBox();
		String[] s = "Mã;Tên Nhân Viên;Khoa".split(";");
		boxcmpTim.add(cmp = new JComboBox<String>(s));
		pnlSouthRight.add(boxcmpTim);

		pnlSouthRight.add(Box.createVerticalStrut(30));
		Box boxbtnTim = Box.createHorizontalBox();
		boxbtnTim.add(btnTim = new JButton("Tìm"));
		btnTim.add(Box.createHorizontalStrut(50));
		btnTim.add(Box.createVerticalStrut(50));
		btnTim.setBackground(Color.green);
		pnlSouthRight.setBackground(Color.CYAN);

		boxbtnTim.add(btnDSNV = new JButton("Hiển thị DSNV"));
		btnDSNV.add(Box.createHorizontalStrut(50));
		btnDSNV.add(Box.createVerticalStrut(50));
		pnlSouthRight.add(boxbtnTim);

		btnHuongDanSD.addActionListener(this);
		btnNhanVien.addActionListener(this);
		btnSinhVien.addActionListener(this);
		btnSua.addActionListener(this);
		btnThem.addActionListener(this);
		btnThoat.addActionListener(this);
		btnThongKe.addActionListener(this);
		btnThueTro.addActionListener(this);
		btnTim.addActionListener(this);
		btnTro.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnDSNV.addActionListener(this);
		btnLuu.addActionListener(this);
		table.addMouseListener(this);

		/////////////////////////////////////////////////

		// pnlcen3.add(Box.createHorizontalStrut(400), BorderLayout.EAST);
		nv_dao = new NhanVien_Dao();
		nv_dao.loadNhanVienTuDatabase();
		loadToanBoNhanVienLenTable();
		btnLuu.setEnabled(false);
		setVisible(true);
	}

	public void setPicture(JLabel label, String filename) {
		try {
			BufferedImage image = ImageIO.read(new File(filename));
			int x = label.getSize().width;
			int y = label.getSize().height;
			int ix = image.getWidth();
			int iy = image.getHeight();

			int dx = x;
			int dy = y;

			ImageIcon icon = new ImageIcon(image.getScaledInstance(dx, dy, BufferedImage.SCALE_SMOOTH));
			label.setIcon(icon);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Loi hinh anh");
		}
	}

	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		if (ob.equals(btnLuu)) {
			int row = table.getSelectedRow();

			String maNV = txtMaNV.getText().strip();
			String tenNV = txtTenNV.getText().strip();
			String ngaySinh = txtNgaySinh.getText().strip();
			String khoa = cbKhoa.getSelectedItem().toString();
			String loaiNV = cbCapBac.getSelectedItem().toString();

			String ngaySinh_arr[] = ngaySinh.split("/");
			String ngaySinh_format = ngaySinh_arr[2] + "-" + ngaySinh_arr[1] + "-" + ngaySinh_arr[0];

			NhanVien nv = new NhanVien(maNV, loaiNV, tenNV, "12345", Date.valueOf(ngaySinh_format), khoa);
			nv_dao.capNhatNhanVienTrongDatabase(nv);
			nv_dao.loadNhanVienTuDatabase();
			Object o[] = { nv.getMaNV(), nv.getTenNV(), new SimpleDateFormat("dd/MM/yyyy").format(nv.getNgaySinh()),
					nv.getTenKhoa(), nv.getLoaiNV() };
			tableModel.insertRow(row, o);

		} else if (ob.equals(btnSua)) {
			txtTenNV.setEditable(true);
			txtNgaySinh.setEditable(true);
			cbKhoa.setEnabled(true);
			cbCapBac.setEnabled(true);
			btnLuu.setEnabled(true);
		} else if (ob.equals(btnThem)) {
			// lấy dl từ textfield
			String maNV = txtMaNV.getText().strip();
			String tenNV = txtTenNV.getText().strip();
			String ngaySinh = txtNgaySinh.getText().strip();
			String khoa = cbKhoa.getSelectedItem().toString();
			String loaiNV = cbCapBac.getSelectedItem().toString();

			String ngaySinh_arr[] = ngaySinh.split("/");
			String ngaySinh_format = ngaySinh_arr[2] + "-" + ngaySinh_arr[1] + "-" + ngaySinh_arr[0];

			validData();

			NhanVien nv = new NhanVien(maNV, loaiNV, tenNV, "12345", Date.valueOf(ngaySinh_format), khoa);

			if (loaiNV.equals("QL")) {
				khoa = "";
			}
			if (nv_dao.themNV(nv)) {
				Object o[] = { maNV, tenNV, ngaySinh, khoa, loaiNV };
				tableModel.insertRow(row, o);
				xoaTrangAction();
				JOptionPane.showMessageDialog(this, "Thêm thành công!");
			} else {
				JOptionPane.showMessageDialog(this, "Trùng mã nhân viên!");
			}
		} else if (ob.equals(btnThoat)) {
				removeAll();
				add(new GD_Admin());
				repaint();
				revalidate();
		} else if (ob.equals(btnThongKe)) {

		} else if (ob.equals(btnTim)) {

			// tim theo ma
			if (cmp.getSelectedItem().toString().equals("Mã")) {
				String maNhanVienCanTim = txtTim.getText().strip();
				ArrayList<NhanVien> dsNVTheoMa = nv_dao.timNhanVienTheoMa(maNhanVienCanTim);
				tableModel.setRowCount(0);
				for (NhanVien nv : dsNVTheoMa) {
					Object o[] = { nv.getMaNV(), nv.getTenNV(),
							new SimpleDateFormat("dd/MM/yyyy").format(nv.getNgaySinh()), nv.getTenKhoa(),
							nv.getLoaiNV() };
					tableModel.addRow(o);
				}
			}

			// tim theo ten
			if (cmp.getSelectedItem().toString().equals("Tên Nhân Viên")) {
				String tenNhanVienCanTim = txtTim.getText().strip();
				ArrayList<NhanVien> dsNVTheoTen = nv_dao.timNhanVienTheoTen(tenNhanVienCanTim);
				tableModel.setRowCount(0);
				for (NhanVien nv : dsNVTheoTen) {
					Object o[] = { nv.getMaNV(), nv.getTenNV(),
							new SimpleDateFormat("dd/MM/yyyy").format(nv.getNgaySinh()), nv.getTenKhoa(),
							nv.getLoaiNV() };
					tableModel.addRow(o);
				}
			}

			// tim theo khoa
			if (cmp.getSelectedItem().toString().equals("Khoa")) {
				String khoaCanTim = txtTim.getText().strip();
				ArrayList<NhanVien> dsNVTheoTen = nv_dao.timNhanVienTheoKhoa(khoaCanTim);
				tableModel.setRowCount(0);
				for (NhanVien nv : dsNVTheoTen) {
					Object o[] = { nv.getMaNV(), nv.getTenNV(),
							new SimpleDateFormat("dd/MM/yyyy").format(nv.getNgaySinh()), nv.getTenKhoa(),
							nv.getLoaiNV() };
					tableModel.addRow(o);
				}
			}

		} else if (ob.equals(btnThoat)) {
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
		} else if (ob.equals(btnQuanLy)) {

		} else if (ob.equals(btnXoa)) {
			xoaAction();
		} else if (ob.equals(btnXoaTrang)) {
			xoaTrangAction();

		} else if (ob.equals(btnDSNV)) {
			loadToanBoNhanVienLenTable();
		} else if (ob.equals(btnTro)) {
			removeAll();
			add(new GD_QuanLyTro());
			repaint();
			revalidate();
		} else if (ob.equals(btnSinhVien)) {
			removeAll();
			add(new GD_QuanLySinhVien());
			repaint();
			revalidate();
		}

		else if (ob.equals(btnThueTro)) {
			removeAll();
			add(new GD_ThongTinThueTro());
			repaint();
			revalidate();
		}
	}

	private void themAction() {

		// lấy dl từ textfield
		String maNV = txtMaNV.getText().strip();
		String tenNV = txtTenNV.getText().strip();
		String ngaySinh = txtNgaySinh.getText().strip();
		String khoa = cbKhoa.getSelectedItem().toString();
		String loaiNV = cbCapBac.getSelectedItem().toString();

		String ngaySinh_arr[] = ngaySinh.split("/");
		String ngaySinh_format = ngaySinh_arr[2] + "-" + ngaySinh_arr[1] + "-" + ngaySinh_arr[0];

		validData();

		NhanVien nv = new NhanVien(maNV, loaiNV, tenNV, "12345", Date.valueOf(ngaySinh_format), khoa);

		if (loaiNV.equals("QL")) {
			khoa = "";
		}
		if (nv_dao.themNV(nv)) {
			Object o[] = { maNV, tenNV, ngaySinh, khoa, loaiNV };
			tableModel.insertRow(row, o);
			xoaTrangAction();
			JOptionPane.showMessageDialog(this, "Thêm thành công!");
		} else {
			JOptionPane.showMessageDialog(this, "Trùng mã nhân viên!");
		}
	}

	private void xoaAction() {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		String maNV = txtMaNV.getText();
		if (nv_dao.xoaNhanVien(maNV)) {
			int loiNhac = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa?");
			if (loiNhac == JOptionPane.YES_OPTION) {
				tableModel.removeRow(row);
				JOptionPane.showMessageDialog(this, "Xóa thành công!");
				xoaTrangAction();
			}
		}
	}

	private void xoaTrangAction() {
		// TODO Auto-generated method stub
		txtMaNV.setEditable(true);
		txtTenNV.setEditable(true);
		txtNgaySinh.setEditable(true);
		cbKhoa.setEnabled(true);
		cbCapBac.setEnabled(true);

		txtMaNV.setText("");
		txtTenNV.setText("");
		txtNgaySinh.setText("");
		cbKhoa.setSelectedIndex(0);
		cbCapBac.setSelectedIndex(0);
		txtTim.setText("");
		cmp.setSelectedIndex(0);
	}

	public void loadToanBoNhanVienLenTable() {
		tableModel.setRowCount(0);
		for (NhanVien nv : nv_dao.layDanhSach()) {
			Object o[] = { nv.getMaNV(), nv.getTenNV(), new SimpleDateFormat("dd/MM/yyyy").format(nv.getNgaySinh()),
					nv.getTenKhoa(), nv.getLoaiNV() };
			tableModel.addRow(o);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMaNV.setText(table.getValueAt(row, 0).toString());
		txtTenNV.setText(table.getValueAt(row, 1).toString());
		txtNgaySinh.setText(table.getValueAt(row, 2).toString());
		cbKhoa.setSelectedItem(table.getValueAt(row, 3).toString());
		cbCapBac.setSelectedItem(table.getValueAt(row, 4).toString());

		// uneditable
		txtMaNV.setEditable(false);
		txtTenNV.setEditable(false);
		txtNgaySinh.setEditable(false);
		cbKhoa.setEnabled(false);
		cbCapBac.setEnabled(false);
	}

	public void validData() {
		if (txtMaNV.getText().equals("") || txtTenNV.getText().equals("") || txtNgaySinh.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Các trường không được để trống");
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

}