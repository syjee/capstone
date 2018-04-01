import java.security.PublicKey;
import java.util.Scanner;

public class PeerNetworkManager extends Thread{
	public int term;
	public boolean shouldRun = true;
	public DBHelper db;
	public PeerNetwork pn;
	public Blockchain bc;
	
	public KeyManager km;
	
	public PeerNetworkManager(Blockchain bc){
		this.bc = bc;
		term = 600000;
		db = new DBHelper();
		pn = new PeerNetwork(bc);
	}
	
	public PeerNetworkManager(Blockchain bc,KeyManager km){
		this.bc = bc;
		term = 600000;
		db = new DBHelper();
		this.km = km;
		pn = new PeerNetwork(bc,km);
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		pn.start();
		while(shouldRun){
			Scanner scan;
			String host="";
			
			try {				
//				db.updateFile(db.getPeerListFile());
				scan = new Scanner(db.getIpListFile());
				while (scan.hasNextLine()) 
		        { 
		            host = scan.nextLine(); 
		            if(!db.integrityCheck(db.getPeerListFile(), host))
		            	pn.connectToPeer(host, 8015);
		        }
				scan.close(); 
				Thread.sleep(term);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("connectToPeer ( "+host+" ) detected an error...");
			}
		}
	}
	
	public void broadcast(Block newBlock){
		pn.broadcast(newBlock);
    }
	
	public void broadcast(PublicKey key){
		pn.broadcast(key);
    }
}
