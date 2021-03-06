import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class BlockchainManager {
	
	public DBHelper db;
	public ArrayList<Block>block_chain;
	public int height;
	
	public BlockchainManager() {
		db = new DBHelper();
		block_chain = new ArrayList<Block>();
	}
	
	public void formBlockchain() {
		Scanner scan;
		String data;
		int i=0;
		try {
			scan = new Scanner(db.getBlockchainFile());
			while (scan.hasNextLine()) 
	        { 
	            data = scan.nextLine();
	            String[] parts = data.split(":");
	            block_chain.add(new Block(Integer.valueOf(parts[0]),parts[1],parts[2],parts[3]));
	            i++;
	        }
			height = i;
			scan.close(); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public int getHeight() {
		return this.height;
	}
	public void showBlockchain() {
		for(int i =0; i<block_chain.size(); i++) {
			System.out.println("-----------------------Block-----------------------");
			System.out.println("index = " + (i+1));
			System.out.println("transaction = " + block_chain.get(i).getTransaction());
			System.out.println("previousHash = " + block_chain.get(i).getPreviousHash());
			System.out.println("currentHash = " + block_chain.get(i).getCurrentHash());
		}
	}
}
