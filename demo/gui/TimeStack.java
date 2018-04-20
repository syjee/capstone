package TimeStack;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

import java.awt.Component;
import java.awt.Dialog;

import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import java.util.Calendar;
import java.util.Vector;
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
import javax.swing.JTextField;

public class TimeStack{
	Calendar calendar;
	public SimpleDateFormat sdf;
	
	private JFrame TS;
	private JPanel panel1;
	private JLabel label1_1;
	private JLabel label1_2;
	private JPanel panel2;
	private JButton button2_1;
	private JButton button2_2;
	private JPanel panel3;
	private JLabel label3;
	
	private JFrame BCTimeSync;
	private JPanel panelBC1;
	private JButton buttonBC1_1;
	private JButton buttonBC1_2;
	private JPanel panelBC2;
	private JList listBC2_1;
	private JList listBC2_2;
	
	private JFrame STimeSync;
	private JPanel panelS1;
	private JPanel panelS1_0;
	private JLabel labelS1_1;
	private JPanel panelS1_1;
	private JRadioButton RadioButtonS1_1_1;
	private JRadioButton RadioButtonS1_1_2;
	private JPanel panelS2;
	private JPanel panelS2_1;
	private JLabel labelS2_1_1;
	private JLabel labelS2_1_2;
	private JLabel labelS2_1_3;
	private JLabel labelS2_1_4;
	private JPanel panelS2_2;
	private JTextField textFieldS2_2_1;
	private JTextField textFieldS2_2_2;
	private JTextField textFieldS2_2_3;
	private JTextField textFieldS2_2_4;
	
