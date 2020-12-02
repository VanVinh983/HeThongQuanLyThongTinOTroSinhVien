package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

import connectDB.ConnectDB;
import dao.NhaTro_Dao;
import dao.NhanVien_Dao;
import dao.SinhVien_Dao;
import dao.TamLuuMaNhanVien_Dao;
import dao.ThongTinThueTro_Dao;
import entity.NhaTro;
import entity.NhanVien;
import entity.SinhVien;

public class GD_QuanLySinhVien extends JPanel implements ActionListener, MouseListener{
	private NhanVien_Dao nv_Dao;
	private TamLuuMaNhanVien_Dao tamluu_dao;
	
	//attribute Form thông tin phần txt
	private JTextField txtMaSV;
	private JTextField txtTenSV;
	private JTextField txtNgaySinh;
	private JTextField txtQueQuan;
	private JTextField txtMaLop;
	private JTextField txtMaNV;
	private JComboBox<String> cmpMaLop;
	private JComboBox<String> cmpMaNV;
	private JTextField txtGioiTinh;
	private JTextField txtChuyenNghanh;
	
	//attribute Form thông tin phần label
	
	private JLabel lblMaSV;
	private JLabel lblTenSV;
	private JLabel lblNgaySinh;
	private JLabel lblQueQuan;
	private JLabel lblMaLop;
	private JLabel lblMaNV;
	private JLabel lblGioiTinh;
	private JLabel lblChuyenNghanh;
	
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
	//Compobox Lua cong tim kiếm
	private JTextField txtTim;
	private JLabel lblTim;
	private JComboBox<String> cmp;
	private NhanVien nhanVienTamLuu;
	public GD_QuanLySinhVien() {
		nv_Dao = new NhanVien_Dao(); 
		tamluu_dao = new TamLuuMaNhanVien_Dao();
		
		nhanVienTamLuu = tamluu_dao.layNhanVienTrongBangTamLuu();
		
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(1200, 600));
		
		JPanel pnl = new JPanel();
		
		pnl.setLayout(new BorderLayout());
		Box box = Box.createVerticalBox();
		
		//pnluser1 Tạo ảnh và txt hiển thị user
			JPanel pnlUser = new JPanel();
				pnlUser.setBackground(Color.GREEN);
				Box buser = Box.createVerticalBox();
				
					//tạo 3 box user định dạng theo  chiều dọc
					Box bUser, buserImg;
					
					//box user1 
					buser.add(bUser = Box.createHorizontalBox());
					
					//NhaTro_Dao dao = new NhaTro_Dao();
					SinhVien_Dao daoSV = new SinhVien_Dao();
					
					String tenKhoaNV = daoSV.layTenKhoaNhanVien().trim().toString();
					if(daoSV.layLoaiNV().trim().toString().equals("NV"))
					{
						bUser.add(lblNameUser = new JLabel("Giáo vụ khoa: " + tenKhoaNV));
					}
					else if(daoSV.layLoaiNV().trim().toString().equals("QL")){
						bUser.add(lblNameUser = new JLabel("Người quản lý: " + tenKhoaNV));
					}
					
					
					buser.add(buserImg = Box.createVerticalBox());
					//box user2 Hinh Anh user
					JPanel pnlcontent=new JPanel();
		     		JLabel lblBanner = new JLabel();
					pnlcontent.add(lblBanner);
					lblBanner.setSize(250,250);
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
				pnlMenubtn.add(Box.createVerticalStrut(20));
				pnlMenubtn.add(btnTro = new JButton("Quản lý trọ"));
				btnTro.add(Box.createHorizontalStrut(30));
				btnTro.add(Box.createVerticalStrut(20));
				
				pnlMenubtn.add(Box.createVerticalStrut(10));
				pnlMenubtn.add(btnSinhVien = new JButton("Quản lý sinh viên"));
				btnSinhVien.add(Box.createHorizontalStrut(30));
				btnSinhVien.add(Box.createVerticalStrut(20));
				
