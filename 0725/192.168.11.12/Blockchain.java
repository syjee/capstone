import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Blockchain extends Thread{
	private ArrayList<Block>block_queue;
	private ArrayList<ArrayList<Block>>chains;
	public DBHelper db;
	public BlockchainManager bm;
	public boolean shouldRun = true;
	
	public Blockchain(){
		this.block_queue = new ArrayList<Block>();
		this.chains = new ArrayList<ArrayList<Block>>();
		this.db = new DBHelper();
		this.bm = new BlockchainManager();
	}
	
	public ArrayList<Block> getBlockQueue(){
		return this.block_queue;
	}
	
	public void addToBlockQueue(Block b,TimeManagement tm) {
//		try{
			block_queue.add(b);
			b.setIndex(bm.getHeight()+1);
			addToBlockchain(b,tm);
//		}catch(Exception e) {
//			System.out.println("Fail to Adding A Block to Blockchain File...");
//		}
	}
	
	public void addToBlockchain(Block b,TimeManagement tm) {
//		b.setIndex(bm.getHeight()+1);

		
		BlockchainManager bm = new BlockchainManager();
		if(bm.checkDuplicate(b)) {
			return;
		}
		tm.compare(b);
		db.addtoFile(db.getBlockchainFile(), b.getRawBlock());
		bm.formBlockchain();
		bm.showBlock(bm.getRecentBlock());
	}

	public void run() {
		while(shouldRun) {
			try {
				
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}