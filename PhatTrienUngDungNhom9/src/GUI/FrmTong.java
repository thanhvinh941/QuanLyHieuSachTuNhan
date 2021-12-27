package GUI;

import java.awt.EventQueue;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.CardLayout;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Toolkit;
import java.awt.Color;

public class FrmTong extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JDesktopPane desktopPane;
	private FrameHeThongTrangChu frameHeThongTrangchu;
	private FrameDanhMucNV frameDanhMucNV;
	private FrameDanhMucKH frameDanhMucKH;
	private FrameDanhMucSach frameDanhMucSP;
	private FrameDanhMucDCHT frameDanhMucDCHT;
	private FrameDanhMucDM frameDanhMucDM;
	private FrameDanhMucNCC frameDanhMucNCC;
	private FrameDanhMucNXB frameDanhMucNXB;
	private FrameDanhMucTG frameDanhMucTG;
	private FrameThongKeDT frameThongKeDT;
	private FrameThongKeKH frameThongKeKH;
	private FrameThongKeNV frameThongKeNV;
	private FrameThongKeSach frameThongKeSP;
	private FrameXuLyHoaDon frameXuLyHD;
	private FrameXuLyDonHang frameXuLyDH;
	private FrameTimKiemKH frameTimKiemKH;
	private FrameTimKiemNV frameTimKiemNV;
	private FrameTimKiemSach frameTimKiemSP;
	private FrameTimKiemDCHT frameTimKiemDCHT;
	private FrameHeThongDangKyCaLam frameHeThongDangKyCaLam;
	private JMenu mnTimKiem,mnHeThong,mnDanhMuc,mnXuLy,mnThongKe;
	private JMenuItem mntmDangKyCaNhanVien,mntmTrangChu,mntmDM_SP,mntmDM_DMSP,mntmDM_NV,mntmDM_KH,mntmDM_TG,mntmNewMenuItem,mntmDM_NXB,mntmDM_NCC, mntmTao_DH,mntmTao_HD,mntmTimKiem_KH,mntmTimKiem_NV,mntmNewMenuItem_1,mntmTimKiem_SP,mntmTK_DT,mntmTK_KH,mntmTK_SP,mntmTK_NV;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmTong frame = new FrmTong("QL1","Quản lý");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public FrmTong(String maNV,String chucVu) throws Exception {
		setForeground(UIManager.getColor("inactiveCaptionBorder"));
		//new
		setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setFocusCycleRoot(true);
		setFocusableWindowState(true);
		setUndecorated(false);
		//
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmTong.class.getResource("/image/bookstore.png")));
		setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
		setType(Type.POPUP);
		setTitle("Quản lý hiệu sách tư nhân");
		setBackground(UIManager.getColor("inactiveCaptionText"));
		setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0,  1299, 674);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 31, 1283, 644);
		contentPane.add(desktopPane);
		desktopPane.setLayout(null);

		JInternalFrame internalFrame = new JInternalFrame("New JInternalFrame");
		internalFrame.setBounds(-5, -26, 1292, 670);
		desktopPane.add(internalFrame);
		internalFrame.setVisible(true);
		
//		chechChucVu(chucVu);
//--==================Hệ thống==========================-

		frameHeThongTrangchu = new FrameHeThongTrangChu(maNV);
		desktopPane.add(frameHeThongTrangchu);
		frameHeThongTrangchu.setVisible(true);

		frameHeThongDangKyCaLam = new FrameHeThongDangKyCaLam();
		desktopPane.add(frameHeThongDangKyCaLam);

//--=====================Xử lý==========================--

		frameXuLyDH = new FrameXuLyDonHang(maNV);
		desktopPane.add(frameXuLyDH);

		frameXuLyHD = new FrameXuLyHoaDon(maNV);
		desktopPane.add(frameXuLyHD);

