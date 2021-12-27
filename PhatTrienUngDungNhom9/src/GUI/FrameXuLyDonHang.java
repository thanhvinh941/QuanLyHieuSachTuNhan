package GUI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Connect.ConnectDB;
import Dao.CTHoaDonDao;
import Dao.DonHangDao;
import Dao.HoaDonDao;
import Dao.KhachHangDao;
import Dao.LoaiSanPhamDao;
import Dao.NhanVienDao;
import Dao.SanPhamDao;
import Dao.TacGiaDao;
import Entity.CTHoaDon;
import Entity.DonHang;
import Entity.HoaDon;
import Entity.KhachHang;
import Entity.LoaiSanPham;
import Entity.NhanVien;
import Entity.SanPham;
import Entity.TacGia;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Locale;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JCheckBox;

public class FrameXuLyDonHang extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
	private JTable table;
	private JScrollPane scpTimKH;
	private JTable tableTimKH;
	private DefaultTableModel tableModel1;
	private JTextField txtMaSP;
	private JTextField txtSoluong;
	private JTextField txtGiaThanh;
	private JTextField txtThanhTien;
	private JTextField txtTenKH;
	private JTextField txtMaKH;
	private JTextField txtSDT;
	private JTextField txtDiaChi;
	private JButton btnLuu;
	private JButton btnThemSach;
	private JButton btnThemDungCu;
	private JButton btnXoaTrang;
	private JButton btnIn;
	private JButton btnLuh;
	private JButton btnTaoHD;
	private JButton btnXoaSP;
	private JButton btnCapNhatSP;
	private static int Enter;
	private JPanel panel;
	private JLabel lblHD;
	private JLabel lblThongBao;
	private JLabel lblThongBaoSoLuong, lblTongThanTien;
	private JCheckBox chckbxNewCheckBox;
	private JComboBox<String> cbbLoaiSP, comboBox, cbbTG;
	private JList<String> list;
	private DefaultListModel<String> listModel;
	private LocalDate day = LocalDate.now();
	private CTHoaDonDao ctdhDao = new CTHoaDonDao();
	private SanPhamDao spDao = new SanPhamDao();
	private DonHangDao dhDao = new DonHangDao();
	private KhachHangDao khDao = new KhachHangDao();
	private NhanVienDao nvDao = new NhanVienDao();
	private HoaDonDao hdDao = new HoaDonDao();
	private NumberFormat currentLocale = NumberFormat.getInstance();
	private Locale localeVN = new Locale("vi", "VN");
	private NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
	/**
	 * Launch the application. Create the frame.
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public FrameXuLyDonHang(String maNV) throws ClassNotFoundException, SQLException {
		setFocusCycleRoot(true);
		setFocusable(true);
		setFocusCycleRoot(true);
		getContentPane().setEnabled(false);
		setResizable(true);
		setBounds(-5, -26, 1292, 670);
		JPanel pQL_HD = new JPanel();
		pQL_HD.setBackground(Color.WHITE);
		pQL_HD.setBounds(0, 0, 1186, 654);
		getContentPane().add(pQL_HD);
		pQL_HD.setLayout(null);

		String col1[] = { "Mã KH", "Tên KH", "SDT", "Địa chỉ" };
		tableModel1 = new DefaultTableModel(col1, 0);
		tableTimKH = new JTable(tableModel1);
		tableTimKH.setRowHeight(25);
		tableTimKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tableTimKH.getColumnModel().getColumn(0).setPreferredWidth(10);
		tableTimKH.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableTimKH.getColumnModel().getColumn(2).setPreferredWidth(25);
		tableTimKH.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				int rowSelect = tableTimKH.getSelectedRow();
				String maKH = (String) tableTimKH.getValueAt(rowSelect, 0);
				String tenKH = (String) tableTimKH.getValueAt(rowSelect, 1);
				String soDT = (String) tableTimKH.getValueAt(rowSelect, 2);
				String diaChi = (String) tableTimKH.getValueAt(rowSelect, 3);
				txtTenKH.setText(tenKH);
				txtMaKH.setText(maKH);
				txtSDT.setText(soDT);
				txtDiaChi.setText(diaChi);
				scpTimKH.setVisible(false);
				txtTenKH.setEditable(false);
				btnLuh.setEnabled(true);
			}

		});
		scpTimKH = new JScrollPane(tableTimKH);
		scpTimKH.setVisible(false);
		scpTimKH.setBounds(180, 59, 514, 163);
		pQL_HD.add(scpTimKH);

		comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maDH = (String) comboBox.getSelectedItem();
				if (maDH != null) {
					DonHang dh = null;
					try {
						dh = dhDao.getDHTheoID(maDH);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (dh != null) {
						lblTongThanTien.setText(currencyVN.format(dh.getTongThanhTien()));
						lblHD.setText(dh.getMaDH());
					}
					try {
						loadCTDH(dh.getMaDH());
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					KhachHang kh = null;
					try {
						kh = khDao.getKHTheoMa(dh.getKhachHang().getMaKH());
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (kh != null) {
						txtTenKH.setText(kh.getTenKH());
						txtMaKH.setText(kh.getMaKH());
						txtSDT.setText(kh.getSoDT());
						txtDiaChi.setText(kh.getDiaChi());
					}
					btnThemDungCu.setEnabled(true);
					btnThemSach.setEnabled(true);
					btnIn.setEnabled(true);
				} else {
					xoaTrangKH();
				}
			}
		});
		comboBox.setBounds(10, 233, 200, 34);
		pQL_HD.add(comboBox);

		JLabel lblNewLabel_7 = new JLabel("Đơn hàng");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("Leelawadee UI", Font.ITALIC, 18));
		lblNewLabel_7.setBounds(10, 278, 200, 40);
		pQL_HD.add(lblNewLabel_7);

		lblHD = new JLabel("");
		lblHD.setHorizontalAlignment(SwingConstants.CENTER);
		lblHD.setForeground(Color.RED);
		lblHD.setFont(new Font("Trajan Pro", Font.BOLD | Font.ITALIC, 18));
		lblHD.setBounds(10, 329, 200, 40);
		pQL_HD.add(lblHD);

		btnTaoHD = new JButton("Tạo đơn hàng");
		btnTaoHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(panel, "Bạn có chắc muốn tạo mới hóa đơn?",
						"Thông báo xác nhận tạo hóa đơn", JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					troVeBanDau();
					enabledPKH(false);
					xoaTrangKH();
					btnThemDungCu.setEnabled(false);
					btnThemSach.setEnabled(false);
					btnIn.setEnabled(false);
					int tblRow = tableModel.getRowCount();
					for (int i = tblRow - 1; i >= 0; i--) {
						tableModel.removeRow(i);
					}
					try {
						int soDH = DemHD() + 1;
						lblHD.setText("DH" + soDH);
						enabledPKH(true);

						setThongBao("Đã tạo hóa đơn mới");
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnTaoHD.setIcon(new ImageIcon(FrameXuLyDonHang.class.getResource("/image/btnThem.png")));
		btnTaoHD.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnTaoHD.setBounds(493, 333, 170, 40);
		pQL_HD.add(btnTaoHD);

		btnXoaSP = new JButton("Xóa sản phẩm");
		btnXoaSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maHD = lblHD.getText();
				String maSP = txtMaSP.getText();
				CTHoaDon cthd = ctdhDao.getCTHD(maHD, maSP);
				SanPham sp = null;
				try {
					sp = spDao.getSPTheoID(maSP);

				} catch (Exception e4) {
					// TODO Auto-generated catch block
					e4.printStackTrace();
				}
				int cnSL = sp.getSoLuong() + cthd.getSoLuong();
				try {
					spDao.capNhatSoLuongSP(cnSL, maSP);
				} catch (ClassNotFoundException | SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					ctdhDao.delete(maHD, maSP);
					loadCTDH(maHD);
					troVeBanDau();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int tongThanhTien = 0;
				try {
					tongThanhTien = tongThanhTien();
				} catch (ParseException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				lblTongThanTien.setText(currencyVN.format(tongThanhTien));
				try {
					dhDao.capnhatTongTien(tongThanhTien, maHD);
				} catch (ClassNotFoundException | SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		btnXoaSP.setIcon(new ImageIcon(FrameXuLyDonHang.class.getResource("/image/btnXoa.png")));
		btnXoaSP.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnXoaSP.setEnabled(false);
		btnXoaSP.setBounds(883, 283, 170, 40);
		pQL_HD.add(btnXoaSP);

		btnCapNhatSP = new JButton("Cập nhật sản phẩm");
		btnCapNhatSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkSoLuong()) {
					String maSP = txtMaSP.getText();
					String maHD = lblHD.getText();
					SanPham sp = null;
					try {
						sp = spDao.getSPTheoID(maSP);

					} catch (Exception e4) {
						// TODO Auto-generated catch block
						e4.printStackTrace();
					}
					CTHoaDon ctHoaDon = ctdhDao.getCTHD(maHD, maSP);
					int soSLSPcanCN = 0;
					if (ctHoaDon.getSoLuong() > Integer.valueOf(txtSoluong.getText()).intValue()) {
						int cnSLSP = ctHoaDon.getSoLuong() - Integer.valueOf(txtSoluong.getText()).intValue();
						soSLSPcanCN = sp.getSoLuong() + cnSLSP;
					} else {
						int cnSLSP = Integer.valueOf(txtSoluong.getText()).intValue() - ctHoaDon.getSoLuong();
						soSLSPcanCN = sp.getSoLuong() - cnSLSP;
					}
					try {
						spDao.capNhatSoLuongSP(soSLSPcanCN, maSP);
					} catch (ClassNotFoundException | SQLException e4) {
						// TODO Auto-generated catch block
						e4.printStackTrace();
					}
					try {
						ctdhDao.capNhat(Integer.valueOf(txtSoluong.getText()).intValue(), maHD, maSP);
						loadCTDH(maHD);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					int tongThanhTien = 0;
					try {
						tongThanhTien = tongThanhTien();
					} catch (ParseException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					lblTongThanTien.setText(currencyVN.format(tongThanhTien));
					try {
						dhDao.capnhatTongTien(tongThanhTien, maHD);
					} catch (ClassNotFoundException | SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					list.setBorder(null);
					btnCapNhatSP.setEnabled(false);
					setThongBao("Đã cập nhật " + (String) cbbLoaiSP.getSelectedItem() + " thành công :))");
					lblThongBaoSoLuong.setText("");
					showMessage("Đã cập nhật " + (String) cbbLoaiSP.getSelectedItem() + " thành công :))", txtDiaChi);
					enabledPSP(false);
					btnXoaSP.setEnabled(false);
					xoaTrangPanelSP();
					try {
						loadCTDH(maHD);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnCapNhatSP.setIcon(new ImageIcon(FrameXuLyDonHang.class.getResource("/image/btnCapNhat.png")));
		btnCapNhatSP.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnCapNhatSP.setEnabled(false);
		btnCapNhatSP.setBounds(1063, 283, 200, 40);
		pQL_HD.add(btnCapNhatSP);

		btnLuu = new JButton("Lưu sản phẩm");
		btnLuu.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SanPham sp = null;
				int soLuong = Integer.valueOf(txtSoluong.getText()).intValue();
				HoaDon getHD = null;
				try {
					sp = spDao.getSPTheoID(txtMaSP.getText());
					getHD = hdDao.getHDTheoID(lblHD.getText());
//					if (soLuong > sp.getSoLuong()) {
					CTHoaDon cthd = new CTHoaDon(sp, getHD, soLuong);
					try {
						if (!ctdhDao.checkCTHD(cthd)) {
							try {
								ctdhDao.themCTHD(cthd);
								spDao.capNhatSoLuongSP(
										sp.getSoLuong() - Integer.valueOf(txtSoluong.getText()).intValue(),
										sp.getMaSP());
								loadCTDH(lblHD.getText());
							} catch (Exception e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
						} else {
							try {
								loadCTDH(lblHD.getText());
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					int tongThanhTien = 0;
					try {
						tongThanhTien = tongThanhTien();
						lblTongThanTien.setText(currencyVN.format(tongThanhTien));
						dhDao.capnhatTongTien(tongThanhTien, lblHD.getText());
					} catch (ParseException | ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					list.setBorder(null);
					btnLuu.setEnabled(false);
					setThongBao("Đã thêm " + (String) cbbLoaiSP.getSelectedItem() + " thành công :))");
					lblThongBaoSoLuong.setText("");
					showMessage("Đã thêm " + (String) cbbLoaiSP.getSelectedItem() + " thành công :))", txtDiaChi);
					enabledPSP(false);
					xoaTrangPanelSP();
//					}
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

			}
		});
		btnLuu.setIcon(new ImageIcon(FrameXuLyDonHang.class.getResource("/image/btnSave.png")));
		btnLuu.setEnabled(false);
		btnLuu.setBounds(1063, 232, 200, 40);
		pQL_HD.add(btnLuu);

		btnThemSach = new JButton("Thêm sách");
		btnThemSach.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnThemSach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enabledPSP(true);
				list.setEnabled(true);
				btnLuu.setEnabled(true);
				chckbxNewCheckBox.setEnabled(true);
				try {
					loadLoaiSPCungLoai(1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnThemSach.setIcon(new ImageIcon(FrameXuLyDonHang.class.getResource("/image/btnAddBook.png")));
		btnThemSach.setBounds(673, 333, 200, 40);
		pQL_HD.add(btnThemSach);

		btnThemDungCu = new JButton("Thêm dụng cụ");
		btnThemDungCu.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnThemDungCu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enabledPSP(true);
				list.setEnabled(true);
				btnLuu.setEnabled(true);
				chckbxNewCheckBox.setEnabled(false);
				try {
					loadLoaiSPCungLoai(0);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnThemDungCu.setIcon(new ImageIcon(FrameXuLyDonHang.class.getResource("/image/btnAddTool.png")));
		btnThemDungCu.setBounds(883, 333, 200, 40);
		pQL_HD.add(btnThemDungCu);

		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnXoaTrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				troVeBanDau();
			}
		});
		btnXoaTrang.setIcon(new ImageIcon(FrameXuLyDonHang.class.getResource("/image/btnXoaTrang.png")));
		btnXoaTrang.setBounds(313, 333, 170, 40);
		pQL_HD.add(btnXoaTrang);

		btnIn = new JButton("In hóa đơn");
		btnIn.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnIn.setIcon(new ImageIcon(FrameXuLyDonHang.class.getResource("/image/btnPrint.png")));
		btnIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tongTien = 0;
				try {
					tongTien = currencyVN.parse(lblTongThanTien.getText()).intValue();
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				if (tongTien > 0) {
					xuatHD(lblHD.getText());
					try {
						dhDao.capnhatTrangThai(lblHD.getText());
						troVeBanDau();
						loadDH();
						inhoantat();
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					showMessage("Hãy thêm hàng vào đơn hàng này để thanh toán", txtDiaChi);
				}
			}
		});
		btnIn.setBounds(1093, 333, 170, 40);
		pQL_HD.add(btnIn);

		lblThongBaoSoLuong = new JLabel("");
		lblThongBaoSoLuong.setHorizontalAlignment(SwingConstants.CENTER);
		lblThongBaoSoLuong.setForeground(Color.RED);
		lblThongBaoSoLuong.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblThongBaoSoLuong.setBounds(610, 232, 295, 40);
		pQL_HD.add(lblThongBaoSoLuong);

		String col[] = { "Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Giá thành", "Số lượng", "Thành tiền" };
		tableModel = new DefaultTableModel(col, 0);
		table = new JTable(tableModel);
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowSelect = table.getSelectedRow();
				String maSP = (String) table.getValueAt(rowSelect, 0);
				String tenSP = (String) table.getValueAt(rowSelect, 1);
				String loaiSP = (String) table.getValueAt(rowSelect, 2);
				String donGia = (String) table.getValueAt(rowSelect, 3);
				int soLuong = (Integer) table.getValueAt(rowSelect, 4);
				String thanhTien = (String) table.getValueAt(rowSelect, 5);
				LoaiSanPhamDao loaiSanPhamDao = new LoaiSanPhamDao();
				try {
					LoaiSanPham loaiSanPham = loaiSanPhamDao.getDMTheoTen(loaiSP);
					loadLoaiSPCungLoai(loaiSanPham.isLoaiDM());
					for (int i = 0; i < cbbLoaiSP.getItemCount(); i++) {
						if (loaiSP.equalsIgnoreCase((String) cbbLoaiSP.getItemAt(i))) {
							cbbLoaiSP.setSelectedIndex(i);
						}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for (int i = 0; i < list.getModel().getSize(); i++) {
					if (tenSP.equalsIgnoreCase(list.getModel().getElementAt(i))) {
						list.setSelectedIndex(i);
					}
				}
				txtMaSP.setText(maSP);
				txtSoluong.setText(String.valueOf(soLuong));
				txtGiaThanh.setText(donGia);
				txtThanhTien.setText(thanhTien);
				cbbLoaiSP.setEditable(false);
				list.setEnabled(false);
				btnCapNhatSP.setEnabled(true);
				btnXoaSP.setEnabled(true);
				btnLuh.setEnabled(false);
				btnThemDungCu.setEnabled(false);
				btnThemSach.setEnabled(false);
				txtSoluong.setEditable(true);
			}
		});
		table.setRowHeight(25);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 384, 1253, 212);
		pQL_HD.add(scrollPane);

		JLabel lblNewLabel_9 = new JLabel("Tổng thành tiền là : ");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_9.setForeground(Color.GREEN);
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_9.setBounds(220, 232, 191, 40);
		pQL_HD.add(lblNewLabel_9);

		lblThongBao = new JLabel("Tạo đơn hàng mới thành công");
		lblThongBao.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblThongBao.setForeground(Color.GREEN);
		lblThongBao.setHorizontalAlignment(SwingConstants.CENTER);
		lblThongBao.setBounds(10, 607, 1253, 23);
		pQL_HD.add(lblThongBao);

		JPanel pSP = new JPanel();
		pSP.setBackground(Color.WHITE);
		pSP.setLayout(null);
		pSP.setBounds(450, 11, 813, 211);
		pQL_HD.add(pSP);

		JLabel lblNewLabel_4 = new JLabel("Loại sản phẩm :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_4.setBounds(10, 18, 120, 20);
		pSP.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Mã sản phẩm :\r\n");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_5.setBounds(10, 94, 120, 20);
		pSP.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Giá thành :\r\n");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_6.setBounds(10, 132, 120, 20);
		pSP.add(lblNewLabel_6);

		txtMaSP = new JTextField();
		txtMaSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaSP.setEditable(false);
		txtMaSP.setColumns(10);
		txtMaSP.setBounds(140, 92, 294, 30);
		pSP.add(txtMaSP);

		JLabel lblNewLabel_6_1 = new JLabel("Số lượng :");
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_6_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_6_1.setBounds(274, 132, 100, 20);
		pSP.add(lblNewLabel_6_1);

		txtSoluong = new JTextField();
		txtSoluong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSoluong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkSoLuong()) {
					String maSp = txtMaSP.getText();
					SanPham pham = null;
					try {
						pham = spDao.getSPTheoID(maSp);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					int soLuong = Integer.valueOf(txtSoluong.getText());
					if (soLuong > pham.getSoLuong()) {
						lblThongBaoSoLuong.setText("Không đủ số lượng rồi :((");
						showMessage("Error Hãy nhập số lượng !!", txtSoluong);
					} else {
						int giaThanh = 0;
						try {
							giaThanh = currencyVN.parse(txtGiaThanh.getText()).intValue();
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						txtThanhTien.setText(currencyVN.format(soLuong * giaThanh));

					}
				}
			}
		});
		txtSoluong.setEditable(false);
		txtSoluong.setColumns(10);
		txtSoluong.setBounds(384, 130, 50, 30);
		pSP.add(txtSoluong);

		txtGiaThanh = new JTextField();
		txtGiaThanh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtGiaThanh.setEditable(false);
		txtGiaThanh.setColumns(10);
		txtGiaThanh.setBounds(140, 130, 120, 30);
		pSP.add(txtGiaThanh);

		cbbLoaiSP = new JComboBox<String>();
		cbbLoaiSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSoluong.setText("");
				txtGiaThanh.setText("");
				txtThanhTien.setText("");
				txtMaSP.setText("");
				String tenLoai = (String) cbbLoaiSP.getSelectedItem();
				try {
					if (chckbxNewCheckBox.isSelected()) {
						loadTG();
						String tenTg = (String) cbbTG.getSelectedItem();
						loadSPCungLoai(tenLoai, tenTg);
					} else {
						loadSPCungLoai(tenLoai);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				xoaTrangSP();
				list.setBorder(
						new TitledBorder(
								new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255),
										new Color(160, 160, 160)),
								"Danh sách " + tenLoai + " ", TitledBorder.LEADING, TitledBorder.TOP, null,
								new Color(0, 0, 0)));
			}
		});
		cbbLoaiSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbLoaiSP.setBackground(SystemColor.menu);
		cbbLoaiSP.setBounds(140, 8, 294, 34);
		pSP.add(cbbLoaiSP);

		JLabel lblNewLabel_6_2 = new JLabel("Thành tiền :");
		lblNewLabel_6_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_6_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_6_2.setBounds(10, 170, 120, 20);
		pSP.add(lblNewLabel_6_2);

		txtThanhTien = new JTextField();
		txtThanhTien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtThanhTien.setEditable(false);
		txtThanhTien.setColumns(10);
		txtThanhTien.setBounds(140, 168, 294, 30);
		pSP.add(txtThanhTien);

		listModel = new DefaultListModel<String>();
		list = new JList<String>(listModel);
		list.setBorder(new TitledBorder(null, "Danh s\u00E1ch s\u1EA3n ph\u1EA9m", TitledBorder.CENTER,
				TitledBorder.TOP, null, null));
		list.setFont(new Font("Tahoma", Font.PLAIN, 15));
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				SanPham s = null;
				try {
					s = loadThongTinSPTheoTen(list.getSelectedValue().toString());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (s != null) {
					txtMaSP.setText(s.getMaSP());
					txtGiaThanh.setText(currencyVN.format(s.getDonGia()));
					lblThongBaoSoLuong.setText("Còn lại : " + s.getSoLuong() + " " + s.getDanhMuc().getTenDM() + "");
				} else {
					lblThongBaoSoLuong.setText("");
				}
			}
		});
		JScrollPane scrollPane_1 = new JScrollPane(list);
		scrollPane_1.setBounds(444, 14, 359, 190);
		pSP.add(scrollPane_1);

		JLabel lblNewLabel_5_1 = new JLabel("Tác giả :");
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5_1.setBounds(10, 56, 120, 20);
		pSP.add(lblNewLabel_5_1);

		cbbTG = new JComboBox<String>();
		cbbTG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbbTG.getItemCount() > 0) {
					String tenLoai = (String) cbbLoaiSP.getSelectedItem();
					String tenTG = (String) cbbTG.getSelectedItem();
					try {
						loadSPCungLoai(tenLoai, tenTG);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		cbbTG.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbTG.setBackground(SystemColor.menu);
		cbbTG.setBounds(140, 50, 254, 34);
		pSP.add(cbbTG);

		chckbxNewCheckBox = new JCheckBox();
		chckbxNewCheckBox.setBackground(Color.WHITE);
		chckbxNewCheckBox.setEnabled(false);
		chckbxNewCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox.setSize(30, 30);
		chckbxNewCheckBox.setIcon(new ImageIcon(FrameXuLyDonHang.class.getResource("/image/circle.png")));
		chckbxNewCheckBox.setSelectedIcon(new ImageIcon(FrameXuLyDonHang.class.getResource("/image/check.png")));
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox.isSelected()) {
					cbbTG.setEditable(true);
					try {
						loadTG();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					String tenLoai = (String) cbbLoaiSP.getSelectedItem();
					try {
						loadSPCungLoai(tenLoai);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					cbbTG.removeAllItems();
					cbbTG.setEditable(false);
				}
				txtMaSP.setText("");
				txtGiaThanh.setText("");
				txtSoluong.setText("");
				txtThanhTien.setText("");
				lblThongBaoSoLuong.setText("");
			}
		});
		chckbxNewCheckBox.setBounds(400, 50, 34, 34);
		pSP.add(chckbxNewCheckBox);

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		panel.setBounds(10, 11, 430, 211);
		pQL_HD.add(panel);

		JLabel lblNewLabel = new JLabel("Tên khách hàng :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBounds(10, 23, 150, 20);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Mã khách hàng :\r\n");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setBounds(10, 71, 150, 20);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Số điện thoại :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2.setBounds(10, 119, 150, 20);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Địa chỉ :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_3.setBounds(10, 167, 150, 20);
		panel.add(lblNewLabel_3);

		txtTenKH = new JTextField();
		txtTenKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTenKH.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (Enter == 0) {
					String tenKH = txtTenKH.getText();
					try {
						showTimKiemKH(tenKH);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else
					Enter = 0;
			}
		});
		txtTenKH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scpTimKH.setVisible(true);
				Enter = 1;
			}
		});
		txtTenKH.setEditable(false);
		txtTenKH.setColumns(10);
		txtTenKH.setBounds(170, 18, 250, 30);
		panel.add(txtTenKH);

		txtMaKH = new JTextField();
		txtMaKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaKH.setEditable(false);
		txtMaKH.setColumns(10);
		txtMaKH.setBounds(170, 66, 250, 30);
		panel.add(txtMaKH);

		txtSDT = new JTextField();
		txtSDT.setEditable(false);
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSDT.setColumns(10);
		txtSDT.setBounds(170, 114, 250, 30);
		panel.add(txtSDT);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDiaChi.setEditable(false);
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(170, 162, 250, 30);
		panel.add(txtDiaChi);

		btnLuh = new JButton("Lưu đơn hàng");
		btnLuh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maDH = lblHD.getText();
				String maKh = txtMaKH.getText();
				KhachHang kh = null;
				try {
					kh = khDao.getKHTheoMa(maKh);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				NhanVien nv = null;
				try {
					nv = nvDao.getKHTheoMa(maNV);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				HoaDon hd = new HoaDon(maDH, Date.valueOf(day), kh, nv, 0, 0);
				try {
					dhDao.themDH(hd);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					loadDH();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				btnLuh.setEnabled(false);
				btnThemDungCu.setEnabled(true);
				btnThemSach.setEnabled(true);
				btnIn.setEnabled(true);
				comboBox.setSelectedIndex(0);
			}
		});
		btnLuh.setEnabled(false);
		btnLuh.setIcon(new ImageIcon(FrameXuLyDonHang.class.getResource("/image/btnSave.png")));
		btnLuh.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnLuh.setBounds(313, 283, 170, 40);
		pQL_HD.add(btnLuh);

		lblTongThanTien = new JLabel("");
		lblTongThanTien.setBounds(421, 233, 185, 40);
		pQL_HD.add(lblTongThanTien);

		loadDH();
	}

	public void troVeBanDau() {
		xoaTrangPanelSP();
		enabledPKH(false);
		enabledPSP(false);
		setThongBao("");
		setThongBaoSL("");
		btnThemDungCu.setEnabled(true);
		btnThemSach.setEnabled(true);
		btnIn.setEnabled(true);
		btnXoaSP.setEnabled(false);
		btnCapNhatSP.setEnabled(false);

	}

	public void inhoantat() {
		lblHD.setText("");
		lblTongThanTien.setText("");
		lblThongBaoSoLuong.setText("");
		lblThongBao.setText("");
		int tblRow = tableModel.getRowCount();
		for (int i = tblRow - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
	}

	public void enabledPKH(boolean b) {
		txtTenKH.setEditable(b);
	}

	public void enabledPSP(boolean b) {
		cbbLoaiSP.setEditable(b);
		txtSoluong.setEditable(b);
	}

	public void xoaTrangPanelSP() {
		cbbLoaiSP.removeAllItems();
		xoaTrangSP();
	}

	public void xoaTrangSP() {
		txtSoluong.setText("");
		txtGiaThanh.setText("");
		txtThanhTien.setText("");
		txtMaSP.setText("");
	}

	public void xoaTrangKH() {
		txtTenKH.setText("");
		txtMaKH.setText("");
		txtDiaChi.setText("");
		txtSDT.setText("");
		lblThongBaoSoLuong.setText("");
	}

	public void setThongBao(String s) {
		lblThongBao.setText(s);
	}

	public void setThongBaoSL(String s) {
		lblThongBaoSoLuong.setText(s);
	}

	public void showTimKiemKH(String tenKH) throws ClassNotFoundException, SQLException {
		KhachHangDao khDao = new KhachHangDao();
		int tblRow = tableModel1.getRowCount();
		for (int i = tblRow - 1; i >= 0; i--) {
			tableModel1.removeRow(i);
		}
		for (KhachHang kh : khDao.getMaKHTenKD(tenKH)) {
			tableModel1.addRow(new Object[] { kh.getMaKH(), kh.getTenKH(), kh.getSoDT(), kh.getDiaChi() });
		}
	}

	public int DemHD() throws ClassNotFoundException, SQLException {
		return dhDao.demSoDH();
	}

	public void loadLoaiSPCungLoai(int i) throws Exception {
		LoaiSanPhamDao lspDao = new LoaiSanPhamDao();
		cbbLoaiSP.removeAllItems();
		for (LoaiSanPham lsp : lspDao.getAllDMCungLoai(i)) {
			cbbLoaiSP.addItem(lsp.getTenDM());
		}
	}

	public void loadTG() throws Exception {
		String tenLSP = (String) cbbLoaiSP.getSelectedItem();
		TacGiaDao tgDao = new TacGiaDao();
		cbbTG.removeAllItems();
		for (TacGia tg : tgDao.getTGTheoTenLSP(tenLSP)) {
			cbbTG.addItem(tg.getTenTG());
		}
	}

	public void loadSPCungLoai(String loai) throws Exception {
		listModel.removeAllElements();
		SanPhamDao spDao = new SanPhamDao();
		listModel.addAll(spDao.getallSPTheoLoaiDM(loai));
	}

	public void loadSPCungLoai(String loai, String tenTg) throws Exception {
		listModel.removeAllElements();
		SanPhamDao spDao = new SanPhamDao();
		listModel.addAll(spDao.getallSPTheoTenLSPTenTG(loai, tenTg));
	}

	public SanPham loadThongTinSPTheoTen(String ten) throws Exception {
		SanPhamDao spDao = new SanPhamDao();
		return spDao.getMaSPTenSP(ten);
	}

	public boolean checkSoLuong() {
		String soLuong = txtSoluong.getText().trim();
		if (!(soLuong.length() > 0 && soLuong.matches("[1-9][0-9]*"))) {
			showMessage("Error Hãy nhập số lượng !!", txtSoluong);
			btnLuu.setEnabled(false);
			return false;
		}
		return true;
	}

	public int tongThanhTien() throws ParseException {
		int tblRow1 = tableModel.getRowCount();
		int tongThanhTien = 0;
		for (int i = tblRow1 - 1; i >= 0; i--) {
			int row = currencyVN.parse(tableModel.getValueAt(i, 5).toString()).intValue();
			tongThanhTien += row;
		}
		return tongThanhTien;
	}

	public void loadDH() throws ClassNotFoundException, SQLException {
		comboBox.removeAllItems();
		for (int i = dhDao.getallDH().size() - 1; i >= 0; i--) {
			comboBox.addItem(dhDao.getallDH().get(i).getMaDH());
		}
	}

	public void loadCTDH(String maDH) throws Exception {
		int tblRow = tableModel.getRowCount();
		for (int i = tblRow - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
		for (CTHoaDon cthd : ctdhDao.getallCTHD(maDH)) {
			tableModel.addRow(new Object[] { cthd.getSanPham().getMaSP(), cthd.getSanPham().getTenSP(),
					cthd.getSanPham().getDanhMuc().getTenDM(),currencyVN.format(cthd.getSanPham().getDonGia()), cthd.getSoLuong(),
					currencyVN.format(cthd.getThanhTien())

			});
		}
	}

	public void xuatHD(String maHD) {
		try {
			String sql = "SELECT \"LOAISANPHAM\".\"TENLSP\",\r\n" + "	\"SANPHAM\".\"MASP\",\r\n"
					+ "	\"SANPHAM\".\"DONGIA\",\r\n" + "	\"SANPHAM\".\"TENSP\",\r\n"
					+ "	\"CTHOADON\".\"SOLUONG\",\r\n" + "	\"CTHOADON\".\"THANHTIEN\",\r\n"
					+ "	\"HOADON\".\"NGAYTAO\",\r\n" + "	\"HOADON\".\"MAHD\",\r\n"
					+ "	\"HOADON\".\"TONGTHANHTIEN\",\r\n" + "	\"HOADON\".\"TIENMAT\",\r\n"
					+ "	\"HOADON\".\"TIENDU\",\r\n" + "	\"NHANVIEN\".\"TENNV\",\r\n" + "	\"NHANVIEN\".\"SDT\",\r\n"
					+ "	\"KHACHHANG\".\"DIACHI\",\r\n" + "	\"KHACHHANG\".\"TENKH\",\r\n"
					+ "	\"KHACHHANG\".\"GIOITINH\"\r\n" + "FROM \"CTHOADON\"\r\n" + "	INNER JOIN \"HOADON\" ON \r\n"
					+ "	 \"CTHOADON\".\"MAHD\" = \"HOADON\".\"MAHD\" \r\n" + "	INNER JOIN \"KHACHHANG\" ON \r\n"
					+ "	 \"HOADON\".\"MAKH\" = \"KHACHHANG\".\"MAKH\" \r\n" + "	INNER JOIN \"NHANVIEN\" ON \r\n"
					+ "	 \"HOADON\".\"MANV\" = \"NHANVIEN\".\"MANV\" \r\n" + "	INNER JOIN \"SANPHAM\" ON \r\n"
					+ "	 \"CTHOADON\".\"MASP\" = \"SANPHAM\".\"MASP\" \r\n" + "	INNER JOIN \"LOAISANPHAM\" ON \r\n"
					+ "	 \"SANPHAM\".\"MALSP\" = \"LOAISANPHAM\".\"MALSP\" \r\n" + "WHERE \r\n"
					+ "	 \"HOADON\".\"MAHD\" = '" + maHD + "'";

			JasperDesign jasperDesign = JRXmlLoader.load("src/Report/HoaDonRepoter.jrxml");
			JRDesignQuery updateQuery = new JRDesignQuery();
			updateQuery.setText(sql);
			jasperDesign.setQuery(updateQuery);

			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			JasperPrint p = JasperFillManager.fillReport(jasperReport, null, ConnectDB.getConnection());
			JasperViewer.viewReport(p, false);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void showMessage(String message, JTextField txt) {
		// TODO Auto-generated method stub
		txt.requestFocus();
		JOptionPane.showMessageDialog(null, message);
	}
}
