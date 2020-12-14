package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import dao.TamLuuMaNhanVien_Dao;
import dao.ThongKe_Dao;
import entity.NhaTro;

public class GD_ThongKe extends JPanel implements ActionListener {
	private TamLuuMaNhanVien_Dao tamluu_dao;
	
//Thanh điều hướng
	private JButton btnTro;
	private JButton btnSinhVien;
	private JButton btnThongKe;
	private JButton btnThueTro;
	private JButton btnNhanVien;
	private JButton btnHuongDanSD;
	private JButton btnThoat;
	
	
	//center
	private JLabel lblTieuDe;
	private JLabel lblThongTin;
	private JTable table;
	private DefaultTableModel tableModel;
	
	//các thuộc tính riêng
	private JButton btnThoat1;
	private JButton btnXoaTrang;
	private JButton btnTK_Action;
	private JLabel lblNhaTro;
	private JLabel lblSV;
	private JLabel lblThongTinTro;
	private JComboBox<String> cbChon;
	private JComboBox<String> cbSV;
	private JComboBox<String> cbNhaTro;
	private TamLuuMaNhanVien_Dao tamLuu_dao;
	private ThongKe_Dao tk_dao;
	
	public void setDataFont(JLabel lbl) {
		lbl.setForeground(Color.BLUE);
		lbl.setFont(new Font("Monospaced", Font.BOLD, 15)); 
	}
	
