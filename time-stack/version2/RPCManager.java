import java.util.Scanner;

public class RPCManager extends Thread{
	public boolean shouldRun = true;
	public DBHelper db;
	public PeerNetworkManager pm;
	public RPC rpc;
	
	public RPCManager(PeerNetworkManager pm){
		this.db = new DBHelper();
		this.pm = pm;
		this.rpc = new RPC(pm);
	}
	
	public void request() {
		while(shouldRun){
			Scanner scan;
			String host="";
			try {				
				db.updateFile(db.getPeerListFile());
				scan = new Scanner(db.getIpListFile());
				while (scan.hasNextLine()) 
		        { 
		            host = scan.nextLine(); 
		            rpc.requestToPeer(host, 8016);
		        }
				scan.close(); 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("connectToPeer ( "+host+" ) detected an error...");
			}
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		rpc.start();
		
	}
}
