import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.security.PublicKey;
import java.util.Scanner;

public class PeerNetworkManager extends Thread{
	
	public static final int DEFAULT_PORT = 8017;
	
	public int term;
	public boolean shouldRun = true;
	public DBHelper db;
	public PeerNetwork pn;
	public Blockchain bc;
	
	public KeyManager km;
	public RPCManager rm;
	public BlockNetworkManager bnm;
	public TimeManagement tm;
	
	public PeerNetworkManager(Blockchain bc){
		this.bc = bc;
		term = 30000;
		db = new DBHelper();
		pn = new PeerNetwork(bc);
	}
	
	public PeerNetworkManager(Blockchain bc,KeyManager km,BlockNetworkManager bnm,TimeManagement tm){
		this.bc = bc;
		term = 10000;
		db = new DBHelper();
		this.km = km;
		this.bnm = bnm;
		
		this.rm = new RPCManager(this, bc, km, bnm);
		rm.start();
		
		pn = new PeerNetwork(bc,km,this,tm,rm);
		this.tm = tm;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		pn.start();
		while(shouldRun){
			Scanner scan;
			String host="";
			
			try {	
				
				scan = new Scanner(db.getIpListFile());
				while (scan.hasNextLine()) 
		        { 
		            host = scan.nextLine(); 
		            if(!db.integrityCheck(db.getPeerListFile(), host))
		            	pn.connectToPeer(host, 8015);
		        }
				scan.close();
				
//				pn.connectToPeer("192.168.150.11", 8015);
				Thread.sleep(term);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("connectToPeer ( "+host+" ) detected an error...");
			}
		}
	}
	
	public void broadcast(Block newBlock){
//		pn.broadcast(newBlock);
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutput out = new ObjectOutputStream(bos);
			out.writeObject(newBlock);
			byte[] b = bos.toByteArray();
			
			InetAddress host = InetAddress.getByName("192.168.150.255");
			DatagramSocket socket = new DatagramSocket();
			socket.setBroadcast(true);

			DatagramPacket packet = new DatagramPacket(b, b.length, host, DEFAULT_PORT);
			socket.send(packet);			
			socket.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
	
	public void broadcast(PublicKey key){
		pn.broadcast(key);
    }
	
	public void unicast(String host) throws Exception{
		Transaction t = new Transaction(3, km);
		Block newBlock = new Block(t);
		
		pn.unicast(host, newBlock);
	}
	
	public void unicast(String host,Block newBlock){
		pn.unicast(host,newBlock);
	}
}
