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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
import dao.TamLuuMaNhanVien_Dao;
import entity.DanhSachNhanVien;

public class GD_QLNhanVien extends JPanel implements ActionListener, MouseListener{
	
	//thông tin tk nhan vien
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
	
	
	
	//texfield nhanvien
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
	
	private DanhSachNhanVien dsnv;
	
	public void setDataFont(JLabel lbl) {
		lbl.setForeground(Color.BLUE);
		lbl.setFont(new Font("Monospaced", Font.BOLD, 15)); 
	}
	
	public GD_QLNhanVien() {
		// TODO Auto-generated constructor stub
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(1200, 750));
		
		JPanel pnl = new JPanel();
		pnl.setLayout(new BorderLayout());
		Box box = Box.createVerticalBox();
		Box b1, b2;
		box.add(b1 = Box.createHorizontalBox());
		
			JPanel pnluser1 = new JPanel();
				pnluser1.setBackground(Color.CYAN);
				Box buser = Box.createVerticalBox();
					Box buser1, buser2, buser3;
					buser.add(buser1 = Box.createHorizontalBox());
						buser1.add(lblUser = new JLabel("User: "));
						lblUser.setFont(new Font("Arial", Font.BOLD, 35));
						buser1.add(lblTKMaNV_data = new JLabel());
						lblTKMaNV_data.setFont(new Font("Monospace", Font.BOLD, 35));
						lblTKMaNV_data.setText("ÀEQEEV");
						lblTKMaNV_data.setForeground(Color.BLUE);
						buser1.add(Box.createVerticalStrut(20));
						
					buser.add(buser2 = Box.createVerticalBox());
	
					//Hinh Anh
					JPanel pnlcontent=new JPanel();
		     		JLabel lblBanner = new JLabel();
					pnlcontent.add(lblBanner);
					lblBanner.setSize(250,250);
					add(pnlcontent);
					setPicture(lblBanner, "HinhAnh/User.png");
					
					buser2.add(pnlcontent);
				pnluser1.add(buser);
				
				pnluser1.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		b1.add(pnluser1);
		pnl.add(box, BorderLayout.WEST);
		this.add(pnl, BorderLayout.CENTER);
		
		/////////////////////////////////////////////////////////////
		
		JPanel pnluser2 = new JPanel();
		pnluser2.setLayout(new BorderLayout());
			JPanel pnluser22 = new JPanel();
			pnluser22.setBackground(Color.CYAN);
			pnl.add(pnluser2);
			pnluser2.add(pnluser22, BorderLayout.CENTER);
			pnluser22.setLayout(new BoxLayout(pnluser22, BoxLayout.Y_AXIS));
				box.add(pnluser2);
				
				//thong tin TK nhan vien
				pnluser22.add(Box.createVerticalStrut(20));
				pnluser22.add(lblthongTinNV= new JLabel("Thông Tin Nhân Viên"));
				lblthongTinNV.setForeground(Color.RED);
				
				pnluser22.add(Box.createVerticalStrut(20));
				Box boxTKTenNV = Box.createHorizontalBox();
				pnluser22.add(boxTKTenNV);
				boxTKTenNV.add(lblTKTenNV = new JLabel("Họ tên   :   "));
				boxTKTenNV.add(lblTKTenNV_data = new JLabel());
				setDataFont(lblTKTenNV_data);
				lblTKTenNV_data.setText("Nguyễn Văn A");
				
				
				pnluser22.add(Box.createVerticalStrut(20));
				Box boxTKNgaySinh = Box.createHorizontalBox();
				pnluser22.add(boxTKNgaySinh);
				boxTKNgaySinh.add(lblTKNgaySinh = new JLabel("Ngày sinh   :   "));
				boxTKNgaySinh.add(lblTKNgaySinh_data = new JLabel());
				setDataFont(lblTKNgaySinh_data);
				lblTKNgaySinh_data.setText("11-10-1998");
				
				
				pnluser22.add(Box.createVerticalStrut(20));
				Box boxTKKhoa = Box.createHorizontalBox();
				pnluser22.add(boxTKKhoa);
				boxTKKhoa.add(lblTKKhoa = new JLabel("Khoa   :   "));
				boxTKKhoa.add(lblTKKhoa_data = new JLabel());
				setDataFont(lblTKKhoa_data);
				lblTKKhoa_data.setText("CNTT");
				