				if(daoSV.layLoaiNV().equals("QL"))
				{
					pnlMenubtn.add(Box.createVerticalStrut(10));
					pnlMenubtn.add(btnNhanVien = new JButton("Quản lý nhân viên"));
					btnNhanVien.add(Box.createHorizontalStrut(30));
					btnNhanVien.add(Box.createVerticalStrut(20));
					btnNhanVien.addActionListener(this);
				}
				
				
				
				pnlMenubtn.add(Box.createVerticalStrut(10));
				pnlMenubtn.add(btnThueTro = new JButton("Quản lý thuê trọ"));
				btnThueTro.add(Box.createHorizontalStrut(30));
				btnThueTro.add(Box.createVerticalStrut(20));
				pnlMenubtn.add(Box.createVerticalStrut(10));
				
				pnlMenubtn.add(btnThongKe = new JButton("Thống kê"));
				btnThongKe.add(Box.createHorizontalStrut(30));
				btnThongKe.add(Box.createVerticalStrut(20));
				pnlMenubtn.add(Box.createVerticalStrut(10));
				
				pnlMenubtn.add(btnHuongDanSD = new JButton("Hương dẫn sử dụng"));
				btnHuongDanSD.add(Box.createHorizontalStrut(30));
				btnHuongDanSD.add(Box.createVerticalStrut(20));
				pnlMenubtn.add(Box.createVerticalStrut(10));

				pnlMenubtn.add(btnThoat = new JButton("Thoát"));
				btnThoat.add(Box.createHorizontalStrut(30));
				btnThoat.add(Box.createVerticalStrut(20));
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
		JPanel pnlQuanLyTro = new JPanel();
		pnlQuanLyTro.add(lblcenTitle = new JLabel("Quản lý thông tin sinh viên"));
		bcenTitle.add(pnlQuanLyTro);
		//chỉnh font chữ
		pnlQuanLyTro.setBackground(Color.blue);
		lblcenTitle.setFont(new Font("Arial", Font.BOLD, 40));
		lblcenTitle.setForeground(Color.WHITE);
		
	//bcenTable tạo bảng thông tin
		boxCen.add(bcenTable = Box.createHorizontalBox());
		JPanel pnlTable = new JPanel();
			JScrollPane scroll;
			
			
			String[] header="Mã sinh viên;Tên sinh viên;Ngày sinh;Quê quán;Mã lớp;Mã nhân viên;Giới tính;Chuyên nghành".split(";");
			tableModel=new DefaultTableModel(header,0);
			pnlTable.add(scroll=new JScrollPane(table=new JTable(tableModel),JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),BorderLayout.SOUTH);
			scroll.setBorder(BorderFactory.createTitledBorder("Danh Sách"));
			table.setRowHeight(20);
			scroll.setPreferredSize(new Dimension(0,500));
			bcenTable.add(scroll);
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		//bcenForm tạo form thông tin và form tìm
		//Form thông tin
		boxCen.add(bcenForm = Box.createVerticalBox());
		
		JPanel pnlForm = new JPanel();
		pnlForm.setSize(100, 20);
		pnlForm.setLayout(new BorderLayout());
		
		Box boxcenFormTitle, boxcenFormSV;
		//pnlForm.add(boxcenFormTitle = box.createVerticalBox(), BorderLayout.NORTH);

		JLabel lblThongTin = new JLabel("Thông tin");
		
		lblThongTin.setFont(new Font("Arial", Font.BOLD, 30));
		
		bcenForm.add(lblThongTin);
		bcenForm.setBackground(Color.LIGHT_GRAY);
		

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		bcenForm.add(boxcenFormSV = Box.createVerticalBox());
		JPanel pnlSouth = new JPanel();
		pnlSouth.setLayout(new BorderLayout());
	//	pnlSouth.setBackground(Color.LIGHT_GRAY);
		
		pnlSouth.add(Box.createHorizontalStrut(100), BorderLayout.WEST);
		JPanel pnlFormSV=new JPanel();
		pnlSouth.add(pnlFormSV);
		boxcenFormSV.add( pnlSouth);
		pnlFormSV.setBounds(0,80,1055,450);
		pnlFormSV.setLayout(new BoxLayout(pnlFormSV, BoxLayout.Y_AXIS));
		
