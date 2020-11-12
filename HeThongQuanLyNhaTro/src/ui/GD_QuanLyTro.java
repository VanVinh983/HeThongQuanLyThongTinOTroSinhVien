package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

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

import connectDB.ConnectDB;

public class GD_QuanLyTro extends JPanel implements ActionListener{

	//attribute Form thông tin phần txt
	private JTextField txtMaNhatro;
	private JTextField txtChuNha;
	private JComboBox<String> JcmpQuan;
	private JComboBox<String> JcmpPhuong;
	private JComboBox<String> JcmpSoNha;
	private JComboBox<String> JcmpDuong;
	private JTextField txtSDT;
	private JTextField txtTim;
	
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
	private JComboBox<String> cmp;
	public GD_QuanLyTro() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(1200, 600));
		
		JPanel pnl = new JPanel();
		
		pnl.setLayout(new BorderLayout());
		Box box = Box.createVerticalBox();
	
		//Tạo ảnh và txt hiển thị user
			JPanel pnlUser = new JPanel();
				pnlUser.setBackground(Color.GREEN);
				Box buser = Box.createVerticalBox();
					Box bUser, buserImg;
					buser.add(bUser = Box.createHorizontalBox());
						bUser.add(lblUser = new JLabel("User: "));
						lblUser.setFont(new Font("Arial", Font.BOLD, 35));
						bUser.add(txtUser = new JTextField(20));
						bUser.add(Box.createVerticalStrut(20));
						txtUser.setEnabled(false);
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
		JPanel pnlCen = new JPanel();
		Box boxCen = Box.createVerticalBox();
		
		Box bcenTitle, bcenTable, bcenForm;
		boxCen.add(bcenTitle = Box.createHorizontalBox());
		//Label Title Quản lý trọ
		JPanel pnlQuanLyTro = new JPanel();
		pnlQuanLyTro.add(lblcenTitle = new JLabel("Quản lý nhà trọ"));
		bcenTitle.add(pnlQuanLyTro);
		pnlQuanLyTro.setBackground(Color.blue);
		lblcenTitle.setFont(new Font("Arial", Font.BOLD, 40));
		lblcenTitle.setForeground(Color.WHITE);
	//Table
		boxCen.add(bcenTable = Box.createHorizontalBox());

		JPanel pnlTable = new JPanel();
			JScrollPane scroll;
			String[] header="Mã nhà trọ;Tên chủ nhà;Địa chỉ;Số điện thoại".split(";");
			tableModel=new DefaultTableModel(header,20);
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
		
		pnlThongTin.setBackground(Color.LIGHT_GRAY);
		pnlForm.setBackground(Color.LIGHT_GRAY);
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		bcenForm.add(boxcenFormSV = Box.createVerticalBox());
		JPanel pnlSouth = new JPanel();
		pnlSouth.setLayout(new BorderLayout());
		pnlSouth.setBackground(Color.LIGHT_GRAY);
		
		pnlSouth.add(Box.createHorizontalStrut(100), BorderLayout.WEST);
		JPanel pnlFormTro=new JPanel();
		pnlSouth.add(pnlFormTro);
		boxcenFormSV.add( pnlSouth);
		pnlFormTro.setBounds(0,80,1055,450);
		pnlFormTro.setLayout(new BoxLayout(pnlFormTro, BoxLayout.Y_AXIS));
		
		//Form điền thông tin
		pnlFormTro.add(Box.createVerticalStrut(10));
		Box boxMaTro = Box.createHorizontalBox();
		boxMaTro.add(lblMaNhaTro=new JLabel("Mã Trọ:"));
		boxMaTro.add(txtMaNhatro=new JTextField());
		pnlFormTro.add(boxMaTro);
		
		pnlFormTro.add(Box.createVerticalStrut(10));
		Box boxDiaChi = Box.createHorizontalBox();
		boxDiaChi.add(lblDiaChi=new JLabel("Địa chỉ: "));
		boxDiaChi.add(lblQuan = new JLabel("Quận: "));
		boxDiaChi.add(JcmpQuan = new JComboBox<String>());
		
		boxDiaChi.add(lblPhuong = new JLabel("Phường: "));
		boxDiaChi.add(JcmpPhuong = new JComboBox<String>());
		
		boxDiaChi.add(lblSoNha = new JLabel("Số nhà: "));
		boxDiaChi.add(JcmpSoNha = new JComboBox<>());
		
		boxDiaChi.add(lblDuong = new JLabel("Đường: "));
		boxDiaChi.add(JcmpDuong = new JComboBox<String>());
		pnlFormTro.add(boxDiaChi);
		
		pnlFormTro.add(Box.createVerticalStrut(10));
		Box boxChuNha = Box.createHorizontalBox();
		boxChuNha.add(lblChuNha=new JLabel("Tên chủ trọ"));
		boxChuNha.add(txtChuNha=new JTextField());
		pnlFormTro.add(boxChuNha);
		
		pnlFormTro.add(Box.createVerticalStrut(10));
		Box boxSDT = Box.createHorizontalBox();
		boxSDT.add(lblSDT=new JLabel("Số điện thoại"));
		boxSDT.add(txtSDT=new JTextField());
		pnlFormTro.add(boxSDT);
		
		// các nút chức năng thêm xóa sửa xóa trắng
		pnlFormTro.add(Box.createVerticalStrut(10));
		Box boxButton = Box.createHorizontalBox();
		boxButton.add(btnThem =new JButton("Thêm"));
		boxButton.add(Box.createHorizontalStrut(20));
		boxButton.add(btnXoa =new JButton("Xóa"));
		boxButton.add(Box.createHorizontalStrut(20));
		boxButton.add(btnSua =new JButton("Sửa"));
		boxButton.add(Box.createHorizontalStrut(20));
		boxButton.add(btnXoaTrang =new JButton("Xóa trắng"));
		pnlFormTro.add(boxButton); 
		pnlFormTro.add(Box.createVerticalStrut(20));
		pnlFormTro.setBorder(BorderFactory.createRaisedBevelBorder());
		
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Form tìm
		
		
		 JPanel pnlTim = new JPanel();
		 pnlTim.setLayout(new BoxLayout(pnlTim, BoxLayout.Y_AXIS));
		// pnlSouth.add(pnlTim, BorderLayout.EAST);
		 pnlFormTro.add(pnlTim);
		 pnlTim.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		 
		 JPanel pnlFormTim = new JPanel();
		 pnlTim.add(pnlFormTim);
		 
		 Box boxtxtTim = Box.createHorizontalBox();
		 boxtxtTim.add(lblTim=new JLabel("Tìm Thông tin sinh viên: "));
		 boxtxtTim.add(txtTim=new JTextField(10));
		 pnlFormTim.add(boxtxtTim);
		 
		 Box boxFormTim = Box.createVerticalBox();
		 
		 Box boxTimDiaChi = Box.createHorizontalBox();
		 boxTimDiaChi.add(lblDiaChi=new JLabel("Địa chỉ: "));
		 boxTimDiaChi.add(lblQuan = new JLabel("Quận: "));
		 boxTimDiaChi.add(JcmpQuan = new JComboBox<String>());
			
		 boxTimDiaChi.add(lblPhuong = new JLabel("Phường: "));
		 boxTimDiaChi.add(JcmpPhuong = new JComboBox<String>());
			
		 boxTimDiaChi.add(lblSoNha = new JLabel("Số nhà: "));
		 boxTimDiaChi.add(JcmpSoNha = new JComboBox<>());
			
		 boxTimDiaChi.add(lblDuong = new JLabel("Đường: "));
		 boxTimDiaChi.add(JcmpDuong = new JComboBox<String>());
		 pnlFormTim.add(boxTimDiaChi);
		 
		 //compobox chọn phương thức tìm
		 pnlFormTim.add(Box.createVerticalStrut(30));
		 Box boxcmpTim = Box.createHorizontalBox();
		 String[] s = "Mã;Tên;Địa chỉ".split(";");
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
		lblMaNhaTro.setPreferredSize(lblSDT.getPreferredSize());
		lblChuNha.setPreferredSize(lblSDT.getPreferredSize());
		lblDiaChi.setPreferredSize(lblSDT.getPreferredSize());
		
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
		cmp.addActionListener(this);
		//ConnectDB.getInstance().connect();
		
		
				/////////////////////////////////////////////////
		
		

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
    	  JOptionPane.showMessageDialog(null, "Loi hinh anh");
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
			txtChuNha.setText("");
			txtMaNhatro.setText("");
			txtSDT.setText("");
			txtTim.setText("");
			txtUser.setText("");
			txtTim.setText("");
		}
	}
	

}