	public TimeReception tr;
	public TimeSyncronization ts;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimeStack window = new TimeStack();
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
	public TimeStack() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	/**현재시간 출력(시계)*/
	class MyThread extends Thread{
		public void run() {
			int year, month, day, num;
			String daynum = null;
			String date;
			
			while(true) {
				calendar = Calendar.getInstance();
				year = calendar.get(Calendar.YEAR);
				month = calendar.get(Calendar.MONTH)+1;
				day = calendar.get(Calendar.DAY_OF_MONTH);
				num = calendar.get(Calendar.DAY_OF_WEEK);
				switch(num) {
				case 1: 
					daynum = "일"; break;
				case 2: 
					daynum = "월"; break;
				case 3: 
					daynum = "화"; break;
				case 4: 
					daynum = "수"; break;
				case 5: 
					daynum = "목"; break;
				case 6: 
					daynum = "금"; break;
				case 7: 
					daynum = "토"; break;
				default:
					break;
				}
				date = (year+"년 "+month+"월 "+day+"일 "+daynum+"요일 ");
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
		
		/**메인 프레임(창)*/
		TS = new JFrame();
		TS.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\dev\\workspace\\GUI\\TS1.png"));
		TS.setResizable(false);
		//BorderLayout borderLayout = (BorderLayout) TS.getContentPane().getLayout();
		TS.setBounds(700, 300, 900, 800);
		TS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TS.setTitle("Time-Stack 1.0");
		//TS.getContentPane().setBackground(Color.PINK);
		
		/**패널1*/
		panel1 = new JPanel();
		panel1.setBorder(new EmptyBorder(60, 0, 40, 0));
		TS.getContentPane().add(panel1, BorderLayout.NORTH);
		panel1.setBackground(Color.BLACK);
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		/**패널1 안에 레이블1_1*/
		label1_1 = new JLabel();
		label1_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel1.add(label1_1);
		label1_1.setFont(new Font("함초롬돋움", Font.BOLD, 35));
		label1_1.setBackground(Color.BLACK);
		label1_1.setForeground(Color.WHITE);
		label1_1.setVerticalAlignment(SwingConstants.CENTER);
		label1_1.setHorizontalAlignment(SwingConstants.CENTER);
		label1_1.setOpaque(true);
		/**패널1 안에 레이블1_2*/
		label1_2 = new JLabel();
		label1_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel1.add(label1_2);
		label1_2.setFont(new Font("함초롬돋움", Font.BOLD, 120));
		label1_2.setBackground(Color.BLACK);
		label1_2.setForeground(Color.WHITE);
		label1_2.setVerticalAlignment(SwingConstants.CENTER);
		label1_2.setHorizontalAlignment(SwingConstants.CENTER);
		label1_2.setOpaque(true);
		
		/**패널2*/
		panel2 = new JPanel();
		panel2.setBackground(Color.WHITE);
		panel2.setForeground(Color.BLACK);
		panel2.setBorder(new EmptyBorder(45, 110, 45, 110));
		TS.getContentPane().add(panel2, BorderLayout.CENTER);
		panel2.setLayout(new GridLayout(0, 1, 0, 40));
		/**패널2 안에 버튼2_1*/
		button2_1 = new JButton("블록체인 시간 동기화");
		button2_1.setBackground(Color.WHITE);
		button2_1.setForeground(Color.BLACK);
		button2_1.setFont(new Font("함초롬돋움", Font.BOLD, 35));
		button2_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		button2_1.setBorder(new LineBorder(SystemColor.activeCaption, 5, true));
		//button2_1.setMargin(new Insets(12, 0, 12, 0));
		panel2.add(button2_1);
		
		button2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/**블록체인 시간동기화 프레임(창)*/
				BCTimeSync = new JFrame();
				BCTimeSync.setResizable(false);
				//BorderLayout borderLayout = (BorderLayout) BCNTimeSync.getContentPane().getLayout();
				BCTimeSync.setBounds(1500, 400, 1100, 1000);
				BCTimeSync.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				BCTimeSync.setTitle("블록체인 시간 동기화");
				BCTimeSync.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\dev\\workspace\\GUI\\TS1.png"));
				BCTimeSync.getContentPane().setBackground(Color.WHITE);
				BCTimeSync.setVisible(true);
				/**패널 BC1*/
				panelBC1 = new JPanel();
				panelBC1.setBorder(new EmptyBorder(30, 80, 30, 80));
				BCTimeSync.getContentPane().add(panelBC1, BorderLayout.NORTH);
				panelBC1.setBackground(Color.WHITE);
				panelBC1.setLayout(new BoxLayout(panelBC1, BoxLayout.X_AXIS));
				panelBC1.setLayout(new GridLayout(1, 0, 180, 0));
				/**패널BC1 안에 버튼BC1_1*/
				buttonBC1_1 = new JButton("Transaction 생성");
				buttonBC1_1.setBackground(Color.WHITE);
				buttonBC1_1.setForeground(Color.BLACK);
				buttonBC1_1.setFont(new Font("함초롬돋움", Font.BOLD, 30));
				buttonBC1_1.setAlignmentX(Component.CENTER_ALIGNMENT);
				LineBorder BC1_1BorderA = new LineBorder(SystemColor.activeCaption, 5, true);
				EmptyBorder BC1_1BorderB = new EmptyBorder(20, 20, 20, 20); 
				buttonBC1_1.setBorder(new CompoundBorder(BC1_1BorderA, BC1_1BorderB));
				panelBC1.add(buttonBC1_1);
				buttonBC1_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						//
					}
				});
				/**패널BC1 안에 버튼BC1_2*/
				buttonBC1_2 = new JButton("Transaction 전송");
				buttonBC1_2.setBackground(Color.WHITE);
				buttonBC1_2.setForeground(Color.BLACK);
				buttonBC1_2.setFont(new Font("함초롬돋움", Font.BOLD, 30));
				buttonBC1_2.setAlignmentX(Component.CENTER_ALIGNMENT);
				LineBorder BC1_2BorderA = new LineBorder(SystemColor.activeCaption, 5, true);
				EmptyBorder BC1_2BorderB = new EmptyBorder(20, 20, 20, 20); 
				buttonBC1_2.setBorder(new CompoundBorder(BC1_2BorderA, BC1_2BorderB));
				panelBC1.add(buttonBC1_2);
				