				pnluser22.add(Box.createVerticalStrut(20));
				Box boxTKCapBac = Box.createHorizontalBox();
				pnluser22.add(boxTKCapBac);
				boxTKCapBac.add(lblTKLoaiNV = new JLabel("Chức vụ   :   "));
				boxTKCapBac.add(lblTKLoaiNV_data = new JLabel());
				setDataFont(lblTKLoaiNV_data);
				lblTKLoaiNV_data.setText("Người quản lý");
				
				//lblTKTenNV.setPreferredSize(lblTKNgaySinh.getPreferredSize());
				//lblTKKhoa.setPreferredSize(lblTKNgaySinh.getPreferredSize());
				//lblTKLoaiNV.setPreferredSize(lblTKNgaySinh.getPreferredSize());

				
				/*lblTKTenNV.add(Box.createHorizontalStrut(30));
				lblTKTenNV.add(Box.createVerticalStrut(20));
				pnluser22.add(Box.createVerticalStrut(10));
				pnluser22.add(lblTKTenNV= new JLabel("Tên Nhân Viên:"));
				
				/*btnSinhVien.add(Box.createHorizontalStrut(30));
				btnSinhVien.add(Box.createVerticalStrut(20));
				pnluser22.add(Box.createVerticalStrut(10));
				pnluser22.add(btnNhanVien = new JButton("Quản Lý Nhân Viên"));
				
				btnNhanVien.add(Box.createHorizontalStrut(30));
				btnNhanVien.add(Box.createVerticalStrut(20));
				pnluser22.add(Box.createVerticalStrut(10));
				pnluser22.add(btnThueTro = new JButton("Quản Lý Thuê Trọ"));
				
				btnThueTro.add(Box.createHorizontalStrut(30));
				btnThueTro.add(Box.createVerticalStrut(20));
				pnluser22.add(Box.createVerticalStrut(10));
				pnluser22.add(btnThongKe = new JButton("Thống Kê"));
				
				btnThongKe.add(Box.createHorizontalStrut(30));
				btnThongKe.add(Box.createVerticalStrut(20));
				pnluser22.add(Box.createVerticalStrut(10));
				pnluser22.add(btnHuongDanSD = new JButton("Hướng Dẫn Sử Dụng"));
				
				btnHuongDanSD.add(Box.createHorizontalStrut(30));
				btnHuongDanSD.add(Box.createVerticalStrut(20));
				*/
				
				pnluser22.add(Box.createVerticalStrut(50));
				
				pnluser22.add(btnQuanLy = new JButton("Quản Lý Sinh Viên"));
				btnQuanLy.setBackground(Color.ORANGE);
				btnQuanLy.add(Box.createHorizontalStrut(50));
				btnQuanLy.add(Box.createVerticalStrut(20));
				pnluser22.add(Box.createVerticalStrut(10));

				
				pnluser22.add(btnThongKe = new JButton("Thống kê"));
				btnThongKe.setBackground(Color.ORANGE);
				btnThongKe.add(Box.createHorizontalStrut(50));
				btnThongKe.add(Box.createVerticalStrut(20));
				
				pnluser22.add(Box.createVerticalStrut(10));
				pnluser22.add(btnCapNhatTK = new JButton("Cập Nhật Thông Tin"));
				btnCapNhatTK.setBackground(Color.ORANGE);
				btnCapNhatTK.add(Box.createHorizontalStrut(30));
				btnCapNhatTK.add(Box.createVerticalStrut(20));
				
