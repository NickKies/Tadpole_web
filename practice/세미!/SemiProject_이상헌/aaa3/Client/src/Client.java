import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Client {

	private static String url = null;
	private static String user = "root";
	private static String pw = "1234";
	private static PreparedStatement pstmt = null;

	private static JFrame jframe;
	private static JMenuBar jmenuBar;
	private static JMenu jmenu;
	private static JMenuItem desktop;
	private static JMenuItem laptop;
	private static JMenuItem tablet;
	private static JPanel jpannel;
	private static JButton search;
	private static JButton reset;
	private static JButton purchase;
	private static JTextField jtextField;
	private static ImageIcon image1;
	private static ImageIcon image2;
	private static ImageIcon image3;
	private static JLabel imageLabel;
	private static JLabel blank;
	private static JButton refresh;
	private static JTable jtable;
	private static JRadioButton rb1;
	private static JRadioButton rb2;
	private static JRadioButton rb3;
	private static JRadioButton rb4;
	private static ButtonGroup bg;

	private static String[][] selectedContents = null;
	private static ArrayList<String[]> tmpContents = new ArrayList<String[]>();

	private static String tmpName = "찾고 싶은 제품명을 입력하세요...";

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("./ServerURL.txt")));
			url = br.readLine();
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Popup popup1 = new Popup("목록에 없는 제품명입니다.", true), popup2 = new Popup("리스트를 갱신중 입니다...", false);
		
		String[] header = {"번호", "제품명", "최저가격(원)", "평점"};
		String[][] contents = new String[100][5];
		String[] imgUrl = new String[100];

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, pw);
			for (int i = 0; i < contents.length; i++) {
				String sql = String.format("SELECT * FROM desktop WHERE num=%d;", i + 1);
				pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				rs.next();
				contents[i][0] = rs.getString("num");
				contents[i][1] = rs.getString("name");
				contents[i][2] = rs.getString("price");
				contents[i][3] = rs.getString("score") + " / 5";
				if (contents[i][3].equalsIgnoreCase("null / 5")) contents[i][3] = "";
				contents[i][4] = rs.getString("pageURL");
				imgUrl[i] = rs.getString("imgURL");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			Popup popup3 = new Popup("현재 서버를 이용하실 수 없습니다.", true, true);
			popup3.visible();
			for (int i = 5; i > 0; i--) {
				popup3.setButtonText(i + "초 후 종료");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			System.exit(0);
		}

		jframe = new JFrame("네이버 쇼핑 컴퓨터 가격비교");
		jmenuBar = new JMenuBar();
		jmenu = new JMenu();
		desktop = new JMenuItem("데스크탑 PC");
		laptop = new JMenuItem("노트북");
		tablet = new JMenuItem("태블릿 PC");
		jpannel = new JPanel();
		jtable = new JTable();
		search = new JButton("검색");
		reset = new JButton("초기화");
		purchase = new JButton("구매하러 가기");
		jtextField = new JTextField("찾고 싶은 제품명을 입력하세요...");
		rb1 = new JRadioButton("번호순", true);
		rb2 = new JRadioButton("낮은 가격순");
		rb3 = new JRadioButton("높은 가격순");
		rb4 = new JRadioButton("평점순");
		blank = new JLabel();
		bg = new ButtonGroup();
		bg.add(rb1); bg.add(rb2); bg.add(rb3); bg.add(rb4);

		jframe.setSize(500, 667);
		jframe.setResizable(false);

		ImageIcon menuImage = new ImageIcon("./image/menu.png");
		jmenu.setIcon(menuImage);
		jmenu.setOpaque(true);
		jmenu.setBackground(Color.WHITE);

		jmenu.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				jmenu.setBackground(Color.WHITE);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				jmenu.setBackground(Color.GRAY);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				jmenu.setBackground(Color.WHITE);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				jmenu.setBackground(Color.LIGHT_GRAY);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				jmenu.setBackground(Color.LIGHT_GRAY);
			}
		});

		jmenuBar.setBackground(Color.WHITE);
		jmenuBar.setBorderPainted(false);
		jmenu.add(desktop);
		jmenu.addSeparator();
		jmenu.add(laptop);
		jmenu.addSeparator();
		jmenu.add(tablet);
		jmenuBar.add(jmenu);

		desktop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("org.mariadb.jdbc.Driver");
					Connection con = DriverManager.getConnection(url, user, pw);
					for (int i = 0; i < contents.length; i++) {
						String sql = String.format("SELECT * FROM desktop WHERE num=%d;", i + 1);
						pstmt = con.prepareStatement(sql);
						ResultSet rs = pstmt.executeQuery();
						rs.next();
						contents[i][0] = rs.getString("num");
						contents[i][1] = rs.getString("name");
						contents[i][2] = rs.getString("price");
						contents[i][3] = rs.getString("score") + " / 5";
						if (contents[i][3].equalsIgnoreCase("null / 5")) contents[i][3] = "";
						contents[i][4] = rs.getString("pageURL");
						imgUrl[i] = rs.getString("imgURL");
					}
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					Popup popup3 = new Popup("현재 서버를 이용하실 수 없습니다.", true, true);
					popup3.visible();
					for (int i = 5; i > 0; i--) {
						popup3.setButtonText(i + "초 후 종료");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e2) {
							e2.printStackTrace();
						}
					}
					System.exit(0);
				}

				jtable.setModel(new DefaultTableModel(contents, header) {
					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				});

				rb1.setSelected(true);
				DefaultTableCellRenderer alignCenter = new DefaultTableCellRenderer();
				alignCenter.setHorizontalAlignment(JLabel.CENTER);
				jtable.getColumn("번호").setPreferredWidth(10);
				jtable.getColumn("번호").setCellRenderer(alignCenter);
				jtable.getColumn("최저가격(원)").setPreferredWidth(10);
				jtable.getColumn("최저가격(원)").setCellRenderer(alignCenter);
				jtable.getColumn("평점").setPreferredWidth(10);
				jtable.getColumn("평점").setCellRenderer(alignCenter);
				jtable.getTableHeader().setReorderingAllowed(false);
				jtable.setDragEnabled(false);
				jtable.setRowSelectionInterval(0, 0);
				int selectedNum = Integer.parseInt(jtable.getValueAt(jtable.getSelectedRow(), 0).toString()) - 1;
				URL url = null;
				try {
					url = new URL(imgUrl[selectedNum]);
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
				image1 = new ImageIcon(url);
				imageLabel.setIcon(image1);
			}
		});

		laptop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("org.mariadb.jdbc.Driver");
					Connection con = DriverManager.getConnection(url, user, pw);
					for (int i = 0; i < contents.length; i++) {
						String sql = String.format("SELECT * FROM laptop WHERE num=%d;", i + 1);
						pstmt = con.prepareStatement(sql);
						ResultSet rs = pstmt.executeQuery();
						rs.next();
						contents[i][0] = rs.getString("num");
						contents[i][1] = rs.getString("name");
						contents[i][2] = rs.getString("price");
						contents[i][3] = rs.getString("score") + " / 5";
						if (contents[i][3].equalsIgnoreCase("null / 5")) contents[i][3] = "";
						contents[i][4] = rs.getString("pageURL");
						imgUrl[i] = rs.getString("imgURL");
					}
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					Popup popup3 = new Popup("현재 서버를 이용하실 수 없습니다.", true, true);
					popup3.visible();
					for (int i = 5; i > 0; i--) {
						popup3.setButtonText(i + "초 후 종료");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e2) {
							e2.printStackTrace();
						}
					}
					System.exit(0);
				}

				jtable.setModel(new DefaultTableModel(contents, header) {
					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				});

				rb1.setSelected(true);
				DefaultTableCellRenderer alignCenter = new DefaultTableCellRenderer();
				alignCenter.setHorizontalAlignment(JLabel.CENTER);
				jtable.getColumn("번호").setPreferredWidth(10);
				jtable.getColumn("번호").setCellRenderer(alignCenter);
				jtable.getColumn("최저가격(원)").setPreferredWidth(10);
				jtable.getColumn("최저가격(원)").setCellRenderer(alignCenter);
				jtable.getColumn("평점").setPreferredWidth(10);
				jtable.getColumn("평점").setCellRenderer(alignCenter);
				jtable.getTableHeader().setReorderingAllowed(false);
				jtable.setDragEnabled(false);
				jtable.setRowSelectionInterval(0, 0);
				int selectedNum = Integer.parseInt(jtable.getValueAt(jtable.getSelectedRow(), 0).toString()) - 1;
				URL url = null;
				try {
					url = new URL(imgUrl[selectedNum]);
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
				image1 = new ImageIcon(url);
				imageLabel.setIcon(image1);
			}
		});

		tablet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("org.mariadb.jdbc.Driver");
					Connection con = DriverManager.getConnection(url, user, pw);
					for (int i = 0; i < contents.length; i++) {
						String sql = String.format("SELECT * FROM tablet WHERE num=%d;", i + 1);
						pstmt = con.prepareStatement(sql);
						ResultSet rs = pstmt.executeQuery();
						rs.next();
						contents[i][0] = rs.getString("num");
						contents[i][1] = rs.getString("name");
						contents[i][2] = rs.getString("price");
						contents[i][3] = rs.getString("score") + " / 5";
						if (contents[i][3].equalsIgnoreCase("null / 5")) contents[i][3] = "";
						contents[i][4] = rs.getString("pageURL");
						imgUrl[i] = rs.getString("imgURL");
					}
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					Popup popup3 = new Popup("현재 서버를 이용하실 수 없습니다.", true, true);
					popup3.visible();
					for (int i = 5; i > 0; i--) {
						popup3.setButtonText(i + "초 후 종료");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e2) {
							e2.printStackTrace();
						}
					}
					System.exit(0);
				}

				jtable.setModel(new DefaultTableModel(contents, header) {
					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				});

				rb1.setSelected(true);
				DefaultTableCellRenderer alignCenter = new DefaultTableCellRenderer();
				alignCenter.setHorizontalAlignment(JLabel.CENTER);
				jtable.getColumn("번호").setPreferredWidth(10);
				jtable.getColumn("번호").setCellRenderer(alignCenter);
				jtable.getColumn("최저가격(원)").setPreferredWidth(10);
				jtable.getColumn("최저가격(원)").setCellRenderer(alignCenter);
				jtable.getColumn("평점").setPreferredWidth(10);
				jtable.getColumn("평점").setCellRenderer(alignCenter);
				jtable.getTableHeader().setReorderingAllowed(false);
				jtable.setDragEnabled(false);
				jtable.setRowSelectionInterval(0, 0);
				int selectedNum = Integer.parseInt(jtable.getValueAt(jtable.getSelectedRow(), 0).toString()) - 1;
				URL url = null;
				try {
					url = new URL(imgUrl[selectedNum]);
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
				image1 = new ImageIcon(url);
				imageLabel.setIcon(image1);
			}
		});

		purchase.setPreferredSize(new Dimension(468, 40));
		purchase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedNum = Integer.parseInt(jtable.getValueAt(jtable.getSelectedRow(), 0).toString()) - 1;
				String pageURL = contents[selectedNum][4];
				try {
					Desktop.getDesktop().browse(new URI(pageURL));
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});

		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedContents = null;

				jtextField.setText("찾고 싶은 제품명을 입력하세요...");
				tmpName = "찾고 싶은 제품명을 입력하세요...";

				jtable.setModel(new DefaultTableModel(contents, header) {
					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				});

				rb1.setSelected(true);
				DefaultTableCellRenderer alignCenter = new DefaultTableCellRenderer();
				alignCenter.setHorizontalAlignment(JLabel.CENTER);
				jtable.getColumn("번호").setPreferredWidth(10);
				jtable.getColumn("번호").setCellRenderer(alignCenter);
				jtable.getColumn("최저가격(원)").setPreferredWidth(10);
				jtable.getColumn("최저가격(원)").setCellRenderer(alignCenter);
				jtable.getColumn("평점").setPreferredWidth(10);
				jtable.getColumn("평점").setCellRenderer(alignCenter);
				jtable.getTableHeader().setReorderingAllowed(false);
				jtable.setDragEnabled(false);
				jtable.setRowSelectionInterval(0, 0);
				int selectedNum = Integer.parseInt(jtable.getValueAt(jtable.getSelectedRow(), 0).toString()) - 1;
				URL url = null;
				try {
					url = new URL(imgUrl[selectedNum]);
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
				image1 = new ImageIcon(url);
				imageLabel.setIcon(image1);
			}
		});

		image1 = new ImageIcon();
		imageLabel = new JLabel(image1);
		imageLabel.setPreferredSize(new Dimension(500, 300));

		image2 = new ImageIcon("./image/re1.png");
		image3 = new ImageIcon("./image/re2.png");
		refresh = new JButton(image2);
		refresh.setPreferredSize(new Dimension(25, 25));
		refresh.setBackground(Color.WHITE);
		refresh.setBorderPainted(false);
		refresh.setFocusPainted(false);
		
		refresh.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				refresh.setIcon(image2);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				refresh.setIcon(image2);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				refresh.setIcon(image2);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				refresh.setIcon(image3);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				refresh.setIcon(image2);
			}
		});

		refresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jtextField.setText("찾고 싶은 제품명을 입력하세요...");
				tmpName = "찾고 싶은 제품명을 입력하세요...";

				Thread thread1 = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							Class.forName("org.mariadb.jdbc.Driver");
							Connection con = DriverManager.getConnection(url, user, pw);
							for (int i = 0; i < contents.length; i++) {
								String sql = String.format("SELECT * FROM desktop WHERE num=%d;", i + 1);
								pstmt = con.prepareStatement(sql);
								ResultSet rs = pstmt.executeQuery();
								rs.next();
								contents[i][0] = rs.getString("num");
								contents[i][1] = rs.getString("name");
								contents[i][2] = rs.getString("price");
								contents[i][3] = rs.getString("score") + " / 5";
								if (contents[i][3].equalsIgnoreCase("null / 5")) contents[i][3] = "";
								contents[i][4] = rs.getString("pageURL");
								imgUrl[i] = rs.getString("imgURL");
							}
						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						jtable.setModel(new DefaultTableModel(contents, header) {
							@Override
							public boolean isCellEditable(int row, int column) {
								return false;
							}
						});

						DefaultTableCellRenderer alignCenter = new DefaultTableCellRenderer();
						alignCenter.setHorizontalAlignment(JLabel.CENTER);
						jtable.getColumn("번호").setPreferredWidth(10);
						jtable.getColumn("번호").setCellRenderer(alignCenter);
						jtable.getColumn("최저가격(원)").setPreferredWidth(10);
						jtable.getColumn("최저가격(원)").setCellRenderer(alignCenter);
						jtable.getColumn("평점").setPreferredWidth(10);
						jtable.getColumn("평점").setCellRenderer(alignCenter);
						jtable.getTableHeader().setReorderingAllowed(false);
						jtable.setDragEnabled(false);
						jtable.setRowSelectionInterval(0, 0);
						URL url = null;
						try {
							url = new URL(imgUrl[0]);
						} catch (MalformedURLException e1) {
							e1.printStackTrace();
						}
						image1 = new ImageIcon(url);
						imageLabel.setIcon(image1);
						rb1.setSelected(true);
					}
				});
				thread1.start();
				Thread thread2 = new Thread(new Runnable() {
					@Override
					public void run() {
						jframe.setVisible(false);
						popup2.visible();
						while (thread1.isAlive());
						popup2.notVisible();
						jframe.setVisible(true);
					}
				});
				thread2.start();
			}
		});

		blank.setPreferredSize(new Dimension(102, 25));

		rb1.setBackground(Color.WHITE);
		rb2.setBackground(Color.WHITE);
		rb3.setBackground(Color.WHITE);
		rb4.setBackground(Color.WHITE);

		rb1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (selectedContents == null) {
					Arrays.sort(contents, new Comparator<String[]>() {
						@Override
						public int compare(String[] o1, String[] o2) {
							int n1 = Integer.parseInt(o1[0]), n2 = Integer.parseInt(o2[0]);
							if (n1 < n2) return -1;
							else if (n1 > n2) return 1;
							else return 0;
						}
					});
					jtable.setModel(new DefaultTableModel(contents, header) {
						@Override
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					});
					DefaultTableCellRenderer alignCenter = new DefaultTableCellRenderer();
					alignCenter.setHorizontalAlignment(JLabel.CENTER);
					jtable.getColumn("번호").setPreferredWidth(10);
					jtable.getColumn("번호").setCellRenderer(alignCenter);
					jtable.getColumn("최저가격(원)").setPreferredWidth(10);
					jtable.getColumn("최저가격(원)").setCellRenderer(alignCenter);
					jtable.getColumn("평점").setPreferredWidth(10);
					jtable.getColumn("평점").setCellRenderer(alignCenter);
					jtable.getTableHeader().setReorderingAllowed(false);
					jtable.setDragEnabled(false);
					jtable.setRowSelectionInterval(0, 0);
					int selectedNum = Integer.parseInt(jtable.getValueAt(jtable.getSelectedRow(), 0).toString()) - 1;
					URL url = null;
					try {
						url = new URL(imgUrl[selectedNum]);
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					}
					image1 = new ImageIcon(url);
					imageLabel.setIcon(image1);
				} else {
					Arrays.sort(selectedContents, new Comparator<String[]>() {
						@Override
						public int compare(String[] o1, String[] o2) {
							int n1 = Integer.parseInt(o1[0]), n2 = Integer.parseInt(o2[0]);
							if (n1 < n2) return -1;
							else if (n1 > n2) return 1;
							else return 0;
						}
					});
					jtable.setModel(new DefaultTableModel(selectedContents, header) {
						@Override
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					});
					DefaultTableCellRenderer alignCenter = new DefaultTableCellRenderer();
					alignCenter.setHorizontalAlignment(JLabel.CENTER);
					jtable.getColumn("번호").setPreferredWidth(10);
					jtable.getColumn("번호").setCellRenderer(alignCenter);
					jtable.getColumn("최저가격(원)").setPreferredWidth(10);
					jtable.getColumn("최저가격(원)").setCellRenderer(alignCenter);
					jtable.getColumn("평점").setPreferredWidth(10);
					jtable.getColumn("평점").setCellRenderer(alignCenter);
					jtable.getTableHeader().setReorderingAllowed(false);
					jtable.setDragEnabled(false);
					jtable.setRowSelectionInterval(0, 0);
					int selectedNum = Integer.parseInt(jtable.getValueAt(jtable.getSelectedRow(), 0).toString()) - 1;
					URL url = null;
					try {
						url = new URL(imgUrl[selectedNum]);
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					}
					image1 = new ImageIcon(url);
					imageLabel.setIcon(image1);
				}
			}
		});

		rb2.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (selectedContents == null) {
					Arrays.sort(contents, new Comparator<String[]>() {
						@Override
						public int compare(String[] o1, String[] o2) {
							String[] a1 = o1[2].split(","), a2 = o2[2].split(",");
							String s1 = "", s2 = "";
							for (String a : a1) s1 += a;
							for (String a : a2) s2 += a;
							int n1 = Integer.parseInt(s1), n2 = Integer.parseInt(s2);
							if (n1 < n2) return -1;
							else if (n1 > n2) return 1;
							else {
								if (Integer.parseInt(o1[0]) < Integer.parseInt(o2[0])) return -1;
								else return 1;
							}
						}
					});
					jtable.setModel(new DefaultTableModel(contents, header) {
						@Override
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					});
					DefaultTableCellRenderer alignCenter = new DefaultTableCellRenderer();
					alignCenter.setHorizontalAlignment(JLabel.CENTER);
					jtable.getColumn("번호").setPreferredWidth(10);
					jtable.getColumn("번호").setCellRenderer(alignCenter);
					jtable.getColumn("최저가격(원)").setPreferredWidth(10);
					jtable.getColumn("최저가격(원)").setCellRenderer(alignCenter);
					jtable.getColumn("평점").setPreferredWidth(10);
					jtable.getColumn("평점").setCellRenderer(alignCenter);
					jtable.getTableHeader().setReorderingAllowed(false);
					jtable.setDragEnabled(false);
					jtable.setRowSelectionInterval(0, 0);
					int selectedNum = Integer.parseInt(jtable.getValueAt(jtable.getSelectedRow(), 0).toString()) - 1;
					URL url = null;
					try {
						url = new URL(imgUrl[selectedNum]);
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					}
					image1 = new ImageIcon(url);
					imageLabel.setIcon(image1);
				} else {
					Arrays.sort(selectedContents, new Comparator<String[]>() {
						@Override
						public int compare(String[] o1, String[] o2) {
							String[] a1 = o1[2].split(","), a2 = o2[2].split(",");
							String s1 = "", s2 = "";
							for (String a : a1) s1 += a;
							for (String a : a2) s2 += a;
							int n1 = Integer.parseInt(s1), n2 = Integer.parseInt(s2);
							if (n1 < n2) return -1;
							else if (n1 > n2) return 1;
							else {
								if (Integer.parseInt(o1[0]) < Integer.parseInt(o2[0])) return -1;
								else return 1;
							}
						}
					});
					jtable.setModel(new DefaultTableModel(selectedContents, header) {
						@Override
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					});
					DefaultTableCellRenderer alignCenter = new DefaultTableCellRenderer();
					alignCenter.setHorizontalAlignment(JLabel.CENTER);
					jtable.getColumn("번호").setPreferredWidth(10);
					jtable.getColumn("번호").setCellRenderer(alignCenter);
					jtable.getColumn("최저가격(원)").setPreferredWidth(10);
					jtable.getColumn("최저가격(원)").setCellRenderer(alignCenter);
					jtable.getColumn("평점").setPreferredWidth(10);
					jtable.getColumn("평점").setCellRenderer(alignCenter);
					jtable.getTableHeader().setReorderingAllowed(false);
					jtable.setDragEnabled(false);
					jtable.setRowSelectionInterval(0, 0);
					int selectedNum = Integer.parseInt(jtable.getValueAt(jtable.getSelectedRow(), 0).toString()) - 1;
					URL url = null;
					try {
						url = new URL(imgUrl[selectedNum]);
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					}
					image1 = new ImageIcon(url);
					imageLabel.setIcon(image1);
				}
			}
		});

		rb3.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (selectedContents == null) {
					Arrays.sort(contents, new Comparator<String[]>() {
						@Override
						public int compare(String[] o1, String[] o2) {
							String[] a1 = o1[2].split(","), a2 = o2[2].split(",");
							String s1 = "", s2 = "";
							for (String a : a1) s1 += a;
							for (String a : a2) s2 += a;
							int n1 = Integer.parseInt(s1), n2 = Integer.parseInt(s2);
							if (n1 > n2) return -1;
							else if (n1 < n2) return 1;
							else {
								if (Integer.parseInt(o1[0]) < Integer.parseInt(o2[0])) return -1;
								else return 1;
							}
						}
					});
					jtable.setModel(new DefaultTableModel(contents, header) {
						@Override
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					});
					DefaultTableCellRenderer alignCenter = new DefaultTableCellRenderer();
					alignCenter.setHorizontalAlignment(JLabel.CENTER);
					jtable.getColumn("번호").setPreferredWidth(10);
					jtable.getColumn("번호").setCellRenderer(alignCenter);
					jtable.getColumn("최저가격(원)").setPreferredWidth(10);
					jtable.getColumn("최저가격(원)").setCellRenderer(alignCenter);
					jtable.getColumn("평점").setPreferredWidth(10);
					jtable.getColumn("평점").setCellRenderer(alignCenter);
					jtable.getTableHeader().setReorderingAllowed(false);
					jtable.setDragEnabled(false);
					jtable.setRowSelectionInterval(0, 0);
					int selectedNum = Integer.parseInt(jtable.getValueAt(jtable.getSelectedRow(), 0).toString()) - 1;
					URL url = null;
					try {
						url = new URL(imgUrl[selectedNum]);
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					}
					image1 = new ImageIcon(url);
					imageLabel.setIcon(image1);
				} else {
					Arrays.sort(selectedContents, new Comparator<String[]>() {
						@Override
						public int compare(String[] o1, String[] o2) {
							String[] a1 = o1[2].split(","), a2 = o2[2].split(",");
							String s1 = "", s2 = "";
							for (String a : a1) s1 += a;
							for (String a : a2) s2 += a;
							int n1 = Integer.parseInt(s1), n2 = Integer.parseInt(s2);
							if (n1 > n2) return -1;
							else if (n1 < n2) return 1;
							else {
								if (Integer.parseInt(o1[0]) < Integer.parseInt(o2[0])) return -1;
								else return 1;
							}
						}
					});
					jtable.setModel(new DefaultTableModel(selectedContents, header) {
						@Override
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					});
					DefaultTableCellRenderer alignCenter = new DefaultTableCellRenderer();
					alignCenter.setHorizontalAlignment(JLabel.CENTER);
					jtable.getColumn("번호").setPreferredWidth(10);
					jtable.getColumn("번호").setCellRenderer(alignCenter);
					jtable.getColumn("최저가격(원)").setPreferredWidth(10);
					jtable.getColumn("최저가격(원)").setCellRenderer(alignCenter);
					jtable.getColumn("평점").setPreferredWidth(10);
					jtable.getColumn("평점").setCellRenderer(alignCenter);
					jtable.getTableHeader().setReorderingAllowed(false);
					jtable.setDragEnabled(false);
					jtable.setRowSelectionInterval(0, 0);
					int selectedNum = Integer.parseInt(jtable.getValueAt(jtable.getSelectedRow(), 0).toString()) - 1;
					URL url = null;
					try {
						url = new URL(imgUrl[selectedNum]);
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					}
					image1 = new ImageIcon(url);
					imageLabel.setIcon(image1);
				}
			}
		});

		rb4.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (selectedContents == null) {
					Arrays.sort(contents, new Comparator<String[]>() {
						@Override
						public int compare(String[] o1, String[] o2) {
							Double n1 = null, n2 = null;
							try {
								n1 = Double.parseDouble(o1[3].substring(0, 3));
							} catch (StringIndexOutOfBoundsException e) {
								n1 = (double)0;
							}
							try {
								n2 = Double.parseDouble(o2[3].substring(0, 3));
							} catch (StringIndexOutOfBoundsException e) {
								n2 = (double)0;
							}
							if (n1 > n2) return -1;
							else if (n1 < n2) return 1;
							else {
								if (Integer.parseInt(o1[0]) < Integer.parseInt(o2[0])) return -1;
								else return 1;
							}
						}
					});
					jtable.setModel(new DefaultTableModel(contents, header) {
						@Override
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					});
					DefaultTableCellRenderer alignCenter = new DefaultTableCellRenderer();
					alignCenter.setHorizontalAlignment(JLabel.CENTER);
					jtable.getColumn("번호").setPreferredWidth(10);
					jtable.getColumn("번호").setCellRenderer(alignCenter);
					jtable.getColumn("최저가격(원)").setPreferredWidth(10);
					jtable.getColumn("최저가격(원)").setCellRenderer(alignCenter);
					jtable.getColumn("평점").setPreferredWidth(10);
					jtable.getColumn("평점").setCellRenderer(alignCenter);
					jtable.getTableHeader().setReorderingAllowed(false);
					jtable.setDragEnabled(false);
					jtable.setRowSelectionInterval(0, 0);
					int selectedNum = Integer.parseInt(jtable.getValueAt(jtable.getSelectedRow(), 0).toString()) - 1;
					URL url = null;
					try {
						url = new URL(imgUrl[selectedNum]);
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					}
					image1 = new ImageIcon(url);
					imageLabel.setIcon(image1);
				} else {
					Arrays.sort(selectedContents, new Comparator<String[]>() {
						@Override
						public int compare(String[] o1, String[] o2) {
							Double n1 = Double.parseDouble(o1[3].substring(0, 3)), n2 = Double.parseDouble(o2[3].substring(0, 3));
							if (n1 > n2) return -1;
							else if (n1 < n2) return 1;
							else {
								if (Integer.parseInt(o1[0]) < Integer.parseInt(o2[0])) return -1;
								else return 1;
							}
						}
					});
					jtable.setModel(new DefaultTableModel(selectedContents, header) {
						@Override
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					});
					DefaultTableCellRenderer alignCenter = new DefaultTableCellRenderer();
					alignCenter.setHorizontalAlignment(JLabel.CENTER);
					jtable.getColumn("번호").setPreferredWidth(10);
					jtable.getColumn("번호").setCellRenderer(alignCenter);
					jtable.getColumn("최저가격(원)").setPreferredWidth(10);
					jtable.getColumn("최저가격(원)").setCellRenderer(alignCenter);
					jtable.getColumn("평점").setPreferredWidth(10);
					jtable.getColumn("평점").setCellRenderer(alignCenter);
					jtable.getTableHeader().setReorderingAllowed(false);
					jtable.setDragEnabled(false);
					jtable.setRowSelectionInterval(0, 0);
					int selectedNum = Integer.parseInt(jtable.getValueAt(jtable.getSelectedRow(), 0).toString()) - 1;
					URL url = null;
					try {
						url = new URL(imgUrl[selectedNum]);
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					}
					image1 = new ImageIcon(url);
					imageLabel.setIcon(image1);
				}
			}
		});

		TableModel tm = new DefaultTableModel(contents, header) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		jtable.setModel(tm);

		jtable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					int selectedNum = Integer.parseInt(jtable.getValueAt(jtable.getSelectedRow(), 0).toString()) - 1;

					URL url = null;
					try {
						url = new URL(imgUrl[selectedNum]);
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					}
					image1 = new ImageIcon(url);
					imageLabel.setIcon(image1);
				} else if (e.getClickCount() == 2) {
					String pageURL = null;
					for (String[] c : contents) {
						if (c[0].equals(jtable.getValueAt(jtable.getSelectedRow(), 0).toString())) {
							pageURL = c[4];
							break;
						}
					}
					try {
						Desktop.getDesktop().browse(new URI(pageURL));
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		jtable.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				int selectedNum = Integer.parseInt(jtable.getValueAt(jtable.getSelectedRow(), 0).toString()) - 1;
				URL url = null;
				try {
					url = new URL(imgUrl[selectedNum]);
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
				image1 = new ImageIcon(url);
				imageLabel.setIcon(image1);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				int selectedNum = Integer.parseInt(jtable.getValueAt(jtable.getSelectedRow(), 0).toString()) - 1;
				URL url = null;
				try {
					url = new URL(imgUrl[selectedNum]);
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
				image1 = new ImageIcon(url);
				imageLabel.setIcon(image1);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				int selectedNum = Integer.parseInt(jtable.getValueAt(jtable.getSelectedRow(), 0).toString()) - 1;
				char keyCode = e.getKeyChar();
				if (keyCode == KeyEvent.VK_ENTER) {
					String pageURL = null;
					for (String[] c : contents) {
						if (c[0].equals(jtable.getValueAt(jtable.getSelectedRow(), 0).toString())) {
							pageURL = c[4];
							break;
						}
					}
					try {
						Desktop.getDesktop().browse(new URI(pageURL));
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						e1.printStackTrace();
					}
					try {
						jtable.setRowSelectionInterval(jtable.getSelectedRow() - 1, jtable.getSelectedRow() - 1);
					} catch (IllegalArgumentException e1) {
						if (selectedNum == 0) {
							try {
								jtable.setRowSelectionInterval(99, 99);
							} catch (IllegalArgumentException e2) {
								jtable.setRowSelectionInterval(selectedContents.length - 1, selectedContents.length - 1);
							}
						} else {
							try {
								jtable.setRowSelectionInterval(selectedContents.length - 1, selectedContents.length - 1);
							} catch (NullPointerException e2) {
								jtable.setRowSelectionInterval(99, 99);
							}
						}
					}
				}
				URL url = null;
				try {
					url = new URL(imgUrl[selectedNum]);
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
				image1 = new ImageIcon(url);
				imageLabel.setIcon(image1);
			}
		});

		DefaultTableCellRenderer alignCenter = new DefaultTableCellRenderer();
		alignCenter.setHorizontalAlignment(JLabel.CENTER);
		jtable.getColumn("번호").setPreferredWidth(10);
		jtable.getColumn("번호").setCellRenderer(alignCenter);
		jtable.getColumn("최저가격(원)").setPreferredWidth(10);
		jtable.getColumn("최저가격(원)").setCellRenderer(alignCenter);
		jtable.getColumn("평점").setPreferredWidth(10);
		jtable.getColumn("평점").setCellRenderer(alignCenter);
		jtable.getTableHeader().setReorderingAllowed(false);

		search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				String name = tmpName;
				if (name.equalsIgnoreCase("찾고 싶은 제품명을 입력하세요...")) {
					selectedContents = null;
					jtable.setModel(new DefaultTableModel(contents, header) {
						@Override
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					});
					alignCenter.setHorizontalAlignment(JLabel.CENTER);
					jtable.getColumn("번호").setPreferredWidth(10);
					jtable.getColumn("번호").setCellRenderer(alignCenter);
					jtable.getColumn("최저가격(원)").setPreferredWidth(10);
					jtable.getColumn("최저가격(원)").setCellRenderer(alignCenter);
					jtable.getColumn("평점").setPreferredWidth(10);
					jtable.getColumn("평점").setCellRenderer(alignCenter);
					jtable.getTableHeader().setReorderingAllowed(false);
					jtable.setDragEnabled(false);
					jtable.setRowSelectionInterval(0, 0);
				} else {
					tmpContents = new ArrayList<String[]>();
					for (int i = 0; i < contents.length; i++) {
						if (contents[i][1].toLowerCase().contains(name.toLowerCase())) {
							tmpContents.add(contents[i]);
						}
					}
					if (tmpContents.isEmpty()) {
						jtable.setModel(new DefaultTableModel(contents, header) {
							@Override
							public boolean isCellEditable(int row, int column) {
								return false;
							}
						});
						alignCenter.setHorizontalAlignment(JLabel.CENTER);
						jtable.getColumn("번호").setPreferredWidth(10);
						jtable.getColumn("번호").setCellRenderer(alignCenter);
						jtable.getColumn("최저가격(원)").setPreferredWidth(10);
						jtable.getColumn("최저가격(원)").setCellRenderer(alignCenter);
						jtable.getColumn("평점").setPreferredWidth(10);
						jtable.getColumn("평점").setCellRenderer(alignCenter);
						jtable.getTableHeader().setReorderingAllowed(false);
						popup1.visible();
					} else {
						selectedContents = tmpContents.toArray(new String[tmpContents.size()][]);
						jtable.setModel(new DefaultTableModel(selectedContents, header) {
							@Override
							public boolean isCellEditable(int row, int column) {
								return false;
							}
						});
						alignCenter.setHorizontalAlignment(JLabel.CENTER);
						jtable.getColumn("번호").setPreferredWidth(10);
						jtable.getColumn("번호").setCellRenderer(alignCenter);
						jtable.getColumn("최저가격(원)").setPreferredWidth(10);
						jtable.getColumn("최저가격(원)").setCellRenderer(alignCenter);
						jtable.getColumn("평점").setPreferredWidth(10);
						jtable.getColumn("평점").setCellRenderer(alignCenter);
						jtable.getTableHeader().setReorderingAllowed(false);
						jtable.setDragEnabled(false);
						jtable.setRowSelectionInterval(0, 0);
						int selectedNum = Integer.parseInt(jtable.getValueAt(jtable.getSelectedRow(), 0).toString()) - 1;
						URL url = null;
						try {
							url = new URL(imgUrl[selectedNum]);
						} catch (MalformedURLException e1) {
							e1.printStackTrace();
						}
						image1 = new ImageIcon(url);
						imageLabel.setIcon(image1);
					}
				}
			}
		});

		jtextField.setPreferredSize(new Dimension(325, 27));
		jtextField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				tmpName = jtextField.getText();
				if (jtextField.getText().equals("")) {
					jtextField.setText("찾고 싶은 제품명을 입력하세요...");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				jtextField.setText("");
			}
		});
		
		jtextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				char keyCode = e.getKeyChar();
				if (keyCode == KeyEvent.VK_ENTER) {
					String name = jtextField.getText();
					if (name.equalsIgnoreCase("찾고 싶은 제품명을 입력하세요...")) {
						selectedContents = null;
						jtable.setModel(new DefaultTableModel(contents, header) {
							@Override
							public boolean isCellEditable(int row, int column) {
								return false;
							}
						});
						alignCenter.setHorizontalAlignment(JLabel.CENTER);
						jtable.getColumn("번호").setPreferredWidth(10);
						jtable.getColumn("번호").setCellRenderer(alignCenter);
						jtable.getColumn("최저가격(원)").setPreferredWidth(10);
						jtable.getColumn("최저가격(원)").setCellRenderer(alignCenter);
						jtable.getColumn("평점").setPreferredWidth(10);
						jtable.getColumn("평점").setCellRenderer(alignCenter);
						jtable.getTableHeader().setReorderingAllowed(false);
						jtable.setRowSelectionInterval(0, 0);
						jtable.setDragEnabled(false);
					} else {
						tmpContents = new ArrayList<String[]>();
						for (int i = 0; i < contents.length; i++) {
							if (contents[i][1].toLowerCase().contains(name.toLowerCase())) {
								tmpContents.add(contents[i]);
							}
						}
						if (tmpContents.isEmpty()) {
							jtable.setModel(new DefaultTableModel(contents, header) {
								@Override
								public boolean isCellEditable(int row, int column) {
									return false;
								}
							});
							alignCenter.setHorizontalAlignment(JLabel.CENTER);
							jtable.getColumn("번호").setPreferredWidth(10);
							jtable.getColumn("번호").setCellRenderer(alignCenter);
							jtable.getColumn("최저가격(원)").setPreferredWidth(10);
							jtable.getColumn("최저가격(원)").setCellRenderer(alignCenter);
							jtable.getColumn("평점").setPreferredWidth(10);
							jtable.getColumn("평점").setCellRenderer(alignCenter);
							jtable.getTableHeader().setReorderingAllowed(false);
							popup1.visible();
						} else {
							selectedContents = tmpContents.toArray(new String[tmpContents.size()][]);
							jtable.setModel(new DefaultTableModel(selectedContents, header) {
								@Override
								public boolean isCellEditable(int row, int column) {
									return false;
								}
							});
							alignCenter.setHorizontalAlignment(JLabel.CENTER);
							jtable.getColumn("번호").setPreferredWidth(10);
							jtable.getColumn("번호").setCellRenderer(alignCenter);
							jtable.getColumn("최저가격(원)").setPreferredWidth(10);
							jtable.getColumn("최저가격(원)").setCellRenderer(alignCenter);
							jtable.getColumn("평점").setPreferredWidth(10);
							jtable.getColumn("평점").setCellRenderer(alignCenter);
							jtable.getTableHeader().setReorderingAllowed(false);
							jtable.setDragEnabled(false);
						}
					}
					jtable.setRowSelectionInterval(0, 0);
					int selectedNum = Integer.parseInt(jtable.getValueAt(jtable.getSelectedRow(), 0).toString()) - 1;
					URL url = null;
					try {
						url = new URL(imgUrl[selectedNum]);
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					}
					image1 = new ImageIcon(url);
					imageLabel.setIcon(image1);
				}
			}
		});

		JScrollPane jsp = new JScrollPane(jtable);
		jsp.setPreferredSize(new Dimension(468, 182));
		jpannel.setBackground(Color.WHITE);
		jpannel.add(BorderLayout.NORTH, imageLabel);
		jpannel.add(BorderLayout.WEST, rb1);
		jpannel.add(BorderLayout.WEST, rb2);
		jpannel.add(BorderLayout.WEST, rb3);
		jpannel.add(BorderLayout.WEST, rb4);
		jpannel.add(BorderLayout.WEST, blank);
		jpannel.add(BorderLayout.WEST, refresh);
		jpannel.add(BorderLayout.CENTER, jsp);
		jpannel.add(BorderLayout.WEST, jtextField);
		jpannel.add(BorderLayout.EAST, search);
		jpannel.add(BorderLayout.EAST, reset);
		jpannel.add(BorderLayout.SOUTH, purchase);
		jframe.setJMenuBar(jmenuBar);
		jframe.add(BorderLayout.CENTER, jpannel);
		
		Font fp = new Font("맑은 고딕", Font.PLAIN, 12), fb = new Font("맑은 고딕", Font.BOLD, 12);
		
		desktop.setFont(fb);
		laptop.setFont(fb);
		tablet.setFont(fb);
		rb1.setFont(fb);
		rb2.setFont(fb);
		rb3.setFont(fb);
		rb4.setFont(fb);
		jtable.setFont(fp);
		jtextField.setFont(fp);
		search.setFont(fb);
		reset.setFont(fb);
		purchase.setFont(fb);
		
		jframe.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		jframe.setLocationRelativeTo(null);
		jframe.setVisible(true);
		jtable.setRowSelectionInterval(0, 0);
		URL url = null;
		try {
			url = new URL(imgUrl[0]);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		image1 = new ImageIcon(url);
		imageLabel.setIcon(image1);
	}

}
