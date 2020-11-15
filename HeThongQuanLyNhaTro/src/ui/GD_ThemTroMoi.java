package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class GD_ThemTroMoi extends JPanel implements ActionListener{

	private JTextField txtSDT;
	private JTextField txtTPMaNhaTro;
	private JTextField txtTPTenChuNhaTro;
	private JButton btnThem;
	private JButton btnLuu;
	private JComboBox cboDuong;
	private JComboBox cboQuan;
	private JComboBox cboSoNha;
	private JComboBox cboPhuong;
	
	public GD_ThemTroMoi() {
		setAutoscrolls(true);
		setBorder(null);
		this.setPreferredSize(new Dimension(1400, 280));
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnlRight = new JPanel();
		pnlRight.setMinimumSize(new Dimension(1000, 10));
		pnlRight.setPreferredSize(new Dimension(100, 10));
		add(pnlRight, BorderLayout.CENTER);
		pnlRight.setLayout(new BorderLayout(0, 0));
		
		
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
		
		JPanel pnlTro = new JPanel();
		pnlThanhPhan.add(Box.createVerticalStrut(10));
		pnlThanhPhan.add(pnlTro);
		pnlTro.setLayout(new BoxLayout(pnlTro, BoxLayout.X_AXIS));
		
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
		btnLuu.addActionListener(this);
		btnthem.addActionListener(this);


	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if(ob.equals(btnThem)) {
			txtSDT.setText("");
			txtTPMaNhaTro.setText("");
			txtTPTenChuNhaTro.setText("");
			cboQuan.setEditor(null);
			cboPhuong.setEditor(null);
			cboSoNha.setEditor(null);
			cboDuong.setEditor(null);
		}
	}
}
