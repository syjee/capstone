import java.util.Scanner;

public class RPCManager extends Thread{
	public int term;
	public boolean shouldRun = true;
	public DBHelper db;
	public RPC rpc;
	public PeerNetworkManager pm;
	public Blockchain bc;
	
	public RPCManager(PeerNetworkManager pm){
		this.pm = pm;
		term = 600000;
		db = new DBHelper();
		rpc = new RPC(pm);
	}
	
	public RPCManager(PeerNetworkManager pm,Blockchain bc){
		this.pm = pm;
		term = 600000;
		db = new DBHelper();
		this.bc = bc;
		rpc = new RPC(pm,bc);
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		rpc.start();
		while(shouldRun){
			Scanner scan;
			String host="";
			
			try {				
//				db.updateFile(db.getPeerListFile());
				scan = new Scanner(db.getPeerListFile());
				while (scan.hasNextLine()) 
		        { 
		            host = scan.nextLine(); 
		            if(!db.integrityCheck(db.getRPCListFile(), host))
		            	rpc.connectToPeer(host, 8016);
		        }
				scan.close(); 
				Thread.sleep(term);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("connectToPeer ( "+host+" ) detected an error...");
			}
		}
	}
	
	public void request(){
		rpc.request();
    }
	
	public void request(int difficulty){
		rpc.request(difficulty);
    }
	
}
