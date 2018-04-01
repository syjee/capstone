import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.PublicKey;
import java.util.Scanner;

public class PeerNetworkManager extends Thread{
	public static final int DEFAULT_PORT = 8017;
	public int term;
	public boolean shouldRun = true;
	public DBHelper db;
	public PeerNetwork pn;
	public Blockchain bc;
	public String subnet;
	
	public KeyManager km;
	
	public PeerNetworkManager(Blockchain bc){
		this.bc = bc;
		term = 600000;
		db = new DBHelper();
		pn = new PeerNetwork(bc);
	}
	
	public PeerNetworkManager(String subnet,Blockchain bc,KeyManager km){
		this.subnet = subnet;
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
	
	/*
	public void broadcast(Block newBlock) throws SocketException{
//		pn.broadcast(newBlock);
		byte[] buffer = ObjectToByte(newBlock);
		InetAddress address;
		
		DatagramSocket socket = new DatagramSocket(DEFAULT_PORT);
        socket.setBroadcast(true);
		try {
			address = InetAddress.getByName(subnet+"255");
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length,address,4445);
			
			System.out.println("[BroadCast]broadcast a Block");
			socket.send(packet);
	        socket.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	*/
	
	public void broadcast(PublicKey key){
		pn.broadcast(key);
    }
	
	public byte[] ObjectToByte(Object obj){
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = null;
		
		try {
			out = new ObjectOutputStream(bos);
			out.writeObject(obj);
			
			out.close();
			bos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] buffer = bos.toByteArray();
		
		return buffer;
	}
}
