package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.chrono.JapaneseDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.NhaTro_Dao;
import dao.NhanVien_Dao;
import dao.SinhVien_Dao;
import dao.TamLuuMaNhanVien_Dao;
import dao.ThongTinThueTro_Dao;
import entity.NhaTro;
import entity.NhanVien;
import entity.SinhVien;
import resourse.SetSizeByPercent;

public class GD_QuanLySinhVien extends JPanel implements ActionListener, MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NhanVien_Dao nv_Dao;
	private TamLuuMaNhanVien_Dao tamluu_dao;
	
	//attribute Form thông tin phần txt
	private JTextField txtMaSV;
	private JTextField txtTenSV;
	private JTextField txtMaLop;
	private JTextField txtMaNV;
	private JComboBox<String> cmpQueQuan;
	private JComboBox<String> cmpMaLop;
	private JComboBox<String> cmpMaNV;
	private JComboBox<String> cmpGioiTinh;
	private JComboBox<String> cmpKhoa;
	private JDateChooser dateNgaySinh;
	private JTextField txtKhoa;
	
	//attribute Form thông tin phần label
	
	private JLabel lblMaSV;
	private JLabel lblTenSV;
	private JLabel lblNgaySinh;
	private JLabel lblQueQuan;
	private JLabel lblMaLop;
	private JLabel lblMaNV;
	private JLabel lblGioiTinh;
	private JLabel lblKhoa;
	
	//Button Form Thông tin
	private JButton btnTim;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnXoaTrang;
	
	//Phần tên user
	private JLabel lblNameUser;
	private JLabel lblUser;
	
	//Tiêu đề Quản lý Trọ
	private JLabel lblcenTitle;
	//Bảng thông tin
	private DefaultTableModel tableModel;
	private JTable table;
	//Tiêu đề thông tin form
	private JLabel lblThongTin;
	
	//Thanh điều hướng
	private JButton btnTro;
	private JButton btnSinhVien;
	private JButton btnThongKe;
	private JButton btnThueTro;
	private JButton btnNhanVien;
	private JButton btnHuongDanSD;
	private JButton btnThoat;
	private JButton btnDoiMK;
	//Compobox Lua cong tim kiếm
	private JComboBox cmpTim;
