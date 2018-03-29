import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.border.EmptyBorder;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.awt.SystemColor;
import javax.swing.JButton;

public class TimeStack {

	private JFrame TS;
	private JPanel panel1;
	private JLabel label1_1;
	private JLabel label1_2;
	private JPanel panel2;
	private JButton button2_1;
	private JButton button2_3;
	private JButton button2_2;
	private JPanel panel3;
	private JLabel label3;

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
	//현재시간 출력(시계)
	class MyThread extends Thread{
		public void run() {
			int year, month, day, num;
			String daynum = null;
			String date;
			
			while(true) {
				Calendar calendar = Calendar.getInstance();
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
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); 
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
	
	private void initialize() {
		//---프레임(창)
		TS = new JFrame();
		TS.setResizable(false);
		//BorderLayout borderLayout = (BorderLayout) TS.getContentPane().getLayout();
		TS.setBounds(1100, 400, 900, 800);
		TS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TS.setTitle("Time-Stack 1.0");
		TS.getContentPane().setBackground(Color.PINK);
		
		//------패널1
		panel1 = new JPanel();
		panel1.setBorder(new EmptyBorder(50, 0, 35, 0));
		TS.getContentPane().add(panel1, BorderLayout.NORTH);
		panel1.setBackground(Color.BLACK);
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		//---패널1 안에 레이블1_1
		label1_1 = new JLabel();
		label1_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel1.add(label1_1);
		label1_1.setFont(new Font("함초롬돋움", Font.BOLD, 35));
		label1_1.setBackground(Color.BLACK);
		label1_1.setForeground(Color.WHITE);
		label1_1.setVerticalAlignment(SwingConstants.CENTER);
		label1_1.setHorizontalAlignment(SwingConstants.CENTER);
		label1_1.setOpaque(true);
		//---패널1 안에 레이블1_2
		label1_2 = new JLabel();
		label1_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel1.add(label1_2);
		label1_2.setFont(new Font("함초롬돋움", Font.BOLD, 120));
		label1_2.setBackground(Color.BLACK);
		label1_2.setForeground(Color.WHITE);
		label1_2.setVerticalAlignment(SwingConstants.CENTER);
		label1_2.setHorizontalAlignment(SwingConstants.CENTER);
		label1_2.setOpaque(true);
		
		//------패널2 
		panel2 = new JPanel();
		panel2.setBackground(Color.WHITE);
		panel2.setForeground(Color.BLACK);
		panel2.setBorder(new EmptyBorder(50, 150, 50, 150));
		TS.getContentPane().add(panel2, BorderLayout.CENTER);
	
		panel2.setLayout(new BorderLayout(100, 60));
		//---패널2 안에 버튼2_1
		button2_1 = new JButton("블록체인 노드 확인");
		button2_1.setBackground(Color.WHITE);
		button2_1.setForeground(Color.BLACK);
		button2_1.setFont(new Font("함초롬돋움", Font.BOLD, 35));
		button2_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel2.add(button2_1, BorderLayout.NORTH);
		//---패널2 안에 버튼2_2
		button2_2 = new JButton("Peer IPList 확인");
		button2_2.setForeground(Color.BLACK);
		button2_2.setBackground(Color.WHITE);
		button2_2.setFont(new Font("함초롬돋움", Font.BOLD, 35));
		button2_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel2.add(button2_2, BorderLayout.CENTER);
		//---패널2 안에 버튼2_3
		button2_3 = new JButton("시간 동기화");
		button2_3.setBackground(Color.WHITE);
		button2_3.setForeground(Color.BLACK);
		button2_3.setVerticalAlignment(SwingConstants.BOTTOM);
		button2_3.setFont(new Font("함초롬돋움", Font.BOLD, 35));
		button2_3.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel2.add(button2_3, BorderLayout.SOUTH);
		
		//------패널3
		panel3 = new JPanel();
		panel3.setBorder(new EmptyBorder(5, 0, 5, 0));
		TS.getContentPane().add(panel3, BorderLayout.SOUTH);
		panel3.setBackground(SystemColor.activeCaption);
		//---패널3 안에 레이블3
		label3 = new JLabel("사용자의 PC가 0.000 초 빠릅니다.");
		label3.setHorizontalAlignment(SwingConstants.CENTER);
		label3.setFont(new Font("함초롬돋움", Font.BOLD, 30));
		label3.setForeground(Color.BLACK);
		label3.setBackground(Color.WHITE);
		panel3.add(label3);
		
		(new MyThread()).start();
	}

}