		JPanel pnlFormtxt = new JPanel();
		pnlFormtxt.setLayout(new GridLayout(1, 2));
		pnlFormSV.add(pnlFormtxt);
		
		//Form điền thông tin
		JPanel pnlFormtxtSV = new JPanel();
		pnlFormtxtSV.setLayout(new BoxLayout(pnlFormtxtSV, BoxLayout.Y_AXIS));
		
		pnlFormtxt.add(pnlFormtxtSV);
		
		pnlFormtxtSV.add(Box.createVerticalStrut(10));
		Box boxMaSV = Box.createHorizontalBox();
		boxMaSV.add(lblMaSV=new JLabel("Mã sinh viên:"));
		boxMaSV.add(txtMaSV=new JTextField());
	//	txtMaSV.setEditable(false);
		boxMaSV.add(lblMaLop=new JLabel("Mã Lớp: "));
		boxMaSV.add(txtMaLop=new JTextField());
		//txtMaLop.setEditable(false);
		pnlFormtxtSV.add(boxMaSV);
		
		pnlFormtxtSV.add(Box.createVerticalStrut(10));
		Box boxTenSV = Box.createHorizontalBox();
		boxTenSV.add(lblTenSV=new JLabel("Tên sinh viên: "));
		boxTenSV.add(txtTenSV=new JTextField(5));
		boxTenSV.add(lblMaNV=new JLabel("Mã nhân viên: "));
	    boxTenSV.add(txtMaNV=new JTextField());
		pnlFormtxtSV.add(boxTenSV);
		
		txtMaNV.setText(daoSV.layMaNVTamLuu().trim().toString());
		txtMaNV.setEditable(false);
		
		pnlFormtxtSV.add(Box.createVerticalStrut(10));
		Box boxNgaySinh = Box.createHorizontalBox();
		boxNgaySinh.add(lblNgaySinh=new JLabel("Ngày sinh: "));
		boxNgaySinh.add(txtNgaySinh=new JTextField());
		boxNgaySinh.add(lblGioiTinh=new JLabel("Giới tính: "));
		boxNgaySinh.add(txtGioiTinh=new JTextField());
		pnlFormtxtSV.add(boxNgaySinh);
		
		pnlFormtxtSV.add(Box.createVerticalStrut(10));
		Box boxQueQuan = Box.createHorizontalBox();
		boxQueQuan.add(lblQueQuan=new JLabel("Quê quán: "));
		boxQueQuan.add(txtQueQuan=new JTextField());
		boxQueQuan.add(lblChuyenNghanh=new JLabel("Nghành: "));
		boxQueQuan.add(txtChuyenNghanh=new JTextField());
		
		pnlFormtxtSV.add(boxQueQuan);
		
		// các nút chức năng thêm xóa sửa xóa trắng
		pnlFormtxtSV.add(Box.createVerticalStrut(20));
		Box boxButton = Box.createHorizontalBox();
		boxButton.add(btnThem =new JButton("Thêm"));
		boxButton.add(Box.createHorizontalStrut(20));
		boxButton.add(btnXoa =new JButton("Xóa"));
		boxButton.add(Box.createHorizontalStrut(20));
		boxButton.add(btnSua =new JButton("Sửa"));
		boxButton.add(Box.createHorizontalStrut(20));
		boxButton.add(btnXoaTrang =new JButton("Xóa trắng"));
		pnlFormtxtSV.add(Box.createVerticalStrut(5));
		pnlFormtxtSV.add(boxButton);
		pnlFormtxtSV.add(Box.createVerticalStrut(20));
		pnlFormtxtSV.setBorder(BorderFactory.createRaisedBevelBorder());
		

		
		 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 //Form tìm
		 JPanel pnlTim = new JPanel();
		 pnlTim.setLayout(new BoxLayout(pnlTim, BoxLayout.Y_AXIS));
		// pnlSouth.add(pnlTim, BorderLayout.EAST);
		 pnlFormtxtSV.add(pnlTim);
		 pnlTim.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		 
		 JPanel pnlFormTim = new JPanel();
		 pnlTim.add(pnlFormTim);
		 
