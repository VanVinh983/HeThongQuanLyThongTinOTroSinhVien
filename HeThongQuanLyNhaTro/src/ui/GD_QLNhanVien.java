
package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
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
import java.time.LocalDate;
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

public class GD_QLNhanVien extends JPanel implements ActionListener, MouseListener{
	
	//thông tin tk nhan vien
	private JLabel lblthongTinNV;
	private JLabel lblTKMaNV;
	private JLabel lblTKTenNV;
	private JLabel lblTKNgaySinh;
	private JLabel lblTKLoaiNV;
	private JLabel lblTKKhoa;
	private TamLuuMaNhanVien_Dao tamluu_dao;
	private String maNV_data;
	
	//lịch
	private JComboBox<Integer> cbNgay;
	private JComboBox<Integer> cbThang;
	private JComboBox<Integer> cbNam;
	
	private JLabel lblNameUser;
	//texfield nhanvien
	private JLabel lblMaNV;
	private JLabel lblTenNV;
	private JLabel lblNgaySinh;
	private JLabel lblLoaiNV;
	private JLabel lblKhoa;
	private JTextField txtMaNV;
	private JTextField txtTenNV;
	private JTextField txtLoaiNV;
	private JTextField txtKhoa;
	
	//Thanh điều hướng
	private JButton btnTro;
	private JButton btnSinhVien;
	private JButton btnThongKe;
	private JButton btnThueTro;
	private JButton btnNhanVien;
	private JButton btnHuongDanSD;
	private JButton btnThoat;

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
		// TODO Auto-generated constructor stub
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
		

		
		//Center
		JPanel pnlCen = new JPanel();
		Box boxCen = Box.createVerticalBox();
		
		Box bcen1, bcen2, bcen3;
		boxCen.add(bcen1 = Box.createHorizontalBox());
		
