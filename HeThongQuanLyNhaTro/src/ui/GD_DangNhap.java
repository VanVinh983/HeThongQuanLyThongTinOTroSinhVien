package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GD_DangNhap extends JPanel implements ActionListener {

	private JLabel lbltitle;

	private JLabel lblImgUser;
	private JButton btnDN;
	private JCheckBox btnXemMK;
	private JTextField txtMaDN;
	private JPasswordField txtMK;

	public GD_DangNhap() {
		this.setPreferredSize(new Dimension(1200, 600));

		ImageIcon img_background = new ImageIcon(
				new ImageIcon("HinhAnh/background.jpg").getImage().getScaledInstance(1200, 600, Image.SCALE_SMOOTH));
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
		JPanel pnlXem = new JPanel();

		pnlLeft.setPreferredSize(new Dimension(400, 400));
		pnlLeft.setLayout(new BoxLayout(pnlLeft, BoxLayout.Y_AXIS));
		pnlLeft.setBackground(Color.white);
		pnlRight.setBackground(Color.white);
		pnlTieuDe.setBackground(Color.white);
		pnlMaDN.setBackground(Color.white);
		pnlMK.setBackground(Color.white);
		pnlDN.setBackground(Color.white);
		pnlXem.setBackground(Color.white);

		ImageIcon img_user = new ImageIcon(
				new ImageIcon("HinhAnh/person_login.png").getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));

		lbltitle = new JLabel("ĐĂNG NHẬP");
		lblImgUser = new JLabel(img_user);

		txtMaDN = new JTextField("Mã Nhân Viên", 20);
		txtMK = new JPasswordField("Mật Khẩu", 20);
		txtMK.setEchoChar((char) 0);

		txtMaDN.setPreferredSize(new Dimension(200, 50));
		txtMK.setPreferredSize(new Dimension(150, 50));

		txtMaDN.setBackground(new Color(242, 242, 242));
		txtMK.setBackground(new Color(242, 242, 242));

		txtMaDN.setForeground(new Color(153, 153, 153));
		txtMK.setForeground(new Color(153, 153, 153));

		btnDN = new JButton("Đăng Nhập");
		btnXemMK = new JCheckBox("Hiển thị mật khẩu!");
		btnXemMK.setBackground(Color.white);

		btnDN.setPreferredSize(new Dimension(320, 50));
		btnDN.setBackground(new Color(254, 0, 54));
		btnDN.setForeground(Color.white);

		lblImgUser.setPreferredSize(new Dimension(300, 300));
		lbltitle.setFont(new Font("arial", Font.BOLD, 30));
		txtMaDN.setFont(new Font("arial", Font.ITALIC, 20));
		txtMK.setFont(new Font("arial", Font.ITALIC, 20));
		btnDN.setFont(new Font("arial", 1, 20));

		txtMaDN.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtMaDN.getText().equals("Mã Nhân Viên")) {
					txtMaDN.setForeground(Color.black);
					txtMaDN.setText("");
				} else {
					txtMaDN.selectAll();
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtMaDN.getText().equals("")) {
					txtMaDN.setForeground(new Color(153, 153, 153));
					txtMaDN.setText("Mã Nhân Viên");
				}
			}
		});

		txtMK.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtMK.getText().equals("Mật Khẩu")) {
					txtMK.setForeground(Color.black);
					txtMK.setEchoChar('*');
					txtMK.setText("");

				} else {
					txtMK.selectAll();
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtMK.getText().equals("")) {
					txtMK.setForeground(new Color(153, 153, 153));
					txtMK.setText("Mật Khẩu");
					txtMK.setEchoChar((char) 0);
				}
			}
		});

		pnlDangNhap.setPreferredSize(new Dimension(800, 400));
		pnlDangNhap.setBackground(Color.WHITE);
		pnlDangNhap.setLayout(new BoxLayout(pnlDangNhap, BoxLayout.X_AXIS));

		pnlLeft.add(lblImgUser);

		pnlTieuDe.add(lbltitle);
		pnlMaDN.add(txtMaDN);
		pnlMK.add(txtMK);
		pnlXem.add(btnXemMK);
		pnlDN.add(btnDN);

		pnlRight.setLayout(new BoxLayout(pnlRight, BoxLayout.Y_AXIS));
		pnlRight.add(pnlTieuDe);
		pnlRight.add(pnlMaDN);
		pnlRight.add(pnlMaDN);
		pnlRight.add(pnlMK);
		pnlRight.add(pnlXem);
		pnlRight.add(pnlDN);

		pnlDangNhap.add(pnlLeft);
		pnlDangNhap.add(pnlRight);

		lblbackground.add(Box.createVerticalStrut(100), BorderLayout.NORTH);
		lblbackground.add(Box.createVerticalStrut(100), BorderLayout.SOUTH);
		lblbackground.add(Box.createHorizontalStrut(200), BorderLayout.WEST);
		lblbackground.add(Box.createHorizontalStrut(200), BorderLayout.EAST);
		lblbackground.add(pnlDangNhap);

		this.add(lblbackground);

		txtMaDN.addActionListener(this);
		txtMK.addActionListener(this);
		btnDN.addActionListener(this);
		btnXemMK.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnXemMK)) {
			if (txtMK.getText().equals("Mật Khẩu")) {
				JOptionPane.showMessageDialog(this, "xin nhập vào mật khẩu");
			} else {
				if (btnXemMK.isSelected()) {
					txtMK.setEchoChar((char) 0);
				} else {
					txtMK.setEchoChar('*');
				}
			}

		}
	}
}
