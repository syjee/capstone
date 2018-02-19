import java.security.NoSuchAlgorithmException;

public class BlockGenerator extends Thread{
	
	public String previousHash;
	public String currentHash;
	public Block newBlock;
	public String transaction;
	public int difficulty;
	
	public BlockchainManager bm;
	public DBHelper db;
	public boolean shouldRun = true;
	public PeerNetworkManager pm;
	
	public BlockGenerator(Transaction request,PeerNetworkManager pm) {
		// TODO Auto-generated constructor stub
		this.db = new DBHelper();
		this.transaction = request.getTransation();
		this.bm = new BlockchainManager();
		this.pm = pm;
		this.difficulty = 2;
	}
	
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	
	public void run() {
		while(shouldRun) {
			try {
				newBlock = new Block(bm.getHeight()+1,transaction,previousHash,currentHash);
				if(newBlock.mineBlock(difficulty)) pm.broadcast(newBlock);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			shouldRun = false;
		}
	}
}
