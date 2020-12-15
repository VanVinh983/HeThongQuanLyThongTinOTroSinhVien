package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteComboBoxEditor;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import connectDB.ConnectDB;
import dao.NhaTro_Dao;
import dao.SinhVien_Dao;
import dao.TamLuuMaNhanVien_Dao;
import dao.ThongTinThueTro_Dao;
import entity.SinhVien;
import resourse.SetSizeByPercent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.Component;
import javax.swing.Box;
import java.awt.GridLayout;

public class DiaLog_TimKiemSinhVien extends JFrame implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2188442716925616098L;
	private JPanel contentPane;
	private DefaultTableModel tableModelBTT;
	private JTable tableBTT;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JLabel lblNewLabel;
	private JComboBox cbotimkiem;
	private Component horizontalStrut;
	private JPanel panel_4;
	private JPanel panel_5;
	private JButton btntimkiem;
	private JButton btnthoat;
	private TamLuuMaNhanVien_Dao tamluu_dao;
	private SinhVien_Dao sinhvien_dao;
	private NhaTro_Dao nhatro_dao;
	private ThongTinThueTro_Dao thongtinthuetro_dao;
	private String loaiNV;
	private String khoa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DiaLog_TimKiemSinhVien frame = new DiaLog_TimKiemSinhVien();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DiaLog_TimKiemSinhVien() {
		tamluu_dao = new TamLuuMaNhanVien_Dao();
		sinhvien_dao = new SinhVien_Dao();
		nhatro_dao = new NhaTro_Dao();
		thongtinthuetro_dao = new ThongTinThueTro_Dao();
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		loaiNV = tamluu_dao.layNhanVienTrongBangTamLuu().getLoaiNV();
		khoa = tamluu_dao.layNhanVienTrongBangTamLuu().getTenKhoa();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		SetSizeByPercent setSizeByPercent = new SetSizeByPercent(screenSize);
		int screenHeight = screenSize.height - setSizeByPercent.getHeightByPercent(40);
		int screenWidth = screenSize.width - setSizeByPercent.getWidthByPercent(50);
		this.setSize(new Dimension(screenWidth, screenHeight));
		this.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		String[] header = "Mã sinh viên;Tên sinh viên;Ngày sinh;Quê quán;Mã lớp;Mã nhân viên;Giới tính;Khoa".split(";");
		tableModelBTT = new DefaultTableModel(header, 0);
		tableBTT = new JTable(tableModelBTT) {
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
		panel.add(new JScrollPane(tableBTT, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
		tableBTT.setRowHeight(30);

		panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));

		panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));

		lblNewLabel = new JLabel("Tìm kiếm sinh viên: ");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		panel_2.add(lblNewLabel, BorderLayout.WEST);

		cbotimkiem = new JComboBox();
		cbotimkiem.setFont(new Font("Arial", Font.PLAIN, 20));
		panel_2.add(cbotimkiem);

		horizontalStrut = Box.createHorizontalStrut(20);
		panel_2.add(horizontalStrut, BorderLayout.EAST);

		panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new GridLayout(1, 2));

		panel_4 = new JPanel();
		panel_4.setBorder(new EmptyBorder(9, 9, 9, 9));
		panel_3.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));

		btntimkiem = new JButton("Tìm kiếm\r\n");
		btntimkiem.setFont(new Font("Arial", Font.BOLD, 20));
		panel_4.add(btntimkiem);

		panel_5 = new JPanel();
		panel_5.setBorder(new EmptyBorder(9, 9, 9, 9));
		panel_3.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));

		btnthoat = new JButton("Thoát");
		btnthoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnthoat.setFont(new Font("Arial", Font.BOLD, 20));
		panel_5.add(btnthoat);
		addDatabase();

		List<SinhVien> listSV = new ArrayList<SinhVien>();

		if (sinhvien_dao.layLoaiNV().equals("QL")) {
			listSV = sinhvien_dao.layTatCaBangQL();
		} else if (sinhvien_dao.layLoaiNV().equals("NV")) {
			listSV = sinhvien_dao.layTatCaBangNV();
		} else {
			JOptionPane.showMessageDialog(null, "Lỗi loại NV!!");
		}
		
		DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		for (SinhVien sinhVien : listSV) {
			cbotimkiem.addItem(sinhVien.getMaSV());
			cbotimkiem.addItem(sinhVien.getTenSV());
			cbotimkiem.addItem(sinhVien.getChuyenNghanh());
			cbotimkiem.addItem(sinhVien.getGioiTinh());
			cbotimkiem.addItem(dt.format(sinhVien.getNgaySinh()));
			cbotimkiem.addItem(sinhVien.getQueQuanSV());
		}
		cbotimkiem.setEditable(true);
		cbotimkiem.setAutoscrolls(false);
		AutoCompleteDecorator.decorate(cbotimkiem);
	}

	public void addDatabase() {

		SinhVien_Dao dao = new SinhVien_Dao();
		List<SinhVien> listSV = new ArrayList<SinhVien>();

		if (dao.layLoaiNV().equals("QL")) {
			listSV = dao.layTatCaBangQL();
		} else if (dao.layLoaiNV().equals("NV")) {
			listSV = dao.layTatCaBangNV();
		} else {
			JOptionPane.showMessageDialog(null, "Lỗi loại NV!!");
		}
		// Đưa thông tin vào bảng
		listSV.forEach(v -> {
			DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/mm/yyyy");
			String ngaySinh = v.getNgaySinh().getDayOfMonth() + "/" + v.getNgaySinh().getMonthValue() + "/"
					+ v.getNgaySinh().getYear();
			String[] row = { v.getMaSV(), v.getTenSV(), ngaySinh, v.getQueQuanSV(), v.getMaLop(), v.getMaNV().getMaNV(),
					v.getGioiTinh(), v.getChuyenNghanh() };
			tableModelBTT.addRow(row);
		});
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btntimkiem)) {

		}
	}

}
