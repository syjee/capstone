package demo_gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

public class JTableTest2 {
	private int index;
	private DBHelper db = new DBHelper();
	  JTableTest2(int index) {
		   this.index = index;
	        JFrame jf = new JFrame();
	        JPanel jp = new JPanel();
	        
	        
	        jp.setBackground(Color.WHITE);
	        jp.setLayout(null);
	        
	        jf.setTitle("BLOCKCHAIN");
	        
	        // 1. 컴포넌트를 만들자.
	        JLabel title=new JLabel("BLOCKCHAIN-Node");
	        title.setFont(new Font("함초롬돋움", Font.BOLD, 30));
	        
	        JLabel jl1 = new JLabel("INDEX:");
	        JTextField jtf1 = new JTextField(10);
	        jtf1.setText(String.valueOf(index));
	        
	        JLabel jl2 = new JLabel("TIMESTACK:");
	        JTextField jtf2 = new JTextField(10);
	        
	        JLabel jl3 = new JLabel("MINER:");
	        JTextField jtf3 = new JTextField(10);
	        
	        JLabel jl4 = new JLabel("CURRENT HASH:");
	        JTextField jtf4 = new JTextField(10);
	        
	        JLabel jl5 = new JLabel("MERKLE ROOT:");
	        JTextField jtf5 = new JTextField(10);
	        
	        JLabel jl6 = new JLabel("NUM OF Txs:");
	        JTextField jtf6 = new JTextField(10);
	        
	        JLabel table_label = new JLabel("Tx's");
	        
	        String table_title[]= {"index","host"};
	    	String data[][]= {{"1","red"},{"2","orange"},{"3","yellow"},{"4","green"},{"5","blue"},{"6","navy"},{"7","purple"},{"1","red"},{"2","orange"},{"3","yellow"},{"4","green"},{"5","blue"},{"6","navy"},{"7","purple"}
	    	};
	        JTable table=new JTable(data,table_title);
			
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.getColumnModel().getColumn(0).setPreferredWidth(50);
			table.getColumnModel().getColumn(1).setPreferredWidth(232);
			
			DefaultTableCellRenderer tablecell=new DefaultTableCellRenderer();
			tablecell.setHorizontalAlignment(SwingConstants.CENTER);
			TableColumnModel cellmodel=table.getColumnModel();
			cellmodel.getColumn(0).setCellRenderer(tablecell);
			
			
			JScrollPane scroll=new JScrollPane(table);
			//scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			Dimension ds=new  Dimension(400, 200);
			scroll.setPreferredSize(ds);
	       
	
	        // 2. 컴포넌트를 컨테이너에 올리자.
	        
	        //jp.setLayout(new GridLayout(10, 3));
	        
	        
	        jp.add(title);
	       
	        
	        jp.add(jl1);jp.add(jtf1);
	        jp.add(jl2);jp.add(jtf2);
	        jp.add(jl3);jp.add(jtf3);
	        jp.add(jl4);jp.add(jtf4);
	        jp.add(jl5);jp.add(jtf5);
	        jp.add(jl6);jp.add(jtf6);
	        jp.add(table_label);
	        jp.add(scroll);
	        
	        title.setBounds(100, 30, 400,50);
	        jl1.setBounds(100, 77, 50, 50);
	        jtf1.setBounds(150, 95, 100, 20);
	        jl2.setBounds(100, 97, 100, 50);
	        jtf2.setBounds(180, 115, 100, 20);
	        jl3.setBounds(100, 117, 100, 50);
	        jtf3.setBounds(150, 135, 100, 20);
	        jl4.setBounds(100, 137, 100, 50);
	        jtf4.setBounds(200, 155, 100, 20);
	        jl5.setBounds(100, 157, 100, 50);
	        jtf5.setBounds(195, 175, 100, 20);
	        jl6.setBounds(100, 177, 100, 50);
	        jtf6.setBounds(180, 195, 100, 20);
	        
	        table_label.setBounds(100, 207, 100, 50);
	       
	        scroll.setBounds(100, 240, 300, 100);
	        // 3. 컨테이너를 프레임에 올리자
	        jf.add(jp);
	        
	        jf.setBounds(100, 100, 500, 400);
	        
	        jf.setVisible(true);
	        
	    }




}