				pnluser22.add(Box.createVerticalStrut(10));
				pnluser22.add(btnThoat = new JButton("Thoát"));
				btnThoat.setBackground(Color.RED);
				btnThoat.setForeground(Color.WHITE);
				btnThoat.add(Box.createHorizontalStrut(30));
				btnThoat.add(Box.createVerticalStrut(20));

		
		//Center
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
	//Table
		boxCen.add(bcen2 = Box.createHorizontalBox());
		
		
		JPanel pnlcen2 = new JPanel();
			JScrollPane scroll;
			String[] header="Mã Nhân Viên;Tên Nhân Viên;Ngày Sinh;Khoa;Cấp Bậc".split(";");
			tableModel=new DefaultTableModel(header,20);
			pnlcen2.add(scroll=new JScrollPane(table=new JTable(tableModel),JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),BorderLayout.SOUTH);
			scroll.setBorder(BorderFactory.createTitledBorder("Danh Sách"));
			table.setRowHeight(50);
			scroll.setPreferredSize(new Dimension(0,500));
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
		JPanel pnlForm=new JPanel();
		pnlSouth.add(pnlForm);
		boxcen31.add( pnlSouth);
		pnlForm.setBounds(0,80,1055,450);
		pnlForm.setLayout(new BoxLayout(pnlForm, BoxLayout.Y_AXIS));
		
		
		pnlForm.add(Box.createVerticalStrut(30));
		Box boxMaNV = Box.createHorizontalBox();
		boxMaNV.add(lblMaNV=new JLabel("Mã Nhân Viên: "));
		boxMaNV.add(txtMaNV=new JTextField());
		pnlForm.add(boxMaNV);
		
		pnlForm.add(Box.createVerticalStrut(10));
		Box boxTenNV = Box.createHorizontalBox();
		boxTenNV.add(lblTenNV=new JLabel("Tên Nhân Viên: "));
		boxTenNV.add(txtTenNV=new JTextField());
		pnlForm.add(boxTenNV);
		
		pnlForm.add(Box.createVerticalStrut(10));
		Box boxNgaySinh = Box.createHorizontalBox();
		boxNgaySinh.add(lblNgaySinh=new JLabel("Ngày Sinh: "));
		boxNgaySinh.add(txtNgaySinh=new JTextField());
		pnlForm.add(boxNgaySinh);
		
		pnlForm.add(Box.createVerticalStrut(10));
		Box boxKhoa = Box.createHorizontalBox();
		boxKhoa.add(lblKhoa=new JLabel("Khoa: "));
		boxKhoa.add(cbKhoa=new JComboBox<>());
		String []khoa = "CNTT;QTKD;TMDT".split(";");
		for(String k : khoa) {
			cbKhoa.addItem(k);
		}
		pnlForm.add(boxKhoa);
		
		pnlForm.add(Box.createVerticalStrut(10));
		Box boxLoaiNV = Box.createHorizontalBox();
		boxLoaiNV.add(lblLoaiNV=new JLabel("Chức Vụ: "));
		boxLoaiNV.add(cbCapBac = new JComboBox<>());
		cbCapBac.addItem("GVK");
		cbCapBac.addItem("NQL");
		pnlForm.add(boxLoaiNV);
		
		lblMaNV.setPreferredSize(lblTenNV.getPreferredSize());
		lblNgaySinh.setPreferredSize(lblTenNV.getPreferredSize());
		lblKhoa.setPreferredSize(lblTenNV.getPreferredSize());
		lblLoaiNV.setPreferredSize(lblTenNV.getPreferredSize());
		
		pnlForm.add(Box.createVerticalStrut(10));
		Box boxButton = Box.createHorizontalBox();
		boxButton.add(btnThem =new JButton("Thêm"));
		boxButton.add(Box.createHorizontalStrut(20));
		boxButton.add(btnXoa =new JButton("Xoá"));
		boxButton.add(Box.createHorizontalStrut(20));
		boxButton.add(btnSua =new JButton("Sửa"));
		boxButton.add(Box.createHorizontalStrut(20));
		boxButton.add(btnXoaTrang =new JButton("Xoá Trắng"));
		pnlForm.add(boxButton);
		pnlForm.add(Box.createVerticalStrut(20));
		
		
		
		
		 pnlForm.setBorder(BorderFactory.createRaisedBevelBorder());
		 
		 JPanel pnlSouthRight = new JPanel();
		 pnlSouthRight.setLayout(new BoxLayout(pnlSouthRight, BoxLayout.Y_AXIS));
		 pnlSouth.add(pnlSouthRight, BorderLayout.EAST);
		 pnlSouthRight.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		 
		 
		 pnlSouthRight.add(Box.createVerticalStrut(30));
		 Box boxtxtTim = Box.createHorizontalBox();
		 boxtxtTim.add(lblTim=new JLabel("Tìm"));
		 boxtxtTim.add(txtTim=new JTextField(20));
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
		 pnlSouthRight.add(boxbtnTim);
		 
		
	
