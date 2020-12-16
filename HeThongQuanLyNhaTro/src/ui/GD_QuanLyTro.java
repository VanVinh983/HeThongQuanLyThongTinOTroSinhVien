package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import connectDB.ConnectDB;
import dao.NhaTro_Dao;
import dao.NhanVien_Dao;
import dao.SinhVien_Dao;
import dao.TamLuuMaNhanVien_Dao;
import entity.DiaChi;
import entity.NhaTro;
import entity.NhanVien;
import entity.SinhVien;
import resourse.SetSizeByPercent;

public class GD_QuanLyTro extends JPanel implements ActionListener, MouseListener{

	private JPanel pnl;
	private NhanVien_Dao nv_Dao;
	private TamLuuMaNhanVien_Dao tamluu_dao;
	private JTextField txtMaNhatro;
	private JTextField txtChuNha;
	private JComboBox<String> JcmpQuan;
	private JComboBox<String> JcmpPhuong;
	private JTextField txtSoNha;
	private JTextField txtDuong;
	private JTextField txtSDT;
	private JComboBox<String> cmpTim;
	
	//attribute Form thông tin phần label
	private JLabel lblMaNhaTro;
	private JLabel lblChuNha;
	private JLabel lblDiaChi;
	private JLabel lblQuan;
	private JLabel lblPhuong;
	private JLabel lblSoNha;
	private JLabel lblDuong;
	private JLabel lblSDT;
	private JLabel lblTim;
	//Button Form Thông tin
	private JButton btnTim;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnXoaTrang;
	//
	
	private JComboBox<String> JcmpTimQuan;
	private JComboBox<String> JcmpTimPhuong;
	private JComboBox<String> JcmpTimSoNha;
	private JComboBox<String> JcmpTimDuong;
	
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
	private JComboBox<String> cmp;
	
	private List<String> a = new ArrayList<String>();
	private List<String> b = new ArrayList<String>();
	private List list = new ArrayList<String>();
	private String dstim ="";
	private int max=0;
	public GD_QuanLyTro(){
		nv_Dao = new NhanVien_Dao(); 
		tamluu_dao = new TamLuuMaNhanVien_Dao();
		
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(1200, 600));
		pnl = new JPanel();
		pnl.setLayout(new BorderLayout());
		Box box = Box.createVerticalBox();
	
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
		
		//Tạo ảnh và txt hiển thị user
			JPanel pnlUser = new JPanel();
					pnlUser.setBackground(new Color(108, 123, 139));
				Box buser = Box.createVerticalBox();
					Box bUser, buserImg;
					buser.add(bUser = Box.createHorizontalBox());
					NhaTro_Dao dao = new NhaTro_Dao();
					
					String tenKhoaNV = dao.layTenNhanVien().trim().toString();
					if(dao.layTenNhanVien().trim().toString().equals("NV"))
					{
						bUser.add(lblNameUser = new JLabel("GV:" + tenKhoaNV));
					}
					else if(dao.layTenNhanVien().trim().toString().equals("QL")){
						bUser.add(lblNameUser = new JLabel("Quản lý"));
					}
					lblNameUser.setFont(new Font( "Arial", Font.BOLD, 30));
					
					buser.add(buserImg = Box.createVerticalBox());
					//Hinh Anh user
					JPanel pnlImg=new JPanel();
		     		JLabel lblBanner = new JLabel();
		     		pnlImg.add(lblBanner);
					lblBanner.setSize(250,250);
					add(pnlImg);
					//đường dẫn
					setPicture(lblBanner, "HinhAnh/User.png");
					
					buserImg.add(pnlImg);
					pnlUser.add(buser);
				
					pnlUser.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		box.add(pnlUser);
		pnl.add(box, BorderLayout.WEST);
		//thêm vào màn hình chính
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
				//Thanh điều hướng 
				pnlMenubtn.add(Box.createVerticalStrut(20));
				pnlMenubtn.add(btnTro = new JButton("Trọ"));
				pnlMenubtn.setBackground(new Color(0, 238, 238));
				btnTro.add(Box.createHorizontalStrut(30));
				btnTro.add(Box.createVerticalStrut(20));
				btnTro.setFont(new Font( "Arial", Font.BOLD, 25));
				btnTro.setBackground(new Color(72, 209, 204));
				
				pnlMenubtn.add(Box.createVerticalStrut(25));
				pnlMenubtn.add(btnSinhVien = new JButton("Sinh viên"));
				btnSinhVien.add(Box.createHorizontalStrut(30));
				btnSinhVien.add(Box.createVerticalStrut(20));
				btnSinhVien.setFont(new Font( "Arial", Font.BOLD, 25));
				btnSinhVien.setBackground(new Color(0, 206, 209));
				SinhVien_Dao daosv = new SinhVien_Dao();
				if(daosv.layLoaiNV().equals("QL"))
				{
					pnlMenubtn.add(Box.createVerticalStrut(25));
					pnlMenubtn.add(btnNhanVien = new JButton("Nhân viên"));
					btnNhanVien.add(Box.createHorizontalStrut(30));
					btnNhanVien.add(Box.createVerticalStrut(20));
					btnNhanVien.setFont(new Font( "Arial", Font.BOLD, 25));
					btnNhanVien.addActionListener(this);
					btnNhanVien.setBackground(new Color(64, 224, 208));
				}
				
				
				
				pnlMenubtn.add(Box.createVerticalStrut(25));
				pnlMenubtn.add(btnThueTro = new JButton("Thuê trọ"));
				btnThueTro.add(Box.createHorizontalStrut(30));
				btnThueTro.add(Box.createVerticalStrut(20));
				btnThueTro.setFont(new Font( "Arial", Font.BOLD, 25));
				btnThueTro.setBackground(new Color(0, 245, 255));
				pnlMenubtn.add(Box.createVerticalStrut(10));
				
				pnlMenubtn.add(Box.createVerticalStrut(25));
				pnlMenubtn.add(btnThongKe = new JButton("Thống kê"));
				btnThongKe.add(Box.createHorizontalStrut(30));
				btnThongKe.setFont(new Font( "Arial", Font.BOLD, 25));
				btnThongKe.add(Box.createVerticalStrut(20));
				btnThongKe.setBackground(new Color(0, 229, 238));
				pnlMenubtn.add(Box.createVerticalStrut(10));
				
				pnlMenubtn.add(Box.createVerticalStrut(25));
				pnlMenubtn.add(btnHuongDanSD = new JButton("Trợ giúp"));
				btnHuongDanSD.add(Box.createHorizontalStrut(30));
				btnHuongDanSD.setFont(new Font( "Arial", Font.BOLD, 25));
				btnHuongDanSD.add(Box.createVerticalStrut(20));
				btnHuongDanSD.setBackground(new Color(0, 197, 205));
				pnlMenubtn.add(Box.createVerticalStrut(10));
				
				pnlMenubtn.add(Box.createVerticalStrut(25));
				pnlMenubtn.add(btnDoiMK = new JButton("Đổi mật khẩu"));
				btnDoiMK.add(Box.createHorizontalStrut(30));
				btnDoiMK.setFont(new Font( "Arial", Font.BOLD, 25));
				btnDoiMK.add(Box.createVerticalStrut(20));
				btnDoiMK.setBackground(new Color(0, 197, 205));
				pnlMenubtn.add(Box.createVerticalStrut(10));

				pnlMenubtn.add(Box.createVerticalStrut(25));
				pnlMenubtn.add(btnThoat = new JButton("Thoát"));
				btnThoat.add(Box.createHorizontalStrut(30));
				btnThoat.setFont(new Font( "Arial", Font.BOLD, 25));
				btnThoat.add(Box.createVerticalStrut(20));
				btnThoat.setBackground(new Color(0, 134, 139));
				pnlMenubtn.add(Box.createVerticalStrut(10));
				////////////////////////////////////////////////////////////////////////////////////////////
				if(dao.layTenNhanVien().trim().toString().equals("QL")){
					btnNhanVien.setIcon(imgNV);
				}
			//	btnNhanVien.setIcon(imgNV);
				btnTro.setIcon(imgTro);
				btnSinhVien.setIcon(imgSV);
				btnThoat.setIcon(imgexit);
				btnThongKe.setIcon(imgTK);
				btnThueTro.setIcon(imgBTT);
				btnHuongDanSD.setIcon(imgHDSD);
				btnDoiMK.setIcon(imgDoiMK);
				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//Center
		JPanel pnlCen = new JPanel();
		Box boxCen = Box.createVerticalBox();
		
