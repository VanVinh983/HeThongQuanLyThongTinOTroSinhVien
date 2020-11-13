package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

public class GD_QuanLySinhVien extends JPanel implements ActionListener{
	//attribute Form thông tin phần txt
	private JTextField txtMaSV;
	private JTextField txtTenSV;
	private JTextField txtNgaySinh;
	private JTextField txtQueQuan;
	private JTextField txtMaLop;
	private JTextField txtMaNV;
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
	private JTextField txtUser;
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
	public GD_QuanLySinhVien() {
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
						bUser.add(lblUser = new JLabel("User: "));
						lblUser.setFont(new Font("Arial", Font.BOLD, 35));
						bUser.add(txtUser = new JTextField(20));
						bUser.add(Box.createVerticalStrut(20));
						txtUser.setEnabled(false);
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
				pnlMenubtn.add(Box.createVerticalStrut(10));
				pnlMenubtn.add(btnNhanVien = new JButton("Quản lý nhân viên"));
				
				btnNhanVien.add(Box.createHorizontalStrut(30));
				btnNhanVien.add(Box.createVerticalStrut(20));
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
			tableModel=new DefaultTableModel(header,20);
			pnlTable.add(scroll=new JScrollPane(table=new JTable(tableModel),JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),BorderLayout.SOUTH);
			scroll.setBorder(BorderFactory.createTitledBorder("Danh Sách"));
			table.setRowHeight(30);
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
		txtMaSV.setEditable(false);
		boxMaSV.add(lblMaLop=new JLabel("Mã Lớp: "));
		boxMaSV.add(txtMaLop=new JTextField());
		txtMaLop.setEditable(false);
		pnlFormtxtSV.add(boxMaSV);
		
		pnlFormtxtSV.add(Box.createVerticalStrut(10));
		Box boxTenSV = Box.createHorizontalBox();
		boxTenSV.add(lblTenSV=new JLabel("Tên sinh viên: "));
		boxTenSV.add(txtTenSV=new JTextField());
		boxTenSV.add(lblMaNV=new JLabel("Mã nhân viên: "));
		boxTenSV.add(txtMaNV=new JTextField());
		txtMaNV.setEditable(false);
		pnlFormtxtSV.add(boxTenSV);
		
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
		boxQueQuan.add(lblChuyenNghanh=new JLabel("Số điện thoại"));
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
		lblMaSV.setPreferredSize(lblTenSV.getPreferredSize());
		lblGioiTinh.setPreferredSize(lblTenSV.getPreferredSize());
		lblNgaySinh.setPreferredSize(lblTenSV.getPreferredSize());
		lblMaLop.setPreferredSize(lblTenSV.getPreferredSize());
		lblMaNV.setPreferredSize(lblTenSV.getPreferredSize());
		lblChuyenNghanh.setPreferredSize(lblTenSV.getPreferredSize());
		lblQueQuan.setPreferredSize(lblTenSV.getPreferredSize());
		//lblTim.setPreferredSize(lblTenSV.getPreferredSize());
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// phần add với action 
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
		
	////////////////////////////////////////////////////////////////////////////////////////////
		
		//pnlcen3.add(Box.createHorizontalStrut(400), BorderLayout.EAST);

		setVisible(true);
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
			
		}
		else if(ob.equals(btnNhanVien))
		{
			
		}
		else if(ob.equals(btnSinhVien))
		{
			
		}
		else if(ob.equals(btnSua))
		{
			
		}
		else if(ob.equals(btnThem))
		{
			
		}
		else if(ob.equals(btnThoat))
		{
			
		}
		else if(ob.equals(btnThongKe))
		{
			
		}
		else if(ob.equals(btnThueTro))
		{
			
		}
		else if(ob.equals(btnTim))
		{
			
		}
		else if(ob.equals(btnTro))
		{
			
		}
		else if(ob.equals(btnXoa))
		{
			
		}
		else if(ob.equals(btnXoaTrang))
		{
			txtMaSV.setText("");
			txtTenSV.setText("");
			txtNgaySinh.setText("");
			txtQueQuan.setText("");
			txtMaLop.setText("");
			txtUser.setText("");
			txtTim.setText("");
			txtMaNV.setText("");
			txtGioiTinh.setText("");
			txtChuyenNghanh.setText("");
		}
		
	}
}