	public GD_ThongKe() {
		tamLuu_dao = new TamLuuMaNhanVien_Dao();
		tk_dao = new ThongKe_Dao();
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
				
			JPanel pnlThongKe = new JPanel();
			pnlThongKe.add(lblTieuDe = new JLabel("THỐNG KÊ"));
			bcen1.add(pnlThongKe);
			pnlThongKe.setBackground(Color.BLUE);
			lblTieuDe.setFont(new Font("Arial", Font.BOLD, 40));
			lblTieuDe.setForeground(Color.PINK);
			pnl.add(boxCen, BorderLayout.CENTER);
//			
		
		
			
			
			
		//Table
			boxCen.add(bcen2 = Box.createHorizontalBox());
			
			
			JPanel pnlcen2 = new JPanel();
				JScrollPane scroll;
				String[] header="Mã Nhân Viên;Tên Nhân Viên;Ngày Sinh;Khoa;Cấp Bậc".split(";");
				tableModel=new DefaultTableModel();
				table = new JTable(tableModel) {
					/**
					 * 
//					 */
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
				
				// South
				boxCen.add(bcen3 = Box.createVerticalBox());
				JPanel pnlcen3 = new JPanel();
				pnlcen3.setLayout(new BorderLayout());
				
				Box boxcen31, boxcen32;
				pnlcen3.add(boxcen31 = box.createVerticalBox(), BorderLayout.NORTH);

				bcen3.add(pnlcen3);
				JPanel pnlThongTin = new JPanel();
				pnlThongTin.add(lblThongTin = new JLabel("Thông tin thống kê"));
				boxcen31.add(pnlThongTin);
				lblThongTin.setFont(new Font("Arial", Font.BOLD, 30));
				pnlThongTin.setBackground(Color.LIGHT_GRAY);
				
				
			///////////////////
		
				
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
//				
				pnlForm.add(Box.createVerticalStrut(30));
				
				Box bTTTK = Box.createHorizontalBox();
				bTTTK.add(lblNhaTro = new JLabel("       Trọ:    "));
				bTTTK.add(cbNhaTro = new JComboBox<>());
				cbNhaTro.addItem(" ");
				cbNhaTro.addItem("Đang được thuê");
				cbNhaTro.addItem("Không có sinh viên thuê");
				cbNhaTro.addItem("Thống kê trọ theo địa chỉ");
				cbNhaTro.addItem("Lịch sử thuê trọ");
				pnlForm.add(Box.createHorizontalStrut(50));
				
				bTTTK.add(lblSV = new JLabel("                   Sinh viên:   "));
				bTTTK.add(cbSV = new JComboBox<>());
				cbSV.addItem(" ");
				cbSV.addItem("Đang thuê trọ");
				cbSV.addItem("Không thuê trọ");
				
				pnlForm.add(bTTTK);
				pnlForm.add(Box.createVerticalStrut(30));
				
//				Box bTTTK1 = Box.createHorizontalBox();
//				bTTTK1.add(lblThongTinTro = new JLabel("    Thông tin trọ:    "));
//				bTTTK1.add(cbThongTinTro = new JComboBox<>());
//				cbThongTinTro.addItem(" ");
				
				//pnlForm.add(bTTTK1);
				
				pnlForm.add(Box.createVerticalStrut(30));
				Box bButton = Box.createHorizontalBox();
				bButton.add(btnTK_Action = new JButton("Thống kê"));
				bButton.add(Box.createHorizontalStrut(50));
				bButton.add(btnXoaTrang = new JButton("Làm mới"));
				bButton.add(Box.createHorizontalStrut(50));
				bButton.add(btnThoat1 = new JButton("Thoát"));
				pnlForm.add(bButton);
				
//			// South
//			boxCen.add(bcen3 = Box.createVerticalBox());
//			JPanel pnlcen3 = new JPanel();
//			pnlcen3.setLayout(new BorderLayout());
//			
//			Box boxcen31, boxcen32;
//			pnlcen3.add(boxcen31 = box.createVerticalBox(), BorderLayout.NORTH);
//
//			bcen3.add(pnlcen3);
//	}
				btnHuongDanSD.addActionListener(this);
				btnNhanVien.addActionListener(this);
				btnSinhVien.addActionListener(this);
				btnThoat.addActionListener(this);
				btnThoat1.addActionListener(this);
				btnThongKe.addActionListener(this);
				btnThueTro.addActionListener(this);
				btnTro.addActionListener(this);
				btnXoaTrang.addActionListener(this);
				btnTK_Action.addActionListener(this);
				xoaTrangAction();
		setVisible(true); 		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object ob = e.getSource();
		
		if(ob.equals(btnThoat) || ob.equals(btnThoat1))
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
		else if(ob.equals(btnXoaTrang)) {
			xoaTrangAction();
		}
		else if(ob.equals(btnTK_Action)) {
			if(cbNhaTro.getSelectedItem().toString().strip().equals("Đang được thuê")) {
				
				String[] header = "Mã nhà trọ;Tên chủ nhà;Địa chỉ;Số điện thoại".split(";");
				tableModel = new DefaultTableModel(header, 0);
				table.setModel(tableModel);
				//tableModel.setRowCount(0);
				for(NhaTro v : tk_dao.thongKeTroDangThue_data()) {
					String diaChi = v.getDiaChiTro().getSoNha() + " - " + v.getDiaChiTro().getTenDuong() + " - "
							+ v.getDiaChiTro().getTenPhuong() + " - " + v.getDiaChiTro().getTenQuan();
					String[] row = { v.getMaTro(), v.getTenChutro(), diaChi, v.getSDT()};
					tableModel.addRow(row);
				}
			}
			else if(cbNhaTro.getSelectedItem().toString().strip().equals("Không có sinh viên thuê")) {
				String[] header = "Mã nhà trọ;Tên chủ nhà;Địa chỉ;Số điện thoại".split(";");
				tableModel = new DefaultTableModel(header, 0);
				table.setModel(tableModel);
				//tableModel.setRowCount(0);
				for(NhaTro v : tk_dao.thongKeTroChuaThueTro_data()) {
					String diaChi = v.getDiaChiTro().getSoNha() + " - " + v.getDiaChiTro().getTenDuong() + " - "
							+ v.getDiaChiTro().getTenPhuong() + " - " + v.getDiaChiTro().getTenQuan();
					String[] row = { v.getMaTro(), v.getTenChutro(), diaChi, v.getSDT()};
					tableModel.addRow(row);
				}
			}
		}
	}
	private void xoaTrangAction() {
		// TODO Auto-generated method stub
		cbNhaTro.setSelectedIndex(0);
		cbSV.setSelectedIndex(0);
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
}