		Box bcenTitle, bcenTable, bcenForm;
		boxCen.add(bcenTitle = Box.createHorizontalBox());
		//Label Title Quản lý trọ
		JPanel pnlQuanLyTro = new JPanel();
		pnlQuanLyTro.add(lblcenTitle = new JLabel("Quản lý nhà trọ"));
		bcenTitle.add(pnlQuanLyTro);
		pnlQuanLyTro.setBackground(new Color(79, 79, 79));
		lblcenTitle.setFont(new Font("Arial", Font.BOLD, 40));
		lblcenTitle.setForeground(Color.WHITE);
	//Table
		boxCen.add(bcenTable = Box.createHorizontalBox());

		JPanel pnlTable = new JPanel();
			JScrollPane scroll;
			String[] header="Mã nhà trọ;Tên chủ nhà;Địa chỉ;Số điện thoại".split(";");
			tableModel=new DefaultTableModel(header,0);
			pnlTable.add(scroll=new JScrollPane(table=new JTable(tableModel),JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),BorderLayout.SOUTH);
			scroll.setBorder(BorderFactory.createTitledBorder("Danh Sách"));
			table.setRowHeight(30);
			scroll.setPreferredSize(new Dimension(0,500));
			bcenTable.add(scroll);
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		pnl.add(boxCen, BorderLayout.CENTER);
		
		// South
		//Form thông tin
		boxCen.add(bcenForm = Box.createVerticalBox());
		JPanel pnlForm = new JPanel();
		pnlForm.setLayout(new BorderLayout());
		
		Box boxcenTitleForm, boxcenFormSV;
		pnlForm.add(boxcenTitleForm = box.createVerticalBox(), BorderLayout.SOUTH);

		bcenForm.add(pnlForm);
		JPanel pnlThongTin = new JPanel();
		
		pnlThongTin.add(lblThongTin = new JLabel("Thông tin"));
		boxcenTitleForm.add(pnlThongTin);
		lblThongTin.setFont(new Font("Arial", Font.BOLD, 30));
		
		pnlThongTin.setBackground(new Color(108, 123, 139));
		pnlForm.setBackground(new Color(108, 123, 139));
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		bcenForm.add(boxcenFormSV = Box.createVerticalBox());
		JPanel pnlSouth = new JPanel();
		pnlSouth.setLayout(new BorderLayout());
		pnlSouth.setBackground(new Color(108, 123, 139));
		
		pnlSouth.add(Box.createHorizontalStrut(100), BorderLayout.WEST);
		JPanel pnlFormSV=new JPanel();
		pnlSouth.add(pnlFormSV);
		boxcenFormSV.add( pnlSouth);
		pnlFormSV.setBounds(0,80,1055,450);
		pnlFormSV.setLayout(new BoxLayout(pnlFormSV, BoxLayout.Y_AXIS));
		
		//Form điền thông tin
		pnlFormSV.add(Box.createVerticalStrut(10));
		Box boxMaTro = Box.createHorizontalBox();
		
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		NhaTro_Dao daont = new NhaTro_Dao();
		
		List<NhaTro> list = daont.layTatCaBang();
		list.forEach(v -> {
			String[] ma1 = v.getMaTro().split("_");			
			
			if(max<=Integer.parseInt(ma1[1].toString().trim()))
			{
				max = Integer.parseInt(ma1[1].toString().trim());
				max = max+1;
			}
		});
		
		String maTro = null;
		if(max<10)
		{
			maTro = "NT_0000"+max;
		}
		else if(max<100)
		{
			maTro = "NT_000"+max;
		}
		else if(max<1000)
		{
			maTro = "NT_00"+max;
		}
		else if(max<10000)
		{
			maTro = "NT_"+max;
		}
		else if(max<100000)
		{
			maTro = "NT_"+max;
		}
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		boxMaTro.add(lblMaNhaTro=new JLabel("Mã Tro:"));
		boxMaTro.add(txtMaNhatro=new JTextField());
		txtMaNhatro.setText(maTro);
		pnlFormSV.add(boxMaTro);
		txtMaNhatro.setEditable(false);
		
		pnlFormSV.add(Box.createVerticalStrut(10));
		Box boxDiaChi = Box.createHorizontalBox();
		boxDiaChi.add(lblDiaChi=new JLabel("Địa chỉ: "));
		boxDiaChi.add(lblQuan = new JLabel("Quận: "));
		boxDiaChi.add(JcmpQuan = new JComboBox<String>());
		String[] item = " ,Quận 1,Quận Bình Tân,Quận 2,Quận Bình Thạnh,Quận 3,Quận Gò Vấp,Quận 4,Quận Phú Nhuận,Quận 5,Quận Tân Bình,Quận 6,Quận Tân Phú,Quận 7,Quận Thủ Đức,Quận 8,Huyện Bình Chánh,Quận 9,Huyện Cần Giờ,Quận 10,Huyện Củ Chi,Quận 11,Huyện Hóc Môn,Quận 12,Huyện Nhà Bè".split(",");
		JcmpQuan.setEditable(true);
		for (int i = 0; i < item.length; i++) {
			String string = item[i];
			JcmpQuan.addItem(string);
		}
		
		boxDiaChi.add(lblPhuong = new JLabel("Phường: "));
		boxDiaChi.add(JcmpPhuong = new JComboBox<String>());
		JcmpPhuong.setEditable(true);
		boxDiaChi.add(lblSoNha = new JLabel("Số nhà: "));
		boxDiaChi.add(txtSoNha = new JTextField());
		boxDiaChi.add(lblDuong = new JLabel("Đường: "));
		boxDiaChi.add(txtDuong = new JTextField());
		pnlFormSV.add(boxDiaChi);
		pnlFormSV.add(Box.createVerticalStrut(10));
		Box boxChuNha = Box.createHorizontalBox();
		boxChuNha.add(lblChuNha=new JLabel("Tên chủ trọ"));
		boxChuNha.add(txtChuNha=new JTextField());
		pnlFormSV.add(boxChuNha);
		
		pnlFormSV.add(Box.createVerticalStrut(10));
		Box boxSDT = Box.createHorizontalBox();
		boxSDT.add(lblSDT=new JLabel("Số điện thoại"));
		boxSDT.add(txtSDT=new JTextField());
		pnlFormSV.add(boxSDT);
		pnlFormSV.setBackground(new Color(181, 181, 181));
		// các nút chức năng thêm xóa sửa xóa trắng
		pnlFormSV.add(Box.createVerticalStrut(10));
		Box boxButton = Box.createHorizontalBox();
		boxButton.add(btnThem =new JButton("Thêm"));
		btnThem.setBackground(new Color(156, 156, 156));
		btnThem.setFont(new Font("Arial", Font.BOLD, 25));
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
		pnlFormSV.add(boxButton); 
		pnlFormSV.add(Box.createVerticalStrut(20));
		pnlFormSV.setBorder(BorderFactory.createRaisedBevelBorder());
		
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Form tìm
		
		
		 JPanel pnlTim = new JPanel();
		 pnlTim.setLayout(new BoxLayout(pnlTim, BoxLayout.Y_AXIS));
		 pnlFormSV.add(pnlTim);
		 pnlTim.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		 
		 JPanel pnlFormTim = new JPanel();
		 
		 Box boxTieuDeTim = Box.createHorizontalBox();
		 JLabel lblTieuDeTim = new JLabel("___Tìm thông tin trọ___");
		 lblTieuDeTim.setFont(new Font("Arial", Font.BOLD, 30));
		 boxTieuDeTim.add(lblTieuDeTim);
		 pnlTim.add(boxTieuDeTim);
		 pnlTim.add(Box.createVerticalStrut(20));
		 pnlFormTim = new JPanel();
		 pnlTim.add(pnlFormTim);
		 Box boxtxtTim = Box.createHorizontalBox();
		 boxtxtTim.add(lblTim=new JLabel("Tìm Thông tin sinh viên: "));
		 cmpTim = new JComboBox<String>();
		 cmpTim.setEditable(true);
	        AutoCompleteDecorator.decorate(cmpTim);
	        cmpTim.setEditable(true);
	        cmpTim.addActionListener(this);
	        
	        boxtxtTim.add(cmpTim);
	        pnlFormTim.add(boxtxtTim);
		 String[] s = "Mã;Tên;Địa chỉ".split(";");
		 Box boxcmpTim = Box.createHorizontalBox();
		 boxtxtTim.add(cmp = new JComboBox<String>(s));
		 pnlTim.add(boxtxtTim);
		 pnlTim.add(pnlFormTim);
		 
