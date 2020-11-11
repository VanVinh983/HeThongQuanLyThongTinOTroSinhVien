package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import entity.NhanVien;

public class GD_ThemTroMoi extends JFrame implements ActionListener, MouseListener{

	private JLabel lblTitle,lblMaTro,lblDiaChi,lblTenChuTro,lblSDT;
	private JTextField txtDiaChi, txtTenChuTro,txtSDT, txtMaTro;
	private JButton  btnLuu,btnThoat,btnThem, btnXoaTrang;
	

	public GD_ThemTroMoi(){
		
		setBackground(Color.MAGENTA);
		JPanel pNorth = new JPanel();
		pNorth.add(lblTitle = new JLabel("THÊM TRỌ MỚI"));
		lblTitle.setForeground(Color.black);
		Font font = new Font("Arias", Font.BOLD, 30);
		lblTitle.setFont(font);
		add(pNorth, BorderLayout.NORTH);
		
		Box b= Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		Box b4 = Box.createHorizontalBox();
		
		
		b.add(b1);
		b.add(Box.createVerticalStrut(10));
		b.add(b2);
		b.add(Box.createVerticalStrut(10));
		b.add(b3);
		b.add(Box.createVerticalStrut(10));
		b.add(b4);
		b.add(Box.createVerticalStrut(10)); 
		b.add(Box.createVerticalStrut(10));
		add(b,BorderLayout.CENTER);
		
		
		b1.add(lblMaTro = new JLabel("Mã Trọ:"));
		b1.add(txtMaTro = new  JTextField(10));
		
		b2.add(lblDiaChi = new JLabel("Địa Chỉ:"));
		b2.add(txtDiaChi = new JTextField(10));
		
		b3.add(lblTenChuTro = new JLabel("Tên Chủ Trọ:"));
		b3.add(txtTenChuTro = new JTextField(10));
		
		
		b4.add(lblSDT = new JLabel("Số Điện Thoại: "));
		b4.add(txtSDT = new JTextField(10));
		
		
		lblMaTro.setPreferredSize(lblSDT.getPreferredSize());
		lblDiaChi.setPreferredSize(lblSDT.getPreferredSize());
		lblTenChuTro.setPreferredSize(lblSDT.getPreferredSize());
	
		
		 
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
			add(split, BorderLayout.SOUTH);
			JPanel pEast;	
			
			split.add(pEast = new JPanel());
			pEast.add(btnThem = new JButton("Thêm"));
			pEast.add(btnLuu = new JButton("Lưu"));
			pEast.add(btnThoat = new JButton("Thoát"));
			pEast.add(btnXoaTrang = new JButton("Xóa Trắng"));
			setSize(1000, 350);
			setVisible(true);
			
			setDefaultCloseOperation(HIDE_ON_CLOSE);
			
			btnThem.addActionListener(this);
		
			btnXoaTrang.addActionListener(this);
			btnLuu.addActionListener(this);
			
			setLocationRelativeTo(null);
			setResizable(false);
			
			
			
	}
	
	private void xoaTrangAction() {
		// TODO Auto-generated method stub
		
		
		txtMaTro.setText("");
		txtDiaChi.setText("");
		txtSDT.setText("");
		txtTenChuTro.setText("");
		
	}

//	private void themAction() {
//		// TODO Auto-generated method stub
//		try {
//			
//			
//			String maTro= txtMaTro.getText();
//			String tenChuTro = txtTenChuTro.getText();
//			String diaChi = txtDiaChi.getText();
//			String SDT = txtSDT.getText();
//			
//			NhanVien nhanVien =  new NhanVien(maNhanVien, maPhongBan, tenNhanVien, LocalDate.of(namSinh, thangSinh, ngaySinh), gioiTinh, noiSinh, diaChi, email, soDienThoai);
//			
//			if(dao.themNhanVien(nhanVien)) {
//				String []row = {maNhanVien, tenNhanVien, nhanVien.ngaySinhToString(),nhanVien.gioiTinhToString(), noiSinh, diaChi, email, soDienThoai,tenPhongBan, ""};
//				tableModel.addRow(row);
//				xoaTrangAction();
//				JOptionPane.showMessageDialog(this, "Thêm thành công!");
//			}
//			else {
//				JOptionPane.showMessageDialog(this, "Thêm thất bại!");
//				txtMaNhanVien.selectAll();
//				txtMaNhanVien.requestFocus();
//			}
//		}catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			JOptionPane.showMessageDialog(this, "Lỗi nhập liệu!");
//		}
//	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
}