		//btnHuongDanSD.addActionListener(this);
		//btnNhanVien.addActionListener(this);
		//btnSinhVien.addActionListener(this);
		btnSua.addActionListener(this);
		btnThem.addActionListener(this);
		btnThoat.addActionListener(this);
		//btnThongKe.addActionListener(this);
		//btnThueTro.addActionListener(this);
		btnTim.addActionListener(this);
		//btnTro.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		table.addMouseListener(this);
		
		
		
		
				/////////////////////////////////////////////////
		
		//pnlcen3.add(Box.createHorizontalStrut(400), BorderLayout.EAST);
		loadNhanVienTuDatabase();
		setVisible(true);
	}
	
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
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		
		if(ob.equals(btnCapNhatTK))
		{
			
		}
		else if(ob.equals(btnSua))
		{
			
		}
		else if(ob.equals(btnThem))
		{
			//lấy dl từ textfield
			String maNV = txtMaNV.getText();
			String tenNV = txtTenNV.getText();
			String ngaySinh = txtNgaySinh.getText();
			String khoa = cbKhoa.getSelectedItem().toString();
			String loaiNV = cbCapBac.getSelectedItem().toString();
			
		
			
			//sql connecting
			if
			try {
				ConnectDB.getInstance().connect();
				Connection con = ConnectDB.getConnecction();
				Statement st = con.createStatement();
				st.executeUpdate("INSERT INTO NhanVien(maNhanVien, loaiNhanVien, matKhau, tenNhanVien, ngaySinh, tenKhoa) "
						+ "VALUES ('"+maNV+"', '"+loaiNV+"', '12345','"+tenNV+"', '"+ngaySinh+"', '"+khoa+"')"); 
				con.close();
				Object o[] = {maNV, tenNV, ngaySinh, khoa, loaiNV};
				tableModel.insertRow(row, o);
			} catch (SQLException ex) {
				// TODO: handle exception
				ex.printStackTrace();

			}
		}
		else if(ob.equals(btnThoat))
		{
			
		}
		else if(ob.equals(btnThongKe))
		{
			
		}
		else if(ob.equals(btnTim))
		{
			
			//lay maNV
			String maNhanVienCanTim = txtTim.getText();
			
			try {
				ConnectDB.getInstance().connect();
				Connection con = ConnectDB.getConnecction();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM NhanVien WHERE maNhanVien='"+maNhanVienCanTim+"'");
				int row = 0;
				while(rs.next()) {
					
					Object o[] = {rs.getString("maNhanVien"), rs.getString("tenNhanVien"),
							rs.getString("ngaySinh"),rs.getString("tenKhoa"), rs.getString("loaiNhanVien")};
					tableModel.setRowCount(0);
					tableModel.addRow(o);
					row++;
				}
				con.close();
			} catch (SQLException e3) {
				// TODO: handle exception
				e3.printStackTrace();

			}
		}
		else if(ob.equals(btnQuanLy))
		{
			
		}
		else if(ob.equals(btnXoa))
		{
			
		}
		else if(ob.equals(btnXoaTrang))
		{
			txtMaNV.setText("");
			txtTenNV.setText("");
			txtNgaySinh.setText("");
			cbKhoa.setSelectedIndex(0);
			cbCapBac.setSelectedIndex(0);
			txtTim.setText("");
			cmp.setSelectedIndex(0);
		}
	
	}

	private void themVaoBang() {
		// TODO Auto-generated method stub
		
	}
	public void loadNhanVienTuDatabase() {
		//mở kết nối sql
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnecction();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM NhanVien");
			int row = 0;
			tableModel.setRowCount(0);
			while(rs.next()) {
				
				Object o[] = {rs.getString("maNhanVien"), rs.getString("tenNhanVien"),
						rs.getString("ngaySinh"),rs.getString("tenKhoa"), rs.getString("loaiNhanVien")};
				
				tableModel.addRow(o);
				String namSinh_arr[] = rs.getString("ngaySinh").split("-");
				
			}
			con.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();

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
		
		//uneditable
		txtMaNV.setEditable(false);
		txtTenNV.setEditable(false);
		txtNgaySinh.setEditable(false);
		cbKhoa.setEnabled(false);
		cbCapBac.setEnabled(false);
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