		 Box boxTimDiaChi = Box.createHorizontalBox();
		 boxTimDiaChi.add(lblDiaChi=new JLabel("Địa chỉ: "));
		 boxTimDiaChi.add(lblQuan = new JLabel("Quận: "));
		 boxTimDiaChi.add(JcmpTimQuan = new JComboBox<String>());
		 JcmpTimQuan.setEditable(true);
	     AutoCompleteDecorator.decorate(JcmpTimQuan);
	     JcmpTimQuan.setEditable(true);
	       
			
		 boxTimDiaChi.add(lblPhuong = new JLabel("Phường: "));
		 boxTimDiaChi.add(JcmpTimPhuong = new JComboBox<String>());
		 JcmpTimPhuong.setEditable(true);
	     AutoCompleteDecorator.decorate(JcmpTimPhuong);
	     JcmpTimPhuong.setEditable(true);	
		 
		 boxTimDiaChi.add(lblSoNha = new JLabel("Số nhà: "));
		 boxTimDiaChi.add(JcmpTimSoNha = new JComboBox<>());
		 JcmpTimSoNha.setEditable(true);
	     AutoCompleteDecorator.decorate(JcmpTimSoNha);
	     JcmpTimSoNha.setEditable(true);
		 
		 boxTimDiaChi.add(lblDuong = new JLabel("Đường: "));
		 boxTimDiaChi.add(JcmpTimDuong = new JComboBox<String>());
		 pnlFormTim.add(boxTimDiaChi);
		 JcmpTimDuong.setEditable(true);
	     AutoCompleteDecorator.decorate(JcmpTimDuong);
	     JcmpTimDuong.setEditable(true);
		 //compobox chọn phương thức tìm

		 
		 // Button tìm
		 pnlTim.add(Box.createVerticalStrut(5));
		 Box boxbtnTim = Box.createHorizontalBox();
		 
		 boxbtnTim.add(btnTim = new JButton("Tìm"));
		 btnTim.setFont(new Font("Arial", Font.BOLD, 25));
		 btnTim.setBackground(new Color(102, 205, 170));
		 pnlTim.add(boxbtnTim);
		 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 //điêu chỉnh kích thướt theo lblSDT
		lblMaNhaTro.setPreferredSize(lblSDT.getPreferredSize());
		lblChuNha.setPreferredSize(lblSDT.getPreferredSize());
		lblDiaChi.setPreferredSize(lblSDT.getPreferredSize());
		
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
		cmp.addActionListener(this);
		table.addMouseListener(this);
		JcmpTimQuan.addActionListener(this);
		JcmpTimPhuong.addActionListener(this);
		JcmpTimDuong.addActionListener(this);
		JcmpTimSoNha.addActionListener(this);
		
		////////////////////////////////////////////////////////////////////////////////////////
	
