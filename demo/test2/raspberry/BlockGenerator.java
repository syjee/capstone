import java.security.NoSuchAlgorithmException;

public class BlockGenerator extends Thread{
	
	public String previousHash;
	public Block newBlock;
	public Transaction request;
	public int difficulty;
	
	public BlockchainManager bm; 
	public boolean shouldRun = true;
	public PeerNetworkManager pm;
	
	public BlockGenerator(Transaction request,PeerNetworkManager pm) {
		// TODO Auto-generated constructor stub
		this.request = request;
		this.bm = new BlockchainManager();
		this.pm = pm;
		this.difficulty = request.getDifficulty();
	}
	
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	
	public void run() {
		while(shouldRun) {
			try {
				if(bm.getHeight() != 0)
					newBlock = new Block(request.getTransaction(),bm.getRecentBlock().getCurrentHash());
				else if(bm.getHeight() == 0)
					newBlock = new Block(request.getTransaction());
				
				if(newBlock.mineBlock(difficulty)) pm.broadcast(newBlock);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			shouldRun = false;
		}
	}
}