				/**패널 BC2*/
				panelBC2 = new JPanel();
				panelBC2.setBackground(Color.WHITE);
				EmptyBorder BC2BorderA = new EmptyBorder(25, 25, 25, 25); 
				LineBorder BC2BorderB = new LineBorder(Color.GRAY, 5, true);
				panelBC2.setBorder(new CompoundBorder(BC2BorderA, BC2BorderB));
				BCTimeSync.getContentPane().add(panelBC2, BorderLayout.CENTER);
				panelBC2.setLayout(new BoxLayout(panelBC2, BoxLayout.X_AXIS));
				
			}
		});
		/**패널2 안에 버튼2_2*/
		button2_2 = new JButton("서버시간 동기화");
		button2_2.setForeground(Color.BLACK);
		button2_2.setBackground(Color.WHITE);
		button2_2.setFont(new Font("함초롬돋움", Font.BOLD, 35));
		button2_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		button2_2.setBorder(new LineBorder(SystemColor.activeCaption, 5, true));
		//button2_2.setMargin(new Insets(12, 0, 12, 0));
		panel2.add(button2_2);
		
		button2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/**서버시간 동기화 프레임(창)*/
				STimeSync = new JFrame();
				STimeSync.setResizable(false);
				//BorderLayout borderLayout = (BorderLayout) STimeSync.getContentPane().getLayout();
				STimeSync.setBounds(1500, 400, 900, 800);
				STimeSync.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				STimeSync.setTitle("서버시간 동기화");
				STimeSync.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\dev\\workspace\\GUI\\TS1.png"));
				STimeSync.getContentPane().setBackground(Color.WHITE);
				STimeSync.setVisible(true);
				
				/**패널 S1*/
				panelS1 = new JPanel();
				panelS1.setBorder(new EmptyBorder(25, 0, 25, 0));
				STimeSync.getContentPane().add(panelS1, BorderLayout.NORTH);
				panelS1.setBackground(Color.WHITE);
				//panelS1.setLayout(new BoxLayout(panelS1, BoxLayout.X_AXIS));
				//panelS1.setLayout(new GridLayout(1, 0, 10, 0));
				/**패널S1 안에 패널 S1_0*/
				panelS1_0 = new JPanel();
				LineBorder S1_0BorderA = new LineBorder(Color.DARK_GRAY, 4, true);
				EmptyBorder S1_0BorderB = new EmptyBorder(20, 110, 20, 110); 
				panelS1_0.setBorder(new CompoundBorder(S1_0BorderA, S1_0BorderB));
				panelS1_0.setBackground(SystemColor.activeCaption);
				panelS1.add(panelS1_0);
				/**패널S1_0 안에 레이블S1_1*/
				labelS1_1 = new JLabel("시간 동기 활성화");
				labelS1_1.setAlignmentX(Component.CENTER_ALIGNMENT);
				panelS1_0.add(labelS1_1);
				labelS1_1.setFont(new Font("함초롬돋움", Font.BOLD, 30));
				labelS1_1.setBackground(SystemColor.activeCaption);
				labelS1_1.setForeground(Color.BLACK);
				labelS1_1.setVerticalAlignment(SwingConstants.CENTER);
				labelS1_1.setHorizontalAlignment(SwingConstants.CENTER);
				labelS1_1.setOpaque(true);
				/**패널S1_0 안에 패널 S1_1*/
				panelS1_1 = new JPanel();
				panelS1_1.setBorder(new EmptyBorder(0, 100, 0, 0));
				//panelS1_1.getContentPane().add(panelS1_1, BorderLayout.NORTH);
				panelS1_1.setBackground(SystemColor.activeCaption);
				panelS1_0.add(panelS1_1);
				//panelS1.setLayout(new BoxLayout(panelS1_1, BoxLayout.X_AXIS));
				//panelS1_1.setLayout(new GridLayout(1, 0, 0, 0));
				ButtonGroup radiogroup = new ButtonGroup();
				//radiogroup.add(RadioButtonS1_1_1);
				//radiogroup.add(RadioButtonS1_1_2);
				/**패널S1_1 안에 라디오버튼S1_1_1*/
				RadioButtonS1_1_1 = new JRadioButton("YES");
				RadioButtonS1_1_1.setFont(new Font("함초롬돋움", Font.BOLD, 30));
				RadioButtonS1_1_1.setBackground(SystemColor.activeCaption);
				RadioButtonS1_1_1.setForeground(Color.WHITE);
				panelS1_1.add(RadioButtonS1_1_1);
				radiogroup.add(RadioButtonS1_1_1);
				RadioButtonS1_1_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						/**시간 동기화 수행*/
						tr.timesynchronize();
						label3.setText("동기화 되었습니다.");
						RadioButtonS1_1_1.setForeground(Color.BLACK);
						RadioButtonS1_1_2.setForeground(Color.WHITE);
						labelS2_1_1.setForeground(Color.BLACK);
						labelS2_1_2.setForeground(Color.BLACK);
						labelS2_1_3.setForeground(Color.BLACK);
						labelS2_1_4.setForeground(Color.BLACK);
						textFieldS2_2_1.setEnabled(true);
						textFieldS2_2_1.setBackground(Color.WHITE);
						textFieldS2_2_1.setText(tr.timeServer);
						textFieldS2_2_2.setEnabled(true);
						textFieldS2_2_2.setBackground(Color.WHITE);
						textFieldS2_2_2.setText(Long.toString(tr.offsetValue));
						textFieldS2_2_3.setEnabled(true);
						textFieldS2_2_3.setBackground(Color.WHITE);
						textFieldS2_2_3.setText(Long.toString(tr.msNewTime));
						textFieldS2_2_4.setEnabled(true);
						textFieldS2_2_4.setBackground(Color.WHITE);
						String strNewTime = sdf.format(tr.msNewTime);
						textFieldS2_2_4.setText(strNewTime);
						
					}
				});
				/**패널S1_1 안에 라디오버튼S1_1_2*/
				RadioButtonS1_1_2 = new JRadioButton("NO");
				RadioButtonS1_1_2.setFont(new Font("함초롬돋움", Font.BOLD, 30));
				RadioButtonS1_1_2.setBorder(new EmptyBorder(0, 40, 0, 0));
				RadioButtonS1_1_2.setBackground(SystemColor.activeCaption);
				RadioButtonS1_1_2.setForeground(Color.BLACK);
				panelS1_1.add(RadioButtonS1_1_2);
				radiogroup.add(RadioButtonS1_1_2);
				RadioButtonS1_1_2.setSelected(true);
		
				RadioButtonS1_1_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						label3.setText("동기에 실패했습니다.");
						RadioButtonS1_1_2.setForeground(Color.BLACK);
						RadioButtonS1_1_1.setForeground(Color.WHITE);
						labelS2_1_1.setForeground(Color.GRAY);
						labelS2_1_2.setForeground(Color.GRAY);
						labelS2_1_3.setForeground(Color.GRAY);
						labelS2_1_4.setForeground(Color.GRAY);
						textFieldS2_2_1.setEnabled(false);
						textFieldS2_2_1.setBackground(SystemColor.control);
						textFieldS2_2_1.setText("");
						textFieldS2_2_2.setEnabled(false);
						textFieldS2_2_2.setBackground(SystemColor.control);
						textFieldS2_2_2.setText("");
						textFieldS2_2_3.setEnabled(false);
						textFieldS2_2_3.setBackground(SystemColor.control);
						textFieldS2_2_3.setText("");
						textFieldS2_2_4.setEnabled(false);
						textFieldS2_2_4.setBackground(SystemColor.control);
						textFieldS2_2_4.setText("");
					}
				});
				
				/**패널 S2*/
				panelS2 = new JPanel();
				panelS2.setBackground(Color.WHITE);
				EmptyBorder S2BorderA = new EmptyBorder(25, 25, 25, 25); 
				LineBorder S2BorderB = new LineBorder(SystemColor.activeCaption, 5, true);
				panelS2.setBorder(new CompoundBorder(S2BorderA, S2BorderB));
				STimeSync.getContentPane().add(panelS2, BorderLayout.CENTER);
				panelS2.setLayout(new BoxLayout(panelS2, BoxLayout.X_AXIS));
				/**패널S2 안에 패널 S2_1*/
				panelS2_1 = new JPanel();
				panelS2_1.setBorder(new EmptyBorder(40, 30, 40, 15));
				panelS2_1.setBackground(Color.WHITE);
				panelS2_1.setLayout(new GridLayout(0, 1, 0, 50));
				panelS2.add(panelS2_1);
				/**패널S2_1 안에 레이블S2_1_1*/
				labelS2_1_1 = new JLabel("TimeServer :");
				labelS2_1_1.setAlignmentX(Component.CENTER_ALIGNMENT);
				labelS2_1_1.setFont(new Font("함초롬돋움", Font.BOLD, 26));
				labelS2_1_1.setBackground(Color.WHITE);
				labelS2_1_1.setForeground(Color.GRAY);
				labelS2_1_1.setVerticalAlignment(SwingConstants.CENTER);
				labelS2_1_1.setHorizontalAlignment(SwingConstants.LEFT);
				labelS2_1_1.setOpaque(true);
				panelS2_1.add(labelS2_1_1);
				/**패널S2_1 안에 레이블S2_1_2*/
				labelS2_1_2 = new JLabel("Clock offset(ms) :");
				labelS2_1_2.setAlignmentX(Component.CENTER_ALIGNMENT);
				labelS2_1_2.setFont(new Font("함초롬돋움", Font.BOLD, 26));
				labelS2_1_2.setBackground(Color.WHITE);
				labelS2_1_2.setForeground(Color.GRAY);
				labelS2_1_2.setVerticalAlignment(SwingConstants.CENTER);
				labelS2_1_2.setHorizontalAlignment(SwingConstants.LEFT);
				labelS2_1_2.setOpaque(true);
				panelS2_1.add(labelS2_1_2);
				/**패널S2_1 안에 레이블S2_1_3*/
				labelS2_1_3 = new JLabel("New Time(ms) :");
				labelS2_1_3.setAlignmentX(Component.CENTER_ALIGNMENT);
				labelS2_1_3.setFont(new Font("함초롬돋움", Font.BOLD, 26));
				labelS2_1_3.setBackground(Color.WHITE);
				labelS2_1_3.setForeground(Color.GRAY);
				labelS2_1_3.setVerticalAlignment(SwingConstants.CENTER);
				labelS2_1_3.setHorizontalAlignment(SwingConstants.LEFT);
				labelS2_1_3.setOpaque(true);
				panelS2_1.add(labelS2_1_3);
				/**패널S2_1 안에 레이블S2_1_4*/
				labelS2_1_4 = new JLabel("New Time(HH:mm:ss) :");
				labelS2_1_4.setAlignmentX(Component.CENTER_ALIGNMENT);
				labelS2_1_4.setFont(new Font("함초롬돋움", Font.BOLD, 26));
				labelS2_1_4.setBackground(Color.WHITE);
				labelS2_1_4.setForeground(Color.GRAY);
				labelS2_1_4.setVerticalAlignment(SwingConstants.CENTER);
				labelS2_1_4.setHorizontalAlignment(SwingConstants.CENTER);
				labelS2_1_4.setOpaque(true);
				panelS2_1.add(labelS2_1_4);
				/**패널S2 안에 패널 S2_2*/
				panelS2_2 = new JPanel();
				panelS2_2.setBorder(new EmptyBorder(40, 15, 40, 30));
				panelS2_2.setLayout(new GridLayout(0, 1, 0, 50));
				panelS2_2.setBackground(Color.WHITE);
				panelS2.add(panelS2_2);
				/**패널S2_2 안에 텍스트필드S2_2_1*/
				textFieldS2_2_1 = new JTextField();
				//textFieldS2_2_1.setBackground(SystemColor.control);
				textFieldS2_2_1.setBorder(new LineBorder(Color.DARK_GRAY, 2));
				textFieldS2_2_1.setFont(new Font("함초롬돋움", Font.BOLD, 26));
				textFieldS2_2_1.setHorizontalAlignment(SwingConstants.CENTER);
				textFieldS2_2_1.setEditable(false);
				textFieldS2_2_1.setEnabled(false);
				panelS2_2.add(textFieldS2_2_1);
				/**패널S2_2 안에 텍스트필드S2_2_2*/
				textFieldS2_2_2 = new JTextField(20);
				//textFieldS2_2_2.setBackground(Color.WHITE);
				textFieldS2_2_2.setBorder(new LineBorder(Color.DARK_GRAY, 2));
				textFieldS2_2_2.setFont(new Font("함초롬돋움", Font.BOLD, 26));
				textFieldS2_2_2.setHorizontalAlignment(SwingConstants.CENTER);
				textFieldS2_2_2.setEditable(false);
				textFieldS2_2_2.setEnabled(false);
				panelS2_2.add(textFieldS2_2_2);
				/**패널S2_2 안에 텍스트필드S2_2_3*/
				textFieldS2_2_3 = new JTextField(20);
				//textFieldS2_2_3.setBackground(Color.WHITE);
				textFieldS2_2_3.setBorder(new LineBorder(Color.DARK_GRAY, 2));
				textFieldS2_2_3.setFont(new Font("함초롬돋움", Font.BOLD, 26));
				textFieldS2_2_3.setHorizontalAlignment(SwingConstants.CENTER);
				textFieldS2_2_3.setEditable(false);
				textFieldS2_2_3.setEnabled(false);
				panelS2_2.add(textFieldS2_2_3);
				/**패널S2_2 안에 텍스트필드S2_2_4*/
				textFieldS2_2_4 = new JTextField(20);
				//textFieldS2_2_4.setBackground(Color.WHITE);
				textFieldS2_2_4.setBorder(new LineBorder(Color.DARK_GRAY, 2));
				textFieldS2_2_4.setFont(new Font("함초롬돋움", Font.BOLD, 26));
				textFieldS2_2_4.setHorizontalAlignment(SwingConstants.CENTER);
				textFieldS2_2_4.setEditable(false);
				textFieldS2_2_4.setEnabled(false);
				panelS2_2.add(textFieldS2_2_4);
				
			}
		});
		
		/**패널3*/
		panel3 = new JPanel();
		panel3.setBorder(new EmptyBorder(10, 0, 10, 0));
		TS.getContentPane().add(panel3, BorderLayout.SOUTH);
		panel3.setBackground(SystemColor.activeCaption);
		/**패널3 안에 레이블3*/
		label3 = new JLabel("...");
		label3.setHorizontalAlignment(SwingConstants.CENTER);
		label3.setFont(new Font("함초롬돋움", Font.BOLD, 30));
		label3.setForeground(Color.BLACK);
		label3.setBackground(Color.WHITE);
		panel3.add(label3);
		/**레이블3-서버시간과 현재시간 비교*/
		tr.timereceive();
		DecimalFormat format = new DecimalFormat();
        format.applyLocalizedPattern("0.####");
		if(tr.offsetValue < 0) {
			label3.setText("사용자의 PC가 "+ format.format(tr.offsetValue*(-0.001)) +"초 빠릅니다.");
		} else {
			label3.setText("사용자의 PC가 "+ format.format(tr.offsetValue*-0.001) +"초 느립니다.");
		}
		
		(new MyThread()).start();
	}

}
