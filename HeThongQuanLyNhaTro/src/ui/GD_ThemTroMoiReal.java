package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class GD_ThemTroMoiReal extends JPanel{

	private JTextField txtSDT;
	private JTextField txtTPMaNhaTro;
	private JTextField txtTPTenChuNhaTro;
	private JButton btnThem;
	private JButton btnLuu;
	private JComboBox cboDuong;
	private JComboBox cboQuan;
	private JComboBox cboSoNha;
	private JComboBox cboPhuong;
	
	public GD_ThemTroMoiReal() {
		setAutoscrolls(true);
		setBorder(null);
		this.setPreferredSize(new Dimension(1600, 600));
		setLayout(new BorderLayout(0, 0));
		ImageIcon imgUser = new ImageIcon(new ImageIcon("HinhAnh/User.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		ImageIcon imgSV = new ImageIcon(new ImageIcon("HinhAnh/sinhvien.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon imgNV = new ImageIcon(new ImageIcon("HinhAnh/nhanvien.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon imgTK = new ImageIcon(new ImageIcon("HinhAnh/thongke.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon imgBTT = new ImageIcon(new ImageIcon("HinhAnh/ghichu1.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon imgTro = new ImageIcon(new ImageIcon("HinhAnh/nhatro.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon imgexit = new ImageIcon(new ImageIcon("HinhAnh/exit.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon imgHDSD = new ImageIcon(new ImageIcon("HinhAnh/User manual.jpg").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		
		JPanel pnlTrai = new JPanel();
		pnlTrai.setBackground(Color.CYAN);
		pnlTrai.setBorder(null);
		pnlTrai.setPreferredSize(new Dimension(200, 600));
		pnlTrai.setMinimumSize(new Dimension(300, 600));
		pnlTrai.setMaximumSize(new Dimension(300, 600));
		add(pnlTrai, BorderLayout.WEST);
		
		JPanel pnlUser = new JPanel();
		pnlUser.setPreferredSize(new Dimension(200, 200));
		pnlTrai.add(pnlUser);
		pnlUser.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlChucVu = new JPanel();
		pnlChucVu.setBackground(Color.CYAN);
		pnlUser.add(pnlChucVu, BorderLayout.NORTH);
		
		JLabel lblChucVu = new JLabel("Quản Lý\r\n");
		lblChucVu.setFont(new Font("Arial", Font.BOLD, 27));
		
//		if(loaiNV.equals("NV")) {
//			lblChucVu.setText("    Giáo Vụ Khoa: "+ khoa);
//			lblChucVu.setFont(new Font("Arial", 1, 15));
//		}
		
		pnlChucVu.add(lblChucVu);
		
		JLabel lblAnhUser = new JLabel("");
		lblAnhUser.setPreferredSize(new Dimension(200, 200));
		pnlUser.add(Box.createHorizontalStrut(25),BorderLayout.WEST);
		pnlUser.add(lblAnhUser, BorderLayout.CENTER);
		lblAnhUser.setIcon(imgUser);
		
		JPanel pnlTacVu = new JPanel();
		pnlTacVu.setFont(new Font("Arial", Font.BOLD, 20));
		pnlTacVu.setPreferredSize(new Dimension(200, 400));
		pnlTrai.add(pnlTacVu);
		pnlTacVu.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel pnlSinhVien = new JPanel();
		pnlSinhVien.setPreferredSize(new Dimension(200, 50));
		pnlTacVu.add(pnlSinhVien);
		
		JButton btnSinhVien = new JButton("Sinh Viên\r\n");
		btnSinhVien.setBorder(null);
		btnSinhVien.setBackground(Color.CYAN);
		btnSinhVien.setPreferredSize(new Dimension(170, 45));
		btnSinhVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(new GD_QuanLySinhVien());
				repaint();
				revalidate();
			}
		});
		btnSinhVien.setFont(new Font("Arial", Font.BOLD, 16));
		btnSinhVien.setIcon(imgSV);
		pnlSinhVien.add(btnSinhVien);
		
		JPanel pnlNhanVien = new JPanel();
		pnlNhanVien.setPreferredSize(new Dimension(200, 50));
		
		
//		if(loaiNV.equals("QL")) {
//			pnlTacVu.add(pnlNhanVien);
//		}
		JButton btnNhanVien = new JButton("Nhân Viên\r\n");
		btnNhanVien.setBorder(null);
		btnNhanVien.setBackground(Color.CYAN);
		btnNhanVien.setPreferredSize(new Dimension(170, 45));
		btnNhanVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(new GD_QLNhanVien());
				repaint();
				revalidate();
			}
		});
		btnNhanVien.setFont(new Font("Arial", Font.BOLD, 16));
		btnNhanVien.setIcon(imgNV);
		pnlNhanVien.add(btnNhanVien);
		
		JPanel pnlThongKe = new JPanel();
		pnlThongKe.setPreferredSize(new Dimension(200, 50));
		pnlTacVu.add(pnlThongKe);
		
		JButton btnTK = new JButton("Thống Kê\r\n");
		btnTK.setBorder(null);
		btnTK.setBackground(Color.CYAN);
		btnTK.setPreferredSize(new Dimension(170, 45));
		btnTK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTK.setFont(new Font("Arial", Font.BOLD, 16));
		btnTK.setIcon(imgTK);
		pnlThongKe.add(btnTK);
		
		JPanel pnlBTT = new JPanel();
		pnlBTT.setPreferredSize(new Dimension(200, 50));
		pnlTacVu.add(pnlBTT);
		
		JButton btnBTT = new JButton("Thuê Trọ"+ "\r\n");
		btnBTT.setBorder(null);
		btnBTT.setBackground(Color.CYAN);
		btnBTT.setPreferredSize(new Dimension(170, 45));
		btnBTT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(new GD_ThongTinThueTro());
				repaint();
				revalidate();
			}
		});
		btnBTT.setPreferredSize(btnNhanVien.getPreferredSize());
		btnBTT.setFont(new Font("Arial", Font.BOLD, 16));
		btnBTT.setIcon(imgBTT);
		pnlBTT.add(btnBTT);
		

		JPanel pnlTro = new JPanel();
		pnlTro.setPreferredSize(new Dimension(200, 50));
		pnlTacVu.add(pnlTro);
		
		JButton btnTro = new JButton("Nhà Trọ\r\n");
		btnTro.setBorder(null);
		btnTro.setBackground(Color.CYAN);
		btnTro.setPreferredSize(new Dimension(170, 45));
		btnTro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(new GD_QuanLyTro());
				repaint();
				revalidate();
			}
		});
		btnTro.setFont(new Font("Arial", Font.BOLD, 16));
		btnTro.setIcon(imgTro);
		pnlTro.add(btnTro);
		
		JPanel pnlHDSD = new JPanel();
		pnlHDSD.setPreferredSize(new Dimension(200, 50));
		pnlTacVu.add(pnlHDSD);
		
		JButton btnHDSD = new JButton("Hướng dẫn\r\n");
		btnHDSD.setBorder(null);
		btnHDSD.setBackground(Color.CYAN);
		btnHDSD.setPreferredSize(new Dimension(170, 45));
		btnHDSD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(new GD_HDSD());
				repaint();
				revalidate();
			}
		});
		btnHDSD.setFont(new Font("Arial", Font.BOLD, 16));
		btnHDSD.setIcon(imgHDSD);
		pnlHDSD.add(btnHDSD);
		
		JPanel pnlThoat = new JPanel();
		pnlThoat.setPreferredSize(new Dimension(200, 50));
		pnlTacVu.add(pnlThoat);
		
		JButton btnThoat = new JButton("Thoát\r\n");
		btnThoat.setBorder(null);
		btnThoat.setBackground(Color.CYAN);
		btnThoat.setPreferredSize(new Dimension(170, 45));