//	private JTextField txtTim;
	private JLabel lblTim;
	private JComboBox<String> cmp;
	private NhanVien nhanVienTamLuu;
	
	///////////////////////////////////////////////////////////////////////////

	private JPanel contentPane ;
	private JComboBox<String> comboBox;
	private Box boxtxtTim;
	private JPanel pnlFormTim;
	private JPanel pnl;
	private String dstim ="";
	private int max = 0;
	private String ma = "";
	////////////////////////////////////////////////////////////////////////
	private List list = new ArrayList<String>();
	
	public GD_QuanLySinhVien() {
		nv_Dao = new NhanVien_Dao(); 
		tamluu_dao = new TamLuuMaNhanVien_Dao();
		
		nhanVienTamLuu = tamluu_dao.layNhanVienTrongBangTamLuu();
		
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(1200, 600));
		
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
		ImageIcon imgDoiMK = new ImageIcon(new ImageIcon("HinhAnh/doimk.png").getImage().getScaledInstance(
				setSizeByPercent.getWidthByPercent(3), setSizeByPercent.getHeightByPercent(6), Image.SCALE_DEFAULT));
		setLayout(new BorderLayout(0, 0));
		
		pnl = new JPanel();
		pnl.setLayout(new BorderLayout());
		Box box = Box.createVerticalBox();
		
		//pnluser1 Tạo ảnh và txt hiển thị user
			JPanel pnlUser = new JPanel();
				pnlUser.setBackground(new Color(108, 123, 139));
				Box buser = Box.createVerticalBox();
				
					//tạo 3 box user định dạng theo  chiều dọc
					Box bUser, buserImg;
					
					//box user1 
					buser.add(bUser = Box.createHorizontalBox());
					
					SinhVien_Dao daoSV = new SinhVien_Dao();
					
					String tenKhoaNV = daoSV.layTenKhoaNhanVien().trim().toString();
					if(daoSV.layLoaiNV().trim().toString().equals("NV"))
					{
						bUser.add(lblNameUser = new JLabel("GV:" + tenKhoaNV));
					}
					else if(daoSV.layLoaiNV().trim().toString().equals("QL")){
						bUser.add(lblNameUser = new JLabel("Quản lý"));
					}
					
					lblNameUser.setFont(new Font( "Arial", Font.BOLD, 30));
					buser.add(buserImg = Box.createVerticalBox());
					//box user2 Hinh Anh user
					JPanel pnlcontent=new JPanel();
		     		JLabel lblBanner = new JLabel();
					pnlcontent.add(lblBanner);
					lblBanner.setSize(200,200);
					add(pnlcontent);
					//đường dẫn
					setPicture(lblBanner, "HinhAnh/User.png");
					buserImg.add(pnlcontent);
					pnlUser.add(buser);
				
					pnlUser.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		box.add(pnlUser);
		
		pnl.add(box, BorderLayout.WEST);
		//thêm vào màn hình chính
		this.add(pnl, BorderLayout.CENTER);
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//pnlMenu chứa thanh điều hướng nằm dưới hình user
		JPanel pnlMenu = new JPanel();
		pnlMenu.setLayout(new BorderLayout());
		
			JPanel pnlMenubtn = new JPanel();
			pnlMenubtn.setBackground(Color.MAGENTA);
			pnlMenu.add(pnlMenubtn, BorderLayout.CENTER);
			pnlMenubtn.setLayout(new BoxLayout(pnlMenubtn, BoxLayout.Y_AXIS));
			
				box.add(pnlMenu);
				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				//Thanh điều hướng 
				pnlMenubtn.add(Box.createVerticalStrut(10));
				pnlMenubtn.add(btnTro = new JButton("Trọ"));
				pnlMenubtn.setBackground(new Color(0, 238, 238));
				btnTro.add(Box.createHorizontalStrut(30));
				btnTro.add(Box.createVerticalStrut(20));
				btnTro.setFont(new Font( "Arial", Font.BOLD, 25));
				btnTro.setBackground(new Color(72, 209, 204));
				
				pnlMenubtn.add(Box.createVerticalStrut(2));
				pnlMenubtn.add(btnSinhVien = new JButton("Sinh viên"));
				btnSinhVien.add(Box.createHorizontalStrut(30));
				btnSinhVien.add(Box.createVerticalStrut(20));
				btnSinhVien.setFont(new Font( "Arial", Font.BOLD, 25));
				btnSinhVien.setBackground(new Color(0, 206, 209));
				if(daoSV.layLoaiNV().equals("QL"))
				{
					pnlMenubtn.add(Box.createVerticalStrut(2));
					pnlMenubtn.add(btnNhanVien = new JButton("Nhân viên"));
					btnNhanVien.add(Box.createHorizontalStrut(30));
					btnNhanVien.add(Box.createVerticalStrut(20));
					btnNhanVien.setFont(new Font( "Arial", Font.BOLD, 25));
					btnNhanVien.addActionListener(this);
					btnNhanVien.setBackground(new Color(64, 224, 208));
				}
				
				
				
				pnlMenubtn.add(Box.createVerticalStrut(2));
				pnlMenubtn.add(btnThueTro = new JButton("Thuê trọ"));
				btnThueTro.add(Box.createHorizontalStrut(30));
				btnThueTro.add(Box.createVerticalStrut(20));
				btnThueTro.setFont(new Font( "Arial", Font.BOLD, 25));
				btnThueTro.setBackground(new Color(0, 245, 255));
				//pnlMenubtn.add(Box.createVerticalStrut(10));
				
				pnlMenubtn.add(Box.createVerticalStrut(2));
				pnlMenubtn.add(btnThongKe = new JButton("Thống kê"));
				btnThongKe.add(Box.createHorizontalStrut(30));
				btnThongKe.setFont(new Font( "Arial", Font.BOLD, 25));
				btnThongKe.add(Box.createVerticalStrut(20));
				btnThongKe.setBackground(new Color(0, 229, 238));
				//pnlMenubtn.add(Box.createVerticalStrut(10));
				
				pnlMenubtn.add(Box.createVerticalStrut(2));
				pnlMenubtn.add(btnHuongDanSD = new JButton("Trợ giúp"));
				btnHuongDanSD.add(Box.createHorizontalStrut(30));
				btnHuongDanSD.setFont(new Font( "Arial", Font.BOLD, 25));
				btnHuongDanSD.add(Box.createVerticalStrut(20));
				btnHuongDanSD.setBackground(new Color(0, 197, 205));
				//pnlMenubtn.add(Box.createVerticalStrut(10));
				
				pnlMenubtn.add(Box.createVerticalStrut(2));
				pnlMenubtn.add(btnDoiMK = new JButton("Đổi mật khẩu"));
				btnDoiMK.add(Box.createHorizontalStrut(30));
				btnDoiMK.setFont(new Font( "Arial", Font.BOLD, 25));
				btnDoiMK.add(Box.createVerticalStrut(20));
				btnDoiMK.setBackground(new Color(0, 197, 205));
				//pnlMenubtn.add(Box.createVerticalStrut(10));

				pnlMenubtn.add(Box.createVerticalStrut(2));
				pnlMenubtn.add(btnThoat = new JButton("Thoát"));
				btnThoat.add(Box.createHorizontalStrut(30));
				btnThoat.setFont(new Font( "Arial", Font.BOLD, 25));
				btnThoat.add(Box.createVerticalStrut(20));
				btnThoat.setBackground(new Color(0, 134, 139));
				pnlMenubtn.add(Box.createVerticalStrut(10));
				////////////////////////////////////////////////////////////////////////////////
				if(daoSV.layLoaiNV().trim().toString().equals("QL")){
					btnNhanVien.setIcon(imgNV);
				}
				
				btnTro.setIcon(imgTro);
				btnSinhVien.setIcon(imgSV);
				btnThoat.setIcon(imgexit);
				btnThongKe.setIcon(imgTK);
				btnThueTro.setIcon(imgBTT);
				btnHuongDanSD.setIcon(imgHDSD);
				btnDoiMK.setIcon(imgDoiMK);
				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//Center
		//tạo 1 boxcen chứa bcen1, bcen2, bcen3 theo chiều dọc
		Box boxCen = Box.createVerticalBox();
		pnl.add(boxCen, BorderLayout.CENTER);
		// còn lại toàn bộ phần bên phải sẽ được chia ra 3 phần bcen1, bcen2, bcen3 được định dang theo chiều dọc
		//bcen1 sẽ chưa phần tiêu đề
		//bcen2 sẽ chứa bảng
		//bcen3 sẽ chứa form thông tin và form tìm
		Box bcenTitle, bcenTable, bcenForm;
		//bcenTitle tạo tiêu đề
		boxCen.add(bcenTitle = Box.createHorizontalBox());
		//Label Title Quản lý trọ
		JPanel pnlQuanLySinhVien = new JPanel();
		pnlQuanLySinhVien.add(lblcenTitle = new JLabel("Quản lý thông tin sinh viên"));
		bcenTitle.add(pnlQuanLySinhVien);
		//chỉnh font chữ
		pnlQuanLySinhVien.setBackground(new Color(79, 79, 79));
		lblcenTitle.setFont(new Font("Arial", Font.BOLD, 40));
		lblcenTitle.setForeground(Color.WHITE);
		
	//bcenTable tạo bảng thông tin
		boxCen.add(bcenTable = Box.createHorizontalBox());
		JPanel pnlTable = new JPanel();
			JScrollPane scroll;
			
			
			String[] header="Mã sinh viên;Tên sinh viên;Ngày sinh;Quê quán;Mã lớp;Mã nhân viên;Giới tính;Khoa".split(";");
			tableModel=new DefaultTableModel(header,0);
			pnlTable.add(scroll=new JScrollPane(table=new JTable(tableModel),JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),BorderLayout.SOUTH);
			scroll.setBorder(BorderFactory.createTitledBorder("Danh Sách"));
			table.setRowHeight(20);
			scroll.setPreferredSize(new Dimension(0,500));
			bcenTable.add(scroll);
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//Form thông tin
		boxCen.add(bcenForm = Box.createVerticalBox());
		
		JPanel pnlForm = new JPanel();
		pnlForm.setSize(100, 20);
		pnlForm.setLayout(new BorderLayout());
		//pnlForm.setBackground(new Color(0, 139, 139));
		Box boxcenFormTitle, boxcenFormSV;
		
		JLabel lblThongTin = new JLabel("Thông tin");
		
		lblThongTin.setFont(new Font("Arial", Font.BOLD, 30));
		
		lblThongTin.setBackground(new Color(0, 139, 139));
		bcenForm.add(lblThongTin);
		bcenForm.setBackground(Color.LIGHT_GRAY);
		

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		bcenForm.add(boxcenFormSV = Box.createVerticalBox());
		JPanel pnlSouth = new JPanel();
		pnlSouth.setLayout(new BorderLayout());
	
		boxcenFormSV.setBackground(new Color(0, 139, 139));
		pnlSouth.add(Box.createHorizontalStrut(100), BorderLayout.WEST);
		JPanel pnlFormSV=new JPanel();
		pnlSouth.add(pnlFormSV);
		boxcenFormSV.add( pnlSouth);
		pnlFormSV.setBounds(0,80,1055,450);
		pnlFormSV.setLayout(new BoxLayout(pnlFormSV, BoxLayout.Y_AXIS));
		
		JPanel pnlFormtxt = new JPanel();
		pnlFormtxt.setLayout(new GridLayout(1, 2));
		pnlFormSV.add(pnlFormtxt);
		////////////////////////////////////////////////////////////////////////////
		
		//Form điền thông tin
		JPanel pnlFormtxtbtnSV = new JPanel();
		pnlFormtxtbtnSV.setLayout(new BoxLayout(pnlFormtxtbtnSV, BoxLayout.Y_AXIS));
		JPanel pnlFormtxtSV = new JPanel();
		pnlFormtxt.add(pnlFormtxtbtnSV);
		pnlFormtxtbtnSV.add(pnlFormtxtSV);
		
		pnlFormtxtSV.setLayout(new GridLayout(2, 4));
		Box boxMaSV = Box.createHorizontalBox();
		boxMaSV.add(lblMaSV=new JLabel("Mã sinh viên:"));
		boxMaSV.add(txtMaSV=new JTextField());
	
		
		SinhVien_Dao daosv = new SinhVien_Dao();
		
		List<SinhVien> list = daosv.layTatCaBangQL();
		list.forEach(v -> {
			String[] ma1 = v.getMaSV().split("_");				
			
			if(max<=Integer.parseInt(ma1[1].toString().trim()))
			{
				max = Integer.parseInt(ma1[1].toString().trim());
				max = max+1;
			}
		});
		
		String maSV = null;
		if(max<10)
		{
			maSV = "SV_0000"+max;
		}
		else if(max<100)
		{
			maSV = "SV_000"+max;
		}
		else if(max<1000)
		{
			maSV = "SV_00"+max;
		}
		else if(max<10000)
		{
			maSV = "SV_"+max;
		}
		else if(max<100000)
		{
			maSV = "SV_"+max;
		}
		txtMaSV.setText(maSV);
		
		txtMaSV.setEditable(false);
		Box boxMaLop = Box.createHorizontalBox();
		boxMaLop.add(lblMaLop=new JLabel("Mã Lớp: "));
		boxMaLop.add(txtMaLop=new JTextField());
		
		pnlFormtxtSV.add(boxMaSV);
		pnlFormtxtSV.add(boxMaLop);
		
		
		Box boxTenSV = Box.createHorizontalBox();
		boxTenSV.add(lblTenSV=new JLabel("Tên sinh viên: "));
		boxTenSV.add(txtTenSV=new JTextField(5));
		Box boxMaNV = Box.createHorizontalBox();
		
		List<SinhVien> listSV = new ArrayList<SinhVien>();
		if(daosv.layLoaiNV().equals("QL"))
		{
			boxMaNV.add(lblMaNV=new JLabel("Mã nhân viên: "));
			boxMaNV.add(cmpMaNV=new JComboBox<String>());
			List<String> listMaNV = daosv.layTatCaMaNV();
			listMaNV.forEach(v -> {
				cmpMaNV.addItem(v);
			});
			
		}
		else if(daosv.layLoaiNV().equals("NV"))
		{
			boxMaNV.add(lblMaNV=new JLabel("Mã nhân viên: "));
			boxMaNV.add(txtMaNV=new JTextField());
			txtMaNV.setText(daoSV.layMaNVTamLuu().trim().toString());
			txtMaNV.setEditable(false);
		}
		else {
			JOptionPane.showMessageDialog(null, "Lỗi loại NV!!");
		}
		
		pnlFormtxtSV.add(boxTenSV);
		pnlFormtxtSV.add(boxMaNV);
		
		Box boxNgaySinh = Box.createHorizontalBox();
		dateNgaySinh = new JDateChooser();
		dateNgaySinh.setDateFormatString("dd-MM-yyyy");
		boxNgaySinh.add(lblNgaySinh=new JLabel("Ngày sinh: "));
		boxNgaySinh.add(dateNgaySinh);
		Box boxGioiTinh = Box.createHorizontalBox();
		boxGioiTinh.add(lblGioiTinh=new JLabel("Giới tính: "));
		String[] gioiTinh = "Nam,Nữ".split(",");
		boxGioiTinh.add(cmpGioiTinh=new JComboBox<String>());
		for (String gt : gioiTinh) {
			cmpGioiTinh.addItem(gt);
		}
		pnlFormtxtSV.add(boxNgaySinh);
		pnlFormtxtSV.add(boxGioiTinh);
		String []queQuan =";Thành phố Hồ Chí Minh;Thành phố Hà Nội;Tỉnh An Giang;Tỉnh Bà Rịa – Vũng Tàu;Tỉnh Bạc Liêu;Tỉnh Bắc Giang;Tỉnh Bắc Kạn;Tỉnh Bắc Ninh;Tỉnh Bến Tre;Tỉnh Bình Định;Tỉnh Bình Dương;Tỉnh Bình Phước;Tỉnh Bình Thuận;Tỉnh Cà Mau;Tỉnh Cao Bằng;Thành phố Cần Thơ;Thành phố Đà Nẵng;Thành phố Hải Phòng;Tỉnh Gia Lai;Tỉnh Hòa Bình;Tỉnh Hà Giang;Tỉnh Hà Nam;Tỉnh Hưng Yên;Tỉnh Hải Dương;Tỉnh Hà Tĩnh;Tỉnh Điện Biên;Tỉnh Hậu Giang;Tỉnh Đắk Lắk;Tỉnh Đắk NôngTỉnh Đồng Nai;Tỉnh Đồng Tháp;Tỉnh Kiên Giang;Tỉnh Khánh Hòa;Tỉnh Lai Châu;Tỉnh Kon Tum;Tỉnh Long An;Tỉnh Lâm Đồng;Tỉnh Lào Cai;Tỉnh Lạng Sơn;Tỉnh Nghệ AnTỉnh Nam Định;Tỉnh Ninh Bình;Tỉnh Ninh Thuận;Tỉnh Phú Thọ;Tỉnh Phú Yên;Tỉnh Quảng Bình;Tỉnh Quảng Nam;Tỉnh Quảng Ngãi;Tỉnh Gia Lai;Tỉnh Hòa Bình;Tỉnh Hà Giang;Tỉnh Hà Nam;Tỉnh Hưng Yên;Tỉnh Hải Dương;Tỉnh Hà Tĩnh;Tỉnh Điện Biên;Tỉnh Hậu Giang;Tỉnh Đắk Lắk;Tỉnh Đắk Nông;Tỉnh Quảng Trị;Tỉnh Quảng Ninh;Tỉnh Sóc Trăng;Tỉnh Thanh Hóa;Tỉnh Sơn La;Tỉnh Thái Bình;Tỉnh Thừa Thiên – Huế;Tỉnh Thái Nguyên;Tỉnh Tiền Giang;Tỉnh Gia Lai;Tỉnh Hòa Bình;Tỉnh Hà Giang;Tỉnh Hà Nam;Tỉnh Hưng Yên;Tỉnh Hải Dương;Tỉnh Hà Tĩnh;Tỉnh Điện Biên;Tỉnh Hậu Giang;Tỉnh Đắk Lắk;Tỉnh Đắk Nông;Tỉnh Trà Vinh;Tỉnh Tuyên Quang;Tỉnh Tây Ninh;Tỉnh Vĩnh Long;Tỉnh Vĩnh Phúc;Tỉnh Yên Bái".split(";");
		Box boxQueQuan = Box.createHorizontalBox();
		boxQueQuan.add(lblQueQuan=new JLabel("Quê quán: "));
		cmpQueQuan = new JComboBox<String>();
		for (String string : queQuan) {
			cmpQueQuan.addItem(string);
		}
		cmpQueQuan.setEditable(true);
		AutoCompleteDecorator.decorate(cmpQueQuan);
		boxQueQuan.add(cmpQueQuan);
		cmpQueQuan.setEditable(true);
		Box boxKhoa = Box.createHorizontalBox();
		if(daosv.layLoaiNV().equals("QL"))
		{
			
			boxKhoa.add(lblKhoa=new JLabel("Khoa: "));
			boxKhoa.add(txtKhoa=new JTextField());
			txtKhoa.setEditable(false);
			txtKhoa.setText(daosv.layTenKhoaNV(cmpMaNV.getSelectedItem().toString().trim()));

		}
		else if(daosv.layLoaiNV().equals("NV"))
		{
			boxKhoa.add(lblKhoa=new JLabel("Khoa: "));
			boxKhoa.add(txtKhoa=new JTextField());
			txtKhoa.setEditable(false);
			txtKhoa.setText(daosv.layTenKhoaNV(txtMaNV.getText()));
			
		}
		pnlFormtxtSV.setBackground(new Color(181, 181, 181));
		pnlForm.add(boxKhoa);
		pnlFormtxtSV.add(boxQueQuan);
		
		pnlFormtxtSV.add(boxKhoa);
		// các nút chức năng thêm xóa sửa xóa trắng
		pnlFormtxtbtnSV.add(Box.createVerticalStrut(20));
		Box boxButton = Box.createHorizontalBox();
		boxButton.add(btnThem =new JButton("Thêm"));
		btnThem.setFont(new Font("Arial", Font.BOLD, 25));
		btnThem.setBackground(new Color(156, 156, 156));
		boxButton.add(Box.createHorizontalStrut(20));
		boxButton.add(btnXoa =new JButton("Xóa"));
		btnXoa.setBackground(new Color(105, 105, 105));
		btnXoa.setFont(new Font("Arial", Font.BOLD, 25));
		boxButton.add(Box.createHorizontalStrut(20));
		boxButton.add(btnSua =new JButton("Sửa"));
		btnSua.setBackground(new Color(130, 130, 130));
		btnSua.setFont(new Font("Arial", Font.BOLD, 25));
		boxButton.add(Box.createHorizontalStrut(20));
		boxButton.add(btnXoaTrang =new JButton("Xóa trắng"));
		btnXoaTrang.setFont(new Font("Arial", Font.BOLD, 25));
		btnXoaTrang.setBackground(new Color(156, 156, 156));
		pnlFormtxtbtnSV.add(Box.createVerticalStrut(5));
		pnlFormtxtbtnSV.add(boxButton);
		pnlFormtxtbtnSV.add(Box.createVerticalStrut(20));
		pnlFormtxtbtnSV.setBorder(BorderFactory.createRaisedBevelBorder());
		pnlFormtxtbtnSV.setBackground(new Color(181, 181, 181));
		 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 //Form tìm
		 JPanel pnlTim = new JPanel();
		 pnlTim.setLayout(new BoxLayout(pnlTim, BoxLayout.Y_AXIS));
		 pnlFormtxtbtnSV.add(pnlTim);
		 pnlTim.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		 
		 pnlFormTim = new JPanel();
		 pnlTim.add(pnlFormTim);
		 //JPanel pnlTim = new JPanel();
		 boxtxtTim = Box.createHorizontalBox();
		 boxtxtTim.add(lblTim=new JLabel("Tìm Thông tin sinh viên: "));
		 lblTim.setFont(new Font("Arial", Font.BOLD, 20));
		 cmpTim = new JComboBox<String>();
		 
		 
		 
		 cmpTim.setEditable(true);
		 
	      
	        
	        AutoCompleteDecorator.decorate(cmpTim);
	        
	        cmpTim.setEditable(true);
	        
	        cmpTim.addActionListener(this);
	        
	        boxtxtTim.add(cmpTim);
	        pnlFormTim.add(boxtxtTim);
	        
		 Box boxcmpTim = Box.createHorizontalBox();
		 JLabel lblTim ;
		 boxcmpTim.add(lblTim = new JLabel("__Tìm kiếm thông tin__"));
		 lblTim.setFont(new Font("Arial", Font.BOLD, 30));
		 String[] s = "Mã;Tên".split(";");
		 boxtxtTim.add(cmp = new JComboBox<String>(s));
		 pnlTim.add(boxtxtTim);
		 pnlFormTim.add(boxcmpTim);
		 
		 
		 // Button tìm
		 pnlTim.add(Box.createVerticalStrut(30));
		 Box boxbtnTim = Box.createHorizontalBox();
		 
		 pnlTim.add(btnTim = new JButton("Tìm"));
		 btnTim.setFont(new Font("Arial", Font.BOLD, 25));
		 btnTim.setBackground(new Color(232, 232, 232));
		 pnlTim.setBackground(Color.CYAN);
		 pnlTim.add(box.createVerticalStrut(40));
		 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 //điêu chỉnh kích thướt theo lblSDT
		lblMaSV.setPreferredSize(lblMaNV.getPreferredSize());
		lblGioiTinh.setPreferredSize(lblMaNV.getPreferredSize());
		lblNgaySinh.setPreferredSize(lblMaNV.getPreferredSize());
		lblMaLop.setPreferredSize(lblMaNV.getPreferredSize());
		lblTenSV.setPreferredSize(lblMaNV.getPreferredSize());
		lblKhoa.setPreferredSize(lblMaNV.getPreferredSize());
		lblQueQuan.setPreferredSize(lblMaNV.getPreferredSize());
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// phần add với action 
		btnHuongDanSD.addActionListener(this);
		
		
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
		btnDoiMK.addActionListener(this);
		table.addMouseListener(this);
		//cmpKhoa.addActionListener(this);
		SinhVien_Dao dao = new SinhVien_Dao();
		
		if(dao.layLoaiNV().equals("QL"))
		{
			cmpMaNV.addActionListener(this);
		}
		
	////////////////////////////////////////////////////////////////////////////////////////////
		
		//pnlcen3.add(Box.createHorizontalStrut(400), BorderLayout.EAST);

		setVisible(true);
		
		try {
			ConnectDB.getInstance().connect();
			addDatabase();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
public void addDatabase() {
		
	
	
		SinhVien_Dao dao = new SinhVien_Dao();
		List<SinhVien> listSV = new ArrayList<SinhVien>();
		
		if(dao.layLoaiNV().equals("QL"))
		{
			listSV = dao.layTatCaBangQL();
		}
		else if(dao.layLoaiNV().equals("NV"))
		{
			listSV = dao.layTatCaBangNV();
		}
		else {
			JOptionPane.showMessageDialog(null, "Lỗi loại NV!!");
		}
		
		//Đưa thông tin vào bảng
		listSV.forEach(v -> {
			DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/mm/yyyy");
			String ngaySinh = v.getNgaySinh().getDayOfMonth() + "/" + v.getNgaySinh().getMonthValue() + "/" +v.getNgaySinh().getYear();
			String[] row = {v.getMaSV(), v.getTenSV(),ngaySinh, v.getQueQuanSV(), v.getMaLop(), v.getMaNV().getMaNV(), v.getGioiTinh(), v.getChuyenNghanh()};
			tableModel.addRow(row);
			ma = v.getMaNV().getMaNV().toString().trim();
		});
		//cmpKhoa.setSelectedItem(dao.layTenKhoaNV(ma));
		//txtKhoa.setText(dao.layTenKhoaNV(ma));
}
	// Phương thức đọc hình ảnh từ file
	public  void setPicture(  JLabel label ,String filename ){
        try {
          BufferedImage image = ImageIO.read(new File(filename));
          int x =label.getSize().width;
          int y =label.getSize().height;
          int ix =image.getWidth();
          int iy =image.getHeight();

          int dx=x;
          int dy=y;
         

          ImageIcon icon = new ImageIcon(image.getScaledInstance(dx, dy, BufferedImage.SCALE_SMOOTH));
          label.setIcon(icon);
      } catch (IOException ex) {
    	  JOptionPane.showMessageDialog(null, "Lỗi hình ảnh");
      }
  }
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		
		SinhVien_Dao dao = new SinhVien_Dao();
		
		if(dao.layLoaiNV().equals("QL"))
		{
			if(!(cmpMaNV.getSelectedItem().equals(dao.layMaNVTamLuu())))
			{
				txtKhoa.setText((dao.layTenKhoaNV(cmpMaNV.getSelectedItem().toString().trim())));
			}
		}
		
		
		
		
		if(ob.equals(btnHuongDanSD))
		{
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
		else if(ob.equals(btnNhanVien))
		{
			removeAll();
			add(new GD_QLNhanVien());
			repaint();
			revalidate();
		}
		else if(ob.equals(btnDoiMK)) {
			new GD_DoiMK();
		}
		else if(ob.equals(btnSinhVien))
		{
			
		}
		else if(ob.equals(btnSua))
		{
			if(validData()==true)
			{

				String maSV = txtMaSV.getText();
				String tenSV = txtTenSV.getText();
				//String[] ns = txtNgaySinh.getText().split("/");
				LocalDate ngaySinh = LocalDate.of(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
				String queQuanSV = cmpQueQuan.getSelectedItem().toString().trim();
				String maLop = txtMaLop.getText();
				
				//SinhVien_Dao dao = new SinhVien_Dao();
				List<SinhVien> listSV = new ArrayList<SinhVien>();
				String maNV = null;
				String Khoa =null;
				if(dao.layLoaiNV().equals("QL"))
				{
					maNV = cmpMaNV.getSelectedItem().toString().trim();
					Khoa = dao.layTenKhoaNV(cmpMaNV.getSelectedItem().toString().trim());
				}
				else if(dao.layLoaiNV().equals("NV"))
				{
					maNV = txtMaNV.getText();
					Khoa = txtKhoa.getText().trim();
				}
				
				String gioiTinh = cmpGioiTinh.getSelectedItem().toString().trim();
				//String Khoa = txtKhoa.getSelectedItem().toString().trim();
				
				SinhVien sv = new SinhVien(maSV, tenSV, ngaySinh, queQuanSV, maLop, new NhanVien(maNV), gioiTinh, Khoa);
				if(dao.CapNhatSinhVien(sv)==true)
				{
					JOptionPane.showMessageDialog(this, "Sửa thành công!!");
					tableModel.setRowCount(0);
					addDatabase();
				}
				else {
					JOptionPane.showMessageDialog(this, "Sửa thất bại!!");
				}
			}
		}
		else if(ob.equals(btnThem))
		{
			if(validData()==true)
			{
				String tenSV = txtTenSV.getText();
				Calendar c = new GregorianCalendar();
				String maSV = txtMaSV.getText().toString().trim();
				LocalDate ngaySinh = LocalDate.of(dateNgaySinh.getJCalendar().getYearChooser().getYear(), (dateNgaySinh.getJCalendar().getMonthChooser().getMonth()+1), dateNgaySinh.getJCalendar().getDayChooser().getDay());
				String queQuanSV = cmpQueQuan.getSelectedItem().toString().trim();
				String maLop = txtMaLop.getText();
				String maNV =null;
				String Khoa =null;
				if(dao.layLoaiNV().equals("QL"))
					{
						maNV = cmpMaNV.getSelectedItem().toString().toString().trim();
						Khoa = txtKhoa.getText().toString().trim();
					}
				else if(dao.layLoaiNV().equals("NV"))
					{
						maNV = txtMaNV.getText();
						Khoa = txtKhoa.getText().toString().trim();
					}

				
				String gioiTinh = cmpGioiTinh.getSelectedItem().toString().trim();
				 
				List<SinhVien> listSV = new ArrayList<SinhVien>();
				
				
				
				SinhVien sv = new SinhVien(maSV, tenSV, ngaySinh, queQuanSV, maLop, new NhanVien(maNV), gioiTinh, Khoa);
				listSV = dao.layTatCaBangQL();
			
				
				if(!(listSV.contains(sv)))
				{
					boolean rs=dao.themSV(sv);
					if(rs==true)
					{
						JOptionPane.showMessageDialog(this, "Thêm thành công!!");
						tableModel.setRowCount(0);
						addDatabase();
					}
					else {
						JOptionPane.showMessageDialog(this, "Thêm thất bại!!");
					}
				}
				else {
					JOptionPane.showMessageDialog(this, "Mã sinh viên trùng!!!!");
				}
				
			}
			
			
		}
		else if(ob.equals(btnThoat))
		{
			int n = JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát hay không?","Thoát",JOptionPane.YES_NO_OPTION);
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
		
		
		else if(ob.equals(btnThongKe))
		{
			removeAll();
			add(new GD_ThongKe());
			repaint();
			revalidate();
		}
		else if(ob.equals(btnThueTro))
		{
			removeAll();
			add(new GD_ThongTinThueTro());
			repaint();
			revalidate();
		}
		
		else if(ob.equals(btnTro))
		{
			EventQueue.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					try {
						removeAll();
						add(new GD_QuanLyTro());
						repaint();
						revalidate();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					
				}
			});
			
		}
		else if(ob.equals(btnXoa))
		{
			
			int n = JOptionPane.showConfirmDialog(this, "bạn có chắc muốn xóa hay không?", "Xóa một dòng",JOptionPane.YES_NO_OPTION);
			if(n==JOptionPane.YES_OPTION)
			{
				//SinhVien_Dao dao = new SinhVien_Dao();
				String id = txtMaSV.getText();
				if(dao.XoaSinhVien(id)==true)
				{
					JOptionPane.showMessageDialog(this, "Xóa thành công");
					tableModel.setRowCount(0);
					addDatabase();
				}
				else {
					JOptionPane.showMessageDialog(this, "Xóa thất bại");
				}
			}	
		}
		else if(ob.equals(btnXoaTrang))
		{

			List<SinhVien> list = dao.layTatCaBangQL();
			list.forEach(v -> {
				String[] ma1 = v.getMaSV().split("_");
								
				if(max<=Integer.parseInt(ma1[1].toString().trim()))
				{
					max = Integer.parseInt(ma1[1].toString().trim());
					max = max+1;
				}
				
			});
			
			String maSV = null;
			if(max<10)
			{
				maSV = "SV_0000"+max;
			}
			else if(max<100)
			{
				maSV = "SV_000"+max;
			}
			else if(max<1000)
			{
				maSV = "SV_00"+max;
			}
			else if(max<10000)
			{
				maSV = "SV_"+max;
			}
			else if(max<100000)
			{
				maSV = "SV_"+max;
			}
			txtMaSV.setText(maSV);
			txtTenSV.setText("");
			cmpQueQuan.setSelectedItem("");
			txtMaLop.setText("");
			cmpGioiTinh.setSelectedItem("Nam");;
			SinhVien_Dao daoSV = new SinhVien_Dao();
			//txtKhoa.setSelectedItem("");
			tableModel.setRowCount(0);
			dateNgaySinh.setCalendar(null);
			addDatabase();
		}
		
		
		else if(cmp.getSelectedItem().equals("Mã"))
		{
			if(ob.equals(cmpTim))
			{
				
				if(!(cmpTim.getSelectedItem().toString().trim().equalsIgnoreCase("")))
				{

						List<SinhVien> listSV = new ArrayList<SinhVien>();
						if(dao.layLoaiNV().equals("QL"))
						{
							listSV = dao.layTatCaBangQL();
						}
						else if(dao.layLoaiNV().equals("NV"))
						{
							listSV = dao.layTatCaBangNV();
						}
						else {
							JOptionPane.showMessageDialog(null, "Lỗi loại NV!!");
						}
						int m=0;
						listSV.forEach(v -> {
							dstim = dstim+","+v.getMaSV();
						});
						
						String[] tim = dstim.split(",");
						List b= new ArrayList<String>();
						
						List a=new ArrayList<String>();
						
						int i=0;
						int j=0;
				        for (String string : tim) {
				        	try {
				        		if(!(cmpTim.getSelectedItem().toString().trim().equals("")))	
				    	        {
				    	        	if(cmpTim.getSelectedItem().toString().trim()!=null && string.equalsIgnoreCase(cmpTim.getSelectedItem().toString().trim()))
				    	        	{
				    	        		a.add(string.toString());
				    	        		i++;
				    	        	}
				    	        	if(cmpTim.getSelectedItem().toString().trim()!=null && string.contains(cmpTim.getSelectedItem().toString().trim()))
				    	        	{
				    	        		b.add(string.toString());
				    	        		j++;
				    	        	}  
				    	        }
							} catch (Exception e2) {
								
							}
						}
				       for (Object object : a) {
				    	   if(!(list.contains(object)))
				    	   {
				    		   cmpTim.addItem(object);
				    		   list.add(object.toString());
				    	   }
						
					}
			
				       for (Object object : b) {
				    	   if(!(list.contains(object)))
				    	   {
					    	   cmpTim.addItem(object);
					    	   list.add(object.toString());
				    	   }
					}
				        if(cmpTim.getSelectedItem().toString().length()<2)
				        {
				        	cmpTim.removeAllItems();
				        	list.clear();
				        	a.clear();
				        	b.clear();
				        	dstim="";
				        }
				}
			}
			
			
			if(ob.equals(btnTim))
			{
				//SinhVien_Dao dao = new SinhVien_Dao();
				
				String ma = cmpTim.getSelectedItem().toString().trim();
				if(dao.laySinhVienTheoMa(ma, nhanVienTamLuu.getMaNV(),nhanVienTamLuu.getLoaiNV())!=null)
				{
					
					SinhVien v = dao.laySinhVienTheoMa(ma,nhanVienTamLuu.getMaNV(),nhanVienTamLuu.getLoaiNV());
					DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/mm/yyyy");
					String ngaySinh = v.getNgaySinh().getDayOfMonth() + "/" + v.getNgaySinh().getMonthValue() + "/" +v.getNgaySinh().getYear();
					String[] row = {v.getMaSV(), v.getTenSV(),ngaySinh, v.getQueQuanSV(), v.getMaLop(), v.getMaNV().getMaNV(), v.getGioiTinh(), v.getChuyenNghanh()};
					
					tableModel.setRowCount(0);
					tableModel.addRow(row);
				}
				else if(dao.laySinhVienTheoMa(ma,nhanVienTamLuu.getMaNV(),nhanVienTamLuu.getLoaiNV())==null){
					cmpTim.setSelectedItem("");
					JOptionPane.showMessageDialog(this, "Tìm thất bại!");
				}
			}	
			
		}
		else if(cmp.getSelectedItem().equals("Tên"))
		{
			if(ob.equals(cmpTim))
			{
				
				if(!(cmpTim.getSelectedItem().toString().trim().equalsIgnoreCase("")))
				{

						List<SinhVien> listSV = new ArrayList<SinhVien>();
						if(dao.layLoaiNV().equals("QL"))
						{
							listSV = dao.layTatCaBangQL();
						}
						else if(dao.layLoaiNV().equals("NV"))
						{
							listSV = dao.layTatCaBangNV();
						}
						else {
							JOptionPane.showMessageDialog(null, "Lỗi loại NV!!");
						}
						int m=0;
						listSV.forEach(v -> {
							dstim = dstim+","+v.getTenSV();
						});
						
						String[] tim = dstim.split(",");
						List b= new ArrayList<String>();
						
						List a=new ArrayList<String>();
						
						int i=0;
						int j=0;
				        for (String string : tim) {
				        	try {
				        		if(!(cmpTim.getSelectedItem().toString().trim().equals("")))	
				    	        {
				    	        	if(cmpTim.getSelectedItem().toString().trim()!=null && string.equalsIgnoreCase(cmpTim.getSelectedItem().toString().trim()))
				    	        	{
				    	        		a.add(string.toString());
				    	        		i++;
				    	        	}
				    	        	if(cmpTim.getSelectedItem().toString().trim()!=null && string.contains(cmpTim.getSelectedItem().toString().trim()))
				    	        	{
				    	        		b.add(string.toString());
				    	        		j++;
				    	        	}  
				    	        }
							} catch (Exception e2) {
								
							}
				        
				        
			
						}
				       for (Object object : a) {
				    	   if(!(list.contains(object)))
				    	   {
				    		   cmpTim.addItem(object);
				    		   list.add(object.toString());
				    	   }
						
					}
			
				       for (Object object : b) {
				    	   if(!(list.contains(object)))
				    	   {
					    	   cmpTim.addItem(object);
					    	   list.add(object.toString());
				    	   }
					}
				        if(cmpTim.getSelectedItem().toString().length()<2)
				        {
				        	
				        	cmpTim.removeAllItems();
				        	list.clear();
				        	a.clear();
				        	b.clear();
				        	dstim="";
				        }
				}
			}
			
			if(ob.equals(btnTim))
			{
				//SinhVien_Dao dao = new SinhVien_Dao();
				String ten = cmpTim.getSelectedItem().toString().trim();
				if(dao.laySinhVienTheoTen(ten)!=null)
				{
					tableModel.setRowCount(0);
					dao.laySinhVienTheoTen(ten).forEach(v ->{
						DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/mm/yyyy");
						String ngaySinh = v.getNgaySinh().getDayOfMonth() + "/" + v.getNgaySinh().getMonthValue() + "/" +v.getNgaySinh().getYear();
						String[] row = {v.getMaSV(), v.getTenSV(),ngaySinh, v.getQueQuanSV(), v.getMaLop(), v.getMaNV().getMaNV(), v.getGioiTinh(), v.getChuyenNghanh()};
						tableModel.addRow(row);
					});
					
				}
				else if(dao.laySinhVienTheoTen(ten)==null){
					cmpTim.setSelectedItem("");
					JOptionPane.showMessageDialog(this, "Tìm thất bại!");
				}
			}	
			
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		txtMaSV.setText(table.getValueAt(row,0).toString());
		txtTenSV.setText(table.getValueAt(row, 1).toString());
		String[] date = table.getValueAt(row, 2).toString().split("/");
		
		Calendar ca = new GregorianCalendar();
		String day = date[0];
		String month = date[1];
		String year = date[2];

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
			dateNgaySinh.setDate(d);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		cmpQueQuan.setSelectedItem(table.getValueAt(row, 3).toString());
		txtMaLop.setText(table.getValueAt(row, 4).toString());
		cmpGioiTinh.setSelectedItem(table.getValueAt(row, 6).toString());
		
		SinhVien_Dao daosv = new SinhVien_Dao();
				List<SinhVien> listSV = new ArrayList<SinhVien>();
				if(daosv.layLoaiNV().equals("QL"))
				{
					
					cmpMaNV.setSelectedItem(table.getValueAt(row, 5).toString());
					txtKhoa.setText(table.getValueAt(row, 7).toString());
					txtMaSV.setEditable(false);
				}
				else if(daosv.layLoaiNV().equals("NV"))
				{
					txtMaNV.setText(table.getValueAt(row, 5).toString());
					txtMaNV.setEditable(false);
					txtKhoa.setText(table.getValueAt(row, 7).toString());
					txtMaSV.setEditable(false);
				}
				else {
					JOptionPane.showMessageDialog(null, "Lỗi loại NV!!");
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
	
	public boolean reangBuocDuLieu() {
		return true;
	}
	
	private boolean validData() {
		String maSV = txtMaSV.getText().trim();
		String maLop = txtMaLop.getText().trim();
		String tenSV = txtTenSV.getText().trim();
		String queQuan = cmpQueQuan.getSelectedItem().toString().trim();

		if(maLop.length()==0) {
			JOptionPane.showMessageDialog(this, "Mã lớp không được bỏ trống");
			txtMaSV.requestFocus();
			return false;
		}
		
		if(!(maLop.matches("[A-Z]{4,}[0-9]{2}"))) {
			JOptionPane.showMessageDialog(this, "Mã lớp nhập sai cấu trúc");
			txtMaSV.requestFocus();
			return false;
		}
		if(!(tenSV.length()>0)) {
			JOptionPane.showMessageDialog(this, "Tên sinh viên không được bỏ trống");
			txtTenSV.requestFocus();
			return false;
		}
		if(!(tenSV.matches("[\\p{Lu}[A-Z]][\\p{L}[a-z]]*( [\\p{Lu}[A-Z]][\\p{L}[a-z]]*)*"))) {
			JOptionPane.showMessageDialog(this, "Họ tên nhâp sai");
			txtTenSV.requestFocus();
			return false;
		}
	
		return true;
	}
	
	 
}