//--======================Danh muc==================--

		frameDanhMucNV = new FrameDanhMucNV();
		desktopPane.add(frameDanhMucNV);

		frameDanhMucKH = new FrameDanhMucKH();
		desktopPane.add(frameDanhMucKH);

		frameDanhMucSP = new FrameDanhMucSach();
		desktopPane.add(frameDanhMucSP);

		frameDanhMucDCHT = new FrameDanhMucDCHT();
		desktopPane.add(frameDanhMucDCHT);

		frameDanhMucDM = new FrameDanhMucDM();
		desktopPane.add(frameDanhMucDM);

		frameDanhMucNCC = new FrameDanhMucNCC();
		desktopPane.add(frameDanhMucNCC);
		
		frameDanhMucNXB = new FrameDanhMucNXB();
		desktopPane.add(frameDanhMucNXB);
		
		frameDanhMucTG = new FrameDanhMucTG();
		desktopPane.add(frameDanhMucTG);

//--=========================Thống kê===================--

		frameThongKeDT = new FrameThongKeDT();

		desktopPane.add(frameThongKeDT);

		frameThongKeKH = new FrameThongKeKH();
		desktopPane.add(frameThongKeKH);

		frameThongKeNV = new FrameThongKeNV();
		desktopPane.add(frameThongKeNV);

		frameThongKeSP = new FrameThongKeSach();
		desktopPane.add(frameThongKeSP);

//--==============================TimKiem-==================		

		frameTimKiemKH = new FrameTimKiemKH();
		desktopPane.add(frameTimKiemKH);

		frameTimKiemNV = new FrameTimKiemNV();
		desktopPane.add(frameTimKiemNV);

		frameTimKiemSP = new FrameTimKiemSach();
		desktopPane.add(frameTimKiemSP);

		frameTimKiemDCHT = new FrameTimKiemDCHT();
		desktopPane.add(frameTimKiemDCHT);

//--==============================Menu======================--

		anTatCa();
		
		JPanel pMenu = new JPanel();
		pMenu.setBounds(0, 0, 1186, 32);
		contentPane.add(pMenu);
		pMenu.setLayout(new CardLayout(0, 0));

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(UIManager.getColor("window"));
		pMenu.add(menuBar, "name_417659960722100");
//		=================================================================

		mnHeThong = new JMenu("H\u1EC7 Th\u1ED1ng");
		mnHeThong.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnHeThong.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuHeThong.png")));
		menuBar.add(mnHeThong);

		mntmTrangChu = new JMenuItem("Trang ch\u1EE7");
		mntmTrangChu.setBackground(new Color(135, 206, 250));
		mntmTrangChu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anTatCa();
				chinhMau();
				frameHeThongTrangchu.setVisible(true);
				mntmTrangChu.setBackground(new Color(135, 206, 250));
			}
		});
		mntmTrangChu.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuItemTrangChu.png")));
		mnHeThong.add(mntmTrangChu);

		mntmDangKyCaNhanVien = new JMenuItem("Đăng ký ca nhân viên");
		mntmDangKyCaNhanVien.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuItemHTDKCNV.png")));
		mntmDangKyCaNhanVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anTatCa();
				chinhMau();
				frameHeThongDangKyCaLam.setVisible(true);
				try {
					frameHeThongDangKyCaLam.showTableCA();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				mntmDangKyCaNhanVien.setBackground(new Color(135, 206, 250));
			}
		});
		mnHeThong.add(mntmDangKyCaNhanVien);

		JMenuItem mntmDangXuat = new JMenuItem("\u0110\u0103ng xu\u1EA5t");
		mntmDangXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmLogin frmLogin = new FrmLogin();
				frmLogin.pack();
				frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frmLogin.setBounds(300, 100, 800, 400);
				frmLogin.setFocusCycleRoot(true);
				frmLogin.setFocusableWindowState(true);
				frmLogin.setVisible(true);
				dispose();
			}
		});
		mntmDangXuat.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuItemDangXuat.png")));
		mnHeThong.add(mntmDangXuat);

		JMenuItem mntmThoat = new JMenuItem("Tho\u00E1t");
		mntmThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mntmThoat.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuItemThoat.png")));
		mnHeThong.add(mntmThoat);

