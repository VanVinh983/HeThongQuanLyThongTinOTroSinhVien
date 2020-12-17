package ui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.chrono.JapaneseDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.NhaTro_Dao;
import dao.NhanVien_Dao;
import dao.SinhVien_Dao;
import dao.TamLuuMaNhanVien_Dao;
import dao.ThongTinThueTro_Dao;
import entity.NhaTro;
import entity.NhanVien;
import entity.SinhVien;
import resourse.SetSizeByPercent;

public class GD_ThongKe extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NhanVien_Dao nv_Dao;
	private TamLuuMaNhanVien_Dao tamluu_dao;
	private int TKQuan=0;
	private int TKTuoi=0;
	private int TKGT=0;
	private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel();
	private JPanel pnlFormtxtSV = new JPanel();
	private Box box = Box.createVerticalBox();
	private Box boxCen = Box.createVerticalBox();
	private JPanel pnlTim = new JPanel();
	private JTabbedPane tabbedPane = new JTabbedPane();
	private JPanel pnlFormtxtbtnSV = new JPanel();
	private int q1=0;
	private int maxx1 =0;
	private String key1=null;
	private int maxx2 =0;
	private String key2=null;
	private int maxx3 =0;
	private String key3=null;
	private int maxx4 =0;
	private String key4=null;
	private int maxx5 =0;
	private String key5=null;
	
	private int qbt=0;
	private int q2=0;
	private int qbth=0;
	private int q3=0;
	private int qgv=0;
	private int q4=0;
	private int qpnh=0;
	private int q5=0;
	private int qtb=0;
	private int q6=0;
	private int qtph=0;
	private int q7=0;
	private int qtd=0;
	private int q8=0;
	private int qbch=0;
	private int q9=0;
	private int qcg=0;
	private int q10=0;
	private int qcch=0;
	private int q11=0;
	private int qhm=0;
	private int q12=0;
	private int qnhbe=0;
	//private JPanel pnlFormtxtbtnSV = new JPanel();
	private Box bcenForm = Box.createVerticalBox();
	private int TKTinhTrangThue=0;
	private Box bcenTable;
	private int slTuoi18 = 0;
	private int slTuoi19 = 0;
	private int slTuoi20 = 0;
	private int slTuoi21 = 0;
	private int slTuoi22 = 0;
	private int slTuoi23 = 0;
	private int slTuoi24 = 0;
	private int slTuoiKhac = 0;
	private int tongTuoiTBKhac = 0;
	private int tuoiTBKhac = 0;
	//attribute Form thông tin phần txt
	private JTextField txtTongSo;
	private JTextField txt1;
	private JTextField txt2;
	private JTextField txt3;
	private JTextField txt4;
	private JTextField txt5;
	private JTextField txt6;
	private JTextField txt7;
	private JTextField txt8;
	private JTextField txt9;
	private JTextField txt10;
	
	//attribute Form thông tin phần label
	
	private JLabel lblTongSo;
	private JLabel lblSLNhieuNhat;
	private JLabel lblNgaySinh;
	private JLabel lblQueQuan;
	private JLabel lblSLNhoNhat;
	private JLabel lblMaNV;
	private JLabel lblGioiTinh;
	private JLabel lblKhoa;
	
	//Button Form Thông tin
	private JButton btnTim;
	private JButton btnTKSLSVTheoQuan;
	private JButton btnTKTop5TroDuocThueNhieu;
	private JButton btnTKDoTuoiDangThue;
	private JButton btnTKGioiTinh;
	
	//Phần tên user
	private JLabel lblNameUser;
	private JLabel lblUser;
	
	//Tiêu đề Quản lý Trọ
	private JLabel lblcenTitle;
	//Bảng thông tin
	private DefaultTableModel tableModel;
	private JTable table;
	//Tiêu đề thông tin form
	private JLabel lblThongTin;
	
	//Thanh điều hướng
	private JButton btnTro;
	private JButton btnSinhVien;
	private JButton btnThongKe;
	private JButton btnThueTro;
	private JButton btnNhanVien;
	private JButton btnHuongDanSD;
	private JButton btnThoat;
	//Compobox Lua cong tim kiếm
	private JComboBox cmpTim;