//		btnThoat.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			String loaiNV = tamluu_dao.layNhanVienTrongBangTamLuu().getLoaiNV();
//				if(loaiNV.equals("QL")) {
//					removeAll();
//					add(new GD_Admin());
//					repaint();
//					revalidate();
//				}
//				else if (loaiNV.equals("NV")) {
//					removeAll();
//					add(new GD_TrangChuNhanVienGVK());
//					repaint();
//					revalidate();
//				}
//			}
//		});
		btnThoat.setFont(new Font("Arial", Font.BOLD, 16));
		btnThoat.setIcon(imgexit);
		pnlThoat.add(btnThoat);
		
		JPanel pnlRight = new JPanel();
		pnlRight.setMinimumSize(new Dimension(1000, 10));
		pnlRight.setPreferredSize(new Dimension(100, 10));
		add(pnlRight, BorderLayout.CENTER);
		pnlRight.setLayout(new BorderLayout(0, 0));
		
		
		JPanel pnlTieuDe = new JPanel();
		pnlTieuDe.setBackground(Color.CYAN);
		pnlRight.add(pnlTieuDe, BorderLayout.NORTH);
		
		JLabel lblTieuDe = new JLabel("THÊM TRỌ MỚI\r\n");
		lblTieuDe.setBackground(Color.CYAN);
		lblTieuDe.setForeground(Color.BLUE);
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 30));
		pnlTieuDe.add(lblTieuDe);
		
		JPanel pnlNhapThongTin = new JPanel();
		pnlNhapThongTin.setBackground(Color.CYAN);
		pnlNhapThongTin.setPreferredSize(new Dimension(1400, 280));
		pnlRight.add(pnlNhapThongTin, BorderLayout.EAST);
		pnlNhapThongTin.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlNhapThongTinCoBan = new JPanel();
		pnlNhapThongTin.add(pnlNhapThongTinCoBan, BorderLayout.CENTER);
		pnlNhapThongTinCoBan.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlTieuDeNhapThongTin = new JPanel();
		pnlTieuDeNhapThongTin.setForeground(Color.YELLOW);
		pnlTieuDeNhapThongTin.setPreferredSize(new Dimension(10, 50));
		pnlTieuDeNhapThongTin.setBackground(Color.GRAY);
		pnlNhapThongTinCoBan.add(pnlTieuDeNhapThongTin, BorderLayout.NORTH);
		
		
		JPanel pnlKhoangTrongTrai = new JPanel();
		pnlKhoangTrongTrai.setBackground(Color.GRAY);
		pnlKhoangTrongTrai.setPreferredSize(new Dimension(50, 10));
		pnlNhapThongTinCoBan.add(pnlKhoangTrongTrai, BorderLayout.WEST);
		
		JLabel lblNhapThongTin = new JLabel("Thêm Trọ Mới");
		lblNhapThongTin.setForeground(Color.WHITE);
		lblNhapThongTin.setFont(new Font("Arial", Font.BOLD, 27));
		pnlTieuDeNhapThongTin.add(lblNhapThongTin);
		
		JPanel pnlThanhPhan = new JPanel();
		pnlNhapThongTinCoBan.add(pnlThanhPhan, BorderLayout.CENTER);
		pnlThanhPhan.setLayout(new BoxLayout(pnlThanhPhan, BoxLayout.Y_AXIS));
		
		JPanel pnlTPNhaTro = new JPanel();
		pnlThanhPhan.add(Box.createVerticalStrut(10));
		pnlThanhPhan.add(pnlTPNhaTro);
		pnlTPNhaTro.setLayout(new BoxLayout(pnlTPNhaTro, BoxLayout.X_AXIS));
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		pnlTPNhaTro.add(horizontalStrut_2);
		
		JLabel lblTPMaNhaTro = new JLabel("Mã Nhà Trọ: ");
		lblTPMaNhaTro.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTPNhaTro.add(lblTPMaNhaTro);
		
		txtTPMaNhaTro = new JTextField();
		txtTPMaNhaTro.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTPNhaTro.add(txtTPMaNhaTro);
		txtTPMaNhaTro.setColumns(10);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalStrut_3.setPreferredSize(new Dimension(30, 0));
		pnlTPNhaTro.add(horizontalStrut_3);
		
		JLabel lblTenChuNha = new JLabel("Tên Chủ Nhà: ");
		lblTenChuNha.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTPNhaTro.add(lblTenChuNha);
		
		txtTPTenChuNhaTro = new JTextField();
		txtTPTenChuNhaTro.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTPNhaTro.add(txtTPTenChuNhaTro);
		txtTPTenChuNhaTro.setColumns(10);
		
		Component horizontalStrut_9 = Box.createHorizontalStrut(20);
		pnlTPNhaTro.add(horizontalStrut_9);
		
		JPanel pnlTro1 = new JPanel();
		pnlThanhPhan.add(Box.createVerticalStrut(10));
		pnlThanhPhan.add(pnlTro1);
		pnlTro1.setLayout(new BoxLayout(pnlTro1, BoxLayout.X_AXIS));
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		pnlTro.add(horizontalStrut_1);
		
		JLabel lblSDT = new JLabel("SDT Chủ Nhà: ");
		lblSDT.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTro.add(lblSDT);
		
		txtSDT = new JTextField();
		txtSDT.setPreferredSize(new Dimension(20, 20));
		txtSDT.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTro.add(txtSDT);
		txtSDT.setColumns(10);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setPreferredSize(new Dimension(30, 0));
		pnlTro.add(horizontalStrut);
		
		
		
		Component horizontalStrut_10 = Box.createHorizontalStrut(20);
		pnlTro.add(horizontalStrut_10);
		
		JPanel pnlTPDiaChiTro = new JPanel();
		pnlThanhPhan.add(Box.createVerticalStrut(10));
		pnlThanhPhan.add(pnlTPDiaChiTro);
		pnlTPDiaChiTro.setLayout(new BoxLayout(pnlTPDiaChiTro, BoxLayout.X_AXIS));
		
		Component horizontalStrut_7 = Box.createHorizontalStrut(20);
		pnlTPDiaChiTro.add(horizontalStrut_7);
		
		JLabel lblTPDiaChiTro = new JLabel("Địa Chỉ: ");
		lblTPDiaChiTro.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTPDiaChiTro.add(lblTPDiaChiTro);
		
		JLabel lblQuan = new JLabel("Quận:\r\n \r\n");
		lblQuan.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTPDiaChiTro.add(lblQuan);
		
		JComboBox cboQuan = new JComboBox();
		cboQuan.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTPDiaChiTro.add(cboQuan);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		pnlTPDiaChiTro.add(horizontalStrut_4);
		
		JLabel lblNewLabel = new JLabel("Phường: \r\n");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTPDiaChiTro.add(lblNewLabel);
		
		JComboBox cboPhuong = new JComboBox();
		cboPhuong.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTPDiaChiTro.add(cboPhuong);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		pnlTPDiaChiTro.add(horizontalStrut_5);
		
		JLabel lblDuong = new JLabel("Đường: ");
		lblDuong.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTPDiaChiTro.add(lblDuong);
		
		JComboBox cboDuong = new JComboBox();
		cboDuong.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTPDiaChiTro.add(cboDuong);
		

		JLabel lblSoNha = new JLabel("Số Nhà: ");
		lblSoNha.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTPDiaChiTro.add(lblSoNha);
		
		JComboBox cboSoNha = new JComboBox();
		cboSoNha.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlTPDiaChiTro.add(cboSoNha);
		
		JPanel Button = new JPanel();
		pnlThanhPhan.add(Box.createVerticalStrut(10));
		pnlThanhPhan.add(Button);
		Button.setLayout(new BoxLayout(Button, BoxLayout.X_AXIS));
		Component horizontalStrut_8 = Box.createHorizontalStrut(20);
		Button.add(horizontalStrut_8);
		
		JButton btnthem = new JButton("Thêm");
		Button.add(btnthem);
		
		JButton btnLuu = new JButton("Lưu");
		Button.add(btnLuu);
		
		lblTPMaNhaTro.setPreferredSize(lblSDT.getPreferredSize());
		
		
	}
}
