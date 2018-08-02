import java.util.Scanner;

public class RPCManager extends Thread{
	public int term;
	public boolean shouldRun = true;
	public DBHelper db;
	public RPC rpc;
	public PeerNetworkManager pm;
	public KeyManager km;
	public Blockchain bc;
	public BlockNetworkManager bnm;
	
	public RPCManager(PeerNetworkManager pm,Blockchain bc,KeyManager km,BlockNetworkManager bnm){
		this.bc = bc;
		this.pm = pm;
		term = 300000;
		db = new DBHelper();
		this.km = km;
		this.bnm = bnm;
		rpc = new RPC(pm,bc,km,bnm);
		
		
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
		//	e1.printStackTrace();
		}
		// TODO Auto-generated method stub
		rpc.start();
		while(shouldRun){
			Scanner scan;
			String host="";
			
			try {				
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
	
	public void request(int difficulty) throws Exception{
		rpc.request(difficulty);
    }
	
	public void request(Block newBlock){
		rpc.request(newBlock);
	}
	
}
