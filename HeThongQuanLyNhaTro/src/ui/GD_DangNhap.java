package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GD_DangNhap extends JPanel implements ActionListener{



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel lbltitle;

	private JLabel lblImgUser;
	private JButton btnDN;
	private JButton btnXemMK;
	private JTextField txtMaDN;
	private JTextField txtMK;
	public GD_DangNhap() {
		this.setPreferredSize(new Dimension(1200, 600));
		
		ImageIcon img_background = new ImageIcon(new ImageIcon("HinhAnh/background.jpg").getImage().getScaledInstance(1200, 600, Image.SCALE_SMOOTH));
		JLabel lblbackground = new JLabel(img_background);
		lblbackground.setPreferredSize(new Dimension(1200, 600));
	    lblbackground.setLayout(new BorderLayout());

		JPanel pnlDangNhap = new JPanel();
		JPanel pnlLeft = new JPanel();
		JPanel pnlRight = new JPanel();
		JPanel pnlTieuDe = new JPanel();
		JPanel pnlMaDN = new JPanel();
		JPanel pnlMK = new JPanel();
		JPanel pnlDN = new JPanel();
		
		pnlLeft.setPreferredSize(new Dimension(400, 400));
		pnlLeft.setLayout(new BoxLayout(pnlLeft, BoxLayout.Y_AXIS));
		pnlLeft.setBackground(Color.white);
		pnlRight.setBackground(Color.white);
		pnlTieuDe.setBackground(Color.white);
		pnlMaDN.setBackground(Color.white);
		pnlMK.setBackground(Color.white);
		pnlDN.setBackground(Color.white);
		
		ImageIcon img_user = new ImageIcon(new ImageIcon("HinhAnh/person_login.png").getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
		ImageIcon img_eye = new ImageIcon(new ImageIcon("HinhAnh/eye.png").getImage().getScaledInstance(17, 17, Image.SCALE_DEFAULT));
		
		
		lbltitle = new JLabel("ĐĂNG NHẬP");
		lblImgUser = new JLabel(img_user);
		
		txtMaDN = new JTextField("Mã Nhân Viên",20);
		txtMK = new JTextField("Mật Khẩu",17);
		
		
		txtMaDN.setPreferredSize(new Dimension(200, 50));
		txtMK.setPreferredSize(new Dimension(150, 50));
		
		txtMaDN.setBackground(new Color(242, 242, 242));
		txtMK.setBackground(new Color(242, 242, 242));
		
		txtMaDN.setForeground(new Color(153, 153, 153));
		txtMK.setForeground(new Color(153,153,153));
		
		btnDN = new JButton("Đăng Nhập");
		btnXemMK = new JButton(img_eye);
		
		btnDN.setPreferredSize(new Dimension(320,50));
		btnDN.setBackground(new Color(254, 0, 54));
		btnDN.setForeground(Color.white);
		
		lblImgUser.setPreferredSize(new Dimension(300, 300));
		lbltitle.setFont(new Font("arial",Font.BOLD, 30));
		txtMaDN.setFont(new Font("arial",Font.ITALIC,20));
		txtMK.setFont(new Font("arial",Font.ITALIC,20));
		btnDN.setFont(new Font("arial",1,20));
		

		pnlDangNhap.setPreferredSize(new Dimension(800, 400));
		pnlDangNhap.setBackground(Color.WHITE);
		pnlDangNhap.setLayout(new BoxLayout(pnlDangNhap, BoxLayout.X_AXIS));
		
		pnlLeft.add(lblImgUser);
		
		pnlTieuDe.add(lbltitle);
		pnlMaDN.add(txtMaDN);
		pnlMK.add(txtMK);
		pnlMK.add(btnXemMK);
		pnlDN.add(btnDN);
		
		pnlRight.setLayout(new BoxLayout(pnlRight, BoxLayout.Y_AXIS));
		pnlRight.add(pnlTieuDe);
		pnlRight.add(pnlMaDN);
		pnlRight.add(pnlMaDN);
		pnlRight.add(pnlMK);
		pnlRight.add(pnlDN);
		
		pnlDangNhap.add(pnlLeft);
		pnlDangNhap.add(pnlRight);
		
		lblbackground.add(Box.createVerticalStrut(100),BorderLayout.NORTH);
		lblbackground.add(Box.createVerticalStrut(100),BorderLayout.SOUTH);
		lblbackground.add(Box.createHorizontalStrut(200),BorderLayout.WEST);
		lblbackground.add(Box.createHorizontalStrut(200),BorderLayout.EAST);
		lblbackground.add(pnlDangNhap);
		
		this.add(lblbackground);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		
	}
}
