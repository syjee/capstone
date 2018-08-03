import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.PageAttributes;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class JTableTest extends JFrame  {
	
	public static void main(String[] args) {
		
        JFrame jf = new JFrame();
        JPanel jp = new JPanel();
        
        jf.setTitle("BLOCKCHAIN");
        
        
        jp.setBackground(Color.WHITE);
        jp.setLayout(null);
        
        
        
        JLabel title=new JLabel("BLOCKCHAIN");
        title.setFont(new Font("함초롬돋움", Font.BOLD, 30));
        
   

        //String table_title[]= {"index","block num"};
    	String data[][]= {{"1","red"},{"2","orange"},{"3","yellow"},{"4","green"},{"5","blue"},{"6","navy"},{"7","purple"}};
       // JTable table=new JTable(data,table_title);
    	JTable table = new JTable(new DefaultTableModel(new Object[]{"index", "block num"},20) {
    		public boolean isCellEditable(int i,int c) {
    			return false;
    		}
    	});

    	for(int i=0;i<data.length;i++) {
    		for(int j=0;j<data[0].length;j++) {
    			table.setValueAt(data[i][j], i, j);
    		}
    	}
        
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(332);

		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
				//int row = table.getSelectedRow();
				 // int col = table.getSelectedColumn();
				  //for (int i = 0; i < table.getColumnCount(); i++) {
				
				new JTableTest2();
				  //}

			}
		});

	
		
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
		
		title.setBounds(140, 10, 500,100);
		scroll.setBounds(45, 100, 400,250);
		
		jf.add(jp);
        
        jf.setBounds(100, 100, 500, 400);
        
        jf.setVisible(true);
		
	}
	

}