//	private JTextField txtTim;
	private JLabel lblTim;
	private JComboBox<String> cmp;
	private NhanVien nhanVienTamLuu;
	
	///////////////////////////////////////////////////////////////////////////

	private JPanel contentPane ;
	private JComboBox<String> comboBox;
	private Box boxtxtTim;
	private JPanel pnlFormTim;
	private JPanel pnl;
	private String dstim ="";
	private int max = 0;
	private String ma = "";
	////////////////////////////////////////////////////////////////////////
	private List list = new ArrayList<String>();
	
	public GD_ThongKe() {
		nv_Dao = new NhanVien_Dao(); 
		tamluu_dao = new TamLuuMaNhanVien_Dao();
		
		nhanVienTamLuu = tamluu_dao.layNhanVienTrongBangTamLuu();
		
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(1200, 600));
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		SetSizeByPercent setSizeByPercent = new SetSizeByPercent(screenSize);
		int screenHeight = screenSize.height - setSizeByPercent.getHeightByPercent(3);
		int screenWidth = screenSize.width;
		this.setPreferredSize(new Dimension(screenSize));
		
		ImageIcon imgUser = new ImageIcon(
				new ImageIcon("HinhAnh/User.png").getImage().getScaledInstance(setSizeByPercent.getWidthByPercent(10),
						setSizeByPercent.getHeightByPercent(17.7), Image.SCALE_DEFAULT));
		ImageIcon imgSV = new ImageIcon(new ImageIcon("HinhAnh/sinhvien.png").getImage().getScaledInstance(
				setSizeByPercent.getWidthByPercent(3), setSizeByPercent.getHeightByPercent(6), Image.SCALE_DEFAULT));
		ImageIcon imgNV = new ImageIcon(new ImageIcon("HinhAnh/nhanvien.png").getImage().getScaledInstance(
				setSizeByPercent.getWidthByPercent(3), setSizeByPercent.getHeightByPercent(6), Image.SCALE_DEFAULT));
		ImageIcon imgTK = new ImageIcon(new ImageIcon("HinhAnh/thongke.png").getImage().getScaledInstance(
				setSizeByPercent.getWidthByPercent(3), setSizeByPercent.getHeightByPercent(6), Image.SCALE_DEFAULT));
		ImageIcon imgBTT = new ImageIcon(new ImageIcon("HinhAnh/ghichu1.png").getImage().getScaledInstance(
				setSizeByPercent.getWidthByPercent(3), setSizeByPercent.getHeightByPercent(6), Image.SCALE_DEFAULT));
		ImageIcon imgTro = new ImageIcon(new ImageIcon("HinhAnh/nhatro.png").getImage().getScaledInstance(
				setSizeByPercent.getWidthByPercent(3), setSizeByPercent.getHeightByPercent(6), Image.SCALE_DEFAULT));
		ImageIcon imgexit = new ImageIcon(new ImageIcon("HinhAnh/exit.png").getImage().getScaledInstance(
				setSizeByPercent.getWidthByPercent(3), setSizeByPercent.getHeightByPercent(6), Image.SCALE_DEFAULT));
		ImageIcon imgHDSD = new ImageIcon(new ImageIcon("HinhAnh/User manual.jpg").getImage().getScaledInstance(
				setSizeByPercent.getWidthByPercent(3), setSizeByPercent.getHeightByPercent(6), Image.SCALE_DEFAULT));
		setLayout(new BorderLayout(0, 0));
		
		pnl = new JPanel();
		pnl.setLayout(new BorderLayout());
		
		
		//pnluser1 Tạo ảnh và txt hiển thị user
			JPanel pnlUser = new JPanel();
				pnlUser.setBackground(new Color(108, 123, 139));
				Box buser = Box.createVerticalBox();
				
					//tạo 3 box user định dạng theo  chiều dọc
					Box bUser, buserImg;
					
					//box user1 
					buser.add(bUser = Box.createHorizontalBox());
					
					SinhVien_Dao daoSV = new SinhVien_Dao();
					
					String tenKhoaNV = daoSV.layTenKhoaNhanVien().trim().toString();
					if(daoSV.layLoaiNV().trim().toString().equals("NV"))
					{
						bUser.add(lblNameUser = new JLabel("GV:" + tenKhoaNV));
					}
					else if(daoSV.layLoaiNV().trim().toString().equals("QL")){
						bUser.add(lblNameUser = new JLabel("Quản lý"));
					}
					
					lblNameUser.setFont(new Font( "Arial", Font.BOLD, 30));
					buser.add(buserImg = Box.createVerticalBox());
					//box user2 Hinh Anh user
					JPanel pnlcontent=new JPanel();
		     		JLabel lblBanner = new JLabel();
					pnlcontent.add(lblBanner);
					lblBanner.setSize(200,200);
					add(pnlcontent);
					//đường dẫn
					setPicture(lblBanner, "HinhAnh/User.png");
					buserImg.add(pnlcontent);
					pnlUser.add(buser);
				
					pnlUser.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		box.add(pnlUser);
		
		pnl.add(box, BorderLayout.WEST);
		//thêm vào màn hình chính
		this.add(pnl, BorderLayout.CENTER);
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//pnlMenu chứa thanh điều hướng nằm dưới hình user
		JPanel pnlMenu = new JPanel();
		pnlMenu.setLayout(new BorderLayout());
		
			JPanel pnlMenubtn = new JPanel();
			pnlMenubtn.setBackground(Color.MAGENTA);
			pnlMenu.add(pnlMenubtn, BorderLayout.CENTER);
			pnlMenubtn.setLayout(new BoxLayout(pnlMenubtn, BoxLayout.Y_AXIS));
			
				box.add(pnlMenu);
				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				//Thanh điều hướng 
				pnlMenubtn.add(Box.createVerticalStrut(10));
				pnlMenubtn.add(btnTro = new JButton("Trọ"));
				pnlMenubtn.setBackground(new Color(0, 238, 238));
				btnTro.add(Box.createHorizontalStrut(30));
				btnTro.add(Box.createVerticalStrut(20));
				btnTro.setFont(new Font( "Arial", Font.BOLD, 25));
				btnTro.setBackground(new Color(72, 209, 204));
				
				pnlMenubtn.add(Box.createVerticalStrut(2));
				pnlMenubtn.add(btnSinhVien = new JButton("Sinh viên"));
				btnSinhVien.add(Box.createHorizontalStrut(30));
				btnSinhVien.add(Box.createVerticalStrut(20));
				btnSinhVien.setFont(new Font( "Arial", Font.BOLD, 25));
				btnSinhVien.setBackground(new Color(0, 206, 209));
				if(daoSV.layLoaiNV().equals("QL"))
				{
					pnlMenubtn.add(Box.createVerticalStrut(2));
					pnlMenubtn.add(btnNhanVien = new JButton("Nhân viên"));
					btnNhanVien.add(Box.createHorizontalStrut(30));
					btnNhanVien.add(Box.createVerticalStrut(20));
					btnNhanVien.setFont(new Font( "Arial", Font.BOLD, 25));
					btnNhanVien.addActionListener(this);
					btnNhanVien.setBackground(new Color(64, 224, 208));
				}
				
				
				
				pnlMenubtn.add(Box.createVerticalStrut(2));
				pnlMenubtn.add(btnThueTro = new JButton("Thuê trọ"));
				btnThueTro.add(Box.createHorizontalStrut(30));
				btnThueTro.add(Box.createVerticalStrut(20));
				btnThueTro.setFont(new Font( "Arial", Font.BOLD, 25));
				btnThueTro.setBackground(new Color(0, 245, 255));
				//pnlMenubtn.add(Box.createVerticalStrut(10));
				
				pnlMenubtn.add(Box.createVerticalStrut(2));
				pnlMenubtn.add(btnThongKe = new JButton("Thống kê"));
				btnThongKe.add(Box.createHorizontalStrut(30));
				btnThongKe.add(Box.createVerticalStrut(20));
				btnThongKe.setFont(new Font( "Arial", Font.BOLD, 25));
				btnThongKe.add(Box.createVerticalStrut(20));
				btnThongKe.setBackground(new Color(0, 229, 238));
				//pnlMenubtn.add(Box.createVerticalStrut(10));
				
				pnlMenubtn.add(Box.createVerticalStrut(2));
				pnlMenubtn.add(btnHuongDanSD = new JButton("Trợ giúp"));
				btnHuongDanSD.add(Box.createHorizontalStrut(30));
				btnHuongDanSD.add(Box.createVerticalStrut(20));
				btnHuongDanSD.setFont(new Font( "Arial", Font.BOLD, 25));
				btnHuongDanSD.add(Box.createVerticalStrut(20));
				btnHuongDanSD.setBackground(new Color(0, 197, 205));
				//pnlMenubtn.add(Box.createVerticalStrut(10));

				pnlMenubtn.add(Box.createVerticalStrut(2));
				pnlMenubtn.add(btnThoat = new JButton("Thoát"));
				btnThoat.add(Box.createHorizontalStrut(30));
				btnThoat.add(Box.createVerticalStrut(20));
				btnThoat.setFont(new Font( "Arial", Font.BOLD, 25));
				btnThoat.add(Box.createVerticalStrut(20));
				btnThoat.setBackground(new Color(0, 134, 139));
				pnlMenubtn.add(Box.createVerticalStrut(10));
				////////////////////////////////////////////////////////////////////////////////
				if(daoSV.layLoaiNV().trim().toString().equals("QL")){
					btnNhanVien.setIcon(imgNV);
				}
				
				btnTro.setIcon(imgTro);
				btnSinhVien.setIcon(imgSV);
				btnThoat.setIcon(imgexit);
				btnThongKe.setIcon(imgTK);
				btnThueTro.setIcon(imgBTT);
				btnHuongDanSD.setIcon(imgHDSD);
				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//Center
		//tạo 1 boxcen chứa bcen1, bcen2, bcen3 theo chiều dọc
		Box boxCen = Box.createVerticalBox();
		pnl.add(boxCen, BorderLayout.CENTER);
		// còn lại toàn bộ phần bên phải sẽ được chia ra 3 phần bcen1, bcen2, bcen3 được định dang theo chiều dọc
		//bcen1 sẽ chưa phần tiêu đề
		//bcen2 sẽ chứa bảng
		//bcen3 sẽ chứa form thông tin và form tìm
		Box bcenTitle;
		//bcenTitle tạo tiêu đề
		boxCen.add(bcenTitle = Box.createHorizontalBox());
		//Label Title Quản lý trọ
		JPanel pnlQuanLySinhVien = new JPanel();
		pnlQuanLySinhVien.add(lblcenTitle = new JLabel("Thống kê thông tin trọ sinh viên"));
		bcenTitle.add(pnlQuanLySinhVien);
		//chỉnh font chữ
		pnlQuanLySinhVien.setBackground(new Color(79, 79, 79));
		lblcenTitle.setFont(new Font("Arial", Font.BOLD, 40));
		lblcenTitle.setForeground(Color.WHITE);
		
	//bcenTable tạo bảng thông tin
		
        
 
        /* add three tab with three JPanel */
        
       // tabbedPane.addTab("Tab 2", null, panel2, "click to show panel 2");
       // tabbedPane.addTab("Tab 3", null, panel3, "click to show panel 3");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		///////////////////////////////////////////////////////////////////////////////////////
		boxCen.add(bcenTable = Box.createHorizontalBox());
		JPanel pnlTable = new JPanel();

		 XYDataset dataset1 = (XYDataset) createDatasetAge();
	        JFreeChart chart = createChartAge(dataset1);

	        ChartPanel chartPanel = new ChartPanel(chart);
	        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
	        chartPanel.setBackground(Color.white);
	        panel1.add(chartPanel);
	        tabbedPane.addTab("Tab 1", null, panel1, "click to show panel 1");
	        bcenTable.add(tabbedPane);
	        
	        //SinhVien_Dao dao = new SinhVien_Dao();
	        List<SinhVien> listsv = daoSV.layTatCaBangQL();
    		
	    	
	    		List<SinhVien> list18 = new ArrayList<SinhVien>();
	    		listsv.forEach(v -> {
	    			if((LocalDate.now().getYear()-v.getNgaySinh().getYear())==18)
	    			{
	    				list18.add(v);
	    				slTuoi18++;
	    			}
	    		});
	    		
	    		
	    		List<SinhVien> list19 = new ArrayList<SinhVien>();
	    		listsv.forEach(v -> {
	    			if((LocalDate.now().getYear()-v.getNgaySinh().getYear())==19)
	    			{
	    				list19.add(v);
	    				slTuoi19++;

	    			}
	    		});
	    		
	    		List<SinhVien> list20 = new ArrayList<SinhVien>();
	    		listsv.forEach(v -> {
	    			if((LocalDate.now().getYear()-v.getNgaySinh().getYear())==20)
	    			{
	    				list20.add(v);
	    				slTuoi20++;

	    			}
	    		});
	    		
	    		List<SinhVien> list21 = new ArrayList<SinhVien>();
	    		listsv.forEach(v -> {
	    			if((LocalDate.now().getYear()-v.getNgaySinh().getYear())==21)
	    			{
	    				list21.add(v);
	    				slTuoi21++;
	    			}
	    		});
	    		
	    		List<SinhVien> list22 = new ArrayList<SinhVien>();
	    		listsv.forEach(v -> {
	    			if((LocalDate.now().getYear()-v.getNgaySinh().getYear())==22)
	    			{
	    				list22.add(v);
	    				slTuoi22++;
	    			}
	    		});
	    		
	    		List<SinhVien> list23 = new ArrayList<SinhVien>();
	    		listsv.forEach(v -> {
	    			if((LocalDate.now().getYear()-v.getNgaySinh().getYear())==23)
	    			{
	    				list23.add(v);
	    				slTuoi23++;
	    			}
	    		});
	    		
	    		List<SinhVien> list24 = new ArrayList<SinhVien>();
	    		listsv.forEach(v -> {
	    			if((LocalDate.now().getYear()-v.getNgaySinh().getYear())==24)
	    			{
	    				
	    				list24.add(v);
	    				slTuoi24++;
	    			}
	    		});
	    		
	    		List<SinhVien> listkhac = new ArrayList<SinhVien>();
	    		listsv.forEach(v -> {
	    			if((LocalDate.now().getYear()-v.getNgaySinh().getYear())>24)
	    			{
	    				tongTuoiTBKhac = tongTuoiTBKhac + (LocalDate.now().getYear()-v.getNgaySinh().getYear());
	    				
	    				listkhac.add(v);
	    				slTuoiKhac++;
	    			}
	    		});
	    		
	    		if(slTuoiKhac>0)
	    		{
	    			tuoiTBKhac = tongTuoiTBKhac/slTuoiKhac;
	    		}
	    		
	    		/////////////////////////////////////////////////////////////////////////////////////////////////
	       
	    		
	    		 DefaultPieDataset datasetSH = createDatasetGT();
	 	        JFreeChart chartGT = createChartGT(datasetSH);
	 			 ChartPanel chartPanelGT = new ChartPanel(chartGT);
	 		        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
	 		        chartPanel.setBackground(Color.white);
	 		        //bcenTable.add(chartPanelGT);
	 		       
