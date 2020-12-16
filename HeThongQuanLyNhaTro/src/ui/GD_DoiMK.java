package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import connectDB.ConnectDB;
import dao.NhanVien_Dao;
import dao.TamLuuMaNhanVien_Dao;
import entity.NhanVien;

public class GD_DoiMK extends JFrame implements ActionListener {
	
	private JLabel lblMaNV;
	private JLabel lblMKHienTai;
	private JLabel lblMKMoi;
	private JLabel lblMKMoi_1;
	private JPasswordField txtMKHienTai;
	private JPasswordField txtMKMoi;
	private JPasswordField txtMKMoi_1;
	private JButton btnDoiMK;
	private JButton btnQuayLai;
	private JCheckBox btnXemMK;
	private TamLuuMaNhanVien_Dao tamluu_dao;
	private NhanVien_Dao nv_dao;
	
	public GD_DoiMK() {
		
		tamluu_dao = new TamLuuMaNhanVien_Dao();
		nv_dao = new NhanVien_Dao();
		
//		this.setLayout(new BorderLayout());
//		this.setPreferredSize(new Dimension(450, 250));
		
		JPanel pnl = new JPanel();
		pnl.setLayout(new BorderLayout());
		this.add(pnl, BorderLayout.CENTER);
		Box box = Box.createVerticalBox();
		
		Box bMaNV = Box.createHorizontalBox();
		box.add(Box.createVerticalStrut(20));

		box.add(bMaNV);
		JLabel lblMaNV_data = new JLabel();
		lblMaNV_data.setText(tamluu_dao.layNhanVienTrongBangTamLuu().getMaNV());
		lblMaNV_data.setForeground(Color.RED);
		
		bMaNV.add(lblMaNV = new JLabel("Mã nhân viên:  "));
		bMaNV.add(lblMaNV_data);
		box.add(Box.createVerticalStrut(20));
		
		Box bMKHienTai = Box.createHorizontalBox();
		box.add(bMKHienTai);
		bMKHienTai.add(lblMKHienTai = new JLabel("  Mật khẩu hiện tại: "));
		bMKHienTai.add(txtMKHienTai = new JPasswordField(20));
		box.add(Box.createVerticalStrut(20));
		
		Box bMKMoi = Box.createHorizontalBox();
		box.add(bMKMoi);
		bMKMoi.add(lblMKMoi = new JLabel("  Mật khẩu mới: "));
		bMKMoi.add(txtMKMoi = new JPasswordField(20));
		box.add(Box.createVerticalStrut(20));
		
		Box bMKMoi_1 = Box.createHorizontalBox();
		box.add(bMKMoi_1);
		bMKMoi_1.add(lblMKMoi_1 = new JLabel("  Xác nhận mật khẩu mới: "));
		bMKMoi_1.add(txtMKMoi_1 = new JPasswordField(20));
		box.add(Box.createVerticalStrut(10));
		
		box.add(btnXemMK = new JCheckBox("Hiển thị mật khẩu"));
		
		box.add(Box.createVerticalStrut(20));
		
		
		lblMKHienTai.setPreferredSize(lblMKMoi_1.getPreferredSize());
		lblMKMoi.setPreferredSize(lblMKMoi_1.getPreferredSize());
		
		Box bButton = Box.createHorizontalBox();
		box.add(bButton);
		bButton.add(btnDoiMK = new JButton("Xác nhận"));
		bButton.add(Box.createHorizontalStrut(50));
		bButton.add(btnQuayLai = new JButton("Quay lại"));
		btnQuayLai.setBackground(Color.RED);
		btnQuayLai.setForeground(Color.WHITE);
		bButton.add(Box.createHorizontalStrut(50));
		
		pnl.add(box, BorderLayout.CENTER);
	
		btnXemMK.addActionListener(this);
		btnDoiMK.addActionListener(this);
		btnQuayLai.addActionListener(this);
		
		nv_dao.loadNhanVienTuDatabase();

		setSize(500, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setTitle("Đổi mật khẩu");
		setResizable(false);
		setVisible(true);
		
		try {
			ConnectDB.getInstance().connect();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object ob = e.getSource();
		
		if(ob.equals(btnXemMK)) {
			if (btnXemMK.isSelected()) {
				txtMKHienTai.setEchoChar((char) 0);
				txtMKMoi.setEchoChar((char) 0);
				txtMKMoi_1.setEchoChar((char) 0);
			} else {
				txtMKHienTai.setEchoChar('*');
				txtMKMoi.setEchoChar('*');
				txtMKMoi_1.setEchoChar('*');
			}
		}
		else if(ob.equals(btnDoiMK)) {
			String matKhauMoi = txtMKMoi.getText().strip();
			String maNhanVien = tamluu_dao.layNhanVienTrongBangTamLuu().getMaNV();
			if(validMatKhau()) {
				nv_dao.doiMatKhau(maNhanVien, matKhauMoi);
				setVisible(false);
				JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công");
			}
		}
		else if(ob.equals(btnQuayLai)) {
			setVisible(false);
		}
		
	}
	
	public boolean validMatKhau() {
		String mkHienTai = txtMKHienTai.getText().strip();
		String mkMoi = txtMKMoi.getText().strip();
		String mkMoi_1 = txtMKMoi_1.getText().strip();
		
		if(!(mkHienTai.length() > 0 && mkMoi.length() > 0 && mkMoi_1.length() > 0)) {
			JOptionPane.showMessageDialog(this, "Các ô không được để trống!");
			return false;
		}
		if(!mkHienTai.equals(tamluu_dao.layNhanVienTrongBangTamLuu().getMatKhau())) {
			JOptionPane.showMessageDialog(this, "Mật khẩu hiện tại không đúng!");
			return false;
		}	
		if(!mkMoi.equals(mkMoi_1)) {
			JOptionPane.showMessageDialog(this, "Xác nhận mật khẩu không trùng khớp!");
			return false;
		}
		if(mkHienTai.equals(mkMoi)) {
			JOptionPane.showMessageDialog(this, "Mật khẩu mới trùng với mật khẩu hiện tại!");
			return false;
		}
		return true;
	}
	
}