//		==============================================================================
		mnDanhMuc = new JMenu("Danh mục");
		mnDanhMuc.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuDanhMuc.png")));
		menuBar.add(mnDanhMuc);

		mntmDM_DMSP = new JMenuItem("Loại sản phẩm");
		mntmDM_DMSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anTatCa();
				chinhMau();
				frameDanhMucDM.setVisible(true);
				mntmDM_DMSP.setBackground(new Color(135, 206, 250));
				try {
					frameDanhMucDM.loadDM();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntmDM_DMSP.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuItemDMSP.png")));
		mnDanhMuc.add(mntmDM_DMSP);

		mntmDM_SP = new JMenuItem("Sách");
		mntmDM_SP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anTatCa();
				chinhMau();
				frameDanhMucSP.setVisible(true);
				mntmDM_SP.setBackground(new Color(135, 206, 250));
				try {
					frameDanhMucSP.loadSP();
					frameDanhMucSP.loadNXB();
					frameDanhMucSP.loadTG();
					frameDanhMucSP.loadLSP();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntmDM_SP.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuItemDMS.png")));
		mnDanhMuc.add(mntmDM_SP);

		mntmNewMenuItem = new JMenuItem("Dụng cụ học tập");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anTatCa();
				chinhMau();
				frameDanhMucDCHT.setVisible(true);
				mntmNewMenuItem.setBackground(new Color(135, 206, 250));
				try {
					frameDanhMucDCHT.loadSP();
					frameDanhMucDCHT.loadNCC();
					frameDanhMucDCHT.loadLSP();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntmNewMenuItem.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuItemDMDCHT.png")));
		mnDanhMuc.add(mntmNewMenuItem);

		mntmDM_NCC = new JMenuItem("Nhà cung cấp");
		mntmDM_NCC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anTatCa();
				chinhMau();
				frameDanhMucNCC.setVisible(true);
				mntmDM_NCC.setBackground(new Color(135, 206, 250));
				try {
					frameDanhMucNCC.loadNCC();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntmDM_NCC.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuItemDMNCC.png")));
		mnDanhMuc.add(mntmDM_NCC);

		mntmDM_NXB = new JMenuItem("Nhà xuất bản");
		mntmDM_NXB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anTatCa();
				chinhMau();
				frameDanhMucNXB.setVisible(true);
				mntmDM_NXB.setBackground(new Color(135, 206, 250));
				try {
					frameDanhMucNXB.showTableNXB();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntmDM_NXB.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuItemDMNXB.png")));
		mnDanhMuc.add(mntmDM_NXB);

		mntmDM_TG = new JMenuItem("Tác giả");
		mntmDM_TG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anTatCa();
				chinhMau();
				frameDanhMucTG.setVisible(true);
				mntmDM_TG.setBackground(new Color(135, 206, 250));
				try {
					frameDanhMucTG.loadTG();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntmDM_TG.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuItemDMTG.png")));
		mnDanhMuc.add(mntmDM_TG);

		mntmDM_KH = new JMenuItem("Khách hàng");
		mntmDM_KH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anTatCa();
				chinhMau();
				frameDanhMucKH.setVisible(true);
				mntmDM_KH.setBackground(new Color(135, 206, 250));
				try {
					frameDanhMucKH.showTableKH();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntmDM_KH.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuItemQLKH.png")));
		mnDanhMuc.add(mntmDM_KH);

		mntmDM_NV = new JMenuItem("Nhân viên");
		mntmDM_NV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anTatCa();
				chinhMau();
				frameDanhMucNV.setVisible(true);
				mntmDM_NV.setBackground(new Color(135, 206, 250));
			}
		});
		mntmDM_NV.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuItemQLNV.png")));
		mnDanhMuc.add(mntmDM_NV);

//		=====================================================================

		mnXuLy = new JMenu("Xử lý");
		mnXuLy.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuXuLy.png")));
		menuBar.add(mnXuLy);

		mntmTao_HD = new JMenuItem("Thêm hóa đơn");
		mntmTao_HD.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuItemTaoHD.png")));
		mntmTao_HD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anTatCa();
				chinhMau();
				frameXuLyHD.setVisible(true);
				mntmTao_HD.setBackground(new Color(135, 206, 250));
			}
		});
		mnXuLy.add(mntmTao_HD);

		mntmTao_DH = new JMenuItem("Thêm đơn hàng");
		mntmTao_DH.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuItemTaoDH.png")));
		mntmTao_DH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anTatCa();
				chinhMau();
				frameXuLyDH.setVisible(true);
				mntmTao_DH.setBackground(new Color(135, 206, 250));
				try {
					frameXuLyDH.loadDH();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnXuLy.add(mntmTao_DH);

//		==========================================================

		mnTimKiem = new JMenu("Tìm kiếm thông tin");
		mnTimKiem.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuTimKiem.png")));
		menuBar.add(mnTimKiem);

		mntmTimKiem_SP = new JMenuItem("Tìm kiếm sách");
		mntmTimKiem_SP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anTatCa();
				chinhMau();
				frameTimKiemSP.setVisible(true);
				mntmTimKiem_SP.setBackground(new Color(135, 206, 250));
				try {
					frameTimKiemSP.loadSP();
					frameTimKiemSP.loadLSP();
					frameTimKiemSP.loadTG();
					frameTimKiemSP.loadNXB();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntmTimKiem_SP.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuItemTKS.png")));
		mnTimKiem.add(mntmTimKiem_SP);

		mntmNewMenuItem_1 = new JMenuItem("Tìm kiếm dụng cụ học tập");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anTatCa();
				chinhMau();
				frameTimKiemDCHT.setVisible(true);
				mntmNewMenuItem_1.setBackground(new Color(135, 206, 250));
				try {
					frameTimKiemDCHT.loadSP();
					frameTimKiemDCHT.loadNXB();;
					frameTimKiemDCHT.loadLSP();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntmNewMenuItem_1.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuItemDMDCHT.png")));
		mnTimKiem.add(mntmNewMenuItem_1);

		mntmTimKiem_NV = new JMenuItem("Tìm kiếm nhân viên theo ca");
		mntmTimKiem_NV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anTatCa();
				chinhMau();
				frameTimKiemNV.setVisible(true);
				mntmTimKiem_NV.setBackground(new Color(135, 206, 250));
			}
		});
		mntmTimKiem_NV.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuItemTimKiemNV.png")));
		mnTimKiem.add(mntmTimKiem_NV);

		mntmTimKiem_KH = new JMenuItem("Tìm kiếm khách hàng");
		mntmTimKiem_KH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anTatCa();
				chinhMau();
				frameTimKiemKH.setVisible(true);
				mntmTimKiem_KH.setBackground(new Color(135, 206, 250));
			}
		});
		mntmTimKiem_KH.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuItemTimKiemKH.png")));
		mnTimKiem.add(mntmTimKiem_KH);