//	 		        tabbedPane.addTab("Tab 3", null, panel3, "click to show panel 3");
//	 		        bcenTable.add(tabbedPane);
//	 		       TKQuan=0;
//	 		        TKTinhTrangThue=0;
	 		        SinhVien_Dao daosv = new SinhVien_Dao();
	 		 	   List<SinhVien> listNam = daosv.layTatCaNam();
	 		 	   List<SinhVien> listNu = daosv.layTatCaNu();
	 		 	   int slNam = listNam.size();
	 		 	   int slNu = listNu.size();
	 		 	   int tog = (slNam+slNu);
	 		 	   float perNam = ((slNam)/(tog));
	 		 	   
	 		 	   
	 		 	   
	 		 	  DefaultPieDataset datasetStateHire= createDatasetStateHire();
	 		        JFreeChart chartStateHire = createChartStateHire(datasetStateHire);
	 				 ChartPanel chartPanelStateHire = new ChartPanel(chartStateHire);
	 			        chartPanelStateHire.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
	 			        chartPanelStateHire.setBackground(Color.white);
	 			        bcenTable.add(chartPanelStateHire);
	 			       
	 			        Box bChart = Box.createHorizontalBox();
	 			       bChart.add(chartPanelStateHire);
	 			      bChart.add(chartPanelGT);
	 			      panel2.add(bChart);
		 		        tabbedPane.addTab("Tab 2", null, panel2, "click to show panel 3");
		 		        bcenTable.add(tabbedPane);
	    		
		 		        
		 		        
		 		 //////////// Thong Ke theo Quan
		 		        
		 		       DefaultPieDataset datasetQuan = createDatasetQuan();

				        JFreeChart chartQuan = createChartQuan(datasetQuan);
				        ChartPanel chartPanelQuan = new ChartPanel(chartQuan);
				        chartPanelQuan.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
				        chartPanelQuan.setBackground(Color.white);
				        bcenTable.add(chartPanelQuan);
				        //5 Quận nhiều 
				        CategoryDataset datasetQuanI = createDatasetQuanI();
				        JFreeChart charttQuanI = createChartQuanI(datasetQuanI);
				        ChartPanel chartPaneltQuanI = new ChartPanel(charttQuanI);
				        chartPaneltQuanI.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
				        chartPaneltQuanI.setBackground(Color.white);
				        
				        
				        Box bChartQuan = Box.createHorizontalBox();
		 			       bChartQuan.add(chartPanelQuan);
		 			      bChartQuan.add(chartPaneltQuanI);
		 			      panel3.add(bChartQuan);
			 		        tabbedPane.addTab("Tab 3", null, panel3, "click to show panel 3");
			 		        bcenTable.add(tabbedPane);
				        bcenTable.add(tabbedPane);
	    		

			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//Form thông tin
		boxCen.add(bcenForm);
		
		JPanel pnlForm = new JPanel();
		pnlForm.setSize(100, 20);
		pnlForm.setLayout(new BorderLayout());
		Box boxcenFormTitle, boxcenFormSV;
		
		JLabel lblThongTin = new JLabel("Thông tin");
		
		lblThongTin.setFont(new Font("Arial", Font.BOLD, 30));
		
		lblThongTin.setBackground(new Color(0, 139, 139));
		bcenForm.add(lblThongTin);
		bcenForm.setBackground(Color.LIGHT_GRAY);
		

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		bcenForm.add(boxcenFormSV = Box.createVerticalBox());
		JPanel pnlSouth = new JPanel();
		pnlSouth.setLayout(new BorderLayout());
	
		boxcenFormSV.setBackground(new Color(0, 139, 139));
		pnlSouth.add(Box.createHorizontalStrut(100), BorderLayout.WEST);
		JPanel pnlFormSV=new JPanel();
		pnlSouth.add(pnlFormSV);
		boxcenFormSV.add( pnlSouth);
		pnlFormSV.setBounds(0,80,1055,450);
		pnlFormSV.setLayout(new BoxLayout(pnlFormSV, BoxLayout.Y_AXIS));
		
		JPanel pnlFormtxt = new JPanel();
		pnlFormtxt.setLayout(new GridLayout(1, 2));
		pnlFormSV.add(pnlFormtxt);
		////////////////////////////////////////////////////////////////////////////
		
		//Form điền thông tin
		
		pnlFormtxtbtnSV.setLayout(new BoxLayout(pnlFormtxtbtnSV, BoxLayout.Y_AXIS));
		
		pnlFormtxt.add(pnlFormtxtbtnSV);
		pnlFormtxtbtnSV.add(pnlFormtxtSV);
		
		pnlFormtxtSV.setLayout(new GridLayout(2, 4));
		Box boxMaSV = Box.createHorizontalBox();
		boxMaSV.add(lblTongSo=new JLabel("Tổng số:"));
		boxMaSV.add(txtTongSo=new JTextField());
	
		Box boxMaLop = Box.createHorizontalBox();
		boxMaLop.add(lblSLNhoNhat=new JLabel("2 "));
		boxMaLop.add(txt2=new JTextField());
		
		pnlFormtxtSV.add(boxMaSV);
		pnlFormtxtSV.add(boxMaLop);
		
		Box boxTenSV = Box.createHorizontalBox();
		boxTenSV.add(lblSLNhieuNhat=new JLabel("1 "));
		boxTenSV.add(txt1=new JTextField(5));
		Box boxMaNV = Box.createHorizontalBox();
	
		boxMaNV.add(lblMaNV=new JLabel("3 "));
		boxMaNV.add(txt3=new JTextField());
		
		
		pnlFormtxtSV.add(boxTenSV);
		pnlFormtxtSV.add(boxMaNV);
		
		Box boxNgaySinh = Box.createHorizontalBox();
		boxNgaySinh.add(lblNgaySinh=new JLabel("7 "));
		boxNgaySinh.add(txt7 = new JTextField());
		Box boxGioiTinh = Box.createHorizontalBox();
		boxGioiTinh.add(lblGioiTinh=new JLabel("5 "));
		boxGioiTinh.add(txt5=new JTextField());
		
		pnlFormtxtSV.add(boxNgaySinh);
		pnlFormtxtSV.add(boxGioiTinh);
		Box boxQueQuan = Box.createHorizontalBox();
		boxQueQuan.add(lblQueQuan=new JLabel("4 "));
		boxQueQuan.add(txt4 = new JTextField());
		Box boxKhoa = Box.createHorizontalBox();
		
		boxKhoa.add(lblKhoa=new JLabel("6 "));
		boxKhoa.add(txt6=new JTextField());
			
		
		pnlFormtxtSV.setBackground(new Color(181, 181, 181));
		pnlForm.add(boxKhoa);
		pnlFormtxtSV.add(boxQueQuan);
		
		pnlFormtxtSV.add(boxKhoa);
		// các nút chức năng thêm xóa sửa xóa trắng
		
		 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 //Form tìm
		 
		 pnlTim.setLayout(new BoxLayout(pnlTim, BoxLayout.Y_AXIS));
		 pnlFormtxtbtnSV.add(pnlTim);
		 pnlTim.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		 
		 pnlFormTim = new JPanel();
		 pnlTim.add(pnlFormTim);

	        
		 Box boxcmpTim = Box.createHorizontalBox();
		 JLabel lblTim ;
		 boxcmpTim.add(lblTim = new JLabel("__Các chức năng thống kê__"));
		 lblTim.setFont(new Font("Arial", Font.BOLD, 30));
		 String[] s = "Mã;Tên".split(";");

		 pnlFormTim.add(boxcmpTim);
		 
		 
		 // Button tìm
		 pnlTim.add(Box.createVerticalStrut(30));
		 Box boxbtnTim = Box.createHorizontalBox();
		 
		 pnlTim.add(Box.createVerticalStrut(20));
			Box boxButton = Box.createHorizontalBox();
			boxButton.add(btnTKSLSVTheoQuan =new JButton("Số lượng sinh viên theo quận"));
			btnTKSLSVTheoQuan.setFont(new Font("Arial", Font.BOLD, 25));
			btnTKSLSVTheoQuan.setBackground(new Color(156, 156, 156));
			boxButton.add(Box.createHorizontalStrut(20));