		setVisible(true);
		JcmpQuan.addActionListener(this);
		JcmpPhuong.addActionListener(this);
		try {
			ConnectDB.getInstance().connect();
			addDatabase();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	//Phương thức thêm toàn bộ dử liệu nhà trọ vào bảng
	public void addDatabase() {
		
		NhaTro_Dao dao = new NhaTro_Dao();
		List<NhaTro> listNhaTro = dao.layTatCaBang();
		//Đưa thông tin vào bảng
		listNhaTro.forEach(v -> {
			String diaChi = v.getDiaChiTro().getSoNha() +" - "+ v.getDiaChiTro().getTenDuong() +" - "+  v.getDiaChiTro().getTenPhuong() +" - " + v.getDiaChiTro().getTenQuan();
			String[] row = {v.getMaTro(), v.getTenChutro(),diaChi, v.getSDT()};
			tableModel.addRow(row);
		});
		
		ArrayList<String> arrSoNha = new ArrayList<String>();
		JcmpTimSoNha.addItem("");
		listNhaTro.forEach(v -> {
			
			if(!arrSoNha.contains(v.getDiaChiTro().getSoNha()))
			{
				JcmpTimSoNha.addItem(v.getDiaChiTro().getSoNha());
				arrSoNha.add(v.getDiaChiTro().getSoNha());
				
			}
		});
		
		ArrayList<String> arrTenDuong = new ArrayList<String>();
		JcmpTimDuong.addItem("");
		listNhaTro.forEach(v -> {	
		
			if(!arrTenDuong.contains(v.getDiaChiTro().getTenDuong()))
			{
				JcmpTimDuong.addItem(v.getDiaChiTro().getTenDuong());
				arrTenDuong.add(v.getDiaChiTro().getTenDuong());
				
			}
		});
		
		ArrayList<String> arrTenPhuong = new ArrayList<String>();
		JcmpTimPhuong.addItem("");
		listNhaTro.forEach(v -> {
		
			if(!arrTenPhuong.contains(v.getDiaChiTro().getTenPhuong()))
			{
				JcmpTimPhuong.addItem(v.getDiaChiTro().getTenPhuong());
				arrTenPhuong.add(v.getDiaChiTro().getTenPhuong());
			}
		});
		
		ArrayList<String> arrTenQuan = new ArrayList<String>();
		JcmpTimQuan.addItem("");
		listNhaTro.forEach(v -> {
			if(!arrTenQuan.contains(v.getDiaChiTro().getTenQuan()))
			{
				JcmpTimQuan.addItem(v.getDiaChiTro().getTenQuan());
				arrTenQuan.add(v.getDiaChiTro().getTenQuan());
			}
		});
		
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
    	  JOptionPane.showMessageDialog(null, "Loi hinh anh");
      }
  }
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		
		if(ob.equals(JcmpQuan) )
		{
			
			if(JcmpQuan.getSelectedItem().equals(""))
			{
				JcmpPhuong.removeAllItems();
				JcmpPhuong.setSelectedItem("");
			}
			else if(JcmpQuan.getSelectedItem().equals("Quận Gò Vấp"))
			{
				JcmpPhuong.removeAllItems();
				String[] item = " ,Phường 1,Phường 3,Phường 4,Phường 5,Phường 6,Phường 7,Phường 8,Phường 9,Phường 10,Phường 11,Phường 12,Phường 13,Phường 14,Phường 15,Phường 16,Phường 17".split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			}
			else if(JcmpQuan.getSelectedItem().equals("Quận 1"))
			{
				JcmpPhuong.removeAllItems();
				String[] item = " ,Phường Bến Nghé,Phường Bến Thành,Phường Cầu Kho,Phường Cầu Ông Lãnh,Phường Cô Giang,Phường Đa Kao,Phường Nguyễn Cư Trinh,Phường Nguyễn Thái Bình,Phường Phạm Ngũ Lão,Phường Tân Định".split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			}
			
			else if(JcmpQuan.getSelectedItem().equals("Quận 2"))
			{
				JcmpPhuong.removeAllItems();
				
				String[] item = " ,Phường An Khánh,Phường An Lợi Đông,Phường An Phú,Phường Bình An,Phường Bình Khánh,Phường Bình Trưng Đông,Phường Bình Trưng Tây,Phường Cát Lái,Phường Thạnh Mỹ Lợi,Phường Thảo Điền,Phường Thủ Thiêm".split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			}
			if(JcmpQuan.getSelectedItem().equals("Quận 3"))
			{
				JcmpPhuong.removeAllItems();
				String[] item = " ,Phường 1,Phường 3,Phường 4,Phường 5,Phường 6,Phường 7,Phường 8,Phường 9,Phường 10,Phường 11,Phường 12,Phường 13,Phường 14".split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			}
			else if(JcmpQuan.getSelectedItem().equals("Quận 4"))
			{
				JcmpPhuong.removeAllItems();
				String[] item = " ,Phường 1,Phường 3,Phường 4,Phường 5,Phường 6,Phường 7,Phường 8,Phường 9,Phường 10,Phường 11,Phường 12,Phường 13,Phường 14,Phường 15,Phường 16,Phường 17,Phường 18".split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			}
			else if(JcmpQuan.getSelectedItem().equals("Quận 5"))
			{
				JcmpPhuong.removeAllItems();
				String[] item = " ,Phường 1,Phường 3,Phường 4,Phường 5,Phường 6,Phường 7,Phường 8,Phường 9,Phường 10,Phường 11,Phường 12,Phường 13,Phường 14,Phường 15".split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			}
			else if(JcmpQuan.getSelectedItem().equals("Quận 6"))
			{
				JcmpPhuong.removeAllItems();
				String[] item = " ,Phường 1,Phường 3,Phường 4,Phường 5,Phường 6,Phường 7,Phường 8,Phường 9,Phường 10,Phường 11,Phường 12,Phường 13,Phường 14".split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			}

			else if(JcmpQuan.getSelectedItem().equals("Quận 7"))
			{
				JcmpPhuong.removeAllItems();
				String[] item = " ,Phường Bình Thuận,Phường Phú Mỹ,Phường Phú Thuận,Phường Tân Hưng,Phường Tân Kiểng,Phường Tân Phong,Phường Tân Phú,Phường Tân Quy,Phường Tân Thuận Đông,Phường Tân Thuận Tây".split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			}
			else if(JcmpQuan.getSelectedItem().equals("Quận 8"))
			{
				JcmpPhuong.removeAllItems();
				String[] item = " ,Phường 1,Phường 3,Phường 4,Phường 5,Phường 6,Phường 7,Phường 8,Phường 9,Phường 10,Phường 11,Phường 12,Phường 13,Phường 14,Phường 15,Phường 16".split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			}
	
			else if(JcmpQuan.getSelectedItem().equals("Quận 9"))
			{
				JcmpPhuong.removeAllItems();
				String[] item = " ,Phường Hiệp Phú,Phường Long Bình,Phường Long Phước,Phường Long Thạnh Mỹ,Phường Long Trường,Phường Phú Hữu,Phường Phước Bình,Phường Phước Long A,Phường Phước Long B,Phường Tân Phú,Phường Tăng Nhơn Phú A,Phường Tăng Nhơn Phú B,Phường Trường Thạnh".split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			}
			//
			else if(JcmpQuan.getSelectedItem().equals("Quận 10"))
			{
				JcmpPhuong.removeAllItems();
				String[] item = " ,Phường 1,Phường 3,Phường 4,Phường 5,Phường 6,Phường 7,Phường 8,Phường 9,Phường 10,Phường 11,Phường 12,Phường 13,Phường 14,Phường 15".split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			}
			else if(JcmpQuan.getSelectedItem().equals("Quận 11"))
			{
				JcmpPhuong.removeAllItems();
				String[] item = " ,Phường 1,Phường 3,Phường 4,Phường 5,Phường 6,Phường 7,Phường 8,Phường 9,Phường 10,Phường 11,Phường 12,Phường 13,Phường 14,Phường 15,Phường 16".split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			}
			else if(JcmpQuan.getSelectedItem().equals("Quận 12"))
			{
				JcmpPhuong.removeAllItems();
				String[] item = " ,Phường An Phú Đông,Phường Đông Hưng Thuận,Phường Hiệp Thành,Phường Tân Chánh Hiệp,Phường Tân Hưng Thuận,Phường Tân Thới Hiệp,Phường Tân Thới Nhất,Phường Thạnh Lộc,Phường Thạnh Xuân,Phường Thới An,Phường Trung Mỹ Tây".split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			}
			
			else if(JcmpQuan.getSelectedItem().equals("Quận Bình Tân"))
			{
				JcmpPhuong.removeAllItems();
				String[] item = " ,Phường An Lạc,Phường An Lạc A,Phường Bình Hưng Hòa,Phường Bình Hưng Hoà A,Phường Bình Hưng Hoà B,Phường Bình Trị Đông,Phường Bình Trị Đông A,Phường Bình Trị Đông B,Phường Tân Tạo,Phường Tân Tạo A".split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			}
			
			else if(JcmpQuan.getSelectedItem().equals("Quận Bình Thạnh"))
			{
				JcmpPhuong.removeAllItems();
				String[] item = " ,Phường 1,Phường 3,Phường 4,Phường 5,Phường 6,Phường 7,Phường 8,Phường 9,Phường 10,Phường 11,Phường 12,Phường 13,Phường 14,Phường 15,Phường 16,Phường 17,Phường 19,Phường 21,Phường 22,Phường 24,Phường 25,Phường 26,Phường 27,Phường 28".split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			}
			else if(JcmpQuan.getSelectedItem().equals("Quận Phú Nhuận"))
			{
				JcmpPhuong.removeAllItems();
				String[] item = " ,Phường 1,Phường 3,Phường 4,Phường 5,Phường 7,Phường 8,Phường 9,Phường 10,Phường 11,Phường 12,Phường 13,Phường 14,Phường 15,Phường 17".split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			}
			else if(JcmpQuan.getSelectedItem().equals("Quận Tân Bình"))
			{
				JcmpPhuong.removeAllItems();
				String[] item = " ,Phường 1,Phường 3,Phường 4,Phường 5,Phường 6,Phường 7,Phường 8,Phường 9,Phường 10,Phường 11,Phường 12,Phường 13,Phường 14,Phường 15".split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			}
			else if(JcmpQuan.getSelectedItem().equals("Quận Tân Phú"))
			{
				JcmpPhuong.removeAllItems();
				String[] item = " ,Phường Hiệp Tân,Phường Hoà Thạnh,Phường Phú Thạnh,Phường Phú Thọ Hoà,Phường Phú Trung,Phường Sơn Kỳ,Phường Tân Qúy,Phường Tân Sơn Nhì,Phường Tân Thành,Phường Tân Thới Hoà,Phường Tây Thạnh".split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			}
			else if(JcmpQuan.getSelectedItem().equals("Quận Thủ Đức"))
			{
				JcmpPhuong.removeAllItems();
				String[] item = " ,Phường Bình Chiểu,Phường Bình Thọ,Phường Hiệp Bình Chánh,Phường Hiệp Bình Phước,Phường Linh Chiểu,Phường Linh Đông,Phường Linh Tây,Phường Linh Trung,Phường Linh Xuân,Phường Tam Bình,Phường Tam Phú,Phường Trường Thọ".split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			}
			if(JcmpQuan.getSelectedItem().equals("Quận Bình Chánh"))
			{
				JcmpPhuong.removeAllItems();
				String[] item = " ,Thị trấn Tân Túc,Xã An Phú Tây,Xã Bình Chánh,Xã Bình Hưng,Xã Bình Lợi,Xã Đa Phước,Xã Hưng Long,Xã Lê Minh Xuân,Xã Phạm Văn Hai,Xã Phong Phú,Xã Quy Đức,Xã Tân Kiên,Xã Tân Nhựt,Xã Tân Quý Tây,Xã Vĩnh Lộc A,Xã Vĩnh Lộc B".split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			}
			if(JcmpQuan.getSelectedItem().equals("Quận Cần Giờ"))
			{
				JcmpPhuong.removeAllItems();
				String[] item = " ,Thị trấn Cần Thạnh,Xã An Thới Đông,Xã Bình Khánh,Xã Long Hòa,Xã Lý Nhơn,Xã Tam Thôn Hiệp,Xã Thạnh An".split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			}
			else if(JcmpQuan.getSelectedItem().equals("Quận Củ Chi"))
			{
				JcmpPhuong.removeAllItems();
				String[] item = " ,Thị trấn Củ Chi,Xã An Nhơn Tây,Xã An Phú,Xã Bình Mỹ,Xã Hòa Phú,Xã Nhuận Đức,Xã Phạm Văn Cội,Xã Phú Hòa Đông,Xã Phú Mỹ Hưng,Xã Phước Hiệp,Xã Phước Thạnh,Xã Phước Vĩnh An,Xã Tân An Hội,Xã Tân Phú Trung,Xã Tân Thạnh Đông,Xã Tân Thạnh Tây,Xã Tân Thông Hội,Xã Thái Mỹ,Xã Trung An,Xã Trung Lập Hạ,Xã Trung Lập Thượng".split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			}
			else if(JcmpQuan.getSelectedItem().equals("Quận Hóc Môn"))
			{
				JcmpPhuong.removeAllItems();
				String[] item = " ,Thị trấn Hóc Môn,Xã Bà Điểm,Xã Đông Thạnh,Xã Nhị Bình,Xã Tân Hiệp,Xã Tân Thới Nhì,Xã Tân Xuân,Xã Thới Tam Thôn,Xã Trung Chánh,Xã Xuân Thới Đông,Xã Xuân Thới Sơn,Xã Xuân Thới Thượng".split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			}
			

			else if(JcmpQuan.getSelectedItem().equals("Quận Hóc Môn"))
			{
				JcmpPhuong.removeAllItems();
				String[] item = " ,Thị trấn Nhà Bè,Xã Hiệp Phước,Xã Long Thới,Xã Nhơn Đức,Xã Phú Xuân,Xã Phước Kiển,Xã Phước Lộc".split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
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
		else if(ob.equals(btnDoiMK)) {
			new GD_DoiMK();
		}
		else if(ob.equals(btnNhanVien))
		{
			removeAll();
			add(new GD_QLNhanVien());
			repaint();
			revalidate();
		}
		else if(ob.equals(btnSinhVien))
		{
			EventQueue.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					try {
						removeAll();
						add(new GD_QuanLySinhVien());
						repaint();
						revalidate();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					
				}
			});
			
		}
		else if(ob.equals(btnSua))
		{
			if(validData()==true)
			{
				NhaTro_Dao daoNhaTro= new NhaTro_Dao();
				String maTro = txtMaNhatro.getText();
				String tenChutro = txtChuNha.getText();
				String SDT = txtSDT.getText();
				String tenQuan = JcmpQuan.getSelectedItem().toString();
				String tenPhuong = JcmpPhuong.getSelectedItem().toString();
				String soNha = txtSoNha.getText().toString().trim();
				String tenDuong = txtDuong.getText().toString().trim();
				NhaTro nhaTro = new NhaTro(maTro, tenChutro, SDT, new DiaChi(tenQuan, tenPhuong, soNha, tenDuong));
				
				if(daoNhaTro.CapNhatNhaTroSinhVien(nhaTro)==true)
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
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				//Phat sinh ma tu dong
				NhaTro_Dao daont = new NhaTro_Dao();

				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				String maTro = txtMaNhatro.getText().toString().trim();
				String tenChutro = txtChuNha.getText();
				String sDT = txtSDT.getText();
				String tenQuan = JcmpQuan.getSelectedItem().toString();
				String tenPhuong = JcmpPhuong.getSelectedItem().toString();
				String soNha = txtSoNha.getText().toString().trim();
				String tenDuong = txtDuong.getText().toString().trim();
				NhaTro_Dao dao = new NhaTro_Dao();
				boolean result = dao.them(new NhaTro(maTro, tenChutro, sDT, new DiaChi(tenQuan, tenPhuong, soNha, tenDuong)));
				if(result==false)
				{
					JOptionPane.showMessageDialog(this, "Mã trọ bị trùng!!!");
				}
				else {
					JOptionPane.showMessageDialog(this, "Thêm thành công!!!");
				}
				tableModel.setRowCount(0);
				addDatabase();
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
			
		}
		else if(ob.equals(btnXoa))
		{
			int n = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa hay không?","Xóa một dòng",JOptionPane.YES_NO_OPTION);
			if(n==JOptionPane.YES_OPTION)
			{
				String maNhaTro = txtMaNhatro.getText().toString();
				NhaTro_Dao dao = new NhaTro_Dao();
				boolean result = dao.XoaNhaTroSinhVien(maNhaTro);
				if(result==false)
				{
					JOptionPane.showMessageDialog(this, "Xóa thất bại!!!");
				}
				else {
					JOptionPane.showMessageDialog(this, "Xóa thành công!!!");
				}
				tableModel.setRowCount(0);
				addDatabase();
			}
			
		}
		else if(ob.equals(btnXoaTrang))
		{
			//setText ma nha tro moi khong trung vao txtMaNhaTro
			NhaTro_Dao daont = new NhaTro_Dao();
			
			List<NhaTro> list = daont.layTatCaBang();
			list.forEach(v -> {
				String[] ma1 = v.getMaTro().split("_");			
				
				if(max<=Integer.parseInt(ma1[1].toString().trim()))
				{
					max = Integer.parseInt(ma1[1].toString().trim());
					max = max+1;
				}
			});
			
			String maTro = null;
			if(max<10)
			{
				maTro = "NT_0000"+max;
			}
			else if(max<100)
			{
				maTro = "NT_000"+max;
			}
			else if(max<1000)
			{
				maTro = "NT_00"+max;
			}
			else if(max<10000)
			{
				maTro = "NT_"+max;
			}
			else if(max<100000)
			{
				maTro = "NT_"+max;
			}
			txtMaNhatro.setText(maTro);
			txtChuNha.setText("");
			txtSDT.setText("");
			cmpTim.setSelectedItem("");
			JcmpQuan.setSelectedItem("");
			JcmpPhuong.setSelectedItem("");
			txtSoNha.setText("");
			txtDuong.setText("");
			tableModel.setRowCount(0);
			JcmpTimDuong.setSelectedItem("");
			JcmpTimPhuong.setSelectedItem("");
			JcmpTimQuan.setSelectedItem("");
			JcmpTimSoNha.setSelectedItem("");
			addDatabase();
		}
		
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		else if(cmp.getSelectedItem().equals("Mã"))
			{
			try {
				if(ob.equals(cmpTim))
				{
					if(!(cmpTim.getSelectedItem().toString().trim().equalsIgnoreCase("")))
					{
							NhaTro_Dao daont = new NhaTro_Dao();
							List<NhaTro> listSV = new ArrayList<NhaTro>();
							listSV = daont.layTatCaBang();
							int m=0;
							listSV.forEach(v -> {
								dstim = dstim+","+v.getMaTro();
							});
						
							String[] tim = dstim.split(",");
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
					        
					        for (String string : a) {
					        	if(!(list.contains(string)))
						    	   {
						    		   cmpTim.addItem(string);
						    		   list.add(string.toString());
						    	   }
							}
			
					       for (String string : b) {
					    	   if(!(list.contains(string)))
					    	   {
						    	   cmpTim.addItem(string);
						    	   list.add(string.toString());
					    	   }
						}
					       cmpTim.addItem("");
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
					NhaTro_Dao dao = new NhaTro_Dao();
					String ma = cmpTim.getSelectedItem().toString().trim();
					if(dao.layTroTheoMa(ma)!=null)
					{
						tableModel.setRowCount(0);
						NhaTro nhaTro = dao.layTroTheoMa(ma);
							String maTro = nhaTro.getMaTro();
							String tenChuNha = nhaTro.getTenChutro();
							String diaChi = nhaTro.getDiaChiTro().getSoNha() +" - "+ nhaTro.getDiaChiTro().getTenDuong() +" - "+  nhaTro.getDiaChiTro().getTenPhuong() +" - " + nhaTro.getDiaChiTro().getTenQuan();
							String SDT = nhaTro.getSDT();
							String[] row = {maTro,tenChuNha, diaChi, SDT};
							tableModel.addRow(row);
						
						
					}
					else if(dao.layTroTheoMa(ma)==null){
						cmpTim.setSelectedItem("");
						JOptionPane.showMessageDialog(this, "Tìm thất bại!");
					}
				}	
			} catch (Exception e2) {
				// TODO: handle exception
			}
				
				
			}
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		else if(cmp.getSelectedItem().equals("Tên"))
		{
			try {
				if(ob.equals(cmpTim))
				{
					 for (String string : a) {
				        	cmpTim.removeItem(string);
						}
		
				       for (String string : b) {
				    	   cmpTim.removeItem(string);
					}
					if(!(cmpTim.getSelectedItem().toString().trim().equalsIgnoreCase("")))
					{
							NhaTro_Dao daont = new NhaTro_Dao();
							List<NhaTro> listSV = new ArrayList<NhaTro>();
							
							listSV = daont.layTatCaBang();
							
							int m=0;
							listSV.forEach(v -> {
								dstim = dstim+","+v.getTenChutro();
							});
							
							String[] tim = dstim.split(",");
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
					        for (String string : a) {
					        	if(!(list.contains(string)))
						    	   {
						    		   cmpTim.addItem(string);
						    		   list.add(string.toString());
						    	   }
							}
			
					       for (String string : b) {
					    	   if(!(list.contains(string)))
					    	   {
						    	   cmpTim.addItem(string);
						    	   list.add(string.toString());
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
					NhaTro_Dao dao = new NhaTro_Dao();
					String tenChuTro = cmpTim.getSelectedItem().toString().trim();
					if(dao.layNhaTroTheoTenChuTro(tenChuTro)!=null)
					{
						tableModel.setRowCount(0);
						dao.layNhaTroTheoTenChuTro(tenChuTro).forEach(v -> {
							String maTro = v.getMaTro();
							String tenChuNha = v.getTenChutro();
							String diaChi = v.getDiaChiTro().getSoNha() +" - "+ v.getDiaChiTro().getTenDuong() +" - "+  v.getDiaChiTro().getTenPhuong() +" - " + v.getDiaChiTro().getTenQuan();
							String SDT = v.getSDT();
							String[] row = {maTro,tenChuNha, diaChi, SDT};
							tableModel.addRow(row);
						});
						
					}
					else if(dao.layNhaTroTheoTenChuTro(tenChuTro)==null){
						cmpTim.setSelectedItem("");
						JOptionPane.showMessageDialog(this, "Tìm thất bại!");
					}
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
				
			
		}

		
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		else if(cmp.getSelectedItem().equals("Địa chỉ"))
		{
			try {
				if(!(JcmpTimDuong.getSelectedItem().equals("")) && JcmpTimSoNha.getSelectedItem().equals("") && JcmpTimPhuong.getSelectedItem().equals("") && JcmpTimQuan.getSelectedItem().equals(""))
				{
					if(JcmpTimDuong.getSelectedItem().toString().trim()!="" )
					{
						NhaTro_Dao dao = new NhaTro_Dao();
						String tDuong = JcmpTimDuong.getSelectedItem().toString().trim();
						if(dao.layNhaTroTheoTenDuong(tDuong)!=null)
						{
							tableModel.setRowCount(0);
							dao.layNhaTroTheoTenDuong(tDuong).forEach(v -> {
								String maTro = v.getMaTro();
								String tenChuNha = v.getTenChutro();
								String diaChi = v.getDiaChiTro().getSoNha() +" - "+ v.getDiaChiTro().getTenDuong() +" - "+  v.getDiaChiTro().getTenPhuong() +" - " + v.getDiaChiTro().getTenQuan();
								String SDT = v.getSDT();
								String[] row = {maTro,tenChuNha, diaChi, SDT};
								tableModel.addRow(row);
							});
							
						}
						else if(dao.layNhaTroTheoTenDuong(tDuong)==null){
							JOptionPane.showMessageDialog(this, "Tìm thất bại!");
						}
					}
				}
				
				if(JcmpTimDuong.getSelectedItem().equals("") && !(JcmpTimSoNha.getSelectedItem().equals("")) && JcmpTimPhuong.getSelectedItem().equals("") && JcmpTimQuan.getSelectedItem().equals(""))
				{
					if(JcmpTimSoNha.getSelectedItem().toString().trim()!="" )
					{
						NhaTro_Dao dao = new NhaTro_Dao();
						String sn = JcmpTimSoNha.getSelectedItem().toString().trim();
						if(dao.layNhaTroTheoSoNha(sn)!=null)
						{
							tableModel.setRowCount(0);
							dao.layNhaTroTheoSoNha(sn).forEach(v -> {
								String maTro = v.getMaTro();
								String tenChuNha = v.getTenChutro();
								String diaChi = v.getDiaChiTro().getSoNha() +" - "+ v.getDiaChiTro().getTenDuong() +" - "+  v.getDiaChiTro().getTenPhuong() +" - " + v.getDiaChiTro().getTenQuan();
								String SDT = v.getSDT();
								String[] row = {maTro,tenChuNha, diaChi, SDT};
								tableModel.addRow(row);
							});
							
						}
						else if(dao.layNhaTroTheoSoNha(sn)==null){
							JOptionPane.showMessageDialog(this, "Tìm thất bại!");
						}
					}
				}
				if(JcmpTimDuong.getSelectedItem().equals("") && JcmpTimSoNha.getSelectedItem().equals("") && !(JcmpTimPhuong.getSelectedItem().equals("")) && JcmpTimQuan.getSelectedItem().equals(""))
				{
					if(JcmpTimPhuong.getSelectedItem().toString().trim()!="" )
					{
						NhaTro_Dao dao = new NhaTro_Dao();
						String tenPhuong = JcmpTimPhuong.getSelectedItem().toString().trim();
						if(dao.layNhaTroTheoPhuong(tenPhuong)!=null)
						{
							tableModel.setRowCount(0);
							dao.layNhaTroTheoPhuong(tenPhuong).forEach(v -> {
								String maTro = v.getMaTro();
								String tenChuNha = v.getTenChutro();
								String diaChi = v.getDiaChiTro().getSoNha() +" - "+ v.getDiaChiTro().getTenDuong() +" - "+  v.getDiaChiTro().getTenPhuong() +" - " + v.getDiaChiTro().getTenQuan();
								String SDT = v.getSDT();
								String[] row = {maTro,tenChuNha, diaChi, SDT};
								tableModel.addRow(row);
							});
							
						}
						else if(dao.layNhaTroTheoPhuong(tenPhuong)==null){
							JOptionPane.showMessageDialog(this, "Tìm thất bại!");
						}
					}
				}
				
				if(JcmpTimDuong.getSelectedItem().equals("") && JcmpTimSoNha.getSelectedItem().equals("") && JcmpTimPhuong.getSelectedItem().equals("") && !(JcmpTimQuan.getSelectedItem().equals("")))
				{
					if(JcmpTimQuan.getSelectedItem().toString().trim()!="" )
					{
					
						NhaTro_Dao dao = new NhaTro_Dao();
						String tenQuan = JcmpTimQuan.getSelectedItem().toString().trim();
						if(dao.layNhaTroTheoQuan(tenQuan)!=null)
						{
							tableModel.setRowCount(0);
							dao.layNhaTroTheoQuan(tenQuan).forEach(v -> {
								String maTro = v.getMaTro();
								String tenChuNha = v.getTenChutro();
								String diaChi = v.getDiaChiTro().getSoNha() +" - "+ v.getDiaChiTro().getTenDuong() +" - "+  v.getDiaChiTro().getTenPhuong() +" - " + v.getDiaChiTro().getTenQuan();
								String SDT = v.getSDT();
								String[] row = {maTro,tenChuNha, diaChi, SDT};
								tableModel.addRow(row);
							});
							
						}
						else if(dao.layNhaTroTheoQuan(tenQuan)==null){
							JOptionPane.showMessageDialog(this, "Tìm thất bại!");
						}
					}
				}
			//2	//////////////////////////////////////////////////////////////////////////////////////////////////////
				if(JcmpTimDuong.getSelectedItem().equals("") && JcmpTimSoNha.getSelectedItem().equals("") && !(JcmpTimPhuong.getSelectedItem().equals("")) && !(JcmpTimQuan.getSelectedItem().equals("")))
				{
					if(JcmpTimQuan.getSelectedItem().toString().trim()!="" && JcmpTimPhuong.getSelectedItem().toString().trim()!="")
					{
					
						NhaTro_Dao dao = new NhaTro_Dao();
						String tenQuan = JcmpTimQuan.getSelectedItem().toString().trim();
						String tenPhuong = JcmpTimPhuong.getSelectedItem().toString().trim();
						if(dao.layNhaTroTheoPhuongQuan(tenQuan, tenPhuong)!=null)
						{
							tableModel.setRowCount(0);
							dao.layNhaTroTheoPhuongQuan(tenQuan, tenPhuong).forEach(v -> {
								String maTro = v.getMaTro();
								String tenChuNha = v.getTenChutro();
								String diaChi = v.getDiaChiTro().getSoNha() +" - "+ v.getDiaChiTro().getTenDuong() +" - "+  v.getDiaChiTro().getTenPhuong() +" - " + v.getDiaChiTro().getTenQuan();
								String SDT = v.getSDT();
								String[] row = {maTro,tenChuNha, diaChi, SDT};
								tableModel.addRow(row);
							});
							
						}
						else if(dao.layNhaTroTheoPhuongQuan(tenQuan, tenPhuong)==null){
							JOptionPane.showMessageDialog(this, "Tìm thất bại!");
						}
					}
				}
				
				//Tim theo Quan va ten Duong
				if(!(JcmpTimDuong.getSelectedItem().equals("")) && JcmpTimSoNha.getSelectedItem().equals("") && JcmpTimPhuong.getSelectedItem().equals("") && !(JcmpTimQuan.getSelectedItem().equals("")))
				{
					if(JcmpTimQuan.getSelectedItem().toString().trim()!="" && JcmpTimDuong.getSelectedItem().toString().trim()!="")
					{
					
						NhaTro_Dao dao = new NhaTro_Dao();
						String tQuan = JcmpTimQuan.getSelectedItem().toString().trim();
						String tDuong = JcmpTimDuong.getSelectedItem().toString().trim();
						if(dao.layNhaTroTheoQuanTenDuong(tQuan, tDuong)!=null)
						{
							tableModel.setRowCount(0);
							dao.layNhaTroTheoQuanTenDuong(tQuan, tDuong).forEach(v -> {
								String maTro = v.getMaTro();
								String tenChuNha = v.getTenChutro();
								String diaChi = v.getDiaChiTro().getSoNha() +" - "+ v.getDiaChiTro().getTenDuong() +" - "+  v.getDiaChiTro().getTenPhuong() +" - " + v.getDiaChiTro().getTenQuan();
								String SDT = v.getSDT();
								String[] row = {maTro,tenChuNha, diaChi, SDT};
								tableModel.addRow(row);
							});
							
						}
						else if(dao.layNhaTroTheoQuanTenDuong(tQuan, tDuong)==null){
							JOptionPane.showMessageDialog(this, "Tìm thất bại!");
						}
					}
				}
				

				//Tim theo quan va so nha
				if(JcmpTimDuong.getSelectedItem().equals("") && !(JcmpTimSoNha.getSelectedItem().equals("")) && JcmpTimPhuong.getSelectedItem().equals("") && !(JcmpTimQuan.getSelectedItem().equals("")))
				{
					if(JcmpTimQuan.getSelectedItem().toString().trim()!="" && JcmpTimSoNha.getSelectedItem().toString().trim()!="")
					{
					
						NhaTro_Dao dao = new NhaTro_Dao();
						String tQuan = JcmpTimQuan.getSelectedItem().toString().trim();
						String sn = JcmpTimSoNha.getSelectedItem().toString().trim();
						if(dao.layNhaTroTheoQuanSoNha(tQuan, sn)!=null)
						{
							tableModel.setRowCount(0);
							dao.layNhaTroTheoQuanSoNha(tQuan, sn).forEach(v -> {
								String maTro = v.getMaTro();
								String tenChuNha = v.getTenChutro();
								String diaChi = v.getDiaChiTro().getSoNha() +" - "+ v.getDiaChiTro().getTenDuong() +" - "+  v.getDiaChiTro().getTenPhuong() +" - " + v.getDiaChiTro().getTenQuan();
								String SDT = v.getSDT();
								String[] row = {maTro,tenChuNha, diaChi, SDT};
								tableModel.addRow(row);
							});
							
						}
						else if(dao.layNhaTroTheoQuanSoNha(tQuan, sn)==null){
							JOptionPane.showMessageDialog(this, "Tìm thất bại!");
						}
					}
				}
					//Tim theo phuong va ten duong
					if(!(JcmpTimDuong.getSelectedItem().equals("")) && JcmpTimSoNha.getSelectedItem().equals("") && !(JcmpTimPhuong.getSelectedItem().equals("")) && JcmpTimQuan.getSelectedItem().equals(""))
					{
						if(JcmpTimDuong.getSelectedItem().toString().trim()!="" && JcmpTimPhuong.getSelectedItem().toString().trim()!="")
						{
						
							NhaTro_Dao dao = new NhaTro_Dao();
							String Phuong = JcmpTimPhuong.getSelectedItem().toString().trim();
							String td = JcmpTimDuong.getSelectedItem().toString().trim();
							if(dao.layNhaTroTheoPhuongTenDuong(Phuong, td)!=null)
							{
								tableModel.setRowCount(0);
								dao.layNhaTroTheoPhuongTenDuong(Phuong, td).forEach(v -> {
									String maTro = v.getMaTro();
									String tenChuNha = v.getTenChutro();
									String diaChi = v.getDiaChiTro().getSoNha() +" - "+ v.getDiaChiTro().getTenDuong() +" - "+  v.getDiaChiTro().getTenPhuong() +" - " + v.getDiaChiTro().getTenQuan();
									String SDT = v.getSDT();
									String[] row = {maTro,tenChuNha, diaChi, SDT};
									tableModel.addRow(row);
								});
								
							}
							else if(dao.layNhaTroTheoPhuongTenDuong(Phuong, td)==null){
								JOptionPane.showMessageDialog(this, "Tìm thất bại!");
							}
						}
					}
					//Tim theo phuong va so nha
					if(JcmpTimDuong.getSelectedItem().equals("") && !(JcmpTimSoNha.getSelectedItem().equals("")) && !(JcmpTimPhuong.getSelectedItem().equals("")) && JcmpTimQuan.getSelectedItem().equals(""))
					{
						if(JcmpTimPhuong.getSelectedItem().toString().trim()!="" && JcmpTimSoNha.getSelectedItem().toString().trim()!="")
						{
							NhaTro_Dao dao = new NhaTro_Dao();
							String Phuong = JcmpTimPhuong.getSelectedItem().toString().trim();
							String sn = JcmpTimSoNha.getSelectedItem().toString().trim();
							if(dao.layNhaTroTheoPhuongSoNha(Phuong, sn)!=null)
							{
								tableModel.setRowCount(0);
								dao.layNhaTroTheoPhuongSoNha(Phuong, sn).forEach(v -> {
									String maTro = v.getMaTro();
									String tenChuNha = v.getTenChutro();
									String diaChi = v.getDiaChiTro().getSoNha() +" - "+ v.getDiaChiTro().getTenDuong() +" - "+  v.getDiaChiTro().getTenPhuong() +" - " + v.getDiaChiTro().getTenQuan();
									String SDT = v.getSDT();
									String[] row = {maTro,tenChuNha, diaChi, SDT};
									tableModel.addRow(row);
								});
								
							}
							else if(dao.layNhaTroTheoPhuongSoNha(Phuong, sn)==null){
								JOptionPane.showMessageDialog(this, "Tìm thất bại!");
							}
						}
					}
					//Tim theo ten duong so nha
					if(!(JcmpTimDuong.getSelectedItem().equals("")) && !(JcmpTimSoNha.getSelectedItem().equals("")) && JcmpTimPhuong.getSelectedItem().equals("") && JcmpTimQuan.getSelectedItem().equals(""))
					{
						if(JcmpTimDuong.getSelectedItem().toString().trim()!="" && JcmpTimSoNha.getSelectedItem().toString().trim()!="")
						{
							NhaTro_Dao dao = new NhaTro_Dao();
							String td = JcmpTimDuong.getSelectedItem().toString().trim();
							String sn = JcmpTimSoNha.getSelectedItem().toString().trim();
							if(dao.layNhaTroTheoTenDuongSoNha(td, sn)!=null)
							{
								tableModel.setRowCount(0);
								dao.layNhaTroTheoTenDuongSoNha(td, sn).forEach(v -> {
									String maTro = v.getMaTro();
									String tenChuNha = v.getTenChutro();
									String diaChi = v.getDiaChiTro().getSoNha() +" - "+ v.getDiaChiTro().getTenDuong() +" - "+  v.getDiaChiTro().getTenPhuong() +" - " + v.getDiaChiTro().getTenQuan();
									String SDT = v.getSDT();
									String[] row = {maTro,tenChuNha, diaChi, SDT};
									tableModel.addRow(row);
								});
								
							}
							else if(dao.layNhaTroTheoTenDuongSoNha(td, sn)==null){
								JOptionPane.showMessageDialog(this, "Tìm thất bại!");
							}
						}
					}
					
					//Tim theo phuong, quan, so nha
					if(JcmpTimDuong.getSelectedItem().equals("") && !(JcmpTimSoNha.getSelectedItem().equals("")) && !(JcmpTimPhuong.getSelectedItem().equals("")) && !(JcmpTimQuan.getSelectedItem().equals("")))
					{
						if(JcmpTimQuan.getSelectedItem().toString().trim()!="" && JcmpTimPhuong.getSelectedItem().toString().trim()!="" && JcmpTimSoNha.getSelectedItem().toString().trim()!="")
						{
							NhaTro_Dao dao = new NhaTro_Dao();
							String Phuong = JcmpTimPhuong.getSelectedItem().toString().trim();
							String Quan = JcmpTimQuan.getSelectedItem().toString().trim();
							String sn = JcmpTimSoNha.getSelectedItem().toString().trim();
							if(dao.layNhaTroTheoPhuongQuanSoNha(Phuong, Quan, sn)!=null)
							{
								tableModel.setRowCount(0);
								dao.layNhaTroTheoPhuongQuanSoNha(Phuong, Quan, sn).forEach(v -> {
									String maTro = v.getMaTro();
									String tenChuNha = v.getTenChutro();
									String diaChi = v.getDiaChiTro().getSoNha() +" - "+ v.getDiaChiTro().getTenDuong() +" - "+  v.getDiaChiTro().getTenPhuong() +" - " + v.getDiaChiTro().getTenQuan();
									String SDT = v.getSDT();
									String[] row = {maTro,tenChuNha, diaChi, SDT};
									tableModel.addRow(row);
								});
								
							}
							else if(dao.layNhaTroTheoPhuongQuanSoNha(Phuong, Quan, sn)==null){
								JOptionPane.showMessageDialog(this, "Tìm thất bại!");
							}
						}
					}
					
					//Tim theo phuong, quan, ten duong
					if(!(JcmpTimDuong.getSelectedItem().equals("")) && JcmpTimSoNha.getSelectedItem().equals("") && !(JcmpTimPhuong.getSelectedItem().equals("")) && !(JcmpTimQuan.getSelectedItem().equals("")))
					{
						if(JcmpTimQuan.getSelectedItem().toString().trim()!="" && JcmpTimPhuong.getSelectedItem().toString().trim()!="" && JcmpTimDuong.getSelectedItem().toString().trim()!="")
						{
							NhaTro_Dao dao = new NhaTro_Dao();
							String Phuong = JcmpTimPhuong.getSelectedItem().toString().trim();
							String Quan = JcmpTimQuan.getSelectedItem().toString().trim();
							String td = JcmpTimDuong.getSelectedItem().toString().trim();
							if(dao.layNhaTroTheoQuanPhuongTenDuong(Quan, Phuong, td)!=null)
							{
								tableModel.setRowCount(0);
								dao.layNhaTroTheoQuanPhuongTenDuong(Quan, Phuong, td).forEach(v -> {
									String maTro = v.getMaTro();
									String tenChuNha = v.getTenChutro();
									String diaChi = v.getDiaChiTro().getSoNha() +" - "+ v.getDiaChiTro().getTenDuong() +" - "+  v.getDiaChiTro().getTenPhuong() +" - " + v.getDiaChiTro().getTenQuan();
									String SDT = v.getSDT();
									String[] row = {maTro,tenChuNha, diaChi, SDT};
									tableModel.addRow(row);
								});
								
							}
							else if(dao.layNhaTroTheoQuanPhuongTenDuong(Quan, Phuong, td)==null){
								JOptionPane.showMessageDialog(this, "Tìm thất bại!");
							}
						}
					}
					
					//Tim theo quan, ten duong, so nha
					if(!(JcmpTimDuong.getSelectedItem().equals("")) && !(JcmpTimSoNha.getSelectedItem().equals("")) && JcmpTimPhuong.getSelectedItem().equals("") && !(JcmpTimQuan.getSelectedItem().equals("")))
					{
						if(JcmpTimQuan.getSelectedItem().toString().trim()!="" && JcmpTimSoNha.getSelectedItem().toString().trim()!="" && JcmpTimDuong.getSelectedItem().toString().trim()!="")
						{
							NhaTro_Dao dao = new NhaTro_Dao();
							String Phuong = JcmpTimPhuong.getSelectedItem().toString().trim();
							String Quan = JcmpTimQuan.getSelectedItem().toString().trim();
							String td = JcmpTimDuong.getSelectedItem().toString().trim();
							if(dao.layNhaTroTheoQuanPhuongTenDuong(Quan, Phuong, td)!=null)
							{
								tableModel.setRowCount(0);
								dao.layNhaTroTheoQuanPhuongTenDuong(Quan, Phuong, td).forEach(v -> {
									String maTro = v.getMaTro();
									String tenChuNha = v.getTenChutro();
									String diaChi = v.getDiaChiTro().getSoNha() +" - "+ v.getDiaChiTro().getTenDuong() +" - "+  v.getDiaChiTro().getTenPhuong() +" - " + v.getDiaChiTro().getTenQuan();
									String SDT = v.getSDT();
									String[] row = {maTro,tenChuNha, diaChi, SDT};
									tableModel.addRow(row);
								});
								
							}
							else if(dao.layNhaTroTheoQuanPhuongTenDuong(Quan, Phuong, td)==null){
								JOptionPane.showMessageDialog(this, "Tìm thất bại!");
							}
						}
					}
					
					//Tim theo Phuong, ten duong, so nha
					if(!(JcmpTimDuong.getSelectedItem().equals("")) && !(JcmpTimSoNha.getSelectedItem().equals("")) && !(JcmpTimPhuong.getSelectedItem().equals("")) && JcmpTimQuan.getSelectedItem().equals(""))
					{
						if(JcmpTimPhuong.getSelectedItem().toString().trim()!="" && JcmpTimSoNha.getSelectedItem().toString().trim()!="" && JcmpTimDuong.getSelectedItem().toString().trim()!="")
						{
							NhaTro_Dao dao = new NhaTro_Dao();
							String Phuong = JcmpTimPhuong.getSelectedItem().toString().trim();
							String sn = JcmpTimSoNha.getSelectedItem().toString().trim();
							String td = JcmpTimDuong.getSelectedItem().toString().trim();
							if(dao.layNhaTroTheoPhuongTenDuongSoNha(Phuong, td, sn)!=null)
							{
								tableModel.setRowCount(0);
								dao.layNhaTroTheoPhuongTenDuongSoNha(Phuong, td, sn).forEach(v -> {
									String maTro = v.getMaTro();
									String tenChuNha = v.getTenChutro();
									String diaChi = v.getDiaChiTro().getSoNha() +" - "+ v.getDiaChiTro().getTenDuong() +" - "+  v.getDiaChiTro().getTenPhuong() +" - " + v.getDiaChiTro().getTenQuan();
									String SDT = v.getSDT();
									String[] row = {maTro,tenChuNha, diaChi, SDT};
									tableModel.addRow(row);
								});
								
							}
							else if(dao.layNhaTroTheoPhuongTenDuongSoNha(Phuong, td, sn)==null){
								JOptionPane.showMessageDialog(this, "Tìm thất bại!");
							}
						}
					}
					//Tim theo quan, phuong, ten duong, so nha
					if(!(JcmpTimDuong.getSelectedItem().equals("")) && !(JcmpTimSoNha.getSelectedItem().equals("")) && !(JcmpTimPhuong.getSelectedItem().equals("")) && !(JcmpTimQuan.getSelectedItem().equals("")))
					{
						if(JcmpTimQuan.getSelectedItem().toString().trim()!="" && JcmpTimSoNha.getSelectedItem().toString().trim()!="" && JcmpTimDuong.getSelectedItem().toString().trim()!="" && JcmpTimPhuong.getSelectedItem().toString().trim()!="")
						{
							NhaTro_Dao dao = new NhaTro_Dao();
							String Phuong = JcmpTimPhuong.getSelectedItem().toString().trim();
							String Quan = JcmpTimQuan.getSelectedItem().toString().trim();
							String td = JcmpTimDuong.getSelectedItem().toString().trim();
							String sn = JcmpTimSoNha.getSelectedItem().toString().trim();
							if(dao.layNhaTroTheoQuanPhuongTenDuongSoNha(Quan, Phuong, td, sn)!=null)
							{
								tableModel.setRowCount(0);
								dao.layNhaTroTheoQuanPhuongTenDuongSoNha(Quan, Phuong, td, sn).forEach(v -> {
									String maTro = v.getMaTro();
									String tenChuNha = v.getTenChutro();
									String diaChi = v.getDiaChiTro().getSoNha() +" - "+ v.getDiaChiTro().getTenDuong() +" - "+  v.getDiaChiTro().getTenPhuong() +" - " + v.getDiaChiTro().getTenQuan();
									String SDT = v.getSDT();
									String[] row = {maTro,tenChuNha, diaChi, SDT};
									tableModel.addRow(row);
								});
								
							}
							else if(dao.layNhaTroTheoQuanPhuongTenDuongSoNha(Quan, Phuong, td, sn)==null){
								JOptionPane.showMessageDialog(this, "Tìm thất bại!");
							}
						}
					}
			} catch (Exception e2) {
				// TODO: handle exception
			}	
		}
		}
	
	
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		txtMaNhatro.setText(table.getValueAt(row,0).toString());
		txtChuNha.setText(table.getValueAt(row, 1).toString());
		String[] s = table.getValueAt(row, 2).toString().split(" - ");
		
		
		txtSoNha.setText(s[0]);
		txtDuong.setText(s[1]);
		
		JcmpQuan.setSelectedItem(s[3]);
		JcmpPhuong.setSelectedItem(s[2]);
		txtSDT.setText(table.getValueAt(row, 3).toString());
		txtMaNhatro.setEditable(false);
		
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
	private boolean validData() {
		String maNT = txtMaNhatro.getText().trim();
		String tenChuNha = txtChuNha.getText().trim();
		String dienThoai = txtSDT.getText().trim();
	
		if(JcmpQuan.getSelectedItem().toString().equals(" "))
		{
			JOptionPane.showMessageDialog(this, "Bạn chưa nhập địa chỉ!!!");
			return false;
		}
		if(maNT.length()==0) {
			JOptionPane.showMessageDialog(this, "Mã nhà trọ không được bỏ trống");
			txtMaNhatro.requestFocus();
			return false;
		}
		
		if(!(maNT.matches("NT_[0-9]{5}"))) {
			JOptionPane.showMessageDialog(this, "Mã nhà nhập sai cấu trúc NT_00000");
			txtMaNhatro.requestFocus();
			return false;
		}
		if(!(tenChuNha.length()>0)) {
			JOptionPane.showMessageDialog(this, "Tên chủ nhà không được bỏ trống");
			txtChuNha.requestFocus();
			return false;
		}
		if(!(tenChuNha.matches("[\\p{Lu}[A-Z]][\\p{L}[a-z]]*( [\\p{Lu}[A-Z]][\\p{L}[a-z]]*)*"))) {
			JOptionPane.showMessageDialog(this, "Họ tên nhâp sai");
			txtChuNha.requestFocus();
			return false;
		}
		
		if(!(dienThoai.length()>0)) {
			JOptionPane.showMessageDialog(this, "Điện thoại nhân viên không được bỏ trống");
			txtSDT.requestFocus();
			return false;
		}
		if(!(dienThoai.matches("(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})"))) {
			JOptionPane.showMessageDialog(this, "Nhập số điện thoại sai");
			txtSDT.requestFocus();
			return false;
		}
		
		return true;
	}

	
}

