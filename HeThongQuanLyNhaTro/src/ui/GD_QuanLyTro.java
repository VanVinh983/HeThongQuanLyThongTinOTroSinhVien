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

public class GD_QuanLyTro extends JPanel implements ActionListener{

	private JTextField txtMaNhatro;
	private JTextField txtChuNha;
	private JTextField txtDiaChi;
	private JTextField txtSDT;
	private JTextField txtTim;
	private JLabel lblMaNhaTro;
	private JLabel lblChuNha;
	private JLabel lblDiaChi;
	private JLabel lblSDT;
	private JLabel lblTim;
	private JTextField txtUser;
	private JButton btnTim;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnXoaTrang;
	private JLabel lblUser;
	private JLabel lblcen1;
	private DefaultTableModel tableModel;
	private JTable table;
	private JLabel lblThongTin;
	private JButton btnTro;
	private JButton btnSinhVien;
	private JButton btnThongKe;
	private JButton btnThueTro;
	private JButton btnNhanVien;
	private JButton btnHuongDanSD;
	private JButton btnThoat;
	
	private JComboBox<String> cmp;
	public GD_QuanLyTro() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(1200, 600));
		
		JPanel pnl = new JPanel();
		pnl.setLayout(new BorderLayout());
		Box box = Box.createVerticalBox();
		Box b1, b2;
		box.add(b1 = Box.createHorizontalBox());
		
			JPanel pnluser1 = new JPanel();
				pnluser1.setBackground(Color.GREEN);
				Box buser = Box.createVerticalBox();
					Box buser1, buser2, buser3;
					buser.add(buser1 = Box.createHorizontalBox());
						buser1.add(lblUser = new JLabel("User: "));
						lblUser.setFont(new Font("Arial", Font.BOLD, 35));
						buser1.add(txtUser = new JTextField(20));
						buser1.add(Box.createVerticalStrut(20));
						txtUser.setEnabled(false);
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
			pnluser22.setBackground(Color.MAGENTA);
			pnluser2.add(pnluser22, BorderLayout.CENTER);
			pnluser22.setLayout(new BoxLayout(pnluser22, BoxLayout.Y_AXIS));
				box.add(pnluser2);
				
				
				pnluser22.add(Box.createVerticalStrut(20));
				pnluser22.add(btnTro = new JButton("Quan Ly Tro"));
				
				btnTro.add(Box.createHorizontalStrut(30));
				btnTro.add(Box.createVerticalStrut(20));
				pnluser22.add(Box.createVerticalStrut(10));
				pnluser22.add(btnSinhVien = new JButton("Quan Sinh Vien"));
				
				btnSinhVien.add(Box.createHorizontalStrut(30));
				btnSinhVien.add(Box.createVerticalStrut(20));
				pnluser22.add(Box.createVerticalStrut(10));
				pnluser22.add(btnNhanVien = new JButton("Quan Ly Nhan Vien"));
				
				btnNhanVien.add(Box.createHorizontalStrut(30));
				btnNhanVien.add(Box.createVerticalStrut(20));
				pnluser22.add(Box.createVerticalStrut(10));
				pnluser22.add(btnThueTro = new JButton("Quan Ly Thue tro"));
				
				btnThueTro.add(Box.createHorizontalStrut(30));
				btnThueTro.add(Box.createVerticalStrut(20));
				pnluser22.add(Box.createVerticalStrut(10));
				pnluser22.add(btnThongKe = new JButton("Thong Ke"));
				
				btnThongKe.add(Box.createHorizontalStrut(30));
				btnThongKe.add(Box.createVerticalStrut(20));
				pnluser22.add(Box.createVerticalStrut(10));
				pnluser22.add(btnHuongDanSD = new JButton("Huong Dan Su Dung"));
				
				btnHuongDanSD.add(Box.createHorizontalStrut(30));
				btnHuongDanSD.add(Box.createVerticalStrut(20));
				pnluser22.add(Box.createVerticalStrut(10));
				pnluser22.add(btnThoat = new JButton("Thoat"));
				btnThoat.add(Box.createHorizontalStrut(30));
				btnThoat.add(Box.createVerticalStrut(20));
		
		//Center
		JPanel pnlCen = new JPanel();
		Box boxCen = Box.createVerticalBox();
		
		Box bcen1, bcen2, bcen3;
		boxCen.add(bcen1 = Box.createHorizontalBox());
		
		JPanel pnlQuanLyTro = new JPanel();
		pnlQuanLyTro.add(lblcen1 = new JLabel("Quản lý nhà trọ"));
		bcen1.add(pnlQuanLyTro);
		pnlQuanLyTro.setBackground(Color.blue);
		lblcen1.setFont(new Font("Arial", Font.BOLD, 40));
		lblcen1.setForeground(Color.YELLOW);
	//Table
		boxCen.add(bcen2 = Box.createHorizontalBox());
		
		
		JPanel pnlcen2 = new JPanel();
			JScrollPane scroll;
			String[] header="Mã nhà trọ;Tên chủ nhà;Địa chỉ;Số điện thoại".split(";");
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
		Box boxMaTro = Box.createHorizontalBox();
		boxMaTro.add(lblMaNhaTro=new JLabel("Mã Tro:"));
		boxMaTro.add(txtMaNhatro=new JTextField());
		pnlForm.add(boxMaTro);
		
		pnlForm.add(Box.createVerticalStrut(10));
		Box boxDiaChi = Box.createHorizontalBox();
		boxDiaChi.add(lblDiaChi=new JLabel("Địa chỉ: "));
		boxDiaChi.add(txtDiaChi=new JTextField());
		pnlForm.add(boxDiaChi);
		
		pnlForm.add(Box.createVerticalStrut(10));
		Box boxChuNha = Box.createHorizontalBox();
		boxChuNha.add(lblChuNha=new JLabel("Tên chu tro"));
		boxChuNha.add(txtChuNha=new JTextField());
		pnlForm.add(boxChuNha);
		
		pnlForm.add(Box.createVerticalStrut(10));
		Box boxSDT = Box.createHorizontalBox();
		boxSDT.add(lblSDT=new JLabel("Số điện thoại"));
		boxSDT.add(txtSDT=new JTextField());
		pnlForm.add(boxSDT);
		
		pnlForm.add(Box.createVerticalStrut(10));
		Box boxButton = Box.createHorizontalBox();
		boxButton.add(btnThem =new JButton("Them"));
		boxButton.add(Box.createHorizontalStrut(20));
		boxButton.add(btnXoa =new JButton("Xoa"));
		boxButton.add(Box.createHorizontalStrut(20));
		boxButton.add(btnSua =new JButton("Sua"));
		boxButton.add(Box.createHorizontalStrut(20));
		boxButton.add(btnXoaTrang =new JButton("Xoa Trang"));
		pnlForm.add(boxButton);
		pnlForm.add(Box.createVerticalStrut(20));
		
		
		
		
		 pnlForm.setBorder(BorderFactory.createRaisedBevelBorder());
		 
		 JPanel pnlSouthRight = new JPanel();
		 pnlSouthRight.setLayout(new BoxLayout(pnlSouthRight, BoxLayout.Y_AXIS));
		 pnlSouth.add(pnlSouthRight, BorderLayout.EAST);
		 pnlSouthRight.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		 
		 
		 pnlSouthRight.add(Box.createVerticalStrut(30));
		 Box boxtxtTim = Box.createHorizontalBox();
		 boxtxtTim.add(lblTim=new JLabel("Tim"));
		 boxtxtTim.add(txtTim=new JTextField(20));
		 pnlSouthRight.add(boxtxtTim);
		 
		 pnlSouthRight.add(Box.createVerticalStrut(30));
		 Box boxcmpTim = Box.createHorizontalBox();
		 String[] s = "ma;Ten;diachi,sdt".split(";");
		 boxcmpTim.add(cmp = new JComboBox<String>(s));
		 pnlSouthRight.add(boxcmpTim);
		 
		 pnlSouthRight.add(Box.createVerticalStrut(30));
		 Box boxbtnTim = Box.createHorizontalBox();
		 boxbtnTim.add(btnTim = new JButton("Tim"));
		 btnTim.add(Box.createHorizontalStrut(50));
		 btnTim.add(Box.createVerticalStrut(50));
		 btnTim.setBackground(Color.green);
		 pnlSouthRight.setBackground(Color.CYAN);
		 pnlSouthRight.add(boxbtnTim);
		 
		lblMaNhaTro.setPreferredSize(lblSDT.getPreferredSize());
		lblChuNha.setPreferredSize(lblSDT.getPreferredSize());
		lblDiaChi.setPreferredSize(lblSDT.getPreferredSize());
		lblTim.setPreferredSize(lblSDT.getPreferredSize());
	
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
		
		
		
		
				/////////////////////////////////////////////////
		
		//pnlcen3.add(Box.createHorizontalStrut(400), BorderLayout.EAST);

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
			txtDiaChi.setText("");
			txtMaNhatro.setText("");
			txtSDT.setText("");
			txtTim.setText("");
			txtUser.setText("");
			txtTim.setText("");
		}
	}
}