		 Box boxtxtTim = Box.createHorizontalBox();
		 boxtxtTim.add(lblTim=new JLabel("Tìm Thông tin sinh viên: "));
		 boxtxtTim.add(txtTim=new JTextField(10));
		 pnlFormTim.add(boxtxtTim);
		 
		 //compobox chọn phương thức tìm
		 pnlFormTim.add(Box.createVerticalStrut(30));
		 Box boxcmpTim = Box.createHorizontalBox();
		 String[] s = "Mã;Tên".split(";");
		 boxcmpTim.add(cmp = new JComboBox<String>(s));
		 pnlFormTim.add(boxcmpTim);
		 
		 // Button tìm
		 pnlTim.add(Box.createVerticalStrut(30));
		 Box boxbtnTim = Box.createHorizontalBox();
		 
		 boxbtnTim.add(btnTim = new JButton("Tìm"));

		 btnTim.setBackground(Color.green);
		 pnlTim.setBackground(Color.CYAN);
		 pnlTim.add(boxbtnTim);
		 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 //điêu chỉnh kích thướt theo lblSDT
		lblMaSV.setPreferredSize(lblMaNV.getPreferredSize());
		lblGioiTinh.setPreferredSize(lblMaNV.getPreferredSize());
		lblNgaySinh.setPreferredSize(lblMaNV.getPreferredSize());
		lblMaLop.setPreferredSize(lblMaNV.getPreferredSize());
		lblTenSV.setPreferredSize(lblMaNV.getPreferredSize());
		lblChuyenNghanh.setPreferredSize(lblMaNV.getPreferredSize());
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
		table.addMouseListener(this);
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
		List<SinhVien> listSV = dao.layTatCaBang();
		//Đưa thông tin vào bảng
		listSV.forEach(v -> {
			DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/mm/yyyy");
			String ngaySinh = v.getNgaySinh().getDayOfMonth() + "/" + v.getNgaySinh().getMonthValue() + "/" +v.getNgaySinh().getYear();
			String[] row = {v.getMaSV(), v.getTenSV(),ngaySinh, v.getQueQuanSV(), v.getMaLop(), v.getMaNV().getMaNV(), v.getGioiTinh(), v.getChuyenNghanh()};
			tableModel.addRow(row);
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
    	  JOptionPane.showMessageDialog(null, "Lỗi hình ảnh");
      }
  }
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		
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
		else if(ob.equals(btnSinhVien))
		{
			
		}
		else if(ob.equals(btnSua))
		{
			if(validData()==true)
			{
				SinhVien_Dao daosv = new SinhVien_Dao();
				String maSV = txtMaSV.getText();
				String tenSV = txtTenSV.getText();
				String[] ns = txtNgaySinh.getText().split("/");
				LocalDate ngaySinh = LocalDate.of(Integer.parseInt(ns[2]), Integer.parseInt(ns[1]), Integer.parseInt(ns[0]));
				String queQuanSV = txtQueQuan.getText();
				String maLop = txtMaLop.getText();
				String maNV = txtMaNV.getText();
				String gioiTinh = txtGioiTinh.getText();
				String chuyenNghanh = txtChuyenNghanh.getText();
				
				SinhVien sv = new SinhVien(maSV, tenSV, ngaySinh, queQuanSV, maLop, new NhanVien(maNV), gioiTinh, chuyenNghanh);
				if(daosv.UpdateSinhVien(sv)==true)
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
				SinhVien_Dao daosv = new SinhVien_Dao();
				String maSV = txtMaSV.getText();
				String tenSV = txtTenSV.getText();
				String[] ns = txtNgaySinh.getText().split("/");
				LocalDate ngaySinh = LocalDate.of(Integer.parseInt(ns[2]), Integer.parseInt(ns[1]), Integer.parseInt(ns[0]));
				String queQuanSV = txtQueQuan.getText();
				String maLop = txtMaLop.getText();
				String maNV = txtMaNV.getText();
				String gioiTinh = txtGioiTinh.getText();
				String chuyenNghanh = txtChuyenNghanh.getText();
				
				SinhVien sv = new SinhVien(maSV, tenSV, ngaySinh, queQuanSV, maLop, new NhanVien(maNV), gioiTinh, chuyenNghanh);
				if(!(daosv.layTatCaBang().contains(sv)))
				{
					if(daosv.themSV(sv)==true)
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
		
		
		else if(ob.equals(btnThongKe))
		{
		
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
			removeAll();
			add(new GD_QuanLyTro());
			repaint();
			revalidate();
		}
		else if(ob.equals(btnXoa))
		{
			
			SinhVien_Dao dao = new SinhVien_Dao();
			String id = txtMaSV.getText();
			if(dao.DeleteSinhVien(id)==true)
			{
				JOptionPane.showMessageDialog(this, "Xóa thành công");
				tableModel.setRowCount(0);
				addDatabase();
			}
			else {
				JOptionPane.showMessageDialog(this, "Xóa thất bại");
			}
		}
		else if(ob.equals(btnXoaTrang))
		{
			txtMaSV.setText("");
			txtTenSV.setText("");
			txtNgaySinh.setText("");
			txtQueQuan.setText("");
			txtMaLop.setText("");
			txtTim.setText("");
			
			txtGioiTinh.setText("");
			SinhVien_Dao daoSV = new SinhVien_Dao();
			txtChuyenNghanh.setText(daoSV.layTenKhoaNhanVien());
			txtTim.setText("");
			tableModel.setRowCount(0);
			addDatabase();
			txtMaNV.setEditable(false);
			txtMaSV.setEditable(true);
		}
		else if(cmp.getSelectedItem().equals("Mã"))
		{
			if(ob.equals(btnTim))
			{
				SinhVien_Dao dao = new SinhVien_Dao();
				
				String ma = txtTim.getText();
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
					txtTim.setText("");
					JOptionPane.showMessageDialog(this, "Tìm thất bại!");
				}
			}	
			
		}
		else if(cmp.getSelectedItem().equals("Tên"))
		{
			
			if(ob.equals(btnTim))
			{
				SinhVien_Dao dao = new SinhVien_Dao();
				String ten = txtTim.getText();
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
					txtTim.setText("");
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
		txtNgaySinh.setText(table.getValueAt(row, 2).toString());
		txtQueQuan.setText(table.getValueAt(row, 3).toString());
		txtMaLop.setText(table.getValueAt(row, 4).toString());
		txtGioiTinh.setText(table.getValueAt(row, 6).toString());
		txtMaNV.setText(table.getValueAt(row, 5).toString());
		txtChuyenNghanh.setText(table.getValueAt(row, 7).toString());
		txtMaNV.setEditable(false);
		txtMaSV.setEditable(false);
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
		String ngaySinh= txtNgaySinh.getText().trim();
		String queQuan = txtQueQuan.getText().trim();
		String gioiTinh = txtGioiTinh.getText().trim();
		String nghanh = txtChuyenNghanh.getText().trim();
		
		

		if(maSV.length()==0) {
			JOptionPane.showMessageDialog(this, "Mã sinh viên không được bỏ trống");
			txtMaSV.requestFocus();
			return false;
		}
	
		if(!(maSV.matches("SV_[0-9]{5}"))) {
			JOptionPane.showMessageDialog(this, "Mã nhà nhập sai cấu trúc SV_00000");
			txtMaSV.requestFocus();
			return false;
		}
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
		if(!(ngaySinh.length()>0)) {
			JOptionPane.showMessageDialog(this, "Ngày sinh không được bỏ trống");
			txtNgaySinh.requestFocus();
			return false;
		}
		if(!(ngaySinh.matches("\\d{1,2}[-|/]\\d{1,2}[-|/]\\d{4}"))) {
			JOptionPane.showMessageDialog(this, "Ngày sinh nhâp sai");
			txtNgaySinh.requestFocus();
			return false;
		}
		
//		if(!(dienThoai.length()>0)) {
//			JOptionPane.showMessageDialog(this, "Điện thoại nhân viên không được bỏ trống");
//			txtSDT.requestFocus();
//			return false;
//		}
//		if(!(dienThoai.matches("(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})"))) {
//			JOptionPane.showMessageDialog(this, "Nhập số điện thoại sai");
//			txtSDT.requestFocus();
//			return false;
//		}
//		
		return true;
	}
}

