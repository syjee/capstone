import java.net.Socket;
import java.security.NoSuchAlgorithmException;

public class BlockGenerator extends Thread{
	
	public String previousHash;
	public Block newBlock;
	public Transaction request;
	public int difficulty;
	public Socket socket;
	
	public BlockchainManager bm; 
	public boolean shouldRun = true;
	public PeerNetworkManager pm;
	public KeyManager km;
	public Blockchain bc;
	
	public BlockGenerator(Transaction request,PeerNetworkManager pm) {
		// TODO Auto-generated constructor stub
		this.request = request;
		this.bm = new BlockchainManager();
		this.pm = pm;
		this.difficulty = request.getDifficulty();
	}
	
	public BlockGenerator(Transaction request,PeerNetworkManager pm,Socket socket,Blockchain bc,KeyManager km) {
		// TODO Auto-generated constructor stub
		this.request = request;
		this.bm = new BlockchainManager();
		this.pm = pm;
		this.difficulty = request.getDifficulty();
		this.socket = socket;
		this.km = km;
		this.bc = bc;
	}
	
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	
	public void run() {
		while(shouldRun) {
			try {
/*				
				String str = km.decrypt(request.getTransaction());
				if(str == null)
					throw new NoSuchAlgorithmException();
				else request.setTransaction(str);
				*/
				String str = km.decrypt(request.getTransaction(), km.getPublicKey(socket.getInetAddress().toString()));
				if(str == null)
					throw new NoSuchAlgorithmException();
				else request.setTransaction(str);
				
				if(bm.getHeight() != 0)
					newBlock = new Block(request.getTransaction(),bm.getRecentBlock().getCurrentHash());
				else if(bm.getHeight() == 0)
					newBlock = new Block(request.getTransaction());
				
				if(newBlock.mineBlock(difficulty)) {
//					bc.addToBlockQueue(newBlock);
					pm.broadcast(newBlock);
				}
			} catch (NoSuchAlgorithmException e) {
				shouldRun = false;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("[MINE]Cannot mining a block...");
				shouldRun = false;
			}
			shouldRun = false;
		}
	}
}
