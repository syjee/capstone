import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

public class JTableTest3 {



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
        JFrame jf = new JFrame();
        JPanel jp = new JPanel();
        
        jf.setTitle("BLOCKCHAIN");
        
        
        jp.setBackground(Color.WHITE);
        jp.setLayout(null);
        
        JLabel title=new JLabel("Time Synchronization History");
        title.setFont(new Font("함초롬돋움", Font.BOLD, 30));
        
        
        String table_title[]= {"index","block num","time-stack","offset"};
    	String data[][]= {{"1","red","",""},{"2","orange","",""},{"3","yellow","",""},{"4","green","",""},{"1","red","",""},{"2","orange","",""},{"3","yellow","",""},{"4","green","",""},{"5","blue","",""},{"6","navy","",""},{"7","purple","",""},{"1","red","",""},{"2","orange","",""},{"3","yellow","",""},{"4","green","",""},{"5","blue","",""},{"6","navy","",""},{"7","purple","",""}
    	};
        JTable table=new JTable(data,table_title);
		
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);

		
		DefaultTableCellRenderer tablecell=new DefaultTableCellRenderer();
		tablecell.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel cellmodel=table.getColumnModel();
		cellmodel.getColumn(0).setCellRenderer(tablecell);
		
		
		JScrollPane scroll=new JScrollPane(table);
		//scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		Dimension ds=new  Dimension(400, 200);
		scroll.setPreferredSize(ds);
		
		jp.add(title);
		jp.add(scroll);
		
		title.setBounds(30, 10, 500,100);
		scroll.setBounds(45, 100, 400,250);
		
		jf.add(jp);
        
        jf.setBounds(100, 100, 500, 400);
        
        jf.setVisible(true);
		
	}


}
