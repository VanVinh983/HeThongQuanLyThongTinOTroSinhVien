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
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
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

public class GD_ThemTroMoi extends JPanel implements ActionListener, MouseListener {

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
	public GD_ThemTroMoi() {
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

		// Tạo ảnh và txt hiển thị user
		JPanel pnlUser = new JPanel();
		pnlUser.setBackground(new Color(108, 123, 139));
		Box buser = Box.createVerticalBox();
		Box bUser, buserImg;
		buser.add(bUser = Box.createHorizontalBox());
		NhaTro_Dao dao = new NhaTro_Dao();

		String tenKhoaNV = dao.layTenNhanVien().trim().toString();
		if (dao.layTenNhanVien().trim().toString().equals("NV")) {
			bUser.add(lblNameUser = new JLabel("GV:" + tenKhoaNV));
		} else if (dao.layTenNhanVien().trim().toString().equals("QL")) {
			bUser.add(lblNameUser = new JLabel("Quản lý"));
		}
		lblNameUser.setFont(new Font("Arial", Font.BOLD, 30));

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
		//thêm vào màn hình chính
		this.add(pnl, BorderLayout.CENTER);

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		JPanel pnlMenu = new JPanel();
		pnlMenu.setLayout(new BorderLayout());
		JPanel pnlMenubtn = new JPanel();
		pnlMenubtn.setBackground(Color.cyan);
		pnlMenu.add(pnlMenubtn, BorderLayout.CENTER);
		pnlMenubtn.setLayout(new BoxLayout(pnlMenubtn, BoxLayout.Y_AXIS));
		box.add(pnlMenu);
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Thanh điều hướng

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// Center
		JPanel pnlCen = new JPanel();
		Box boxCen = Box.createVerticalBox();

		Box bcenTitle, bcenTable, bcenForm;
		boxCen.add(bcenTitle = Box.createHorizontalBox());
		// Label Title Quản lý trọ
		JPanel pnlQuanLyTro = new JPanel();
		pnlQuanLyTro.add(lblcenTitle = new JLabel("Thêm Trọ Mới"));
		bcenTitle.add(pnlQuanLyTro);
		pnlQuanLyTro.setBackground(Color.blue);
		lblcenTitle.setFont(new Font("Arial", Font.BOLD, 40));
		lblcenTitle.setForeground(Color.WHITE);
		// Table
		boxCen.add(bcenTable = Box.createHorizontalBox());

		JPanel pnlTable = new JPanel();
		JScrollPane scroll;
		String[] header = "Mã nhà trọ;Tên chủ nhà;Địa chỉ;Số điện thoại".split(";");
		tableModel = new DefaultTableModel(header, 0);
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
		pnlTable.add(scroll = new JScrollPane(table = new JTable(tableModel), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.SOUTH);
		scroll.setBorder(BorderFactory.createTitledBorder("Danh Sách"));
		table.setRowHeight(30);
		scroll.setPreferredSize(new Dimension(0, 500));
		bcenTable.add(scroll);
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		pnl.add(boxCen, BorderLayout.CENTER);

		// South
		// Form thông tin
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

		// Form điền thông tin
		pnlFormSV.add(Box.createVerticalStrut(10));
		
		Box boxMaTro = Box.createHorizontalBox();
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
		/////////////////////////
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
		
		boxButton.add(btnXoaTrang =new JButton("Xóa trắng"));
		btnXoaTrang.setFont(new Font("Arial", Font.BOLD, 25));
		btnXoaTrang.setBackground(new Color(156, 156, 156));
		pnlFormSV.add(boxButton); 
		pnlFormSV.add(Box.createVerticalStrut(20));
		pnlFormSV.setBorder(BorderFactory.createRaisedBevelBorder());
		


		// điêu chỉnh kích thướt theo lblSDT
		lblMaNhaTro.setPreferredSize(lblSDT.getPreferredSize());
		lblChuNha.setPreferredSize(lblSDT.getPreferredSize());
		lblDiaChi.setPreferredSize(lblSDT.getPreferredSize());

		btnThem.addActionListener(this);

		btnXoaTrang.addActionListener(this);
		table.addMouseListener(this);

		setVisible(true);
		JcmpQuan.addActionListener(this);
		JcmpPhuong.addActionListener(this);
		try {
			ConnectDB.getInstance().connect();
			addDatabase();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addDatabase() {
		NhaTro_Dao dao = new NhaTro_Dao();
		List<NhaTro> listNhaTro = dao.layTatCaBang();
		//Đưa thông tin vào bảng
		listNhaTro.forEach(v -> {
			String diaChi = v.getDiaChiTro().getSoNha() +" - "+ v.getDiaChiTro().getTenDuong() +" - "+  v.getDiaChiTro().getTenPhuong() +" - " + v.getDiaChiTro().getTenQuan();
			String[] row = {v.getMaTro(), v.getTenChutro(),diaChi, v.getSDT()};
			tableModel.addRow(row);
		});
		
		
		
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

	@Override
	public void mouseClicked(MouseEvent arg0) {
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

	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		if (ob.equals(JcmpQuan)) {

			if (JcmpQuan.getSelectedItem().equals(" ")) {
				JcmpPhuong.removeAllItems();
			} else if (JcmpQuan.getSelectedItem().equals("Quận Gò Vấp")) {
				JcmpPhuong.removeAllItems();
				String[] item = "Phường 1,Phường 3,Phường 4,Phường 5,Phường 6,Phường 7,Phường 8,Phường 9,Phường 10,Phường 11,Phường 12,Phường 13,Phường 14,Phường 15,Phường 16,Phường 17"
						.split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			} else if (JcmpQuan.getSelectedItem().equals("Quận 1")) {
				JcmpPhuong.removeAllItems();
				String[] item = "Phường Bến Nghé,Phường Bến Thành,Phường Cầu Kho,Phường Cầu Ông Lãnh,Phường Cô Giang,Phường Đa Kao,Phường Nguyễn Cư Trinh,Phường Nguyễn Thái Bình,Phường Phạm Ngũ Lão,Phường Tân Định"
						.split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			}

			else if (JcmpQuan.getSelectedItem().equals("Quận 2")) {
				JcmpPhuong.removeAllItems();

				String[] item = "Phường An Khánh,Phường An Lợi Đông,Phường An Phú,Phường Bình An,Phường Bình Khánh,Phường Bình Trưng Đông,Phường Bình Trưng Tây,Phường Cát Lái,Phường Thạnh Mỹ Lợi,Phường Thảo Điền,Phường Thủ Thiêm"
						.split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			}
			if (JcmpQuan.getSelectedItem().equals("Quận 3")) {
				JcmpPhuong.removeAllItems();
				String[] item = "Phường 1,Phường 3,Phường 4,Phường 5,Phường 6,Phường 7,Phường 8,Phường 9,Phường 10,Phường 11,Phường 12,Phường 13,Phường 14"
						.split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			} else if (JcmpQuan.getSelectedItem().equals("Quận 4")) {
				JcmpPhuong.removeAllItems();
				String[] item = "Phường 1,Phường 3,Phường 4,Phường 5,Phường 6,Phường 7,Phường 8,Phường 9,Phường 10,Phường 11,Phường 12,Phường 13,Phường 14,Phường 15,Phường 16,Phường 17,Phường 18"
						.split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			} else if (JcmpQuan.getSelectedItem().equals("Quận 5")) {
				JcmpPhuong.removeAllItems();
				String[] item = "Phường 1,Phường 3,Phường 4,Phường 5,Phường 6,Phường 7,Phường 8,Phường 9,Phường 10,Phường 11,Phường 12,Phường 13,Phường 14,Phường 15"
						.split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			} else if (JcmpQuan.getSelectedItem().equals("Quận 6")) {
				JcmpPhuong.removeAllItems();
				String[] item = "Phường 1,Phường 3,Phường 4,Phường 5,Phường 6,Phường 7,Phường 8,Phường 9,Phường 10,Phường 11,Phường 12,Phường 13,Phường 14"
						.split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			}

			else if (JcmpQuan.getSelectedItem().equals("Quận 7")) {
				JcmpPhuong.removeAllItems();
				String[] item = "Phường Bình Thuận,Phường Phú Mỹ,Phường Phú Thuận,Phường Tân Hưng,Phường Tân Kiểng,Phường Tân Phong,Phường Tân Phú,Phường Tân Quy,Phường Tân Thuận Đông,Phường Tân Thuận Tây"
						.split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			} else if (JcmpQuan.getSelectedItem().equals("Quận 8")) {
				JcmpPhuong.removeAllItems();
				String[] item = "Phường 1,Phường 3,Phường 4,Phường 5,Phường 6,Phường 7,Phường 8,Phường 9,Phường 10,Phường 11,Phường 12,Phường 13,Phường 14,Phường 15,Phường 16"
						.split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			}

			else if (JcmpQuan.getSelectedItem().equals("Quận 9")) {
				JcmpPhuong.removeAllItems();
				String[] item = "Phường Hiệp Phú,Phường Long Bình,Phường Long Phước,Phường Long Thạnh Mỹ,Phường Long Trường,Phường Phú Hữu,Phường Phước Bình,Phường Phước Long A,Phường Phước Long B,Phường Tân Phú,Phường Tăng Nhơn Phú A,Phường Tăng Nhơn Phú B,Phường Trường Thạnh"
						.split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			}
			//
			else if (JcmpQuan.getSelectedItem().equals("Quận 10")) {
				JcmpPhuong.removeAllItems();
				String[] item = "Phường 1,Phường 3,Phường 4,Phường 5,Phường 6,Phường 7,Phường 8,Phường 9,Phường 10,Phường 11,Phường 12,Phường 13,Phường 14,Phường 15"
						.split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			} else if (JcmpQuan.getSelectedItem().equals("Quận 11")) {
				JcmpPhuong.removeAllItems();
				String[] item = "Phường 1,Phường 3,Phường 4,Phường 5,Phường 6,Phường 7,Phường 8,Phường 9,Phường 10,Phường 11,Phường 12,Phường 13,Phường 14,Phường 15,Phường 16"
						.split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			} else if (JcmpQuan.getSelectedItem().equals("Quận 12")) {
				JcmpPhuong.removeAllItems();
				String[] item = "Phường An Phú Đông,Phường Đông Hưng Thuận,Phường Hiệp Thành,Phường Tân Chánh Hiệp,Phường Tân Hưng Thuận,Phường Tân Thới Hiệp,Phường Tân Thới Nhất,Phường Thạnh Lộc,Phường Thạnh Xuân,Phường Thới An,Phường Trung Mỹ Tây"
						.split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			}

			else if (JcmpQuan.getSelectedItem().equals("Quận Bình Tân")) {
				JcmpPhuong.removeAllItems();
				String[] item = "Phường An Lạc,Phường An Lạc A,Phường Bình Hưng Hòa,Phường Bình Hưng Hoà A,Phường Bình Hưng Hoà B,Phường Bình Trị Đông,Phường Bình Trị Đông A,Phường Bình Trị Đông B,Phường Tân Tạo,Phường Tân Tạo A"
						.split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			}

			else if (JcmpQuan.getSelectedItem().equals("Quận Bình Thạnh")) {
				JcmpPhuong.removeAllItems();
				String[] item = "Phường 1,Phường 3,Phường 4,Phường 5,Phường 6,Phường 7,Phường 8,Phường 9,Phường 10,Phường 11,Phường 12,Phường 13,Phường 14,Phường 15,Phường 16,Phường 17,Phường 19,Phường 21,Phường 22,Phường 24,Phường 25,Phường 26,Phường 27,Phường 28"
						.split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			} else if (JcmpQuan.getSelectedItem().equals("Quận Phú Nhuận")) {
				JcmpPhuong.removeAllItems();
				String[] item = "Phường 1,Phường 3,Phường 4,Phường 5,Phường 7,Phường 8,Phường 9,Phường 10,Phường 11,Phường 12,Phường 13,Phường 14,Phường 15,Phường 17"
						.split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			} else if (JcmpQuan.getSelectedItem().equals("Quận Tân Bình")) {
				JcmpPhuong.removeAllItems();
				String[] item = "Phường 1,Phường 3,Phường 4,Phường 5,Phường 6,Phường 7,Phường 8,Phường 9,Phường 10,Phường 11,Phường 12,Phường 13,Phường 14,Phường 15"
						.split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			} else if (JcmpQuan.getSelectedItem().equals("Quận Tân Phú")) {
				JcmpPhuong.removeAllItems();
				String[] item = "Phường Hiệp Tân,Phường Hoà Thạnh,Phường Phú Thạnh,Phường Phú Thọ Hoà,Phường Phú Trung,Phường Sơn Kỳ,Phường Tân Qúy,Phường Tân Sơn Nhì,Phường Tân Thành,Phường Tân Thới Hoà,Phường Tây Thạnh"
						.split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			} else if (JcmpQuan.getSelectedItem().equals("Quận Thủ Đức")) {
				JcmpPhuong.removeAllItems();
				String[] item = "Phường Bình Chiểu,Phường Bình Thọ,Phường Hiệp Bình Chánh,Phường Hiệp Bình Phước,Phường Linh Chiểu,Phường Linh Đông,Phường Linh Tây,Phường Linh Trung,Phường Linh Xuân,Phường Tam Bình,Phường Tam Phú,Phường Trường Thọ"
						.split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			}
			if (JcmpQuan.getSelectedItem().equals("Quận Bình Chánh")) {
				JcmpPhuong.removeAllItems();
				String[] item = "Thị trấn Tân Túc,Xã An Phú Tây,Xã Bình Chánh,Xã Bình Hưng,Xã Bình Lợi,Xã Đa Phước,Xã Hưng Long,Xã Lê Minh Xuân,Xã Phạm Văn Hai,Xã Phong Phú,Xã Quy Đức,Xã Tân Kiên,Xã Tân Nhựt,Xã Tân Quý Tây,Xã Vĩnh Lộc A,Xã Vĩnh Lộc B"
						.split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			}
			if (JcmpQuan.getSelectedItem().equals("Quận Cần Giờ")) {
				JcmpPhuong.removeAllItems();
				String[] item = "Thị trấn Cần Thạnh,Xã An Thới Đông,Xã Bình Khánh,Xã Long Hòa,Xã Lý Nhơn,Xã Tam Thôn Hiệp,Xã Thạnh An"
						.split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			} else if (JcmpQuan.getSelectedItem().equals("Quận Củ Chi")) {
				JcmpPhuong.removeAllItems();
				String[] item = "Thị trấn Củ Chi,Xã An Nhơn Tây,Xã An Phú,Xã Bình Mỹ,Xã Hòa Phú,Xã Nhuận Đức,Xã Phạm Văn Cội,Xã Phú Hòa Đông,Xã Phú Mỹ Hưng,Xã Phước Hiệp,Xã Phước Thạnh,Xã Phước Vĩnh An,Xã Tân An Hội,Xã Tân Phú Trung,Xã Tân Thạnh Đông,Xã Tân Thạnh Tây,Xã Tân Thông Hội,Xã Thái Mỹ,Xã Trung An,Xã Trung Lập Hạ,Xã Trung Lập Thượng"
						.split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			} else if (JcmpQuan.getSelectedItem().equals("Quận Hóc Môn")) {
				JcmpPhuong.removeAllItems();
				String[] item = "Thị trấn Hóc Môn,Xã Bà Điểm,Xã Đông Thạnh,Xã Nhị Bình,Xã Tân Hiệp,Xã Tân Thới Nhì,Xã Tân Xuân,Xã Thới Tam Thôn,Xã Trung Chánh,Xã Xuân Thới Đông,Xã Xuân Thới Sơn,Xã Xuân Thới Thượng"
						.split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			}

			else if (JcmpQuan.getSelectedItem().equals("Quận Hóc Môn")) {
				JcmpPhuong.removeAllItems();
				String[] item = "Thị trấn Nhà Bè,Xã Hiệp Phước,Xã Long Thới,Xã Nhơn Đức,Xã Phú Xuân,Xã Phước Kiển,Xã Phước Lộc"
						.split(",");
				for (int i = 0; i < item.length; i++) {
					String string = item[i];
					JcmpPhuong.addItem(string);
				}
			}
		} else if (ob.equals(btnThem)) {
			
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
			
		} else if (ob.equals(btnXoaTrang)) {
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
	
			JcmpQuan.setSelectedItem("");
			JcmpPhuong.setSelectedItem("");
			txtSoNha.setText("");
			txtDuong.setText("");
			tableModel.setRowCount(0);
			addDatabase();
		}
		
	}

	private boolean validData() {
		String maNT = txtMaNhatro.getText().trim();
		String tenChuNha = txtChuNha.getText().trim();
		String dienThoai = txtSDT.getText().trim();
		// JOptionPane.showMessageDialog(this,
		// JcmpQuan.getSelectedItem().toString().length());

		if (JcmpQuan.getSelectedItem().toString().equals(" ")) {
			JOptionPane.showMessageDialog(this, "Bạn chưa nhập địa chỉ !!!");
			return false;
		}
		if (maNT.length() == 0) {
			JOptionPane.showMessageDialog(this, "Mã nhà trọ không được bỏ trống !!!");
			txtMaNhatro.requestFocus();
			return false;
		}

		if (!(maNT.matches("NT_[0-9]{5}"))) {
			JOptionPane.showMessageDialog(this, "Mã nhà nhập sai cấu trúc NT_xxxxx");
			txtMaNhatro.requestFocus();
			return false;
		}
		if (!(tenChuNha.length() > 0)) {
			JOptionPane.showMessageDialog(this, "Tên chủ nhà không được bỏ trống !!!");
			txtChuNha.requestFocus();
			return false;
		}
		if (!(tenChuNha.matches("[\\p{Lu}[A-Z]][\\p{L}[a-z]]*( [\\p{Lu}[A-Z]][\\p{L}[a-z]]*)*"))) {
			JOptionPane.showMessageDialog(this, "Họ tên nhâp sai");
			txtChuNha.requestFocus();
			return false;
		}

		if (!(dienThoai.length() > 0)) {
			JOptionPane.showMessageDialog(this, "Điện thoại nhân viên không được bỏ trống");
			txtSDT.requestFocus();
			return false;
		}
		if (!(dienThoai.matches(
				"(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})"))) {
			JOptionPane.showMessageDialog(this,
					"Nhập số điện thoại sai cấu trúc :" + "Viettel: 09, 03\r\n" + "MobiFone: 09, 07\r\n"
							+ "VinaPhone: 09, 08\r\n" + "Vietnamobile và Gmobile: 09, 05" + "Và Phải có 10 chữ số");
			txtSDT.requestFocus();
			return false;
		}

		return true;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}