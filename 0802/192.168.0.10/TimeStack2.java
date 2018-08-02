import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;

import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Vector;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class TimeStack2 {
	private DBHelper db = new DBHelper();
	private myIP mip = new myIP("wlan0");
	private String TimeServer = "";
	Calendar calendar;
	public SimpleDateFormat sdf;

	private ButtonGroup radiogroup = new ButtonGroup();
	private TimeReception time_packet = new TimeReception();
	private JFrame TS = new JFrame();
	private JPanel panel1 = new JPanel();
	private JLabel label1_1 = new JLabel();
	private JLabel label1_2 = new JLabel();
	private JPanel panel2 = new JPanel();
	private JButton button2_1 = new JButton("블록체인 시간 동기화");
	private JButton button2_2 = new JButton("서버시간 동기화");
	private JPanel panel3 = new JPanel();
	private JLabel label3 = new JLabel("...");

	private JFrame BCTimeSync = new JFrame();
	private JPanel panelBC1 = new JPanel();
	private JPanel panelBC1_0 = new JPanel();
	private JLabel labelBC1_1 = new JLabel("블록체인 시간 동기화");
	private JPanel panelBC1_1 = new JPanel();
	private JRadioButton RadioButtonBC1_1_1 = new JRadioButton("YES");
	private JRadioButton RadioButtonBC1_1_2 = new JRadioButton("NO");
	private JPanel panelBC2 = new JPanel();
	private JButton buttonBC2_1 = new JButton("Show IP List");
	private JFrame shIPList = new JFrame();
	private JPanel panelshI1 = new JPanel();
	private JLabel labelshI1 = new JLabel("*  IP List  ");
	private JPanel panelshI2 = new JPanel();
	private JPanel panelshI2_1 = new JPanel();
	private JLabel labelshI2_1_1 = new JLabel("Network :");
	private JPanel panelshI2_2 = new JPanel();
	private JTextField textFieldshI2_2_1 = new JTextField(20);
	private JPanel panelshI3 = new JPanel();
	private JPanel panelshI3_0 = new JPanel();
	private JLabel labelshI3_0 = new JLabel("Reachable IP");
	private JPanel panelshI3_1 = new JPanel();
	private JList listshI3_1;
	private JScrollPane Iscroller;

	private JButton buttonBC2_2 = new JButton("Show Peer List");
	private JFrame shPeerList = new JFrame();
	private JPanel panelshP1 = new JPanel();
	private JLabel labelshP1 = new JLabel("*  Peer List  ");
	private JPanel panelshP2 = new JPanel();
	private JPanel panelshP2_1 = new JPanel();
	private JLabel labelshP2_1_1 = new JLabel("Network :");
	private JLabel labelshP2_1_2 = new JLabel("Netmask :");
	private JLabel labelshP2_1_3 = new JLabel("Gateway :");
	private JLabel labelshP2_1_4 = new JLabel("Number of peers :");
	private JPanel panelshP2_2 = new JPanel();
	private JTextField textFieldshP2_2_1 = new JTextField(20);
	private JTextField textFieldshP2_2_2 = new JTextField(20);
	private JTextField textFieldshP2_2_3 = new JTextField(20);
	private JTextField textFieldshP2_2_4 = new JTextField(20);
	private JPanel panelshP3 = new JPanel();
	private JPanel panelshP3_0 = new JPanel();
	private JLabel labelshP3_0 = new JLabel("Peer's IP");
	private JPanel panelshP3_1 = new JPanel();
	private JList listshP3_1;
	private JScrollPane Pscroller;

	private JButton buttonBC2_3 = new JButton("Show Blockchain");
	private JFrame shBlockchain = new JFrame();
	private JPanel panelshB1 = new JPanel();
	private JButton buttonBC2_4 = new JButton("Show Time Sync History");
	private JFrame shTSHistory = new JFrame();
	private JPanel panelshH1 = new JPanel();

	private JFrame STimeSync = new JFrame();
	private JPanel panelS1 = new JPanel();
	private JPanel panelS1_0 = new JPanel();
	private JLabel labelS1_1 = new JLabel("시간 동기 활성화");
	private JPanel panelS1_1 = new JPanel();
	private JRadioButton RadioButtonS1_1_1 = new JRadioButton("YES");
	private JRadioButton RadioButtonS1_1_2 = new JRadioButton("NO");
	private JPanel panelS2 = new JPanel();
	private JPanel panelS2_1 = new JPanel();
	private JLabel labelS2_1_1 = new JLabel("TimeServer :");
	private JLabel labelS2_1_2 = new JLabel("Clock offset(ms) :");
	private JLabel labelS2_1_3 = new JLabel("New Time(ms) :");
	private JLabel labelS2_1_4 = new JLabel("New Time(HH:mm:ss) :");
	private JPanel panelS2_2 = new JPanel();
	private JTextField textFieldS2_2_1 = new JTextField();
	private JTextField textFieldS2_2_2 = new JTextField(20);
	private JTextField textFieldS2_2_3 = new JTextField(20);
	private JTextField textFieldS2_2_4 = new JTextField(20);

	public TimeReception tr;
	public TimeSyncronization ts;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimeStack2 window = new TimeStack2();
					window.TS.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TimeStack2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	/** 현재시간 출력(시계) */
	class MyThread extends Thread {
		public void run() {
			int year, month, day, num;
			String daynum = null;
			String date;

			while (true) {
				calendar = Calendar.getInstance();
				year = calendar.get(Calendar.YEAR);
				month = calendar.get(Calendar.MONTH) + 1;
				day = calendar.get(Calendar.DAY_OF_MONTH);
				num = calendar.get(Calendar.DAY_OF_WEEK);
				switch (num) {
				case 1:
					daynum = "일";
					break;
				case 2:
					daynum = "월";
					break;
				case 3:
					daynum = "화";
					break;
				case 4:
					daynum = "수";
					break;
				case 5:
					daynum = "목";
					break;
				case 6:
					daynum = "금";
					break;
				case 7:
					daynum = "토";
					break;
				default:
					break;
				}
				date = (year + "년 " + month + "월 " + day + "일 " + daynum + "요일 ");
				sdf = new SimpleDateFormat("HH:mm:ss");
				label1_1.setText(date);
				label1_2.setText(sdf.format(calendar.getTime()));

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					continue;
				}
			}
		}
	}

	public void initialize() {
		this.tr = new TimeReception();

		/** 메인 프레임(창) */
		// TS = new JFrame();
		TS.setIconImage(Toolkit.getDefaultToolkit().getImage(
				"/home/pi/workspace/Time-Stack/TS1.png"));
		// "/home/pi/workspace/Time-Sync/TS.png"));
		TS.setResizable(false);
		// BorderLayout borderLayout = (BorderLayout)
		// TS.getContentPane().getLayout();
		TS.setBounds(700, 300, 900, 800);
		TS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TS.setTitle("Time-Stack 1.0");
		// TS.getContentPane().setBackground(Color.PINK);

		/** 패널1 */
		// panel1 = new JPanel();
		panel1.setBorder(new EmptyBorder(60, 0, 40, 0));
		TS.getContentPane().add(panel1, BorderLayout.NORTH);
		panel1.setBackground(Color.BLACK);
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		/** 패널1 안에 레이블1_1 */
		// label1_1 = new JLabel();
		label1_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel1.add(label1_1);
		label1_1.setFont(new Font("함초롬돋움", Font.BOLD, 35));
		label1_1.setBackground(Color.BLACK);
		label1_1.setForeground(Color.WHITE);
		label1_1.setVerticalAlignment(SwingConstants.CENTER);
		label1_1.setHorizontalAlignment(SwingConstants.CENTER);
		label1_1.setOpaque(true);
		/** 패널1 안에 레이블1_2 */
		// label1_2 = new JLabel();
		label1_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel1.add(label1_2);
		label1_2.setFont(new Font("함초롬돋움", Font.BOLD, 120));
		label1_2.setBackground(Color.BLACK);
		label1_2.setForeground(Color.WHITE);
		label1_2.setVerticalAlignment(SwingConstants.CENTER);
		label1_2.setHorizontalAlignment(SwingConstants.CENTER);
		label1_2.setOpaque(true);

		/** 패널2 */
		// panel2 = new JPanel();
		panel2.setBackground(Color.WHITE);
		panel2.setForeground(Color.BLACK);
		panel2.setBorder(new EmptyBorder(45, 110, 45, 110));
		TS.getContentPane().add(panel2, BorderLayout.CENTER);
		panel2.setLayout(new GridLayout(0, 1, 0, 40));

		/** 패널2 안에 버튼2_1 */
		button2_1 = new JButton("블록체인 시간 동기화");
		button2_1.setBackground(Color.WHITE);
		button2_1.setForeground(Color.BLACK);
		button2_1.setFont(new Font("함초롬돋움", Font.BOLD, 35));
		button2_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		button2_1.setBorder(new LineBorder(SystemColor.activeCaption, 5, true));
		// button2_1.setMargin(new Insets(12, 0, 12, 0));
		panel2.add(button2_1);

		button2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/** 블록체인 시간동기화 프레임(창) */
				// BCTimeSync = new JFrame();
				BCTimeSync.setResizable(false);
				// BorderLayout borderLayout = (BorderLayout)
				// BCNTimeSync.getContentPane().getLayout();
				BCTimeSync.setBounds(1500, 400, 900, 800);
				BCTimeSync.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				BCTimeSync.setTitle("블록체인 시간 동기화");
				BCTimeSync.setIconImage(Toolkit.getDefaultToolkit().getImage(
						"/home/pi/workspace/Time-Stack/TS1.png"));
				// "/home/pi/workspace/Time-Sync/TS.png"));
				BCTimeSync.getContentPane().setBackground(Color.WHITE);
				BCTimeSync.setVisible(true);

				/** 패널 BC1 */
				// panelBC1 = new JPanel();
				panelBC1.setBorder(new EmptyBorder(25, 0, 25, 0));
				BCTimeSync.getContentPane().add(panelBC1, BorderLayout.NORTH);
				panelBC1.setBackground(Color.WHITE);
				// panelBC1.setLayout(new BoxLayout(panelBC1,
				// BoxLayout.X_AXIS));
				// panelBC1.setLayout(new GridLayout(1, 0, 180, 0));
				/** 패널BC1 안에 패널 BC1_0 */
				// panelBC1_0 = new JPanel();
				LineBorder BC1_0BorderA = new LineBorder(Color.DARK_GRAY, 4,
						true);
				EmptyBorder BC1_0BorderB = new EmptyBorder(20, 80, 20, 80);
				panelBC1_0.setBorder(new CompoundBorder(BC1_0BorderA,
						BC1_0BorderB));
				panelBC1_0.setBackground(SystemColor.activeCaption);
				panelBC1.add(panelBC1_0);
				/** 패널BC1_0 안에 레이블BC1_1 */
				// labelBC1_1 = new JLabel("블록체인 시간 동기화");
				labelBC1_1.setAlignmentX(Component.CENTER_ALIGNMENT);
				panelBC1_0.add(labelBC1_1);
				labelBC1_1.setFont(new Font("함초롬돋움", Font.BOLD, 30));
				labelBC1_1.setBackground(SystemColor.activeCaption);
				labelBC1_1.setForeground(Color.BLACK);
				labelBC1_1.setVerticalAlignment(SwingConstants.CENTER);
				labelBC1_1.setHorizontalAlignment(SwingConstants.CENTER);
				labelBC1_1.setOpaque(true);
				/** 패널BC1_0 안에 패널BC1_1 */
				// panelBC1_1 = new JPanel();
				panelBC1_1.setBorder(new EmptyBorder(0, 100, 0, 0));
				// panelBC1_1.getContentPane().add(panelBC1_1,
				// BorderLayout.NORTH);
				panelBC1_1.setBackground(SystemColor.activeCaption);
				panelBC1_0.add(panelBC1_1);
				// panelBC1.setLayout(new BoxLayout(panelBC1_1,
				// BoxLayout.X_AXIS));
				// panelBC1_1.setLayout(new GridLayout(1, 0, 0, 0));
				ButtonGroup radiogroup = new ButtonGroup();
				// radiogroup.add(RadioButtonBC1_1_1);
				// radiogroup.add(RadioButtonBC1_1_2);
				/** 패널BC1_1 안에 라디오버튼BC1_1_1 */
				// RadioButtonBC1_1_1 = new JRadioButton("YES");
				RadioButtonBC1_1_1.setFont(new Font("함초롬돋움", Font.BOLD, 30));
				RadioButtonBC1_1_1.setBackground(SystemColor.activeCaption);
				RadioButtonBC1_1_1.setForeground(Color.WHITE);
				panelBC1_1.add(RadioButtonBC1_1_1);
				radiogroup.add(RadioButtonBC1_1_1);
				RadioButtonBC1_1_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						RadioButtonBC1_1_1.setForeground(Color.BLACK);
						RadioButtonBC1_1_2.setForeground(Color.WHITE);

						new Thread() {
							public void run() {
								// while (true) {
								try {

									String ip = mip.getMyIP();
									System.out.println("I am " + ip);

									String algorithm = "RSA"; // or RSA, DH,
																// DSA
																// etc.
									// Generate a 1024-bit Digital Signature
									// Algorithm (DSA) key pair
									KeyPairGenerator keyGen = KeyPairGenerator
											.getInstance(algorithm);

									keyGen.initialize(512, new SecureRandom());
									KeyPair keypair = keyGen.genKeyPair();

									KeyPair keyPair2 = keyGen.generateKeyPair();

									KeyManager km = new KeyManager("/" + ip,
											keypair);
									km.setKeyFile("KeyFile");

									db.setDB();

									IpScanManager ism = new IpScanManager(
											"192.168.0");
									ism.start();

									Blockchain block_chain = new Blockchain();
									block_chain.start();

									TimeManagement tm = new TimeManagement(km);

									BlockNetworkManager bnm = new BlockNetworkManager(
											block_chain, tm);
									bnm.start();

									Thread.sleep(10000);

									PeerNetworkManager pnm = new PeerNetworkManager(
											block_chain, km, bnm, tm);
									pnm.start();

									Thread.sleep(30000);
									while (true) {
										pnm.unicast("192.168.0.11");
										Thread.sleep(300000);
									}
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								// }
							}
						}.start();
					}
				});

				/** 패널BC1_1 안에 라디오버튼BC1_1_2 */
				// RadioButtonBC1_1_2 = new JRadioButton("NO");
				RadioButtonBC1_1_2.setFont(new Font("함초롬돋움", Font.BOLD, 30));
				RadioButtonBC1_1_2.setBorder(new EmptyBorder(0, 40, 0, 0));
				RadioButtonBC1_1_2.setBackground(SystemColor.activeCaption);
				RadioButtonBC1_1_2.setForeground(Color.BLACK);
				panelBC1_1.add(RadioButtonBC1_1_2);
				radiogroup.add(RadioButtonBC1_1_2);
				RadioButtonBC1_1_2.setSelected(true);

				RadioButtonBC1_1_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						RadioButtonBC1_1_2.setForeground(Color.BLACK);
						RadioButtonBC1_1_1.setForeground(Color.WHITE);

					}
				});

				/** 패널 BC2 */
				// panelBC2 = new JPanel();
				panelBC2.setBackground(Color.WHITE);
				BCTimeSync.getContentPane().add(panelBC2, BorderLayout.CENTER);
				panelBC2.setBorder(new EmptyBorder(20, 110, 40, 110));
				panelBC2.setBackground(Color.WHITE);
				panelBC2.setLayout(new GridLayout(0, 1, 0, 36));

				/** 패널BC2 안에 버튼BC2_1 */
				// buttonBC2_1 = new JButton("Show IP List");
				buttonBC2_1.setBackground(Color.WHITE);
				buttonBC2_1.setForeground(Color.BLACK);
				buttonBC2_1.setFont(new Font("함초롬돋움", Font.BOLD, 28));
				buttonBC2_1.setAlignmentX(Component.CENTER_ALIGNMENT);
				buttonBC2_1.setBorder(new LineBorder(SystemColor.activeCaption,
						5, true));
				panelBC2.add(buttonBC2_1);
				buttonBC2_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						/** Show IP List 프레임(창) */
						// shIPList = new JFrame();
						shIPList.setResizable(false);
						// BorderLayout borderLayout = (BorderLayout)
						// BCNTimeSync.getContentPane().getLayout();
						shIPList.setBounds(1600, 500, 900, 800);
						shIPList.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						shIPList.setTitle("Show IP List");
						shIPList.setIconImage(Toolkit
								.getDefaultToolkit()
								.getImage(
										"/home/pi/workspace/Time-Stack/TS1.png"));
						// "/home/pi/workspace/Time-Sync/TS.png"));
						shIPList.getContentPane().setBackground(Color.WHITE);
						shIPList.setVisible(true);

						/** 패널shI1 */
						// panelshI1 = new JPanel();
						panelshI1.setBorder(new EmptyBorder(30, 40, 30, 0));
						shIPList.getContentPane().add(panelshI1,
								BorderLayout.NORTH);
						panelshI1.setBackground(Color.DARK_GRAY);
						panelshI1.setLayout(new BoxLayout(panelshI1,
								BoxLayout.X_AXIS));
						/** 패널shI1 안에 레이블shI1 */
						// labelshI1 = new JLabel("*  IP List  ");
						labelshI1.setAlignmentX(Component.LEFT_ALIGNMENT);
						panelshI1.add(labelshI1);
						labelshI1.setFont(new Font("함초롬돋움", Font.BOLD, 30));
						labelshI1.setBackground(Color.DARK_GRAY);
						labelshI1.setForeground(Color.WHITE);
						labelshI1.setVerticalAlignment(SwingConstants.CENTER);
						labelshI1.setHorizontalAlignment(SwingConstants.CENTER);
						labelshI1.setOpaque(true);

						/** 패널shI2 */
						// panelshI2 = new JPanel();
						panelshI2.setBackground(Color.WHITE);
						EmptyBorder shI2BorderA = new EmptyBorder(30, 25, 25,
								25);
						LineBorder shI2BorderB = new LineBorder(
								Color.DARK_GRAY, 5, true);
						panelshI2.setBorder(new CompoundBorder(shI2BorderA,
								shI2BorderB));
						shIPList.getContentPane().add(panelshI2,
								BorderLayout.CENTER);
						panelshI2.setLayout(new BoxLayout(panelshI2,
								BoxLayout.X_AXIS));
						/** 패널shI2 안에 패널shI2_1 */
						// panelshI2_1 = new JPanel();
						panelshI2_1.setBorder(new EmptyBorder(35, 30, 35, 15));
						panelshI2_1.setBackground(Color.WHITE);
						panelshI2_1.setLayout(new GridLayout(0, 1, 0, 35));
						panelshI2.add(panelshI2_1);
						/** 패널shI2_1 안에 레이블shI2_1_1 */
						// labelshI2_1_1 = new JLabel("Network :");
						labelshI2_1_1.setAlignmentX(Component.CENTER_ALIGNMENT);
						labelshI2_1_1.setFont(new Font("함초롬돋움", Font.BOLD, 26));
						labelshI2_1_1.setBackground(Color.WHITE);
						labelshI2_1_1.setForeground(Color.BLACK);
						labelshI2_1_1
								.setVerticalAlignment(SwingConstants.CENTER);
						labelshI2_1_1
								.setHorizontalAlignment(SwingConstants.LEFT);
						labelshI2_1_1.setOpaque(true);
						panelshI2_1.add(labelshI2_1_1);
						/** 패널shI2 안에 패널shI2_2 */
						// panelshI2_2 = new JPanel();
						panelshI2_2.setBorder(new EmptyBorder(35, 15, 35, 30));
						panelshI2_2.setBackground(Color.WHITE);
						panelshI2_2.setLayout(new GridLayout(0, 1, 0, 35));
						panelshI2.add(panelshI2_2);
						/** 패널shI2_2 안에 텍스트필드shI2_2_1 */
						// textFieldshI2_2_1 = new JTextField();
						textFieldshI2_2_1.setBackground(Color.WHITE);
						textFieldshI2_2_1.setBorder(new LineBorder(
								Color.DARK_GRAY, 2));
						textFieldshI2_2_1.setFont(new Font("함초롬돋움", Font.BOLD,
								26));
						textFieldshI2_2_1
								.setHorizontalAlignment(SwingConstants.CENTER);
						textFieldshI2_2_1.setEditable(false);
						textFieldshI2_2_1.setEnabled(true);
						textFieldshI2_2_1.setText(mip.getMyNetwork());
						panelshI2_2.add(textFieldshI2_2_1);

						/** 패널shI3 */
						// panelshI3 = new JPanel();
						panelshI3.setBorder(new EmptyBorder(0, 25, 10, 25));
						shIPList.getContentPane().add(panelshI3,
								BorderLayout.SOUTH);
						panelshI3.setBackground(Color.WHITE);
						panelshI3.setLayout(new BoxLayout(panelshI3,
								BoxLayout.Y_AXIS));
						/** 패널shI3 안에 패널shI3_0 */
						// panelshI3_0 = new JPanel();
						LineBorder shI3_0BorderA = new LineBorder(Color.WHITE,
								2, true);
						EmptyBorder shI3_0BorderB = new EmptyBorder(10, 10, 10,
								10);
						panelshI3_0.setBorder(new CompoundBorder(shI3_0BorderA,
								shI3_0BorderB));
						panelshI3_0.setBackground(SystemColor.activeCaption);
						panelshI3.add(panelshI3_0);
						/** 패널shI3_0 안에 레이블shI3_0 */
						// labelshI3_0 = new JLabel("Reachable IP");
						// labelshI3.setAlignmentX(Component.CENTER_ALIGNMENT);
						labelshI3_0.setFont(new Font("함초롬돋움", Font.BOLD, 26));
						labelshI3_0.setBackground(SystemColor.activeCaption);
						labelshI3_0.setForeground(Color.BLACK);
						// labelshI3.setVerticalAlignment(SwingConstants.CENTER);
						// labelshI3.setHorizontalAlignment(SwingConstants.CENTER);
						labelshI3_0.setOpaque(true);
						panelshI3_0.add(labelshI3_0);//
						/** 패널shI3 안에 패널shI3_1 */
						// panelshI3_1 = new JPanel();
						panelshI3_1.setBorder(new EmptyBorder(0, 0, 0, 0));
						panelshI3_1.setBackground(Color.WHITE);
						panelshI3.add(panelshI3_1);//
						/** 패널shI3_1 안에 리스트shI3_1 */
						

						DefaultListModel shI3_1  = null;
						Scanner scan;
						String next;
						try {
							scan = new Scanner(db.getIpListFile());
							shI3_1 = new DefaultListModel();
							while (scan.hasNextLine()) {
								next = scan.nextLine();
								shI3_1.addElement(next);
							}
							scan.close();
						} catch (Exception e) {

						}

						/*
						 * shI3_1.addElement("192.168.35.1");
						 * shI3_1.addElement("192.168.35.2");
						 * shI3_1.addElement("192.168.35.3");
						 * shI3_1.addElement("192.168.35.4");
						 * shI3_1.addElement("192.168.35.5");
						 * shI3_1.addElement("192.168.35.6");
						 * shI3_1.addElement("192.168.35.7");
						 * shI3_1.addElement("192.168.35.8");
						 * shI3_1.addElement("192.168.35.9");
						 * shI3_1.addElement("192.168.35.10");
						 */
						listshI3_1 = new JList(shI3_1);

						listshI3_1.setFont(new Font("함초롬돋움", Font.BOLD, 26));
						// listshI3_1.setBorder(new EmptyBorder(30, 40, 30, 0));
						// listshI3_1.setBorder(BorderFactory.createLineBorder(Color.BLACK,
						// 1));
						EmptyBorder shI3_1BorderA = new EmptyBorder(20, 20, 20,
								20);
						LineBorder shI3_1BorderB = new LineBorder(Color.WHITE,
								2, true);
						listshI3_1.setBorder(new CompoundBorder(shI3_1BorderA,
								shI3_1BorderB));
						listshI3_1.setBackground(Color.WHITE);
						listshI3_1.setForeground(Color.BLACK);
						Iscroller = new JScrollPane(listshI3_1);
						Iscroller.setBorder(new LineBorder(
								SystemColor.activeCaption, 4, true));
						Iscroller
								.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
						Iscroller.setPreferredSize(new Dimension(840, 350));
						panelshI3_1.add(Iscroller);
					}
				});

				/** 패널BC2 안에 버튼BC2_2 */
				// buttonBC2_2 = new JButton("Show Peer List");
				buttonBC2_2.setBackground(Color.WHITE);
				buttonBC2_2.setForeground(Color.BLACK);
				buttonBC2_2.setFont(new Font("함초롬돋움", Font.BOLD, 28));
				buttonBC2_2.setAlignmentX(Component.CENTER_ALIGNMENT);
				buttonBC2_2.setBorder(new LineBorder(SystemColor.activeCaption,
						5, true));
				panelBC2.add(buttonBC2_2);
				buttonBC2_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						/** Show Peer List 프레임(창) */
						// shPeerList = new JFrame();
						shPeerList.setResizable(false);
						// BorderLayout borderLayout = (BorderLayout)
						// BCNTimeSync.getContentPane().getLayout();
						shPeerList.setBounds(1600, 500, 900, 950);
						shPeerList
								.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						shPeerList.setTitle("Show Peer List");
						shPeerList
								.setIconImage(Toolkit
										.getDefaultToolkit()
										.getImage(
												"/home/pi/workspace/Time-Stack/TS1.png"));
						// "/home/pi/workspace/Time-Sync/TS.png"));
						shPeerList.getContentPane().setBackground(Color.WHITE);
						shPeerList.setVisible(true);

						/** 패널 shP1 */
						// panelshP1 = new JPanel();
						panelshP1.setBorder(new EmptyBorder(30, 40, 30, 0));
						shPeerList.getContentPane().add(panelshP1,
								BorderLayout.NORTH);
						panelshP1.setBackground(Color.DARK_GRAY);
						panelshP1.setLayout(new BoxLayout(panelshP1,
								BoxLayout.X_AXIS));
						/** 패널shP1 안에 레이블shP1 */
						// labelshP1 = new JLabel("*  Peer List  ");
						labelshP1.setAlignmentX(Component.LEFT_ALIGNMENT);
						panelshP1.add(labelshP1);
						labelshP1.setFont(new Font("함초롬돋움", Font.BOLD, 30));
						labelshP1.setBackground(Color.DARK_GRAY);
						labelshP1.setForeground(Color.WHITE);
						labelshP1.setVerticalAlignment(SwingConstants.CENTER);
						labelshP1.setHorizontalAlignment(SwingConstants.CENTER);
						labelshP1.setOpaque(true);

						/** 패널shP2 */
						// panelshP2 = new JPanel();
						panelshP2.setBackground(Color.WHITE);
						EmptyBorder shP2BorderA = new EmptyBorder(30, 25, 25,
								25);
						LineBorder shP2BorderB = new LineBorder(
								Color.DARK_GRAY, 5, true);
						panelshP2.setBorder(new CompoundBorder(shP2BorderA,
								shP2BorderB));
						shPeerList.getContentPane().add(panelshP2,
								BorderLayout.CENTER);
						panelshP2.setLayout(new BoxLayout(panelshP2,
								BoxLayout.X_AXIS));
						/** 패널shP2 안에 패널shP2_1 */
						// panelshP2_1 = new JPanel();
						panelshP2_1.setBorder(new EmptyBorder(35, 30, 35, 15));
						panelshP2_1.setBackground(Color.WHITE);
						panelshP2_1.setLayout(new GridLayout(0, 1, 0, 35));
						panelshP2.add(panelshP2_1);
						/** 패널shP2_1 안에 레이블shP2_1_1 */
						// labelshP2_1_1 = new JLabel("Network :");
						labelshP2_1_1.setAlignmentX(Component.CENTER_ALIGNMENT);
						labelshP2_1_1.setFont(new Font("함초롬돋움", Font.BOLD, 26));
						labelshP2_1_1.setBackground(Color.WHITE);
						labelshP2_1_1.setForeground(Color.BLACK);
						labelshP2_1_1
								.setVerticalAlignment(SwingConstants.CENTER);
						labelshP2_1_1
								.setHorizontalAlignment(SwingConstants.LEFT);
						labelshP2_1_1.setOpaque(true);
						panelshP2_1.add(labelshP2_1_1);
						/** 패널shP2_1 안에 레이블shP2_1_2 */
						// labelshP2_1_2 = new JLabel("Netmask :");
						labelshP2_1_2.setAlignmentX(Component.CENTER_ALIGNMENT);
						labelshP2_1_2.setFont(new Font("함초롬돋움", Font.BOLD, 26));
						labelshP2_1_2.setBackground(Color.WHITE);
						labelshP2_1_2.setForeground(Color.BLACK);
						labelshP2_1_2
								.setVerticalAlignment(SwingConstants.CENTER);
						labelshP2_1_2
								.setHorizontalAlignment(SwingConstants.LEFT);
						labelshP2_1_2.setOpaque(true);
						panelshP2_1.add(labelshP2_1_2);
						/** 패널shP2_1 안에 레이블shP2_1_3 */
						// labelshP2_1_3 = new JLabel("Gateway :");
						labelshP2_1_3.setAlignmentX(Component.CENTER_ALIGNMENT);
						labelshP2_1_3.setFont(new Font("함초롬돋움", Font.BOLD, 26));
						labelshP2_1_3.setBackground(Color.WHITE);
						labelshP2_1_3.setForeground(Color.BLACK);
						labelshP2_1_3
								.setVerticalAlignment(SwingConstants.CENTER);
						labelshP2_1_3
								.setHorizontalAlignment(SwingConstants.LEFT);
						labelshP2_1_3.setOpaque(true);
						panelshP2_1.add(labelshP2_1_3);
						/** 패널shP2_1 안에 레이블shP2_1_4 */
						// labelshP2_1_4 = new JLabel("Number of peers :");
						labelshP2_1_4.setAlignmentX(Component.CENTER_ALIGNMENT);
						labelshP2_1_4.setFont(new Font("함초롬돋움", Font.BOLD, 26));
						labelshP2_1_4.setBackground(Color.WHITE);
						labelshP2_1_4.setForeground(Color.BLACK);
						labelshP2_1_4
								.setVerticalAlignment(SwingConstants.CENTER);
						labelshP2_1_4
								.setHorizontalAlignment(SwingConstants.LEFT);
						labelshP2_1_4.setOpaque(true);
						panelshP2_1.add(labelshP2_1_4);
						/** 패널shP2 안에 패널shP2_2 */
						// panelshP2_2 = new JPanel();
						panelshP2_2.setBorder(new EmptyBorder(35, 15, 35, 30));
						panelshP2_2.setBackground(Color.WHITE);
						panelshP2_2.setLayout(new GridLayout(0, 1, 0, 35));
						panelshP2.add(panelshP2_2);
						/** 패널shP2_2 안에 텍스트필드shP2_2_1 */
						// textFieldshP2_2_1 = new JTextField();
						textFieldshP2_2_1.setBackground(Color.WHITE);
						textFieldshP2_2_1.setBorder(new LineBorder(
								Color.DARK_GRAY, 2));
						textFieldshP2_2_1.setFont(new Font("함초롬돋움", Font.BOLD,
								26));
						textFieldshP2_2_1
								.setHorizontalAlignment(SwingConstants.CENTER);
						textFieldshP2_2_1.setEditable(false);
						textFieldshP2_2_1.setEnabled(true);
						textFieldshP2_2_1.setText(mip.getMyNetwork());
						panelshP2_2.add(textFieldshP2_2_1);
						/** 패널shP2_2 안에 텍스트필드shP2_2_2 */
						// textFieldshP2_2_2 = new JTextField();
						textFieldshP2_2_2.setBackground(Color.WHITE);
						textFieldshP2_2_2.setBorder(new LineBorder(
								Color.DARK_GRAY, 2));
						textFieldshP2_2_2.setFont(new Font("함초롬돋움", Font.BOLD,
								26));
						textFieldshP2_2_2
								.setHorizontalAlignment(SwingConstants.CENTER);
						textFieldshP2_2_2.setEditable(false);
						textFieldshP2_2_2.setEnabled(true);
						textFieldshP2_2_2.setText(mip.getMyNetmask());
						panelshP2_2.add(textFieldshP2_2_2);
						/** 패널shP2_2 안에 텍스트필드shP2_2_3 */
						// textFieldshP2_2_3 = new JTextField();
						textFieldshP2_2_3.setBackground(Color.WHITE);
						textFieldshP2_2_3.setBorder(new LineBorder(
								Color.DARK_GRAY, 2));
						textFieldshP2_2_3.setFont(new Font("함초롬돋움", Font.BOLD,
								26));
						textFieldshP2_2_3
								.setHorizontalAlignment(SwingConstants.CENTER);
						textFieldshP2_2_3.setEditable(false);
						textFieldshP2_2_3.setEnabled(true);
						textFieldshP2_2_3.setText(mip.getMyGateway());
						panelshP2_2.add(textFieldshP2_2_3);
						/** 패널shP2_2 안에 텍스트필드shP2_2_4 */
						// textFieldshP2_2_4 = new JTextField();
						textFieldshP2_2_4.setBackground(Color.WHITE);
						textFieldshP2_2_4.setBorder(new LineBorder(
								Color.DARK_GRAY, 2));
						textFieldshP2_2_4.setFont(new Font("함초롬돋움", Font.BOLD,
								26));
						textFieldshP2_2_4
								.setHorizontalAlignment(SwingConstants.CENTER);
						textFieldshP2_2_1.setEditable(false);
						textFieldshP2_2_4.setEnabled(true);

						// panelshP2_2.add(textFieldshP2_2_4);

						/** 패널shP3 */
						// panelshP3 = new JPanel();
						panelshP3.setBorder(new EmptyBorder(0, 25, 10, 25));
						shPeerList.getContentPane().add(panelshP3,
								BorderLayout.SOUTH);
						panelshP3.setBackground(Color.WHITE);
						panelshP3.setLayout(new BoxLayout(panelshP3,
								BoxLayout.Y_AXIS));
						/** 패널shP3 안에 패널shP3_0 */
						// panelshP3_0 = new JPanel();
						LineBorder shP3_0BorderA = new LineBorder(Color.WHITE,
								2, true);
						EmptyBorder shP3_0BorderB = new EmptyBorder(10, 10, 10,
								10);
						panelshP3_0.setBorder(new CompoundBorder(shP3_0BorderA,
								shP3_0BorderB));
						panelshP3_0.setBackground(SystemColor.activeCaption);
						panelshP3.add(panelshP3_0);
						/** 패널shP3_0 안에 레이블shP3_0 */
						// labelshP3_0 = new JLabel("Peer's IP");
						// labelshP3_0.setAlignmentX(Component.CENTER_ALIGNMENT);
						labelshP3_0.setFont(new Font("함초롬돋움", Font.BOLD, 26));
						labelshP3_0.setBackground(SystemColor.activeCaption);
						labelshP3_0.setForeground(Color.BLACK);
						// labelshP3_0.setVerticalAlignment(SwingConstants.CENTER);
						// labelshP3_0.setHorizontalAlignment(SwingConstants.CENTER);
						labelshP3_0.setOpaque(true);
						panelshP3_0.add(labelshP3_0);//
						/** 패널shP3 안에 패널shP3_1 */
						// panelshP3_1 = new JPanel();
						panelshP3_1.setBorder(new EmptyBorder(0, 0, 0, 0));
						panelshP3_1.setBackground(Color.WHITE);
						panelshP3.add(panelshP3_1);//
						/** 패널shI3_1 안에 리스트shI3_1 */
						

						DefaultListModel shP3_1 = null;
						Scanner scan;
						String next;
						int index = 0;
						try {
							scan = new Scanner(db.getPeerListFile());
							shP3_1 = new DefaultListModel();
							while (scan.hasNextLine()) {
								next = scan.nextLine();
								shP3_1.addElement(next);
								index++;
							}
							scan.close();
						} catch (Exception e) {

						}

						textFieldshP2_2_4.setText(String.valueOf(index));
						panelshP2_2.add(textFieldshP2_2_4);

						/*
						 * shP3_1.addElement("192.168.35.6");
						 * shP3_1.addElement("192.168.35.7");
						 * shP3_1.addElement("192.168.35.8");
						 * shP3_1.addElement("192.168.35.9");
						 * shP3_1.addElement("192.168.35.10");
						 */
						listshP3_1 = new JList(shP3_1);

						listshP3_1.setFont(new Font("함초롬돋움", Font.BOLD, 26));
						// listshP3_1.setBorder(new EmptyBorder(30, 40, 30, 0));
						// listshP3_1.setBorder(BorderFactory.createLineBorder(Color.BLACK,
						// 1));
						EmptyBorder shP3_1BorderA = new EmptyBorder(20, 20, 20,
								20);
						LineBorder shP3_1BorderB = new LineBorder(Color.WHITE,
								2, true);
						listshP3_1.setBorder(new CompoundBorder(shP3_1BorderA,
								shP3_1BorderB));
						listshP3_1.setBackground(Color.WHITE);
						listshP3_1.setForeground(Color.BLACK);
						Pscroller = new JScrollPane(listshP3_1);
						Pscroller.setBorder(new LineBorder(
								SystemColor.activeCaption, 4, true));
						Pscroller
								.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
						Pscroller.setPreferredSize(new Dimension(840, 200));
						panelshP3_1.add(Pscroller);
					}
				});

				/** 패널BC2 안에 버튼BC2_3 */
				// buttonBC2_3 = new JButton("Show Blockchain");
				buttonBC2_3.setBackground(Color.WHITE);
				buttonBC2_3.setForeground(Color.BLACK);
				buttonBC2_3.setFont(new Font("함초롬돋움", Font.BOLD, 28));
				buttonBC2_3.setAlignmentX(Component.CENTER_ALIGNMENT);
				buttonBC2_3.setBorder(new LineBorder(SystemColor.activeCaption,
						5, true));
				panelBC2.add(buttonBC2_3);
				buttonBC2_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						/** Show Blockchain 프레임(창) */
						// shBlockchain = new JFrame();
						shBlockchain.setResizable(false);
						// BorderLayout borderLayout = (BorderLayout)
						// BCNTimeSync.getContentPane().getLayout();
						shBlockchain.setBounds(1600, 500, 900, 800);
						shBlockchain
								.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						shBlockchain.setTitle("Show Blockchain");
						shBlockchain
								.setIconImage(Toolkit
										.getDefaultToolkit()
										.getImage(
												"/home/pi/workspace/Time-Stack/TS1.png"));
						// "/home/pi/workspace/Time-Sync/TS.png"));
						shBlockchain.getContentPane()
								.setBackground(Color.WHITE);
						shBlockchain.setVisible(true);

					}
				});

				/** 패널BC2 안에 버튼BC2_4 */
				// buttonBC2_4 = new JButton("Show Time Sync History");
				buttonBC2_4.setBackground(Color.WHITE);
				buttonBC2_4.setForeground(Color.BLACK);
				buttonBC2_4.setFont(new Font("함초롬돋움", Font.BOLD, 28));
				buttonBC2_4.setAlignmentX(Component.CENTER_ALIGNMENT);
				buttonBC2_4.setBorder(new LineBorder(SystemColor.activeCaption,
						5, true));
				panelBC2.add(buttonBC2_4);
				buttonBC2_4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						/** Show Time Sync History 프레임(창) */
						// shTSHistory = new JFrame();
						shTSHistory.setResizable(false);
						// BorderLayout borderLayout = (BorderLayout)
						// BCNTimeSync.getContentPane().getLayout();
						shTSHistory.setBounds(1600, 500, 900, 800);
						shTSHistory
								.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						shTSHistory.setTitle("Show Time Sync History");
						shTSHistory
								.setIconImage(Toolkit
										.getDefaultToolkit()
										.getImage(
												"/home/pi/workspace/Time-Stack/TS1.png"));
						// "/home/pi/workspace/Time-Sync/TS.png"));
						shTSHistory.getContentPane().setBackground(Color.WHITE);
						shTSHistory.setVisible(true);

					}
				});
			}
		});

		/** 패널2 안에 버튼2_2 */
		// button2_2 = new JButton("서버시간 동기화");
		button2_2.setForeground(Color.BLACK);
		button2_2.setBackground(Color.WHITE);
		button2_2.setFont(new Font("함초롬돋움", Font.BOLD, 35));
		button2_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		button2_2.setBorder(new LineBorder(SystemColor.activeCaption, 5, true));
		// button2_2.setMargin(new Insets(12, 0, 12, 0));
		panel2.add(button2_2);

		STimeSync.setBounds(1500, 400, 900, 800);
		STimeSync.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		STimeSync.setTitle("서버시간 동기화");
		STimeSync.setIconImage(Toolkit.getDefaultToolkit().getImage(
				"/home/pi/workspace/Time-Stack/TS1.png"));
		// "/home/pi/workspace/Time-Sync/TS.png"));
		STimeSync.getContentPane().setBackground(Color.WHITE);

		panelS1.setBorder(new EmptyBorder(25, 0, 25, 0));
		STimeSync.getContentPane().add(panelS1, BorderLayout.NORTH);
		panelS1.setBackground(Color.WHITE);
		// panelS1.setLayout(new BoxLayout(panelS1,
		// BoxLayout.X_AXIS));
		// panelS1.setLayout(new GridLayout(1, 0, 10, 0));
		/** 패널S1 안에 패널 S1_0 */
		// panelS1_0 = new JPanel();
		LineBorder S1_0BorderA = new LineBorder(Color.DARK_GRAY, 4, true);
		EmptyBorder S1_0BorderB = new EmptyBorder(20, 110, 20, 110);
		panelS1_0.setBorder(new CompoundBorder(S1_0BorderA, S1_0BorderB));
		panelS1_0.setBackground(SystemColor.activeCaption);
		panelS1.add(panelS1_0);
		/** 패널S1_0 안에 레이블S1_1 */
		// labelS1_1 = new JLabel("시간 동기 활성화");
		labelS1_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelS1_0.add(labelS1_1);
		labelS1_1.setFont(new Font("함초롬돋움", Font.BOLD, 30));
		labelS1_1.setBackground(SystemColor.activeCaption);
		labelS1_1.setForeground(Color.BLACK);
		labelS1_1.setVerticalAlignment(SwingConstants.CENTER);
		labelS1_1.setHorizontalAlignment(SwingConstants.CENTER);
		labelS1_1.setOpaque(true);
		/** 패널S1_0 안에 패널 S1_1 */
		// panelS1_1 = new JPanel();
		panelS1_1.setBorder(new EmptyBorder(0, 100, 0, 0));
		// panelS1_1.getContentPane().add(panelS1_1,
		// BorderLayout.NORTH);
		panelS1_1.setBackground(SystemColor.activeCaption);
		panelS1_0.add(panelS1_1);
		// panelS1.setLayout(new BoxLayout(panelS1_1,
		// BoxLayout.X_AXIS));
		// panelS1_1.setLayout(new GridLayout(1, 0, 0, 0));

		// radiogroup.add(RadioButtonS1_1_1);
		// radiogroup.add(RadioButtonS1_1_2);
		/** 패널S1_1 안에 라디오버튼S1_1_1 */
		RadioButtonS1_1_1 = new JRadioButton("YES");
		RadioButtonS1_1_1.setFont(new Font("함초롬돋움", Font.BOLD, 30));
		RadioButtonS1_1_1.setBackground(SystemColor.activeCaption);
		RadioButtonS1_1_1.setForeground(Color.WHITE);
		panelS1_1.add(RadioButtonS1_1_1);
		radiogroup.add(RadioButtonS1_1_1);

		/** 패널 S2 */
		// panelS2 = new JPanel();

		panelS2.setBackground(Color.WHITE);
		EmptyBorder S2BorderA = new EmptyBorder(25, 25, 25, 25);
		LineBorder S2BorderB = new LineBorder(SystemColor.activeCaption, 5,
				true);
		panelS2.setBorder(new CompoundBorder(S2BorderA, S2BorderB));
		STimeSync.getContentPane().add(panelS2, BorderLayout.CENTER);
		panelS2.setLayout(new BoxLayout(panelS2, BoxLayout.X_AXIS));
		/** 패널S2 안에 패널 S2_1 */
		// panelS2_1 = new JPanel();
		panelS2_1.setBorder(new EmptyBorder(40, 30, 40, 15));
		panelS2_1.setBackground(Color.WHITE);
		panelS2_1.setLayout(new GridLayout(0, 1, 0, 50));
		panelS2.add(panelS2_1);
		/** 패널S2_1 안에 레이블S2_1_1 */
		// labelS2_1_1 = new JLabel("TimeServer :");
		labelS2_1_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelS2_1_1.setFont(new Font("함초롬돋움", Font.BOLD, 26));
		labelS2_1_1.setBackground(Color.WHITE);
		labelS2_1_1.setForeground(Color.GRAY);
		labelS2_1_1.setVerticalAlignment(SwingConstants.CENTER);
		labelS2_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		labelS2_1_1.setOpaque(true);
		panelS2_1.add(labelS2_1_1);
		/** 패널S2_1 안에 레이블S2_1_2 */
		// labelS2_1_2 = new JLabel("Clock offset(ms) :");
		labelS2_1_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelS2_1_2.setFont(new Font("함초롬돋움", Font.BOLD, 26));
		labelS2_1_2.setBackground(Color.WHITE);
		labelS2_1_2.setForeground(Color.GRAY);
		labelS2_1_2.setVerticalAlignment(SwingConstants.CENTER);
		labelS2_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		labelS2_1_2.setOpaque(true);
		panelS2_1.add(labelS2_1_2);
		/** 패널S2_1 안에 레이블S2_1_3 */
		// labelS2_1_3 = new JLabel("New Time(ms) :");
		labelS2_1_3.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelS2_1_3.setFont(new Font("함초롬돋움", Font.BOLD, 26));
		labelS2_1_3.setBackground(Color.WHITE);
		labelS2_1_3.setForeground(Color.GRAY);
		labelS2_1_3.setVerticalAlignment(SwingConstants.CENTER);
		labelS2_1_3.setHorizontalAlignment(SwingConstants.LEFT);
		labelS2_1_3.setOpaque(true);
		panelS2_1.add(labelS2_1_3);
		/** 패널S2_1 안에 레이블S2_1_4 */
		// labelS2_1_4 = new JLabel("New Time(HH:mm:ss) :");
		labelS2_1_4.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelS2_1_4.setFont(new Font("함초롬돋움", Font.BOLD, 26));
		labelS2_1_4.setBackground(Color.WHITE);
		labelS2_1_4.setForeground(Color.GRAY);
		labelS2_1_4.setVerticalAlignment(SwingConstants.CENTER);
		labelS2_1_4.setHorizontalAlignment(SwingConstants.CENTER);
		labelS2_1_4.setOpaque(true);
		panelS2_1.add(labelS2_1_4);
		/** 패널S2 안에 패널 S2_2 */
		// panelS2_2 = new JPanel();
		panelS2_2.setBorder(new EmptyBorder(40, 15, 40, 30));
		panelS2_2.setLayout(new GridLayout(0, 1, 0, 50));
		panelS2_2.setBackground(Color.WHITE);
		panelS2.add(panelS2_2);
		/** 패널S2_2 안에 텍스트필드S2_2_1 */
		// textFieldS2_2_1 = new JTextField();
		// textFieldS2_2_1.setBackground(SystemColor.control);
		textFieldS2_2_1.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		textFieldS2_2_1.setFont(new Font("함초롬돋움", Font.BOLD, 26));
		textFieldS2_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldS2_2_1.setEditable(false);
		textFieldS2_2_1.setEnabled(false);
		panelS2_2.add(textFieldS2_2_1);
		/** 패널S2_2 안에 텍스트필드S2_2_2 */
		// textFieldS2_2_2 = new JTextField(20);
		// textFieldS2_2_2.setBackground(Color.WHITE);
		textFieldS2_2_2.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		textFieldS2_2_2.setFont(new Font("함초롬돋움", Font.BOLD, 26));
		textFieldS2_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldS2_2_2.setEditable(false);
		textFieldS2_2_2.setEnabled(false);
		panelS2_2.add(textFieldS2_2_2);
		/** 패널S2_2 안에 텍스트필드S2_2_3 */
		// textFieldS2_2_3 = new JTextField(20);
		// textFieldS2_2_3.setBackground(Color.WHITE);
		textFieldS2_2_3.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		textFieldS2_2_3.setFont(new Font("함초롬돋움", Font.BOLD, 26));
		textFieldS2_2_3.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldS2_2_3.setEditable(false);
		textFieldS2_2_3.setEnabled(false);
		panelS2_2.add(textFieldS2_2_3);
		/** 패널S2_2 안에 텍스트필드S2_2_4 */
		// textFieldS2_2_4 = new JTextField(20);
		// textFieldS2_2_4.setBackground(Color.WHITE);
		textFieldS2_2_4.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		textFieldS2_2_4.setFont(new Font("함초롬돋움", Font.BOLD, 26));
		textFieldS2_2_4.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldS2_2_4.setEditable(false);
		textFieldS2_2_4.setEnabled(false);
		panelS2_2.add(textFieldS2_2_4);

		button2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Thread() {
					public void run() {
						/** 서버시간 동기화 프레임(창) */
						// STimeSync = new JFrame();
						STimeSync.setResizable(false);
						// BorderLayout borderLayout = (BorderLayout)
						// STimeSync.getContentPane().getLayout();
						STimeSync.setVisible(true);

						/** 패널 S1 */
						// panelS1 = new JPanel();

						RadioButtonS1_1_1
								.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {
										new Thread() {
											public void run() {
												// while(true){
												/** 시간 동기화 수행 */
												// tr.initTimeServer();
												/*
												 * if(TimeServer.equals("")) {
												 * tr.initTimeServer();
												 * TimeServer =
												 * tr.getTimeServer(); } else
												 * tr.setTimeServer(TimeServer);
												 */
												tr.timereceive();
												tr.timesynchronize();
												// label3.setText("동기화 되었습니다.");
												RadioButtonS1_1_1
														.setForeground(Color.BLACK);
												RadioButtonS1_1_2
														.setForeground(Color.WHITE);
												labelS2_1_1
														.setForeground(Color.BLACK);
												labelS2_1_2
														.setForeground(Color.BLACK);
												labelS2_1_3
														.setForeground(Color.BLACK);
												labelS2_1_4
														.setForeground(Color.BLACK);
												textFieldS2_2_1
														.setEnabled(true);
												textFieldS2_2_1
														.setBackground(Color.WHITE);
												textFieldS2_2_1
														.setText(tr.timeServer);
												textFieldS2_2_2
														.setEnabled(true);
												textFieldS2_2_2
														.setBackground(Color.WHITE);
												textFieldS2_2_2.setText(Long
														.toString(tr.offsetValue));
												textFieldS2_2_3
														.setEnabled(true);
												textFieldS2_2_3
														.setBackground(Color.WHITE);
												textFieldS2_2_3.setText(Long
														.toString(tr.msNewTime));
												textFieldS2_2_4
														.setEnabled(true);
												textFieldS2_2_4
														.setBackground(Color.WHITE);
												String strNewTime = sdf
														.format(tr.msNewTime);
												textFieldS2_2_4
														.setText(strNewTime);
												// try {
												// Thread.sleep(10000);
												// } catch (InterruptedException
												// e) {
												// TODO Auto-generated catch
												// block
												// e.printStackTrace();
												// }s

												// }
											}
										}.start();
									}
								});
						/** 패널S1_1 안에 라디오버튼S1_1_2 */
						// RadioButtonS1_1_2 = new JRadioButton("NO");
						RadioButtonS1_1_2.setFont(new Font("함초롬돋움", Font.BOLD,
								30));
						RadioButtonS1_1_2
								.setBorder(new EmptyBorder(0, 40, 0, 0));
						RadioButtonS1_1_2
								.setBackground(SystemColor.activeCaption);
						RadioButtonS1_1_2.setForeground(Color.BLACK);
						panelS1_1.add(RadioButtonS1_1_2);
						radiogroup.add(RadioButtonS1_1_2);
						RadioButtonS1_1_2.setSelected(true);

						RadioButtonS1_1_2
								.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {
										new Thread() {
											public void run() {
												label3.setText("동기에 실패했습니다.");
												RadioButtonS1_1_2
														.setForeground(Color.BLACK);
												RadioButtonS1_1_1
														.setForeground(Color.WHITE);
												labelS2_1_1
														.setForeground(Color.GRAY);
												labelS2_1_2
														.setForeground(Color.GRAY);
												labelS2_1_3
														.setForeground(Color.GRAY);
												labelS2_1_4
														.setForeground(Color.GRAY);
												textFieldS2_2_1
														.setEnabled(false);
												textFieldS2_2_1
														.setBackground(SystemColor.control);
												textFieldS2_2_1.setText("");
												textFieldS2_2_2
														.setEnabled(false);
												textFieldS2_2_2
														.setBackground(SystemColor.control);
												textFieldS2_2_2.setText("");
												textFieldS2_2_3
														.setEnabled(false);
												textFieldS2_2_3
														.setBackground(SystemColor.control);
												textFieldS2_2_3.setText("");
												textFieldS2_2_4
														.setEnabled(false);
												textFieldS2_2_4
														.setBackground(SystemColor.control);
												textFieldS2_2_4.setText("");
											}
										}.start();
									}
								});

					}
				}.start();

			}
		});
		// panel3 = new JPanel();
		// label3 = new JLabel("...");
		panel3.setBorder(new EmptyBorder(10, 0, 10, 0));
		TS.getContentPane().add(panel3, BorderLayout.SOUTH);
		panel3.setBackground(SystemColor.activeCaption);
		/** 패널3 안에 레이블3 */

		label3.setHorizontalAlignment(SwingConstants.CENTER);
		label3.setFont(new Font("함초롬돋움", Font.BOLD, 30));
		label3.setForeground(Color.BLACK);
		label3.setBackground(Color.WHITE);
		panel3.add(label3);
		new Thread() {
			public void run() {
				while (true) {
					/** 패널3 */

					/** 레이블3-서버시간과 현재시간 비교 */
					// tr.timereceive();
					/*
					 * if(TimeServer.equals("")) { time_packet.initTimeServer();
					 * TimeServer = time_packet.getTimeServer(); } else
					 * time_packet.setTimeServer(TimeServer);
					 */

					try {
						time_packet.timereceive();
						// time_packet.timesynchronize();

						DecimalFormat format = new DecimalFormat();
						format.applyLocalizedPattern("0.####");
						if (time_packet.offsetValue < 0) {
							label3.setText("사용자의 PC가 "
									+ format.format(time_packet.offsetValue
											* (-0.001)) + "초 빠릅니다.");
						} else {
							label3.setText("사용자의 PC가 "
									+ format.format(time_packet.offsetValue
											* -0.001) + "초 느립니다.");
						}
						
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						System.out.println("[NTP]TIMEOUT");
						// e.printStackTrace();
					}
				}
			}
		}.start();
		(new MyThread()).start();
	}
}