//		=======================================================================

		mnThongKe = new JMenu("Th\u1ED1ng k\u00EA");
		mnThongKe.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuThongKe.png")));
		menuBar.add(mnThongKe);

		mntmTK_DT = new JMenuItem("Th\u1ED1ng k\u00EA doanh thu");
		mntmTK_DT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anTatCa();
				chinhMau();
				frameThongKeDT.setVisible(true);
				mntmTK_DT.setBackground(new Color(135, 206, 250));
				try {
					frameThongKeDT.loadHD();
					frameThongKeDT.khoiTaoTop();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntmTK_DT.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuItemTKDT.png")));
		mnThongKe.add(mntmTK_DT);

		mntmTK_KH = new JMenuItem("Th\u1ED1ng k\u00EA kh\u00E1ch h\u00E0ng");
		mntmTK_KH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anTatCa();
				chinhMau();
				frameThongKeKH.setVisible(true);
				mntmTK_KH.setBackground(new Color(135, 206, 250));
			}
		});
		mntmTK_KH.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuItemTKKH.png")));
		mnThongKe.add(mntmTK_KH);

		mntmTK_SP = new JMenuItem("Th\u1ED1ng k\u00EA s\u1EA3n ph\u1EA9m");
		mntmTK_SP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anTatCa();
				chinhMau();
				frameThongKeSP.setVisible(true);
				try {
				
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				mntmTK_SP.setBackground(new Color(135, 206, 250));
			}
		});
		mntmTK_SP.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuItemTKSP.png")));
		mnThongKe.add(mntmTK_SP);

		mntmTK_NV = new JMenuItem("Th\u1ED1ng k\u00EA nh\u00E2n vi\u00EAn");
		mntmTK_NV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anTatCa();
				chinhMau();
				frameThongKeNV.setVisible(true);
				mntmTK_NV.setBackground(new Color(135, 206, 250));
			}
		});
		mntmTK_NV.setIcon(new ImageIcon(FrmTong.class.getResource("/image/MenuItemTKNV.png")));
		mnThongKe.add(mntmTK_NV);
		frameHeThongTrangchu.setVisible(true);
		
		mntmTK_NV.setVisible(false);
	}

	void anTatCa() {
		frameHeThongTrangchu.setVisible(false);
		frameHeThongDangKyCaLam.setVisible(false);
		frameXuLyDH.setVisible(false);
		frameXuLyHD.setVisible(false);
		frameDanhMucDCHT.setVisible(false);
		frameDanhMucNV.setVisible(false);
		frameDanhMucKH.setVisible(false);
		frameDanhMucSP.setVisible(false);
		frameDanhMucDM.setVisible(false);
		frameDanhMucNCC.setVisible(false);
		frameDanhMucNXB.setVisible(false);
		frameDanhMucTG.setVisible(false);
		frameTimKiemDCHT.setVisible(true);
		frameTimKiemSP.setVisible(false);
		frameTimKiemNV.setVisible(false);
		frameTimKiemKH.setVisible(false);
		frameThongKeDT.setVisible(false);
		frameThongKeNV.setVisible(false);
		frameThongKeKH.setVisible(false);
		frameThongKeSP.setVisible(false);
	}
	void chinhMau() {
		mntmDangKyCaNhanVien.setBackground(new Color(240,240,240));
		mntmTrangChu.setBackground(new Color(240,240,240));
		mntmDM_SP.setBackground(new Color(240,240,240));
		mntmDM_DMSP.setBackground(new Color(240,240,240));
		mntmDM_NV.setBackground(new Color(240,240,240));
		mntmDM_KH.setBackground(new Color(240,240,240));
		mntmDM_TG.setBackground(new Color(240,240,240));
		mntmNewMenuItem.setBackground(new Color(240,240,240));
		mntmDM_NXB.setBackground(new Color(240,240,240));
		mntmDM_NCC.setBackground(new Color(240,240,240));
		mntmTao_DH.setBackground(new Color(240,240,240));
		mntmTao_HD.setBackground(new Color(240,240,240));
		mntmTimKiem_KH.setBackground(new Color(240,240,240));
		mntmTimKiem_NV.setBackground(new Color(240,240,240));
		mntmNewMenuItem_1.setBackground(new Color(240,240,240));
		mntmTimKiem_SP.setBackground(new Color(240,240,240));
		mntmTK_DT.setBackground(new Color(240,240,240));
		mntmTK_KH.setBackground(new Color(240,240,240));
		mntmTK_SP.setBackground(new Color(240,240,240));
		mntmTK_NV.setBackground(new Color(240,240,240));
	}
	
	void chechChucVu(String nhanVien) {
		if(nhanVien.equalsIgnoreCase("Nhân viên")) {
			mntmDangKyCaNhanVien.setVisible(false);
			mnThongKe.setVisible(false);
			mntmDM_NV.setVisible(false);			
		}else {
			mntmDangKyCaNhanVien.setVisible(true);
			mnThongKe.setVisible(true);
			mntmDM_NV.setVisible(true);	
		}
	}
}
