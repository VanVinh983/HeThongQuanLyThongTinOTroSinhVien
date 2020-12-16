package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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
import resourse.SetSizeByPercent;

import java.awt.Component;
import java.awt.FlowLayout;

public class GD_DangNhap extends JPanel implements ActionListener, KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1850860719201618407L;

	private JLabel lbltitle;

	private JLabel lblImgUser;
	private JButton btnDN;
	private JCheckBox btnXemMK;
	private JTextField txtMaDN;
	private JPasswordField txtMK;
	private JFrame frame;
	private NhanVien_Dao nv_dao;

	private TamLuuMaNhanVien_Dao tamLuuMaNhanVien_dao;

	

	public GD_DangNhap() {
		this.frame = frame;
		nv_dao = new NhanVien_Dao();
		tamLuuMaNhanVien_dao = new TamLuuMaNhanVien_Dao();
		//mở kết nối sql
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		SetSizeByPercent setSizeByPercent = new SetSizeByPercent(screenSize);
		int screenHeight = screenSize.height - setSizeByPercent.getHeightByPercent(3);
		int screenWidth = screenSize.width;
		this.setPreferredSize(new Dimension(screenSize));
		tamLuuMaNhanVien_dao.xoaMaNhanVienVaoVungNhoTam();
		ImageIcon img_background = new ImageIcon(
				new ImageIcon("HinhAnh/background.jpg").getImage().getScaledInstance(screenWidth, screenHeight, Image.SCALE_SMOOTH));
		JLabel lblbackground = new JLabel(img_background);
		lblbackground.setPreferredSize(new Dimension(screenWidth, screenHeight));
		lblbackground.setLayout(new BorderLayout());

		JPanel pnlDangNhap = new JPanel();
		JPanel pnlLeft = new JPanel();
		JPanel pnlRight = new JPanel();
		JPanel pnlTieuDe = new JPanel();
		JPanel pnlMaDN = new JPanel();
		JPanel pnlMK = new JPanel();
		JPanel pnlDN = new JPanel();
		JPanel pnlXem = new JPanel();

		pnlLeft.setPreferredSize(new Dimension(setSizeByPercent.getWidthByPercent(29.4), setSizeByPercent.getHeightByPercent(52)));
		pnlLeft.setLayout(new BoxLayout(pnlLeft, BoxLayout.Y_AXIS));
		pnlLeft.setBackground(Color.white);
		pnlRight.setBackground(Color.white);
		pnlTieuDe.setBackground(Color.white);
		pnlMaDN.setBackground(Color.white);
		pnlMK.setBackground(Color.white);
		pnlDN.setBackground(Color.white);
		pnlXem.setBackground(Color.white);

		ImageIcon img_user = new ImageIcon(
				new ImageIcon("HinhAnh/person_login.png").getImage().getScaledInstance(setSizeByPercent.getWidthByPercent(18.4), setSizeByPercent.getHeightByPercent(39), Image.SCALE_DEFAULT));

		lbltitle = new JLabel("ĐĂNG NHẬP");
		lblImgUser = new JLabel(img_user);

		txtMaDN = new JTextField("Mã Nhân Viên", 20);
		txtMK = new JPasswordField("Mật Khẩu", 20);
		txtMK.setEchoChar((char) 0);

		txtMaDN.setPreferredSize(new Dimension(setSizeByPercent.getWidthByPercent(12.2), setSizeByPercent.getHeightByPercent(6.5)));
		txtMK.setPreferredSize(new Dimension(setSizeByPercent.getWidthByPercent(11), setSizeByPercent.getHeightByPercent(6.5)));

		txtMaDN.setBackground(new Color(242, 242, 242));
		txtMK.setBackground(new Color(242, 242, 242));

		txtMaDN.setForeground(new Color(153, 153, 153));
		txtMK.setForeground(new Color(153, 153, 153));

		btnDN = new JButton("Đăng Nhập");
		btnXemMK = new JCheckBox("Hiển thị mật khẩu!");
		btnXemMK.setFont(new Font("Arial", Font.BOLD, 20));
		btnXemMK.setBackground(Color.white);

		btnDN.setPreferredSize(new Dimension(setSizeByPercent.getWidthByPercent(23.5), setSizeByPercent.getHeightByPercent(6.5)));
		btnDN.setBackground(new Color(254, 0, 54));
		btnDN.setForeground(Color.white);

		lblImgUser.setPreferredSize(new Dimension(setSizeByPercent.getWidthByPercent(22), setSizeByPercent.getHeightByPercent(39)));
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
		
		
		pnlDangNhap.setPreferredSize(new Dimension(setSizeByPercent.getWidthByPercent(58.8), setSizeByPercent.getHeightByPercent(52)));
		pnlDangNhap.setBackground(Color.WHITE);
		pnlDangNhap.setLayout(new BoxLayout(pnlDangNhap, BoxLayout.X_AXIS));

		pnlLeft.add(lblImgUser);

		pnlTieuDe.add(lbltitle);
		pnlMaDN.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblMaDangNhap = new JLabel("Mã đăng nhập                                    \r\n");
		lblMaDangNhap.setFont(new Font("Arial", Font.PLAIN, 20));
		pnlMaDN.add(lblMaDangNhap);
		pnlMaDN.add(txtMaDN);
		
		JLabel lblNewLabel = new JLabel("Mật khẩu                                            \r\n");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		pnlMK.add(lblNewLabel);
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

		Component verticalStrut_1 = Box.createVerticalStrut(45);
		verticalStrut_1.setMinimumSize(new Dimension(0, setSizeByPercent.getHeightByPercent(13)));
		verticalStrut_1.setMaximumSize(new Dimension(0, setSizeByPercent.getHeightByPercent(13)));
		verticalStrut_1.setPreferredSize(new Dimension(0, setSizeByPercent.getHeightByPercent(13)));
		lblbackground.add(verticalStrut_1, BorderLayout.NORTH);
		Component verticalStrut = Box.createVerticalStrut(79);
		verticalStrut.setMaximumSize(new Dimension(0, setSizeByPercent.getHeightByPercent(13)));
		verticalStrut.setMinimumSize(new Dimension(0, setSizeByPercent.getHeightByPercent(13)));
		verticalStrut.setPreferredSize(new Dimension(0, setSizeByPercent.getHeightByPercent(19.5)));
		lblbackground.add(verticalStrut, BorderLayout.SOUTH);
		Component horizontalStrut = Box.createHorizontalStrut(200);
		horizontalStrut.setPreferredSize(new Dimension(setSizeByPercent.getWidthByPercent(22),0));
		horizontalStrut.setMinimumSize(new Dimension(setSizeByPercent.getWidthByPercent(22),0));
		lblbackground.add(horizontalStrut, BorderLayout.WEST);
		Component horizontalStrut_1 = Box.createHorizontalStrut(200);
		horizontalStrut_1.setPreferredSize(new Dimension(setSizeByPercent.getWidthByPercent(22),0));
		lblbackground.add(horizontalStrut_1, BorderLayout.EAST);
		lblbackground.add(pnlDangNhap);

		this.add(lblbackground);

		txtMaDN.addActionListener(this);
		txtMK.addActionListener(this);
		btnDN.addActionListener(this);
		btnXemMK.addActionListener(this);
		txtMaDN.addKeyListener(this);
		txtMK.addKeyListener(this);
		btnXemMK.addKeyListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
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
		if(o.equals(btnDN)) {
			if(vaidData()==true) {
				NhanVien nv = nv_dao.dangNhap(txtMaDN.getText().trim(), txtMK.getText().trim());
				if(nv!=null) {
					tamLuuMaNhanVien_dao.themMaNhanVienVaoVungNhoTam(txtMaDN.getText().trim());
					if(nv.getLoaiNV().equals("NV")) {
						removeAll();
						add(new GD_TrangChuNhanVienGVK());
						repaint();
						revalidate();
					}
					else if (nv.getLoaiNV().equals("QL")) {
						removeAll();
						add(new GD_Admin());
						repaint();
						revalidate();
					}
				}
				else {
					JOptionPane.showMessageDialog(this, "sai mật khẩu hoặc sai mã nhân viên! mời nhập lại");
				}
			}
		}
		
	}
	
	public boolean vaidData() {
		String MaNV = txtMaDN.getText().trim();
		String matkhau = txtMK.getText().trim();
		
		if(MaNV.length()<=0||MaNV.equals("Mã Nhân Viên")) {
			JOptionPane.showMessageDialog(this, "mời nhập vào mã nhân viên");
			return false;
		}
		else if (matkhau.length()<=0||matkhau.equals("Mật Khẩu")) {
			JOptionPane.showMessageDialog(this, "mời nhập đầy đủ mật khẩu và mã nhân viên");
			return false;
		}
		return true;
	}
	public String getMaNhanVien() {
		return txtMaDN.getText().trim();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
			btnDN.doClick();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