//			boxButton.add(btnTKTop5TroDuocThueNhieu =new JButton("Thống kê trang thái thuê trọ"));
//			btnTKTop5TroDuocThueNhieu.setBackground(new Color(105, 105, 105));
//			btnTKTop5TroDuocThueNhieu.setFont(new Font("Arial", Font.BOLD, 25));
//			boxButton.add(Box.createHorizontalStrut(20));
			boxButton.add(btnTKDoTuoiDangThue =new JButton("Độ tuổi đang thuê"));
			btnTKDoTuoiDangThue.setBackground(new Color(130, 130, 130));
			btnTKDoTuoiDangThue.setFont(new Font("Arial", Font.BOLD, 25));
			boxButton.add(Box.createHorizontalStrut(20));
			boxButton.add(btnTKGioiTinh =new JButton("Giới tính và trọ"));
			btnTKGioiTinh.setFont(new Font("Arial", Font.BOLD, 25));
			btnTKGioiTinh.setBackground(new Color(156, 156, 156));
			pnlTim.add(Box.createVerticalStrut(5));
			pnlTim.add(boxButton);
			pnlTim.add(Box.createVerticalStrut(20));
			pnlTim.setBorder(BorderFactory.createRaisedBevelBorder());
			pnlTim.setBackground(new Color(181, 181, 181));
		 
		// pnlTim.add(pnlFormtxtbtnSV);
		// btnTim.setFont(new Font("Arial", Font.BOLD, 25));
		// btnTim.setBackground(new Color(232, 232, 232));
		 pnlTim.setBackground(Color.CYAN);
		 pnlTim.add(box.createVerticalStrut(40));
		 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 //điêu chỉnh kích thướt theo lblSDT
		
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// phần add với action 
		btnHuongDanSD.addActionListener(this);
		
		
		btnSinhVien.addActionListener(this);
		btnTKDoTuoiDangThue.addActionListener(this);
		btnTKSLSVTheoQuan.addActionListener(this);
		btnThoat.addActionListener(this);
		btnThongKe.addActionListener(this);
		btnThueTro.addActionListener(this);
		//btnTim.addActionListener(this);
		btnTro.addActionListener(this);
		//btnTKTop5TroDuocThueNhieu.addActionListener(this);
		btnTKGioiTinh.addActionListener(this);
		//txt8.addActionListener(this);
		//SinhVien_Dao dao = new SinhVien_Dao();
		
		
		lblTongSo.setPreferredSize(lblMaNV.getPreferredSize());
		lblGioiTinh.setPreferredSize(lblMaNV.getPreferredSize());
		lblNgaySinh.setPreferredSize(lblMaNV.getPreferredSize());
		lblSLNhoNhat.setPreferredSize(lblMaNV.getPreferredSize());
		lblSLNhieuNhat.setPreferredSize(lblMaNV.getPreferredSize());
		lblKhoa.setPreferredSize(lblMaNV.getPreferredSize());
		lblQueQuan.setPreferredSize(lblMaNV.getPreferredSize());
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		txtTongSo.setText("Tổng số sinh viên là: " + listsv.size());
		txt1.setText("Số sinh viên tuổi 18: " + slTuoi18);
		txt2.setText("Số sinh viên tuổi 19: " + slTuoi19);
		txt3.setText("Số sinh viên tuổi 20: " + slTuoi20);
		txt4.setText("Số sinh viên tuổi 21: " + slTuoi21);
		txt5.setText("Số sinh viên tuổi 22: " + slTuoi22);
		txt6.setText("Số sinh viên tuổi 23: " + slTuoi23);
		txt7.setText("Số sinh viên tuổi 24: " + slTuoi24);
		
		setVisible(true);
	
	}

	// Phương thức đọc hình ảnh từ file
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
    	  JOptionPane.showMessageDialog(null, "Lỗi hình ảnh");
      }
  }
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		SinhVien_Dao daosv = new SinhVien_Dao();
		
		
		if(ob.equals(btnHuongDanSD))
		{
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
		else if(ob.equals(btnNhanVien))
		{
			removeAll();
			add(new GD_QLNhanVien());
			repaint();
			revalidate();
		}
		
		else if(ob.equals(btnThoat))
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
		else if(ob.equals(btnSinhVien))
		{
			removeAll();
			add(new GD_QuanLySinhVien());
			repaint();
			revalidate();
		}
		else if(ob.equals(btnThongKe))
		{
			removeAll();
			add(new GD_ThongKe());
			repaint();
			revalidate();
		}
		else if(ob.equals(btnThueTro))
		{
			removeAll();
			add(new GD_ThongTinThueTro());
			repaint();
			revalidate();
		}
		
		else if(ob.equals(btnTro))
		{
			EventQueue.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					try {
						removeAll();
						add(new GD_QuanLyTro());
						repaint();
						revalidate();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					
				}
			});
			
		}
		
		if(ob.equals(btnTKSLSVTheoQuan) && TKQuan <1)
		{
			txtTongSo.setText("");
    		txt1.setText("");
    		txt2.setText("");
    		txt3.setText("");
    		txt4.setText("");
    		txt5.setText("");
    		txt6.setText("");
    		txt7.setText("");
//			pnlFormTim.removeAll();
//			bcenTable.removeAll();
			
			TKQuan++;
			//Tổng 
			tabbedPane.setSelectedIndex(2);
			//tabbedPane.setSelectedIndex(3);
		        TKTinhTrangThue=0;
		        TKTuoi=0;
		   
		       
		        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				NhaTro_Dao daont = new NhaTro_Dao();
				List<NhaTro> listnt = daont.layTatCaBang();
				
				listnt.forEach(v -> {
				if(v.getDiaChiTro().getTenQuan().equals("Quận 1"))
				{
					q1++;	
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Quận Bình Tân"))
				{
					qbt++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Quận 2"))
				{
					q2++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Quận Bình Thạnh"))
				{
					qbth++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Quận Gò Vấp"))
				{
					qgv++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Quận 4"))
				{
					q4++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Quận Phú Nhuận"))
				{
					qpnh++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Quận 5"))
				{
					q5++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Quận Tân Bình"))
				{
					qtb++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Quận 6"))
				{
					q6++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Quận Tân Phú"))
				{
					qtph++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Quận 7"))
				{
					q7++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Quận Thủ Đức"))
				{
					qtd++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Quận 8"))
				{
					q8++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Huyện Bình Chánh"))
				{
					qbch++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Quận 9"))
				{
					q9++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Huyện Cần Giờ"))
				{
					qcg++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Quận 10"))
				{
					q10++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Huyện Củ Chi"))
				{
					qcch++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Quận 11"))
				{
					q11++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Huyện Hóc Môn"))
				{
					qhm++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Quận 12"))
				{
					q12++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Huyện Nhà Bè"))
				{
					qnhbe++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Quận 3"))
				{
					q3++;
				}
				});
				
		        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		        
				List<NhaTro> listntro = daont.ThongKeNhaTro();
				Map<String, Integer> maxQuan = new HashMap<String, Integer>();
				listntro.forEach(v -> {
				if(v.getDiaChiTro().getTenQuan().equals("Quận 1"))
				{
					q1++;	
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Quận Bình Tân"))
				{
					qbt++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Quận 2"))
				{
					q2++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Quận Bình Thạnh"))
				{
					qbth++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Quận Gò Vấp"))
				{
					qgv++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Quận 4"))
				{
					q4++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Quận Phú Nhuận"))
				{
					qpnh++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Quận 5"))
				{
					q5++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Quận Tân Bình"))
				{
					qtb++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Quận 6"))
				{
					q6++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Quận Tân Phú"))
				{
					qtph++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Quận 7"))
				{
					q7++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Quận Thủ Đức"))
				{
					qtd++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Quận 8"))
				{
					q8++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Huyện Bình Chánh"))
				{
					qbch++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Quận 9"))
				{
					q9++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Huyện Cần Giờ"))
				{
					qcg++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Quận 10"))
				{
					q10++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Huyện Củ Chi"))
				{
					qcch++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Quận 11"))
				{
					q11++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Huyện Hóc Môn"))
				{
					qhm++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Quận 12"))
				{
					q12++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Huyện Nhà Bè"))
				{
					qnhbe++;
				}
				else if(v.getDiaChiTro().getTenQuan().equals("Quận 3"))
				{
					q3++;
				}
				});
				
				maxQuan.put("Quận 1", q1);
				maxQuan.put("Quận Bình Tân", qbt);
				maxQuan.put("Quận 2", q2);
				maxQuan.put("Quận Bình Thạnh", qbth);
				maxQuan.put("Quận Gò Vấp", qgv);
				maxQuan.put("Quận Phú Nhuận", qpnh);
				maxQuan.put("Quận Tân Bình", qtb);
				maxQuan.put("Quận 6", q6);
				maxQuan.put("Quận Tân Phú", qtph);
				maxQuan.put("Quận 7", q7);
				maxQuan.put("Quận Thủ Đức", qtd);
				maxQuan.put("Quận 8", q8);
				maxQuan.put("Huyện Bình Chánh", qbch);
				maxQuan.put("Quận 9", q9);
				maxQuan.put("Huyện Cần Giờ", qcg);
				maxQuan.put("Quận 10", q10);
				maxQuan.put("Huyện Củ Chi", qcch);
				maxQuan.put("Quận 11", q11);
				maxQuan.put("Huyện Hóc Môn", qhm);
				maxQuan.put("Quận 12", q12);
				maxQuan.put("Huyện Nhà Bè", qnhbe);
				maxQuan.put("Quận 3", q3);
				maxQuan.put("Quận 4", q4);
				
				
				Map<String, Integer> top5max = new HashMap<String, Integer>(); 
				
				maxQuan.forEach((k,v) -> {
					if(maxx1 <= v)
					{
						maxx1 = v;
						key1=k;
					}
				});
				top5max.put(key1, maxx1);
				maxQuan.remove(key1, maxx1);
				
				////////////////////////////////////////////////////////////////////////////////////////////////////////
				maxQuan.forEach((k,v) -> {
					if(maxx2 <= v)
					{
						maxx2 = v;
						key2=k;
					}
				});
				top5max.put(key2, maxx2);
				maxQuan.remove(key2, maxx2);
				
		////////////////////////////////////////////////////////////////////////////////////////////////////////
				maxQuan.forEach((k,v) -> {
					if(maxx3 <= v)
					{
						maxx3 = v;
						key3=k;
					}
				});
				top5max.put(key3, maxx3);
				maxQuan.remove(key3, maxx3);
			
		////////////////////////////////////////////////////////////////////////////////////////////////////////
				maxQuan.forEach((k,v) -> {
					if(maxx4 <= v)
					{
						maxx4 = v;
						key4=k;
					}
				});
				top5max.put(key4, maxx4);
				maxQuan.remove(key4, maxx4);
		////////////////////////////////////////////////////////////////////////////////////////////////////////
				maxQuan.forEach((k,v) -> {
					if(maxx5 <= v)
					{
						maxx5 = v;
						key5=k;
					}
				});
				top5max.put(key5, maxx5);
				maxQuan.remove(key5, maxx5);
		        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		        txtTongSo.setText("Top 5 quận là: " +key1+" = "+ maxx1);
	    		txt1.setText("Top 5 quận là: " +key2+" = "+ maxx2);
	    		txt2.setText("Top 5 quận là: " +key3+" = "+ maxx3);
	    		txt3.setText("Top 5 quận là: " +key4+" = "+ maxx4);
	    		txt4.setText("Top 5 quận là: " +key5+" = "+ maxx5);
	    		txt5.setText("Không");
	    		txt6.setText("Không");
	    		txt7.setText("Không");
		}
		
		if(ob.equals(btnTKDoTuoiDangThue) && TKTuoi <1)
		{
			TKTuoi++;
			txtTongSo.setText("");
    		txt1.setText("");
    		txt2.setText("");
    		txt3.setText("");
    		txt4.setText("");
    		txt5.setText("");
    		txt6.setText("");
    		txt7.setText("");
//			pnlFormTim.removeAll();
//			bcenTable.removeAll();
			TKTuoi++;
			//Độ tuổi TB thuê
//			XYDataset datasetTuoi = (XYDataset) createDatasetAge();
//	        JFreeChart chart = createChartAge(datasetTuoi);
//			 ChartPanel chartPanel = new ChartPanel(chart);
//		        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
//		        chartPanel.setBackground(Color.white);
//		        bcenTable.add(chartPanel);
			tabbedPane.setSelectedIndex(0);
		        TKQuan=0;
		        TKTinhTrangThue=0;
		        
		      //SinhVien_Dao daosv = new SinhVien_Dao();
		    	List<SinhVien> listsv = daosv .layTatCaBangQL();
		    		
		    	//	txtTongSo.setText("Tổng số sinh viên là: " + listsv.size());
		    		//List<Integer> listMaxSV = new ArrayList<Integer>();
		    		List<SinhVien> list18 = new ArrayList<SinhVien>();
		    		listsv.forEach(v -> {
		    			if((LocalDate.now().getYear()-v.getNgaySinh().getYear())==18)
		    			{
		    				list18.add(v);
		    				slTuoi18++;
		    			}
		    		});
		    		
		    		
		    		List<SinhVien> list19 = new ArrayList<SinhVien>();
		    		listsv.forEach(v -> {
		    			if((LocalDate.now().getYear()-v.getNgaySinh().getYear())==19)
		    			{
		    				list19.add(v);
		    				slTuoi19++;

		    			}
		    		});
		    		
		    		List<SinhVien> list20 = new ArrayList<SinhVien>();
		    		listsv.forEach(v -> {
		    			if((LocalDate.now().getYear()-v.getNgaySinh().getYear())==20)
		    			{
		    				list20.add(v);
		    				slTuoi20++;

		    			}
		    		});
		    		
		    		List<SinhVien> list21 = new ArrayList<SinhVien>();
		    		listsv.forEach(v -> {
		    			if((LocalDate.now().getYear()-v.getNgaySinh().getYear())==21)
		    			{
		    				list21.add(v);
		    				slTuoi21++;
		    			}
		    		});
		    		
		    		List<SinhVien> list22 = new ArrayList<SinhVien>();
		    		listsv.forEach(v -> {
		    			if((LocalDate.now().getYear()-v.getNgaySinh().getYear())==22)
		    			{
		    				list22.add(v);
		    				slTuoi22++;
		    			}
		    		});
		    		
		    		List<SinhVien> list23 = new ArrayList<SinhVien>();
		    		listsv.forEach(v -> {
		    			if((LocalDate.now().getYear()-v.getNgaySinh().getYear())==23)
		    			{
		    				list23.add(v);
		    				slTuoi23++;
		    			}
		    		});
		    		
		    		List<SinhVien> list24 = new ArrayList<SinhVien>();
		    		listsv.forEach(v -> {
		    			if((LocalDate.now().getYear()-v.getNgaySinh().getYear())==24)
		    			{
		    				
		    				list24.add(v);
		    				slTuoi24++;
		    			}
		    		});
		    		
		    		List<SinhVien> listkhac = new ArrayList<SinhVien>();
		    		listsv.forEach(v -> {
		    			if((LocalDate.now().getYear()-v.getNgaySinh().getYear())>24)
		    			{
		    				tongTuoiTBKhac = tongTuoiTBKhac + (LocalDate.now().getYear()-v.getNgaySinh().getYear());
		    				
		    				listkhac.add(v);
		    				slTuoiKhac++;
		    			}
		    		});
		    		
		    		if(slTuoiKhac>0)
		    		{
		    			tuoiTBKhac = tongTuoiTBKhac/slTuoiKhac;
		    		}
		    		
		    		txtTongSo.setText("Tổng số sinh viên là: " + listsv.size());
		    		txt1.setText("Số sinh viên tuổi 18: " + slTuoi18);
		    		txt2.setText("Số sinh viên tuổi 19: " + slTuoi19);
		    		txt3.setText("Số sinh viên tuổi 20: " + slTuoi20);
		    		txt4.setText("Số sinh viên tuổi 21: " + slTuoi21);
		    		txt5.setText("Số sinh viên tuổi 22: " + slTuoi22);
		    		txt6.setText("Số sinh viên tuổi 23: " + slTuoi23);
		    		txt7.setText("Số sinh viên tuổi 24: " + slTuoi24);
		       
		}
		
		if(ob.equals(btnTKGioiTinh) && TKTinhTrangThue <1)
		{
			TKGT++;
			//Độ tuổi TB thuê
			txtTongSo.setText("");
    		txt1.setText("");
    		txt2.setText("");
    		txt3.setText("");
    		txt4.setText("");
    		txt5.setText("");
    		txt6.setText("");
    		txt7.setText("");
//			pnlFormTim.removeAll();
//			bcenTable.removeAll();
			
			TKTuoi++;
			//Độ tuổi TB thuê
			tabbedPane.setSelectedIndex(1);
			
				
			
	        
		 	   List<SinhVien> listNam = daosv.layTatCaNam();
		 	   List<SinhVien> listNu = daosv.layTatCaNu();
		 	   int slNam = listNam.size();
		 	   int slNu = listNu.size();
		 	   int tog = (slNam+slNu);
		 	   float perNam = ((slNam)/(tog));

			        TKQuan=0;
			        TKTinhTrangThue=0;
			        TKTuoi=0;

			        NhaTro_Dao daont = new NhaTro_Dao();
			    	   List<NhaTro> listDagThue = daont.ThongKeNhaTroDangThue();
			    	   List<NhaTro> listDaChuyenDi = daont.ThongKeNhaTroDaChuyenDi();
			    	   int slDagThue = listDagThue.size();
				 	   int slDaChuyen = listDaChuyenDi.size();
				 	   int togd = (slDagThue+slDaChuyen);
		 	 //  JOptionPane.showMessageDialog(null, "% la: " + perNam );
		 	  txtTongSo.setText("Nam chiếm : " +slNam+"/"+tog+"%");
	    		txt1.setText("Nữ chiếm : " +slNu+"/"+tog+"%");
	    		txt2.setText("Số lượng Nam là: " + listNam.size());
	    		txt3.setText("Số lượng Nữ là: " + slNu);
	    		txt4.setText("Đang Thuê Chiếm: " + slDagThue+"/"+togd+"%");
	    		txt5.setText("Số lượng đang thuê là: " + listDagThue.size());
	    		txt6.setText("Đã chuyển đi chiếm: "+ slDaChuyen+"/"+togd+"%");
	    		txt7.setText("Số lượng Đã chuyển đi là " + listDaChuyenDi.size());
		}
		
	}
	//Quận 1,Quận Bình Tân,Quận 2,Quận Bình Thạnh,Quận 3,Quận Gò Vấp,Quận 4,Quận Phú Nhuận,Quận 5,Quận Tân Bình,Quận 6,Quận Tân Phú,Quận 7,
	//Quận Thủ Đức,Quận 8,Huyện Bình Chánh,Quận 9,Huyện Cần Giờ,Quận 10,Huyện Củ Chi,Quận 11,Huyện Hóc Môn,Quận 12,Huyện Nhà Bè
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private CategoryDataset createDatasetQuanI() {

		NhaTro_Dao daont = new NhaTro_Dao();
		List<NhaTro> listnt = daont.ThongKeNhaTro();
		
		listnt.forEach(v -> {
		if(v.getDiaChiTro().getTenQuan().equals("Quận 1"))
		{
			q1++;	
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận Bình Tân"))
		{
			qbt++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận 2"))
		{
			q2++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận Bình Thạnh"))
		{
			qbth++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận Gò Vấp"))
		{
			qgv++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận 4"))
		{
			q4++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận Phú Nhuận"))
		{
			qpnh++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận 5"))
		{
			q5++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận Tân Bình"))
		{
			qtb++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận 6"))
		{
			q6++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận Tân Phú"))
		{
			qtph++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận 7"))
		{
			q7++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận Thủ Đức"))
		{
			qtd++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận 8"))
		{
			q8++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Huyện Bình Chánh"))
		{
			qbch++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận 9"))
		{
			q9++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Huyện Cần Giờ"))
		{
			qcg++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận 10"))
		{
			q10++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Huyện Củ Chi"))
		{
			qcch++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận 11"))
		{
			q11++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Huyện Hóc Môn"))
		{
			qhm++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận 12"))
		{
			q12++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Huyện Nhà Bè"))
		{
			qnhbe++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận 3"))
		{
			q3++;
		}
		});
		
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
  //      NhaTro_Dao daont = new NhaTro_Dao();
		List<NhaTro> listntro = daont.ThongKeNhaTro();
		Map<String, Integer> maxQuan = new HashMap<String, Integer>();
		listntro.forEach(v -> {
		if(v.getDiaChiTro().getTenQuan().equals("Quận 1"))
		{
			q1++;	
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận Bình Tân"))
		{
			qbt++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận 2"))
		{
			q2++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận Bình Thạnh"))
		{
			qbth++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận Gò Vấp"))
		{
			qgv++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận 4"))
		{
			q4++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận Phú Nhuận"))
		{
			qpnh++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận 5"))
		{
			q5++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận Tân Bình"))
		{
			qtb++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận 6"))
		{
			q6++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận Tân Phú"))
		{
			qtph++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận 7"))
		{
			q7++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận Thủ Đức"))
		{
			qtd++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận 8"))
		{
			q8++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Huyện Bình Chánh"))
		{
			qbch++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận 9"))
		{
			q9++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Huyện Cần Giờ"))
		{
			qcg++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận 10"))
		{
			q10++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Huyện Củ Chi"))
		{
			qcch++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận 11"))
		{
			q11++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Huyện Hóc Môn"))
		{
			qhm++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận 12"))
		{
			q12++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Huyện Nhà Bè"))
		{
			qnhbe++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận 3"))
		{
			q3++;
		}
		});
		
		maxQuan.put("Quận 1", q1);
		maxQuan.put("Quận Bình Tân", qbt);
		maxQuan.put("Quận 2", q2);
		maxQuan.put("Quận Bình Thạnh", qbth);
		maxQuan.put("Quận Gò Vấp", qgv);
		maxQuan.put("Quận Phú Nhuận", qpnh);
		maxQuan.put("Quận Tân Bình", qtb);
		maxQuan.put("Quận 6", q6);
		maxQuan.put("Quận Tân Phú", qtph);
		maxQuan.put("Quận 7", q7);
		maxQuan.put("Quận Thủ Đức", qtd);
		maxQuan.put("Quận 8", q8);
		maxQuan.put("Huyện Bình Chánh", qbch);
		maxQuan.put("Quận 9", q9);
		maxQuan.put("Huyện Cần Giờ", qcg);
		maxQuan.put("Quận 10", q10);
		maxQuan.put("Huyện Củ Chi", qcch);
		maxQuan.put("Quận 11", q11);
		maxQuan.put("Huyện Hóc Môn", qhm);
		maxQuan.put("Quận 12", q12);
		maxQuan.put("Huyện Nhà Bè", qnhbe);
		maxQuan.put("Quận 3", q3);
		maxQuan.put("Quận 4", q4);
		
		
		Map<String, Integer> top5max = new HashMap<String, Integer>(); 
		
		maxQuan.forEach((k,v) -> {
			if(maxx1 <= v)
			{
				maxx1 = v;
				key1=k;
			}
		});
		top5max.put(key1, maxx1);
		maxQuan.remove(key1, maxx1);
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////
		maxQuan.forEach((k,v) -> {
			if(maxx2 <= v)
			{
				maxx2 = v;
				key2=k;
			}
		});
		top5max.put(key2, maxx2);
		maxQuan.remove(key2, maxx2);
		
////////////////////////////////////////////////////////////////////////////////////////////////////////
		maxQuan.forEach((k,v) -> {
			if(maxx3 <= v)
			{
				maxx3 = v;
				key3=k;
			}
		});
		top5max.put(key3, maxx3);
		maxQuan.remove(key3, maxx3);
	
////////////////////////////////////////////////////////////////////////////////////////////////////////
		maxQuan.forEach((k,v) -> {
			if(maxx4 <= v)
			{
				maxx4 = v;
				key4=k;
			}
		});
		top5max.put(key4, maxx4);
		maxQuan.remove(key4, maxx4);
////////////////////////////////////////////////////////////////////////////////////////////////////////
		maxQuan.forEach((k,v) -> {
			if(maxx5 <= v)
			{
				maxx5 = v;
				key5=k;
			}
		});
		top5max.put(key5, maxx5);
		maxQuan.remove(key5, maxx5);
        
        
        dataset.setValue(maxx1, "Số lượng sinh viên", key1);
        dataset.setValue(maxx2, "Số lượng sinh viên", key2);
        dataset.setValue(maxx3, "Số lượng sinh viên",key3);
        dataset.setValue(maxx4, "Số lượng sinh viên", key4);
        dataset.setValue(maxx5, "Số lượng sinh viên", key5);


        return dataset;
    }

    private JFreeChart createChartQuanI(CategoryDataset dataset) {

        JFreeChart barChart = ChartFactory.createBarChart(
                "Thống kê thông tin ở trọ sinh viên theo quận",
                "Tên quận",
                "Số lượng sinh viên",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        return barChart;
    }
	
	private DefaultPieDataset createDatasetQuan() {
		NhaTro_Dao daont = new NhaTro_Dao();
		List<NhaTro> listnt = daont.ThongKeNhaTro();
		Map<String, Integer> maxQuan = new HashMap<String, Integer>();
		listnt.forEach(v -> {
		if(v.getDiaChiTro().getTenQuan().equals("Quận 1"))
		{
			q1++;	
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận Bình Tân"))
		{
			qbt++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận 2"))
		{
			q2++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận Bình Thạnh"))
		{
			qbth++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận Gò Vấp"))
		{
			qgv++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận 4"))
		{
			q4++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận Phú Nhuận"))
		{
			qpnh++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận 5"))
		{
			q5++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận Tân Bình"))
		{
			qtb++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận 6"))
		{
			q6++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận Tân Phú"))
		{
			qtph++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận 7"))
		{
			q7++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận Thủ Đức"))
		{
			qtd++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận 8"))
		{
			q8++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Huyện Bình Chánh"))
		{
			qbch++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận 9"))
		{
			q9++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Huyện Cần Giờ"))
		{
			qcg++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận 10"))
		{
			q10++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Huyện Củ Chi"))
		{
			qcch++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận 11"))
		{
			q11++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Huyện Hóc Môn"))
		{
			qhm++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận 12"))
		{
			q12++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Huyện Nhà Bè"))
		{
			qnhbe++;
		}
		else if(v.getDiaChiTro().getTenQuan().equals("Quận 3"))
		{
			q3++;
		}
		});
		
		maxQuan.put("Quận 1", q1);
		maxQuan.put("Quận Bình Tân", qbt);
		maxQuan.put("Quận 2", q2);
		maxQuan.put("Quận Bình Thạnh", qbth);
		maxQuan.put("Quận Gò Vấp", qgv);
		maxQuan.put("Quận Phú Nhuận", qpnh);
		maxQuan.put("Quận Tân Bình", qtb);
		maxQuan.put("Quận 6", q6);
		maxQuan.put("Quận Tân Phú", qtph);
		maxQuan.put("Quận 7", q7);
		maxQuan.put("Quận Thủ Đức", qtd);
		maxQuan.put("Quận 8", q8);
		maxQuan.put("Huyện Bình Chánh", qbch);
		maxQuan.put("Quận 9", q9);
		maxQuan.put("Huyện Cần Giờ", qcg);
		maxQuan.put("Quận 10", q10);
		maxQuan.put("Huyện Củ Chi", qcch);
		maxQuan.put("Quận 11", q11);
		maxQuan.put("Huyện Hóc Môn", qhm);
		maxQuan.put("Quận 12", q12);
		maxQuan.put("Huyện Nhà Bè", qnhbe);
		maxQuan.put("Quận 3", q3);
		maxQuan.put("Quận 4", q4);
		
		
		Map<String, Integer> top5max = new HashMap<String, Integer>(); 
		
		maxQuan.forEach((k,v) -> {
			if(maxx1 <= v)
			{
				maxx1 = v;
				key1=k;
			}
		});
		top5max.put(key1, maxx1);
		maxQuan.remove(key1, maxx1);
		////////////////////////////////////////////////////////////////////////////////////////////////////////
		maxQuan.forEach((k,v) -> {
			if(maxx2 <= v)
			{
				maxx2 = v;
				key2=k;
			}
		});
		top5max.put(key2, maxx2);
		maxQuan.remove(key2, maxx2);
////////////////////////////////////////////////////////////////////////////////////////////////////////
		maxQuan.forEach((k,v) -> {
			if(maxx3 <= v)
			{
				maxx3 = v;
				key3=k;
			}
		});
		top5max.put(key3, maxx3);
		maxQuan.remove(key3, maxx3);
////////////////////////////////////////////////////////////////////////////////////////////////////////
		maxQuan.forEach((k,v) -> {
			if(maxx4 <= v)
			{
				maxx4 = v;
				key4=k;
			}
		});
		top5max.put(key4, maxx4);
		maxQuan.remove(key4, maxx4);
////////////////////////////////////////////////////////////////////////////////////////////////////////
		maxQuan.forEach((k,v) -> {
			if(maxx5 <= v)
			{
				maxx5 = v;
				key5=k;
			}
		});
		top5max.put(key5, maxx5);
		maxQuan.remove(key5, maxx5);
		
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Quận 1",q1);
      dataset.setValue("Quận Bình Tân",qbt);
      dataset.setValue("Quận 2",q2);
      dataset.setValue("Quận Bình Thạnh",qbth);
      dataset.setValue("Quận 3",q3);
      dataset.setValue("Quận Gò Vấp",qgv);
      dataset.setValue("Quận 4",q4);
      dataset.setValue("Quận Phú Nhuận",qpnh);
      dataset.setValue("Quận 5",q5);
      dataset.setValue("Quận Tân Bình",qtb);
      dataset.setValue("Quận 6",q6);
      dataset.setValue("Quận Tân Phú",qtph);
      dataset.setValue("Quận 7",q7);
      dataset.setValue("Quận Thủ Đức",qtd);
      dataset.setValue("Quận 8",q8);
      dataset.setValue("Quận Bình Chánh",qbch);
      dataset.setValue("Quận 9",q9);
      dataset.setValue("Quận Cần Giờ",qcg);
      dataset.setValue("Quận 10",q10);
      dataset.setValue("Quận Củ Chi",qcch);
      dataset.setValue("Quận 11",q11);
      dataset.setValue("Quận Hóc Môn",qhm);
      dataset.setValue("Quận 12",q12);
      dataset.setValue("Quận Nhà Bè",qnhbe);

       return dataset;
   }

   private JFreeChart createChartQuan(DefaultPieDataset dataset) {

       JFreeChart pieChart = ChartFactory.createPieChart(
               "Thống kê Sinh viên theo Quận",
               dataset,
               false, true, false);

       return pieChart;
   }
   
   

   private XYDataset createDatasetAge() {

	   
	   SinhVien_Dao daosv = new SinhVien_Dao();
	List<SinhVien> listsv = daosv .layTatCaBangQL();
		
		
		//List<Integer> listMaxSV = new ArrayList<Integer>();
		List<SinhVien> list18 = new ArrayList<SinhVien>();
		listsv.forEach(v -> {
			if((LocalDate.now().getYear()-v.getNgaySinh().getYear())==18)
			{
				list18.add(v);
				slTuoi18++;
			}
		});
		
		
		List<SinhVien> list19 = new ArrayList<SinhVien>();
		listsv.forEach(v -> {
			if((LocalDate.now().getYear()-v.getNgaySinh().getYear())==19)
			{
				list19.add(v);
				slTuoi19++;

			}
		});
		
		List<SinhVien> list20 = new ArrayList<SinhVien>();
		listsv.forEach(v -> {
			if((LocalDate.now().getYear()-v.getNgaySinh().getYear())==20)
			{
				list20.add(v);
				slTuoi20++;

			}
		});
		
		List<SinhVien> list21 = new ArrayList<SinhVien>();
		listsv.forEach(v -> {
			if((LocalDate.now().getYear()-v.getNgaySinh().getYear())==21)
			{
				list21.add(v);
				slTuoi21++;
			}
		});
		
		List<SinhVien> list22 = new ArrayList<SinhVien>();
		listsv.forEach(v -> {
			if((LocalDate.now().getYear()-v.getNgaySinh().getYear())==22)
			{
				list22.add(v);
				slTuoi22++;
			}
		});
		
		List<SinhVien> list23 = new ArrayList<SinhVien>();
		listsv.forEach(v -> {
			if((LocalDate.now().getYear()-v.getNgaySinh().getYear())==23)
			{
				list23.add(v);
				slTuoi23++;
			}
		});
		
		List<SinhVien> list24 = new ArrayList<SinhVien>();
		listsv.forEach(v -> {
			if((LocalDate.now().getYear()-v.getNgaySinh().getYear())==24)
			{
				
				list24.add(v);
				slTuoi24++;
			}
		});
		
		List<SinhVien> listkhac = new ArrayList<SinhVien>();
		listsv.forEach(v -> {
			if((LocalDate.now().getYear()-v.getNgaySinh().getYear())>24)
			{
				tongTuoiTBKhac = tongTuoiTBKhac + (LocalDate.now().getYear()-v.getNgaySinh().getYear());
				
				listkhac.add(v);
				slTuoiKhac++;
			}
		});
		
		if(slTuoiKhac>0)
		{
			tuoiTBKhac = tongTuoiTBKhac/slTuoiKhac;
		}
		
		

       XYSeries series = new XYSeries("2021");
       
       series.add(18, slTuoi18);
       series.add(19, slTuoi19);
       series.add(20, slTuoi20);
       series.add(21, slTuoi21);
       series.add(22, slTuoi22);
       series.add(23, slTuoi23);
       series.add(24, slTuoi24);
       series.add(tuoiTBKhac, slTuoiKhac);

       XYSeriesCollection dataset = new XYSeriesCollection();
       dataset.addSeries(series);

       return dataset;
   }

   private JFreeChart createChartAge(XYDataset dataset) {

       JFreeChart chart = ChartFactory.createXYLineChart(
               "Tuổi trung bình",
               "Tuổi",
               "Số lượng",
               dataset,
               PlotOrientation.VERTICAL,
               true,
               true,
               false
       );

       XYPlot plot = chart.getXYPlot();

       XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
       renderer.setSeriesPaint(0, Color.RED);
       renderer.setSeriesStroke(0, new BasicStroke(2.0f));

       plot.setRenderer(renderer);
       plot.setBackgroundPaint(Color.white);

       plot.setRangeGridlinesVisible(true);
       plot.setRangeGridlinePaint(Color.BLACK);

       plot.setDomainGridlinesVisible(true);
       plot.setDomainGridlinePaint(Color.BLACK);

       chart.getLegend().setFrame(BlockBorder.NONE);

       chart.setTitle(new TextTitle("Thống kê độ tuổi thuê trọ trung bình",
                       new Font("Serif", java.awt.Font.BOLD, 18)
               )
       );

       return chart;
   }

   private DefaultPieDataset createDatasetGT() {
	   SinhVien_Dao daosv = new SinhVien_Dao();
	   List<SinhVien> listNam = daosv.layTatCaNam();
	   List<SinhVien> listNu = daosv.layTatCaNu();
	            DefaultPieDataset datasetGT = new DefaultPieDataset();
	           datasetGT.setValue("Nam", listNam.size());
	           datasetGT.setValue("Nữ", listNu.size());
           return datasetGT;
	       }
	   
	       private JFreeChart createChartGT(DefaultPieDataset dataset) {
	           JFreeChart pieChart = ChartFactory.createPieChart(
	                   "Thống kê theo giới tính",
	                   dataset,
	                   false, true, false);
	           return pieChart;
       }

	       private DefaultPieDataset createDatasetStateHire() {
	    	   NhaTro_Dao daont = new NhaTro_Dao();
	    	   List<NhaTro> listDagThue = daont.ThongKeNhaTroDangThue();
	    	   List<NhaTro> listDaChuyenDi = daont.ThongKeNhaTroDaChuyenDi();
	    	            DefaultPieDataset datasetStateHire = new DefaultPieDataset();
	    	           datasetStateHire.setValue("Đang thuê", listDagThue.size());
	    	           datasetStateHire.setValue("Đã chuyển nhà", listDaChuyenDi.size());
	               return datasetStateHire;
	    	       }
	    	   
	    	       private JFreeChart createChartStateHire(DefaultPieDataset dataset) {
	    	           JFreeChart pieChart = ChartFactory.createPieChart(
	    	                   "Thống kê trạng thái thuê trọ",
	    	                   dataset,
	    	                   false, true, false);
	    	           return pieChart;
	           }
	       
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	    	       private JPanel createPane1() {
	    	           JPanel panel = new JPanel();
	    	           panel.add(new JScrollPane(createTextArea(10, 40)));
	    	           return panel;
	    	       }
	    	    
	    	       /**
	    	        * create a JPanel contain a JLabel
	    	        */
	    	       private JPanel createJPanel(String text) {
	    	           JPanel panel = new JPanel(new GridLayout(1, 1));
	    	           JLabel lb = new JLabel(text);
	    	           lb.setHorizontalAlignment(JLabel.CENTER);
	    	           panel.add(lb);
	    	           return panel;
	    	       }
	    	    
	    	       /**
	    	        * create a JTextArea with rows and columns, two method setWrapStyleWord and
	    	        * setLineWrap make text can down line when text too long
	    	        */
	    	       private JTextArea createTextArea(int row, int col) {
	    	           JTextArea ta = new JTextArea(row, col);
	    	           ta.setWrapStyleWord(true);
	    	           ta.setLineWrap(true);
	    	           ta.setForeground(Color.BLUE);
	    	           return ta;
	    	       }
	    	    	       

}