		JPanel pnlQuanLyNhanVien = new JPanel();
		pnlQuanLyNhanVien.add(lblcen1 = new JLabel("QUẢN LÝ NHÂN VIÊN"));
		bcen1.add(pnlQuanLyNhanVien);
		pnlQuanLyNhanVien.setBackground(Color.BLUE);
		lblcen1.setFont(new Font("Arial", Font.BOLD, 40));
		lblcen1.setForeground(Color.PINK);
	//Table
		boxCen.add(bcen2 = Box.createHorizontalBox());
		
		
		JPanel pnlcen2 = new JPanel();
			JScrollPane scroll;
			String[] header="Mã Nhân Viên;Tên Nhân Viên;Ngày Sinh;Khoa;Cấp Bậc".split(";");
			tableModel=new DefaultTableModel(header, 20);
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
			pnlcen2.add(scroll=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),BorderLayout.SOUTH);
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
		boxMaNV.add(lblMaNV=new JLabel("Mã nhân viên: "));
		boxMaNV.add(txtMaNV=new JTextField());
		txtMaNV.setEditable(false);
		pnlForm.add(boxMaNV);
		
		pnlForm.add(Box.createVerticalStrut(10));
		Box boxTenNV = Box.createHorizontalBox();
		boxTenNV.add(lblTenNV=new JLabel("Tên nhân viên: "));
		boxTenNV.add(txtTenNV=new JTextField());
		pnlForm.add(boxTenNV);
		
		pnlForm.add(Box.createVerticalStrut(10));
		Box boxNgaySinh = Box.createHorizontalBox();
		boxNgaySinh.add(lblNgaySinh=new JLabel("Ngày sinh: "));
		cbNam = new JComboBox<Integer>();
		for(int i = 1970; i <= 2020; i++) {
			cbNam.addItem(i);
		}
		cbThang = new JComboBox<Integer>();
        for (int i = 1; i <= 12; i++) {
            cbThang.addItem(i);
        }

        cbNgay = new JComboBox<Integer>();
        for (int i = 1; i<=31; i++) {
        	cbNgay.addItem(i);
        }
        cbNam.setSelectedIndex(cbNam.getItemCount() - 1);
        boxNgaySinh.add(new JLabel("  Năm  "));
        boxNgaySinh.add(cbNam);
        boxNgaySinh.add(new JLabel("  Tháng  "));
        boxNgaySinh.add(cbThang);
        boxNgaySinh.add(new JLabel("  Ngày  "));
        boxNgaySinh.add(cbNgay);
		//boxNgaySinh.add(txtNgaySinh=new JTextField());
		pnlForm.add(boxNgaySinh);
		
		pnlForm.add(Box.createVerticalStrut(10));
		Box boxLoaiNV = Box.createHorizontalBox();
		boxLoaiNV.add(lblLoaiNV=new JLabel("Cấp bậc: "));
		boxLoaiNV.add(cbCapBac = new JComboBox<>());
		cbCapBac.addItem("QL");
		cbCapBac.addItem("NV");
		pnlForm.add(boxLoaiNV);
		
		pnlForm.add(Box.createVerticalStrut(10));
		Box boxKhoa = Box.createHorizontalBox();
		boxKhoa.add(lblKhoa=new JLabel("Khoa: "));
		boxKhoa.add(cbKhoa=new JComboBox<>());
		//cbkhoa
		pnlForm.add(boxKhoa);
		
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
		 //Box boxtxtTim = Box.createHorizontalBox();
		 pnlSouthRight.add(lblTim=new JLabel("Tìm"));
		 pnlSouthRight.add(txtTim=new JTextField(10));
		 //pnlSouthRight.add(boxtxtTim);
		 
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
		 
		 boxbtnTim.add(btnDSNV = new JButton("Làm mới DS"));
		 btnDSNV.add(Box.createHorizontalStrut(50));
		 btnDSNV.add(Box.createVerticalStrut(50));
		 pnlSouthRight.add(boxbtnTim);
		 
		 
		
		cbCapBac.addActionListener(this);
		cbKhoa.addActionListener(this);
		cbNam.addActionListener(this);
		cbThang.addActionListener(this);
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
		table.addMouseListener(this);
		
		/////////////////////////////////////////////////
		
		//pnlcen3.add(Box.createHorizontalStrut(400), BorderLayout.EAST);
		tamluu_dao = new TamLuuMaNhanVien_Dao();
		nv_dao = new NhanVien_Dao();
		nv_dao.loadNhanVienTuDatabase();
		loadToanBoNhanVienLenTable();
		xoaTrangAction();
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
		
		if(ob.equals(cbThang) || ob.equals(cbNam)) {
			
			// chỉnh các ngày trong tháng
			
			int nam = (int)cbNam.getSelectedItem();
			int thang = (int) cbThang.getSelectedItem();
		    int soNgayTrongThang = LocalDate.of(nam, thang, 1).lengthOfMonth();
		    int previousSelection = cbNgay.getSelectedItem() != null ? (int) cbNgay.getSelectedItem() : 1;
		    cbNgay.removeAllItems();
		    for (int i = 1; i <= soNgayTrongThang; i++) {
		        cbNgay.addItem(i);
		    }
		     if (previousSelection >= cbNgay.getItemCount())
		            //select last index of month
		     cbNgay.setSelectedIndex(cbNgay.getItemCount() - 1);
		     else
		    	 cbNgay.setSelectedItem(previousSelection);
		}
		else if(ob.equals(cbCapBac)) {
			if(cbCapBac.getSelectedItem().toString().equals("QL")) {
				cbKhoa.removeAllItems();
				cbKhoa.addItem(" ");
			}
			else if(cbCapBac.getSelectedItem().toString().equals("NV")) {
				cbKhoa.removeAllItems();
				String []khoa = "Công Nghệ Cơ Khí;Công Nghệ Thông Tin;Công Nghệ Điện;Công Nghệ Điện Tử;Công Nghệ Động Lực;Công Nghệ Nhiệt - Lạnh;Công Nghệ May - Thời Trang;Công Nghệ Hóa Học;Kế toán - Kiểm toán;Lý Luận Chính Trị;Ngoại Ngữ;Quản Trị Kinh Doanh;Tài Chính - Ngân Hàng;Thương Mại - Du Lịch;Kỹ Thuật Xây Dựng;Luật;Khoa Học Cơ Bản".split(";");
				for(String k : khoa) {
					cbKhoa.addItem(k);
				}
			}	
		}
		else if(ob.equals(btnSua))
		{
			if(validData()) {
				int row = table.getSelectedRow();
				
				String maNV = txtMaNV.getText().strip();
				String tenNV = txtTenNV.getText().strip();
				String khoa = cbKhoa.getSelectedItem().toString();
				String loaiNV = cbCapBac.getSelectedItem().toString();
				
				String ngaySinh =  (int)cbNam.getSelectedItem() + "-" + (int)cbThang.getSelectedItem() + "-" + (int)cbNgay.getSelectedItem();

				
				NhanVien nv = new NhanVien(maNV, loaiNV, tenNV,"12345", Date.valueOf(ngaySinh), khoa);
				nv_dao.capNhatNhanVienTrongDatabase(nv);
				table.setValueAt(nv.getTenNV(), row, 1);
				table.setValueAt(new SimpleDateFormat("dd/MM/yyyy").format(nv.getNgaySinh()), row, 2);
				table.setValueAt(nv.getTenKhoa(), row, 3);
				table.setValueAt(nv.getLoaiNV(), row, 4);
			}
		}	
		else if(ob.equals(btnThem))
		{
			if(validData()) {
				//lấy dl từ textfield
				String maNV = txtMaNV.getText().strip();
				String tenNV = txtTenNV.getText().strip();
				String loaiNV = cbCapBac.getSelectedItem().toString();
				String khoa = cbKhoa.getSelectedItem().toString();
				String ngaySinh =  (int)cbNam.getSelectedItem() + "-" + (int)cbThang.getSelectedItem() + "-" + (int)cbNgay.getSelectedItem();
				String ngaySinh_str =  (int)cbNgay.getSelectedItem() + "/" + (int)cbThang.getSelectedItem() + "/" + (int)cbNam.getSelectedItem();
				
				validData();
				
				NhanVien nv = new NhanVien(maNV, loaiNV, tenNV,"12345", Date.valueOf(ngaySinh), khoa);
				
				
				if(nv_dao.themNV(nv)) {
					Object o[] = {maNV, tenNV, ngaySinh_str, khoa, loaiNV};
					tableModel.addRow(o);
					loadToanBoNhanVienLenTable();
					xoaTrangAction();
					JOptionPane.showMessageDialog(this, "Thêm thành công!");
				}
				else {
					JOptionPane.showMessageDialog(this, "Trùng mã nhân viên!");
				}
			}	
		}
		else if(ob.equals(btnThoat))
		{
				removeAll();
				add(new GD_Admin());
				repaint();
				revalidate();
		}
		else if(ob.equals(btnThongKe))
		{
			removeAll();
			add(new GD_ThongKe());
			repaint();
			revalidate();
		}
		else if(ob.equals(btnTim))
		{
			
			//tim theo ma
			if(cmp.getSelectedItem().toString().equals("Mã")) {
				String maNhanVienCanTim = txtTim.getText().strip();
				ArrayList<NhanVien> dsNVTheoMa  = nv_dao.timNhanVienTheoMa(maNhanVienCanTim);
				tableModel.setRowCount(0);
				for(NhanVien nv : dsNVTheoMa){
					Object o[] = {nv.getMaNV(), nv.getTenNV(), new SimpleDateFormat("dd/MM/yyyy").format(nv.getNgaySinh()), nv.getTenKhoa(), nv.getLoaiNV()};
					tableModel.addRow(o);
				}
			}
			
			//tim theo ten
			if(cmp.getSelectedItem().toString().equals("Tên Nhân Viên")) {
				String tenNhanVienCanTim = txtTim.getText().strip();
				ArrayList<NhanVien> dsNVTheoTen  = nv_dao.timNhanVienTheoTen(tenNhanVienCanTim);
				tableModel.setRowCount(0);
				for(NhanVien nv : dsNVTheoTen){
					Object o[] = {nv.getMaNV(), nv.getTenNV(), new SimpleDateFormat("dd/MM/yyyy").format(nv.getNgaySinh()), nv.getTenKhoa(), nv.getLoaiNV()};
					tableModel.addRow(o);
				}
			}
			
			//tim theo khoa
			if(cmp.getSelectedItem().toString().equals("Khoa")) {
				String khoaCanTim = txtTim.getText().strip();
				ArrayList<NhanVien> dsNVTheoTen  = nv_dao.timNhanVienTheoKhoa(khoaCanTim);
				tableModel.setRowCount(0);
				for(NhanVien nv : dsNVTheoTen){
					Object o[] = {nv.getMaNV(), nv.getTenNV(), new SimpleDateFormat("dd/MM/yyyy").format(nv.getNgaySinh()), nv.getTenKhoa(), nv.getLoaiNV()};
					tableModel.addRow(o);
				}
			}
			
			
		}
		else if(ob.equals(btnQuanLy))
		{
			
		}
		else if(ob.equals(btnXoa))
		{
			xoaAction();
		}
		else if(ob.equals(btnXoaTrang))
		{	
			xoaTrangAction();
			
		}
		else if(ob.equals(btnDSNV)) {
			loadToanBoNhanVienLenTable();
		}
		else if(ob.equals(btnTro)) {
			removeAll();
			add(new GD_QuanLyTro());
			repaint();
			revalidate();
		}
		else if(ob.equals(btnSinhVien)) {
			removeAll();
			add(new GD_QuanLySinhVien());
			repaint();
			revalidate();
		}
		else if(ob.equals(btnHuongDanSD)) {
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
		else if(ob.equals(btnThueTro)) {
			removeAll();
			add(new GD_ThongTinThueTro());
			repaint();
			revalidate();
		}
	}



	private void xoaAction() {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		String maNV = txtMaNV.getText();
		if(nv_dao.xoaNhanVien(maNV)) {
			int loiNhac = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa?");
			if(loiNhac==JOptionPane.YES_OPTION) {
				tableModel.removeRow(row);
				JOptionPane.showMessageDialog(this, "Xóa thành công!");
				xoaTrangAction();
			}
			else {
				return;
			}
		}
	}

	private void xoaTrangAction() {
		// TODO Auto-generated method stub
		txtTenNV.setEditable(true);
		cbKhoa.setEnabled(true);
		cbCapBac.setEnabled(true);
		
		txtMaNV.setText(nv_dao.phatSinhMaNV());
		txtTenNV.setText("");
		cbKhoa.setSelectedItem(" ");
		cbCapBac.setSelectedIndex(0);
		txtTim.setText("");
		cmp.setSelectedIndex(0);
		cbNam.setSelectedIndex(0);
		cbThang.setSelectedIndex(0);
		cbNgay.setSelectedIndex(0);
	}

	public void loadToanBoNhanVienLenTable() {
		tableModel.setRowCount(0);
		for(NhanVien nv : nv_dao.layDanhSach()) {
			Object o[] = {nv.getMaNV(), nv.getTenNV(), new SimpleDateFormat("dd/MM/yyyy").format(nv.getNgaySinh()), nv.getTenKhoa(), nv.getLoaiNV()};
			tableModel.addRow(o);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMaNV.setText(table.getValueAt(row, 0).toString());
		txtTenNV.setText(table.getValueAt(row, 1).toString());
		cbKhoa.setSelectedItem(table.getValueAt(row, 3).toString());
		cbCapBac.setSelectedItem(table.getValueAt(row, 4).toString());
		String []ngaySinh_arr = table.getValueAt(row, 2).toString().split("/");
		cbNam.setSelectedItem(Integer.parseInt(ngaySinh_arr[2]));
		cbThang.setSelectedItem(Integer.parseInt(ngaySinh_arr[1]));
		cbNgay.setSelectedItem(Integer.parseInt(ngaySinh_arr[0]));
		
	}
	
	public boolean validData() {
		if(!(txtTenNV.getText().length()>0)) {
			JOptionPane.showMessageDialog(this, "Tên nhân viên không được bỏ trống");
			txtTenNV.requestFocus();
			return false;
		}
		/*if(!(txtTenNV.getText().matches("[A-Z][a-z]*( [A-Z].[a-z]*)*"))) {
			JOptionPane.showMessageDialog(this, "Họ tên sai định dạng");
			txtTenNV.requestFocus();
			return false;
		}*/
		

		
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
}
