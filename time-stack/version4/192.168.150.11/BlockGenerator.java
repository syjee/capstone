import java.net.Socket;
import java.security.NoSuchAlgorithmException;

public class BlockGenerator extends Thread{
	
	public String previousHash;
	public Block newBlock;
	public Block request;
	public int difficulty;
	public Socket socket;
	
	public BlockchainManager bm; 
	public boolean shouldRun = true;
	public PeerNetworkManager pm;
	public BlockNetworkManager bnm;
	public KeyManager km;
	public Blockchain bc;

	/*
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
	*/
	
	public BlockGenerator(Block request,PeerNetworkManager pm,Socket socket,Blockchain bc,KeyManager km,BlockNetworkManager bnm) {
		// TODO Auto-generated constructor stub
		this.request = request;
		this.bm = new BlockchainManager();
		this.pm = pm;
		this.difficulty = request.getDifficulty();
		this.socket = socket;
		this.km = km;
		this.bc = bc;
		
		this.bnm = bnm;
	}
	
	public void run() {
		while(shouldRun) {
			try {
//				String str = km.decrypt(request.getStringTransaction(), km.getPublicKey(socket.getInetAddress().toString()));
//				if(str == null)
//					throw new NoSuchAlgorithmException();
			//	else request.getTransaction().setTransaction(str);
				
				if(bm.getHeight() != 0)
					newBlock = new Block(request.getArrayTransaction(),bm.getRecentBlock().getCurrentHash());
				else if(bm.getHeight() == 0)
					newBlock = new Block(request.getArrayTransaction());
				
				if(newBlock.mineBlock(difficulty)) {
//					bc.addToBlockQueue(newBlock);
//					pm.broadcast(newBlock);
					bnm.broadcast(newBlock);
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
